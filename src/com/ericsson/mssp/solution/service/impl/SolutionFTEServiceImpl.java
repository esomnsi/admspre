/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.service.impl
 * File name       		    :  SolutionFTEServiceImpl.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Mar 19, 20132013
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Mar 19, 2013  	   |edassri   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.LocationBreakupDTO;
import com.ericsson.mssp.common.dto.MonthFTEDTO;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.entity.AdditionalFTE;
import com.ericsson.mssp.common.entity.FTEStaging;
import com.ericsson.mssp.common.entity.FTESummary;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.JobStage;
import com.ericsson.mssp.common.entity.LocationBreakup;
import com.ericsson.mssp.common.entity.LocationPyramid;
import com.ericsson.mssp.common.entity.Opportunity;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.ProductivityLever;
import com.ericsson.mssp.common.entity.ServiceElement;
import com.ericsson.mssp.common.entity.ServiceElementJobRoleStages;
import com.ericsson.mssp.common.entity.ServiceScope;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionLever;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.jobrole.management.dao.IJobRoleManagementDAO;
import com.ericsson.mssp.solution.dao.IOpportunityScopeDAO;
import com.ericsson.mssp.solution.dao.ISolutionDAO;
import com.ericsson.mssp.solution.dao.ISolutionFTEDAO;
import com.ericsson.mssp.solution.dao.ISolutionLeverDAO;
import com.ericsson.mssp.solution.forms.ReviewFTEForm;
import com.ericsson.mssp.solution.service.ISolutionFTEService;

/**
 * @author edassri
 * 
 */
@Service
public class SolutionFTEServiceImpl implements ISolutionFTEService,
	MSSPConstants {

    private static final Logger logger = Logger
	    .getLogger(SolutionServiceImpl.class);
    private HashMap<Integer, Map<String, Object>> reviewFTEData = null;
    DecimalFormat df = new DecimalFormat(DECIMAL_POINTS_STRING);
    private static double storedStartUpFTE = -1;
    @Autowired
    private ISolutionFTEDAO solutionFTEDAO;
    
    @Autowired
    private ISolutionDAO solutionDAO;
    
    @Autowired
    private ISolutionLeverDAO solutionLeverDAO;
    
    @Autowired
    private IOpportunityScopeDAO opportunityScopeDAO;
    
    @Autowired
    private com.ericsson.mssp.solution.dao.IJobRoleStagesDAO JobRoleStagesDAO;

    @Override
    public Map<Integer, String> getAllServiceScopeInMapBySolId(
	    Integer solutionID) throws MSSPException {
	Map<Integer, String> serviceScopeDTOMap = null;

	try {
	    serviceScopeDTOMap = getAllServiceScopeInMapByOppID(getOpportunityBySolId(
		    solutionID).getOpportunityId());
	    // System.out.println(serviceScopeDTOMap);
	} catch (Exception e) {
	    logger.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());

	}
	return serviceScopeDTOMap;

    }

    @Override
    public Opportunity getOpportunityBySolId(Integer solutionID)
	    throws MSSPException {
	return (Opportunity) solutionFTEDAO
		.getObject(Opportunity.class, getSolutionById(solutionID)
			.getOpportunity().getOpportunityId());
    }

    @Override
    public Map<Integer, String> getAllServiceScopeInMapByOppID(
	    Integer opportunityId) throws MSSPException {
	Map<Integer, String> serviceScopeDTOMap = null;

	try {
	    serviceScopeDTOMap = solutionFTEDAO
		    .loadAllSelectedOppSSIdServiceScopes(opportunityId);
	} catch (Exception e) {
	    logger.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	}
	return serviceScopeDTOMap;

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
    public void deleteAdditionalFTE(AdditionalFTE additionalFTE)
	    throws MSSPException {
	solutionFTEDAO.removeObject(AdditionalFTE.class,
		additionalFTE.getAdditionalFteid());
    }

    /**
     * 
     * Description : FTEStaging object will be returned by loading additionalFTE
     * object values except MonthYear value Method Name :
     * getFTEStagingFromAdditionalFTE Input& Output:
     * 
     * @param additionalFTE
     * @return FTEStaging
     * @throws MSSPException
     */
    private FTEStaging getFTEStagingFromAdditionalFTE(
	    AdditionalFTE additionalFTE) throws MSSPException {
	FTEStaging fteStaging = new FTEStaging();
	fteStaging.setFtecount(additionalFTE.getNoofFte());
	/*JobRoleStages jobRoleStage = new JobRoleStages();
	jobRoleStage.setJobRoleStagesId(additionalFTE.getJobRoleStageID().getJobRoleStagesId());*/
	fteStaging.setJobRoleStage(additionalFTE.getJobRoleStageID());
	fteStaging.setLocation(additionalFTE.getLocation());
	fteStaging.setSubLocation(additionalFTE.getSubLocation());
	// fteStaging.setMonthYear(date);
	OpportunityScope opportunityScope = new OpportunityScope();
	opportunityScope.setOpportunityScopeId(additionalFTE
		.getOpportunityScopeId());
	fteStaging.setOpportunityScope(opportunityScope);
	fteStaging.setSolution(additionalFTE.getSolution());
	return fteStaging;
    }

    /**
     * 
     * Description : Converting to GUI required format Method Name :
     * convert2FTEDataList Input& Output:
     * 
     * @param fteStagingList
     * @return List<String>
     */
    private List<String> convert2FTEDataList(List<FTEStaging> fteStagingList)
	    throws MSSPException {
	List<String> fTEDataList = new ArrayList<String>(5);
	Integer jobRoleStageID = fteStagingList.get(0).getJobRoleStage().getJobRoleStagesId();
	StringBuilder jobRoles = new StringBuilder(fteStagingList.get(0)
		.getJobRoleStage().getJobRole().getRole()).append(" ").append(fteStagingList.get(0)
		.getJobRoleStage().getJobStage().getStage()).append(";");
	StringBuilder roleFTEValues = new StringBuilder();
	for (FTEStaging fteStaging : fteStagingList) {
	    if (!fteStaging.getJobRoleStage().getJobRoleStagesId().equals(jobRoleStageID)) {
		fTEDataList.add(roleFTEValues.toString());
		roleFTEValues = new StringBuilder();
		roleFTEValues.append(
			fteStaging.getFtecount() < 0 ? "NA" : fteStaging
				.getFtecount()).append(";");
		jobRoles.append(fteStaging.getJobRoleStage().getJobRole().getRole()).append(" ").append(fteStaging
				.getJobRoleStage().getJobStage().getStage()).append(";");
		jobRoleStageID = fteStaging.getJobRoleStage().getJobRoleStagesId();
	    } else {
		roleFTEValues.append(
			fteStaging.getFtecount() < 0 ? "NA" : fteStaging
				.getFtecount()).append(";");
	    }
	}
	fTEDataList.add(roleFTEValues.toString());
	fTEDataList.add(jobRoles.toString());
	return fTEDataList;
    }

    @Override
    public Solution getSolutionById(Integer solID) throws MSSPException {
	return (Solution) solutionFTEDAO.getObject(Solution.class, solID);
    }

    @Override
    public ReviewFTEForm loadFTETableDisplayValues(ReviewFTEForm reviewFTEForm,
	    boolean isForceReload) {
	try {
	    // Pick solution's solution lever ID
	    Integer solutionLeverID = (solutionFTEDAO
		    .getSolutionLeverBySolId(reviewFTEForm.getSolutionID()))
		    .getSolutionLeverId();

	    logger.debug("solutionLeverID=" + solutionLeverID);

	    if (solutionLeverID > 0) {
		getValidFTEDataLoaded(solutionLeverID, reviewFTEForm,
			isForceReload);
	    }

	} catch (Exception e) {
	    logger.error("Exception in loadFTETableDisplayValues:" + e);
	}
	return reviewFTEForm;
    }

    @Override
    public ReviewFTEForm getAddionalFTE(ReviewFTEForm reviewFTEForm) {
	try {
	    List<AdditionalFTE> additionalFTEList = solutionFTEDAO
		    .getAdditionalFTEBySolutionAddFTEIDs(
			    reviewFTEForm.getSolutionID(),
			    reviewFTEForm.getsServiceScope());
	    if (additionalFTEList.size() > 0) {
		AdditionalFTE additionalFTE = additionalFTEList.get(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		reviewFTEForm.setAdditionalFteid(null != additionalFTE
			.getAdditionalFteid() ? additionalFTE
			.getAdditionalFteid() : -1);
		reviewFTEForm
			.setaFTE(null != additionalFTE.getNoofFte() ? additionalFTE
				.getNoofFte() : 0d);
		reviewFTEForm
			.setJobrole(null != additionalFTE.getJobRoleStageID() ? ""
				+ additionalFTE.getJobRoleStageID().getJobRole().getJobRoleId() : "-1");
		reviewFTEForm
		.setJobStage(null != additionalFTE.getJobRoleStageID() ? ""
			+ additionalFTE.getJobRoleStageID().getJobRoleStagesId() : "-1");
		reviewFTEForm
			.setSite(null != additionalFTE.getLocation() ? additionalFTE
				.getLocation() : "");
		reviewFTEForm
			.setfDate(null != additionalFTE.getFromMonthYear() ? sdf
				.format(additionalFTE.getFromMonthYear()) : "");
		reviewFTEForm
			.settDate(null != additionalFTE.getToMonthYear() ? sdf
				.format(additionalFTE.getToMonthYear()) : "");
	    } else {
		reviewFTEForm.setaFTE(0d);
	    }

	} catch (Exception e) {
	    logger.info("Exception in getAddionalFTE:" + e);
	}
	return reviewFTEForm;
    }

    /**
     * 
     * Description : It will validate stored FTE pool data and return FTE value
     * Method Name : getValidFTEDataLoaded Input& Output:
     */
    @SuppressWarnings("unchecked")
    private void getValidFTEDataLoaded(Integer solutionLeverID,
	    ReviewFTEForm reviewFTEForm, boolean isForceReload)
	    throws MSSPException {
	Map<String, Object> storedFTEMap = null;
	try {
	    // if reload request received
	    if (isForceReload) {
		loadFTEPoolValues(solutionLeverID, reviewFTEForm, isForceReload);
		calculateAndSaveServicesFTE(solutionLeverID, reviewFTEForm,
			isForceReload);
		logger.info("FTE pool regeneration - solution ID:"
			+ reviewFTEForm.getSolutionID());
		return;
	    }
	    if (null != reviewFTEData
		    && null != reviewFTEData.get(solutionLeverID)) {
		storedFTEMap = reviewFTEData.get(solutionLeverID);
		if (!isThrAnyChangeInTimeline(solutionLeverID, reviewFTEForm,
			storedFTEMap)
			&& !isThrAnyChangeInServices(solutionLeverID,
				reviewFTEForm, storedFTEMap)) {

		    reviewFTEForm
			    .setOffShoreDataList((List<String>) storedFTEMap
				    .get(LOCATION_OFFSHORE));
		    reviewFTEForm
		    .setOnShoreDataList((List<String>) storedFTEMap
			    .get(LOCATION_ONSHORE));
		    reviewFTEForm
			    .setOnShoreLocalDataList((List<String>) storedFTEMap
				    .get(LOCATION_ONSHORE_LOCAL));
		    reviewFTEForm
		    .setOnShoreGsciDataList((List<String>) storedFTEMap
			    .get(LOCATION_ONSHORE_GSCI));
		    reviewFTEForm
		    .setOnShore3ppDataList((List<String>) storedFTEMap
			    .get(LOCATION_ONSHORE_3PP));
		    reviewFTEForm
		    .setNearShoreDataList((List<String>) storedFTEMap
			    .get(LOCATION_NEARSHORE));
		    reviewFTEForm.setNonEmptyRoles(storedFTEMap.get(
			    FTE_SERVICES).toString());
		    // Try to reload if any of the data provided after cache
		    if (reviewFTEForm.getNonEmptyRoles().equals("")) {
			loadFTEPoolValues(solutionLeverID, reviewFTEForm);
		    }
		    calculateAndSaveServicesFTE(solutionLeverID, reviewFTEForm,
			    false);
		} else {
		    loadFTEPoolValues(solutionLeverID, reviewFTEForm);
		    calculateAndSaveServicesFTE(solutionLeverID, reviewFTEForm,
			    true);
		}
	    } else {
		loadFTEPoolValues(solutionLeverID, reviewFTEForm);
		calculateAndSaveServicesFTE(solutionLeverID, reviewFTEForm,
			false);
	    }
	    // If system is in running state cached FTEPool returned but Summary
	    // Not stored in DB or removed in back end.
	    // Very little chance of its happening
	    if (isFTESummaryDataNotStored(reviewFTEForm.getSolutionID())) {
		loadFTEPoolValues(solutionLeverID, reviewFTEForm);
		logger.info("FTESummary data stored for SolutionID:"
			+ reviewFTEForm.getSolutionID());
	    }
	} catch (Exception e) {
	    logger.error("FTE pool Exception - " + e);
	    // e.printStackTrace();
	    throw new MSSPException(e);
	}
	// System.out.println("Validated FTE pool method return");
	logger.debug("Validated FTE pool method return");
    }

    /**
     * 
     * Description : It will load entity values to DTO Method Name :
     * loadIntoLocationBreakupDTO Input& Output:
     * 
     * @param locationBreakupList
     * @return List<LocationBreakupDTO>
     */
    private List<LocationBreakupDTO> loadIntoLocationBreakupDTO(
	    List<LocationBreakup> locationBreakupList) {
	List<LocationBreakupDTO> locationBreakupDTOList = new LinkedList<LocationBreakupDTO>();
	for (LocationBreakup locationBreakup : locationBreakupList) {
	    LocationBreakupDTO locationBreakupDTO = new LocationBreakupDTO();
	    locationBreakupDTO.setLocationBreakupId(locationBreakup
		    .getLocationBreakupId());
	    locationBreakupDTO.setMonthYear(locationBreakup.getMonthYear());
	    
	    locationBreakupDTO.setOffshoreFte(locationBreakup.getOffshoreFte());
	    locationBreakupDTO.setOffshorePc(locationBreakup.getOffshorePc());
	    
	    locationBreakupDTO.setOnshoreFte(locationBreakup.getOnshoreFte());
	    locationBreakupDTO.setOnshorePc(locationBreakup.getOnshorePc());
	    
	    locationBreakupDTO.setNearshoreFte(locationBreakup.getNearshoreFte());
	    locationBreakupDTO.setNearshorePc(locationBreakup.getNearshorePc());
	    
	    locationBreakupDTO.setOnshoreLocalFte(locationBreakup.getOnshoreLocalFte());
	    locationBreakupDTO.setOnshoreLocalPc(locationBreakup.getOnshoreLocalPc());
	    
	    locationBreakupDTO.setOnshoreGSCiFte(locationBreakup.getOnshoreGSCiFte());
	    locationBreakupDTO.setOnshoreGSCiPc(locationBreakup.getOnshoreGSCiPc());
	    
	    locationBreakupDTO.setOnshore3PPFte(locationBreakup.getOnshore3PPFte());
	    locationBreakupDTO.setOnshore3PPPc(locationBreakup.getOnshore3PPPc());
	    
	    locationBreakupDTO.setOpportunityScope(locationBreakup
		    .getOpportunityScope());
	    locationBreakupDTO.setProcessed(false);
	    locationBreakupDTO.setSolutionLever(locationBreakup
		    .getSolutionLever());
	    locationBreakupDTOList.add(locationBreakupDTO);
	}
	return locationBreakupDTOList;
    }

    private int fillLessTimeLineFTEValues(int index, int loopMonth,
	    int currLocBrkUpMonth, int loopYear, int currLocBrkUpYear,
	    StringBuilder onShoreData, StringBuilder offShoreData,
	    String[] monthYearsArray, double startUpFTE,
	    double[] productivityValues, LocationBreakupDTO pastLocationBreakup) {
	String[] monthYear;
	while (loopMonth < currLocBrkUpMonth || loopYear < currLocBrkUpYear) {
	    startUpFTE -= startUpFTE * productivityValues[index] / 100;
	    if (null != pastLocationBreakup) {
		// Code to continue last break values
		fillMatchingTimeLineFTEValue(pastLocationBreakup, onShoreData,
			offShoreData, startUpFTE);
	    } else {
		onShoreData.append(0).append(";");
		offShoreData.append(df.format(startUpFTE)).append(";");
	    }
	    index++;
	    if (index >= monthYearsArray.length) {
		break;

	    } else {
		monthYear = monthYearsArray[index].split("-");
		loopMonth = convertChar2NumericMonth(monthYear[0]);
		try {
		    loopYear = Integer.parseInt(monthYear[1]);
		} catch (NumberFormatException e) {
		    logger.info("Number format exception for year" + e
			    + " Val-" + monthYear[1]);
		    break;
		    // throw new
		    // NumberFormatException(
		    // "Number format exception for year"
		    // +
		    // e +
		    // " Val-"
		    // + monthYear[1]);
		}
	    }
	}
	storedStartUpFTE = startUpFTE;
	return index;
    }
    
	private int fillLessTimeLineFTEValues(int index, int loopMonth,
			int currLocBrkUpMonth, int loopYear, int currLocBrkUpYear,
			StringBuilder nearShoreData, StringBuilder offShoreData, StringBuilder onShoreData,
			StringBuilder onShoreLocalData, StringBuilder onShoreGsciData,
			StringBuilder onShore3ppData, String[] monthYearsArray,
			double startUpFTE, double[] productivityValues,
			LocationBreakupDTO pastLocationBreakup) {
		String[] monthYear;
		while (loopMonth < currLocBrkUpMonth || loopYear < currLocBrkUpYear) {
			startUpFTE -= startUpFTE * productivityValues[index] / 100;
			if (null != pastLocationBreakup) {
				// Code to continue last break values
				fillMatchingTimeLineFTEValue(pastLocationBreakup,
						nearShoreData, onShoreData, onShoreLocalData, onShoreGsciData,
						onShore3ppData, offShoreData, startUpFTE);
			} else {
				nearShoreData.append(0).append(";");
				onShoreData.append(0).append(";");
				onShoreLocalData.append(0).append(";");
				onShoreGsciData.append(0).append(";");
				onShore3ppData.append(0).append(";");
				offShoreData.append(df.format(startUpFTE)).append(";");
			}
			index++;
			if (index >= monthYearsArray.length) {
				break;

			} else {
				monthYear = monthYearsArray[index].split("-");
				loopMonth = convertChar2NumericMonth(monthYear[0]);
				try {
					loopYear = Integer.parseInt(monthYear[1]);
				} catch (NumberFormatException e) {
					logger.info("Number format exception for year" + e
							+ " Val-" + monthYear[1]);
					break;
					// throw new
					// NumberFormatException(
					// "Number format exception for year"
					// +
					// e +
					// " Val-"
					// + monthYear[1]);
				}
			}
		}
		storedStartUpFTE = startUpFTE;
		return index;
	}

    private void fillMatchingTimeLineFTEValue(
	    LocationBreakupDTO locationBreakup, StringBuilder onShoreData,
	    StringBuilder offShoreData, double startUpFTE) {
	// Value came after % calculated one so removed %calculation bug 1574
	double value = null != locationBreakup
		&& null != locationBreakup.getOnshorePc() ? startUpFTE
		* locationBreakup.getOnshorePc() / 100f : 0f;
	// Keep it to one decimal point
	// value = Math.round(value * DECIMAL_POINTS_NUM) / DECIMAL_POINTS_NUM;
	onShoreData.append(df.format(value)).append(";");
	value = null != locationBreakup
		&& null != locationBreakup.getOffshorePc() ? startUpFTE
		* locationBreakup.getOffshorePc() / 100f : 0f;
	// Set processed
	if (null != locationBreakup) {
	    locationBreakup.setProcessed(true);
	}
	// Keep it to one decimal point
	// value = Math.round(value * DECIMAL_POINTS_NUM) / DECIMAL_POINTS_NUM;
	offShoreData.append(df.format(value)).append(";");
    }
    
	private void fillMatchingTimeLineFTEValue(
			LocationBreakupDTO locationBreakup, StringBuilder nearShoreData, StringBuilder onShoreData,
			StringBuilder onShoreLocalData, StringBuilder onShoreGsciData,
			StringBuilder onShore3ppData, StringBuilder offShoreData,
			double startUpFTE) {

		// Value came after % calculated one so removed %calculation bug 1574
		double value = null != locationBreakup
				&& null != locationBreakup.getNearshorePc() ? startUpFTE
				* locationBreakup.getNearshorePc() / 100f : 0f;
		// Keep it to one decimal point
		// value = Math.round(value * DECIMAL_POINTS_NUM) / DECIMAL_POINTS_NUM;
		nearShoreData.append(df.format(value)).append(";");

		// populate Offshore
		value = null != locationBreakup
				&& null != locationBreakup.getOffshorePc() ? startUpFTE
				* locationBreakup.getOffshorePc() / 100f : 0f;
		// Set processed
		if (null != locationBreakup) {
			locationBreakup.setProcessed(true);
		}
		// Keep it to one decimal point
		// value = Math.round(value * DECIMAL_POINTS_NUM) / DECIMAL_POINTS_NUM;
		offShoreData.append(df.format(value)).append(";");

		// populate Onshore
		value = null != locationBreakup
				&& null != locationBreakup.getOnshorePc() ? startUpFTE
				* locationBreakup.getOnshorePc() / 100f : 0f;
		// Set processed
		if (null != locationBreakup) {
			locationBreakup.setProcessed(true);
		}
		// Keep it to one decimal point
		// value = Math.round(value * DECIMAL_POINTS_NUM) / DECIMAL_POINTS_NUM;
		onShoreData.append(df.format(value)).append(";");
		
		// populate Onshore Local
		value = null != locationBreakup
				&& null != locationBreakup.getOnshoreLocalPc() ? startUpFTE
				* locationBreakup.getOnshoreLocalPc() / 100f : 0f;
		// Set processed
		if (null != locationBreakup) {
			locationBreakup.setProcessed(true);
		}
		// Keep it to one decimal point
		// value = Math.round(value * DECIMAL_POINTS_NUM) / DECIMAL_POINTS_NUM;
		onShoreLocalData.append(df.format(value)).append(";");

		// populate Onshore GSCI
		value = null != locationBreakup
				&& null != locationBreakup.getOnshoreGSCiPc() ? startUpFTE
				* locationBreakup.getOnshoreGSCiPc() / 100f : 0f;
		// Set processed
		if (null != locationBreakup) {
			locationBreakup.setProcessed(true);
		}
		// Keep it to one decimal point
		// value = Math.round(value * DECIMAL_POINTS_NUM) / DECIMAL_POINTS_NUM;
		onShoreGsciData.append(df.format(value)).append(";");

		// populate Onshore 3PP
		value = null != locationBreakup
				&& null != locationBreakup.getOnshore3PPPc() ? startUpFTE
				* locationBreakup.getOnshore3PPPc() / 100f : 0f;
		// Set processed
		if (null != locationBreakup) {
			locationBreakup.setProcessed(true);
		}
		// Keep it to one decimal point
		// value = Math.round(value * DECIMAL_POINTS_NUM) / DECIMAL_POINTS_NUM;
		onShore3ppData.append(df.format(value)).append(";");
	}

    /**
     * 
     * Description : It will store all month wise productivity % values in array
     * Method Name : loadMonthYrProductivityValues Input& Output:
     * 
     * @param productivityLeverList
     * @param solutionLever
     * @param monthYrArray
     * @return double[]
     * @throws MSSPException
     */
    private double[] loadMonthYrProductivityValues(
	    List<ProductivityLever> productivityLeverList,
	    SolutionLever solutionLever, String[] monthYrArray)
	    throws MSSPException {
	double productivityVals[] = new double[monthYrArray.length];
	int yrIndex = 0;
	for (ProductivityLever prodLever : productivityLeverList) {
	    if (null != solutionLever) {
		if (solutionLever.getSolutionLeverApproach().equalsIgnoreCase(
			SOLUTION_APPROACH_UNIFORM)) {
		    List<Date> dates = getDatesArrayByDateRange(
			    convert2Date(monthYrArray[yrIndex]),
			    prodLever.getMonthYear());
//		    double prodVal = prodLever.getComputedProductivity();
//		    prodVal = prodVal / dates.size();
		    int dtIndx = 0;
		    while (dtIndx < dates.size()) {
//			productivityVals[yrIndex] = prodVal;
			yrIndex++;
			dtIndx++;
		    }
		} else {
		    // Code for staircase
		    while (yrIndex < monthYrArray.length
			    && !convert2Date(monthYrArray[yrIndex]).after(
				    prodLever.getMonthYear())) {
			yrIndex++;
		    }
//		    double prodVal = prodLever.getComputedProductivity();
//		    productivityVals[yrIndex - 1] = Double.parseDouble(df
//			    .format(prodVal));
		}
	    }
	}
	return productivityVals;
    }

    /**
     * 
     * Description : It will calculate FTE pool values and stored into cache&DB
     * Method Name : loadFTEPoolValues Input& Output:
     * 
     * @param solutionLeverID
     * @param reviewFTEForm
     * @throws MSSPException
     */
    private void loadFTEPoolValues(Integer solutionLeverID,
	    ReviewFTEForm reviewFTEForm) throws MSSPException {
	loadFTEPoolValues(solutionLeverID, reviewFTEForm, false);
    }

    /**
     * 
     * Description : It will calculate FTE pool values and stored into cache&DB
     * Method Name : loadFTEPoolValues Input& Output:
     * 
     * @param solutionLeverID
     * @param reviewFTEForm
     * @param isForceReload
     * @throws MSSPException
     */
    private void loadFTEPoolValues(Integer solutionLeverID,
	    ReviewFTEForm reviewFTEForm, boolean isForceReload)
	    throws MSSPException {
	double productivityValues[] = null;
	List<LocationBreakup> locationBreakupList = null;
	List<LocationBreakupDTO> locationBreakupDTOList = null;
	String[] monthYearsArray = null;
	boolean isFTESummaryDBDataAbsent = false;
	try {
	    locationBreakupList = solutionFTEDAO
		    .getLocationBreakupBySolLeverId(solutionLeverID);
	    locationBreakupDTOList = loadIntoLocationBreakupDTO(locationBreakupList);
	    isFTESummaryDBDataAbsent = isFTESummaryDataNotStored(reviewFTEForm
		    .getSolutionID());
	    if (isForceReload && !isFTESummaryDBDataAbsent) {
		// Delete FTE Summary old records
		int deletedFTESummaryRecsCount = solutionFTEDAO
			.removeAllFTESummaryBySolutionID(reviewFTEForm
				.getSolutionID());
		logger.info(reviewFTEForm.getSolutionID()
			+ ":solutionID all deleted FTESummary records count is:"
			+ deletedFTESummaryRecsCount);
		isFTESummaryDBDataAbsent = true;
	    }
	    monthYearsArray = reviewFTEForm.getMonthYears().split(";");
	    StringBuilder serviceScopes = new StringBuilder("");
	    List<String> nearshoreFTEDataList = new LinkedList<String>();
	    List<String> onshoreFTEDataList = new LinkedList<String>();
	    List<String> offshoreFTEDataList = new LinkedList<String>();
	    List<String> onshoreLocalFTEDataList = new LinkedList<String>();
	    List<String> onshoreGsciFTEDataList = new LinkedList<String>();
	    List<String> onshore3ppFTEDataList = new LinkedList<String>();

	    // It will return OppScope ID and Service Scope name
	    Map<Integer, String> oppScopeIdSSNamesMap = getAllServiceScopeInMapBySolId(reviewFTEForm
		    .getSolutionID());
	    // // Code to pick type solution
	    SolutionLever solutionLever = solutionFTEDAO
		    .getSolutionLeverBySolId(reviewFTEForm.getSolutionID());
	    // Availed services data collection& pick onshore and offshore data
	    for (Map.Entry<Integer, String> entry : oppScopeIdSSNamesMap
		    .entrySet()) {
		LocationBreakupDTO pastLocationBreakup = null;
		double startUpFTE = solutionFTEDAO
			.getStartupFTEBySolOppScopeID(
				reviewFTEForm.getSolutionID(), entry.getKey());

		// code to pick productivity applied or not
		List<ProductivityLever> productivityLeverList = solutionFTEDAO
			.getProductivityLeversByLeverScopeID(
				solutionLever.getSolutionLeverId(),
				entry.getKey());
		if (null != productivityLeverList
			&& productivityLeverList.size() > 0) {
		    productivityValues = loadMonthYrProductivityValues(
			    productivityLeverList, solutionLever,
			    monthYearsArray);
		} else {
		    productivityValues = new double[monthYearsArray.length];
		}
		serviceScopes.append(entry.getValue()).append(";");
		StringBuilder nearShoreData = new StringBuilder("");
		StringBuilder onShoreData = new StringBuilder("");
		StringBuilder offShoreData = new StringBuilder("");
		StringBuilder onShoreLocalData = new StringBuilder("");
		StringBuilder onShoreGsciData = new StringBuilder("");
		StringBuilder onShore3ppData = new StringBuilder("");
		int index = 0;
		if (index < monthYearsArray.length) {
		    String[] monthYear = monthYearsArray[index].split("-");
		    int loopMonth = convertChar2NumericMonth(monthYear[0]);
		    int loopYear = -1;
		    // boolean isNtAdded = true;
		    try {
			loopYear = Integer.parseInt(monthYear[1]);
		    } catch (NumberFormatException e) {
			logger.info("Number format exception for year" + e
				+ " Val-" + monthYear[1]);
			throw new NumberFormatException(
				"Number format exception for year" + e
					+ " Val-" + monthYear[1]);
		    }
		    int currLocBrkUpMonth = -1;
		    int currLocBrkUpYear = -1;
		    // int oldMonth = -1;
		    // int oldYear = -1;
		    for (LocationBreakupDTO locationBreakup : locationBreakupDTOList) {
			// String formattedMonYr =
			// format2MonthYear(locationBreakup
			// .getMonthYear());
			if (entry.getKey().equals(
				locationBreakup.getOpportunityScope()
					.getOpportunityScopeId())) {
			    Calendar calendar = Calendar.getInstance();
			    calendar.setTime(locationBreakup.getMonthYear());
			    currLocBrkUpMonth = calendar.get(Calendar.MONTH);
			    currLocBrkUpYear = calendar.get(Calendar.YEAR);
			    if (loopYear > 0 && currLocBrkUpMonth >= 0
				    && currLocBrkUpYear > 0) {

				// earlier month year not exist so fill zero
				if (!locationBreakup.isProcessed()
					&& (loopMonth < currLocBrkUpMonth || loopYear < currLocBrkUpYear)) {
				    index = fillLessTimeLineFTEValues(index,
					    loopMonth, currLocBrkUpMonth,
					    loopYear, currLocBrkUpYear,
					    nearShoreData, offShoreData, onShoreData,
					    onShoreLocalData, onShoreGsciData, onShore3ppData,
					    monthYearsArray, startUpFTE,
					    productivityValues,
					    pastLocationBreakup);
				    startUpFTE = storedStartUpFTE > 0 ? storedStartUpFTE
					    : startUpFTE;
				    startUpFTE -= startUpFTE
					    * productivityValues[index] / 100;
				    // Code here for the matching year month
				    fillMatchingTimeLineFTEValue(
					    locationBreakup, nearShoreData, onShoreData,
					    onShoreLocalData, onShoreGsciData, onShore3ppData, offShoreData, startUpFTE);
				    pastLocationBreakup = locationBreakup;
				    index++;

				} else if (!locationBreakup.isProcessed()
					&& loopMonth == currLocBrkUpMonth
					&& loopYear == currLocBrkUpYear) {
				    startUpFTE -= startUpFTE
					    * productivityValues[index] / 100;
				    // Code here for the matching year month
				    fillMatchingTimeLineFTEValue(
					    locationBreakup, nearShoreData, onShoreData,
					    onShoreLocalData, onShoreGsciData, onShore3ppData, offShoreData, startUpFTE);
				    // isNtAdded = false;
				    pastLocationBreakup = locationBreakup;
				    index++;
				    // locationBreakup.setProcessed(true);

				} else {
				    // Code here if month,year is
				    // greater than LocationBreakup year month
				    startUpFTE -= startUpFTE
					    * productivityValues[index] / 100;
				    fillMatchingTimeLineFTEValue(
					    pastLocationBreakup, nearShoreData, onShoreData,
					    onShoreLocalData, onShoreGsciData, onShore3ppData, offShoreData, startUpFTE);
				    index++;
				    // isNtAdded = false;
				}
				monthYear = index<monthYearsArray.length?monthYearsArray[index].split("-"):monthYearsArray[monthYearsArray.length].split("-");
				loopMonth = convertChar2NumericMonth(monthYear[0]);
			    }
			}
		    }

		    // LocationBreakup data completion but loop year month
		    // still
		    // expected fill zeros
		    while (index < monthYearsArray.length) {
			// onShoreData.append(0).append(";");
			startUpFTE -= startUpFTE * productivityValues[index]
				/ 100;
			// offShoreData.append(df.format(startUpFTE)).append(";");
			fillMatchingTimeLineFTEValue(pastLocationBreakup,
				nearShoreData, onShoreData, onShoreLocalData, onShoreGsciData, onShore3ppData, offShoreData, startUpFTE);
			index++;
		    }
		    logger.debug("nearShoreData=" + nearShoreData
				    + " | onShoreData" + onShoreData
			    + " | offShoreData" + offShoreData
			    + " | onShoreLocalData" + onShoreLocalData
			    + " | onShoreGsciData" + onShoreGsciData
			    + " | onShore3ppData" + onShore3ppData);
		    // Save data into FTE summary table for reports and
		    // Non-Labor Cost
		    if (isFTESummaryDBDataAbsent) {
			save2FTESummary(reviewFTEForm, entry.getKey(),
				monthYearsArray, nearShoreData.toString(),
				offShoreData.toString(), onShoreData.toString(), onShoreLocalData.toString(),
				onShoreGsciData.toString(), onShore3ppData.toString());
		    }

		    // System.out.println("onShoreData=" + onShoreData
		    // + " | offShoreData" + offShoreData);
		    nearshoreFTEDataList.add(nearShoreData.toString());
		    onshoreFTEDataList.add(onShoreData.toString());
		    offshoreFTEDataList.add(offShoreData.toString());
		    onshoreLocalFTEDataList.add(onShoreLocalData.toString());
		    onshoreGsciFTEDataList.add(onShoreGsciData.toString());
		    onshore3ppFTEDataList.add(onShore3ppData.toString());
		}
	    }
	    // Demand for over all FTE with non empty found to display

	    reviewFTEForm.setOffShoreDataList(offshoreFTEDataList);
	    reviewFTEForm.setNearShoreDataList(nearshoreFTEDataList);
	    reviewFTEForm.setOnShoreDataList(onshoreFTEDataList);
	    reviewFTEForm.setOnShoreLocalDataList(onshoreLocalFTEDataList);
	    reviewFTEForm.setOnShoreGsciDataList(onshoreGsciFTEDataList);
	    reviewFTEForm.setOnShore3ppDataList(onshore3ppFTEDataList);
	    
	    reviewFTEForm.setNonEmptyRoles(serviceScopes.toString());

	    // Loading data into cache for further processing
	    loadFTEPoolValuesIntoCache(solutionLeverID, offshoreFTEDataList,
		    nearshoreFTEDataList, onshoreFTEDataList, onshoreLocalFTEDataList, onshoreGsciFTEDataList,
		    onshore3ppFTEDataList, serviceScopes, serviceScopes,
		    reviewFTEForm.getMonthYears());

	    // System.out.println("FTE pool Data stored");
	    logger.debug("FTE pool Data stored");

	} catch (Exception e) {
	    logger.error("Exception - " + e);
	    e.printStackTrace();
	    throw new MSSPException(e);
	}
    }

    /**
     * 
     * Description : It will check FTEStaging table data stored earlier or not
     * Method Name : isFTEStagingDataNotStored Input& Output:
     * 
     * @param solutionID
     * @return
     * @throws MSSPException
     */
    private boolean isFTESummaryDataNotStored(Integer solutionID)
	    throws MSSPException {
	boolean isDataAbsent = true;
	List<FTESummary> fteSummaryList = solutionFTEDAO
		.getFTESummaryListBySolutionID(solutionID);
	if (null != fteSummaryList && fteSummaryList.size() > 0) {
	    isDataAbsent = false;
	}
	return isDataAbsent;
    }

    /**
     * 
     * Description : It will save FTESummary bulk data Method Name :
     * save2FTESummary Input& Output:
     * 
     * @param reviewFTEForm
     * @param oppScopeID
     * @param monthYearsArray
     * @param nearShoreDataRow
     * @param offShoreDataRow
     * * @param onShoreDataRow
     * @param onShoreLocalDataRow
     * @param onShoreGsciDataRow
     * @param onShore3ppDataRow
     * @return boolean
     * @throws MSSPException
     */
    private boolean save2FTESummary(ReviewFTEForm reviewFTEForm,
	    Integer oppScopeID, String[] monthYearsArray,
	    String nearShoreDataRow, String offShoreDataRow, String onShoreDataRow,
	    String onShoreLocalDataRow, String onShoreGsciDataRow, String onShore3ppDataRow) throws MSSPException {
	boolean response = true;
	Date monthYear = null;
	FTESummary fTESummaryNearshore = null;
	FTESummary fTESummaryOffshore = null;
	FTESummary fTESummaryOnshore = null;
	FTESummary fTESummaryOnshoreLocal = null;
	FTESummary fTESummaryOnshoreGsci = null;
	FTESummary fTESummaryOnshore3pp = null;
	String nearShoreData[] = nearShoreDataRow.split(";");
	String offShoreData[] = offShoreDataRow.split(";");
	String onShoreData[] = onShoreDataRow.split(";");
	String onShoreLocalData[] = onShoreLocalDataRow.split(";");
	String onShoreGsciData[] = onShoreGsciDataRow.split(";");
	String onShore3ppData[] = onShore3ppDataRow.split(";");
	// String[] monthYearsArray = reviewFTEForm.getMonthYears().split(";");
	try {

	    List<FTESummary> fTESummaryList = new ArrayList<FTESummary>(10);
	    for (int index = 0; index < monthYearsArray.length; index++) {
		float valueNearshore = index < nearShoreData.length
			&& null != nearShoreData[index] ? Float
			.parseFloat(nearShoreData[index]) : 0;
		float valueOffshore = index < offShoreData.length
			&& null != offShoreData[index] ? Float
			.parseFloat(offShoreData[index]) : 0;
		float valueOnshore = index < onShoreData.length
				&& null != onShoreData[index] ? Float
				.parseFloat(onShoreData[index]) : 0;
		float valueOnshoreLocal = index < onShoreLocalData.length
				&& null != onShoreLocalData[index] ? Float
				.parseFloat(onShoreLocalData[index]) : 0;
		float valueOnshoreGsci = index < onShoreGsciData.length
				&& null != onShoreGsciData[index] ? Float
				.parseFloat(onShoreGsciData[index]) : 0;
		float valueOnshore3pp = index < onShore3ppData.length
				&& null != onShore3ppData[index] ? Float
				.parseFloat(onShore3ppData[index]) : 0;
		// if (value > 0) {
		monthYear = convert2Date(null != monthYearsArray[index] ? monthYearsArray[index]
			: null);
		Date[] fromToDates = getMonthFromToDateOfTheDate(monthYear);
		fTESummaryNearshore = new FTESummary();
		fTESummaryOffshore = new FTESummary();
		fTESummaryOnshore = new FTESummary();
		fTESummaryOnshoreLocal = new FTESummary();
		fTESummaryOnshoreGsci = new FTESummary();
		fTESummaryOnshore3pp = new FTESummary();
		
		//fTESummaryNearshore.setFromDate(fromToDates[0]);
		fTESummaryNearshore.setToDate(fromToDates[1]);
		fTESummaryNearshore.setFtecount(valueNearshore);
		//fTESummaryNearshore.setIsSteadyStateType(true);
		fTESummaryNearshore.setSolutionId(reviewFTEForm.getSolutionID());
		//fTESummaryNearshore.setLocation(LOCATION_NEARSHORE);
		OpportunityScope opportunityScope = new OpportunityScope();
		opportunityScope.setOpportunityScopeId(oppScopeID);
		fTESummaryNearshore.setOpportunityScope(opportunityScope);
		Solution solution = new Solution();
		solution.setSolutionId(reviewFTEForm.getSolutionID());
		fTESummaryList.add(fTESummaryNearshore);// Nearshore added
		
		BeanUtils.copyProperties(fTESummaryNearshore, fTESummaryOffshore);
		fTESummaryOffshore.setFtecount(valueOffshore);
	//	fTESummaryOffshore.setLocation(LOCATION_OFFSHORE);
		fTESummaryList.add(fTESummaryOffshore);// Offshore added
		
		BeanUtils.copyProperties(fTESummaryNearshore, fTESummaryOnshore);
		fTESummaryOnshore.setFtecount(valueOnshore);
		//fTESummaryOnshore.setLocation(LOCATION_ONSHORE);
		fTESummaryList.add(fTESummaryOnshore);// Onshore added
		
		BeanUtils.copyProperties(fTESummaryNearshore, fTESummaryOnshoreLocal);
		fTESummaryOnshoreLocal.setFtecount(valueOnshoreLocal);
		//fTESummaryOnshoreLocal.setLocation(LOCATION_ONSHORE);
		//fTESummaryOnshoreLocal.setSubLocation(SUBLOC_LOCAL);
		fTESummaryList.add(fTESummaryOnshoreLocal);// Onshore Local added
		
		BeanUtils.copyProperties(fTESummaryNearshore, fTESummaryOnshoreGsci);
		fTESummaryOnshoreGsci.setFtecount(valueOnshoreGsci);
		//fTESummaryOnshoreGsci.setLocation(LOCATION_ONSHORE);
		//fTESummaryOnshoreGsci.setSubLocation(SUBLOC_GSCI);
		fTESummaryList.add(fTESummaryOnshoreGsci);// Onshore GSCI added
		
		BeanUtils.copyProperties(fTESummaryNearshore, fTESummaryOnshore3pp);
		fTESummaryOnshore3pp.setFtecount(valueOnshore3pp);
		//fTESummaryOnshore3pp.setLocation(LOCATION_ONSHORE);
		//fTESummaryOnshore3pp.setSubLocation(SUBLOC_3PP);
		fTESummaryList.add(fTESummaryOnshore3pp);// Onshore 3PP added
	    }
	    solutionFTEDAO.saveFTESummaryList(fTESummaryList);

	} catch (Exception e) {
	    logger.error("save2FTESummary exception-" + e);
	    response = false;
	    e.printStackTrace();
	    throw new MSSPException(e);
	}
	return response;

    }

    /**
     * 
     * Description : Returns weeks first and last dates from passed date Method
     * Name : getWeekFromToDateOfDate Input& Output:
     * 
     * @return Date[]
     * @throws MSSPException
     */
    private Date[] getWeekFromToDateOfTheDate(Date date) throws MSSPException {
	Date[] fromToDates = new Date[2];
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.add(Calendar.DAY_OF_MONTH, 1 - cal.get(Calendar.DAY_OF_WEEK));
	fromToDates[0] = ((Calendar) cal.clone()).getTime();
	cal.add(Calendar.DAY_OF_MONTH, 7 - cal.get(Calendar.DAY_OF_WEEK));
	fromToDates[1] = ((Calendar) cal.clone()).getTime();
	logger.debug("Passed date:" + date + " Week From to Dates:"
		+ fromToDates);
	return fromToDates;
    }

    /**
     * 
     * Description : First and last dates of the month returned Method Name :
     * getMonthFromToDateOfTheDate Input& Output:
     * 
     * @param calendar
     * @return Calendar[]
     * @throws MSSPException
     */
    private Date[] getMonthFromToDateOfTheDate(Date date) throws MSSPException {
	Date[] fromToDates = new Date[2];
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.add(Calendar.DAY_OF_MONTH, 1 - cal.get(Calendar.DAY_OF_MONTH));
	fromToDates[0] = ((Calendar) cal.clone()).getTime();
	cal.set(Calendar.DAY_OF_MONTH,
		cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	fromToDates[1] = ((Calendar) cal.clone()).getTime();
	logger.debug("Passed date:" + date + " Month From to Dates:"
		+ fromToDates);
	return fromToDates;
    }

    /**
     * 
     * Description : It will cache FTE pool values Method Name :
     * loadFTEPoolValuesIntoCache Input& Output:
     * 
     * @param solutionLeverID
     * @param onshoreFTEDataList
     * @param offshoreFTEDataList
     * @param nonEmpOnshoreFTEDataList
     * @param nonEmpOffshoreFTEDataList
     * @param serviceScopes
     * @param nonEmptyServiceScopes
     * @param monthYears
     */
    private void loadFTEPoolValuesIntoCache(Integer solutionLeverID,
	    List<String> onshoreFTEDataList, List<String> offshoreFTEDataList,
	    StringBuilder serviceScopes, StringBuilder nonEmptyServiceScopes,
	    String monthYears) {
	Map<String, Object> value = new HashMap<String, Object>(3);
	value.put(LOCATION_OFFSHORE, offshoreFTEDataList);
	value.put(LOCATION_ONSHORE, onshoreFTEDataList);
	value.put(FTE_SERVICES, serviceScopes.toString());
	value.put(FTE_TIMELINE, monthYears);
	if (null == reviewFTEData) {
	    reviewFTEData = new HashMap<Integer, Map<String, Object>>(1);
	}
	reviewFTEData.put(solutionLeverID, value);
	logger.info(solutionLeverID + " Solution lever FTE pool cached");
    }
    
    /**
     * 
     * Description : It will cache FTE pool values Method Name :
     * loadFTEPoolValuesIntoCache Input& Output:
     * 
     * @param solutionLeverID
     * @param offshoreFTEDataList
     * @param nearshoreFTEDataList
     * @param onshoreFTEDataList
     * @param onshoreLocalFTEDataList
     * @param onshoreGsciFTEDataList
     * @param onshore3ppFTEDataList
     * @param serviceScopes
     * @param nonEmptyServiceScopes
     * @param monthYears
     */
	private void loadFTEPoolValuesIntoCache(Integer solutionLeverID,
			List<String> offshoreFTEDataList,
			List<String> nearshoreFTEDataList,
			List<String> onshoreFTEDataList,
			List<String> onshoreLocalFTEDataList,
			List<String> onshoreGsciFTEDataList,
			List<String> onshore3ppFTEDataList, StringBuilder serviceScopes,
			StringBuilder nonEmptyServiceScopes, String monthYears) {
		Map<String, Object> value = new HashMap<String, Object>(3);
		value.put(LOCATION_OFFSHORE, offshoreFTEDataList);
		value.put(LOCATION_ONSHORE, onshoreFTEDataList);
		value.put(LOCATION_ONSHORE_LOCAL, onshoreLocalFTEDataList);
		value.put(LOCATION_ONSHORE_GSCI, onshoreGsciFTEDataList);
		value.put(LOCATION_ONSHORE_3PP, onshore3ppFTEDataList);
		value.put(LOCATION_NEARSHORE, nearshoreFTEDataList);
		value.put(FTE_SERVICES, serviceScopes.toString());
		value.put(FTE_TIMELINE, monthYears);
		if (null == reviewFTEData) {
			reviewFTEData = new HashMap<Integer, Map<String, Object>>(1);
		}
		reviewFTEData.put(solutionLeverID, value);
		logger.info(solutionLeverID + " Solution lever FTE pool cached");
	}

    /**
     * 
     * Description : It will check change in timeline during cache acts of FTE
     * Method Name : isThrAnyChangeInTimeline Input& Output:
     * 
     * @param solutionLeverID
     * @param reviewFTEForm
     * @param storedFTEMap
     * @return boolean
     * @throws MSSPException
     */
    private boolean isThrAnyChangeInTimeline(Integer solutionLeverID,
	    ReviewFTEForm reviewFTEForm, Map<String, Object> storedFTEMap)
	    throws MSSPException {
	boolean isChanged = false;
	String timeline = null;
	try {
	    storedFTEMap = reviewFTEData.get(solutionLeverID);
	    timeline = storedFTEMap.get(FTE_TIMELINE).toString();
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new MSSPException(e);
	}
	if (!timeline.equalsIgnoreCase(reviewFTEForm.getMonthYears())) {
	    isChanged = true;
	}
	return isChanged;
    }

    /**
     * 
     * Description : It will check if there will be any change in Services
     * during caching acts Method Name : isThrAnyChangeInServices Input& Output:
     * 
     * @param solutionLeverID
     * @param reviewFTEForm
     * @param storedFTEMap
     * @return boolean
     * @throws MSSPException
     */
    private boolean isThrAnyChangeInServices(Integer solutionLeverID,
	    ReviewFTEForm reviewFTEForm, Map<String, Object> storedFTEMap)
	    throws MSSPException {
	boolean isChanged = false;
	Map<Integer, String> oppScopeIdSSNamesMap = null;
	String services = "";
	String sysCurrServices = "";
	try {
	    storedFTEMap = reviewFTEData.get(solutionLeverID);
	    services = storedFTEMap.get(FTE_SERVICES).toString();
	    oppScopeIdSSNamesMap = getAllServiceScopeInMapBySolId(reviewFTEForm
		    .getSolutionID());
	    StringBuilder sysServices = new StringBuilder("");
	    for (Map.Entry<Integer, String> mapVal : oppScopeIdSSNamesMap
		    .entrySet()) {
		sysServices.append(mapVal.getValue()).append(";");
	    }
	    sysCurrServices = sysServices.toString();
	    if (!services.equalsIgnoreCase(sysCurrServices)) {
		isChanged = true;
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return isChanged;
    }

    /**
     * 
     * Description : It takes string with ; separated values and index to pick
     * float FTE value to return Method Name : getIndexFTEValue Input& Output:
     * 
     * @param str
     * @param indx
     * @return float
     */
    private float getIndexFTEValue(String str, int indx) {
	float val = 0f;
	try {
	    String[] ftes = str.split(";");
	    val = Float.parseFloat(ftes[indx]);

	} catch (NumberFormatException e) {
	    logger.info("NumberFormatException - String- " + str + " Index-"
		    + indx);
	    e.printStackTrace();
	} catch (Exception e) {
	    logger.info("Exception - String- " + str + " Index-" + indx);
	    e.printStackTrace();
	}
	return val;
    }

    @SuppressWarnings("unchecked")
    private List<String> getCacheFTEPoolBySolutionLeverLocation(
	    Integer solutionLeverID, String location) {
	return (List<String>) reviewFTEData.get(solutionLeverID).get(location);
    }

    private double fillServiceFTEValue(LocationPyramid locationPyramid,
	    StringBuilder dataRow, double locationFTEVal) {
	double value = null != locationPyramid
		&& null != locationPyramid.getDistributionPc() ? (locationPyramid
		.getDistributionPc() * locationFTEVal / 100)
		: -1;
	dataRow.append(df.format(value)).append(";");
	return value;
    }

    /**
     * 
     * Description : This will load all FTE values role wise break up based on
     * selected service Method Name : getServiceFTEDataLoaded Input& Output:
     * 
     * @param solutionLeverID
     * @param
     */
    private void getServiceFTEDataLoaded(Integer solutionLeverID,
	    int serviceIndex, int oppScopeID, ReviewFTEForm reviewFTEForm)
	    throws MSSPException {
	List<LocationPyramid> locationPyramidOnshoreLocalList = null;
	List<LocationPyramid> locationPyramidOffShoreList = null;
	List<LocationPyramid> locationPyramidOnShoreList = null;
	List<LocationPyramid> locationPyramidOnshoreGSCIList = null;
	List<LocationPyramid> locationPyramidOnshore3PPList = null;
	List<LocationPyramid> locationPyramidNearShoreList = null;
	try {
	    List<String> offshoreFTEPoolList = getCacheFTEPoolBySolutionLeverLocation(
		    solutionLeverID, LOCATION_OFFSHORE);
	    List<String> onshoreFTEPoolList = getCacheFTEPoolBySolutionLeverLocation(
			    solutionLeverID, LOCATION_ONSHORE);
	    List<String> nearshoreFTEPoolList = getCacheFTEPoolBySolutionLeverLocation(
		    solutionLeverID, LOCATION_NEARSHORE);
	    List<String> onshoreLocalFTEPoolList = getCacheFTEPoolBySolutionLeverLocation(
			    solutionLeverID, LOCATION_ONSHORE_LOCAL);
	    List<String> onshoreGsciFTEPoolList = getCacheFTEPoolBySolutionLeverLocation(
			    solutionLeverID, LOCATION_ONSHORE_GSCI);
	    List<String> onshore3PPFTEPoolList = getCacheFTEPoolBySolutionLeverLocation(
			    solutionLeverID, LOCATION_ONSHORE_3PP);
	    
	    String[] monthYearsArray = reviewFTEForm.getMonthYears().split(";");
	    StringBuilder serviceRoles = new StringBuilder("");
	    
	    Map<Integer, String> rolesMap = new LinkedHashMap<Integer, String>();
	    
	    if(reviewFTEForm.getsServiceScope() != -1){
		    Integer serviceElementId = 0;

		    if(MSSPConstants.CCM_JOB_ROLE_MODEL.toLowerCase().equals(reviewFTEForm.getSolutionJobRoleModel().toLowerCase())){
			    serviceElementId = (getServiceElementIdByOppScopeId(reviewFTEForm.getsServiceScope())).getServiceElementID();
		    }else{
		    	serviceElementId = MSSPConstants.SERVICE_ELEMENT_FOR_CCM_JOB_ROLES;
		    }
	
		    rolesMap = getJobRoleByServiceElementId(serviceElementId);
		}else{
			int ccmFlag = MSSPConstants.JOB_ROLE_NON_CCM_FLAG;
			if(MSSPConstants.CCM_JOB_ROLE_MODEL.toLowerCase().equals(reviewFTEForm.getSolutionJobRoleModel().toLowerCase())){
				ccmFlag = MSSPConstants.JOB_ROLE_CCM_FLAG;
			}
			rolesMap = getJobRoles(ccmFlag);
	    }

	    // Availed roles data collection& pick onshore and offshore
	    if (serviceIndex >= 0) {
		// load FTE pool value of the selected service
		float nearShoreFTEVal = 0f;
		float onShoreFTEVal = 0f;
		float offShoreFTEVal = 0f;
		float onShoreLocalFTEVal = 0f;
		float onShoreGsciFTEVal = 0f;
		float onShore3PPFTEVal = 0f;

		// rotate it for all roles inside the service
		for (Map.Entry<Integer, String> entry : rolesMap.entrySet()) {
		    // LocationPyramid lastOffshoreLocationPyramid = null;
		    LocationPyramid lastLocationPyramid = null;
		    // boolean isAllNonEmpty = false;
		    serviceRoles.append(entry.getValue()).append(";");
		    
		    StringBuilder nearShoreData = new StringBuilder("");
		    StringBuilder onShoreData = new StringBuilder("");
		    StringBuilder offShoreData = new StringBuilder("");
		    StringBuilder onShoreLocalData = new StringBuilder("");
		    StringBuilder onShoreGsciData = new StringBuilder("");
		    StringBuilder onShore3PPData = new StringBuilder("");
		    
		    locationPyramidNearShoreList = solutionFTEDAO
			    .getLocationPyramidBySolLeverId(solutionLeverID,
				    oppScopeID, entry.getKey(),
				    LOCATION_NEARSHORE);
		    locationPyramidOnShoreList = solutionFTEDAO
				    .getLocationPyramidBySolLeverId(solutionLeverID,
					    oppScopeID, entry.getKey(),
					    LOCATION_ONSHORE);
		    locationPyramidOffShoreList = solutionFTEDAO
			    .getLocationPyramidBySolLeverId(solutionLeverID,
				    oppScopeID, entry.getKey(),
				    LOCATION_OFFSHORE);
		    locationPyramidOnshoreLocalList = solutionFTEDAO
				    .getLocationPyramidBySolLeverId(solutionLeverID,
					    oppScopeID, entry.getKey(),
					    LOCATION_ONSHORE,SUBLOC_LOCAL);
		    locationPyramidOnshoreGSCIList = solutionFTEDAO
				    .getLocationPyramidBySolLeverId(solutionLeverID,
					    oppScopeID, entry.getKey(),
					    LOCATION_ONSHORE,SUBLOC_GSCI);
		    locationPyramidOnshore3PPList = solutionFTEDAO
				    .getLocationPyramidBySolLeverId(solutionLeverID,
					    oppScopeID, entry.getKey(),
					    LOCATION_ONSHORE,SUBLOC_3PP);
		    
		    // Rotate for all month and years to catch Nearshore data
		    int yearIndex = 0;
		    Date loopDate = null;
		    if (yearIndex < monthYearsArray.length) {
			for (LocationPyramid locationPyramid : locationPyramidNearShoreList) {
			    loopDate = convert2Date(monthYearsArray[yearIndex]);
			    if (loopDate.before(locationPyramid.getMonthYear())) {
				List<Date> dates = getDatesArrayByDateRange(
					loopDate,
					locationPyramid.getMonthYear());
				int dtCount = 0;
				while (dtCount < (dates.size() - 1)) {
				    nearShoreFTEVal = getIndexFTEValue(
					    nearshoreFTEPoolList
						    .get(serviceIndex),
					    yearIndex);
				    fillServiceFTEValue(lastLocationPyramid,
					    nearShoreData, nearShoreFTEVal);
				    yearIndex++;
				    dtCount++;
				}
				nearShoreFTEVal = getIndexFTEValue(
					nearshoreFTEPoolList.get(serviceIndex),
					yearIndex);
				fillServiceFTEValue(locationPyramid,
					nearShoreData, nearShoreFTEVal);
				yearIndex++;
				lastLocationPyramid = locationPyramid;
			    } else if (loopDate.after(locationPyramid
				    .getMonthYear())) {
				logger.info(locationPyramid
					.getLocationPyramidId()
					+ "ID of LocationPyramid old unused data should get removed");
			    } else {
				nearShoreFTEVal = getIndexFTEValue(
					nearshoreFTEPoolList.get(serviceIndex),
					yearIndex);
				fillServiceFTEValue(locationPyramid,
					nearShoreData, nearShoreFTEVal);
				lastLocationPyramid = locationPyramid;
				yearIndex++;
			    }

			}
			while (yearIndex < monthYearsArray.length) {
			    nearShoreFTEVal = getIndexFTEValue(
				    nearshoreFTEPoolList.get(serviceIndex),
				    yearIndex);
			    fillServiceFTEValue(lastLocationPyramid,
				    nearShoreData, nearShoreFTEVal);
			    yearIndex++;
			}
		    }
		    lastLocationPyramid = null;
		    
		    // Load for offshore data
		    yearIndex = 0;
		    if (yearIndex < monthYearsArray.length) {
			// if (locationPyramidDTOList.size() > 0) {
			for (LocationPyramid locationPyramid : locationPyramidOffShoreList) {
			    loopDate = convert2Date(monthYearsArray[yearIndex]);
			    if (loopDate.before(locationPyramid.getMonthYear())) {
				List<Date> dates = getDatesArrayByDateRange(
					loopDate,
					locationPyramid.getMonthYear());
				int dtCount = 0;
				while (dtCount < (dates.size() - 1)) {
				    offShoreFTEVal = getIndexFTEValue(
					    offshoreFTEPoolList
						    .get(serviceIndex),
					    yearIndex);
				    fillServiceFTEValue(lastLocationPyramid,
					    offShoreData, offShoreFTEVal);
				    yearIndex++;
				    dtCount++;
				}
				offShoreFTEVal = getIndexFTEValue(
					offshoreFTEPoolList.get(serviceIndex),
					yearIndex);
				fillServiceFTEValue(locationPyramid,
					offShoreData, offShoreFTEVal);
				yearIndex++;
				lastLocationPyramid = locationPyramid;
			    } else if (loopDate.after(locationPyramid
				    .getMonthYear())) {
				logger.info(locationPyramid
					.getLocationPyramidId()
					+ "ID of LocationPyramid old unused data should get removed");
			    } else {
				offShoreFTEVal = getIndexFTEValue(
					offshoreFTEPoolList.get(serviceIndex),
					yearIndex);
				fillServiceFTEValue(locationPyramid,
					offShoreData, offShoreFTEVal);
				lastLocationPyramid = locationPyramid;
				yearIndex++;
			    }
			}
			while (yearIndex < monthYearsArray.length) {
			    offShoreFTEVal = getIndexFTEValue(
				    offshoreFTEPoolList.get(serviceIndex),
				    yearIndex);
			    fillServiceFTEValue(lastLocationPyramid,
				    offShoreData, offShoreFTEVal);
			    yearIndex++;
			}
		    }
		    
		 // Load for onshore data
		    yearIndex = 0;
		    if (yearIndex < monthYearsArray.length) {
			// if (locationPyramidDTOList.size() > 0) {
			for (LocationPyramid locationPyramid : locationPyramidOnShoreList) {
			    loopDate = convert2Date(monthYearsArray[yearIndex]);
			    if (loopDate.before(locationPyramid.getMonthYear())) {
				List<Date> dates = getDatesArrayByDateRange(
					loopDate,
					locationPyramid.getMonthYear());
				int dtCount = 0;
				while (dtCount < (dates.size() - 1)) {
				    onShoreFTEVal = getIndexFTEValue(
					    onshoreFTEPoolList
						    .get(serviceIndex),
					    yearIndex);
				    fillServiceFTEValue(lastLocationPyramid,
					    onShoreData, onShoreFTEVal);
				    yearIndex++;
				    dtCount++;
				}
				onShoreFTEVal = getIndexFTEValue(
						onshoreFTEPoolList.get(serviceIndex),
					yearIndex);
				fillServiceFTEValue(locationPyramid,
						onShoreData, onShoreFTEVal);
				yearIndex++;
				lastLocationPyramid = locationPyramid;
			    } else if (loopDate.after(locationPyramid
				    .getMonthYear())) {
				logger.info(locationPyramid
					.getLocationPyramidId()
					+ "ID of LocationPyramid old unused data should get removed");
			    } else {
				onShoreFTEVal = getIndexFTEValue(
						onshoreFTEPoolList.get(serviceIndex),
					yearIndex);
				fillServiceFTEValue(locationPyramid,
						onShoreData, onShoreFTEVal);
				lastLocationPyramid = locationPyramid;
				yearIndex++;
			    }
			}
			while (yearIndex < monthYearsArray.length) {
			    onShoreFTEVal = getIndexFTEValue(
				    onshoreFTEPoolList.get(serviceIndex),
				    yearIndex);
			    fillServiceFTEValue(lastLocationPyramid,
				    onShoreData, onShoreFTEVal);
			    yearIndex++;
			}
		    }
		    
		    // Rotate for all month and years to catch Onshore Local data
		    yearIndex = 0;
		    if (yearIndex < monthYearsArray.length) {
			for (LocationPyramid locationPyramid : locationPyramidOnshoreLocalList) {
			    loopDate = convert2Date(monthYearsArray[yearIndex]);
			    if (loopDate.before(locationPyramid.getMonthYear())) {
				List<Date> dates = getDatesArrayByDateRange(
					loopDate,
					locationPyramid.getMonthYear());
				int dtCount = 0;
				while (dtCount < (dates.size() - 1)) {
				    onShoreLocalFTEVal = getIndexFTEValue(
					    onshoreLocalFTEPoolList
						    .get(serviceIndex),
					    yearIndex);
				    fillServiceFTEValue(lastLocationPyramid,
					    onShoreLocalData, onShoreLocalFTEVal);
				    yearIndex++;
				    dtCount++;
				}
				onShoreLocalFTEVal = getIndexFTEValue(
					onshoreLocalFTEPoolList.get(serviceIndex),
					yearIndex);
				fillServiceFTEValue(locationPyramid,
					onShoreLocalData, onShoreLocalFTEVal);
				yearIndex++;
				lastLocationPyramid = locationPyramid;
			    } else if (loopDate.after(locationPyramid
				    .getMonthYear())) {
				logger.info(locationPyramid
					.getLocationPyramidId()
					+ "ID of LocationPyramid old unused data should get removed");
			    } else {
				onShoreLocalFTEVal = getIndexFTEValue(
					onshoreLocalFTEPoolList.get(serviceIndex),
					yearIndex);
				fillServiceFTEValue(locationPyramid,
					onShoreLocalData, onShoreLocalFTEVal);
				lastLocationPyramid = locationPyramid;
				yearIndex++;
			    }

			}
			while (yearIndex < monthYearsArray.length) {
			    onShoreLocalFTEVal = getIndexFTEValue(
				    onshoreLocalFTEPoolList.get(serviceIndex),
				    yearIndex);
			    fillServiceFTEValue(lastLocationPyramid,
				    onShoreLocalData, onShoreLocalFTEVal);
			    yearIndex++;
			}
		    }
		    
		 // Rotate for all month and years to catch Onshore GSCI data
		    yearIndex = 0;
		    if (yearIndex < monthYearsArray.length) {
			for (LocationPyramid locationPyramid : locationPyramidOnshoreGSCIList) {
			    loopDate = convert2Date(monthYearsArray[yearIndex]);
			    if (loopDate.before(locationPyramid.getMonthYear())) {
				List<Date> dates = getDatesArrayByDateRange(
					loopDate,
					locationPyramid.getMonthYear());
				int dtCount = 0;
				while (dtCount < (dates.size() - 1)) {
				    onShoreGsciFTEVal = getIndexFTEValue(
					    onshoreGsciFTEPoolList
						    .get(serviceIndex),
					    yearIndex);
				    fillServiceFTEValue(lastLocationPyramid,
					    onShoreGsciData, onShoreGsciFTEVal);
				    yearIndex++;
				    dtCount++;
				}
				onShoreGsciFTEVal = getIndexFTEValue(
					onshoreGsciFTEPoolList.get(serviceIndex),
					yearIndex);
				fillServiceFTEValue(locationPyramid,
					onShoreGsciData, onShoreGsciFTEVal);
				yearIndex++;
				lastLocationPyramid = locationPyramid;
			    } else if (loopDate.after(locationPyramid
				    .getMonthYear())) {
				logger.info(locationPyramid
					.getLocationPyramidId()
					+ "ID of LocationPyramid old unused data should get removed");
			    } else {
				onShoreGsciFTEVal = getIndexFTEValue(
					onshoreGsciFTEPoolList.get(serviceIndex),
					yearIndex);
				fillServiceFTEValue(locationPyramid,
					onShoreGsciData, onShoreGsciFTEVal);
				lastLocationPyramid = locationPyramid;
				yearIndex++;
			    }

			}
			while (yearIndex < monthYearsArray.length) {
			    onShoreGsciFTEVal = getIndexFTEValue(
				    onshoreGsciFTEPoolList.get(serviceIndex),
				    yearIndex);
			    fillServiceFTEValue(lastLocationPyramid,
				    onShoreGsciData, onShoreGsciFTEVal);
			    yearIndex++;
			}
		    }
		    
		 // Rotate for all month and years to catch Onshore 3PP data
		    yearIndex = 0;
		    if (yearIndex < monthYearsArray.length) {
			for (LocationPyramid locationPyramid : locationPyramidOnshore3PPList) {
			    loopDate = convert2Date(monthYearsArray[yearIndex]);
			    if (loopDate.before(locationPyramid.getMonthYear())) {
				List<Date> dates = getDatesArrayByDateRange(
					loopDate,
					locationPyramid.getMonthYear());
				int dtCount = 0;
				while (dtCount < (dates.size() - 1)) {
				    onShore3PPFTEVal = getIndexFTEValue(
					    onshore3PPFTEPoolList
						    .get(serviceIndex),
					    yearIndex);
				    fillServiceFTEValue(lastLocationPyramid,
					    onShore3PPData, onShore3PPFTEVal);
				    yearIndex++;
				    dtCount++;
				}
				onShore3PPFTEVal = getIndexFTEValue(
					onshore3PPFTEPoolList.get(serviceIndex),
					yearIndex);
				fillServiceFTEValue(locationPyramid,
					onShore3PPData, onShore3PPFTEVal);
				yearIndex++;
				lastLocationPyramid = locationPyramid;
			    } else if (loopDate.after(locationPyramid
				    .getMonthYear())) {
				logger.info(locationPyramid
					.getLocationPyramidId()
					+ "ID of LocationPyramid old unused data should get removed");
			    } else {
				onShore3PPFTEVal = getIndexFTEValue(
					onshore3PPFTEPoolList.get(serviceIndex),
					yearIndex);
				fillServiceFTEValue(locationPyramid,
					onShore3PPData, onShore3PPFTEVal);
				lastLocationPyramid = locationPyramid;
				yearIndex++;
			    }

			}
			while (yearIndex < monthYearsArray.length) {
			    onShore3PPFTEVal = getIndexFTEValue(
				    onshore3PPFTEPoolList.get(serviceIndex),
				    yearIndex);
			    fillServiceFTEValue(lastLocationPyramid,
				    onShore3PPData, onShore3PPFTEVal);
			    yearIndex++;
			}
		    }

		    logger.debug("Solution ID:" + reviewFTEForm.getSolutionID()
			    + " OppScope:" + oppScopeID + ":nearShoreData="
			    + nearShoreData + " | offShoreData" + offShoreData + " | onShoreData" + onShoreData + " | onShoreLocalData" + onShoreLocalData
			    + " | onShoreGsciData" + onShoreGsciData + " | onShore3PPData" + onShore3PPData);
		    
		    // Save Service's FTE value into our FTEStaging table
		    // Check all invalid or not to insert data into FTEStaging
		    if (!nearShoreData.toString().replaceAll("-1;", "")
			    .equals("")
			    || !offShoreData.toString().replaceAll("-1;", "")
				    .equals("")
			    || !onShoreData.toString().replaceAll("-1;", "")
				    .equals("")
				|| !onShoreLocalData.toString().replaceAll("-1;", "")
			    .equals("")
				|| !onShoreGsciData.toString().replaceAll("-1;", "")
			    .equals("")
				|| !onShore3PPData.toString().replaceAll("-1;", "")
			    .equals("")) {
			save2FTEStaging(reviewFTEForm, oppScopeID,
				monthYearsArray, nearShoreData.toString(),
				LOCATION_NEARSHORE, entry.getKey());
			save2FTEStaging(reviewFTEForm, oppScopeID,
					monthYearsArray, onShoreData.toString(),
					LOCATION_ONSHORE, entry.getKey());
			save2FTEStaging(reviewFTEForm, oppScopeID,
				monthYearsArray, offShoreData.toString(),
				LOCATION_OFFSHORE, entry.getKey());
			save2FTEStaging(reviewFTEForm, oppScopeID,
					monthYearsArray, onShoreLocalData.toString(),
					LOCATION_ONSHORE, entry.getKey(), SUBLOC_LOCAL);
			save2FTEStaging(reviewFTEForm, oppScopeID,
					monthYearsArray, onShoreGsciData.toString(),
					LOCATION_ONSHORE, entry.getKey(), SUBLOC_GSCI);
			save2FTEStaging(reviewFTEForm, oppScopeID,
					monthYearsArray, onShore3PPData.toString(),
					LOCATION_ONSHORE, entry.getKey(), SUBLOC_3PP);

		    }
		}
	    }
	} catch (Exception e) {
	    logger.error("Exception - " + e);
	    // e.printStackTrace();
	    throw new MSSPException(e);
	}

    }

    /**
     * 
     * Description : Data saved into FTEStaging for labor cost Method Name :
     * save2FTEStaging Input& Output:
     * 
     * @param reviewFTEForm
     * @param oppScopeID
     * @param monthYearsArray
     * @param dataRow
     * @param location
     * @param jobRoleStageID
     * @return boolean
     * @throws MSSPException
     */
    private boolean save2FTEStaging(ReviewFTEForm reviewFTEForm,
	    Integer oppScopeID, String[] monthYearsArray, String dataRow,
	    String location, Integer jobRoleStageID) throws MSSPException {
	boolean response = true;
	Date monthYear = null;
	FTEStaging fTEStaging = null;
	String data[] = dataRow.split(";");
	// String[] monthYearsArray = reviewFTEForm.getMonthYears().split(";");
	try {

	    List<FTEStaging> fTEStagingList = new ArrayList<FTEStaging>(10);
	    for (int index = 0; index < monthYearsArray.length; index++) {
		float value = index < data.length && null != data[index] ? Float
			.parseFloat(data[index]) : 0;
		// if (value > 0) {
		monthYear = convert2Date(null != monthYearsArray[index] ? monthYearsArray[index]
			: null);
		fTEStaging = new FTEStaging();
		JobRoleStages jobRoleStage = new JobRoleStages();
		jobRoleStage.setJobRoleStagesId(jobRoleStageID);
		fTEStaging.setJobRoleStage(jobRoleStage);
		fTEStaging.setLocation(location);
		OpportunityScope opportunityScope = new OpportunityScope();
		opportunityScope.setOpportunityScopeId(oppScopeID);
		fTEStaging.setOpportunityScope(opportunityScope);
		Solution solution = new Solution();
		solution.setSolutionId(reviewFTEForm.getSolutionID());
		fTEStaging.setSolution(solution);
		fTEStaging.setMonthYear(monthYear);
		fTEStaging.setFtecount(value);
		fTEStaging.setFtestagingId(null);
		fTEStagingList.add(fTEStaging);
	    }
	    solutionFTEDAO.saveFTEStagingList(fTEStagingList);

	} catch (Exception e) {
	    logger.error("save2FTEStaging exception-" + e);
	    // e.printStackTrace();
	    response = false;
	    throw new MSSPException(e);
	}
	return response;
    }
    
    private boolean save2FTEStaging(ReviewFTEForm reviewFTEForm,
    	    Integer oppScopeID, String[] monthYearsArray, String dataRow,
    	    String location, Integer jobRoleStageID, String subLocation) throws MSSPException {
    	boolean response = true;
    	Date monthYear = null;
    	FTEStaging fTEStaging = null;
    	String data[] = dataRow.split(";");
    	// String[] monthYearsArray = reviewFTEForm.getMonthYears().split(";");
    	try {

    	    List<FTEStaging> fTEStagingList = new ArrayList<FTEStaging>(10);
    	    for (int index = 0; index < monthYearsArray.length; index++) {
    		float value = index < data.length && null != data[index] ? Float
    			.parseFloat(data[index]) : 0;
    		// if (value > 0) {
    		monthYear = convert2Date(null != monthYearsArray[index] ? monthYearsArray[index]
    			: null);
    		fTEStaging = new FTEStaging();
    		JobRoleStages jobRoleStage = new JobRoleStages();
    		jobRoleStage.setJobRoleStagesId(jobRoleStageID);
    		fTEStaging.setJobRoleStage(jobRoleStage);
    		fTEStaging.setLocation(location);
    		fTEStaging.setSubLocation(subLocation);
    		OpportunityScope opportunityScope = new OpportunityScope();
    		opportunityScope.setOpportunityScopeId(oppScopeID);
    		fTEStaging.setOpportunityScope(opportunityScope);
    		Solution solution = new Solution();
    		solution.setSolutionId(reviewFTEForm.getSolutionID());
    		fTEStaging.setSolution(solution);
    		fTEStaging.setMonthYear(monthYear);
    		fTEStaging.setFtecount(value);
    		fTEStaging.setFtestagingId(null);
    		fTEStagingList.add(fTEStaging);
    	    }
    	    solutionFTEDAO.saveFTEStagingList(fTEStagingList);

    	} catch (Exception e) {
    	    logger.error("save2FTEStaging exception-" + e);
    	    // e.printStackTrace();
    	    response = false;
    	    throw new MSSPException(e);
    	}
    	return response;
        }

    /**
     * 
     * Description : It will return Review FTE pool stored cache values by
     * passed key Method Name : getCacheValueByKey Input& Output:
     * 
     * @param solutionLever
     * @param lookingFor
     * @return Object FTE_TIMELINE key return String Object FTE_SERVICES key
     *         returns String Object LOCATION_OFFSHORE key returns Object of
     *         List<String> LOCATION_ONSHORE key returns Object of List<String>
     * 
     */
    private Object getCacheValueByKey(Integer solutionLeverID, String lookingFor) {
	List<Object> allValues = null;
	if (null == lookingFor) {
	    lookingFor = FTE_ALL;
	    allValues = new ArrayList<Object>();
	}
	if (lookingFor.equals(FTE_TIMELINE)) {
	    return reviewFTEData.get(solutionLeverID).get(FTE_TIMELINE)
		    .toString();
	} else if (lookingFor.equals(FTE_SERVICES)) {
	    return reviewFTEData.get(solutionLeverID).get(FTE_SERVICES)
		    .toString();
	} else if (lookingFor.equals(LOCATION_OFFSHORE)) {
	    return getCacheFTEPoolBySolutionLeverLocation(solutionLeverID,
		    LOCATION_OFFSHORE);
	} else if (lookingFor.equals(LOCATION_ONSHORE)) {
	    return getCacheFTEPoolBySolutionLeverLocation(solutionLeverID,
			    LOCATION_ONSHORE);
	} else if (lookingFor.equals(LOCATION_ONSHORE_LOCAL)) {
	    return getCacheFTEPoolBySolutionLeverLocation(solutionLeverID,
		    LOCATION_ONSHORE_LOCAL);
	} else if (lookingFor.equals(LOCATION_ONSHORE_GSCI)) {
	    return getCacheFTEPoolBySolutionLeverLocation(solutionLeverID,
			    LOCATION_ONSHORE_GSCI);
	} else if (lookingFor.equals(LOCATION_ONSHORE_3PP)) {
	    return getCacheFTEPoolBySolutionLeverLocation(solutionLeverID,
			    LOCATION_ONSHORE_3PP);
	} else if (lookingFor.equals(LOCATION_NEARSHORE)) {
	    return getCacheFTEPoolBySolutionLeverLocation(solutionLeverID,
			    LOCATION_NEARSHORE);
	} else if (lookingFor.equals(FTE_ALL)) {
	    if (null != allValues) {
		allValues = new ArrayList<Object>();
	    }
	    allValues.add(reviewFTEData.get(solutionLeverID).get(FTE_TIMELINE));
	    allValues.add(reviewFTEData.get(solutionLeverID).get(FTE_SERVICES));
	    allValues.add(getCacheFTEPoolBySolutionLeverLocation(
		    solutionLeverID, LOCATION_ONSHORE_LOCAL));
	    allValues.add(getCacheFTEPoolBySolutionLeverLocation(
			    solutionLeverID, LOCATION_ONSHORE_GSCI));
	    allValues.add(getCacheFTEPoolBySolutionLeverLocation(
			    solutionLeverID, LOCATION_ONSHORE_3PP));
	    allValues.add(getCacheFTEPoolBySolutionLeverLocation(
			    solutionLeverID, LOCATION_NEARSHORE));
	    allValues.add(getCacheFTEPoolBySolutionLeverLocation(
		    solutionLeverID, LOCATION_OFFSHORE));
	    allValues.add(getCacheFTEPoolBySolutionLeverLocation(
			    solutionLeverID, LOCATION_ONSHORE));
	    return allValues;
	}

	return allValues;
    }

    @Override
    public void updateIntoFTEStaging(AdditionalFTE additionalFTE,
	    boolean isDelete) throws MSSPException {
	try {
	    // System.out.println("Service Desk --"
	    // + additionalFTE.getOpportunityScopeId());
	    AdditionalFTE oldAdditionalFTE = null;
	    if (null != additionalFTE.getAdditionalFteid()) {
		oldAdditionalFTE = solutionFTEDAO
			.getAdditonalFTEByID(additionalFTE.getAdditionalFteid());
	    }
	    // If additonal FTE came for deletion
	    if (isDelete && null != oldAdditionalFTE) {
		oldAdditionalFTE.setNoofFte(oldAdditionalFTE.getNoofFte() * -1);
		doAdditonalFTEUpdates(oldAdditionalFTE);
	    } else {
		// clean old values
		if (null != oldAdditionalFTE) {
		    oldAdditionalFTE.setNoofFte(oldAdditionalFTE.getNoofFte()
			    * -1);
		    doAdditonalFTEUpdates(oldAdditionalFTE);
		}
		// Add new values
		doAdditonalFTEUpdates(additionalFTE);
	    }

	} catch (Exception e) {
	    logger.error("Error - " + e + " Solution ID-"
		    + additionalFTE.getSolution().getSolutionId());
	    throw new MSSPException(e);
	}

    }

    /**
     * 
     * Description : Updates additional FTE based values Method Name :
     * doAdditonalFTEUpdates Input& Output:
     * 
     * @param additionalFTE
     * @throws MSSPException
     */
    private void doAdditonalFTEUpdates(AdditionalFTE additionalFTE)
	    throws MSSPException {
	// Pick solution's solution lever ID
	SolutionLever solutionLever = solutionFTEDAO
		.getSolutionLeverBySolId(additionalFTE.getSolution()
			.getSolutionId());

	List<Date> dates = getDatesArrayByDateRange(
		additionalFTE.getFromMonthYear(),
		additionalFTE.getToMonthYear());
	FTEStaging fteStaging = getFTEStagingFromAdditionalFTE(additionalFTE);
	// if dates size greater than 1 then do check stair case or Uniform
	// If stair case add the same value in each month else add equally
	// distributed values
	if (dates.size() > 1) {
	    if (null != solutionLever.getSolutionLeverApproach()
		    && solutionLever.getSolutionLeverApproach().equals(
			    SOLUTION_APPROACH_UNIFORM)) {
		// Code for uniform
		float userProvidedVal = fteStaging.getFtecount();
		float appliedVal = userProvidedVal / dates.size();
		for (int dateIndex = 0; dateIndex < dates.size(); dateIndex++) {
		    fteStaging.setFtecount(appliedVal);
		    fteStaging.setMonthYear(dates.get(dateIndex));
		    saveAdditionalFTE2Staging(solutionLever, fteStaging);
		}
		// System.out.println("Uniform appliedVal-" + appliedVal);
	    } else {
		// Code for stair case
		// System.out.println("Staircase");
		float userInputedFTE = fteStaging.getFtecount();
		for (int dateIndex = 0; dateIndex < dates.size(); dateIndex++) {
		    fteStaging.setMonthYear(dates.get(dateIndex));
		    fteStaging.setFtecount(userInputedFTE);
		    saveAdditionalFTE2Staging(solutionLever, fteStaging);
		}
	    }

	} else {
	    // Check entry is in FTEStaging existance. if not exist add for
	    // offshore and onshore entries with these non zero values
	    fteStaging.setMonthYear(dates.get(0));
	    saveAdditionalFTE2Staging(solutionLever, fteStaging);
	}
    }

    /**
     * 
     * Description : Updated additonal FTE value into FTEStaging saved values
     * Method Name : saveAdditionalFTE2Staging Input& Output:
     * 
     * @param solutionLever
     * @param fteStaging
     * @throws MSSPException
     */
    private void saveAdditionalFTE2Staging(SolutionLever solutionLever,
	    FTEStaging fteStaging) throws MSSPException {
	FTEStaging dbFTEStaging = null;
	
	if(fteStaging.getSubLocation() == null){
		dbFTEStaging = solutionFTEDAO.getFtestagingByPassedParam(
			fteStaging.getSolution().getSolutionId(), fteStaging
				.getOpportunityScope().getOpportunityScopeId(),
			fteStaging.getJobRoleStage().getJobRoleStagesId(), fteStaging
				.getMonthYear(), fteStaging.getLocation());
	}else{
		dbFTEStaging = solutionFTEDAO.getFtestagingByPassedParam(
				fteStaging.getSolution().getSolutionId(), fteStaging
					.getOpportunityScope().getOpportunityScopeId(),
				fteStaging.getJobRoleStage().getJobRoleStagesId(), fteStaging
					.getMonthYear(), fteStaging.getLocation(), fteStaging.getSubLocation());
	}
	if (null != dbFTEStaging) {
	    fteStaging.setFtestagingId(dbFTEStaging.getFtestagingId());
	    saveCalculatedAdditionalFTE2Stating(
		    solutionLever.getSolutionLeverId(), fteStaging,
		    dbFTEStaging);
	} else {
	    nonExistanceAdditonalFTESave(solutionLever.getSolutionLeverId(),
		    fteStaging);
	}
    }

    /**
     * 
     * Description : If additional FTE provided whose value not stored in
     * FTEStaging Method Name : nonExistanceAdditonalFTESave Input& Output:
     * 
     * @param solutionLeverID
     * @param fteStaging
     * @throws MSSPException
     */
    private void nonExistanceAdditonalFTESave(Integer solutionLeverID,
	    FTEStaging fteStaging) throws MSSPException {
	List<FTEStaging> fTEStagingList = new ArrayList<FTEStaging>(10);
	FTEStaging sTEStagingZeroVal = null;

	String times[] = getCacheValueByKey(solutionLeverID, FTE_TIMELINE)
		.toString().split(";");
	for (int index = 0; index < times.length; index++) {
	    Date monthYear = convert2Date(times[index]);
	    sTEStagingZeroVal = new FTEStaging();
	    BeanUtils.copyProperties(fteStaging, sTEStagingZeroVal);
	    sTEStagingZeroVal.setFtecount(0f);
	    sTEStagingZeroVal.setMonthYear(monthYear);
	    if (monthYear.equals(fteStaging.getMonthYear())) {
			if (LOCATION_ONSHORE_LOCAL.equals(fteStaging.getLocation())) {
			    fTEStagingList.add(fteStaging);
			    sTEStagingZeroVal.setLocation(LOCATION_ONSHORE_LOCAL);
			    fTEStagingList.add(sTEStagingZeroVal);
			}else if (LOCATION_ONSHORE_GSCI.equals(fteStaging.getLocation())) {
			    fTEStagingList.add(fteStaging);
			    sTEStagingZeroVal.setLocation(LOCATION_ONSHORE_GSCI);
			    fTEStagingList.add(sTEStagingZeroVal);
			}else if (LOCATION_ONSHORE_3PP.equals(fteStaging.getLocation())) {
			    fTEStagingList.add(fteStaging);
			    sTEStagingZeroVal.setLocation(LOCATION_ONSHORE_3PP);
			    fTEStagingList.add(sTEStagingZeroVal);
			}else if (LOCATION_NEARSHORE.equals(fteStaging.getLocation())) {
			    fTEStagingList.add(fteStaging);
			    sTEStagingZeroVal.setLocation(LOCATION_NEARSHORE);
			    fTEStagingList.add(sTEStagingZeroVal);
			}else if (LOCATION_ONSHORE.equals(fteStaging.getLocation())) {
			    fTEStagingList.add(fteStaging);
			    sTEStagingZeroVal.setLocation(LOCATION_ONSHORE);
			    fTEStagingList.add(sTEStagingZeroVal);
			} else {
			    fTEStagingList.add(fteStaging);
			    sTEStagingZeroVal.setLocation(LOCATION_OFFSHORE);
			    fTEStagingList.add(sTEStagingZeroVal);
			}
	    } else {
		// As zero is valid so putting -ve
		sTEStagingZeroVal.setFtecount(-1f);
		sTEStagingZeroVal.setLocation(LOCATION_NEARSHORE);
		fTEStagingList.add(sTEStagingZeroVal);
		sTEStagingZeroVal = new FTEStaging();
		BeanUtils.copyProperties(fteStaging, sTEStagingZeroVal);
		sTEStagingZeroVal.setFtecount(-1f);
		sTEStagingZeroVal.setMonthYear(monthYear);
		sTEStagingZeroVal.setLocation(LOCATION_OFFSHORE);
		fTEStagingList.add(sTEStagingZeroVal);
		sTEStagingZeroVal = new FTEStaging();
		BeanUtils.copyProperties(fteStaging, sTEStagingZeroVal);
		sTEStagingZeroVal.setFtecount(-1f);
		sTEStagingZeroVal.setMonthYear(monthYear);
		sTEStagingZeroVal.setLocation(LOCATION_ONSHORE);
		fTEStagingList.add(sTEStagingZeroVal);
		sTEStagingZeroVal = new FTEStaging();
		BeanUtils.copyProperties(fteStaging, sTEStagingZeroVal);
		sTEStagingZeroVal.setFtecount(-1f);
		sTEStagingZeroVal.setMonthYear(monthYear);
		sTEStagingZeroVal.setLocation(LOCATION_ONSHORE);
		sTEStagingZeroVal.setSubLocation(SUBLOC_LOCAL);
		fTEStagingList.add(sTEStagingZeroVal);
		sTEStagingZeroVal = new FTEStaging();
		BeanUtils.copyProperties(fteStaging, sTEStagingZeroVal);
		sTEStagingZeroVal.setFtecount(-1f);
		sTEStagingZeroVal.setMonthYear(monthYear);
		sTEStagingZeroVal.setLocation(LOCATION_ONSHORE);
		sTEStagingZeroVal.setSubLocation(SUBLOC_GSCI);
		fTEStagingList.add(sTEStagingZeroVal);
		sTEStagingZeroVal = new FTEStaging();
		BeanUtils.copyProperties(fteStaging, sTEStagingZeroVal);
		sTEStagingZeroVal.setFtecount(-1f);
		sTEStagingZeroVal.setMonthYear(monthYear);
		sTEStagingZeroVal.setLocation(LOCATION_ONSHORE);
		sTEStagingZeroVal.setSubLocation(SUBLOC_3PP);
		fTEStagingList.add(sTEStagingZeroVal);
	    }

	}
	solutionFTEDAO.saveFTEStagingList(fTEStagingList);
    }

    /**
     * 
     * Description : calculate value based on current data and update/save
     * Method Name : saveCalculatedAdditionalFTE2Stating Input& Output:
     * 
     * @param solutionLeverID
     * @param fteStaging
     * @param dbFTEStaging
     * @throws MSSPException
     */
    private void saveCalculatedAdditionalFTE2Stating(Integer solutionLeverID,
	    FTEStaging fteStaging, FTEStaging dbFTEStaging)
	    throws MSSPException {
	// Do calculate Location Pyramid and save
	if (null != dbFTEStaging && dbFTEStaging.getFtecount() > 0) {
	    float value = calculatedServiceFTEValue(fteStaging, solutionLeverID);
	    if (value >= 0) {
		value += fteStaging.getFtecount() > 0 ? fteStaging
			.getFtecount() : 0;
	    } else {
		value = dbFTEStaging.getFtecount() + fteStaging.getFtecount();
	    }
	    value = Math.round(value * DECIMAL_POINTS_NUM) / DECIMAL_POINTS_NUM;
	    fteStaging.setFtecount(value);
	}
	solutionFTEDAO.save2FTEStaging(fteStaging);
	// Set zero to opposite location value if it negative
	setOppLoc2ZeroValue(fteStaging);

    }

    /**
     * 
     * Description : Set zero to opposite location value if it negative Method
     * Name : setOppLoc2ZeroValue Input& Output:
     * 
     * @param fteStaging
     * @throws MSSPException
     */
    private void setOppLoc2ZeroValue(FTEStaging fteStaging)
	    throws MSSPException {
	FTEStaging oppLocFTEStaging = solutionFTEDAO
		.getFtestagingByPassedParam(
			fteStaging.getSolution().getSolutionId(),
			fteStaging.getOpportunityScope()
				.getOpportunityScopeId(),
			fteStaging.getJobRoleStage().getJobRoleStagesId(),
			fteStaging.getMonthYear(),
			fteStaging.getLocation().equals(LOCATION_OFFSHORE) ? LOCATION_ONSHORE
				: LOCATION_OFFSHORE);
	if (oppLocFTEStaging.getFtecount() < 0) {
	    oppLocFTEStaging.setFtecount(0f);
	    solutionFTEDAO.save2FTEStaging(oppLocFTEStaging);
	}
    }

    /**
     * 
     * Description : Calculation of FTE for current data Method Name :
     * calculatedServiceFTEValue Input& Output:
     * 
     * @param fteStaging
     * @param solutionLeverID
     * @return
     * @throws MSSPException
     */
    private float calculatedServiceFTEValue(FTEStaging fteStaging,
	    Integer solutionLeverID) throws MSSPException {
	float value = 0f;
	LocationPyramid locationPyramid = solutionFTEDAO
		.getLocationPyramidByFTEStaging(solutionLeverID, fteStaging);
	if (null != locationPyramid) {
	    float fTEVal = getFTEValueByPassedParam(fteStaging, solutionLeverID);
	    value = null != locationPyramid.getDistributionPc() ? (locationPyramid
		    .getDistributionPc() * fTEVal / 100)
		    : 0f;
	} else {
	    value = -1f;
	}
	return value;
    }

    private float getFTEValueByPassedParam(FTEStaging fteStaging,
	    Integer solutionLeverID) throws MSSPException {
	// It will return OppScope ID and Service Scope name
	Map<Integer, String> oppScopeIdSSNamesMap = getAllServiceScopeInMapBySolId(fteStaging
		.getSolution().getSolutionId());
	int serviceIndex = -1;
	for (Map.Entry<Integer, String> mapVal : oppScopeIdSSNamesMap
		.entrySet()) {
	    serviceIndex++;
	    if (mapVal.getKey().equals(
		    fteStaging.getOpportunityScope().getOpportunityScopeId())) {
		break;
	    }
	}
	@SuppressWarnings("unchecked")
	String location = fteStaging.getLocation();
	if(LOCATION_ONSHORE.equals(location)){
		if(SUBLOC_LOCAL.equals(fteStaging.getSubLocation())){
			location = LOCATION_ONSHORE_LOCAL;
		}else if(SUBLOC_GSCI.equals(fteStaging.getSubLocation())){
			location = LOCATION_ONSHORE_GSCI;
		}else if(SUBLOC_3PP.equals(fteStaging.getSubLocation())){
			location = LOCATION_ONSHORE_3PP;
		}else{
			location = LOCATION_ONSHORE;
		}
	}
	
	String serviceFTEData = ((List<String>) getCacheValueByKey(
		solutionLeverID,
		location)).get(serviceIndex);
	int yearIndex = 0;
	String[] monthYears = getCacheValueByKey(solutionLeverID, FTE_TIMELINE)
		.toString().split(";");
	Calendar cal = Calendar.getInstance();
	cal.setTime(fteStaging.getMonthYear());
	String additionalFTEMonthYr = convertNumericMonth2Char(cal
		.get(Calendar.MONTH)) + "-" + cal.get(Calendar.YEAR);
	while (!additionalFTEMonthYr.equals(monthYears[yearIndex])) {
	    yearIndex++;
	}
	return getIndexFTEValue(serviceFTEData, yearIndex);
    }

    /**
     * 
     * Description : Returns Dates list based on passed from and to date Method
     * Name : getDatesArrayByDateRange Input& Output:
     * 
     * @param fromDate
     * @param toDate
     * @return List<Date>
     */
    private List<Date> getDatesArrayByDateRange(Date fromDate, Date toDate) {
	List<Date> datesArr = new LinkedList<Date>();
	Calendar start = Calendar.getInstance();
	start.setTime(fromDate);
	Calendar end = Calendar.getInstance();
	end.setTime(toDate);
	while (!start.after(end)) {
	    Date targetDay = start.getTime();
	    datesArr.add(targetDay);
	    logger.debug("Month=" + new Date(targetDay.getTime()));
	    start.add(Calendar.MONTH, 1);
	}
	return datesArr;
    }

    @SuppressWarnings("unused")
    private String format2MonthYear(Date date) {
	String frmtdMnthYr = "";
	try {
	    Calendar clDt = Calendar.getInstance();
	    clDt.setTime(date);
	    frmtdMnthYr = convertNumericMonth2Char(clDt.get(Calendar.MONTH))
		    + "-" + clDt.get(Calendar.YEAR);
	} catch (Exception e) {
	    logger.info("Exception in date formating-" + e);
	}
	return frmtdMnthYr;
    }

    /**
     * 
     * Description : Based on passed MMM-YYYY string returns first day of the
     * month Method Name : convert2Date Input& Output:
     * 
     * @param monthYr
     * @return Date
     */
    private Date convert2Date(String monthYr) {
	SimpleDateFormat df = null;
	Date conDate = null;
	try {
	    if (null != monthYr) {
		df = new SimpleDateFormat("dd-MMM-yyyy");
		conDate = df.parse("01-" + monthYr);
	    }
	    logger.debug("monthYr=" + monthYr + " conDate=" + conDate);
	    // System.out.println("monthYr=" + monthYr + " conDate=" + conDate);
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("Error in date-" + e);
	}
	return conDate;
    }

    /**
     * 
     * Description : It will check FTEStaging table data stored earlier or not
     * Method Name : isFTEStagingDataNotStored Input& Output:
     * 
     * @param solutionID
     * @return
     * @throws MSSPException
     */
    private boolean isFTEStagingDataNotStored(Integer solutionID)
	    throws MSSPException {
	boolean isDataAbsent = true;
	List<FTEStaging> fteStagings = solutionFTEDAO
		.getFTEStagingListBySolutionID(solutionID);
	if (null != fteStagings && fteStagings.size() > 0) {
	    isDataAbsent = false;
	}
	return isDataAbsent;
    }

    /**
     * 
     * Description : Load data from FTE staging Method Name :
     * loadServiceFTEViewData Input& Output:
     * 
     * @param solutionLeverID
     * @param serviceIndex
     * @param reviewFTEForm
     */
    private void calculateAndSaveServicesFTE(Integer solutionLeverID,
	    ReviewFTEForm reviewFTEForm, boolean isReloadRequired)
	    throws MSSPException {
	boolean isStagingDBDataAbsent = isFTEStagingDataNotStored(reviewFTEForm
		.getSolutionID());
	// Flush and reload FTEStging data if change in timeline or services or
	// data not loaded earlier in FTEStaging table
	if (isReloadRequired || isStagingDBDataAbsent) {
	    // Flush FTEStaging earlier stored data for fresh load
	    if (!isStagingDBDataAbsent) {
		// Delete FTE Staging old records
		int deletedFTEStagingRecsCount = solutionFTEDAO
			.removeAllFTEStagingBySolutionID(reviewFTEForm
				.getSolutionID());

		// remove additional FTE values
		int deletedAdditionalFTEs = solutionFTEDAO
			.removeAllAdditionalFTEsBySolutionID(reviewFTEForm
				.getSolutionID());
		logger.info(reviewFTEForm.getSolutionID()
			+ ":solutionID all deleted FTEStaging records count is:"
			+ deletedFTEStagingRecsCount
			+ " deletedAdditionalFTEs count:"
			+ deletedAdditionalFTEs);

	    }

	    // It will return OppScope ID and Service Scope name
	    Map<Integer, String> oppScopeIdSSNamesMap = getAllServiceScopeInMapBySolId(reviewFTEForm
		    .getSolutionID());
	    int serviceIndex = -1;
	    for (Map.Entry<Integer, String> mapVal : oppScopeIdSSNamesMap
		    .entrySet()) {
		// ++serviceIndex;
		getServiceFTEDataLoaded(solutionLeverID, ++serviceIndex,
			mapVal.getKey(), reviewFTEForm);
	    }
	}

	if (reviewFTEForm.getsServiceScope() >= 0) {
	    loadServiceFTEData(reviewFTEForm);
	}
    }

    /**
     * 
     * Description : Load FTEStaging having service's data Method Name :
     * loadServiceFTEData Input& Output:
     * 
     * @param reviewFTEForm
     * @throws MSSPException
     */
    private void loadServiceFTEData(ReviewFTEForm reviewFTEForm)
	    throws MSSPException {
	OpportunityDetail oppDetail = getOpportunityBySolId(
		reviewFTEForm.getSolutionID()).getOpportunityDetail();
	Date startDate = oppDetail.getSteadyStateStartDate();
	Date endDate = oppDetail.getSteadyStateEndDate();
	List<FTEStaging> offshoreList = null;
	List<FTEStaging> nearshoreList = null;
	List<FTEStaging> onshoreList = null;
	List<FTEStaging> onshoreLocalList = null;
	List<FTEStaging> onshoreGsciList = null;
	List<FTEStaging> onshore3ppList = null;
	try {
	    offshoreList = solutionFTEDAO.getFtestagingListByPassedParam(
		    reviewFTEForm.getSolutionID(),
		    reviewFTEForm.getsServiceScope(), startDate, endDate,
		    LOCATION_OFFSHORE);
	    nearshoreList = solutionFTEDAO.getFtestagingListByPassedParam(
		    reviewFTEForm.getSolutionID(),
		    reviewFTEForm.getsServiceScope(), startDate, endDate,
		    LOCATION_NEARSHORE);
	    onshoreList = solutionFTEDAO.getFtestagingListByPassedParam(
			    reviewFTEForm.getSolutionID(),
			    reviewFTEForm.getsServiceScope(), startDate, endDate,
			    LOCATION_ONSHORE);
	    onshoreLocalList = solutionFTEDAO.getFtestagingListByPassedParam(
			    reviewFTEForm.getSolutionID(),
			    reviewFTEForm.getsServiceScope(), startDate, endDate,
			    LOCATION_ONSHORE, SUBLOC_LOCAL);
	    onshoreGsciList = solutionFTEDAO.getFtestagingListByPassedParam(
			    reviewFTEForm.getSolutionID(),
			    reviewFTEForm.getsServiceScope(), startDate, endDate,
			    LOCATION_ONSHORE, SUBLOC_GSCI);
	    onshore3ppList = solutionFTEDAO.getFtestagingListByPassedParam(
			    reviewFTEForm.getSolutionID(),
			    reviewFTEForm.getsServiceScope(), startDate, endDate,
			    LOCATION_ONSHORE, SUBLOC_3PP);
	    if (offshoreList.size() > 0 && nearshoreList.size() > 0 && onshoreLocalList.size() > 0
	    		 && onshoreGsciList.size() > 0 && onshore3ppList.size() > 0) {
		List<String> nearshoreListData = convert2FTEDataList(nearshoreList);
		reviewFTEForm.setNonEmptyRoles(nearshoreListData
			.get((nearshoreListData.size() - 1)));
		nearshoreListData.remove((nearshoreListData.size() - 1));
		reviewFTEForm.setNearShoreDataList(nearshoreListData);
		reviewFTEForm
			.setOffShoreDataList(convert2FTEDataList(offshoreList));
		reviewFTEForm
		.setOnShoreDataList(convert2FTEDataList(onshoreList));
		reviewFTEForm
		.setOnShoreLocalDataList(convert2FTEDataList(onshoreLocalList));
		reviewFTEForm
		.setOnShoreGsciDataList(convert2FTEDataList(onshoreGsciList));
		reviewFTEForm
		.setOnShore3ppDataList(convert2FTEDataList(onshore3ppList));
	    } else {
		reviewFTEForm.setOffShoreDataList(null);
		reviewFTEForm.setOnShoreDataList(null);
		reviewFTEForm.setNearShoreDataList(null);
		reviewFTEForm.setOnShoreLocalDataList(null);
		reviewFTEForm.setOnShoreGsciDataList(null);
		reviewFTEForm.setOnShore3ppDataList(null);
		reviewFTEForm.setNonEmptyRoles(null);
	    }
	} catch (Exception e) {
	    logger.error("Exception -- " + e);
	    throw new MSSPException(e);
	}
    }

    @Override
    public Map<Integer, String> getJobRoles() throws MSSPException {
	Map<Integer, String> jobRolesMap = null;
	try {
	    jobRolesMap = new LinkedHashMap<Integer, String>(10);
	    @SuppressWarnings("unchecked")
	    List<JobRole> jobRoles = solutionFTEDAO.getObjects(JobRole.class);
	    for (JobRole jr : jobRoles) {
		jobRolesMap.put(jr.getJobRoleId(), jr.getRole());
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	}
	return jobRolesMap;
    }
    
    @Override
    public Map<Integer, String> getJobRoles(int ccmFlag) throws MSSPException {
	Map<Integer, String> jobRolesMap = null;
	try {
	    jobRolesMap = new LinkedHashMap<Integer, String>(10);
	    @SuppressWarnings("unchecked")
	    List<JobRoleStages> jobRoleStages = solutionFTEDAO.getJobRoles(ccmFlag);
	    for (JobRoleStages jr : jobRoleStages) {
		jobRolesMap.put(jr.getJobRoleStagesId(), jr.getJobRole().getRole());
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	}
	return jobRolesMap;
    }

    private String getAllJobRoleNamesString() {
	StringBuilder jobRoles = new StringBuilder();
	List<JobRole> jobRolesList = solutionFTEDAO.getAllJobRole();
	for (JobRole jobRole : jobRolesList) {
	    jobRoles.append(jobRole.getRole()).append(";");
	}
	return jobRoles.toString();
    }

    @Override
    public String getOpportunityTransStartSteadyEndMonthYearsString(
	    Integer solutionID) throws MSSPException {
	String delimeter = ";";
	Calendar steadyEndDt = Calendar.getInstance();
	Calendar steadyStartDt = Calendar.getInstance();
	StringBuilder monthYears = new StringBuilder("");
	OpportunityDetail opportunityDetail = null;
	// Month Years capturing code
	try {
	    Opportunity opportunity = getOpportunityBySolId(solutionID);
	    opportunityDetail = ((Opportunity) solutionFTEDAO.getObject(
		    Opportunity.class, opportunity.getOpportunityId()))
		    .getOpportunityDetail();
	    logger.debug(opportunityDetail.getSteadyStateStartDate() + "|"
		    + opportunityDetail.getSteadyStateEndDate());
	    // steadyStartDt.setTime(opportunityDetail.getSteadyStateStartDate());
	    if (null == opportunityDetail.getSteadyStateStartDate()
		    || null == opportunityDetail.getSteadyStateEndDate()) {
		logger.info("SolutionID:"
			+ solutionID
			+ " Opportunity details steady state start& end dates not found:"
			+ opportunityDetail.getSteadyStateStartDate() + "|"
			+ opportunityDetail.getSteadyStateEndDate());
		return "";
	    }
	    steadyStartDt.setTime(opportunityDetail.getSteadyStateStartDate());
	    steadyEndDt.setTime(opportunityDetail.getSteadyStateEndDate());
	} catch (Exception e) {
	    logger.info("SolutionID:"
		    + solutionID
		    + " Opportunity details not found:"
		    + (null != opportunityDetail ? opportunityDetail
			    .getSteadyStateStartDate() : opportunityDetail));
	    return "";
	}
	if (steadyEndDt.compareTo(steadyStartDt) < 0) {
	    logger.error("Invalid opportunity time line: Start date:"
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

    @Override
    public Integer saveAdditionalFTE(AdditionalFTE additionalFTE) {
	Integer generatedID = additionalFTE.getAdditionalFteid();
	if (null != generatedID && generatedID >= 0) {
	    solutionFTEDAO.saveObject(additionalFTE);
	} else {
	    generatedID = (Integer) solutionFTEDAO
		    .saveObjectReturnId(additionalFTE);
	}
	return generatedID;
    }
    
    @Override
	public Map<Integer, String> getJobStages(String jobRole) throws MSSPException {
		Map<Integer, String> jobStagesMap = null;
		try {
			jobStagesMap = new LinkedHashMap<Integer, String>();
			List<JobRoleStages> jobRoleStages = solutionFTEDAO.getJobStages(jobRole);
			for (JobRoleStages jobStage : jobRoleStages) {
				jobStagesMap.put(jobStage.getJobRoleStagesId(),
						jobStage.getJobStage().getStage());
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + " |  " + e.getCause());
			throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		}
		return jobStagesMap;
	}
    
    @Override
	public Map<Integer, String> getJobRoleByServiceElementId(Integer serviceElementId) throws MSSPException {
    	Map<Integer, String> jobRolesMap = new LinkedHashMap<Integer, String>();
    	
		List<ServiceElementJobRoleStages> serEleJobRoles = solutionFTEDAO.getJobRoleByServiceElementId(serviceElementId);
		
		for(ServiceElementJobRoleStages serviceElementJobRoles : serEleJobRoles ){
			jobRolesMap.put(serviceElementJobRoles.getJobRoleStages().getJobRoleStagesId(), serviceElementJobRoles.getJobRoleStages().getJobRole().getRole());
		}
		
		return jobRolesMap;
	}
    
    @Override
	public Map<Integer, String> getJobRoleDropdownByServiceElementId(Integer serviceElementId) throws MSSPException {
    	Map<Integer, String> jobRolesMap = new LinkedHashMap<Integer, String>();
    	StringBuffer roleStageName = new StringBuffer();
    	
		List<ServiceElementJobRoleStages> serEleJobRoles = solutionFTEDAO.getJobRoleByServiceElementId(serviceElementId);
		
		for(ServiceElementJobRoleStages serviceElementJobRoles : serEleJobRoles ){
			roleStageName = new StringBuffer().append(serviceElementJobRoles.getJobRoleStages().getJobRole().getRole()).append(" ").append(serviceElementJobRoles.getJobRoleStages().getJobStage().getStage());
			jobRolesMap.put(serviceElementJobRoles.getJobRoleStages().getJobRoleStagesId(), roleStageName.toString());
		}
		
		return jobRolesMap;
	}
    
    @Override
    public Map<Integer, String> getJobRolesDropdown(int ccmFlag) throws MSSPException {
	Map<Integer, String> jobRolesMap = null;
	try {
	    jobRolesMap = new LinkedHashMap<Integer, String>(10);
    	StringBuffer roleStageName = new StringBuffer();
	    @SuppressWarnings("unchecked")
	    List<JobRoleStages> jobRoleStages = solutionFTEDAO.getJobRoles(ccmFlag);
	    for (JobRoleStages jr : jobRoleStages) {
	    	roleStageName = new StringBuffer().append(jr.getJobRole().getRole()).append(" ").append(jr.getJobStage().getStage());
	    	jobRolesMap.put(jr.getJobRoleStagesId(), roleStageName.toString());
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage() + " |  " + e.getCause());
	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	}
	return jobRolesMap;
    }
    
 
    @Override
	public ServiceElement getServiceElementIdByOppScopeId(Integer oppScopeId) throws MSSPException {
		return solutionFTEDAO.getServiceElementIdByOppScopeId(oppScopeId);
	}
    
    @Override
    public List<FTESummary> getFteSummaryListByPassedParam(Integer solutionID,
    	    Integer serviceScopeID,String location, String subLocation)
    	    throws MSSPException {
    	List<FTESummary> FTESummaryList = null;
    	try {
    		if (subLocation!=null) {
    			FTESummaryList = solutionFTEDAO.getFteSummaryListByPassedParam(solutionID, serviceScopeID, location, subLocation);
    		} else {
    			FTESummaryList = solutionFTEDAO.getFteSummaryListByPassedParam(solutionID, serviceScopeID, location);
    		}
    	} catch (Exception e) {
    	    logger.error(e.getMessage() + " |  " + e.getCause());
    	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return FTESummaryList;
    }
    
    
    @Override
    public ServiceScopeDTO getServiceScopeForOpportunityScopeId(
			Integer opportunityId) throws MSSPException {
    	
    	ServiceScopeDTO serviceScopeDTO = null;
    	try {
    		serviceScopeDTO = solutionDAO.getServiceScopeForOpportunityScopeId(opportunityId);
    	} catch (Exception e) {
    	    logger.error(e.getMessage() + " |  " + e.getCause());
    	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return serviceScopeDTO;
    }
    
    @Override
    public List<FTEStaging> getFtestagingListByPassedParam(Integer solutionID,
    	    Integer serviceScopeID, Integer jobRoleStageID,String location, String subLocation)
    	    throws MSSPException {
    	List<FTEStaging> FTEStagingList = null;
    	try {
    		FTEStagingList = solutionFTEDAO.getFtestagingListByPassedParam(solutionID, serviceScopeID, jobRoleStageID, location, subLocation);
    	} catch (Exception e) {
    	    logger.error(e.getMessage() + " |  " + e.getCause());
    	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return FTEStagingList;
    	
    }
    
    @Override
    public List<JobRoleStages> getSpecificJobRolesForSolution(Integer solutionID , Integer opportunityScopeID) throws MSSPException {
    	List<JobRoleStages> jobRoleList = null;
    	try {
    		jobRoleList = solutionFTEDAO.getSpecificJobRolesForSolution(solutionID, opportunityScopeID);
    	} catch (Exception e) {
    	    logger.error(e.getMessage() + " |  " + e.getCause());
    	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return jobRoleList;
    }
    
    @Override
    public List<FTEStaging> getFTEStagingObjects(Integer solutionID,Integer serviceScopeID, Integer jobRoleStageID, Date fromDate,Date toDate, String location, String subLocation) throws MSSPException {
    	List<FTEStaging> FTEStagingList = null;
    	try {
    			FTEStagingList = solutionFTEDAO.getFtestagingByPassedParam(solutionID, serviceScopeID, jobRoleStageID, fromDate,toDate, location, subLocation);
    	} catch (Exception e) {
    	    logger.error(e.getMessage() + " |  " + e.getCause());
    	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return FTEStagingList;
    }
    
    @Override
    public void saveAdditionalFTE(List<FTEStaging> fteStagingList) throws MSSPException{
    	try {
    		solutionFTEDAO.saveFTEStagingList(fteStagingList);
    	} catch (Exception e) {
    	    logger.error(e.getMessage() + " |  " + e.getCause());
    	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    }
    
    @Override
    public int  getFteCount(Integer solutionID,
    	    Integer serviceScopeID, Integer jobRoleStageID, String location, String subLocation) throws MSSPException {
    	int count = 0;
    	try {
    		count = solutionFTEDAO.getFteCount(solutionID, serviceScopeID, jobRoleStageID, location, subLocation);
    	} catch (Exception e) {
    	    logger.error(e.getMessage() + " |  " + e.getCause());
    	    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return count;
    }
    
    @Override
    public OpportunityDetail getOpportunityDetail(Integer opportunityId) throws MSSPException {
    	OpportunityDetail opportunityDetail = null;
    	try {
    		opportunityDetail = solutionLeverDAO.getOpportunityDetail(opportunityId);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return opportunityDetail;
    }
    
    @Override
    public Solution getSolution(Integer solutionId) throws MSSPException {
    	
    	Solution solution = null;
    	try {
    		solution = solutionDAO.getSolution(solutionId);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	
    	return solution;
    	
    }
    
    @Override
    public OpportunityScope getOpportunityScope(Integer opportunityScopeID) throws MSSPException {
    	OpportunityScope opportunityScope = null;
    	try {
    		opportunityScope = opportunityScopeDAO.getOpportunityScope(opportunityScopeID);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return opportunityScope;
    	
    }
    
    @Override
    public JobRoleStages getJobRoleStagesByjobRoleStagesId(Integer jobRoleStagesId) throws MSSPException{
    	JobRoleStages jobRoleStages = null;
    	try {
    		jobRoleStages = JobRoleStagesDAO.getJobRoleStagesByjobRoleStagesId(jobRoleStagesId);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return jobRoleStages;
    }
    
    @Override
    public List<MonthFTEDTO> fetchFTESummary(Integer solutionID,Integer serviceScopeID,String location, String subLocation) throws MSSPException {
    	List<Object[]> list = null;
    	List<MonthFTEDTO> fteDtoList = null;
    	try {
    		list = solutionFTEDAO.fetchFTESummary(solutionID, serviceScopeID, location, subLocation);
    		if (list!=null && list.size() > 0) {
    			fteDtoList = new ArrayList<>();
    			for (Object[] objects : list) {
    				MonthFTEDTO monthFTEDTO =  new MonthFTEDTO();
    				monthFTEDTO.setDate((Date)objects[1]);
    				monthFTEDTO.setFteCount(((Double) objects[0]).floatValue());
    				fteDtoList.add(monthFTEDTO);
				}
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return fteDtoList;
    }
    
    @Override
    public SolutionLever  getSolutionLeverForSolution(Integer solutionID) throws MSSPException {
    	SolutionLever solutionLever = null;
    	try {
    		solutionLever = solutionLeverDAO.getSolutionLever(solutionID);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return solutionLever;
    }
    
    @Override
    public boolean isFTEStagingRecordAvailable (Integer solutionID,Integer opportunityScopeID) throws MSSPException {
    	boolean result = false;
    	try {
    		result = solutionFTEDAO.isFTEStagingRecordAvailable(solutionID,opportunityScopeID);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return result;
    }
    
    @Override
    public boolean isFTESummaryRecordsAvailable(Integer solutionID,Integer opportunityScopeID) throws MSSPException {
    	boolean result = false;
    	try {
    		result = solutionFTEDAO.isFTESummaryRecordsAvailable(solutionID,opportunityScopeID);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return result;
    }
    
    @Override
    public List<LocationBreakup> getLocationBreakupByOppScopeID(Integer oppScopeId) throws MSSPException{
    	List<LocationBreakup> locationBreakupList = null;
    	try {
    		locationBreakupList = solutionLeverDAO.loadLocationBreakupByOppScopeID(oppScopeId);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return locationBreakupList;
    	
    }
    
    @Override
    public List<FTESummary> getFTESummaryBySolnIDoppID(Integer solutionID,Integer opportunityScopeID)
    	    throws MSSPException {
    	List<FTESummary> fteSummaryList = null;
    	try {
    		fteSummaryList = solutionFTEDAO.getFTESummaryBySolnIDoppID(solutionID, opportunityScopeID);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return fteSummaryList;
    }
    
    @Override
    public List<LocationPyramid> getLocationPyramidForOppScopeID(Integer opportunityScopeID) throws MSSPException {
    	List<LocationPyramid> locationPyramidList = null;
    	try {
    		locationPyramidList = solutionFTEDAO.getLocationPyramidForOppScopeID(opportunityScopeID);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return locationPyramidList;
    }
    
    @Override
    public List<LocationPyramid> getLocPyramidSiteWise(Integer opportunityScopeID,String location, String subLocation) throws MSSPException {
    	List<LocationPyramid> locationPyramidList = null;
    	try {
    		locationPyramidList = solutionFTEDAO.getLocPyramidSiteWise(opportunityScopeID, location, subLocation);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return locationPyramidList;
    }
    
    @Override
    public int removeAllFTEStagingBySolutionID(Integer solutionID) throws MSSPException{
    	int deletedFTEStagingRecsCount ;
    	try {
    		deletedFTEStagingRecsCount = solutionFTEDAO.removeAllFTEStagingBySolutionID(solutionID);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new MSSPException(e.getMessage() + " |  " + e.getCause());
    	}
    	return deletedFTEStagingRecsCount;
    }
    
    private static Date getDateFromObject(Object obj) throws Exception{
    	Date date = null;
    	SimpleDateFormat dateFormatter = null;
    	try {
    		dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    		String dateStr = (String)obj;
    		date = dateFormatter.parse(dateStr);
    	} catch (Exception e) {
    		throw e;
    	}
    	return date;
    }
}
