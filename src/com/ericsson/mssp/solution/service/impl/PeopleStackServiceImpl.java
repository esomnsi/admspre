package com.ericsson.mssp.solution.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.dto.ScopeSummaryReportDTO;
import com.ericsson.mssp.common.entity.LessEffortDetail;
import com.ericsson.mssp.common.entity.PeopleStackResourceDetail;
import com.ericsson.mssp.common.entity.StaffAllocation;
import com.ericsson.mssp.common.entity.StaffCategory;
import com.ericsson.mssp.solution.dao.PeopleStackDAO;
import com.ericsson.mssp.solution.service.PeopleStackService;
@Service
public class PeopleStackServiceImpl implements PeopleStackService
{
	@Autowired
	private PeopleStackDAO peopleStackDAO;
	@Override
	public List<PeopleStackResourceDetail> loadAllResourceDetails(long solutionId) {
		return peopleStackDAO.loadAllResourceDetails(solutionId);
	}
	@Override
	public void saveAllResourceDetails(List<PeopleStackResourceDetail> dtos) {
		peopleStackDAO.saveResourceDetails(dtos);
	}
	@Override
	public List<ScopeSummaryReportDTO> loadInScopeSummaryData(long solutionId) {
		return peopleStackDAO.loadInScopeSummaryDTO(solutionId);
	}
	@Override
	public LessEffortDetail loadInScopeSummaryLessEffort(long solutionId) {
		return peopleStackDAO.loadInScopeSummaryLessEffort(solutionId);
	}
	@Override
	public void saveScopeSummary(LessEffortDetail dto) {
		peopleStackDAO.saveScopeSummary(dto);
	}
	@Override
	public List<StaffAllocation> loadStaffAllocation(long solutionId) {
		return peopleStackDAO.loadStaffAllocation(solutionId);
	}
	@Override
	public void saveStaffAllocation(List<StaffAllocation> staffList) {
		peopleStackDAO.saveStaffAllocation(staffList);
	}
	@Override
	public List<StaffCategory> loadStaffCategory() {
		return peopleStackDAO.loadStaffCategoryList();
	}
	@Override
	public void deleteResource(PeopleStackResourceDetail dto) {
		peopleStackDAO.deleteResource(dto);
	}
}
