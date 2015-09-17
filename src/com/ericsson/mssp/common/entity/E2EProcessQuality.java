/**
 * 
 */
package com.ericsson.mssp.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author egaivij
 *
 */
@Entity
@Table(name="E2EProcessQuality")
public class E2EProcessQuality {
	
	private Integer e2EProcessQualityId;
	
	private float monitorBusinessProcess;
	private float monitorDataQualityAndAlign;
	private float analyzeBPandDataQuality;
	private float improvementOfProcesses;
	private float analyzeRCofTTVolume;
	private float reportPreparation;
	private float totalHours;
	
	private Solution solution;
	private OpportunityScope opportunityScope;
	/**
	 * @return the e2EProcessQualityId
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="E2EProcessQualityID")
	public Integer getE2EProcessQualityId() {
		return e2EProcessQualityId;
	}
	/**
	 * @param e2eProcessQualityId the e2EProcessQualityId to set
	 */
	public void setE2EProcessQualityId(Integer e2eProcessQualityId) {
		e2EProcessQualityId = e2eProcessQualityId;
	}
	/**
	 * @return the monitorBusinessProcess
	 */
	@Column(name="MonitorBusinessProcess")
	public float getMonitorBusinessProcess() {
		return monitorBusinessProcess;
	}
	/**
	 * @param monitorBusinessProcess the monitorBusinessProcess to set
	 */
	public void setMonitorBusinessProcess(float monitorBusinessProcess) {
		this.monitorBusinessProcess = monitorBusinessProcess;
	}
	/**
	 * @return the monitorDataQualityAndAlign
	 */
	@Column(name="MonitorDataQualityAndAlign")
	public float getMonitorDataQualityAndAlign() {
		return monitorDataQualityAndAlign;
	}
	/**
	 * @param monitorDataQualityAndAlign the monitorDataQualityAndAlign to set
	 */
	public void setMonitorDataQualityAndAlign(float monitorDataQualityAndAlign) {
		this.monitorDataQualityAndAlign = monitorDataQualityAndAlign;
	}
	/**
	 * @return the analyzeBPandDataQuality
	 */
	@Column(name="AnalyzeBPandDataQuality")
	public float getAnalyzeBPandDataQuality() {
		return analyzeBPandDataQuality;
	}
	/**
	 * @param analyzeBPandDataQuality the analyzeBPandDataQuality to set
	 */
	public void setAnalyzeBPandDataQuality(float analyzeBPandDataQuality) {
		this.analyzeBPandDataQuality = analyzeBPandDataQuality;
	}
	/**
	 * @return the improvementOfProcesses
	 */
	@Column(name="ImprovementOfProcesses")
	public float getImprovementOfProcesses() {
		return improvementOfProcesses;
	}
	/**
	 * @param improvementOfProcesses the improvementOfProcesses to set
	 */
	public void setImprovementOfProcesses(float improvementOfProcesses) {
		this.improvementOfProcesses = improvementOfProcesses;
	}
	/**
	 * @return the analyzeRCofTTVolume
	 */
	@Column(name="AnalyzeRCofTTVolume")
	public float getAnalyzeRCofTTVolume() {
		return analyzeRCofTTVolume;
	}
	/**
	 * @param analyzeRCofTTVolume the analyzeRCofTTVolume to set
	 */
	public void setAnalyzeRCofTTVolume(float analyzeRCofTTVolume) {
		this.analyzeRCofTTVolume = analyzeRCofTTVolume;
	}
	/**
	 * @return the reportPreparation
	 */
	@Column(name="ReportPreparation")
	public float getReportPreparation() {
		return reportPreparation;
	}
	/**
	 * @param reportPreparation the reportPreparation to set
	 */
	public void setReportPreparation(float reportPreparation) {
		this.reportPreparation = reportPreparation;
	}


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SolutionID")
	public Solution getSolution() {
		return solution;
	}
	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OpportunityScopeID")
	public OpportunityScope getOpportunityScope() {
		return opportunityScope;
	}
	public void setOpportunityScope(OpportunityScope opportunityScope) {
		this.opportunityScope = opportunityScope;
	}
	/**
	 * @return the totalHours
	 */
	@Column(name="TotalHours")
	public float getTotalHours() {
		return totalHours;
	}
	/**
	 * @param totalHours the totalHours to set
	 */
	public void setTotalHours(float totalHours) {
		this.totalHours = totalHours;
	}
	
	
	
}
