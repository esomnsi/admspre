package com.ericsson.mssp.taas.objects;

public class ComplexityFactor 
{
	private double ratio;
	private String factor;
	private double testDesign;
	private double testExec;
	private double autoScripting;
	
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	public String getFactor() {
		return factor;
	}
	public void setFactor(String factor) {
		this.factor = factor;
	}
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
	public double getAutoScripting() {
		return autoScripting;
	}
	public void setAutoScripting(double autoScripting) {
		this.autoScripting = autoScripting;
	}
}
