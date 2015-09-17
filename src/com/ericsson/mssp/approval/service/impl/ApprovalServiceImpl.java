package com.ericsson.mssp.approval.service.impl;

import java.util.List;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ericsson.mssp.approval.dao.IApprovalDAO;
import com.ericsson.mssp.approval.forms.ADRReport;
import com.ericsson.mssp.approval.forms.ApprovalDetails;
import com.ericsson.mssp.approval.forms.CheckListElements;
import com.ericsson.mssp.approval.forms.ExecSummary;
import com.ericsson.mssp.approval.forms.ServiceFTEReport;
import com.ericsson.mssp.approval.forms.SolutionSummary;
import com.ericsson.mssp.approval.forms.TNTReport;
import com.ericsson.mssp.approval.service.IApprovalService;
import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.InitiateApprovalProcessDTO;
import com.ericsson.mssp.common.dto.NotificationDTO;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionADR;
import com.ericsson.mssp.common.entity.SolutionApprover;
import com.ericsson.mssp.common.exception.ApprovalStatusException;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;

import com.ericsson.mssp.solution.service.impl.SolutionServiceImpl;

@Service
public class ApprovalServiceImpl  implements IApprovalService, MSSPConstants {
    
	private final Log log = LogFactory.getLog(SolutionServiceImpl.class);
	
	@Autowired
    private IApprovalDAO approvalDAO;
	
	public NotificationDTO submitSolution(ApprovalDetails details) throws ApprovalStatusException {
		return approvalDAO.submitSolution(details);
	}
	
	public String identifyMailReceiver(int solutionId) throws DAOException {
		String signum=approvalDAO.identifyMailReceiver(solutionId);
		return signum;
	}
	
	public NotificationDTO approveRequest(int solutionId,String approverSignum,String selectedRole,String currentAssignedapprover, String comments) throws ApprovalStatusException{
		return approvalDAO.approveRequest(solutionId,approverSignum,selectedRole,currentAssignedapprover,comments);
	}
	
	public NotificationDTO  rejectRequest(int solutionId,String approverSignum,String selectedRole,String currentAssignedapprover,String comments) throws ApprovalStatusException{
       return approvalDAO.rejectRequest(solutionId,approverSignum,selectedRole,currentAssignedapprover,comments);		
	}
	public int retrieveSolutionStatus(int solutionId){
		return approvalDAO.retrieveSolutionStatus(solutionId);
	}
	public List<InitiateApprovalProcessDTO> inbox(String approverSignum) throws ApprovalStatusException{
		List<InitiateApprovalProcessDTO> sList=approvalDAO.inbox(approverSignum);
		return sList;
	}
	public List<SolutionApprover> mailScheduler(){
		List<SolutionApprover> appList=approvalDAO.mailScheduler();
		return appList;
	}
	public ADRReport retrieveTop3ADR(int solutionId) throws DAOException {
		List<SolutionADR> asuumptionList=approvalDAO.retrieveTop3ADR(solutionId,ADR_ASSUM_CATEGORY);
		List<SolutionADR> dependencyList=approvalDAO.retrieveTop3ADR(solutionId,ADR_DEPEND_CATEGORY);
		List<SolutionADR> riskList=approvalDAO.retrieveTop3ADR(solutionId,ADR_RISK_CATEGORY);
		ADRReport adrReport=new ADRReport();
		adrReport.setAssumptionList(asuumptionList);
		adrReport.setDependencyList(dependencyList);
		adrReport.setRiskList(riskList);
		return adrReport;
	}
	public List<SolutionApprover> retrieveExistingApproversForSolution(int solutionId) {
		List<SolutionApprover> existingApprovers=approvalDAO.retrieveExistingApproversForSolution(solutionId);
		return existingApprovers;
	}

	@Override
	public List<InitiateApprovalProcessDTO> getAllApprovalRequestsForAdmin()
			throws ApprovalStatusException {
		
		List<InitiateApprovalProcessDTO> allRequests = approvalDAO.getAllApprovalRequestsForAdmin();
		return allRequests;
	}

	@Override
	public List<SolutionApprover> getAllApproversForThissolution(
			Integer solutionId) {
		// TODO Auto-generated method stub
		List<SolutionApprover>  solAppList = approvalDAO.getAllApproversForThissolution(solutionId);
		return solAppList;
	}

	public CheckListElements retrieveCheckListElements(int solutionId){
		return approvalDAO.retrieveCheckListElements(solutionId);
	}
	
	public List<TNTReport> retrieveTNTReport(int solutionId){
		return approvalDAO.retrieveTNTReport(solutionId);
	}
	public  SolutionSummary retrieveSolutionSummaryReport(int solutionId){
		return approvalDAO.retrieveSolutionSummaryReport(solutionId);
	}
	public  List<ServiceFTEReport> retrieveServiceFTEReport(int solutionId){
		return approvalDAO.retrieveServiceFTEReport(solutionId);
	}
	public ExecSummary retrieveExeSummaryReport(int solID){
		return approvalDAO.retrieveExeSummaryReport(solID);
	}
	
}