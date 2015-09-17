package com.ericsson.mssp.common.entity;

// Generated May 7, 2013 8:30:07 AM by Hibernate Tools 3.4.0.CR1

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
 * TtonOffRatio generated by hbm2java
 */
@Entity
@Table(name = "TTOnOffRatio")
public class TTOnOffRatio implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7740545862801826495L;
    private Integer ttonOffRatioId;
    private TTPartitionName ttpartitionName;
    private Float planOffFte;
    private Float planOnFte;
    private Float planOffPc;
    private Float planOnPc;
    private Float learnOffFte;
    private Float learnOnFte;
    private Float learnOffPc;
    private Float learnOnPc;
    private Float assistOffFte;
    private Float assistOnFte;
    private Float assistOffPc;
    private Float assistOnPc;
    private Float performOffFte;
    private Float performOnFte;
    private Float performOffPc;
    private Float performOnPc;
    private Float deliverOffFte;
    private Float deliverOnFte;
    private Float deliverOffPc;
    private Float deliverOnPc;

    public TTOnOffRatio() {
    }

    public TTOnOffRatio(TTPartitionName ttpartitionName, Float planOffFte,
	    Float planOnFte, Float planOffPc, Float planOnPc,
	    Float learnOffFte, Float learnOnFte, Float learnOffPc,
	    Float learnOnPc, Float assistOffFte, Float assistOnFte,
	    Float assistOffPc, Float assistOnPc, Float performOffFte,
	    Float performOnFte, Float performOffPc, Float performOnPc,
	    Float deliverOffFte, Float deliverOnFte, Float deliverOffPc,
	    Float deliverOnPc) {
	this.ttpartitionName = ttpartitionName;
	this.planOffFte = planOffFte;
	this.planOnFte = planOnFte;
	this.planOffPc = planOffPc;
	this.planOnPc = planOnPc;
	this.learnOffFte = learnOffFte;
	this.learnOnFte = learnOnFte;
	this.learnOffPc = learnOffPc;
	this.learnOnPc = learnOnPc;
	this.assistOffFte = assistOffFte;
	this.assistOnFte = assistOnFte;
	this.assistOffPc = assistOffPc;
	this.assistOnPc = assistOnPc;
	this.performOffFte = performOffFte;
	this.performOnFte = performOnFte;
	this.performOffPc = performOffPc;
	this.performOnPc = performOnPc;
	this.deliverOffFte = deliverOffFte;
	this.deliverOnFte = deliverOnFte;
	this.deliverOffPc = deliverOffPc;
	this.deliverOnPc = deliverOnPc;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "TTOnOffRatioID", unique = true, nullable = false)
    public Integer getTtonOffRatioId() {
	return this.ttonOffRatioId;
    }

    public void setTtonOffRatioId(Integer ttonOffRatioId) {
	this.ttonOffRatioId = ttonOffRatioId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TTPartitionNameID")
    public TTPartitionName getTtpartitionName() {
	return this.ttpartitionName;
    }

    public void setTtpartitionName(TTPartitionName ttpartitionName) {
	this.ttpartitionName = ttpartitionName;
    }

    @Column(name = "PlanOffFTE", precision = 10)
    public Float getPlanOffFte() {
	return this.planOffFte;
    }

    public void setPlanOffFte(Float planOffFte) {
	this.planOffFte = planOffFte;
    }

    @Column(name = "PlanOnFTE", precision = 10)
    public Float getPlanOnFte() {
	return this.planOnFte;
    }

    public void setPlanOnFte(Float planOnFte) {
	this.planOnFte = planOnFte;
    }

    @Column(name = "PlanOffPC", precision = 10)
    public Float getPlanOffPc() {
	return this.planOffPc;
    }

    public void setPlanOffPc(Float planOffPc) {
	this.planOffPc = planOffPc;
    }

    @Column(name = "PlanOnPC", precision = 10)
    public Float getPlanOnPc() {
	return this.planOnPc;
    }

    public void setPlanOnPc(Float planOnPc) {
	this.planOnPc = planOnPc;
    }

    @Column(name = "LearnOffFTE", precision = 10)
    public Float getLearnOffFte() {
	return this.learnOffFte;
    }

    public void setLearnOffFte(Float learnOffFte) {
	this.learnOffFte = learnOffFte;
    }

    @Column(name = "LearnOnFTE", precision = 10)
    public Float getLearnOnFte() {
	return this.learnOnFte;
    }

    public void setLearnOnFte(Float learnOnFte) {
	this.learnOnFte = learnOnFte;
    }

    @Column(name = "LearnOffPC", precision = 10)
    public Float getLearnOffPc() {
	return this.learnOffPc;
    }

    public void setLearnOffPc(Float learnOffPc) {
	this.learnOffPc = learnOffPc;
    }

    @Column(name = "LearnOnPC", precision = 10)
    public Float getLearnOnPc() {
	return this.learnOnPc;
    }

    public void setLearnOnPc(Float learnOnPc) {
	this.learnOnPc = learnOnPc;
    }

    @Column(name = "AssistOffFTE", precision = 10)
    public Float getAssistOffFte() {
	return this.assistOffFte;
    }

    public void setAssistOffFte(Float assistOffFte) {
	this.assistOffFte = assistOffFte;
    }

    @Column(name = "AssistOnFTE", precision = 10)
    public Float getAssistOnFte() {
	return this.assistOnFte;
    }

    public void setAssistOnFte(Float assistOnFte) {
	this.assistOnFte = assistOnFte;
    }

    @Column(name = "AssistOffPC", precision = 10)
    public Float getAssistOffPc() {
	return this.assistOffPc;
    }

    public void setAssistOffPc(Float assistOffPc) {
	this.assistOffPc = assistOffPc;
    }

    @Column(name = "AssistOnPC", precision = 10)
    public Float getAssistOnPc() {
	return this.assistOnPc;
    }

    public void setAssistOnPc(Float assistOnPc) {
	this.assistOnPc = assistOnPc;
    }

    @Column(name = "PerformOffFTE", precision = 10)
    public Float getPerformOffFte() {
	return this.performOffFte;
    }

    public void setPerformOffFte(Float performOffFte) {
	this.performOffFte = performOffFte;
    }

    @Column(name = "PerformOnFTE", precision = 10)
    public Float getPerformOnFte() {
	return this.performOnFte;
    }

    public void setPerformOnFte(Float performOnFte) {
	this.performOnFte = performOnFte;
    }

    @Column(name = "PerformOffPC", precision = 10)
    public Float getPerformOffPc() {
	return this.performOffPc;
    }

    public void setPerformOffPc(Float performOffPc) {
	this.performOffPc = performOffPc;
    }

    @Column(name = "PerformOnPC", precision = 10)
    public Float getPerformOnPc() {
	return this.performOnPc;
    }

    public void setPerformOnPc(Float performOnPc) {
	this.performOnPc = performOnPc;
    }

    @Column(name = "DeliverOffFTE", precision = 10)
    public Float getDeliverOffFte() {
	return this.deliverOffFte;
    }

    public void setDeliverOffFte(Float deliverOffFte) {
	this.deliverOffFte = deliverOffFte;
    }

    @Column(name = "DeliverOnFTE", precision = 10)
    public Float getDeliverOnFte() {
	return this.deliverOnFte;
    }

    public void setDeliverOnFte(Float deliverOnFte) {
	this.deliverOnFte = deliverOnFte;
    }

    @Column(name = "DeliverOffPC", precision = 10)
    public Float getDeliverOffPc() {
	return this.deliverOffPc;
    }

    public void setDeliverOffPc(Float deliverOffPc) {
	this.deliverOffPc = deliverOffPc;
    }

    @Column(name = "DeliverOnPC", precision = 10)
    public Float getDeliverOnPc() {
	return this.deliverOnPc;
    }

    public void setDeliverOnPc(Float deliverOnPc) {
	this.deliverOnPc = deliverOnPc;
    }

}
