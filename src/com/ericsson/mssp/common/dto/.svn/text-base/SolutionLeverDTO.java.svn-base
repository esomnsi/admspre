/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  SolutionLeverDTO.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 12, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 12, 2012  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

import java.util.Date;
import java.util.List;

import org.springframework.util.AutoPopulatingList;

import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.LocationBreakup;
import com.ericsson.mssp.common.entity.LocationPyramid;
import com.ericsson.mssp.common.entity.OpportunityScope;

import com.ericsson.mssp.common.entity.ServiceElementJobRoleStages;
import com.ericsson.mssp.common.entity.SolutionLever;

/**
 * @author eruvwyn
 *
 */
public class SolutionLeverDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer solutionLeverId;
	
	public void setSolutionLeverId(Integer solutionLeverId) {
		this.solutionLeverId = solutionLeverId;
	}
	private String solutionLeverApproach;
	private Integer solutionId;
	
	private Date steadyStateStartDate;
	private Date steadyStateEndDate;
	
	//private Map<Long, Float> oppScopeFTE;
	private SolutionLever solutionLever;
	private List<OpportunityScope> oppScopeList;
	private List<JobRoleStages> jobRoleStagesList;
	
	private List<ServiceElementJobRoleStages> serEleJobRoleStagesList;
	
	private AutoPopulatingList<ProductivityLeverDTO> prodLeverList = null;
	private AutoPopulatingList<LocationBreakup> locBreakupList = null;
	
	private List<LocationPyramid> locPyramidList = null;
	private List<LocationPyramid> onLocPyramidList = null;
	private List<LocationPyramid> onLocalLocPyramidList = null;
	private List<LocationPyramid> onGSCiLocPyramidList = null;
	private List<LocationPyramid> on3PPLocPyramidList = null;
	private List<LocationPyramid> nearLocPyramidList = null;
	private List<LocationPyramid> offLocPyramidList = null;
	
	private List<LocationPyramid> onLocalLocListForSave = null;
	private List<LocationPyramid> onGSCiLocListForSave = null;
	private List<LocationPyramid> on3PPLocListForSave = null;
	private List<LocationPyramid> nearLocListForSave = null;
	private List<LocationPyramid> offLocListForSave = null;
	
	//
	private List <LocationBreakupDTO> locationBreakupDTOList= null;
	private List<ServiceElementJobRoleStagesDTO> serviceElementJobRoleStagesDTO = null;
	private AutoPopulatingList<LocationPyramidDTO> locationPyramidDTOList = null;
	//
	private Integer oppScopeId;
	private Integer serviceElementId;
	
	public Integer getSolutionId() {
		return solutionId;
	}
	public void setSolutionId(Integer solutionId) {
		this.solutionId = solutionId;
	}
	public Date getSteadyStateStartDate() {
		return steadyStateStartDate;
	}
	public void setSteadyStateStartDate(Date steadyStateStartDate) {
		this.steadyStateStartDate = steadyStateStartDate;
	}
	public Date getSteadyStateEndDate() {
		return steadyStateEndDate;
	}
	public void setSteadyStateEndDate(Date steadyStateEndDate) {
		this.steadyStateEndDate = steadyStateEndDate;
	}
	
	//added for new changes 
	
	public AutoPopulatingList<ProductivityLeverDTO> getProdLeverList() {
		return prodLeverList;
	}
	public void setProdLeverList(AutoPopulatingList<ProductivityLeverDTO> prodLeverList) {
		this.prodLeverList = prodLeverList;
	}
	
	/**
	 * @return the solutionLeverApproach
	 */
	public String getSolutionLeverApproach() {
		return solutionLeverApproach;
	}
	/**
	 * @param solutionLeverApproach the solutionLeverApproach to set
	 */
	public void setSolutionLeverApproach(String solutionLeverApproach) {
		this.solutionLeverApproach = solutionLeverApproach;
	}
	
	/**
	 * @return the solutionLeverId
	 */
	public Integer getSolutionLeverId() {
		return solutionLeverId;
	}
	/**
	 * @param solutionLeverId the solutionLeverId to set
	 */
	
	/*public Map<Long, Float> getOppScopeFTE() {
		return oppScopeFTE;
	}
	public void setOppScopeFTE(Map<Long, Float> oppScopeFTE) {
		this.oppScopeFTE = oppScopeFTE;
	}*/
	public SolutionLever getSolutionLever() {
		return solutionLever;
	}
	public void setSolutionLever(SolutionLever solutionLever) {
		this.solutionLever = solutionLever;
	}
	public List<OpportunityScope> getOppScopeList() {
		return oppScopeList;
	}
	public void setOppScopeList(List<OpportunityScope> oppScopeList) {
		this.oppScopeList = oppScopeList;
	}	
	public List<JobRoleStages> getJobRoleStagesList() {
		return jobRoleStagesList;
	}
	public void setJobRoleStagesList(List<JobRoleStages> jobRoleList) {
		this.jobRoleStagesList = jobRoleList;
	}
	/*public List<ProductivityLever> getProdLeverList() {
		return prodLeverList;
	}
	public void setProdLeverList(List<ProductivityLever> prodLeverList) {
		this.prodLeverList = prodLeverList;
	}*/	
	
	
	/*public List<LocationBreakup> getLocBreakupList() {
		return locBreakupList;
	}
	public void setLocBreakupList(List<LocationBreakup> locBreakupList) {
		this.locBreakupList = locBreakupList;
	}
	public List<LocationPyramid> getLocPyramidList() {
		return locPyramidList;
	}
	public void setLocPyramidList(List<LocationPyramid> locPyramidList) {
		this.locPyramidList = locPyramidList;
	}*/
	public AutoPopulatingList<LocationBreakup> getLocBreakupList() {
		return locBreakupList;
	}
	public void setLocBreakupList(AutoPopulatingList<LocationBreakup> locBreakupList) {
		this.locBreakupList = locBreakupList;
	}
	
	/**
	 * @return the oppScopeId
	 */
	public Integer getOppScopeId() {
		return oppScopeId;
	}
	/**
	 * @param oppScopeId the oppScopeId to set
	 */
	public void setOppScopeId(Integer oppScopeId) {
		this.oppScopeId = oppScopeId;
	}
	/**
	 * @return the onLocPyramidList
	 */
	public List<LocationPyramid> getOnLocPyramidList() {
		return onLocPyramidList;
	}
	/**
	 * @param onLocPyramidList the onLocPyramidList to set
	 */
	public void setOnLocPyramidList(
			List<LocationPyramid> onLocPyramidList) {
		this.onLocPyramidList = onLocPyramidList;
	}
	/**
	 * @return the offLocPyramidList
	 */
	public List<LocationPyramid> getOffLocPyramidList() {
		return offLocPyramidList;
	}
	/**
	 * @param offLocPyramidList the offLocPyramidList to set
	 */
	public void setOffLocPyramidList(
			List<LocationPyramid> offLocPyramidList) {
		this.offLocPyramidList = offLocPyramidList;
	}
	/**
	 * @return the locPyramidList
	 */
	public List<LocationPyramid> getLocPyramidList() {
		return locPyramidList;
	}
	/**
	 * @param locPyramidList the locPyramidList to set
	 */
	public void setLocPyramidList(List<LocationPyramid> locPyramidList) {
		this.locPyramidList = locPyramidList;
	}
	public Integer getServiceElementId() {
		return serviceElementId;
	}
	public void setServiceElementId(Integer serviceElementId) {
		this.serviceElementId = serviceElementId;
	}
	public List<LocationPyramid> getOnLocalLocPyramidList() {
		return onLocalLocPyramidList;
	}
	public void setOnLocalLocPyramidList(
			List<LocationPyramid> onLocalLocPyramidList) {
		this.onLocalLocPyramidList = onLocalLocPyramidList;
	}
	public List<LocationPyramid> getOnGSCiLocPyramidList() {
		return onGSCiLocPyramidList;
	}
	public void setOnGSCiLocPyramidList(
			List<LocationPyramid> onGSCiLocPyramidList) {
		this.onGSCiLocPyramidList = onGSCiLocPyramidList;
	}
	public List<LocationPyramid> getOn3PPLocPyramidList() {
		return on3PPLocPyramidList;
	}
	public void setOn3PPLocPyramidList(
			List<LocationPyramid> on3ppLocPyramidList) {
		on3PPLocPyramidList = on3ppLocPyramidList;
	}
	public List<LocationPyramid> getNearLocPyramidList() {
		return nearLocPyramidList;
	}
	public void setNearLocPyramidList(
			List<LocationPyramid> nearLocPyramidList) {
		this.nearLocPyramidList = nearLocPyramidList;
	}
	public List<LocationPyramid> getOnLocalLocListForSave() {
		return onLocalLocListForSave;
	}
	public void setOnLocalLocListForSave(List<LocationPyramid> onLocalLocListForSave) {
		this.onLocalLocListForSave = onLocalLocListForSave;
	}
	public List<LocationPyramid> getOnGSCiLocListForSave() {
		return onGSCiLocListForSave;
	}
	public void setOnGSCiLocListForSave(List<LocationPyramid> onGSCiLocListForSave) {
		this.onGSCiLocListForSave = onGSCiLocListForSave;
	}
	public List<LocationPyramid> getOn3PPLocListForSave() {
		return on3PPLocListForSave;
	}
	public void setOn3PPLocListForSave(List<LocationPyramid> on3ppLocListForSave) {
		on3PPLocListForSave = on3ppLocListForSave;
	}
	public List<LocationPyramid> getNearLocListForSave() {
		return nearLocListForSave;
	}
	public void setNearLocListForSave(List<LocationPyramid> nearLocListForSave) {
		this.nearLocListForSave = nearLocListForSave;
	}
	public List<LocationPyramid> getOffLocListForSave() {
		return offLocListForSave;
	}
	public void setOffLocListForSave(List<LocationPyramid> offLocListForSave) {
		this.offLocListForSave = offLocListForSave;
	}
	/**
	 * @return the serEleJobRoleStagesList
	 */
	public List<ServiceElementJobRoleStages> getSerEleJobRoleStagesList() {
		return serEleJobRoleStagesList;
	}
	/**
	 * @param serEleJobRoleStagesList the serEleJobRoleStagesList to set
	 */
	public void setSerEleJobRoleStagesList(
			List<ServiceElementJobRoleStages> serEleJobRoleStagesList) {
		this.serEleJobRoleStagesList = serEleJobRoleStagesList;
	}
	public List<LocationBreakupDTO> getLocationBreakupDTOList() {
		return locationBreakupDTOList;
	}
	public void setLocationBreakupDTOList(
			List<LocationBreakupDTO> locationBreakupDTOList) {
		this.locationBreakupDTOList = locationBreakupDTOList;
	}
	public List<ServiceElementJobRoleStagesDTO> getServiceElementJobRoleStagesDTO() {
		return serviceElementJobRoleStagesDTO;
	}
	public void setServiceElementJobRoleStagesDTO(
			List<ServiceElementJobRoleStagesDTO> serviceElementJobRoleStagesDTO) {
		this.serviceElementJobRoleStagesDTO = serviceElementJobRoleStagesDTO;
	}
	public AutoPopulatingList<LocationPyramidDTO> getLocationPyramidDTOList() {
		return locationPyramidDTOList;
	}
	public void setLocationPyramidDTOList(
			AutoPopulatingList<LocationPyramidDTO> locationPyramidDTOList) {
		this.locationPyramidDTOList = locationPyramidDTOList;
	}
	
	
	
}