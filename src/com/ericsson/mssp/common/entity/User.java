package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "User")
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1737853324359920989L;
	private Integer userId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String contactNumber;
	private List<ApplicationRole> applicationRoles;
	private String signumId;
	private Date startDate;
	private Date endDate;


	public User() {
	}	

		
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "UserID", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	@Column(name = "SignumID", length = 10)
	public String getSignumId() {
		return this.signumId;
	}

	public void setSignumId(String signumId) {
		this.signumId = signumId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "StartDate", length = 19)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EndDate", length = 19)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the applicationRoles
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "UserApplicationRole", joinColumns = { @JoinColumn(name = "UserID") }, 
	inverseJoinColumns = { @JoinColumn(name = "ApplicationRoleID")})
	public List<ApplicationRole> getApplicationRoles() {
		return applicationRoles;
	}

	/**
	 * @param applicationRoles the applicationRoles to set
	 */
	public void setApplicationRoles(List<ApplicationRole> applicationRoles) {
		this.applicationRoles = applicationRoles;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}


	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}


	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
}
