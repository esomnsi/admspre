package com.ericsson.mssp.common.entity;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OpportunityDetail generated by hbm2java
 */
@Entity
@Table(name = "OpportunityDetail")
public class OpportunityDetail implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7000046425300102349L;
	private Integer opportunityDetailId;
	private Opportunity opportunity;
	private String opportunityOwner;
	private String newBusiness;
	private String existingCustomer;
	private String cadenceId;
	private String vertical;
	private Date transformationStartDate;
	private Date transformationEndDate;
	private Date steadyStateStartDate;
	private Date steadyStateEndDate;
	private String competitors;
	private String capexSpend;
	private String opexSpend;
	private String existingFte;
	private String turnOver;
	private String primaryBusinessLine;
	private String rfpreason;
	private String otherReason;
	private String regionKam;
	private String primaryVerticalRepresentative;
	private String admssolutionRepresentative;
	private String accountCommercialResponsible;
	private String customerFulfillmentResponsible;
	private String customerSolutionResponsible;
	private String tertiarySolutionSME;
	private String contractDuration;
	private String steadyStateDuration;
	private ScopeOfServicesDefinedBy scopeOfServicesDefinedBy;
	private InputVolumetricsDefinedBy volumetricsDefinedBy;
	private DeliveryModel deliveryModel;
	private DeliveryType deliveryType;
	private String serviceType;
	
	private Set<OpportunityLocation> opportunityLocations = new HashSet<OpportunityLocation>(0);

	public OpportunityDetail() {
	}

	public OpportunityDetail(Opportunity opportunity) {
		this.opportunity = opportunity;
	}
	public OpportunityDetail(ScopeOfServicesDefinedBy scopeOfServicesDefinedBy){
		this.scopeOfServicesDefinedBy = scopeOfServicesDefinedBy;
	}
	public OpportunityDetail(InputVolumetricsDefinedBy inputVolumetricsDefinedBy){
		this.volumetricsDefinedBy = inputVolumetricsDefinedBy;
	}
	public OpportunityDetail(DeliveryModel deliveryModel){
		this.deliveryModel = deliveryModel;
	}
	public OpportunityDetail(DeliveryType deliveryType){
		this.deliveryType = deliveryType;
	}

	public OpportunityDetail(Opportunity opportunity, String opportunityOwner,
			String newBusiness, String existingCustomer, String cadenceId,
			String vertical, Date transformationStartDate,
			Date transformationEndDate, Date steadyStateStartDate,
			Date steadyStateEndDate, String competitors, String capexSpend,
			String opexSpend, String existingFte, String turnOver,
			String primaryBusinessLine, String rfpreason, String otherReason,
			String regionKam, String primaryVerticalRepresentative,
			String admssolutionRepresentative, String accountCommercialResponsible,
			String customerFulfillmentResponsible,String customerSolutionResponsible,Set<OpportunityLocation> opportunityLocations,
			ScopeOfServicesDefinedBy scopeOfServicesDefinedBy, InputVolumetricsDefinedBy volumetricsDefinedBy, DeliveryModel deliveryModel,
			DeliveryType deliveryType,Float onshoreLocal, Float onshoreGSC, Float onshore3PP, Float nearShore, Float offShore ){
		this.opportunity = opportunity;
		this.opportunityOwner = opportunityOwner;
		this.newBusiness = newBusiness;
		this.existingCustomer = existingCustomer;
		this.cadenceId = cadenceId;
		this.vertical = vertical;
		this.transformationStartDate = transformationStartDate;
		this.transformationEndDate = transformationEndDate;
		this.steadyStateStartDate = steadyStateStartDate;
		this.steadyStateEndDate = steadyStateEndDate;
		this.competitors = competitors;
		this.capexSpend = capexSpend;
		this.opexSpend = opexSpend;
		this.existingFte = existingFte;
		this.turnOver = turnOver;
		this.primaryBusinessLine = primaryBusinessLine;
		this.rfpreason = rfpreason;
		this.otherReason = otherReason;
		this.regionKam = regionKam;
		this.primaryVerticalRepresentative = primaryVerticalRepresentative;
		this.admssolutionRepresentative = admssolutionRepresentative;
		this.opportunityLocations = opportunityLocations;
		this.accountCommercialResponsible = accountCommercialResponsible;
		this.customerFulfillmentResponsible = customerFulfillmentResponsible;
		this.customerSolutionResponsible = customerSolutionResponsible;
		this.scopeOfServicesDefinedBy = scopeOfServicesDefinedBy;
		this.volumetricsDefinedBy = volumetricsDefinedBy;
		this.deliveryModel = deliveryModel;
		this.deliveryType = deliveryType;
	
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "OpportunityDetailID", unique = true, nullable = false)
	public Integer getOpportunityDetailId() {
		return this.opportunityDetailId;
	}

	public void setOpportunityDetailId(Integer opportunityDetailId) {
		this.opportunityDetailId = opportunityDetailId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OpportunityID", nullable = false)
	public Opportunity getOpportunity() {
		return this.opportunity;
	}

	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}

	@Column(name = "OpportunityOwner", length = 50)
	public String getOpportunityOwner() {
		return this.opportunityOwner;
	}

	public void setOpportunityOwner(String opportunityOwner) {
		this.opportunityOwner = opportunityOwner;
	}

	@Column(name = "NewBusiness", length = 1)
	public String getNewBusiness() {
		return this.newBusiness;
	}

	public void setNewBusiness(String newBusiness) {
		this.newBusiness = newBusiness;
	}

	@Column(name = "ExistingCustomer", length = 1)
	public String getExistingCustomer() {
		return this.existingCustomer;
	}

	public void setExistingCustomer(String existingCustomer) {
		this.existingCustomer = existingCustomer;
	}

	@Column(name = "CadenceID", length = 50)
	public String getCadenceId() {
		return this.cadenceId;
	}

	public void setCadenceId(String cadenceId) {
		this.cadenceId = cadenceId;
	}

	@Column(name = "Vertical", length = 50)
	public String getVertical() {
		return this.vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TransformationStartDate", length = 19)
	public Date getTransformationStartDate() {
		return this.transformationStartDate;
	}

	public void setTransformationStartDate(Date transformationStartDate) {
		this.transformationStartDate = transformationStartDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TransformationEndDate", length = 19)
	public Date getTransformationEndDate() {
		return this.transformationEndDate;
	}

	public void setTransformationEndDate(Date transformationEndDate) {
		this.transformationEndDate = transformationEndDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SteadyStateStartDate", length = 19)
	public Date getSteadyStateStartDate() {
		return this.steadyStateStartDate;
	}

	public void setSteadyStateStartDate(Date steadyStateStartDate) {
		this.steadyStateStartDate = steadyStateStartDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SteadyStateEndDate", length = 19)
	public Date getSteadyStateEndDate() {
		return this.steadyStateEndDate;
	}

	public void setSteadyStateEndDate(Date steadyStateEndDate) {
		this.steadyStateEndDate = steadyStateEndDate;
	}

	@Column(name = "Competitors")
	public String getCompetitors() {
		return this.competitors;
	}

	public void setCompetitors(String competitors) {
		this.competitors = competitors;
	}

	@Column(name = "CapexSpend", length = 50)
	public String getCapexSpend() {
		return this.capexSpend;
	}

	public void setCapexSpend(String capexSpend) {
		this.capexSpend = capexSpend;
	}

	@Column(name = "OpexSpend", length = 50)
	public String getOpexSpend() {
		return this.opexSpend;
	}

	public void setOpexSpend(String opexSpend) {
		this.opexSpend = opexSpend;
	}

	@Column(name = "ExistingFTE", length = 100)
	public String getExistingFte() {
		return this.existingFte;
	}

	public void setExistingFte(String existingFte) {
		this.existingFte = existingFte;
	}

	@Column(name = "TurnOver", length = 50)
	public String getTurnOver() {
		return this.turnOver;
	}

	public void setTurnOver(String turnOver) {
		this.turnOver = turnOver;
	}

	@Column(name = "PrimaryBusinessLine", length = 50)
	public String getPrimaryBusinessLine() {
		return this.primaryBusinessLine;
	}

	public void setPrimaryBusinessLine(String primaryBusinessLine) {
		this.primaryBusinessLine = primaryBusinessLine;
	}

	@Column(name = "RFPReason")
	public String getRfpreason() {
		return this.rfpreason;
	}

	public void setRfpreason(String rfpreason) {
		this.rfpreason = rfpreason;
	}

	@Column(name = "OtherReason")
	public String getOtherReason() {
		return this.otherReason;
	}

	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}

	@Column(name = "RegionKAM", length = 50)
	public String getRegionKam() {
		return this.regionKam;
	}

	public void setRegionKam(String regionKam) {
		this.regionKam = regionKam;
	}

	@Column(name = "PrimaryVerticalRepresentative", length = 50)
	public String getPrimaryVerticalRepresentative() {
		return this.primaryVerticalRepresentative;
	}

	public void setPrimaryVerticalRepresentative(
			String primaryVerticalRepresentative) {
		this.primaryVerticalRepresentative = primaryVerticalRepresentative;
	}

	@Column(name = "ADMSSolutionRepresentative", length = 50)
	public String getAdmssolutionRepresentative() {
		return this.admssolutionRepresentative;
	}

	public void setAdmssolutionRepresentative(String admssolutionRepresentative) {
		this.admssolutionRepresentative = admssolutionRepresentative;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "opportunityDetail",cascade = CascadeType.ALL)
	@JoinColumn(name = "OpportunityLocationID" , nullable = true)
	public Set<OpportunityLocation> getOpportunityLocations() {
		return this.opportunityLocations;
	}

	public void setOpportunityLocations(Set<OpportunityLocation> opportunityLocations) {
		this.opportunityLocations = opportunityLocations;
	}

	
	@Column(name="SteadyStateDuration")
	public String getSteadyStateDuration() {
		return steadyStateDuration;
	}

	public void setSteadyStateDuration(String steadyStateDuration) {
		this.steadyStateDuration = steadyStateDuration;
	}

	@Column(name="ContractDuration")
	public String getContractDuration() {
		return contractDuration;
	}

	public void setContractDuration(String contractDuration) {
		this.contractDuration = contractDuration;
	}

	@Column(name="AccountCommercialResponsible")
	public String getAccountCommercialResponsible() {
		return accountCommercialResponsible;
	}

	public void setAccountCommercialResponsible(String accountCommercialResponsible) {
		this.accountCommercialResponsible = accountCommercialResponsible;
	}
	
	@Column(name="CustomerFulfillmentResponsible")
	public String getCustomerFulfillmentResponsible() {
		return customerFulfillmentResponsible;
	}

	public void setCustomerFulfillmentResponsible(
			String customerFulfillmentResponsible) {
		this.customerFulfillmentResponsible = customerFulfillmentResponsible;
	}

	@Column(name="CustomerSolutionResponsible")
	public String getCustomerSolutionResponsible() {
		return customerSolutionResponsible;
	}

	public void setCustomerSolutionResponsible(String customerSolutionResponsible) {
		this.customerSolutionResponsible = customerSolutionResponsible;
	}

	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ScopeOfServicesDefinedByID", nullable = true)
	public ScopeOfServicesDefinedBy getScopeOfServicesDefinedBy() {
		return scopeOfServicesDefinedBy;
	}

	public void setScopeOfServicesDefinedBy(
			ScopeOfServicesDefinedBy scopeOfServicesDefinedBy) {
		this.scopeOfServicesDefinedBy = scopeOfServicesDefinedBy;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "InputVolumetricsDefinedByID", nullable = true)
	public InputVolumetricsDefinedBy getVolumetricsDefinedBy() {
		return volumetricsDefinedBy;
	}

	public void setVolumetricsDefinedBy(
			InputVolumetricsDefinedBy volumetricsDefinedBy) {
		this.volumetricsDefinedBy = volumetricsDefinedBy;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DeliveryModelID", nullable = true)
	public DeliveryModel getDeliveryModel() {
		return deliveryModel;
	}

	public void setDeliveryModel(DeliveryModel deliveryModel) {
		this.deliveryModel = deliveryModel;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DeliveryTypeID", nullable = true)
	public DeliveryType getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(DeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getTertiarySolutionSME() {
		return tertiarySolutionSME;
	}

	public void setTertiarySolutionSME(String tertiarySolutionSME) {
		this.tertiarySolutionSME = tertiarySolutionSME;
	}
	
	/**
	 * @return the serviceType
	 */
	@Column(name = "ServiceType", length = 50)
	public String getServiceType() {
		return serviceType;
	}

	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	

}