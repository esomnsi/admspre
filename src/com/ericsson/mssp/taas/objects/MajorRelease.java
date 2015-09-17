package com.ericsson.mssp.taas.objects;

public class MajorRelease 
{
	private double testDesign;
	private double testExec;
	private double testPlRqAnAtEx;
	private double testMgmt;
	private double totalEffort;
	
	public double getTestDesign() {
		return testDesign;
	}
	public void setTestDesign(double testDesign) {
		this.testDesign = testDesign;
	}
	public double getTestExec() {
		return testExec;
	}
	public void setTestExec(double testExec) {
		this.testExec = testExec;
	}
	public double getTestPlRqAnAtEx() {
		return testPlRqAnAtEx;
	}
	public void setTestPlRqAnAtEx(double testPlRqAnAtEx) {
		this.testPlRqAnAtEx = testPlRqAnAtEx;
	}
	public double getTestMgmt() {
		return testMgmt;
	}
	public void setTestMgmt(double testMgmt) {
		this.testMgmt = testMgmt;
	}
	public double getTotalEffort() {
		return totalEffort;
	}
	public void setTotalEffort(double totalEffort) {
		this.totalEffort = totalEffort;
	}
}
