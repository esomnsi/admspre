/**
 * 
 */
package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ServiceScope generated by hbm2java
 */
@Entity
@Table(name = "LocationBreakupDefault")
public class LocationBreakupDefault {
	
	private Integer locationBreakupDefaultId;
	private ServiceElement serviceElement;
	private String onshoreLocalPC;
	private String onshoreGSCPC;
	private String onshore3PPPC;
	private String nearshoreGSCPC;
	private String offshoreGSCPC;
	
	
	
	/**
	 * @return the locationBreakupDefaultId
	 */
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LocationBreakupDefaultID", unique = true, nullable = false)
	public Integer getLocationBreakupDefaultId() {
		return locationBreakupDefaultId;
	}
	/**
	 * @param locationBreakupDefaultId the locationBreakupDefaultId to set
	 */
	public void setLocationBreakupDefaultId(Integer locationBreakupDefaultId) {
		this.locationBreakupDefaultId = locationBreakupDefaultId;
	}
	/**
	 * @return the serviceElement
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ServiceElementID", nullable = false)
	public ServiceElement getServiceElement() {
		return serviceElement;
	}
	/**
	 * @param serviceElement the serviceElement to set
	 */
	public void setServiceElement(ServiceElement serviceElement) {
		this.serviceElement = serviceElement;
	}
	/**
	 * @return the onshoreLocalPC
	 */
	@Column(name = "OnshoreLocalPC", length = 10)
	public String getOnshoreLocalPC() {
		return onshoreLocalPC;
	}
	/**
	 * @param onshoreLocalPC the onshoreLocalPC to set
	 */
	public void setOnshoreLocalPC(String onshoreLocalPC) {
		this.onshoreLocalPC = onshoreLocalPC;
	}
	/**
	 * @return the onshoreGSCPC
	 */
	@Column(name = "OnshoreGSCPC", length = 10)
	public String getOnshoreGSCPC() {
		return onshoreGSCPC;
	}
	/**
	 * @param onshoreGSCPC the onshoreGSCPC to set
	 */
	public void setOnshoreGSCPC(String onshoreGSCPC) {
		this.onshoreGSCPC = onshoreGSCPC;
	}
	/**
	 * @return the onshore3PPPC
	 */
	@Column(name = "Onshore3PPPC", length = 10)
	public String getOnshore3PPPC() {
		return onshore3PPPC;
	}
	/**
	 * @param onshore3pppc the onshore3PPPC to set
	 */
	public void setOnshore3PPPC(String onshore3pppc) {
		onshore3PPPC = onshore3pppc;
	}
	/**
	 * @return the nearshoreGSCPC
	 */
	@Column(name = "NearshoreGSCPC", length = 10)
	public String getNearshoreGSCPC() {
		return nearshoreGSCPC;
	}
	/**
	 * @param nearshoreGSCPC the nearshoreGSCPC to set
	 */
	public void setNearshoreGSCPC(String nearshoreGSCPC) {
		this.nearshoreGSCPC = nearshoreGSCPC;
	}
	/**
	 * @return the offshoreGSCPC
	 */
	@Column(name = "OffshoreGSCPC", length = 10)
	public String getOffshoreGSCPC() {
		return offshoreGSCPC;
	}
	/**
	 * @param offshoreGSCPC the offshoreGSCPC to set
	 */
	public void setOffshoreGSCPC(String offshoreGSCPC) {
		this.offshoreGSCPC = offshoreGSCPC;
	}

	

	
}