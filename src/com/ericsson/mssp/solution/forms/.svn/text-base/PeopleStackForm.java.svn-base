package com.ericsson.mssp.solution.forms;

import java.util.ArrayList;
import java.util.List;

import com.ericsson.mssp.common.dto.ScopeSummaryReportDTO;
import com.ericsson.mssp.common.entity.LessEffortDetail;
import com.ericsson.mssp.common.entity.PeopleStackResourceDetail;
import com.ericsson.mssp.common.entity.StaffAllocation;

public class PeopleStackForm 
{
	private Long solId;//Solution Id remains fixed for the whole page
	private List<PeopleStackResourceDetail> peopleStackResourceDetailDTOs;//required for People Stack Resource Details
	private List<ScopeSummaryReportDTO> scopeSummaryReportDTOs;//required for In Scope Summary Report
	private List<String> categoryList ;//consist all the categories(Employee,Contractor,Others)
	private List<String> currencyList;//consist different currency(USSD,EURO,INR)
	private LessEffortDetail lessEffortDTO;//required for less effort management
	private double netOutSourcedEffort;//derived field
	private double peopleManagementPercent;//derived field
	private double peopleManagementPercentRatio;//derived  field
	private List<StaffAllocation> staffCategoryDTOs ;//required for staff allocation
	private double totalAnnualHrs;//derived field
	
	public Long getSolId() {
		return solId;
	}
	public void setSolId(Long solId) {
		this.solId = solId;
	}
	public List<StaffAllocation> getStaffCategoryDTOs() {
		return staffCategoryDTOs;
	}
	public void setStaffCategoryDTOs(List<StaffAllocation> staffCategoryDTOs) {
		this.staffCategoryDTOs = staffCategoryDTOs;
	}
	public double getPeopleManagementPercent() {
		return peopleManagementPercent;
	}
	public void setPeopleManagementPercent(double peopleManagementPercent) {
		this.peopleManagementPercent = peopleManagementPercent;
	}
	public double getNetOutSourcedEffort() {
		return netOutSourcedEffort;
	}
	public void setNetOutSourcedEffort(double netOutSourcedEffort) {
		this.netOutSourcedEffort = netOutSourcedEffort;
	}
	public LessEffortDetail getLessEffortDTO() {
		return lessEffortDTO;
	}
	public void setLessEffortDTO(LessEffortDetail lessEffortDTO) {
		this.lessEffortDTO = lessEffortDTO;
	}
	public double getTotalAnnualHrs() {
		return totalAnnualHrs;
	}
	public void setTotalAnnualHrs(double totalAnnualHrs) {
		this.totalAnnualHrs = totalAnnualHrs;
	}
	public List<ScopeSummaryReportDTO> getScopeSummaryReportDTOs() {
		return scopeSummaryReportDTOs;
	}
	public void setScopeSummaryReportDTOs(
			List<ScopeSummaryReportDTO> scopeSummaryReportDTOs) {
		this.scopeSummaryReportDTOs = scopeSummaryReportDTOs;
	}
	public List<String> getCurrencyList() {
		currencyList = new ArrayList<String>();
		currencyList.add("USSD");
		currencyList.add("EURO");
		currencyList.add("INR");
		return currencyList;
	}
	public void setCurrencyList(List<String> currencyList) {
		this.currencyList = currencyList;
	}
	public List<String> getCategoryList () {
		categoryList = new ArrayList<String>();
		categoryList.add("Employee");
		categoryList.add("Contractor");
		categoryList.add("Other");
		return categoryList;
	}
	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}
	public List<PeopleStackResourceDetail> getPeopleStackResourceDetailDTOs() {
		return peopleStackResourceDetailDTOs;
	}
	public void setPeopleStackResourceDetailDTOs(
			List<PeopleStackResourceDetail> peopleStackResourceDetailDTOs) {
		this.peopleStackResourceDetailDTOs = peopleStackResourceDetailDTOs;
	}
	public double getPeopleManagementPercentRatio() {
		return peopleManagementPercentRatio;
	}
	public void setPeopleManagementPercentRatio(double peopleManagementPercentRatio) {
		this.peopleManagementPercentRatio = peopleManagementPercentRatio;
	}
}
