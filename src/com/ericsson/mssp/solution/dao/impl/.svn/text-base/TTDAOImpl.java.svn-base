/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.dao.impl
* File name       		    :  ITTDAOImpl.java
* Description				:	<To Do>
* Author, Date & Release	:	May 8, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| May 8, 2013  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.dto.TTLaborCostDTO;
import com.ericsson.mssp.common.entity.Opportunity;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.TTJobRoleDistribution;
import com.ericsson.mssp.common.entity.TTLaborCost;
import com.ericsson.mssp.common.entity.TTOnOffRatio;
import com.ericsson.mssp.common.entity.TTPartitionDetail;
import com.ericsson.mssp.common.entity.TTPartitionName;
import com.ericsson.mssp.common.entity.TTPlanning;
import com.ericsson.mssp.common.entity.TTSummaryStaging;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.dao.ITTDAO;
import com.ericsson.mssp.solution.forms.TTLaborCostForm;

/**
 * @author egaivij
 *
 */
@Repository
public class TTDAOImpl extends BaseDAOImpl implements ITTDAO{
	
	public static Logger logger = Logger.getLogger(TTDAOImpl.class);

	@Override
	public void savePlanning(TTPlanning ttPlanning) throws MSSPException{
		// TODO Auto-generated method stub
		saveObject(ttPlanning);
	}

	@Override
	public TTPlanning loadPartitionNames(Integer solutionId) throws MSSPException{
		TTPlanning partDetail = null; 
		List<TTPlanning> list = getHibernateTemplate().find(" from TTPlanning where SolutionID=" + solutionId);
		if(list != null && list.size()>0){
			partDetail = (TTPlanning)list.get(0);
		}
		return partDetail;
	}
	
	@Override
	public List<TTPartitionName> getPartitionNames(Integer solutionID) {
		
		String query = "from TTPartitionName where ttplanning.ttplanningId in (select ttplanningId from TTPlanning where solution.solutionId="+solutionID+")";
		List<TTPartitionName> ttPartitionNames = new ArrayList<TTPartitionName>();
		logger.info("query for partition names : " + query);
		ttPartitionNames = getHibernateTemplate().find(query);
		
		return ttPartitionNames;
	}

	@Override
	public TTPartitionDetail getTtPartitionDetails(Integer partitionNameID) {
		String query = "from TTPartitionDetail where ttpartitionName.ttpartitionNameId="+partitionNameID;
		logger.info("query to get partition details : "+query);
		List <TTPartitionDetail> list = getHibernateTemplate().find(query);
		if(list.size()>0){
			return (TTPartitionDetail)list.get(0);
		}
		return null;
	}

	@Override
	public List<TTJobRoleDistribution> getJobRoleDistributions(
			Integer solutionID) {
		
		String query = "from TTJobRoleDistribution where ttplanning.ttplanningId in (select ttplanningId from TTPlanning where solution.solutionId="+solutionID+")";
		logger.info("query to get job role distribution : "+query);
		return getHibernateTemplate().find(query);
	}

	@Override
	public List<TTSummaryStaging> getTtSummaryStagingData(String partitionNameIds) {
		String query = "from TTSummaryStaging where ttpartitionName.ttpartitionNameId in ("+partitionNameIds+")";
		
		logger.info("query to get on shore / off shore data : "+query);
		List<TTSummaryStaging> ttOnOff = getHibernateTemplate().find(query);
		return ttOnOff;
	}

	@Override
	public void savePartitionDetail(TTPartitionDetail partitionDetail, TTOnOffRatio ttOnOffRatio)
			throws MSSPException {
		
		saveObject(partitionDetail);
		
		saveObject(ttOnOffRatio);
		
	}
/*
	@Override
	public TTPartitionDetail getPartitionDetailById(Integer selPartitionId)
			throws MSSPException {
		
		return (TTPartitionDetail) getObject(TTPartitionDetail.class, selPartitionId);
		
	}*/

	@Override
	public TTOnOffRatio getOnOffRatioByPartitionId(Integer selPartitionId) {
		String query = "from TTOnOffRatio where ttpartitionName.ttpartitionNameId="+selPartitionId;
		logger.debug("query to get getOnOffRatioByPartitionId  : "+query);
		List <TTOnOffRatio> list = getHibernateTemplate().find(query);
		if(list.size()>0){
			return (TTOnOffRatio)list.get(0);
		}
		return null;
	}

	@Override
	public void saveSummaryStagingList(List<Object> summaryStagingList) throws MSSPException{
		
		batchSaveUpdate(summaryStagingList);
		
	}

	@Override
	public void saveJobroleDistribution(List<Object> jobroleDistList) throws MSSPException{
		batchSaveUpdate(jobroleDistList);
		
	}

	@Override
	public void deleteJobRoleDistributionByPlanningId(Integer ttPlanningId)
			throws MSSPException {
		
		final String query = " delete from TTJobRoleDistribution where TTPlanningID="+ ttPlanningId;
			Integer deltdCount = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
				    public Object doInHibernate(Session session)
					    throws HibernateException {
					SQLQuery sq = session.createSQLQuery(query);
					int counts = sq.executeUpdate();
					return Integer.valueOf(counts);
				    }

				});
			logger.info(" deleteJobRoleDistributionByPlanningId no of rows delete "+deltdCount);
			
	}

	@Override
	public void deletePlanningByPlanningId(Integer planningId)
			throws MSSPException {
			
		removeObject(TTPlanning.class, planningId);
		logger.info(" deletePlanningByPlanningId no of rows delete "+planningId);
		
	}
	
	@Override
	public void saveTTLaborCost(TTLaborCostForm ttLaborCostForm,
			Integer solutionID, Integer opportunityID) throws MSSPException{
		
		List<TTLaborCostDTO> ttLaborCostList = ttLaborCostForm.getTtLaborCostList();
		List<Object> ttLaborObjectList = new ArrayList<Object>();
		
		TTLaborCost ttLaborCost;
		Opportunity opportunity = new Opportunity();
		Solution solution = new Solution();
		
		opportunity.setOpportunityId(opportunityID);
		solution.setSolutionId(solutionID);
		
		final String query = "delete from TTLaborCost where SolutionID="+solutionID;
		
		Integer deltdCount = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
				    public Object doInHibernate(Session session)
					    throws HibernateException {
					SQLQuery sq = session.createSQLQuery(query);
					int counts = sq.executeUpdate();
					return Integer.valueOf(counts);
				    }

				});
		
		logger.debug("number of rows deleted : " + deltdCount);
		
		for (TTLaborCostDTO ttLaborCostDTO : ttLaborCostList) {
			ttLaborCost = new TTLaborCost();
			String rate = ttLaborCostDTO.getRate();
			if(rate == null || ("").equals(rate)){
				ttLaborCost.setRate(null);
				continue;
			}
			else{
				rate = rate.replace(",", "");
			ttLaborCost.setRate(Float.valueOf(rate));
			}
			ttLaborCost.setWeekDate(ttLaborCostDTO.getWeekDate());
			
			ttLaborCost.setOpportunity(opportunity);
			ttLaborCost.setSolution(solution);
			
			ttLaborObjectList.add(ttLaborCost);
		}
		
		batchSaveUpdate(ttLaborObjectList);
	}

	@Override
	public void deleteSummaryStagingByPartitionId(Integer selPartitionId)
			throws MSSPException {
			
			final String query = " delete from TTSummaryStaging where TTPartitionNameID="+ selPartitionId;
				Integer deltdCount = (Integer) getHibernateTemplate().execute(
					new HibernateCallback() {
					    public Object doInHibernate(Session session)
						    throws HibernateException {
						SQLQuery sq = session.createSQLQuery(query);
						int counts = sq.executeUpdate();
						return Integer.valueOf(counts);
					    }

					});
				logger.info(" deleteSummaryStagingByPartitionId no of rows delete "+deltdCount);
				
		
		
	}

}
