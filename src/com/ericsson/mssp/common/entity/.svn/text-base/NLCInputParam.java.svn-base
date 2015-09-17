package com.ericsson.mssp.common.entity;


import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import java.util.Set;


/**
 * The persistent class for the InputParams database table.
 * 
 */
@Entity
@Table(name="NLCInputParam")
public class NLCInputParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "NLCInputParamID", unique = true, nullable = false)
	private int nlcInputParamID;

	@Column(name="MobileCommCost")
	private float mobileCommCost;

	private float MSDPCost;

	private int MSDPPlatformUsed;

	@Column(name="OneLongTermVisaCost")
	private float oneLongTermVisaCost;

	@Column(name="OneRoundTripCost")
	private float oneRoundTripCost;

	@Column(name="OneShortTermVisaCost")
	private float oneShortTermVisaCost;

	@Column(name="OnsiteConveyanceCost")
	private float onsiteConveyanceCost;

	@Column(name="OnsiteHotelCost")
	private float onsiteHotelCost;

	@Column(name="OnsitePerDiem")
	private float onsitePerDiem;
	
	@Column(name="InvoicingType")
	private String invoicingType;

	@Column(name="SolutionID")
	private int solutionID;

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="inputParam")
	private Set<NLCOther> nlcOthers;

	@OneToMany(fetch = FetchType.LAZY,mappedBy="inputParam")
	private Set<NLCTravel> nlcTravels;

    public NLCInputParam() {
    }

	



	public int getNlcInputParamID() {
		return nlcInputParamID;
	}

	public void setNlcInputParamID(int nlcInputParamID) {
		this.nlcInputParamID = nlcInputParamID;
	}

	public float getMobileCommCost() {
		return this.mobileCommCost;
	}

	public void setMobileCommCost(float mobileCommCost) {
		this.mobileCommCost = mobileCommCost;
	}

	public float getMSDPCost() {
		return this.MSDPCost;
	}

	public void setMSDPCost(float MSDPCost) {
		this.MSDPCost = MSDPCost;
	}

	public int getMSDPPlatformUsed() {
		return this.MSDPPlatformUsed;
	}

	public void setMSDPPlatformUsed(int MSDPPlatformUsed) {
		this.MSDPPlatformUsed = MSDPPlatformUsed;
	}

	public float getOneLongTermVisaCost() {
		return this.oneLongTermVisaCost;
	}

	public void setOneLongTermVisaCost(float oneLongTermVisaCost) {
		this.oneLongTermVisaCost = oneLongTermVisaCost;
	}

	public float getOneRoundTripCost() {
		return this.oneRoundTripCost;
	}

	public void setOneRoundTripCost(float oneRoundTripCost) {
		this.oneRoundTripCost = oneRoundTripCost;
	}

	public float getOneShortTermVisaCost() {
		return this.oneShortTermVisaCost;
	}

	public void setOneShortTermVisaCost(float oneShortTermVisaCost) {
		this.oneShortTermVisaCost = oneShortTermVisaCost;
	}

	public float getOnsiteConveyanceCost() {
		return this.onsiteConveyanceCost;
	}

	public void setOnsiteConveyanceCost(float onsiteConveyanceCost) {
		this.onsiteConveyanceCost = onsiteConveyanceCost;
	}

	public float getOnsiteHotelCost() {
		return this.onsiteHotelCost;
	}

	public void setOnsiteHotelCost(float onsiteHotelCost) {
		this.onsiteHotelCost = onsiteHotelCost;
	}

	public float getOnsitePerDiem() {
		return this.onsitePerDiem;
	}

	public void setOnsitePerDiem(float onsitePerDiem) {
		this.onsitePerDiem = onsitePerDiem;
	}

	public int getSolutionID() {
		return this.solutionID;
	}

	public void setSolutionID(int solutionID) {
		this.solutionID = solutionID;
	}
	public String getInvoicingType() {
		return invoicingType;
	}
	public void setInvoicingType(String invoicingType) {
		this.invoicingType = invoicingType;
	}
	
	public Set<NLCOther> getNlcOthers() {
		return nlcOthers;
	}
	public void setNlcOthers(Set<NLCOther> nlcOthers) {
		this.nlcOthers = nlcOthers;
	}

	public Set<NLCTravel> getNlcTravels() {
		return nlcTravels;
	}

	public void setNlcTravels(Set<NLCTravel> nlcTravels) {
		this.nlcTravels = nlcTravels;
	}
	
}