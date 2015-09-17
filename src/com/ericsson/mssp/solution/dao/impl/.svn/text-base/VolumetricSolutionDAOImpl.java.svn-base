/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.dao.impl
* File name       		    :  VolumetricSolutionDAOImpl.java
* Description				:	<To Do>
* Author, Date & Release	:	Jan 23, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Jan 23, 2013  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.dto.SolutionOtherMiscDTO;
import com.ericsson.mssp.common.dto.SolutionServiceDeskDTO;
import com.ericsson.mssp.common.entity.E2EProcessQuality;
import com.ericsson.mssp.common.entity.JobRole;
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
import com.ericsson.mssp.common.entity.VolumetricSolutionDefault;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.dao.IVolumetricSolutionDAO;
import com.ericsson.mssp.solution.forms.SolutionOtherMiscForm;

/**
 * @author eruvwyn
 *
 */
@Repository
public class VolumetricSolutionDAOImpl extends BaseDAOImpl implements IVolumetricSolutionDAO {

	@Override
	public void saveServiceDesk(SolutionServiceDesk serviceDesk)throws MSSPException {
		saveObject(serviceDesk);
	}
	@Override
	public List<SupportWindowMatrix> getAllSupportWindowMatrix() throws MSSPException{
		return getObjects(SupportWindowMatrix.class);
	}
	
	@Override
	public List<SolutionServiceDeskDTO> getServicDesk(int solutionId) throws MSSPException{
		
		return getHibernateTemplate().findByNamedQuery("get.serviceDesk");
	}

	@Override
	public List<SolutionServiceDesk> loadServiceDeskListBySolutionId(
			Integer solId, Integer solDimentionAttId)throws MSSPException {
		// TODO Auto-generated method stub

		String query = "from SolutionServiceDesk where solutionID=" + solId;
		if (solDimentionAttId != null) {
			query += " and solutionApproachDimensionID=" + solDimentionAttId;
		}
		List<SolutionServiceDesk> list = getHibernateTemplate().find(query);
		return list;
			
	}
	
	@Override
	public SolutionApproachDimension getSolutionApproachDimensionBySolutionID(
			Integer solutionID)throws MSSPException {
		List<SolutionApproachDimension> solutionApproachDimensionList = getHibernateTemplate()
				.find(" from SolutionApproachDimension where SolutionID = "
						+ solutionID);
		return null != solutionApproachDimensionList
				&& solutionApproachDimensionList.size() > 0 ? solutionApproachDimensionList
				.get(0) : null;
	}
	
	@Override
	public void saveSolutionL1Operations(
			SolutionL1operations solutionL1Operations,
			SolutionL1addServices solutionL1AddServices) throws MSSPException {

		saveObject(solutionL1Operations);
		saveObject(solutionL1AddServices);

	}
	
	@Override   
	public void saveSolutionL2Operations(
			SolutionL2operations solutionL2Operations,
			SolutionL2addServices solutionL2AddServices) throws MSSPException {

		saveObject(solutionL2Operations);
		saveObject(solutionL2AddServices);

	}
	
	@Override
	public void saveSolutionL3Operations(
			SolutionL3operations solutionL3Operations,
			SolutionL3addServices solutionL3AddServices) throws MSSPException {

		saveObject(solutionL3Operations);
		saveObject(solutionL3AddServices);

	}
	   
	   
	   
	@Override
	public List<SolutionL3operations> loadSolutionL3Operations(Integer solId,
			Integer solDimentionAttId) throws MSSPException {
		// TODO Auto-generated method stub

		String query = "from SolutionL3operations where solutionID=" + solId;
		if (solDimentionAttId != null) {
			query += " and solutionApproachDimensionID=" + solDimentionAttId;
		}
		List<SolutionL3operations> list = getHibernateTemplate().find(query);
		return list;
	}
	@Override   
	public List<SolutionL3addServices> loadSolutionL3addServices(Integer solId,
			Integer solDimentionAttId) throws MSSPException {
		String query = "from SolutionL3addServices where solutionID=" + solId;
		if (solDimentionAttId != null) {
			query += " and solutionApproachDimensionID=" + solDimentionAttId;
		}
		List<SolutionL3addServices> list = getHibernateTemplate().find(query);

		return list;

	}
	
	@Override
	public List<SolutionL1operations> loadSolutionL1Operations(Integer solId,
			Integer solDimentionAttId) throws MSSPException {
		// TODO Auto-generated method stub

		String query = "from SolutionL1operations where solutionID=" + solId;
		if (solDimentionAttId != null) {
			query += " and solutionApproachDimensionID=" + solDimentionAttId;
		}
		List<SolutionL1operations> list = getHibernateTemplate().find(query);
		return list;
	}
	@Override   
	public List<SolutionL1addServices> loadSolutionL1addServices(Integer solId,
			Integer solDimentionAttId) throws MSSPException {
		String query = "from SolutionL1addServices where solutionID=" + solId;
		if (solDimentionAttId != null) {
			query += " and solutionApproachDimensionID=" + solDimentionAttId;
		}
		List<SolutionL1addServices> list = getHibernateTemplate().find(query);

		return list;

	}
	

	@Override
	public List<VolumetricSolutionDefault> getDefaultValuesListById(
			Integer serviceId) throws MSSPException {
		// TODO Auto-generated method stub
		String query = "from VolumetricSolutionDefault where serviceScopeID=" + serviceId;
		List<VolumetricSolutionDefault> list = getHibernateTemplate().find(query);

		return list;

	}
	
	@Override
	public List<SolutionL2operations> loadSolutionL2Operations(Integer solId,
			Integer solDimentionAttId) throws MSSPException {
		// TODO Auto-generated method stub

		String query = "from SolutionL2operations where solutionID=" + solId;
		if (solDimentionAttId != null) {
			query += " and solutionApproachDimensionID=" + solDimentionAttId;
		}
		List<SolutionL2operations> list = getHibernateTemplate().find(query);
		return list;
	}
	@Override
	public List<SolutionL2addServices> loadSolutionL2addServices(Integer solId,
			Integer solDimentionAttId) throws MSSPException {
		String query = "from SolutionL2addServices where solutionID=" + solId;
		if (solDimentionAttId != null) {
			query += " and solutionApproachDimensionID=" + solDimentionAttId;
		}
		List<SolutionL2addServices> list = getHibernateTemplate().find(query);

		return list;

	}
	

	
	@Override
	public void saveMiscData(SolutionOtherMisc solutionOtherMisc) throws MSSPException{
		saveObject(solutionOtherMisc);

	}

	@Override
	public List<SolutionOtherMisc> loadOtherMisc(Integer solId,
			Integer solDimentionAttId) throws MSSPException {
		
		List<SolutionOtherMisc> solutionOtherMiscs;
		
		String query = "from SolutionOtherMisc where solutionID="+solId;
		
		if(null != solDimentionAttId)
		{
			query += " and solutionApproachDimensionID="+solDimentionAttId;
		}
		logger.info("query for saving misc data : " + query);
		solutionOtherMiscs = getHibernateTemplate().find(query);
		
		return solutionOtherMiscs;
	}
	@Override
	public void saveSolutionEnhanDev(
			SolutionEnhanDevRicefw solutionEnhanDevRicefw) throws MSSPException {
		saveObject(solutionEnhanDevRicefw);
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<SolutionEnhanDevRicefw> loadSolutionSolutionEnhanDevRicefw(
			Integer solId, Integer solDimentionAttId) throws MSSPException{
		String query = "from SolutionEnhanDevRicefw where solutionID=" + solId;
		if (solDimentionAttId != null) {
			query += " and solutionApproachDimensionID=" + solDimentionAttId;
		}
		List<SolutionEnhanDevRicefw> list = getHibernateTemplate().find(query);
		return list;
	}
	@Override
	public List<SolutionEnhanDevFixedCapacity> loadSolutionEnhanDevFixedDeposite(
			Integer solId, Integer solDimentionAttId)throws MSSPException {
		String query = "from SolutionEnhanDevFixedCapacity where solutionID=" + solId;
		if (solDimentionAttId != null) {
			query += " and solutionApproachDimensionID=" + solDimentionAttId;
		}
		List<SolutionEnhanDevFixedCapacity> list = getHibernateTemplate().find(query);
		return list;
	}
	
	@Override
	public void savesolutionEnhanDevFixedDeposite(
			SolutionEnhanDevFixedCapacity solutionEnhanDevFixedDeposite) throws MSSPException{
		// TODO Auto-generated method stub
		saveObject(solutionEnhanDevFixedDeposite);
	}
	@Override
	public List<SolutionGovAndPmo> loadGovAndPMO(Integer solId,
			Integer solDimentionAttId) throws DAOException {
		
		List<SolutionGovAndPmo> listGovAndPMO;
		
		String query = "from SolutionGovAndPmo where solutionID="+solId;
		
		if(null != solDimentionAttId)
		{
			query += " and solutionApproachDimensionID="+solDimentionAttId;
		}					
		logger.info("query for saving misc data : " + query);
		listGovAndPMO = getHibernateTemplate().find(query);
		
		return listGovAndPMO;
	}
	@Override
	public void saveGovernancePmo(SolutionGovAndPmo solutionGovAndPmo) throws MSSPException{

			saveObject(solutionGovAndPmo);
	}
	
	@Override
	public E2EProcessQuality loadE2EProcessQualityDTO(Integer solId)
			throws MSSPException {
		List<E2EProcessQuality> list;
		E2EProcessQuality e2EProcessQuality= null;
		String query = "from E2EProcessQuality where solutionID="+solId;
		
		logger.info("query for saving misc data : " + query);
		list = getHibernateTemplate().find(query);
		
		if(list.size()>0){
			e2EProcessQuality = list.get(0);
		}
		
		return e2EProcessQuality;
	}
}
