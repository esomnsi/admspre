/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.service.impl
 * File name       		    :  SolutionCloneImp.java
 * Description				:	Used for Solution Cloning
 * Author, Date & Release	:	Apr 24, 20132013
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Apr 24, 2013  	   |edassri   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.entity.AdditionalFTE;
import com.ericsson.mssp.common.entity.Apacomplexity;
import com.ericsson.mssp.common.entity.Apaweightage;
import com.ericsson.mssp.common.entity.CheckLists;
import com.ericsson.mssp.common.entity.EfficiencyLever;
import com.ericsson.mssp.common.entity.GenericTestingInputs;
import com.ericsson.mssp.common.entity.GenericTestingOverhead;
import com.ericsson.mssp.common.entity.LocationBreakup;
import com.ericsson.mssp.common.entity.LocationPyramid;
import com.ericsson.mssp.common.entity.NonLabourCost;
import com.ericsson.mssp.common.entity.ProductivityLever;
import com.ericsson.mssp.common.entity.RegressionLever;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionADR;
import com.ericsson.mssp.common.entity.SolutionAPA;
import com.ericsson.mssp.common.entity.SolutionApproachDimension;
import com.ericsson.mssp.common.entity.SolutionApprover;
import com.ericsson.mssp.common.entity.SolutionComplexity;
import com.ericsson.mssp.common.entity.SolutionEnhanDevFixedCapacity;
import com.ericsson.mssp.common.entity.SolutionEnhanDevRicefw;
import com.ericsson.mssp.common.entity.SolutionGovAndPmo;
import com.ericsson.mssp.common.entity.SolutionL1addServices;
import com.ericsson.mssp.common.entity.SolutionL1operations;
import com.ericsson.mssp.common.entity.SolutionL2addServices;
import com.ericsson.mssp.common.entity.SolutionL2operations;
import com.ericsson.mssp.common.entity.SolutionL3addServices;
import com.ericsson.mssp.common.entity.SolutionL3operations;
import com.ericsson.mssp.common.entity.SolutionLever;
import com.ericsson.mssp.common.entity.SolutionOtherMisc;
import com.ericsson.mssp.common.entity.SolutionServiceDesk;
import com.ericsson.mssp.common.entity.SolutionTestingAsAservice;
import com.ericsson.mssp.common.entity.StaffingPlan;
import com.ericsson.mssp.common.entity.StartUpFTE;
import com.ericsson.mssp.common.entity.TNTDetail;
import com.ericsson.mssp.common.entity.TNTPartitionDate;
import com.ericsson.mssp.common.entity.TestEffReduction;
import com.ericsson.mssp.common.entity.TestingService;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.dao.ISolutionDAO;
import com.ericsson.mssp.solution.service.ISolutionClone;

/**
 * @author edassri
 * 
 */
@Service
public class SolutionCloneImpl implements ISolutionClone {
    @Autowired
    private ISolutionDAO solutionDAO;

    public Solution getSolutionById(Integer solID) throws MSSPException {
	return (Solution) solutionDAO.getObject(Solution.class, solID);
    }

    @Override
    public Integer cloneOfTheSolutionBySolutionIdUserID(Integer solutionID,
	    String userName) throws MSSPException {
	Integer newSolutionID = null;
	Solution solution = getSolutionById(solutionID);
	solution.setSolutionId(null);
	solution.setCreatedBy(userName);
	newSolutionID = solutionDAO.saveObjectReturnId(solution);
	Solution newSolution = new Solution();
	newSolution.setSolutionId(newSolutionID);
	createCloneOfAPACmplx(solutionID, newSolution);
	createCloneOfAPAWeight(solutionID, newSolution);
	createCloneOfAdditionalFTE(solutionID, newSolution);
	createCloneOfCheckLists(solutionID, newSolution);
	createCloneOfEfficiencyLever(solutionID, newSolution);
	// FTEStaging& Summary not required on absence will be created
	// createCloneOfFTEStaging(solutionID,newSolution);
	createCloneOfGenericTestingInputs(solutionID, newSolution);
	createCloneOfGenericTestingOverhead(solutionID, newSolution);
	createCloneOfNonLaborCost(solutionID, newSolution);
	createCloneOfRegressionLever(solutionID, newSolution);
	createCloneOfSolutionADR(solutionID, newSolution);
	createCloneOfSolutionAPA(solutionID, newSolution);
	createCloneOfSolutionApprover(solutionID, newSolution);
	createCloneOfSolutionComplxty(solutionID, newSolution);
	createCloneOfSolutionLeverItsDependents(solutionID, newSolution);
	createCloneOfStaffingPlan(solutionID, newSolution);
	createCloneOfStartUpFTE(solutionID, newSolution);
	createCloneOfTnTDetails(solutionID, newSolution);
	createCloneOfTnTPartitionDate(solutionID, newSolution);
	createCloneOfTestEffReduction(solutionID, newSolution);
	createCloneOfTestingService(solutionID, newSolution);
	createCloneOfSolutionApproachDimensionItsDependents(solutionID,
		newSolution);
	// apacomplexity.setSol
	return newSolutionID;
    }

    private void createCloneOfSolutionApproachDimensionItsDependents(
	    Integer solutionID, Solution newSolution) throws MSSPException {
	List<Object> solutionApproachDimensionList = solutionDAO
		.getEntitiesObjBySolutionID("from SolutionApproachDimension where solutionID="
			+ solutionID);
	for (Object apaObj : solutionApproachDimensionList) {
	    SolutionApproachDimension solutionApproachDimension = (SolutionApproachDimension) apaObj;
	    solutionApproachDimension.setSolutionApproachDimensionId(null);
	    solutionApproachDimension.setSolution(newSolution);
	    Integer newSolAppDimensionID = solutionDAO
		    .saveObjectReturnId(solutionApproachDimension);
	    solutionApproachDimension
		    .setSolutionApproachDimensionId(newSolAppDimensionID);
	    cloneSolutionApprochDependentTables(solutionApproachDimension,
		    solutionID, newSolution);
	}
	// If solution approach not exist then save all as clone
	cloneSolutionApprochDependentTables(null, solutionID, newSolution);
    }

    private void cloneSolutionApprochDependentTables(
	    SolutionApproachDimension solutionApproachDimension,
	    Integer solutionID, Solution newSolution) throws MSSPException {
	cloneOfSolutionEnhnDevFixdDiposite(solutionApproachDimension,
		solutionID, newSolution);
	cloneOfSolutionEnhanDevRicefw(solutionApproachDimension, solutionID,
		newSolution);
	cloneOfSolutionGovAndPMO(solutionApproachDimension, solutionID,
		newSolution);
	cloneOfSolutionL1AddServices(solutionApproachDimension, solutionID,
		newSolution);
	cloneOfSolutionL1Operations(solutionApproachDimension, solutionID,
		newSolution);
	cloneOfSolutionL2AddServices(solutionApproachDimension, solutionID,
		newSolution);
	cloneOfSolutionL2Operations(solutionApproachDimension, solutionID,
		newSolution);
	cloneOfSolutionOtherMisc(solutionApproachDimension, solutionID,
		newSolution);
	cloneOfSolutionL3Operations(solutionApproachDimension, solutionID,
		newSolution);
	cloneOfSolutionL3AddServices(solutionApproachDimension, solutionID,
		newSolution);
	cloneOfSolutionServiceDesk(solutionApproachDimension, solutionID,
		newSolution);
	cloneOfSolutionTestingAsAService(solutionApproachDimension, solutionID,
		newSolution);
    }

    private void cloneOfSolutionTestingAsAService(
	    SolutionApproachDimension solutionApproachDimension,
	    Integer solutionID, Solution newSolution) throws MSSPException {
	String hql = null != solutionApproachDimension ? "from SolutionTestingAsAservice where SolutionID="
		+ solutionID
		+ " and SolutionApproachDimensionID="
		+ solutionApproachDimension.getSolutionApproachDimensionId()
		: "from SolutionTestingAsAservice where SolutionID="
			+ solutionID
			+ " and SolutionApproachDimensionID is null";
	List<Object> solutionTestingAsAServiceList = solutionDAO
		.getEntitiesObjBySolutionID(hql);
	SolutionTestingAsAservice solutionTestingAsAservice = null != solutionTestingAsAServiceList
		& solutionTestingAsAServiceList.size() > 0 ? (SolutionTestingAsAservice) solutionTestingAsAServiceList
		.get(0) : null;
	if (null != solutionTestingAsAservice) {
	    solutionTestingAsAservice.setSolutionTestingAsAserviceId(null);
	    solutionTestingAsAservice.setSolution(newSolution);
	    solutionTestingAsAservice
		    .setSolutionApproachDimension(solutionApproachDimension);
	    solutionDAO.saveObject(solutionTestingAsAservice);
	}

    }

    private void cloneOfSolutionServiceDesk(
	    SolutionApproachDimension solutionApproachDimension,
	    Integer solutionID, Solution newSolution) throws MSSPException {
	String hql = null != solutionApproachDimension ? "from SolutionServiceDesk where SolutionID="
		+ solutionID
		+ " and SolutionApproachDimensionID="
		+ solutionApproachDimension.getSolutionApproachDimensionId()
		: "from SolutionServiceDesk where SolutionID=" + solutionID
			+ " and SolutionApproachDimensionID is null";
	List<Object> entities = solutionDAO.getEntitiesObjBySolutionID(hql);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    SolutionServiceDesk obj = (SolutionServiceDesk) apaObj;
	    obj.setServiceDeskId(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);
    }

    private void cloneOfSolutionL3Operations(
	    SolutionApproachDimension solutionApproachDimension,
	    Integer solutionID, Solution newSolution) throws MSSPException {
	String hql = null != solutionApproachDimension ? "from SolutionL3operations where SolutionID="
		+ solutionID
		+ " and SolutionApproachDimensionID="
		+ solutionApproachDimension.getSolutionApproachDimensionId()
		: "from SolutionL3operations where SolutionID=" + solutionID
			+ " and SolutionApproachDimensionID is null";
	List<Object> solutionL3operationsList = solutionDAO
		.getEntitiesObjBySolutionID(hql);
	SolutionL3operations solutionL3operations = null != solutionL3operationsList
		& solutionL3operationsList.size() > 0 ? (SolutionL3operations) solutionL3operationsList
		.get(0) : null;
	if (null != solutionL3operations) {
	    solutionL3operations.setSolutionL3operationsId(null);
	    solutionL3operations.setSolution(newSolution);
	    solutionL3operations
		    .setSolutionApproachDimension(solutionApproachDimension);
	    solutionDAO.saveObject(solutionL3operations);
	}

    }

    private void cloneOfSolutionL3AddServices(
	    SolutionApproachDimension solutionApproachDimension,
	    Integer solutionID, Solution newSolution) throws MSSPException {
	String hql = null != solutionApproachDimension ? "from SolutionL3addServices where SolutionID="
		+ solutionID
		+ " and SolutionApproachDimensionID="
		+ solutionApproachDimension.getSolutionApproachDimensionId()
		: "from SolutionL3addServices where SolutionID=" + solutionID
			+ " and SolutionApproachDimensionID is null";
	List<Object> solutionL3addServicesList = solutionDAO
		.getEntitiesObjBySolutionID(hql);
	SolutionL3addServices solutionL3addServices = null != solutionL3addServicesList
		& solutionL3addServicesList.size() > 0 ? (SolutionL3addServices) solutionL3addServicesList
		.get(0) : null;
	if (null != solutionL3addServices) {
	    solutionL3addServices.setSolutionL3addServicesId(null);
	    solutionL3addServices.setSolution(newSolution);
	    solutionL3addServices
		    .setSolutionApproachDimension(solutionApproachDimension);
	    solutionDAO.saveObject(solutionL3addServices);
	}
    }

    private void cloneOfSolutionOtherMisc(
	    SolutionApproachDimension solutionApproachDimension,
	    Integer solutionID, Solution newSolution) throws MSSPException {
	String hql = null != solutionApproachDimension ? "from SolutionOtherMisc where SolutionID="
		+ solutionID
		+ " and SolutionApproachDimensionID="
		+ solutionApproachDimension.getSolutionApproachDimensionId()
		: "from SolutionOtherMisc where SolutionID=" + solutionID
			+ " and SolutionApproachDimensionID is null";
	List<Object> solutionOtherMiscList = solutionDAO
		.getEntitiesObjBySolutionID(hql);
	SolutionOtherMisc solutionOtherMisc = null != solutionOtherMiscList
		& solutionOtherMiscList.size() > 0 ? (SolutionOtherMisc) solutionOtherMiscList
		.get(0) : null;
	if (null != solutionOtherMisc) {
	    solutionOtherMisc.setSolutionOtherMiscId(null);
	    solutionOtherMisc.setSolution(newSolution);
	    solutionOtherMisc
		    .setSolutionApproachDimension(solutionApproachDimension);
	    solutionDAO.saveObject(solutionOtherMisc);
	}

    }

    private void cloneOfSolutionL2AddServices(
	    SolutionApproachDimension solutionApproachDimension,
	    Integer solutionID, Solution newSolution) throws MSSPException {
	String hql = null != solutionApproachDimension ? "from SolutionL2addServices where SolutionID="
		+ solutionID
		+ " and SolutionApproachDimensionID="
		+ solutionApproachDimension.getSolutionApproachDimensionId()
		: "from SolutionL2addServices where SolutionID=" + solutionID
			+ " and SolutionApproachDimensionID is null";
	List<Object> solutionL2addServicesList = solutionDAO
		.getEntitiesObjBySolutionID(hql);
	SolutionL2addServices solutionL2addServices = null != solutionL2addServicesList
		& solutionL2addServicesList.size() > 0 ? (SolutionL2addServices) solutionL2addServicesList
		.get(0) : null;
	if (null != solutionL2addServices) {
	    solutionL2addServices.setSolutionL2addServicesId(null);
	    solutionL2addServices.setSolution(newSolution);
	    solutionL2addServices
		    .setSolutionApproachDimension(solutionApproachDimension);
	    solutionDAO.saveObject(solutionL2addServices);
	}
    }

    private void cloneOfSolutionL2Operations(
	    SolutionApproachDimension solutionApproachDimension,
	    Integer solutionID, Solution newSolution) throws MSSPException {
	String hql = null != solutionApproachDimension ? "from SolutionL2operations where SolutionID="
		+ solutionID
		+ " and SolutionApproachDimensionID="
		+ solutionApproachDimension.getSolutionApproachDimensionId()
		: "from SolutionL2operations where SolutionID=" + solutionID
			+ " and SolutionApproachDimensionID is null";
	List<Object> solutionL2operationsList = solutionDAO
		.getEntitiesObjBySolutionID(hql);
	SolutionL2operations solutionL2operations = null != solutionL2operationsList
		& solutionL2operationsList.size() > 0 ? (SolutionL2operations) solutionL2operationsList
		.get(0) : null;
	if (null != solutionL2operations) {
	    solutionL2operations.setSolutionL2operationsId(null);
	    solutionL2operations.setSolution(newSolution);
	    solutionL2operations
		    .setSolutionApproachDimension(solutionApproachDimension);
	    solutionDAO.saveObject(solutionL2operations);
	}

    }

    private void cloneOfSolutionL1Operations(
	    SolutionApproachDimension solutionApproachDimension,
	    Integer solutionID, Solution newSolution) throws MSSPException {
	String hql = null != solutionApproachDimension ? "from SolutionL1operations where SolutionID="
		+ solutionID
		+ " and SolutionApproachDimensionID="
		+ solutionApproachDimension.getSolutionApproachDimensionId()
		: "from SolutionL1operations where SolutionID=" + solutionID
			+ " and SolutionApproachDimensionID is null";
	List<Object> solutionL1operationsList = solutionDAO
		.getEntitiesObjBySolutionID(hql);
	SolutionL1operations solutionL1operations = null != solutionL1operationsList
		& solutionL1operationsList.size() > 0 ? (SolutionL1operations) solutionL1operationsList
		.get(0) : null;
	if (null != solutionL1operations) {
	    solutionL1operations.setSolutionL1operationsId(null);
	    solutionL1operations.setSolution(newSolution);
	    solutionL1operations
		    .setSolutionApproachDimension(solutionApproachDimension);
	    solutionDAO.saveObject(solutionL1operations);
	}

    }

    private void cloneOfSolutionL1AddServices(
	    SolutionApproachDimension solutionApproachDimension,
	    Integer solutionID, Solution newSolution) throws MSSPException {
	String hql = null != solutionApproachDimension ? "from SolutionL1addServices where SolutionID="
		+ solutionID
		+ " and SolutionApproachDimensionID="
		+ solutionApproachDimension.getSolutionApproachDimensionId()
		: "from SolutionL1addServices where SolutionID=" + solutionID
			+ " and SolutionApproachDimensionID is null";
	List<Object> solutionL1addServicesList = solutionDAO
		.getEntitiesObjBySolutionID(hql);
	SolutionL1addServices solutionL1operations = null != solutionL1addServicesList
		& solutionL1addServicesList.size() > 0 ? (SolutionL1addServices) solutionL1addServicesList
		.get(0) : null;
	if (null != solutionL1operations) {
	    solutionL1operations.setSolutionL1addServicesId(null);
	    solutionL1operations.setSolution(newSolution);
	    solutionL1operations
		    .setSolutionApproachDimension(solutionApproachDimension);
	    solutionDAO.saveObject(solutionL1operations);
	}

    }

    private void cloneOfSolutionGovAndPMO(
	    SolutionApproachDimension solutionApproachDimension,
	    Integer solutionID, Solution newSolution) throws MSSPException {
	String hql = null != solutionApproachDimension ? "from SolutionGovAndPmo where SolutionID="
		+ solutionID
		+ " and SolutionApproachDimensionID="
		+ solutionApproachDimension.getSolutionApproachDimensionId()
		: "from SolutionGovAndPmo where SolutionID=" + solutionID
			+ " and SolutionApproachDimensionID is null";
	List<Object> solutionGovAndPmoList = solutionDAO
		.getEntitiesObjBySolutionID(hql);
	SolutionGovAndPmo solutionGovAndPmo = null != solutionGovAndPmoList
		& solutionGovAndPmoList.size() > 0 ? (SolutionGovAndPmo) solutionGovAndPmoList
		.get(0) : null;
	if (null != solutionGovAndPmo) {
	    solutionGovAndPmo.setSolutionGovAndPmoid(null);
	    solutionGovAndPmo.setSolution(newSolution);
	    solutionGovAndPmo
		    .setSolutionApproachDimension(solutionApproachDimension);
	    solutionDAO.saveObject(solutionGovAndPmo);
	}

    }

    private void cloneOfSolutionEnhanDevRicefw(
	    SolutionApproachDimension solutionApproachDimension,
	    Integer solutionID, Solution newSolution) throws MSSPException {
	String hql = null != solutionApproachDimension ? "from SolutionEnhanDevRicefw where SolutionID="
		+ solutionID
		+ " and SolutionApproachDimensionID="
		+ solutionApproachDimension.getSolutionApproachDimensionId()
		: "from SolutionEnhanDevRicefw where SolutionID=" + solutionID
			+ " and SolutionApproachDimensionID is null";
	List<Object> solutionEnhanDevRicefwList = solutionDAO
		.getEntitiesObjBySolutionID(hql);
	SolutionEnhanDevRicefw solutionEnhanDevRicefw = null != solutionEnhanDevRicefwList
		& solutionEnhanDevRicefwList.size() > 0 ? (SolutionEnhanDevRicefw) solutionEnhanDevRicefwList
		.get(0) : null;
	if (null != solutionEnhanDevRicefw) {
	    solutionEnhanDevRicefw.setSolutionEnhanDevRicefwId(null);
	    solutionEnhanDevRicefw.setSolution(newSolution);
	    solutionEnhanDevRicefw
		    .setSolutionApproachDimension(solutionApproachDimension);
	    solutionDAO.saveObject(solutionEnhanDevRicefw);
	}

    }

    private void cloneOfSolutionEnhnDevFixdDiposite(
	    SolutionApproachDimension solutionApproachDimension,
	    Integer solutionID, Solution newSolution) throws MSSPException {
	try {
	    String hql = null != solutionApproachDimension ? "from SolutionEnhanDevFixedCapacity where SolutionID="
		    + solutionID
		    + " and SolutionApproachDimensionID="
		    + solutionApproachDimension
			    .getSolutionApproachDimensionId()
		    : "from SolutionEnhanDevFixedCapacity where SolutionID="
			    + solutionID
			    + " and SolutionApproachDimensionID is null";
	    List<Object> solutionEnhanDevFixedCapacityList = solutionDAO
		    .getEntitiesObjBySolutionID(hql);
	    SolutionEnhanDevFixedCapacity solutionEnhanDevFixedCapacity = null != solutionEnhanDevFixedCapacityList
		    & solutionEnhanDevFixedCapacityList.size() > 0 ? (SolutionEnhanDevFixedCapacity) solutionEnhanDevFixedCapacityList
		    .get(0) : null;
	    if (null != solutionEnhanDevFixedCapacity) {
		solutionEnhanDevFixedCapacity
			.setSolutionEnhanDevFixedCapacityId(null);
		solutionEnhanDevFixedCapacity.setSolution(newSolution);
		solutionEnhanDevFixedCapacity
			.setSolutionApproachDimension(solutionApproachDimension);
		solutionDAO.saveObject(solutionEnhanDevFixedCapacity);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void createCloneOfTestingService(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from TestingService where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    TestingService obj = (TestingService) apaObj;
	    obj.setTestingServiceId(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfTnTDetails(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from TNTDetail where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    TNTDetail obj = (TNTDetail) apaObj;
	    obj.setTntdetailId(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfTestEffReduction(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from TestEffReduction where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    TestEffReduction obj = (TestEffReduction) apaObj;
	    obj.setTestEffReductionId(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfTnTPartitionDate(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from TNTPartitionDate where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    TNTPartitionDate obj = (TNTPartitionDate) apaObj;
	    obj.setTntpartitionDateId(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfStartUpFTE(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from StartUpFTE where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    StartUpFTE obj = (StartUpFTE) apaObj;
	    obj.setStartUpFteid(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfStaffingPlan(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from StaffingPlan where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    StaffingPlan obj = (StaffingPlan) apaObj;
	    obj.setStaffingPlanId(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfSolutionLeverItsDependents(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from SolutionLever where solutionID="
			+ solutionID);
	List<Object> solutionLeverList = new ArrayList<Object>(entities.size());
	Integer oldSolutionLeverID = null;
	for (Object apaObj : solutionLeverList) {
	    SolutionLever solutionLever = (SolutionLever) apaObj;
	    oldSolutionLeverID = solutionLever.getSolutionLeverId();
	    solutionLever.setSolutionLeverId(null);
	    solutionLever.setSolution(newSolution);
	    Integer newSolutionLeverID = solutionDAO
		    .saveObjectReturnId(solutionLever);
	    solutionLever.setSolutionLeverId(newSolutionLeverID);
	    cloneOfLocationPyramid(oldSolutionLeverID, solutionLever);
	    cloneOfLocationBreakUp(oldSolutionLeverID, solutionLever);
	    cloneOfProductivityLever(oldSolutionLeverID, solutionLever);
	}
    }

    private void cloneOfProductivityLever(Integer oldSolutionLeverID,
	    SolutionLever solutionLever) throws MSSPException {
	String hql = "from ProductivityLever where SolutionLeverID="
		+ oldSolutionLeverID;
	List<Object> locationBreakupList = solutionDAO
		.getEntitiesObjBySolutionID(hql);
	for (Object productivityLeverObjs : locationBreakupList) {
	    ProductivityLever productivityLever = (ProductivityLever) productivityLeverObjs;
	    productivityLever.setProductivityLeverId(null);
	    productivityLever.setSolutionLever(solutionLever);
	    solutionDAO.saveObject(productivityLever);
	}
    }

    private void cloneOfLocationBreakUp(Integer oldSolutionLeverID,
	    SolutionLever solutionLever) throws MSSPException {
	String hql = "from LocationBreakup where SolutionLeverID="
		+ oldSolutionLeverID;
	List<Object> locationBreakupList = solutionDAO
		.getEntitiesObjBySolutionID(hql);
	for (Object obj : locationBreakupList) {
	    LocationBreakup locationBreakup = (LocationBreakup) obj;
	    locationBreakup.setLocationBreakupId(null);
	    locationBreakup.setSolutionLever(solutionLever);
	    solutionDAO.saveObject(locationBreakup);
	}
    }

    private void cloneOfLocationPyramid(Integer oldSolutionLeverID,
	    SolutionLever solutionLever) throws MSSPException {
	String hql = "from LocationPyramid where SolutionLeverID="
		+ oldSolutionLeverID;
	List<Object> locationPyramidList = solutionDAO
		.getEntitiesObjBySolutionID(hql);
	for (Object obj : locationPyramidList) {
	    LocationPyramid locationPyramid = (LocationPyramid) obj;
	    locationPyramid.setLocationPyramidId(null);
	    locationPyramid.setSolutionLever(solutionLever);
	    solutionDAO.saveObject(locationPyramid);
	}
    }

    private void createCloneOfSolutionApprover(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from SolutionApprover where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    SolutionApprover obj = (SolutionApprover) apaObj;
	    obj.setSolutionApproverId(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfSolutionComplxty(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from SolutionComplexity where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    SolutionComplexity obj = (SolutionComplexity) apaObj;
	    obj.setSolutionComplexityId(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfSolutionADR(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from SolutionADR where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    SolutionADR obj = (SolutionADR) apaObj;
	    obj.setSolutionAdrid(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfSolutionAPA(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from SolutionAPA where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    SolutionAPA obj = (SolutionAPA) apaObj;
	    obj.setSolutionApaid(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfRegressionLever(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from RegressionLever where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    RegressionLever obj = (RegressionLever) apaObj;
	    obj.setRegressionLeverId(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfNonLaborCost(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from NonLabourCost where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    NonLabourCost obj = (NonLabourCost) apaObj;
	    obj.setNonlabourCostId(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfGenericTestingOverhead(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from GenericTestingOverhead where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    GenericTestingOverhead obj = (GenericTestingOverhead) apaObj;
	    obj.setGenericTestingOverheadId(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfGenericTestingInputs(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from GenericTestingInputs where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    GenericTestingInputs obj = (GenericTestingInputs) apaObj;
	    obj.setGenericTestingInputsId(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfEfficiencyLever(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from EfficiencyLever where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    EfficiencyLever obj = (EfficiencyLever) apaObj;
	    obj.setEfficiencyLeverId(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfCheckLists(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from CheckLists where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    CheckLists obj = (CheckLists) apaObj;
	    obj.setCheckListsId(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfAdditionalFTE(Integer solutionID,
	    Solution newSolution) throws MSSPException {
	List<Object> entities = solutionDAO
		.getEntitiesObjBySolutionID("from AdditionalFTE where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(entities.size());
	for (Object apaObj : entities) {
	    AdditionalFTE obj = (AdditionalFTE) apaObj;
	    obj.setAdditionalFteid(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);

    }

    private void createCloneOfAPACmplx(Integer solutionID, Solution newSolution)
	    throws MSSPException {
	List<Object> apacomplexitis = solutionDAO
		.getEntitiesObjBySolutionID("from Apacomplexity where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(apacomplexitis.size());
	for (Object apaObj : apacomplexitis) {
	    Apacomplexity obj = (Apacomplexity) apaObj;
	    obj.setApacomplexityId(null);
	    obj.setSolution(newSolution);
	    apaList.add(obj);
	}
	solutionDAO.saveObjects(apaList);
    }

    private void createCloneOfAPAWeight(Integer solutionID, Solution newSolution)
	    throws MSSPException {
	List<Object> apacomplexitis = solutionDAO
		.getEntitiesObjBySolutionID("from Apaweightage where solutionID="
			+ solutionID);
	List<Object> apaList = new ArrayList<Object>(apacomplexitis.size());
	for (Object apaObj : apacomplexitis) {
	    Apaweightage apaWt = (Apaweightage) apaObj;
	    apaWt.setApaweightageId(null);
	    apaWt.setSolution(newSolution);
	    apaList.add(apaWt);
	}
	solutionDAO.saveObjects(apaList);
    }

    /*
     * private void setObjectCloneVals(Object apaObj, Solution newSolution, int
     * tablesIndex) { switch (tablesIndex) { case 0: Apacomplexity apa =
     * (Apacomplexity) apaObj; apa.setApacomplexityId(null);
     * apa.setSolution(newSolution); apaObj = apa; break;
     * 
     * case 1: Apaweightage apaWt = (Apaweightage) apaObj;
     * apaWt.setApaweightageId(null); apaWt.setSolution(newSolution); apaObj =
     * apaWt; break; case 2: AdditionalFTE additionalFTE = (AdditionalFTE)
     * apaObj; additionalFTE.setAdditionalFteid(null);
     * additionalFTE.setSolution(newSolution); apaObj = additionalFTE; break;
     * case 3: CheckLists chkLst = (CheckLists) apaObj;
     * chkLst.setCheckListsId(null); chkLst.setSolution(newSolution); apaObj =
     * chkLst; break; case 4: EfficiencyLever efficiencyLever =
     * (EfficiencyLever) apaObj; efficiencyLever.setEfficiencyLeverId(null);
     * efficiencyLever.setSolution(newSolution); apaObj = efficiencyLever;
     * break; case 5: FTEStaging fTEStaging = (FTEStaging) apaObj;
     * fTEStaging.setFtestagingId(null); fTEStaging.setSolution(newSolution);
     * apaObj = fTEStaging; break; case 6: GenericTestingInputs
     * genericTestingInputs = (GenericTestingInputs) apaObj;
     * genericTestingInputs.setGenericTestingInputsId(null);
     * genericTestingInputs.setSolution(newSolution); apaObj =
     * genericTestingInputs; break; case 7: GenericTestingOverhead
     * genericTestingOverhead = (GenericTestingOverhead) apaObj;
     * genericTestingOverhead.setGenericTestingOverheadId(null);
     * genericTestingOverhead.setSolution(newSolution); apaObj =
     * genericTestingOverhead; break; case 8: NonLabourCost nonLabourCost =
     * (NonLabourCost) apaObj; nonLabourCost.setNonlabourCostId(null);
     * nonLabourCost.setSolution(newSolution); apaObj = nonLabourCost; break;
     * case 9: RegressionLever obj = (RegressionLever) apaObj;
     * obj.setRegressionLeverId(null); obj.setSolution(newSolution); apaObj =
     * obj; break; case 10: SolutionADR solutionADR = (SolutionADR) apaObj;
     * solutionADR.setSolutionAdrid(null); solutionADR.setSolution(newSolution);
     * apaObj = solutionADR; break; case 11: SolutionAPA solutionAPA =
     * (SolutionAPA) apaObj; solutionAPA.setSolutionApaid(null);
     * solutionAPA.setSolution(newSolution); apaObj = solutionAPA; break; case
     * 12: SolutionApprover solutionApprover = (SolutionApprover) apaObj;
     * solutionApprover.setSolutionApproverId(null);
     * solutionApprover.setSolution(newSolution); apaObj = solutionApprover;
     * break; case 13: SolutionComplexity solutionComplexity =
     * (SolutionComplexity) apaObj;
     * solutionComplexity.setSolutionComplexityId(null);
     * solutionComplexity.setSolution(newSolution); apaObj = solutionComplexity;
     * break; case 14: SolutionLever solutionLever = (SolutionLever) apaObj;
     * solutionLever.setSolutionLeverId(null);
     * solutionLever.setSolution(newSolution); apaObj = solutionLever; break;
     * case 15: StaffingPlan staffingPlan = (StaffingPlan) apaObj;
     * staffingPlan.setStaffingPlanId(null);
     * staffingPlan.setSolution(newSolution); apaObj = staffingPlan; break; case
     * 16: StartUpFTE startUpFTE = (StartUpFTE) apaObj;
     * startUpFTE.setStartUpFteid(null); startUpFTE.setSolution(newSolution);
     * apaObj = startUpFTE; break; case 17: TNTDetail tNTDetail = (TNTDetail)
     * apaObj; tNTDetail.setTntdetailId(null);
     * tNTDetail.setSolution(newSolution); apaObj = tNTDetail; break; case 19:
     * TNTPartitionDate tNTPartitionDate = (TNTPartitionDate) apaObj;
     * tNTPartitionDate.setTntpartitionDateId(null);
     * tNTPartitionDate.setSolution(newSolution); apaObj = tNTPartitionDate;
     * break; case 20: TestEffReduction testEffReduction = (TestEffReduction)
     * apaObj; testEffReduction.setTestEffReductionId(null);
     * testEffReduction.setSolution(newSolution); apaObj = testEffReduction;
     * break; case 21: TestingService testingService = (TestingService) apaObj;
     * testingService.setTestingServiceId(null);
     * testingService.setSolution(newSolution); apaObj = testingService; break;
     * 
     * default: break; }
     * 
     * }
     */
}
