package com.ericsson.mssp.solution.forms;

import java.util.List;

import com.ericsson.mssp.common.dto.TaasServiceDTO;
import com.ericsson.mssp.common.entity.TestingService;

public class TaasGenericInputForm 
{
	//private List<TestingService> testServList;
	private Integer solution;
	private List<TaasServiceDTO> testServList;
	
	public List<TaasServiceDTO> getTestServList() {
		return testServList;
	}
	public void setTestServList(List<TaasServiceDTO> testServList) {
		this.testServList = testServList;
	}
	
	public Integer getSolution() {
		return solution;
	}
	public void setSolution(Integer solution) {
		this.solution = solution;
	}
	
}
