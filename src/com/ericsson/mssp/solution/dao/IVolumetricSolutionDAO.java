/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.dao
 * File name       		    :  IVolumetricSolutionDAO.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Jan 23, 20132013
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Jan 23, 2013  	   |eruvwyn   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.dao;

import java.util.List;

import com.ericsson.mssp.common.dao.IBaseDAO;
import com.ericsson.mssp.common.dto.SolutionServiceDeskDTO;
import com.ericsson.mssp.common.entity.E2EProcessQuality;
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
import com.ericsson.mssp.common.entity.VolumetricSolutionDefault;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.forms.SolutionOtherMiscForm;

/**
 * @author eruvwyn
 * 
 */
public interface IVolumetricSolutionDAO extends IBaseDAO {
	
	public List<SupportWindowMatrix> getAllSupportWindowMatrix() throws MSSPException;
	
	void saveServiceDesk(SolutionServiceDesk serviceDesk)throws MSSPException;

	List<SolutionServiceDeskDTO> getServicDesk(int solutionId)throws MSSPException;
	
	public List<SolutionServiceDesk> loadServiceDeskListBySolutionId(
			Integer solId, Integer solDimentionAttId)throws MSSPException;
	
	SolutionApproachDimension getSolutionApproachDimensionBySolutionID(
			Integer solutionID)throws MSSPException;
	
	public void saveSolutionL1Operations(
			SolutionL1operations solutionL1Operations,
			SolutionL1addServices solutionL1AddServices) throws MSSPException;
	
	public void saveSolutionL2Operations(
			SolutionL2operations solutionL2Operations,
			SolutionL2addServices solutionL2AddServices) throws MSSPException;
	
	public void saveSolutionL3Operations(
			SolutionL3operations solutionL3Operations,
			SolutionL3addServices solutionL3AddServices) throws MSSPException;

	public List<SolutionL3operations> loadSolutionL3Operations(Integer solId,
			Integer solDimentionAttId) throws MSSPException;
	
	public List<SolutionL3addServices> loadSolutionL3addServices(Integer solId,
			Integer solDimentionAttId) throws MSSPException;
	
	public List<VolumetricSolutionDefault> getDefaultValuesListById(Integer serviceId)throws MSSPException;

	public List<SolutionL1operations> loadSolutionL1Operations(Integer solId,
			Integer solDimentionAttId) throws MSSPException;
	
	public List<SolutionL1addServices> loadSolutionL1addServices(Integer solId,
			Integer solDimentionAttId) throws MSSPException;
	
	public List<SolutionL2operations> loadSolutionL2Operations(Integer solId,
			Integer solDimentionAttId) throws MSSPException;
	
	public List<SolutionL2addServices> loadSolutionL2addServices(Integer solId,
			Integer solDimentionAttId) throws MSSPException;

	
	public void saveMiscData(SolutionOtherMisc solutionOtherMisc) throws MSSPException;
	
	public List<SolutionOtherMisc> loadOtherMisc(Integer solId, Integer solDimentionAttId) throws MSSPException;
	
	public void saveSolutionEnhanDev(
			SolutionEnhanDevRicefw solutionEnhanDevRicefw
			) throws MSSPException;

	public List<SolutionEnhanDevRicefw> loadSolutionSolutionEnhanDevRicefw(
			Integer solId, Integer solDimentionAttId)throws MSSPException;

	public void savesolutionEnhanDevFixedDeposite(
			SolutionEnhanDevFixedCapacity solutionEnhanDevFixedDeposite)throws MSSPException;

	public List<SolutionEnhanDevFixedCapacity> loadSolutionEnhanDevFixedDeposite(
			Integer solId, Integer solDimentionAttId)throws MSSPException;
	
	public List<SolutionGovAndPmo> loadGovAndPMO(Integer solId,
			Integer solDimentionAttId) throws DAOException;

	public void saveGovernancePmo (
			SolutionGovAndPmo solutionGovAndPmo) throws MSSPException;

	public E2EProcessQuality loadE2EProcessQualityDTO(Integer solId)throws MSSPException;
}
