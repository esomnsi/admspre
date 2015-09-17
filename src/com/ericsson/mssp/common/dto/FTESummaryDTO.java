/**
 * 
 */
package com.ericsson.mssp.common.dto;

import java.util.Date;

import com.ericsson.mssp.common.entity.OpportunityScope;

/**
 * @author egaivij
 *
 */
public class FTESummaryDTO {

	
	   private static final long serialVersionUID = -1367707884512338924L;
	    private Long ftesummaryId;
	    private OpportunityScopeDTO opportunityScopeDTO;
	    private Integer solutionId;
	    private String location;
	    private Float ftecount;
	    private Date fromDate;
	    private Date toDate;
	    private Integer jobRoleStageId;
	    private boolean isSteadyStateType;
	    private String subLocation;
		/**
		 * @return the ftesummaryId
		 */
		public Long getFtesummaryId() {
			return ftesummaryId;
		}
		/**
		 * @param ftesummaryId the ftesummaryId to set
		 */
		public void setFtesummaryId(Long ftesummaryId) {
			this.ftesummaryId = ftesummaryId;
		}
		/**
		 * @return the opportunityScopeDTO
		 */
		public OpportunityScopeDTO getOpportunityScopeDTO() {
			return opportunityScopeDTO;
		}
		/**
		 * @param opportunityScopeDTO the opportunityScopeDTO to set
		 */
		public void setOpportunityScopeDTO(OpportunityScopeDTO opportunityScopeDTO) {
			this.opportunityScopeDTO = opportunityScopeDTO;
		}
		/**
		 * @return the solutionId
		 */
		public Integer getSolutionId() {
			return solutionId;
		}
		/**
		 * @param solutionId the solutionId to set
		 */
		public void setSolutionId(Integer solutionId) {
			this.solutionId = solutionId;
		}
		/**
		 * @return the location
		 */
		public String getLocation() {
			return location;
		}
		/**
		 * @param location the location to set
		 */
		public void setLocation(String location) {
			this.location = location;
		}
		/**
		 * @return the ftecount
		 */
		public Float getFtecount() {
			return ftecount;
		}
		/**
		 * @param ftecount the ftecount to set
		 */
		public void setFtecount(Float ftecount) {
			this.ftecount = ftecount;
		}
		/**
		 * @return the fromDate
		 */
		public Date getFromDate() {
			return fromDate;
		}
		/**
		 * @param fromDate the fromDate to set
		 */
		public void setFromDate(Date fromDate) {
			this.fromDate = fromDate;
		}
		/**
		 * @return the toDate
		 */
		public Date getToDate() {
			return toDate;
		}
		/**
		 * @param toDate the toDate to set
		 */
		public void setToDate(Date toDate) {
			this.toDate = toDate;
		}
		/**
		 * @return the jobRoleStageId
		 */
		public Integer getJobRoleStageId() {
			return jobRoleStageId;
		}
		/**
		 * @param jobRoleStageId the jobRoleStageId to set
		 */
		public void setJobRoleStageId(Integer jobRoleStageId) {
			this.jobRoleStageId = jobRoleStageId;
		}
		/**
		 * @return the isSteadyStateType
		 */
		public boolean isSteadyStateType() {
			return isSteadyStateType;
		}
		/**
		 * @param isSteadyStateType the isSteadyStateType to set
		 */
		public void setSteadyStateType(boolean isSteadyStateType) {
			this.isSteadyStateType = isSteadyStateType;
		}
		/**
		 * @return the subLocation
		 */
		public String getSubLocation() {
			return subLocation;
		}
		/**
		 * @param subLocation the subLocation to set
		 */
		public void setSubLocation(String subLocation) {
			this.subLocation = subLocation;
		}
	    
	    
	    
	    
}
