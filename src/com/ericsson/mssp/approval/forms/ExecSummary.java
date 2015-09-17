package com.ericsson.mssp.approval.forms;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExecSummary {

	private List<Row> 	laborCost = new ArrayList<Row>();
	private List<Row> 	travelCost = new ArrayList<Row>();
	private List<Row> 	otherCost = new ArrayList<Row>();
	private String 		invoiceType;
	private String 		currency;
	private String 		region;
	private List<Float> grandTotalRevenue = new ArrayList<Float>();
	private List<Float> grandTotalCost = new ArrayList<Float>();
	private List<Float> grandTotalEffort = new ArrayList<Float>();
	private List<Float> totalNLC = new ArrayList<Float>();
	
	public List<Row> getLaborCost() {
		return laborCost;
	}

	public void setLaborCost(List<Row> laborCost) {
		this.laborCost = laborCost;
	}

	public List<Row> getTravelCost() {
		return travelCost;
	}

	public void setTravelCost(List<Row> travelCost) {
		this.travelCost = travelCost;
	}

	public List<Row> getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(List<Row> otherCost) {
		this.otherCost = otherCost;
	}
	
	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
    
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<Float> getGrandTotalRevenue() {
		float tot;
		for (int i = 0; i < otherCost.size(); i++) {
			tot = 0f;
			tot += laborCost.get(i).getRevenueValue()
					+ travelCost.get(i).revenueValue
					+ otherCost.get(i).revenueValue;
			grandTotalRevenue.add(tot);
		}
		return grandTotalRevenue;
	}

	public List<Float> getGrandTotalCost() {
		float tot;
		for (int i = 0; i < otherCost.size(); i++) {
			tot = 0f;
			tot += laborCost.get(i).costValue + travelCost.get(i).costValue
					+ otherCost.get(i).costValue;
			grandTotalCost.add(tot);
		}
		return grandTotalCost;
	}

	public List<Float> getTotalNLC(){
		float tot;
		 DecimalFormat f = new DecimalFormat("##.00");
		 for(int i=0;i<otherCost.size();i++){
			 tot = 0f;
			 tot += travelCost.get(i).costValue + otherCost.get(i).costValue;
			 tot = Float.valueOf(f.format(tot));
			 totalNLC.add(tot);
		 }
		 
		 return totalNLC;
	}
	
	public List<Float> getGrandTotalEffort() {
		float tot;
		for (int i = 0; i < otherCost.size(); i++) {
			tot = 0f;
			tot += laborCost.get(i).fteValue + travelCost.get(i).fteValue
					+ otherCost.get(i).fteValue;
			grandTotalEffort.add(tot);
		}
		return grandTotalEffort;
	}

	public class Row {
		private int year;
		private float revenueValue;
		private float costValue;
		private float fteValue;

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public float getRevenueValue() {
			float multFactor=1f;
			if("PO".equalsIgnoreCase(invoiceType)){
				if("RINA".equalsIgnoreCase(region)){
					multFactor=1.05f;
				}else{
					multFactor=1.15f;
				}
			}
			revenueValue=costValue*multFactor;
			return revenueValue;
		}

		public void setRvenueValue(float revenueValue) {
			this.revenueValue = revenueValue;
		}
		
		public float getFteValue() {
			return fteValue;
		}

		public void setFteValue(float fteValue) {
			this.fteValue = fteValue;
		}

		public float getCostValue() {
			return costValue;
		}

		public void setCostValue(float costValue) {
			this.costValue = costValue;
		}

	}

}
