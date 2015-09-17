package com.ericsson.mssp.common.dto;


import com.ericsson.mssp.common.entity.Apacomplexity;
import com.ericsson.mssp.common.entity.CostSummary;
import com.ericsson.mssp.common.entity.FTEStaging;
import com.ericsson.mssp.common.entity.FTESummary;
import com.ericsson.mssp.common.entity.LocationBreakup;
import com.ericsson.mssp.common.entity.LocationPyramid;
import com.ericsson.mssp.common.entity.Opportunity;
import com.ericsson.mssp.common.entity.ProductivityLever;
import com.ericsson.mssp.common.entity.ServiceScope;
import com.ericsson.mssp.common.entity.SolutionAPA;

public class OpportunityScopeDTO {
	
	private Integer opportunityScopeId;
	private ServiceScopeDTO serviceScopeDTO;
	private OpportunityDTO opportunityDTO;
	/*private ProductivityLever productivityLevers;
	private LocationBreakup locationBreakups;
	private FTEStaging ftestagings;
	private Apacomplexity apacomplexities;
	private LocationPyramid locationPyramids;
	private FTESummary ftesummaries;
	private SolutionAPA solutionApas;
	private CostSummary costSummaries;
	*/
	public Integer getOpportunityScopeId() {
		return opportunityScopeId;
	}
	public void setOpportunityScopeId(Integer opportunityScopeId) {
		this.opportunityScopeId = opportunityScopeId;
	}
	/**
	 * @return the serviceScopeDTO
	 */
	public ServiceScopeDTO getServiceScopeDTO() {
		return serviceScopeDTO;
	}
	/**
	 * @param serviceScopeDTO the serviceScopeDTO to set
	 */
	public void setServiceScopeDTO(ServiceScopeDTO serviceScopeDTO) {
		this.serviceScopeDTO = serviceScopeDTO;
	}
	/**
	 * @return the opportunityDTO
	 */
	public OpportunityDTO getOpportunityDTO() {
		return opportunityDTO;
	}
	/**
	 * @param opportunityDTO the opportunityDTO to set
	 */
	public void setOpportunityDTO(OpportunityDTO opportunityDTO) {
		this.opportunityDTO = opportunityDTO;
	}
	
	

}
