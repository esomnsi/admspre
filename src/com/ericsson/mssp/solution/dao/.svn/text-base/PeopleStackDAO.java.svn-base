package com.ericsson.mssp.solution.dao;

import java.util.List;

import com.ericsson.mssp.common.dto.ScopeSummaryReportDTO;
import com.ericsson.mssp.common.entity.LessEffortDetail;
import com.ericsson.mssp.common.entity.PeopleStackResourceDetail;
import com.ericsson.mssp.common.entity.StaffAllocation;
import com.ericsson.mssp.common.entity.StaffCategory;

public interface PeopleStackDAO 
{
	public List<PeopleStackResourceDetail> loadAllResourceDetails(long solutionId); //all resource details for a particular solution id
	public void saveResourceDetails(List<PeopleStackResourceDetail> dtos); //save all resource details
	public List<ScopeSummaryReportDTO> loadInScopeSummaryDTO(long solutionId);//load scope summary
	public LessEffortDetail loadInScopeSummaryLessEffort(long solutionId);//load less effort management
	public void saveScopeSummary(LessEffortDetail dto); // save less effort management
	public List<StaffAllocation> loadStaffAllocation(long solutionId); //load staff allocation details
	public void saveStaffAllocation(List<StaffAllocation> staffList);//save staff allocation details
	public List<StaffCategory> loadStaffCategoryList();// load all staff categories
	public void deleteResource(PeopleStackResourceDetail dto);// delete resource
}
