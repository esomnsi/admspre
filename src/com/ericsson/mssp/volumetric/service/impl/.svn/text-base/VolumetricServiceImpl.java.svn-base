package com.ericsson.mssp.volumetric.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.constant.MSSPConstants;
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
import com.ericsson.mssp.common.dto.PostReleaseActivitiesDTO;
import com.ericsson.mssp.common.dto.ReleaseManagementDTO;
import com.ericsson.mssp.common.dto.ProgramManagementDTO;
import com.ericsson.mssp.common.dto.SecurityManagementDTO;
import com.ericsson.mssp.common.dto.ServiceAssuranceDTO;
import com.ericsson.mssp.common.dto.ServiceScopeAppMainDataBean;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.dto.SupportWindowMatrixDTO;
import com.ericsson.mssp.common.dto.TicketDistributionDTO;
import com.ericsson.mssp.common.entity.AccessManagement;
import com.ericsson.mssp.common.entity.AppMainSla;
import com.ericsson.mssp.common.entity.AppMainSupportActivity;
import com.ericsson.mssp.common.entity.AvailabilityManagement;
import com.ericsson.mssp.common.entity.Build;
import com.ericsson.mssp.common.entity.CapacityManagement;
import com.ericsson.mssp.common.entity.ChangeManagement;
import com.ericsson.mssp.common.entity.ConfigurationManagement;
import com.ericsson.mssp.common.entity.DemandSupport;
import com.ericsson.mssp.common.entity.DeploymentRollOut;
import com.ericsson.mssp.common.entity.Design;
import com.ericsson.mssp.common.entity.DesignAndBuild;
import com.ericsson.mssp.common.entity.OpportunityComponent;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.OtherDefaults;
import com.ericsson.mssp.common.entity.PostReleaseActivities;
import com.ericsson.mssp.common.entity.ProductEstimationAuxiliaryParams;
import com.ericsson.mssp.common.entity.ProductEstimationBaseEffortForSolution;
import com.ericsson.mssp.common.entity.ReleaseManagement;
import com.ericsson.mssp.common.entity.SecurityManagement;
import com.ericsson.mssp.common.entity.ServiceAssurance;
import com.ericsson.mssp.common.entity.ServiceScope;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.ProgramManagement;
import com.ericsson.mssp.common.entity.SupportWindowMatrix;
import com.ericsson.mssp.common.entity.TicketDistribution;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.volumetric.controller.ApplicationMaintenanceController;
import com.ericsson.mssp.volumetric.dao.VolumetricDAO;
import com.ericsson.mssp.volumetric.service.VolumetricService;


@Service
public class VolumetricServiceImpl implements VolumetricService,MSSPConstants{
	
	private static final Log logger = LogFactory.getLog(VolumetricServiceImpl.class);
	
	@Autowired
	VolumetricDAO volDao;
	
	
	@Override
	public List<OpportunityScopeDTO> getServiceScopeByServiceElement(Integer oppId,String selectedTab) throws MSSPException {
		
		return volDao.getServiceScopeByServiceElement(oppId, selectedTab);
		
	}
	
	/**
	 * 
	 * Description : TODO Method Name : convertPMEnitityToDTO Input& Output:
	 * 
	 * @param solProgramMgmt
	 * @return
	 */
	private ProgramManagementDTO convertPMEnitityToDTO(ProgramManagement programMgmt) {

		ProgramManagementDTO programMgmtDTO = new ProgramManagementDTO();
		programMgmtDTO.setProgramManagementID(programMgmt.getProgramManagementID());
		programMgmtDTO.setCoordinateProjects(programMgmt.getCoordinateProjects());
		programMgmtDTO.setEnsureAchievement(programMgmt.getEnsureAchievement());
		programMgmtDTO.setManageClient(programMgmt.getManageClient());
		programMgmtDTO.setManageQualityRiskIssues(programMgmt.getManageQualityRiskIssues());
		programMgmtDTO.setMeasureADMServiceKPI(programMgmt.getMeasureADMServiceKPI());	
		programMgmtDTO.setTotalProgramManagementHours(programMgmt.getTotalProgramManagementHours());
		
		return programMgmtDTO;
	}
	
	/**
	 * 
	 * Description : TODO Method Name : convertRMEnitityToDTO Input& Output:
	 * 
	 * @param releaseMgmt
	 * @return
	 */
	private ReleaseManagementDTO convertRMEnitityToDTO(	ReleaseManagement releaseMgmt) {

		ReleaseManagementDTO releaseManagementDTO = new ReleaseManagementDTO();
		releaseManagementDTO.setReleaseManagementID(releaseMgmt.getReleaseManagementID());
		releaseManagementDTO.setInstlnDistnOfSoftRel(releaseMgmt.getInstlnDistnOfSoftRel());
		releaseManagementDTO.setDefineRolloutPlan(releaseMgmt.getDefineRolloutPlan());
		releaseManagementDTO.setEnsureTraceability(releaseMgmt.getEnsureTraceability());
		releaseManagementDTO.setEnsureInstallation(releaseMgmt.getEnsureInstallation());
		releaseManagementDTO.setTotalReleaseManagementHours(releaseMgmt.getTotalReleaseManagementHours());
		/*releaseManagementDTO.setReleaseManagementID(releaseMgmt.getReleaseManagementID());
		releaseManagementDTO.setTotalNumberEffortHours(releaseMgmt
				.getTotalNumberEffortHours());
		releaseManagementDTO.setPercentOfTotalEffort(releaseMgmt
				.getPercentOfTotalEffort());
		releaseManagementDTO.setPercentOfServiceElementEffort(releaseMgmt.getPercentOfServiceElementEffort());*/
		/*releaseManagementDTO.setSoftwareDefectsEffort(releaseMgmt.getSoftwareDefectsEffort());
		releaseManagementDTO.setIssuesEffort(releaseMgmt.getIssuesEffort());
		releaseManagementDTO.setRisksEffort(releaseMgmt.getRisksEffort());
		releaseManagementDTO.setSoftwareChangeRequestsEffort(releaseMgmt.getSoftwareChangeRequestsEffort());
		releaseManagementDTO.setNewDevelopmentRequestsEffort(releaseMgmt.getNewDevelopmentRequestsEffort());
		releaseManagementDTO.setDeploymentAndPackagingEffort(releaseMgmt.getDeploymentAndPackagingEffort());
		releaseManagementDTO.setNewDevelopmentTasksEffort(releaseMgmt.getNewDevelopmentTasksEffort());
		releaseManagementDTO.setOtherReleaseManagementEffort(releaseMgmt.getOtherReleaseManagementEffort());
		*/
		return releaseManagementDTO;
	}
	
	/**
	 * 
	 * Description : TODO Method Name : convertCMEnitityToDTO Input& Output:
	 * 
	 * @param changeMgmt
	 * @return
	 */
	private ChangeManagementDTO convertCMEnitityToDTO(ChangeManagement changeMgmt) {

		ChangeManagementDTO changeManagementDTO = new ChangeManagementDTO();
		changeManagementDTO.setApproveChangesAndPlanning(changeMgmt.getApproveChangesAndPlanning());
		changeManagementDTO.setChangeManagementID(changeMgmt.getChangeManagementID());
		changeManagementDTO.setEnsureStandards(changeMgmt.getEnsureStandards());
		changeManagementDTO.setFeasibilityImpactAndCostAnalysis(changeMgmt.getFeasibilityImpactAndCostAnalysis());
		changeManagementDTO.setManageTraceability(changeMgmt.getManageTraceability());
		changeManagementDTO.setReqGatheringAndAnalysis(changeMgmt.getReqGatheringAndAnalysis());
		changeManagementDTO.setTotalChangeManagementHours(changeMgmt.getTotalChangeManagementHours());
				
		return changeManagementDTO;
	}
	
	/**
	 * 
	 * Description : TODO Method Name : convertCPMEnitityToDTO Input& Output:
	 * 
	 * @param capacityMgmt
	 * @return
	 */
	private CapacityManagementDTO convertCPMEnitityToDTO(
			CapacityManagement capacityMgmt) {

		CapacityManagementDTO capacityManagementDTO = new CapacityManagementDTO();
		capacityManagementDTO.setCapacityManagementID(capacityMgmt.getCapacityManagementID());
		capacityManagementDTO.setPrepareAndUseModels(capacityMgmt.getPrepareAndUseModels());
		capacityManagementDTO.setFteCapacityManagement(capacityMgmt.getFteCapacityManagement());
		return capacityManagementDTO;
	}
	
	/**
	 * 
	 * Description : TODO Method Name : convertCFGEnitityToDTO Input& Output:
	 * 
	 * @param configurationMgmt
	 * @return
	 */
	private ConfigurationManagementDTO convertCFGEnitityToDTO(
			ConfigurationManagement configurationMgmt) {

		ConfigurationManagementDTO configurationManagementDTO = new ConfigurationManagementDTO();
		configurationManagementDTO.setConfigurationManagementID(configurationMgmt.getConfigurationManagementID());
		configurationManagementDTO.setManageConfigurationItems(configurationMgmt.getManageConfigurationItems());
		configurationManagementDTO.setManageConfigInfoAndDocumentation(configurationMgmt.getManageConfigInfoAndDocumentation());
		configurationManagementDTO.setFteConfigurationManagement(configurationMgmt.getFteConfigurationManagement());
		
		return configurationManagementDTO;
	}
	
	/**
	 * 
	 * Description : TODO Method Name : convertSECEnitityToDTO Input& Output:
	 * 
	 * @param securityMgmt
	 * @return
	 */
	private SecurityManagementDTO convertSECEnitityToDTO(
			SecurityManagement securityMgmt) {

		SecurityManagementDTO securityManagementDTO = new SecurityManagementDTO();
		securityManagementDTO.setSecurityManagementID(securityMgmt.getSecurityManagementID());
		securityManagementDTO.setManageSecurity(securityMgmt.getManageSecurity());
		securityManagementDTO.setFteSecurityManagement(securityMgmt.getFteSecurityManagement());
		return securityManagementDTO;
	}
	
	/**
	 * 
	 * Description : TODO Method Name : convertAVLEnitityToDTO Input& Output:
	 * 
	 * @param availabilityMgmt
	 * @return
	 */
	private AvailabilityManagementDTO convertAVLEnitityToDTO(
			AvailabilityManagement availabilityMgmt) {

		AvailabilityManagementDTO availabilityManagementDTO = new AvailabilityManagementDTO();
		
		availabilityManagementDTO.setAvailabilityManagementID(availabilityMgmt.getAvailabilityManagementID());
		availabilityManagementDTO.setManageAvailability(availabilityMgmt.getManageAvailability());
		availabilityManagementDTO.setManageOutageAndRisks(availabilityMgmt.getManageOutageAndRisks());
		availabilityManagementDTO.setFteAvailabilityManagement(availabilityMgmt.getFteAvailabilityManagement());
		return availabilityManagementDTO;
	}
	

	
	/**
	 * 
	 * Description : Returns Solution object by ID Method Name : getSolutionByID
	 * Input& Output:
	 * 
	 * @param solutionID
	 * @return Solution
	 */
	private Solution setSolutionById(Integer solutionId) {
		Solution solution = new Solution();
		solution.setSolutionId(solutionId);
		return solution;
	}
	
	private OpportunityScope getOpportunityScope(Integer oppScopeId){
		OpportunityScope opportunityScope = new OpportunityScope();
		opportunityScope.setOpportunityScopeId(oppScopeId);
		return opportunityScope;
	}

	
		
	private ProgramManagement convertFromProgramManagementDTO2Entity(
			ProgramManagementDTO programManagementDTO, Integer solutionId, Integer oppScopeId)
			throws MSSPException {
		

			ProgramManagement programManagement = new ProgramManagement();
			programManagement.setProgramManagementID(programManagementDTO.getProgramManagementID());
			programManagement.setCoordinateProjects(programManagementDTO.getCoordinateProjects());
			programManagement.setEnsureAchievement(programManagementDTO.getEnsureAchievement());
			programManagement.setManageClient(programManagementDTO.getManageClient());
			programManagement.setManageQualityRiskIssues(programManagementDTO.getManageQualityRiskIssues());
			programManagement.setMeasureADMServiceKPI(programManagementDTO.getMeasureADMServiceKPI());
			programManagement.setTotalProgramManagementHours(programManagementDTO.getTotalProgramManagementHours());
			
			programManagement.setSolution(setSolutionById(solutionId));
			programManagement.setOpportunityScope(getOpportunityScope(oppScopeId));
			/*programManagement.setProgramManagementID(programManagementDTO.getProgramManagementID());
			programManagement.setSolution(setSolutionById(solutionId));
			programManagement.setTotalNumberEffortHours(programManagementDTO.getTotalNumberEffortHours());
			programManagement.setPercentOfTotalEffort(programManagementDTO.getPercentOfTotalEffort());
			programManagement.setPercentOfServiceElementEffort(programManagementDTO.getPercentOfServiceElementEffort());
			programManagement.setFinancialMgmtAndBudgetingEffort(programManagementDTO.getFinancialMgmtAndBudgetingEffort());
			programManagement.setNegotiationsEffort(programManagementDTO.getNegotiationsEffort());
			programManagement.setCoordinationEffort(programManagementDTO.getCoordinationEffort());
			programManagement.setProjectPacingAndObtainIncrementalBenefitsEffort(
			programManagementDTO.getProjectPacingAndObtainIncrementalBenefitsEffort());
			programManagement.setKnowledgeEstablishingEffort(programManagementDTO.getKnowledgeEstablishingEffort());
			programManagement.setExecutingTheGovernanceFrameworkEffort(programManagementDTO.getExecutingTheGovernanceFrameworkEffort());
			programManagement.setOtherProgramMgmtEffort(programManagementDTO.getOtherProgramMgmtEffort());*/
			return programManagement;
		
	}
	
		
	private ReleaseManagement convertFromReleaseManagementDTO2Entity(
			ReleaseManagementDTO releaseManagementDTO, Integer solutionId, Integer oppScopeID)
			throws MSSPException {
		

		ReleaseManagement releaseManagement = new ReleaseManagement();
		
		releaseManagement.setReleaseManagementID(releaseManagementDTO.getReleaseManagementID());
		releaseManagement.setInstlnDistnOfSoftRel(releaseManagementDTO.getInstlnDistnOfSoftRel());
		releaseManagement.setDefineRolloutPlan(releaseManagementDTO.getDefineRolloutPlan());
		releaseManagement.setEnsureTraceability(releaseManagementDTO.getEnsureTraceability());
		releaseManagement.setEnsureInstallation(releaseManagementDTO.getEnsureInstallation());
		releaseManagement.setTotalReleaseManagementHours(releaseManagementDTO.getTotalReleaseManagementHours());
		
		releaseManagement.setSolution(setSolutionById(solutionId));
		releaseManagement.setOpportunityScope(getOpportunityScope(oppScopeID));
		
		return releaseManagement;
		
	}

	

	private ChangeManagement convertFromChangeManagementDTO2Entity1(ChangeManagementDTO changeManagementDTO, Integer solutionId, Integer oppScopeID)
			throws MSSPException {
		
		ChangeManagement changeManagement = new ChangeManagement();
		changeManagement.setApproveChangesAndPlanning(changeManagementDTO.getApproveChangesAndPlanning());
		changeManagement.setChangeManagementID(changeManagementDTO.getChangeManagementID());
		changeManagement.setEnsureStandards(changeManagementDTO.getEnsureStandards());
		changeManagement.setFeasibilityImpactAndCostAnalysis(changeManagementDTO.getFeasibilityImpactAndCostAnalysis());
		changeManagement.setManageTraceability(changeManagementDTO.getManageTraceability());
		changeManagement.setReqGatheringAndAnalysis(changeManagementDTO.getReqGatheringAndAnalysis());
		changeManagement.setTotalChangeManagementHours(changeManagementDTO.getTotalChangeManagementHours());
		
		changeManagement.setSolution(setSolutionById(solutionId));
		changeManagement.setOpportunityScope(getOpportunityScope(oppScopeID));
		return changeManagement;
		
	}
	
	private CapacityManagement convertFromCapacityManagementDTO2Entity(
			CapacityManagementDTO capacityManagementDTO, Integer solutionId, Integer oppScopeID)
			throws MSSPException {
		
			CapacityManagement capacityManagement = new CapacityManagement();
			capacityManagement.setCapacityManagementID(capacityManagementDTO.getCapacityManagementID());
			capacityManagement.setPrepareAndUseModels(capacityManagementDTO.getPrepareAndUseModels());
			capacityManagement.setFteCapacityManagement(capacityManagementDTO.getFteCapacityManagement());
			
			capacityManagement.setSolution(setSolutionById(solutionId));
			capacityManagement.setOpportunityScope(getOpportunityScope(oppScopeID));
			return capacityManagement;
		
	}
	
	private ConfigurationManagement convertFromConfigurationManagementDTO2Entity(
			ConfigurationManagementDTO configurationManagementDTO, Integer solutionId, Integer oppScopeID)
			throws MSSPException {
		
		ConfigurationManagement configurationManagement = new ConfigurationManagement();
		configurationManagement.setConfigurationManagementID(configurationManagementDTO.getConfigurationManagementID());
		configurationManagement.setManageConfigurationItems(configurationManagementDTO.getManageConfigurationItems());
		configurationManagement.setManageConfigInfoAndDocumentation(configurationManagementDTO.getManageConfigInfoAndDocumentation());
		configurationManagement.setFteConfigurationManagement(configurationManagementDTO.getFteConfigurationManagement());
		
		configurationManagement.setSolution(setSolutionById(solutionId));
		configurationManagement.setOpportunityScope(getOpportunityScope(oppScopeID));
		
		return configurationManagement;
		
	}
	
	private SecurityManagement convertFromSecurityManagementDTO2Entity(
			SecurityManagementDTO securityManagementDTO, Integer solutionId, Integer oppScopeID)
			throws MSSPException {
		
		SecurityManagement securityManagement = new SecurityManagement();
		securityManagement.setSecurityManagementID(securityManagementDTO.getSecurityManagementID());
		securityManagement.setManageSecurity(securityManagementDTO.getManageSecurity());
		securityManagement.setFteSecurityManagement(securityManagementDTO.getFteSecurityManagement());
		
		securityManagement.setSolution(setSolutionById(solutionId));
		securityManagement.setOpportunityScope(getOpportunityScope(oppScopeID));
			return securityManagement;
		
	}
	
	private AvailabilityManagement convertFromAvailabilityManagementDTO2Entity(
			AvailabilityManagementDTO availabilityManagementDTO, Integer solutionId, Integer oppScopeId)
			throws MSSPException {
		
		AvailabilityManagement availabilityManagement = new AvailabilityManagement();
		availabilityManagement.setAvailabilityManagementID(availabilityManagementDTO.getAvailabilityManagementID());
		availabilityManagement.setManageAvailability(availabilityManagementDTO.getManageAvailability());
		availabilityManagement.setManageOutageAndRisks(availabilityManagementDTO.getManageOutageAndRisks());
		availabilityManagement.setFteAvailabilityManagement(availabilityManagementDTO.getFteAvailabilityManagement());
		
		availabilityManagement.setSolution(setSolutionById(solutionId));
		availabilityManagement.setOpportunityScope(getOpportunityScope(oppScopeId));
		
		return availabilityManagement;
		
	}
	
	
	
	@Override
	public ProgramManagementDTO loadProgramManagement(Integer solId, Integer serviceScopeID) throws MSSPException {
			
	
		ProgramManagementDTO programManagementDTO = new ProgramManagementDTO();
		List<ProgramManagement> programMgmtList = volDao.loadProgramManagementListBySolutionId(solId);
		
		if(programMgmtList.size() >0){
			programManagementDTO = convertPMEnitityToDTO(programMgmtList.get(0));
		}else{
			List<OtherDefaults> list = volDao.loadDefaultValuesByServiceScopeID(serviceScopeID);
			programManagementDTO = convertOtherDefaultsEntityListToProgMgmtDTO(list);
		}
		return programManagementDTO;
	
	}


	
	
	@Override
	public void saveProgramManagement(	ProgramManagementDTO programManagementDTO,Integer solutionId, Integer oppScopeId)
			throws MSSPException {
			
		ProgramManagement programManagement = convertFromProgramManagementDTO2Entity(programManagementDTO, solutionId, oppScopeId);			
		volDao.saveProgramManagement(programManagement);
	
	}
	
		
	@Override
	public ReleaseManagementDTO loadReleaseManagement(Integer solId, Integer serviceScopeID) throws MSSPException {
		
		ReleaseManagementDTO releaseManagementDTO = new ReleaseManagementDTO();
		List<ReleaseManagement> releaseMgmtList = volDao.loadReleaseManagementListBySolutionId(solId);
		
		
		if(releaseMgmtList.size() >0){
			releaseManagementDTO = convertRMEnitityToDTO(releaseMgmtList.get(0));
		}else{
			List<OtherDefaults> list = volDao.loadDefaultValuesByServiceScopeID(serviceScopeID);
			releaseManagementDTO = convertOtherDefaultsEntityListToRelMgmtDTO(list);
		}
			
		
		return releaseManagementDTO;
	}

	
	@Override
	public void saveReleaseManagement(
			ReleaseManagementDTO releaseManagementDTO,
			Integer solutionId, Integer oppScopeId)
			throws MSSPException {
		
		ReleaseManagement releaseManagement = convertFromReleaseManagementDTO2Entity(releaseManagementDTO, solutionId, oppScopeId);
		volDao.saveReleaseManagement(releaseManagement);
	

	}
	
		
	@Override
	public ChangeManagementDTO loadChangeManagement(Integer solId, Integer serviceScopeID) throws MSSPException {
		
		ChangeManagementDTO changeManagementDTO = new ChangeManagementDTO();
		List<ChangeManagement> changeMgmtList = volDao.loadChangeManagementListBySolutionId(solId);
		
		
		if(changeMgmtList.size() >0){
			changeManagementDTO = convertCMEnitityToDTO(changeMgmtList.get(0));
		}else{
			List<OtherDefaults> list = volDao.loadDefaultValuesByServiceScopeID(serviceScopeID);
			changeManagementDTO = convertOtherDefaultsEntityListToChangeMgmtDTO(list);
		}
			
		
		return changeManagementDTO;
	}


	
	
	@Override
	public void saveChangeManagement(ChangeManagementDTO changeManagementDTO,
			Integer solutionId, Integer oppScopeId)
			throws MSSPException {
		
		ChangeManagement changeManagement = convertFromChangeManagementDTO2Entity1(changeManagementDTO, solutionId, oppScopeId);
		changeManagement.setOpportunityScope(getOpportunityScope(oppScopeId));
		volDao.saveChangeManagement(changeManagement);
	

	}
	
	@Override
	public CapacityManagementDTO loadCapacityManagement(Integer solId, Integer serviceScopeID) throws MSSPException {
		
		CapacityManagementDTO capacityManagementDTO = new CapacityManagementDTO();
		List<CapacityManagement> capacityMgmtList = volDao.loadCapacityManagementListBySolutionId(solId);
		
		if(capacityMgmtList.size() >0){
			capacityManagementDTO = convertCPMEnitityToDTO(capacityMgmtList.get(0));
		}else{
			List<OtherDefaults> list = volDao.loadDefaultValuesByServiceScopeID(serviceScopeID);
			capacityManagementDTO = convertOtherDefaultsEntityListToCapacityMgmtDTO(list);
		}
				
		return capacityManagementDTO;
	}
	
	@Override
	public void saveCapacityManagement(CapacityManagementDTO capacityManagementDTO,
			Integer solutionId, Integer oppScopeId)
			throws MSSPException {
		
		CapacityManagement capacityManagement = convertFromCapacityManagementDTO2Entity(
				capacityManagementDTO, solutionId, oppScopeId);
		volDao.saveCapacityManagement(capacityManagement);
	
	}
	
	@Override
	public ConfigurationManagementDTO loadConfigurationManagement(
			Integer solId, Integer serviceScopeID) throws MSSPException {
		
		ConfigurationManagementDTO configurationManagementDTO = new ConfigurationManagementDTO();
		List<ConfigurationManagement> configurationMgmtList = volDao.loadConfigurationManagementListBySolutionId(solId);
		
		if(configurationMgmtList.size() >0){
			configurationManagementDTO = convertCFGEnitityToDTO(configurationMgmtList.get(0));
		}else{
			List<OtherDefaults> list = volDao.loadDefaultValuesByServiceScopeID(serviceScopeID);
			configurationManagementDTO = convertOtherDefaultsEntityLisyToConfigMgmtDTO(list);
		}
				
		return configurationManagementDTO;
	}
	
	@Override
	public void saveConfigurationManagement(
			ConfigurationManagementDTO configurationManagementDTO,
			Integer solutionId, Integer oppScopeId)
			throws MSSPException {
		
		ConfigurationManagement configurationManagement = convertFromConfigurationManagementDTO2Entity(configurationManagementDTO, solutionId, oppScopeId);
		volDao.saveConfigurationManagement(configurationManagement);
	
	}
	
	@Override
	public SecurityManagementDTO loadSecurityManagement(
			Integer solId, Integer serviceScopeID) throws MSSPException {
		
		SecurityManagementDTO securityManagementDTO = new SecurityManagementDTO();
		List<SecurityManagement> securityMgmtList = volDao.loadSecurityManagementListBySolutionId(solId);
		
		if(securityMgmtList.size() >0){
			securityManagementDTO = convertSECEnitityToDTO(securityMgmtList.get(0));
		}else{
			List<OtherDefaults> list = volDao.loadDefaultValuesByServiceScopeID(serviceScopeID);
			securityManagementDTO = convertOtherDefaultsEntityListToSecurityMgmtDTO(list);
		}
				
		return securityManagementDTO;
	}
	
	@Override
	public void saveSecurityManagement(SecurityManagementDTO securityManagementDTO,
			Integer solutionId, Integer oppScopeId)
			throws MSSPException {
		
		SecurityManagement securityManagement = convertFromSecurityManagementDTO2Entity(securityManagementDTO, solutionId, oppScopeId);
		volDao.saveSecurityManagement(securityManagement);
	
	}
	
	@Override
	public AvailabilityManagementDTO loadAvailabilityManagement(
			Integer solId, Integer serviceScopeID) throws MSSPException {
		
		AvailabilityManagementDTO availabilityManagementDTO = new AvailabilityManagementDTO();
		List<AvailabilityManagement> availabilityMgmtList = volDao.loadAvailabilityManagementListBySolutionId(solId);
		
		if(availabilityMgmtList.size() >0){
			availabilityManagementDTO = convertAVLEnitityToDTO(availabilityMgmtList.get(0));
		}else{
			List<OtherDefaults> list = volDao.loadDefaultValuesByServiceScopeID(serviceScopeID);
			availabilityManagementDTO = convertOtherDefaultsEntityListToAvailMgmtDTO(list);
		}
				
		return availabilityManagementDTO;
	}
	
	@Override
	public void saveAvailabilityManagement(
			AvailabilityManagementDTO availabilityManagementDTO,
			Integer solutionId, Integer oppScopeId)
			throws MSSPException {
		
		AvailabilityManagement availabilityManagement = convertFromAvailabilityManagementDTO2Entity(
				availabilityManagementDTO, solutionId, oppScopeId);

		availabilityManagement.setOpportunityScope(getOpportunityScope(oppScopeId));
		volDao.saveAvailabilityManagement(availabilityManagement);
	
	}
	
	@Override
	public AccessManagementDTO loadAccessManagementBySolutionId(
			Integer solId) throws MSSPException {
		
		AccessManagementDTO accessManagementDTO = new AccessManagementDTO();
		List<AccessManagement> accessMgmtList = volDao
				.loadAccessManagementListBySolutionId(solId);
		
		if(accessMgmtList.size() >0){
			AccessManagement accessManagement = accessMgmtList.get(0);
			BeanUtils.copyProperties(accessManagement, accessManagementDTO);
		}
				
		return accessManagementDTO;
	}
	
	@Override
	public void saveAccessManagement(
			AccessManagementDTO accessManagementDTO,
			Integer solutionId, Integer solDimentionAttId, Integer oppScopeId)
			throws MSSPException {
		AccessManagement accessManagement = new AccessManagement();
		BeanUtils.copyProperties(accessManagementDTO, accessManagement);
		
		accessManagement.setSolution(setSolutionById(solutionId));
		accessManagement.setOpportunityScope(getOpportunityScope(oppScopeId));
		volDao.saveAccessManagement(accessManagement);
	
	}
	
	 @Override
	    public Float loadServiceBucketDataByOppSolutionID(
		    Integer opportunityID, Integer solutionID) throws MSSPException {
		 Float totalFteYear1 = 0f;
		
		try {
			List<String> serviceBucketData = volDao
			    .getServiceBucketDataByOppSolutionID(opportunityID,
				    solutionID);
		    //Parse the result here 
		    //List returned is having following elements :
		    //Service Element; Scope of Service ; Day1 FTE ; Day1 HC; Year 1 FTE; Year 1 HC
		    List<String> admSupportServiceElementsList = new ArrayList<String>();
		    admSupportServiceElementsList.add(MSSPConstants.CHANGE_MANAGEMENT_STR);
		    admSupportServiceElementsList.add(MSSPConstants.ACCESS_MANAGEMENT_STR);
		    admSupportServiceElementsList.add(MSSPConstants.AVAILABILITY_MANAGEMENT_STR);
		    admSupportServiceElementsList.add(MSSPConstants.CAPACITY_MANAGEMENT_STR);
		    admSupportServiceElementsList.add(MSSPConstants.CONFIGURATION_MANAGEMENT_STR);
		    admSupportServiceElementsList.add(MSSPConstants.RELEASE_MANAGEMENT_STR);
		    admSupportServiceElementsList.add(MSSPConstants.PROGRAM_MANAGEMENT_STR);
		    admSupportServiceElementsList.add(MSSPConstants.SECURITY_MANAGEMENT_STR);
		    												
		    for(String svcBucketStr : serviceBucketData){
		    	boolean continueFlag = false;
		    	String[] parts = svcBucketStr.split(";");
		    	for(String excludeAdmSupportStr : admSupportServiceElementsList){
		    		if(parts[1].equalsIgnoreCase(excludeAdmSupportStr))
		    		{
		    			continueFlag = true;
		    			continue;
		    		}
		    	}
		    	
		    	if(!parts[4].equals("NA") &&  !continueFlag)
		    		totalFteYear1 = totalFteYear1+ Float.parseFloat(parts[4]);
		    }
		   
		    
		} catch (Exception e) {
			throw new MSSPException("Error during serviceBucketData-" , e);
		}
		return totalFteYear1;
	    }

	@Override
	public List<ServiceScopeDTO> getAllServiceScopeByServiceElement(
			String serviceelement) {
		// TODO Auto-generated method stub
		return volDao.getAllServiceScopeByServiceElement(serviceelement);
	}

	
	// Demand Support

		private DemandSupportDTO convertDSEnitityToDTO(	DemandSupport demandSupport) {

			DemandSupportDTO demandSupportDTO = new DemandSupportDTO();
			demandSupportDTO.setDemandSupportID(demandSupport.getDemandSupportID());
			demandSupportDTO.setCrAnalysis(demandSupport.getCrAnalysis());
			demandSupportDTO.setRequirementGathering(demandSupport.getRequirementGathering());
			demandSupportDTO.setTechFeasibilityAnalysis(demandSupport.getTechFeasibilityAnalysis());
			demandSupportDTO.setEffCostEstimation(demandSupport.getEffCostEstimation());
			demandSupportDTO.setWorkPlanFormulation(demandSupport.getWorkPlanFormulation());
			demandSupportDTO.setTotalDemandSupportHours(demandSupport.getTotalDemandSupportHours());
			
			return demandSupportDTO;
		}
		
		private DemandSupport convertFromDemandSupportDTO2Entity(DemandSupportDTO demandSupportDTO, Integer solutionId)
				throws MSSPException {
			

				DemandSupport demandSupport = new DemandSupport();
				
				demandSupport.setDemandSupportID(demandSupportDTO.getDemandSupportID());
				demandSupport.setSolution(setSolutionById(solutionId));
				demandSupport.setCrAnalysis(demandSupportDTO.getCrAnalysis());
				demandSupport.setRequirementGathering(demandSupportDTO.getRequirementGathering());
				demandSupport.setTechFeasibilityAnalysis(demandSupportDTO.getTechFeasibilityAnalysis());
				demandSupport.setEffCostEstimation(demandSupportDTO.getEffCostEstimation());
				demandSupport.setWorkPlanFormulation(demandSupportDTO.getWorkPlanFormulation());
				demandSupport.setTotalDemandSupportHours(demandSupportDTO.getTotalDemandSupportHours());
				return demandSupport;
			
		}
		//
		@Override
		public void saveDemandSupport(DemandSupportDTO demandSupportDTO, Integer solutionId, Integer oppScopeId)
				throws MSSPException {
				
			DemandSupport demandSupport = convertFromDemandSupportDTO2Entity(demandSupportDTO,solutionId);
			demandSupport.setOpportunityScope(getOpportunityScope(oppScopeId));
			volDao.saveDemandSupport(demandSupport);		
			
		}

		@Override
		public DemandSupportDTO loadDemandSupport(Integer solId, Integer serviceScopeID) throws MSSPException {
			
			DemandSupportDTO demandSupportDTO = new DemandSupportDTO();
			List<DemandSupport> demandSupportList = volDao.loadDemandSupportListBySolutionId(solId);
			
			if(demandSupportList.size() >0){
				demandSupportDTO = convertDSEnitityToDTO(demandSupportList.get(0));
			}else{
				List<OtherDefaults> list = volDao.loadDefaultValuesByServiceScopeID(serviceScopeID);
				demandSupportDTO = convertOtherDefaultsEntityToDemandSupportDTO(list);
			}
			 
			return demandSupportDTO;
		}

		/*// Design //
		private Design convertFromDesignDTO2Entity(DesignDTO designDTO){
			
			Design design = new Design();
			
			design.setDesignID(designDTO.getDesignID());
			design.setUseCaseRealisation(designDTO.getUseCaseRealisation());
			design.setWorkflowOfUseCase(designDTO.getWorkflowOfUseCase());
			design.setManageInterfaces(designDTO.getManageInterfaces());
			design.setManageNFR(designDTO.getManageNFR());
			design.setDesignDocument(designDTO.getDesignDocument());
			design.setReview(designDTO.getReview());
			design.setBaseLine(designDTO.getBaseLine());
			design.setTotalDesignHours(designDTO.getTotalDesignHours());
			
			design.setSolution(setSolutionById(designDTO.getSolutionDTO().getSolutionId()));
			design.setOpportunityScope(getOpportunityScope(designDTO.getOpportunityScopeDTO().getOpportunityScopeId()));
			
			return design;
		}
		
		private DesignDTO convertDesignEntityToDTO(Design design){
			
			DesignDTO designDTO = new DesignDTO();
			
			designDTO.setDesignID(design.getDesignID());
			designDTO.setUseCaseRealisation(design.getUseCaseRealisation());
			designDTO.setWorkflowOfUseCase(design.getWorkflowOfUseCase());
			designDTO.setManageInterfaces(design.getManageInterfaces());
			designDTO.setManageNFR(design.getManageNFR());
			designDTO.setDesignDocument(design.getDesignDocument());
			designDTO.setReview(design.getReview());
			designDTO.setBaseLine(design.getBaseLine());
			designDTO.setTotalDesignHours(design.getTotalDesignHours());
			
			return designDTO;
			
		}
		@Override
		public void saveDesign(DesignDTO designDTO) throws MSSPException {
			
			Design design = convertFromDesignDTO2Entity(designDTO);
			volDao.saveDesign(design);
		}

		@Override
		public DesignDTO loadDesignBySolutionId(Integer solId) throws MSSPException {
			DesignDTO designDTO = new DesignDTO();
			List<Design> designList = volDao.loadDesignListBySolutionId(solId);
			
			if(designList.size() > 0){
				designDTO = convertDesignEntityToDTO(designList.get(0));
			}
			return designDTO;
		}

		// Build
		private Build  convertBuildDTOtoEntity(BuildDTO buildDTO, Integer solId){
			Build build  = new Build();
			build.setBuildId(buildDTO.getBuildId());
			build.setCreatePlan(buildDTO.getCreatePlan());
			build.setWriteScript(buildDTO.getWriteScript());
			build.setIdentifyDependency(buildDTO.getIdentifyDependency());
			build.setBuildBinary(buildDTO.getBuildBinary());
			build.setTotalBuildHours(buildDTO.getTotalBuildHours());
			
			build.setSolution(setSolutionById(solId));
			build.setOpportunityScope(getOpportunityScope(buildDTO.getOpportunityScopeDTO().getOpportunityScopeId()));
			
			return build;
		}
		
		private BuildDTO convertBuildEntityToDTO(Build build){
			BuildDTO buildDTO = new BuildDTO();
			buildDTO.setBuildId(build.getBuildId());
			buildDTO.setCreatePlan(build.getCreatePlan());
			buildDTO.setWriteScript(build.getWriteScript());
			buildDTO.setIdentifyDependency(build.getIdentifyDependency());
			buildDTO.setBuildBinary(build.getBuildBinary());
			buildDTO.setTotalBuildHours(build.getTotalBuildHours());
			return buildDTO;
			
		}
		@Override
		public void saveBuild(BuildDTO buildDTO, Integer solId) throws MSSPException {
			
			Build build = convertBuildDTOtoEntity(buildDTO,solId);
			volDao.saveBuild(build);
			
		}

		@Override
		public BuildDTO loadBuildDTOBySolutionId(Integer solId)
				throws MSSPException {
			BuildDTO buildDTO = new BuildDTO();
			List<Build> buildList = volDao.loadBuildListBySolutionId(solId);
			
			if(buildList.size() > 0){
				buildDTO = convertBuildEntityToDTO(buildList.get(0));
			}
			return buildDTO;
		}*/

		// Deployment Roll Out
		
		private DeploymentRollOut convertDeploymentRODTOToEntity(DeploymentRollOutDTO deploymentRollOutDTO, Integer solId,
				Integer oppScopeId){
			DeploymentRollOut deploymentRollOut = new DeploymentRollOut();
			
			deploymentRollOut.setDeploymentRollOutId(deploymentRollOutDTO.getDeploymentRollOutId());
			deploymentRollOut.setOpsBusinessTraining(deploymentRollOutDTO.getOpsBusinessTraining());
			deploymentRollOut.setDeploySoftwareInstln(deploymentRollOutDTO.getDeploySoftwareInstln());
			deploymentRollOut.setDeployInstlnPlanExec(deploymentRollOutDTO.getDeployInstlnPlanExec());
			deploymentRollOut.setDataMigration(deploymentRollOutDTO.getDataMigration());
			deploymentRollOut.setLegacySwitchOff(deploymentRollOutDTO.getLegacySwitchOff());
			deploymentRollOut.setTotalDeploymentRollOutHours(deploymentRollOutDTO.getTotalDeploymentRollOutHours());
			
			deploymentRollOut.setSolution (setSolutionById(solId));
			deploymentRollOut.setOpportunityScope(getOpportunityScope(oppScopeId));
			return deploymentRollOut;
		}
		
		private DeploymentRollOutDTO convertDeploymentRollOutEntityToDTO(DeploymentRollOut deploymentRollOut){
			
			DeploymentRollOutDTO deploymentRollOutDTO = new DeploymentRollOutDTO();
			
			deploymentRollOutDTO.setDeploymentRollOutId(deploymentRollOut.getDeploymentRollOutId());
			deploymentRollOutDTO.setOpsBusinessTraining(deploymentRollOut.getOpsBusinessTraining());
			deploymentRollOutDTO.setDeploySoftwareInstln(deploymentRollOut.getDeploySoftwareInstln());
			deploymentRollOutDTO.setDeployInstlnPlanExec(deploymentRollOut.getDeployInstlnPlanExec());
			deploymentRollOutDTO.setDataMigration(deploymentRollOut.getDataMigration());
			deploymentRollOutDTO.setLegacySwitchOff(deploymentRollOut.getLegacySwitchOff());
			deploymentRollOutDTO.setTotalDeploymentRollOutHours(deploymentRollOut.getTotalDeploymentRollOutHours());
			
			return deploymentRollOutDTO;
		}
		
		@Override
		public void saveDeploymentRollOut(
				DeploymentRollOutDTO deploymentRollOutDTO, Integer solId,
				Integer oppScopeId) throws MSSPException {
			
			DeploymentRollOut deploymentRollOut = convertDeploymentRODTOToEntity(deploymentRollOutDTO,solId,oppScopeId);
			volDao.saveDeploymentRollOut(deploymentRollOut);
			
		}

		@Override
		public DeploymentRollOutDTO loadDeploymentRollOutDTO(
				Integer solId, Integer serviceScopeID) throws MSSPException {
			DeploymentRollOutDTO deploymentRollOutDTO = new DeploymentRollOutDTO();
			List<DeploymentRollOut> list = volDao.loadDeploymentRollOutListBySolutionId(solId);
			
			if(list.size() > 0){
				deploymentRollOutDTO = convertDeploymentRollOutEntityToDTO(list.get(0));
			}else{
				List<OtherDefaults> defList = volDao.loadDefaultValuesByServiceScopeID(serviceScopeID);
				deploymentRollOutDTO = convertOtherDefaultsEntityListToDeploymentRODTO(defList);
			}
			return deploymentRollOutDTO;
		}

		// Post Release Activities
		
		private PostReleaseActivities convertPostRelActDTOToEntity(PostReleaseActivitiesDTO postRelActDTO,
				Integer solId, Integer oppScopeId) {
			
			PostReleaseActivities postRelAct = new PostReleaseActivities();
			postRelAct.setPostRelActivityID(postRelActDTO.getPostRelActivityID());
			postRelAct.setPostInstRelIssues(postRelActDTO.getPostInstRelIssues());
			postRelAct.setSuppBusinessSimulations(postRelActDTO.getSuppBusinessSimulations());
			postRelAct.setManageStaggeredAct(postRelActDTO.getManageStaggeredAct());
			postRelAct.setTunePerformance(postRelActDTO.getTunePerformance());
			postRelAct.setPostInstallationComm(postRelActDTO.getPostInstallationComm());
			postRelAct.setTotalPostRelActHours(postRelActDTO.getTotalPostRelActHours());
			
			postRelAct.setSolution(setSolutionById(solId));
			postRelAct.setOpportunityScope(getOpportunityScope(oppScopeId));
			
			return postRelAct;
			
		}
		
		private PostReleaseActivitiesDTO convertPostReleaseActEntityToDTO(PostReleaseActivities postRelAct){
			
			PostReleaseActivitiesDTO postRelActDTO =  new PostReleaseActivitiesDTO();
			postRelActDTO.setPostRelActivityID(postRelAct.getPostRelActivityID());
			postRelActDTO.setPostInstRelIssues(postRelAct.getPostInstRelIssues());
			postRelActDTO.setSuppBusinessSimulations(postRelAct.getSuppBusinessSimulations());
			postRelActDTO.setManageStaggeredAct(postRelAct.getManageStaggeredAct());
			postRelActDTO.setTunePerformance(postRelAct.getTunePerformance());
			postRelActDTO.setPostInstallationComm(postRelAct.getPostInstallationComm());
			postRelActDTO.setTotalPostRelActHours(postRelAct.getTotalPostRelActHours());
			
			return postRelActDTO;
		}
		@Override
		public void savePostReleaseActivity(PostReleaseActivitiesDTO postRelActDTO,
				Integer solId, Integer oppScopeId) throws MSSPException {
			
			PostReleaseActivities postRelAct = convertPostRelActDTOToEntity(postRelActDTO, solId, oppScopeId);
			volDao.savePostReleaseActivities(postRelAct);
		}

		@Override
		public PostReleaseActivitiesDTO loadPostRelActDTO(Integer solId, Integer serviceScopeID)
				throws MSSPException {
			
			PostReleaseActivitiesDTO postRelActDTO = new PostReleaseActivitiesDTO();
			List<PostReleaseActivities> list = volDao.loadPostReleaseActivitiesListBySolutionId(solId);
			
			if(list.size() > 0){
				postRelActDTO = convertPostReleaseActEntityToDTO(list.get(0));
			}else{
				List<OtherDefaults> defList = volDao.loadDefaultValuesByServiceScopeID(serviceScopeID);
				postRelActDTO = convertOtherDefaultsEntityListToPostRelActDTO(defList);
			}
			return postRelActDTO;
		}

		// Design And build
		
		private DesignAndBuildDTO convertDesignBuildEntityToDTO(DesignAndBuild designBuild){
			
			DesignAndBuildDTO designBuildDTO = new DesignAndBuildDTO();
			
			designBuildDTO.setDesignAndBuildId(designBuild.getDesignAndBuildId());
			designBuildDTO.setDetailTechDesign(designBuild.getDetailTechDesign());
			designBuildDTO.setTestCaseDesignTestCyclePlan(designBuild.getTestCaseDesignTestCyclePlan());
			designBuildDTO.setFunctionalSpecDevFinal(designBuild.getFunctionalSpecDevFinal());
			designBuildDTO.setCapacityPlanning(designBuild.getCapacityPlanning());
			designBuildDTO.setSoftwareChanges(designBuild.getSoftwareChanges());
			designBuildDTO.setConfigChanges(designBuild.getConfigChanges());
			designBuildDTO.setUnitComponentTest(designBuild.getUnitComponentTest());
			designBuildDTO.setRelNotesPrepareDistribute(designBuild.getRelNotesPrepareDistribute());
			designBuildDTO.setMaintananceManualCreation(designBuild.getMaintananceManualCreation());
			designBuildDTO.setTotalDesignBuildHours(designBuild.getTotalDesignBuildHours());
			
			return designBuildDTO;
		}
		
		private DesignAndBuild convertDesignBuildDTOToEntity(Integer solId,Integer oppScopeId,DesignAndBuildDTO designAndBuildDTO) {
			
			DesignAndBuild designBuild = new DesignAndBuild();
			
			designBuild.setSolution(setSolutionById(solId));
			designBuild.setOpportunityScope(getOpportunityScope(oppScopeId));
			
			designBuild.setDesignAndBuildId(designAndBuildDTO.getDesignAndBuildId());
			designBuild.setDetailTechDesign(designAndBuildDTO.getDetailTechDesign());
			designBuild.setTestCaseDesignTestCyclePlan(designAndBuildDTO.getTestCaseDesignTestCyclePlan());
			designBuild.setFunctionalSpecDevFinal(designAndBuildDTO.getFunctionalSpecDevFinal());
			designBuild.setCapacityPlanning(designAndBuildDTO.getCapacityPlanning());
			designBuild.setSoftwareChanges(designAndBuildDTO.getSoftwareChanges());
			designBuild.setConfigChanges(designAndBuildDTO.getConfigChanges());
			designBuild.setUnitComponentTest(designAndBuildDTO.getUnitComponentTest());
			designBuild.setRelNotesPrepareDistribute(designAndBuildDTO.getRelNotesPrepareDistribute());
			designBuild.setMaintananceManualCreation(designAndBuildDTO.getMaintananceManualCreation());
			designBuild.setTotalDesignBuildHours(designAndBuildDTO.getTotalDesignBuildHours());
			
			return designBuild;
		}
		@Override
		public void saveDesignBuild(Integer solId, Integer oppScopeId,
				DesignAndBuildDTO designAndBuildDTO) throws MSSPException {
			
			DesignAndBuild designBuild = convertDesignBuildDTOToEntity(solId,oppScopeId,designAndBuildDTO);
			volDao.saveDesignBuild(designBuild);
		}

		@Override
		public DesignAndBuildDTO loadDesignAndBuild(Integer solId,Integer serviceScopeID)
				throws MSSPException {
			DesignAndBuildDTO designBuildDTO = new DesignAndBuildDTO();
			List<DesignAndBuild> designBuildList = volDao.loadDesignBuildListBySolutionId(solId);
			if(designBuildList.size() > 0){
				designBuildDTO = convertDesignBuildEntityToDTO(designBuildList.get(0));
			}else{
				List<OtherDefaults> list = volDao.loadDefaultValuesByServiceScopeID(serviceScopeID);
				designBuildDTO = convertOtherDefaultsEntityToDesignBuildDTO(list);
			}
			return designBuildDTO;
		}
		
		@Override
		public void saveServiceAssurance(ServiceAssuranceDTO serviceAssuranceDTO,
				Integer solId, Integer oppScopeId) throws MSSPException {
			ServiceAssurance serviceAssurance = convertServiceAssuranceDTOToEntity(solId,oppScopeId,serviceAssuranceDTO);
			volDao.saveServiceAssurance(serviceAssurance);
		}
		
		@Override
		public ServiceAssuranceDTO loadServiceAssurance(Integer solId,
				Integer serviceScopeID) throws MSSPException {
			ServiceAssuranceDTO serviceAssuranceDTO = new ServiceAssuranceDTO();
			List<ServiceAssurance> serviceAssuranceList = volDao.loadServiceAssuranceListBySolutionId(solId);
			if(serviceAssuranceList.size() > 0){
				serviceAssuranceDTO = convertServiceAssuranceEntityToDTO(serviceAssuranceList.get(0));
			}else{
				List<OtherDefaults> list = volDao.loadDefaultValuesByServiceScopeID(serviceScopeID);
				serviceAssuranceDTO = convertOtherDefaultsEntityToServiceAssuranceDTO(list);
			}
			return serviceAssuranceDTO;
		}
		
		private ServiceAssurance convertServiceAssuranceDTOToEntity(Integer solId,Integer oppScopeId,ServiceAssuranceDTO serviceAssuranceDTO){
			ServiceAssurance serviceAssurance = new ServiceAssurance();
			
			serviceAssurance.setSolution(setSolutionById(solId));
			serviceAssurance.setOpportunityScope(getOpportunityScope(oppScopeId));
			
			serviceAssurance.setServiceAssuranceID(serviceAssuranceDTO.getServiceAssuranceID());
			serviceAssurance.setHelpDeskSupport(serviceAssuranceDTO.getHelpDeskSupport());
			serviceAssurance.setIncidentManagement(serviceAssuranceDTO.getIncidentManagement());
			serviceAssurance.setProblemManagement(serviceAssuranceDTO.getProblemManagement());
			serviceAssurance.setImpactAnalysis(serviceAssuranceDTO.getImpactAnalysis());
			serviceAssurance.setDataAlignment(serviceAssuranceDTO.getDataAlignment());
			serviceAssurance.setIncidentReporting(serviceAssuranceDTO.getIncidentReporting());
			serviceAssurance.setBugFixing(serviceAssuranceDTO.getBugFixing());
			serviceAssurance.setTotalServiceAssuranceHours(serviceAssuranceDTO.getTotalServiceAssuranceHours());
			
			return serviceAssurance;
		}
		private ServiceAssuranceDTO convertServiceAssuranceEntityToDTO(ServiceAssurance serviceAssurance){
			ServiceAssuranceDTO serviceAssuranceDTO = new ServiceAssuranceDTO();
			
			serviceAssuranceDTO.setServiceAssuranceID(serviceAssurance.getServiceAssuranceID());
			serviceAssuranceDTO.setHelpDeskSupport(serviceAssurance.getHelpDeskSupport());
			serviceAssuranceDTO.setIncidentManagement(serviceAssurance.getIncidentManagement());
			serviceAssuranceDTO.setProblemManagement(serviceAssurance.getProblemManagement());
			serviceAssuranceDTO.setImpactAnalysis(serviceAssurance.getImpactAnalysis());
			serviceAssuranceDTO.setDataAlignment(serviceAssurance.getDataAlignment());
			serviceAssuranceDTO.setIncidentReporting(serviceAssurance.getIncidentReporting());
			serviceAssuranceDTO.setBugFixing(serviceAssurance.getBugFixing());
			serviceAssuranceDTO.setTotalServiceAssuranceHours(serviceAssurance.getTotalServiceAssuranceHours());
			
			return serviceAssuranceDTO;
		}
		
		private ServiceAssuranceDTO convertOtherDefaultsEntityToServiceAssuranceDTO(List<OtherDefaults> list) throws MSSPException{
			ServiceAssuranceDTO serviceAssuranceDTO = new ServiceAssuranceDTO();
			for(OtherDefaults def : list){
				String defaultValue = def.getDefaultValues();
				switch(def.getOtherFieldsName()){
				case ServiceAssurance_HelpDeskSupport : serviceAssuranceDTO.setHelpDeskSupport(Float.parseFloat(defaultValue));break;
				case ServiceAssurance_IncidentManagement : serviceAssuranceDTO.setIncidentManagement(Float.parseFloat(defaultValue));break;
				case ServiceAssurance_ProblemManagement : serviceAssuranceDTO.setProblemManagement(Float.parseFloat(defaultValue));break;
				case ServiceAssurance_ImpactAnalysis : serviceAssuranceDTO.setImpactAnalysis(Float.parseFloat(defaultValue));break;
				case ServiceAssurance_DataAlignment : serviceAssuranceDTO.setDataAlignment(Float.parseFloat(defaultValue));break;
				case ServiceAssurance_IncidentReporting : serviceAssuranceDTO.setIncidentReporting(Float.parseFloat(defaultValue));break;
				case ServiceAssurance_BugFixing : serviceAssuranceDTO.setBugFixing(Float.parseFloat(defaultValue));break;
				default :
					throw new MSSPException(def.getOtherFieldsName() + " does not match the Column Names for ServiceAssurance in MSSPConstants File.");
				}
			}
			
			return serviceAssuranceDTO;
		}
		
		private DemandSupportDTO convertOtherDefaultsEntityToDemandSupportDTO(List<OtherDefaults> list) throws MSSPException{
			DemandSupportDTO demandSupportDTO = new DemandSupportDTO();
			for(OtherDefaults def : list){
				switch(def.getOtherFieldsName()){
				case DemandSupport_CRAnalysis : 
					demandSupportDTO.setCrAnalysis(Float.parseFloat(def.getDefaultValues()));
					break;
									
				case DemandSupport_RequirementGathering : 
					demandSupportDTO.setRequirementGathering(Float.parseFloat(def.getDefaultValues()));
					break;
									
				case DemandSupport_TechFeasibilityAnalysis : 
					demandSupportDTO.setTechFeasibilityAnalysis(Float.parseFloat(def.getDefaultValues()));
					break;
									
				case DemandSupport_EffortCostEstimation : 
					demandSupportDTO.setEffCostEstimation(Float.parseFloat(def.getDefaultValues()));
					break;
									
				case DemandSupport_WorkPlanFormulation : 
					demandSupportDTO.setWorkPlanFormulation(Float.parseFloat(def.getDefaultValues()));
					break;
				
				case DemandSupport_TotalDemandSupportHours : 
					demandSupportDTO.setTotalDemandSupportHours(Float.parseFloat(def.getDefaultValues()));
					break;
					
				default :
					throw new MSSPException(def.getOtherFieldsName() + " does not match the Column Names for DemandSupport in MSSPConstants File.");
				}			
				
			}			
			return demandSupportDTO;
		}
		
	private DesignAndBuildDTO convertOtherDefaultsEntityToDesignBuildDTO(List<OtherDefaults> list) throws MSSPException{
		DesignAndBuildDTO designBuildDTO = new DesignAndBuildDTO();
		for(OtherDefaults def : list){
			float defaultValue = Float.parseFloat(def.getDefaultValues());
			switch(def.getOtherFieldsName()){
			case DesignBuild_DetailedTechDesign : 
				designBuildDTO.setDetailTechDesign(defaultValue);
				break;
								
			case DesignBuild_TestCaseDesignTestCyclePlan : 
				designBuildDTO.setTestCaseDesignTestCyclePlan(defaultValue);
				break;
								
			case DesignBuild_FunctionalSpecDevFinal : 
				designBuildDTO.setFunctionalSpecDevFinal(defaultValue);
				break;
								
			case DesignBuild_CapacityPlanning : 
				designBuildDTO.setCapacityPlanning(defaultValue);
				break;
								
			case DesignBuild_SoftwareChanges : 
				designBuildDTO.setSoftwareChanges(defaultValue);
				break;
			
			case DesignBuild_ConfigChanges : 
				designBuildDTO.setConfigChanges(defaultValue);
				break;
				
			case DesignBuild_UnitComponentTest : 
				designBuildDTO.setUnitComponentTest(defaultValue);
				break;
				
			case DesignBuild_RelNotesPrepareDistribute :
				designBuildDTO.setRelNotesPrepareDistribute(defaultValue);
				break;
				
			case DesignBuild_MaintananceManualCreation :
				designBuildDTO.setMaintananceManualCreation(defaultValue);
				break;
				
			default :
				throw new MSSPException(def.getOtherFieldsName() + " does not match the Column Names for DesignBuild in MSSPConstants File.");
			}			
			
		}			
		return designBuildDTO;
	
		
	}
	
	private DeploymentRollOutDTO convertOtherDefaultsEntityListToDeploymentRODTO(List<OtherDefaults> list) throws MSSPException{
		DeploymentRollOutDTO deploymentRODTO = new DeploymentRollOutDTO();
		for(OtherDefaults def : list){
			float defaultValue = Float.parseFloat(def.getDefaultValues());
			switch(def.getOtherFieldsName()){
			case DeploymentRollOut_OpsBusinessTraining : 
				deploymentRODTO.setOpsBusinessTraining(defaultValue);
				break;
								
			case DeploymentRollOut_DeploySoftwareInstln : 
				deploymentRODTO.setDeploySoftwareInstln(defaultValue);
				break;
								
			case DeploymentRollOut_DeployInstlnPlanExec : 
				deploymentRODTO.setDeployInstlnPlanExec(defaultValue);
				break;
								
			case DeploymentRollOut_DataMigration : 
				deploymentRODTO.setDataMigration(defaultValue);
				break;
								
			case DeploymentRollOut_LegacySwitchOff : 
				deploymentRODTO.setLegacySwitchOff(defaultValue);
				break;
				
			default :
				throw new MSSPException(def.getOtherFieldsName() + " does not match the Column Names for DeploymentRollout in MSSPConstants File.");
			
			}			
			
		}			
		return deploymentRODTO;		
	}
	
	private PostReleaseActivitiesDTO convertOtherDefaultsEntityListToPostRelActDTO(List<OtherDefaults> list) throws MSSPException{
		PostReleaseActivitiesDTO postRelActDTO = new PostReleaseActivitiesDTO();
		for(OtherDefaults def : list){
			float defaultValue = Float.parseFloat(def.getDefaultValues());
			switch(def.getOtherFieldsName()){
			case PostReleaseActivities_PostInstRelIssues : 
				postRelActDTO.setPostInstRelIssues(defaultValue);
				break;
								
			case PostReleaseActivities_SuppBusinessSimulations : 
				postRelActDTO.setSuppBusinessSimulations(defaultValue);
				break;
								
			case PostReleaseActivities_ManageStaggeredAct : 
				postRelActDTO.setManageStaggeredAct(defaultValue);
				break;
								
			case PostReleaseActivities_TunePerformance : 
				postRelActDTO.setTunePerformance(defaultValue);
				break;
								
			case PostReleaseActivities_PostInstallationComm : 
				postRelActDTO.setPostInstallationComm(defaultValue);
				break;
			
			default :
				throw new MSSPException(def.getOtherFieldsName() + " does not match the Column Names for PostReleaseActivities in MSSPConstants File.");
			}			
			
		}			
		return postRelActDTO;				
	}
	
	private ProgramManagementDTO convertOtherDefaultsEntityListToProgMgmtDTO(List<OtherDefaults> list) throws MSSPException{
		ProgramManagementDTO progMgmtDTO = new ProgramManagementDTO();
		for(OtherDefaults def : list){
			float defaultValue = Float.parseFloat(def.getDefaultValues());
			switch(def.getOtherFieldsName()){
			
			case ProgramManagement_CoordinateProjects : 
				progMgmtDTO.setCoordinateProjects(defaultValue);
				break;
				
			case ProgramManagement_EnsureAchievement :
				progMgmtDTO.setEnsureAchievement(defaultValue);
				break;
				
			case ProgramManagement_ManageClient : 
				progMgmtDTO.setManageClient(defaultValue);
				break;
				
			case ProgramManagement_ManageQualityRiskIssues : 
				progMgmtDTO.setManageQualityRiskIssues(defaultValue);
				break;
				
			case ProgramManagement_MeasureADMServiceKPI : 
				progMgmtDTO.setMeasureADMServiceKPI(defaultValue);
				break;
				
			default :
				throw new MSSPException(def.getOtherFieldsName() + " does not match the Column Names for ProgramManagement in MSSPConstants File.");
			}
		}
		
		return progMgmtDTO;
	}
	
	
	private ReleaseManagementDTO convertOtherDefaultsEntityListToRelMgmtDTO(List<OtherDefaults> list) throws MSSPException{
		ReleaseManagementDTO relMgmtDTO = new ReleaseManagementDTO();
		for(OtherDefaults def : list){
			float defaultValue = Float.parseFloat(def.getDefaultValues());
			
			switch(def.getOtherFieldsName()){
			
			case ReleaseManagement_InstlnDistnOfSoftRel : 
				relMgmtDTO.setInstlnDistnOfSoftRel(defaultValue);
				break;
				
			case ReleaseManagement_DefineRolloutPlan : 
				relMgmtDTO.setDefineRolloutPlan(defaultValue);
				break;
				
			case ReleaseManagement_EnsureTraceability : 
				relMgmtDTO.setEnsureTraceability(defaultValue);
				break;
				
			case ReleaseManagement_EnsureInstallation : 
				relMgmtDTO.setEnsureInstallation(defaultValue);
				break;
				
			default :
				throw new MSSPException(def.getOtherFieldsName() + " does not match the Column Names for ReleaseManagement in MSSPConstants File.");
			}
		}
		
		return relMgmtDTO;
	}
	
	private ChangeManagementDTO convertOtherDefaultsEntityListToChangeMgmtDTO(List<OtherDefaults> list) throws MSSPException{
		ChangeManagementDTO changeManagementDTO = new ChangeManagementDTO();
		
		for(OtherDefaults def : list){
			float defaultValue = Float.parseFloat(def.getDefaultValues());
			
			switch(def.getOtherFieldsName()){
			
			case ChangeManagement_ReqGatheringAndAnalysis : 
				changeManagementDTO.setReqGatheringAndAnalysis(defaultValue);
				break;
				
			case ChangeManagement_FeasibilityImpactAndCostAnalysis : 
				changeManagementDTO.setFeasibilityImpactAndCostAnalysis(defaultValue);
				break;
				
			case ChangeManagement_ApproveChangesAndPlanning : 
				changeManagementDTO.setApproveChangesAndPlanning(defaultValue);
				break;
				
			case ChangeManagement_EnsureStandards : 
				changeManagementDTO.setEnsureStandards(defaultValue);
				break;
				
			case ChangeManagement_ManageTraceability : 
				changeManagementDTO.setManageTraceability(defaultValue);
				break;
			default :
				throw new MSSPException(def.getOtherFieldsName() + " does not match the Column Names for ChangeManagement in MSSPConstants File.");
			}
		}
		
		return changeManagementDTO;
	}
	
	private CapacityManagementDTO convertOtherDefaultsEntityListToCapacityMgmtDTO(List<OtherDefaults> list) throws MSSPException{
		
		CapacityManagementDTO capacityMgmtDTO = new CapacityManagementDTO();
		for(OtherDefaults def : list){
			float defaultValue = Float.parseFloat(def.getDefaultValues());
			switch(def.getOtherFieldsName()){
			
			case CapacityManagement_PrepareAndUseModels : 
				capacityMgmtDTO.setPrepareAndUseModels(defaultValue);
				break;
				
			default :
				throw new MSSPException(def.getOtherFieldsName() + " does not match the Column Names for CapacityManagement in MSSPConstants File.");
			}
		}
		return capacityMgmtDTO;
	}
	
	private ConfigurationManagementDTO convertOtherDefaultsEntityLisyToConfigMgmtDTO(List<OtherDefaults> list) throws MSSPException{
		
		ConfigurationManagementDTO configMgmtDTO = new ConfigurationManagementDTO();
		for(OtherDefaults def : list){
			float defaultValue = Float.parseFloat(def.getDefaultValues());
			switch (def.getOtherFieldsName()){
			case ConfigurationManagement_ManageConfigurationItems : 
				configMgmtDTO.setManageConfigurationItems(defaultValue);
				break;
			
			case ConfigurationManagement_ManageConfigInfoAndDocumentation : 
				configMgmtDTO.setManageConfigInfoAndDocumentation(defaultValue);
				break;
				
			default :
				throw new MSSPException(def.getOtherFieldsName() + " does not match the Column Names for CapacityManagement in MSSPConstants File.");
			}
		}
		return configMgmtDTO;
	}
	
	private SecurityManagementDTO convertOtherDefaultsEntityListToSecurityMgmtDTO(List<OtherDefaults> list) throws MSSPException{
		SecurityManagementDTO securityMgmtDTO = new SecurityManagementDTO();
		for(OtherDefaults def : list){
			float defaultValue = Float.parseFloat(def.getDefaultValues());
			switch(def.getOtherFieldsName()){
			case SecurityManagement_ManageSecurity : 
				securityMgmtDTO.setManageSecurity(defaultValue);
				break;
				
			default :
				throw new MSSPException(def.getOtherFieldsName() + " does not match the Column Names for SecurityManagement in MSSPConstants File.");
			}
		}
		return securityMgmtDTO;
	}
	
	private AvailabilityManagementDTO convertOtherDefaultsEntityListToAvailMgmtDTO(List<OtherDefaults> list) throws MSSPException{
		AvailabilityManagementDTO availMgmtDTO = new AvailabilityManagementDTO();
		for(OtherDefaults def : list){
			float defaultValue = Float.parseFloat(def.getDefaultValues());
			switch(def.getOtherFieldsName()){
			case AvailabilityManagement_ManageAvailability :
				availMgmtDTO.setManageAvailability(defaultValue);
				break;
				
			case AvailabilityManagement_ManageOutageAndRisks :
				availMgmtDTO.setManageOutageAndRisks(defaultValue);
				break;
				
			default :
				throw new MSSPException(def.getOtherFieldsName() + " does not match the Column Names for AvailabilityManagement in MSSPConstants File.");
			}
		}
		return availMgmtDTO;
	}
	
	@Override
	public OpportunityDetail getOpportunityDetail(Integer opportunityId)
			throws MSSPException {
		return volDao.getOpportunityDetail(opportunityId);
	}

	@Override
	public AppMainSupportActivityDTO getSuppActivityDTOByOppCompID(	Integer oppCompId) throws MSSPException {
		AppMainSupportActivityDTO dto = new AppMainSupportActivityDTO();
		AppMainSupportActivity entity = volDao.getSuppActivityByOppCompID(oppCompId);
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	@Override
	public void saveAppMainSupportActivity(AppMainSupportActivityDTO dto)
			throws MSSPException {
		AppMainSupportActivity entity = new AppMainSupportActivity();
		BeanUtils.copyProperties(dto, entity);
		volDao.saveAppMainSupportActivity(entity);
		
	}

	@Override
	public void saveAppMainSLA(AppMainSlaDTO appMainSlaDTO)
			throws MSSPException {
		AppMainSla appMainSla = new AppMainSla();
		BeanUtils.copyProperties(appMainSlaDTO, appMainSla);
		volDao.saveAppMainSLA(appMainSla);
	}

	@Override
	public List<AppMainSlaDTO> getAppMainSlaDtoByOppId(Integer oppId)
			throws MSSPException {
		List<AppMainSlaDTO> dtoList = new ArrayList<AppMainSlaDTO>();
		List<AppMainSla> entityList = volDao.getAppMainSlaDtoByOppId(oppId);
		if(null != entityList && entityList.size() > 0){
			logger.info("Fetching data from AppMainSLA Table");
			for(AppMainSla entity : entityList){
				AppMainSlaDTO dto = new AppMainSlaDTO();
				BeanUtils.copyProperties(entity, dto);
				dtoList.add(dto);
			}			
		}else{
			logger.info("DATA is not present in AppMainSLA Table. Taking Default data");
			List<OtherDefaults> defList = volDao.getDefaultAppMainSlaDto();
			AppMainSlaDTO dto1 = new AppMainSlaDTO();
			AppMainSlaDTO dto2 = new AppMainSlaDTO();
			AppMainSlaDTO dto3 = new AppMainSlaDTO();
			AppMainSlaDTO dto4 = new AppMainSlaDTO();
			
			for(OtherDefaults def : defList){
				if(def.getApplicationArea().equalsIgnoreCase("P1")){
					//dto1.setSeverity(def.getApplicationArea());
					dto1.setSeverity(Priority1);
					if(def.getOtherFieldsName().equalsIgnoreCase("L1")){
						dto1.setLOneResponse(Float.parseFloat(def.getDefaultValues()));
					}else if(def.getOtherFieldsName().equalsIgnoreCase("L2")){
						dto1.setLTwoResponse(Float.parseFloat(def.getDefaultValues()));
					}else if(def.getOtherFieldsName().equalsIgnoreCase("L3")){
						dto1.setLThreeResponse(Float.parseFloat(def.getDefaultValues()));
					}else if(def.getOtherFieldsName().equalsIgnoreCase("ticketDistribution")){
						dto1.setTicketDistribution(Float.parseFloat(def.getDefaultValues()));
					}
					
				}else if(def.getApplicationArea().equalsIgnoreCase("P2")){
					//dto2.setSeverity(def.getApplicationArea());
					dto2.setSeverity(Priority2);
					if(def.getOtherFieldsName().equalsIgnoreCase("L1")){
						dto2.setLOneResponse(Float.parseFloat(def.getDefaultValues()));
					}else if(def.getOtherFieldsName().equalsIgnoreCase("L2")){
						dto2.setLTwoResponse(Float.parseFloat(def.getDefaultValues()));
					}else if(def.getOtherFieldsName().equalsIgnoreCase("L3")){
						dto2.setLThreeResponse(Float.parseFloat(def.getDefaultValues()));
					}else if(def.getOtherFieldsName().equalsIgnoreCase("ticketDistribution")){
						dto2.setTicketDistribution(Float.parseFloat(def.getDefaultValues()));
					}					
				}else if(def.getApplicationArea().equalsIgnoreCase("P3")){
					//dto3.setSeverity(def.getApplicationArea());
					dto3.setSeverity(Priority3);
					if(def.getOtherFieldsName().equalsIgnoreCase("L1")){
						dto3.setLOneResponse(Float.parseFloat(def.getDefaultValues()));
					}else if(def.getOtherFieldsName().equalsIgnoreCase("L2")){
						dto3.setLTwoResponse(Float.parseFloat(def.getDefaultValues()));
					}else if(def.getOtherFieldsName().equalsIgnoreCase("L3")){
						dto3.setLThreeResponse(Float.parseFloat(def.getDefaultValues()));
					}else if(def.getOtherFieldsName().equalsIgnoreCase("ticketDistribution")){
						dto3.setTicketDistribution(Float.parseFloat(def.getDefaultValues()));
					}
					
				}else if(def.getApplicationArea().equalsIgnoreCase("P4")){
					//dto4.setSeverity(def.getApplicationArea());
					dto4.setSeverity(Priority4);
					if(def.getOtherFieldsName().equalsIgnoreCase("L1")){
						dto4.setLOneResponse(Float.parseFloat(def.getDefaultValues()));
					}else if(def.getOtherFieldsName().equalsIgnoreCase("L2")){
						dto4.setLTwoResponse(Float.parseFloat(def.getDefaultValues()));
					}else if(def.getOtherFieldsName().equalsIgnoreCase("L3")){
						dto4.setLThreeResponse(Float.parseFloat(def.getDefaultValues()));
					}else if(def.getOtherFieldsName().equalsIgnoreCase("ticketDistribution")){
						dto4.setTicketDistribution(Float.parseFloat(def.getDefaultValues()));
					}
					
				}
				
			}
			
			dtoList.add(dto1);dtoList.add(dto2);dtoList.add(dto3);dtoList.add(dto4);
		}
		
		return dtoList;
	}

	@Override
	public List<TicketDistributionDTO> getTktDistByOppScopeIds(
			List<OpportunityScopeDTO> opportunityScopeList , String serviceElement) throws MSSPException {
		List<TicketDistributionDTO> dtoList = null;
		
		List<Integer> oppScopeIdList = new ArrayList<Integer>();
		for(OpportunityScopeDTO dto : opportunityScopeList){
			oppScopeIdList.add(dto.getOpportunityScopeId());
		}
		List<TicketDistribution> listEntity = volDao.getTktDistByOppScopeIds(oppScopeIdList);
		if(null != listEntity && listEntity.size() > 0){
			List<Integer> addedServElementIds = compareOppScopeIds(opportunityScopeList,listEntity);
			if(addedServElementIds != null && addedServElementIds.size() > 0){
				logger.info("Fetching data from TicketDistribution Table and OtherDefaults. New OpportunityScope ids are "+addedServElementIds);
				dtoList = new ArrayList<TicketDistributionDTO>();
				
				// Adding opp scopes already present in Tkt Dist Table.
				for(TicketDistribution entity : listEntity){
					TicketDistributionDTO dto = new TicketDistributionDTO();
					BeanUtils.copyProperties(entity, dto);
					dtoList.add(dto);
				}	
				
				// Adding New opp scopes from OtherDefaults table.
				List<OtherDefaults> defList1 = volDao.getDefTktDistByServEleServScopes(serviceElement,addedServElementIds);
				for(OpportunityScopeDTO oppScopedto : opportunityScopeList){
					for(OtherDefaults def : defList1){
						if(def.getServiceScopeID().equals(oppScopedto.getServiceScopeDTO().getServiceScopeId())){
							TicketDistributionDTO dto = new TicketDistributionDTO();
							dto.setOpportunityScope(convertOppScopeDtoToEntity(oppScopedto));
							dto.setPercentTicketDistribution(Float.parseFloat(def.getDefaultValues()));
							dtoList.add(dto);
						}
						
					}
				}
			}else{
				logger.info("Fetching data from TicketDistribution Table. Opp scopes are same in OpportunityScope and TicketDistribution tables.");
				dtoList = new ArrayList<TicketDistributionDTO>();
				for(TicketDistribution entity : listEntity){
					TicketDistributionDTO dto = new TicketDistributionDTO();
					BeanUtils.copyProperties(entity, dto);
					dtoList.add(dto);
				}				
			}
			
		}else{
			logger.info("DATA is not present in TicketDistribution Table. Taking Default data");
			dtoList = new ArrayList<TicketDistributionDTO>();
			List<OtherDefaults> defList = volDao.getDefTktDistByServEle(serviceElement);
			for(OpportunityScopeDTO oppScopedto : opportunityScopeList){
				TicketDistributionDTO dto = new TicketDistributionDTO();
				for(OtherDefaults def : defList){
					if(def.getServiceScopeID().equals(oppScopedto.getServiceScopeDTO().getServiceScopeId())){
						dto.setOpportunityScope(convertOppScopeDtoToEntity(oppScopedto));
						dto.setPercentTicketDistribution(Float.parseFloat(def.getDefaultValues()));
						dtoList.add(dto);
					}
					
				}
			}
		}
		return dtoList;
	}

	@Override
	public void saveTicketDistribution(TicketDistributionDTO tktDistDTO)
			throws MSSPException {
		TicketDistribution entity = new TicketDistribution();
		BeanUtils.copyProperties(tktDistDTO, entity);
		volDao.saveTicketDistribution(entity);
		
	}

	@Override
	public List<AppMainSupportActivityDTO> getSuppActListByOppCompIds(List<OpportunityComponentDTO> components) throws MSSPException {
		List<AppMainSupportActivityDTO> suppActDtoList = null;
		List<Integer> oppCompIds = new ArrayList<Integer>();
		for(OpportunityComponentDTO dto : components){
			oppCompIds.add(dto.getOpportunityComponentID());
		}
		List<AppMainSupportActivity> suppActList = volDao.getSuppActListByOppCompIds(oppCompIds);
		if(null != suppActList && suppActList.size() > 0){
			List<OpportunityComponentDTO> addedOpportunityComponents = compareOppCompIds(components,suppActList);
			if(addedOpportunityComponents != null && addedOpportunityComponents.size() > 0){
				logger.info("Fetching Data from Support Activity Table and OtherDefaults table.");
				suppActDtoList = new ArrayList<AppMainSupportActivityDTO>();
				// Adding Opportunity components already present in AppMainSupportActivity table.
				for(AppMainSupportActivity entity : suppActList){
					AppMainSupportActivityDTO dto = new AppMainSupportActivityDTO();
					BeanUtils.copyProperties(entity, dto);
					suppActDtoList.add(dto);
				}
				
				// Adding New opportunity components from OtherDefaults table.
				List<String> addedCompNames = new ArrayList<String>();
				for(OpportunityComponentDTO dto : addedOpportunityComponents){
					addedCompNames.add(dto.getComponent().getComponentName());
				}
				List<OtherDefaults> defList = volDao.getDefSuppActivityByCompName(addedCompNames);
				for(OpportunityComponentDTO oppCompDto : components){					
					for(OtherDefaults def : defList){
						if(oppCompDto.getComponent().getComponentName().equalsIgnoreCase(def.getOtherFieldsName())){
							AppMainSupportActivityDTO dto = new AppMainSupportActivityDTO();
							dto.setOpportinityComponent(getOppCompEntityfromDto(oppCompDto));
							dto.setTicketsPerMonth(Float.parseFloat(def.getDefaultValues()));
							dto.setCallVolume("Above 25");
							dto.setOnCallSupport("Y");
							dto.setSupportWindowMatrix(getSupportWindowMatrixById(2));
							suppActDtoList.add(dto);
						}
						
					}
				}
				
			}else{
				logger.info("Fetching Data from Support Activity Table. OpportunityComponents are same in OppComp and SuppActivity tables.");
				suppActDtoList = new ArrayList<AppMainSupportActivityDTO>();
				for(AppMainSupportActivity entity : suppActList){
					AppMainSupportActivityDTO dto = new AppMainSupportActivityDTO();
					BeanUtils.copyProperties(entity, dto);
					suppActDtoList.add(dto);
				}
				
			}
			
		}else{
			logger.info("DATA is not present in Support Activity Table. Taking Default data");
			suppActDtoList = new ArrayList<AppMainSupportActivityDTO>();
			List<OtherDefaults> defList = volDao.getDefSuppActivity();
			for(OpportunityComponentDTO oppCompDto : components){
				AppMainSupportActivityDTO dto = new AppMainSupportActivityDTO();
				for(OtherDefaults def : defList){
					if(oppCompDto.getComponent().getComponentName().equalsIgnoreCase(def.getOtherFieldsName())){
						dto.setOpportinityComponent(getOppCompEntityfromDto(oppCompDto));
						dto.setTicketsPerMonth(Float.parseFloat(def.getDefaultValues()));
						dto.setCallVolume("Above 25");
						dto.setOnCallSupport("Y");
						dto.setSupportWindowMatrix(getSupportWindowMatrixById(2));
						suppActDtoList.add(dto);
					}
					
				}
			}
			
		}
		return suppActDtoList;
	}

	private OpportunityComponent getOppCompEntityfromDto(OpportunityComponentDTO oppCompDto){
		OpportunityComponent comp = new OpportunityComponent();
		BeanUtils.copyProperties(oppCompDto, comp);
		return comp;
	}
	
	private SupportWindowMatrix getSupportWindowMatrixById(Integer suppWinMatId) throws MSSPException{
		SupportWindowMatrix matrix = volDao.getSupportWindowMatrixById(suppWinMatId);
		return matrix;
	}
	
	private OpportunityScope convertOppScopeDtoToEntity(OpportunityScopeDTO dto){
		OpportunityScope entity = new OpportunityScope();
		ServiceScope servScope = convertServScopeDtoToEntity(dto.getServiceScopeDTO());		
		entity.setServiceScope(servScope);
		entity.setOpportunityScopeId(dto.getOpportunityScopeId());
		//BeanUtils.copyProperties(dto, entity);
		return entity;
	}
	private ServiceScope convertServScopeDtoToEntity(ServiceScopeDTO dto){
		ServiceScope entity = new ServiceScope();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	@Override
	public List<SupportWindowMatrixDTO> getAllSupportWindowMatrix() throws MSSPException {
		List<SupportWindowMatrixDTO> dtoList = new ArrayList<SupportWindowMatrixDTO>();
		List<SupportWindowMatrix> entityList = volDao.getAllSupportWindowMatrix();
		for(SupportWindowMatrix entity : entityList){
			SupportWindowMatrixDTO dto = new SupportWindowMatrixDTO();
			BeanUtils.copyProperties(entity, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public Double getFactor(Integer SolutionID, String paramTypeCode)
			throws MSSPException {
		Double factor = null;
		List<Object> list = volDao.getFactorBySolutionId(SolutionID, paramTypeCode);
		if(null!=list && list.size() > 0){
			factor = ((BigDecimal)list.get(0)).doubleValue();
		}else{
			List<Object> defList = volDao.getDefaultFactor(paramTypeCode);
			factor = ((BigDecimal)defList.get(0)).doubleValue();
		}
		return factor;
	}

	@Override
	public List<Object[]> getTotalBaseHrsFteForDevMain(Integer solId)
			throws MSSPException {
		List<Object[]> list = volDao.getTotalBaseHrsFteForDevMain(solId);
		return list;
	}

	@Override
	public List<ServiceScopeAppMainDataBean> getProductEstBaseEffData(
			List<OpportunityScopeDTO> opportunityScopeList)
			throws MSSPException {
		List<Integer> oppScopeIdList = new ArrayList<Integer>();
		for(OpportunityScopeDTO dto : opportunityScopeList){
			oppScopeIdList.add(dto.getOpportunityScopeId());
		}
		List<ProductEstimationBaseEffortForSolution> list = volDao.getProductEstBaseEffData(oppScopeIdList);
		
		List<ServiceScopeAppMainDataBean> beanList = new ArrayList<ServiceScopeAppMainDataBean>();
		for(ProductEstimationBaseEffortForSolution entity : list){
			ServiceScopeAppMainDataBean bean = new ServiceScopeAppMainDataBean();
			bean.setServScopeName(entity.getOpportunityScope().getServiceScope().getServiceScopeName());
			bean.setTotalBaseHours(entity.getTotalBaseHours());
			bean.setTotalBaseFTE(entity.getTotalBaseFTE());
			beanList.add(bean);
		}
		return beanList;
	}

	/*
	 * This method compares the Opportunity Scope Id's of the OpportunityScope table and TicketDistribution table. This is to render the case
	 * when a new OpportunityScope is added in OpportunityScope table then that opp scope with default value should also be displayed on
	 * input page.
	 */
	private List<Integer> compareOppScopeIds(List<OpportunityScopeDTO> opportunityScopeList,List<TicketDistribution> listEntity){
		logger.info("Comparing old and new service scopes");
		
		List<Integer> addedServElementIds = new ArrayList<Integer>();
		for(OpportunityScopeDTO dto : opportunityScopeList){
			boolean isContains = false;
			for(TicketDistribution entity : listEntity){
				if(dto.getOpportunityScopeId().equals(entity.getOpportunityScope().getOpportunityScopeId())){
					isContains = true;
				}
			}
			if(!isContains){
				addedServElementIds.add(dto.getServiceScopeDTO().getServiceScopeId());
			}
		}
		return addedServElementIds;
	}
	
	private List<OpportunityComponentDTO> compareOppCompIds(List<OpportunityComponentDTO> components,List<AppMainSupportActivity> suppActList){
		List<OpportunityComponentDTO> addedOpportunityComponents = new ArrayList<OpportunityComponentDTO>();
		for(OpportunityComponentDTO dto : components){
			boolean isContains = false;
			for(AppMainSupportActivity activity : suppActList){
				if(dto.getOpportunityComponentID().equals(activity.getOpportinityComponent().getOpportunityComponentID())){
					isContains = true;
				}
			}
			if(!isContains){
				addedOpportunityComponents.add(dto);
			}
		}
		
		return addedOpportunityComponents;
	}

	@Override
	public boolean opportunityHasServiceElement(Integer opportunityId,
			String serviceElementName) throws MSSPException {
		return volDao.opportunityHasServiceElement(opportunityId, serviceElementName);
	}
		
}
