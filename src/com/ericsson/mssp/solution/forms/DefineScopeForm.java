/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.forms
* File name       		    :  DefineScopeForm.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 27, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 27, 2012  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.forms;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ericsson.mssp.common.dto.ComponentDTO;
import com.ericsson.mssp.common.dto.OpportunityComponentDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.SolutionDTO;
import com.ericsson.mssp.common.entity.Component;

/**
 * @author egaivij
 *
 */
public class DefineScopeForm {
	
	private SolutionDTO solutionDTO;
	private List<OpportunityScopeDTO> opportunityScopes = new ArrayList<OpportunityScopeDTO>();
	
	private String[] serviceScopes;
	
	private String selServiceScopes;
	
	private String deleletedOppScopeIds;
	private String newServiceScopeIds; 
	
	private String[] serviceElements;
	
	private String selServiceElements;
	
	private String  serviceType;
	
	private List<OpportunityComponentDTO> opportunityComponents = new ArrayList<OpportunityComponentDTO>();
	private String[] componentIDs;
	
	/**
	 * @return the solutionDTO
	 */
	public SolutionDTO getSolutionDTO() {
		return solutionDTO;
	}
	/**
	 * @param solutionDTO the solutionDTO to set
	 */
	public void setSolutionDTO(SolutionDTO solutionDTO) {
		this.solutionDTO = solutionDTO;
	}
	/**
	 * @return the opportunityScopes
	 */
	public List<OpportunityScopeDTO> getOpportunityScopes() {
		return opportunityScopes;
	}
	/**
	 * @param opportunityScopes the opportunityScopes to set
	 */
	public void setOpportunityScopes(List<OpportunityScopeDTO> opportunityScopes) {
		this.opportunityScopes = opportunityScopes;
	}
	/**
	 * @return the serviceScopes
	 */
	public String[] getServiceScopes() {
		
		return serviceScopes;
	}
	/**
	 * @param serviceScopes the serviceScopes to set
	 */
	public void setServiceScopes(String[] serviceScopes) {
		this.serviceScopes = serviceScopes;
	}
	/**
	 * @return the selServiceScopes
	 */
	public String getSelServiceScopes() {
		return selServiceScopes;
	}
	/**
	 * @param selServiceScopes the selServiceScopes to set
	 */
	public void setSelServiceScopes(String selServiceScopes) {
		this.selServiceScopes = selServiceScopes;
	}
	/**
	 * @return the deleletedOppScopeIds
	 */
	public String getDeleletedOppScopeIds() {
		return deleletedOppScopeIds;
	}
	/**
	 * @param deleletedOppScopeIds the deleletedOppScopeIds to set
	 */
	public void setDeleletedOppScopeIds(String deleletedOppScopeIds) {
		this.deleletedOppScopeIds = deleletedOppScopeIds;
	}
		/**
	 * @return the newServiceScopeIds
	 */
	public String getNewServiceScopeIds() {
		return newServiceScopeIds;
	}
	/**
	 * @param newServiceScopeIds the newServiceScopeIds to set
	 */
	public void setNewServiceScopeIds(String newServiceScopeIds) {
		this.newServiceScopeIds = newServiceScopeIds;
	}
	
	public String[] getServiceElements() {
		return serviceElements;
	}
	
	public void setServiceElements(String[] serviceElements) {
		this.serviceElements = serviceElements;
	}
	
	public String getSelServiceElements() {
		return selServiceElements;
	}
	
	public void setSelServiceElements(String selServiceElements) {
		this.selServiceElements = selServiceElements;
	}
	/**
	 * @return the serviceType
	 */
	public String getServiceType() {
		return serviceType;
	}
	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	/*public List<ComponentDTO> getComponents() {
		return components;
	}
	public void setComponents(List<ComponentDTO> components) {
		this.components = components;
	}*/
	public List<OpportunityComponentDTO> getOpportunityComponents() {
		return opportunityComponents;
	}
	public void setOpportunityComponents(
			List<OpportunityComponentDTO> opportunityComponents) {
		this.opportunityComponents = opportunityComponents;
	}
	
	public String[] getComponentIDs() {
		return componentIDs;
	}
	public void setComponentIDs(String[] componentIDs) {
		this.componentIDs = componentIDs;
	}
	
	
	
	
}
