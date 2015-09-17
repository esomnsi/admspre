package com.ericsson.mssp.approval.forms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SolutionSummary {
	private String deliveryModel;
	private String deliveryType;
	private Date ssd;
	private int offShoreID;
	private int onShoreID;
	private String offShoreLocation;
	private String onShoreLocation;
	private String serviceScopeDefinedBy;
	private String yoyFTE="";
	private String knowledgeTrnsfrInScope;
	private Date KTStartDt;
	private Date KTEndDt;
	private String KTStartDtText;
	private int durationKT;
	private String ktLocation;
	private String pLeverApplied;
	private String inputVolumetricsDefinedBy;
	private Date pLeverAppliedMnth;
	private String ssdText;
	private String pLeverAppliedMnthText;
	public String getDeliveryModel() {
		return deliveryModel;
	}
	public void setDeliveryModel(String deliveryModel) {
		this.deliveryModel = deliveryModel;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public String getOffShoreLocation() {
		return offShoreLocation;
	}
	public void setOffShoreLocation(String offShoreLocation) {
		this.offShoreLocation = offShoreLocation;
	}
	public String getOnShoreLocation() {
		return onShoreLocation;
	}
	public void setOnShoreLocation(String onShoreLocation) {
		this.onShoreLocation = onShoreLocation;
	}
	public String getServiceScopeDefinedBy() {
		return serviceScopeDefinedBy;
	}
	public void setServiceScopeDefinedBy(String serviceScopeDefinedBy) {
		this.serviceScopeDefinedBy = serviceScopeDefinedBy;
	}
	public String getYoyFTE() {
		return yoyFTE;
	}
	public void setYoyFTE(String yoyFTE) {
		this.yoyFTE = yoyFTE;
	}
	public String getKnowledgeTrnsfrInScope() {
		return knowledgeTrnsfrInScope;
	}
	public void setKnowledgeTrnsfrInScope(String knowledgeTrnsfrInScope) {
		this.knowledgeTrnsfrInScope = knowledgeTrnsfrInScope;
	}
	public Date getKTStartDt() {
		return KTStartDt;
	}
	public void setKTStartDt(Date kTStartDt) {
		KTStartDt = kTStartDt;
	}
	public Date getKTEndDt() {
		return KTEndDt;
	}
	public void setKTEndDt(Date kTEndDt) {
		KTEndDt = kTEndDt;
	}
	public String getKTStartDtText() {
		KTStartDtText=formatDateToString(KTStartDt);
		return KTStartDtText;
	}
	public void setKTStartDtText(String kTStartDtText) {
		KTStartDtText = kTStartDtText;
	}

	public int getDurationKT() {
		/*this.durationKT = (int) Math
				.round((new Double(this.KTEndDt.getTime()) - new Double(
						this.KTStartDt.getTime())) / (1000 * 60 * 60 * 24 * 30));*/
		if (this.KTEndDt == null || this.KTStartDt == null) {
			this.durationKT = 0;
		} else {
			Double ed = new Double(this.KTEndDt.getTime());
			Double sd = new Double(this.KTStartDt.getTime());
			Double diff_days = (ed - sd) / (1000 * 60 * 60 * 24);
			Double diff_mnths = diff_days / 30;
			this.durationKT = (int) Math.round(diff_mnths);
		}
		return durationKT;
	}
	
	
	/*public void setDurationKT(int durationKT) {
		this.durationKT = durationKT;
	}*/
	public String getKtLocation() {
		return ktLocation;
	}
	public void setKtLocation(String ktLocation) {
		this.ktLocation = ktLocation;
	}
	public String getpLeverApplied() {
		return pLeverApplied;
	}
	public void setpLeverApplied(String pLeverApplied) {
		this.pLeverApplied = pLeverApplied;
	}
	public String getInputVolumetricsDefinedBy() {
		return inputVolumetricsDefinedBy;
	}
	public void setInputVolumetricsDefinedBy(String inputVolumetricsDefinedBy) {
		this.inputVolumetricsDefinedBy = inputVolumetricsDefinedBy;
	}
	public Date getpLeverAppliedMnth() {
		return pLeverAppliedMnth;
	}
	public void setpLeverAppliedMnth(Date pLeverAppliedMnth) {
		this.pLeverAppliedMnth = pLeverAppliedMnth;
	}
	public Date getSsd() {
		return ssd;
	}
	public void setSsd(Date ssd) {
		this.ssd = ssd;
	}
	public int getOffShoreID() {
		return offShoreID;
	}
	public void setOffShoreID(int offShoreID) {
		this.offShoreID = offShoreID;
	}
	public int getOnShoreID() {
		return onShoreID;
	}
	public void setOnShoreID(int onShoreID) {
		this.onShoreID = onShoreID;
	}
	
	public String getSsdText() {
		 this.ssdText=formatDateToString(ssd);
		 return ssdText;
	}
	
	public String getpLeverAppliedMnthText() {
		 pLeverAppliedMnthText=formatDateToString(pLeverAppliedMnth);
		 return pLeverAppliedMnthText;
	}
	
	private String formatDateToString(Date date)
	{
		if(date != null)
		{
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			return formatter.format(date);
		}
		else
		{
			return "";
		}
		
	}
}
