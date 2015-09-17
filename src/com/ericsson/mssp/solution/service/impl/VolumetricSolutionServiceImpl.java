/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.dao.impl
 * File name       		    :  CalculateVolumetricSolutionFTE.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Jan 9, 20132013
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Jan 9, 2013  	   |egaivij   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.E2EProcessQualityDTO;
import com.ericsson.mssp.common.dto.JobRoleDTO;
import com.ericsson.mssp.common.dto.SolutionEnhanDevFixedCapacityDTO;
import com.ericsson.mssp.common.dto.SolutionEnhanDevRicefwDTO;
import com.ericsson.mssp.common.dto.SolutionL1AddServicesDTO;
import com.ericsson.mssp.common.dto.SolutionL1OperationsDTO;
import com.ericsson.mssp.common.dto.SolutionL2AddServicesDTO;
import com.ericsson.mssp.common.dto.SolutionL2OperationsDTO;
import com.ericsson.mssp.common.dto.SolutionL3AddServicesDTO;
import com.ericsson.mssp.common.dto.SolutionL3OperationsDTO;
import com.ericsson.mssp.common.dto.SolutionOtherMiscDTO;
import com.ericsson.mssp.common.dto.SolutionServiceDeskDTO;
import com.ericsson.mssp.common.dto.SupportWindowMatrixDTO;
import com.ericsson.mssp.common.dto.TouchPointChannelDTO;
import com.ericsson.mssp.common.entity.E2EProcessQuality;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionApproachDimension;
import com.ericsson.mssp.common.entity.SolutionEnhanDevFixedCapacity;
import com.ericsson.mssp.common.entity.SolutionEnhanDevRicefw;
import com.ericsson.mssp.common.entity.SolutionGovAndPmo;
import com.ericsson.mssp.common.entity.SolutionL1addServices;
import com.ericsson.mssp.common.entity.SolutionL1operations;
import com.ericsson.mssp.common.entity.SolutionL2addServices;
import com.ericsson.mssp.common.entity.SolutionL2operations;
import com.ericsson.mssp.common.entity.SolutionL3addServices;
import com.ericsson.mssp.common.entity.SolutionL3operations;
import com.ericsson.mssp.common.entity.SolutionOtherMisc;
import com.ericsson.mssp.common.entity.SolutionServiceDesk;
import com.ericsson.mssp.common.entity.SupportWindowMatrix;
import com.ericsson.mssp.common.entity.TouchPointChannel;
import com.ericsson.mssp.common.entity.VolumetricSolutionDefault;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.dao.IVolumetricSolutionDAO;
import com.ericsson.mssp.solution.forms.EnhancementAndDevForm;
import com.ericsson.mssp.solution.forms.SolutionL1OperationsForm;
import com.ericsson.mssp.solution.forms.SolutionL2OperationsForm;
import com.ericsson.mssp.solution.forms.SolutionL3OperationsForm;
import com.ericsson.mssp.solution.forms.SolutionOtherMiscForm;
import com.ericsson.mssp.solution.service.IVolumetricSolutionService;

/**
 * @author egaivij
 * 
 */
@Service
public class VolumetricSolutionServiceImpl implements
		IVolumetricSolutionService, MSSPConstants {

	@Autowired
	private IVolumetricSolutionDAO volumetricSolutionDAO;

	private static final Logger logger = Logger
			.getLogger(VolumetricSolutionServiceImpl.class);

	public List<SupportWindowMatrixDTO> getAllSupportWindowMatrix()
			throws MSSPException {

		List<SupportWindowMatrixDTO> swmList = new ArrayList<SupportWindowMatrixDTO>();
		List<SupportWindowMatrix> list = volumetricSolutionDAO
				.getAllSupportWindowMatrix();
		for (SupportWindowMatrix swm : list) {
			swmList.add(convertSWMEntityToDTO(swm));
		}
		return swmList;
	}

	private SupportWindowMatrixDTO convertSWMEntityToDTO(SupportWindowMatrix swm) {
		SupportWindowMatrixDTO swmDTO = new SupportWindowMatrixDTO();
		swmDTO.setSupportWindowMatrixId(swm.getSupportWindowMatrixId());
		swmDTO.setSupportWindow(swm.getSupportWindow());
		swmDTO.setDerivedFactor(swm.getDerivedFactor());
		return swmDTO;
	}
	
	private OpportunityScope getOpportunityScope(Integer oppScopeId){
		OpportunityScope opportunityScope = new OpportunityScope();
		opportunityScope.setOpportunityScopeId(oppScopeId);
		return opportunityScope;
	}
	
	@Override
	public void saveServiceDesk(
			List<SolutionServiceDeskDTO> serviceDeskDTOList,
			Integer solutionId, Integer supportWindowMatrixId,Integer solDimentionAttId, Integer OppScopeId)
			throws MSSPException {
		List<SolutionServiceDesk> serviceDeskList = convertFromServiceDeskDTO2Entity(
				serviceDeskDTOList, solutionId, solDimentionAttId);

		SupportWindowMatrix swm = new SupportWindowMatrix();
		swm.setSupportWindowMatrixId(supportWindowMatrixId);
		
		for (SolutionServiceDesk serviceDesk : serviceDeskList) {
			serviceDesk.setSupportWindowMatrix(swm);
			serviceDesk.setOpportunityScope(getOpportunityScope(OppScopeId));
			volumetricSolutionDAO.saveServiceDesk(serviceDesk);
		}

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

	/**
	 * 
	 * Description : Returns SupportWindowMatrix object by ID Method Name :
	 * getSolutionByID Input& Output:
	 * 
	 * @param supportWindowMatrixID
	 * @return SupportWindowMatrix
	 */
	private SupportWindowMatrix getSupportWindowMatrixByID(
			Integer supportWindowMatrixID) {
		return (SupportWindowMatrix) volumetricSolutionDAO.getObject(
				SupportWindowMatrix.class, supportWindowMatrixID);
	}

	/**
	 * 
	 * Description : It will convert Solution additional Services DTO values
	 * into entity Method Name : convertSolutionL1AddServicesDTO2Enitity Input&
	 * Output:
	 * 
	 * @param solutionL1AddServicesDTO
	 * @return SolutionL1addServices
	 */
	private SolutionL1addServices convertSolutionL1AddServicesDTO2Enitity(
			SolutionL1AddServicesDTO solutionL1AddServicesDTO,
			Integer solutionID, Integer dimensionId, Integer oppScopeId) {
		SolutionL1addServices solutionL1AddServices = new SolutionL1addServices();

		solutionL1AddServices
				.setSolutionL1addServicesId(solutionL1AddServicesDTO
						.getSolutionL1addServicesId());
		solutionL1AddServices.setSolution(setSolutionById(solutionID));
		solutionL1AddServices.setOpportunityScope(getOpportunityScope(oppScopeId));

		solutionL1AddServices.setSolutionApproachDimension(getSolutionApproachDimension(dimensionId));
		
		solutionL1AddServices.setScopeAccessMgmt(solutionL1AddServicesDTO
				.getScopeAccessMgmt());
		solutionL1AddServices.setScopeRequestMgmt(solutionL1AddServicesDTO
				.getScopeRequestMgmt());
		solutionL1AddServices.setScopeEventMgmt(solutionL1AddServicesDTO
				.getScopeEventMgmt());
		solutionL1AddServices.setScopeL1billOperation(solutionL1AddServicesDTO
				.getScopeL1billOperation());
		solutionL1AddServices.setScopeL1preventive(solutionL1AddServicesDTO
				.getScopeL1preventive());
		solutionL1AddServices.setScopeL1change(solutionL1AddServicesDTO
				.getScopeL1change());
		solutionL1AddServices.setScopeL1routine(solutionL1AddServicesDTO
				.getScopeL1routine());
		solutionL1AddServices.setScopeL1performance(solutionL1AddServicesDTO
				.getScopeL1performance());
		solutionL1AddServices.setEffortAccessMgmt(solutionL1AddServicesDTO
				.getEffortAccessMgmt());
		solutionL1AddServices.setEffortRequestMgmt(solutionL1AddServicesDTO
				.getEffortRequestMgmt());
		solutionL1AddServices.setEffortEventMgmt(solutionL1AddServicesDTO
				.getEffortEventMgmt());
		solutionL1AddServices.setEffortL1billOperation(solutionL1AddServicesDTO
				.getEffortL1billOperation());
		solutionL1AddServices.setEffortL1preventive(solutionL1AddServicesDTO
				.getEffortL1preventive());
		solutionL1AddServices.setEffortL1change(solutionL1AddServicesDTO
				.getEffortL1change());
		solutionL1AddServices.setEffortL1routine(solutionL1AddServicesDTO
				.getEffortL1routine());
		solutionL1AddServices.setEffortL1performance(solutionL1AddServicesDTO
				.getEffortL1performance());
		solutionL1AddServices.setFteaccessMgmt(solutionL1AddServicesDTO
				.getFteaccessMgmt());
		solutionL1AddServices.setFterequestMgmt(solutionL1AddServicesDTO
				.getFterequestMgmt());
		solutionL1AddServices.setFteeventMgmt(solutionL1AddServicesDTO
				.getFteeventMgmt());
		solutionL1AddServices.setFtel1billOperation(solutionL1AddServicesDTO
				.getFtel1billOperation());
		solutionL1AddServices.setFtel1preventive(solutionL1AddServicesDTO
				.getFtel1preventive());
		solutionL1AddServices.setFtel1change(solutionL1AddServicesDTO
				.getFtel1change());
		solutionL1AddServices.setFtel1routine(solutionL1AddServicesDTO
				.getFtel1routine());
		solutionL1AddServices.setFtel1performance(solutionL1AddServicesDTO
				.getFtel1performance());
		solutionL1AddServices.setBaseL1serviceFte(solutionL1AddServicesDTO
				.getBaseL1serviceFte());
		solutionL1AddServices.setAugmentedL1serviceFte(solutionL1AddServicesDTO
				.getAugmentedL1serviceFte());
		return solutionL1AddServices;
	}

	/**
	 * 
	 * Description : It will convert Solution operation Services DTO values into
	 * entity Method Name : convertSolutionL1OperationsDTO2Enitity Input&
	 * Output:
	 * 
	 * @param solutionL1OperationsDTO
	 * @return SolutionL1operations
	 */
	private SolutionL1operations convertSolutionL1OperationsDTO2Enitity(
			SolutionL1OperationsDTO solutionL1OperationsDTO,
			Integer solutionID, Integer dimensionId,Integer oppScopeId) {
		
		SolutionL1operations solutionL1operations = new SolutionL1operations();

		solutionL1operations.setSolutionL1operationsId(solutionL1OperationsDTO
				.getSolutionL1operationsId());

		solutionL1operations.setSolution(setSolutionById(solutionID));
		solutionL1operations.setOpportunityScope(getOpportunityScope(oppScopeId));
		
		if (null != solutionL1OperationsDTO.getSupportWindowMatrixDTO()
				.getSupportWindowMatrixId()) {
			solutionL1operations
					.setSupportWindowMatrix(getSupportWindowMatrixByID(solutionL1OperationsDTO
							.getSupportWindowMatrixDTO()
							.getSupportWindowMatrixId()));
		}
	
		solutionL1operations.setSolutionApproachDimension(getSolutionApproachDimension(dimensionId));
		
		solutionL1operations.setPcofL1incidents(solutionL1OperationsDTO
				.getPcofL1incidents());
		solutionL1operations.setTotalL1incidentsperYr(solutionL1OperationsDTO
				.getTotalL1incidentsperYr());
		solutionL1operations.setPcsimpleIncidents(solutionL1OperationsDTO
				.getPcsimpleIncidents());
		solutionL1operations.setPcmediumIncidents(solutionL1OperationsDTO
				.getPcmediumIncidents());
		solutionL1operations.setPccomplexIncidents(solutionL1OperationsDTO
				.getPccomplexIncidents());
		solutionL1operations.setHrsSimpleIncidents(solutionL1OperationsDTO
				.getHrsSimpleIncidents());
		solutionL1operations.setHrsMediumIncidents(solutionL1OperationsDTO
				.getHrsMediumIncidents());
		solutionL1operations.setHrsComplexIncidents(solutionL1OperationsDTO
				.getHrsComplexIncidents());
		solutionL1operations.setAvgResolutionTimeHrs(solutionL1OperationsDTO
				.getAvgResolutionTimeHrs());
		solutionL1operations.setAnualHrsSpent(solutionL1OperationsDTO
				.getAnualHrsSpent());
		solutionL1operations.setUtilizationperYr(solutionL1OperationsDTO
				.getUtilizationperYr());
		solutionL1operations.setBaseL1operationFte(solutionL1OperationsDTO
				.getBaseL1operationFte());
		solutionL1operations.setAugmentedL1operationFte(solutionL1OperationsDTO
				.getAugmentedL1operationFte());
		return solutionL1operations;

	}

	@Override
	public void saveSolutionL1Operations(Integer solutionID,
			SolutionL1OperationsForm solutionL1OperationsForm,
			Integer dimensionId, Integer oppScopeId) throws MSSPException {
		SolutionL1OperationsDTO solutionL1OperationsDTO = solutionL1OperationsForm
				.getSolutionL1OperationsDTO();

		SolutionL1AddServicesDTO solutionL1AddServicesDTO = solutionL1OperationsForm
				.getSolutionL1AddServicesDTO();
		// Save SolutionL2Operation data
		if (solutionL1OperationsDTO != null) {
			SolutionL1operations solutionL1Operations = convertSolutionL1OperationsDTO2Enitity(
					solutionL1OperationsDTO, solutionID, dimensionId, oppScopeId);

			SolutionL1addServices solutionL1AddServices = convertSolutionL1AddServicesDTO2Enitity(
					solutionL1AddServicesDTO, solutionID, dimensionId, oppScopeId);

			volumetricSolutionDAO.saveSolutionL1Operations(
					solutionL1Operations, solutionL1AddServices);

		} else {
			logger.info(" solutionL1OperationsDTO  : "
					+ solutionL1OperationsDTO);
		}

	}

	@Override
	public void saveSolutionL2Operations(Integer solutionID,
			SolutionL2OperationsForm solutionL2OperationsForm,
			Integer dimensionId,Integer oppScopeId) throws MSSPException {
		SolutionL2OperationsDTO solutionL2OperationsDTO = solutionL2OperationsForm
				.getSolutionL2OperationsDTO();

		SolutionL2AddServicesDTO solutionL2AddServicesDTO = solutionL2OperationsForm
				.getSolutionL2AddServicesDTO();
		// Save SolutionL2Operation data
		if (solutionL2OperationsDTO != null) {
			SolutionL2operations solutionL2Operations = convertSolutionL2OperationsDTO2Enitity(
					solutionL2OperationsDTO, solutionID, dimensionId, oppScopeId);

			SolutionL2addServices solutionL2AddServices = convertSolutionL2AddServicesDTO2Enitity(
					solutionL2AddServicesDTO, solutionID, dimensionId, oppScopeId);

			volumetricSolutionDAO.saveSolutionL2Operations(
					solutionL2Operations, solutionL2AddServices);

		} else {
			logger.info(" solutionL2OperationsDTO  : "
					+ solutionL2OperationsDTO);
		}

	}

	/**
	 * 
	 * Description : It will convert Solution additional Services DTO values
	 * into entity Method Name : convertSolutionL2AddServicesDTO2Enitity Input&
	 * Output:
	 * 
	 * @param solutionL2AddServicesDTO
	 * @return SolutionL2addServices
	 */
	private SolutionL2addServices convertSolutionL2AddServicesDTO2Enitity(
			SolutionL2AddServicesDTO solutionL2AddServicesDTO,
			Integer solutionID, Integer dimensionId, Integer oppScopeId ) {
		SolutionL2addServices solutionL2AddServices = new SolutionL2addServices();

		solutionL2AddServices
				.setSolutionL2addServicesId(solutionL2AddServicesDTO
						.getSolutionL2addServicesId());

		solutionL2AddServices.setSolution(setSolutionById(solutionID));
		solutionL2AddServices.setOpportunityScope(getOpportunityScope(oppScopeId));
		solutionL2AddServices
				.setSolutionApproachDimension(getSolutionApproachDimension(dimensionId));
		
		solutionL2AddServices.setScopeAvailMgmt(solutionL2AddServicesDTO
				.getScopeAvailMgmt());
		solutionL2AddServices.setScopeCapacityMgmt(solutionL2AddServicesDTO
				.getScopeCapacityMgmt());
		solutionL2AddServices.setScopeSecurityMgmt(solutionL2AddServicesDTO
				.getScopeSecurityMgmt());
		solutionL2AddServices.setScopeL2billOperation(solutionL2AddServicesDTO
				.getScopeL2billOperation());
		solutionL2AddServices.setScopeL2preventive(solutionL2AddServicesDTO
				.getScopeL2preventive());
		solutionL2AddServices.setScopeL2change(solutionL2AddServicesDTO
				.getScopeL2change());
		solutionL2AddServices.setScopeL2routine(solutionL2AddServicesDTO
				.getScopeL2routine());
		solutionL2AddServices.setScopeL2performance(solutionL2AddServicesDTO
				.getScopeL2performance());
		solutionL2AddServices.setScopeL2releaseDev(solutionL2AddServicesDTO
				.getScopeL2releaseDev());
		solutionL2AddServices.setEffortAvailMgmt(solutionL2AddServicesDTO
				.getEffortAvailMgmt());
		solutionL2AddServices.setEffortCapacityMgmt(solutionL2AddServicesDTO
				.getEffortCapacityMgmt());
		solutionL2AddServices.setEffortSecurityMgmt(solutionL2AddServicesDTO
				.getEffortSecurityMgmt());
		solutionL2AddServices.setEffortL2billOperation(solutionL2AddServicesDTO
				.getEffortL2billOperation());
		solutionL2AddServices.setEffortL2preventive(solutionL2AddServicesDTO
				.getEffortL2preventive());
		solutionL2AddServices.setEffortL2change(solutionL2AddServicesDTO
				.getEffortL2change());
		solutionL2AddServices.setEffortL2routine(solutionL2AddServicesDTO
				.getEffortL2routine());
		solutionL2AddServices.setEffortL2performance(solutionL2AddServicesDTO
				.getEffortL2performance());
		solutionL2AddServices.setEffortL2releaseDev(solutionL2AddServicesDTO
				.getEffortL2releaseDev());
		solutionL2AddServices.setFteavailMgmt(solutionL2AddServicesDTO
				.getFteavailMgmt());
		solutionL2AddServices.setFtecapacityMgmt(solutionL2AddServicesDTO
				.getFtecapacityMgmt());
		solutionL2AddServices.setFtesecurityMgmt(solutionL2AddServicesDTO
				.getFtesecurityMgmt());
		solutionL2AddServices.setFtel2billOperation(solutionL2AddServicesDTO
				.getFtel2billOperation());
		solutionL2AddServices.setFtel2preventive(solutionL2AddServicesDTO
				.getFtel2preventive());
		solutionL2AddServices.setFtel2change(solutionL2AddServicesDTO
				.getFtel2change());
		solutionL2AddServices.setFtel2routine(solutionL2AddServicesDTO
				.getFtel2routine());
		solutionL2AddServices.setFtel2performance(solutionL2AddServicesDTO
				.getFtel2performance());
		solutionL2AddServices.setFtel2releaseDev(solutionL2AddServicesDTO
				.getFtel2releaseDev());
		solutionL2AddServices.setBaseL2serviceFte(solutionL2AddServicesDTO
				.getBaseL2serviceFte());
		solutionL2AddServices.setAugmentedL2serviceFte(solutionL2AddServicesDTO
				.getAugmentedL2serviceFte());
		return solutionL2AddServices;
	}

	/**
	 * 
	 * Description : It will convert Solution operation Services DTO values into
	 * entity Method Name : convertSolutionL2OperationsDTO2Enitity Input&
	 * Output:
	 * 
	 * @param solutionL2OperationsDTO
	 * @return SolutionL2operations
	 */
	private SolutionL2operations convertSolutionL2OperationsDTO2Enitity(
			SolutionL2OperationsDTO solutionL2OperationsDTO,
			Integer solutionID, Integer dimensionId, Integer oppScopeId) {
		SolutionL2operations solutionL2operations = new SolutionL2operations();

		solutionL2operations.setSolutionL2operationsId(solutionL2OperationsDTO
				.getSolutionL2operationsId());

		solutionL2operations.setSolution(setSolutionById(solutionID));
		solutionL2operations.setOpportunityScope(getOpportunityScope(oppScopeId));
		
		if (null != solutionL2OperationsDTO.getSupportWindowMatrixDTO()
				.getSupportWindowMatrixId()) {
			solutionL2operations
					.setSupportWindowMatrix(getSupportWindowMatrixByID(solutionL2OperationsDTO
							.getSupportWindowMatrixDTO()
							.getSupportWindowMatrixId()));
		}
		
		solutionL2operations
		.setSolutionApproachDimension(getSolutionApproachDimension(dimensionId));
		
		solutionL2operations.setPcofL2incidents(solutionL2OperationsDTO
				.getPcofL2incidents());
		solutionL2operations.setTotalL2incidentsperYr(solutionL2OperationsDTO
				.getTotalL2incidentsperYr());
		solutionL2operations.setPcsimpleIncidents(solutionL2OperationsDTO
				.getPcsimpleIncidents());
		solutionL2operations.setPcmediumIncidents(solutionL2OperationsDTO
				.getPcmediumIncidents());
		solutionL2operations.setPccomplexIncidents(solutionL2OperationsDTO
				.getPccomplexIncidents());
		solutionL2operations.setHrsSimpleIncidents(solutionL2OperationsDTO
				.getHrsSimpleIncidents());
		solutionL2operations.setHrsMediumIncidents(solutionL2OperationsDTO
				.getHrsMediumIncidents());
		solutionL2operations.setHrsComplexIncidents(solutionL2OperationsDTO
				.getHrsComplexIncidents());
		solutionL2operations.setAvgResolutionTimeHrs(solutionL2OperationsDTO
				.getAvgResolutionTimeHrs());
		solutionL2operations.setAnualHrsSpent(solutionL2OperationsDTO
				.getAnualHrsSpent());
		solutionL2operations.setUtilizationperYr(solutionL2OperationsDTO
				.getUtilizationperYr());
		solutionL2operations.setBaseL2operationFte(solutionL2OperationsDTO
				.getBaseL2operationFte());
		solutionL2operations.setAugmentedL2operationFte(solutionL2OperationsDTO
				.getAugmentedL2operationFte());
		return solutionL2operations;

	}
	
	private SolutionApproachDimension getSolutionApproachDimension(Integer solDimentionAttId){
		SolutionApproachDimension sad = null;
		if(solDimentionAttId != null){
		 sad = new SolutionApproachDimension();
		sad.setSolutionApproachDimensionId(solDimentionAttId);
		}
		return sad;
	}

	/**
	 * 
	 * Description : converting ServiceDeskDTO to Entity Method Name :
	 * convertFromServiceDeskDTO2Entity Input& Output:
	 * 
	 * @param serviceDeskDTOList
	 * @param solutionId
	 * @return
	 * @throws MSSPException
	 */
	private List<SolutionServiceDesk> convertFromServiceDeskDTO2Entity(
			List<SolutionServiceDeskDTO> serviceDeskDTOList, Integer solutionId, Integer solDimentionAttId)
			throws MSSPException {
		SolutionServiceDesk serviceDesk = null;
	
		TouchPointChannel touchPointChannel = null;
		List<SolutionServiceDesk> serviceDeskList = new ArrayList<SolutionServiceDesk>();
		for (SolutionServiceDeskDTO serviceDeskDTO : serviceDeskDTOList) {

			serviceDesk = new SolutionServiceDesk();
			
			touchPointChannel = new TouchPointChannel();
			serviceDesk.setServiceDeskId(serviceDeskDTO.getServiceDeskId());
			serviceDesk.setTransactionsPerMonth(serviceDeskDTO
					.getTransactionsPerMonth());
			serviceDesk.setAverageHandlingTime(serviceDeskDTO
					.getAverageHandlingTime());
			serviceDesk.setTotalTransPerYear(serviceDeskDTO
					.getTotalTransPerYear());
			serviceDesk.setDurationOfCallsPerYear(serviceDeskDTO
					.getDurationOfCallsPerYear());
			serviceDesk.setUtilizationPerYear(serviceDeskDTO
					.getUtilizationPerYear());
			serviceDesk.setBaseServiceDeskFte(serviceDeskDTO
					.getBaseServiceDeskFte());
			serviceDesk.setAugmentedHeads(serviceDeskDTO.getAugmentedHeads());
			
			serviceDesk.setSolution(setSolutionById(solutionId));

			touchPointChannel.setTouchPointChannelId(serviceDeskDTO
					.getTouchPointChannelDTO().getTouchPointChannelId());
			serviceDesk.setTouchPointChannel(touchPointChannel);
			
			serviceDesk.setSolutionApproachDimension(getSolutionApproachDimension(solDimentionAttId));
			serviceDeskList.add(serviceDesk);

		}
		return serviceDeskList;
	}

	@Override
	public List<SolutionServiceDeskDTO> getServicDesk(int solutionId)
			throws MSSPException {
		return (volumetricSolutionDAO.getServicDesk(solutionId));
	}

	@Override
	public List<SolutionServiceDeskDTO> loadServiceDeskListBySolutionId(
			Integer solId, Integer solDimentionAttId)throws MSSPException {

		List<SolutionServiceDeskDTO> serviceDeskDTOList = new ArrayList<SolutionServiceDeskDTO>();
		List<SolutionServiceDesk> serviceDeskList = volumetricSolutionDAO
				.loadServiceDeskListBySolutionId(solId, solDimentionAttId);

		for (SolutionServiceDesk solSerDesk : serviceDeskList) {

			serviceDeskDTOList.add(convertSDEnitityToDTO(solSerDesk));

		}

		return serviceDeskDTOList;
	}

	/**
	 * 
	 * Description : TODO Method Name : convertSDEnitityToDTO Input& Output:
	 * 
	 * @param solSerDesk
	 * @return
	 */
	private SolutionServiceDeskDTO convertSDEnitityToDTO(
			SolutionServiceDesk solSerDesk) {

		SolutionServiceDeskDTO solutionServiceDeskDTO = new SolutionServiceDeskDTO();

		solutionServiceDeskDTO.setServiceDeskId(solSerDesk.getServiceDeskId());
		solutionServiceDeskDTO.setTransactionsPerMonth(solSerDesk
				.getTransactionsPerMonth());
		solutionServiceDeskDTO.setAverageHandlingTime(solSerDesk
				.getAverageHandlingTime());
		solutionServiceDeskDTO.setTotalTransPerYear(solSerDesk
				.getTotalTransPerYear());
		solutionServiceDeskDTO.setDurationOfCallsPerYear(solSerDesk
				.getDurationOfCallsPerYear());
		solutionServiceDeskDTO.setUtilizationPerYear(solSerDesk
				.getUtilizationPerYear());
		solutionServiceDeskDTO.setBaseServiceDeskFte(solSerDesk
				.getBaseServiceDeskFte());
		solutionServiceDeskDTO
				.setAugmentedHeads(solSerDesk.getAugmentedHeads());

		TouchPointChannelDTO touchPointChannelDTO = new TouchPointChannelDTO();
		touchPointChannelDTO.setTouchPointChannelId(solSerDesk
				.getTouchPointChannel().getTouchPointChannelId());
		solutionServiceDeskDTO.setTouchPointChannelDTO(touchPointChannelDTO);

		SupportWindowMatrixDTO swm = new SupportWindowMatrixDTO();
		swm.setSupportWindowMatrixId(solSerDesk.getSupportWindowMatrix()
				.getSupportWindowMatrixId());
		solutionServiceDeskDTO.setSupportWindowMatrixDTO(swm);
		return solutionServiceDeskDTO;
	}

	@Override
	public void saveSolutionL3Operations(Integer solutionID,
			SolutionL3OperationsForm solutionL3OperationsForm,
			Integer dimensionId, Integer oppScopeId ) throws MSSPException {
		SolutionL3OperationsDTO solutionL3OperationsDTO = solutionL3OperationsForm
				.getSolutionL3OperationsDTO();

		SolutionL3AddServicesDTO solutionL3AddServicesDTO = solutionL3OperationsForm
				.getSolutionL3AddServicesDTO();
		// Save SolutionL2Operation data
		if (null != solutionL3OperationsDTO) {
			SolutionL3operations solutionL3Operations = convertSolutionL3OperationsDTO2Enitity(
					solutionL3OperationsDTO, solutionID, dimensionId, oppScopeId);

			SolutionL3addServices solutionL3AddServices = convertSolutionL3AddServicesDTO2Enitity(
					solutionL3AddServicesDTO, solutionID, dimensionId, oppScopeId);

			volumetricSolutionDAO.saveSolutionL3Operations(
					solutionL3Operations, solutionL3AddServices);

		} else {
			logger.info("Null solutionL2AddServicesDTO :"
					+ solutionL3AddServicesDTO);
		}

	}

	/**
	 * 
	 * Description : It will convert Solution operation Services DTO values into
	 * entity Method Name : convertSolutionL3OperationsDTO2Enitity Input&
	 * Output:
	 * 
	 * @param solutionL3OperationsDTO
	 * @return SolutionL3operations
	 */
	private SolutionL3operations convertSolutionL3OperationsDTO2Enitity(
			SolutionL3OperationsDTO solutionL3OperationsDTO,
			Integer solutionID, Integer dimensionId, Integer oppScopeId) {
		SolutionL3operations solutionL3operations = new SolutionL3operations();

		solutionL3operations.setSolutionL3operationsId(solutionL3OperationsDTO
				.getSolutionL3operationsId());

		solutionL3operations.setSolution(setSolutionById(solutionID));
		solutionL3operations.setOpportunityScope(getOpportunityScope(oppScopeId));
		
		if (null != solutionL3OperationsDTO.getSupportWindowMatrixDTO()) {
			solutionL3operations
					.setSupportWindowMatrix(getSupportWindowMatrixByID(solutionL3OperationsDTO
							.getSupportWindowMatrixDTO()
							.getSupportWindowMatrixId()));
		}
		
		solutionL3operations
		.setSolutionApproachDimension(getSolutionApproachDimension(dimensionId));

		solutionL3operations.setPcofL2incidentsConvL3(solutionL3OperationsDTO
				.getPcofL2incidentsConvL3());
		solutionL3operations.setTotalL3bugFixesPerYear(solutionL3OperationsDTO
				.getTotalL3bugFixesPerYear());
		solutionL3operations.setPcsimpleIncidents(solutionL3OperationsDTO
				.getPcsimpleIncidents());
		solutionL3operations.setPcmediumIncidents(solutionL3OperationsDTO
				.getPcmediumIncidents());
		solutionL3operations.setPccomplexIncidents(solutionL3OperationsDTO
				.getPccomplexIncidents());
		solutionL3operations.setHrsSimpleIncidents(solutionL3OperationsDTO
				.getHrsSimpleIncidents());
		solutionL3operations.setHrsMediumIncidents(solutionL3OperationsDTO
				.getHrsMediumIncidents());
		solutionL3operations.setHrsComplexIncidents(solutionL3OperationsDTO
				.getHrsComplexIncidents());
		solutionL3operations.setAvgResolutionTimeHrs(solutionL3OperationsDTO
				.getAvgResolutionTimeHrs());
		solutionL3operations.setAnualHrsSpent(solutionL3OperationsDTO
				.getAnualHrsSpent());
		solutionL3operations.setUtilizationperYr(solutionL3OperationsDTO
				.getUtilizationperYr());
		solutionL3operations.setBaseL3operationFte(solutionL3OperationsDTO
				.getBaseL3operationFte());
		solutionL3operations.setAugmentedL3operationFte(solutionL3OperationsDTO
				.getAugmentedL3operationFte());
		return solutionL3operations;

	}

	/**
	 * 
	 * Description : It will convert Solution additional Services DTO values
	 * into entity Method Name : convertSolutionL3AddServicesDTO2Enitity Input&
	 * Output:
	 * 
	 * @param solutionL2AddServicesDTO
	 * @return SolutionL2addServices
	 */
	private SolutionL3addServices convertSolutionL3AddServicesDTO2Enitity(
			SolutionL3AddServicesDTO solutionL3AddServicesDTO,
			Integer solutionID, Integer dimensionId, Integer oppScopeId) {
		SolutionL3addServices solutionL3AddServices = new SolutionL3addServices();

		solutionL3AddServices
				.setSolutionL3addServicesId(solutionL3AddServicesDTO
						.getSolutionL3addServicesId());
		solutionL3AddServices.setSolution(setSolutionById(solutionID));
		solutionL3AddServices.setOpportunityScope(getOpportunityScope(oppScopeId));
		
		solutionL3AddServices
		.setSolutionApproachDimension(getSolutionApproachDimension(dimensionId));
		solutionL3AddServices.setScopeIntegTest(solutionL3AddServicesDTO
				.getScopeIntegTest());
		solutionL3AddServices.setScopeRegreTest(solutionL3AddServicesDTO
				.getScopeRegreTest());
		solutionL3AddServices.setScopeOtherTest(solutionL3AddServicesDTO
				.getScopeOtherTest());
		solutionL3AddServices.setScopeMisceSupp(solutionL3AddServicesDTO
				.getScopeMisceSupp());
		solutionL3AddServices.setScopePhysicalDatabase(solutionL3AddServicesDTO
				.getScopePhysicalDatabase());
		solutionL3AddServices.setScopeServerAndVirt(solutionL3AddServicesDTO
				.getScopeServerAndVirt());
		solutionL3AddServices.setScopeOtherInfra(solutionL3AddServicesDTO
				.getScopeOtherInfra());

		solutionL3AddServices.setUnitIntegTest(solutionL3AddServicesDTO
				.getUnitIntegTest());
		solutionL3AddServices.setUnitRegreTest(solutionL3AddServicesDTO
				.getUnitRegreTest());
		solutionL3AddServices.setUnitOtherTest(solutionL3AddServicesDTO
				.getUnitOtherTest());
		solutionL3AddServices.setUnitMisceSupp(solutionL3AddServicesDTO
				.getUnitMisceSupp());
		solutionL3AddServices.setUnitPhysicalDatabase(solutionL3AddServicesDTO
				.getUnitPhysicalDatabase());
		solutionL3AddServices.setUnitServerAndVirt(solutionL3AddServicesDTO
				.getUnitServerAndVirt());
		solutionL3AddServices.setUnitOtherInfra(solutionL3AddServicesDTO
				.getUnitOtherInfra());

		solutionL3AddServices.setValueIntegTest(solutionL3AddServicesDTO
				.getValueIntegTest());
		solutionL3AddServices.setValueRegreTest(solutionL3AddServicesDTO
				.getValueRegreTest());
		solutionL3AddServices.setValueOtherTest(solutionL3AddServicesDTO
				.getValueOtherTest());
		solutionL3AddServices.setValueMisceSupp(solutionL3AddServicesDTO
				.getValueMisceSupp());
		solutionL3AddServices.setValuePhysicalDatabase(solutionL3AddServicesDTO
				.getValuePhysicalDatabase());
		solutionL3AddServices.setValueServerAndVirt(solutionL3AddServicesDTO
				.getValueServerAndVirt());
		solutionL3AddServices.setValueOtherInfra(solutionL3AddServicesDTO
				.getValueOtherInfra());

		solutionL3AddServices.setFteintegTest(solutionL3AddServicesDTO
				.getFteintegTest());
		solutionL3AddServices.setFteregreTest(solutionL3AddServicesDTO
				.getFteregreTest());
		solutionL3AddServices.setFteotherTest(solutionL3AddServicesDTO
				.getFteotherTest());
		solutionL3AddServices.setFtemisceSupp(solutionL3AddServicesDTO
				.getFtemisceSupp());
		solutionL3AddServices.setFtephysicalDatabase(solutionL3AddServicesDTO
				.getFtephysicalDatabase());
		solutionL3AddServices.setFteserverAndVirt(solutionL3AddServicesDTO
				.getFteserverAndVirt());
		solutionL3AddServices.setFteotherInfra(solutionL3AddServicesDTO
				.getFteotherInfra());
		solutionL3AddServices.setBaseL3serviceFte(solutionL3AddServicesDTO
				.getBaseL3serviceFte());
		solutionL3AddServices.setAugmentedL3serviceFte(solutionL3AddServicesDTO
				.getAugmentedL3serviceFte());
		return solutionL3AddServices;
	}

	@Override
	public SolutionL3OperationsForm loadSolutionL3OperationsDetails(
			Integer solId, Integer solDimentionAttId) throws MSSPException {

		SolutionL3OperationsForm solutionL3OperationsForm = new SolutionL3OperationsForm();

		List<SolutionL3operations> l3OppList = volumetricSolutionDAO
				.loadSolutionL3Operations(solId, solDimentionAttId);

		if (l3OppList.size() > 0) {
			solutionL3OperationsForm
					.setSolutionL3OperationsDTO(convertSolutionL3OperationsEntity2DTO(l3OppList
							.get(0)));
		}

		List<SolutionL3addServices> l3AddOppList = volumetricSolutionDAO
				.loadSolutionL3addServices(solId, solDimentionAttId);

		if (l3AddOppList.size() > 0) {
			solutionL3OperationsForm
					.setSolutionL3AddServicesDTO(convertSolutionL3AddServicesEnitity2DTO(l3AddOppList
							.get(0)));
		}
		return solutionL3OperationsForm;
	}

	/**
	 * 
	 * Description : It will convert Solution operation Services DTO values into
	 * entity Method Name : convertSolutionL3OperationsDTO2Enitity Input&
	 * Output:
	 * 
	 * @param solutionL3OperationsDTO
	 * @return SolutionL3operations
	 */
	private SolutionL3OperationsDTO convertSolutionL3OperationsEntity2DTO(
			SolutionL3operations solutionL3operations) {
		SolutionL3OperationsDTO solutionL3OperationsDTO = new SolutionL3OperationsDTO();

		solutionL3OperationsDTO.setSolutionL3operationsId(solutionL3operations
				.getSolutionL3operationsId());

		if (solutionL3operations.getSupportWindowMatrix() != null) {
			SupportWindowMatrixDTO swm = convertSWMEntityToDTO(solutionL3operations
					.getSupportWindowMatrix());

			solutionL3OperationsDTO.setSupportWindowMatrixDTO(swm);
		}

		solutionL3OperationsDTO.setPcofL2incidentsConvL3(solutionL3operations
				.getPcofL2incidentsConvL3());
		solutionL3OperationsDTO.setTotalL3bugFixesPerYear(solutionL3operations
				.getTotalL3bugFixesPerYear());
		solutionL3OperationsDTO.setPcsimpleIncidents(solutionL3operations
				.getPcsimpleIncidents());
		solutionL3OperationsDTO.setPcmediumIncidents(solutionL3operations
				.getPcmediumIncidents());
		solutionL3OperationsDTO.setPccomplexIncidents(solutionL3operations
				.getPccomplexIncidents());
		solutionL3OperationsDTO.setHrsSimpleIncidents(solutionL3operations
				.getHrsSimpleIncidents());
		solutionL3OperationsDTO.setHrsMediumIncidents(solutionL3operations
				.getHrsMediumIncidents());
		solutionL3OperationsDTO.setHrsComplexIncidents(solutionL3operations
				.getHrsComplexIncidents());
		solutionL3OperationsDTO.setAvgResolutionTimeHrs(solutionL3operations
				.getAvgResolutionTimeHrs());
		solutionL3OperationsDTO.setAnualHrsSpent(solutionL3operations
				.getAnualHrsSpent());
		solutionL3OperationsDTO.setUtilizationperYr(solutionL3operations
				.getUtilizationperYr());
		solutionL3OperationsDTO.setBaseL3operationFte(solutionL3operations
				.getBaseL3operationFte());
		solutionL3OperationsDTO.setAugmentedL3operationFte(solutionL3operations
				.getAugmentedL3operationFte());
		return solutionL3OperationsDTO;

	}

	/**
	 * 
	 * Description : It will convert Solution additional Services DTO values
	 * into entity Method Name : convertSolutionL3AddServicesDTO2Enitity Input&
	 * Output:
	 * 
	 * @param solutionL2AddServicesDTO
	 * @return SolutionL2addServices
	 */
	private SolutionL3AddServicesDTO convertSolutionL3AddServicesEnitity2DTO(
			SolutionL3addServices solutionL3AddServices) {
		SolutionL3AddServicesDTO solutionL3AddServicesDTO = new SolutionL3AddServicesDTO();

		solutionL3AddServicesDTO
				.setSolutionL3addServicesId(solutionL3AddServices
						.getSolutionL3addServicesId());

	
		solutionL3AddServicesDTO.setScopeIntegTest(solutionL3AddServices
				.getScopeIntegTest());
		solutionL3AddServicesDTO.setScopeRegreTest(solutionL3AddServices
				.getScopeRegreTest());
		solutionL3AddServicesDTO.setScopeOtherTest(solutionL3AddServices
				.getScopeOtherTest());
		solutionL3AddServicesDTO.setScopeMisceSupp(solutionL3AddServices
				.getScopeMisceSupp());
		solutionL3AddServicesDTO.setScopePhysicalDatabase(solutionL3AddServices
				.getScopePhysicalDatabase());
		solutionL3AddServicesDTO.setScopeServerAndVirt(solutionL3AddServices
				.getScopeServerAndVirt());
		solutionL3AddServicesDTO.setScopeOtherInfra(solutionL3AddServices
				.getScopeOtherInfra());

		solutionL3AddServicesDTO.setUnitIntegTest(solutionL3AddServices
				.getUnitIntegTest());
		solutionL3AddServicesDTO.setUnitRegreTest(solutionL3AddServices
				.getUnitRegreTest());
		solutionL3AddServicesDTO.setUnitOtherTest(solutionL3AddServices
				.getUnitOtherTest());
		solutionL3AddServicesDTO.setUnitMisceSupp(solutionL3AddServices
				.getUnitMisceSupp());
		solutionL3AddServicesDTO.setUnitPhysicalDatabase(solutionL3AddServices
				.getUnitPhysicalDatabase());
		solutionL3AddServicesDTO.setUnitServerAndVirt(solutionL3AddServices
				.getUnitServerAndVirt());
		solutionL3AddServicesDTO.setUnitOtherInfra(solutionL3AddServices
				.getUnitOtherInfra());

		solutionL3AddServicesDTO.setValueIntegTest(solutionL3AddServices
				.getValueIntegTest());
		solutionL3AddServicesDTO.setValueRegreTest(solutionL3AddServices
				.getValueRegreTest());
		solutionL3AddServicesDTO.setValueOtherTest(solutionL3AddServices
				.getValueOtherTest());
		solutionL3AddServicesDTO.setValueMisceSupp(solutionL3AddServices
				.getValueMisceSupp());
		solutionL3AddServicesDTO.setValuePhysicalDatabase(solutionL3AddServices
				.getValuePhysicalDatabase());
		solutionL3AddServicesDTO.setValueServerAndVirt(solutionL3AddServices
				.getValueServerAndVirt());
		solutionL3AddServicesDTO.setValueOtherInfra(solutionL3AddServices
				.getValueOtherInfra());

		solutionL3AddServicesDTO.setFteintegTest(solutionL3AddServices
				.getFteintegTest());
		solutionL3AddServicesDTO.setFteregreTest(solutionL3AddServices
				.getFteregreTest());
		solutionL3AddServicesDTO.setFteotherTest(solutionL3AddServices
				.getFteotherTest());
		solutionL3AddServicesDTO.setFtemisceSupp(solutionL3AddServices
				.getFtemisceSupp());
		solutionL3AddServicesDTO.setFtephysicalDatabase(solutionL3AddServices
				.getFtephysicalDatabase());
		solutionL3AddServicesDTO.setFteserverAndVirt(solutionL3AddServices
				.getFteserverAndVirt());
		solutionL3AddServicesDTO.setFteotherInfra(solutionL3AddServices
				.getFteotherInfra());
		solutionL3AddServicesDTO.setBaseL3serviceFte(solutionL3AddServices
				.getBaseL3serviceFte());
		solutionL3AddServicesDTO.setAugmentedL3serviceFte(solutionL3AddServices
				.getAugmentedL3serviceFte());
		return solutionL3AddServicesDTO;
	}

	public String loadDefaultValuesByServiceId(Integer serviceId)
			throws MSSPException {

		List<VolumetricSolutionDefault> defaultList = volumetricSolutionDAO
				.getDefaultValuesListById(serviceId);
		String defaultValuesString = "";

		switch (serviceId) {
		case 1:
			defaultValuesString = getDefaultValuesForServiceDesk(defaultList);
			break;

		case 2:
			defaultValuesString = getDefaultValuesForL1Operations(defaultList);
			break;

		case 3:
			defaultValuesString = getDefaultValuesForL2Operations(defaultList);
			break;
			
		case 4:
			defaultValuesString = getDefaultValuesForL3Operations(defaultList);
			break;	
			
		case 5:
			defaultValuesString = getDefaultValuesForEnhanAndDevRICEFW(defaultList);
			break;	

		}
		
		
		
		return defaultValuesString;
	}

	/**
	 * 
	 * Description : create string for default values for service desk operation
	 * Method Name : getDefaultValuesForServiceDesk Input& Output:
	 * 
	 * @param defaultList
	 * @return
	 */
	private String getDefaultValuesForServiceDesk(
			List<VolumetricSolutionDefault> defaultList) {
		String transpermonth = "";
		String handlingtime = "";
		for (VolumetricSolutionDefault volSolDef : defaultList) {
			if ("transactionsPerMonth.voice".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				transpermonth = volSolDef.getColumnDefaultValue();
				continue;
			}
			if ("averageHandlingTime.voice".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				handlingtime = volSolDef.getColumnDefaultValue();
				continue;
			}

			if ("transactionsPerMonth.web".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				transpermonth += ";" + volSolDef.getColumnDefaultValue();
				continue;
			}
			if ("averageHandlingTime.web".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				handlingtime += ";" + volSolDef.getColumnDefaultValue();
				continue;
			}

			if ("transactionsPerMonth.email".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				transpermonth += ";" + volSolDef.getColumnDefaultValue();
				continue;
			}
			if ("averageHandlingTime.email".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				handlingtime += ";" + volSolDef.getColumnDefaultValue();
				continue;
			}
			if ("serviceDesk.utilizationPerYear".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				handlingtime += ";" + volSolDef.getColumnDefaultValue();
				continue;
			}
		}
		return transpermonth + "$" + handlingtime;
	}

	/**
	 * 
	 * Description : create string for default values for l1 operation Method
	 * Name : getDefaultValuesForL1Operations Input& Output:
	 * 
	 * @param defaultList
	 * @return
	 */
	private String getDefaultValuesForL1Operations(
			List<VolumetricSolutionDefault> defaultList) {
		// TODO Auto-generated method stub
		String defultValuesStr = "";
		for (VolumetricSolutionDefault volSolDef : defaultList) {
			if ("operationL1.pcofL1incidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"pcofL1incidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("operationL1.pcsimpleIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += " \"pcsimpleIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}

			if ("operationL1.pcmediumIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += " \"pcmediumIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("operationL1.pccomplexIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += " \"pccomplexIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}

			if ("operationL1.hrsSimpleIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += " \"hrsSimpleIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("operationL1.hrsMediumIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += " \"hrsMediumIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("operationL1.hrsComplexIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += " \"hrsComplexIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("operationL1.utilizationperYr".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += " \"utilizationperYr\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
		}
		//System.out.println("defultValuesStr : " + "{" + defultValuesStr + "}");
		return "{" + defultValuesStr + "}";
	}

	/**
	 * 
	 * Description : create string for default values for L2 operation Method
	 * Name : getDefaultValuesForL2Operations Input& Output:
	 * 
	 * @param defaultList
	 * @return
	 */
	private String getDefaultValuesForL2Operations(
			List<VolumetricSolutionDefault> defaultList) {
		// TODO Auto-generated method stub
		String defultValuesStr = "";
		for (VolumetricSolutionDefault volSolDef : defaultList) {
			if ("operationL2.pcofL2incidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"pcofL2incidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("operationL2.pcsimpleIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"pcsimpleIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}

			if ("operationL2.pcmediumIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"pcmediumIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("operationL2.pccomplexIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += " \"pccomplexIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}

			if ("operationL2.hrsSimpleIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"hrsSimpleIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("operationL2.hrsMediumIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"hrsMediumIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("operationL2.hrsComplexIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"hrsComplexIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("operationL2.utilizationperYr".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"utilizationperYr\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
		}
		//System.out.println("defultValuesStr : " + "{" + defultValuesStr + "}");
		return "{" + defultValuesStr + "}";
	}
	
	/**
	 * 
	 * Description : create string for default values for L3 operation Method
	 * Name : getDefaultValuesForL3Operations Input& Output:
	 * 
	 * @param defaultList
	 * @return
	 */
	private String getDefaultValuesForL3Operations(
			List<VolumetricSolutionDefault> defaultList) {
		// TODO Auto-generated method stub
		String defultValuesStr = "";
		for (VolumetricSolutionDefault volSolDef : defaultList) {
			if ("operationL3.pcofL2incidentsConvL3".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"pcofL2incidentsConvL3\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("operationL3.pcsimpleIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"pcsimpleIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}

			if ("operationL3.pcmediumIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"pcmediumIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("operationL3.pccomplexIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += " \"pccomplexIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}

			if ("operationL3.hrsSimpleIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"hrsSimpleIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("operationL3.hrsMediumIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"hrsMediumIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("operationL3.hrsComplexIncidents".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"hrsComplexIncidents\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("operationL3.utilizationperYr".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"utilizationperYr\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
		}
		System.out.println("defultValuesStr : " + "{" + defultValuesStr + "}");
		return "{" + defultValuesStr + "}";
	}

	/**
	 * 
	 * Description : create string for default values for Enhancement & dev Method
	 * Name : getDefaultValuesForEnhanAndDevRICEFW Input& Output:
	 * 
	 * @param defaultList
	 * @return
	 */
	private String getDefaultValuesForEnhanAndDevRICEFW(
			List<VolumetricSolutionDefault> defaultList) {
		// TODO Auto-generated method stub
		String defultValuesStr = "";
		for (VolumetricSolutionDefault volSolDef : defaultList) {
			if ("enhanAndDevRICEFW.qaunSimpleInterface".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"qaunSimpleInterface\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.qaunMediumInterface".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"qaunMediumInterface\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}

			if ("enhanAndDevRICEFW.qaunComplexInterface".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"qaunComplexInterface\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.qaunSimpleExtension".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += " \"qaunSimpleExtension\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}

			if ("enhanAndDevRICEFW.qaunMediumExtension".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"qaunMediumExtension\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.qaunComplexExtension".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"qaunComplexExtension\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.qaunSimpleReport".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"qaunSimpleReport\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.qaunMediumReport".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"qaunMediumReport\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			
			if ("enhanAndDevRICEFW.qaunComplexReport".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"qaunComplexReport\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.qaunSimpleWorkflow".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"qaunSimpleWorkflow\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.qaunMediumWorkflow".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"qaunMediumWorkflow\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.qaunComplexWorkflow".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"qaunComplexWorkflow\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			
			//for efforts 
			if ("enhanAndDevRICEFW.effortSimpleInterface".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"effortSimpleInterface\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.effortMediumInterface".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"effortMediumInterface\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}

			if ("enhanAndDevRICEFW.effortComplexInterface".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"effortComplexInterface\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.effortSimpleExtension".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += " \"effortSimpleExtension\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}

			if ("enhanAndDevRICEFW.effortMediumExtension".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"effortMediumExtension\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.effortComplexExtension".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"effortComplexExtension\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.effortSimpleReport".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"effortSimpleReport\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.effortMediumReport".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"effortMediumReport\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			
			if ("enhanAndDevRICEFW.effortComplexReport".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"effortComplexReport\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.effortSimpleWorkflow".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"effortSimpleWorkflow\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.effortMediumWorkflow".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"effortMediumWorkflow\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevRICEFW.effortComplexWorkflow".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"effortComplexWorkflow\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			
			
			//default values for fixed capacity
			
			
			if ("enhanAndDevFD.pcqaunSimpleCr".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"pcqaunSimpleCr\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevFD.pcqaunMediumCr".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"pcqaunMediumCr\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}

			if ("enhanAndDevFD.pcqaunComplexCr".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"pcqaunComplexCr\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevFD.effortSimpleCr".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += " \"effortSimpleCr\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}

			if ("enhanAndDevFD.effortMediumCr".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"effortMediumCr\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			if ("enhanAndDevFD.effortComplexCr".equalsIgnoreCase(volSolDef
					.getColumnKeyName())) {
				defultValuesStr += "\"effortComplexCr\":\""
						+ volSolDef.getColumnDefaultValue() + "\",";
				continue;
			}
			
		}
		System.out.println("defultValuesStr : " + "{" + defultValuesStr + "}");
		return "{" + defultValuesStr + "}";
	}
	
	
	@Override
	public SolutionL1OperationsForm loadSolutionL1OperationsDetails(
			Integer solId, Integer solDimentionAttId) throws MSSPException {

		SolutionL1OperationsForm solutionL1OperationsForm = new SolutionL1OperationsForm();

		List<SolutionL1operations> l1OppList = volumetricSolutionDAO
				.loadSolutionL1Operations(solId, solDimentionAttId);

		if (l1OppList.size() > 0) {
			solutionL1OperationsForm
					.setSolutionL1OperationsDTO(convertSolutionL1OperationsEntity2DTO(l1OppList
							.get(0)));
		}

		List<SolutionL1addServices> l1AddOppList = volumetricSolutionDAO
				.loadSolutionL1addServices(solId, solDimentionAttId);

		if (l1AddOppList.size() > 0) {
			solutionL1OperationsForm
					.setSolutionL1AddServicesDTO(convertSolutionL1AddServicesEnitity2DTO(l1AddOppList
							.get(0)));
		}
		return solutionL1OperationsForm;
	}

	/**
	 * 
	 * Description : It will convert Solution operation Services entity values
	 * into DTO Method Name : convertSolutionL1OperationsEntity2DTO Input&
	 * Output:
	 * 
	 * @param solutionL1operations
	 * @return
	 */
	private SolutionL1OperationsDTO convertSolutionL1OperationsEntity2DTO(
			SolutionL1operations solutionL1operations) {
		SolutionL1OperationsDTO solutionL1OperationsDTO = new SolutionL1OperationsDTO();

		solutionL1OperationsDTO.setSolutionL1operationsId(solutionL1operations
				.getSolutionL1operationsId());

		SupportWindowMatrixDTO swm = new SupportWindowMatrixDTO();
		swm.setSupportWindowMatrixId(solutionL1operations
				.getSupportWindowMatrix().getSupportWindowMatrixId());
		solutionL1OperationsDTO.setSupportWindowMatrixDTO(swm);

		solutionL1OperationsDTO.setPcofL1incidents(solutionL1operations
				.getPcofL1incidents());
		solutionL1OperationsDTO.setTotalL1incidentsperYr(solutionL1operations
				.getTotalL1incidentsperYr());
		solutionL1OperationsDTO.setPcsimpleIncidents(solutionL1operations
				.getPcsimpleIncidents());
		solutionL1OperationsDTO.setPcmediumIncidents(solutionL1operations
				.getPcmediumIncidents());
		solutionL1OperationsDTO.setPccomplexIncidents(solutionL1operations
				.getPccomplexIncidents());
		solutionL1OperationsDTO.setHrsSimpleIncidents(solutionL1operations
				.getHrsSimpleIncidents());
		solutionL1OperationsDTO.setHrsMediumIncidents(solutionL1operations
				.getHrsMediumIncidents());
		solutionL1OperationsDTO.setHrsComplexIncidents(solutionL1operations
				.getHrsComplexIncidents());
		solutionL1OperationsDTO.setAvgResolutionTimeHrs(solutionL1operations
				.getAvgResolutionTimeHrs());
		solutionL1OperationsDTO.setAnualHrsSpent(solutionL1operations
				.getAnualHrsSpent());
		solutionL1OperationsDTO.setUtilizationperYr(solutionL1operations
				.getUtilizationperYr());
		solutionL1OperationsDTO.setBaseL1operationFte(solutionL1operations
				.getBaseL1operationFte());
		solutionL1OperationsDTO.setAugmentedL1operationFte(solutionL1operations
				.getAugmentedL1operationFte());
		return solutionL1OperationsDTO;

	}

	/**
	 * 
	 * Description : It will convert Solution additional Services Entity values
	 * into DTO Method Name : convertSolutionL1AddServicesEnitity2DTO Input&
	 * Output:
	 * 
	 * @param solutionL1AddServicesDTO
	 * @param solutionID
	 * @return
	 */
	private SolutionL1AddServicesDTO convertSolutionL1AddServicesEnitity2DTO(
			SolutionL1addServices solutionL1addServices) {
		SolutionL1AddServicesDTO solutionL1AddServicesDTO = new SolutionL1AddServicesDTO();

		solutionL1AddServicesDTO
				.setSolutionL1addServicesId(solutionL1addServices
						.getSolutionL1addServicesId());

		solutionL1AddServicesDTO.setScopeAccessMgmt(solutionL1addServices
				.getScopeAccessMgmt());
		solutionL1AddServicesDTO.setScopeRequestMgmt(solutionL1addServices
				.getScopeRequestMgmt());
		solutionL1AddServicesDTO.setScopeEventMgmt(solutionL1addServices
				.getScopeEventMgmt());
		solutionL1AddServicesDTO.setScopeL1billOperation(solutionL1addServices
				.getScopeL1billOperation());
		solutionL1AddServicesDTO.setScopeL1preventive(solutionL1addServices
				.getScopeL1preventive());
		solutionL1AddServicesDTO.setScopeL1change(solutionL1addServices
				.getScopeL1change());
		solutionL1AddServicesDTO.setScopeL1routine(solutionL1addServices
				.getScopeL1routine());
		solutionL1AddServicesDTO.setScopeL1performance(solutionL1addServices
				.getScopeL1performance());
		solutionL1AddServicesDTO.setEffortAccessMgmt(solutionL1addServices
				.getEffortAccessMgmt());
		solutionL1AddServicesDTO.setEffortRequestMgmt(solutionL1addServices
				.getEffortRequestMgmt());
		solutionL1AddServicesDTO.setEffortEventMgmt(solutionL1addServices
				.getEffortEventMgmt());
		solutionL1AddServicesDTO.setEffortL1billOperation(solutionL1addServices
				.getEffortL1billOperation());
		solutionL1AddServicesDTO.setEffortL1preventive(solutionL1addServices
				.getEffortL1preventive());
		solutionL1AddServicesDTO.setEffortL1change(solutionL1addServices
				.getEffortL1change());
		solutionL1AddServicesDTO.setEffortL1routine(solutionL1addServices
				.getEffortL1routine());
		solutionL1AddServicesDTO.setEffortL1performance(solutionL1addServices
				.getEffortL1performance());
		solutionL1AddServicesDTO.setFteaccessMgmt(solutionL1addServices
				.getFteaccessMgmt());
		solutionL1AddServicesDTO.setFterequestMgmt(solutionL1addServices
				.getFterequestMgmt());
		solutionL1AddServicesDTO.setFteeventMgmt(solutionL1addServices
				.getFteeventMgmt());
		solutionL1AddServicesDTO.setFtel1billOperation(solutionL1addServices
				.getFtel1billOperation());
		solutionL1AddServicesDTO.setFtel1preventive(solutionL1addServices
				.getFtel1preventive());
		solutionL1AddServicesDTO.setFtel1change(solutionL1addServices
				.getFtel1change());
		solutionL1AddServicesDTO.setFtel1routine(solutionL1addServices
				.getFtel1routine());
		solutionL1AddServicesDTO.setFtel1performance(solutionL1addServices
				.getFtel1performance());
		solutionL1AddServicesDTO.setBaseL1serviceFte(solutionL1addServices
				.getBaseL1serviceFte());
		solutionL1AddServicesDTO.setAugmentedL1serviceFte(solutionL1addServices
				.getAugmentedL1serviceFte());
		return solutionL1AddServicesDTO;
	}

	@Override
	public SolutionL2OperationsForm loadSolutionL2OperationsDetails(
			Integer solId, Integer solDimentionAttId) throws MSSPException {

		SolutionL2OperationsForm solutionL2OperationsForm = new SolutionL2OperationsForm();

		List<SolutionL2operations> l2OppList = volumetricSolutionDAO
				.loadSolutionL2Operations(solId, solDimentionAttId);

		if (l2OppList.size() > 0) {
			solutionL2OperationsForm
					.setSolutionL2OperationsDTO(convertSolutionL2OperationsEntity2DTO(l2OppList
							.get(0)));
		}

		List<SolutionL2addServices> l2AddOppList = volumetricSolutionDAO
				.loadSolutionL2addServices(solId, solDimentionAttId);

		if (l2AddOppList.size() > 0) {
			solutionL2OperationsForm
					.setSolutionL2AddServicesDTO(convertSolutionL2AddServicesEnitity2DTO(l2AddOppList
							.get(0)));
		}
		return solutionL2OperationsForm;
	}

	/**
	 * 
	 * Description : It will convert Solution operation Services entity values
	 * into DTO Method Name : convertSolutionL1OperationsEntity2DTO Input&
	 * Output:
	 * 
	 * @param solutionL1operations
	 * @return
	 */
	private SolutionL2OperationsDTO convertSolutionL2OperationsEntity2DTO(
			SolutionL2operations solutionL2operations) {
		SolutionL2OperationsDTO solutionL2OperationsDTO = new SolutionL2OperationsDTO();

		solutionL2OperationsDTO.setSolutionL2operationsId(solutionL2operations
				.getSolutionL2operationsId());

		SupportWindowMatrixDTO swm = new SupportWindowMatrixDTO();
		swm.setSupportWindowMatrixId(solutionL2operations
				.getSupportWindowMatrix().getSupportWindowMatrixId());
		solutionL2OperationsDTO.setSupportWindowMatrixDTO(swm);

		solutionL2OperationsDTO.setPcofL2incidents(solutionL2operations
				.getPcofL2incidents());
		solutionL2OperationsDTO.setTotalL2incidentsperYr(solutionL2operations
				.getTotalL2incidentsperYr());
		solutionL2OperationsDTO.setPcsimpleIncidents(solutionL2operations
				.getPcsimpleIncidents());
		solutionL2OperationsDTO.setPcmediumIncidents(solutionL2operations
				.getPcmediumIncidents());
		solutionL2OperationsDTO.setPccomplexIncidents(solutionL2operations
				.getPccomplexIncidents());
		solutionL2OperationsDTO.setHrsSimpleIncidents(solutionL2operations
				.getHrsSimpleIncidents());
		solutionL2OperationsDTO.setHrsMediumIncidents(solutionL2operations
				.getHrsMediumIncidents());
		solutionL2OperationsDTO.setHrsComplexIncidents(solutionL2operations
				.getHrsComplexIncidents());
		solutionL2OperationsDTO.setAvgResolutionTimeHrs(solutionL2operations
				.getAvgResolutionTimeHrs());
		solutionL2OperationsDTO.setAnualHrsSpent(solutionL2operations
				.getAnualHrsSpent());
		solutionL2OperationsDTO.setUtilizationperYr(solutionL2operations
				.getUtilizationperYr());
		solutionL2OperationsDTO.setBaseL2operationFte(solutionL2operations
				.getBaseL2operationFte());
		solutionL2OperationsDTO.setAugmentedL2operationFte(solutionL2operations
				.getAugmentedL2operationFte());
		return solutionL2OperationsDTO;

	}

	/**
	 * 
	 * Description : It will convert Solution additional Services Entity values
	 * into DTO Method Name : convertSolutionL2AddServicesEnitity2DTO Input&
	 * Output:
	 * 
	 * @param solutionL2AddServicesDTO
	 * @param solutionID
	 * @return
	 */
	private SolutionL2AddServicesDTO convertSolutionL2AddServicesEnitity2DTO(
			SolutionL2addServices solutionL2addServices) {
		SolutionL2AddServicesDTO solutionL2AddServicesDTO = new SolutionL2AddServicesDTO();

		solutionL2AddServicesDTO
				.setSolutionL2addServicesId(solutionL2addServices
						.getSolutionL2addServicesId());

		solutionL2AddServicesDTO.setScopeAvailMgmt(solutionL2addServices
				.getScopeAvailMgmt());
		solutionL2AddServicesDTO.setScopeSecurityMgmt(solutionL2addServices
				.getScopeSecurityMgmt());
		solutionL2AddServicesDTO.setScopeCapacityMgmt(solutionL2addServices
				.getScopeCapacityMgmt());
		solutionL2AddServicesDTO.setScopeL2billOperation(solutionL2addServices
				.getScopeL2billOperation());
		solutionL2AddServicesDTO.setScopeL2preventive(solutionL2addServices
				.getScopeL2preventive());
		solutionL2AddServicesDTO.setScopeL2change(solutionL2addServices
				.getScopeL2change());
		solutionL2AddServicesDTO.setScopeL2routine(solutionL2addServices
				.getScopeL2routine());
		solutionL2AddServicesDTO.setScopeL2performance(solutionL2addServices
				.getScopeL2performance());
		solutionL2AddServicesDTO.setScopeL2releaseDev(solutionL2addServices
				.getScopeL2releaseDev());
		solutionL2AddServicesDTO.setEffortAvailMgmt(solutionL2addServices
				.getEffortAvailMgmt());
		solutionL2AddServicesDTO.setEffortSecurityMgmt(solutionL2addServices
				.getEffortSecurityMgmt());
		solutionL2AddServicesDTO.setEffortCapacityMgmt(solutionL2addServices
				.getEffortCapacityMgmt());
		solutionL2AddServicesDTO.setEffortL2billOperation(solutionL2addServices
				.getEffortL2billOperation());
		solutionL2AddServicesDTO.setEffortL2preventive(solutionL2addServices
				.getEffortL2preventive());
		solutionL2AddServicesDTO.setEffortL2change(solutionL2addServices
				.getEffortL2change());
		solutionL2AddServicesDTO.setEffortL2routine(solutionL2addServices
				.getEffortL2routine());
		solutionL2AddServicesDTO.setEffortL2performance(solutionL2addServices
				.getEffortL2performance());
		solutionL2AddServicesDTO.setEffortL2releaseDev(solutionL2addServices
				.getEffortL2releaseDev());
		solutionL2AddServicesDTO.setFteavailMgmt(solutionL2addServices
				.getFteavailMgmt());
		solutionL2AddServicesDTO.setFtesecurityMgmt(solutionL2addServices
				.getFtesecurityMgmt());
		solutionL2AddServicesDTO.setFtecapacityMgmt(solutionL2addServices
				.getFtecapacityMgmt());
		solutionL2AddServicesDTO.setFtel2billOperation(solutionL2addServices
				.getFtel2billOperation());
		solutionL2AddServicesDTO.setFtel2preventive(solutionL2addServices
				.getFtel2preventive());
		solutionL2AddServicesDTO.setFtel2change(solutionL2addServices
				.getFtel2change());
		solutionL2AddServicesDTO.setFtel2routine(solutionL2addServices
				.getFtel2routine());
		solutionL2AddServicesDTO.setFtel2performance(solutionL2addServices
				.getFtel2performance());
		solutionL2AddServicesDTO.setFtel2releaseDev(solutionL2addServices
				.getFtel2releaseDev());
		solutionL2AddServicesDTO.setBaseL2serviceFte(solutionL2addServices
				.getBaseL2serviceFte());
		solutionL2AddServicesDTO.setAugmentedL2serviceFte(solutionL2addServices
				.getAugmentedL2serviceFte());
		return solutionL2AddServicesDTO;
	}
	
	@Override
	public boolean saveMiscData(SolutionOtherMiscForm solutionOtherMiscForm,
			Integer solId, Integer solDimentionAttId, Integer oppScopeId)
			throws MSSPException {
		
		boolean saveFlag = false;

		SolutionOtherMisc solutionOtherMisc = null;
		Float[] fte = solutionOtherMiscForm.getFte();
		Integer[] jobRoles = solutionOtherMiscForm.getJobRoleId();
		String[] skill = solutionOtherMiscForm.getSkill();
		String[] remarks = solutionOtherMiscForm.getRemarks();
		List<SolutionOtherMiscDTO> miscDatalist = solutionOtherMiscForm
				.getSolutionOtherMiscList();

		Solution solution = new Solution();
		JobRole jobRole = null;
		solution.setSolutionId(solId);
		SolutionApproachDimension solutionApproachDimension = null;

		for (int i = 0; i < solutionOtherMiscForm.getFte().length; i++) {
			solutionOtherMisc = new SolutionOtherMisc();
			jobRole = new JobRole();
			jobRole.setJobRoleId(jobRoles[i]);
			if (solDimentionAttId != null) {
				solutionApproachDimension = new SolutionApproachDimension();
				solutionApproachDimension
						.setSolutionApproachDimensionId(solDimentionAttId);
				solutionOtherMisc
						.setSolutionApproachDimension(solutionApproachDimension);
			}
			if (solutionOtherMiscForm.getSolutionOtherMiscList() != null)
				solutionOtherMisc
						.setSolutionOtherMiscId(solutionOtherMiscForm
								.getSolutionOtherMiscList().size() > i ? solutionOtherMiscForm
								.getSolutionOtherMiscList().get(i)
								.getSolutionOtherMiscId()
								: null);

			solutionOtherMisc.setFte(fte[i]);
			if(remarks.length>0){
				solutionOtherMisc.setRemarks(remarks[i]);
			}
			if(skill.length>0){
				solutionOtherMisc.setSkill(skill[i]);
			}
			solutionOtherMisc.setJobRole(jobRole);
			solutionOtherMisc.setSolution(solution);
			solutionOtherMisc
					.setOpportunityScope(getOpportunityScope(oppScopeId));

			volumetricSolutionDAO.saveMiscData(solutionOtherMisc);

		}

		return saveFlag;
	}

	@Override
	public List<SolutionOtherMiscDTO> loadOtherMiscDTO(Integer solId,
			Integer solDimensionAttId)  throws MSSPException {
		
		List<SolutionOtherMiscDTO> solutionOtherMiscDTOs = new ArrayList<SolutionOtherMiscDTO>();
		SolutionOtherMiscDTO solutionOtherMiscDTO = null;
		JobRoleDTO jobRoleDTO = null;
		List<SolutionOtherMisc> solutionOtherMiscs = volumetricSolutionDAO.loadOtherMisc(solId,solDimensionAttId);
		
		for(SolutionOtherMisc item : solutionOtherMiscs)
		{
			solutionOtherMiscDTO = new SolutionOtherMiscDTO();
			jobRoleDTO = new JobRoleDTO();
			
			jobRoleDTO.setJobRoleId(item.getJobRole().getJobRoleId());
			
			solutionOtherMiscDTO.setSolutionOtherMiscId(item.getSolutionOtherMiscId());
			solutionOtherMiscDTO.setFte(item.getFte());
			solutionOtherMiscDTO.setRemarks(item.getRemarks());
			solutionOtherMiscDTO.setSkill(item.getSkill());
			solutionOtherMiscDTO.setJobRoleDTO(jobRoleDTO);
						
			solutionOtherMiscDTOs.add(solutionOtherMiscDTO);
			
		}
		
		return solutionOtherMiscDTOs;
	}

	@Override
	public void saveSolutionEnhanDev(Integer solutionId,
			EnhancementAndDevForm enhancementAndDevForm, Integer dimensionId, Integer oppScopeId)
			throws MSSPException {
		// TODO Auto-generated method stub
		
		SolutionEnhanDevRicefwDTO solutionEnhanDevRicefwDTO = enhancementAndDevForm.getSolutionEnhanDevRicefwDTO();
				

		SolutionEnhanDevFixedCapacityDTO solutionEnhanDevFixedDepositeDTO = enhancementAndDevForm
				.getSolutionEnhanDevFixedCapacityDTO();
		
		// Save SolutionL2Operation data
		if (solutionEnhanDevRicefwDTO !=null) {
			SolutionEnhanDevRicefw solutionEnhanDevRicefw = convertSolutionEnhanDevRicefwDTODTO2Enitity(
					solutionEnhanDevRicefwDTO, solutionId, dimensionId, oppScopeId);
			
			volumetricSolutionDAO.saveSolutionEnhanDev(
					solutionEnhanDevRicefw);

		}
		if (solutionEnhanDevFixedDepositeDTO !=null) {
			SolutionEnhanDevFixedCapacity solutionEnhanDevFixedDeposite = convertsolutionEnhanDevFixedDepositeDTODTO2Enitity(
					solutionEnhanDevFixedDepositeDTO, solutionId, dimensionId, oppScopeId);
			
			volumetricSolutionDAO.savesolutionEnhanDevFixedDeposite(
					solutionEnhanDevFixedDeposite);

		}
		
	}
	
	public SolutionEnhanDevRicefw convertSolutionEnhanDevRicefwDTODTO2Enitity(
			SolutionEnhanDevRicefwDTO solutionEnhanDevRicefwDTO,
			Integer solutionId, Integer dimensionId,Integer oppScopeId) {
		
		SolutionEnhanDevRicefw solutionEnhanDevRicefw = new SolutionEnhanDevRicefw();
		
		BeanUtils.copyProperties(solutionEnhanDevRicefwDTO, solutionEnhanDevRicefw);
		solutionEnhanDevRicefw.setSolution(setSolutionById(solutionId));
		
		solutionEnhanDevRicefw
		.setSolutionApproachDimension(getSolutionApproachDimension(dimensionId));
		solutionEnhanDevRicefw.setOpportunityScope(getOpportunityScope(oppScopeId));
		
		return solutionEnhanDevRicefw;

	}
	
	public SolutionEnhanDevFixedCapacity convertsolutionEnhanDevFixedDepositeDTODTO2Enitity(
			SolutionEnhanDevFixedCapacityDTO solutionEnhanDevFixedDepositeDTO,
			Integer solutionId, Integer dimensionId,Integer oppScopeId) {
		
		SolutionEnhanDevFixedCapacity solutionEnhanDevFixedDeposite = new SolutionEnhanDevFixedCapacity();
		
		BeanUtils.copyProperties(solutionEnhanDevFixedDepositeDTO, solutionEnhanDevFixedDeposite);
		solutionEnhanDevFixedDeposite.setSolution(setSolutionById(solutionId));
		
		solutionEnhanDevFixedDeposite
		.setSolutionApproachDimension(getSolutionApproachDimension(dimensionId));
		solutionEnhanDevFixedDeposite.setOpportunityScope(getOpportunityScope(oppScopeId));
		
		return solutionEnhanDevFixedDeposite;

	}

	@Override
	public EnhancementAndDevForm loadSolutionEnhancementAndDevDetails(
			Integer solId, Integer solDimentionAttId)throws MSSPException {
		EnhancementAndDevForm enhancementAndDevForm = new EnhancementAndDevForm();

		List<SolutionEnhanDevRicefw> list1 = volumetricSolutionDAO
				.loadSolutionSolutionEnhanDevRicefw(solId, solDimentionAttId);
		
		List<SolutionEnhanDevFixedCapacity> list2 = volumetricSolutionDAO
				.loadSolutionEnhanDevFixedDeposite(solId, solDimentionAttId);

		if (list1.size() > 0) {
			enhancementAndDevForm.setSolutionEnhanDevRicefwDTO(convertSolutionEnhanDevRice2DTO(list1
							.get(0)));
		}
		
		if (list2.size() > 0) {
			enhancementAndDevForm.setSolutionEnhanDevFixedCapacityDTO(convertSolutionEnhanDevFixedDeposite2DTO(list2
							.get(0)));
		}

		
		return enhancementAndDevForm;
	}

	private SolutionEnhanDevRicefwDTO convertSolutionEnhanDevRice2DTO(
			SolutionEnhanDevRicefw solutionEnhanDevRicefw) {
		// TODO Auto-generated method stub
		
		SolutionEnhanDevRicefwDTO solutionEnhanDevRicefwDTO = new SolutionEnhanDevRicefwDTO();
		
		BeanUtils.copyProperties(solutionEnhanDevRicefw, solutionEnhanDevRicefwDTO);
		
		return solutionEnhanDevRicefwDTO;

		
	}

	private SolutionEnhanDevFixedCapacityDTO convertSolutionEnhanDevFixedDeposite2DTO(
			SolutionEnhanDevFixedCapacity solutionEnhanDevFixedDeposite) {
		// TODO Auto-generated method stub
		
		SolutionEnhanDevFixedCapacityDTO solutionEnhanDevFixedDepositDTO = new SolutionEnhanDevFixedCapacityDTO();
		
		BeanUtils.copyProperties(solutionEnhanDevFixedDeposite, solutionEnhanDevFixedDepositDTO);
	//	solutionEnhanDevRicefw.setSolution(setSolutionById(solutionId));
		
		return solutionEnhanDevFixedDepositDTO;

		
	}

	@Override
	public List<SolutionOtherMiscDTO> loadGovAndPMODTO(Integer solId,
			Integer solDimentionAttId) throws MSSPException {

		
		List<SolutionOtherMiscDTO> listGovAndPMODTO = new ArrayList<SolutionOtherMiscDTO>();
		SolutionOtherMiscDTO solutionOtherMiscDTO = null;
		JobRoleDTO jobRoleDTO = null;
		List<SolutionGovAndPmo>  listGovAndPMO = volumetricSolutionDAO.loadGovAndPMO(solId,solDimentionAttId);
		
		for(SolutionGovAndPmo item : listGovAndPMO)
		{
			solutionOtherMiscDTO = new SolutionOtherMiscDTO();
			jobRoleDTO = new JobRoleDTO();
			
			jobRoleDTO.setJobRoleId(item.getJobRole().getJobRoleId());
			
			solutionOtherMiscDTO.setSolutionOtherMiscId(item.getSolutionGovAndPmoid());
			solutionOtherMiscDTO.setFte(item.getFte());
			solutionOtherMiscDTO.setRemarks(item.getRemarks());
			/*solutionOtherMiscDTO.setSkill(item.getSkill());*/
			solutionOtherMiscDTO.setJobRoleDTO(jobRoleDTO);
						
			listGovAndPMODTO.add(solutionOtherMiscDTO);
			
		}
		
		return listGovAndPMODTO;
	
	}

	@Override
	public void saveGovernancePmo (
			SolutionOtherMiscForm solutionOtherMiscForm, Integer solId,
			Integer solDimentionAttId, Integer oppScopeId) throws MSSPException{
		
		SolutionGovAndPmo solutionOtherMisc = null;
		Float []fte = solutionOtherMiscForm.getFte();
		Integer []jobRoles = solutionOtherMiscForm.getJobRoleId();
		String []remarks = solutionOtherMiscForm.getRemarks();
		//List<SolutionOtherMiscDTO> miscDatalist = solutionOtherMiscForm.getSolutionOtherMiscList();
				
		
		JobRole jobRole = null;
	
		SolutionApproachDimension solutionApproachDimension = null;
		
		for(int i = 0; i<solutionOtherMiscForm.getFte().length;i++)
		{
			solutionOtherMisc = new SolutionGovAndPmo();
			jobRole =  new JobRole();
			jobRole.setJobRoleId(jobRoles[i]);
			if(solDimentionAttId != null){
				solutionApproachDimension = new SolutionApproachDimension();			
				solutionApproachDimension.setSolutionApproachDimensionId(solDimentionAttId);
				solutionOtherMisc.setSolutionApproachDimension(solutionApproachDimension);
			}
			  if (solutionOtherMiscForm.getSolutionOtherMiscList() != null)
				  solutionOtherMisc.setSolutionGovAndPmoid(solutionOtherMiscForm.getSolutionOtherMiscList()
							.size() > i ? solutionOtherMiscForm.getSolutionOtherMiscList().get(i).getSolutionOtherMiscId() : null);
			
			solutionOtherMisc.setFte(fte[i]);
			solutionOtherMisc.setRemarks(remarks[i]);
			/*solutionOtherMisc.setSkill(skill[i]);*/
			solutionOtherMisc.setJobRole(jobRole);
			solutionOtherMisc.setSolution(setSolutionById(solId));
			solutionOtherMisc.setOpportunityScope(getOpportunityScope(oppScopeId));
			solutionOtherMisc.setOpportunityScope(getOpportunityScope(oppScopeId));
			volumetricSolutionDAO.saveGovernancePmo(solutionOtherMisc);
			
		}
	
	}

	@Override
	public E2EProcessQualityDTO loadE2EProcessQualityDTO(Integer solId)
			throws MSSPException {
		// TODO Auto-generated method stub
		E2EProcessQuality e2EProcessQuality =  volumetricSolutionDAO.loadE2EProcessQualityDTO(solId);
		
		E2EProcessQualityDTO e2EProcessQualityDTO = new E2EProcessQualityDTO();
		if(e2EProcessQuality != null){
			BeanUtils.copyProperties(e2EProcessQuality, e2EProcessQualityDTO);
		}
		
		return e2EProcessQualityDTO;
	}

	@Override
	public void saveE2EProcessQuality(Integer solutionId,
			E2EProcessQualityDTO e2eProcessQualityDTO) throws MSSPException {
		// TODO Auto-generated method stub
		
		E2EProcessQuality e2EProcessQuality = new E2EProcessQuality();
	
		BeanUtils.copyProperties(e2eProcessQualityDTO, e2EProcessQuality);
		
		OpportunityScope op = new OpportunityScope();
		op.setOpportunityScopeId(e2eProcessQualityDTO.getOpportunityScopeDTO().getOpportunityScopeId());
		e2EProcessQuality.setOpportunityScope(op);
		e2EProcessQuality.setSolution(setSolutionById(solutionId));
		volumetricSolutionDAO.saveObject(e2EProcessQuality);
	}
	
}
