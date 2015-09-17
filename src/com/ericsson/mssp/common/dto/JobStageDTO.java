package com.ericsson.mssp.common.dto;

public class JobStageDTO {

	private Integer 	jobStageID;
	private String 		stage;
	private boolean 	cCMFlag;
	
	public Integer getJobStageID() {
		return jobStageID;
	}
	public void setJobStageID(Integer jobStageID) {
		this.jobStageID = jobStageID;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public boolean iscCMFlag() {
		return cCMFlag;
	}
	public void setcCMFlag(boolean cCMFlag) {
		this.cCMFlag = cCMFlag;
	}
		
}
