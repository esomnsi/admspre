package com.ericsson.mssp.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;


@Entity
@Table(name = "STAFF_CATEGORY")
//@SecondaryTable(name="STAFF_CATEGORY_ALLOCATION",pkJoinColumns=@PrimaryKeyJoinColumn(name="STAFF_CATEGORY_ID"))
public class StaffCategory implements Serializable{
	
	private String category;
	private Long categoryId;
	
	@Id
	@Column(name="ID")
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	@Column(name="CATEGORY")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
