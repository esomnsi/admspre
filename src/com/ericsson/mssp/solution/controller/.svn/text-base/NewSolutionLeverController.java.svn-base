/**
 * 
 */
package com.ericsson.mssp.solution.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.AutoPopulatingList;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ericsson.mssp.common.dto.FTESummaryDTO;
import com.ericsson.mssp.common.dto.LocationBreakupDTO;
import com.ericsson.mssp.common.dto.LocationPyramidDTO;
import com.ericsson.mssp.common.dto.ProductEstimationBaseEffortForSolutionDTO;
import com.ericsson.mssp.common.dto.ProductivityLeverDTO;
import com.ericsson.mssp.common.dto.ServiceElementJobRoleStagesDTO;
import com.ericsson.mssp.common.dto.SolutionDTO;
import com.ericsson.mssp.common.dto.SolutionLeverDTO;
import com.ericsson.mssp.common.dto.SolutionUtilPerYearDTO;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.service.ISolutionService;
import com.ericsson.mssp.solution.service.SolutionLeverService;


/**
 * @author egaivij
 *
 */
@Controller
public class NewSolutionLeverController extends BaseController{
	
	@Autowired
	private SolutionLeverService solutionLeverService;
	
	@Autowired
	private ISolutionService solutionService;
	
	private final String JOB_ROLE_MODEL_CCM="CCM";

	public  static Logger logger = Logger.getLogger(NewSolutionLeverController.class);
	
	@ModelAttribute("solutionLeverDTO")
	public SolutionLeverDTO getSolutionLeverDTO() {
		SolutionLeverDTO solutionLeverDTO = new SolutionLeverDTO();
		solutionLeverDTO.setProdLeverList(new AutoPopulatingList<ProductivityLeverDTO>(ProductivityLeverDTO.class));
		//For Location Pyramid
		solutionLeverDTO.setLocationPyramidDTOList(new AutoPopulatingList<LocationPyramidDTO>(LocationPyramidDTO.class));
		
		return solutionLeverDTO;
	}
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
	            //The date format to parse or output your dates
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-yyyy");
	            //Create a new CustomDateEditor
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	            //Register it as custom editor for the Date type
	    binder.registerCustomEditor(Date.class, editor);
	}
	
	
	@RequestMapping("/solution/solutionLever")
	public String loadSolutionLever(ModelMap model,@ModelAttribute ("solutionUtilPerYearDTO") SolutionUtilPerYearDTO solutionUtilPerYearDTO, HttpSession session,HttpServletRequest request) {
		logger.info("inside loadSolutionLever");
		
		String message = (String)request.getAttribute("message");
		model.addAttribute("selectedTab", "TAB1");
		
		//Loading  solution util
		Integer opportunityId = getSessionOpportunityId(session);
		Integer solutionId = getSessionSolutionId(session);
		SolutionUtilPerYearDTO dto = solutionLeverService.getSolutionUtilBySolutionId(solutionId);
		if(null!=dto){
		BeanUtils.copyProperties(dto,solutionUtilPerYearDTO);
		}
		
		List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(opportunityId, solutionId);
		model.addAttribute("serviceBucketData", serviceBucketData);
		
		model.addAttribute("message", message);
		return "solutionLever";
	}
	
	/*
	 * Saving solution util
	 */
	@RequestMapping(value="/solution/saveSolutionUtil" ,method = RequestMethod.POST)
	public String saveSolutionUtil(ModelMap model, @ModelAttribute ("solutionUtilPerYearDTO") SolutionUtilPerYearDTO solutionUtilPerYearDTO,HttpSession session,HttpServletRequest request) {
		logger.info("Saving solution Utilization  !!");
		String message = "success";
		//fetching solutionId from session
		Integer solutionId = getSessionSolutionId(session);
		try{
		/*	String totalBaseHours = null;
			totalBaseHours.toString();*/
		solutionLeverService.saveSolutionUtilPerYear(solutionUtilPerYearDTO,solutionId);
		}
		catch(Exception e){
			logger.error("Exception in saveSolutionUtil method while saving data !!",e);
			logger.error(ExceptionUtils.getStackTrace(e));
			
			//e.printtackTrace();
			message="exception";
		}
		
		request.setAttribute("message",message);
		return "forward:solutionLever";
	}
	
	
	@RequestMapping("/solution/deliveryPyramid")
	public String deliveryPyramid(ModelMap model,@ModelAttribute ("solutionLeverDTO")SolutionLeverDTO solutionLeverDTO, HttpSession session,HttpServletRequest request)  {
		logger.info("inside deliveryPyramid ");
		Integer opportunityId = getSessionOpportunityId(session);
		Integer solutionId = getSessionSolutionId(session);
		Integer firstScopeId = null;
		ProductEstimationBaseEffortForSolutionDTO productEstimationBaseEffortForSolutionDTO= null;
		Double totalBaseHours = null;
		
		try {
			Map<Integer, String> oppScopesMap = solutionService.getAllServiceScopeInMapByOppID(opportunityId);
			
			
			model.addAttribute("oppScopesMap", oppScopesMap);
			//if(oppScopesMap.keySet().size()>0){
			firstScopeId = (Integer) oppScopesMap.keySet().toArray()[0];
		//	}
			
		} catch (MSSPException e) {
			logger.error("There is a error while loading  opprotunity scope map data from database",e);	
			e.printStackTrace();
		}
		
		List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(opportunityId, solutionId);
		model.addAttribute("serviceBucketData", serviceBucketData);
		
		model.addAttribute("selectedTab", "TAB2");
		model.addAttribute("locationBreakupDTO",new LocationBreakupDTO());
		// for displaying 
		List<Integer> yearListDP = null;
		//wrap a this list in a form and instantiate the form here  PojoForm pojoForm = new PojoForm();
		//SolutionLeverDTO solutionLeverDTO = new SolutionLeverDTO();
		List<LocationBreakupDTO> locationBreakupDTOList = null;
		
		//fetch year list
		OpportunityDetail oppDetail;
		Integer totalMonths = null;
		try {
			oppDetail = solutionLeverService.getOpportunityDetail(opportunityId);
			yearListDP = solutionLeverService.getYearList(oppDetail);
			
			/*
			 * for  months 
			 */
			
			 totalMonths=getTotalMonths(oppDetail.getSteadyStateStartDate(),oppDetail.getSteadyStateEndDate());

			
		} catch (MSSPException e) {
			logger.error("There is a error while fetching  Opportunity Detail from database",e);	
			e.printStackTrace();
		}
		
		if(solutionLeverDTO.getOppScopeId() == null){
			//Default fetch for first time form loading
		locationBreakupDTOList= solutionLeverService.loadLocationBreakupByOppScopeID( firstScopeId);
		
		/*		Loading hours
		*/		
		productEstimationBaseEffortForSolutionDTO=	solutionLeverService.getBaseEffort(solutionId, firstScopeId);
		}
		else {
			//For other selected options
		locationBreakupDTOList= solutionLeverService.loadLocationBreakupByOppScopeID(solutionLeverDTO.getOppScopeId());
		productEstimationBaseEffortForSolutionDTO =solutionLeverService.getBaseEffort(solutionId, solutionLeverDTO.getOppScopeId());
		}
			
		if(null ==locationBreakupDTOList || locationBreakupDTOList.size() <= 0){
			//Filling empty DTO 
			for(int i = 0 ; i< yearListDP.size();i++){
				LocationBreakupDTO tempDTO = new LocationBreakupDTO();
				
				SimpleDateFormat sf = new SimpleDateFormat("MMM-yyyy");
				String date = "JAN-"+yearListDP.get(i);
				
				try {
					tempDTO.setMonthYear(sf.parse(date));
				} catch (ParseException e) {
					logger.error("There is a error while parsing date ",e);	
					e.printStackTrace();
				}
				tempDTO.setOnshoreLocalPc(20.0f);
				tempDTO.setOnshoreGSCiPc(10.0f);
				//tempDTO.setOnshore3PPPc(20.0f);
				tempDTO.setOffshorePc(60.0f);
				tempDTO.setNearshorePc(10.0f);
				locationBreakupDTOList.add(tempDTO);
				
			}		
		}
		
		solutionLeverDTO.setSolutionId(solutionId);
		solutionLeverDTO.setLocationBreakupDTOList(locationBreakupDTOList);

		
/*		Loading  solution util per year DTO and adding to model for calculation for FTE
*/		
		SolutionUtilPerYearDTO solutionUtilPerYearDTO = solutionLeverService.getSolutionUtilBySolutionId(solutionId);
		model.addAttribute("solutionUtilPerYearDTO",solutionUtilPerYearDTO);
		
		/*
		 * Loading total base hours 
		 */
		if(null !=productEstimationBaseEffortForSolutionDTO){
		totalBaseHours =productEstimationBaseEffortForSolutionDTO.getTotalBaseHours();
		}
		
		/*
		 * Per month Hours 
		 */
		Double perMonthHours= totalBaseHours/totalMonths;
		 model.addAttribute("perMonthHours",perMonthHours);
		 model.addAttribute("totalBaseHours", totalBaseHours);
		 model.addAttribute("yearListDP", yearListDP);
		 model.addAttribute("solutionLeverDTO",solutionLeverDTO);
		 model.addAttribute("deliveryPyramidMessage", request.getAttribute("deliveryPyramidMessage"));
		return "solutionLever";
	}
	/*
	 * For Popup Display
	 */
	
	@RequestMapping(value="/solution/displayPopup")
	public String popUpDisplay(Model model,@ModelAttribute ("solutionLeverDTO")SolutionLeverDTO solutionLeverDTO,
			@RequestParam("param")Integer oppScopeId,@RequestParam("param2")String locationSubLoc,@RequestParam("param3")String saveMessage,HttpSession session){
		logger.info("Displaying Pop-up !! ");
		Integer serviceElementId;
		Integer firstScopeId = null;
		Integer opportunityId = getSessionOpportunityId(session);
		Integer solutionId =getSessionSolutionId(session);
		Map<Integer, String> oppScopesMap = null ;
		List<ServiceElementJobRoleStagesDTO> seDTOList = null;
		List<ServiceElementJobRoleStagesDTO> serviceElementJobRoleStagesDTOList = new ArrayList<ServiceElementJobRoleStagesDTO>();
		boolean ccmFlag = true;
		String message;
		try {
		oppScopesMap = solutionService.getAllServiceScopeInMapByOppID(opportunityId);
			
			
			model.addAttribute("oppScopesMap", oppScopesMap);
			firstScopeId = (Integer) oppScopesMap.keySet().toArray()[0];
			
			
		} catch (MSSPException e) {
			logger.error("There is a error while fetching  Opportunity Scope map data from database",e);	
			e.printStackTrace();
		}
			
			if(oppScopeId==null || oppScopeId.equals(0)){
				//In case of first time loading of page
				oppScopeId =firstScopeId;
			}
		
		try {
			 serviceElementId = solutionLeverService.getServiceElementIdByOppScopeId(oppScopeId);
				SolutionDTO solutionDTO = solutionService.getSolutionDetail(solutionId);

				if(!JOB_ROLE_MODEL_CCM.equalsIgnoreCase(solutionDTO.getJobRoleModel())){
				ccmFlag = false;
			}
			
				seDTOList = solutionLeverService.getJobRoleSerEleDTOListByServiceElementId(serviceElementId, ccmFlag);

		} catch (MSSPException e) {
			logger.error("There is a error while loading  Job role Service Element DTO List from database",e);	
			e.printStackTrace();
		}
		
		//fetching location and sub location from locationSubLoc
		
		String[] splited = locationSubLoc.split(" ");
		String location = splited[0];
		String subLocation = splited [1];
		
		
		//Service for fetching LocationPyramidDTO list based on  OpportunityScopeId 
		List <LocationPyramidDTO> locationPyramidDTOList =	solutionLeverService.loadLocationPyramid( solutionId, oppScopeId ,location,subLocation);
	
		
		
		List <Integer>yearListDP = null;
		//fetch year list
				OpportunityDetail oppDetail;
				try {
					oppDetail = solutionLeverService.getOpportunityDetail(opportunityId);
					yearListDP = solutionLeverService.getYearList(oppDetail);
				} catch (MSSPException e) {
					logger.error("There is a error while fetching  Opportunity Detail/year List from database",e);	
					e.printStackTrace();
				}
				
				
					if(seDTOList != null && seDTOList.size()>0  ){
					for(ServiceElementJobRoleStagesDTO dto :seDTOList){
						ServiceElementJobRoleStagesDTO tempDTO = new ServiceElementJobRoleStagesDTO();
						BeanUtils.copyProperties(dto,tempDTO);
						tempDTO.setJobRoleStages(dto.getJobRoleStages());
						tempDTO.setServiceElement(dto.getServiceElement());
					serviceElementJobRoleStagesDTOList.add(tempDTO);
				}
				}
					
				solutionLeverDTO.setServiceElementJobRoleStagesDTO(serviceElementJobRoleStagesDTOList);
				model.addAttribute("locationPyramidDTOList",locationPyramidDTOList);
				model.addAttribute("oppScopeId", oppScopeId);
				model.addAttribute("location",location);
				model.addAttribute("subLocation",subLocation);
				model.addAttribute("yearListDP", yearListDP);
				model.addAttribute("solutionLeverDTO",solutionLeverDTO);
				//to be used in save pop -up method:
				
				if( saveMessage.equals("success")){
					//Saved Sucessfully
					message="Data Saved Sucessfully !!";
				}
				
				
				else {
					//In case of Exception
					message=saveMessage;
				}
				
				model.addAttribute("message", message);



		return "popUpPyramid";
	}
	
	//@ResponseBody
	//@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping("/solution/savePopUp")
	public String savePopUp(Model model,@ModelAttribute ("solutionLeverDTO")SolutionLeverDTO solutionLeverDTO,
			/*@RequestParam("param")Integer oppScopeId,*/HttpSession session,HttpServletRequest request){
		logger.info("Saving pop-up");
		String saveMessage= "";
		Integer solutionId = getSessionSolutionId(session);
		Integer oppScopeId = solutionLeverDTO.getOppScopeId();
		for(LocationPyramidDTO locationPyramidDTO: solutionLeverDTO.getLocationPyramidDTOList()){
			//saving data 
			try {
				solutionLeverService.saveLocationPyramid(locationPyramidDTO, solutionId, oppScopeId);
			} catch (DAOException e) {
				saveMessage=e.getMessage();
				e.printStackTrace();
			}
		}
		if(saveMessage ==""){
			saveMessage="success";
		}
		
		String p1 = request.getParameter("param");
		String p2 = request.getParameter("param2");
		
		return "redirect:displayPopup?param="+p1+"&param2="+p2+"&param3="+saveMessage;
	}
	
	@RequestMapping("/solution/saveDeliveryPyramid")
	public String saveDeliveryPyramid(ModelMap model, HttpSession session,@ModelAttribute ("solutionLeverDTO")SolutionLeverDTO solutionLeverDTO,HttpServletRequest request) {
		logger.info("Saving delivery pyramid !! ");
		Integer solutionId = getSessionSolutionId(session);
		Integer oppScopeId = solutionLeverDTO.getOppScopeId();
		String deliveryPyramidMessage = null;
		for(LocationBreakupDTO locationBreakupDTO : solutionLeverDTO.getLocationBreakupDTOList()) {
			 
			 try {
				solutionLeverService.saveLocationBreakup(locationBreakupDTO, solutionId, oppScopeId);
				deliveryPyramidMessage="success";
			} catch (DAOException e) {
				logger.error("There is a error while saving Location Breakup datat into database",e);	
				deliveryPyramidMessage="exception";
				e.printStackTrace();
			}
			 
			 
		    }
		request.setAttribute("deliveryPyramidMessage", deliveryPyramidMessage);
		return "forward:deliveryPyramid";
	
	}
			
	
	@RequestMapping("/solution/productivityModelling")
	public String productivityModelling(ModelMap model, HttpSession session, @ModelAttribute ("solutionLeverDTO") SolutionLeverDTO solutionLeverDTO,HttpServletRequest request) {
		logger.info("inside productivityModelling ");
		Integer opportunityId = getSessionOpportunityId(session);
		Integer solutionId = getSessionSolutionId(session);
		
		List<Integer> yearList = null;
		Map<Integer, String> oppScopesMap = null;
		List<ProductivityLeverDTO> prodLeverDTOList = null;
		String solutionLeverApproach="";
		
		
		try {
			OpportunityDetail oppDetail = solutionLeverService.getOpportunityDetail(opportunityId);
		//	if(solutionLeverService !=null){
			yearList = solutionLeverService.getYearList(oppDetail);
		//	}
			solutionLeverApproach = solutionLeverService.getSolutionLeverApporach(solutionId);
			 oppScopesMap = solutionService.getAllServiceScopeInMapByOppID(opportunityId);
			
			 prodLeverDTOList= solutionLeverService.getProductivityLeverList(solutionId);
			
		
		} catch (Exception e) {
			logger.error("There is a error while fetching  Opportunity Scope Map or Productivity Lever list from database",e);	
			e.printStackTrace();
		}
		List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(opportunityId, solutionId);
		model.addAttribute("serviceBucketData", serviceBucketData);
		
		model.addAttribute("solutionLeverApproach", solutionLeverApproach);
		model.addAttribute("displayType", "yearwise");
		model.addAttribute("oppScopesMap", oppScopesMap);
		model.addAttribute("yearList", yearList);
		model.addAttribute("selectedTab", "TAB3");
		model.addAttribute("solutionLeverDTO",new SolutionLeverDTO());
		model.addAttribute("prodLeverDTOList", prodLeverDTOList);
		model.addAttribute("prodModMessage",request.getAttribute("prodModMessage"));
		return "solutionLever";
	}
	
	@RequestMapping("/solution/saveProductivityModelling")
	public String saveProductivityModelling(ModelMap model, HttpSession session, 
			@ModelAttribute ("solutionLeverDTO") SolutionLeverDTO solutionLeverDTO,HttpServletRequest request)  {
		logger.info("Saving productivityModelling !!");
		Integer opportunityId = getSessionOpportunityId(session);
		Integer solutionId = getSessionSolutionId(session);
		String prodModMessage = null;
		solutionLeverDTO.setSolutionId(solutionId);
		logger.info("Productivity Lever Size: "+solutionLeverDTO.getSolutionLeverApproach());
		logger.info("Productivity Lever Size: "+solutionLeverDTO.getProdLeverList().get(0).getMonthYear());
		try {
			
			solutionLeverService.saveProductivityLever(solutionLeverDTO, opportunityId);
			prodModMessage="success";
			
		} catch (Exception e) {
			logger.error("There is a error while saving Productivity Lever into database",e);	
			prodModMessage="exception";
			e.printStackTrace();
		}
		request.setAttribute("prodModMessage", prodModMessage);
		model.addAttribute("selectedTab", "TAB3");
		//return "redirect:productivityModelling";
		return "forward:productivityModelling";
	}
	
		
	@RequestMapping("/solution/monthwiseProductivity")
	public String monthwiseProductivity(ModelMap model, HttpSession session, @ModelAttribute ("solutionLeverDTO") SolutionLeverDTO solutionLeverDTO,
			@RequestParam(value="startDate" , required = false)String startDate, @RequestParam(value="endDate", required = false)String endDate,
			 @RequestParam(value="isCal", required = false, defaultValue="true")boolean isCal, @RequestParam(value="page", required = false, defaultValue="1")String page) {
		logger.info("inside  monthwiseProductivity !!");
		Integer solutionId = getSessionSolutionId(session);
		Integer opportunityId = getSessionOpportunityId(session);
		//String startDate="02/01/2015";
		//String endDate="01/01/2016";
		logger.info( "startDate  + " + startDate);
		logger.info( "endDate  + " + endDate);
		logger.info( "isCal  + " + isCal);
		List<Date> monthList = null;
		Map<Integer, String> oppScopesMap = null;
		List<FTESummaryDTO> monthwiseFTEList = null;
		Map<Integer,String> pageList = null;
		try {
				OpportunityDetail oppDetail = solutionLeverService.getOpportunityDetail(opportunityId);
				pageList = getPageList(isCal,oppDetail.getSteadyStateStartDate(), oppDetail.getSteadyStateEndDate());
				
				if("".equals(startDate) || startDate == null){
					String dateStr = pageList.get(1);
					String[] dates = dateStr.split(";");
					startDate = dates[0];
					endDate = dates[1];
				
				}	
				
				SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
				
				monthwiseFTEList = solutionLeverService.getMonthwiseFTEList(solutionId, sdf.parse(startDate), sdf.parse(endDate));
				
				oppScopesMap = solutionService.getAllServiceScopeInMapByOppID(opportunityId);
				monthList  = getMonthList(isCal,sdf.parse(startDate), sdf.parse(endDate));
				
				List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(opportunityId, solutionId);
				model.addAttribute("serviceBucketData", serviceBucketData);
				
		logger.info( "monthwiseFTEList  + " + monthwiseFTEList);
		} catch (Exception e) {
			logger.error("There is a error in monthwiseProductivity method",e);	
			e.printStackTrace();
		}
		model.addAttribute("displayType", "monthwise");
		model.addAttribute("selectedTab", "TAB3");
		model.addAttribute("monthwiseFTEList", monthwiseFTEList);
		model.addAttribute("oppScopesMap", oppScopesMap);
		model.addAttribute("monthList", monthList);
		model.addAttribute("pageList", pageList);
		model.addAttribute("page", page);
		model.addAttribute("isCal", isCal);
		return "solutionLever";
	}
	
	@RequestMapping(value = "/solution/updateFTESummary", method = RequestMethod.POST)
	public @ResponseBody
	String updateFTESummary(Model model,
			@RequestParam("fteSummaryId") Long fteSummaryId, @RequestParam("fteValue") Float fteValue) {
		String retStr = "Success";
		try {
			retStr = solutionLeverService.updateFTESummary(fteSummaryId,fteValue);
				

		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.error("There is a error while loading data from database", e);
			e.printStackTrace();
		}

		return retStr;

	}
	
	private List<Date> getMonthList(boolean isCal, Date prevDate, Date nextDate) {

		List<Date> monthList = new ArrayList<Date>();
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(prevDate);
		if (isCal) {
			cal1.set(Calendar.MONTH, 0);
		}
		int i = 0;
		while (i<12) {
			monthList.add(cal1.getTime());
			cal1.add(Calendar.MONTH, 1);
			prevDate = cal1.getTime();
			i++;
		}

		return monthList;
	}
	
	private Integer getTotalMonths(Date startDate, Date endDate ){
		
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		
		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		
		return diffMonth+1;
	}
	
	private Map<Integer,String> getPageList(boolean isCal, Date startDate, Date endDate) {

		Map<Integer,String> pageList = new HashMap<Integer,String>();
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(startDate);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(endDate);
		
		int len = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
		
		if(isCal){
			len = len+1;
		}else
		if(cal2.get(Calendar.MONTH)>=cal1.get(Calendar.MONTH)){
			len = len+1;
		}
		
		Integer i= 1;
		
		Date prevDate= null;
		Date nextDate= null;
	
		while (i<=len) {
			if (isCal) {
				cal1.set(Calendar.MONTH, 0);
				prevDate = cal1.getTime();
				
				cal1.set(Calendar.MONTH, 11);
				nextDate = cal1.getTime();
				cal1.add(Calendar.YEAR, 1);
				startDate = cal1.getTime();
			}else{
				
				prevDate = cal1.getTime();
				
				cal1.add(Calendar.YEAR, 1);
				cal1.add(Calendar.MONTH, -1);
				nextDate = cal1.getTime();
				cal1.add(Calendar.MONTH, 1);
				
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
			pageList.put(i,sdf.format(prevDate)+";"+ sdf.format(nextDate));
			i++;
		}
		
		for (Map.Entry<Integer, String> entry : pageList.entrySet()) {
			logger.info("Key : " + entry.getKey() + " Value : "
				+ entry.getValue());
		}
		return pageList;
	}
}
