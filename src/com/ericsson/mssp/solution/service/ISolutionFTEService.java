/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.service
 * File name       		    :  ISolutionFTEService.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Mar 19, 20132013
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Mar 19, 2013  	   |edassri   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ericsson.mssp.common.dto.MonthFTEDTO;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.entity.AdditionalFTE;
import com.ericsson.mssp.common.entity.FTEStaging;
import com.ericsson.mssp.common.entity.FTESummary;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.LocationBreakup;
import com.ericsson.mssp.common.entity.LocationPyramid;
import com.ericsson.mssp.common.entity.Opportunity;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.ServiceElement;
import com.ericsson.mssp.common.entity.ServiceScope;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionLever;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.forms.ReviewFTEForm;

/**
 * @author edassri
 * 
 */
public interface ISolutionFTEService {

    /**
     * 
     * Description : It will return all opportunity selected services list by
     * solution ID Method Name : getAllServiceScopeInMapBySolId Input& Output:
     * 
     * @param solutionId
     * @return
     * @throws MSSPException
     */
    Map<Integer, String> getAllServiceScopeInMapBySolId(Integer solutionId)
	    throws MSSPException;

    /**
     * 
     * Description : Returns all job roles of the system Method Name :
     * getJobRoles Input& Output:
     * 
     * @return Map<Integer, String>
     * @throws MSSPException
     */
    Map<Integer, String> getJobRoles() throws MSSPException;

    /**
     * 
     * Description : It will return opportunity object by passed sol ID Method
     * Name : getOpportunityById Input& Output:
     * 
     * @param solutionID
     * @return Opportunity
     * @throws MSSPException
     */
    Opportunity getOpportunityBySolId(Integer solutionID) throws MSSPException;

    /**
     * 
     * Description : It will fetch opportunity selected services list in scope
     * by opp ID Method Name : getAllServiceScopeInMapByOppID Input& Output:
     * 
     * @param opportunityId
     * @return
     * @throws MSSPException
     */
    Map<Integer, String> getAllServiceScopeInMapByOppID(Integer opportunityId)
	    throws MSSPException;

    /**
     * 
     * Description : It will return Solution object by passed solution ID Method
     * Name : getSolutionById Input& Output:
     * 
     * @param solutionID
     * @return Solution
     * @throws MSSPException
     */
    Solution getSolutionById(Integer solutionID) throws MSSPException;

    /**
     * 
     * Description : Load all service wise onshore and offshore values
     * isForceReload true will flush cached values and regenerate Method Name :
     * loadFTETableDisplayValues Input& Output:
     * 
     * @param reviewFTEForm
     * @param isForceReload
     * @return reviewFTEForm
     */
    ReviewFTEForm loadFTETableDisplayValues(ReviewFTEForm reviewFTEForm,
	    boolean isForceReload);

    /**
     * 
     * Description : It will return additional FTE for the service id and
     * solution id Method Name : getAddionalFTE Input& Output:
     * 
     * @param reviewFTEForm
     * @return
     */
    ReviewFTEForm getAddionalFTE(ReviewFTEForm reviewFTEForm);

    void updateIntoFTEStaging(AdditionalFTE additionalFTE, boolean isDelete)
	    throws MSSPException;

    void deleteAdditionalFTE(AdditionalFTE additionalFTE) throws MSSPException;

    /**
     * 
     * Description : TO save additional FTE Method Name : saveAdditionalFTE
     * Input& Output:
     * 
     * @param additionalFTE
     * @return Integer
     */
    Integer saveAdditionalFTE(AdditionalFTE additionalFTE);

    /**
     * 
     * Description : Returns month year in a string from the opportunity time
     * line Method Name : getOpportunitySteadyMonthYearsString Input& Output:
     * 
     * @param solutionID
     * @return
     * @throws MSSPException
     */
    String getOpportunityTransStartSteadyEndMonthYearsString(Integer solutionID)
	    throws MSSPException;
    
    /**
     * Fetches Job Stages for specific Job Role
     * @param jobRole
     * @return
     * @throws MSSPException
     */
    public Map<Integer, String> getJobStages(String jobRole) throws MSSPException;
    
    /**
     * Fetches Job Role for specific Service Element
     * @param serviceElementId
     * @return
     * @throws MSSPException
     */
    public Map<Integer, String> getJobRoleByServiceElementId(Integer serviceElementId) throws MSSPException;
    
    
    /**
     * Fetches Service Element from Opportunity Scope
     * @param oppScopeId
     * @return
     * @throws DAOException
     */
	public ServiceElement getServiceElementIdByOppScopeId(Integer oppScopeId) throws MSSPException;
	
	/**
	 * Fetches Job Roles as per the CCM Flag
	 * @param ccmFlag
	 * @return
	 * @throws MSSPException
	 */
	public Map<Integer, String> getJobRoles(int ccmFlag) throws MSSPException;
	
	/**
	 * Fetches Job Role for specific Service Element - for dropdown - clubbing job role and job stage
	 * @param serviceElementId
	 * @return
	 * @throws MSSPException
	 */
	public Map<Integer, String> getJobRoleDropdownByServiceElementId(Integer serviceElementId) throws MSSPException;
	
	/**
	 * Fetches Job Roles as per the CCM Flag - for dropdown - clubbing job role and job stage
	 * @param ccmFlag
	 * @return
	 * @throws MSSPException
	 */
	public Map<Integer, String> getJobRolesDropdown(int ccmFlag) throws MSSPException;
	
	
	public List<FTESummary> getFteSummaryListByPassedParam(Integer solutionID,Integer serviceScopeID,String location, String subLocation) throws MSSPException;
	
	public ServiceScopeDTO getServiceScopeForOpportunityScopeId(Integer opportunityId) throws MSSPException;
	
	public List<FTEStaging> getFtestagingListByPassedParam(Integer solutionID,
    	    Integer serviceScopeID, Integer jobRoleStageID,String location, String subLocation)
    	    throws MSSPException;
	
	public List<JobRoleStages> getSpecificJobRolesForSolution(Integer solutionID , Integer opportunityScopeID) throws MSSPException;
	
	public List<FTEStaging> getFTEStagingObjects(Integer solutionID,Integer serviceScopeID, Integer jobRoleStageID, Date fromDate,Date toDate, String location, String subLocation) throws MSSPException;
	
	public void saveAdditionalFTE(List<FTEStaging> fteStagingList) throws MSSPException;
	
	public int  getFteCount(Integer solutionID,
    	    Integer serviceScopeID, Integer jobRoleStageID, String location, String subLocation) throws MSSPException;
	
	public OpportunityDetail getOpportunityDetail(Integer opportunityId) throws MSSPException;
	public Solution getSolution(Integer solutionId) throws MSSPException;
	public OpportunityScope getOpportunityScope(Integer opportunityScopeID) throws MSSPException;
	public JobRoleStages getJobRoleStagesByjobRoleStagesId(Integer jobRoleStagesId) throws MSSPException;
	public List<MonthFTEDTO> fetchFTESummary(Integer solutionID,Integer serviceScopeID,String location, String subLocation) throws MSSPException;
	public SolutionLever  getSolutionLeverForSolution(Integer solutionID) throws MSSPException;
	
	public boolean isFTEStagingRecordAvailable (Integer solutionID,Integer opportunityScopeID) throws MSSPException;
	public List<LocationBreakup> getLocationBreakupByOppScopeID(Integer oppScopeId) throws MSSPException;
	public List<FTESummary> getFTESummaryBySolnIDoppID(Integer solutionID,Integer opportunityScopeID) throws MSSPException;
	public List<LocationPyramid> getLocationPyramidForOppScopeID(Integer opportunityScopeID) throws MSSPException;
	public int removeAllFTEStagingBySolutionID(Integer solutionID) throws MSSPException;
	public boolean isFTESummaryRecordsAvailable(Integer solutionID,Integer opportunityScopeID) throws MSSPException;
	public List<LocationPyramid> getLocPyramidSiteWise(Integer opportunityScopeID,String location, String subLocation) throws MSSPException;
}
