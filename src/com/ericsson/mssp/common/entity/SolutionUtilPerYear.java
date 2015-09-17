package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * Entity SolutionUtilPerYear
 * @author evyyzci
 */
@Entity
@Table(name = "SolutionUtilPerYear")
public class SolutionUtilPerYear implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5865797446367501957L;
	private Integer solutionUtilPerYearID;
	private Solution solution;
	private Float onshoreLocal;
	private Float onshoreGSC;
	private Float onshore3PP;
	private Float offShore;
	private Float nearShore;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SolutionUtilPerYearID", unique = true, nullable = false)
	public Integer getSolutionUtilPerYearID() {
		return solutionUtilPerYearID;
	}
	public void setSolutionUtilPerYearID(Integer solutionUtilPerYearID) {
		this.solutionUtilPerYearID = solutionUtilPerYearID;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SolutionID", nullable = false)
	public Solution getSolution() {
		return solution;
	}
	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	
	@Column(name = "OnshoreLocal", precision = 10)
	public Float getOnshoreLocal() {
		return onshoreLocal;
	}
	public void setOnshoreLocal(Float onshoreLocal) {
		this.onshoreLocal = onshoreLocal;
	}
	
	@Column(name = "OnshoreGSC", precision = 10)
	public Float getOnshoreGSC() {
		return onshoreGSC;
	}
	public void setOnshoreGSC(Float onshoreGSC) {
		this.onshoreGSC = onshoreGSC;
	}
	
	@Column(name = "Onshore3PP", precision = 10)
	public Float getOnshore3PP() {
		return onshore3PP;
	}
	public void setOnshore3PP(Float onshore3pp) {
		onshore3PP = onshore3pp;
	}
	
	@Column(name = "OffShore", precision = 10)
	public Float getOffShore() {
		return offShore;
	}
	public void setOffShore(Float offShore) {
		this.offShore = offShore;
	}
	
	
	@Column(name = "NearShore", precision = 10)
	public Float getNearShore() {
		return nearShore;
	}
	public void setNearShore(Float nearShore) {
		this.nearShore = nearShore;
	}
	
	
}
