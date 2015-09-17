package com.ericsson.mssp.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.dto.JobRoleDTO;
import com.ericsson.mssp.common.dto.JobRoleStagesDTO;
import com.ericsson.mssp.common.dto.JobStageDTO;
import com.ericsson.mssp.common.dto.ServiceElementDTO;
import com.ericsson.mssp.common.dto.ServiceElementJobRoleStagesDTO;
import com.ericsson.mssp.jobrole.management.form.JobRoleForm;
import com.ericsson.mssp.jobrole.management.service.IJobRoleManagementService;
import com.ericsson.mssp.serviceelement.management.form.ServiceElementManagementForm;
import com.ericsson.mssp.servicelement.management.service.IServiceElementManagementService;
import com.ericsson.mssp.solution.controller.BaseController;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
/*
 * @author 
 */
@Controller
public class JobRoleAndServiceElementController  extends BaseController{


	public static Logger logger = Logger.getLogger(JobRoleAndServiceElementController.class);

	@Autowired
	IJobRoleManagementService jobRoleMgmtService;

	@RequestMapping("/admin/serviceJobRoleManagement")
	public String jobRoleManagement(Model model, @ModelAttribute("jobRoleDTO") JobRoleDTO jobRoleDTO, HttpSession session){
		logger.info("!!Welcome to Job Role Management!!");
		
		List<ServiceElementDTO> listServiceElements = serviceElementMgmtService.getServiceElements();
		model.addAttribute("listServiceElements", listServiceElements);
		model.addAttribute("selectedTab", "TAB1");
		
		
		session.removeAttribute(JOB_ROLE_ID);
		session.removeAttribute("exception");
		return "forward:/admin/listJobRoles";

	}

	@RequestMapping("/admin/listJobRoles")
	public String listJobRoles(ModelMap model, @ModelAttribute("jobRoleDTO")JobRoleDTO jobRoleDTO,@ModelAttribute("serviceElementManagementForm") ServiceElementManagementForm serviceElementMgmtForm){
		logger.info("Listing Job Roles");
		List<JobRoleDTO> list = jobRoleMgmtService.getJobRoles(jobRoleDTO);
		model.addAttribute("jobRoleList",list);
		//return "jobRoleManagement";
		
		//added code for populating tab 2 items
		List<ServiceElementDTO> listServiceElements = serviceElementMgmtService.getServiceElements();
		model.addAttribute("listServiceElements", listServiceElements);
		model.addAttribute("selectedTab", "TAB1");
		return "serviceJobRoleManagement";
	}

	@RequestMapping("/admin/addRole")
	public String addRole(ModelMap model, @ModelAttribute("jobRoleForm")JobRoleForm jobRoleForm , HttpSession session){
		logger.info("Opening Job Role Page");
		List<JobStageDTO> listJobStageDTO = new ArrayList<JobStageDTO>();
		listJobStageDTO = jobRoleMgmtService.getJobStageList();
		model.addAttribute("jobStageList", listJobStageDTO);

		// Creating String[] of all jobStageId's		
		String[] listJobStageId = null;
		jobRoleForm.setListJobStageId(listJobStageId);

		if(session.getAttribute(JOB_ROLE_ID) != null){
			logger.info("Selected Job Role Id is = "+session.getAttribute(JOB_ROLE_ID));
			Integer jobRoleID = Integer.parseInt((String) session.getAttribute(JOB_ROLE_ID));
			JobRoleDTO jobRoleDTO = new JobRoleDTO();			
			jobRoleDTO = jobRoleMgmtService.viewJobRole(jobRoleID);	
			jobRoleForm.setJobRoleDTO(jobRoleDTO);

			// Creating String[] of selected jobStageId's
			List<JobRoleStagesDTO> listSelectedJobRoleStagesDTO = jobRoleMgmtService.getJobRoleStagesByJobRoleId(jobRoleID);	
			String[] selectedJobStageId = new String[listSelectedJobRoleStagesDTO.size()];
			for(int i=0;i<listSelectedJobRoleStagesDTO.size();i++){
				selectedJobStageId[i] = Integer.toString(listSelectedJobRoleStagesDTO.get(i).getJobStageDTO().getJobStageID());
			}

			jobRoleForm.setListJobStageId(selectedJobStageId);
			
			logger.info("************selectedJobStageIdis = "+selectedJobStageId);
			model.addAttribute("selectedJobStageIdTemp", selectedJobStageId);

		}
		return "addJobRole";
	}

	@RequestMapping("/admin/saveJobRole")
	public String saveJobRole(ModelMap model, @ModelAttribute("jobRoleForm")JobRoleForm jobRoleForm,HttpSession session ) throws MySQLIntegrityConstraintViolationException {
		logger.info("Saving Job Role");		
		session.removeAttribute("exception");
		jobRoleMgmtService.saveJobRole(jobRoleForm.getJobRoleDTO(), jobRoleForm.getListJobStageId());		
		return "redirect:/admin/addRole";
	}
	
	/*
	 * Exception handler for MySQLIntegrityConstraintViolationException.
	 */
	@ExceptionHandler(DataAccessException.class)
	public String sqlExceptionHandler(HttpSession session,DataAccessException e){
		session.setAttribute("exception", e.getMessage());
		logger.info("*********Inside Exception Handler*************");			

		
		return "redirect:/admin/addRole";
	}

	@RequestMapping("/admin/editRole")
	public String editRole(ModelMap model,@ModelAttribute("jobRoleDTO")JobRoleDTO jobRoleDTO, HttpSession session){
		logger.info("Opening selected Job Role to edit");
		session.setAttribute(JOB_ROLE_ID, jobRoleDTO.getSelected());
		return "redirect:/admin/addRole";
	}
	
	
	
	// <-----------------------------------Second controller functions starts here ------------------------>

	
	
	@Autowired
	IServiceElementManagementService serviceElementMgmtService;
	
		
	@RequestMapping("/admin/serviceElementManagement")
	@ResponseBody
	public String serviceElementManagement(ModelMap model, @ModelAttribute("serviceElementManagementForm") ServiceElementManagementForm serviceElementMgmtForm, HttpSession session, HttpServletRequest req,@ModelAttribute("jobRoleDTO")JobRoleDTO jobRoleDTO){
		logger.info("Running controller for Service element management");
		logger.info("Selected Service Element is : "+serviceElementMgmtForm.getServiceElementId());
		
		
		return "serviceJobRoleManagement";
	}
	
	
	//AJAX call as an option
/*	@RequestMapping("/admin/showAlreadyMappedRoles")
	@ResponseBody
	public String showAlreadyMappedJobRolesByServEleId(ModelMap model, @RequestParam("serviceElementID")Integer serviceElementID){
		
		logger.info("Showing Already mapped job roles.");
		
		String resp = "";
			
		List<ServiceElementJobRoleStagesDTO> listServEleJobRoleStages = serviceElementMgmtService.getServiceElementJobRoleStagesByServEleId(serviceElementID);
		
		
		if(listServEleJobRoleStages.size() > 0){
			
			for(ServiceElementJobRoleStagesDTO dto : listServEleJobRoleStages){
				resp += dto.getJobRoleStages().getJobRole().getRole()+"-"+dto.getJobRoleStages().getJobStage().getStage()+"-"+dto.getOnshoreLocalDefault()+"-"+dto.getOnshoreGSCDefault()+"-"+dto.getOnshore3PPDefault()+"-"+dto.getNearshoreDefault()+"-"+dto.getOffshoreDefault()+"|";
			}
			logger.info("*********************"+resp);
		}else{
			resp = "No Entries for the selected Service Element";
		}
		
		return resp;
		
	}*/
	
	@RequestMapping("/admin/showAlreadyMappedRoles")
	public String showAlreadyMappedJobRolesByServEleId(ModelMap model, @ModelAttribute("serviceElementManagementForm") ServiceElementManagementForm serviceElementMgmtForm,@ModelAttribute("jobRoleDTO")JobRoleDTO jobRoleDTO, HttpSession session,HttpServletRequest req){
		
		logger.info("Showing Already mapped job roles.");
		logger.info("Selected Service Element is : "+serviceElementMgmtForm.getServiceElementId());
		
		// if service element is not received from form (case when the control returns back to jsp after adding a new Job Role Stage),  
		// get it from session.
		Integer serviceElementID = null;
		if(serviceElementMgmtForm.getServiceElementId() == null){
			serviceElementID = (Integer) getSessionValueFromKey(session, "serviceElement");
			serviceElementMgmtForm.setServiceElementId(serviceElementID);
		}else{
			serviceElementID =  serviceElementMgmtForm.getServiceElementId();
		}
		
		
		// listing already mapped Job Roles and Job Role Stages.		
		List<ServiceElementJobRoleStagesDTO> listServEleJobRoleStages = serviceElementMgmtService.getServiceElementJobRoleStagesByServEleId(serviceElementID);
		
		serviceElementMgmtForm.setListServiceElementJobRoleStagesDTO(listServEleJobRoleStages);				
		model.addAttribute("serviceElementManagementForm",serviceElementMgmtForm);
		
		//setting serviceElement id in session for next page. 
		setSessionValueByKey(session, "serviceElement", serviceElementID);
		setSessionValueByKey(session, "listServEleJobRoleStages", listServEleJobRoleStages);
		
		// setting value for Add Job Role Button
		if(serviceElementID != null && serviceElementID != -1){
		model.addAttribute("isServiceElementSelected", "true");
		}
		//
		List<ServiceElementDTO> listServiceElements = serviceElementMgmtService.getServiceElements();
		model.addAttribute("listServiceElements", listServiceElements);
		model.addAttribute("selectedTab", "TAB2");

		return "serviceJobRoleManagement";
	}
	
	
	@RequestMapping("/admin/addJobRoleToServiceElement")
	public String addJobRoleToServiceElement(ModelMap model, @ModelAttribute("serviceElementJobRoleStagesDTO") ServiceElementJobRoleStagesDTO serviceElementJobRoleStagesDTO, HttpSession session){
		logger.info("Add Job Role to Service Element");
		List<ServiceElementJobRoleStagesDTO> listServEleJobRoleStages = (List<ServiceElementJobRoleStagesDTO>) session.getAttribute("listServEleJobRoleStages");
		
		// get Already Mapped JobRoleStageId's
		List<Integer> alreadyMappedJobRoleStageIds = new ArrayList<Integer>();
		for(ServiceElementJobRoleStagesDTO dto : listServEleJobRoleStages){
			alreadyMappedJobRoleStageIds.add(dto.getJobRoleStages().getJobRoleStagesId());
		}
		//listing only those job Role Stages that are not already mapped.
		List<JobRoleStagesDTO> listJobRoleStages =  serviceElementMgmtService.getAvailableJobRoleStages(alreadyMappedJobRoleStageIds);
		
		model.addAttribute("listJobRoleStages", listJobRoleStages);
		return "addJobRoleToServiceElement";
	}
	
	@RequestMapping("/admin/saveServiceElementJobRoleMapping")
	public String saveServiceEleJobRoleMapping(ModelMap model, @ModelAttribute("serviceElementJobRoleStagesDTO") ServiceElementJobRoleStagesDTO serviceElementJobRoleStagesDTO, HttpSession session){
		logger.info("Save ServiceElement Job Role Mapping");
		serviceElementMgmtService.saveServiceElementJobRoleMapping(serviceElementJobRoleStagesDTO);
		
		return "forward:/admin/showAlreadyMappedRoles";
	}
	
	@RequestMapping("/admin/editServiceElementJobRoleStage")
	public String editServiceElementJobRoleStage(ModelMap map, @ModelAttribute("serviceElementManagementForm") ServiceElementManagementForm form){
		logger.info("Edit Service Element Job Role Stage");
		serviceElementMgmtService.updateServiceElementJobRoleMapping(form.getListServiceElementJobRoleStagesDTO().get(form.getSelected()));
		
		
		return "forward:/admin/showAlreadyMappedRoles";
	}

}
