package com.ericsson.mssp.approval.service;

import java.util.List;

import com.ericsson.mssp.approval.forms.ADRReport;
import com.ericsson.mssp.approval.forms.ApprovalDetails;
import com.ericsson.mssp.approval.forms.CheckListElements;
import com.ericsson.mssp.approval.forms.ExecSummary;
import com.ericsson.mssp.approval.forms.ServiceFTEReport;
import com.ericsson.mssp.approval.forms.SolutionSummary;
import com.ericsson.mssp.approval.forms.TNTReport;
import com.ericsson.mssp.common.dto.InitiateApprovalProcessDTO;
import com.ericsson.mssp.common.dto.NotificationDTO;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionADR;
import com.ericsson.mssp.common.entity.SolutionApprover;
import com.ericsson.mssp.common.exception.ApprovalStatusException;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;

public interface IApprovalService {
	
	public NotificationDTO submitSolution(ApprovalDetails  details) throws ApprovalStatusException;
	public String identifyMailReceiver(int SolutionId) throws DAOException;
	public NotificationDTO approveRequest(int solutionId,String approverSignum,String selectedRole,String currentAssignedapprover,String comments) throws ApprovalStatusException;
	public NotificationDTO rejectRequest(int solutionId,String approverSignum,String selectedRole,String currentAssignedapprover,String comments) throws ApprovalStatusException;
	public List<InitiateApprovalProcessDTO> inbox(String approverSignum) throws ApprovalStatusException;
	public List<InitiateApprovalProcessDTO> getAllApprovalRequestsForAdmin() throws ApprovalStatusException;
	public ADRReport retrieveTop3ADR(int solutionId) throws DAOException;
	public List<SolutionApprover> mailScheduler();
	public List<SolutionApprover> retrieveExistingApproversForSolution(int solutionId);
	public int retrieveSolutionStatus(int solutionId);
	public List<SolutionApprover> getAllApproversForThissolution(Integer solutionId);
	public CheckListElements retrieveCheckListElements(int solutionId);
	public List<TNTReport> retrieveTNTReport(int solutionId);
	public  SolutionSummary retrieveSolutionSummaryReport(int solutionId);
	public  List<ServiceFTEReport> retrieveServiceFTEReport(int solutionId);
	public ExecSummary retrieveExeSummaryReport(int solID);
	
}
