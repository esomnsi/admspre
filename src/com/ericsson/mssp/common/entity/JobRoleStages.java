package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "JobRoleStages")
public class JobRoleStages implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5045317701525514694L;
	private Integer jobRoleStagesId;
	private JobRole jobRole;
	private JobStage jobStage;
	
	public JobRoleStages(Integer jobRoleStagesId, JobRole jobRole, JobStage jobStage, Set<FTEStaging> ftestagings){
		this.jobRoleStagesId = jobRoleStagesId;
		this.jobRole = jobRole;
		this.jobStage = jobStage;
		
	}
	
	public JobRoleStages(){
		
	}
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "JobRoleStageID", unique = true, nullable = false)
	public Integer getJobRoleStagesId() {
		return jobRoleStagesId;
	}
	public void setJobRoleStagesId(Integer jobRoleStageID) {
		this.jobRoleStagesId = jobRoleStageID;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JobRoleID", nullable = false)
	public JobRole getJobRole() {
		return jobRole;
	}
	public void setJobRole(JobRole jobRole) {
		this.jobRole = jobRole;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JobStageID", nullable = false)
	public JobStage getJobStage() {
		return jobStage;
	}
	public void setJobStage(JobStage jobStage) {
		this.jobStage = jobStage;
	}
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "jobRoleStageID")
	public Set<FTEStaging> getFtestagings() {
		return this.ftestagings;
	}

	public void setFtestagings(Set<FTEStaging> ftestagings) {
		this.ftestagings = ftestagings;
	}*/
}
