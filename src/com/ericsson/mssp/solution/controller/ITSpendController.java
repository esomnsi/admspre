/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.controller
* File name       		    :  ITSpendController.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 17, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 17, 2012  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ericsson.mssp.common.dto.ITSpendDTO;
import com.ericsson.mssp.solution.forms.ItSpendForm;

/**
 * @author eruvwyn
 *
 */
@Controller
public class ITSpendController {
	
	@RequestMapping(value="/solution/itSpend",method = RequestMethod.GET )
	public String itSpend(Model model){
		ItSpendForm itSpendForm = new ItSpendForm();
		
		itSpendForm.setItSpendDto(new ArrayList<ITSpendDTO>());
		model.addAttribute("itSpendForm",itSpendForm);
		System.out.println("ITSPPPPPPP");
		return "itSpend";
		
	}
	
	@RequestMapping(value = "/solution/SaveitSpend", method = RequestMethod.POST)
	public String saveItSpend(@ModelAttribute("itSpendForm") ItSpendForm itSpendForm)
	{
	System.out.println(itSpendForm.getItSpendDto().size());
		return "itSpend";
	}
	
	

}
