package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JobStage")
public class JobStage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7809146218428516785L;
	private Integer 	jobStageID;
	private String 		stage;
	private boolean 	cCMFlag;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "JobStageID", unique = true, nullable = false)
	public Integer getJobStageID() {
		return jobStageID;
	}
	public void setJobStageID(Integer jobStageID) {
		this.jobStageID = jobStageID;
	}
	
	@Column(name="Stage",length=50)
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	
	@Column(name="CCMFlag")
	public boolean isCCMFlag() {
		return cCMFlag;
	}
	public void setCCMFlag(boolean cCMFlag) {
		this.cCMFlag = cCMFlag;
	}
	
}
