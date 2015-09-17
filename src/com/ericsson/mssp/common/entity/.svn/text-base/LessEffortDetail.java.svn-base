package com.ericsson.mssp.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LESS_EFFORT")
public class LessEffortDetail implements Serializable{
	private Long solId;
	private double peopleMgmt;
	private double others;
	private String comments;
	@Id
	@Column(name="SOL_ID")
	public Long getSolId() {
		return solId;
	}
	public void setSolId(Long solId) {
		this.solId = solId;
	}
	@Column(name="PEOPLE_MANAGEMENT")
	public double getPeopleMgmt() {
		return peopleMgmt;
	}
	public void setPeopleMgmt(double peopleMgmt) {
		this.peopleMgmt = peopleMgmt;
	}
	@Column(name="OTHERS")
	public double getOthers() {
		return others;
	}
	public void setOthers(double others) {
		this.others = others;
	}
	@Column(name="COMMENTS")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
