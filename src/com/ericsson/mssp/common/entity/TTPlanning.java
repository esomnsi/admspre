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
 * Ttplanning generated by hbm2java
 */
@Entity
@Table(name = "TTPlanning")
public class TTPlanning implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2648458813840882046L;
    private Integer ttplanningId;
    private Solution solution;
    private Integer noofPartition;
    private Set<TTSummary> ttsummaries = new HashSet<TTSummary>(0);
    private Set<TTPartitionName> ttpartitionNames = new HashSet<TTPartitionName>(
	    0);
    private Set<TTJobRoleDistribution> ttjobRoleDistributions = new HashSet<TTJobRoleDistribution>(
	    0);

    public TTPlanning() {
    }

    public TTPlanning(Solution solution, Integer noofPartition,
	    Set<TTSummary> ttsummaries, Set<TTPartitionName> ttpartitionNames,
	    Set<TTJobRoleDistribution> ttjobRoleDistributions) {
	this.solution = solution;
	this.noofPartition = noofPartition;
	this.ttsummaries = ttsummaries;
	this.ttpartitionNames = ttpartitionNames;
	this.ttjobRoleDistributions = ttjobRoleDistributions;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "TTPlanningID", unique = true, nullable = false)
    public Integer getTtplanningId() {
	return this.ttplanningId;
    }

    public void setTtplanningId(Integer ttplanningId) {
	this.ttplanningId = ttplanningId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SolutionID")
    public Solution getSolution() {
	return this.solution;
    }

    public void setSolution(Solution solution) {
	this.solution = solution;
    }

    @Column(name = "NoofPartition")
    public Integer getNoofPartition() {
	return this.noofPartition;
    }

    public void setNoofPartition(Integer noofPartition) {
	this.noofPartition = noofPartition;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ttplanning")
    public Set<TTSummary> getTtsummaries() {
	return this.ttsummaries;
    }

    public void setTtsummaries(Set<TTSummary> ttsummaries) {
	this.ttsummaries = ttsummaries;
    }

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "TTPlanningID",referencedColumnName="TTPlanningID" ,nullable = false)
    public Set<TTPartitionName> getTtpartitionNames() {
	return this.ttpartitionNames;
    }

    public void setTtpartitionNames(Set<TTPartitionName> ttpartitionNames) {
	this.ttpartitionNames = ttpartitionNames;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ttplanning")
    public Set<TTJobRoleDistribution> getTtjobRoleDistributions() {
	return this.ttjobRoleDistributions;
    }

    public void setTtjobRoleDistributions(
	    Set<TTJobRoleDistribution> ttjobRoleDistributions) {
	this.ttjobRoleDistributions = ttjobRoleDistributions;
    }

}
