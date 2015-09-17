/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.service.impl
 * File name       		    :  SolutionServiceImpl.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Dec 12, 20122012
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Dec 12, 2012  	   |eruvwyn   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dao.IManageUserAccessDAO;
import com.ericsson.mssp.common.dto.AdditionalFTEDTO;
import com.ericsson.mssp.common.dto.CommercialModelDTO;
import com.ericsson.mssp.common.dto.ComponentDTO;
import com.ericsson.mssp.common.dto.CountryDTO;
import com.ericsson.mssp.common.dto.DeliveryModelDTO;
import com.ericsson.mssp.common.dto.DeliveryTypeModelDTO;
import com.ericsson.mssp.common.dto.ExchangeRateDTO;
import com.ericsson.mssp.common.dto.FTEStagingDTO;
import com.ericsson.mssp.common.dto.FteHeadCountAndPercentageDTO;
import com.ericsson.mssp.common.dto.JobRoleDTO;
import com.ericsson.mssp.common.dto.JobRoleStagesDTO;
import com.ericsson.mssp.common.dto.JobStageDTO;
import com.ericsson.mssp.common.dto.LaborCostDTO;
import com.ericsson.mssp.common.dto.NonLabourCostDTO;
import com.ericsson.mssp.common.dto.OpportunityComponentDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.OpportunityDetailDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.OpportunitySourceDTO;
import com.ericsson.mssp.common.dto.ProductDetailsDTO;
import com.ericsson.mssp.common.dto.RateCardDTO;
import com.ericsson.mssp.common.dto.ScopeDefinedByDTO;
import com.ericsson.mssp.common.dto.ServiceElementDTO;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.dto.SolutionADRDTO;
import com.ericsson.mssp.common.dto.SolutionAPADTO;
import com.ericsson.mssp.common.dto.SolutionApproachDimensionDTO;
import com.ericsson.mssp.common.dto.SolutionComplexityDTO;
import com.ericsson.mssp.common.dto.SolutionDTO;
import com.ericsson.mssp.common.dto.StaffAugDTO;
import com.ericsson.mssp.common.dto.UserAccessDTO;
import com.ericsson.mssp.common.dto.UserDTO;
import com.ericsson.mssp.common.dto.VolumetricsDefinedByDTO;
import com.ericsson.mssp.common.entity.CommercialModel;
import com.ericsson.mssp.common.entity.Component;
import com.ericsson.mssp.common.entity.Country;
import com.ericsson.mssp.common.entity.DeliveryModel;
import com.ericsson.mssp.common.entity.DeliveryType;
import com.ericsson.mssp.common.entity.InputVolumetricsDefinedBy;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.NonLabourCost;
import com.ericsson.mssp.common.entity.Opportunity;
import com.ericsson.mssp.common.entity.OpportunityComponent;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.OpportunitySource;
import com.ericsson.mssp.common.entity.ProductDetails;
import com.ericsson.mssp.common.entity.RateCard;
import com.ericsson.mssp.common.entity.ScopeOfServicesDefinedBy;
import com.ericsson.mssp.common.entity.ServiceElement;
import com.ericsson.mssp.common.entity.ServiceScope;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionADR;
import com.ericsson.mssp.common.entity.SolutionAPA;
import com.ericsson.mssp.common.entity.SolutionLever;
import com.ericsson.mssp.common.entity.StaffingPlan;
import com.ericsson.mssp.common.entity.User;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.dao.ISolutionDAO;
import com.ericsson.mssp.solution.dao.ISolutionLeverDAO;
import com.ericsson.mssp.solution.forms.NonLabourCostForm;
import com.ericsson.mssp.solution.forms.StaffAugForm;
import com.ericsson.mssp.solution.forms.StaffingPlanForm;
import com.ericsson.mssp.solution.service.ISolutionService;
import com.ericsson.mssp.solution.service.SolutionLeverService;

//import com.ericsson.mssp.solution.service.ICalculateVolumetricFTEService;

/**
 * @author eruvwyn
 * 
 */
@Service
public class SolutionServiceImpl implements ISolutionService, MSSPConstants {

    private final Log log = LogFactory.getLog(SolutionServiceImpl.class);

    private static final Logger logger = Logger
	    .getLogger(SolutionServiceImpl.class);
    private HashMap<Integer, Map<String, Object>> reviewFTEData = null;

    @Autowired
    private ISolutionDAO solutionDAO;
    
    @Autowired
	private IManageUserAccessDAO manageUserAccessDAO;
    
    @Autowired
    private ISolutionLeverDAO solutionLeverDAO;

    // @Autowired
    // private ICalculateVolumetricFTEService calculateVolumetricFTEService;
    @Override
    public Integer saveSolutionDetail(SolutionDTO solutionDTO, Integer oppId)
	    throws MSSPException {

	Integer solId = solutionDAO.saveSolutionDetail(solutionDTO, oppId);

	return solId;

    }

    public void saveOpportunityScopes(Integer oppId, String newServiceScopeIds,
	    String deleletedOppScopeIds) throws MSSPException {
	solutionDAO.saveOpportunityScopes(oppId, newServiceScopeIds,
		deleletedOppScopeIds);
    }

    public SolutionDTO getSolutionDetail(Integer solId) throws MSSPException {

	return solutionDAO.getSolutionDetail(solId);

    }

    public List<OpportunityScopeDTO> getOpportunityScopes(Integer oppId)
	    throws MSSPException {
	return solutionDAO.getOpportunityScopes(oppId);
    }

    public String[] getAllServiceScopeByOppId(Integer oppId)
	    throws MSSPException {
	List<ServiceScopeDTO> serviceScopeList = solutionDAO
		.loadAllSelectedServiceScopes(oppId);

	String[] serviceList = new String[serviceScopeList.size()];
	int i = 0;
	for (ServiceScopeDTO serviceScopeDTO : serviceScopeList) {
	    serviceList[i++] = serviceScopeDTO.getServiceScopeId().toString();
	}

	return serviceList;
    }

    public List<SolutionApproachDimensionDTO> getDimentionListBySolutionId(
	    Integer solutionId) throws MSSPException {

	return solutionDAO.getDimentionListBySolutionId(solutionId);
    }

    public List<ServiceScopeDTO> loadAllSelectedServiceScopes(
	    Integer opportunityId) throws MSSPException {

	List<ServiceScopeDTO> serviceScopeList = solutionDAO
		.loadAllSelectedServiceScopes(opportunityId);
	return serviceScopeList;
    }

    @Override
    public List<ServiceScopeDTO> getAllServiceScope() throws MSSPException {
	List<ServiceScopeDTO> serviceScopeDTOList = null;

	try {
	    List<ServiceScope> list = solutionDAO.getAllServiceScope();

	    serviceScopeDTOList = setSSEntityIntoSSDTO(list);

	} catch (Exception e) {
	    log.info(":::::: " + e.getMessage() + " ::::: " + e.getCause()
		    + " ...", new MSSPException("INVALID USER"));
	    throw new MSSPException();
	    // TODO: handle exception
	}
	return serviceScopeDTOList;

    }

    @Override
    public Map<Integer, String> getAllServiceScopeInMap() throws MSSPException {
	Map<Integer, String> serviceScopeDTOMap = null;

	try {
	    serviceScopeDTOMap = new LinkedHashMap<Integer, String>(10);
	    List<ServiceScope> sSList = solutionDAO.getAllServiceScope();
	    for (ServiceScope serviceScope : sSList) {
		serviceScopeDTOMap.put(serviceScope.getServiceScopeId(),
			serviceScope.getServiceScopeName());
	    }
	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	}
	return serviceScopeDTOMap;

    }

    @Override
    public Map<Integer, String> getJobRoles() throws MSSPException {
	Map<Integer, String> jobRolesMap = null;
	try {
	    jobRolesMap = new LinkedHashMap<Integer, String>(10);
	    List<JobRole> jobRoles = solutionDAO.getObjects(JobRole.class);
	    for (JobRole jr : jobRoles) {
		jobRolesMap.put(jr.getJobRoleId(), jr.getRole());
	    }
	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	}
	return jobRolesMap;
    }

    @Override
    public Map<Integer, String> getAllServiceScopeInMapByOppID(
	    Integer opportunityId) throws MSSPException {
	Map<Integer, String> serviceScopeDTOMap = null;

	try {
	    serviceScopeDTOMap = solutionDAO
		    .loadAllSelectedOppSSIdServiceScopes(opportunityId);
	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	}
	return serviceScopeDTOMap;

    }

    @Override
    public Map<Integer, String> getAllServiceScopeInMapBySolId(
	    Integer solutionID) throws MSSPException {
	Map<Integer, String> serviceScopeDTOMap = null;

	try {
	    serviceScopeDTOMap = getAllServiceScopeInMapByOppID(getOpportunityBySolId(
		    solutionID).getOpportunityId());
	    // System.out.println(serviceScopeDTOMap);
	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());

	}
	return serviceScopeDTOMap;

    }

    private List<ServiceScopeDTO> setSSEntityIntoSSDTO(
	    List<ServiceScope> serviceScopelist) {
	// TODO Auto-generated method stub
	List<ServiceScopeDTO> list = new ArrayList<ServiceScopeDTO>();
	ServiceScopeDTO dto;
	for (ServiceScope serviceScope : serviceScopelist) {
	    dto = new ServiceScopeDTO();
	    dto.setServiceScopeId(serviceScope.getServiceScopeId());
	    dto.setServiceScopeCategory(serviceScope.getServiceScopeCategory());
	    dto.setServiceScopeName(serviceScope.getServiceScopeName());
	    dto.setServiceElementName(serviceScope.getServiceElement().getServiceElementName());

	    list.add(dto);
	}

	return list;
    }

    @Override
    public void saveFTEStaging(FTEStagingDTO fteStagingDTO) {
	// TODO Auto-generated method stub

    }

    @Override
    public void saveSolutionComplexity(
	    SolutionComplexityDTO solutionComplexityDTO) {
	// TODO Auto-generated method stub

    }

    @Override
    public void saveSolutionApproachDimension(
	    SolutionApproachDimensionDTO solutionApproachDimensionDTO) {
	// TODO Auto-generated method stub

    }

    /**
     * 
     * Description : TODO Method Name : convertADRDTO2Entity Input& Output:
     * 
     * @param solutionADRDTO
     * @return
     */
    private SolutionADR convertADRDTO2Entity(SolutionADRDTO solutionADRDTO) {
	SolutionADR solutionADR = new SolutionADR();
	solutionADR.setAdrarea(solutionADRDTO.getAdrArea());
	solutionADR.setAdrcategory(solutionADRDTO.getAdrCategory());
	solutionADR.setAdrimpact(solutionADRDTO.getAdrImpact());
	solutionADR.setAdrstatement(solutionADRDTO.getAdrStatement());
	solutionADR.setAdrtype(solutionADRDTO.getAdrType());
	solutionADR.setAdrweightage(solutionADRDTO.getAdrWeightage());
	Solution solution = (Solution) solutionDAO.getObject(Solution.class,
		solutionADRDTO.getSolutionId());
	// log.debug("solution="+solution);
	solutionADR.setSolution(solution);
	solutionADR.setSolutionAdrid(solutionADRDTO.getSolutionAdrid());
	return solutionADR;
    }

    @Override
    public boolean saveSolutionADR(SolutionADRDTO solutionADRDTO)
	    throws MSSPException {
	try {
	    SolutionADR solutionADR = convertADRDTO2Entity(solutionADRDTO);
	    solutionDAO.saveObject(solutionADR);
	} catch (Exception e) {
	    // e.printStackTrace();
	    log.error(e.getMessage() + " |  " + e.getCause());
	    e.printStackTrace();
	    return false;
	    // throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	    // TODO: handle exception
	}
	return true;
    }

    @Override
    public Integer saveSolutionADRReturnId(SolutionADRDTO solutionADRDTO)
	    throws MSSPException {
	Integer id = -1;
	try {
	    SolutionADR solutionADR = convertADRDTO2Entity(solutionADRDTO);
	    id = solutionDAO.saveObjectReturnId(solutionADR);
	} catch (Exception e) {
	    // e.printStackTrace();
	    log.error(e.getMessage() + " |  " + e.getCause());
	    e.printStackTrace();
	    // return -1;
	    // throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	    // TODO: handle exception
	}
	return id;
    }

    @Override
    public boolean deleteSolutionADR(SolutionADRDTO solutionADRDTO)
	    throws MSSPException {
	try {
	    solutionDAO.removeObject(SolutionADR.class,
		    solutionADRDTO.getSolutionAdrid());
	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    // throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    /**
	 * 
	 */
    @Override
    public List<SolutionADRDTO> getAllSolutionADR() throws MSSPException {
	List<SolutionADRDTO> solutionADRDTOList = null;
	try {
	    List<SolutionADR> solADRlist = solutionDAO.getAllSolutionADR();
	    solutionADRDTOList = setADREntity2ADRDTO(solADRlist);
	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	}
	return solutionADRDTOList;
    }

    /**
     * It will pick all solution ADR and return these in SolutionADRDTO list
     */
    @Override
    public List<SolutionADRDTO> loadAllADR2SolADRDTOBySolID(Integer solID)
	    throws MSSPException {
	List<SolutionADRDTO> solutionADRDTOList = null;
	try {
	    solutionADRDTOList = solutionDAO.loadAllADR2SolADRDTOBySolID(solID);

	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	    // TODO: handle exception
	}
	return solutionADRDTOList;
    }

    /**
     * 
     * Description : Convert solutionADR entity to its DTO Method Name :
     * setADREnt2DTO Input& Output:
     * 
     * @param solADR
     * @return
     */
    private SolutionADRDTO setADREnt2DTO(SolutionADR solADR) {
	SolutionADRDTO dto = new SolutionADRDTO();
	dto.setAdrArea(solADR.getAdrarea());
	dto.setAdrCategory(solADR.getAdrcategory());
	dto.setAdrImpact(solADR.getAdrimpact());
	dto.setAdrImpactString(ADR_IMPACTS[solADR.getAdrimpact() - 1]);
	dto.setAdrStatement(solADR.getAdrstatement());
	dto.setAdrType(solADR.getAdrtype());
	dto.setAdrWeightage(solADR.getAdrweightage());
	dto.setSolutionId(solADR.getSolution().getSolutionId());
	dto.setSolutionAdrid(solADR.getSolutionAdrid());
	return dto;
    }

    /**
     * 
     * Description : It will return one map with three Assumption, Dependency
     * and Risk list loaded in it of type SolutionADRDTO Method Name :
     * getAllADRInADRListsMapBySolId Input& Output: Solution ID
     * 
     * @return Map<String, List<SolutionADRDTO>>
     * @throws MSSPException
     */
    @Override
    public Map<String, List<SolutionADRDTO>> getAllADRInADRListsMapBySolId(
	    Integer solID) throws MSSPException {
	Map<String, List<SolutionADRDTO>> solADRListsMap = null;
	try {
	    List<SolutionADR> solADRlist = solutionDAO
		    .loadAllSolutionADRBySolID(solID);
	    List<SolutionADRDTO> solAssumList = new LinkedList<SolutionADRDTO>();
	    List<SolutionADRDTO> solDependList = new LinkedList<SolutionADRDTO>();
	    List<SolutionADRDTO> solRiskList = new LinkedList<SolutionADRDTO>();
	    for (SolutionADR solutionADR : solADRlist) {
		if (solutionADR.getAdrcategory().equalsIgnoreCase(
			ADR_ASSUM_CATEGORY)) {
		    solAssumList.add(setADREnt2DTO(solutionADR));
		} else if (solutionADR.getAdrcategory().equalsIgnoreCase(
			ADR_DEPEND_CATEGORY)) {
		    solDependList.add(setADREnt2DTO(solutionADR));
		} else if (solutionADR.getAdrcategory().equalsIgnoreCase(
			ADR_RISK_CATEGORY)) {
		    solRiskList.add(setADREnt2DTO(solutionADR));
		}

	    }
	    solADRListsMap = new LinkedHashMap<String, List<SolutionADRDTO>>(3);
	    solADRListsMap.put(ADR_ASSUM_CATEGORY, solAssumList);
	    solADRListsMap.put(ADR_DEPEND_CATEGORY, solDependList);
	    solADRListsMap.put(ADR_RISK_CATEGORY, solRiskList);

	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	    // TODO: handle exception
	}
	return solADRListsMap;
    }

    /**
     * 
     * Description : It will return one map with three list loaded in it of type
     * SolutionADRDTO Method Name : getAllADRInADRListsMap Input& Output:
     * 
     * @return Map<String, List<SolutionADRDTO>>
     * @throws MSSPException
     */
    @Override
    public Map<String, List<SolutionADRDTO>> getAllADRInADRListsMap()
	    throws MSSPException {
	Map<String, List<SolutionADRDTO>> solADRListsMap = null;
	try {
	    List<SolutionADR> solADRlist = solutionDAO.getAllSolutionADR();
	    List<SolutionADRDTO> solAssumList = new LinkedList<SolutionADRDTO>();
	    List<SolutionADRDTO> solDependList = new LinkedList<SolutionADRDTO>();
	    List<SolutionADRDTO> solRiskList = new LinkedList<SolutionADRDTO>();
	    for (SolutionADR solutionADR : solADRlist) {
		if (solutionADR.getAdrcategory().equalsIgnoreCase(
			ADR_ASSUM_CATEGORY)) {
		    solAssumList.add(setADREnt2DTO(solutionADR));
		} else if (solutionADR.getAdrcategory().equalsIgnoreCase(
			ADR_DEPEND_CATEGORY)) {
		    solDependList.add(setADREnt2DTO(solutionADR));
		} else if (solutionADR.getAdrcategory().equalsIgnoreCase(
			ADR_RISK_CATEGORY)) {
		    solRiskList.add(setADREnt2DTO(solutionADR));
		}

	    }
	    solADRListsMap = new LinkedHashMap<String, List<SolutionADRDTO>>(3);
	    solADRListsMap.put(ADR_ASSUM_CATEGORY, solAssumList);
	    solADRListsMap.put(ADR_DEPEND_CATEGORY, solDependList);
	    solADRListsMap.put(ADR_RISK_CATEGORY, solRiskList);

	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	    // TODO: handle exception
	}
	return solADRListsMap;
    }

    /**
     * 
     * Description : It will return one map with three list loaded in it of type
     * SolutionADRDTO Method Name : getAllADRInADRListsMap Input& Output: Search
     * parameters
     * 
     * @return Map<String, List<SolutionADRDTO>>
     * @throws MSSPException
     */
    @Override
    public Map<String, List<SolutionADRDTO>> getAllADRInADRListsMap(
	    String sArea, String sType, String sImpact, Integer solID)
	    throws MSSPException {
	Map<String, List<SolutionADRDTO>> solADRListsMap = null;
	try {
	    List<SolutionADR> solADRlist = solutionDAO
		    .loadAllSolutionADRBySolID(solID);
	    List<SolutionADRDTO> solAssumList = new LinkedList<SolutionADRDTO>();
	    List<SolutionADRDTO> solDependList = new LinkedList<SolutionADRDTO>();
	    List<SolutionADRDTO> solRiskList = new LinkedList<SolutionADRDTO>();
	    for (SolutionADR solutionADR : solADRlist) {

		if ((null == sArea || sArea.equals("NONE") || sArea.equals("") || solutionADR
			.getAdrarea().equalsIgnoreCase(sArea))
			&& (null == sType || sType.equals("NONE")
				|| sType.equals("") || solutionADR.getAdrtype()
				.equalsIgnoreCase(sType))
			&& (null == sImpact || sImpact.equals("NONE")
				|| sImpact.equals("") || solutionADR
				.getAdrimpact().intValue() == Integer
				.parseInt(sImpact))) {
		    if (solutionADR.getAdrcategory().equalsIgnoreCase(
			    ADR_ASSUM_CATEGORY)) {
			solAssumList.add(setADREnt2DTO(solutionADR));
		    } else if (solutionADR.getAdrcategory().equalsIgnoreCase(
			    ADR_DEPEND_CATEGORY)) {
			solDependList.add(setADREnt2DTO(solutionADR));
		    } else if (solutionADR.getAdrcategory().equalsIgnoreCase(
			    ADR_RISK_CATEGORY)) {
			solRiskList.add(setADREnt2DTO(solutionADR));
		    }
		}

	    }
	    solADRListsMap = new LinkedHashMap<String, List<SolutionADRDTO>>(3);
	    solADRListsMap.put(ADR_ASSUM_CATEGORY, solAssumList);
	    solADRListsMap.put(ADR_DEPEND_CATEGORY, solDependList);
	    solADRListsMap.put(ADR_RISK_CATEGORY, solRiskList);

	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    e.printStackTrace();
	    // throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	    // TODO: handle exception
	}
	return solADRListsMap;
    }

    /**
     * 
     * Description : It will return one list with ADR loaded in it of type
     * SolutionADRDTO Method Name : getAllADRInADRListsMap Input& Output: Search
     * parameters
     * 
     * @return List<SolutionADRDTO>
     * @throws MSSPException
     */
    @Override
    public List<SolutionADRDTO> getSolutionAllADRBySolID(String sArea,
	    String sType, String sImpact, Integer solID) throws MSSPException {
	List<SolutionADRDTO> solADRList = null;
	try {
	    String query = "from SolutionADR where solutionID=" + solID
		    + " order by ADRCategory";
	    List<SolutionADR> solADRlist = solutionDAO
		    .loadAllSolutionADRByQuery(query);
	    solADRList = new LinkedList<SolutionADRDTO>();
	    for (SolutionADR solutionADR : solADRlist) {

		if ((null == sArea || sArea.equals("NONE") || sArea.equals("") || solutionADR
			.getAdrarea().equalsIgnoreCase(sArea))
			&& (null == sType || sType.equals("NONE")
				|| sType.equals("") || solutionADR.getAdrtype()
				.equalsIgnoreCase(sType))
			&& (null == sImpact || sImpact.equals("NONE")
				|| sImpact.equals("") || solutionADR
				.getAdrimpact().intValue() == Integer
				.parseInt(sImpact))) {
		    solADRList.add(setADREnt2DTO(solutionADR));
		}
	    }
	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    e.printStackTrace();
	    // throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	    // TODO: handle exception
	}
	return solADRList;
    }

    /**
     * 
     * Description : It will return all Assumptions list Method Name :
     * getSolutionAllAssumptions Input& Output:
     * 
     * @return Assumptions SolutionADRDTO list
     * @throws MSSPException
     */
    @Override
    public List<SolutionADRDTO> getSolutionAllAssumptions()
	    throws MSSPException {
	List<SolutionADRDTO> solAssumptionlist = null;
	try {
	    List<SolutionADR> solADRList = solutionDAO.getAllSolutionADR();
	    solAssumptionlist = new LinkedList<SolutionADRDTO>();
	    // write code to collect Assumption category
	    for (SolutionADR solutionADR : solADRList) {
		if (solutionADR.getAdrcategory().equalsIgnoreCase(
			ADR_ASSUM_CATEGORY)) {
		    solAssumptionlist.add(setADREnt2DTO(solutionADR));
		}
	    }
	    log.debug("Data of solAssumptionlist="
		    + (solAssumptionlist != null ? solAssumptionlist.size()
			    : solAssumptionlist));

	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	    // TODO: handle exception
	}
	return solAssumptionlist;

    }

    /**
     * 
     * Description : It will return all Risks list
     * 
     * Method Name : getSolutionAllRisks Input& Output:
     * 
     * @return Risks SolutionADRDTO list
     * @throws MSSPException
     */
    @Override
    public List<SolutionADRDTO> getSolutionAllRisks() throws MSSPException {
	List<SolutionADRDTO> solRiskslist = null;
	try {
	    // write code to collect Risks category
	    List<SolutionADR> solADRList = solutionDAO.getAllSolutionADR();
	    solRiskslist = new LinkedList<SolutionADRDTO>();
	    for (SolutionADR solutionADR : solADRList) {
		if (solutionADR.getAdrcategory().equalsIgnoreCase(
			ADR_RISK_CATEGORY)) {
		    solRiskslist.add(setADREnt2DTO(solutionADR));
		}
	    }
	    log.debug("Data of solRiskslist="
		    + (solRiskslist != null ? solRiskslist.size()
			    : solRiskslist));

	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	    // TODO: handle exception
	}
	return solRiskslist;

    }

    /**
     * 
     * Description : Returns all dependencies list Method Name :
     * getSolutionDependencies Input& Output:
     * 
     * @return Dependencies SolutionADRDTO list
     * @throws MSSPException
     */
    @Override
    public List<SolutionADRDTO> getSolutionAllDependencies()
	    throws MSSPException {
	List<SolutionADRDTO> solDependencieslist = null;
	try {
	    List<SolutionADR> solADRList = solutionDAO.getAllSolutionADR();
	    solDependencieslist = new LinkedList<SolutionADRDTO>();
	    for (SolutionADR solutionADR : solADRList) {
		if (solutionADR.getAdrcategory().equalsIgnoreCase(
			ADR_RISK_CATEGORY)) {
		    solDependencieslist.add(setADREnt2DTO(solutionADR));
		}
	    }
	    log.debug("Data of solDependencieslist="
		    + (solDependencieslist != null ? solDependencieslist.size()
			    : solDependencieslist));
	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	    // TODO: handle exception
	}
	return solDependencieslist;

    }

    /**
     * 
     * Description : TODO Method Name : setSADREntity2SADRDTO Input& Output:
     * 
     * @param SolADRlist
     * @return
     */
    private List<SolutionADRDTO> setADREntity2ADRDTO(
	    List<SolutionADR> SolADRlist) {
	List<SolutionADRDTO> list = new ArrayList<SolutionADRDTO>();
	SolutionADRDTO dto = null;
	for (SolutionADR solutionADR : SolADRlist) {
	    dto = new SolutionADRDTO();
	    dto.setAdrArea(solutionADR.getAdrarea());
	    dto.setAdrCategory(solutionADR.getAdrcategory());
	    dto.setAdrImpact(solutionADR.getAdrimpact());
	    dto.setAdrStatement(solutionADR.getAdrstatement());
	    dto.setAdrType(solutionADR.getAdrtype());
	    dto.setAdrWeightage(solutionADR.getAdrweightage());
	    dto.setSolutionId(solutionADR.getSolution().getSolutionId());
	    dto.setSolutionAdrid(solutionADR.getSolutionAdrid());
	    list.add(dto);
	}

	return list;
    }

    @Override
    public void saveNonLabourCost(NonLabourCostDTO nonLabourCostDTO) {
	// TODO Auto-generated method stub

    }

    @Override
    public void saveAdditionalFTE(AdditionalFTEDTO additionalFTEDTO) {
	// TODO Auto-generated method stub

    }

    @Override
    public void saveStaffingPlan(StaffingPlanForm staffingPlanForm,
	    int solutionID) {
	List<StaffingPlan> staffingPlanList = convertFromStaffPlanFormTOStaffPlan(
		staffingPlanForm, solutionID);

	for (StaffingPlan staffingPlan : staffingPlanList) {
	    // solutionDAO.saveStaffAug(staffingPlan);
	    solutionDAO.updateObject(staffingPlan);
	}

    }

    @Override
    public void saveSolutionAPA(SolutionAPADTO solutionAPADTO, Integer oppId,
	    Integer solutionId) throws MSSPException {
	solutionDAO.saveSolutionAPA(solutionAPADTO, oppId, solutionId);
    }

    @Override
    public List<CountryDTO> getCountries() {

	List<CountryDTO> listDto = new ArrayList<CountryDTO>();
	List<Country> listEntity = new ArrayList<Country>();
	listEntity = solutionDAO.getCountries();
	for (int i = 0; i < listEntity.size(); i++) {
	    CountryDTO cDto = new CountryDTO();
	    cDto.setCountryId(listEntity.get(i).getCountryId());
	    cDto.setCountryName(listEntity.get(i).getCountryName());
	    cDto.setCurrencyCode(listEntity.get(i).getCurrencyCode());
	    listDto.add(cDto);
	}
	logger.info("list : " + listDto.size());
	return listDto;
    }

    @Override
    public CountryDTO getSelectedCountry(Integer countryId) {

	logger.info("inside get selected country with Id : " + countryId);
	CountryDTO countryDTO = new CountryDTO();
	Country entity = new Country();
	entity = solutionDAO.getSelectedCountry(countryId);
	countryDTO.setCurrencyName(entity.getCurrencyName());
	countryDTO.setCurrencyCode(entity.getCurrencyCode());
	countryDTO.setTimeZone(entity.getTimeZone());
	return countryDTO;
    }

    @Override
    public List<UserDTO> getRepresentatives() {
	List<UserDTO> userDto = new ArrayList<UserDTO>();
	List<User> listEntity = new ArrayList<User>();
	listEntity = solutionDAO.getRepresentatives();
	for (int i = 0; i < listEntity.size(); i++) {
	    UserDTO uDto = new UserDTO();
	    uDto.setUserId(listEntity.get(i).getUserId());
	    uDto.setSignumId(listEntity.get(i).getSignumId().toUpperCase());
	    if(null == listEntity.get(i).getFirstName()){
	    	uDto.setFirstName(listEntity.get(i).getSignumId().toUpperCase());
	    	uDto.setLastName("");
	    }
	    else{
		    uDto.setFirstName(listEntity.get(i).getFirstName().toUpperCase());
		    uDto.setLastName(listEntity.get(i).getLastName()==null?"":listEntity.get(i).getLastName().toUpperCase());
	    }
	    userDto.add(uDto);
	}
	logger.info("vertical : " + userDto.size());
	return userDto;
    }

    @Override
    public String initiateOpportunity(OpportunityDTO opportunityDTO) {
	logger.info("inside initiate opportunity at service level");
	logger.info("customer name : "
		+ opportunityDTO.getCustomerDTO().getCustomerName());
	logger.info("opportunity name : " + opportunityDTO.getOpportunityName());
	String returnString = null;
	returnString = solutionDAO.saveOpportunity(opportunityDTO);
	logger.info(returnString);
	return returnString;
    }

    @Override
    public OpportunityDTO getOpportunity(Integer opportunityId) {
	OpportunityDTO opportunityDTO = new OpportunityDTO();
	opportunityDTO = solutionDAO.getOpportunity(opportunityId);
	return opportunityDTO;
    }

    @Override
    public List<String> loadServiceBucketData(Integer solutionID) {
	List<String> serviceBucketData = null;
	try {
	    serviceBucketData = solutionDAO
		    .getServiceBucketDataBySolutionID(solutionID);
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("Error during serviceBucketData-" + e);
	}
	return serviceBucketData;
    }

    @Override
    public List<String> loadServiceBucketDataByOppSolutionID(
	    Integer opportunityID, Integer solutionID){
	List<String> serviceBucketData = null;
	try {
	    serviceBucketData = solutionDAO
		    .getServiceBucketDataByOppSolutionID(opportunityID,
			    solutionID);
	} catch (MSSPException e) {
	    e.printStackTrace();
	    logger.error("Error during serviceBucketData-" + e);
	}
	return serviceBucketData;
    }

    @Override
    public String saveOpportunityDetails(
	    OpportunityDetailDTO opportunityDetailDTO) {
	String returnString;
	returnString = solutionDAO.saveOpportunityDetails(opportunityDetailDTO);
	return returnString;
    }

    @Override
    public void saveStaffAug(StaffAugForm staffAugForm, int solutionID)
	    throws MSSPException {

	List<StaffingPlan> staffingPlanList = convertFromStaffAugFormTOStaffAug(
		staffAugForm, solutionID);
	try {
	    for (StaffingPlan staffingPlan : staffingPlanList) {
		solutionDAO.saveStaffAug(staffingPlan);
	    }
	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	    // TODO: handle exception
	}
    }

    protected List<StaffingPlan> convertFromStaffAugFormTOStaffAug(
	    StaffAugForm staffAugForm, int solutionID) {
	StaffingPlan staffingPlan = null;
	List<StaffingPlan> staffingPlanList = new ArrayList<StaffingPlan>();
	String region = staffAugForm.getRegion();
	String[] demandRaisedBy = staffAugForm.getDemandRaisedBy();
	String[] demandCreateddate = staffAugForm.getDemandCreateddate();
	String[] status = staffAugForm.getStatus();
	String vertical = staffAugForm.getVertical();
	String[] egibu = staffAugForm.getEgibu();
	String client = staffAugForm.getClient();
	String opportunity = staffAugForm.getOpportunity();
	String projectType[] = staffAugForm.getProjectType();
	String[] gttopportunity = staffAugForm.getGttopportunity();
	String[] noDemandedPositions = staffAugForm.getNoDemandedPositions();
	String[] acceptSubcontractors = staffAugForm.getAcceptSubcontractors();
	String[] winOddsProbability = staffAugForm.getWinOddsProbability();
	String[] primaryLocation = staffAugForm.getPrimaryLocation();
	String[] secondaryLocation = staffAugForm.getSecondaryLocation();
	String[] onshoreLocation = staffAugForm.getOnshoreLocation();
	String[] positionStartdate = staffAugForm.getPositionStartdate();
	String[] positionEnddate = staffAugForm.getPositionEnddate();
	String[] onshoreStartdate = staffAugForm.getOnshoreStartdate();
	String[] onshoreEnddate = staffAugForm.getOnshoreEnddate();
	String[] gradeLow = staffAugForm.getGradeLow();
	String[] gradeHigh = staffAugForm.getGradeHigh();
	String[] jobFamily = staffAugForm.getJobFamily();
	Integer[] jobRole = staffAugForm.getJobRole();
	String[] competency = staffAugForm.getCompetency();
	String[] practice = staffAugForm.getPractice();
	String[] practiceArea = staffAugForm.getPracticeArea();
	String[] primarySkillSet = staffAugForm.getPrimarySkillSet();
	String[] secondarySkillSet = staffAugForm.getSecondarySkillSet();
	String[] typeofRequest = staffAugForm.getTypeofRequest();
	String[] resourceManager = staffAugForm.getResourceManager();
	String[] additionalInformation = staffAugForm
		.getAdditionalInformation();
	for (int i = 0; i < jobRole.length; i++) {
	    staffingPlan = new StaffingPlan();
	    /*
	     * staffingPlan.setRegion(region.length<1 ? "" :region[i]);
	     * staffingPlan.setVertical(vertical.length<1 ? "" : vertical[i]);
	     * staffingPlan.setEgibu(egibu.length<1 ? "" : egibu[i]);
	     * staffingPlan.setClient(client.length<1 ? "" :client[i]);
	     * staffingPlan.setOpportunity(opportunity.length<1 ? ""
	     * :opportunity[i]);
	     * staffingPlan.setProjectType(projectType.length<1 ? ""
	     * :projectType[i]);
	     */
	    if (staffAugForm.getStaffAugDTOList() != null)
		staffingPlan
			.setStaffingPlanId(staffAugForm.getStaffAugDTOList()
				.size() > i ? staffAugForm.getStaffAugDTOList()
				.get(i).getStaffingPlanId() : null);
	    staffingPlan.setRegion(region);
	    staffingPlan.setVertical(vertical);
	    staffingPlan.setDemandRaisedBy(demandRaisedBy.length <= i ? null
		    : demandRaisedBy[i]);
	    staffingPlan.setDemandCreateddate(demandCreateddate == null
		    || demandCreateddate.length <= i ? null
		    : ApplicationPropertiesUtil
			    .string2Date(demandCreateddate[i]));
	    staffingPlan.setStatus(status.length <= i ? null : status[i]);
	    staffingPlan.setEgibu(egibu.length <= i ? null : egibu[i]);
	    staffingPlan.setClient(client);
	    staffingPlan.setOpportunity(opportunity);
	    staffingPlan.setProjectType(projectType.length <= i ? null
		    : projectType[i]);

	    staffingPlan.setGttopportunity(gttopportunity.length <= i ? null
		    : gttopportunity[i]);
	    staffingPlan
		    .setNoDemandedPositions(noDemandedPositions.length <= i ? null
			    : noDemandedPositions[i]);
	    // staffingPlan.setAcceptSubcontractors(acceptSubcontractors.length<=1
	    // ? "" :acceptSubcontractors[i]);
	    staffingPlan
		    .setAcceptSubcontractors(acceptSubcontractors.length <= i ? null
			    : acceptSubcontractors[i]);
	    staffingPlan
		    .setWinOddsProbability(winOddsProbability.length <= i ? null
			    : winOddsProbability[i]);
	    staffingPlan.setPrimaryLocation(primaryLocation.length <= i ? null
		    : primaryLocation[i]);
	    staffingPlan
		    .setSecondaryLocation(secondaryLocation.length <= i ? null
			    : secondaryLocation[i]);
	    staffingPlan.setOnshoreLocation(onshoreLocation.length <= i ? null
		    : onshoreLocation[i]);
	    staffingPlan.setPositionStartdate(positionStartdate == null
		    || positionStartdate.length <= i ? null
		    : ApplicationPropertiesUtil
			    .string2Date(positionStartdate[i]));
	    staffingPlan
		    .setPositionEnddate(positionEnddate == null
			    || positionEnddate.length <= i ? null
			    : ApplicationPropertiesUtil
				    .string2Date(positionEnddate[i]));
	    staffingPlan.setOnshoreStartdate(onshoreStartdate == null
		    || onshoreStartdate.length <= i ? null
		    : ApplicationPropertiesUtil
			    .string2Date(onshoreStartdate[i]));
	    staffingPlan.setOnshoreEnddate(onshoreEnddate == null
		    || onshoreEnddate.length <= i ? null
		    : ApplicationPropertiesUtil.string2Date(onshoreEnddate[i]));
	    staffingPlan.setGradeLow(gradeLow.length <= i ? null : gradeLow[i]);
	    staffingPlan.setGradeHigh(gradeHigh.length <= i ? null
		    : gradeHigh[i]);
	    staffingPlan.setJobFamily(jobFamily.length <= i ? null
		    : jobFamily[i]);
	    JobRole role = new JobRole();
	    role.setJobRoleId(jobRole[i]);
	    staffingPlan.setJobRole(role);
	    staffingPlan.setCompetency(competency.length <= i ? null
		    : competency[i]);
	    staffingPlan.setPractice(practice.length <= i ? null : practice[i]);
	    staffingPlan.setPracticeArea(practiceArea.length <= i ? null
		    : practiceArea[i]);
	    staffingPlan.setPrimarySkillSet(primarySkillSet.length <= i ? null
		    : primarySkillSet[i]);
	    staffingPlan
		    .setSecondarySkillSet(secondarySkillSet.length <= i ? null
			    : secondarySkillSet[i]);
	    staffingPlan.setTypeofRequest(typeofRequest.length <= i ? null
		    : typeofRequest[i]);
	    staffingPlan.setResourceManager(resourceManager.length <= i ? null
		    : resourceManager[i]);
	    staffingPlan
		    .setAdditionalInformation(additionalInformation.length <= i ? null
			    : additionalInformation[i]);
	    Solution solution = new Solution();
	    solution.setSolutionId(solutionID);
	    staffingPlan.setSolution(solution);
	    staffingPlanList.add(staffingPlan);
	}

	return staffingPlanList;

    }

    public static String emptyString(String str) {
	String eStr = "";
	if (str == null) {
	    eStr = "";
	} else {
	    eStr = str.trim().toUpperCase(Locale.getDefault());
	}
	return eStr;
    }

    @Override
    public StaffAugForm getStaffAugWithSolutionID(int solutionID)
	    throws MSSPException {
	List<StaffAugDTO> staffAugTOList = null;
	StaffAugForm staffAugForm = new StaffAugForm();
	try {
	    Solution objSol = solutionDAO.getStaffAugWithSolutionID(solutionID);
	    List<StaffingPlan> staffAugList = solutionDAO
		    .getStaffingPlanBySolutionID(solutionID);
	    // new ArrayList<StaffingPlan>(
	    // objSol.getStaffingPlans());
	    staffAugTOList = convertFromStaffAugToFrom(staffAugList);
	    staffAugForm.setStaffAugDTOList(staffAugTOList);
	    staffAugForm.setRegion(objSol.getOpportunity().getCustomer()
		    .getCountry().getRegion());
	    // staffAugForm.setEgibu("egibu");
	    /*
	     * staffAugForm.setVertical(objSol.getOpportunity()
	     * .getOpportunityDetail().getVertical());
	     */
	    staffAugForm.setClient(objSol.getOpportunity().getCustomer()
		    .getCustomerName());
	    staffAugForm.setOpportunity(objSol.getOpportunity()
		    .getOpportunityName());
	    // staffAugForm.setProjectType("projectType");

	} catch (Exception e) {
	    log.info(e.getMessage() + " |  " + e.getCause() + "|",
		    new MSSPException("INVALID USER"));
	    throw new MSSPException();
	}

	return staffAugForm;
    }

    private List<StaffAugDTO> convertFromStaffAugToFrom(
	    List<StaffingPlan> staffAugList) {

	List<StaffAugDTO> list = new ArrayList<StaffAugDTO>();
	StaffAugDTO staffAugTO = null;
	for (StaffingPlan staffingPlan : staffAugList) {
	    staffAugTO = new StaffAugDTO();

	    staffAugTO.setStaffingPlanId(staffingPlan.getStaffingPlanId());
	    staffAugTO.setDemandRaisedBy(staffingPlan.getDemandRaisedBy());
	    staffAugTO.setDemandCreateddate(ApplicationPropertiesUtil
		    .date2string(staffingPlan.getDemandCreateddate()));
	    staffAugTO.setStatus(staffingPlan.getStatus());
	    staffAugTO.setRegion(staffingPlan.getRegion());
	    staffAugTO.setVertical(staffingPlan.getVertical());
	    staffAugTO.setEgibu(staffingPlan.getEgibu());
	    staffAugTO.setClient(staffingPlan.getClient());
	    staffAugTO.setOpportunity(staffingPlan.getOpportunity());
	    staffAugTO.setProjectType(staffingPlan.getProjectType());

	    staffAugTO.setGttopportunity(staffingPlan.getGttopportunity());
	    staffAugTO.setNoDemandedPositions(staffingPlan
		    .getNoDemandedPositions());
	    // staffingPlan.setAcceptSubcontractors(acceptSubcontractors.length<1
	    // ? "" :acceptSubcontractors[i]);
	    staffAugTO.setAcceptSubcontractors(staffingPlan
		    .getAcceptSubcontractors());
	    staffAugTO.setWinOddsProbability(staffingPlan
		    .getWinOddsProbability());
	    staffAugTO.setPrimaryLocation(staffingPlan.getPrimaryLocation());
	    staffAugTO
		    .setSecondaryLocation(staffingPlan.getSecondaryLocation());
	    staffAugTO.setOnshoreLocation(staffingPlan.getOnshoreLocation());
	    staffAugTO.setPositionStartdate(ApplicationPropertiesUtil
		    .date2string(staffingPlan.getPositionStartdate()));
	    staffAugTO.setPositionEnddate(ApplicationPropertiesUtil
		    .date2string(staffingPlan.getPositionEnddate()));
	    staffAugTO.setOnshoreStartdate(ApplicationPropertiesUtil
		    .date2string(staffingPlan.getOnshoreStartdate()));
	    staffAugTO.setOnshoreEnddate(ApplicationPropertiesUtil
		    .date2string(staffingPlan.getOnshoreEnddate()));
	    staffAugTO.setGradeLow(staffingPlan.getGradeLow());
	    staffAugTO.setGradeHigh(staffingPlan.getGradeHigh());
	    staffAugTO.setJobFamily(staffingPlan.getJobFamily());
	    JobRole jobRole = staffingPlan.getJobRole();

	    // JobRole role = new JobRole();
	    // role.setJobRoleId();
	    staffAugTO.setJobRoleName(jobRole.getRole());
	    staffAugTO.setJobRole(jobRole.getJobRoleId());

	    staffAugTO.setCompetency(staffingPlan.getCompetency());
	    staffAugTO.setPractice(staffingPlan.getPractice());
	    staffAugTO.setPracticeArea(staffingPlan.getPracticeArea());
	    staffAugTO.setPrimarySkillSet(staffingPlan.getPrimarySkillSet());
	    staffAugTO
		    .setSecondarySkillSet(staffingPlan.getSecondarySkillSet());
	    staffAugTO.setTypeofRequest(staffingPlan.getTypeofRequest());
	    staffAugTO.setResourceManager(staffingPlan.getResourceManager());
	    staffAugTO.setAdditionalInformation(staffingPlan
		    .getAdditionalInformation());
	    list.add(staffAugTO);
	}
	return list;
    }

    /**
     * 
     * Description : It will set all NonLabour cost entity values to GUI bind
     * form bean except year field Method Name : setNLREntity2NLRForm Input&
     * Output:
     * 
     * @param nonLabourCost
     * @param nonLabourCostForm
     */
    private void setNLCEntity2NLCForm(List<NonLabourCost> solutionNLCList,
	    NonLabourCostForm nonLabourCostForm) throws MSSPException {
	String defaultValue = "NA";
	String delimiters = ";";
	StringBuilder nonlabourCostId = new StringBuilder();
	StringBuilder itnoofTravels = new StringBuilder();
	StringBuilder itstayDuration = new StringBuilder();
	StringBuilder itshortVisaCost = new StringBuilder();
	StringBuilder ittickectCost = new StringBuilder();
	StringBuilder itdailyPerDiem = new StringBuilder();
	StringBuilder ithotelCostPerNight = new StringBuilder();
	StringBuilder itconveyancePerDay = new StringBuilder();
	StringBuilder dtnoofTravels = new StringBuilder();
	StringBuilder dtstayDuration = new StringBuilder();
	StringBuilder dttickectCost = new StringBuilder();
	StringBuilder dtdailyPerDiem = new StringBuilder();
	StringBuilder dthotelCostPerNight = new StringBuilder();
	StringBuilder dtconveyancePerDay = new StringBuilder();
	StringBuilder egiconnectivityCost = new StringBuilder();
	StringBuilder gscconnectivityCost = new StringBuilder();
	StringBuilder otherConnectivityCost = new StringBuilder();
	StringBuilder monthlyNightAllowance = new StringBuilder();
	StringBuilder pcpeopleAtNight = new StringBuilder();
	StringBuilder monthlyWeekendAllowance = new StringBuilder();
	StringBuilder pcpeopleAtWeekend = new StringBuilder();
	StringBuilder monthMobileCost = new StringBuilder();
	StringBuilder monthlyDataCardCost = new StringBuilder();
	StringBuilder hwsw3ppcost = new StringBuilder();
	StringBuilder trainingCost = new StringBuilder();
	StringBuilder otherCost = new StringBuilder();
	String[] years = nonLabourCostForm.getYear().split(";");
	log.info("years==" + nonLabourCostForm.getYear());
	// System.out.println("years==" + nonLabourCostForm.getYear());
	if (years.length < 1) {
	    log.info("Illegal opportunity timeline");
	    return;
	    // throw new MSSPException("Illegal opportunity timeline");
	}
	int startYr = Integer.parseInt(years[0]);
	int endYr = Integer.parseInt(years[years.length - 1]);
	int yrIndex = 0;
	// Delete old stored years NLC records in current opportunity timeline
	int deletedCount = solutionDAO.removeAllNNLCBySolIdTimeline(
		nonLabourCostForm.getSolutionId(), startYr, endYr);
	log.info("Non Labour cost removed record count:" + deletedCount
		+ "  - SolutionID:" + nonLabourCostForm.getSolutionId()
		+ " expected years:" + nonLabourCostForm.getYear());
	if (deletedCount > 0) {
	    solutionNLCList = solutionDAO
		    .getAllNonLabourCostBySolId(nonLabourCostForm
			    .getSolutionId());
	}
	for (NonLabourCost nonLabourCost : solutionNLCList) {
	    if (yrIndex < years.length) {

		if (nonLabourCost.getYear() <= endYr
			|| nonLabourCost.getYear() >= startYr) {
		    if (nonLabourCost.getYear().intValue() == Integer
			    .parseInt(years[yrIndex])) {
			nonlabourCostId
				.append(nonLabourCost.getNonlabourCostId() == null ? defaultValue
					: nonLabourCost.getNonlabourCostId())
				.append(delimiters);
			itnoofTravels
				.append(nonLabourCost.getItnoofTravels() == null ? defaultValue
					: nonLabourCost.getItnoofTravels())
				.append(delimiters);
			itstayDuration
				.append(nonLabourCost.getItstayDuration() == null ? defaultValue
					: nonLabourCost.getItstayDuration())
				.append(delimiters);
			itshortVisaCost
				.append(nonLabourCost.getItshortVisaCost() == null ? defaultValue
					: nonLabourCost.getItshortVisaCost())
				.append(delimiters);
			ittickectCost
				.append(nonLabourCost.getIttickectCost() == null ? defaultValue
					: nonLabourCost.getIttickectCost())
				.append(delimiters);
			itdailyPerDiem
				.append(nonLabourCost.getItdailyPerDiem() == null ? defaultValue
					: nonLabourCost.getItdailyPerDiem())
				.append(delimiters);
			ithotelCostPerNight
				.append(nonLabourCost.getIthotelCostPerNight() == null ? defaultValue
					: nonLabourCost
						.getIthotelCostPerNight())
				.append(delimiters);
			itconveyancePerDay
				.append(nonLabourCost.getItconveyancePerDay() == null ? defaultValue
					: nonLabourCost.getItconveyancePerDay())
				.append(delimiters);
			dtnoofTravels
				.append(nonLabourCost.getDtnoofTravels() == null ? defaultValue
					: nonLabourCost.getDtnoofTravels())
				.append(delimiters);
			dtstayDuration
				.append(nonLabourCost.getDtstayDuration() == null ? defaultValue
					: nonLabourCost.getDtstayDuration())
				.append(delimiters);
			dttickectCost
				.append(nonLabourCost.getDttickectCost() == null ? defaultValue
					: nonLabourCost.getDttickectCost())
				.append(delimiters);
			dtdailyPerDiem
				.append(nonLabourCost.getDtdailyPerDiem() == null ? defaultValue
					: nonLabourCost.getDtdailyPerDiem())
				.append(delimiters);
			dthotelCostPerNight
				.append(nonLabourCost.getDthotelCostPerNight() == null ? defaultValue
					: nonLabourCost
						.getDthotelCostPerNight())
				.append(delimiters);
			dtconveyancePerDay
				.append(nonLabourCost.getDtconveyancePerDay() == null ? defaultValue
					: nonLabourCost.getDtconveyancePerDay())
				.append(delimiters);
			egiconnectivityCost
				.append(nonLabourCost.getEgiconnectivityCost() == null ? defaultValue
					: nonLabourCost
						.getEgiconnectivityCost())
				.append(delimiters);
			gscconnectivityCost
				.append(nonLabourCost.getGscconnectivityCost() == null ? defaultValue
					: nonLabourCost
						.getGscconnectivityCost())
				.append(delimiters);
			otherConnectivityCost
				.append(nonLabourCost
					.getOtherConnectivityCost() == null ? defaultValue
					: nonLabourCost
						.getOtherConnectivityCost())
				.append(delimiters);
			monthlyNightAllowance
				.append(nonLabourCost
					.getMonthlyNightAllowance() == null ? defaultValue
					: nonLabourCost
						.getMonthlyNightAllowance())
				.append(delimiters);
			pcpeopleAtNight
				.append(nonLabourCost.getPcpeopleAtNight() == null ? defaultValue
					: nonLabourCost.getPcpeopleAtNight())
				.append(delimiters);
			monthlyWeekendAllowance
				.append(nonLabourCost
					.getMonthlyWeekendAllowance() == null ? defaultValue
					: nonLabourCost
						.getMonthlyWeekendAllowance())
				.append(delimiters);
			pcpeopleAtWeekend
				.append(nonLabourCost.getPcpeopleAtWeekend() == null ? defaultValue
					: nonLabourCost.getPcpeopleAtWeekend())
				.append(delimiters);
			monthMobileCost
				.append(nonLabourCost.getMonthMobileCost() == null ? defaultValue
					: nonLabourCost.getMonthMobileCost())
				.append(delimiters);
			monthlyDataCardCost
				.append(nonLabourCost.getMonthlyDataCardCost() == null ? defaultValue
					: nonLabourCost
						.getMonthlyDataCardCost())
				.append(delimiters);
			hwsw3ppcost
				.append(nonLabourCost.getHwsw3ppcost() == null ? defaultValue
					: nonLabourCost.getHwsw3ppcost())
				.append(delimiters);
			trainingCost
				.append(nonLabourCost.getTrainingCost() == null ? defaultValue
					: nonLabourCost.getTrainingCost())
				.append(delimiters);
			otherCost
				.append(nonLabourCost.getOtherCost() == null ? defaultValue
					: nonLabourCost.getOtherCost()).append(
					delimiters);
		    } else {
			if (Integer.parseInt(years[yrIndex]) < nonLabourCost
				.getYear().intValue()) {
			    // Set first less than NLC years blank entries
			    for (int yr = Integer.parseInt(years[yrIndex]); yr < nonLabourCost
				    .getYear().intValue(); yr++) {
				nonlabourCostId.append(defaultValue).append(
					delimiters);
				itnoofTravels.append(defaultValue).append(
					delimiters);
				itstayDuration.append(defaultValue).append(
					delimiters);
				itshortVisaCost.append(defaultValue).append(
					delimiters);
				ittickectCost.append(defaultValue).append(
					delimiters);
				itdailyPerDiem.append(defaultValue).append(
					delimiters);
				ithotelCostPerNight.append(defaultValue)
					.append(delimiters);
				itconveyancePerDay.append(defaultValue).append(
					delimiters);
				dtnoofTravels.append(defaultValue).append(
					delimiters);
				dtstayDuration.append(defaultValue).append(
					delimiters);
				dttickectCost.append(defaultValue).append(
					delimiters);
				dtdailyPerDiem.append(defaultValue).append(
					delimiters);
				dthotelCostPerNight.append(defaultValue)
					.append(delimiters);
				dtconveyancePerDay.append(defaultValue).append(
					delimiters);
				egiconnectivityCost.append(defaultValue)
					.append(delimiters);
				gscconnectivityCost.append(defaultValue)
					.append(delimiters);
				otherConnectivityCost.append(defaultValue)
					.append(delimiters);
				monthlyNightAllowance.append(defaultValue)
					.append(delimiters);
				pcpeopleAtNight.append(defaultValue).append(
					delimiters);
				monthlyWeekendAllowance.append(defaultValue)
					.append(delimiters);
				pcpeopleAtWeekend.append(defaultValue).append(
					delimiters);
				monthMobileCost.append(defaultValue).append(
					delimiters);
				monthlyDataCardCost.append(defaultValue)
					.append(delimiters);
				hwsw3ppcost.append(defaultValue).append(
					delimiters);
				trainingCost.append(defaultValue).append(
					delimiters);
				otherCost.append(defaultValue).append(
					delimiters);
				yrIndex++;
			    }
			    nonlabourCostId
				    .append(nonLabourCost.getNonlabourCostId() == null ? defaultValue
					    : nonLabourCost
						    .getNonlabourCostId())
				    .append(delimiters);
			    itnoofTravels
				    .append(nonLabourCost.getItnoofTravels() == null ? defaultValue
					    : nonLabourCost.getItnoofTravels())
				    .append(delimiters);
			    itstayDuration
				    .append(nonLabourCost.getItstayDuration() == null ? defaultValue
					    : nonLabourCost.getItstayDuration())
				    .append(delimiters);
			    itshortVisaCost
				    .append(nonLabourCost.getItshortVisaCost() == null ? defaultValue
					    : nonLabourCost
						    .getItshortVisaCost())
				    .append(delimiters);
			    ittickectCost
				    .append(nonLabourCost.getIttickectCost() == null ? defaultValue
					    : nonLabourCost.getIttickectCost())
				    .append(delimiters);
			    itdailyPerDiem
				    .append(nonLabourCost.getItdailyPerDiem() == null ? defaultValue
					    : nonLabourCost.getItdailyPerDiem())
				    .append(delimiters);
			    ithotelCostPerNight
				    .append(nonLabourCost
					    .getIthotelCostPerNight() == null ? defaultValue
					    : nonLabourCost
						    .getIthotelCostPerNight())
				    .append(delimiters);
			    itconveyancePerDay
				    .append(nonLabourCost
					    .getItconveyancePerDay() == null ? defaultValue
					    : nonLabourCost
						    .getItconveyancePerDay())
				    .append(delimiters);
			    dtnoofTravels
				    .append(nonLabourCost.getDtnoofTravels() == null ? defaultValue
					    : nonLabourCost.getDtnoofTravels())
				    .append(delimiters);
			    dtstayDuration
				    .append(nonLabourCost.getDtstayDuration() == null ? defaultValue
					    : nonLabourCost.getDtstayDuration())
				    .append(delimiters);
			    dttickectCost
				    .append(nonLabourCost.getDttickectCost() == null ? defaultValue
					    : nonLabourCost.getDttickectCost())
				    .append(delimiters);
			    dtdailyPerDiem
				    .append(nonLabourCost.getDtdailyPerDiem() == null ? defaultValue
					    : nonLabourCost.getDtdailyPerDiem())
				    .append(delimiters);
			    dthotelCostPerNight
				    .append(nonLabourCost
					    .getDthotelCostPerNight() == null ? defaultValue
					    : nonLabourCost
						    .getDthotelCostPerNight())
				    .append(delimiters);
			    dtconveyancePerDay
				    .append(nonLabourCost
					    .getDtconveyancePerDay() == null ? defaultValue
					    : nonLabourCost
						    .getDtconveyancePerDay())
				    .append(delimiters);
			    egiconnectivityCost
				    .append(nonLabourCost
					    .getEgiconnectivityCost() == null ? defaultValue
					    : nonLabourCost
						    .getEgiconnectivityCost())
				    .append(delimiters);
			    gscconnectivityCost
				    .append(nonLabourCost
					    .getGscconnectivityCost() == null ? defaultValue
					    : nonLabourCost
						    .getGscconnectivityCost())
				    .append(delimiters);
			    otherConnectivityCost
				    .append(nonLabourCost
					    .getOtherConnectivityCost() == null ? defaultValue
					    : nonLabourCost
						    .getOtherConnectivityCost())
				    .append(delimiters);
			    monthlyNightAllowance
				    .append(nonLabourCost
					    .getMonthlyNightAllowance() == null ? defaultValue
					    : nonLabourCost
						    .getMonthlyNightAllowance())
				    .append(delimiters);
			    pcpeopleAtNight
				    .append(nonLabourCost.getPcpeopleAtNight() == null ? defaultValue
					    : nonLabourCost
						    .getPcpeopleAtNight())
				    .append(delimiters);
			    monthlyWeekendAllowance
				    .append(nonLabourCost
					    .getMonthlyWeekendAllowance() == null ? defaultValue
					    : nonLabourCost
						    .getMonthlyWeekendAllowance())
				    .append(delimiters);
			    pcpeopleAtWeekend
				    .append(nonLabourCost
					    .getPcpeopleAtWeekend() == null ? defaultValue
					    : nonLabourCost
						    .getPcpeopleAtWeekend())
				    .append(delimiters);
			    monthMobileCost
				    .append(nonLabourCost.getMonthMobileCost() == null ? defaultValue
					    : nonLabourCost
						    .getMonthMobileCost())
				    .append(delimiters);
			    monthlyDataCardCost
				    .append(nonLabourCost
					    .getMonthlyDataCardCost() == null ? defaultValue
					    : nonLabourCost
						    .getMonthlyDataCardCost())
				    .append(delimiters);
			    hwsw3ppcost
				    .append(nonLabourCost.getHwsw3ppcost() == null ? defaultValue
					    : nonLabourCost.getHwsw3ppcost())
				    .append(delimiters);
			    trainingCost
				    .append(nonLabourCost.getTrainingCost() == null ? defaultValue
					    : nonLabourCost.getTrainingCost())
				    .append(delimiters);
			    otherCost
				    .append(nonLabourCost.getOtherCost() == null ? defaultValue
					    : nonLabourCost.getOtherCost())
				    .append(delimiters);

			}
		    }
		    yrIndex++;
		}

	    }
	    nonLabourCostForm.setNonlabourCostId(nonlabourCostId.toString());
	    nonLabourCostForm.setItnoofTravels(itnoofTravels.toString());
	    nonLabourCostForm.setItstayDuration(itstayDuration.toString());
	    nonLabourCostForm.setItshortVisaCost(itshortVisaCost.toString());
	    nonLabourCostForm.setIttickectCost(ittickectCost.toString());
	    nonLabourCostForm.setItdailyPerDiem(itdailyPerDiem.toString());
	    nonLabourCostForm.setIthotelCostPerNight(ithotelCostPerNight
		    .toString());
	    nonLabourCostForm.setItconveyancePerDay(itconveyancePerDay
		    .toString());
	    nonLabourCostForm.setDtnoofTravels(dtnoofTravels.toString());
	    nonLabourCostForm.setDtstayDuration(dtstayDuration.toString());
	    nonLabourCostForm.setDttickectCost(dttickectCost.toString());
	    nonLabourCostForm.setDtdailyPerDiem(dtdailyPerDiem.toString());
	    nonLabourCostForm.setDthotelCostPerNight(dthotelCostPerNight
		    .toString());
	    nonLabourCostForm.setDtconveyancePerDay(dtconveyancePerDay
		    .toString());
	    nonLabourCostForm.setEgiconnectivityCost(egiconnectivityCost
		    .toString());
	    nonLabourCostForm.setGscconnectivityCost(gscconnectivityCost
		    .toString());
	    nonLabourCostForm.setOtherConnectivityCost(otherConnectivityCost
		    .toString());
	    nonLabourCostForm.setMonthlyNightAllowance(monthlyNightAllowance
		    .toString());
	    nonLabourCostForm.setPcpeopleAtNight(pcpeopleAtNight.toString());
	    nonLabourCostForm
		    .setMonthlyWeekendAllowance(monthlyWeekendAllowance
			    .toString());
	    nonLabourCostForm
		    .setPcpeopleAtWeekend(pcpeopleAtWeekend.toString());
	    nonLabourCostForm.setMonthMobileCost(monthMobileCost.toString());
	    nonLabourCostForm.setMonthlyDataCardCost(monthlyDataCardCost
		    .toString());
	    nonLabourCostForm.setHwsw3ppcost(hwsw3ppcost.toString());
	    nonLabourCostForm.setTrainingCost(trainingCost.toString());
	    nonLabourCostForm.setOtherCost(otherCost.toString());
	}
    }

    @Override
    public Solution getSolutionById(Integer solID) throws MSSPException {
	return (Solution) solutionDAO.getObject(Solution.class, solID);
    }

    @Override
    public Opportunity getOpportunityBySolId(Integer solutionID)
	    throws MSSPException {
	return (Opportunity) solutionDAO
		.getObject(Opportunity.class, getSolutionById(solutionID)
			.getOpportunity().getOpportunityId());
    }

    @Override
    public String getOpportunitySteadyYearsString(Integer solutionID)
	    throws MSSPException {

	// Years capturing code
	StringBuilder years = new StringBuilder("");
	Calendar steadyStartDt = Calendar.getInstance();
	Calendar steadyEndDt = Calendar.getInstance();
	try {
	    OpportunityDetail opportunityDetail = ((Opportunity) solutionDAO
		    .getObject(Opportunity.class,
			    getOpportunityBySolId(solutionID)
				    .getOpportunityId()))
		    .getOpportunityDetail();
	    steadyStartDt.setTime(opportunityDetail.getSteadyStateStartDate());
	    steadyEndDt.setTime(opportunityDetail.getSteadyStateEndDate());
	    for (int year = steadyStartDt.get(Calendar.YEAR); year <= steadyEndDt
		    .get(Calendar.YEAR); year++) {
		years.append(year).append(";");
	    }
	} catch (Exception e) {
	    logger.info("Opportunity details not found Exception-" + e);
	    // e.printStackTrace();
	    return "";
	}
	if (years.length() == 0) {
	    logger.info("Invalid opportunity timeline: Start year:"
		    + steadyStartDt.get(Calendar.YEAR) + " End year:"
		    + steadyEndDt.get(Calendar.YEAR));
	    return "";
	    // throw new
	    // MSSPException("Invalid opportunity timeline: Start year:"
	    // + steadyStartDt.get(Calendar.YEAR) + " End year:"
	    // + steadyEndDt.get(Calendar.YEAR));
	}
	return years.toString();
    }

    @Override
    public NonLabourCostForm getSolutionNLC(Integer solutionID)
	    throws MSSPException {
	NonLabourCostForm nonLabourCostForm = new NonLabourCostForm();
	nonLabourCostForm.setSolutionId(solutionID);
	nonLabourCostForm.setYear(getOpportunitySteadyYearsString(solutionID));
	// Delete duplicate NLC records by Solution ID and year
	int deletedCount = solutionDAO.removeAllDuplicateNLC();
	log.info("Duplicate Non Labour cost removed record count:"
		+ deletedCount);
	List<NonLabourCost> solutionNLCList = solutionDAO
		.getAllNonLabourCostBySolId(solutionID);

	// Passed to loaded into form bean for GUI format data
	if (!nonLabourCostForm.getYear().equals("")) {
	    setNLCEntity2NLCForm(solutionNLCList, nonLabourCostForm);
	}
	return nonLabourCostForm;

    }

    @Override
    public Integer saveSolutionNLC(NonLabourCost nonLabourCost)
	    throws MSSPException {
	Integer id = -1;
	try {
	    // NonLabourCost nonLabourCost =
	    // convertNLRDTO2Entity(nonLabourCostDTO);
	    id = solutionDAO.saveObjectReturnId(nonLabourCost);
	} catch (Exception e) {
	    log.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	    // TODO: handle exception
	}
	return id;
    }

    /**
     * 
     * Description : It will convert passed month number to string month Method
     * Name : convertNumericMonth2Char Input& Output:
     * 
     * @param monthNum
     * @return String
     */
    private String convertNumericMonth2Char(int monthNum) {
	String monthName = "Dec";
	switch (monthNum) {
	case 0:
	    monthName = "Jan";
	    break;
	case 1:
	    monthName = "Feb";
	    break;
	case 2:
	    monthName = "Mar";
	    break;
	case 3:
	    monthName = "Apr";
	    break;
	case 4:
	    monthName = "May";
	    break;
	case 5:
	    monthName = "Jun";
	    break;
	case 6:
	    monthName = "Jul";
	    break;
	case 7:
	    monthName = "Aug";
	    break;
	case 8:
	    monthName = "Sep";
	    break;
	case 9:
	    monthName = "Oct";
	    break;
	case 10:
	    monthName = "Nov";
	    break;
	case 11:
	    monthName = "Dec";
	    break;
	}
	return monthName;

    }

    private int convertChar2NumericMonth(String month) {
	int monthName = month.equals("Jan") ? 0 : month.equals("Feb") ? 1
		: month.equals("Mar") ? 2 : month.equals("Apr") ? 3 : month
			.equals("May") ? 4 : month.equals("Jun") ? 5 : month
			.equals("Jul") ? 6 : month.equals("Aug") ? 7 : month
			.equals("Sep") ? 8 : month.equals("Oct") ? 9 : month
			.equals("Nov") ? 10 : 11;

	return monthName;

    }

    @Override
    public String getOpportunitySteadyMonthYearsStringByOpportunityID(
	    Integer opportunityID) throws MSSPException {
	String delimeter = "|";
	Calendar steadyEndDt = Calendar.getInstance();
	Calendar steadyStartDt = Calendar.getInstance();
	StringBuilder monthYears = new StringBuilder("");
	OpportunityDetail opportunityDetail = null;
	// Month Years capturing code
	try {
	    opportunityDetail = ((Opportunity) solutionDAO.getObject(
		    Opportunity.class, opportunityID)).getOpportunityDetail();
	    log.debug(opportunityDetail.getSteadyStateStartDate() + "|"
		    + opportunityDetail.getSteadyStateEndDate());
	    steadyStartDt.setTime(opportunityDetail.getSteadyStateStartDate());
	    steadyEndDt.setTime(opportunityDetail.getSteadyStateEndDate());
	} catch (Exception e) {
	    logger.info("Opportunity details not found:"
		    + (null != opportunityDetail ? opportunityDetail
			    .getSteadyStateStartDate() : opportunityDetail));
	    return "";
	}
	if (steadyEndDt.compareTo(steadyStartDt) < 0) {
	    log.error("Invalid opportunity time line: Start date:"
		    + steadyStartDt.get(Calendar.DATE) + "-"
		    + steadyStartDt.get(Calendar.MONTH) + "-"
		    + steadyStartDt.get(Calendar.YEAR) + " End year:"
		    + steadyEndDt.get(Calendar.DATE) + "-"
		    + steadyEndDt.get(Calendar.MONTH) + "-"
		    + steadyEndDt.get(Calendar.YEAR));
	    return "";
	}
	int sMonth = steadyStartDt.get(Calendar.MONTH);
	for (int year = steadyStartDt.get(Calendar.YEAR); year <= steadyEndDt
		.get(Calendar.YEAR); year++) {
	    while (sMonth < 12
		    && !(sMonth > steadyEndDt.get(Calendar.MONTH) && year >= steadyEndDt
			    .get(Calendar.YEAR))) {
		monthYears.append(convertNumericMonth2Char(sMonth)).append("-")
			.append(year).append(delimeter);
		sMonth++;
	    }
	    sMonth = 0;
	}
	return monthYears.toString();
    }

    @Override
    public StaffingPlanForm getStaffingPlan(int solutionID)
	    throws MSSPException {

	List<StaffAugDTO> staffingPlanDTOList = null;
	StaffingPlanForm staffingPlanForm = new StaffingPlanForm();
	try {
	    List<StaffingPlan> staffAugList = solutionDAO
		    .getStaffingPlan(solutionID);
	    staffingPlanDTOList = convertFromStaffAugToFrom(staffAugList);
	    staffingPlanForm.setStaffAugDTOList(staffingPlanDTOList);

	} catch (Exception e) {
	    log.info(e.getMessage() + " |  " + e.getCause() + "|",
		    new MSSPException("INVALID USER"));
	    throw new MSSPException();
	}

	return staffingPlanForm;
    }

    protected List<StaffingPlan> convertFromStaffPlanFormTOStaffPlan(
	    StaffingPlanForm staffingPlanForm, int solutionID) {
	StaffingPlan staffingPlan = null;
	List<StaffingPlan> staffingPlanList = new ArrayList<StaffingPlan>();
	List<StaffAugDTO> staffingPlanDtoList = staffingPlanForm
		.getStaffAugDTOList();
	for (StaffAugDTO staffAugDTO : staffingPlanDtoList) {

	    staffingPlan = new StaffingPlan();
	    staffingPlan.setStaffingPlanId(staffAugDTO.getStaffingPlanId());
	    staffingPlan
		    .setDemandRaisedBy(staffAugDTO.getDemandRaisedBy() == null ? null
			    : staffAugDTO.getDemandRaisedBy());
	    staffingPlan
		    .setDemandCreateddate(staffAugDTO.getDemandCreateddate() == null
			    || staffAugDTO.getDemandCreateddate()
				    .equalsIgnoreCase("") ? null
			    : ApplicationPropertiesUtil.string2Date(staffAugDTO
				    .getDemandCreateddate()));
	    staffingPlan.setStatus(staffAugDTO.getStatus() == null ? null
		    : staffAugDTO.getStatus());
	    staffingPlan.setRegion(staffAugDTO.getRegion());
	    staffingPlan.setVertical(staffAugDTO.getVertical());
	    staffingPlan.setEgibu(staffAugDTO.getEgibu());
	    staffingPlan.setClient(staffAugDTO.getClient());
	    staffingPlan.setOpportunity(staffAugDTO.getOpportunity());
	    // staffingPlan.setProjectType(staffAugDTO.getProjectType());

	    staffingPlan
		    .setGttopportunity(staffAugDTO.getGttopportunity() == null ? null
			    : staffAugDTO.getGttopportunity());
	    staffingPlan.setNoDemandedPositions(staffAugDTO
		    .getNoDemandedPositions() == null ? null : staffAugDTO
		    .getNoDemandedPositions());
	    staffingPlan.setAcceptSubcontractors(staffAugDTO
		    .getAcceptSubcontractors() == null ? null : staffAugDTO
		    .getAcceptSubcontractors());
	    staffingPlan.setWinOddsProbability(staffAugDTO
		    .getWinOddsProbability() == null ? null : staffAugDTO
		    .getWinOddsProbability());
	    staffingPlan
		    .setPrimaryLocation(staffAugDTO.getPrimaryLocation() == null ? null
			    : staffAugDTO.getPrimaryLocation());
	    staffingPlan.setSecondaryLocation(staffAugDTO
		    .getSecondaryLocation() == null ? null : staffAugDTO
		    .getSecondaryLocation());
	    staffingPlan
		    .setOnshoreLocation(staffAugDTO.getOnshoreLocation() == null ? null
			    : staffAugDTO.getOnshoreLocation());

	    staffingPlan.setPositionStartdate(staffAugDTO
		    .getPositionStartdate() == null ? null
		    : ApplicationPropertiesUtil.string2Date(staffAugDTO
			    .getPositionStartdate()));
	    staffingPlan
		    .setPositionEnddate(staffAugDTO.getPositionEnddate() == null ? null
			    : ApplicationPropertiesUtil.string2Date(staffAugDTO
				    .getPositionEnddate()));
	    staffingPlan
		    .setOnshoreStartdate(staffAugDTO.getOnshoreStartdate() == null ? null
			    : ApplicationPropertiesUtil.string2Date(staffAugDTO
				    .getOnshoreStartdate()));
	    staffingPlan
		    .setOnshoreEnddate(staffAugDTO.getOnshoreEnddate() == null ? null
			    : ApplicationPropertiesUtil.string2Date(staffAugDTO
				    .getOnshoreEnddate()));

	    staffingPlan.setGradeLow(staffAugDTO.getGradeLow() == null ? null
		    : staffAugDTO.getGradeLow());
	    staffingPlan.setGradeHigh(staffAugDTO.getGradeHigh() == null ? null
		    : staffAugDTO.getGradeHigh());
	    staffingPlan.setJobFamily(staffAugDTO.getJobFamily() == null ? null
		    : staffAugDTO.getJobFamily());
	    JobRole role = new JobRole();
	    role.setJobRoleId(staffAugDTO.getJobRole());
	    staffingPlan.setJobRole(role);
	    staffingPlan
		    .setCompetency(staffAugDTO.getCompetency() == null ? null
			    : staffAugDTO.getCompetency());
	    staffingPlan.setPractice(staffAugDTO.getPractice() == null ? null
		    : staffAugDTO.getPractice());
	    staffingPlan
		    .setPracticeArea(staffAugDTO.getPracticeArea() == null ? null
			    : staffAugDTO.getPracticeArea());
	    staffingPlan
		    .setPrimarySkillSet(staffAugDTO.getPrimarySkillSet() == null ? null
			    : staffAugDTO.getPrimarySkillSet());
	    staffingPlan.setSecondarySkillSet(staffAugDTO
		    .getSecondarySkillSet() == null ? null : staffAugDTO
		    .getSecondarySkillSet());
	    staffingPlan
		    .setTypeofRequest(staffAugDTO.getTypeofRequest() == null ? null
			    : staffAugDTO.getTypeofRequest());
	    staffingPlan
		    .setResourceManager(staffAugDTO.getResourceManager() == null ? null
			    : staffAugDTO.getResourceManager());
	    staffingPlan.setAdditionalInformation(staffAugDTO
		    .getAdditionalInformation() == null ? null : staffAugDTO
		    .getAdditionalInformation());
	    Solution solution = new Solution();
	    solution.setSolutionId(solutionID);
	    staffingPlan.setSolution(solution);
	    staffingPlanList.add(staffingPlan);
	}

	return staffingPlanList;

    }

    @Override
    public List<JobRole> getAllJobRole() {
	return (solutionDAO.getAllJobRole());
    }

    @Override
    public void getAllSolutionAPABySolId(Integer solutionId, Integer oppId,
	    Integer ServiceScopeID, SolutionAPADTO solutionAPADTO,
	    List<SolutionAPA> solutionAPAList) throws MSSPException {

	solutionDAO.getSolutionAPA(solutionId, oppId, ServiceScopeID,
		solutionAPADTO, solutionAPAList);
    }

    @Override
    public List<JobRoleDTO> getJobRoleList() {

	List<JobRole> listJobRolesEntity = solutionDAO.getJobRoleList();
	List<JobRoleDTO> listJobRole = new ArrayList<JobRoleDTO>();
	JobRoleDTO roleDTO = null;
	for (JobRole item : listJobRolesEntity) {
	    roleDTO = new JobRoleDTO();
	    roleDTO.setJobRoleId(item.getJobRoleId());
	    roleDTO.setRole(item.getRole());

	    listJobRole.add(roleDTO);
	}

	return listJobRole;
    }
    
    @Override
    public List<JobRoleStagesDTO> getJobRoleStagesbySolutionID(Integer solutionId) throws MSSPException{
    	List<JobRoleStagesDTO> jobRoleStagesDTOList = new ArrayList<JobRoleStagesDTO>();
    	List<JobRoleStages> jobRoleStagesList = solutionDAO.getJobRoleStagesbySolutionID( solutionId);
    	
    	for(JobRoleStages jrs : jobRoleStagesList){
    		JobRoleStagesDTO dto = new JobRoleStagesDTO();
    		JobRoleDTO jrDTO = new JobRoleDTO();
    		JobStageDTO jsDTO = new JobStageDTO();
    		BeanUtils.copyProperties(jrs.getJobRole(),jrDTO);
    		BeanUtils.copyProperties(jrs.getJobStage(),jsDTO);
    		dto.setJobRoleStagesId(jrs.getJobRoleStagesId());
    		dto.setJobRoleDTO(jrDTO);
    		dto.setJobStageDTO(jsDTO);
    		jobRoleStagesDTOList.add(dto);
    	}
    	
    	return jobRoleStagesDTOList;
    }
    

    @Override
    public List<RateCardDTO> getRateCardList() {
	RateCardDTO rateCardDTO = null;
	JobRoleDTO jobRoleDTO = null;
	JobStageDTO jobStageDTO = null;
	
	JobRoleStagesDTO jobRoleStagesDTO = null;
	
	
	List<RateCardDTO> rateCardDTOs = new ArrayList<RateCardDTO>();
	
	List<RateCard> rateCards = solutionDAO.getRateCards();
	
	for (RateCard item : rateCards) {
	    rateCardDTO = new RateCardDTO();
	    jobRoleDTO = new JobRoleDTO();
	    jobStageDTO = new JobStageDTO();
	    jobRoleStagesDTO = new JobRoleStagesDTO();
	    
	    jobRoleDTO.setJobRoleId(item.getJobRoleStages().getJobRole().getJobRoleId());
	    jobRoleDTO.setRole(item.getJobRoleStages().getJobRole().getRole());
	    
	    jobStageDTO.setJobStageID(item.getJobRoleStages().getJobStage().getJobStageID());

	    jobRoleStagesDTO.setJobRoleDTO(jobRoleDTO);
	    jobRoleStagesDTO.setJobStageDTO(jobStageDTO);
	    
	    rateCardDTO.setJobRoleStages(jobRoleStagesDTO);
	    
	    rateCardDTO.setLocation(item.getLocation());
	    rateCardDTO.setRate(item.getRate());
	    
	    rateCardDTOs.add(rateCardDTO);
	}
	return rateCardDTOs;
    }
    
    
    @Override
    public List<RateCardDTO> getRateCardList(Integer countryID) {
	RateCardDTO rateCardDTO = null;
	JobRoleDTO jobRoleDTO = null;
	JobStageDTO jobStageDTO = null;
	
	JobRoleStagesDTO jobRoleStagesDTO = null;
	
	
	List<RateCardDTO> rateCardDTOs = new ArrayList<RateCardDTO>();
	
	List<RateCard> rateCards = solutionDAO.getRateCards(countryID);
	
	for (RateCard item : rateCards) {
	    rateCardDTO = new RateCardDTO();
	    jobRoleDTO = new JobRoleDTO();
	    jobStageDTO = new JobStageDTO();
	    jobRoleStagesDTO = new JobRoleStagesDTO();
	    
	    jobRoleDTO.setJobRoleId(item.getJobRoleStages().getJobRole().getJobRoleId());
	    jobRoleDTO.setRole(item.getJobRoleStages().getJobRole().getRole());
	    
	    jobStageDTO.setJobStageID(item.getJobRoleStages().getJobStage().getJobStageID());
	    jobStageDTO.setStage(item.getJobRoleStages().getJobStage().getStage());

	    jobRoleStagesDTO.setJobRoleStagesId(item.getJobRoleStages().getJobRoleStagesId());
	    jobRoleStagesDTO.setJobRoleDTO(jobRoleDTO);
	    jobRoleStagesDTO.setJobStageDTO(jobStageDTO);
	    
	    rateCardDTO.setJobRoleStages(jobRoleStagesDTO);
	    
	    rateCardDTO.setGsc(item.getGsc());
	    rateCardDTO.setLocation(item.getLocation());
	    rateCardDTO.setRate(item.getRate());
	    rateCardDTO.setRateCardId(item.getRateCardId());
	    rateCardDTOs.add(rateCardDTO);
	}
	return rateCardDTOs;
    }
    
    
    
    public List<RateCardDTO> getRateCardList1(Integer opportunityID,Integer jobRoleID,String location) {
	RateCardDTO rateCardDTO = null;
	
	JobRoleDTO jobRoleDTO = null;
	JobStageDTO jobStageDTO = null;
	JobRoleStagesDTO jobRoleStagesDTO = null;
	
	Date steadyStateStartDate = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	List<RateCardDTO> rateCardDTOs = new ArrayList<RateCardDTO>();
	String sssd = solutionDAO.getSteadyStateStartDate(opportunityID);
		try {
			steadyStateStartDate = sdf.parse(sssd);
		} catch (java.text.ParseException e) {
			logger.info(e);
		}
	
	List<RateCard> rateCards = solutionDAO.getRateCards(opportunityID,jobRoleID,location);
	for (RateCard item : rateCards) {
	    rateCardDTO = new RateCardDTO();
	    
	    jobRoleDTO = new JobRoleDTO();
	    jobStageDTO = new JobStageDTO();
	    jobRoleStagesDTO = new JobRoleStagesDTO();

	    if(null == item.getUpdatedTimestamp()){
	    	if(!steadyStateStartDate.before(item.getCreatedTimestamp())){
	    	//logger.info(" ----------insdie null for rate card : " + item.getRateInr());
	    	
	    		jobRoleDTO.setJobRoleId(item.getJobRoleStages().getJobRole().getJobRoleId());
		    jobRoleDTO.setRole(item.getJobRoleStages().getJobRole().getRole());

		    jobRoleStagesDTO.setJobRoleDTO(jobRoleDTO);
		    
		    rateCardDTO.setLocation(item.getLocation());
		    rateCardDTO.setRate(item.getRate());
		    rateCardDTO.setCreatedTimestamp(item.getCreatedTimestamp());
		    rateCardDTO.setUpdatedTimestamp(item.getUpdatedTimestamp());
		    
		    rateCardDTO.setJobRoleStages(jobRoleStagesDTO);
		    rateCardDTOs.add(rateCardDTO);
		    }else{
		    	continue;
		    }
	    }
	    else if(steadyStateStartDate.after(item.getUpdatedTimestamp())){
	    	//logger.info(" ----------insdie continue for rate card : " + item.getRateInr());
	    	continue;
	    }else if((steadyStateStartDate.compareTo(item.getUpdatedTimestamp())<0) && (steadyStateStartDate.compareTo(item.getCreatedTimestamp())>0)){
	    	//logger.info(" ----------insdie less date for rate card : " + item.getRateInr());
	    	jobRoleDTO.setJobRoleId(item.getJobRoleStages().getJobRole().getJobRoleId());
		    jobRoleDTO.setRole(item.getJobRoleStages().getJobRole().getRole());

		    jobRoleStagesDTO.setJobRoleDTO(jobRoleDTO);
		    
		    rateCardDTO.setLocation(item.getLocation());
		    rateCardDTO.setRate(item.getRate());
		    rateCardDTO.setCreatedTimestamp(item.getCreatedTimestamp());
		    rateCardDTO.setUpdatedTimestamp(item.getUpdatedTimestamp());
		    
		    rateCardDTO.setJobRoleStages(jobRoleStagesDTO);
		    rateCardDTOs.add(rateCardDTO);
	    }
	}
	return rateCardDTOs;
    }
    
    private Map<Integer,Double> getRateCardMap(List<RateCardDTO> rateCardDTOs,String location){
		
		Map<Integer,Double> map = new HashMap<Integer,Double>();
		for(RateCardDTO rateCardDTO : rateCardDTOs){
			if(location.equalsIgnoreCase(rateCardDTO.getLocation()))
			if(rateCardDTO.getJobRoleStages() != null){
				map.put(rateCardDTO.getJobRoleStages().getJobRoleStagesId(), rateCardDTO.getRate());
			}
		}
		return map;
	}
    
    @Autowired
	private SolutionLeverService solutionLeverService;
    
    @Override
    public Map<String,Map<String,String[]>> getFinalList(Integer solutionID,
	    Integer opportunityID, Integer opportunityScopeID,String gsc ) {

    	System.out.println("inside get final list");
    	String[] t = null;
    	String monthYear = null;
    	String[] monthYearString = null;
    	boolean isSubmitted = false;
    	
    	
    	Map<String,Map<String,String[]>> finalList = null;
    	
    	
    	List<String[]> pppRateToBeDisplayed = new ArrayList<String[]>();
    	List<String[]> gsciRateToBeDisplayed = new ArrayList<String[]>();
    	List<String[]> localRateToBeDisplayed = new ArrayList<String[]>();
    	List<String[]> offShoreRateToBeDisplayed = new ArrayList<String[]>();
    	List<String[]> nearShoreRateToBeDisplayed = new ArrayList<String[]>();
	
	
    	float hoursPerMonth = new Float(ApplicationPropertiesUtil.getProperty("msg.labourcost.hoursmonth"));
	
    	try {
    		
    		SolutionDTO dto = solutionDAO.getSolutionDetail(solutionID);
    		Integer countryID = solutionDAO.getOpportunity(opportunityID).getCustomerDTO().getCountryDTO().getCountryId();
    		List<RateCardDTO> rateCardDTOs = loadRateCardList(countryID);
    		Map <Integer, Double> onshoreRateCardMap = getRateCardMap(rateCardDTOs,"onshore");
    		Map <Integer, Double> offshoreRateCardMap = getRateCardMap(rateCardDTOs,"offshore");
    		Map <Integer, Double> nearshoreRateCardMap = getRateCardMap(rateCardDTOs,"nearshore");
    		
    		Integer statusId = dto.getStatus().getStatusId();
    		
    		String solSubmittedDate = null;
    		
    		if(statusId == 2){
    			isSubmitted = true;
    			solSubmittedDate = (dto.getSubmissionDate().toString()).split(" ")[0];
    		}
    		   		
    		List<FTEStagingDTO> fteStagingData = solutionDAO.getFTEStagingData(solutionID,opportunityScopeID);
    		
    		logger.info("fte staging data : " + fteStagingData.size());
    		
    		HashMap<Integer,String> jobRolesForCurrentServiceScope = new HashMap<Integer,String>();
    		
    		for(FTEStagingDTO stagingDTO : fteStagingData){
    			
    			String roleName = stagingDTO.getJobRoleStagesDTO().getJobRoleDTO().getRole() +"(" +stagingDTO.getJobRoleStagesDTO().getJobStageDTO().getStage() +")";
    			
    			jobRolesForCurrentServiceScope.put(stagingDTO.getJobRoleStagesDTO().getJobRoleStagesId(), roleName);
    			
    		}
    		
    		monthYear = getOpportunitySteadyMonthYearsStringByOpportunityID(opportunityID);
    		monthYearString = monthYear.split("\\|");
    		
    		String[] rateOffShore;
    		String[] rateNearShore;
    		String[] rate3PP;
    		String[] rateGscI;
    		String[] rateLocal;
    		
    		Collection<Integer> ids = jobRolesForCurrentServiceScope.keySet();
    		Integer[] idsArray = ids.toArray(new Integer[jobRolesForCurrentServiceScope.size()]);
    		
    		double totalLaborCostforSelectedOpportunityScopeID = 0.0;
    		
    		for(int jobRoleCounter = 0 ; jobRoleCounter < jobRolesForCurrentServiceScope.size(); jobRoleCounter++){
    			
    			rateOffShore = new String[monthYearString.length];
    			rateNearShore = new String[monthYearString.length];
    			rate3PP = new String[monthYearString.length];
    			rateGscI = new String[monthYearString.length];
    			rateLocal = new String[monthYearString.length];
    			
    		for(FTEStagingDTO stagingDTO : fteStagingData){
    			
    			if(stagingDTO.getJobRoleStagesDTO().getJobRoleStagesId() ==  idsArray[jobRoleCounter])
    			for(int opportuntiyInterval = 0; opportuntiyInterval < monthYearString.length; opportuntiyInterval++){
    				
						int currentYear;
						int currentMonth;
						int valueYear = 0;
						int valueMonth = 0;
						
						
						t = monthYearString[opportuntiyInterval].split("-");
						valueYear = Integer.parseInt(t[1]);
						valueMonth = convertChar2NumericMonth(t[0]);

						String[] currentDateString = stagingDTO.getMonthYear().toString().split("-");
						
						currentYear = Integer.parseInt(currentDateString[0]);
						currentMonth = Integer.parseInt(currentDateString[1]);
						
						if (valueYear == currentYear)
						{
							if ((valueMonth + 1) == currentMonth)
							{
								if (stagingDTO.getLocation().equalsIgnoreCase("onshore")) 
								{
									if(null != stagingDTO.getSubLocation())
									if(stagingDTO.getSubLocation().equalsIgnoreCase("3pp")){
										double rate = 0.0;
										double finalRate = 0.0;
										/* need to un-comment later
										 * if(onshoreRateCardMap.get(stagingDTO.getJobRoleStagesDTO().getJobRoleStagesId()) != null){
											rate = onshoreRateCardMap.get(stagingDTO.getJobRoleStagesDTO().getJobRoleStagesId());
										}*/
										rate = solutionDAO.getRateForCurrentJobRole(countryID,idsArray[jobRoleCounter],stagingDTO.getLocation(),stagingDTO.getSubLocation(),gsc,isSubmitted,solSubmittedDate);
										finalRate = hoursPerMonth * rate	* stagingDTO.getFtecount();
										if(finalRate == 0.0){
											break;
										}
										if(finalRate < 0.0){
											finalRate = 0.0;
										}
										totalLaborCostforSelectedOpportunityScopeID = totalLaborCostforSelectedOpportunityScopeID + finalRate;
										rate3PP[opportuntiyInterval] = String.valueOf(finalRate);
																				
									}else if(stagingDTO.getSubLocation().equalsIgnoreCase("gsci")){
										double rate = 0.0;
										double finalRate = 0.0;
										/* need to un-comment later
										 * if(onshoreRateCardMap.get(stagingDTO.getJobRoleStagesDTO().getJobRoleStagesId()) != null){
											rate = onshoreRateCardMap.get(stagingDTO.getJobRoleStagesDTO().getJobRoleStagesId());
										}*/
										rate = solutionDAO.getRateForCurrentJobRole(countryID,idsArray[jobRoleCounter],stagingDTO.getLocation(),stagingDTO.getSubLocation(),gsc,isSubmitted,solSubmittedDate);
										finalRate = hoursPerMonth * rate	* stagingDTO.getFtecount();
										if(finalRate == 0.0){
											break;
										}
										if(finalRate < 0.0){
											finalRate = 0.0;
										}
										totalLaborCostforSelectedOpportunityScopeID = totalLaborCostforSelectedOpportunityScopeID + finalRate;
										rateGscI[opportuntiyInterval] = String.valueOf(finalRate);
										
									}else if(stagingDTO.getSubLocation().equalsIgnoreCase("local")){
										double rate = 0.0;
										double finalRate = 0.0;
										/* need to un-comment later
										 * if(onshoreRateCardMap.get(stagingDTO.getJobRoleStagesDTO().getJobRoleStagesId()) != null){
											rate = onshoreRateCardMap.get(stagingDTO.getJobRoleStagesDTO().getJobRoleStagesId());
										}*/
										rate = solutionDAO.getRateForCurrentJobRole(countryID,idsArray[jobRoleCounter],stagingDTO.getLocation(),stagingDTO.getSubLocation(),gsc,isSubmitted,solSubmittedDate);
										finalRate = hoursPerMonth * rate	* stagingDTO.getFtecount();
										if(finalRate == 0.0){
											break;
										}
										if(finalRate < 0.0){
											finalRate = 0.0;
										}
										totalLaborCostforSelectedOpportunityScopeID = totalLaborCostforSelectedOpportunityScopeID + finalRate;
										rateLocal[opportuntiyInterval] = String.valueOf(finalRate);
										
									}
								}
								else if(stagingDTO.getLocation().equalsIgnoreCase("offshore")) 
								{
									double rate = 0.0;
									double finalRate = 0.0;
									
									rate = solutionDAO.getRateForCurrentJobRole(countryID,idsArray[jobRoleCounter],stagingDTO.getLocation(),stagingDTO.getSubLocation(),gsc,isSubmitted,solSubmittedDate);
									finalRate = hoursPerMonth * rate	* stagingDTO.getFtecount();
									if(finalRate == 0.0){
										break;
									}
									if(finalRate < 0.0){
										finalRate = 0.0;
									}
									totalLaborCostforSelectedOpportunityScopeID = totalLaborCostforSelectedOpportunityScopeID + finalRate;
									rateOffShore[opportuntiyInterval] = String.valueOf(finalRate);
								}
								else if (stagingDTO.getLocation().equalsIgnoreCase("nearshore")){
									double rate = 0.0;
									double finalRate = 0.0;
									
									rate = solutionDAO.getRateForCurrentJobRole(countryID,idsArray[jobRoleCounter],stagingDTO.getLocation(),stagingDTO.getSubLocation(),gsc,isSubmitted,solSubmittedDate);
									finalRate = hoursPerMonth * rate	* stagingDTO.getFtecount();
									
									if(finalRate == 0.0){
										break;
									}if(finalRate < 0.0){
										finalRate = 0.0;
									}
									totalLaborCostforSelectedOpportunityScopeID = totalLaborCostforSelectedOpportunityScopeID + finalRate;
									rateNearShore[opportuntiyInterval] = String.valueOf(finalRate);
											//Double.toString(finalRate);
								}
								
								//break;
							} else 
							{
								//logger.info("month did not match");
							}
						} else 
						{
							//logger.info("year did not match");
						}
					
    			}
    		}
    		
    		/*pppRateToBeDisplayed.add(rate3PP);
    		gsciRateToBeDisplayed.add(rateGscI);
    		localRateToBeDisplayed.add(rateLocal);
    		offShoreRateToBeDisplayed.add(rateOffShore);
    		nearShoreRateToBeDisplayed.add(rateNearShore);*/
    		
    		/*boolean flag = false;
    		
    		for(int i=0;i<monthYearString.length;i++){
    			if(rate3PP[i] != null ){
    				flag = true;
    			}
    		}
    		if(flag){
    			pppRateToBeDisplayed.add(rate3PP);
    		}
    		
    		flag = false;
    		for(int i=0;i<monthYearString.length;i++){
    			if(rateGscI[i] != null){
    				flag = true;
    			}
    		}
    		if(flag){
    			gsciRateToBeDisplayed.add(rateGscI);
    		}
    		
    		
    		flag = false;
    		for(int i=0;i<monthYearString.length;i++){
    			if(rateLocal[i] != null){
    				flag = true;
    			}
    		}
    		if(flag){
    			localRateToBeDisplayed.add(rateLocal);
    		}

    			
    		flag = false;
    		for(int i=0;i<monthYearString.length;i++){
    			if(rateOffShore[i] != null){
    				flag = true;
    			}
    		}
    		if(flag){
    			offShoreRateToBeDisplayed.add(rateOffShore);
    		}	
    			
    			
    		flag = false;
    		for(int i=0;i<monthYearString.length;i++){
    			if(rateNearShore[i] != null){
    				flag = true;
    			}
    		}
    		if(flag){
    			nearShoreRateToBeDisplayed.add(rateNearShore);
    		}*/		
        		
        		
        		
        		
    		
    		
    		if((rate3PP != null)){
    			pppRateToBeDisplayed.add(rate3PP);
    		}
    		if((rateGscI != null) && (rateGscI.length > 0)){
    			gsciRateToBeDisplayed.add(rateGscI);
    		}
    		if((rateLocal != null) && (rateLocal.length > 0)){
    			localRateToBeDisplayed.add(rateLocal);
    		}
    		if((rateOffShore != null) && (rateOffShore.length > 0)){
    			offShoreRateToBeDisplayed.add(rateOffShore);
    		}
    		if((rateNearShore != null) && (rateNearShore.length > 0)){
    			nearShoreRateToBeDisplayed.add(rateNearShore);
    		}
    		
    	}
    		
    	logger.info("totalLaborCostforSelectedOpportunityScopeID : " + totalLaborCostforSelectedOpportunityScopeID);
    		
    		List<JobRoleStages> jobRoleList;
    		boolean ccmFLag = false;
    		Integer serviceElementId = solutionLeverService.getServiceElementIdByOppScopeId(opportunityScopeID);
    		jobRoleList = solutionLeverService.getJobRoleByServiceElementId(serviceElementId, ccmFLag);
    		
    	
    		
    		logger.info("size of jobrole list ["+jobRoleList.size()+"]");
    		logger.info("size of job role ["+jobRolesForCurrentServiceScope.size()+"]");
    		
    		Map<String,String[]> f_3pp = new HashMap<String,String[]>();
    		Collection<String> names_3pp = jobRolesForCurrentServiceScope.values();
    		String[] no_3pp = names_3pp.toArray(new String[pppRateToBeDisplayed.size()]);
    		
    		    		
    		for(int onshoreCounter = 0;onshoreCounter < pppRateToBeDisplayed.size();onshoreCounter++)
    		{
    			f_3pp.put(no_3pp[onshoreCounter],pppRateToBeDisplayed.get(onshoreCounter) );
    		}
    		
    		
    		
    		Map<String,String[]> f_gsci = new HashMap<String,String[]>();
    		Collection<String> names_gsci = jobRolesForCurrentServiceScope.values();
    		String[] no_gsci = names_gsci.toArray(new String[gsciRateToBeDisplayed.size()]);
    		for(int onshoreCounter = 0;onshoreCounter < gsciRateToBeDisplayed.size();onshoreCounter++)
    		{
    			f_gsci.put(no_gsci[onshoreCounter],gsciRateToBeDisplayed.get(onshoreCounter) );
    		}
    		
    		Map<String,String[]> f_local = new HashMap<String,String[]>();
    		Collection<String> names_local = jobRolesForCurrentServiceScope.values();
    		String[] no_local = names_local.toArray(new String[localRateToBeDisplayed.size()]);
    		for(int onshoreCounter = 0;onshoreCounter < localRateToBeDisplayed.size();onshoreCounter++)
    		{
    			f_local.put(no_local[onshoreCounter],localRateToBeDisplayed.get(onshoreCounter) );
    		}
    		
    		Map<String,String[]> f_ns = new HashMap<String,String[]>();
    		Collection<String> names_ns = jobRolesForCurrentServiceScope.values();
    		String[] no_ns = names_ns.toArray(new String[nearShoreRateToBeDisplayed.size()]);
    		for(int onshoreCounter = 0;onshoreCounter < nearShoreRateToBeDisplayed.size();onshoreCounter++)
    		{
    			f_ns.put(no_ns[onshoreCounter],nearShoreRateToBeDisplayed.get(onshoreCounter) );
    		}
    		    		
    		Map<String,String[]> f_offs = new HashMap<String,String[]>();
    		Collection<String> names_offs = jobRolesForCurrentServiceScope.values();
    		String[] no_offs = names_offs.toArray(new String[offShoreRateToBeDisplayed.size()]);
    		for(int offshoreCounter = 0;offshoreCounter < offShoreRateToBeDisplayed.size();offshoreCounter++)
    		{
    			f_offs.put(no_offs[offshoreCounter],offShoreRateToBeDisplayed.get(offshoreCounter) );
    		}
    		
    		Map<String,String[]> totalLaborCostforslectedOppSocpeID = new HashMap<String, String[]>();
    		String[] totalLabotCost_selectedOppScopeID = new String[1];
    		totalLabotCost_selectedOppScopeID[0] = String.valueOf(totalLaborCostforSelectedOpportunityScopeID);
    				//Double.toString(totalLaborCostforSelectedOpportunityScopeID);
    		totalLaborCostforslectedOppSocpeID.put("totalforSelectedOppScopeID", totalLabotCost_selectedOppScopeID);
    		
    		finalList = new HashMap<String,Map<String,String[]>>();
    		finalList.put("3pp",f_3pp);
    		finalList.put("gsci",f_gsci);
    		finalList.put("local",f_local);
    		finalList.put("offShore",f_offs);
    		finalList.put("nearShore",f_ns);
    		finalList.put("totalforSelectedOppScopeID", totalLaborCostforslectedOppSocpeID);
    		
    	
	} catch (Exception e) {
		e.printStackTrace();
	    logger.info(e);
	}

	return finalList;
    }

    @Override
    public String validateUserEditAccess(Integer solId, String userId,String selectedRole)
	    throws MSSPException {
	logger.info("validateUserEditAccess......");
	String returnStr="false";
	SolutionDTO solutionDTO = getSolutionDetail(solId);
		if ("ROLE_SUPER_USER".equalsIgnoreCase(selectedRole)
				|| "ROLE_SOLUTION_MANAGER".equalsIgnoreCase(selectedRole)) {
			
			if (solutionDTO.getCreatedBy().equalsIgnoreCase(userId)) {
				logger.info("user has no access to edit the solution....");
				returnStr="true";	
			}
		}

	return returnStr;
    }

    @Override
    public String getSelOpportunityScopes(Integer oppId) throws MSSPException {
	String selServiceScopes = "";

	List<OpportunityScopeDTO> opportunityScopes = getOpportunityScopes(oppId);

	for (OpportunityScopeDTO oppScope : opportunityScopes) {
	    Integer id = oppScope.getServiceScopeDTO().getServiceScopeId();
	    if (id != null) {
		selServiceScopes += id + "_" + oppScope.getOpportunityScopeId()
			+ "_"
			+ oppScope.getServiceScopeDTO().getServiceScopeName()
			+ ";";
	    }
	}
	if (!"".equals(selServiceScopes)) {
	    selServiceScopes = selServiceScopes.substring(0,
		    selServiceScopes.length() - 1);
	}
	return selServiceScopes;
    }

    @Override
    public List<OpportunityScopeDTO> loadAllOpportunityScopesByOppotunityId(
	    Integer oppId) throws MSSPException {

	return solutionDAO.loadAllOpportunityScopesByOppotunityId(oppId);
    }

    @Override
    public List<OpportunitySourceDTO> getOpportunitySources() {

	List<OpportunitySourceDTO> opportunitySourceDTOs = new ArrayList<OpportunitySourceDTO>();
	List<OpportunitySource> opportunitySources = solutionDAO
		.getOpportunitySources();
	OpportunitySourceDTO opportunitySourceDTO;
	for (OpportunitySource opportunitySource : opportunitySources) {

	    opportunitySourceDTO = new OpportunitySourceDTO();

	    opportunitySourceDTO.setDescription(opportunitySource
		    .getDescription());
	    opportunitySourceDTO.setName(opportunitySource.getName());
	    opportunitySourceDTO.setOpportunitySourceId(opportunitySource
		    .getOpportunitySourceId());
	    opportunitySourceDTO.setOrderId(opportunitySource.getOrderId());

	    opportunitySourceDTOs.add(opportunitySourceDTO);
	}

	return opportunitySourceDTOs;
    }

    @Override
    public List<CommercialModelDTO> getCommercialModels() {

	List<CommercialModelDTO> commercialModelDTOs = new ArrayList<CommercialModelDTO>();
	List<CommercialModel> commercialModels = solutionDAO
		.getCommercialModels();
	CommercialModelDTO commercialModelDTO;
	for (CommercialModel commercialModel : commercialModels) {

	    commercialModelDTO = new CommercialModelDTO();

	    commercialModelDTO.setCommercialModelId(commercialModel
		    .getCommercialModelId());
	    commercialModelDTO.setDescription(commercialModel.getDescription());
	    commercialModelDTO.setName(commercialModel.getName());
	    commercialModelDTO.setOrderId(commercialModel.getOrderId());

	    commercialModelDTOs.add(commercialModelDTO);
	}
	return commercialModelDTOs;
    }

    @Override
    public List<DeliveryModelDTO> getDeliveryModels() {

	List<DeliveryModelDTO> deliveryModelDTOs = new ArrayList<DeliveryModelDTO>();
	List<DeliveryModel> deliveryModels = solutionDAO.getDeliveryModels();
	DeliveryModelDTO deliveryModelDTO;
	for (DeliveryModel deliveryModel : deliveryModels) {

	    deliveryModelDTO = new DeliveryModelDTO();

	    deliveryModelDTO.setDeliveryModelId(deliveryModel
		    .getDeliveryModelId());
	    deliveryModelDTO.setDescription(deliveryModel.getDescription());
	    deliveryModelDTO.setName(deliveryModel.getName());

	    deliveryModelDTOs.add(deliveryModelDTO);
	}
	return deliveryModelDTOs;
    }

    @Override
    public List<DeliveryTypeModelDTO> getDeliveryTypeModels() {

	List<DeliveryTypeModelDTO> deliveryTypeModelDTOs = new ArrayList<DeliveryTypeModelDTO>();
	List<DeliveryType> deliveryTypes = solutionDAO.getDeliveryTypeModels();
	DeliveryTypeModelDTO deliveryTypeModelDTO;
	for (DeliveryType deliveryType : deliveryTypes) {

	    deliveryTypeModelDTO = new DeliveryTypeModelDTO();

	    deliveryTypeModelDTO.setDeliveryTypeId(deliveryType
		    .getDeliveryTypeId());
	    deliveryTypeModelDTO.setDescription(deliveryType.getDescription());
	    deliveryTypeModelDTO.setName(deliveryType.getName());

	    deliveryTypeModelDTOs.add(deliveryTypeModelDTO);
	}
	return deliveryTypeModelDTOs;
    }

    @Override
    public List<ScopeDefinedByDTO> getScopeDefinedBy() {

	List<ScopeDefinedByDTO> scopeDefinedByDTOs = new ArrayList<ScopeDefinedByDTO>();
	List<ScopeOfServicesDefinedBy> scopeOfServicesDefinedBies = solutionDAO
		.getScopeDefinedBy();
	ScopeDefinedByDTO scopeDefinedByDTO;
	for (ScopeOfServicesDefinedBy servicesDefinedBy : scopeOfServicesDefinedBies) {

	    scopeDefinedByDTO = new ScopeDefinedByDTO();

	    scopeDefinedByDTO
		    .setDescription(servicesDefinedBy.getDescription());
	    scopeDefinedByDTO.setName(servicesDefinedBy.getName());
	    scopeDefinedByDTO.setScopeOfServicesDefinedById(servicesDefinedBy
		    .getScopeOfServicesDefinedById());

	    scopeDefinedByDTOs.add(scopeDefinedByDTO);
	}
	return scopeDefinedByDTOs;
    }

    @Override
    public List<VolumetricsDefinedByDTO> getVolumetricsDefinedBy() {

	List<VolumetricsDefinedByDTO> volumetricsDefinedByDTOs = new ArrayList<VolumetricsDefinedByDTO>();
	List<InputVolumetricsDefinedBy> volumetricsDefinedBies = solutionDAO
		.getVolumetricsDefinedBy();
	VolumetricsDefinedByDTO volumetricsDefinedByDTO;
	for (InputVolumetricsDefinedBy volumetricsDefinedBy : volumetricsDefinedBies) {

	    volumetricsDefinedByDTO = new VolumetricsDefinedByDTO();

	    volumetricsDefinedByDTO.setDescription(volumetricsDefinedBy
		    .getDescription());
	    volumetricsDefinedByDTO
		    .setInputVolumetricsDefinedById(volumetricsDefinedBy
			    .getInputVolumetricsDefinedById());
	    volumetricsDefinedByDTO.setName(volumetricsDefinedBy.getName());

	    volumetricsDefinedByDTOs.add(volumetricsDefinedByDTO);
	}
	return volumetricsDefinedByDTOs;
    }

	@Override
	public boolean updateTable(String file,String filePath,String tableName) {
		return solutionDAO.updateTable(file,filePath,tableName);
	}

	@Override
	public List<Float> getExchangeRateList(Integer opportunityID) {
		List<ExchangeRateDTO> exchangeRates = solutionDAO.getExchangeRateList();
		List<Float> finalRates = new ArrayList<Float>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date steadyStateStartDate = null;
		String sssd = solutionDAO.getSteadyStateStartDate(opportunityID);
		try {
			steadyStateStartDate = sdf.parse(sssd);
		} catch (java.text.ParseException e) {
			logger.info(e);
		}
		for (ExchangeRateDTO exchangeRateDTO : exchangeRates) {
			if((null == exchangeRateDTO.getEndDate())&&(steadyStateStartDate.compareTo(exchangeRateDTO.getStartDate())>0)){
				finalRates.add(exchangeRateDTO.getUSD());
				finalRates.add(exchangeRateDTO.getPound());
				finalRates.add(exchangeRateDTO.getDollarRs());
				finalRates.add(exchangeRateDTO.getPoundRs());
				finalRates.add(exchangeRateDTO.getDollarPound());
				finalRates.add(exchangeRateDTO.getPoundDollar());
			}else if((steadyStateStartDate.compareTo(exchangeRateDTO.getStartDate())>0) && ((steadyStateStartDate.compareTo(exchangeRateDTO.getEndDate())<0))){
				finalRates.add(exchangeRateDTO.getUSD());
				finalRates.add(exchangeRateDTO.getPound());
				finalRates.add(exchangeRateDTO.getDollarRs());
				finalRates.add(exchangeRateDTO.getPoundRs());
				finalRates.add(exchangeRateDTO.getDollarPound());
				finalRates.add(exchangeRateDTO.getPoundDollar());
			}
		}
		logger.info("********** final  exchange rate list with size : " + finalRates.size());
		return finalRates; 
	}

	@Override
	public String getCurrencyCode(Integer oppportunityID) {
		return solutionDAO.getCurrencyCode(oppportunityID);
	}
	
	@Override
	public void saveLaborCost(LaborCostDTO lcDTO){
		 solutionDAO.saveLaborCost(lcDTO);
	}

	@Override
	public Map<String,Object> getServiceScopeNamesFteHeadCountAndPercentage(Integer solutionID) {
		
		Map<Integer,String> serviceScopeNames = solutionDAO.getServiceScopeNames(solutionID);
		List<FteHeadCountAndPercentageDTO> fteHeadCountDTOs = solutionDAO.getFteHeadCount(solutionID);
	//	List<FteHeadCountAndPercentageDTO> percentageDTOs = solutionDAO.getDistributionPercentage(solutionID);
		
		/*Map<String, Double> percentageMap = new HashMap<String, Double>();
		for(FteHeadCountAndPercentageDTO pcDTO : percentageDTOs){
				percentageMap = new HashMap<String, Double>();
				String key = pcDTO.getOpportunityScopeID()+"_"+pcDTO.getJobRoleID()+"_"+pcDTO.getYear();
				percentageMap.put(key,pcDTO.getDistributionPercentage());
		}*/
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("SSN", serviceScopeNames);
		map.put("FHC", fteHeadCountDTOs);
		//map.put("DPC", percentageMap);
		
		return map;
	}
	
	/**
	 * Returns all Service Elements
	 * 
	 * @return HashMap<String,List<ServiceElementDTO>>
	 * @throws DAOException
	 */
	public HashMap<String, List<ServiceElementDTO>> getAllServiceElement(String serviceType)
			throws MSSPException {
		HashMap<String, List<ServiceElementDTO>> serviceElementDTOMap = null;

		try {
			List<ServiceElement> serviceElementList = solutionDAO
					.getAllServiceElement(serviceType);

			serviceElementDTOMap = setSEEntityIntoSEDTO(serviceElementList);

		} catch (Exception e) {
			log.info(":::::: " + e.getMessage() + " ::::: " + e.getCause()
					+ " ...", new MSSPException("INVALID USER"));
			throw new MSSPException();
		}
		return serviceElementDTOMap;
	}

	private HashMap<String, List<ServiceElementDTO>> setSEEntityIntoSEDTO(
			List<ServiceElement> serviceElementList) {
		HashMap<String, List<ServiceElementDTO>> serviceElementMap = new HashMap<String, List<ServiceElementDTO>>();

		List<ServiceElementDTO> list = new ArrayList<ServiceElementDTO>();
		ServiceElementDTO dto;
		for (ServiceElement serviceElement : serviceElementList) {
			dto = new ServiceElementDTO();
			dto.setServiceElementID(serviceElement.getServiceElementID());
			dto.setServiceElementName(serviceElement.getServiceElementName());
			dto.setServiceElementDescription(serviceElement
					.getServiceElementDescription());
			dto.setHasServiceScope(serviceElement.getHasServiceScope());

			if(serviceElementMap.get(serviceElement.getServiceFunctionName()) == null){
				list = new ArrayList<ServiceElementDTO>();
			}
			list.add(dto);
			
			serviceElementMap
					.put(serviceElement.getServiceFunctionName(), list);
		}

		return serviceElementMap;
	}
	
	@Override
    public HashMap<String, List<ServiceScopeDTO>> getServiceScopeByServiceElement(String[] serviceElements) throws MSSPException {
	HashMap<String, List<ServiceScopeDTO>> serviceScopeDTOList = null;

	StringBuilder serviceElementsBuilder = new StringBuilder();
	for(String serviceElement: serviceElements){
		serviceElementsBuilder.append(serviceElement).append(MSSPConstants.COMMA_SEPARATOR);
	}
	if (serviceElements!=null && serviceElements.length != 0){
		serviceElementsBuilder.deleteCharAt(serviceElementsBuilder.length()-1);
	}
	
	String commaSeparatedServiceElements = serviceElementsBuilder.toString();
			
	try {
	    List<ServiceScope> list = solutionDAO.getServiceScopeByServiceElement(commaSeparatedServiceElements);

	    serviceScopeDTOList = setSSEntityIntoSSDTOMap(list);

	} catch (Exception e) {
	    log.info(":::::: " + e.getMessage() + " ::::: " + e.getCause()
		    + " ...", new MSSPException("INVALID USER"));
	    throw new MSSPException();
	    // TODO: handle exception
	}
	return serviceScopeDTOList;

    }
	
	private HashMap<String, List<ServiceScopeDTO>> setSSEntityIntoSSDTOMap(
			List<ServiceScope> serviceScopeList) {
		HashMap<String, List<ServiceScopeDTO>> serviceScopeMap = new HashMap<String, List<ServiceScopeDTO>>();

		List<ServiceScopeDTO> list = new ArrayList<ServiceScopeDTO>();
		ServiceScopeDTO dto;
		for (ServiceScope serviceScope : serviceScopeList) {
			dto = new ServiceScopeDTO();
			dto.setServiceScopeId(serviceScope.getServiceScopeId());
			dto.setServiceScopeCategory(("N".equalsIgnoreCase(serviceScope.getServiceElement().getHasServiceScope()))
					? MSSPConstants.SERVICE_ELEMENT_HAS_NO_SERVICE_SCOPE
					: serviceScope.getServiceScopeCategory());
			dto.setServiceScopeName(serviceScope.getServiceScopeName());
			dto.setServiceElementName(serviceScope.getServiceElement()
					.getServiceElementName());
			dto.setServiceScopeDescription(serviceScope.getServiceScopeDescription());

			if (serviceScopeMap.get(dto.getServiceScopeCategory()) == null) {
				list = new ArrayList<ServiceScopeDTO>();
			}else{
				list = serviceScopeMap.get(dto.getServiceScopeCategory());
			}
			list.add(dto);

			serviceScopeMap.put(dto.getServiceScopeCategory(), list);
		}

		return serviceScopeMap;
	}
	
	@Override
	public String searchLDAPUsers(String signumId) {
		// TODO Auto-generated method stub
		List<UserAccessDTO> resultSet = solutionDAO.searchLDAPUsers(signumId);
		String ldapUserName ="";
		if(resultSet.size() >0){
		ldapUserName = resultSet.get(0).getFirstName()+" "+resultSet.get(0).getLastName();
		ldapUserName += " ( " +resultSet.get(0).getSignumId()+ " )";
		} 
		return ldapUserName;
	}

	@Override
	public List<JobRoleDTO> getJobRoleListForCCM(Integer flag) {
		
		List<JobRoleDTO> jobRoleDTOs = new ArrayList<JobRoleDTO>();
		
		List<JobRole> jobRoles = solutionDAO.getJobRoleListForCCM(flag);
		
		for(JobRole jr : jobRoles){
			
			JobRoleDTO dto = new JobRoleDTO();
			
			try{
			
				org.apache.commons.beanutils.BeanUtils.copyProperties( dto, jr);
			
			jobRoleDTOs.add(dto);
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		return jobRoleDTOs;
	}

	@Override
	public List<RateCardDTO> loadRateCardList(Integer countryID) {
		
		List<RateCardDTO> rateCardDTOs= new ArrayList<RateCardDTO>();
		
		List<RateCard> rateCards = solutionDAO.loadRateCards(countryID);
		
		RateCardDTO rateCardDTO = null;
		JobRoleStagesDTO jobRoleStagesDTO = null;
		CountryDTO countryDTO = null;
		
		for(RateCard ratecard : rateCards){
			
			rateCardDTO = new RateCardDTO();
			jobRoleStagesDTO = new JobRoleStagesDTO();
			countryDTO = new CountryDTO();
			
			BeanUtils.copyProperties(ratecard.getJobRoleStages(),jobRoleStagesDTO);
			BeanUtils.copyProperties(ratecard.getCountry(),countryDTO);		
			
			rateCardDTO.setJobRoleStages(jobRoleStagesDTO);
			rateCardDTO.setCountry(countryDTO);
			
			rateCardDTO.setRateCardId(ratecard.getRateCardId());
			rateCardDTO.setRate(ratecard.getRate());
			rateCardDTO.setCreatedBy(ratecard.getCreatedBy());
			rateCardDTO.setCreatedTimestamp(ratecard.getCreatedTimestamp());
			rateCardDTO.setGsc(ratecard.getGsc());
			rateCardDTO.setLocation(ratecard.getLocation());
			rateCardDTO.setStatusFlag(ratecard.getStatusFlag());
			rateCardDTO.setSubLocation(ratecard.getSubLocation());
			rateCardDTO.setUpdatedBy(ratecard.getUpdatedBy());
			rateCardDTO.setUpdatedTimestamp(ratecard.getUpdatedTimestamp());
			
			//BeanUtils.copyProperties(ratecard,rateCardDTO);
						
			rateCardDTOs.add(rateCardDTO);
		}
		
		return rateCardDTOs;
	}

	@Override
	public List<FTEStagingDTO> getFteStagingData(Integer solId,
			Integer serviceScopeId) {
		
		List<FTEStagingDTO> fteStagingDTOs = solutionDAO.getFTEStagingData(solId, serviceScopeId);
		
		return fteStagingDTOs;
	}

	@Override
	public List<JobStageDTO> getJobStages(Integer jobRoleID) {
		List<JobStageDTO> jobStageDTOs = new ArrayList<>();
		List<JobRoleStages> jobStages = solutionDAO.getJobStages(jobRoleID);
		
		for(JobRoleStages jobRoleStage : jobStages){
			JobStageDTO stageDTO = new JobStageDTO();
			
			stageDTO.setJobStageID(jobRoleStage.getJobStage().getJobStageID());
			stageDTO.setcCMFlag(jobRoleStage.getJobStage().isCCMFlag());
			stageDTO.setStage(jobRoleStage.getJobStage().getStage());
			
			jobStageDTOs.add(stageDTO);
		}
		
		return jobStageDTOs;
	}	
	
	public void updateServiceType(Integer oppId, String serviceType) throws Exception{
		
		solutionDAO.updateServiceType(oppId , serviceType);
	}

	@Override
	public Map<String, Float> getUtilizationPerYearDefaultValues() {
		return solutionDAO.getUtilizationPerYearDefaultValues();
	}


	@Override
	public List<CountryDTO> getGscCountries() {
		List<CountryDTO> countriesList = getCountries();
		List<CountryDTO> gscCountriesList= new ArrayList<>();
		for(CountryDTO dto : countriesList){
			if(dto.getCountryName().equalsIgnoreCase("india") || 
					dto.getCountryName().equalsIgnoreCase("romania") || 
					dto.getCountryName().equalsIgnoreCase("china") || 
					dto.getCountryName().equalsIgnoreCase("mexico")){
				CountryDTO countryDTO = new CountryDTO();
				try {
					BeanUtils.copyProperties(dto,countryDTO);
					gscCountriesList.add(countryDTO);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return gscCountriesList;
	}

	@Override
	public List<ComponentDTO> getAllComponents() {
		List<Component> components = solutionDAO.getAllComponents();
		List<ComponentDTO> componentsDTOs = new ArrayList<ComponentDTO>();
		for(Component obj : components){
			ComponentDTO componentDTO = new ComponentDTO();
			BeanUtils.copyProperties(obj,componentDTO);
			componentsDTOs.add(componentDTO);
		}
		return componentsDTOs;
	}

	@Override
	public void saveOpportunityComponent(Integer oppId, String[] componentIDs) throws MSSPException{
		List<Integer> newComponentsList = convertStrArrToIntList(componentIDs);
		List<Integer> oldComponentList = getOldComponentList(oppId);
		if(oldComponentList != null && oldComponentList.size() > 0){
			
			Map<String,List<Integer>> statusMap = compareComponents(oldComponentList,newComponentsList);
			List<Integer> deletedComponents = statusMap.get("deletedComponents");
			List<Integer> addedComponents = statusMap.get("addedComponents");
			
			if(deletedComponents != null && deletedComponents.size() > 0){
				solutionDAO.removeOpportunityCompByOppId(oppId,deletedComponents);
			}
			
			if(addedComponents != null && addedComponents.size() > 0){
				List<OpportunityComponent> entityList = populateOpportunityComponentList(oppId,addedComponents);
				solutionDAO.saveOpportunityComponent(entityList);
			}
			
		}else{
				// Saving for the first time. When No entries in OppComp table for this opportunity.
				List<OpportunityComponent> entityList = populateOpportunityComponentList(oppId,newComponentsList);
				solutionDAO.saveOpportunityComponent(entityList);
			}
		
		// first deleting already present records.
		//solutionDAO.removeOpportunityCompByOppId(oppId);
		
		// after deleting the old records, adding new ones.
		//solutionDAO.saveOpportunityComponent(entitylist);
		
	}
	
	private List<Integer> convertStrArrToIntList(String[] newComponents){
		List<Integer> list = new ArrayList<Integer>();
		for(String str : newComponents){
			list.add(Integer.parseInt(str));
		}
		return list;
	}
	
	private List<Integer> getOldComponentList(Integer oppId) throws MSSPException{
		List<Integer> list = new ArrayList<Integer>();
		List<OpportunityComponent> oldComponentsList = solutionDAO.getComponentsByOpportunityID(oppId);
		if(oldComponentsList != null && oldComponentsList.size() > 0){
		for(OpportunityComponent comp : oldComponentsList){
			list.add(comp.getComponent().getComponentID());
		}
		}
		return list;
	}
	
	private Map<String,List<Integer>> compareComponents(List<Integer> oldComponents, List<Integer> newComponents){
		Map<String,List<Integer>> statusMap = new HashMap<String, List<Integer>>();
		List<Integer> deletedComponents = new ArrayList<Integer>();
		List<Integer> addedComponents = new ArrayList<Integer>();
		
		// calculating deleted components
			for(Integer oldC : oldComponents){
				boolean isExist = false;
				for(Integer newC : newComponents){
					if(oldC.equals(newC)){
						isExist = true;
					}
				}
				if(!isExist){
					deletedComponents.add(oldC);
				}
			}
			
			
			// calculating added components.
			for(Integer newC : newComponents){
				boolean isExist = false;
				for(Integer oldC : oldComponents){
					if(newC.equals(oldC)){
						isExist = true;
					}
				}
				if(!isExist){
					addedComponents.add(newC);
				}
			}
		statusMap.put("deletedComponents", deletedComponents);
		statusMap.put("addedComponents", addedComponents);
		return statusMap;
	}

	private List<OpportunityComponent> populateOpportunityComponentList(Integer oppId, List<Integer> componentIDs){
		List<Component> componentList = solutionDAO.getSelectedComponents(componentIDs);
		
		List<OpportunityComponent> entitylist = new ArrayList<OpportunityComponent>();
		for(Component component : componentList){
			OpportunityComponent entity = new OpportunityComponent();
			Opportunity opportunity = new Opportunity();
			opportunity.setOpportunityId(oppId);
			entity.setComponent(component);
			entity.setOpportunity(opportunity);
			entitylist.add(entity);
		}
		return entitylist;
	}
	
	@Override
	public List<OpportunityComponentDTO> getComponentsByOpportunityID(Integer oppId) throws MSSPException{
		List<OpportunityComponentDTO> listDto = new ArrayList<OpportunityComponentDTO>();
		List<OpportunityComponent> list = solutionDAO.getComponentsByOpportunityID(oppId);
		for(OpportunityComponent comp : list){
			OpportunityComponentDTO dto = new OpportunityComponentDTO();
			BeanUtils.copyProperties(comp,dto);
			listDto.add(dto);
		}
		return listDto;
	}
	
	@Override
	public SolutionLever getSolutionLever(Integer solnId) throws MSSPException {
		//SolutionLeverDTO solutionLeverDto = null;
		SolutionLever solutionLever = null; 
		try {
			
			solutionLever = solutionLeverDAO.getSolutionLever(solnId);

		} catch (Exception e) {
			log.info(":::::: " + e.getMessage() + " ::::: " + e.getCause()
					+ " ...", new MSSPException("INVALID OpportunityScopeID"));
			throw new MSSPException();
		}
		
		return solutionLever;
	}
	

	@Override
	public List<ProductDetailsDTO> fetchProductList() throws MSSPException {
		List<ProductDetailsDTO> resultList = new ArrayList<>();
		ProductDetailsDTO tempObject = null;
		
		List<ProductDetails> productList = solutionDAO.fetchProductList();
		
		for(ProductDetails eachProduct: productList){
			tempObject = new ProductDetailsDTO();
			BeanUtils.copyProperties(eachProduct, tempObject);
			resultList.add(tempObject);
		}
		
		return resultList;
	}

	@Override
	public List<ComponentDTO> getComponentByProduct(Integer productID)
			throws MSSPException {
		List<ComponentDTO> componentsDTOs = new ArrayList<ComponentDTO>();
		ComponentDTO componentDTO = null;
		String[] ignoreProperties = new String[] { "productDetails","productID"};

		List<Component> components = solutionDAO.getComponentByProduct(productID);
		
		for(Component eachObject : components){
			componentDTO = new ComponentDTO();
			BeanUtils.copyProperties(eachObject,componentDTO,ignoreProperties);
			componentDTO.setProductID(eachObject.getProductDetails().getProductID());
			componentsDTOs.add(componentDTO);
		}
		return componentsDTOs;
	}

	@Override
	public void saveOpportunityScopesFromServiceElements(Integer oppId,
			String[] selectedServiceElements) throws MSSPException {
		
		StringBuilder serviceElementsBuilder = new StringBuilder();
		for(String serviceElement: selectedServiceElements){
			serviceElementsBuilder.append(serviceElement).append(MSSPConstants.COMMA_SEPARATOR);
		}
		if (selectedServiceElements!=null && selectedServiceElements.length != 0){
			serviceElementsBuilder.deleteCharAt(serviceElementsBuilder.length()-1);
		}		
		String commaSeparatedServiceElements = serviceElementsBuilder.toString();
				
		solutionDAO.saveOpportunityScopesFromServiceElements(oppId, commaSeparatedServiceElements);
	}

	@Override
	public List<Integer> getOpportunityComponents(Integer oppId)
			throws MSSPException {
		List<OpportunityComponent> compList = solutionDAO.getComponentsByOpportunityID(oppId);
		List<Integer> resultList = new ArrayList<>();
		
		for(OpportunityComponent eachObject: compList){
			resultList.add(eachObject.getComponent().getComponentID());
		}
		
		return resultList;
	}
}
