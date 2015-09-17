package com.ericsson.mssp.search.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.ericsson.mssp.common.dto.CountryDTO;
import com.ericsson.mssp.common.dto.SearchDTO;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.search.service.ISearchService;
import com.ericsson.mssp.solution.controller.BaseController;
import com.ericsson.mssp.solution.service.ISolutionClone;
import com.ericsson.mssp.solution.service.ISolutionService;

@Controller
public class SearchController extends BaseController {

    @Autowired
    ISearchService searchService;

    @Autowired
    private ISolutionService solutionService;
    
    @Autowired
    private ISolutionClone solutionClone;
    
    public static Logger logger = Logger.getLogger(SearchController.class);

    private String message = "";

    @ModelAttribute("regions")
    public Map<String, String> listCountries() {
	Map<String, String> a = new HashMap<String, String>();
	List<CountryDTO> regions = searchService.getRegions();
	for (int i = 0; i < regions.size(); i++) {

	    if (!a.containsValue(regions.get(i).getRegion())) {
		a.put(regions.get(i).getRegion(), regions.get(i).getRegion());
	    }
	}
	return a;
    }

    @ModelAttribute("countries")
    public List<CountryDTO> listCountries(Map<String, Object> map) {
	List<CountryDTO> regions = searchService.getRegions();
	return regions;
    }

    @RequestMapping(value = "/search/openSolution")
    public String openSolution(ModelMap model,HttpSession session) {
	String mes = "";
	logger.info("inside open solution");
	mes = message;
	message = "";
	SearchDTO searchDTO = new SearchDTO();
	model.put("message", mes);
	model.put("searchDTO", searchDTO);
	model.put("role_guest",(String)session.getAttribute("role_guest"));
	return "openSolution";
    }

    @RequestMapping(value = "/search/cloneSolution")
    public String cloneSolution(
	    ModelMap model,
	    @RequestParam(value = "oppSolID", required = false, defaultValue = "NA") String oppSolID,
	    HttpSession session) {
	logger.debug("inside clonesolution  Opp Sol ID:" + oppSolID);
	System.out.println("inside clonesolution Opp Sol ID:" + oppSolID);
	String[] oppSolIDs = null;
	Integer solutionID = null;
	try {
	    oppSolIDs = !oppSolID.equalsIgnoreCase("NA") ? oppSolID.split("_")
		    : null;
	    solutionID = null != oppSolIDs && oppSolIDs.length > 1 ? Integer
		    .valueOf(oppSolIDs[1]) : -1;
	    String mes = "";
	    mes = message;
	    if (solutionID > 0) {
		solutionClone.cloneOfTheSolutionBySolutionIdUserID(
			solutionID, "" + session.getAttribute(USER_NAME));
	    } else {
		logger.info(session.getAttribute(USER_NAME)
			+ " User Solution cloing error for oppSolID:"
			+ oppSolID);
	    }
	    message = "";
	    SearchDTO searchDTO = new SearchDTO();
	    model.put("message", mes);
	    model.put("searchDTO", searchDTO);
	} catch (Exception e) {
	    logger.error("Error while cloneSolution user:"
		    + session.getAttribute(USER_NAME) + " Opp Solution ID:"
		    + oppSolID + " Exception:" + e);
	    e.printStackTrace();
	}
	return "openSolution";
    }

    @RequestMapping(value = "/search/listOpportunities")
    public String listOpportunities(ModelMap model,
	    @ModelAttribute("searchDTO") SearchDTO searchDTO) {
	logger.info("inside list opportunities");
	logger.info("opportunity name : " + searchDTO.getOpportunityName()
		+ " | customer name : " + searchDTO.getCustomerName()
		+ " | region : " + searchDTO.getRegion()
		+ " | min value : " + searchDTO.getMin()
		+ " | max value : " + searchDTO.getMax());

	if (
					("".equals(searchDTO.getOpportunityName()) || (searchDTO.getOpportunityName() == null))
					&& ("".equals(searchDTO.getCustomerName()) || (searchDTO.getCustomerName() == null))
					&& ("".equals(searchDTO.getRegion()) || (searchDTO.getRegion() == null))
					&& ("".equals(searchDTO.getMin()) || (searchDTO.getMin() == null))
					&& ("".equals(searchDTO.getMax()) || (searchDTO.getMax() == null))
		)
	{
	    message = "<ul><li>"
		    + ApplicationPropertiesUtil
			    .getProperty("msg.search.selectAnyOne")
		    + "</li></ul>";
	    return "redirect:/search/openSolution";
	}

	List<SearchDTO> search = new ArrayList<SearchDTO>();

	search = searchService.getOpportunities(searchDTO);
	logger.info("search list size : " + search.size());
	String mes = new String();
	mes = "";
	if (search.size() == 0) {
	    mes = "<ul><li>"
		    + ApplicationPropertiesUtil
			    .getProperty("msg.search.noResult") + "</li></ul>";
	} else {
	    mes = message;
	    message = "";
	}
	logger.info("message value set : " + mes);
	model.put("message", mes);
	model.put("searchList", search);
	return "openSolution";
    }

    @RequestMapping(value = "/search/viewRecords")
    public String viewRecords(ModelMap model, HttpSession session,
	    @ModelAttribute("searchDTO") SearchDTO searchDTO) {
	logger.info("inside view records");
	logger.info("selected : " + searchDTO.getSelected());
	logger.info(searchDTO.getCustomerName() + "|"
		+ searchDTO.getOpportunityName());
	Integer solutionId = null;
	try {
	    if (searchDTO.getSelected() != null) {
		String[] ids = searchDTO.getSelected().split("_");
		Integer opportunityId = Integer.parseInt(ids[0]);
		setSessionOpportunityId(session, opportunityId);

		if (ids.length > 1) {
		    solutionId = Integer.parseInt(ids[1]);
		    setSessionSolutionId(session, solutionId);
		} else {
		    session.removeAttribute(SOLUTION_ID);
		}

		String userId = (String) getSessionValueFromKey(session,
			USER_NAME);
		String selectedRole = (String) session.getAttribute("ROLE");
		String hasEditSolAccess = "true";
		if (solutionId != null) {

		    hasEditSolAccess = solutionService.validateUserEditAccess(
			    solutionId, userId, selectedRole);
		}
		setSessionValueByKey(session, HAS_EDIT_SOL_ACCESS,
			hasEditSolAccess);

		return "redirect:/opportunity/displayOpportunity";
	    }
	} catch (MSSPException e) {
	    logger.error("Error while setting the values in session for display");
	    e.printStackTrace();
	}

	message = "<ul><li>"
		+ ApplicationPropertiesUtil
			.getProperty("msg.search.selectToView") + "</li></ul>";
	model.put("searchDTO", searchDTO);
	return "forward:./listOpportunities";
    }
}
