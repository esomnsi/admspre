package com.ericsson.mssp.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * represents the composite primary key of "PEOPLE_STACK" table
 */
@Embeddable
public class PeopleStackResourceDetailPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long solId;
	private Long resourceId;
	
	@Column(name="SOL_ID")
	public Long getSolId() {
		return solId;
	}
	public void setSolId(Long solId) {
		this.solId = solId;
	}
	@Column(name="RESOURCE_ID")
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
}
