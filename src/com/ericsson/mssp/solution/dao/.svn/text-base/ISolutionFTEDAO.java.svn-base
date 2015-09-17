/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.dao
 * File name       		    :  ISolutionFTEDAO.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Mar 19, 20132013
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Mar 19, 2013  	   |edassri   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ericsson.mssp.common.dao.IBaseDAO;
import com.ericsson.mssp.common.entity.AdditionalFTE;
import com.ericsson.mssp.common.entity.FTEStaging;
import com.ericsson.mssp.common.entity.FTESummary;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.LocationBreakup;
import com.ericsson.mssp.common.entity.LocationPyramid;
import com.ericsson.mssp.common.entity.ProductivityLever;
import com.ericsson.mssp.common.entity.ServiceElement;
import com.ericsson.mssp.common.entity.ServiceElementJobRoleStages;
import com.ericsson.mssp.common.entity.ServiceScope;
import com.ericsson.mssp.common.entity.SolutionLever;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;

/**
 * @author edassri
 * 
 */
public interface ISolutionFTEDAO extends IBaseDAO {

    /**
     * 
     * Description : Return opportunity id based OppScopeID based service scope
     * names Method Name : loadAllSelectedOppSSIdServiceScopes Input& Output:
     * 
     * @param opportunityId
     * @return Map<Integer, String>
     * @throws DAOException
     */
    public Map<Integer, String> loadAllSelectedOppSSIdServiceScopes(
	    Integer opportunityId) throws DAOException;

    /**
     * 
     * Description : It returns solution lever based on solution id Method Name
     * : getSolutionLeverIdBySolId Input& Output:
     * 
     * @param solutionID
     * @return SolutionLever
     * @throws DAOException
     */
    SolutionLever getSolutionLeverBySolId(Integer solutionID)
	    throws DAOException;

    /**
     * 
     * Description : Get additional FTE by passed solution ID and additional FTE
     * ID Method Name : getAdditionalFTEBySolutionAddFTEIDs Input& Output:
     * 
     * @param solutionID
     * @param additionalFTEId
     * @return AdditionalFTE
     * @throws DAOException
     */
    List<AdditionalFTE> getAdditionalFTEBySolutionAddFTEIDs(Integer solutionID,
	    Integer additionalFTEId) throws DAOException;

    /**
     * 
     * Description : Returns Location breakup by solution lever id Method Name :
     * getLocationBreakupBySolLeverId Input& Output:
     * 
     * @param solutionLeverID
     * @return List<LocationBreakup>
     * @throws DAOException
     */
    List<LocationBreakup> getLocationBreakupBySolLeverId(Integer solutionLeverID)
	    throws DAOException;

    /**
     * 
     * Description : Returns location pyramid list by solution lever id Method
     * Name : getLocationPyramidBySolLeverId Input& Output:
     * 
     * @param solutionKeverID
     * @return List<LocationPyramid>
     * @throws DAOException
     */
    List<LocationPyramid> getLocationPyramidBySolLeverId(
	    Integer solutionLeverID, Integer oppScopeID, Integer roleID,
	    String location) throws DAOException;

    /**
     * Description : Returns location pyramid list by solution lever id Method - considers sublocation as well
     * @param solutionLeverID
     * @param oppScopeID
     * @param jobRoleStageID
     * @param location
     * @param subLocation
     * @return
     * @throws DAOException
     */
    public List<LocationPyramid> getLocationPyramidBySolLeverId(
    	    Integer solutionLeverID, Integer oppScopeID, Integer jobRoleStageID,
    	    String location, String subLocation) throws DAOException;
    /**
     * 
     * Description : It will do batch update of FTEStaging objects Method Name :
     * saveFTEStagingList Input& Output:
     * 
     * @param objectsList
     */
    void saveFTEStagingList(List<FTEStaging> objectsList);

    /**
     * 
     * Description : Returns AdditionalFTE object by its ID Method Name :
     * getAdditonalFTEByID Input& Output:
     * 
     * @param additonalFTEID
     * @return
     */
    AdditionalFTE getAdditonalFTEByID(Integer additonalFTEID);

    /**
     * 
     * Description : Return FTEStaging existing record ID based on passed params
     * Method Name : getFtestagingIdByPassedParam Input& Output:
     * 
     * @param solutionID
     * @param serviceScopeID
     * @param roleID
     * @param monthYear
     * @param location
     * @return FTEStaging
     * @throws DAOException
     */
    FTEStaging getFtestagingByPassedParam(Integer solutionID,
	    Integer serviceScopeID, Integer roleID, Date monthYear,
	    String location) throws DAOException;
    
    /**
     * 
     * Description : Return FTEStaging existing record ID based on passed params
     * Method Name : getFtestagingIdByPassedParam Input& Output:
     * 
     * @param solutionID
     * @param serviceScopeID
     * @param roleID
     * @param monthYear
     * @param location
     * @param subLocation
     * @return FTEStaging
     * @throws DAOException
     */
    FTEStaging getFtestagingByPassedParam(Integer solutionID,
	    Integer serviceScopeID, Integer roleID, Date monthYear,
	    String location, String subLocation) throws DAOException;

    /**
     * 
     * Description : save FTEStaging by FTE calculated values Method Name :
     * save2FTEStaging Input& Output:
     * 
     * @param fTEStaging
     * @return long
     * @throws DAOException
     */
    long save2FTEStaging(FTEStaging fTEStaging) throws DAOException;

    /**
     * 
     * Description : It will return FTEStaging records based on passed params
     * Method Name : getFtestagingListByPassedParam Input& Output:
     * 
     * @param solutionID
     * @param serviceScopeID
     * @param fromDate
     * @param toDate
     * @param location
     * @return List<FTEStaging>
     * @throws DAOException
     */
    List<FTEStaging> getFtestagingListByPassedParam(Integer solutionID,
	    Integer serviceScopeID, Date fromDate, Date toDate, String location)
	    throws DAOException;
    
    /**
     * 
     * Description : It will return FTEStaging records based on passed params
     * Method Name : getFtestagingListByPassedParam Input& Output:
     * 
     * @param solutionID
     * @param serviceScopeID
     * @param fromDate
     * @param toDate
     * @param location
     * @param subLocation
     * @return List<FTEStaging>
     * @throws DAOException
     */
    List<FTEStaging> getFtestagingListByPassedParam(Integer solutionID,
	    Integer serviceScopeID, Date fromDate, Date toDate, String location, String subLocation)
	    throws DAOException;

    /**
     * 
     * Description : It will remove all FTEStaging records by passed Solution ID
     * Method Name : removeAllFTEStagingBySolutionID Input& Output:
     * 
     * @param solutionID
     * @return int
     * @throws DAOException
     */
    int removeAllFTEStagingBySolutionID(Integer solutionID) throws DAOException;

    /**
     * 
     * Description : Returns FTEStagings by solution ID Method Name :
     * getFTEStagingBySolutionID Input& Output:
     * 
     * @param solutionID
     * @return List<FTEStaging>
     * @throws DAOException
     */
    List<FTEStaging> getFTEStagingListBySolutionID(Integer solutionID)
	    throws DAOException;

    /**
     * 
     * Description : It will return LocationPyramid object by FTEStaging params
     * Method Name : getLocationPyramidByFTEStaging Input& Output:
     * 
     * @param solutionLeverID
     * @param fteStaging
     * @return
     * @throws DAOException
     */
    LocationPyramid getLocationPyramidByFTEStaging(Integer solutionLeverID,
	    FTEStaging fteStaging) throws DAOException;

    /**
     * 
     * Description : Removes all saved additional FTEs. Method Name :
     * removeAllAdditionalFTEsBySolutionID Input& Output:
     * 
     * @param solutionID
     * @return int
     * @throws DAOException
     */
    int removeAllAdditionalFTEsBySolutionID(Integer solutionID)
	    throws DAOException;

    /**
     * 
     * Description : Returns all roles of the system Method Name : getAllJobRole
     * Input& Output:
     * 
     * @return List
     */
    public List<JobRole> getAllJobRole();

    /**
     * 
     * Description : Save one object and return its newly generated ID Method
     * Name : saveObjectReturnId Input& Output:
     * 
     * @param o
     * @return Integer
     */
    public Integer saveObjectReturnId(Object o);

    /**
     * 
     * Description : Returns all Service scopes Method Name : getAllServiceScope
     * Input& Output:
     * 
     * @return
     * @throws DAOException
     */
    public List<ServiceScope> getAllServiceScope() throws DAOException;

    /**
     * 
     * Description : Returns list of productivity levers by oppscope ID& lever
     * ID Method Name : getProductivityLeversByLeverScopeID Input& Output:
     * 
     * @param solutionLeverID
     * @param oppScopeIDs
     * @return List<ProductivityLever>
     * @throws DAOException
     */
    List<ProductivityLever> getProductivityLeversByLeverScopeID(
	    Integer solutionLeverID, Integer oppScopeIDs) throws DAOException;

    /**
     * 
     * Description : Returns StartupFTE value by solution and oppScope ID Method
     * Name : getStartupFTEBySolOppScopeID Input& Output:
     * 
     * @param solutionID
     * @param oppScopeIDs
     * @return float
     */
    double getStartupFTEBySolOppScopeID(Integer solutionID, Integer oppScopeIDs);

    /**
     * 
     * Description : It will do batch update of FTEStaging objects Method Name :
     * saveFTESummaryList Input& Output:
     * 
     * @param objectsList
     */
    void saveFTESummaryList(List<FTESummary> objectsList);

    /**
     * 
     * Description	: Returns list of FTESummary by solution ID
     * Method Name	: getFTESummaryBySolutionID
     * Input& Output:
     * 	@param solutionID
     * 	@return List<FTESummary>
     * 	@throws DAOException
     */
    List<FTESummary> getFTESummaryListBySolutionID(Integer solutionID)
	    throws DAOException;

    /**
     * 
     * Description	: Delete solution all FTESummary records for regeneration
     * Method Name	: removeAllFTESummaryBySolutionID
     * Input& Output:
     * 	@param solutionID
     * 	@return int
     * 	@throws DAOException
     */
    int removeAllFTESummaryBySolutionID(Integer solutionID) throws DAOException;

    /**
     * Fetches Job Stages for specific Job Role
     * @param jobRole
     * @return
     * @throws MSSPException
     */
    public List<JobRoleStages> getJobStages(String jobRole) throws DAOException;
    
    /**
     * Fetches Job Roles for Service Element
     * @param serviceElementId
     * @param ccmFLag
     * @return
     * @throws DAOException
     */
    public List<ServiceElementJobRoleStages> getJobRoleByServiceElementId(Integer serviceElementId) throws DAOException;
    
    /**
     * Fetches Service Element from Opportunity Scope
     * @param oppScopeId
     * @return
     * @throws DAOException
     */
    public ServiceElement getServiceElementIdByOppScopeId(Integer oppScopeId)
			throws DAOException;
    
    /**
     * Fetches Job Roles as per the CCM Flag
     * @param ccmFlag
     * @return
     * @throws DAOException
     */
    public List<JobRoleStages> getJobRoles(int ccmFlag) throws DAOException;
    
    public List<FTESummary> getFteSummaryListByPassedParam(Integer solutionID,Integer serviceScopeID,String location, String subLocation) throws DAOException;
    
    public List<FTESummary> getFteSummaryListByPassedParam(Integer solutionID,Integer serviceScopeID,String location) throws DAOException;
    
    public List<FTEStaging> getFtestagingListByPassedParam(Integer solutionID, Integer serviceScopeID, Integer jobRoleStageID,String location, String subLocation) throws DAOException;
    
    public List<JobRoleStages> getSpecificJobRolesForSolution(Integer solutionID , Integer opportunityScopeID) throws DAOException;
    
    public List<FTEStaging> getFtestagingByPassedParam(Integer solutionID,
    	    Integer serviceScopeID, Integer jobRoleStageID, Date startDate,
    	    Date endDate,String location, String subLocation) throws DAOException;
    
    public int  getFteCount(Integer solutionID,
    	    Integer serviceScopeID, Integer jobRoleStageID, String location, String subLocation) throws DAOException;
    
    public List<Object[]> fetchFTESummary(Integer solutionID,Integer serviceScopeID,String location, String subLocation) throws Exception;
    public boolean isFTEStagingRecordAvailable (Integer solutionID,Integer opportunityScopeID) throws Exception;
    public List<FTESummary> getFTESummaryBySolnIDoppID(Integer solutionID,Integer opportunityScopeID)
    	    throws DAOException;
    public List<LocationPyramid> getLocationPyramidForOppScopeID(Integer opportunityScopeID) throws DAOException;
    
    public boolean isFTESummaryRecordsAvailable(Integer solutionID,Integer opportunityScopeID) throws Exception;
    public List<LocationPyramid> getLocPyramidSiteWise(Integer opportunityScopeID,String location, String subLocation) throws DAOException;
}
