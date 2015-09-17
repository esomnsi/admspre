package com.ericsson.mssp.approval.controller;


import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.ericsson.mssp.approval.service.IApprovalService;
import com.ericsson.mssp.common.exception.ApprovalStatusException;
import com.ericsson.mssp.approval.forms.ADRReport;
import com.ericsson.mssp.approval.forms.ApprovalDetails;
import com.ericsson.mssp.approval.forms.CheckListElements;
import com.ericsson.mssp.approval.forms.ExecSummary;
import com.ericsson.mssp.approval.forms.ServiceFTEReport;
import com.ericsson.mssp.approval.forms.SolutionSummary;
import com.ericsson.mssp.approval.forms.TNTReport;

import com.ericsson.mssp.common.dto.CountryDTO;
import com.ericsson.mssp.common.dto.CustomerDTO;
import com.ericsson.mssp.common.dto.InitiateApprovalProcessDTO;
import com.ericsson.mssp.common.dto.NotificationDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.OpportunityDetailDTO;

import com.ericsson.mssp.common.dto.SearchDTO;
import com.ericsson.mssp.common.dto.UserAccessDTO;
import com.ericsson.mssp.common.dto.UserDTO;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionADR;

import com.ericsson.mssp.common.entity.SolutionApprover;
import com.ericsson.mssp.common.exception.MSSPException;

import com.ericsson.mssp.login.service.IManageUserAccessService;
import com.ericsson.mssp.notification.service.INotificationService;
import com.ericsson.mssp.search.service.ISearchService;
import com.ericsson.mssp.solution.controller.BaseController;

import com.ericsson.mssp.solution.service.ISolutionService;

import com.ericsson.mssp.approval.createpdf.CreatePDF;

@Controller
public class ApprovalController extends BaseController{
	
	
	  
	public static Logger logger = Logger.getLogger(ApprovalController.class);
	

	private CreatePDF createPDF;
	
	@Autowired
	private IApprovalService approvalService;
	
	@Autowired
	private ISolutionService solutionService;
	
	@Autowired
	private IManageUserAccessService userAccessService;
	
	@Autowired
	private INotificationService notificationService;
	
	@Autowired
	ISearchService searchService;
	
	@RequestMapping(value = "/approval/loadApproverPage")
	public String loadApproverPage(ModelMap model,HttpServletRequest request,HttpSession session) {
		Integer solId = getSessionSolutionId(session);
		String loggedInUser=getSessionValueFromKey(session, USER_NAME)!=null?getSessionValueFromKey(session, USER_NAME).toString():null;
		Object changeApproverList=getSessionValueFromKey(session, APPROVER_LIST_CHANGE);
		logger.info("Entered ApprovalController[loadApproverPage] User:"+loggedInUser+"SolutionId:"+solId);
		try{
		List<UserDTO> allApprovers=solutionService.getRepresentatives();
		List<SolutionApprover> existingApprovers=approvalService.retrieveExistingApproversForSolution(solId);
		List<UserDTO> availableApprovers=new ArrayList<UserDTO>();
		boolean selected=false;
		for(int i=0;i<allApprovers.size();i++){
			selected=false;
			for(int j=0;j<existingApprovers.size();j++){
				if(allApprovers.get(i).getSignumId().equals(existingApprovers.get(j).getApprover())){
					selected=true;
					break;
				}
			}
			if(!selected){
				availableApprovers.add(allApprovers.get(i));
			}
		}
		int solutionStatus=approvalService.retrieveSolutionStatus(solId);
		CheckListElements elements=approvalService.retrieveCheckListElements(solId);
		Map<Integer,String> checkListValues = new LinkedHashMap<Integer,String>();
		checkListValues.put(1, "Yes");
		checkListValues.put(0, "No");
		checkListValues.put(2, "NA");
		
		
		ApprovalDetails approvalDetails=new ApprovalDetails();
		approvalDetails.setSolutionStatus(solutionStatus);
		approvalDetails.setCheckListElements(elements);
		model.addAttribute("availableApprovers", availableApprovers);
		model.addAttribute("existingApprovers",existingApprovers);
		model.addAttribute("checkListValues",checkListValues);
		model.addAttribute("approvalDetails", approvalDetails);
		model.addAttribute("changeApproverList", changeApproverList);
		}catch(Exception e){
			logger.error("Exception ApprovalController[loadApproverPage] User:"+loggedInUser+"SolutionId:"+solId,e);
			model.addAttribute("message","There has been an error");
		}
		logger.info("Exit ApprovalController[loadApproverPage] User:"+loggedInUser+"SolutionId:"+solId);
		return ("saveApprovers");
    }
	
	@RequestMapping(value = "/approval/submitSolution")
	public String submitSolution(Model model, @ModelAttribute("approvalDetails") ApprovalDetails approvalDetails, HttpSession session) {
		Integer solId = getSessionSolutionId(session);
		String loggedInUser=getSessionValueFromKey(session, USER_NAME)!=null?getSessionValueFromKey(session, USER_NAME).toString():null;
		Object changeApproverList=getSessionValueFromKey(session, APPROVER_LIST_CHANGE);
		logger.info("Entered ApprovalController[submitSolution] User:"+loggedInUser+"SolutionId:"+solId);
		session.removeAttribute(APPROVER_LIST_CHANGE);
		approvalDetails.setSolutionId(solId);
		String successMessage="",errorMessage="",msg="";
		NotificationDTO notificationDTO=null;
		if(changeApproverList==(Object)true){
			approvalDetails.setApproverListChangeInitiated(true);
			setSessionSolutionId(session, null);
		}
		try{
			notificationDTO=approvalService.submitSolution(approvalDetails);
		   //signum=approvalService.identifyMailReceiver(solId);
		 String emailId=null;
		 UserAccessDTO dto=new UserAccessDTO();
		 dto.setSignumId(notificationDTO.getNextApprover());
		 List<UserAccessDTO>searchResults = userAccessService.searchLDAPUsers(dto,null);
		 if(searchResults!=null && searchResults.size()!=0){
			 emailId=searchResults.get(0).getEmailId();
			 notificationDTO.setRecipientEmailId(emailId);
			 notificationService.sendNotification(notificationDTO);
			 successMessage="Solution submitted successfully and approval request mail sent to "+notificationDTO.getNextApprover();
		 }else{
			 logger.info("ApprovalController[submitSolution] User:"+loggedInUser+"SolutionId:"+solId+"LDAP authentication failed for Signum :"+dto.getSignumId() );
			 successMessage="Solution submitted successfully.";
			 errorMessage="But Error in sending email to "+dto.getSignumId();
		 }
		 //notificationService.sendNotification(emailId,INotificationService.NOTIFICATION_TYPE_APPROVED,solId);
		 
		}catch(ApprovalStatusException e){
			logger.error("Exception ApprovalController[submitSolution] User:"+loggedInUser+"SolutionId:"+solId,e);
			errorMessage="Error in submitting solution";
		}catch(Exception e){
			logger.error("Exception ApprovalController[submitSolution] User:"+loggedInUser+"SolutionId:"+solId,e);
			successMessage="Solution submitted successfully";
			errorMessage="Error in sending email";
		}
		model.addAttribute("successMessage",successMessage);
		model.addAttribute("errorMessage",errorMessage);
		logger.info("Exit ApprovalController[submitSolution] User:"+loggedInUser+"SolutionId:"+solId);
		//return("forward:../approval/loadApproverPage");
		return("redirectToHome");
	}
	
	
	
	@RequestMapping(value = "/approval/approveRequest")
	public String approveRequest (Model model, @ModelAttribute("approvalDetails") ApprovalDetails approvalDetails,HttpSession session,HttpServletRequest request) {
		 
		 String loggedInUser=getSessionValueFromKey(session, USER_NAME)!=null?getSessionValueFromKey(session, USER_NAME).toString():null;
		 String currentAssignedapprover =request.getParameter("currentApproverId")!=null? request.getParameter("currentApproverId"):null;
		 System.out.println(currentAssignedapprover);
		 String selectedRole = (String)session.getAttribute("ROLE");
		 Integer solId = approvalDetails.getSolutionId();
		 logger.info("Entered ApprovalController[approveRequest] User:"+loggedInUser+"SolutionId:"+solId);
		 
		 String successMessage="",errorMessage="";
		 NotificationDTO notificationDTO=null;
		 try{

			 notificationDTO=approvalService.approveRequest(solId,loggedInUser,selectedRole,currentAssignedapprover,approvalDetails.getComments());

			// notificationDTO=approvalService.approveRequest(solId,loggedInUser,approvalDetails.getComments());

			 UserAccessDTO dto=new UserAccessDTO();
			 if(INotificationService.NOTIFICATION_TYPE_APPROVAL_REQUEST.equals(notificationDTO.getNotificationType())){
				 dto.setSignumId(notificationDTO.getNextApprover());
				 
			 }else if(INotificationService.NOTIFICATION_TYPE_APPROVED.equals(notificationDTO.getNotificationType())){
				 dto.setSignumId(notificationDTO.getSolutionCreator());
			 }else if(INotificationService.NOTIFICATION_TYPE_WAITING_PREVIOUS_APPROVAL.equals(notificationDTO.getNotificationType())){
				 errorMessage="Previous Level approval not done, please visit again.";
				 model.addAttribute("errorMessage",errorMessage);
				 logger.info("Exit ApprovalController[approveRequest] User:"+loggedInUser+"SolutionId:"+solId);				
				return("redirectToHome");
			 }
			 List<UserAccessDTO> searchResults = userAccessService.searchLDAPUsers(dto,null);
			 if(searchResults!=null && searchResults.size()!=0){
				String  emailId=searchResults.get(0).getEmailId();
				notificationDTO.setRecipientEmailId(emailId);
				notificationService.sendNotification(notificationDTO);
				successMessage="Solution approved successfully and mail sent to :"+dto.getSignumId();
			 }else{
				 logger.info("ApprovalController[approveRequest] User:"+loggedInUser+"SolutionId:"+solId+"LDAP authentication failed for Signum :"+dto.getSignumId() );
				 successMessage="Solution submitted successfully.";
				 errorMessage="But Error in sending email to "+dto.getSignumId();
			 }
		/* if(nextApprover!=null){
			 String emailId=null;
			 UserAccessDTO dto=new UserAccessDTO();
			 dto.setSignumId(nextApprover);
			 List<UserAccessDTO>searchResults = userAccessService.searchLDAPUsers(dto);
			 if(searchResults!=null && searchResults.size()!=0){
				 emailId=searchResults.get(0).getEmailId();
			 }
			 notificationService.sendNotification(emailId,INotificationService.NOTIFICATION_TYPE_APPROVED,solId);
			 msg+=". Approval request email sent to next approver "+nextApprover;
		 }*/
		}catch(ApprovalStatusException  e){
			logger.error("Exception ApprovalController[approveRequest] User:"+loggedInUser+"SolutionId:"+solId,e);
			errorMessage="Error in approve request. Please try later.";
			successMessage="";
			logger.error(errorMessage,e);
		}catch(Exception e){
			logger.error("Exception ApprovalController[approveRequest] User:"+loggedInUser+"SolutionId:"+solId,e);
			successMessage="Solution submitted successfully.";
			errorMessage="But Error in sending email.";
		}
		 model.addAttribute("successMessage",successMessage);
		 model.addAttribute("errorMessage",errorMessage);
		 logger.info("Exit ApprovalController[approveRequest] User:"+loggedInUser+"SolutionId:"+solId);
		//return("forward:../approval/approvalDetails");
		return("redirectToHome");
	}
	
	@RequestMapping(value = "/approval/rejectRequest")
	public String rejectRequet (Model model,@ModelAttribute("approvalDetails") ApprovalDetails approvalDetails, HttpSession session,HttpServletRequest request) {
		 String loggedInUser=getSessionValueFromKey(session, USER_NAME)!=null?getSessionValueFromKey(session, USER_NAME).toString():null;
		 String currentAssignedapprover =request.getParameter("currentApproverId")!=null? request.getParameter("currentApproverId"):null;
		 System.out.println(currentAssignedapprover);
		 String selectedRole = (String)session.getAttribute("ROLE");
		 Integer solId = approvalDetails.getSolutionId();		 
		 logger.info("Entered ApprovalController[rejectRequet] User:"+loggedInUser+"SolutionId:"+solId);
		 String successMessage="",errorMessage="";
		 NotificationDTO notificationDTO=null;
		try{

			notificationDTO= approvalService.rejectRequest(solId,loggedInUser,selectedRole,currentAssignedapprover,approvalDetails.getComments());

			//notificationDTO= approvalService.rejectRequest(solId,loggedInUser,approvalDetails.getComments());

			 UserAccessDTO dto=new UserAccessDTO();
			 dto.setSignumId(notificationDTO.getSolutionCreator());
			 List<UserAccessDTO>searchResults = userAccessService.searchLDAPUsers(dto,null);
			 if(searchResults!=null && searchResults.size()!=0){
				 String emailId=searchResults.get(0).getEmailId();
				 notificationDTO.setRecipientEmailId(emailId);
				 notificationService.sendNotification(notificationDTO);
				 successMessage="Solution rejected Successfully and mail sent to :"+dto.getSignumId();
			 }else{
				 logger.info("ApprovalController[rejectRequet] User:"+loggedInUser+"SolutionId:"+solId+"LDAP authentication failed for Signum :"+dto.getSignumId() );
				 successMessage="Solution submitted successfully.";
				 errorMessage="But Error in sending email to "+dto.getSignumId();
			 }
			/*if(solutionCreatedBy!=null){
				 String emailId=null;
				 UserAccessDTO dto=new UserAccessDTO();
				 dto.setSignumId(solutionCreatedBy);
				 List<UserAccessDTO>searchResults = userAccessService.searchLDAPUsers(dto);
				 if(searchResults!=null && searchResults.size()!=0){
					 emailId=searchResults.get(0).getEmailId();
				 }
				 notificationService.sendNotification(emailId,INotificationService.NOTIFICATION_TYPE_REJECTED,solId);
				 msg+=".Email sent to Solution Creator "+solutionCreatedBy;
			 }*/
		}catch(ApprovalStatusException e){
			logger.error("Exception ApprovalController[rejectRequest] User:"+loggedInUser+"SolutionId:"+solId,e);
			errorMessage="Error in reject request. Please try later.";
			successMessage="";
		}catch(Exception e){
			logger.error("Exception ApprovalController[rejectRequest] User:"+loggedInUser+"SolutionId:"+solId,e);
			successMessage="Solution submitted successfully.";
			errorMessage="But Error in sending email.";
		}
		model.addAttribute("successMessage",successMessage);
		model.addAttribute("errorMessage",errorMessage);
		logger.info("Exit ApprovalController[rejectRequet] User:"+loggedInUser+"SolutionId:"+solId);
		//return("forward:../approval/approvalDetails");
		return("redirectToHome");
	}
	
	@RequestMapping(value="/approval/approvalDetails")
	public String approvalDetails(ModelMap model,@ModelAttribute("approvalDetails") ApprovalDetails approvalDetails,HttpServletRequest request ,HttpSession session, HttpServletResponse response) throws Exception
	{
		String loggedInUser=getSessionValueFromKey(session, USER_NAME)!=null?getSessionValueFromKey(session, USER_NAME).toString():null;
		Integer solId=request.getParameter("myActionSolutionId")!=null? Integer.parseInt((String)request.getParameter("myActionSolutionId")):null;
		Integer oppId=request.getParameter("myActionOpportunityId")!=null?Integer.parseInt((String)request.getParameter("myActionOpportunityId")):null;
		logger.info("Entered ApprovalController[approvalDetails] User:"+loggedInUser+"SolutionId:"+solId+"OpportunityID:"+oppId);
		String selectedRole = (String)session.getAttribute("ROLE");
		List<SolutionApprover> solAppList = null;
		boolean adminProcessForApproval = false;
		try{
		if(solId!=null){
		boolean validate=false;
		if(!"ROLE_SUPER_USER".equalsIgnoreCase(selectedRole)){
			List<InitiateApprovalProcessDTO> pendingApprovalList=approvalService.inbox(loggedInUser);
			for(int i=0;i<pendingApprovalList.size();i++){
				if(new Integer(pendingApprovalList.get(i).getSolutionId()).equals(solId)){
					validate=true;
					break;
				}
			}
		}else{
			validate = true;
			adminProcessForApproval = true;
			solAppList =  approvalService.getAllApproversForThissolution(solId);
		}
		
		
		if(validate){
			//boolean isCreatePDF = false;
			Map<String,String> vertical = getVertical();
			OpportunityDetailDTO opportunityDetailDTO = new OpportunityDetailDTO();
			OpportunityDTO opportunityDTO = new OpportunityDTO();
			
			opportunityDTO = solutionService.getOpportunity(oppId);
			
			/*logger.info("opportunity created by : " + opportunityDTO.getCreatedBy());
			
			logger.info(
					 "currency name : "+opportunityDTO.getCustomerDTO().getCountryDTO().getCurrencyName()+"|"
					+"customer name : "+opportunityDTO.getCustomerDTO().getCustomerName()+"|"
					+"country name  : "+opportunityDTO.getCustomerDTO().getCountryDTO().getCountryName()
					+"created by : " + opportunityDetailDTO.getCreatedBy()
					);
			
			logger.info("created by : " + opportunityDetailDTO.getCreatedBy());
			opportunityDetailDTO.setOpportunityDTO(opportunityDTO);
			
			System.out.println("Client Name=");
			System.out.println("Owning GSC-I Vertica="+opportunityDTO.getOpportunityDetailsDTO().getVertical());
			System.out.println("Region="+opportunityDTO.getCustomerDTO().getCountryDTO().getRegion());
			System.out.println("Engagement Duration="+opportunityDTO.getOpportunityDetailsDTO().getContractDuration());
			System.out.println("Opportunity Name="+opportunityDTO.getOpportunityName());
			System.out.println("Opportunity Id="+opportunityDTO.getOpportunityId());*/
			ADRReport adrReport=approvalService.retrieveTop3ADR(solId);
			List<TNTReport> tntReportRows=approvalService.retrieveTNTReport(solId); 		
			List<UserDTO> representatives=solutionService.getRepresentatives();
			SolutionSummary solutionSummary=approvalService.retrieveSolutionSummaryReport(solId);
			List<ServiceFTEReport> sericeFTEList =approvalService.retrieveServiceFTEReport(solId);
			
			CheckListElements elements=approvalService.retrieveCheckListElements(solId);
			Map<Integer,String> checkListValues = new LinkedHashMap<Integer,String>();
			checkListValues.put(1, "Yes");
			checkListValues.put(0, "No");
			checkListValues.put(2, "NA");
			model.addAttribute("checkListValues",checkListValues);
			
			ExecSummary summary= approvalService.retrieveExeSummaryReport(solId);
			summary.setRegion(opportunityDTO.getCustomerDTO().getCountryDTO().getRegion());
			if("GBP".equalsIgnoreCase(opportunityDTO.getCustomerDTO().getCountryDTO().getCurrencyCode())||
					"INR".equalsIgnoreCase(opportunityDTO.getCustomerDTO().getCountryDTO().getCurrencyCode())||
					"USD".equalsIgnoreCase(opportunityDTO.getCustomerDTO().getCountryDTO().getCurrencyCode())){
					summary.setCurrency(opportunityDTO.getCustomerDTO().getCountryDTO().getCurrencyCode());
				}else{
					summary.setCurrency("USD");
				}
			
			ApprovalDetails appDetails=new ApprovalDetails();
			appDetails.setAllowAction(true);
			/*appDetails.setOpportunityDTO(opportunityDTO);
			appDetails.setAdrReport(adrReport);*/
			appDetails.setRepresentatives(representatives);
			appDetails.setSolutionId(solId);
			appDetails.setCheckListElements(elements);
			
			
			model.addAttribute("approvalDetails", appDetails);
			
			model.addAttribute("opportunityDTO", opportunityDTO);
			model.addAttribute("adrReport", adrReport);
			
			model.addAttribute("tntReportRows", tntReportRows);
			model.addAttribute("verticalList", vertical);
			model.addAttribute("representatives1",representatives);
			model.addAttribute("solAppList", solAppList);
			model.addAttribute("adminProcessForApproval", adminProcessForApproval);
			model.addAttribute("currentRole", selectedRole);
			model.addAttribute("solutionSummary", solutionSummary);
			model.addAttribute("sericeFTEList", sericeFTEList);
			model.addAttribute("execSummary", summary);
			
			model.put("myActionSolutionId", solId);
			model.put("myActionOpportunityId", oppId);
			
			/*String pdfTrigger = (String) request.getParameter("isCreatePDF");
			if("true".equals(pdfTrigger)){
				logger.info("Create PDF Trigger is true");
				logger.info("username = "+session.getAttribute(USER_NAME));
				String comments = approvalDetails.getComments();
				createPDF = new CreatePDF(request,response,session);
				createPDF.createPDFFile(opportunityDTO,solutionSummary,sericeFTEList,tntReportRows,adrReport,approvalDetails.getCheckListElements(),checkListValues,summary,comments);
				  
			}*/
			
		}else{
			ApprovalDetails appDetails=new ApprovalDetails();
			appDetails.setAllowAction(false);
			model.addAttribute("message", "The solution is not pending approval from the user");
		}
		}else{
			ApprovalDetails appDetails=new ApprovalDetails();
			appDetails.setAllowAction(false);
			model.addAttribute("message", "Please select a solution from MyAction");
		}
		}catch(Exception e){
			logger.error("Exception ApprovalController[approvalDetails] User:"+loggedInUser+"SolutionId:"+solId,e);
		}
		logger.info("Exit ApprovalController[approvalDetails] User:"+loggedInUser+"SolutionId:"+solId+"OpportunityID: "+oppId);
		return "approval";
	}
	
	@RequestMapping(value = "/approval/home")
	public String redirectToHome (Model model,HttpSession session) {
		return("redirect:/opportunity/landingPage");
	}
	
	private Map<String, String> getVertical()
	{
		Map<String,String> vertical = new LinkedHashMap<String,String>();
		vertical.put("ADMS", "ADMS");
		vertical.put("BSS", "BSS");
		vertical.put("OSS", "OSS");
		return vertical;
	}
	
	@ModelAttribute("regions")
	public Map<String,String> listCountries(Map<String, Object> map)
	{
		Map<String,String> a = new HashMap<String, String>();
		List<CountryDTO> regions = searchService.getRegions();
		for(int i = 0;i<regions.size();i++){
			
			if(!a.containsValue(regions.get(i).getRegion())){
			a.put(regions.get(i).getRegion(), regions.get(i).getRegion());
			}
	}
		return a;
	}
	
	@RequestMapping(value = "/admin/initApprovalChange")
	public String initApprovalChange(ModelMap model,HttpSession session){
		logger.info("Changing approver list.");
		String mes = "";
		logger.info("inside open solution");
		SearchDTO searchDTO = new SearchDTO();
		InitiateApprovalProcessDTO initiateApprovalProcessDTO = new InitiateApprovalProcessDTO();
		//InitiateApprovalProcessDTO iap = new InitiateApprovalProcessDTO();
		try {
			List<InitiateApprovalProcessDTO> allApprovalRequests = approvalService.getAllApprovalRequestsForAdmin();
			model.put("searchDTO", searchDTO);
			model.put("initiateApprovalProcessDTO", initiateApprovalProcessDTO);
			model.put("allApprovalRequests", allApprovalRequests);
		} catch (ApprovalStatusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "initApprovalChange";
	}
	
	
	@RequestMapping(value="/admin/listAllOpportunities")
	public String listOpportunities(ModelMap model,@ModelAttribute("searchDTO")SearchDTO searchDTO)
	{
		logger.info("inside list opportunities");
		logger.info("opportunity name : "
				+ searchDTO.getOpportunityName()+" | customer name : "
				+ searchDTO.getCustomerName()+ " | region : "
				+ searchDTO.getRegion()
				);
		
		if(("".equals(searchDTO.getOpportunityName())||(searchDTO.getOpportunityName() == null))
				&& ("".equals(searchDTO.getCustomerName())||(searchDTO.getCustomerName() == null))
				&& ("".equals(searchDTO.getRegion())||(searchDTO.getRegion() == null))
				){
			//message = "<ul><li>Please select either opportunity name or customer name or region</li></ul>";
			return "redirect:/admin/changeApprover";
		}
		
		List<SearchDTO> search = new ArrayList<SearchDTO>();
		List<SearchDTO> resultList = new ArrayList<SearchDTO>();
		
		search = searchService.getOpportunities(searchDTO);
		
		for(SearchDTO sDTO : search){
			logger.debug("Iterating SearchDTO for Solution Ids.");
			Integer solutionId = sDTO.getSolutionID();
			if(solutionId!=null){
				resultList.add(sDTO);
			}
		}
		logger.info("search list size : " + resultList.size());
		String mes = new String();
		mes = "";
		if(search.size() == 0)
		{
			mes = "<ul><li>No Result Found</li></ul>";
		}
		else
		{
			/*mes= message;
			message = "";*/
		}
		logger.info("message value set : " + mes);
		model.put("message", mes);
		model.put("searchList",resultList);
		return "initApprovalChange";
	}
	
	@RequestMapping(value="/admin/changeApprovers")
	public String updateCurrentApproverList(ModelMap model,HttpSession session,@ModelAttribute("searchDTO")SearchDTO searchDTO){
		if(searchDTO.getSelected() != null){
			String[] ids = searchDTO.getSelected().split("_");
			Integer solutionId = Integer.parseInt(ids[1]);
			logger.info("Updatin approver list for solutionID::: " + solutionId);
			setSessionSolutionId(session, solutionId);
			setSessionValueByKey(session, APPROVER_LIST_CHANGE, true);
		}
		
		return "redirect:/approval/loadApproverPage";
	}
	
	@RequestMapping(value="/approval/savePDF")
	public void saveDataAsPDF(HttpServletRequest request, HttpSession session , HttpServletResponse response){
		logger.info("Create Pdf Controller initiated.");
		String loggedInUser=getSessionValueFromKey(session, USER_NAME)!=null?getSessionValueFromKey(session, USER_NAME).toString():null;
		Integer solId=request.getParameter("myActionSolutionId")!=null? Integer.parseInt((String)request.getParameter("myActionSolutionId")):null;
		Integer oppId=request.getParameter("myActionOpportunityId")!=null?Integer.parseInt((String)request.getParameter("myActionOpportunityId")):null;
		
		logger.info("Entered ApprovalController[save PDF] User:"+loggedInUser+" SolutionId:"+solId+" OpportunityID:"+oppId);
		try{
		if(solId!=null){
			OpportunityDTO opportunityDTO = solutionService.getOpportunity(oppId);
			List<ServiceFTEReport> sericeFTEList =approvalService.retrieveServiceFTEReport(solId);
			
			ExecSummary summary= approvalService.retrieveExeSummaryReport(solId);
			summary.setRegion(opportunityDTO.getCustomerDTO().getCountryDTO().getRegion());
			if("GBP".equalsIgnoreCase(opportunityDTO.getCustomerDTO().getCountryDTO().getCurrencyCode())||
					"INR".equalsIgnoreCase(opportunityDTO.getCustomerDTO().getCountryDTO().getCurrencyCode())||
					"USD".equalsIgnoreCase(opportunityDTO.getCustomerDTO().getCountryDTO().getCurrencyCode())){
					summary.setCurrency(opportunityDTO.getCustomerDTO().getCountryDTO().getCurrencyCode());
				}else{
					summary.setCurrency("USD");
				}
			
				// Create PDF code.
				createPDF = new CreatePDF(request,response,session);
				createPDF.createPDFFile(opportunityDTO,null,sericeFTEList,null,null,null,null,summary,null);
		
		}
		}catch(Exception e){
			logger.error("Exception ApprovalController while saving PDF file. User:"+loggedInUser+" SolutionId:"+solId,e);
		}
		logger.info("Exit ApprovalController[save PDF] User:"+loggedInUser+" SolutionId:"+solId+" OpportunityID: "+oppId);	  	
		
	}
	
	
}