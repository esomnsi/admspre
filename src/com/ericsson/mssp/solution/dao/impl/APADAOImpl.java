package com.ericsson.mssp.solution.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.AutoPopulatingList;

import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.entity.Apacomplexity;
import com.ericsson.mssp.common.entity.Apaweightage;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.LocationBreakupDefault;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.ServiceElementJobRoleStages;

import com.ericsson.mssp.common.entity.SolutionAPA;
import com.ericsson.mssp.common.entity.SolutionLever;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.solution.dao.APADAO;

@Repository
public class APADAOImpl extends BaseDAOImpl implements APADAO {
	
	public static Logger logger = Logger.getLogger(APADAOImpl.class);

	@Override
	public List<OpportunityScope> getOppourtunityScopeList(Integer oppId,
			Integer solnId)  throws DAOException {

	return getHibernateTemplate().find(" from OpportunityScope where opportunityID="
							+ oppId +"order by ServiceScopeID");
						
	}

	@Override
	public void saveSolutionAPA(SolutionAPA solnAPA) throws DAOException {
		
		getHibernateTemplate().saveOrUpdate(solnAPA);
	}

	@Override
	public void saveAPAComplexity(Apacomplexity apaComplexity) throws DAOException {
		
		getHibernateTemplate().saveOrUpdate(apaComplexity);
		
	}

	@Override
	public AutoPopulatingList<SolutionAPA> getSolutionAPAList(Integer solnId)
			throws DAOException {
		return new AutoPopulatingList<SolutionAPA>(getHibernateTemplate().find(" from SolutionAPA where SolutionID="
				+ solnId +" order by OpportunityScopeID, SolutionAPAID"), SolutionAPA.class);
	}

	@Override
	public Map<Long,Integer> createScopeAPACountMap(Integer solnId)
			throws DAOException {		
		final String query = "select OpportunityScopeID, count(*) from SolutionAPA "
			    + "where SolutionID=" + solnId+ " group by OpportunityScopeID";
		Map<Long,Integer> sacMap = (Map<Long,Integer>)getHibernateTemplate().execute(
		        new HibernateCallback() {
		            public Map<Long,Integer> doInHibernate(Session session)
		                throws HibernateException {
		            	ScrollableResults sr =  session.createSQLQuery(query).scroll();	   
		            	if(sr != null) {
		        	    	Map<Long,Integer> scopeAPACountMap = new HashMap<Long,Integer>();
		        	    	while(sr.next()) {
		        	    		//System.out.println(sr.get(0)+"\t"+sr.get(1));
		        	    		scopeAPACountMap.put(Long.valueOf(String.valueOf(sr.get(0))), Integer.valueOf(String.valueOf(sr.get(1))));
		        	    	}
		        	    	logger.info("scopeAPACountMap size: "+scopeAPACountMap.size());
		        	    	return scopeAPACountMap;
		        	    }
		            	logger.info("sr = null");
		            	return null;
		            }
		        });
		logger.info("sacMap = "+sacMap);
		return sacMap;
	}

	@Override
	public AutoPopulatingList<Apacomplexity> getAPAComplexityList(Integer solnId)
			throws DAOException {
		return new AutoPopulatingList<Apacomplexity>(getHibernateTemplate().find(" from Apacomplexity where SolutionID="
				+ solnId +" order by OpportunityScopeID, APAComplexityID"), Apacomplexity.class);
	}

	@Override
	public int deleteSolutionAPA(Integer solnId) throws DAOException {
		
		return getHibernateTemplate().bulkUpdate("delete SolutionAPA where SolutionID="+solnId);
	}

	@Override
	public int deleteAPAComplexity(Integer solnId) throws DAOException {
		
		return getHibernateTemplate().bulkUpdate("delete Apacomplexity where SolutionID="+solnId);
	}

	@Override
	public Apaweightage getAPAWeightage(Integer solnId) throws DAOException {
		List<Apaweightage> apawList = getHibernateTemplate().find(" from Apaweightage where SolutionID="+ solnId);
		if(apawList != null && apawList.size() > 0) {
			return apawList.get(0);
		}
		return null;
	}

	@Override
	public void saveAPAWeightage(Apaweightage apaWtg) throws DAOException {
		getHibernateTemplate().saveOrUpdate(apaWtg);
	}

	@Override
	public int deleteAPAWeightage(Integer solnId) throws DAOException {
		return getHibernateTemplate().bulkUpdate("delete Apaweightage where SolutionID="+solnId);
	}

	@Override
	public Integer getServiceElementIdByOppScopeId(Integer oppScopeId)
			throws DAOException {
		// TODO Auto-generated method stub
		List<OpportunityScope>  oppScopeList =  getHibernateTemplate().find(" from OpportunityScope where opportunityScopeID="
				+ oppScopeId );
	
		return oppScopeList.get(0).getServiceScope().getServiceElement().getServiceElementID();
	}

	@Override
	public List<ServiceElementJobRoleStages> getJobRoleByServiceElementId(Integer serviceElementId,
			boolean ccmFLag) throws DAOException {
		// TODO Auto-generated method stub
		
		if(!ccmFLag){
			serviceElementId=1000;
		}
		
		String query = " from ServiceElementJobRoleStages sejr where sejr.serviceElement.serviceElementID ="+serviceElementId ;//serviceLement 1000 for non ccm roles
		
		List<ServiceElementJobRoleStages> serEleJobRoles= getHibernateTemplate().find(query);
		
		return serEleJobRoles;
	}

	@Override
	public List<LocationBreakupDefault>  loadDefaultValuesByServiceElementId(Integer id) {
		// TODO Auto-generated method stub
		
		String query = " from LocationBreakupDefault lbd where lbd.serviceElement.serviceElementID ="+id ;//serviceLement 1000 for non ccm roles
		
		List<LocationBreakupDefault> defaultList =  getHibernateTemplate().find(query);
		
		return defaultList;
	}
}
