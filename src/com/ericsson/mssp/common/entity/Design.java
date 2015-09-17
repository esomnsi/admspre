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

@Entity
@Table(name="Design")
public class Design {

	private Integer designID;
	private Integer useCaseRealisation;
	private Integer workflowOfUseCase;
	private Integer manageInterfaces;
	private Integer manageNFR;
	private Integer designDocument;
	private Integer review;
	private Integer baseLine;
	private Integer totalDesignHours;
	private Solution solution;
    private OpportunityScope opportunityScope;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DesignID", unique = true, nullable = false)
	public Integer getDesignID() {
		return designID;
	}
	public void setDesignID(Integer designID) {
		this.designID = designID;
	}
	
	@Column(name="UseCaseRealisation")
	public Integer getUseCaseRealisation() {
		return useCaseRealisation;
	}
	public void setUseCaseRealisation(Integer useCaseRealisation) {
		this.useCaseRealisation = useCaseRealisation;
	}
	
	@Column(name="WorkflowOfUseCase")
	public Integer getWorkflowOfUseCase() {
		return workflowOfUseCase;
	}
	public void setWorkflowOfUseCase(Integer workflowOfUseCase) {
		this.workflowOfUseCase = workflowOfUseCase;
	}
	
	@Column(name="ManageInterfaces")
	public Integer getManageInterfaces() {
		return manageInterfaces;
	}
	public void setManageInterfaces(Integer manageInterfaces) {
		this.manageInterfaces = manageInterfaces;
	}
	
	@Column(name="ManageNFR")
	public Integer getManageNFR() {
		return manageNFR;
	}
	public void setManageNFR(Integer manageNFR) {
		this.manageNFR = manageNFR;
	}
	
	@Column(name="DesignDocument")
	public Integer getDesignDocument() {
		return designDocument;
	}
	public void setDesignDocument(Integer designDocument) {
		this.designDocument = designDocument;
	}
	
	@Column(name="Review")
	public Integer getReview() {
		return review;
	}
	public void setReview(Integer review) {
		this.review = review;
	}
	
	@Column(name="BaseLine")
	public Integer getBaseLine() {
		return baseLine;
	}
	public void setBaseLine(Integer baseLine) {
		this.baseLine = baseLine;
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
	
	@Column(name="TotalDesignHours")
	public Integer getTotalDesignHours() {
		return totalDesignHours;
	}
	public void setTotalDesignHours(Integer totalDesignHours) {
		this.totalDesignHours = totalDesignHours;
	}
	
	
}
