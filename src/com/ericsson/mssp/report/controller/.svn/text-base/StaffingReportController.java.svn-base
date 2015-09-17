/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.report.controller
* File name       		    :  StaffingReportController.java
* Description				:	<To Do>
* Author, Date & Release	:	Jan 10, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Jan 10, 2013  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.report.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ericsson.mssp.common.entity.ServiceScope;
import com.ericsson.mssp.solution.forms.StaffingPyramidReportForm;
import com.ericsson.mssp.solution.service.IReportService;

/**
 * @author eruvwyn
 *
 */
@Controller
public class StaffingReportController {
	@Autowired
	IReportService reportService;
	private final Log log = LogFactory.getLog(StaffingReportController.class);
	@RequestMapping(value = "/report/staffingPyramidReport")
	public  String getServiceScpe(Model model){
		
		StaffingPyramidReportForm staffPyramidReportForm = new StaffingPyramidReportForm();
		List<ServiceScope> listServiceScope = reportService.getAllServiceScope();
		Map<Integer , String> mapServiceScope = new LinkedHashMap<Integer , String>();
		for (ServiceScope serviceScope : listServiceScope) {
			mapServiceScope.put(serviceScope.getServiceScopeId(), serviceScope.getServiceScopeName());
		}
		model.addAttribute("mapServiceScope", mapServiceScope);
		model.addAttribute("staffPyramidReportForm", staffPyramidReportForm);
		return "staffingPyramidReport";
	}

}
