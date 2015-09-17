package com.ericsson.mssp.solution.dao.impl;

import java.util.Date;
import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.type.DateType;
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.entity.FTESummary;
import com.ericsson.mssp.common.entity.LocationBreakup;
import com.ericsson.mssp.common.entity.LocationPyramid;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.ProductEstimationBaseEffortForSolution;
import com.ericsson.mssp.common.entity.ProductivityLever;
import com.ericsson.mssp.common.entity.SolutionLever;
import com.ericsson.mssp.common.entity.SolutionUtilPerYear;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.dao.ISolutionLeverDAO;

@Repository
public class SolutionLeverDAOImpl extends BaseDAOImpl implements ISolutionLeverDAO {
	
	public static Logger logger = Logger.getLogger(SolutionLeverDAOImpl.class);

	//For Solution util per year 
	@Override
	public void saveSolutionUtilPerYear(SolutionUtilPerYear solutionUtilPerYear) throws DAOException {
		getHibernateTemplate().saveOrUpdate(solutionUtilPerYear);
		
	}
	/*
	 * Fetching first element only
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public SolutionUtilPerYear getSolutionUtilBySolutionId(Integer solutionId){
		
		List<SolutionUtilPerYear> solUtilPerYearList = getHibernateTemplate()
				.find(" from SolutionUtilPerYear where solution.solutionId =" + solutionId);
		if(solUtilPerYearList.size() !=0){
		return solUtilPerYearList.get(0);
		}
		return null;
	}
	// End for Solution util per year
	
	@SuppressWarnings("unchecked")
	@Override
	public OpportunityDetail getOpportunityDetail(Integer opportunityId) throws DAOException {
		
		List<OpportunityDetail> odList = getHibernateTemplate().find(" from OpportunityDetail where opportunityID="+ opportunityId);
		if(odList != null && odList.size() > 0) {
			return ((OpportunityDetail)odList.get(0));
		}
		else
			return null;
	}
		
	//	For LocationPyramid
	
	@Override
	public void saveLocationBreakup(LocationBreakup locationBreakup) {
		getHibernateTemplate().saveOrUpdate(locationBreakup);
		
	}
	
	/*
	 * saving Location Pyramid
	 */
	@Override
	public void saveLocationPyramid(LocationPyramid locationPyramid) {
		getHibernateTemplate().saveOrUpdate(locationPyramid);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public 	List<LocationPyramid> loadLocationPyramid(Integer solutionId,Integer oppScopeId ,String location ,String subLocation){
		List<LocationPyramid> locationPyramidList = getHibernateTemplate()
				.find(" from LocationPyramid lp where  lp.location= '"+ location + "' and lp.subLocation = '" + subLocation + "' and lp.opportunityScope.opportunityScopeId= "+ oppScopeId +" and lp.solutionLever.solution.solutionId= " +solutionId);
		if(null != locationPyramidList && locationPyramidList.size() >0){
			return locationPyramidList;
		}
		
		return null;
	}
	
	@Override
	public void saveProductivityLever(ProductivityLever pl) throws DAOException {
		
		getHibernateTemplate().saveOrUpdate(pl);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SolutionLever getSolutionLever(Integer solnId) throws DAOException {
		List<SolutionLever> slList = getHibernateTemplate().find(" from SolutionLever where SolutionID="+ solnId);
		if(slList != null && slList.size() > 0) {
			return slList.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LocationBreakup> loadLocationBreakupByOppScopeID(Integer oppScopeId){
		List<LocationBreakup> locationBreakupList = getHibernateTemplate().find(" from LocationBreakup where opportunityScope.opportunityScopeId="+ oppScopeId);
		if(null != locationBreakupList && locationBreakupList.size() >0){
			return locationBreakupList;
		}
		
		return null;
		
	}

	@Override
	public List<ProductivityLever> getProductivityLeverList(
			Integer solnId) throws DAOException {
		/*List<SolutionLever> slList = getHibernateTemplate().find(" from SolutionLever where SolutionID="+ solnId);
		if(slList != null && slList.size() > 0) {
			SolutionLever sl = slList.get(0);*/
			
			List<ProductivityLever> pllList = getHibernateTemplate().find(" from ProductivityLever where solutionLever.solution.solutionId="
					+solnId+" order by OpportunityScopeID, ProductivityLeverID");
			
			System.out.println( " list size in pL "+ pllList);
			/*	return new List<ProductivityLever>(getHibernateTemplate().find(" from ProductivityLever where solutionLever.SolutionLeverID="
					+sl.getSolutionLeverId()+" order by OpportunityScopeID, ProductivityLeverID"));
		}*/
		return pllList;
	}
	
	@Override
	public void saveSolutionLever(SolutionLever sl) throws DAOException {
		
		getHibernateTemplate().saveOrUpdate(sl);
	}
	//  For Location Pyramid 
	@Override
	public List<LocationBreakup> getAllLocationBreakupBySolLeverId(
			Integer solLeverId) {
		List<LocationBreakup> locationBreakupList = getHibernateTemplate().find(" from LocationBreakup where solutionLever.solutionLeverId="+ solLeverId);
		return locationBreakupList;
	}
	@Override
	public void saveProductivityLeverMonthwise(List<FTESummary> fteSummaryList) throws Exception {
		
		StringBuffer sqlInsert = new StringBuffer()
		.append("insert into FTESummary (OpportunityScopeID, SolutionID, FTECount,  ToDate) values ( :opportunityScopeID, :solutionID, :ftecount, :toDate) " +
				" ON DUPLICATE KEY UPDATE FTECount=VALUES(ftecount)");
		int row;
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try{
			for(FTESummary fte : fteSummaryList){
				
				row = 	session.createSQLQuery(sqlInsert.toString())
						.addScalar("OpportunityScopeID",new IntegerType())
						.addScalar("SolutionID", new IntegerType())
						.addScalar("FTECount", new FloatType())
						.addScalar("ToDate", new DateType())
						.setParameter("opportunityScopeID",fte.getOpportunityScope().getOpportunityScopeId())
						.setParameter("solutionID",	fte.getSolutionId())
						.setParameter("ftecount",	fte.getFtecount())
						.setParameter("toDate",	fte.getToDate())
						.executeUpdate();
				if(fteSummaryList.indexOf(fte)%50 == 0){
					session.flush();
				}
			}
			tx.commit();

		}catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
		}finally{
			session.close();
		}
		
		/*	
			if(fteSummaryList.indexOf(fte)%50 == 0){
				getHibernateTemplate().flush();
			}
		}*/
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ProductEstimationBaseEffortForSolution getBaseEffort(Integer solutionId,Integer oppScopeId){
		
		List<ProductEstimationBaseEffortForSolution> productEstimationBaseEffortForSolutionList = getHibernateTemplate()
				.find(" from ProductEstimationBaseEffortForSolution baseEffort  where  baseEffort.opportunityScope.opportunityScopeId= "+ oppScopeId +" and baseEffort.solution.solutionId= " +solutionId);
		if(null != productEstimationBaseEffortForSolutionList && productEstimationBaseEffortForSolutionList.size() >0){
			return productEstimationBaseEffortForSolutionList.get(0);
		}

		return null;
	}
	
	@Override
	public List<FTESummary> getMonthwiseFTEList(Integer solutionId,
			Date startDate, Date endDate) throws MSSPException{
		
		Criteria criteria = getSession().createCriteria(FTESummary.class);
	
		
		criteria.add(Expression.ge("toDate",startDate));
		criteria.add(Expression.le("toDate",endDate));
		criteria.add(Expression.eq("solutionId",solutionId));
		return criteria.list();
	}
	@Override
	public String updateFTESummary(Long fteSummaryId, Float fteValue) throws MSSPException{
		FTESummary fteSummary = getHibernateTemplate().get(FTESummary.class, fteSummaryId);
		fteSummary.setFtecount(fteValue);
		saveObject(fteSummary);
		return "Success";
	}
	
	/*
	public Date getSteadyStateStartDate(Integer opportunityId) throws DAOException {
		
		List<?> odList = getHibernateTemplate().find(" from OpportunityDetail where opportunityID="+ opportunityId);
		//OpportunityDetail od = (OpportunityDetail) getHibernateTemplate().find(" from OpportunityDetail where opportunityID="+ opportunityId).get(0);
		if(odList != null && odList.size() > 0) {
			return ((OpportunityDetail)odList.get(0)).getSteadyStateStartDate();
		}
		else
			return null;
	}
	
	public Date getSteadyStateEndDate(Integer opportunityId) throws DAOException {
		
		List<?> odList = getHibernateTemplate().find(" from OpportunityDetail where opportunityID="+ opportunityId);
		//OpportunityDetail od = (OpportunityDetail) getHibernateTemplate().find(" from OpportunityDetail where opportunityID="+ opportunityId).get(0);
		if(odList != null && odList.size() > 0) {
			return ((OpportunityDetail)odList.get(0)).getSteadyStateEndDate();
		}
		else
			return null;
	}

	@Override
	public Float getStartupFTE(Integer solutionId, Integer oppScopeId)
			throws DAOException {
		final String query = "select ServiceFTE from ViewStartupFTE "
			    + "where SolutionID=" + solutionId+ " and OpportunityScopeID=" + oppScopeId;
		Float fteValue = (Float)getHibernateTemplate().execute(
		        new HibernateCallback() {
		            public Float doInHibernate(Session session)
		                throws HibernateException {
		            	List<?> fteValues =  session.createSQLQuery(query).list();	   
		            	if (fteValues != null && fteValues.size() > 0 && fteValues.get(0) != null) {
		            		logger.info("FTE = "+ Float.valueOf(fteValues.get(0).toString()));
		                	return Float.valueOf(fteValues.get(0).toString());
		                }
		            	return Float.valueOf(0);
		            }
		        });
		return fteValue;
	}

	@Override
	public void saveSolutionLever(SolutionLever sl) throws DAOException {
		
		getHibernateTemplate().saveOrUpdate(sl);
	}

	@Override
	public void saveProductivityLever(ProductivityLever pl) throws DAOException {
		
		getHibernateTemplate().saveOrUpdate(pl);
		
	}

	@Override
	public List<JobRole> getJobRoleList() throws DAOException {
		
		return getHibernateTemplate().find(" from JobRole");
	}

	@Override
	public void saveLocationBreakup(LocationBreakup lb) throws DAOException {
		
		getHibernateTemplate().saveOrUpdate(lb);
		
	}

	@Override
	public void saveLocationPyramid(LocationPyramid lp) throws DAOException {
		
		getHibernateTemplate().saveOrUpdate(lp);
	}

	@Override
	public SolutionLever getSolutionLever(Integer solnId) throws DAOException {
		List<SolutionLever> slList = getHibernateTemplate().find(" from SolutionLever where SolutionID="+ solnId);
		if(slList != null && slList.size() > 0) {
			return slList.get(0);
		}
		return null;
	}
	
	@Override
	public AutoPopulatingList<ProductivityLever> getProductivityLeverList(
			Integer solnId) throws DAOException {
		List<SolutionLever> slList = getHibernateTemplate().find(" from SolutionLever where SolutionID="+ solnId);
		if(slList != null && slList.size() > 0) {
			SolutionLever sl = slList.get(0);
			return new AutoPopulatingList<ProductivityLever>(getHibernateTemplate().find(" from ProductivityLever where SolutionLeverID="
					+sl.getSolutionLeverId()+" order by OpportunityScopeID, ProductivityLeverID"), ProductivityLever.class);
		}
		return null;
	}

	@Override
	public AutoPopulatingList<LocationBreakup> getLocationBreakupList(
			Integer solnId) throws DAOException {
		List<SolutionLever> slList = getHibernateTemplate().find(" from SolutionLever where SolutionID="+ solnId);
		if(slList != null && slList.size() > 0) {
			SolutionLever sl = slList.get(0);
			return new AutoPopulatingList<LocationBreakup>(getHibernateTemplate().find(" from LocationBreakup where SolutionLeverID="
					+sl.getSolutionLeverId()+" order by OpportunityScopeID, LocationBreakupID"), LocationBreakup.class);
		}
		return null;
	}

	@Override
	public AutoPopulatingList<LocationPyramid> getLocationPyramidList(
			Integer solnId) throws DAOException {
		List<SolutionLever> slList = getHibernateTemplate().find(" from SolutionLever where SolutionID="+ solnId);
		if(slList != null && slList.size() > 0) {
			SolutionLever sl = slList.get(0);
			return new AutoPopulatingList<LocationPyramid>(getHibernateTemplate().find(" from LocationPyramid where SolutionLeverID="
					+sl.getSolutionLeverId()+" order by OpportunityScopeID, LocationPyramidID"), LocationPyramid.class);
		}
		return null;
	}
	
	@Override
	public AutoPopulatingList<LocationPyramid> getOnshorePyramidList(
			Integer solnId) throws DAOException {
		List<SolutionLever> slList = getHibernateTemplate().find(" from SolutionLever where SolutionID="+ solnId);
		if(slList != null && slList.size() > 0) {
			SolutionLever sl = slList.get(0);
			return new AutoPopulatingList<LocationPyramid>(getHibernateTemplate().find(" from LocationPyramid where SolutionLeverID="
					+sl.getSolutionLeverId()+" and location='"+MSSPConstants.LOCATION_ONSHORE+"'"
					+" order by OpportunityScopeID, LocationPyramidID"), LocationPyramid.class);
		}
		return null;
	}
	
	@Override
	public AutoPopulatingList<LocationPyramid> getOffshorePyramidList(
			Integer solnId) throws DAOException {
		List<SolutionLever> slList = getHibernateTemplate().find(" from SolutionLever where SolutionID="+ solnId);
		if(slList != null && slList.size() > 0) {
			SolutionLever sl = slList.get(0);
			return new AutoPopulatingList<LocationPyramid>(getHibernateTemplate().find(" from LocationPyramid where SolutionLeverID="
					+sl.getSolutionLeverId()+" and location='"+MSSPConstants.LOCATION_OFFSHORE+"'"
					+" order by OpportunityScopeID, LocationPyramidID"), LocationPyramid.class);
		}
		return null;
	}

	@Override
	public int deleteSolutionLever(Integer solnId) throws DAOException {
		
		return getHibernateTemplate().bulkUpdate("delete SolutionLever where SolutionID="+solnId);
	}
	
	@Override
	public int deleteProductivityLever(Integer solnId,Integer oppScopeId) throws DAOException {
		SolutionLever slvr = getSolutionLever(solnId);
		Integer solnLeverId = 0;
		if(slvr != null) {
			solnLeverId = slvr.getSolutionLeverId();
			return getHibernateTemplate().bulkUpdate("delete ProductivityLever where SolutionLeverID="+solnLeverId +" and OpportunityScopeId="+oppScopeId);

		}
		return solnLeverId;
	}

	@Override
	public int deleteLocationBreakup(Integer solnId, Integer oppScopeId) throws DAOException {
		SolutionLever slvr = getSolutionLever(solnId);
		Integer solnLeverId = 0;
		if(slvr != null) {
			solnLeverId = slvr.getSolutionLeverId();
		}
		return getHibernateTemplate().bulkUpdate("delete LocationBreakup where SolutionLeverID="+solnLeverId +" and OpportunityScopeId="+oppScopeId);
	}

	@Override
	public int deleteLocationPyramid(Integer solnId, Integer oppScopeId) throws DAOException {
		SolutionLever slvr = getSolutionLever(solnId);
		Integer solnLeverId = 0;
		if(slvr != null) {
			solnLeverId = slvr.getSolutionLeverId();
		}
		return getHibernateTemplate().bulkUpdate("delete LocationPyramid where SolutionLeverID="+solnLeverId+" and OpportunityScopeId="+oppScopeId);
	}

	@Override
	public Map<Long, Integer> createScopePLCountMap(Integer solnId)
			throws DAOException {
		SolutionLever slvr = getSolutionLever(solnId);
		Integer solnLeverId = 0;
		if(slvr != null) {
			solnLeverId = slvr.getSolutionLeverId();
		}
		final String query = "select OpportunityScopeID, count(*) from ProductivityLever "
			    + "where SolutionLeverID=" + solnLeverId+ " group by OpportunityScopeID";
		Map<Long,Integer> splcMap = (Map<Long,Integer>)getHibernateTemplate().execute(
		        new HibernateCallback() {
		            public Map<Long,Integer> doInHibernate(Session session)
		                throws HibernateException {
		            	ScrollableResults sr =  session.createSQLQuery(query).scroll();	   
		            	if(sr != null) {
		        	    	Map<Long,Integer> scopePLCountMap = new HashMap<Long,Integer>();
		        	    	while(sr.next()) {
		        	    		logger.info("creating productivity lever map.....\t"+sr.get(0)+"\t"+sr.get(1));
		        	    		scopePLCountMap.put(Long.valueOf(String.valueOf(sr.get(0))), Integer.valueOf(String.valueOf(sr.get(1))));
		        	    	}
		        	    	logger.info("scopePLCountMap size: "+scopePLCountMap.size());
		        	    	return scopePLCountMap;
		        	    }
		            	return null;
		            }
		        });
		logger.info("splcMap = "+splcMap);
		return splcMap;
	}

	@Override
	public Integer getLBRowCount(Integer solnId) throws DAOException {
		final SolutionLever slvr = getSolutionLever(solnId);
		Integer rowCount = (Integer)getHibernateTemplate().execute(
		        new HibernateCallback() {
		        	public Integer doInHibernate(Session session)
			                throws HibernateException {
		        			Criteria cr = session.createCriteria(LocationBreakup.class)
		        					.add(Restrictions.eq("solutionLever", slvr))
		        					.setProjection(Projections.countDistinct("monthYear"));
		        			List lb = cr.list();	        			
		        			if(lb != null && lb.size() != 0) {		        			
			            		Integer rc = (Integer)lb.get(0);			        	    	
			        	    	logger.info("rc = "+lb.get(0));
			        	    	return rc;
			        	    }
			            	return 0;
			            }
			        });
		logger.info("LocationBreakup rowCount = "+rowCount);
		return rowCount; 
	}

	@Override
	public Map<Long, Integer> createScopePyramidCountMap(Integer solnId)	// For onshore pyramid
			throws DAOException {
		SolutionLever slvr = getSolutionLever(solnId);
		Integer solnLeverId = 0;
		if(slvr != null) {
			solnLeverId = slvr.getSolutionLeverId();
		}
		final String query = "select OpportunityScopeID, count(*) from LocationPyramid "
			    + "where SolutionLeverID=" + solnLeverId+ " and Location='"+MSSPConstants.LOCATION_ONSHORE+"' group by OpportunityScopeID";
		Map<Long,Integer> spyrcMap = (Map<Long,Integer>)getHibernateTemplate().execute(
		        new HibernateCallback() {
		            public Map<Long,Integer> doInHibernate(Session session)
		                throws HibernateException {
		            	ScrollableResults sr =  session.createSQLQuery(query).scroll();	   
		            	if(sr != null) {
		        	    	Map<Long,Integer> scopePyrCountMap = new HashMap<Long,Integer>();
		        	    	while(sr.next()) {
		        	    		Integer numRowsInUI = 1;
								try {
									numRowsInUI = Integer.valueOf(String.valueOf(sr.get(1)))/getJobRoleList().size();
								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (DAOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		        	    		logger.info("creating onshore location pyramid map.....\t"+sr.get(0)+"\t"+numRowsInUI);
		        	    		scopePyrCountMap.put(Long.valueOf(String.valueOf(sr.get(0))), numRowsInUI);
		        	    	}
		        	    	logger.info("onshore scopePyrCountMap size: "+scopePyrCountMap.size());
		        	    	return scopePyrCountMap;
		        	    }
		            	return null;
		            }
		        });
		logger.info("onshore spyrcMap = "+spyrcMap);
		return spyrcMap;
	}

	@Override
	public Map<Long, Integer> createScopeOffPyrCountMap(Integer solnId)			//	For offshore pyramid
			throws DAOException {
		SolutionLever slvr = getSolutionLever(solnId);
		Integer solnLeverId = 0;
		if(slvr != null) {
			solnLeverId = slvr.getSolutionLeverId();
		}
		final String query = "select OpportunityScopeID, count(*) from LocationPyramid "
			    + "where SolutionLeverID=" + solnLeverId+ " and Location='"+MSSPConstants.LOCATION_OFFSHORE+"' group by OpportunityScopeID";
		Map<Long,Integer> spyrcMap = (Map<Long,Integer>)getHibernateTemplate().execute(
		        new HibernateCallback() {
		            public Map<Long,Integer> doInHibernate(Session session)
		                throws HibernateException {
		            	ScrollableResults sr =  session.createSQLQuery(query).scroll();	   
		            	if(sr != null) {
		        	    	Map<Long,Integer> scopePyrCountMap = new HashMap<Long,Integer>();
		        	    	while(sr.next()) {
		        	    		Integer numRowsInUI = 1;
								try {
									numRowsInUI = Integer.valueOf(String.valueOf(sr.get(1)))/getJobRoleList().size();
								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (DAOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		        	    		logger.info("creating offshore location pyramid map.....\t"+sr.get(0)+"\t"+numRowsInUI);
		        	    		scopePyrCountMap.put(Long.valueOf(String.valueOf(sr.get(0))), numRowsInUI);
		        	    	}
		        	    	logger.info("offshore scopePyrCountMap size: "+scopePyrCountMap.size());
		        	    	return scopePyrCountMap;
		        	    }
		            	return null;
		            }
		        });
		logger.info("offshore spyrcMap = "+spyrcMap);
		return spyrcMap;
	}

	@Override
	public AutoPopulatingList<ProductivityLever> getProductivityLeverListByScope(
			Integer opScopeId, Integer solnleverId) throws DAOException {
			return new AutoPopulatingList<ProductivityLever>(getHibernateTemplate().find(" from ProductivityLever where SolutionLeverID="
					+solnleverId+" and OpportunityScopeID="
					+opScopeId), ProductivityLever.class);
		
	}

	@Override
	public AutoPopulatingList<LocationBreakup> getLocationBreakupListByScope(
			Integer opScopeId, Integer solnleverId) throws DAOException {
	
			return new AutoPopulatingList<LocationBreakup>(getHibernateTemplate().find(" from LocationBreakup where SolutionLeverID="
					+solnleverId+" and OpportunityScopeID="
					+opScopeId), LocationBreakup.class);
	
	}
	
	@Override
	public AutoPopulatingList<LocationPyramid> getLocationPyramidListByScope(
			Integer opScopeId, Integer solnId) throws DAOException {
		List<SolutionLever> slList = getHibernateTemplate().find(" from SolutionLever where SolutionID="+ solnId);
		if(slList != null && slList.size() > 0) {
			SolutionLever sl = slList.get(0);
			return new AutoPopulatingList<LocationPyramid>(getHibernateTemplate().find(" from LocationPyramid where SolutionLeverID="
					+sl.getSolutionLeverId()+" and OpportunityScopeID="
					+opScopeId), LocationPyramid.class);+" order by OpportunityScopeID, LocationPyramidID"
		}
		return null;
	}
	
	
	@Override
	public AutoPopulatingList<LocationPyramid> getAllPyramidListByScope(
			Integer opScopeId, Integer solnleverId) throws DAOException {
			return new AutoPopulatingList<LocationPyramid>(getHibernateTemplate().find(" from LocationPyramid where SolutionLeverID="
					+solnleverId+" and OpportunityScopeID=" +opScopeId), LocationPyramid.class);
		
	}
	
	@Override
	public AutoPopulatingList<LocationPyramid> getOnshorePyramidListByScope(
			Integer opScopeId, Integer solnleverId) throws DAOException {
			return new AutoPopulatingList<LocationPyramid>(getHibernateTemplate().find(" from LocationPyramid where SolutionLeverID="
					+solnleverId+" and location='"+MSSPConstants.LOCATION_ONSHORE+"' and OpportunityScopeID="
					+opScopeId), LocationPyramid.class);
		
	}

	
	@Override
	public AutoPopulatingList<LocationPyramid> getOffshorePyramidListByScope(
			Integer opScopeId, Integer solnleverId) throws DAOException {
	
			return new AutoPopulatingList<LocationPyramid>(getHibernateTemplate().find(" from LocationPyramid where SolutionLeverID="
					+solnleverId+" and location='"+MSSPConstants.LOCATION_OFFSHORE+"' and OpportunityScopeID="
					+opScopeId), LocationPyramid.class);
	}*/
}
