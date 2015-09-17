//package com.ericsson.mssp.admin.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.ericsson.mssp.common.dto.JobRoleStagesDTO;
//import com.ericsson.mssp.common.dto.ServiceElementDTO;
//import com.ericsson.mssp.common.dto.ServiceElementJobRoleStagesDTO;
//import com.ericsson.mssp.serviceelement.management.form.ServiceElementManagementForm;
//import com.ericsson.mssp.servicelement.management.service.IServiceElementManagementService;
//import com.ericsson.mssp.solution.controller.BaseController;
//
//@Controller
//public class ServiceElementController extends BaseController{
//	
//	public static Logger logger = Logger.getLogger(ServiceElementController.class);
//	
//	@Autowired
//	IServiceElementManagementService serviceElementMgmtService;
//	
//	@RequestMapping("/admin/serviceElementManagement")
//	public String serviceElementManagement(ModelMap model, @ModelAttribute("serviceElementManagementForm") ServiceElementManagementForm serviceElementMgmtForm, HttpSession session, HttpServletRequest req){
//		logger.info("Running controller for Service element management");
//		logger.info("Selected Service Element is : "+serviceElementMgmtForm.getServiceElementId());
//		
//		// removing earlier attributes from session
//		session.removeAttribute("listServiceElements");
//		session.removeAttribute("serviceElement");
//		session.removeAttribute("listServEleJobRoleStages");
//		
//		//populating dropdown list
//		List<ServiceElementDTO> listServiceElements = serviceElementMgmtService.getServiceElements();
//		//model.addAttribute("listServiceElements", listServiceElements);
//		setSessionValueByKey(session, "listServiceElements", listServiceElements);		
//		return "serviceElementManagement";
//	}
//	
//	@RequestMapping("/admin/showAlreadyMappedRoles")
//	public String showAlreadyMappedJobRolesByServEleId(ModelMap model, @ModelAttribute("serviceElementManagementForm") ServiceElementManagementForm serviceElementMgmtForm, HttpSession session,HttpServletRequest req){
//		
//		logger.info("Showing Already mapped job roles.");
//		logger.info("Selected Service Element is : "+serviceElementMgmtForm.getServiceElementId());
//		
//		// if service element is not received from form (case when the control returns back to jsp after adding a new Job Role Stage),  
//		// get it from session.
//		Integer serviceElementID = null;
//		if(serviceElementMgmtForm.getServiceElementId() == null){
//			serviceElementID = (Integer) getSessionValueFromKey(session, "serviceElement");
//			serviceElementMgmtForm.setServiceElementId(serviceElementID);
//		}else{
//			serviceElementID =  serviceElementMgmtForm.getServiceElementId();
//		}
//		
//		
//		// listing already mapped Job Roles and Job Role Stages.		
//		List<ServiceElementJobRoleStagesDTO> listServEleJobRoleStages = serviceElementMgmtService.getServiceElementJobRoleStagesByServEleId(serviceElementID);
//		
//		serviceElementMgmtForm.setListServiceElementJobRoleStagesDTO(listServEleJobRoleStages);				
//		model.addAttribute("serviceElementManagementForm",serviceElementMgmtForm);
//		
//		//setting serviceElement id in session for next page. 
//		setSessionValueByKey(session, "serviceElement", serviceElementID);
//		setSessionValueByKey(session, "listServEleJobRoleStages", listServEleJobRoleStages);
//		
//		// setting value for Add Job Role Button
//		if(serviceElementID != null && serviceElementID != -1){
//		model.addAttribute("isServiceElementSelected", "true");
//		}
//		return "serviceElementManagement";
//		//return "redirect:./serviceElementManagement";
//	}
//	
//	@RequestMapping("/admin/addJobRoleToServiceElement")
//	public String addJobRoleToServiceElement(ModelMap model, @ModelAttribute("serviceElementJobRoleStagesDTO") ServiceElementJobRoleStagesDTO serviceElementJobRoleStagesDTO, HttpSession session){
//		logger.info("Add Job Role to Service Element");
//		List<ServiceElementJobRoleStagesDTO> listServEleJobRoleStages = (List<ServiceElementJobRoleStagesDTO>) session.getAttribute("listServEleJobRoleStages");
//		
//		// get Already Mapped JobRoleStageId's
//		List<Integer> alreadyMappedJobRoleStageIds = new ArrayList<Integer>();
//		for(ServiceElementJobRoleStagesDTO dto : listServEleJobRoleStages){
//			alreadyMappedJobRoleStageIds.add(dto.getJobRoleStages().getJobRoleStagesId());
//		}
//		//listing only those job Role Stages that are not already mapped.
//		List<JobRoleStagesDTO> listJobRoleStages =  serviceElementMgmtService.getAvailableJobRoleStages(alreadyMappedJobRoleStageIds);
//		
//		model.addAttribute("listJobRoleStages", listJobRoleStages);
//		return "addJobRoleToServiceElement";
//	}
//	
//	@RequestMapping("/admin/saveServiceElementJobRoleMapping")
//	public String saveServiceEleJobRoleMapping(ModelMap model, @ModelAttribute("serviceElementJobRoleStagesDTO") ServiceElementJobRoleStagesDTO serviceElementJobRoleStagesDTO, HttpSession session){
//		logger.info("Save ServiceElement Job Role Mapping");
//		serviceElementMgmtService.saveServiceElementJobRoleMapping(serviceElementJobRoleStagesDTO);
//		
//		return "forward:/admin/showAlreadyMappedRoles";
//	}
//	
//	@RequestMapping("/admin/editServiceElementJobRoleStage")
//	public String editServiceElementJobRoleStage(ModelMap map, @ModelAttribute("serviceElementManagementForm") ServiceElementManagementForm form){
//		logger.info("Edit Service Element Job Role Stage");
//		serviceElementMgmtService.updateServiceElementJobRoleMapping(form.getListServiceElementJobRoleStagesDTO().get(form.getSelected()));
//		
//		
//		return "forward:/admin/showAlreadyMappedRoles";
//	}
//
//}
