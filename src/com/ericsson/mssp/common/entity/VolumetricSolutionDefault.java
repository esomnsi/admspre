package com.ericsson.mssp.common.entity;

// Generated Jan 31, 2013 3:33:26 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * VolumetricSolutionDefault generated by hbm2java
 */
@Entity
@Table(name = "VolumetricSolutionDefault")
public class VolumetricSolutionDefault implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6376134846732354303L;
    private Integer volumetricSolutionDefaultId;
    private ServiceScope serviceScope;
    private String columnKeyName;
    private String columnDisplayName;
    private String columnDefaultValue;

    public VolumetricSolutionDefault() {
    }

    public VolumetricSolutionDefault(ServiceScope serviceScope) {
	this.serviceScope = serviceScope;
    }

    public VolumetricSolutionDefault(ServiceScope serviceScope,
	    String columnKeyName, String columnDisplayName,
	    String columnDefaultValue) {
	this.serviceScope = serviceScope;
	this.columnKeyName = columnKeyName;
	this.columnDisplayName = columnDisplayName;
	this.columnDefaultValue = columnDefaultValue;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "VolumetricSolutionDefaultID", unique = true, nullable = false)
    public Integer getVolumetricSolutionDefaultId() {
	return this.volumetricSolutionDefaultId;
    }

    public void setVolumetricSolutionDefaultId(
	    Integer volumetricSolutionDefaultId) {
	this.volumetricSolutionDefaultId = volumetricSolutionDefaultId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ServiceScopeID", nullable = false)
    public ServiceScope getServiceScope() {
	return this.serviceScope;
    }

    public void setServiceScope(ServiceScope serviceScope) {
	this.serviceScope = serviceScope;
    }

    @Column(name = "ColumnKeyName", length = 50)
    public String getColumnKeyName() {
	return this.columnKeyName;
    }

    public void setColumnKeyName(String columnKeyName) {
	this.columnKeyName = columnKeyName;
    }

    @Column(name = "ColumnDisplayName", length = 100)
    public String getColumnDisplayName() {
	return this.columnDisplayName;
    }

    public void setColumnDisplayName(String columnDisplayName) {
	this.columnDisplayName = columnDisplayName;
    }

    @Column(name = "ColumnDefaultValue", length = 50)
    public String getColumnDefaultValue() {
	return this.columnDefaultValue;
    }

    public void setColumnDefaultValue(String columnDefaultValue) {
	this.columnDefaultValue = columnDefaultValue;
    }

}