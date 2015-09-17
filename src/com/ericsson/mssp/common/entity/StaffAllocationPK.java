package com.ericsson.mssp.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StaffAllocationPK implements Serializable{
	
	private Long solId;
	private Long category;
	
	@Column(name="SOL_ID")
	public Long getSolId() {
		return solId;
	}
	public void setSolId(Long solId) {
		this.solId = solId;
	}
	@Column( name="STAFF_CATEGORY_ID")
	public Long getCategory() {
		return category;
	}
	public void setCategory(Long category) {
		this.category = category;
	}
	
}
