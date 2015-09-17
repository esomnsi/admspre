//package com.ericsson.mssp.admin.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.ericsson.mssp.common.dto.JobRoleDTO;
//import com.ericsson.mssp.common.dto.JobRoleStagesDTO;
//import com.ericsson.mssp.common.dto.JobStageDTO;
//import com.ericsson.mssp.common.entity.JobRoleStages;
//import com.ericsson.mssp.jobrole.management.form.JobRoleForm;
//import com.ericsson.mssp.jobrole.management.service.IJobRoleManagementService;
//import com.ericsson.mssp.solution.controller.BaseController;
//import com.ericsson.mssp.volumetric.controller.ADMSupportController;
//
//
//
//@Controller
//public class JobRoleManagementController extends BaseController{
//	
//	public static Logger logger = Logger.getLogger(JobRoleManagementController.class);
//	
//	@Autowired
//	IJobRoleManagementService jobRoleMgmtService;
//	
//	@RequestMapping("/admin/jobRoleManagement")
//	public String jobRoleManagement(ModelMap model, @ModelAttribute("jobRoleDTO") JobRoleDTO jobRoleDTO, HttpSession session){
//		logger.info("!!Welcome to Job Role Management!!");
//		String selectedTab = "jobRole";
//		model.put("selectedTab", selectedTab);
//		session.removeAttribute(JOB_ROLE_ID);		
//		//return "jobRoleManagement";
//		return "forward:/admin/listJobRoles";
//		
//	}
//	
//	@RequestMapping("/admin/listJobRoles")
//	public String listJobRoles(ModelMap model, @ModelAttribute("jobRoleDTO")JobRoleDTO jobRoleDTO){
//		logger.info("Listing Job Roles");
//		List<JobRoleDTO> list = jobRoleMgmtService.getJobRoles(jobRoleDTO);
//		model.addAttribute("jobRoleList",list);
//		return "jobRoleManagement";
//	}
//	
//	@RequestMapping("/admin/addRole")
//	public String addRole(ModelMap model, @ModelAttribute("jobRoleForm")JobRoleForm jobRoleForm , HttpSession session){
//		logger.info("Opening Job Role Page");
//		List<JobStageDTO> listJobStageDTO = new ArrayList<JobStageDTO>();
//		listJobStageDTO = jobRoleMgmtService.getJobStageList();
//		model.addAttribute("jobStageList", listJobStageDTO);
//		
//		// Creating String[] of all jobStageId's		
//		String[] listJobStageId = null;
//		jobRoleForm.setListJobStageId(listJobStageId);
//		
//		if(session.getAttribute(JOB_ROLE_ID) != null){
//			logger.info("Selected Job Role Id is = "+session.getAttribute(JOB_ROLE_ID));
//			Integer jobRoleID = Integer.parseInt((String) session.getAttribute(JOB_ROLE_ID));
//			JobRoleDTO jobRoleDTO = new JobRoleDTO();			
//			jobRoleDTO = jobRoleMgmtService.viewJobRole(jobRoleID);	
//			jobRoleForm.setJobRoleDTO(jobRoleDTO);
//			
//			// Creating String[] of selected jobStageId's
//			List<JobRoleStagesDTO> listSelectedJobRoleStagesDTO = jobRoleMgmtService.getJobRoleStagesByJobRoleId(jobRoleID);	
//			String[] selectedJobStageId = new String[listSelectedJobRoleStagesDTO.size()];
//			for(int i=0;i<listSelectedJobRoleStagesDTO.size();i++){
//				selectedJobStageId[i] = Integer.toString(listSelectedJobRoleStagesDTO.get(i).getJobStageDTO().getJobStageID());
//			}
//						
//			jobRoleForm.setListJobStageId(selectedJobStageId);
//		}
//		return "addJobRole";
//	}
//	
//	@RequestMapping("/admin/saveJobRole")
//	public String saveJobRole(ModelMap model, @ModelAttribute("jobRoleForm")JobRoleForm jobRoleForm){
//		logger.info("Saving Job Role");			
//		jobRoleMgmtService.saveJobRole(jobRoleForm.getJobRoleDTO(), jobRoleForm.getListJobStageId());		
//		return "redirect:/admin/addRole";
//	}
//	
//	@RequestMapping("/admin/editRole")
//	public String editRole(ModelMap model,@ModelAttribute("jobRoleDTO")JobRoleDTO jobRoleDTO, HttpSession session){
//		logger.info("Opening selected Job Role to edit");
//		session.setAttribute(JOB_ROLE_ID, jobRoleDTO.getSelected());
//		return "redirect:/admin/addRole";
//	}
//	
//	
//}
