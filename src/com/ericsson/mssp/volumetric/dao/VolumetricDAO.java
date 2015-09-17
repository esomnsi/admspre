package com.ericsson.mssp.volumetric.dao;

import java.util.List;

import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.entity.AccessManagement;
import com.ericsson.mssp.common.entity.AppMainSla;
import com.ericsson.mssp.common.entity.AppMainSupportActivity;
import com.ericsson.mssp.common.entity.AvailabilityManagement;
import com.ericsson.mssp.common.entity.CapacityManagement;
import com.ericsson.mssp.common.entity.ChangeManagement;
import com.ericsson.mssp.common.entity.ConfigurationManagement;
import com.ericsson.mssp.common.entity.DemandSupport;
import com.ericsson.mssp.common.entity.DeploymentRollOut;
import com.ericsson.mssp.common.entity.DesignAndBuild;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.OtherDefaults;
import com.ericsson.mssp.common.entity.PostReleaseActivities;
import com.ericsson.mssp.common.entity.ProductEstimationBaseEffortForSolution;
import com.ericsson.mssp.common.entity.ProgramManagement;
import com.ericsson.mssp.common.entity.ReleaseManagement;
import com.ericsson.mssp.common.entity.SecurityManagement;
import com.ericsson.mssp.common.entity.ServiceAssurance;
import com.ericsson.mssp.common.entity.SupportWindowMatrix;
import com.ericsson.mssp.common.entity.TicketDistribution;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;



public interface VolumetricDAO {

	public List<OpportunityScopeDTO> getServiceScopeByServiceElement(Integer oppId, String selectCriteria) throws MSSPException;
	
	public List<ProgramManagement> loadProgramManagementListBySolutionId(
			Integer solId)throws MSSPException;

	void saveProgramManagement(ProgramManagement programManagement)
			throws MSSPException;

	public List<ReleaseManagement> loadReleaseManagementListBySolutionId(
			Integer solId) throws MSSPException;

	void saveReleaseManagement(ReleaseManagement releaseManagement)
			throws MSSPException;

	public List<ChangeManagement> loadChangeManagementListBySolutionId(
			Integer solId);

	public void saveChangeManagement(ChangeManagement changeManagement);

	List<String> getServiceBucketDataByOppSolutionID(Integer opportunityID,
			Integer solutionID);

	List<CapacityManagement> loadCapacityManagementListBySolutionId(
			Integer solId);

	void saveCapacityManagement(CapacityManagement capacityManagement)
			throws MSSPException;

	List<AccessManagement> loadAccessManagementListBySolutionId(Integer solId);

	List<AvailabilityManagement> loadAvailabilityManagementListBySolutionId(
			Integer solId);

	List<SecurityManagement> loadSecurityManagementListBySolutionId(
			Integer solId);

	void saveAccessManagement(AccessManagement accessManagement)
			throws MSSPException;

	void saveAvailabilityManagement(
			AvailabilityManagement availabilityManagement) throws MSSPException;

	void saveSecurityManagement(SecurityManagement securityManagement)
			throws MSSPException;

	List<ConfigurationManagement> loadConfigurationManagementListBySolutionId(
			Integer solId);

	void saveConfigurationManagement(
			ConfigurationManagement configurationManagement)
			throws MSSPException;

	public List<ServiceScopeDTO> getAllServiceScopeByServiceElement(
			String serviceelement);
	
	// Demand Support
	void saveDemandSupport(	DemandSupport demandSupport) throws MSSPException;	
	public List<DemandSupport> loadDemandSupportListBySolutionId(Integer solId)throws MSSPException;
	
	//Design And Build
	public List<DesignAndBuild> loadDesignBuildListBySolutionId(Integer solId) throws MSSPException;
	void saveDesignBuild(DesignAndBuild designBuild) throws MSSPException;
	/*// Design
	void saveDesign(Design design) throws MSSPException;	
	public List<Design> loadDesignListBySolutionId(Integer solId) throws MSSPException;
	
	// Build
	void saveBuild(Build build) throws MSSPException;	
	public List<Build> loadBuildListBySolutionId(Integer solId) throws MSSPException;*/
	
	// Deployment Roll Out
	void saveDeploymentRollOut(DeploymentRollOut deploymentRollOut) throws MSSPException;
	public List<DeploymentRollOut> loadDeploymentRollOutListBySolutionId(Integer solId) throws MSSPException;
	
	// Post Release Activities
	void savePostReleaseActivities(PostReleaseActivities postRelAct) throws MSSPException;
	public List<PostReleaseActivities> loadPostReleaseActivitiesListBySolutionId(Integer solId) throws MSSPException;
	
	// Load Default Values
	public List<OtherDefaults> loadDefaultValuesByServiceScopeID(Integer serviceScopeID) throws MSSPException;
	
	/**
	 * Returns the Opportunity details for a particular opportunity
	 * @param opportunityId
	 * @return OpportunityDetail
	 * @throws DAOException
	 */
	public OpportunityDetail getOpportunityDetail(Integer opportunityId) throws DAOException;
	
	//ServiceAssurance
	public List<ServiceAssurance> loadServiceAssuranceListBySolutionId(Integer solId) throws MSSPException;
	void saveServiceAssurance(ServiceAssurance serviceAssurance) throws MSSPException;
	
	public AppMainSupportActivity getSuppActivityByOppCompID(Integer oppCompId) throws MSSPException;
	public void saveAppMainSupportActivity(AppMainSupportActivity entity) throws MSSPException;
	public void saveAppMainSLA(AppMainSla appMainSla)throws MSSPException;
	public List<AppMainSla> getAppMainSlaDtoByOppId(Integer oppId) throws MSSPException;
	public List<OtherDefaults> getDefaultAppMainSlaDto() throws MSSPException;
	public List<TicketDistribution> getTktDistByOppScopeIds(List<Integer> oppScopeIds) throws MSSPException;
	public List<OtherDefaults> getDefTktDistByServEle(String serviceElement) throws MSSPException;
	public void saveTicketDistribution(TicketDistribution entity) throws MSSPException;
	public List<AppMainSupportActivity> getSuppActListByOppCompIds(List<Integer> oppCompIds) throws MSSPException;
	public List<OtherDefaults> getDefSuppActivity() throws MSSPException;
	public List<OtherDefaults> getDefSuppActivityByCompName(List<String> compNames) throws MSSPException;
	public SupportWindowMatrix getSupportWindowMatrixById(Integer suppWindowMatrixID) throws MSSPException;
	public List<SupportWindowMatrix> getAllSupportWindowMatrix() throws MSSPException;
	public List<Object> getFactorBySolutionId(Integer SolutionID,String paramTypeCode) throws MSSPException;
	public List<Object> getDefaultFactor(String paramTypeCode) throws MSSPException;
	public List<Object[]> getTotalBaseHrsFteForDevMain(Integer solId) throws MSSPException;
	public List<ProductEstimationBaseEffortForSolution> getProductEstBaseEffData(List<Integer> oppScopeIdList) throws MSSPException;
	public List<OtherDefaults> getDefTktDistByServEleServScopes(String serviceElement,List<Integer> addedServElementIds) throws MSSPException;
	
	/**
	 * Checks to see if opportunity contains specified service element
	 * @param opportunityId
	 * @param serviceElementName
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean opportunityHasServiceElement(Integer opportunityId, String serviceElementName) throws DAOException;
}
