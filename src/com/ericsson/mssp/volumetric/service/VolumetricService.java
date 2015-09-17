package com.ericsson.mssp.volumetric.service;

import java.util.List;

import com.ericsson.mssp.common.dto.AccessManagementDTO;
import com.ericsson.mssp.common.dto.AppMainSlaDTO;
import com.ericsson.mssp.common.dto.AppMainSupportActivityDTO;
import com.ericsson.mssp.common.dto.AvailabilityManagementDTO;
import com.ericsson.mssp.common.dto.BuildDTO;
import com.ericsson.mssp.common.dto.CapacityManagementDTO;
import com.ericsson.mssp.common.dto.ChangeManagementDTO;
import com.ericsson.mssp.common.dto.ConfigurationManagementDTO;
import com.ericsson.mssp.common.dto.DemandSupportDTO;
import com.ericsson.mssp.common.dto.DeploymentRollOutDTO;
import com.ericsson.mssp.common.dto.DesignAndBuildDTO;
import com.ericsson.mssp.common.dto.DesignDTO;
import com.ericsson.mssp.common.dto.OpportunityComponentDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.ReleaseManagementDTO;
import com.ericsson.mssp.common.dto.ProgramManagementDTO;
import com.ericsson.mssp.common.dto.SecurityManagementDTO;
import com.ericsson.mssp.common.dto.ServiceAssuranceDTO;
import com.ericsson.mssp.common.dto.ServiceScopeAppMainDataBean;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.dto.PostReleaseActivitiesDTO;
import com.ericsson.mssp.common.dto.SupportWindowMatrixDTO;
import com.ericsson.mssp.common.dto.TicketDistributionDTO;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.exception.MSSPException;



public interface VolumetricService {
	
	public List<OpportunityScopeDTO> getServiceScopeByServiceElement(Integer oppId,String selectedTab) throws MSSPException;
	
	Float loadServiceBucketDataByOppSolutionID(Integer opportunityID,
			Integer solutionID) throws MSSPException;

	ProgramManagementDTO loadProgramManagement(Integer solId , Integer serviceScopeID)
			throws MSSPException;

	void saveProgramManagement(ProgramManagementDTO programManagementDTO,
			Integer solutionId, Integer oppScopeId)
			throws MSSPException;

	ReleaseManagementDTO loadReleaseManagement(Integer solId, Integer serviceScopeID)
			throws MSSPException;

	void saveReleaseManagement(ReleaseManagementDTO releaseManagementDTO,
			Integer solutionId, Integer oppScopeId)
			throws MSSPException;

	ChangeManagementDTO loadChangeManagement(Integer solId, Integer serviceScopeID)
			throws MSSPException;

	void saveChangeManagement(ChangeManagementDTO changeManagementDTO,
			Integer solutionId, Integer oppScopeId)
			throws MSSPException;

	CapacityManagementDTO loadCapacityManagement(Integer solId, Integer serviceScopeID)
			throws MSSPException;

	void saveCapacityManagement(CapacityManagementDTO capacityManagementDTO,
			Integer solutionId, Integer oppScopeId)
			throws MSSPException;

	ConfigurationManagementDTO loadConfigurationManagement(
			Integer solId, Integer serviceScopeID) throws MSSPException;

	void saveConfigurationManagement(
			ConfigurationManagementDTO configurationManagementDTO,
			Integer solutionId, Integer oppScopeId)
			throws MSSPException;

	SecurityManagementDTO loadSecurityManagement(Integer solId, Integer serviceScopeID)
			throws MSSPException;

	void saveSecurityManagement(SecurityManagementDTO securityManagementDTO,
			Integer solutionId, Integer oppScopeId)
			throws MSSPException;

	AvailabilityManagementDTO loadAvailabilityManagement(
			Integer solId, Integer serviceScopeID) throws MSSPException;

	void saveAvailabilityManagement(
			AvailabilityManagementDTO availabilityManagementDTO,
			Integer solutionId, Integer oppScopeId)
			throws MSSPException;

	AccessManagementDTO loadAccessManagementBySolutionId(Integer solId)
			throws MSSPException;

	void saveAccessManagement(AccessManagementDTO accessManagementDTO,
			Integer solutionId, Integer solDimentionAttId, Integer oppScopeId)
			throws MSSPException;

	public List<ServiceScopeDTO> getAllServiceScopeByServiceElement(
			String serviceelement);
	
	// Demand Support
	void saveDemandSupport(DemandSupportDTO demandSupportDTO, Integer solutionId, Integer oppScopeId) throws MSSPException;
	
	DemandSupportDTO loadDemandSupport(Integer solId, Integer serviceScopeID)
			throws MSSPException;
	// DesignBuild
	void saveDesignBuild(Integer solId, Integer oppScopeId, DesignAndBuildDTO designAndBuildDTO) throws MSSPException;
	DesignAndBuildDTO loadDesignAndBuild(Integer solId,Integer serviceScopeID) throws MSSPException;
	
	/*// Design
	void saveDesign(DesignDTO designDTO) throws MSSPException;	
	DesignDTO loadDesignBySolutionId(Integer solId) throws MSSPException;
	
	//Build
	void saveBuild(BuildDTO buildDTO, Integer solId) throws MSSPException;	
	BuildDTO loadBuildDTOBySolutionId(Integer solId) throws MSSPException;*/
	
	// DeploymentRollOut
	void saveDeploymentRollOut(DeploymentRollOutDTO deploymentRollOutDTO, Integer solId, Integer opportunityScopeId) throws MSSPException;	
	DeploymentRollOutDTO loadDeploymentRollOutDTO(Integer solId, Integer serviceScopeID) throws MSSPException;
	
	// Post Release Acitvities
	void savePostReleaseActivity(PostReleaseActivitiesDTO postRelActDTO , Integer solId , Integer oppScopeId) throws MSSPException;
	PostReleaseActivitiesDTO loadPostRelActDTO(Integer solId, Integer serviceScopeID) throws MSSPException;
	
	/**
	 * Returns the Opportunity details for a particular opportunity
	 * @param opportunityId
	 * @return OpportunityDetail
	 * @throws MSSPException
	 */
	public OpportunityDetail getOpportunityDetail(Integer opportunityId) throws MSSPException;
	
	
	//Service Assurance
	ServiceAssuranceDTO loadServiceAssurance(Integer solId, Integer serviceScopeID) throws MSSPException;
	void saveServiceAssurance(ServiceAssuranceDTO serviceAssuranceDTO , Integer solId , Integer oppScopeId) throws MSSPException;
	
	public AppMainSupportActivityDTO getSuppActivityDTOByOppCompID(Integer oppCompId) throws MSSPException;
	public void saveAppMainSupportActivity(AppMainSupportActivityDTO dto) throws MSSPException;
	public void saveAppMainSLA(AppMainSlaDTO appMainSlaDTO) throws MSSPException;
	public List<AppMainSlaDTO> getAppMainSlaDtoByOppId(Integer oppId) throws MSSPException;
	public List<TicketDistributionDTO> getTktDistByOppScopeIds(List<OpportunityScopeDTO> opportunityScopeList , String serviceElement) throws MSSPException;
	public void saveTicketDistribution(TicketDistributionDTO tktDistDTO) throws MSSPException;
	public List<AppMainSupportActivityDTO> getSuppActListByOppCompIds(List<OpportunityComponentDTO> components) throws MSSPException;
	public List<SupportWindowMatrixDTO> getAllSupportWindowMatrix() throws MSSPException;
	public Double getFactor(Integer SolutionID, String paramTypeCode) throws MSSPException;
	public List<Object[]> getTotalBaseHrsFteForDevMain(Integer solId) throws MSSPException;
	public List<ServiceScopeAppMainDataBean> getProductEstBaseEffData(List<OpportunityScopeDTO> opportunityScopeList) throws MSSPException;
	
	/**
	 * Checks to see if opportunity contains specified service element
	 * @param opportunityId
	 * @param serviceElementName
	 * @return boolean
	 * @throws MSSPException
	 */
	public boolean opportunityHasServiceElement(Integer opportunityId, String serviceElementName) throws MSSPException;
}
