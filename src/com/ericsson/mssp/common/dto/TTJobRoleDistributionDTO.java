package com.ericsson.mssp.common.dto;

import java.util.Date;

import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.TTPlanning;

public class TTJobRoleDistributionDTO {

	private Integer ttjobRoleDistributionId;
    private TTPlanningDTO ttPlanningDTO;
    private JobRoleDTO jobRoleDTO;
    private Date weekDate;
    private Float distributionPc;
    private Float ftecount;
    
	public Integer getTtjobRoleDistributionId() {
		return ttjobRoleDistributionId;
	}
	public void setTtjobRoleDistributionId(Integer ttjobRoleDistributionId) {
		this.ttjobRoleDistributionId = ttjobRoleDistributionId;
	}
	public Date getWeekDate() {
		return weekDate;
	}
	public void setWeekDate(Date weekDate) {
		this.weekDate = weekDate;
	}
	public Float getDistributionPc() {
		return distributionPc;
	}
	public void setDistributionPc(Float distributionPc) {
		this.distributionPc = distributionPc;
	}
	public Float getFtecount() {
		return ftecount;
	}
	public void setFtecount(Float ftecount) {
		this.ftecount = ftecount;
	}
	public TTPlanningDTO getTtPlanningDTO() {
		return ttPlanningDTO;
	}
	public void setTtPlanningDTO(TTPlanningDTO ttPlanningDTO) {
		this.ttPlanningDTO = ttPlanningDTO;
	}
	public JobRoleDTO getJobRoleDTO() {
		return jobRoleDTO;
	}
	public void setJobRoleDTO(JobRoleDTO jobRoleDTO) {
		this.jobRoleDTO = jobRoleDTO;
	}
}
