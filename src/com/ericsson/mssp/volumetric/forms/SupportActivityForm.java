package com.ericsson.mssp.volumetric.forms;

import java.util.ArrayList;
import java.util.List;

import com.ericsson.mssp.common.dto.AppMainSupportActivityDTO;

public class SupportActivityForm {
	
	List<AppMainSupportActivityDTO> appMainActivityDTOList = new ArrayList<AppMainSupportActivityDTO>();

	public List<AppMainSupportActivityDTO> getAppMainActivityDTOList() {
		return appMainActivityDTOList;
	}

	public void setAppMainActivityDTOList(
			List<AppMainSupportActivityDTO> appMainActivityDTOList) {
		this.appMainActivityDTOList = appMainActivityDTOList;
	}
	
	
	

}
