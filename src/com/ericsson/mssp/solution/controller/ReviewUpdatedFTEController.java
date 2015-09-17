package com.ericsson.mssp.solution.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ericsson.mssp.common.dto.MonthFTEDTO;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.entity.AdditionalFTE;
import com.ericsson.mssp.common.entity.FTEStaging;
import com.ericsson.mssp.common.entity.FTESummary;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.LocationBreakup;
import com.ericsson.mssp.common.entity.LocationPyramid;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionLever;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.forms.ReviewFTEForm;
import com.ericsson.mssp.solution.service.ISolutionFTEService;
import com.ericsson.mssp.solution.service.ISolutionService;

/**
 * 
 * @author edasbil
 *
 */
@Controller
public class ReviewUpdatedFTEController extends BaseController implements
MSSPConstants {
	
	private static final Logger logger = Logger.getLogger(ReviewUpdatedFTEController.class);
		    		
	    @Autowired
	    private ISolutionFTEService solutionFTEService;

	    @Autowired
	    private ISolutionService solutionService;
	   
	/**
	 *     
	 * @param model
	 * @param reviewFTEForm
	 * @param sServiceScope
	 * @param session
	 * @return
	 * @throws MSSPException
	 */
	@RequestMapping("/solution/reviewUpdatedFTE")
    public String initForm(
	    ModelMap model,
	    @ModelAttribute("reviewFTEForm") ReviewFTEForm reviewFTEForm,
	    @RequestParam(value = "sServiceScope", required = false, defaultValue = "-1") Integer sServiceScope,
	    HttpSession session) throws MSSPException {
		Integer sessSolId = null;
		Integer sessOppId = null;
		Map<Integer, String> sScope = null;
		Map<String, Map<String, Float>> OnShoreLocalFTEMap = null;
		Map<String, Map<String, Float>> OnShoreLocalGSCFTEMap = null;
		Map<String, Map<String, Float>> OnShoreLocal3PPFTEMap = null;
		Map<String, Map<String, Float>> OffshoreFTEMap = null;
		Map<String, Map<String, Float>> NearshoreFTEMap = null;
		List<String> monthList = null;
		
		String returnString = null;
		String hasEditSolAccess = null;
		
		try {
			logger.debug("ReviewUpdatedFTEController::initForm::entry");
			OnShoreLocalFTEMap = new HashMap<>();
			OnShoreLocalGSCFTEMap = new HashMap<>();
			OnShoreLocal3PPFTEMap = new HashMap<>();
			OffshoreFTEMap = new HashMap<>();
			NearshoreFTEMap = new HashMap<>();
			monthList = new ArrayList<>();
		
			sessSolId = (null != session.getAttribute(SOLUTION_ID)) ? (Integer) session.getAttribute(SOLUTION_ID) : null;
			sessOppId = (null != session.getAttribute(OPPORTUNITY_ID)) ? (Integer) session.getAttribute(OPPORTUNITY_ID) : null;
			sScope = (null == sessOppId) ? solutionFTEService.getAllServiceScopeInMapBySolId(sessSolId) : solutionFTEService.getAllServiceScopeInMapByOppID(sessOppId);
			boolean isRecordsAvailable = solutionFTEService.isFTEStagingRecordAvailable(sessSolId,0);
			
			logger.debug("ReviewUpdatedFTEController::initForm::Solution ID : " + sessSolId + " Opportunity ID : "+ sessOppId );
			if (isRecordsAvailable) {
				logger.info("ReviewUpdatedFTEController::initForm::Records are available for Solution ID : " + sessSolId);
				//put the uncommented Code 
				for (Map.Entry<Integer, String> entry : sScope.entrySet()) {
							logger.info("ReviewUpdatedFTEController::initForm::Calculation for service scope : " + entry.getValue() + ":" + entry.getKey() +" and Solution ID : " + sessSolId);
							Integer serviceScopeID = entry.getKey();
							
							
							//checking if data is available in FTESummary
							boolean isFTESummaryAvailavble = solutionFTEService.isFTESummaryRecordsAvailable(sessSolId, serviceScopeID);
							boolean isFTEStgRcdsAvailable = solutionFTEService.isFTEStagingRecordAvailable(sessSolId,serviceScopeID);
							
							if (isFTESummaryAvailavble & isFTEStgRcdsAvailable) {
							
							//calculation Onshore Local FTE values
							List<MonthFTEDTO>  onShoreLocalFTEList =  solutionFTEService.fetchFTESummary(sessSolId, serviceScopeID, LOCATION_ONSHORE, SUBLOC_LOCAL);
							Map<String, Float> onShoreLocalMonthFTEMap = new LinkedHashMap<>();
							for (MonthFTEDTO monthFTEDTO : onShoreLocalFTEList) {
								onShoreLocalMonthFTEMap.put(convertDateToString(monthFTEDTO.getDate()), monthFTEDTO.getFteCount());
							}
						
							
							//Calculating Onshore GSCi FTE values
							List<MonthFTEDTO> onShoreGSCiFTEList =  solutionFTEService.fetchFTESummary(sessSolId, serviceScopeID, LOCATION_ONSHORE, SUBLOC_GSCI);
							Map<String, Float> onShoreGSCiMonthFTEMap = new LinkedHashMap<>();
							for (MonthFTEDTO monthFTEDTO : onShoreGSCiFTEList) {
								onShoreGSCiMonthFTEMap.put(convertDateToString(monthFTEDTO.getDate()), monthFTEDTO.getFteCount());
							}
							
							
							//Calculating Onshore 3PP FTE values
							List<MonthFTEDTO> onShore3PPFTEList =  solutionFTEService.fetchFTESummary(sessSolId, serviceScopeID, LOCATION_ONSHORE, SUBLOC_3PP);
							Map<String, Float> onShore3PPMonthFTEMap = new LinkedHashMap<>();
							for (MonthFTEDTO monthFTEDTO : onShore3PPFTEList) {
								onShore3PPMonthFTEMap.put(convertDateToString(monthFTEDTO.getDate()), monthFTEDTO.getFteCount());
							}
							
							//Calculating Nearshore FTE values
							List<MonthFTEDTO> nearShoreFTEList =  solutionFTEService.fetchFTESummary(sessSolId, serviceScopeID, LOCATION_NEARSHORE,null);
							Map<String, Float> nearShoreMonthFTEMap = new LinkedHashMap<>();
							for (MonthFTEDTO monthFTEDTO : nearShoreFTEList) {
								nearShoreMonthFTEMap.put(convertDateToString(monthFTEDTO.getDate()), monthFTEDTO.getFteCount());
							}
							
							//Calculating Offshore FTE values
							List<MonthFTEDTO> offShoreFTEList =  solutionFTEService.fetchFTESummary(sessSolId, serviceScopeID, LOCATION_OFFSHORE,null);
							Map<String, Float> offShoreMonthFTEMap = new LinkedHashMap<>();
							for (MonthFTEDTO monthFTEDTO : offShoreFTEList) {
								offShoreMonthFTEMap.put(convertDateToString(monthFTEDTO.getDate()), monthFTEDTO.getFteCount());
							}
							
							OnShoreLocalFTEMap.put(entry.getValue(), onShoreLocalMonthFTEMap);
							OnShoreLocalGSCFTEMap.put(entry.getValue(), onShoreGSCiMonthFTEMap);
							OnShoreLocal3PPFTEMap.put(entry.getValue(), onShore3PPMonthFTEMap);
							NearshoreFTEMap.put(entry.getValue(), nearShoreMonthFTEMap);
							OffshoreFTEMap.put(entry.getValue(), offShoreMonthFTEMap);
							} else {
								continue;
							}
						}
						int counter = 0;
						for (Map.Entry<String, Map<String, Float>> entry2 : OnShoreLocalFTEMap.entrySet()) {
							for (Map.Entry<String, Float> entry3 : entry2.getValue().entrySet()) {
								if (counter == 0) {
									monthList.add(entry3.getKey());
								}
							}
							counter ++;
						}
						hasEditSolAccess = (String) getSessionValueFromKey(session,HAS_EDIT_SOL_ACCESS);
						
						model.addAttribute("OnShoreLocalFTEMap", OnShoreLocalFTEMap);
						model.addAttribute("OnShoreLocalGSCFTEMap", OnShoreLocalGSCFTEMap);
						model.addAttribute("OnShoreLocal3PPFTEMap", OnShoreLocal3PPFTEMap);
						model.addAttribute("NearshoreFTEMap", NearshoreFTEMap);
						model.addAttribute("OffshoreFTEMap", OffshoreFTEMap);
						model.addAttribute("monthList", monthList);	
						model.addAttribute("hasEditSolAccess", hasEditSolAccess);
						model.addAttribute("isrecordsavailable",true);
						returnString = "reviewUpdatedFTE";
						
			} else {
						logger.info("ReviewUpdatedFTEController::initForm::Records are not available for Solution ID : " + sessSolId);
						//insert values in FTE Staging
						List<FTEStaging>fteStagingList =  getFTEStagingList(sessSolId, sScope);
						if (fteStagingList.size() == 0) {
							logger.debug("ReviewUpdatedFTEController::initForm::Records are not available IN FTEStaging for Solution ID : " + sessSolId + " and OpportunityScope : "+sScope);
							returnString = "reviewUpdatedFTE";
							model.addAttribute("errormessage", ApplicationPropertiesUtil.getProperty("fte.nosufficientdata"));
							model.addAttribute("isrecordsavailable",false);
						} else {
							//saving the FTEStaging List in Database
							logger.debug("ReviewUpdatedFTEController::initForm::Records are getting inserted in FTEStaging for Solution ID : " + sessSolId + " and OpportunityScope : "+sScope);
							solutionFTEService.saveAdditionalFTE(fteStagingList);
							model.addAttribute("isrecordsavailable",true);
							returnString = "redirect:/solution/reviewUpdatedFTE";
						}
			}
		//getting Service Bucket data
		List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(sessOppId, sessSolId);
		model.addAttribute("serviceBucketData", serviceBucketData);
		model.addAttribute("opportunityId", sessOppId);
		model.addAttribute("solutionId", sessSolId);
		model.addAttribute("solutionScope",sScope);
		logger.info("ReviewUpdatedFTEController::initForm::exit");
		} catch (Exception e) {
			e.printStackTrace();
		    logger.error("Error while initForm user:"
				    + session.getAttribute(USER_NAME) + " Solution ID:"
				    + sessSolId + " Error:" + e);
		    model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
			}
		return returnString;
	}
	
	
	/**
	 * 
	 * @param model
	 * @param reviewFTEForm
	 * @param sServiceScope
	 * @param session
	 * @return
	 * @throws MSSPException
	 */
	@RequestMapping("/solution/regenerateFTE")
    public String regenerate(
	    ModelMap model,
	    @ModelAttribute("reviewFTEForm") ReviewFTEForm reviewFTEForm,
	    @RequestParam(value = "sServiceScope", required = false, defaultValue = "-1") Integer sServiceScope,
	    HttpSession session) throws MSSPException {
		Integer sessSolId = null;
		Integer sessOppId = null;
		Map<Integer, String> sScope = null;
		List<FTEStaging>fteStagingList =  null;
		int deletedFTEStagingRecsCount = 0;
		try {
			logger.debug("ReviewUpdatedFTEController::regenerate::entry");
			sessSolId = (null != session.getAttribute(SOLUTION_ID)) ? (Integer) session.getAttribute(SOLUTION_ID) : null;
			sessOppId = (null != session.getAttribute(OPPORTUNITY_ID)) ? (Integer) session.getAttribute(OPPORTUNITY_ID) : null;
			sScope = (null == sessOppId) ? solutionFTEService.getAllServiceScopeInMapBySolId(sessSolId) : solutionFTEService.getAllServiceScopeInMapByOppID(sessOppId);
			logger.debug("ReviewUpdatedFTEController::regenerate::Solution ID : " + sessSolId +" Opportunity ID : "+ sessOppId);
			fteStagingList =  getFTEStagingList(sessSolId, sScope);
			logger.debug("ReviewUpdatedFTEController::regenerate::deleting FTEStaging records for Solution ID : " + sessSolId);
			deletedFTEStagingRecsCount = solutionFTEService.removeAllFTEStagingBySolutionID(sessSolId);
			logger.debug("ReviewUpdatedFTEController::regenerate::deleted "+deletedFTEStagingRecsCount+"  FTEStaging records for Solution ID : " + sessSolId);
			if (deletedFTEStagingRecsCount!=0) {
				logger.debug("ReviewUpdatedFTEController::regenerate::Saving FTEStaginglist after regeneration");
				solutionFTEService.saveAdditionalFTE(fteStagingList);
			}
			logger.debug("ReviewUpdatedFTEController::regenerate::exit");
		} catch (Exception e) {
			e.printStackTrace();
		    logger.error("Error while initForm user:"
				    + session.getAttribute(USER_NAME) + " Solution ID:"
				    + sessSolId + " Error:" + e);
		    model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
			} 
		return "redirect:/solution/reviewUpdatedFTE";
	}
	
	
	
	/**
	 * 
	 * @param model
	 * @param reviewFTEForm
	 * @param sServiceScope
	 * @param isInvalidDate
	 * @param session
	 * @return
	 * @throws MSSPException
	 */
	@RequestMapping("/solution/reviewsscopeFTE")
    public String viewsscopeFTE(
	    ModelMap model,
	    @ModelAttribute("reviewFTEForm") ReviewFTEForm reviewFTEForm,
	    @RequestParam(value = "sServiceScope", required = false, defaultValue = "-1") Integer sServiceScope,
	    @RequestParam(value = "isInvalidDate", required = false, defaultValue = "false") String isInvalidDate,
	    HttpSession session) throws MSSPException {
		Integer sessSolId = null;
		Integer sessOppId = null;
		Map<Integer, String> sScope = null;
		Map<String, Map<String, Float>> OnShoreLocalFTEMap = null;
		Map<String, Map<String, Float>> OnShoreLocalGSCFTEMap = null;
		Map<String, Map<String, Float>> OnShoreLocal3PPFTEMap = null;
		Map<String, Map<String, Float>> OffshoreFTEMap = null;
		Map<String, Map<String, Float>> NearshoreFTEMap = null;
		Map<Integer, String>  jobRoleMap = null;
		List<String> monthList = null;
		Integer serviceElementId ;
		Map<Integer, String> jobRoleDropdownMap = null;
		String hasEditSolAccess = null;
		try {
			logger.debug("ReviewUpdatedFTEController::viewsscopeFTE::entry");
			OnShoreLocalFTEMap = new HashMap<>();
			OnShoreLocalGSCFTEMap = new HashMap<>();
			OnShoreLocal3PPFTEMap = new HashMap<>();
			OffshoreFTEMap = new HashMap<>();
			NearshoreFTEMap = new HashMap<>();
			monthList = new ArrayList<>();
			jobRoleMap = new HashMap<>();
			jobRoleDropdownMap = new HashMap<>();
			sessSolId = (null != session.getAttribute(SOLUTION_ID)) ? (Integer) session.getAttribute(SOLUTION_ID) : null;
			sessOppId = (null != session.getAttribute(OPPORTUNITY_ID)) ? (Integer) session.getAttribute(OPPORTUNITY_ID) : null;
			sScope = (null == sessOppId) ? solutionFTEService.getAllServiceScopeInMapBySolId(sessSolId) : solutionFTEService.getAllServiceScopeInMapByOppID(sessOppId);
			logger.debug("ReviewUpdatedFTEController::viewsscopeFTE::Solution ID : " + sessSolId + " Opportunity ID : "+ sessOppId);
			List<JobRoleStages> list =  solutionFTEService.getSpecificJobRolesForSolution(sessSolId, sServiceScope);
			//----------------------------------  getting dropdown Job Role Map ---------------------------------------------------------
			serviceElementId = (solutionFTEService.getServiceElementIdByOppScopeId(sServiceScope)).getServiceElementID();
			logger.debug("ReviewUpdatedFTEController::viewsscopeFTE::ServiceElementID : " + serviceElementId);
			if (serviceElementId != null) {
				jobRoleDropdownMap = solutionFTEService.getJobRoleDropdownByServiceElementId(serviceElementId);
			}
			if (list!=null) {
				for (JobRoleStages jobrole : list) {
					jobRoleMap.put(jobrole.getJobRoleStagesId(), jobrole.getJobRole().getRole()+" "+jobrole.getJobStage().getStage());
				}
			}

				if (jobRoleMap!=null) {
					for (Map.Entry<Integer, String> entry : jobRoleMap.entrySet()) {
						
						//---------------------------------------- OnShore Local -------------------------------------------------------------------
						List<FTEStaging> onshoreLocalFTEList =  solutionFTEService.getFtestagingListByPassedParam(sessSolId, sServiceScope, entry.getKey(), LOCATION_ONSHORE, SUBLOC_LOCAL);
						Map<String, Float> onShoreLocalMonthFTEMap = new LinkedHashMap<>();
						for (FTEStaging fteStaging : onshoreLocalFTEList) {
							onShoreLocalMonthFTEMap.put(convertDateToString(fteStaging.getMonthYear()), fteStaging.getFtecount());
						}
						
						//---------------------------------------- OnShore GSCi -------------------------------------------------------------------
						List<FTEStaging> onshoreGSCFTEList =  solutionFTEService.getFtestagingListByPassedParam(sessSolId, sServiceScope, entry.getKey(), LOCATION_ONSHORE, SUBLOC_GSCI);
						Map<String, Float> onShoreGSCiMonthFTEMap = new LinkedHashMap<>();
						for (FTEStaging fteStaging : onshoreGSCFTEList) {
							onShoreGSCiMonthFTEMap.put(convertDateToString(fteStaging.getMonthYear()), fteStaging.getFtecount());
						}
						
						//---------------------------------------- OnShore 3PP -------------------------------------------------------------------
						List<FTEStaging> onshore3PPFTEList =  solutionFTEService.getFtestagingListByPassedParam(sessSolId, sServiceScope, entry.getKey(), LOCATION_ONSHORE, SUBLOC_3PP);
						Map<String, Float> onShore3PPMonthFTEMap = new LinkedHashMap<>();
						for (FTEStaging fteStaging : onshore3PPFTEList) {
							onShore3PPMonthFTEMap.put(convertDateToString(fteStaging.getMonthYear()), fteStaging.getFtecount());
						}
						
						//---------------------------------------- Offshore -------------------------------------------------------------------
						List<FTEStaging> offshoreFTEList =  solutionFTEService.getFtestagingListByPassedParam(sessSolId, sServiceScope, entry.getKey(), LOCATION_OFFSHORE, null);
						Map<String, Float> offshoreMonthFTEMap = new LinkedHashMap<>();
						for (FTEStaging fteStaging : offshoreFTEList) {
							offshoreMonthFTEMap.put(convertDateToString(fteStaging.getMonthYear()), fteStaging.getFtecount());
						}
						
						//---------------------------------------- Nearshore -------------------------------------------------------------------
						List<FTEStaging> nearshoreFTEList =  solutionFTEService.getFtestagingListByPassedParam(sessSolId, sServiceScope, entry.getKey(), LOCATION_NEARSHORE, null);
						Map<String, Float> nearShoreMonthFTEMap = new LinkedHashMap<>();
						for (FTEStaging fteStaging : nearshoreFTEList) {
							nearShoreMonthFTEMap.put(convertDateToString(fteStaging.getMonthYear()), fteStaging.getFtecount());
						}
						
						OnShoreLocalFTEMap.put(entry.getValue(), onShoreLocalMonthFTEMap);
						OnShoreLocalGSCFTEMap.put(entry.getValue(), onShoreGSCiMonthFTEMap);
						OnShoreLocal3PPFTEMap.put(entry.getValue(), onShore3PPMonthFTEMap);
						OffshoreFTEMap.put(entry.getValue(), offshoreMonthFTEMap);
						NearshoreFTEMap.put(entry.getValue(), nearShoreMonthFTEMap);
						
					}
				}
			int counter = 0;
			for (Map.Entry<String, Map<String, Float>> entry2 : OnShoreLocal3PPFTEMap.entrySet()) {
				for (Map.Entry<String, Float> entry3 : entry2.getValue().entrySet()) {
					if (counter == 0) {
						monthList.add(entry3.getKey());
					}
				}
				counter ++;
			}
			hasEditSolAccess = (String) getSessionValueFromKey(session,HAS_EDIT_SOL_ACCESS);
			
			//getting service bucket data -------------------------------------------------
			List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(sessOppId, sessSolId);
			model.addAttribute("serviceBucketData", serviceBucketData);
			
			model.addAttribute("opportunityId", sessOppId);
			model.addAttribute("solutionId", sessSolId);
			model.addAttribute("solutionScope",sScope);
			model.addAttribute("OnShoreLocalFTEMap", OnShoreLocalFTEMap);
			model.addAttribute("OnShoreLocalGSCFTEMap", OnShoreLocalGSCFTEMap);
			model.addAttribute("OnShoreLocal3PPFTEMap", OnShoreLocal3PPFTEMap);
			model.addAttribute("NearshoreFTEMap", NearshoreFTEMap);
			model.addAttribute("OffshoreFTEMap", OffshoreFTEMap);
			model.addAttribute("monthList", monthList);
			model.addAttribute("jobRoleDropdownMap", jobRoleDropdownMap);
			model.addAttribute("hasEditSolAccess", hasEditSolAccess);
			
			if (OnShoreLocal3PPFTEMap.size() > 0) {
				model.addAttribute("isrecordsavailable",true);
				
			} else {
				model.addAttribute("errormessage", ApplicationPropertiesUtil.getProperty("fte.nosufficientdata"));
				model.addAttribute("isrecordsavailable",false);
			}
			
			if (new Boolean(isInvalidDate)) {
				logger.info("ReviewUpdatedFTEController::viewsscopeFTE::Date is out of range");
				model.addAttribute("errormessage", ApplicationPropertiesUtil.getProperty("fte.dt.outofrange.err.msg"));
			}
			session.setAttribute("sServiceScope", sServiceScope);
			logger.debug("ReviewUpdatedFTEController::viewsscopeFTE::exit");
		} catch (Exception e) {
			e.printStackTrace();
		    logger.error("Error while initForm user:"
				    + session.getAttribute(USER_NAME) + " Solution ID:"
				    + sessSolId + " Error:" + e);
			}
		
		return "reviewsscopeFTE";
	}
	
	/**
	 * 
	 * @param model
	 * @param reviewFTEForm
	 * @param sServiceScope
	 * @param session
	 * @return
	 * @throws MSSPException
	 */
	@RequestMapping("/solution/manipulateFTEValues")
    public String manipulateFTEValues(
	    ModelMap model,
	    @ModelAttribute("reviewFTEForm") ReviewFTEForm reviewFTEForm,
	    @RequestParam(value = "sServiceScope", required = false, defaultValue = "-1") Integer sServiceScope,
	    HttpSession session) throws MSSPException {
		Integer sessSolId = null;
		Integer sessOppId = null;
		String solutionApproach = null;
		Map<Integer, String> sScope = null;
		Date startDate = null;
		Date endDate = null;
		List<FTEStaging> fteStagingList = null;
		Integer jobRole = null;
		AdditionalFTE additionalFTE = null;
		String site =  null;
		Integer addFTEId = null;
		String location = null;
		String subLocation = null;
		OpportunityDetail opportunityDetail = null;
		Date projectStartDate = null;
		Date projectEndDate = null;
		Solution solution = null;
		OpportunityScope opportunityScope = null;
	    JobRoleStages jobRoleStages = null;
	    SolutionLever solnLever = null;
	    float incrementValue = 0.0f;
	    boolean dateFlag = false;
		try {
			logger.debug("ReviewUpdatedFTEController::manipulateFTEValues::entry");
			sScope = new HashMap<>();
			sessSolId = (null != session.getAttribute(SOLUTION_ID)) ? (Integer) session.getAttribute(SOLUTION_ID) : null;
			sessOppId = (null != session.getAttribute(OPPORTUNITY_ID)) ? (Integer) session.getAttribute(OPPORTUNITY_ID) : null;
			sScope = (null == sessOppId) ? solutionFTEService.getAllServiceScopeInMapBySolId(sessSolId) : solutionFTEService.getAllServiceScopeInMapByOppID(sessOppId);
			
			logger.debug("ReviewUpdatedFTEController::manipulateFTEValues::Solution ID : " + sessSolId + " Opportunity ID :" + sessOppId);
			

			
			solnLever = solutionFTEService.getSolutionLeverForSolution(sessSolId);
			if (solnLever!=null) {
				solutionApproach = solnLever.getSolutionLeverApproach();
			}
			logger.debug("ReviewUpdatedFTEController::manipulateFTEValues::Solution Approach is " + solutionApproach);

			if(reviewFTEForm!=null) {
				solution = solutionFTEService.getSolution(sessSolId);
				if (solution != null) {
					opportunityDetail = solution.getOpportunity().getOpportunityDetail();
					projectStartDate = opportunityDetail.getSteadyStateStartDate();
					projectEndDate = opportunityDetail.getSteadyStateEndDate();
				}
				
				Calendar cal = Calendar.getInstance();
				
				if (projectStartDate!=null) {
					cal.setTime(projectStartDate);
					cal.set(Calendar.DATE,1);
					projectStartDate = cal.getTime();
				}
				
                if (projectEndDate!=null) {
                	cal.setTime(projectEndDate);
                	cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                	projectEndDate = cal.getTime(); 
                }
				
				startDate = getDateFromString(reviewFTEForm.getfDate(), "dd/MM/yyyy");
				endDate = getDateFromString(reviewFTEForm.gettDate(), "dd/MM/yyyy");
				if (startDate!=null & endDate!=null) {
					if (endDate.after(startDate)) {
						if ((startDate.equals(projectStartDate) & endDate.equals(projectEndDate)) || (startDate.equals(projectStartDate) & endDate.before(projectEndDate)) || (startDate.after(projectStartDate) & endDate.equals(projectEndDate)) || (startDate.after(projectStartDate) & endDate.before(projectEndDate))) {
							dateFlag = true;
						} else {}
					} else {}
				} else {}
				
				if (dateFlag)
				{
					logger.debug("ReviewUpdatedFTEController::manipulateFTEValues::Provided date range is correct");
					
					jobRole = Integer.parseInt(reviewFTEForm.getJobrole());
					site = reviewFTEForm.getSite();
					int difference = getMonthDifference(reviewFTEForm.getfDate(), reviewFTEForm.gettDate(), "dd/MM/yyyy") + 1;
					double value = reviewFTEForm.getaFTE();
					if ("Uniform".equalsIgnoreCase(solutionApproach)) {
						incrementValue = (float)(value/difference);
					} else {
						incrementValue = (float)value;
					}
					additionalFTE = new AdditionalFTE();
					//list =  solutionFTEService.getSpecificJobRolesForSolution(sessSolId, sServiceScope);
					//if (null != additionalFteid && additionalFteid >= 0) {
					//additionalFTE.setAdditionalFteid(additionalFteid);
				    //}
				    // additionalFTEDTO.setCreatedTimestamp(date);
				    additionalFTE.setSolution(solutionFTEService.getSolutionById(sessSolId));
				    additionalFTE.setOpportunityScopeId(sServiceScope);
				    JobRoleStages jobRoleStage = new JobRoleStages();
				    jobRoleStage.setJobRoleStagesId(jobRole);
				    additionalFTE.setJobRoleStageID(jobRoleStage);
				    if(LOCATION_ONSHORE_LOCAL.equals(site)){
				    	location = LOCATION_ONSHORE;
				    	additionalFTE.setLocation(LOCATION_ONSHORE);
				    	subLocation = SUBLOC_LOCAL;
				    	additionalFTE.setSubLocation(SUBLOC_LOCAL);
				    } else if(LOCATION_ONSHORE_GSCI.equals(site)){
				    	location = LOCATION_ONSHORE;
				    	additionalFTE.setLocation(LOCATION_ONSHORE);
				    	subLocation = SUBLOC_GSCI;
				    	additionalFTE.setSubLocation(SUBLOC_GSCI);
				    } else if(LOCATION_ONSHORE_3PP.equals(site)){
				    	location = LOCATION_ONSHORE;
				    	additionalFTE.setLocation(LOCATION_ONSHORE);
				    	subLocation = SUBLOC_3PP;
				    	additionalFTE.setSubLocation(SUBLOC_3PP);
				    } else{
				    	location = site;
				    	additionalFTE.setLocation(site);
				    }
				    additionalFTE.setNoofFte(Math.round(incrementValue* DECIMAL_POINTS_NUM)/ DECIMAL_POINTS_NUM);
				    additionalFTE.setFromMonthYear(startDate);
				    additionalFTE.setToMonthYear(endDate);
				    
					fteStagingList = solutionFTEService.getFTEStagingObjects(sessSolId, sServiceScope, jobRole, startDate, endDate, location, subLocation);
					
					if (fteStagingList != null && fteStagingList.size() > 0) {
						logger.debug("ReviewUpdatedFTEController::manipulateFTEValues::Records are available for Solution ID: " + sessSolId + " Service Scope : " + sServiceScope + " JobRole :"+jobRole + " Start Date :" + startDate +" Enddate: " + endDate + " Location: "+location + " Sublocation :"+subLocation);
						for (FTEStaging fteStaging : fteStagingList) {
							float fteCount = fteStaging.getFtecount() + incrementValue;
							fteStaging.setFtecount(fteCount);
						}
						//save the objects in FTEStaging
						solutionFTEService.saveAdditionalFTE(fteStagingList);
						//save the AdditionalDetail object
						addFTEId = solutionFTEService.saveAdditionalFTE(additionalFTE);
					} else {
						logger.debug("ReviewUpdatedFTEController::manipulateFTEValues::Records are not available for Solution ID: " + sessSolId + " Service Scope : " + sServiceScope + " JobRole :"+jobRole + " Start Date :" + startDate +" Enddate: " + endDate + " Location: "+location + " Sublocation :"+subLocation);
						List<FTEStaging> fteList = new ArrayList<>();
						opportunityScope = solutionFTEService.getOpportunityScope(sServiceScope);
						jobRoleStages = solutionFTEService.getJobRoleStagesByjobRoleStagesId(Integer.parseInt(reviewFTEForm.getJobrole()));
						List<Date> dateList = getIntermediateDates(projectStartDate, projectEndDate);
						logger.debug("ReviewUpdatedFTEController::manipulateFTEValues::Retrieving FTE  for Solution ID: " + sessSolId + " Service Scope : " + sServiceScope + " JobRole :"+jobRole + " Start Date :" + startDate +" Enddate: " + endDate + " Location: "+location + " Sublocation :"+subLocation);
						fteList = getFTEListForOtherLocations(location,subLocation,solution,opportunityScope,jobRoleStages,dateList,startDate,endDate,incrementValue);
						logger.debug("ReviewUpdatedFTEController::manipulateFTEValues::Saving FTE for Solution ID: " + sessSolId + " Service Scope : " + sServiceScope + " JobRole :"+jobRole + " Start Date :" + startDate +" Enddate: " + endDate + " Location: "+location + " Sublocation :"+subLocation);
						solutionFTEService.saveAdditionalFTE(fteList);
						addFTEId = solutionFTEService.saveAdditionalFTE(additionalFTE);
					}
				
				} else {
					logger.debug("ReviewUpdatedFTEController::manipulateFTEValues::Provided date range is not correct");
				}
			}
			model.addAttribute("opportunityId", sessOppId);
			model.addAttribute("solutionId", sessSolId);
			model.addAttribute("solutionScope",sScope);
			logger.debug("ReviewUpdatedFTEController::manipulateFTEValues::exit");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ReviewUpdatedFTEController::manipulateFTEValues::Exception",e);
			logger.error("ReviewUpdatedFTEController::manipulateFTEValues::Exception for Solution ID " + sessSolId + " Opportunity ID : " + sessOppId + "Service Scope : "+sServiceScope + " Job Role " + jobRole + " Location : "+location +" Sublocation :"+subLocation,e);
		}
		if (dateFlag){
		    return "redirect:/solution/reviewsscopeFTE?sServiceScope="+sServiceScope;
		} else {
			return "redirect:/solution/reviewsscopeFTE?isInvalidDate=true&sServiceScope="+sServiceScope;
		}
	}
	
	/**
	 * 
	 * @param location
	 * @param subLocation
	 * @param solution
	 * @param opportunityScope
	 * @param jobRoleStages
	 * @param dateList
	 * @param startDate
	 * @param endDate
	 * @param incrementValue
	 * @return
	 */
	private static List<FTEStaging> getFTEListForOtherLocations(String location,String subLocation,Solution solution,OpportunityScope opportunityScope,JobRoleStages jobRoleStages,List<Date> dateList,Date startDate,Date endDate,float incrementValue) {
		
		logger.debug("ReviewUpdatedFTEController::getFTEListForOtherLocations::entry");
		List<String> sublocationList = new ArrayList<>();
		sublocationList.add("Local");
		sublocationList.add("3PP");
		sublocationList.add("GSCi");
		
		Map<String, List<String>> map = new HashMap<>();
		map.put("Onshore", sublocationList);
		map.put("Offshore", null);
		map.put("Nearshore", null);
		
		List<FTEStaging> stagingList = new ArrayList<>();
		
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			String loc = entry.getKey();
			List<String> sublocList = entry.getValue();
			if (sublocList!=null && sublocList.size() > 0) {
				for (String subloc : sublocList) {
					if (loc.equalsIgnoreCase(location) & subloc.equalsIgnoreCase(subLocation)) {
						for (Date date : dateList) {
							FTEStaging fteStaging = new FTEStaging();
							fteStaging.setSubLocation(subloc);
							fteStaging.setLocation(loc);
							fteStaging.setSolution(solution);
							fteStaging.setOpportunityScope(opportunityScope);
							fteStaging.setJobRoleStage(jobRoleStages);
							fteStaging.setMonthYear(date);
							if (date.equals(startDate) || date.equals(endDate) || (date.after(startDate) & date.before(endDate))) {
								fteStaging.setFtecount(incrementValue);
							} else {
								fteStaging.setFtecount(0.0f);
							}
							stagingList.add(fteStaging);
						}
					} else {
						for (Date date : dateList) {
							FTEStaging fteStaging = new FTEStaging();
							fteStaging.setSubLocation(subloc);
							fteStaging.setLocation(loc);
							fteStaging.setSolution(solution);
							fteStaging.setOpportunityScope(opportunityScope);
							fteStaging.setJobRoleStage(jobRoleStages);
							fteStaging.setMonthYear(date);
							fteStaging.setFtecount(0.0f);
							stagingList.add(fteStaging);
						}
					}
					
				}
			} else {
				
				if (loc.equalsIgnoreCase(location)){
					for (Date date : dateList) {
						FTEStaging fteStaging = new FTEStaging();
						fteStaging.setSubLocation(null);
						fteStaging.setLocation(loc);
						fteStaging.setSolution(solution);
						fteStaging.setOpportunityScope(opportunityScope);
						fteStaging.setJobRoleStage(jobRoleStages);
						fteStaging.setMonthYear(date);
						if (date.equals(startDate) || date.equals(endDate) || (date.after(startDate) & date.before(endDate))) {
							fteStaging.setFtecount(incrementValue);
						} else {
							fteStaging.setFtecount(0.0f);
						}
						stagingList.add(fteStaging);
					}
				} else {
					for (Date date : dateList) {
						FTEStaging fteStaging = new FTEStaging();
						fteStaging.setSubLocation(null);
						fteStaging.setLocation(loc);
						fteStaging.setSolution(solution);
						fteStaging.setOpportunityScope(opportunityScope);
						fteStaging.setJobRoleStage(jobRoleStages);
						fteStaging.setMonthYear(date);
						fteStaging.setFtecount(0.0f);
						stagingList.add(fteStaging);
					}
				}
			}
		}
		logger.debug("ReviewUpdatedFTEController::getFTEListForOtherLocations::exit");
		return stagingList;
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	private static String convertDateToString (Date date) throws Exception{
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM-yyyy");
		String dateStr = null;
		if (date != null) {
			dateStr = dateFormatter.format(date);
		}
	   return dateStr;
	}
	
	/**
	 * 
	 * @param startStr
	 * @param endStr
	 * @param dateFormatter
	 * @return
	 */
	public static int getMonthDifference(String startStr , String endStr,String dateFormatter) {
		int difference = 0;
		Calendar sDate = null;
		Calendar eDate = null;
		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat(dateFormatter);
			sDate = Calendar.getInstance();
			eDate = Calendar.getInstance();
			sDate.setTime(sdf.parse(startStr));
			eDate.setTime(sdf.parse(endStr));
			difference =  (eDate.get(Calendar.YEAR) * 12 + eDate.get(Calendar.MONTH)) - (sDate.get(Calendar.YEAR) * 12 + sDate.get(Calendar.MONTH));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return difference;
	}
	
	/**
	 * 
	 * @param dateString
	 * @param dateFormatter
	 * @return
	 */
	public static Date getDateFromString(String dateString,String dateFormatter) {
		Date date = null;
		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat(dateFormatter);
			date = sdf.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

/**
 * 	
 * @param startDt
 * @param endDt
 * @return
 */
public static List<Date> getIntermediateDates (Date startDt , Date endDt) {
		
		Calendar sDate = null;
		Calendar eDate = null;
		List<Date> list = null;
		Date tempDate = null;
		try {
			list = new ArrayList<>();
			sDate = Calendar.getInstance();
			eDate = Calendar.getInstance();
			sDate.setTime(startDt);
			eDate.setTime(endDt);
			sDate.set(Calendar.DATE,1);
			eDate.set(Calendar.DATE,1);
			tempDate = sDate.getTime();
			while (tempDate.before(eDate.getTime())) {
				list.add(tempDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(tempDate);
				cal.add(Calendar.MONTH,1);
				tempDate = cal.getTime();
			}
			list.add(eDate.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

   /**
    * 
    * @param sessSolId
    * @param sScope
    * @return
    * @throws Exception
    */
   private List<FTEStaging> getFTEStagingList(Integer sessSolId,Map<Integer, String> sScope) throws Exception{
	
	   logger.debug("ReviewUpdatedFTEController::getFTEStagingList::entry");
	   
		Solution solution = solutionFTEService.getSolution(sessSolId);
		List<FTEStaging> fteStagingList = new ArrayList<>();
		List<String> sublocationList = new ArrayList<>();
		sublocationList.add("Local");
		sublocationList.add("3PP");
		sublocationList.add("GSCi");
		List<String> blanksublocationList = new ArrayList<>();
		blanksublocationList.add("NA");
		Map<String, List<String>> siteMap = new HashMap<>();
		siteMap.put("Onshore", sublocationList);
		siteMap.put("Offshore", blanksublocationList);
		siteMap.put("Nearshore", blanksublocationList);
		for (Map.Entry<Integer, String> entry : sScope.entrySet()) {
			System.out.println(entry.getKey() + "##################################################################" + entry.getValue());
			OpportunityScope opportunityScope = solutionFTEService.getOpportunityScope(entry.getKey());
			Map<Date, Float> onShoreLocalPCmap = new HashMap<>();
			Map<Date, Float> onShoreGSCPCmap = new HashMap<>();
			Map<Date, Float> onShore3PPPCmap = new HashMap<>();
			Map<Date, Float> offShorePCmap = new HashMap<>();
			Map<Date, Float> nearShorePCmap = new HashMap<>();
			Integer oppScopeId = entry.getKey();
			/*System.out.println("%%%%%%%%%%%%%%%%%%%%%% oppScopeId"  + oppScopeId + "------- sessSolId " + sessSolId );
			System.out.println("#######################################################################################################");
			Integer serviceElementId = (solutionFTEService.getServiceElementIdByOppScopeId(oppScopeId)).getServiceElementID();
			Map<Integer, String> jobRoleDropdownMap = solutionFTEService.getJobRoleDropdownByServiceElementId(serviceElementId);
			for (Map.Entry<Integer, String> item : jobRoleDropdownMap.entrySet()) {
				System.out.println(item.getKey()+"-----"+item.getValue());
			}
			System.out.println("#######################################################################################################");
			List<JobRoleStages> list =  solutionFTEService.getSpecificJobRolesForSolution(sessSolId, oppScopeId);
			for (JobRoleStages jobRoleStages : list) {
				System.out.println(jobRoleStages.getJobRoleStagesId()+"--------"+jobRoleStages.getJobRole().getRole()+" "+jobRoleStages.getJobStage().getStage());
			}*/
			List<FTESummary> fteSummaryList = solutionFTEService.getFTESummaryBySolnIDoppID(sessSolId, oppScopeId);
			List<LocationPyramid> locPyramidList = solutionFTEService.getLocationPyramidForOppScopeID(oppScopeId);
			if (locPyramidList == null) {
				continue;
			} else {
				if (locPyramidList.size() == 0) {
					continue;
				}
			}
			int jobRole = locPyramidList.get(0).getJobRoleStages().getJobRoleStagesId();
			Map<JobRoleStages, Map<Date, Float>> jobRoleMap =  new HashMap<>();
			Map<Date, Float> dateFteMap = new HashMap<>();
			int length = locPyramidList.size();
			int counter = 1;
			for (LocationPyramid locationPyramid : locPyramidList) {
				if (jobRole == locationPyramid.getJobRoleStages().getJobRoleStagesId()) {
					dateFteMap.put(locationPyramid.getMonthYear(), locationPyramid.getDistributionPc());
					if (counter==length) {
						jobRoleMap.put(solutionFTEService.getJobRoleStagesByjobRoleStagesId(jobRole), dateFteMap);
					}
				} else {
					jobRoleMap.put(solutionFTEService.getJobRoleStagesByjobRoleStagesId(jobRole), dateFteMap);
					dateFteMap = new HashMap<>();
					dateFteMap.put(locationPyramid.getMonthYear(), locationPyramid.getDistributionPc());
				}
				jobRole = locationPyramid.getJobRoleStages().getJobRoleStagesId();
				counter ++;
			}
			
			//--------------------------------------------------------------------------------------
			List<JobRoleStages> jobRoleStageList = new ArrayList<>();
			Map<Integer, Map<Date, Float>> onLocJobRoleMap =  getLocationWiseSolPyramid(oppScopeId, LOCATION_ONSHORE, SUBLOC_LOCAL);
			Map<Integer, Map<Date, Float>> onGSCJobRoleMap =  getLocationWiseSolPyramid(oppScopeId, LOCATION_ONSHORE, SUBLOC_GSCI);
			Map<Integer, Map<Date, Float>> on3PPJobRoleMap =  getLocationWiseSolPyramid(oppScopeId, LOCATION_ONSHORE, SUBLOC_3PP);
			Map<Integer, Map<Date, Float>> offJobRoleMap =  getLocationWiseSolPyramid(oppScopeId, LOCATION_OFFSHORE, null);
			Map<Integer, Map<Date, Float>> nearJobRoleMap =  getLocationWiseSolPyramid(oppScopeId, LOCATION_NEARSHORE, null);

			Map<Integer, Map<Date, Float>> tempMap = new HashMap<>();
			if (onLocJobRoleMap.size() > 0) {
				tempMap = onLocJobRoleMap;
			} else if (onGSCJobRoleMap.size() > 0) {
				tempMap = onGSCJobRoleMap;
			} else if (on3PPJobRoleMap.size() > 0) {
				tempMap = on3PPJobRoleMap;
			} else if (offJobRoleMap.size() > 0) {
				tempMap = offJobRoleMap;
			} else {
				tempMap = nearJobRoleMap;
			}
			
			for (Map.Entry<Integer, Map<Date, Float>> it1 : tempMap.entrySet()) {
				jobRoleStageList.add(solutionFTEService.getJobRoleStagesByjobRoleStagesId(it1.getKey()));
			}
			
				List<LocationBreakup>  locationBreakupList =  solutionFTEService.getLocationBreakupByOppScopeID(oppScopeId);
				if (locationBreakupList!=null && locationBreakupList.size() > 0) {
					for (LocationBreakup locationBreakup : locationBreakupList) {
						onShoreLocalPCmap.put(locationBreakup.getMonthYear(), locationBreakup.getOnshoreLocalPc());
						onShoreGSCPCmap.put(locationBreakup.getMonthYear(), locationBreakup.getOnshoreGSCiPc());
						onShore3PPPCmap.put(locationBreakup.getMonthYear(), locationBreakup.getOnshore3PPPc());
						offShorePCmap.put(locationBreakup.getMonthYear(), locationBreakup.getOffshorePc());
						nearShorePCmap.put(locationBreakup.getMonthYear(), locationBreakup.getNearshorePc());
					}
				} 
			
				for (Map.Entry<String, List<String>> locItem : siteMap.entrySet()) {
					String loc = locItem.getKey();
					List<String> sublocList = locItem.getValue();
					if (sublocList.size() > 0) {
						for (String subLocation : sublocList) {
								   for (JobRoleStages jobRoleStages : jobRoleStageList) {   
								   for (FTESummary fteSummary : fteSummaryList) {
									   JobRoleStages jobStageRole = jobRoleStages;
									   Date dt = fteSummary.getToDate();
									   float fteCount = fteSummary.getFtecount();
									   Calendar cal = Calendar.getInstance();
									   cal.setTime(dt);
									   cal.set(Calendar.DATE,1);
									   cal.set(Calendar.MONTH,0);
									   Float locpercentage = 0.0f;
									   Float jobRolepercentage = 0.0f;
									   if ("Onshore".equalsIgnoreCase(loc) && "Local".equalsIgnoreCase(subLocation)) {
										   locpercentage = onShoreLocalPCmap.get(cal.getTime());
										   if (onLocJobRoleMap.size() > 0) {
											   jobRolepercentage = onLocJobRoleMap.get(jobRoleStages.getJobRoleStagesId()).get(cal.getTime());
										   }
									   }
									   if ("Onshore".equalsIgnoreCase(loc) && "GSCi".equalsIgnoreCase(subLocation)) {
										   locpercentage = onShoreGSCPCmap.get(cal.getTime());
										   if (onGSCJobRoleMap.size() > 0) {
											   jobRolepercentage = onGSCJobRoleMap.get(jobRoleStages.getJobRoleStagesId()).get(cal.getTime());
										   }
									   }
									   if ("Onshore".equalsIgnoreCase(loc) && "3PP".equalsIgnoreCase(subLocation)) {
										   locpercentage = onShore3PPPCmap.get(cal.getTime());
										   if (on3PPJobRoleMap.size() > 0) {
											   jobRolepercentage = on3PPJobRoleMap.get(jobRoleStages.getJobRoleStagesId()).get(cal.getTime());
										   }
									   }
									   if ("Nearshore".equalsIgnoreCase(loc) && "NA".equalsIgnoreCase(subLocation)) {
										   locpercentage = nearShorePCmap.get(cal.getTime());
										   if (nearJobRoleMap.size() > 0) {
											   jobRolepercentage = nearJobRoleMap.get(jobRoleStages.getJobRoleStagesId()).get(cal.getTime());
										   }
									   }
									   if ("Offshore".equalsIgnoreCase(loc) && "NA".equalsIgnoreCase(subLocation)) {
										   locpercentage = offShorePCmap.get(cal.getTime());
										   if (offJobRoleMap.size() > 0) {
											   jobRolepercentage = offJobRoleMap.get(jobRoleStages.getJobRoleStagesId()).get(cal.getTime());
										   }
									   }
									   
									   float result = 0.0f;
									   if (fteCount!=0.0f) {
										   if (locpercentage!=null) {
											   if (locpercentage!=0.0f){
												   if (jobRolepercentage != null){
													   if (jobRolepercentage!=0.0f){
														   result = (((fteCount * locpercentage)/100)*jobRolepercentage)/100;
													   }
												   }
											   }
										   }
									   }
									  FTEStaging fteStaging = new FTEStaging();
									  fteStaging.setSolution(solution);
									  fteStaging.setOpportunityScope(opportunityScope);
									  fteStaging.setJobRoleStage(jobStageRole);
									  fteStaging.setLocation(loc);
									  if ("NA".equalsIgnoreCase(subLocation)) {
										  fteStaging.setSubLocation(null);
									  } else {
										  fteStaging.setSubLocation(subLocation);
									  }
									  fteStaging.setMonthYear(dt);
									  fteStaging.setFtecount(result);
									  fteStagingList.add(fteStaging);
								   }
							   }
						}
					}
				}
		}
		logger.debug("ReviewUpdatedFTEController::getFTEStagingList::exit");
	   return fteStagingList;
   }
   
   /**
    * 
    * @param oppScopeId
    * @param location
    * @param subLocation
    * @return
    * @throws Exception
    */
   public Map<Integer, Map<Date, Float>> getLocationWiseSolPyramid(Integer oppScopeId, String location, String subLocation) throws Exception{
	   Map<Integer, Map<Date, Float>> onLocJobRoleMap =  new HashMap<>();
	   List<LocationPyramid> onLocPrdList =  solutionFTEService.getLocPyramidSiteWise(oppScopeId, location, subLocation);
	   if (onLocPrdList!=null && onLocPrdList.size() > 0) {
		   Map<Date, Float> onLocdateFteMap = new HashMap<>();
		   Integer initJobRole = onLocPrdList.get(0).getJobRoleStages().getJobRoleStagesId();
			int listlength = onLocPrdList.size();
			int listcounter = 1;
			for (LocationPyramid locationPyramid : onLocPrdList) {
				if (initJobRole == locationPyramid.getJobRoleStages().getJobRoleStagesId()) {
					onLocdateFteMap.put(locationPyramid.getMonthYear(), locationPyramid.getDistributionPc());
					if (listcounter==listlength) {
						onLocJobRoleMap.put(initJobRole, onLocdateFteMap);
					}
				} else {
					onLocJobRoleMap.put(initJobRole, onLocdateFteMap);
					onLocdateFteMap = new HashMap<>();
					onLocdateFteMap.put(locationPyramid.getMonthYear(), locationPyramid.getDistributionPc());
				}
				initJobRole = locationPyramid.getJobRoleStages().getJobRoleStagesId();
				listcounter ++;
			}
		   
	   }
	   return onLocJobRoleMap;
   }
}