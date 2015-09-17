package com.ericsson.mssp.volumetric.forms;

public class ApplicationMaintenenceOutput {
	
	
	private String priority;
	private String totalYearlyTicket;
	private String baseEffortHrs;
	private String baseEffortPd;
	private String customerBaseFactor;
	private String 	nodesFactor;
	private String callVolumeFactor;
	private String totalPD;
	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getTotalYearlyTicket() {
		return totalYearlyTicket;
	}
	public void setTotalYearlyTicket(String totalYearlyTicket) {
		this.totalYearlyTicket = totalYearlyTicket;
	}
	public String getBaseEffortHrs() {
		return baseEffortHrs;
	}
	public void setBaseEffortHrs(String baseEffortHrs) {
		this.baseEffortHrs = baseEffortHrs;
	}
	public String getBaseEffortPd() {
		return baseEffortPd;
	}
	public void setBaseEffortPd(String baseEffortPd) {
		this.baseEffortPd = baseEffortPd;
	}
	public String getCustomerBaseFactor() {
		return customerBaseFactor;
	}
	public void setCustomerBaseFactor(String customerBaseFactor) {
		this.customerBaseFactor = customerBaseFactor;
	}
	
	public String getNodesFactor() {
		return nodesFactor;
	}
	public void setNodesFactor(String nodesFactor) {
		this.nodesFactor = nodesFactor;
	}
	public String getCallVolumeFactor() {
		return callVolumeFactor;
	}
	public void setCallVolumeFactor(String callVolumeFactor) {
		this.callVolumeFactor = callVolumeFactor;
	}
	public String getTotalPD() {
		return totalPD;
	}
	public void setTotalPD(String totalPD) {
		this.totalPD = totalPD;
	}
}
