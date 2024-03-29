package com.ericsson.mssp.common.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SolutionAdr generated by hbm2java
 */
@Entity
@Table(name = "SolutionADR")
public class SolutionADR implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1714458403019802614L;
	private Integer solutionAdrid;
	private Solution solution;
	private String adrcategory;
	private String adrstatement;
	private String adrarea;
	private String adrtype;
	private Integer adrimpact;
	private Float adrweightage;

	public SolutionADR() {
	}

	public SolutionADR(Solution solution) {
		this.solution = solution;
	}

	public SolutionADR(Solution solution, String adrcategory,
			String adrstatement, String adrarea, String adrtype,
			Integer adrimpact, Float adrweightage) {
		this.solution = solution;
		this.adrcategory = adrcategory;
		this.adrstatement = adrstatement;
		this.adrarea = adrarea;
		this.adrtype = adrtype;
		this.adrimpact = adrimpact;
		this.adrweightage = adrweightage;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SolutionADRID", unique = true, nullable = false)
	public Integer getSolutionAdrid() {
		return this.solutionAdrid;
	}

	public void setSolutionAdrid(Integer solutionAdrid) {
		this.solutionAdrid = solutionAdrid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SolutionID", nullable = false)
	public Solution getSolution() {
		return this.solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	@Column(name = "ADRCategory", length = 100)
	public String getAdrcategory() {
		return this.adrcategory;
	}

	public void setAdrcategory(String adrcategory) {
		this.adrcategory = adrcategory;
	}

	@Column(name = "ADRStatement")
	public String getAdrstatement() {
		return this.adrstatement;
	}

	public void setAdrstatement(String adrstatement) {
		this.adrstatement = adrstatement;
	}

	@Column(name = "ADRArea", length = 100)
	public String getAdrarea() {
		return this.adrarea;
	}

	public void setAdrarea(String adrarea) {
		this.adrarea = adrarea;
	}

	@Column(name = "ADRType", length = 100)
	public String getAdrtype() {
		return this.adrtype;
	}

	public void setAdrtype(String adrtype) {
		this.adrtype = adrtype;
	}

	@Column(name = "ADRImpact")
	public Integer getAdrimpact() {
		return this.adrimpact;
	}

	public void setAdrimpact(Integer adrimpact) {
		this.adrimpact = adrimpact;
	}

	@Column(name = "ADRWeightage", precision = 10)
	public Float getAdrweightage() {
		return this.adrweightage;
	}

	public void setAdrweightage(Float adrweightage) {
		this.adrweightage = adrweightage;
	}

}
