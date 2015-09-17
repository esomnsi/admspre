package com.ericsson.mssp.volumetric.forms;

import java.util.ArrayList;
import java.util.List;

import com.ericsson.mssp.common.dto.AppMainSlaDTO;

public class AppMainSlaForm {
	
	
	private List<AppMainSlaDTO> list = new ArrayList<AppMainSlaDTO>();	
	
	public List<AppMainSlaDTO> getList() {
		return list;
	}
	public void setList(List<AppMainSlaDTO> list) {
		this.list = list;
	}	

}
