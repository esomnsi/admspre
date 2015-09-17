package com.ericsson.mssp.common.entity;


import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;


/**
 * The persistent class for the NLCTravel database table.
 * 
 */
@Entity
@Table(name="NLCTravel")
public class NLCTravel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "NLCTravelID", unique = true, nullable = false)
	private int nlcTravelID;

	/*@Column(name="CalendarDays")
	private float calendarDays;*/

	@Column(name="LongTermVisa")
	private float longTermVisa;

	@Column(name="LongTermVisaCost")
	private float longTermVisaCost;

	@Column(name="MoblileCommCost")
	private float moblileCommCost;

	@Column(name="Month")
	private int month;

	@Column(name="OnsiteConveyanceCost")
	private float onsiteConveyanceCost;

	@Column(name="OnsiteFSI")
	private float onsiteFSI;

	@Column(name="OnsiteHotelCost")
	private float onsiteHotelCost;

	@Column(name="OnsitePerDiem")
	private float onsitePerDiem;

	@Column(name="OnshoreFTE")
	private float onshoreFTE;

	@Column(name="RelocationCost")
	private float relocationCost;

	@Column(name="RoundTripCost")
	private float roundTripCost;

	@Column(name="RoundTrips")
	private float roundTrips;

	@Column(name="ShortTermVisa")
	private float ShortTermVisa;
	
	@Column(name="ShortTermVisaCost")
	private float shortTermVisaCost;

	@Column(name="TotalCost")
	private float totalCost;

	@Column(name="Type")
	private String type;

	/*@Column(name="WorkingDays")
	private float workingDays;*/

	@Column(name="Year")
	private int year;

	//bi-directional many-to-one association to InputParam
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="NLCInputParamID")
    private NLCInputParam inputParam;

    public NLCTravel() {
    }

	

	/*public float getCalendarDays() {
		return this.calendarDays;
	}

	public void setCalendarDays(float calendarDays) {
		this.calendarDays = calendarDays;
	}*/

	public int getNlcTravelID() {
		return nlcTravelID;
	}



	public void setNlcTravelID(int nlcTravelID) {
		this.nlcTravelID = nlcTravelID;
	}



	public float getLongTermVisa() {
		return this.longTermVisa;
	}

	public void setLongTermVisa(float longTermVisa) {
		this.longTermVisa = longTermVisa;
	}

	public float getLongTermVisaCost() {
		return this.longTermVisaCost;
	}

	public void setLongTermVisaCost(float longTermVisaCost) {
		this.longTermVisaCost = longTermVisaCost;
	}

	public float getMoblileCommCost() {
		return this.moblileCommCost;
	}

	public void setMoblileCommCost(float moblileCommCost) {
		this.moblileCommCost = moblileCommCost;
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public float getOnsiteConveyanceCost() {
		return this.onsiteConveyanceCost;
	}

	public void setOnsiteConveyanceCost(float onsiteConveyanceCost) {
		this.onsiteConveyanceCost = onsiteConveyanceCost;
	}

	public float getOnsiteFSI() {
		return this.onsiteFSI;
	}

	public void setOnsiteFSI(float onsiteFSI) {
		this.onsiteFSI = onsiteFSI;
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

    

	public float getOnshoreFTE() {
		return onshoreFTE;
	}
	public void setOnshoreFTE(float onshoreFTE) {
		this.onshoreFTE = onshoreFTE;
	}



	public float getRelocationCost() {
		return this.relocationCost;
	}

	public void setRelocationCost(float relocationCost) {
		this.relocationCost = relocationCost;
	}

	public float getRoundTripCost() {
		return this.roundTripCost;
	}

	public void setRoundTripCost(float roundTripCost) {
		this.roundTripCost = roundTripCost;
	}

	public float getRoundTrips() {
		return this.roundTrips;
	}

	public void setRoundTrips(float roundTrips) {
		this.roundTrips = roundTrips;
	}
	
	public float getShortTermVisa() {
		return ShortTermVisa;
	}

	public void setShortTermVisa(float shortTermVisa) {
		ShortTermVisa = shortTermVisa;
	}

	public float getShortTermVisaCost() {
		return this.shortTermVisaCost;
	}

	public void setShortTermVisaCost(float shortTermVisaCost) {
		this.shortTermVisaCost = shortTermVisaCost;
	}

	public float getTotalCost() {
		return this.totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*public float getWorkingDays() {
		return this.workingDays;
	}

	public void setWorkingDays(float workingDays) {
		this.workingDays = workingDays;
	}*/

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}



	public NLCInputParam getInputParam() {
		return inputParam;
	}



	public void setInputParam(NLCInputParam inputParam) {
		this.inputParam = inputParam;
	}

	
}