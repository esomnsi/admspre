/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  SolutionComplexityDTO.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 12, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 12, 2012  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

import java.util.HashSet;
import java.util.Set;

import com.ericsson.mssp.common.entity.Apacomplexity;
import com.ericsson.mssp.common.entity.Solution;

/**
 * @author eruvwyn
 *
 */
public class SolutionComplexityDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer solutionComplexityId;
	private Solution solution;
	private Short skillRating;
	private Short regionRating;
	private Short auditRating;
	private Short slarating;
	private Short skillWeightage;
	private Short regionWeightage;
	private Short auditWeightage;
	private Short slaweightage;
	private Short computedComplexity;
	private Short overriddenComplexity;
	private Float complexityAdjustor;
	private Float contingencyEffort;
	private Set<Apacomplexity> apacomplexities = new HashSet<Apacomplexity>(0);
	/**
	 * @return the solutionComplexityId
	 */
	public Integer getSolutionComplexityId() {
		return solutionComplexityId;
	}
	/**
	 * @param solutionComplexityId the solutionComplexityId to set
	 */
	public void setSolutionComplexityId(Integer solutionComplexityId) {
		this.solutionComplexityId = solutionComplexityId;
	}
	/**
	 * @return the solution
	 */
	public Solution getSolution() {
		return solution;
	}
	/**
	 * @param solution the solution to set
	 */
	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	/**
	 * @return the skillRating
	 */
	public Short getSkillRating() {
		return skillRating;
	}
	/**
	 * @param skillRating the skillRating to set
	 */
	public void setSkillRating(Short skillRating) {
		this.skillRating = skillRating;
	}
	/**
	 * @return the regionRating
	 */
	public Short getRegionRating() {
		return regionRating;
	}
	/**
	 * @param regionRating the regionRating to set
	 */
	public void setRegionRating(Short regionRating) {
		this.regionRating = regionRating;
	}
	/**
	 * @return the auditRating
	 */
	public Short getAuditRating() {
		return auditRating;
	}
	/**
	 * @param auditRating the auditRating to set
	 */
	public void setAuditRating(Short auditRating) {
		this.auditRating = auditRating;
	}
	/**
	 * @return the slarating
	 */
	public Short getSlarating() {
		return slarating;
	}
	/**
	 * @param slarating the slarating to set
	 */
	public void setSlarating(Short slarating) {
		this.slarating = slarating;
	}
	/**
	 * @return the skillWeightage
	 */
	public Short getSkillWeightage() {
		return skillWeightage;
	}
	/**
	 * @param skillWeightage the skillWeightage to set
	 */
	public void setSkillWeightage(Short skillWeightage) {
		this.skillWeightage = skillWeightage;
	}
	/**
	 * @return the regionWeightage
	 */
	public Short getRegionWeightage() {
		return regionWeightage;
	}
	/**
	 * @param regionWeightage the regionWeightage to set
	 */
	public void setRegionWeightage(Short regionWeightage) {
		this.regionWeightage = regionWeightage;
	}
	/**
	 * @return the auditWeightage
	 */
	public Short getAuditWeightage() {
		return auditWeightage;
	}
	/**
	 * @param auditWeightage the auditWeightage to set
	 */
	public void setAuditWeightage(Short auditWeightage) {
		this.auditWeightage = auditWeightage;
	}
	/**
	 * @return the slaweightage
	 */
	public Short getSlaweightage() {
		return slaweightage;
	}
	/**
	 * @param slaweightage the slaweightage to set
	 */
	public void setSlaweightage(Short slaweightage) {
		this.slaweightage = slaweightage;
	}
	/**
	 * @return the computedComplexity
	 */
	public Short getComputedComplexity() {
		return computedComplexity;
	}
	/**
	 * @param computedComplexity the computedComplexity to set
	 */
	public void setComputedComplexity(Short computedComplexity) {
		this.computedComplexity = computedComplexity;
	}
	/**
	 * @return the overriddenComplexity
	 */
	public Short getOverriddenComplexity() {
		return overriddenComplexity;
	}
	/**
	 * @param overriddenComplexity the overriddenComplexity to set
	 */
	public void setOverriddenComplexity(Short overriddenComplexity) {
		this.overriddenComplexity = overriddenComplexity;
	}
	/**
	 * @return the complexityAdjustor
	 */
	public Float getComplexityAdjustor() {
		return complexityAdjustor;
	}
	/**
	 * @param complexityAdjustor the complexityAdjustor to set
	 */
	public void setComplexityAdjustor(Float complexityAdjustor) {
		this.complexityAdjustor = complexityAdjustor;
	}
	/**
	 * @return the contingencyEffort
	 */
	public Float getContingencyEffort() {
		return contingencyEffort;
	}
	/**
	 * @param contingencyEffort the contingencyEffort to set
	 */
	public void setContingencyEffort(Float contingencyEffort) {
		this.contingencyEffort = contingencyEffort;
	}
	/**
	 * @return the apacomplexities
	 */
	public Set<Apacomplexity> getApacomplexities() {
		return apacomplexities;
	}
	/**
	 * @param apacomplexities the apacomplexities to set
	 */
	public void setApacomplexities(Set<Apacomplexity> apacomplexities) {
		this.apacomplexities = apacomplexities;
	}

}
