/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.bean
* File name       		    :  UserBean.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 5, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 5, 2012  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

import java.util.Date;
import java.util.List;

import com.ericsson.mssp.common.entity.ApplicationRole;

/**
 * @author egaivij
 *
 */
public class UserDTO {
	
	private Integer userId;
	private List<ApplicationRole> applicationRoles;
	private String signumId;
	private Date startDate;
	private Date endDate;
	private String firstName;
	private String lastName;
	private String emailId;
	private String contactNumber;
	private String fullName;

	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getSignumId() {
		return signumId;
	}
	public void setSignumId(String signumId) {
		this.signumId = signumId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the applicationRoles
	 */
	public List<ApplicationRole> getApplicationRoles() {
		return applicationRoles;
	}
	/**
	 * @param applicationRoles the applicationRoles to set
	 */
	public void setApplicationRoles(List<ApplicationRole> applicationRoles) {
		this.applicationRoles = applicationRoles;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getFullName() {
		fullName = firstName+" "+lastName;
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = firstName+" "+lastName;
	}

}
