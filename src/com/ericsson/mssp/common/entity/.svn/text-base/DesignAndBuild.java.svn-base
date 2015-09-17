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

@Entity
@Table(name="DesignBuild")
public class DesignAndBuild {
	
	private Integer designAndBuildId;
	private Solution solution;
    private OpportunityScope opportunityScope;
    private float detailTechDesign;
    private float testCaseDesignTestCyclePlan;
    private float functionalSpecDevFinal;
    private float capacityPlanning;
    private float softwareChanges;
    private float configChanges;
    private float unitComponentTest;
    private float relNotesPrepareDistribute;
    private float maintananceManualCreation;
    private float totalDesignBuildHours;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="DesignBuildID")
	public Integer getDesignAndBuildId() {
		return designAndBuildId;
	}
	public void setDesignAndBuildId(Integer designAndBuildId) {
		this.designAndBuildId = designAndBuildId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SolutionID", nullable = false)
	public Solution getSolution() {
		return solution;
	}
	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OpportunityScopeID")
	public OpportunityScope getOpportunityScope() {
		return opportunityScope;
	}
	public void setOpportunityScope(OpportunityScope opportunityScope) {
		this.opportunityScope = opportunityScope;
	}
	
	@Column(name="DetailedTechDesign")
	public float getDetailTechDesign() {
		return detailTechDesign;
	}
	public void setDetailTechDesign(float detailTechDesign) {
		this.detailTechDesign = detailTechDesign;
	}
	
	@Column(name="TestCaseDesignTestCyclePlan")
	public float getTestCaseDesignTestCyclePlan() {
		return testCaseDesignTestCyclePlan;
	}
	public void setTestCaseDesignTestCyclePlan(float testCaseDesignTestCyclePlan) {
		this.testCaseDesignTestCyclePlan = testCaseDesignTestCyclePlan;
	}
	
	@Column(name="FunctionalSpecDevFinal")
	public float getFunctionalSpecDevFinal() {
		return functionalSpecDevFinal;
	}
	public void setFunctionalSpecDevFinal(float functionalSpecDevFinal) {
		this.functionalSpecDevFinal = functionalSpecDevFinal;
	}
	
	@Column(name="CapacityPlanning")
	public float getCapacityPlanning() {
		return capacityPlanning;
	}
	public void setCapacityPlanning(float capacityPlanning) {
		this.capacityPlanning = capacityPlanning;
	}
	
	@Column(name="SoftwareChanges")
	public float getSoftwareChanges() {
		return softwareChanges;
	}
	public void setSoftwareChanges(float softwareChanges) {
		this.softwareChanges = softwareChanges;
	}
	
	@Column(name="ConfigChanges")
	public float getConfigChanges() {
		return configChanges;
	}
	public void setConfigChanges(float configChanges) {
		this.configChanges = configChanges;
	}
	
	@Column(name="UnitComponentTest")
	public float getUnitComponentTest() {
		return unitComponentTest;
	}
	public void setUnitComponentTest(float unitComponentTest) {
		this.unitComponentTest = unitComponentTest;
	}
	
	@Column(name="RelNotesPrepareDistribute")
	public float getRelNotesPrepareDistribute() {
		return relNotesPrepareDistribute;
	}
	public void setRelNotesPrepareDistribute(float relNotesPrepareDistribute) {
		this.relNotesPrepareDistribute = relNotesPrepareDistribute;
	}
	
	@Column(name="MaintananceManualCreation")
	public float getMaintananceManualCreation() {
		return maintananceManualCreation;
	}
	public void setMaintananceManualCreation(float maintananceManualCreation) {
		this.maintananceManualCreation = maintananceManualCreation;
	}
	
	@Column(name="TotalDesignBuildHours")
	public float getTotalDesignBuildHours() {
		return totalDesignBuildHours;
	}
	public void setTotalDesignBuildHours(float totalDesignBuildHours) {
		this.totalDesignBuildHours = totalDesignBuildHours;
	}
    
    

}
