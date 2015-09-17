package com.ericsson.mssp.common.entity;

// Generated Feb 12, 2013 11:09:46 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tntdetail generated by hbm2java
 */
@Entity
@Table(name = "TNTDetail")
public class TNTDetail implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8327005071138409217L;
    private Integer tNTDetailID;
    private Solution solution;
    private String partitionName;
    private Float totalFte;
    private Date startDate;
    private Date endDate;
    private Float ftecount;
    private String category;
    private Float ftepercent;

    public TNTDetail() {
    }

    public TNTDetail(Solution solution, String partitionName, Float totalFte,
	    Date startDate, Date endDate, Float ftecount, String category,
	    Float ftepercent) {
	this.solution = solution;
	this.partitionName = partitionName;
	this.totalFte = totalFte;
	this.startDate = startDate;
	this.endDate = endDate;
	this.ftecount = ftecount;
	this.category = category;
	this.ftepercent = ftepercent;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "TNTDetailID", unique = true, nullable = false)
    public Integer getTntdetailId() {
	return this.tNTDetailID;
    }

    public void setTntdetailId(Integer tntdetailId) {
	this.tNTDetailID = tntdetailId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SolutionID")
    public Solution getSolution() {
	return this.solution;
    }

    public void setSolution(Solution solution) {
	this.solution = solution;
    }

    @Column(name = "PartitionName", length = 50)
    public String getPartitionName() {
	return this.partitionName;
    }

    public void setPartitionName(String partitionName) {
	this.partitionName = partitionName;
    }

    @Column(name = "TotalFTE", precision = 10)
    public Float getTotalFte() {
	return this.totalFte;
    }

    public void setTotalFte(Float totalFte) {
	this.totalFte = totalFte;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "StartDate", length = 10)
    public Date getStartDate() {
	return this.startDate;
    }

    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "EndDate", length = 10)
    public Date getEndDate() {
	return this.endDate;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    @Column(name = "FTECount", precision = 10)
    public Float getFtecount() {
	return this.ftecount;
    }

    public void setFtecount(Float ftecount) {
	this.ftecount = ftecount;
    }

    @Column(name = "Category", length = 10)
    public String getCategory() {
	return this.category;
    }

    public void setCategory(String category) {
	this.category = category;
    }

    @Column(name = "FTEPercent", precision = 10)
    public Float getFtepercent() {
	return this.ftepercent;
    }

    public void setFtepercent(Float ftepercent) {
	this.ftepercent = ftepercent;
    }

}
