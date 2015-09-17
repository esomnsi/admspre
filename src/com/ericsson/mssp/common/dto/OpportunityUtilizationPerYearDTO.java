/**
 * 
 */
package com.ericsson.mssp.common.dto;


/**
 * @author egaivij
 *
 */
public class OpportunityUtilizationPerYearDTO {
	
	private Integer opportunityUtilizationPerYearId;
    private OpportunityDTO opportunityDTO;
    
    private Float onshoreLocal;
    private Float onshoreGSC;
    private Float onshore3PP;
    private Float nearshore;
    private Float offshore;
    
    

	/**
	 * @return the opportunityUtilizationPerYearId
	 */
	public Integer getOpportunityUtilizationPerYearId() {
		return opportunityUtilizationPerYearId;
	}
	/**
	 * @param opportunityUtilizationPerYearId the opportunityUtilizationPerYearId to set
	 */
	public void setOpportunityUtilizationPerYearId(
			Integer opportunityUtilizationPerYearId) {
		this.opportunityUtilizationPerYearId = opportunityUtilizationPerYearId;
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
	/**
	 * @return the onshoreLocal
	 */
	public Float getOnshoreLocal() {
		return onshoreLocal;
	}
	/**
	 * @param onshoreLocal the onshoreLocal to set
	 */
	public void setOnshoreLocal(Float onshoreLocal) {
		this.onshoreLocal = onshoreLocal;
	}
	/**
	 * @return the onshoreGSC
	 */
	public Float getOnshoreGSC() {
		return onshoreGSC;
	}
	/**
	 * @param onshoreGSC the onshoreGSC to set
	 */
	public void setOnshoreGSC(Float onshoreGSC) {
		this.onshoreGSC = onshoreGSC;
	}
	/**
	 * @return the onshore3PP
	 */
	public Float getOnshore3PP() {
		return onshore3PP;
	}
	/**
	 * @param onshore3pp the onshore3PP to set
	 */
	public void setOnshore3PP(Float onshore3pp) {
		onshore3PP = onshore3pp;
	}
	/**
	 * @return the nearshore
	 */
	public Float getNearshore() {
		return nearshore;
	}
	/**
	 * @param nearshore the nearshore to set
	 */
	public void setNearshore(Float nearshore) {
		this.nearshore = nearshore;
	}
	/**
	 * @return the offshore
	 */
	public Float getOffshore() {
		return offshore;
	}
	/**
	 * @param offshore the offshore to set
	 */
	public void setOffshore(Float offshore) {
		this.offshore = offshore;
	}
	
    
    

}
