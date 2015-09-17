package com.ericsson.mssp.solution.forms;

import java.util.List;

import com.ericsson.mssp.taas.objects.TaasPrimaryOutput;
import com.ericsson.mssp.taas.objects.TaasSecondaryOutput;

public class TaasOutputForm 
{
	private List<TaasPrimaryOutput> majTaasOpList;
	private List<TaasPrimaryOutput> minTaasOpList;
	private List<TaasSecondaryOutput> yearlyTaasOpList;
	private TaasPrimaryOutput yearlyOPObj;
	
	public List<TaasPrimaryOutput> getMajTaasOpList() {
		return majTaasOpList;
	}
	public void setMajTaasOpList(List<TaasPrimaryOutput> majTaasOpList) {
		this.majTaasOpList = majTaasOpList;
	}
	public List<TaasPrimaryOutput> getMinTaasOpList() {
		return minTaasOpList;
	}
	public void setMinTaasOpList(List<TaasPrimaryOutput> minTaasOpList) {
		this.minTaasOpList = minTaasOpList;
	}
	public List<TaasSecondaryOutput> getYearlyTaasOpList() {
		return yearlyTaasOpList;
	}
	public void setYearlyTaasOpList(List<TaasSecondaryOutput> yearlyTaasOpList) {
		this.yearlyTaasOpList = yearlyTaasOpList;
	}
	public TaasPrimaryOutput getYearlyOPObj() {
		return yearlyOPObj;
	}
	public void setYearlyOPObj(TaasPrimaryOutput yearlyOPObj) {
		this.yearlyOPObj = yearlyOPObj;
	}
	
}
