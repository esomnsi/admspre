package com.ericsson.mssp.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ericsson.mssp.solution.controller.BaseController;

@Controller
public class UnderconstructionController extends BaseController{
	
	
	@RequestMapping(value = "/common/undercons")
	public String init(ModelMap model) {
	
		return "underconstruct";
	}

}
