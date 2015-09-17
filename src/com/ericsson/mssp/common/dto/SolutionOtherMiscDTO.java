package com.ericsson.mssp.common.dto;

import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionApproachDimension;


public class SolutionOtherMiscDTO {
	
	 	private Integer solutionOtherMiscId;
	    private Solution solution;
	    private SolutionApproachDimension solutionApproachDimension;
	    private JobRoleDTO jobRoleDTO;
	    private Float fte;
	    private String skill;
	    private String remarks;
	    
		public Integer getSolutionOtherMiscId() {
			return solutionOtherMiscId;
		}
		public void setSolutionOtherMiscId(Integer solutionOtherMiscId) {
			this.solutionOtherMiscId = solutionOtherMiscId;
		}
		public Solution getSolution() {
			return solution;
		}
		public void setSolution(Solution solution) {
			this.solution = solution;
		}
		public SolutionApproachDimension getSolutionApproachDimension() {
			return solutionApproachDimension;
		}
		public void setSolutionApproachDimension(
				SolutionApproachDimension solutionApproachDimension) {
			this.solutionApproachDimension = solutionApproachDimension;
		}
		public Float getFte() {
			return fte;
		}
		public void setFte(Float fte) {
			this.fte = fte;
		}
		public String getSkill() {
			return skill;
		}
		public void setSkill(String skill) {
			this.skill = skill;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public JobRoleDTO getJobRoleDTO() {
			return jobRoleDTO;
		}
		public void setJobRoleDTO(JobRoleDTO jobRoleDTO) {
			this.jobRoleDTO = jobRoleDTO;
		}
}
