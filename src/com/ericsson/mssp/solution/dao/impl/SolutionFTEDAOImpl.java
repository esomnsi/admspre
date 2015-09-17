/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.dao.impl
 * File name       		    :  SolutionFTEDAOImpl.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Mar 19, 20132013
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Mar 19, 2013  	   |edassri   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.entity.AdditionalFTE;
import com.ericsson.mssp.common.entity.FTEStaging;
import com.ericsson.mssp.common.entity.FTESummary;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.LocationBreakup;
import com.ericsson.mssp.common.entity.LocationPyramid;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.ProductivityLever;
import com.ericsson.mssp.common.entity.ServiceElement;
import com.ericsson.mssp.common.entity.ServiceElementJobRoleStages;
import com.ericsson.mssp.common.entity.ServiceScope;
import com.ericsson.mssp.common.entity.SolutionLever;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.solution.dao.ISolutionFTEDAO;

/**
 * @author edassri
 * 
 */
@Repository
public class SolutionFTEDAOImpl extends BaseDAOImpl implements ISolutionFTEDAO,
	MSSPConstants {
    @SuppressWarnings("unchecked")
    @Override
    public List<ServiceScope> getAllServiceScope() throws DAOException {
	return getObjects(ServiceScope.class);
    }

    @Override
    public Integer saveObjectReturnId(Object o) {
	return (Integer) getHibernateTemplate().save(o);
    }

    public Long saveObjectReturnLongId(Object o) {
	return (Long) getHibernateTemplate().save(o);
    }

    @Override
    public AdditionalFTE getAdditonalFTEByID(Integer additonalFTEID) {
	return (AdditionalFTE) getObject(AdditionalFTE.class, additonalFTEID);
    }

    @Override
    public FTEStaging getFtestagingByPassedParam(Integer solutionID,
	    Integer serviceScopeID, Integer jobRoleStageID, Date monthYear,
	    String location) throws DAOException {
	FTEStaging fteStaging = null;
	String sqlDate = date2SQL1stDateString(monthYear);
	@SuppressWarnings("unchecked")
	List<FTEStaging> fteStagingList = getHibernateTemplate().find(
		" from FTEStaging where OpportunityScopeID=" + serviceScopeID
			+ " and SolutionID=" + solutionID + " and JobRoleStageID="
			+ jobRoleStageID + " and MonthYear = date('" + sqlDate
			+ "') and Location='" + location + "'");
	if (fteStagingList.size() > 0) {
	    fteStaging = fteStagingList.get(0);
	}
	return fteStaging;
    }
    
    @Override
    public FTEStaging getFtestagingByPassedParam(Integer solutionID,
	    Integer serviceScopeID, Integer jobRoleStageID, Date monthYear,
	    String location, String subLocation) throws DAOException {
	FTEStaging fteStaging = null;
	String sqlDate = date2SQL1stDateString(monthYear);
	@SuppressWarnings("unchecked")
	List<FTEStaging> fteStagingList = getHibernateTemplate().find(
		" from FTEStaging where OpportunityScopeID=" + serviceScopeID
			+ " and SolutionID=" + solutionID + " and JobRoleStageID="
			+ jobRoleStageID + " and MonthYear = date('" + sqlDate
			+ "') and Location='" + location + "' and SubLocation='"+subLocation+"' ");
	if (fteStagingList.size() > 0) {
	    fteStaging = fteStagingList.get(0);
	}
	return fteStaging;
    }

    @Override
    public Map<Integer, String> loadAllSelectedOppSSIdServiceScopes(
	    Integer opportunityId) throws DAOException {

	@SuppressWarnings("unchecked")
	List<OpportunityScope> opportunityScopeList = getHibernateTemplate()
		.find(" from OpportunityScope where opportunityID="
			+ opportunityId + "order by ServiceScopeID");

	Map<Integer, String> oppScopeIDSSMap = new HashMap<Integer, String>();

	for (OpportunityScope oppScope : opportunityScopeList) {
	    oppScopeIDSSMap.put(oppScope.getOpportunityScopeId(), oppScope
		    .getServiceScope().getServiceScopeName());
	}
	return oppScopeIDSSMap;

    }

    @Override
    public SolutionLever getSolutionLeverBySolId(Integer solutionID)
	    throws DAOException {
	SolutionLever solutionLever = null;

	@SuppressWarnings("unchecked")
	List<SolutionLever> solutionLeverList = getHibernateTemplate().find(
		" from SolutionLever where SolutionID=" + solutionID
			+ " order by SolutionLeverID desc");
	if (solutionLeverList.size() > 0) {
	    solutionLever = solutionLeverList.get(0);
	}
	return solutionLever;
    }

    @Override
    public List<AdditionalFTE> getAdditionalFTEBySolutionAddFTEIDs(
	    Integer solutionID, Integer opportunityScopeId) throws DAOException {
	@SuppressWarnings("unchecked")
	List<AdditionalFTE> additionalFTEList = getHibernateTemplate().find(
		" from AdditionalFTE where solutionId=" + solutionID
			+ " and opportunityScopeId=" + opportunityScopeId);
	return additionalFTEList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LocationBreakup> getLocationBreakupBySolLeverId(
	    Integer solutionLeverID) throws DAOException {
	List<LocationBreakup> locationPyramidList = null;
	locationPyramidList = getHibernateTemplate().find(
		" from LocationBreakup where SolutionLeverID="
			+ solutionLeverID + " order by MonthYear");
	return locationPyramidList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LocationPyramid> getLocationPyramidBySolLeverId(
	    Integer solutionLeverID, Integer oppScopeID, Integer jobRoleStageID,
	    String location) throws DAOException {
	List<LocationPyramid> locationPyramidList = null;
	locationPyramidList = getHibernateTemplate()
		.find(" from LocationPyramid where SolutionLeverID="
			+ solutionLeverID + " and OpportunityScopeID="
			+ oppScopeID + " and JobRoleStageID=" + jobRoleStageID
			+ " and Location='" + location + "' order by MonthYear");
	return locationPyramidList;
    }    

    @Override
	public List<LocationPyramid> getLocationPyramidBySolLeverId(
			Integer solutionLeverID, Integer oppScopeID,
			Integer jobRoleStageID, String location, String subLocation)
			throws DAOException {
		List<LocationPyramid> locationPyramidList = null;
		
		StringBuffer queryStr = new StringBuffer()
				.append("from LocationPyramid where SolutionLeverID=? and OpportunityScopeID=? ")
				.append(" and JobRoleStageID=? and Location=? and SubLocation=? order by MonthYear");
		locationPyramidList = getHibernateTemplate().find(queryStr.toString(),
				solutionLeverID, oppScopeID, jobRoleStageID, location,
				subLocation);
		return locationPyramidList;
	}

    @Override
    public void saveFTEStagingList(List<FTEStaging> objectsList) {
	List<Object> objList = new ArrayList<Object>(objectsList.size());
	objList.addAll(objectsList);
	batchSaveUpdate(objList);
	
	System.out.println(" size of list for save "+objectsList.size());
    }

    @Override
    public void saveFTESummaryList(List<FTESummary> objectsList) {
	List<Object> objList = new ArrayList<Object>(objectsList.size());
	objList.addAll(objectsList);
	batchSaveUpdate(objList);
    }

    /**
     * 
     * Description : Converts date to SQL format date Method Name :
     * convertedDate2SQLDateString Input& Output:
     * 
     * @param monthYear
     * @return String
     */
    private String convertedDate2SQLDateString(Date monthYear) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(monthYear);
	return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1)
		+ "-" + cal.get(Calendar.DAY_OF_MONTH);
    }

    private String date2SQL1stDateString(Date monthYear) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(monthYear);
	return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1)
		+ "-01";
    }

    @Override
    public List<FTEStaging> getFtestagingListByPassedParam(Integer solutionID,
	    Integer serviceScopeID, Date fromDate, Date toDate, String location)
	    throws DAOException {
	// List<FTEStaging> fteStaging = null;
	String sqlFromDate = date2SQL1stDateString(fromDate);
	String sqlToDate = convertedDate2SQLDateString(toDate);
	@SuppressWarnings("unchecked")
	List<FTEStaging> fteStagingList = getHibernateTemplate().find(
		" from FTEStaging where OpportunityScopeID=" + serviceScopeID
			+ " and SolutionID=" + solutionID
			+ " and MonthYear between date('" + sqlFromDate
			+ "') and date('" + sqlToDate + "') and Location='"
			+ location + "' order by JobRoleStageID,MonthYear");
	return fteStagingList;
    }
    
    @Override
    public List<FTEStaging> getFtestagingListByPassedParam(Integer solutionID,
	    Integer serviceScopeID, Date fromDate, Date toDate, String location, String subLocation)
	    throws DAOException {
	// List<FTEStaging> fteStaging = null;
	String sqlFromDate = date2SQL1stDateString(fromDate);
	String sqlToDate = convertedDate2SQLDateString(toDate);
	@SuppressWarnings("unchecked")
	List<FTEStaging> fteStagingList = getHibernateTemplate().find(
		" from FTEStaging where OpportunityScopeID=" + serviceScopeID
			+ " and SolutionID=" + solutionID
			+ " and MonthYear between date('" + sqlFromDate
			+ "') and date('" + sqlToDate + "') and Location='"
			+ location + "' and SubLocation='"+ subLocation + "' order by JobRoleStageID,MonthYear");
	return fteStagingList;
    }

    @Override
    public int removeAllFTEStagingBySolutionID(Integer solutionID)
	    throws DAOException {
	final String query = "delete from FTEStaging where SolutionID="
		+ solutionID;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	Integer deltdCount = (Integer) getHibernateTemplate().execute(
		new HibernateCallback() {
		    public Object doInHibernate(Session session)
			    throws HibernateException {
			SQLQuery sq = session.createSQLQuery(query);
			int counts = sq.executeUpdate();
			return Integer.valueOf(counts);
		    }

		});
	return deltdCount;
    }

    @Override
    public int removeAllFTESummaryBySolutionID(Integer solutionID)
	    throws DAOException {
	final String query = "delete from FTESummary where SolutionID="
		+ solutionID;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	Integer deltdCount = (Integer) getHibernateTemplate().execute(
		new HibernateCallback() {
		    public Object doInHibernate(Session session)
			    throws HibernateException {
			SQLQuery sq = session.createSQLQuery(query);
			int counts = sq.executeUpdate();
			return Integer.valueOf(counts);
		    }

		});
	return deltdCount;
    }

    @Override
    public int removeAllAdditionalFTEsBySolutionID(Integer solutionID)
	    throws DAOException {
	final String query = "delete from AdditionalFTE where SolutionID="
		+ solutionID;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	Integer deltdCount = (Integer) getHibernateTemplate().execute(
		new HibernateCallback() {
		    public Object doInHibernate(Session session)
			    throws HibernateException {
			SQLQuery sq = session.createSQLQuery(query);
			int counts = sq.executeUpdate();
			return Integer.valueOf(counts);
		    }

		});
	return deltdCount;
    }

    @Override
    public List<JobRole> getAllJobRole() {
	@SuppressWarnings("unchecked")
	List<JobRole> listJobRole = getHibernateTemplate().find("from JobRole");
	return listJobRole;
    }

    @Override
    public List<FTEStaging> getFTEStagingListBySolutionID(Integer solutionID)
	    throws DAOException {
	// List<FTEStaging> fteStaging = null;
	@SuppressWarnings("unchecked")
	List<FTEStaging> fteStagingList = getHibernateTemplate().find(
		" from FTEStaging where SolutionID=" + solutionID);
	return fteStagingList;
    }

    @Override
    public List<FTESummary> getFTESummaryListBySolutionID(Integer solutionID)
	    throws DAOException {
	// List<FTEStaging> fteStaging = null;
	@SuppressWarnings("unchecked")
	List<FTESummary> fteSummaryList = getHibernateTemplate().find(
		" from FTESummary where SolutionID=" + solutionID);
	return fteSummaryList;
    }

    @Override
    public long save2FTEStaging(FTEStaging fTEStaging) throws DAOException {
	Long generatedID = fTEStaging.getFtestagingId();
	if (null != generatedID && generatedID > 0) {
	    saveObject(fTEStaging);
	} else {
	    generatedID = saveObjectReturnLongId(fTEStaging);
	}
	return generatedID;
    }

    @Override
    public LocationPyramid getLocationPyramidByFTEStaging(
	    Integer solutionLeverID, FTEStaging fteStaging) throws DAOException {
	@SuppressWarnings("unchecked")
	List<LocationPyramid> locationPyramidList = getHibernateTemplate()
		.find(" from LocationPyramid where OpportunityScopeID="
			+ fteStaging.getOpportunityScope()
				.getOpportunityScopeId()
			+ " and SolutionLeverID=" + solutionLeverID
			+ " and JobRoleStageID="
			+ fteStaging.getJobRoleStage().getJobRoleStagesId()
			+ " and MonthYear = date('"
			+ date2SQL1stDateString(fteStaging.getMonthYear())
			+ "') and Location='" + fteStaging.getLocation() + "'");
	return locationPyramidList.size() > 0 ? locationPyramidList.get(0)
		: null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProductivityLever> getProductivityLeversByLeverScopeID(
	    Integer solutionLeverID, Integer oppScopeIDs) throws DAOException {
	return getHibernateTemplate().find(
		" from ProductivityLever where OpportunityScopeID="
			+ oppScopeIDs + " and SolutionLeverID="
			+ solutionLeverID + " order by MonthYear");
    }

    @Override
    public double getStartupFTEBySolOppScopeID(Integer solutionID,
	    Integer oppScopeIDs) {
	final String query = "select ServiceFTE from ViewStartupFTE where OpportunityScopeID="
		+ oppScopeIDs + " and SolutionID=" + solutionID + " limit 1";
	double value = 0;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	List<Object> data = (List<Object>) getHibernateTemplate().execute(
		new HibernateCallback() {
		    public Object doInHibernate(Session session)
			    throws HibernateException {
			SQLQuery qry = session.createSQLQuery(query);
			List<Object> results = (List<Object>) qry.list();
			return results;
		    }
		});
	if (null != data && data.size() > 0) {
	    value = null != data.get(0) ? Double.parseDouble(data.get(0)
		    .toString()) : 0f;
	}
	return value;
    }
    
    @Override
    public List<JobRoleStages> getJobStages(String jobRoleStageId) throws DAOException{
    	return getHibernateTemplate().find(
    			" from JobRoleStages roleStage where jobRoleStagesId=?",Integer.parseInt(jobRoleStageId));
    }
    
    @Override
	public List<ServiceElementJobRoleStages> getJobRoleByServiceElementId(Integer serviceElementId) throws DAOException {		 
		String query = " from ServiceElementJobRoleStages sejr where sejr.serviceElement.serviceElementID = ?";
		
		List<ServiceElementJobRoleStages> serEleJobRoles= getHibernateTemplate().find(query, serviceElementId);
		
		return serEleJobRoles;
	}
    
    @Override
	public List<JobRoleStages> getJobRoles(int ccmFlag) throws DAOException {		 
		String query = " from JobRoleStages stages where stages.jobRole.CCMFlag = ?";
		
		List<JobRoleStages> jobRolesStages= getHibernateTemplate().find(query, ccmFlag);
		
		return jobRolesStages;
	}
    
    @Override
	public ServiceElement getServiceElementIdByOppScopeId(Integer oppScopeId)
			throws DAOException {
		List<OpportunityScope>  oppScopeList =  getHibernateTemplate().find(" from OpportunityScope where opportunityScopeID="
				+ oppScopeId );
	
		return oppScopeList.get(0).getServiceScope().getServiceElement();
	}
    
    @Override
    public List<FTESummary> getFteSummaryListByPassedParam(Integer solutionID,
	    Integer serviceScopeID,String location, String subLocation)
	    throws DAOException {

	@SuppressWarnings("unchecked")
	List<FTESummary> fteSummaryList = getHibernateTemplate().find(
			" from FTESummary where opportunityScope.opportunityScopeId =" + serviceScopeID
				+ " and solutionId=" + solutionID
				+ " and Location='"
				+ location + "' and SubLocation='"+ subLocation + "' order by fromDate");
	return fteSummaryList;
    }
    
    @Override
    public List<FTESummary> getFteSummaryListByPassedParam(Integer solutionID,
    	    Integer serviceScopeID,String location)
    	    throws DAOException {

    	@SuppressWarnings("unchecked")
    	List<FTESummary> fteSummaryList = getHibernateTemplate().find(
    			" from FTESummary where opportunityScope.opportunityScopeId =" + serviceScopeID
    				+ " and solutionId=" + solutionID
    				+ " and Location='"
    				+ location + "' order by fromDate");
    	return fteSummaryList;
        }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<FTEStaging> getFtestagingListByPassedParam(Integer solutionID,
	    Integer serviceScopeID, Integer jobRoleStageID,String location, String subLocation)
	    throws DAOException {
    	List<FTEStaging> fteStagingList = null;
	/*String sqlFromDate = date2SQL1stDateString(fromDate);
	String sqlToDate = convertedDate2SQLDateString(toDate);*/
	if (subLocation!=null) {
		fteStagingList = getHibernateTemplate().find(
		" from FTEStaging where opportunityScope.opportunityScopeId=" + serviceScopeID
			+ " and solution.solutionId=" + solutionID
			+ " and jobRoleStage.jobRoleStagesId = " + jobRoleStageID
			+ " and location='"
			+ location + "' and subLocation='"+ subLocation + "' order by monthYear");
	} else {
		fteStagingList = getHibernateTemplate().find(
				" from FTEStaging where opportunityScope.opportunityScopeId=" + serviceScopeID
					+ " and solution.solutionId=" + solutionID
					+ " and jobRoleStage.jobRoleStagesId = " + jobRoleStageID
					+ " and location='"
					+ location + "' order by monthYear");
	}
	return fteStagingList;
    }
    
    
    @SuppressWarnings("unchecked")
    @Override
	public List<JobRoleStages> getSpecificJobRolesForSolution(Integer solutionID , Integer opportunityScopeID) throws DAOException {

		List<JobRoleStages> jobRoleJistList = null;
    	
		jobRoleJistList = getHibernateTemplate().find(
    			" select distinct fte.jobRoleStage from FTEStaging fte where fte.opportunityScope.opportunityScopeId=" + opportunityScopeID
    				+ " and fte.solution.solutionId=" + solutionID);
    	
    	return jobRoleJistList;
    	
    }
    

    @SuppressWarnings("unchecked")
    @Override
	public List<FTEStaging> getFtestagingByPassedParam(Integer solutionID,
	    Integer serviceScopeID, Integer jobRoleStageID, Date startDate,
	    Date endDate,String location, String subLocation) throws DAOException {
			List<FTEStaging> fteStagingList = null;
			String sqlStartDate = date2SQL1stDateString(startDate);
			String sqlEndDate = date2SQL1stDateString(endDate);
			String query = null;
			
			if (subLocation!=null) {
				query = " from FTEStaging where OpportunityScopeID=" + serviceScopeID + " and SolutionID=" + solutionID + " and JobRoleStageID=" + jobRoleStageID + " and Location='" + location + "' and SubLocation='"+subLocation+"' and MonthYear between date('" + sqlStartDate + "') and date('"+sqlEndDate+"')";
			} else {
				query = " from FTEStaging where OpportunityScopeID=" + serviceScopeID + " and SolutionID=" + solutionID + " and JobRoleStageID=" + jobRoleStageID + " and Location='" + location + "' and MonthYear between date('" + sqlStartDate + "') and date('"+sqlEndDate+"')";
			}
			fteStagingList = getHibernateTemplate().find(query);
		return fteStagingList;
    }
    
    @Override
    public int  getFteCount(Integer solutionID,
    	    Integer serviceScopeID, Integer jobRoleStageID, String location, String subLocation) throws DAOException {
    			List<FTEStaging> fteStagingList = null;
    			int fteCount = 0;
    			String query = null;
    			if (subLocation!=null) {
    				query = " from FTEStaging where OpportunityScopeID=" + serviceScopeID + " and SolutionID=" + solutionID + " and JobRoleStageID=" + jobRoleStageID + " and Location='" + location + "' and SubLocation='"+subLocation+"'";
    			} else {
    				query = " from FTEStaging where OpportunityScopeID=" + serviceScopeID + " and SolutionID=" + solutionID + " and JobRoleStageID=" + jobRoleStageID + " and Location='" + location + "'";
    			}
    			fteStagingList = getHibernateTemplate().find(query);
    			if (fteStagingList!= null) {
    				fteCount = fteStagingList.size();
    			}
    		return fteCount;
        }
    @Override
    public List<Object[]> fetchFTESummary(Integer solutionID,Integer serviceScopeID,String location, String subLocation) throws Exception{
    
    	//Session session=getSession();
    	List<Object[]> list = null;
    	String str ="";
    	try {
    		
    	if (subLocation!=null) {
    		str = " and SubLocation = '"+subLocation+"'";
    	}
    	
    	final String query  = "SELECT SUM(FTECount),MonthYear FROM FTEStaging where SolutionID = "+solutionID+" and OpportunityScopeID = "+serviceScopeID+" and Location = '"+location+"' " +str+" group by MonthYear;";
    	
    	
    	list = (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery sq = session.createSQLQuery(query);
						return sq.list();
					}

				});
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw e;
    	}
    	return list;
    }
    
    @Override
    public boolean isFTEStagingRecordAvailable (Integer solutionID,Integer opportunityScopeID) throws Exception {
    	boolean recordsAvailable = false;
    	//Session session=getSession();
    	//String query = null;
    	List<Object> list = null;
    	Integer count = 0;
    	String str = "";
    	try {
    		if (opportunityScopeID != 0) {
    			str =  " and OpportunityScopeID = "+opportunityScopeID;
    			//query = "SELECT count(*) FROM FTEStaging where SolutionID ="+solutionID + " and OpportunityScopeID = "+opportunityScopeID;
    		} else {
    			//query = "SELECT count(*) FROM FTEStaging where SolutionID ="+solutionID;
    		}
    		
    		final String query = "SELECT count(*) FROM FTEStaging where SolutionID ="+solutionID + str;
    		
    		/*SQLQuery sq = session.createSQLQuery(query);
    		list = sq.list();*/
    		list = (List) getHibernateTemplate().execute(
    				new HibernateCallback() {
    					public Object doInHibernate(Session session)
    							throws HibernateException {
    						SQLQuery sq = session.createSQLQuery(query);
    						return sq.list();
    					}

    				});
    		
    		if (list!=null && list.size() > 0) {
    			Object obj = list.get(0);
    			count = ((BigInteger)obj).intValue();
    		}
    		if (count> 0) {
    			recordsAvailable = true;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw e;
    	}
    	return recordsAvailable;
    }
    
    @Override
    public boolean isFTESummaryRecordsAvailable(Integer solutionID,Integer opportunityScopeID) throws Exception {
    	boolean recordsAvailable = false;
    	//Session session=getSession();
    	//String query = null;
    	List<Object> list = null;
    	Integer count = 0;
    	try {
    		final String query = "SELECT count(*) FROM FTESummary where OpportunityScopeID = "+opportunityScopeID+" and SolutionID = "+solutionID;
    		/*SQLQuery sq = session.createSQLQuery(query);
    		list = sq.list();*/
    		
    		list = (List) getHibernateTemplate().execute(
    				new HibernateCallback() {
    					public Object doInHibernate(Session session)
    							throws HibernateException {
    						SQLQuery sq = session.createSQLQuery(query);
    						return sq.list();
    					}
    		});
    		
    		if (list!=null && list.size() > 0) {
    			Object obj = list.get(0);
    			count = ((BigInteger)obj).intValue();
    		}
    		if (count > 0) {
    			recordsAvailable = true;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw e;
    	}
    	return recordsAvailable;
    }
    
    @Override
    public List<FTESummary> getFTESummaryBySolnIDoppID(Integer solutionID,Integer opportunityScopeID)
	    throws DAOException {
	@SuppressWarnings("unchecked")
	List<FTESummary> fteSummaryList = getHibernateTemplate().find(
		" from FTESummary where solutionId=" + solutionID +" and opportunityScope.opportunityScopeId = " + opportunityScopeID +" order by toDate");
	return fteSummaryList;
    }
    
    @Override
    @SuppressWarnings("unchecked")
	public List<LocationPyramid> getLocationPyramidForOppScopeID(Integer opportunityScopeID) throws DAOException {
    	List<LocationPyramid> locationPyramidList = null;
    	locationPyramidList =  getHibernateTemplate().find("from LocationPyramid where opportunityScope.opportunityScopeId = " + opportunityScopeID);
    	return locationPyramidList;
    }
    
    @Override
    @SuppressWarnings("unchecked")
	public List<LocationPyramid> getLocPyramidSiteWise(Integer opportunityScopeID,String location, String subLocation) throws DAOException {
    	List<LocationPyramid> locationPyramidList = null;
    	if (subLocation!=null){
    		locationPyramidList =  getHibernateTemplate().find("from LocationPyramid where opportunityScope.opportunityScopeId = " + opportunityScopeID +"and location ='"+location+"' and subLocation = '"+subLocation+"'" );
    	} else {
    		locationPyramidList =  getHibernateTemplate().find("from LocationPyramid where opportunityScope.opportunityScopeId = " + opportunityScopeID +"and location ='"+location+"'");
    	}
    	return locationPyramidList;
    }
}
