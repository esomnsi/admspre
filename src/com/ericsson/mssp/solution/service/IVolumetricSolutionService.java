/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.service
* File name       		    :  ICalculateVolumetricFTEService.java
* Description				:	<To Do>
* Author, Date & Release	:	Jan 9, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Jan 9, 2013  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.service;

import java.util.List;

import com.ericsson.mssp.common.dto.E2EProcessQualityDTO;
import com.ericsson.mssp.common.dto.JobRoleDTO;
import com.ericsson.mssp.common.dto.SolutionOtherMiscDTO;
import com.ericsson.mssp.common.dto.SolutionServiceDeskDTO;
import com.ericsson.mssp.common.dto.SupportWindowMatrixDTO;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.forms.EnhancementAndDevForm;
import com.ericsson.mssp.solution.forms.SolutionL1OperationsForm;
import com.ericsson.mssp.solution.forms.SolutionL2OperationsForm;
import com.ericsson.mssp.solution.forms.SolutionL3OperationsForm;
import com.ericsson.mssp.solution.forms.SolutionOtherMiscForm;

/**
 * @author egaivij
 *
 */
public interface IVolumetricSolutionService {
	
	/**
	 * 
	 * Description	: get all Support window matrix 
	 * Method Name	: getAllSupportWindowMatrix
	 * Input& Output:
	 * 	@return
	 * 	@throws MSSPException
	 */
	public List<SupportWindowMatrixDTO> getAllSupportWindowMatrix() throws MSSPException;
	/**
	 * 
	 * Description	: TODO
	 * Method Name	: saveServiceDesk
	 * Input& Output:
	 * 	@param serviceDeskList
	 * 	@param solutionId
	 * 	@param supportWindowMatrixId
	 * 	@throws MSSPException
	 */
	public void saveServiceDesk(List<SolutionServiceDeskDTO> serviceDeskList,
			Integer solutionId, Integer supportWindowMatrixId, Integer solDimentionAttId, Integer oppScopeId)
			throws MSSPException;
	/**
	 * 
	 * Description	: TODO
	 * Method Name	: getServicDesk
	 * Input& Output:
	 * 	@param solutionId
	 * 	@return
	 * 	@throws MSSPException
	 */
	public List<SolutionServiceDeskDTO> getServicDesk(int solutionId)
			throws MSSPException;
	/**
	 * 
	 * Description	: TODO
	 * Method Name	: loadServiceDeskListBySolutionId
	 * Input& Output:
	 * 	@param solId
	 * 	@param solDimentionAttId
	 * 	@return
	 * @throws MSSPException 
	 */
	public List<SolutionServiceDeskDTO> loadServiceDeskListBySolutionId(
			Integer solId, Integer solDimentionAttId) throws MSSPException;
	/**
	 * 
	 * Description	: To save Level 1 operation data
	 * Method Name	: saveSolutionL1Operations
	 * Input& Output:
	 * 	@param solutionID
	 * 	@param solutionL1OperationsForm
	 * 	@throws MSSPException
	 */
	void saveSolutionL1Operations(Integer solutionID,
		SolutionL1OperationsForm solutionL1OperationsForm, Integer dimensionId, Integer oppScopeId)
		throws MSSPException;
	/**
	 * 
	 * Description	: To Save L2 Operation data
	 * Method Name	: saveSolutionL2Operations
	 * Input& Output:
	 * 	@param solutionID
	 * 	@param solutionL2OperationsForm
	 * 	@throws MSSPException
	 */
	void saveSolutionL2Operations(Integer solutionID,
		SolutionL2OperationsForm solutionL2OperationsForm, Integer dimensionId, Integer oppScopeId)
		throws MSSPException;
	
	/**
	 * 
	 * Description	: To Save L3 Operation data
	 * Method Name	: saveSolutionL3Operations
	 * Input& Output:
	 * 	@param solutionID
	 * 	@param solutionL2OperationsForm
	 * 	@throws MSSPException
	 */
	void saveSolutionL3Operations(Integer solutionID,
		SolutionL3OperationsForm solutionL3OperationsForm , Integer dimensionId, Integer oppScopeId )
		throws MSSPException;
	
	/**
	 * 
	 * Description	: To load L3 Operation data
	 * Method Name	: loadSolutionL3OperationsDetails
	 * Input& Output:
	 * 	@param solutionID
	 * 	@param solutionL2OperationsForm
	 * 	@throws MSSPException
	 */
	public SolutionL3OperationsForm loadSolutionL3OperationsDetails(Integer solId,
			Integer solDimentionAttId) throws MSSPException;
	
	/**
	 * 
	 * Description	: load default data for service desk
	 * Method Name	: loadDefaultServiceDesk
	 * Input& Output:
	 * 	@param serviceId
	 * 	@return
	 * 	@throws MSSPException
	 */
	public String loadDefaultValuesByServiceId(Integer serviceId)throws MSSPException;
	
	/**
	 * 
	 * Description	: load L1 Operation data
	 * Method Name	: loadSolutionL1OperationsBySolutionId
	 * Input& Output:
	 * 	@param solId
	 * 	@param solDimentionAttId
	 * 	@return
	 * 	@throws MSSPException
	 */
	public SolutionL1OperationsForm loadSolutionL1OperationsDetails(
			Integer solId, Integer solDimentionAttId) throws MSSPException;
	
	/**
	 * 
	 * Description	: load L2 Operation data
	 * Method Name	: loadSolutionL2OperationsBySolutionId
	 * Input& Output:
	 * 	@param solId
	 * 	@param solDimentionAttId
	 * 	@return
	 * 	@throws MSSPException
	 */
	public SolutionL2OperationsForm loadSolutionL2OperationsDetails(
			Integer solId, Integer solDimentionAttId) throws MSSPException;
	
	

	public boolean saveMiscData(SolutionOtherMiscForm solutionOtherMiscForm,
			Integer solId, Integer solDimentionAttId, Integer oppScopeId)
			throws MSSPException;

	public List<SolutionOtherMiscDTO> loadOtherMiscDTO(Integer solId,
			Integer solDimensionAttId) throws MSSPException;

	/**
	 * 
	 * Description	: TODO
	 * Method Name	: saveSolutionEnhanDev
	 * Input& Output:
	 * 	@param solutionId
	 * 	@param enhancementAndDevForm
	 * 	@param dimensionId
	 * 	@throws MSSPException
	 */
	public void saveSolutionEnhanDev(Integer solutionId,
			EnhancementAndDevForm enhancementAndDevForm, Integer dimensionId, Integer oppScopeId)throws MSSPException;
	
	public EnhancementAndDevForm loadSolutionEnhancementAndDevDetails(
			Integer solId, Integer solDimentionAttId) throws MSSPException;
	
	/**
	 * 
	 * Description	: load Gov And PMO  data
	 * Method Name	: loadGovAndPMODTO
	 * Input& Output:
	 * 	@param solId
	 * 	@param solDimentionAttId
	 * 	@return
	 * 	@throws MSSPException
	 */
	public List<SolutionOtherMiscDTO> loadGovAndPMODTO(
			Integer solId, Integer solDimentionAttId) throws MSSPException;
	
	public void saveGovernancePmo(SolutionOtherMiscForm solutionOtherMiscForm, Integer solId, Integer solDimentionAttId, Integer oppScopeId) throws MSSPException;
	
	public E2EProcessQualityDTO loadE2EProcessQualityDTO(Integer solId)throws MSSPException;
	public void saveE2EProcessQuality(Integer solutionId,
			E2EProcessQualityDTO e2eProcessQualityDTO)throws MSSPException;
}
