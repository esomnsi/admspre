package com.ericsson.mssp.common.entity;

// Generated May 7, 2013 8:30:07 AM by Hibernate Tools 3.4.0.CR1

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
 * TtpartitionName generated by hbm2java
 */
@Entity
@Table(name = "TTPartitionName")
public class TTPartitionName implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7097751665750275314L;
    private Integer ttpartitionNameId;
    private TTPlanning ttplanning;
    private String ttpartitionName;
    private Float ttpartitionFte;
    private Set<TTPartitionDetail> ttpartitionDetails = new HashSet<TTPartitionDetail>(
	    0);
    private Set<TTOnOffRatio> ttonOffRatios = new HashSet<TTOnOffRatio>(0);
    private Set<TTSummaryStaging> ttsummaryStagings = new HashSet<TTSummaryStaging>(
	    0);

    public TTPartitionName() {
    }

    public TTPartitionName(TTPlanning ttplanning, String ttpartitionName,
	    Float ttpartitionFte, Set<TTPartitionDetail> ttpartitionDetails,
	    Set<TTOnOffRatio> ttonOffRatios,
	    Set<TTSummaryStaging> ttsummaryStagings) {
	this.ttplanning = ttplanning;
	this.ttpartitionName = ttpartitionName;
	this.ttpartitionFte = ttpartitionFte;
	this.ttpartitionDetails = ttpartitionDetails;
	this.ttonOffRatios = ttonOffRatios;
	this.ttsummaryStagings = ttsummaryStagings;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "TTPartitionNameID", unique = true, nullable = false)
    public Integer getTtpartitionNameId() {
	return this.ttpartitionNameId;
    }

    public void setTtpartitionNameId(Integer ttpartitionNameId) {
	this.ttpartitionNameId = ttpartitionNameId;
    }
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TTPlanningID",insertable=false, updatable=false)
    public TTPlanning getTtplanning() {
	return this.ttplanning;
    }

    public void setTtplanning(TTPlanning ttplanning) {
	this.ttplanning = ttplanning;
    }

    @Column(name = "TTPartitionName", length = 50)
    public String getTtpartitionName() {
	return this.ttpartitionName;
    }

    public void setTtpartitionName(String ttpartitionName) {
	this.ttpartitionName = ttpartitionName;
    }

    @Column(name = "TTPartitionFTE", precision = 10)
    public Float getTtpartitionFte() {
	return this.ttpartitionFte;
    }

    public void setTtpartitionFte(Float ttpartitionFte) {
	this.ttpartitionFte = ttpartitionFte;
    }

    @OneToMany(cascade=CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "ttpartitionName")
    public Set<TTPartitionDetail> getTtpartitionDetails() {
	return this.ttpartitionDetails;
    }

    public void setTtpartitionDetails(Set<TTPartitionDetail> ttpartitionDetails) {
	this.ttpartitionDetails = ttpartitionDetails;
    }

    @OneToMany(cascade=CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "ttpartitionName")
    public Set<TTOnOffRatio> getTtonOffRatios() {
	return this.ttonOffRatios;
    }

    public void setTtonOffRatios(Set<TTOnOffRatio> ttonOffRatios) {
	this.ttonOffRatios = ttonOffRatios;
    }

    @OneToMany(cascade=CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "ttpartitionName")
    public Set<TTSummaryStaging> getTtsummaryStagings() {
	return this.ttsummaryStagings;
    }

    public void setTtsummaryStagings(Set<TTSummaryStaging> ttsummaryStagings) {
	this.ttsummaryStagings = ttsummaryStagings;
    }

}
