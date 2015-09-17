package com.ericsson.mssp.common.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * OpportunityScope generated by hbm2java
 */
@Entity
@Table(name = "ServiceElementJobRoleStages")
public class ServiceElementJobRoleStages implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6719679551698272388L;
	/**
	 * 
	 */
	
	private Integer serviceElementJobRoleStagesId;
	private ServiceElement serviceElement;
	

	private JobRoleStages jobRoleStages;
	
	private String onshoreLocalDefault;
	private String onshoreGSCDefault;
	private String onshore3PPDefault;
	private String nearshoreDefault;
	private String offshoreDefault;
	

	public ServiceElementJobRoleStages() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ServiceElementJobRoleStagesID", unique = true, nullable = false)
	public Integer getServiceElementJobRoleStagesId() {
		return serviceElementJobRoleStagesId;
	}

	public void setServiceElementJobRoleStagesId(
			Integer serviceElementJobRoleStagesId) {
		this.serviceElementJobRoleStagesId = serviceElementJobRoleStagesId;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ServiceElementID", nullable = false)
	public ServiceElement getServiceElement() {
		return serviceElement;
	}

	public void setServiceElement(ServiceElement serviceElement) {
		this.serviceElement = serviceElement;
	}

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JobRoleStagesID", nullable = false)
	public JobRoleStages getJobRoleStages() {
		return jobRoleStages;
	}

	public void setJobRoleStages(JobRoleStages jobRoleStages) {
		this.jobRoleStages = jobRoleStages;
	}

	
	/**
	 * @return the onshoreLocalDefault
	 */
	@JoinColumn(name = "OnshoreLocalDefault")
	public String getOnshoreLocalDefault() {
		return onshoreLocalDefault;
	}

	/**
	 * @param onshoreLocalDefault the onshoreLocalDefault to set
	 */
	public void setOnshoreLocalDefault(String onshoreLocalDefault) {
		this.onshoreLocalDefault = onshoreLocalDefault;
	}

	/**
	 * @return the onshoreGSCDefault
	 */
	@JoinColumn(name = "OnshoreGSCDefault")
	public String getOnshoreGSCDefault() {
		return onshoreGSCDefault;
	}

	/**
	 * @param onshoreGSCDefault the onshoreGSCDefault to set
	 */
	public void setOnshoreGSCDefault(String onshoreGSCDefault) {
		this.onshoreGSCDefault = onshoreGSCDefault;
	}

	/**
	 * @return the onshore3PPDefault
	 */
	@JoinColumn(name = "Onshore3PPDefault")
	public String getOnshore3PPDefault() {
		return onshore3PPDefault;
	}

	/**
	 * @param onshore3ppDefault the onshore3PPDefault to set
	 */
	public void setOnshore3PPDefault(String onshore3ppDefault) {
		onshore3PPDefault = onshore3ppDefault;
	}

	/**
	 * @return the nearshoreDefault
	 */
	@JoinColumn(name = "NearshoreDefault")
	public String getNearshoreDefault() {
		return nearshoreDefault;
	}

	/**
	 * @param nearshoreDefault the nearshoreDefault to set
	 */
	public void setNearshoreDefault(String nearshoreDefault) {
		this.nearshoreDefault = nearshoreDefault;
	}

	/**
	 * @return the offshoreDefault
	 */
	@JoinColumn(name = "OffshoreDefault")
	public String getOffshoreDefault() {
		return offshoreDefault;
	}

	/**
	 * @param offshoreDefault the offshoreDefault to set
	 */
	public void setOffshoreDefault(String offshoreDefault) {
		this.offshoreDefault = offshoreDefault;
	}

	

	
}
