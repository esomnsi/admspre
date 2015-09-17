package com.ericsson.mssp.solution.forms;

import java.util.List;

import com.ericsson.mssp.common.dto.SolutionTestingAsAserviceDTO;
import com.ericsson.mssp.common.entity.GenericTestingInputs;
import com.ericsson.mssp.common.entity.GenericTestingOverhead;
import com.ericsson.mssp.common.entity.RegressionLever;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.TestEffReduction;
import com.ericsson.mssp.taas.dto.EffLeverDTO;

public class TaasForm 
{
	private List<GenericTestingInputs> genTestInputList;
	private List<GenericTestingOverhead> genTestOverheadList;
	private List<RegressionLever> regLeverList;
	private List<EffLeverDTO> majEffLeverList;
	private List<EffLeverDTO> minEffLeverList;
	private List<TestEffReduction> testEffRedList;
	private List<SolutionTestingAsAserviceDTO> serviceList;
	private Integer solution;
	private Integer oppScopeID;
	private Integer defaultPercentOfServiceElementEffort;
	
	public List<GenericTestingInputs> getGenTestInputList() {
		return genTestInputList;
	}
	public void setGenTestInputList(List<GenericTestingInputs> genTestInputList) {
		this.genTestInputList = genTestInputList;
	}
	public List<GenericTestingOverhead> getGenTestOverheadList() {
		return genTestOverheadList;
	}
	public void setGenTestOverheadList(
			List<GenericTestingOverhead> genTestOverheadList) {
		this.genTestOverheadList = genTestOverheadList;
	}
	public List<RegressionLever> getRegLeverList() {
		return regLeverList;
	}
	public void setRegLeverList(List<RegressionLever> regLeverList) {
		this.regLeverList = regLeverList;
	}
	public List<EffLeverDTO> getMajEffLeverList() {
		return majEffLeverList;
	}
	public void setMajEffLeverList(List<EffLeverDTO> majEffLeverList) {
		this.majEffLeverList = majEffLeverList;
	}
	public List<EffLeverDTO> getMinEffLeverList() {
		return minEffLeverList;
	}
	public void setMinEffLeverList(List<EffLeverDTO> minEffLeverList) {
		this.minEffLeverList = minEffLeverList;
	}
	public List<TestEffReduction> getTestEffRedList() {
		return testEffRedList;
	}
	public void setTestEffRedList(List<TestEffReduction> testEffRedList) {
		this.testEffRedList = testEffRedList;
	}
	public Integer getSolution() {
		return solution;
	}
	public void setSolution(Integer solution) {
		this.solution = solution;
	}
	public Integer getOppScopeID() {
		return oppScopeID;
	}
	public void setOppScopeID(Integer oppScopeID) {
		this.oppScopeID = oppScopeID;
	}
	public List<SolutionTestingAsAserviceDTO> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<SolutionTestingAsAserviceDTO> serviceList) {
		this.serviceList = serviceList;
	}
	public Integer getDefaultPercentOfServiceElementEffort() {
		return defaultPercentOfServiceElementEffort;
	}
	public void setDefaultPercentOfServiceElementEffort(
			Integer defaultPercentOfServiceElementEffort) {
		this.defaultPercentOfServiceElementEffort = defaultPercentOfServiceElementEffort;
	}	

	
}
