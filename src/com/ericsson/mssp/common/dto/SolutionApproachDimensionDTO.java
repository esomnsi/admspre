/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  SolutionApproachDimensionDTO.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 12, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 12, 2012  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ericsson.mssp.common.entity.Solution;


/**
 * @author eruvwyn
 *
 */
public class SolutionApproachDimensionDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer solutionApproachDimensionId;
	private Solution solution;
	private String dimensionName;
	private String dimensionAttributeName;
	private List<String> dimensionAttributes = new ArrayList<String>();
	
	
	
	/**
	 * @return the solutionApproachDimensionId
	 */
	public Integer getSolutionApproachDimensionId() {
		return solutionApproachDimensionId;
	}
	/**
	 * @param solutionApproachDimensionId the solutionApproachDimensionId to set
	 */
	public void setSolutionApproachDimensionId(Integer solutionApproachDimensionId) {
		this.solutionApproachDimensionId = solutionApproachDimensionId;
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
	 * @return the dimensionName
	 */
	public String getDimensionName() {
		return dimensionName;
	}
	/**
	 * @param dimensionName the dimensionName to set
	 */
	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}
	/**
	 * @return the dimensionAttributeName
	 */
	public String getDimensionAttributeName() {
		return dimensionAttributeName;
	}
	/**
	 * @param dimensionAttributeName the dimensionAttributeName to set
	 */
	public void setDimensionAttributeName(String dimensionAttributeName) {
		this.dimensionAttributeName = dimensionAttributeName;
	}
	
	/**
	 * @return the dimensionAttributes
	 */
	public List<String> getDimensionAttributes() {
		return dimensionAttributes;
	}
	/**
	 * @param dimensionAttributes the dimensionAttributes to set
	 */
	public void setDimensionAttributes(List<String> dimensionAttributes) {
		this.dimensionAttributes = dimensionAttributes;
	}
	

}
