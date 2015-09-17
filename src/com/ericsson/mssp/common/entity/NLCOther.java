package com.ericsson.mssp.common.entity;


import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the NonLaborCostOther database table.
 * 
 */
@Entity
@Table(name="NLCOther")
public class NLCOther implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name="NLCOtherID" , unique = true, nullable = false)
	private int nlcOtherID;

	@Column(name="ConnClientToIndia")
	private float connClientToIndia;

	@Column(name="ConnIndiaToDev")
	private float connIndiaToDev;

	@Column(name="Month")
	private int month;

	@Column(name="MsdpCost")
	private float msdpCost;

	@Column(name="TotalOtherCost")
	private float totalOtherCost;

	@Column(name="Year")
	private int year;
	
	@Column(name="OffshoreFTE")
	private float offshoreFTE;

	//bi-directional many-to-one association to InputParam
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="NLCInputParamID")
	private NLCInputParam inputParam;

    public NLCOther() {
    }

	public int getNlcOtherID() {
		return nlcOtherID;
	}

	public void setNlcOtherID(int nlcOtherID) {
		this.nlcOtherID = nlcOtherID;
	}



	public float getConnClientToIndia() {
		return this.connClientToIndia;
	}

	public void setConnClientToIndia(float connClientToIndia) {
		this.connClientToIndia = connClientToIndia;
	}

	public float getConnIndiaToDev() {
		return this.connIndiaToDev;
	}

	public void setConnIndiaToDev(float connIndiaToDev) {
		this.connIndiaToDev = connIndiaToDev;
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public float getMsdpCost() {
		return this.msdpCost;
	}

	public void setMsdpCost(float msdpCost) {
		this.msdpCost = msdpCost;
	}

	
	public float getTotalOtherCost() {
		return this.totalOtherCost;
	}

	public void setTotalOtherCost(float totalOtherCost) {
		this.totalOtherCost = totalOtherCost;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public float getOffshoreFTE() {
		return offshoreFTE;
	}

	public void setOffshoreFTE(float offshoreFTE) {
		this.offshoreFTE = offshoreFTE;
	}

	public NLCInputParam getInputParam() {
		return inputParam;
	}

	public void setInputParam(NLCInputParam inputParam) {
		this.inputParam = inputParam;
	}
	
}