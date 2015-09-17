package com.ericsson.mssp.common.entity;

// Generated Mar 13, 2013 1:33:14 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ScopeOfServicesDefinedBy generated by hbm2java
 */
@Entity
@Table(name = "ScopeOfServicesDefinedBy")
public class ScopeOfServicesDefinedBy implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8504645784568837128L;
    private Integer scopeOfServicesDefinedById;
    private String name;
    private String description;
   
    public ScopeOfServicesDefinedBy() {
    }

    public ScopeOfServicesDefinedBy(String name, String description,
	    Set<OpportunityDetail> opportunityDetails) {
	this.name = name;
	this.description = description;
	}

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ScopeOfServicesDefinedByID", unique = true, nullable = false)
    public Integer getScopeOfServicesDefinedById() {
	return this.scopeOfServicesDefinedById;
    }

    public void setScopeOfServicesDefinedById(Integer scopeOfServicesDefinedById) {
	this.scopeOfServicesDefinedById = scopeOfServicesDefinedById;
    }

    @Column(name = "Name", length = 30)
    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Column(name = "Description", length = 50)
    public String getDescription() {
	return this.description;
    }

    public void setDescription(String description) {
	this.description = description;
    }
}
