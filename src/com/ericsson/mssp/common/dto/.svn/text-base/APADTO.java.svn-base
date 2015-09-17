package com.ericsson.mssp.common.dto;

import java.util.List;

import org.springframework.util.AutoPopulatingList;

import com.ericsson.mssp.common.entity.Apacomplexity;
import com.ericsson.mssp.common.entity.Apaweightage;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.SolutionAPA;

public class APADTO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;
	
	private Integer solutionId;
	private Apaweightage apaWeightage;
	private List<OpportunityScope> oppScopeList;
	//private List<SolutionAPA> solutionAPAList = new ArrayList<SolutionAPA>();
	private AutoPopulatingList<SolutionAPA> solutionAPAList = null;
	private AutoPopulatingList<Apacomplexity> apaComplexityList = null;
	
	
	public Integer getSolutionId() {
		return solutionId;
	}
	public void setSolutionId(Integer solutionId) {
		this.solutionId = solutionId;
	}	
	public Apaweightage getApaWeightage() {
		return apaWeightage;
	}
	public void setApaWeightage(Apaweightage apaWeightage) {
		this.apaWeightage = apaWeightage;
	}
	public List<OpportunityScope> getOppScopeList() {
		return oppScopeList;
	}
	public void setOppScopeList(List<OpportunityScope> oppScopeList) {
		this.oppScopeList = oppScopeList;
	}	
	/*public List<SolutionAPA> getSolutionAPAList() {
		return solutionAPAList;
	}
	public void setSolutionAPAList(List<SolutionAPA> solutionAPAList) {
		this.solutionAPAList = solutionAPAList;
	}*/
	public AutoPopulatingList<SolutionAPA> getSolutionAPAList() {
		return solutionAPAList;
	}
	public void setSolutionAPAList(AutoPopulatingList<SolutionAPA> solutionAPAList) {
		this.solutionAPAList = solutionAPAList;
	}
	/*public List<Apacomplexity> getApaComplexityList() {
		return apaComplexityList;
	}
	public void setApaComplexityList(List<Apacomplexity> apaComplexityList) {
		this.apaComplexityList = apaComplexityList;
	}*/
	public AutoPopulatingList<Apacomplexity> getApaComplexityList() {
		return apaComplexityList;
	}
	public void setApaComplexityList(
			AutoPopulatingList<Apacomplexity> apaComplexityList) {
		this.apaComplexityList = apaComplexityList;
	}
	
}
