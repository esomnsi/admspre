package com.ericsson.mssp.solution.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.dto.CommercialModelDTO;
import com.ericsson.mssp.common.dto.CountryDTO;
import com.ericsson.mssp.common.dto.DeliveryModelDTO;
import com.ericsson.mssp.common.dto.DeliveryTypeModelDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.OpportunityDetailDTO;
import com.ericsson.mssp.common.dto.OpportunitySourceDTO;
import com.ericsson.mssp.common.dto.ScopeDefinedByDTO;
import com.ericsson.mssp.common.dto.UserAccessDTO;
import com.ericsson.mssp.common.dto.UserDTO;
import com.ericsson.mssp.common.dto.VolumetricsDefinedByDTO;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.login.service.IManageUserAccessService;
import com.ericsson.mssp.solution.service.ISolutionService;

@Controller
public class CreateOpportunityController extends BaseController{

	@Autowired
	ISolutionService solutionService;
	String message = "";
	String cFlag = "false";
	
	@Autowired
	private IManageUserAccessService manageUserAccessService;
	
	public static Logger logger = Logger.getLogger(CreateOpportunityController.class);
	
	@ModelAttribute("countries")
	public List<CountryDTO> listCountries(Map<String, Object> map)
	{
		List<CountryDTO> countriesList = new ArrayList<CountryDTO>();
		countriesList = solutionService.getCountries();
		return countriesList;
	}
	
	@ModelAttribute("gscCountries")
	public List<CountryDTO> listGscCountries(Map<String, Object> map)
	{
		
		
		List<CountryDTO> gscCountriesList = solutionService.getGscCountries();
		
		
		
		return gscCountriesList;
	}

	@ModelAttribute("devModel")
	public List<DeliveryModelDTO> devModels(ModelMap modelMap){
		List<DeliveryModelDTO> devModelDTOs = new ArrayList<DeliveryModelDTO>();
		devModelDTOs = solutionService.getDeliveryModels();
		return devModelDTOs;
	}
	
	@ModelAttribute("devType")
	public List<DeliveryTypeModelDTO> devTypeModels(ModelMap modelMap){
		List<DeliveryTypeModelDTO> devTypeModelDTOs = new ArrayList<DeliveryTypeModelDTO>();
		devTypeModelDTOs = solutionService.getDeliveryTypeModels();
		return devTypeModelDTOs;
	}
	
	@ModelAttribute("scopeBy")
	public List<ScopeDefinedByDTO> scopeBy(ModelMap modelMap){
		List<ScopeDefinedByDTO> scopeByDTOs = new ArrayList<ScopeDefinedByDTO>();
		scopeByDTOs = solutionService.getScopeDefinedBy();
		return scopeByDTOs;
	}
	
	@ModelAttribute("volumetricsBy")
	public List<VolumetricsDefinedByDTO> volumetricsBy(ModelMap modelMap){
		List<VolumetricsDefinedByDTO> volumetricsByDTOs = new ArrayList<VolumetricsDefinedByDTO>();
		volumetricsByDTOs = solutionService.getVolumetricsDefinedBy();
		return volumetricsByDTOs;
	}
	
	@ModelAttribute("representatives")
	public List<UserDTO> listRepresentatives(Map<String, Object> map)
	{
		List<UserDTO> representativesList = new ArrayList<UserDTO>();
		representativesList = solutionService.getRepresentatives();
		return representativesList;
	}
	
	
	
	private Map<String, String> getVertical()
	{
		Map<String,String> vertical = new LinkedHashMap<String,String>();
		vertical.put("ADMS", "ADMS");
		vertical.put("BSS", "BSS");
		vertical.put("OSS", "OSS");
		return vertical;
	}
	
	private Map<String,String> getReasons()
	{
		Map<String,String> reasons = new LinkedHashMap<String,String>();
		reasons.put("one", "Aligns with their overall strategy");
		reasons.put("two", "Cost cutting measures");
		reasons.put("three", "Not happy with current provider");
		reasons.put("four", "Lack of in-house capabilities");
		reasons.put("five", "Other (please specify)");
		
		return reasons;
	}
	
	private Map<String,String> getNewBusiness()
	{
		Map<String,String> newbusiness = new LinkedHashMap<String,String>();
		newbusiness.put("yes", "Yes");
		newbusiness.put("no", "No");
		
		return newbusiness;
	}
	
	@RequestMapping("/opportunity/InitiateOpportunity")
	public String initiateCreateOpportunity(@ModelAttribute("OpportunityDTO") OpportunityDTO opportunityDTO,HttpSession session)
	{
		
		logger.info("inside initiate opportunity");
		logger.info("opportunity being saved with : "
				+"opportunity name as : "+ opportunityDTO.getOpportunityName()+"|"
				+"session user name : " + (String)session.getAttribute("userName")+"|"
				+"opportunity Id : " + opportunityDTO.getOpportunityId()+"|"
				);
		
		opportunityDTO.setCreatedBy((String)session.getAttribute("userName"));
		opportunityDTO.setAssignedTo((String)session.getAttribute("userName"));
		String opportunityId = solutionService.initiateOpportunity(opportunityDTO);
		logger.info("id from db : " + opportunityId);
		if(opportunityId != null)
		{
			setSessionOpportunityId(session, new Integer(opportunityId));
			return "redirect:./displayOpportunity";
		}
		else
		{
			logger.info("same opportunity exists");
			setSessionOpportunityId(session, new Integer(-1));
			message = ApplicationPropertiesUtil.getProperty("msg.opportunity.duplicateOpportunity");
			return "forward:./newOpportunity";
		}
	}
	
	@RequestMapping("/opportunity/newOpportunity")
	public String createNewOpportunity(ModelMap model,HttpSession session)
	{
		List<OpportunitySourceDTO> opportunitySourceDTOs = new ArrayList<OpportunitySourceDTO>();
		List<CommercialModelDTO> commercialModelDTOs = new ArrayList<CommercialModelDTO>();
		
		opportunitySourceDTOs = solutionService.getOpportunitySources();
		commercialModelDTOs = solutionService.getCommercialModels();
		String mes = "";
		mes = message;
		message = "";
		cFlag = "false";
		logger.info("inside new opportunity");
		session.removeAttribute(OPPORTUNITY_ID);
		session.removeAttribute(SOLUTION_ID);
		session.removeAttribute(HAS_EDIT_SOL_ACCESS);
		model.put("OpportunityDTO",new OpportunityDTO());
		model.put("message",mes);
		model.addAttribute("oppSource", opportunitySourceDTOs);
		model.addAttribute("comModel", commercialModelDTOs);
		model.addAttribute("createdByFlag", cFlag);
		model.addAttribute("role_guest", session.getAttribute("role_guest"));
		return "landingPage";
	}
	
	@RequestMapping("/opportunity/landingPage")
	public String landingPage(ModelMap map,HttpSession session)
	{
		logger.info("inside landing page");
		List<OpportunitySourceDTO> opportunitySourceDTOs = new ArrayList<OpportunitySourceDTO>();
		List<CommercialModelDTO> commercialModelDTOs = new ArrayList<CommercialModelDTO>();
		
		opportunitySourceDTOs = solutionService.getOpportunitySources();
		commercialModelDTOs = solutionService.getCommercialModels();
		
		Integer oppId = getSessionOpportunityId(session);
		String checkOppId = "not_null";
		OpportunityDTO opportunityDTO = new OpportunityDTO();
		
		
		
		logger.info("oppId : " + oppId);
		if(oppId == null)
		{
			checkOppId = "";
			opportunityDTO.setOpportunityId(null);
			map.put("checkOppId", checkOppId);
			map.put("OpportunityDTO", opportunityDTO);
			cFlag = "false";
		}
		else
		{
			
			opportunityDTO = solutionService.getOpportunity(oppId);
			if(opportunityDTO.getCreatedBy().equalsIgnoreCase((String)(session.getAttribute("userName")))
/*				||	opportunityDTO.getAssignedTo().equalsIgnoreCase((String)(session.getAttribute("userName")))
*/					)
			{
				cFlag = "false";
				
			}
			else
			{
				cFlag = "true";
				
			}
			map.put("checkOppId", checkOppId);
			map.put("OpportunityDTO",opportunityDTO);
		}
		 String message ="";
		    String solMsg =(String)getSessionValueFromKey(session,OPPORTUNITY_NOTEXISTS);
		    if(solMsg != null  && !"".equals(solMsg)){
		    	message =  (String)getSessionValueFromKey(session,OPPORTUNITY_NOTEXISTS);
			}
		    map.addAttribute("message", message);
		    session.removeAttribute(OPPORTUNITY_NOTEXISTS);
		    map.addAttribute("oppSource", opportunitySourceDTOs);
		    map.addAttribute("comModel", commercialModelDTOs);
		    map.addAttribute("createdByFlag", cFlag);
		    map.addAttribute("role_guest", session.getAttribute("role_guest"));
		   		    
		return "landingPage";
	}
	
	
	@RequestMapping(value="/opportunity/displayOpportunity")
	public String listOpportunities(Model model, HttpSession session)
	{
		Map<String,String> vertical = getVertical();
		Map<String,String> reasons = getReasons();
		Map<String,String> newbusiness = getNewBusiness();
		String mes = "";
		logger.info("inside display opportunity["+session.getAttribute("userName")+"]" );
		OpportunityDetailDTO opportunityDetailDTO = new OpportunityDetailDTO();
		OpportunityDTO opportunityDTO = new OpportunityDTO();
		
		Integer oppId = getSessionOpportunityId(session);
		
		logger.info("oppId : " + oppId);
		if(oppId == null)
		{
			
			opportunityDTO.setOpportunityId(null);
			model.addAttribute("OpportunityDTO", opportunityDTO);
			model.addAttribute("role_guest", session.getAttribute("role_guest"));
			return "redirect:./landingPage";
		}
				
		opportunityDTO = solutionService.getOpportunity(oppId);
		
		logger.info("opportunity created by : " + opportunityDTO.getCreatedBy() + "| session user : " + session.getAttribute("userName"));
		
		if(opportunityDTO.getOpportunityDetailsDTO() != null){
			opportunityDetailDTO = opportunityDTO.getOpportunityDetailsDTO();
			CountryDTO gsc1 = new CountryDTO();
			CountryDTO gsc2 = new CountryDTO();
			CountryDTO nearShore = new CountryDTO();
			
			if(opportunityDTO.getCreatedBy().equalsIgnoreCase((String)(session.getAttribute("userName"))))
					/*||
					opportunityDTO.getAssignedTo().equalsIgnoreCase((String)(session.getAttribute("userName"))))*/
			{//Here False implies read only = false 
				opportunityDetailDTO.setCreatedBy("false");
			}
			else
			{
				opportunityDetailDTO.setCreatedBy("true");
			}
			
			if(opportunityDetailDTO.getOpportunityLocationsDTO() !=null){
				if((opportunityDetailDTO.getOpportunityLocationsDTO().getGsc1() != null))
				{
					gsc1 = solutionService.getSelectedCountry(opportunityDetailDTO.getOpportunityLocationsDTO().getGsc1());
				}
				if(opportunityDetailDTO.getOpportunityLocationsDTO().getGsc2() != null )
				{
					gsc2 = solutionService.getSelectedCountry(opportunityDetailDTO.getOpportunityLocationsDTO().getGsc2());
				}
				if(opportunityDetailDTO.getOpportunityLocationsDTO().getNearShore() != null)
				{
					nearShore = solutionService.getSelectedCountry(opportunityDetailDTO.getOpportunityLocationsDTO().getNearShore());
				}
			}
			model.addAttribute("gsc1",gsc1);
			model.addAttribute("gsc2",gsc2);
			model.addAttribute("nearShore",nearShore);
			
			
		}else{
			
				Map<String,Float> map = new HashMap<String, Float>();
				map = solutionService.getUtilizationPerYearDefaultValues();
				
				
		}
		
		
		
		
		mes = message;
		message = "";
		logger.info("message : " + mes);
		opportunityDetailDTO.setOpportunityDTO(opportunityDTO);
		model.addAttribute("verticalList", vertical);
		model.addAttribute("reasons",reasons);
		model.addAttribute("newbusiness",newbusiness);
		model.addAttribute("opportunityDetailDTO", opportunityDetailDTO);
		model.addAttribute("message",mes);
		model.addAttribute("role_guest", session.getAttribute("role_guest"));
		return "displayOpportunity";
	}
	
	@RequestMapping("/opportunity/saveOpportunity")
	public @ResponseBody String saveOpportunity(@RequestParam(value = "next", required = false, defaultValue = "")String next,@ModelAttribute("opportunityDetailDTO") OpportunityDetailDTO opportunityDetailDTO, HttpSession session)
	{
		String returnString;
		Integer oppId  = getSessionOpportunityId(session);
		logger.info("inside save opportunity");

		if(opportunityDetailDTO.getRegionKam().length() > 50){
			return "rkamLengthError";
		}
		if(opportunityDetailDTO.getCompetitors().length() > 255){
			return "compLengthError";
		}
		if(opportunityDetailDTO.getPrimaryBusinessLine().length() >50){
			return "pBusiness";
		}
		
		try{
				opportunityDetailDTO.setTransformationStartDate(ApplicationPropertiesUtil.string2Date(opportunityDetailDTO.getTsd()));
			    opportunityDetailDTO.setTransformationEndDate(ApplicationPropertiesUtil.string2Date(opportunityDetailDTO.getTed()));
			    opportunityDetailDTO.setSteadyStateStartDate(ApplicationPropertiesUtil.string2Date(opportunityDetailDTO.getSssd()));
			    opportunityDetailDTO.setSteadyStateEndDate(ApplicationPropertiesUtil.string2Date(opportunityDetailDTO.getSsed()));
		}catch(Exception e){logger.info("date e : " + e);}
		
		opportunityDetailDTO.getOpportunityDTO().setOpportunityId(oppId);
		
		logger.info("opportunity detail ID : " + opportunityDetailDTO.getOpportunityDetailId());
		
		if(opportunityDetailDTO.getOpportunityDetailId() != null){
			opportunityDetailDTO.setOpportunityDetailId(opportunityDetailDTO.getOpportunityDetailId());
		}
		returnString = solutionService.saveOpportunityDetails(opportunityDetailDTO);
		logger.info("opportunity detail id received : " + returnString + "| next value received : " + next);
		if(Integer.parseInt(returnString)>0)
		{
			if(next.equals("next"))
			{
				returnString = "redirect:./opportunity/displayDefineScope";
			}
		}
		else
			returnString = "redirect:./displayOpportunity";
		
		return returnString;
	}
	
	@RequestMapping(value="/opportunity/displayDefineScope")
	public String displayDefineScope(Model model)
	{
		logger.info("inside display define scope");
		return "redirect:/ADM_PRE/opportunity/defineScope";
	}
	
	@RequestMapping(value="/opportunity/openSolution")
	public String openSolution(Model model)
	{
		logger.info("inside opensolution");
		return "openSolution";
	}
	
	@RequestMapping(value="/opportunity/populateCountryTable")
	public @ResponseBody String listCountryTable(@RequestParam("co")String country)
	{
		logger.info("inside populate country table");
		Integer countryId = Integer.parseInt(country);
		logger.info("countryId : " + countryId);
		CountryDTO countryDTO = new CountryDTO();
		countryDTO = solutionService.getSelectedCountry(countryId);
		String temp = countryDTO.getCurrencyName()+"|"+countryDTO.getCurrencyCode()+"|"+countryDTO.getTimeZone();
		logger.info("values to jsp : " + temp);
		return temp;
	}
	
	@RequestMapping(value="/opportunity/searchOpportunityOwner", method = RequestMethod.GET)
	public String openPopup(Model model,@RequestParam("param")String ownerType)
	{
		model.addAttribute("otype", ownerType);
		model.addAttribute("userAccessDTO", new UserAccessDTO());
		return "popUpForOpportunityOwner";
	}
	
	@RequestMapping(value="/opportunity/searchUser", method = RequestMethod.POST)
	public String searchUser(Model model,@RequestParam("param")String ownerType,@ModelAttribute("userAccessDTO") UserAccessDTO userAccessDTO, HttpSession session){
		try {
				List<UserAccessDTO>searchResult = manageUserAccessService.searchLDAPUsers(userAccessDTO,null);
				if(searchResult.size() == 0){
					model.addAttribute("noRecords", "No records found, please provide a valid entry");
				}
				model.addAttribute("ownerType", ownerType);
				model.addAttribute("searchResult", searchResult);
				model.addAttribute("userAccessDTO", userAccessDTO);
		} catch (Exception	 e) {
			e.printStackTrace();
		}
		
		return "popUpForOpportunityOwner";
	}
}