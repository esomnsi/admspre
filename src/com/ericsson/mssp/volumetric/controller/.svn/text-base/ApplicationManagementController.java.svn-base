package com.ericsson.mssp.volumetric.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.ServiceScopeAppMainDataBean;
import com.ericsson.mssp.common.dto.TicketDistributionDTO;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.controller.BaseController;
import com.ericsson.mssp.solution.forms.VolumetricSolutionForm;
import com.ericsson.mssp.solution.service.ISolutionService;
import com.ericsson.mssp.volumetric.forms.TicketDistributionForm;
import com.ericsson.mssp.volumetric.service.ProductVolumetricService;
import com.ericsson.mssp.volumetric.service.VolumetricService;

@Controller
public class ApplicationManagementController extends BaseController implements MSSPConstants{
	
	@Autowired
	private VolumetricService volumetricService;
	
	@Autowired
	private ProductVolumetricService productEstimationService;
	
	@Autowired
	private ISolutionService solutionService;
	
	private static final String serviceElement = "APP_MGMT";
	
	@RequestMapping(value="/solution/applicationManagement")
	public String applicationManagement(Model model,HttpSession session){
		logger.info("Welcome to Application Management");
		try {
			boolean isAppMgmtSelected = true;
			Integer oppId = getSessionOpportunityId(session);
			Integer solutionId = getSessionSolutionId(session);
			// Service Bucket Data.
			List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(oppId,solutionId);
			model.addAttribute("serviceBucketData", serviceBucketData);
			
		List<OpportunityScopeDTO> opportunityScopeList = volumetricService.getServiceScopeByServiceElement((Integer)session.getAttribute(OPPORTUNITY_ID), serviceElement);
		
		if(null != opportunityScopeList && opportunityScopeList.size() > 0){
			
				List<TicketDistributionDTO> tktDistList = volumetricService.getTktDistByOppScopeIds(opportunityScopeList, serviceElement);
				TicketDistributionForm tktDistForm = new TicketDistributionForm();
				tktDistForm.setTktDistList(tktDistList);
				
				List<ServiceScopeAppMainDataBean> servScopeData = volumetricService.getProductEstBaseEffData(opportunityScopeList);
				model.addAttribute("servScopeData",servScopeData);
				model.addAttribute("tktDistForm",tktDistForm);
				model.addAttribute("selectedTab", serviceElement);
				model.addAttribute("isAppMgmtSelected", isAppMgmtSelected);
		}else{
			model.addAttribute("isAppMgmtSelected", false);
			model.addAttribute("selectedTab", serviceElement);
		}
		
		// For Common Input part.
		LinkedHashMap<String, LinkedHashMap<String, Integer>> paramMap = productEstimationService.getProductEstimationAuxiliaryParamsWrapper(solutionId);
		OpportunityDetail opportunityDetail = volumetricService.getOpportunityDetail(oppId);
		model.addAttribute("steadyStateDuration",(opportunityDetail != null ? opportunityDetail.getSteadyStateDuration() : 0));
		paramMap = productEstimationService.getProductEstimationAuxiliaryParamsWrapper(solutionId);
		model.addAttribute("paramMap", paramMap);
		
		//check if user has edit access 
				String hasEditSolAccess = (String) getSessionValueFromKey(session,
						HAS_EDIT_SOL_ACCESS);
				model.addAttribute("hasEditSolAccess",
						hasEditSolAccess == null ? true : hasEditSolAccess);
		
		} catch (MSSPException e) {
			logger.error("Error in /solution/applicationManagement::", e);
			e.printStackTrace();
		}
		return "volumetric";
	}
	
	@RequestMapping(value="/solution/saveAppMgmtTktDist")
	public String saveAppMgmtTktDist(Model model, @ModelAttribute("tktDistForm") TicketDistributionForm form, HttpSession session) {
		logger.info("Saving Ticket Distribution Data for Application Management");
		try {
			//getException();
			Integer solId = getSessionSolutionId(session);
			//1. saving into TicketDistribution table.
			List<TicketDistributionDTO> myList = form.getTktDistList();
			for(TicketDistributionDTO dto : myList){
				volumetricService.saveTicketDistribution(dto);		
			}
			
			//2. fetch total base hours and total base FTE for APP_DEV and APP_MAIN		
			List<Object[]> list = volumetricService.getTotalBaseHrsFteForDevMain(solId);
			Double totalBaseHrs = (Double) list.get(0)[0]; // sum of total base hours of APP_DEV and APP_MAIN for this solution.
			Double totalBaseFTE = (Double) list.get(0)[1]; // sum of total base FTE of APP_DEV and APP_MAIN for this solution.
			
			if(null == totalBaseHrs){
				totalBaseHrs = 0.0;
			}
			if(null == totalBaseFTE){
				totalBaseFTE = 0.0;
			}

			//3. calculate % distribution for Application Management service Elements and update in ProductEstimationBaseEffortForSolution table.
			//List<String> dataList = calculateEffortDistForAppMgmt(myList,totalBaseHrs,totalBaseFTE,solId);
			calculateAndUpdateProdEstBaseEff(myList,totalBaseHrs,totalBaseFTE,solId);
			
			//4. save data in ProductEstimationBaseEffortForSolution table.
			//model.addAttribute("dataList",dataList);
			// saving success message and div class name in model
			model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.success"));
		} catch (MSSPException e) {
			logger.error("Error in /solution/saveAppMgmtTktDist::", e);
			model.addAttribute("errorMessage", ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
			e.printStackTrace();
		}
		return "forward:/solution/applicationManagement";
		
	}
	
	private void calculateAndUpdateProdEstBaseEff(List<TicketDistributionDTO> myList, Double totalBaseHrs, Double totalBaseFTE, Integer solutionId) throws MSSPException{
		//List<String> dataList = new ArrayList<String>();
		List<ServiceScopeAppMainDataBean> dtoList = new ArrayList<ServiceScopeAppMainDataBean>();
		ServiceScopeAppMainDataBean bean = null;
		Double distBaseHrs = new Double(0);
		Double distBaseFTE = new Double(0);
		
		for(TicketDistributionDTO dto : myList){
			bean = new ServiceScopeAppMainDataBean();
			
			if(MSSPConstants.ServiceElementName_Fast_Track_Requests.equalsIgnoreCase(dto.getOpportunityScope().getServiceScope().getServiceScopeName())){
				distBaseHrs = (dto.getPercentTicketDistribution()*productEstimationService.getTotalProductEstimationForSolution(solutionId))/100;
			}else{
				distBaseHrs = (dto.getPercentTicketDistribution()*totalBaseHrs)/100;
				distBaseFTE = (dto.getPercentTicketDistribution()*totalBaseFTE)/100;
			}
			
			//String data = dto.getOpportunityScope().getServiceScope().getServiceScopeName()+"_"+distBaseHrs+"_"+distBaseFTE;
			bean.setOppScopeId(dto.getOpportunityScope().getOpportunityScopeId());
			bean.setSolutionId(solutionId);
			bean.setServScopeName(dto.getOpportunityScope().getServiceScope().getServiceScopeName());
			bean.setTotalBaseFTE(distBaseFTE);
			bean.setTotalBaseHours(distBaseHrs);
			dtoList.add(bean);
			//dataList.add(data);
		}
		productEstimationService.saveProductEstimationBaseEffortForSolution(dtoList);
	}
	
	/*private void getException() throws MSSPException{
		try {
			int x = 5/0;
		} catch (Exception e) {
			throw new MSSPException("DIVIDE BY ZERO EXCEPTION", e);
		}
	}*/

}
