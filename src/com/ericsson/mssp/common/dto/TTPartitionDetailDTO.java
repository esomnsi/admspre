package com.ericsson.mssp.common.dto;

import java.util.Date;

import com.ericsson.mssp.common.entity.TTPartitionName;

public class TTPartitionDetailDTO {

	private Integer ttpartitionDetailId;
    private TTPartitionNameDTO ttPartitionNameDTO;
    private Date planStartDate;
    private Date planEndDate;
    private Float planFtecount;
    private Date learnStartDate;
    private Date learnEndDate;
    private Float learnFtecount;
    private Date assistStartDate;
    private Date assistEndDate;
    private Float assistFtecount;
    private Date performStartDate;
    private Date performEndDate;
    private Float performFtecount;
    private Date deliverStartDate;
    private Date deliverEndDate;
    private Float deliverFtecount;
    
	public Integer getTtpartitionDetailId() {
		return ttpartitionDetailId;
	}
	public void setTtpartitionDetailId(Integer ttpartitionDetailId) {
		this.ttpartitionDetailId = ttpartitionDetailId;
	}
	public Date getPlanStartDate() {
		return planStartDate;
	}
	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}
	public Date getPlanEndDate() {
		return planEndDate;
	}
	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}
	public Float getPlanFtecount() {
		return planFtecount;
	}
	public void setPlanFtecount(Float planFtecount) {
		this.planFtecount = planFtecount;
	}
	public Date getLearnStartDate() {
		return learnStartDate;
	}
	public void setLearnStartDate(Date learnStartDate) {
		this.learnStartDate = learnStartDate;
	}
	public Date getLearnEndDate() {
		return learnEndDate;
	}
	public void setLearnEndDate(Date learnEndDate) {
		this.learnEndDate = learnEndDate;
	}
	public Float getLearnFtecount() {
		return learnFtecount;
	}
	public void setLearnFtecount(Float learnFtecount) {
		this.learnFtecount = learnFtecount;
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
	public Float getAssistFtecount() {
		return assistFtecount;
	}
	public void setAssistFtecount(Float assistFtecount) {
		this.assistFtecount = assistFtecount;
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
	public Float getPerformFtecount() {
		return performFtecount;
	}
	public void setPerformFtecount(Float performFtecount) {
		this.performFtecount = performFtecount;
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
	public Float getDeliverFtecount() {
		return deliverFtecount;
	}
	public void setDeliverFtecount(Float deliverFtecount) {
		this.deliverFtecount = deliverFtecount;
	}
	public TTPartitionNameDTO getTtPartitionNameDTO() {
		return ttPartitionNameDTO;
	}
	public void setTtPartitionNameDTO(TTPartitionNameDTO ttPartitionNameDTO) {
		this.ttPartitionNameDTO = ttPartitionNameDTO;
	}
}
