package com.ericsson.mssp.approval.forms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TNTReport {
	 private String partitionName;
	 private Date overAllStartDate;
	 private Date overAllEndDate;
	 
	 private Date planningStartDate;
	 private Date planningEndDate;
	 private Float planningFtepercent;
	 
	 private Date LearnStartDate;
	 private Date LearnEndDate;
	 private Float LearnFtepercent;
	 
	 private Date assistStartDate;
	 private Date assistEndDate;
	 private Float assistFtepercent;
	 
	 private Date performStartDate;
	 private Date performEndDate;
	 private Float performFtepercent;
	 
	 private Date deliverStartDate;
	 private Date deliverEndDate;
	 private Float deliverFtepercent;
	public String getPartitionName() {
		return partitionName;
	}
	public void setPartitionName(String partitionName) {
		this.partitionName = partitionName;
	}
	public Date getOverAllStartDate() {
		return overAllStartDate;
	}
	public void setOverAllStartDate(Date overAllStartDate) {
		this.overAllStartDate = overAllStartDate;
	}
	public Date getOverAllEndDate() {
		return overAllEndDate;
	}
	public void setOverAllEndDate(Date overAllEndDate) {
		this.overAllEndDate = overAllEndDate;
	}
	public Date getPlanningStartDate() {
		return planningStartDate;
	}
	public void setPlanningStartDate(Date planningStartDate) {
		this.planningStartDate = planningStartDate;
	}
	public Date getPlanningEndDate() {
		return planningEndDate;
	}
	public void setPlanningEndDate(Date planningEndDate) {
		this.planningEndDate = planningEndDate;
	}
	public Float getPlanningFtepercent() {
		return planningFtepercent;
	}
	public void setPlanningFtepercent(Float planningFtepercent) {
		this.planningFtepercent = planningFtepercent;
	}
	public Date getLearnStartDate() {
		return LearnStartDate;
	}
	public void setLearnStartDate(Date learnStartDate) {
		LearnStartDate = learnStartDate;
	}
	public Date getLearnEndDate() {
		return LearnEndDate;
	}
	public void setLearnEndDate(Date learnEndDate) {
		LearnEndDate = learnEndDate;
	}
	public Float getLearnFtepercent() {
		return LearnFtepercent;
	}
	public void setLearnFtepercent(Float learnFtepercent) {
		LearnFtepercent = learnFtepercent;
	}
	
	public Date getAssistStartDate() {
		return assistStartDate;
	}
	public void setAssistStartDate(Date assistStartDate) {
		this.assistStartDate = assistStartDate;
	}
	public Date getAssistEndDate() {
		return assistEndDate;
	}
	public void setAssistEndDate(Date assistEndDate) {
		this.assistEndDate = assistEndDate;
	}
	public Float getAssistFtepercent() {
		return assistFtepercent;
	}
	public void setAssistFtepercent(Float assistFtepercent) {
		this.assistFtepercent = assistFtepercent;
	}
	public Date getPerformStartDate() {
		return performStartDate;
	}
	public void setPerformStartDate(Date performStartDate) {
		this.performStartDate = performStartDate;
	}
	public Date getPerformEndDate() {
		return performEndDate;
	}
	public void setPerformEndDate(Date performEndDate) {
		this.performEndDate = performEndDate;
	}
	public Float getPerformFtepercent() {
		return performFtepercent;
	}
	public void setPerformFtepercent(Float performFtepercent) {
		this.performFtepercent = performFtepercent;
	}
	public Date getDeliverStartDate() {
		return deliverStartDate;
	}
	public void setDeliverStartDate(Date deliverStartDate) {
		this.deliverStartDate = deliverStartDate;
	}
	public Date getDeliverEndDate() {
		return deliverEndDate;
	}
	public void setDeliverEndDate(Date deliverEndDate) {
		this.deliverEndDate = deliverEndDate;
	}
	public Float getDeliverFtepercent() {
		return deliverFtepercent;
	}
	public void setDeliverFtepercent(Float deliverFtepercent) {
		this.deliverFtepercent = deliverFtepercent;
	}
	
	private String durationWeeks;
	public String getDurationWeeks(){
		int planningWeeks= (int)Math.round(( (new Double(this.planningEndDate.getTime()) - new Double(this.planningStartDate.getTime())) / (1000 * 60 * 60 * 24*7)));
		int LearnWeeks= (int)Math.round((new Double(this.LearnEndDate.getTime()) - new Double(this.LearnStartDate.getTime())) / (1000 * 60 * 60 * 24*7));
		int assistWeeks= (int)Math.round((new Double(this.assistEndDate.getTime()) - new Double(this.assistStartDate.getTime())) / (1000 * 60 * 60 * 24*7));
		int performWeeks= (int)Math.round( (new Double(this.performEndDate.getTime()) - new Double(this.performStartDate.getTime())) / (1000 * 60 * 60 * 24*7));
		int deliverWeeks=(int)Math.round((new Double(this.deliverEndDate.getTime()) - new Double(this.deliverStartDate.getTime())) / (1000 * 60 * 60 * 24*7));
		int total=planningWeeks+LearnWeeks+assistWeeks+performWeeks+deliverWeeks;
		durationWeeks=""+planningWeeks+"-"+LearnWeeks+"-"+assistWeeks+"-"+performWeeks+"-"+deliverWeeks+"="+total;
		return this.durationWeeks;	
	}
	
	private String staffRampUp;
	public String getstaffRampUp(){
		//this.staffRampUp=""+planningFtepercent+"-"+LearnFtepercent+"-"+assistFtepercent+"-"+performFtepercent+"-"+deliverFtepercent;		
		this.staffRampUp=String.format("%.2f", planningFtepercent)+"-"+String.format("%.2f", LearnFtepercent)+"-"+String.format("%.2f", assistFtepercent)+"-"+String.format("%.2f", performFtepercent)+"-"+String.format("%.2f", deliverFtepercent);
		return this.staffRampUp;	
	}
	
	private String partitionDuration;
	public String getPartitionDuration(){
		String partitionStDate=formatDateToString(this.planningStartDate);
		String partitionEndDate=formatDateToString(this.deliverEndDate);
		this.partitionDuration=partitionStDate +" To "+partitionEndDate;
		return this.partitionDuration;
	}
	
	
	private String formatDateToString(Date date)
	{
		if(date != null)
		{
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.format(date);
		}
		else
		{
			return "";
		}
		
	}
}
