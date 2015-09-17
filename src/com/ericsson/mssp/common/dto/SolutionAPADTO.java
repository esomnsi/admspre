/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  SolutionAPADTO.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 12, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 12, 2012  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

import java.util.List;

import com.ericsson.mssp.common.entity.Apacomplexity;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionAPA;
/**
 * @author eruvwyn
 *
 */
public class SolutionAPADTO extends BaseDTO {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SolutionAPAClone> solutionAPAList;
	public List<SolutionAPA> solutionAPAListCN;
	private Solution solution;
	private OpportunityScope opportunityScope;
	/**
	 * @return the solutionAPAList
	 */
	public List<SolutionAPAClone> getSolutionAPAList() {
		return solutionAPAList;
	}
	/**
	 * @param solutionAPAList the solutionAPAList to set
	 */
	public void setSolutionAPAList(List<SolutionAPAClone> solutionAPAList) {
		this.solutionAPAList = solutionAPAList;
	}
	/**
	 * @return the solutionAPAListCN
	 */
	public List<SolutionAPA> getSolutionAPAListCN() {
		return solutionAPAListCN;
	}
	/**
	 * @param solutionAPAListCN the solutionAPAListCN to set
	 */
	public void setSolutionAPAListCN(List<SolutionAPA> solutionAPAListCN) {
		this.solutionAPAListCN = solutionAPAListCN;
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
	 * @return the opportunityScope
	 */
	public OpportunityScope getOpportunityScope() {
		return opportunityScope;
	}
	/**
	 * @param opportunityScope the opportunityScope to set
	 */
	public void setOpportunityScope(OpportunityScope opportunityScope) {
		this.opportunityScope = opportunityScope;
	}

}
