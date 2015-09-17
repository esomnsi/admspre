package com.ericsson.mssp.common.dto;

import java.util.ArrayList;
import java.util.List;

import com.ericsson.mssp.common.entity.StaffAllocation;
import com.ericsson.mssp.common.entity.StaffAllocationPK;
import com.ericsson.mssp.common.entity.StaffCategory;

public class StaffCategoryMap {

	private static StaffCategoryMap staffCategoryMap;
	public static List<StaffAllocation> staffAllocationList;
	private StaffCategoryMap()
	{
		
	}
	public static synchronized StaffCategoryMap getInstance()
	{
		if(staffCategoryMap==null)staffCategoryMap=new StaffCategoryMap();
		return staffCategoryMap;
	}
	public void setStaffAllocationList(List<StaffCategory> categoryList)
	{
		List<StaffAllocation> staffAllocationDTOs = new ArrayList<StaffAllocation>();
		for(StaffCategory categoryDTO:categoryList)
		{
			StaffAllocation allocationDTO = new StaffAllocation();
			StaffAllocationPK allocationDTOPK = new StaffAllocationPK();
			allocationDTOPK.setSolId(categoryDTO.getCategoryId());
			allocationDTOPK.setCategory(categoryDTO.getCategoryId());
			allocationDTO.setPrimaryKey(allocationDTOPK);
			allocationDTO.setStaffCategory(categoryDTO);
			staffAllocationDTOs.add(allocationDTO);
		}
		staffAllocationList=staffAllocationDTOs;
	}
}
