package com.ericsson.mssp.approval.dao.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.exception.ApprovalStatusException;
import com.ericsson.mssp.approval.forms.ApprovalDetails;
import com.ericsson.mssp.approval.forms.CheckListElements;
import com.ericsson.mssp.approval.forms.ExecSummary;
import com.ericsson.mssp.approval.forms.ServiceFTEReport;
import com.ericsson.mssp.approval.forms.SolutionSummary;
import com.ericsson.mssp.approval.forms.TNTReport;
import com.ericsson.mssp.approval.dao.IApprovalDAO;
import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.dto.InitiateApprovalProcessDTO;
import com.ericsson.mssp.common.dto.NotificationDTO;
import com.ericsson.mssp.common.dto.SearchDTO;
import com.ericsson.mssp.common.entity.CheckListMaster;
import com.ericsson.mssp.common.entity.CheckLists;
import com.ericsson.mssp.common.entity.Country;
import com.ericsson.mssp.common.entity.Opportunity;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.OpportunityLocation;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionADR;
import com.ericsson.mssp.common.entity.SolutionApprovalStatus;
import com.ericsson.mssp.common.entity.SolutionApprover;
import com.ericsson.mssp.common.entity.Status;
import com.ericsson.mssp.common.entity.TNTDetail;
import com.ericsson.mssp.common.entity.TTPartitionDetail;
import com.ericsson.mssp.common.entity.TTPartitionName;
import com.ericsson.mssp.common.entity.TTPlanning;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.notification.service.INotificationService;


import java.util.Date;

@Repository
public class ApprovalDAOImpl extends BaseDAOImpl implements IApprovalDAO ,MSSPConstants{
	
	public static Logger logger = Logger.getLogger(ApprovalDAOImpl.class);
	
	public NotificationDTO submitSolution(ApprovalDetails appDetails) throws ApprovalStatusException {
		String nextApprover=null;
		NotificationDTO notificationDTO=new NotificationDTO();
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		int solutionId=appDetails.getSolutionId();
		try{
			if(StringUtils.isBlank(appDetails.getLevel1Approver())||StringUtils.isBlank(appDetails.getLevel2Approver())||
					StringUtils.isBlank(appDetails.getLevel3Approver())){
				throw new ApprovalStatusException(MSSPConstants.APPROVAL_LEVEL_ERR+": Solution Id ="+solutionId);
			}
			List<Solution> solutionList = session.createQuery(" from Solution  where solutionId="+ solutionId).list();
			Solution solution=solutionList.get(0);
			if(!SOLUTION_STATUS_DRAFT.equals(solution.getStatus().getStatusId()) && !appDetails.isApproverListChangeInitiated()){
				throw new ApprovalStatusException(MSSPConstants.NON_DRAFT_SOL_SUBMIT_ERR +": Solution Id ="+solutionId);
			}
			//solution.getStatus().setStatusId(SOLUTION_STATUS_DRAFT+1);
			Status status=new Status();
			status.setStatusId(MSSPConstants.SOLUTION_STATUS_SUBMIT);
			solution.setStatus(status);
			session.saveOrUpdate(solution);
		   	
			SolutionApprover rejectedSequenceApprover=checkRejectedSequence(session,solutionId);
			if (rejectedSequenceApprover != null) {
				int rejectedSeqId = rejectedSequenceApprover.getSequenceId();
				
				switch (rejectedSeqId) {
				case 1:
					persistApprover(session,appDetails.getLevel1Approver(),1,solutionId,true,rejectedSequenceApprover);
					persistApprover(session,appDetails.getLevel2Approver(),2,solutionId,false,null);
					persistApprover(session,appDetails.getLevel3Approver(),3,solutionId,false,null);
					break;
				case 2:
					persistApprover(session,appDetails.getLevel1Approver(),1,solutionId,false,null);
					persistApprover(session,appDetails.getLevel2Approver(),2,solutionId,true,rejectedSequenceApprover);
					persistApprover(session,appDetails.getLevel3Approver(),3,solutionId,false,null);
					break;
				case 3:
					boolean signumEquals=persistApprover(session,appDetails.getLevel1Approver(),1,solutionId,false,null);
					//persistApprover(session,appDetails.getLevel2Approver(),2,solutionId,!signumEquals,null);
					persistApprover(session,appDetails.getLevel2Approver(),2,solutionId,false,null);
					persistApprover(session,appDetails.getLevel3Approver(),3,solutionId,true,rejectedSequenceApprover);
					break;
				}
			}else{
				//Rejection sequence not found
				String hql = "select sa.solutionApproverId from SolutionApprover sa where sa.solution.solutionId=" + solutionId;						 
				List<Integer> ids = getHibernateTemplate().find(hql);	
				for(int i=0;i<MSSPConstants.TOTAL_APPROVAL_LEVEL;i++){
					String approver=null;
					switch(i){
					case 0:approver=appDetails.getLevel1Approver();
					       break;
					case 1:approver=appDetails.getLevel2Approver();
				           break;
					case 2:approver=appDetails.getLevel3Approver();
				           break;	
					}
					
					
					if(!appDetails.isApproverListChangeInitiated()){
						SolutionApprover  solnApprover=new SolutionApprover();
						solnApprover.setActiveId(true);
						solnApprover.setSequenceId(i+1);
						solnApprover.setApprover(approver);
						solnApprover.setSolution(solution);
						solnApprover.setRequestDate(new Date());
						session.saveOrUpdate(solnApprover);
					}else{
						 SolutionApprover  solnApprover = new SolutionApprover();
						 if(ids.size()>0){
							 solnApprover.setSolutionApproverId(ids.get(i));
						 }
						 solnApprover.setActiveId(true);
						 solnApprover.setSequenceId(i+1);
						 solnApprover.setApprover(approver);
						 solnApprover.setSolution(solution);
						 solnApprover.setRequestDate(new Date());
						 session.saveOrUpdate(solnApprover);					
					}
				}
			}
		persistCheckLists(session,appDetails,solution);	
		nextApprover=identifyNextApprover(session,appDetails.getSolutionId());	
		notificationDTO.setSolutionId(solutionId);
		notificationDTO.setNextApprover(nextApprover);
		notificationDTO.setNotificationType(INotificationService.NOTIFICATION_TYPE_SUBMIT);
		notificationDTO.setPrevApprover(null);
		notificationDTO.setSolutionCreator(solution.getCreatedBy());
		tx.commit();
		}catch(ApprovalStatusException e){
			logger.error("Exception ApprovalDAOImpl[submitSolution] -User Submitted non draft solution",e);
			tx.rollback();
			logger.info("Successfully rolled back");
			throw e;
		}catch(Exception e){
			logger.error("Exception ApprovalDAOImpl[submitSolution] ",e);
			tx.rollback();
			logger.info("Successfully rolled back");
			ApprovalStatusException ex=new ApprovalStatusException();
			ex.initCause(e);
			throw ex;
		}finally{
			session.close();
		}
		return notificationDTO;
		
	}
	
	private SolutionApprover checkRejectedSequence(Session session, int solutionId){
		SolutionApprover rejectedSequenceApprover=null;
		List<SolutionApprover> solutionApproverList =session.createQuery("from SolutionApprover t where t.activeId=0 "+" and t.solution.solutionId="+solutionId+"order by t.solutionApproverId desc").list();
		rejectedSequenceApprover=null;
		if(solutionApproverList!=null && solutionApproverList.size()>=1 ){
			rejectedSequenceApprover=solutionApproverList.get(0);
		}
		return rejectedSequenceApprover;
	}
	
	private boolean persistApprover(Session session, String inputApprover, int seqId, int solutionId, boolean byPassSignum,SolutionApprover rejectedSequenceApprover) {
		boolean signumEquals=true;
		if(rejectedSequenceApprover==null){
			List<SolutionApprover> approverList = session.createQuery(
					"from SolutionApprover t where t.activeId=1 "
							+ "and t.sequenceId=" + seqId
							+ " and t.solution.solutionId=" + solutionId
							+ " order by t.solutionApproverId desc)").list();
			SolutionApprover sequenceApprover = approverList.get(0);
			
			if (!byPassSignum) {
				if (!sequenceApprover.getApprover().equalsIgnoreCase(inputApprover)) {
					SolutionApprover approver = new SolutionApprover();
					approver.setActiveId(true);
					approver.setApprover(inputApprover);
					approver.setRequestDate(new Date());
					approver.setSequenceId(sequenceApprover.getSequenceId());
					approver.setSolution(sequenceApprover.getSolution());
					// approver.setSolutionApprovalStatuses(solutionApprovalStatuses);
					session.saveOrUpdate(approver);
					sequenceApprover.setActiveId(false);
					session.saveOrUpdate(sequenceApprover);
					signumEquals=false;
				}
			}else{
				SolutionApprover approver = new SolutionApprover();
				approver.setActiveId(true);
				approver.setApprover(inputApprover);
				approver.setRequestDate(new Date());
				approver.setSequenceId(sequenceApprover.getSequenceId());
				approver.setSolution(sequenceApprover.getSolution());
				// approver.setSolutionApprovalStatuses(solutionApprovalStatuses);
				session.saveOrUpdate(approver);
				sequenceApprover.setActiveId(false);
				session.saveOrUpdate(sequenceApprover);
			}	
		}else{
			 // if(byPassSignum){
					SolutionApprover approver = new SolutionApprover();
					approver.setActiveId(true);
					approver.setApprover(inputApprover);
					approver.setRequestDate(new Date());
					approver.setSequenceId(rejectedSequenceApprover.getSequenceId());
					approver.setSolution(rejectedSequenceApprover.getSolution());
					session.saveOrUpdate(approver);
					rejectedSequenceApprover.setActiveId(false);
					session.saveOrUpdate(rejectedSequenceApprover);
				//}
			}
		return signumEquals;
	}
	
	public int retrieveSolutionStatus(int solutionId){
		Session session=getSession();
		List<Solution> solutionList = session.createQuery(" from Solution  where solutionId="+ solutionId).list();
		Solution solution=solutionList.get(0);
		int statusId=solution.getStatus().getStatusId();
		return statusId;
	}
	
	
	public List<SolutionApprover> retrieveExistingApproversForSolution(int solutionId) {
		
		Session session=getSession();
		List<SolutionApprover> existingApproverList=new ArrayList<SolutionApprover>();
		try{
		List<SolutionApprover> existingActiveApproverList =session.createQuery("from SolutionApprover t1  where t1.solution.solutionId="+solutionId
					 +" and t1.activeId=1   group by approver   order by t1.sequenceId").list();
		int count=0;
		SolutionApprover rejectedSequenceApprover=checkRejectedSequence(session,solutionId);
		if(existingActiveApproverList.size()<3 && rejectedSequenceApprover!=null){
			for(int i=0;i<3;i++){
				if(count<existingActiveApproverList.size()){
				if(new Integer(i+1).equals(existingActiveApproverList.get(count).getSequenceId())){
					existingApproverList.add(existingActiveApproverList.get(count));
					count++;
				}
				}if(new Integer(i+1).equals(rejectedSequenceApprover.getSequenceId())){
					existingApproverList.add(rejectedSequenceApprover);
				}
			}
		}else{
			existingApproverList=existingActiveApproverList;
		}
		}/*catch(Exception e){
			logger.error("Exception ApprovalDAOImpl[retrieveExistingApproversForSolution]:: ",e);
			ApprovalStatusException ex=new ApprovalStatusException();
			ex.initCause(e);
			throw ex;
		}*/
		finally{
			session.close();
		}
		return existingApproverList;
	}
	
	public String identifyMailReceiver(int solutionId) throws DAOException{
		String query="select * from SolutionApprover t1  where t1.SolutionID= "+solutionId 
		 + " and t1.SolutionApproverID NOT IN (select t2.SolutionApproverID "
		+" from SolutionApprovalStatus t2  where t2.SolutionApproverID=t1.SolutionApproverID) "
		+" order by t1.SequenceID ASC ";
		
		
		
		String hbQuery="from SolutionApprover t1 where t1.solution.solutionId="+solutionId 
		+" and t1.activeId="+1+" and t1.solutionApproverId NOT IN (select t2.solutionApprover.solutionApproverId from SolutionApprovalStatus t2 , SolutionApprover t3 "
		+" where t2.solutionApprover.solutionApproverId=t3.solutionApproverId) "
		+"order by t1.sequenceId ";
		
		List<SolutionApprover> approverLIst = getHibernateTemplate()
				.find(hbQuery);
	    
		SolutionApprover app=approverLIst.get(0);
		String signum=app.getApprover();
		
		/*String signum="";
		SQLQuery query1 = getSession().createSQLQuery(query);
		List dtos = query1.list();
		if(dtos.size()>=1){
			Object [] element = (Object[])dtos.get(0);
			System.out.println("Approver= "+(String)element[2]);
			signum=(String)element[2];
		}*/
		/*for(Object item : dtos)
		{
			Object [] element = (Object[])item;
			System.out.println("Approver= "+(String)element[2]);
			signum=(String)element[2];
			SearchDTO searchDTO2 = new SearchDTO();
			searchDTO2.setOpportunityID((Integer)element[0]);
			searchDTO2.setOpportunityName((String)element[1]);
			searchDTO2.setCustomerName((String)element[2]);
			searchDTO2.setRegion((String)element[3]);
			searchDTO2.setSolutionID((Integer)element[5]);
			searchDTOs.add(searchDTO2);
		}*/
	  return signum; 
	}
	
	private String identifyNextApprover(Session session ,int solutionId) throws DAOException{
		
		String signum=null;
		String hbQuery="from SolutionApprover t1 where t1.solution.solutionId="+solutionId 
		+" and t1.activeId="+1+" and t1.solutionApproverId NOT IN (select t2.solutionApprover.solutionApproverId from SolutionApprovalStatus t2 , SolutionApprover t3 "
		+" where t2.solutionApprover.solutionApproverId=t3.solutionApproverId) "
		+"order by t1.sequenceId ";
		
		List<SolutionApprover> approverLIst = session.createQuery(hbQuery).list();
		 if(approverLIst!=null && approverLIst.size()>0){
			 SolutionApprover app=approverLIst.get(0);
			 signum=app.getApprover();
		 }
		return signum; 
	}
	
	public NotificationDTO approveRequest(int solutionId,String approverSignum,String selectedRole,String currentAssignedapprover, String comments) throws ApprovalStatusException {
		
		NotificationDTO notificationDTO=new NotificationDTO();
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		logger.info("Approval Request handled by:: "+ selectedRole + currentAssignedapprover);
		try{
			
		if(!"ROLE_SUPER_USER".equalsIgnoreCase(selectedRole)){
			List<SolutionApprover> approverList = session.createQuery(" from SolutionApprover t  where t.solution.solutionId="
					+ solutionId +" and t.approver ='"+approverSignum+"' and t.activeId=1 order by t.solutionApproverId desc").list();
			SolutionApprover approver=approverList.get(0);
			
			SolutionApprovalStatus sAppStatus=new SolutionApprovalStatus();
			sAppStatus.setApproved(true);
			sAppStatus.setSolutionApprover(approver);
			sAppStatus.setResponseDate(new Date());
			sAppStatus.setComments(comments);
			session.saveOrUpdate(sAppStatus);
	
			//check for other approvers 
			
			/*String hbQuery = "from SolutionApprover t1 where t1.solution.solutionId="
					+ solutionId
					+ " and t1.activeId="
					+ 1
					+ " and t1.solutionApproverId NOT IN (select t2.solutionApprover.solutionApproverId from SolutionApprovalStatus t2 , SolutionApprover t3 "
					+ " where t2.solutionApprover.solutionApproverId=t3.solutionApproverId) "
					+ "order by t1.sequenceId ";
		
			List<SolutionApprover> appList = session.createQuery(hbQuery).list();
		    if(appList!=null && appList.size()>0){
		    	nextApprover=appList.get(0).getApprover();
		    }*/
			String nextApprover=identifyNextApprover(session,solutionId);
			notificationDTO.setSolutionId(solutionId);
			notificationDTO.setPrevApprover(approverSignum);
			notificationDTO.setNextApprover(nextApprover);
			notificationDTO.setNotificationType(INotificationService.NOTIFICATION_TYPE_APPROVAL_REQUEST);
		    if(nextApprover==null){ /// no other approvers found ...update solution status to approved
		    	List<Solution> solutionList = session.createQuery(" from Solution  where solutionId="+ solutionId).list();
				Solution solution=solutionList.get(0);	
				//solution.getStatus().setStatusId(3);
				Status status=new Status();
				status.setStatusId(MSSPConstants.SOLUTION_STATUS_APPROVED);
				solution.setStatus(status);
				session.saveOrUpdate(solution);
				notificationDTO.setNotificationType(INotificationService.NOTIFICATION_TYPE_APPROVED);
				notificationDTO.setSolutionCreator(solution.getCreatedBy());
		    }
		}else{
			
			String hql = " from SolutionApprover t  where t.solution.solutionId="
					+ solutionId +" and t.activeId=1 and t.approver='"+currentAssignedapprover+"' order by t.solutionApproverId desc";			
			
			List<SolutionApprover> solutionApprover = (List<SolutionApprover>) getHibernateTemplate().find(hql);
			SolutionApprover solutionApp = solutionApprover.get(0);	
			boolean adminApprovalPossible = false;
			
			if(solutionApp.getSequenceId()>1){
				String query = " from SolutionApprover t  where t.solution.solutionId="
						+ solutionId +" and t.sequenceId="+ (solutionApp.getSequenceId()-1)  +" and t.approver NOT IN ('"+ approverSignum+"') order by t.solutionApproverId desc";
				List<SolutionApprover> sa = (List<SolutionApprover>) getHibernateTemplate().find(query);
				SolutionApprover solApp = sa.get(0);					
				boolean upperLevelActiveId = solApp.getActiveId();
				
				if(upperLevelActiveId){
					// Admin can not approve as previous level approval needs to be done first.
					adminApprovalPossible = false;
				}else{
					adminApprovalPossible = true;
				}
				
			}else{
				adminApprovalPossible = true;
			}
			
			if(adminApprovalPossible){					
				solutionApp.setActiveId(false);
				session.saveOrUpdate(solutionApp);
				
				SolutionApprover solApp = new SolutionApprover();
				solApp.setApprover(approverSignum);
				solApp.setSequenceId(solutionApp.getSequenceId());
				solApp.setActiveId(false);
				solApp.setRequestDate(solutionApp.getRequestDate());
				Solution s = new Solution();
				s.setSolutionId(solutionId);
				solApp.setSolution(s);
				session.save(solApp);
				
				
				SolutionApprovalStatus sAppStatus=new SolutionApprovalStatus();
				sAppStatus.setApproved(true);			
				sAppStatus.setSolutionApprover(solApp);
				
				sAppStatus.setResponseDate(new Date());
				sAppStatus.setComments("Approved by Admin ("+ approverSignum  +") on behalf of :" + currentAssignedapprover);
				session.save(sAppStatus);
				
				String nextApprover=identifyNextApprover(session,solutionId);
				notificationDTO.setSolutionId(solutionId);
				notificationDTO.setPrevApprover(approverSignum);
				notificationDTO.setNextApprover(nextApprover);
				notificationDTO.setNotificationType(INotificationService.NOTIFICATION_TYPE_APPROVAL_REQUEST);
			    if(nextApprover==null){ /// no other approvers found ...update solution status to approved
			    	List<Solution> solutionList = session.createQuery(" from Solution  where solutionId="+ solutionId).list();
					Solution solution=solutionList.get(0);	
					//solution.getStatus().setStatusId(3);
					Status status=new Status();
					status.setStatusId(MSSPConstants.SOLUTION_STATUS_APPROVED);
					solution.setStatus(status);
					session.saveOrUpdate(solution);
					notificationDTO.setNotificationType(INotificationService.NOTIFICATION_TYPE_APPROVED);
					notificationDTO.setSolutionCreator(solution.getCreatedBy());
			    }				
		}else{
			notificationDTO.setNotificationType(INotificationService.NOTIFICATION_TYPE_WAITING_PREVIOUS_APPROVAL);					 
		}      
		}
		  tx.commit();
		} catch (Exception e) {
			logger.error("Exception ApprovalDAOImpl[approveRequest]", e);
			tx.rollback();
			logger.info("Successfully rolled back");
			ApprovalStatusException ex=new ApprovalStatusException();
			ex.initCause(e);
			throw ex;
		}finally{
			session.close();
		}
		return notificationDTO;
	}
	
	public NotificationDTO rejectRequest(int solutionId,String approverSignum,String selectedRole,String currentAssignedapprover,String comments) throws ApprovalStatusException {
		NotificationDTO notificationDTO=new NotificationDTO();
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		logger.info("Rejection Request handled by:: "+ selectedRole + currentAssignedapprover);
		String notificationReceiver=null;
		try{
			if(!"ROLE_SUPER_USER".equalsIgnoreCase(selectedRole)){
				List<SolutionApprover> approverLIst = session.createQuery(" from SolutionApprover t  where t.solution.solutionId="
								+ solutionId +" and t.approver ='"+approverSignum+"' and t.activeId=1 order by t.solutionApproverId desc").list();
				SolutionApprover approver=approverLIst.get(0);
				approver.setActiveId(false);
				SolutionApprovalStatus sAppStatus=new SolutionApprovalStatus();
				sAppStatus.setApproved(false);
				sAppStatus.setSolutionApprover(approver);
				sAppStatus.setResponseDate(new Date());
				sAppStatus.setComments(comments);
				Set<SolutionApprovalStatus> statusSet=new HashSet<SolutionApprovalStatus>(); 
				statusSet.add(sAppStatus);
				approver.setSolutionApprovalStatuses(statusSet);
				session.saveOrUpdate(sAppStatus);
				//saveObject(approver);
				
				List<Solution> solutionList = session.createQuery(" from Solution  where solutionId="
								+ solutionId).list();
				Solution solution=solutionList.get(0);
				Status status=new Status();
				status.setStatusId(SOLUTION_STATUS_DRAFT);
				solution.setStatus(status);
				//solution.getStatus().setStatusId(SOLUTION_STATUS_DRAFT);
				session.saveOrUpdate(solution);
				notificationReceiver=solution.getCreatedBy();
				notificationDTO.setSolutionId(solutionId);
				notificationDTO.setPrevApprover(approverSignum);
				notificationDTO.setNotificationType(INotificationService.NOTIFICATION_TYPE_REJECTION_REQUEST);
				notificationDTO.setSolutionCreator(notificationReceiver);
			}else{
				String hql = " from SolutionApprover t  where t.solution.solutionId="
						+ solutionId +" and t.activeId=1 and t.approver='"+currentAssignedapprover+"' order by t.solutionApproverId desc";				
				
				List<SolutionApprover> solutionApprover = (List<SolutionApprover>) getHibernateTemplate().find(hql);
				SolutionApprover solutionApp = solutionApprover.get(0);
				
				solutionApp.setActiveId(false);
				session.saveOrUpdate(solutionApp);
				
				
				SolutionApprover solApp = new SolutionApprover();
				solApp.setApprover(approverSignum);
				solApp.setSequenceId(solutionApp.getSequenceId());
				solApp.setActiveId(false);
				solApp.setRequestDate(solutionApp.getRequestDate());
				Solution s = new Solution();
				s.setSolutionId(solutionId);
				solApp.setSolution(s);
				session.save(solApp);
				
				
				SolutionApprovalStatus sAppStatus=new SolutionApprovalStatus();
				sAppStatus.setApproved(false);			
				sAppStatus.setSolutionApprover(solApp);				
				sAppStatus.setResponseDate(new Date());
				sAppStatus.setComments("Rejected by Admin ("+ approverSignum  +") on behalf of :" + currentAssignedapprover);
				Set<SolutionApprovalStatus> statusSet=new HashSet<SolutionApprovalStatus>(); 
				statusSet.add(sAppStatus);
				solutionApp.setSolutionApprovalStatuses(statusSet);
				session.saveOrUpdate(sAppStatus);
				
				List<Solution> solutionList = session.createQuery(" from Solution  where solutionId="
								+ solutionId).list();
				Solution solution=solutionList.get(0);
				Status status=new Status();
				status.setStatusId(SOLUTION_STATUS_DRAFT);
				solution.setStatus(status);
				//solution.getStatus().setStatusId(SOLUTION_STATUS_DRAFT);
				session.saveOrUpdate(solution);
				notificationReceiver=solution.getCreatedBy();
				notificationDTO.setSolutionId(solutionId);
				notificationDTO.setPrevApprover(approverSignum);
				notificationDTO.setNotificationType(INotificationService.NOTIFICATION_TYPE_REJECTION_REQUEST);
				notificationDTO.setSolutionCreator(notificationReceiver);
				
				
			}
			tx.commit();
		}catch(Exception e){
			logger.error("Exception ApprovalDAOImpl[rejectRequest]",e);
			tx.rollback();
			logger.info("Successfully rolled back");
			ApprovalStatusException ex=new ApprovalStatusException();
			ex.initCause(e);
			throw ex;
		}finally{
			session.close();
		}
		return notificationDTO;
	}
	
	
	public List<InitiateApprovalProcessDTO> inbox(String approverSignum) throws ApprovalStatusException{
	    List<InitiateApprovalProcessDTO> inboxSolutionList=new ArrayList<InitiateApprovalProcessDTO>();
		String hql= " from SolutionApprover t1  "
	    		    + " where t1.approver='"+approverSignum+"'"
	    			+ "	and t1.activeId=1 "
	    		    + " and t1.solution.status.statusId=2"
	    			+ "	and t1.solutionApproverId NOT IN (select t2.solutionApprover.solutionApproverId "
	    			+ "	from SolutionApprovalStatus t2  where t2.solutionApprover.solutionApproverId=t1.solutionApproverId) "
	    			+ "	order by t1.sequenceId ASC ";
		
		Session session=getSession();
		List<SolutionApprover> approverList = session.createQuery(hql).list();
		try{
		SolutionApprover temp=null;
		for(int i=0;i<approverList.size();i++){
			SolutionApprover eachApprover=approverList.get(i);
			if(eachApprover.getSolutionApprovalStatuses()==null || eachApprover.getSolutionApprovalStatuses().size()==0){
				List<SolutionApprover> prevSeqApproverList =session.createQuery("from SolutionApprover t where t.solution.solutionId="+eachApprover.getSolution().getSolutionId() +
						  " and t.sequenceId="+(eachApprover.getSequenceId()-1) +"order by t.solutionApproverId desc").list();
				if(prevSeqApproverList!=null &&prevSeqApproverList.size()>0){
					SolutionApprover prevApprover=prevSeqApproverList.get(0);
					if(prevApprover.getActiveId()){
						if(!(prevApprover.getSolutionApprovalStatuses()==null || prevApprover.getSolutionApprovalStatuses().size()==0)){
							//prevSequenceApprover has approved
							InitiateApprovalProcessDTO dto=new InitiateApprovalProcessDTO();
							dto.setOpportunityId(eachApprover.getSolution().getOpportunity().getOpportunityId());
							dto.setOpportunityName(eachApprover.getSolution().getOpportunity().getOpportunityName());
							dto.setSolutionId(eachApprover.getSolution().getSolutionId());
							dto.setSolutionName(eachApprover.getSolution().getSolutionType());
							dto.setSubmittedDate(eachApprover.getRequestDate());
							inboxSolutionList.add(dto);
						}else{
							//prevSequenceApprover has not yet approved/rejected
						}
					}else{
						//No Action. PrevSeqApprover has rejected hence no notification at inbox of next seq approver.
					}	
				}else{
					//prev approver is null hence current approver should approve
					InitiateApprovalProcessDTO dto=new InitiateApprovalProcessDTO();
					dto.setOpportunityId(eachApprover.getSolution().getOpportunity().getOpportunityId());
					dto.setOpportunityName(eachApprover.getSolution().getOpportunity().getOpportunityName());
					dto.setSolutionId(eachApprover.getSolution().getSolutionId());
					dto.setSolutionName(eachApprover.getSolution().getSolutionType());
					dto.setSubmittedDate(eachApprover.getRequestDate());
					inboxSolutionList.add(dto);
				}
			}
		}
		}catch(Exception e){
			logger.error("Exception ApprovalDAOImpl[inbox]" ,e);
			ApprovalStatusException ex=new ApprovalStatusException();
			ex.initCause(e);
			throw ex;
		}finally{
		session.close();
		}
		return inboxSolutionList;
	}
	
	public List<SolutionApprover> mailScheduler() {
	   // String approverSignum="app3";
	    List<SolutionApprover> recipientApprovers=new ArrayList<SolutionApprover>();
		String hql= " from SolutionApprover t1  "
	    		  /*  + " where t1.approver='"+approverSignum+"'" */
	    			+ "	where t1.activeId=1 "
	    		    + " and t1.solution.status.statusId=2"
	    			+ "	and t1.solutionApproverId NOT IN (select t2.solutionApprover.solutionApproverId "
	    			+ "	from SolutionApprovalStatus t2  where t2.solutionApprover.solutionApproverId=t1.solutionApproverId) "
	    			+ "	order by t1.sequenceId ASC ";
		
		Session session=getHibernateTemplate().getSessionFactory().openSession();
		//session.beginTransaction(); 
		List<SolutionApprover> approverList = session.createQuery(hql).list();
		//List<SolutionApprover> approverList = getHibernateTemplate().find(hql);
		//getHibernateTemplate().getSessionFactory().getCurrentSession();
		//getHibernateTemplate().getSessionFactory().openSession();
		SolutionApprover temp=null;
		for(int i=0;i<approverList.size();i++){
			SolutionApprover eachApprover=approverList.get(i);
			if(eachApprover.getSolutionApprovalStatuses()==null || eachApprover.getSolutionApprovalStatuses().size()==0){
				/*List<SolutionApprover> prevSeqApproverList = getHibernateTemplate()
						.find("from SolutionApprover t where t.solution.solutionId="+eachApprover.getSolution().getSolutionId() +
							  " and t.sequenceId="+(eachApprover.getSequenceId()-1) +"order by t.solutionApproverId desc");*/
				List<SolutionApprover> prevSeqApproverList =session.createQuery("from SolutionApprover t where t.solution.solutionId="+eachApprover.getSolution().getSolutionId() +
						  " and t.sequenceId="+(eachApprover.getSequenceId()-1) +"order by t.solutionApproverId desc").list();
				SolutionApprover prevApprover=prevSeqApproverList.get(0);
				if(prevApprover.getActiveId()){
					if(!(prevApprover.getSolutionApprovalStatuses()==null || prevApprover.getSolutionApprovalStatuses().size()==0)){
						//prevSequenceApprover has approved/rejected
						recipientApprovers.add(eachApprover);
						//temp=eachApprover;
						//System.out.println("Id="+eachApprover.getSolution().getSolutionId()+"_"+eachApprover.getSolution().getSolutionType());
					    //System.out.println("opportunity="+eachApprover.getSolution().getOpportunity().getOpportunityName());
					}else{
						//prevSequenceApprover has not yet approved/rejected
					}
				}else{
					//No Action. PrevSeqApprover has rejected hence no notification at inbox of next seq approver.
				}
				
			}
		}
		//session.getTransaction().commit();
		session.close();
		//System.out.println("caas="+temp.getSolution().getSolutionId()+"__"+temp.getSolution().getSolutionType());
		//System.out.println("opportunity="+temp.getSolution().getOpportunity().getOpportunityName());
		return recipientApprovers;
	}
	
	public List<SolutionADR> retrieveTop3ADR(int solutionId) throws DAOException {
		
		/*List<SolutionADR> adrList = getHibernateTemplate()
				.find(" from SolutionADR t where t.solution.solutionId="
						+ solutionId + "order by (t.adrimpact*t.adrweightage) desc");*/
		
		Session session=getHibernateTemplate().getSessionFactory().openSession();
		Query query=session.createQuery(" from SolutionADR t where t.solution.solutionId="
				+ solutionId + "order by (t.adrimpact*t.adrweightage) desc");
		query.setMaxResults(3);
		List<SolutionADR> adrList=query.list();
		return adrList;
	}

	@Override
	public List<InitiateApprovalProcessDTO> getAllApprovalRequestsForAdmin()
			throws ApprovalStatusException {
		/*String hql0= " from SolutionApprover t1  "
    		    + "	where t1.activeId=1 "
    		    + " and t1.solution.status.statusId=2"
    			+ "	and t1.solutionApproverId NOT IN (select t2.solutionApprover.solutionApproverId "
    			+ "	from SolutionApprovalStatus t2  where t2.solutionApprover.solutionApproverId=t1.solutionApproverId) "
    			+ "	order by t1.sequenceId ASC ";*/
		
		String hql = "select s from Solution s where s.solutionId IN (select sa.solution.solutionId from SolutionApprover sa where sa.activeId=1) and s.status.statusId="+2;
		
		//String hql = "from Solution s where s.status.statusId=" + 2;
		System.out.println(hql);
		Session session=getSession();
		List<InitiateApprovalProcessDTO> inboxSolutionList = new ArrayList<InitiateApprovalProcessDTO>();
		List<Solution> result = session.createQuery(hql).list();
		
		
		logger.debug("Result retrived: " + result.size());  
		
		InitiateApprovalProcessDTO dto = null;
		int i=0;
		for(Solution ipd : result){
			dto=new InitiateApprovalProcessDTO();
			dto.setSolutionId(ipd.getSolutionId());
			dto.setSolutionName(ipd.getSolutionType());
			dto.setOpportunityId(ipd.getOpportunity().getOpportunityId());
			dto.setOpportunityName(ipd.getOpportunity().getOpportunityName());
			dto.setSubmittedDate(ipd.getCreatedDate());
			
			
			/*dto.setSolutionName(ipd.getSolution().getSolutionType());
			dto.setOpportunityName(ipd.getSolution().getOpportunity().getOpportunityName());
			dto.setSolutionId(ipd.getSolution().getSolutionId());
			dto.setOpportunityId(ipd.getSolution().getOpportunity().getOpportunityId());*/
			//dto.setSubmittedDate(ipd.getSolution().getCreatedDate());
			inboxSolutionList.add(dto);
		}
		
		session.close();
		return inboxSolutionList;
	}
	
	
	public List<SolutionApprover> getAllApproversForThissolution(Integer solutionId){
		List<SolutionApprover> solApp = new ArrayList<SolutionApprover>();
		String hql = "select s from SolutionApprover s where s.solution.solutionId = " + solutionId + "and s.activeId=" + true;		
		List<SolutionApprover> solApprover = getHibernateTemplate().find(hql);	
		return solApprover;
	}
	
	
public List<SolutionADR> retrieveTop3ADR(int solutionId, String category) throws DAOException {
		
		/*List<SolutionADR> adrList = getHibernateTemplate()
				.find(" from SolutionADR t where t.solution.solutionId="
						+ solutionId + "order by (t.adrimpact*t.adrweightage) desc");*/
		
		Session session=getHibernateTemplate().getSessionFactory().openSession();
		Query query=session.createQuery(" from SolutionADR t where t.solution.solutionId="
				+ solutionId + " and t.adrcategory='"+category+"' order by (t.adrimpact*t.adrweightage) desc");
		query.setMaxResults(3);
		List<SolutionADR> adrList=query.list();
		return adrList;
	}
	
	private void persistCheckLists(Session session, ApprovalDetails details, Solution solution){
		int solId=details.getSolutionId();
		List<CheckLists> chkList=session.createQuery("from CheckLists t where t.solution.solutionId="+solId+" order by t.checkListsId").list();
		List<CheckListMaster> masterChkList=session.createQuery("from CheckListMaster t where t.checkListType='"+"APPROVAL"+"' order by t.checkListMasterId").list();
		CheckLists _checkList=null;
		for (int i = 0; i < 14; i++) {
			if(chkList.size()>0){
				_checkList=chkList.get(i);
			}else{
				_checkList=new CheckLists();
				_checkList.setSolution(solution);
			}
			switch (i + 1) {
			case 1:
				_checkList.setCheckListValue(details.getCheckListElements()
						.getCheckList1());
				_checkList.setCheckListMaster(masterChkList.get(0));
				break;

			case 2:
				_checkList.setCheckListValue(details.getCheckListElements()
						.getCheckList2());
				_checkList.setCheckListMaster(masterChkList.get(1));
				break;
			case 3:
				_checkList.setCheckListValue(details.getCheckListElements()
						.getCheckList3());
				_checkList.setCheckListMaster(masterChkList.get(2));
				break;
			case 4:
				_checkList.setCheckListValue(details.getCheckListElements()
						.getCheckList4());
				_checkList.setCheckListMaster(masterChkList.get(3));
				break;
			case 5:
				_checkList.setCheckListValue(details.getCheckListElements()
						.getCheckList5());
				_checkList.setCheckListMaster(masterChkList.get(4));
				break;
			case 6:
				_checkList.setCheckListValue(details.getCheckListElements()
						.getCheckList6());
				_checkList.setCheckListMaster(masterChkList.get(5));
				break;
			case 7:
				_checkList.setCheckListValue(details.getCheckListElements()
						.getCheckList7());
				_checkList.setCheckListMaster(masterChkList.get(6));
				break;
			case 8:
				_checkList.setCheckListValue(details.getCheckListElements()
						.getCheckList8());
				_checkList.setCheckListMaster(masterChkList.get(7));
				break;
			case 9:
				_checkList.setCheckListValue(details.getCheckListElements()
						.getCheckList9());
				_checkList.setCheckListMaster(masterChkList.get(8));
				break;
			case 10:
				_checkList.setCheckListValue(details.getCheckListElements()
						.getCheckList10());
				_checkList.setCheckListMaster(masterChkList.get(9));
				break;
			case 11:
				_checkList.setCheckListValue(details.getCheckListElements()
						.getCheckList11());
				_checkList.setCheckListMaster(masterChkList.get(10));
				break;
			case 12:
				_checkList.setCheckListValue(details.getCheckListElements()
						.getCheckList12());
				_checkList.setCheckListMaster(masterChkList.get(11));
				break;
			case 13:
				_checkList.setCheckListValue(details.getCheckListElements()
						.getCheckList13());
				_checkList.setCheckListMaster(masterChkList.get(12));
				break;
			case 14:
				_checkList.setCheckListValue(details.getCheckListElements()
						.getCheckList14());
				_checkList.setCheckListMaster(masterChkList.get(13));
				break;
			}
			session.saveOrUpdate(_checkList);
		}
		}
	
	public CheckListElements retrieveCheckListElements(Integer solId){
		CheckLists checkList=null;
		List<CheckLists> adrList = getHibernateTemplate().find(" from CheckLists t where t.solution.solutionId="+solId+"order by t.checkListsId");
		CheckListElements elements=new CheckListElements();
		if (adrList.size() > 0) {
			for (int i = 0; i < 14; i++) {
				checkList = adrList.get(i);
				switch (i + 1) {
				case 1:
					elements.setCheckList1(checkList.getCheckListValue());
					break;
				case 2:
					elements.setCheckList2(checkList.getCheckListValue());
					break;
				case 3:
					elements.setCheckList3(checkList.getCheckListValue());
					break;
				case 4:
					elements.setCheckList4(checkList.getCheckListValue());
					break;
				case 5:
					elements.setCheckList5(checkList.getCheckListValue());
					break;
				case 6:
					elements.setCheckList6(checkList.getCheckListValue());
					break;
				case 7:
					elements.setCheckList7(checkList.getCheckListValue());
					break;
				case 8:
					elements.setCheckList8(checkList.getCheckListValue());
					break;
				case 9:
					elements.setCheckList9(checkList.getCheckListValue());
					break;
				case 10:
					elements.setCheckList10(checkList.getCheckListValue());
					break;
				case 11:
					elements.setCheckList11(checkList.getCheckListValue());
					break;
				case 12:
					elements.setCheckList12(checkList.getCheckListValue());
					break;
				case 13:
					elements.setCheckList13(checkList.getCheckListValue());
					break;
				case 14:
					elements.setCheckList14(checkList.getCheckListValue());
					break;

				}
			}
		}
		return elements;
		
		}
	
	public List<TNTReport> retrieveTNTReport(int solutionId){
		List<TNTReport> rows=new ArrayList<TNTReport>();
		Session session=getSession();
		try {
			List<TTPlanning> ttPlanningList = session.createQuery(
					" from TTPlanning t where t.solution.solutionId="
							+ solutionId).list();

			if (ttPlanningList != null && ttPlanningList.size() > 0) {
				TNTReport report = null;
				TTPlanning ttPlanning = ttPlanningList.get(0);

				for (TTPartitionName partitionName : ttPlanning
						.getTtpartitionNames()) {
					for (TTPartitionDetail partitionDetail : partitionName
							.getTtpartitionDetails()) {
						report = new TNTReport();

						report.setPartitionName(partitionName
								.getTtpartitionName());

						report.setPlanningStartDate(partitionDetail
								.getPlanStartDate());
						report.setPlanningEndDate(partitionDetail
								.getPlanEndDate());
						report.setPlanningFtepercent(partitionDetail
								.getPlanFtecount()*100/partitionDetail
								.getDeliverFtecount());

						report.setLearnStartDate(partitionDetail
								.getLearnStartDate());
						report.setLearnEndDate(partitionDetail
								.getLearnEndDate());
						report.setLearnFtepercent(partitionDetail
								.getLearnFtecount()*100/partitionDetail
								.getDeliverFtecount());

						report.setAssistStartDate(partitionDetail
								.getAssistStartDate());
						report.setAssistEndDate(partitionDetail
								.getAssistEndDate());
						report.setAssistFtepercent(partitionDetail
								.getAssistFtecount()*100/partitionDetail
								.getDeliverFtecount());

						report.setPerformStartDate(partitionDetail
								.getPerformStartDate());
						report.setPerformEndDate(partitionDetail
								.getPerformEndDate());
						report.setPerformFtepercent(partitionDetail
								.getPerformFtecount()*100/partitionDetail
								.getDeliverFtecount());

						report.setDeliverStartDate(partitionDetail
								.getDeliverStartDate());
						report.setDeliverEndDate(partitionDetail
								.getDeliverEndDate());
						report.setDeliverFtepercent(partitionDetail
								.getDeliverFtecount()*100/partitionDetail
								.getDeliverFtecount());

						rows.add(report);

					}
				}
			}
		}finally{
        	session.close();
        }
		return rows;
	}
	
	public  SolutionSummary retrieveSolutionSummaryReport(int solutionId){
		Session session =getSession();
		SolutionSummary summary=new SolutionSummary();
		try{
		List<Solution> solutionList=session.createQuery("from Solution t where t.solutionId="+solutionId).list();
		Solution solution=solutionList.get(0);
		Opportunity opportunity=solution.getOpportunity();
		OpportunityDetail oppDetail=opportunity.getOpportunityDetail();
		OpportunityLocation loc=null;
		if(oppDetail.getOpportunityLocations()!=null&&oppDetail.getOpportunityLocations().size()>0){
			loc=(OpportunityLocation)oppDetail.getOpportunityLocations().toArray()[0];
		}
		if(oppDetail.getDeliveryModel()!=null){
			summary.setDeliveryModel(oppDetail.getDeliveryModel().getName());
		}
		if(oppDetail.getDeliveryType()!=null){
			summary.setDeliveryType(oppDetail.getDeliveryType().getName());
		}
		summary.setSsd(oppDetail.getSteadyStateStartDate());
		summary.setKTStartDt(oppDetail.getTransformationStartDate());
		summary.setKTEndDt(oppDetail.getTransformationEndDate()); 
		if(loc.getGsc1()!=null){
		summary.setOffShoreID((Integer)loc.getGsc1());
		}
		if(loc.getCustomerCountry()!=null){
			summary.setOnShoreID((Integer)loc.getCustomerCountry());	
		}
		
		if(oppDetail.getScopeOfServicesDefinedBy()!=null){
			summary.setServiceScopeDefinedBy(oppDetail.getScopeOfServicesDefinedBy().getName());
		}
		if(oppDetail.getVolumetricsDefinedBy()	!=null){
			summary.setInputVolumetricsDefinedBy(oppDetail.getVolumetricsDefinedBy().getName());
		}
		if(oppDetail.getScopeOfServicesDefinedBy()!=null){
			summary.setServiceScopeDefinedBy(oppDetail.getScopeOfServicesDefinedBy().getName());
		}
		
		List<Country> gscList=session.createQuery("from Country t where t.countryId="+loc.getGsc1()).list();
		List<Country> customerCountryList=session.createQuery("from Country t where t.countryId="+loc.getCustomerCountry()).list();
		if(gscList!=null && gscList.size()>0){
			summary.setOffShoreLocation(gscList.get(0).getCountryName());
		}
		
		if(customerCountryList!=null && customerCountryList.size()>0){
			summary.setOnShoreLocation(customerCountryList.get(0).getCountryName());
		}
		
		
		String query;
	    List<Object> results;
	    Object[] element;
		//is knowledge transfr in scope
	    query=" select * from Solution t1, Opportunity t2, OpportunityScope t3  , ServiceScope t4 "
	    +" where t4.ServiceScopeID=8 and t1.SolutionID= "+ solutionId
	    +" and t1.OpportunityID=t2.OpportunityID and t3.OpportunityID=t2.OpportunityID   and t3.ServiceScopeID=t4.ServiceScopeID";
	     results =session.createSQLQuery(query).list();
	    if(results.size()>0){
	    	summary.setKnowledgeTrnsfrInScope("Yes");
	    }else{
	    	summary.setKnowledgeTrnsfrInScope("No");
	    }
	    
	    //productivity lever first applied
	    query=" select MIN(t2.MonthYear) from SolutionLever t1 ,ProductivityLever t2  where t1.SolutionID="+solutionId
	    +" and t1.SolutionLeverID=t2.SolutionLeverID"; 
	    results=session.createSQLQuery(query).list();
	    if(results!=null && results.size()>0){
	    	if(results.get(0)!=null){
	    		summary.setpLeverApplied("Yes");
		    //	element= (Object[]) results.get(0);
		    	summary.setpLeverAppliedMnth(((Date)results.get(0)));		
	    	}else{
	    		summary.setpLeverApplied("No");
	    	}
	    }else{
	    	summary.setpLeverApplied("No");
	    }
	    //YOY FTE
	    query="SELECT EXTRACT(year from t.MonthYear) as year , max(t.CalculatedFTE) "
               + " FROM ProductivityLever t, SolutionLever t2 where t2.SolutionId= "+solutionId+" and t2.SolutionLeverID=t.SolutionLeverID "
               +" group by year"; 
	     results=session.createSQLQuery(query).list();
			if (results != null && results.size() > 0) {
				for (Object item : results) {
					element = (Object[]) item;
					summary.setYoyFTE(summary.getYoyFTE() + (Float) element[1]
							+ "-");
				}
				int index = summary.getYoyFTE().lastIndexOf('-');
				if (index != -1) {
					summary.setYoyFTE(summary.getYoyFTE().substring(0, index));
				}
			}
	    }finally{
			session.close();
		}
	    return summary;
    }

	public List<ServiceFTEReport> retrieveServiceFTEReport(int solutionId) {
		List<ServiceFTEReport> reportList = new ArrayList<ServiceFTEReport>();
		List<Object> results;
		Object[] element;
		String query = " SELECT * FROM  VieweOpportunityScopeSolutionServicesFTE t where t.SolutionID= "
				+ solutionId;
		Session session = getSession();
		results = session.createSQLQuery(query).list();
		if (results != null && results.size() > 0) {
			for (int i = 0; i < results.size(); i++) {
				if (results.get(i) != null) {
					element = (Object[]) results.get(i);
					ServiceFTEReport report = new ServiceFTEReport();
					report.setServiceName(((String) element[5]));
					report.setFte((Double) element[6]);
					report.setServiceScopeId((Integer) element[3]);
					report.setOpportunityScopeId((Integer) element[2]);
					String table = "";
					boolean supportWindow = true;
					switch (report.getServiceScopeId()) {
					case 1:
						table = "SolutionServiceDesk";
						break;
					case 2:
						table = "SolutionL1Operations" + "";
						break;
					case 3:
						table = "SolutionL2Operations";
						break;
					case 4:
						table = "SolutionL3Operations";
						break;
					default:
						supportWindow = false;
						break;

					}
					if (supportWindow) {
						query = "select distinct t1.SupportWindow "
								+ "from SupportWindowMatrix t1, "
								+ table
								+ " t2"
								+ " where t2.SupportWindowMatrixID=t1.SupportWindowMatrixID"
								+ " and t2.SolutionID= " + solutionId
								+ " and t2.OpportunityScopeID="
								+ report.getOpportunityScopeId();
						results = session.createSQLQuery(query).list();
						if (results != null && results.size() > 0) {
							//element = (Object[]) results.get(0);
							report.setOperationWindow((String) results.get(0));
						}
					}
					reportList.add(report);
				}
			}

		}
		return reportList;
	}
  
	public ExecSummary retrieveExeSummaryReport(int solID){
		
		ExecSummary summary=new ExecSummary();
		
		Session session=getSession();
		
		try{
			// fetch NonLabor/Travel Cost
			List<Object> valueList=null;
			String query = "select  t1.Year ,sum(t1.TotalCost),sum(t1.OnshoreFTE) from NLCTravel t1 , NLCInputParam t2 "+
					"where t1.NLCInputParamID=t2.NLCInputParamID and t2.SolutionID="+solID+
					" group by t1.Year";
			valueList = session.createSQLQuery(query).list();
			for (int i = 0; i < valueList.size(); i++) {
				Object[] o = (Object[]) valueList.get(i);
				int year = ((Integer) o[0]);
				float value = ((Double) o[1]).floatValue();
				float value2 = ((Double) o[2]).floatValue();
				ExecSummary.Row row = summary.new Row();
				row.setYear(year);
				row.setCostValue(value);
				row.setFteValue(value2);
				summary.getTravelCost().add(row);
			}
			
			// fetch Other Cost
			valueList = null;
			query = "select  t1.Year ,sum(t1.TotalOtherCost) , sum(t1.OffshoreFTE) from NLCOther t1 , NLCInputParam t2 "+
					" where t1.NLCInputParamID=t2.NLCInputParamID	and t2.SolutionID="+solID+
					" group by t1.Year";
			valueList = session.createSQLQuery(query).list();
			for (int i = 0; i < valueList.size(); i++) {
				Object[] o = (Object[]) valueList.get(i);
				int year = ((Integer) o[0]);
				float value = ((Double) o[1]).floatValue();
				float value2 = ((Double) o[2]).floatValue();
				ExecSummary.Row row = summary.new Row();
				row.setYear(year);
				row.setCostValue(value);
				row.setFteValue(value2);
				summary.getOtherCost().add(row);
			}
			
		//fetch Labor Cost SS	
		query = "select t1.Year,sum(Cost) from LaborCost t1 "+
						 "where t1.SolutionID="+solID+" group by t1.Year;";
		valueList = session.createSQLQuery(
				query).list();
		for (int i = 0; i < valueList.size(); i++) {
			Object[] o = (Object[]) valueList.get(i);
			int year = ((Integer) o[0]);
			float value = ((Double) o[1]).floatValue();
			ExecSummary.Row row= summary.new Row();
			row.setYear(year);
			row.setCostValue(value);
			summary.getLaborCost().add(row);
		}
		
		//fetch Labor Cost TT	
		List<ExecSummary.Row> ttList=new ArrayList<ExecSummary.Row>();
		query = "select Extract(Year from t.WeekDate), sum(t.Rate) from TTLaborCost t "+
				" where t.SolutionID="+ solID +" group by  Extract(Year from t.WeekDate)";
		valueList = session.createSQLQuery(
						query).list();
			for (int i = 0; i < valueList.size(); i++) {
				Object[] o = (Object[]) valueList.get(i);
				int year = ((Integer) o[0]);
				float value = ((Double) o[1]).floatValue();
				ExecSummary.Row row = summary.new Row();
				row.setYear(year);
				row.setCostValue(value);
				ttList.add(row);
			}
		// rearrange LaborCost SS and TT	
			if (ttList.size() > 0) {
				List<ExecSummary.Row> tmpList2 = new ArrayList<ExecSummary.Row>();
				boolean overlap = false;
				for (int i = 0; i < ttList.size(); i++) {
					if (ttList.get(i).getYear() != summary.getLaborCost()
							.get(0).getYear()) {
						tmpList2.add(ttList.get(i));
					} else if (ttList.get(i).getYear() == summary
							.getLaborCost().get(0).getYear()) {
						overlap = true;
					}
				}
				if (!overlap) {
					for (int i = 0; i < summary.getLaborCost().size(); i++) {
						tmpList2.add(summary.getLaborCost().get(i));
					}
				} else {
					for (int i = 0; i < summary.getLaborCost().size(); i++) {
						if (i == 0) {
							ExecSummary.Row tmpRow = summary.new Row();
							tmpRow.setYear(summary.getLaborCost().get(i)
									.getYear());
							tmpRow.setCostValue(ttList.get(ttList.size() - 1)
									.getCostValue()
									+ summary.getLaborCost().get(i)
											.getCostValue()); // TT+SS for
																// overlap year
							tmpList2.add(tmpRow);
						} else {
							tmpList2.add(summary.getLaborCost().get(i));
						}
					}
				}
				summary.setLaborCost(tmpList2);
			}else if(ttList.size()==0){
				int diff=summary.getOtherCost().size()-summary.getLaborCost().size();
				if(diff>0){
					List<ExecSummary.Row> tmpList2 = new ArrayList<ExecSummary.Row>();
					for(int i=0;i<diff;i++){
						ExecSummary.Row tmpRow = summary.new Row();
						tmpRow.setCostValue(0);
						tmpList2.add(tmpRow);
					}for(int i=0;i<summary.getLaborCost().size();i++){
						tmpList2.add(summary.getLaborCost().get(i));
					}
					summary.setLaborCost(tmpList2);
				}
			}
		
		// fetch invoicing type which factors in revenue to cost ratio
		valueList = null;
		query="select t.InvoicingType from NLCInputParam t where t.SolutionID="+solID;
		valueList = session.createSQLQuery(query).list();
		for (int i = 0; i < valueList.size(); i++) {
			//Object[] o = (Object[]) valueList.get(i);
			String invoiceType = (String)valueList.get(i);
			summary.setInvoiceType(invoiceType);
		}
		}finally{
			session.close();
		}
		return summary;
	}
}
