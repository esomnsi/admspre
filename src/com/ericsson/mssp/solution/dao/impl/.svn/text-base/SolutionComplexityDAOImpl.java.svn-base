package com.ericsson.mssp.solution.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.entity.Apacomplexity;
import com.ericsson.mssp.common.entity.DealCharacteristics;
import com.ericsson.mssp.common.entity.Opportunity;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionComplexity;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.dao.ISolutionComplexityDAO;
import com.ericsson.mssp.solution.dao.ISolutionDAO;
import com.ericsson.mssp.solution.forms.ComplexityAdjusterForm;

@Repository
public class SolutionComplexityDAOImpl extends BaseDAOImpl implements
		ISolutionComplexityDAO, MSSPConstants {
	@Autowired
    private ISolutionDAO solutionDAO;
	Map<String,String> OpportunityScopeMap = new HashMap<String,String>();
	Map<String,String> SolutionComplexityIdMap = new HashMap<String,String>();
	Map<String,String> startUpFTEMap = new HashMap<String,String>();
	final static Logger logger = LoggerFactory
			.getLogger(SolutionComplexityDAOImpl.class);

	public SolutionComplexityDAOImpl() {
	}

	@Override
	public SolutionComplexity load(Integer solutionId,SolutionComplexity solutionComplexity,final int opportunityId) throws MSSPException {
		solutionComplexity = new SolutionComplexity();
		@SuppressWarnings("unchecked")
		List<SolutionComplexity> solutionComplexityList = getHibernateTemplate().find(" FROM SolutionComplexity WHERE SolutionID=" + solutionId);
		if(solutionComplexityList.size() > 0){
			solutionComplexity = loadData(solutionComplexityList);
			SolutionComplexityIdMap.put(opportunityId+"|"+solutionId, solutionComplexityList.get(0).getSolutionComplexityId().toString());
			return solutionComplexity;	
		}else{
			//SolutionComplexityIdMap.put(opportunityId+"|"+solutionId);
			return solutionComplexity;
		}
	}

	@Override
	public int delete(long id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(SolutionComplexity solutionComplexity)
			throws Exception {
		int solutionComplexityId = 0;
		try {
			saveObject(solutionComplexity);
			solutionComplexityId =  solutionComplexity.getSolutionComplexityId();
		} catch (Exception e) {
			logger.error(e.getMessage() + " |  " + e.getCause());
		    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		}
		return solutionComplexityId;
	}

	@Override
	public int insert(SolutionComplexity solutionComplexity)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SolutionComplexity> queryAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private SolutionComplexity  loadData(List<SolutionComplexity> solutionComplexityList){
		SolutionComplexity solutionComplexityDTO = new SolutionComplexity();
		for(SolutionComplexity solutionComplexity : solutionComplexityList){
			solutionComplexityDTO.setSolutionComplexityId(solutionComplexity.getSolutionComplexityId());
			Solution solution = new Solution();
			solution.setSolutionId(solutionComplexity.getSolution().getSolutionId());
			solutionComplexityDTO.setSolution(solution);
			solutionComplexityDTO.setSkillRating(solutionComplexity.getSkillRating());
			solutionComplexityDTO.setRegionRating(solutionComplexity.getRegionRating());
			solutionComplexityDTO.setAuditRating(solutionComplexity.getAuditRating());
			solutionComplexityDTO.setSlarating(solutionComplexity.getSlarating());
			solutionComplexityDTO.setSkillWeightage(solutionComplexity.getSkillWeightage());
			solutionComplexityDTO.setRegionWeightage(solutionComplexity.getRegionWeightage());
			solutionComplexityDTO.setAuditWeightage(solutionComplexity.getAuditWeightage());
			solutionComplexityDTO.setSlaweightage(solutionComplexity.getSlaweightage());
			solutionComplexityDTO.setComputedComplexity(solutionComplexity.getComputedComplexity());
			solutionComplexityDTO.setOverriddenComplexity(solutionComplexity.getOverriddenComplexity());
			solutionComplexityDTO.setComplexityAdjustor(solutionComplexity.getComplexityAdjustor());
			solutionComplexityDTO.setContingencyEffort(solutionComplexity.getContingencyEffort());	
		}
		return solutionComplexityDTO;
	}
	//needs to implement
	private Map<String,String> fetchStartUpFTE(Integer solutionId,Integer opportunityId){
		final String fetchStartUpFTEQuery = "SELECT OpportunityScopeId,ServiceScopeId,IFNULL(ServiceFTE,0) FROM VieweOpportunityScopeSolutionServicesFTE "+
				" WHERE SolutionID = '"+solutionId+"' AND OpportunityId='"+opportunityId+"'";
		try
		{
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Map<String,String> startUpFTEMap = (Map<String,String>)getHibernateTemplate().execute(
			        new HibernateCallback() {
			            public Map<String,String> doInHibernate(Session session)
			                throws HibernateException {
			            	ScrollableResults sr =  session.createSQLQuery(fetchStartUpFTEQuery).scroll();	   
			            	if(sr != null) {
			        	    	Map<String,String> scopeAPACountMap = new HashMap<String,String>();
			        	    	String test = "";
			        	    	while(sr.next()) {
			        	    		test = test + String.valueOf(sr.get(0)).trim() + ","; 
			        	    		//System.out.println(sr.get(0)+"\t"+sr.get(1));
	scopeAPACountMap.put(String.valueOf(sr.get(0)).trim()+";"+String.valueOf(sr.get(1)).trim(), String.valueOf(sr.get(2)));scopeAPACountMap.put("serviceScopeID", test);
			        	    	}
			        	    	logger.info("scopeAPACountMap size: "+scopeAPACountMap.size());
			        	    	return scopeAPACountMap;
			        	    }
			            	return null;
			            }
			        });
			OpportunityScopeMap.put(opportunityId+"|"+solutionId,startUpFTEMap.get("serviceScopeID").toString());//startUpFTEMap
			startUpFTEMap.remove("serviceScopeID");
			return startUpFTEMap;
		} catch (Exception e) {
			System.out.println(e.getCause()+" fetchStartUpFTE "+e.getMessage());
			logger.error(e.getMessage() + " |  " + e.getCause());
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> startUpFTEDAOImpl(
			SolutionComplexity solutionComplexity, int OpportunityId,
			int solutionId, List<ServiceScopeDTO> listOfServiceScope,int serviceDeliveryYear)
			throws Exception {
		Map<String,String> STFTMap = new HashMap<String,String>();
		Map<String,List> dealCharacteristicsMap = new HashMap<String,List>();
		//List<OpportunityScopeDTO> OpportunityScopeDTOList = new ArrayList<OpportunityScopeDTO>();
		String query = "";
		List<String> serviceScopeIdList = new ArrayList<String>();
		List<DealCharacteristics> dealCharacteristicsList = new ArrayList<DealCharacteristics>();
		for(ServiceScopeDTO serviceScopeDTO : listOfServiceScope){
			query = " FROM OpportunityScope where OpportunityId="+OpportunityId+" and ServiceScopeId="+serviceScopeDTO.getServiceScopeId();
			List<OpportunityScope> OpportunityScopeDTOList = getHibernateTemplate().find(query);
			for(OpportunityScope opportunityScope : OpportunityScopeDTOList){
				serviceScopeIdList.add(opportunityScope.getOpportunityScopeId().toString());
				final String ServiceFTEQuery = "SELECT ServiceFTE FROM VieweOpportunityScopeSolutionServicesFTE where SolutionID = '"+solutionId+"' and OpportunityId='"+OpportunityId
						+"' and OpportunityScopeId="+opportunityScope.getOpportunityScopeId();
				double fteValues = 0;
				try
				{
				fteValues = (Double) getHibernateTemplate().execute(
					    new HibernateCallback() {
						public Object doInHibernate(Session session)
							throws HibernateException {
							List results = session.createSQLQuery(ServiceFTEQuery).setFirstResult(0).list();
						    return results.get(0);
							}
					    });	
				} catch (Exception e) {
					//System.out.println(e.getCause()+" startUpFTEDAOImpl "+e.getMessage());
					logger.error(e.getMessage() + " |  " + e.getCause());
				}
				STFTMap.put(opportunityScope.getOpportunityScopeId().toString()+"|"
						+serviceScopeDTO.getServiceScopeId().toString(),Double.toString(fteValues));
			}
		}
		//OpportunityScopeMap.put(OpportunityId+"|"+solutionId, serviceScopeIdList);
		return STFTMap;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Apacomplexity> loadApacomplexity(Integer solutionId,int opportunityId)
			throws MSSPException {
		List<Apacomplexity> apaComplexityList = new ArrayList<Apacomplexity>();
		Apacomplexity apacomplexity = new Apacomplexity();
		try
		{
			String opportunityScopeIds = OpportunityScopeMap.get(opportunityId+"|"+solutionId);
			String SolutionComplexityId = SolutionComplexityIdMap.get(opportunityId+"|"+solutionId);
			//if(SolutionComplexityId != null){
				String query = " FROM Apacomplexity WHERE SolutionId="+ solutionId
						+" and OpportunityScopeId IN ("+opportunityScopeIds.substring(0, opportunityScopeIds.length()-1)+")";
				apaComplexityList = getHibernateTemplate().find(query);
			//}
			Map<String,String> opIDMAp = new HashMap<String,String>();
			for(Apacomplexity apc : apaComplexityList){
				opIDMAp.put(String.valueOf(apc.getOpportunityScope().getOpportunityScopeId()), "");
			}
			Set<String> APANotExist = new HashSet<String>();
			for(String strTest : opportunityScopeIds.split(",")){
				if(opIDMAp.containsKey(strTest)==false){
					APANotExist.add(strTest);
				}
			}
			for (Map.Entry<String, String> entry : startUpFTEMap.entrySet())
			{
				for(String str : APANotExist){
					if(entry.getKey().split(";")[0].trim().equals(str)){
						startUpFTEMap.put(entry.getKey().trim(),str);
					}
				}
			}
			// All the opid is in the list need to identify which is not in the apaList
		}catch (Exception e) {
			System.out.println(e.getMessage() + " Ap  " + e.getCause());
			logger.error(e.getMessage() + " |  " + e.getCause());
		    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		}
		return apaComplexityList;
	}

	@Override
	public void updateAPA(List<Apacomplexity> apacomplexityList,
			int OpportunityId, int solutionId) throws Exception {	
		List<Object> objList = new ArrayList<Object>(13);
	    objList.addAll(apacomplexityList);
	    batchSaveUpdate(objList);
	}

	@Override
	public int getServiceDeliveryYearByOpportunityID(int OpportunityId)
			throws MSSPException {
		Calendar steadyStartDt = Calendar.getInstance();
		Calendar steadyEndDt = Calendar.getInstance();
		StringBuilder monthYears = new StringBuilder("");
		OpportunityDetail opportunityDetail = null;
		try {
		    opportunityDetail = ((Opportunity) solutionDAO.getObject(Opportunity.class, OpportunityId)).getOpportunityDetail();
		    logger.debug(opportunityDetail.getSteadyStateStartDate() + "|"+ opportunityDetail.getSteadyStateEndDate());
		    steadyStartDt.setTime(opportunityDetail.getSteadyStateStartDate());
		    steadyEndDt.setTime(opportunityDetail.getSteadyStateEndDate());
		} catch (Exception e) {
		    logger.info("Opportunity details not found:"+ (null != opportunityDetail ? opportunityDetail.getSteadyStateStartDate() : opportunityDetail));
		    return 0;
		}
		if (steadyEndDt.compareTo(steadyStartDt) < 0) {
			logger.error("Invalid opportunity time line: Start date:"
			    + steadyStartDt.get(Calendar.DATE) + "-"
			    + steadyStartDt.get(Calendar.MONTH) + "-"
			    + steadyStartDt.get(Calendar.YEAR) + " End year:"
			    + steadyEndDt.get(Calendar.DATE) + "-"
			    + steadyEndDt.get(Calendar.MONTH) + "-"
			    + steadyEndDt.get(Calendar.YEAR));
		    return 0;
		}
		int noOfDays = 0;
		if (steadyEndDt.compareTo(steadyStartDt) > 0) {
			long startDate = steadyStartDt.getTimeInMillis();
			long endDate = steadyEndDt.getTimeInMillis();
			long diff = endDate - startDate;
			long perDay = (24 * 60 * 60 * 1000);
			long diffDays = (diff / perDay);
			/*System.out.println("Time line: Start date:"
			    + steadyStartDt.get(Calendar.DATE) + "-"
			    + steadyStartDt.get(Calendar.MONTH) + "-"
			    + steadyStartDt.get(Calendar.YEAR) + " .......diffDays..."+diffDays+"........>>> End year:"
			    + steadyEndDt.get(Calendar.DATE) + "-"
			    + steadyEndDt.get(Calendar.MONTH) + "-"
			    + steadyEndDt.get(Calendar.YEAR));*/
			if(diffDays <= 365 * 2){
				noOfDays++;
			}else{
				noOfDays = noOfDays + 2;
			}
		}
		return noOfDays;
	}

	@Override
	public ComplexityAdjusterForm loadComplexityAdjusterForm(
			Integer solutionId, SolutionComplexity solutionComplexity,
			int OpportunityId, List<ServiceScopeDTO> listOfServiceScope)
			throws MSSPException {
		ComplexityAdjusterForm complexityAdjusterForm = new ComplexityAdjusterForm();
		//Map<String,String> startUpFTEMap = new HashMap<String,String>();
		int serviceDeliveryYear = 0;
		List<Apacomplexity> apaComplexityList = new ArrayList<Apacomplexity>();
		List<DealCharacteristics> dealCharacteristicsList = new ArrayList<DealCharacteristics>();
		try {
			/*final String solutionComplexitySearchBySolutionIDQuery = " FROM SolutionComplexity WHERE SolutionID=" + solutionId;
			List<SolutionComplexity> solutionComplexityList = (List<SolutionComplexity>) getHibernateTemplate().execute(
					new HibernateCallback() {
					    public Object doInHibernate(Session session)
						    throws HibernateException {
						SQLQuery qry = session.createSQLQuery(solutionComplexitySearchBySolutionIDQuery);
						List<Object> results = (List<Object>) qry.list();
						return results;
					    }
					});*/
			@SuppressWarnings("unchecked")
			List<SolutionComplexity> solutionComplexityList = getHibernateTemplate().find(" FROM SolutionComplexity WHERE SolutionID=" + solutionId);
			if(solutionComplexityList != null && solutionComplexityList.size() > 0){
				SolutionComplexityIdMap.put(OpportunityId+"|"+solutionId, solutionComplexityList.get(0).getSolutionComplexityId().toString());
				for(SolutionComplexity solutionComplexityIterate : solutionComplexityList){
					complexityAdjusterForm.setSolutionComplexity(solutionComplexityIterate);
				}
			}
			serviceDeliveryYear = getServiceDeliveryYearByOpportunityID(OpportunityId);
			//System.out.println("serviceDeliveryYear "+serviceDeliveryYear);
			if(serviceDeliveryYear != 0){
				complexityAdjusterForm.setServiceDeliveryYear(serviceDeliveryYear);
				startUpFTEMap = fetchStartUpFTE(solutionId,OpportunityId);
				complexityAdjusterForm.setStartUpFTEMap(startUpFTEMap);
			}
			apaComplexityList = loadApacomplexity(solutionId,OpportunityId);
			if(apaComplexityList != null && apaComplexityList.size() != 0){
				complexityAdjusterForm.setApacomplexityList(apaComplexityList);
			}
			dealCharacteristicsList = loadDealCharacteristics(serviceDeliveryYear,listOfServiceScope);
			if(dealCharacteristicsList != null){
				complexityAdjusterForm.setDealCharacteristicsList(dealCharacteristicsList);
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + " |  " + e.getCause());
		    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		}
		return complexityAdjusterForm;
	}
	
	private List<DealCharacteristics> loadDealCharacteristics(Integer serviceDeliveryYear,List<ServiceScopeDTO> listOfServiceScope){
		@SuppressWarnings("unchecked")
		//List<DealCharacteristics> dealCharacteristicsList = getHibernateTemplate().find(" FROM DealCharacteristics WHERE DealIdentifier=" + serviceDeliveryYear);
		List<DealCharacteristics> dealCharacteristicsList = getHibernateTemplate().find(" FROM DealCharacteristics");
		return dealCharacteristicsList;
	}

}
