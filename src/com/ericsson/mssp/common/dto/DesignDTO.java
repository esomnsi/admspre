package com.ericsson.mssp.common.dto;

public class DesignDTO {
	
	private Integer designID;
	private Integer useCaseRealisation;
	private Integer workflowOfUseCase;
	private Integer manageInterfaces;
	private Integer manageNFR;
	private Integer designDocument;
	private Integer review;
	private Integer baseLine;
	private Integer totalDesignHours;
	private SolutionDTO solutionDTO;
	private OpportunityScopeDTO opportunityScopeDTO;
	
	
	public Integer getDesignID() {
		return designID;
	}
	public void setDesignID(Integer designID) {
		this.designID = designID;
	}
	public Integer getUseCaseRealisation() {
		return useCaseRealisation;
	}
	public void setUseCaseRealisation(Integer useCaseRealisation) {
		this.useCaseRealisation = useCaseRealisation;
	}
	public Integer getWorkflowOfUseCase() {
		return workflowOfUseCase;
	}
	public void setWorkflowOfUseCase(Integer workflowOfUseCase) {
		this.workflowOfUseCase = workflowOfUseCase;
	}
	public Integer getManageInterfaces() {
		return manageInterfaces;
	}
	public void setManageInterfaces(Integer manageInterfaces) {
		this.manageInterfaces = manageInterfaces;
	}
	public Integer getManageNFR() {
		return manageNFR;
	}
	public void setManageNFR(Integer manageNFR) {
		this.manageNFR = manageNFR;
	}
	public Integer getDesignDocument() {
		return designDocument;
	}
	public void setDesignDocument(Integer designDocument) {
		this.designDocument = designDocument;
	}
	
	public Integer getReview() {
		return review;
	}
	public void setReview(Integer review) {
		this.review = review;
	}
	public Integer getBaseLine() {
		return baseLine;
	}
	public void setBaseLine(Integer baseLine) {
		this.baseLine = baseLine;
	}
	public SolutionDTO getSolutionDTO() {
		return solutionDTO;
	}
	public void setSolutionDTO(SolutionDTO solutionDTO) {
		this.solutionDTO = solutionDTO;
	}
	public OpportunityScopeDTO getOpportunityScopeDTO() {
		return opportunityScopeDTO;
	}
	public void setOpportunityScopeDTO(OpportunityScopeDTO opportunityScopeDTO) {
		this.opportunityScopeDTO = opportunityScopeDTO;
	}
	public Integer getTotalDesignHours() {
		return totalDesignHours;
	}
	public void setTotalDesignHours(Integer totalDesignHours) {
		this.totalDesignHours = totalDesignHours;
	}
	
	
}
