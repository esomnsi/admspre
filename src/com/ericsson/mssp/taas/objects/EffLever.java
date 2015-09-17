package com.ericsson.mssp.taas.objects;

public class EffLever 
{
	private String relType;
	private String testType;
	private double avgNumTestCase;
	private float percentAutoDesign;
	private float testDesignReuse;
	private float testExecCycles;
	private float asIsTestExecCycles;
	
	public String getRelType() {
		return relType;
	}
	public void setRelType(String relType) {
		this.relType = relType;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public double getAvgNumTestCase() {
		return avgNumTestCase;
	}
	public void setAvgNumTestCase(double avgNumTestCase) {
		this.avgNumTestCase = avgNumTestCase;
	}
	public float getPercentAutoDesign() {
		return percentAutoDesign;
	}
	public void setPercentAutoDesign(float percentAutoDesign) {
		this.percentAutoDesign = percentAutoDesign;
	}
	public float getTestDesignReuse() {
		return testDesignReuse;
	}
	public void setTestDesignReuse(float testDesignReuse) {
		this.testDesignReuse = testDesignReuse;
	}
	public float getTestExecCycles() {
		return testExecCycles;
	}
	public void setTestExecCycles(float testExecCycles) {
		this.testExecCycles = testExecCycles;
	}
	public float getAsIsTestExecCycles() {
		return asIsTestExecCycles;
	}
	public void setAsIsTestExecCycles(float asIsTestExecCycles) {
		this.asIsTestExecCycles = asIsTestExecCycles;
	}
}
