/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.controller
 * File name       		    :  NonLabourCostController.java
 * Description				:	It is the controller for Non Labour cost page view
 * Author, Date & Release	:	Dec 21, 20122012
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Dec 21, 2012  	   |edassri   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.CountryDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.entity.NonLabourCost;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.forms.NonLabourCostForm;
import com.ericsson.mssp.solution.forms.NLCForm;
import com.ericsson.mssp.solution.forms.NLCForm.InputParams;
import com.ericsson.mssp.solution.forms.ReviewFTEForm;
import com.ericsson.mssp.solution.service.INLCService;
import com.ericsson.mssp.solution.service.ISolutionFTEService;
import com.ericsson.mssp.solution.service.ISolutionService;

/**
 * @author edassri
 * 
 */
@Controller
public class NonLabourCostController extends BaseController implements
	MSSPConstants {
    
    private static final Logger logger = Logger
	    .getLogger(NonLabourCostController.class);
    @Autowired
    private ISolutionService solutionService;
    @Autowired
    private ISolutionFTEService solutionFTEService;
    @Autowired
    private INLCService nlcService;
    
    private String opMode = "E";// Operation mode E/V
    private Integer solutionID = 1;
    private static String[] mnthString=new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    @RequestMapping(value = { "/solution/nonLabourCost",
	    "/solution/nonLaborCost" })
    public String initForm(
	    ModelMap model,
	    @ModelAttribute("NonLabourCostForm") NonLabourCostForm nonLabourCostForm,
	    HttpSession session) throws MSSPException {

	String sessSolId = (null != session.getAttribute(SOLUTION_ID)) ? session
		.getAttribute(SOLUTION_ID).toString() : null;
	logger.info("Reached....session:" + session + " Years:"
		+ nonLabourCostForm.getYear() + " sessSolId=" + sessSolId);
	if (null != sessSolId) {
	    try {
		solutionID = Integer.valueOf(sessSolId);
	    } catch (NumberFormatException nfe) {
		logger.info("Session solution ID not found for key:"
			+ SOLUTION_ID + " Returned value:" + sessSolId);
	    }
	} else {
	    nonLabourCostForm.setSolutionId(null);
	    model.addAttribute("nonLabourCostForm", nonLabourCostForm);
	    return "nonLabourCost";
	}

	nonLabourCostForm = solutionService.getSolutionNLC(solutionID);

	// Operation mode to be set down here
	// mode of operation
	String sessOpMode = (null != session.getAttribute(OPERATION_MODE)) ? session
		.getAttribute(OPERATION_MODE).toString() : null;
	if (null != sessOpMode && sessOpMode.length() == 1) {
	    opMode = sessOpMode.toUpperCase();
	}
	// nonLabourCostForm.setYear("2010;");
	// nonLabourCostForm.setItnoofTravels("55;");
	// nonLabourCostForm.setHwsw3ppcost("77;");
	nonLabourCostForm.setOperationMode(opMode);
	nonLabourCostForm.setSolutionId(solutionID);
	model.addAttribute("nonLabourCostForm", nonLabourCostForm);
	// Opportunity display data
	OpportunityDTO opportunityDTO = new OpportunityDTO();
	if (getSessionOpportunityId(session) != null) {
	    opportunityDTO = solutionService
		    .getOpportunity(getSessionOpportunityId(session));
	}
	model.addAttribute("opportunityDTO", opportunityDTO);
	// Service Bucket table data
	List<String> serviceBucketData = solutionService
		.loadServiceBucketDataByOppSolutionID(
			getSessionOpportunityId(session), solutionID);
	model.addAttribute("serviceBucketData", serviceBucketData);
	// check if user has edit access
	String hasEditSolAccess = (String) getSessionValueFromKey(session,
		HAS_EDIT_SOL_ACCESS);
	model.addAttribute("hasEditSolAccess", hasEditSolAccess);
	logger.info("Done....");
	// return form view
	return "nonLabourCost";
    }

    @RequestMapping("/solution/saveNonLabourCost")
    public String saveNonLabourCost(
	    ModelMap model,
	    @ModelAttribute("NonLabourCostForm") NonLabourCostForm nonLabourCostForm,
	    @RequestParam(value = "nonlabourCostId", required = false, defaultValue = "NA") String[] nonlabourCostId,
	    @RequestParam(value = "itnoofTravels", required = false, defaultValue = "NA") String[] itnoofTravels,
	    @RequestParam(value = "itstayDuration", required = false, defaultValue = "NA") String[] itstayDuration,
	    @RequestParam(value = "itshortVisaCost", required = false, defaultValue = "NA") String[] itshortVisaCost,
	    @RequestParam(value = "ittickectCost", required = false, defaultValue = "NA") String[] ittickectCost,
	    @RequestParam(value = "itdailyPerDiem", required = false, defaultValue = "NA") String[] itdailyPerDiem,
	    @RequestParam(value = "ithotelCostPerNight", required = false, defaultValue = "NA") String[] ithotelCostPerNight,
	    @RequestParam(value = "itconveyancePerDay", required = false, defaultValue = "NA") String[] itconveyancePerDay,
	    @RequestParam(value = "dtnoofTravels", required = false, defaultValue = "NA") String[] dtnoofTravels,
	    @RequestParam(value = "dtstayDuration", required = false, defaultValue = "NA") String[] dtstayDuration,
	    @RequestParam(value = "dttickectCost", required = false, defaultValue = "NA") String[] dttickectCost,
	    @RequestParam(value = "dtdailyPerDiem", required = false, defaultValue = "NA") String[] dtdailyPerDiem,
	    @RequestParam(value = "dthotelCostPerNight", required = false, defaultValue = "NA") String[] dthotelCostPerNight,
	    @RequestParam(value = "dtconveyancePerDay", required = false, defaultValue = "NA") String[] dtconveyancePerDay,
	    @RequestParam(value = "egiconnectivityCost", required = false, defaultValue = "NA") String[] egiconnectivityCost,
	    @RequestParam(value = "gscconnectivityCost", required = false, defaultValue = "NA") String[] gscconnectivityCost,
	    @RequestParam(value = "otherConnectivityCost", required = false, defaultValue = "NA") String[] otherConnectivityCost,
	    @RequestParam(value = "monthlyNightAllowance", required = false, defaultValue = "NA") String[] monthlyNightAllowance,
	    @RequestParam(value = "pcpeopleAtNight", required = false, defaultValue = "NA") String[] pcpeopleAtNight,
	    @RequestParam(value = "monthlyWeekendAllowance", required = false, defaultValue = "NA") String[] monthlyWeekendAllowance,
	    @RequestParam(value = "pcpeopleAtWeekend", required = false, defaultValue = "NA") String[] pcpeopleAtWeekend,
	    @RequestParam(value = "monthMobileCost", required = false, defaultValue = "NA") String[] monthMobileCost,
	    @RequestParam(value = "monthlyDataCardCost", required = false, defaultValue = "NA") String[] monthlyDataCardCost,
	    @RequestParam(value = "hwsw3ppcost", required = false, defaultValue = "NA") String[] hwsw3ppcost,
	    @RequestParam(value = "trainingCost", required = false, defaultValue = "NA") String[] trainingCost,
	    @RequestParam(value = "otherCost", required = false, defaultValue = "NA") String[] otherCost,
	    HttpSession session) throws MSSPException {
	String sessSolId = (null != session.getAttribute(SOLUTION_ID)) ? session
		.getAttribute(SOLUTION_ID).toString() : null;
	if (null != sessSolId) {
	    try {
		solutionID = Integer.valueOf(sessSolId);
	    } catch (NumberFormatException nfe) {
		logger.info("Session solution ID not found for key:"
			+ SOLUTION_ID + " Returned value:" + sessSolId);
	    }
	}
	String years = solutionService
		.getOpportunitySteadyYearsString(solutionID);
	Solution solution = solutionService.getSolutionById(solutionID);
	logger.info(" years::::::::::::::::::" + years);
	String[] year = years.split(";");
	// nonLabourCostForm = solutionService.getSolutionNLC(solutionID);
	for (int yr = 0; yr < year.length; yr++) {
	    NonLabourCost nonLabourCost = new NonLabourCost();
	    nonLabourCost.setYear(Integer.valueOf(year[yr]));
	    nonLabourCost.setSolution(solution);
	    if (null != nonlabourCostId) {
		try {
		    nonLabourCost.setNonlabourCostId(Integer
			    .valueOf(nonlabourCostId[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(nonlabourCostId + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(nonlabourCostId[yr] + " Exc:"
			    + nfe.getMessage());
		}
	    }

	    if (null != itnoofTravels) {
		try {
		    nonLabourCost.setItnoofTravels(Integer
			    .valueOf(itnoofTravels[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(itnoofTravels + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(itnoofTravels[yr] + " Exc:" + nfe.getMessage());
		}
	    }

	    if (null != itstayDuration) {
		try {
		    nonLabourCost.setItstayDuration(Integer
			    .valueOf(itstayDuration[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(itstayDuration + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(itstayDuration[yr] + " Exc:" + nfe.getMessage());
		}
	    }

	    if (null != itshortVisaCost) {
		try {
		    nonLabourCost.setItshortVisaCost(Integer
			    .valueOf(itshortVisaCost[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(itshortVisaCost + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(itshortVisaCost[yr] + " Exc:"
			    + nfe.getMessage());
		}
	    }

	    if (null != ittickectCost) {
		try {
		    nonLabourCost.setIttickectCost(Integer
			    .valueOf(ittickectCost[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(ittickectCost + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(ittickectCost[yr] + " Exc:" + nfe.getMessage());
		}
	    }

	    if (null != itdailyPerDiem) {
		try {
		    nonLabourCost.setItdailyPerDiem(Integer
			    .valueOf(itdailyPerDiem[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(itdailyPerDiem + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(itdailyPerDiem[yr] + " Exc:" + nfe.getMessage());
		}
	    }

	    if (null != ithotelCostPerNight) {
		try {
		    nonLabourCost.setIthotelCostPerNight(Integer
			    .valueOf(ithotelCostPerNight[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(ithotelCostPerNight + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(ithotelCostPerNight[yr] + " Exc:"
			    + nfe.getMessage());
		}
	    }

	    if (null != itconveyancePerDay) {
		try {
		    nonLabourCost.setItconveyancePerDay(Integer
			    .valueOf(itconveyancePerDay[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(itconveyancePerDay + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(itconveyancePerDay[yr] + " Exc:"
			    + nfe.getMessage());
		}
	    }

	    if (null != dtnoofTravels) {
		try {
		    nonLabourCost.setDtnoofTravels(Integer
			    .valueOf(dtnoofTravels[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(dtnoofTravels + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(dtnoofTravels[yr] + " Exc:" + nfe.getMessage());
		}
	    }

	    if (null != dtstayDuration) {
		try {
		    nonLabourCost.setDtstayDuration(Integer
			    .valueOf(dtstayDuration[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(dtstayDuration + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(dtstayDuration[yr] + " Exc:" + nfe.getMessage());
		}
	    }

	    if (null != dttickectCost) {
		try {
		    nonLabourCost.setDttickectCost(Integer
			    .valueOf(dttickectCost[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(dttickectCost + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(dttickectCost[yr] + " Exc:" + nfe.getMessage());
		}
	    }

	    if (null != dtdailyPerDiem) {
		try {
		    nonLabourCost.setDtdailyPerDiem(Integer
			    .valueOf(dtdailyPerDiem[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(dtdailyPerDiem + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(dtdailyPerDiem[yr] + " Exc:" + nfe.getMessage());
		}
	    }

	    if (null != dthotelCostPerNight) {
		try {
		    nonLabourCost.setDthotelCostPerNight(Integer
			    .valueOf(dthotelCostPerNight[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(dthotelCostPerNight + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(dthotelCostPerNight[yr] + " Exc:"
			    + nfe.getMessage());
		}
	    }

	    if (null != dtconveyancePerDay) {
		try {
		    nonLabourCost.setDtconveyancePerDay(Integer
			    .valueOf(dtconveyancePerDay[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(dtconveyancePerDay + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(dtconveyancePerDay[yr] + " Exc:"
			    + nfe.getMessage());
		}
	    }

	    if (null != egiconnectivityCost) {
		try {
		    nonLabourCost.setEgiconnectivityCost(Integer
			    .valueOf(egiconnectivityCost[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(egiconnectivityCost + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(egiconnectivityCost[yr] + " Exc:"
			    + nfe.getMessage());
		}
	    }

	    if (null != gscconnectivityCost) {
		try {
		    nonLabourCost.setGscconnectivityCost(Integer
			    .valueOf(gscconnectivityCost[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(gscconnectivityCost + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(gscconnectivityCost[yr] + " Exc:"
			    + nfe.getMessage());
		}
	    }

	    if (null != otherConnectivityCost) {
		try {
		    nonLabourCost.setOtherConnectivityCost(Integer
			    .valueOf(otherConnectivityCost[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(otherConnectivityCost + " index-" + yr
			    + " Exc:" + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(otherConnectivityCost[yr] + " Exc:"
			    + nfe.getMessage());
		}
	    }

	    if (null != monthlyNightAllowance) {
		try {
		    nonLabourCost.setMonthlyNightAllowance(Integer
			    .valueOf(monthlyNightAllowance[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(monthlyNightAllowance + " index-" + yr
			    + " Exc:" + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(monthlyNightAllowance[yr] + " Exc:"
			    + nfe.getMessage());
		}
	    }

	    if (null != pcpeopleAtNight) {
		try {
		    nonLabourCost.setPcpeopleAtNight(Integer
			    .valueOf(pcpeopleAtNight[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(pcpeopleAtNight + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(pcpeopleAtNight[yr] + " Exc:"
			    + nfe.getMessage());
		}
	    }

	    if (null != monthlyWeekendAllowance) {
		try {
		    nonLabourCost.setMonthlyWeekendAllowance(Integer
			    .valueOf(monthlyWeekendAllowance[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(monthlyWeekendAllowance + " index-" + yr
			    + " Exc:" + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(monthlyWeekendAllowance[yr] + " Exc:"
			    + nfe.getMessage());
		}
	    }

	    if (null != pcpeopleAtWeekend) {
		try {
		    nonLabourCost.setPcpeopleAtWeekend(Integer
			    .valueOf(pcpeopleAtWeekend[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(pcpeopleAtWeekend + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(pcpeopleAtWeekend[yr] + " Exc:"
			    + nfe.getMessage());
		}
	    }

	    if (null != monthMobileCost) {
		try {
		    nonLabourCost.setMonthMobileCost(Integer
			    .valueOf(monthMobileCost[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(monthMobileCost + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(monthMobileCost[yr] + " Exc:"
			    + nfe.getMessage());
		}
	    }

	    if (null != monthlyDataCardCost) {
		try {
		    nonLabourCost.setMonthlyDataCardCost(Integer
			    .valueOf(monthlyDataCardCost[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(monthlyDataCardCost + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(monthlyDataCardCost[yr] + " Exc:"
			    + nfe.getMessage());
		}
	    }

	    if (null != hwsw3ppcost) {
		try {
		    nonLabourCost.setHwsw3ppcost(Integer
			    .valueOf(hwsw3ppcost[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(hwsw3ppcost + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(hwsw3ppcost[yr] + " Exc:" + nfe.getMessage());
		}
	    }

	    if (null != trainingCost) {
		try {
		    nonLabourCost.setTrainingCost(Integer
			    .valueOf(trainingCost[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(trainingCost + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(trainingCost[yr] + " Exc:" + nfe.getMessage());
		}
	    }

	    if (null != otherCost) {
		try {
		    nonLabourCost.setOtherCost(Integer.valueOf(otherCost[yr]));

		} catch (IndexOutOfBoundsException iobe) {
		    logger.info(otherCost + " index-" + yr + " Exc:"
			    + iobe.getMessage());

		} catch (NumberFormatException nfe) {
		    logger.info(otherCost[yr] + " Exc:" + nfe.getMessage());
		}
	    }

	    // Save the loaded entity
	    solutionService.saveSolutionNLC(nonLabourCost);
	}

	// Operation mode to be set down here
	// mode of operation
	String sessOpMode = (null != session.getAttribute(OPERATION_MODE)) ? session
		.getAttribute(OPERATION_MODE).toString() : "E";
	if (sessOpMode.length() == 1) {
	    opMode = sessOpMode.toUpperCase();
	}
	// nonLabourCostForm.setYear("2010;");
	// nonLabourCostForm.setItnoofTravels("55;");
	// nonLabourCostForm.setHwsw3ppcost("77;");
	nonLabourCostForm.setOperationMode(opMode);
	nonLabourCostForm.setSolutionId(solutionID);
	model.addAttribute("nonLabourCostForm", nonLabourCostForm);
	logger.info("Done....");
	// return form view
	return "nonLabourCost_new";
    }
    
    /*****Re Engineering NonLabour cost*****/
    @RequestMapping(value = { "/solution/loadNLC"})
public String loadNLCPage(
    ModelMap model,
    @ModelAttribute("NonLabourCostForm") NLCForm nonLabourCostForm,
			HttpSession session) throws Exception {

		solutionID = getSessionSolutionId(session);
		logger.info("Entered NonLabourCostController[loadNLCPage] SolutionId:"
				+ solutionID);
		nonLabourCostForm=new NLCForm();
		model.addAttribute("NonLabourCostForm", nonLabourCostForm);
		try {
			Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();
			optionMap.put(1, "Yes");
			optionMap.put(0, "No");
			model.addAttribute("optionMap", optionMap);
            
			Map<String, String> invoiceTypeMap = new LinkedHashMap<String, String>();
			invoiceTypeMap.put("PO", "PO");
			invoiceTypeMap.put("ICRRB", "ICRRB");
			model.addAttribute("invoiceTypeMap", invoiceTypeMap);
			
			OpportunityDTO opportunityDTO = solutionService
					.getOpportunity(getSessionOpportunityId(session));
			// Solution solution = solutionService.getSolutionById(solutionID);
			CountryDTO countryDTO = opportunityDTO.getCustomerDTO()
					.getCountryDTO();

			InputParams inputParams = nonLabourCostForm.getInputParams();
			inputParams.setRegion(countryDTO.getRegion());
			if("GBP".equalsIgnoreCase(countryDTO.getCurrencyCode())||
				"INR".equalsIgnoreCase(countryDTO.getCurrencyCode())||
				"USD".equalsIgnoreCase(countryDTO.getCurrencyCode())){
				inputParams.setCurrrencyCode(countryDTO.getCurrencyCode());
			}else{
				inputParams.setCurrrencyCode("USD");
			}

			ReviewFTEForm reviewFTEForm = new ReviewFTEForm();
			reviewFTEForm.setSolutionID(solutionID);
			reviewFTEForm
					.setMonthYears(solutionFTEService
							.getOpportunityTransStartSteadyEndMonthYearsString(solutionID));
			reviewFTEForm.setsServiceScope(-1);

			reviewFTEForm = solutionFTEService.loadFTETableDisplayValues(
					reviewFTEForm, false);

			nonLabourCostForm.setReviewFTEForm(reviewFTEForm);

			// Totaling
			List<Float> onshoreFTEList = new ArrayList<Float>();
			List<Float> offshoreFTEList = new ArrayList<Float>();
			String temp = "";
			Float tempNum = 0F;
			if (reviewFTEForm.getOnShoreDataList() != null
					&& reviewFTEForm.getOnShoreDataList().get(0) != null) {
				if (reviewFTEForm.getOnShoreDataList().get(0).contains(";")) {
					int loopLength = reviewFTEForm.getOnShoreDataList().get(0)
							.split(";").length;
					for (int i = 0; i < loopLength; i++) {
						tempNum = 0F;
						for (int j = 0; j < reviewFTEForm.getOnShoreDataList()
								.size(); j++) {
							temp = reviewFTEForm.getOnShoreDataList().get(j)
									.split(";")[i];
							tempNum += Float.parseFloat(temp);
						}
						// onshoreFTEList.add(tempNum);
						onshoreFTEList
								.add(Math.round(tempNum * 100.0f) / 100.0f);
					}
				}
			}
			nonLabourCostForm.setSsOnshoreFTE(onshoreFTEList);

			if (reviewFTEForm.getOffShoreDataList() != null
					&& reviewFTEForm.getOffShoreDataList().get(0) != null) {
				reviewFTEForm.getOffShoreDataList().get(0).contains(";");
				int loopLength = reviewFTEForm.getOffShoreDataList().get(0)
						.split(";").length;
				for (int i = 0; i < loopLength; i++) {
					tempNum = 0F;
					for (int j = 0; j < reviewFTEForm.getOffShoreDataList()
							.size(); j++) {
						temp = reviewFTEForm.getOffShoreDataList().get(j)
								.split(";")[i];
						tempNum += Float.parseFloat(temp);
					}
					offshoreFTEList.add(Math.round(tempNum * 100.0f) / 100.0f);
				}
			}
			nonLabourCostForm.setSsOffShoreFTE(offshoreFTEList);

			for (int i = 0; i < onshoreFTEList.size(); i++) {
				nonLabourCostForm
						.getSsOnsiteCalDays()
						.add(Math.round((onshoreFTEList.get(i) * 30 * 100.0f)) / 100.0f);
				nonLabourCostForm
						.getSsOnsiteWorkDays()
						.add(Math.round(onshoreFTEList.get(i)
								* inputParams.getWorkingDaysInMonth() * 100.0f) / 100.0f);
			}

			if (nonLabourCostForm.getSsOnshoreFTE().size() > 0) { //Allow only if ReviewFTE is available
				// Transition Data
				nlcService.retrieveNLC(solutionID, nonLabourCostForm);

				String stDateStr = opportunityDTO.getOpportunityDetailsDTO()
						.getTsd();
				String endDateStr = opportunityDTO.getOpportunityDetailsDTO()
						.getTed();
				Date stDate=null,endDate=null;
				List<String> transMnthYears=null;
				if(!"".equals(stDateStr) && !"".equals(endDateStr)){
					stDate = parseDate(stDateStr, "MM/dd/yyyy");
					endDate = parseDate(endDateStr, "MM/dd/yyyy");
					transMnthYears = MonthYearsBetween(stDate, endDate);
					nonLabourCostForm.setTransMonthYears(transMnthYears);
					// nonLabourCostForm.setTransOnshoreFTE(nlcForm.getTransOnshoreFTE());
					if(transMnthYears.size()!=nonLabourCostForm.getTransOnshoreFTE().size()&& nonLabourCostForm.getTransOnshoreFTE().size()!=0){
						//Transition Data Not Correct in TTSummary. More or less entries present
						//nonLabourCostForm.setTransOnshoreFTE(new ArrayList<Float>());
						logger.info("Months extracted from TTSummary is not equal to months extracted from TSD and TED");
						throw new MSSPException("Months extracted from TTSummary is not equal to months extracted from TSD and TED");
					}
					
				}
				
				nonLabourCostForm.setSsMonthYears(Arrays.asList(reviewFTEForm
						.getMonthYears().split(";")));

				for (int i = 0; i < nonLabourCostForm.getTransOnshoreFTE()
						.size(); i++) {
					nonLabourCostForm.getTransOnsiteCalDays().add(
							Math.round((nonLabourCostForm.getTransOnshoreFTE()
									.get(i) * 30 * 100.0f)) / 100.0f);
					nonLabourCostForm.getTransOnsiteWorkDays().add(
							Math.round(nonLabourCostForm.getTransOnshoreFTE()
									.get(i)
									* inputParams.getWorkingDaysInMonth()
									* 100.0f) / 100.0f);
				}

				if (nonLabourCostForm.getTransOnshoreFTE().size() == 0) {
					// Transition data not entered..populate steady state only
					for (int i = 0; i < nonLabourCostForm.getSsMonthYears()
							.size(); i++) {
						nonLabourCostForm.getOtherMonthYears().add(
								nonLabourCostForm.getSsMonthYears().get(i));
						nonLabourCostForm.getOtherOffShoreFTE().add(
								offshoreFTEList.get(i));
					}
				} else {
					// Transition data exists..populate both ss and transition
					// years
					boolean overlap = nonLabourCostForm
							.getSsMonthYears()
							.get(0)
							.equals(transMnthYears.get(transMnthYears.size() - 1));
					if (!overlap) { // no overlapping between Steady state and
									// transition
						int totLength = transMnthYears.size()
								+ nonLabourCostForm.getSsMonthYears().size();
						int count = 0;
						int transCount = 0;
						int ssCount = 0;
						while (count < totLength) {
							if (count < transMnthYears.size()) {
								nonLabourCostForm.getOtherMonthYears().add(
										nonLabourCostForm.getTransMonthYears()
												.get(transCount));
								nonLabourCostForm.getOtherOffShoreFTE().add(
										nonLabourCostForm.getTransOffShoreFTE()
												.get(transCount));
								transCount++;
							} else {
								nonLabourCostForm.getOtherMonthYears().add(
										nonLabourCostForm.getSsMonthYears()
												.get(ssCount));
								nonLabourCostForm.getOtherOffShoreFTE().add(
										nonLabourCostForm.getSsOffShoreFTE()
												.get(ssCount));
								ssCount++;
							}
							count++;
						}
						if(nonLabourCostForm.getConnectivityToDev().size()<nonLabourCostForm.getOtherMonthYears().size()){
							nonLabourCostForm.setMsdpCost(moveElementsBy(nonLabourCostForm.getMsdpCost(),transCount));
							nonLabourCostForm.setConnectivityToIndia(moveElementsBy(nonLabourCostForm.getConnectivityToIndia(),transCount));
							nonLabourCostForm.setConnectivityToDev(moveElementsBy(nonLabourCostForm.getConnectivityToDev(),transCount));
							nonLabourCostForm.setTotalConnectivity(moveElementsBy(nonLabourCostForm.getTotalConnectivity(),transCount));
						}
						
					} else { // overlap between SS and Transition
						int totLength = transMnthYears.size()
								+ nonLabourCostForm.getSsMonthYears().size()
								- 1;
						int count = 0;
						int transCount = 0;
						int ssCount = 0;
						while (count < totLength) {
							if (count < (transMnthYears.size() - 1)) {
								nonLabourCostForm.getOtherMonthYears().add(
										nonLabourCostForm.getTransMonthYears()
												.get(transCount));
								nonLabourCostForm.getOtherOffShoreFTE().add(
										nonLabourCostForm.getTransOffShoreFTE()
												.get(transCount));
								transCount++;
							} else if (count == (transMnthYears.size() - 1)) {
								nonLabourCostForm.getOtherMonthYears().add(
										nonLabourCostForm.getTransMonthYears()
												.get(transCount));
								nonLabourCostForm.getOtherOffShoreFTE().add(
										nonLabourCostForm.getTransOffShoreFTE()
												.get(transCount)
												+ offshoreFTEList.get(ssCount));
								transCount++;
								ssCount++;
							} else {
								nonLabourCostForm.getOtherMonthYears().add(
										nonLabourCostForm.getSsMonthYears()
												.get(ssCount));
								nonLabourCostForm.getOtherOffShoreFTE().add(
										offshoreFTEList.get(ssCount));
								ssCount++;
							}
							count++;
						}
						if(nonLabourCostForm.getConnectivityToDev().size()<nonLabourCostForm.getOtherMonthYears().size()){
							nonLabourCostForm.setMsdpCost(moveElementsBy(nonLabourCostForm.getMsdpCost(),transCount-1));
							nonLabourCostForm.setConnectivityToIndia(moveElementsBy(nonLabourCostForm.getConnectivityToIndia(),transCount-1));
							nonLabourCostForm.setConnectivityToDev(moveElementsBy(nonLabourCostForm.getConnectivityToDev(),transCount-1));
							nonLabourCostForm.setTotalConnectivity(moveElementsBy(nonLabourCostForm.getTotalConnectivity(),transCount-1));
						}
					}

				}
			}
			//service bucket data and opp info
			if (opportunityDTO.getOpportunityId() != null) {
				model.addAttribute("opportunityDTO", opportunityDTO);
			}
			 List<String> serviceBucketData = solutionService
					    .loadServiceBucketDataByOppSolutionID(opportunityDTO.getOpportunityId(),
						    solutionID);
			 model.addAttribute("serviceBucketData", serviceBucketData);
		} catch (Exception e) {
			logger.error(
					"Exception in NonLabourCostController[loadNLCPage] SolutionId:"
							+ solutionID, e);
			throw new Exception(e.getMessage() + " |  " + e.getCause());
		}
		logger.info("Exit NonLabourCostController[loadNLCPage] SolutionId:"
				+ solutionID);
		return "nlc";
	}
   
	@RequestMapping(value = { "/solution/saveNLC"})
	public String saveNLC(
			ModelMap model,
			@ModelAttribute("NonLabourCostForm") NLCForm nlcForm,
			HttpSession session) throws Exception {

		Integer solId = getSessionSolutionId(session);
    	logger.info("Entered NonLabourCostController[saveNLC] SolutionId:"+solId);
		try{
			nlcForm.setSolutionId(solId);
		boolean flag=true;
		if(flag){
			nlcService.saveNLC(nlcForm);
		}
		}catch(MSSPException e){
			logger.error("Exception in NonLabourCostController[saveNLC] SolutionId:"+solId,e);
			throw new Exception(e.getMessage() + " |  " + e.getCause());
		}
		logger.info("Exit NonLabourCostController[saveNLC] SolutionId:"+solId);
		String successMsg="Data Saved Successfully";
		model.addAttribute("successMsg",successMsg);
		//return "redirect:/solution/loadNLC";
		return "forward:/solution/loadNLC";
	}
    
    private Date parseDate(String date, String format) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);
    }
    
    public  List<String> MonthYearsBetween(Date d1, Date d2) {
    	//String[] mnthString=new String[]{"Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"};
	    List<String> ret = new ArrayList<String>();
	    Calendar c1 = Calendar.getInstance();
	    Calendar c2 = Calendar.getInstance();
	    c1.setTime(d1);c2.setTime(d2);
	    while(c1.before(c2)){
	    	ret.add(mnthString[c1.get(Calendar.MONTH)]+"-"+c1.get(Calendar.YEAR));
	    	c1.add(Calendar.MONTH, 1);
	    }
	    return ret;
	}
    
	private List<Float> moveElementsBy(List<Float> origList, int moveBy) {
		if(moveBy<=0){
			return origList;
		}
		if (origList != null && origList.size()>0) {
			ArrayList<Float> tempList = new ArrayList<Float>();
			int count = 0;
			for (int i = 0; i < moveBy + origList.size(); i++) {
				if (i < moveBy) {
					tempList.add(0f);
				} else {
					tempList.add(origList.get(count));
					count++;
				}
			}
			return tempList;
		}else{
			return origList;
		}
	}
    
}
