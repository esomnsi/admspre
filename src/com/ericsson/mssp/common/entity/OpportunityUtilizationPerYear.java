package com.ericsson.mssp.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class OpportunityUtilizationPerYear implements Serializable{
	

		private static final long serialVersionUID = -1996488133600991409L;
		
		private Integer opportunityUtilizationPerYearId;
	    private Opportunity opportunity;
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
		 * @return the opportunity
		 */
		@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "OpportunityID")
		public Opportunity getOpportunity() {
			return opportunity;
		}
		/**
		 * @param opportunity the opportunity to set
		 */
		public void setOpportunity(Opportunity opportunity) {
			this.opportunity = opportunity;
		}
		
		/**
		 * @return the onshoreLocal
		 */
		@Column(name = "OnshoreLocal", precision = 10)
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
		@Column(name = "OnshoreGSC", precision = 10)
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
		@Column(name = "Onshore3PP", precision = 10)
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
		@Column(name = "Nearshore", precision = 10)
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
		@Column(name = "Offshore", precision = 10)
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