package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ProductEstimationActivities
 */
@Entity
@Table(name = "ProductEstimationActivities")
public class ProductEstimationActivities implements java.io.Serializable {

	private static final long serialVersionUID = -330614778634165293L;
	private Integer productEstimationActivitiesID;
	private String configurationType;
	private String configurationArea;
	private String activityName;
	private ServiceScope serviceScope;
	private List<ProductActivitiesComplexityAssumptions> productActivitiesComplexityAssumptions = new ArrayList<>();
	private Component component;

	public ProductEstimationActivities() {
	}

	public ProductEstimationActivities(Integer productEstimationActivitiesID,
			String configurationType, String configurationArea,
			String activityName, ServiceScope serviceScope) {
		this.productEstimationActivitiesID = productEstimationActivitiesID;
		this.configurationType = configurationType;
		this.configurationArea = configurationArea;
		this.activityName = activityName;
		this.serviceScope = serviceScope;
	}

	public ProductEstimationActivities(
			Integer productEstimationActivitiesID,
			String configurationType,
			String configurationArea,
			String activityName,
			ServiceScope serviceScope,
			List<ProductActivitiesComplexityAssumptions> productActivitiesComplexityAssumptions) {
		super();
		this.productEstimationActivitiesID = productEstimationActivitiesID;
		this.configurationType = configurationType;
		this.configurationArea = configurationArea;
		this.activityName = activityName;
		this.serviceScope = serviceScope;
		this.productActivitiesComplexityAssumptions = productActivitiesComplexityAssumptions;
	}
	
	public ProductEstimationActivities(
			Integer productEstimationActivitiesID,
			String configurationType,
			String configurationArea,
			String activityName,
			ServiceScope serviceScope,
			List<ProductActivitiesComplexityAssumptions> productActivitiesComplexityAssumptions,
			Component component) {
		super();
		this.productEstimationActivitiesID = productEstimationActivitiesID;
		this.configurationType = configurationType;
		this.configurationArea = configurationArea;
		this.activityName = activityName;
		this.serviceScope = serviceScope;
		this.productActivitiesComplexityAssumptions = productActivitiesComplexityAssumptions;
		this.component = component;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ProductEstimationActivitiesID", unique = true, nullable = false)
	public Integer getProductEstimationActivitiesID() {
		return productEstimationActivitiesID;
	}

	public void setProductEstimationActivitiesID(
			Integer productEstimationActivitiesID) {
		this.productEstimationActivitiesID = productEstimationActivitiesID;
	}

	@Column(name = "ConfigurationType")
	public String getConfigurationType() {
		return configurationType;
	}

	public void setConfigurationType(String configurationType) {
		this.configurationType = configurationType;
	}

	@Column(name = "ConfigurationArea")
	public String getConfigurationArea() {
		return configurationArea;
	}

	public void setConfigurationArea(String configurationArea) {
		this.configurationArea = configurationArea;
	}

	@Column(name = "ActivityName")
	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ServiceScopeID")
	public ServiceScope getServiceScope() {
		return serviceScope;
	}

	public void setServiceScope(ServiceScope serviceScope) {
		this.serviceScope = serviceScope;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "productEstimationActivities")
	public List<ProductActivitiesComplexityAssumptions> getProductActivitiesComplexityAssumptions() {
		return productActivitiesComplexityAssumptions;
	}

	public void setProductActivitiesComplexityAssumptions(
			List<ProductActivitiesComplexityAssumptions> productActivitiesComplexityAssumptions) {
		this.productActivitiesComplexityAssumptions = productActivitiesComplexityAssumptions;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "componentID")
	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

}
