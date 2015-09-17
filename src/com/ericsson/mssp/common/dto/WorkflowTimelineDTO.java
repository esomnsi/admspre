package com.ericsson.mssp.common.dto;

import java.util.Date;

import com.ericsson.mssp.common.entity.Opportunity;

public class WorkflowTimelineDTO {

	private Integer workflowTimelineId;
	private OpportunityDTO opportunityDto;
	private Date rfpreceiptDate;
	private Date questionnaireSubmissionDate;
	private Date solutionReviewDate;
	private Date submissionDate;
	private Date approvalDate;
	private String assignedTo;
	private String comments;
	private String rfpRecieveDate;
	private String qSubmissionDate;
	private String sReviewDate;
	private String sDate;
	private String aDate;
	
	public Integer getWorkflowTimelineId() {
		return workflowTimelineId;
	}
	public void setWorkflowTimelineId(Integer workflowTimelineId) {
		this.workflowTimelineId = workflowTimelineId;
	}
	
	public Date getRfpreceiptDate() {
		return rfpreceiptDate;
	}
	public void setRfpreceiptDate(Date rfpreceiptDate) {
		this.rfpreceiptDate = rfpreceiptDate;
	}
	public Date getQuestionnaireSubmissionDate() {
		return questionnaireSubmissionDate;
	}
	public void setQuestionnaireSubmissionDate(Date questionnaireSubmissionDate) {
		this.questionnaireSubmissionDate = questionnaireSubmissionDate;
	}
	public Date getSolutionReviewDate() {
		return solutionReviewDate;
	}
	public void setSolutionReviewDate(Date solutionReviewDate) {
		this.solutionReviewDate = solutionReviewDate;
	}
	public Date getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
	public Date getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public OpportunityDTO getOpportunityDto() {
		return opportunityDto;
	}
	public void setOpportunityDto(OpportunityDTO opportunityDto) {
		this.opportunityDto = opportunityDto;
	}
	public String getRfpRecieveDate() {
		return rfpRecieveDate;
	}
	public void setRfpRecieveDate(String rfpRecieveDate) {
		this.rfpRecieveDate = rfpRecieveDate;
	}
	public String getqSubmissionDate() {
		return qSubmissionDate;
	}
	public void setqSubmissionDate(String qSubmissionDate) {
		this.qSubmissionDate = qSubmissionDate;
	}
	public String getsReviewDate() {
		return sReviewDate;
	}
	public void setsReviewDate(String sReviewDate) {
		this.sReviewDate = sReviewDate;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String getaDate() {
		return aDate;
	}
	public void setaDate(String aDate) {
		this.aDate = aDate;
	}
}
