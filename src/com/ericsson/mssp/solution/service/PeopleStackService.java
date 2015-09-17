package com.ericsson.mssp.solution.service;

import java.util.List;

import com.ericsson.mssp.common.dto.ScopeSummaryReportDTO;
import com.ericsson.mssp.common.entity.LessEffortDetail;
import com.ericsson.mssp.common.entity.PeopleStackResourceDetail;
import com.ericsson.mssp.common.entity.StaffAllocation;
import com.ericsson.mssp.common.entity.StaffCategory;

public interface PeopleStackService {
	public List<PeopleStackResourceDetail> loadAllResourceDetails(long solutionId);
	public void saveAllResourceDetails(List<PeopleStackResourceDetail> dtos);
	public List<ScopeSummaryReportDTO> loadInScopeSummaryData(long solutionId);
	public LessEffortDetail loadInScopeSummaryLessEffort(long solutionId);
	public void saveScopeSummary(LessEffortDetail dto);
	public List<StaffAllocation> loadStaffAllocation(long solutionId);
	public void saveStaffAllocation(List<StaffAllocation> staffList);
	public List<StaffCategory> loadStaffCategory();
	public void deleteResource(PeopleStackResourceDetail dto);
}
	 