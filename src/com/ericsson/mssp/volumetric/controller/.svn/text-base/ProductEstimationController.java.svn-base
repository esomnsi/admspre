package com.ericsson.mssp.volumetric.controller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.AppDevTicketDistributionDTO;
import com.ericsson.mssp.common.dto.OpportunityComponentDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.ProductEstimationGenericInput;
import com.ericsson.mssp.common.dto.ProductVolumetricDTO;
import com.ericsson.mssp.common.dto.SaveProductEstimationForSolutionWrapperDTO;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.controller.BaseController;
import com.ericsson.mssp.solution.service.ISolutionService;
import com.ericsson.mssp.volumetric.forms.ProductEstimationInputResponse;
import com.ericsson.mssp.volumetric.forms.ProductVolumetricResponse;
import com.ericsson.mssp.volumetric.service.ProductVolumetricService;
import com.ericsson.mssp.volumetric.service.VolumetricService;

/**
 * @author EGI
 * 
 */
@Controller
public class ProductEstimationController extends BaseController implements
		MSSPConstants {

	@Autowired
	private ISolutionService solutionService;

	@Autowired
	private VolumetricService volumetricService;

	@Autowired
	private ProductVolumetricService productEstimationService;

	private static final Log logger = LogFactory
			.getLog(ProductEstimationController.class);

	private static final String serviceElement = "APP_DEV";

	@RequestMapping(value = "/solution/productEstimation")
	public String init(Model model, HttpSession session) {

		Integer solutionId = getSessionSolutionId(session);
		Integer opportunityId = getSessionOpportunityId(session);
		LinkedHashMap<String, LinkedHashMap<String, Integer>> paramMap = null;
		/* List<OpportunityScopeDTO> selOppScopeList = null; */
		List<OpportunityComponentDTO> selOppComponentList = null;
		OpportunityDetail opportunityDetail = null;
		boolean hasServiceElements = false;
		boolean hasFastTrackServiceElement = false;
		List<String> serviceBucketData = null;

		try {
			/*
			 * selOppScopeList = volumetricService
			 * .getServiceScopeByServiceElement(opportunityId, serviceElement);
			 * model.addAttribute("opScopeList", selOppScopeList);
			 */

			selOppComponentList = productEstimationService
					.getComponentForOpportunity(opportunityId);
			model.addAttribute("selOppComponentList", selOppComponentList);

			opportunityDetail = volumetricService
					.getOpportunityDetail(opportunityId);
			model.addAttribute(
					"steadyStateDuration",
					(opportunityDetail != null ? opportunityDetail
							.getSteadyStateDuration() : 0));

			paramMap = productEstimationService
					.getProductEstimationAuxiliaryParamsWrapper(solutionId);
			model.addAttribute("paramMap", paramMap);

			serviceBucketData = solutionService
					.loadServiceBucketDataByOppSolutionID(opportunityId,
							solutionId);
			model.addAttribute("serviceBucketData", serviceBucketData);

			String hasEditSolAccess = (String) getSessionValueFromKey(session,
					HAS_EDIT_SOL_ACCESS);
			model.addAttribute("hasEditSolAccess",
					hasEditSolAccess == null ? true : hasEditSolAccess);
			
			List<OpportunityScopeDTO> opportunityScopeList = volumetricService.getServiceScopeByServiceElement(opportunityId, serviceElement);
			if(null != opportunityScopeList && opportunityScopeList.size() > 0){
				hasServiceElements = true;
			}
			model.addAttribute("hasServiceElements",hasServiceElements);
			
			hasFastTrackServiceElement = volumetricService.opportunityHasServiceElement(opportunityId, MSSPConstants.ServiceElementName_Fast_Track_Requests);
			model.addAttribute("hasFastTrackServiceElement",hasFastTrackServiceElement);

		} catch (MSSPException e) {
			logger.error("Error in /solution/activities::", e);
			model.addAttribute("message", ApplicationPropertiesUtil
					.getProperty("msg.common.datasave.error"));
		}
		model.addAttribute("selectedTab", serviceElement);
		model.addAttribute("solutionId", solutionId);
		model.addAttribute("opportunityId", opportunityId);
		return "volumetric";
	}

	@RequestMapping(value = "/solution/activities", method = RequestMethod.POST)
	public @ResponseBody
	ProductVolumetricResponse fetchActivities(
			Model model,
			HttpSession session,
			// @RequestParam("serviceScopeId") Integer serviceScopeId,
			@RequestParam("componentId") Integer componentId,
			// @RequestParam("oppScopeId") Integer oppScopeId,
			@RequestParam("oppComponentId") Integer oppComponentId,
			@RequestParam(value = "filters", required = false, defaultValue = "NONE") String filters,
			@RequestParam(value = "_search", required = false, defaultValue = "false") boolean _search,
			@RequestParam(value = "page", required = false, defaultValue = "NONE") String page,
			@RequestParam(value = "rows", required = false, defaultValue = "NONE") String rows,
			@RequestParam(value = "sidx", required = false, defaultValue = "NONE") String sortField,
			@RequestParam(value = "sord", required = false, defaultValue = "NONE") String sortOrder) {
		ProductVolumetricResponse response = new ProductVolumetricResponse();
		List<ProductVolumetricDTO> activities = null;
		Integer solutionId = getSessionSolutionId(session);

		try {
			/*
			 * activities = productEstimationService
			 * .getProductEstimationActivities(serviceScopeId, solutionId);
			 */
			activities = productEstimationService
					.getProductEstimationActivities(componentId, solutionId);
			response.setProductEstimationActivityList(activities);

			// Assign the total number of records found. This is used for
			// paging.
			response.setRecords(String.valueOf(activities.size()));

			// Since our service is just a simple service for teaching purposes
			// We didn't really do any paging. But normally your DAOs or your
			// persistence layer should support this
			// Assign a dummy page
			response.setPage(page);

			// Same. Assign a dummy total pages
			/*
			 * response.setTotal("" + Math.ceil((float) activities.size() /
			 * Integer.parseInt(rows)));
			 */
			// response.setTotal("1");

		} catch (MSSPException e) {
			logger.error("Error in /solution/activities::", e);
			model.addAttribute("message", ApplicationPropertiesUtil
					.getProperty("msg.common.datasave.error"));
		}
		return response;
	}

	@RequestMapping(value = "/solution/saveEstimation", method = RequestMethod.POST)
	public @ResponseBody
	String saveEstimationForSolution(Model model, HttpSession session,
			@RequestBody SaveProductEstimationForSolutionWrapperDTO editedObject) {
		String message = "";

		try {
			message = productEstimationService
					.saveProductEstimationForSolution(editedObject);
		} catch (MSSPException e) {
			logger.error("Error in /solution/saveEstimation::", e);
			model.addAttribute("message", ApplicationPropertiesUtil
					.getProperty("msg.common.datasave.error"));
		}

		return message;
	}

	@RequestMapping(value = "/solution/loadTicketDistribution", method = RequestMethod.POST)
	public @ResponseBody
	ProductEstimationInputResponse loadTicketDistribution(
			Model model,
			HttpSession session,
			// @RequestBody ProductEstimationGenericInput inputBean,
			@RequestParam("opportunityId") Integer opportunityId,
			@RequestParam("serviceFunctionCode") String serviceFunctionCode,
			@RequestParam(value = "filters", required = false, defaultValue = "NONE") String filters,
			@RequestParam(value = "_search", required = false, defaultValue = "false") boolean _search,
			@RequestParam(value = "page", required = false, defaultValue = "NONE") String page,
			@RequestParam(value = "rows", required = false, defaultValue = "NONE") String rows,
			@RequestParam(value = "sidx", required = false, defaultValue = "NONE") String sortField,
			@RequestParam(value = "sord", required = false, defaultValue = "NONE") String sortOrder) {
		List<AppDevTicketDistributionDTO> resultList = null;
		ProductEstimationInputResponse response = new ProductEstimationInputResponse();
		ProductEstimationGenericInput inputBean = new ProductEstimationGenericInput();
		inputBean.setOpportunityID(opportunityId);
		inputBean.setServiceFunctionCode(serviceFunctionCode);

		try {
			resultList = productEstimationService
					.loadPercentageDistribution(inputBean);
			response.setTicketDistributionList(resultList);

			// Assign the total number of records found. This is used for
			// paging.
			response.setRecords(String.valueOf(resultList.size()));

			// Since our service is just a simple service for teaching purposes
			// We didn't really do any paging. But normally your DAOs or your
			// persistence layer should support this
			// Assign a dummy page
			response.setPage(page);

			// Same. Assign a dummy total pages
			/*
			 * response.setTotal("" + Math.ceil((float) activities.size() /
			 * Integer.parseInt(rows)));
			 */
			// response.setTotal("1");
		} catch (MSSPException e) {
			logger.error("Error in /solution/loadTicketDistribution::", e);
			model.addAttribute("message", ApplicationPropertiesUtil
					.getProperty("msg.common.datasave.error"));
		}

		return response;
	}

	@RequestMapping(value = "/solution/savePercentDistribution", method = RequestMethod.POST)
	public @ResponseBody
	String savePercentDistribution(Model model, HttpSession session,
			@RequestBody AppDevTicketDistributionDTO[] editedObject) {
		String message = MSSPConstants.SAVE_SUCCESS_MESSAGE;
		Integer solutionId = getSessionSolutionId(session);

		try {
			productEstimationService.savePercentDistribution(editedObject, solutionId);
		} catch (MSSPException e) {
			message = ApplicationPropertiesUtil
					.getProperty("msg.common.datasave.error");
			logger.error("Error in /solution/saveEstimation::", e);
			model.addAttribute("message", message);
		}

		return message;
	}
}