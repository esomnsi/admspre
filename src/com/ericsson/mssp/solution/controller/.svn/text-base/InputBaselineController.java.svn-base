/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.controller
 * File name       		    :  InputBaseLineContoller.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Dec 17, 20122012
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Dec 17, 2012  	   |EGI   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ericsson.mssp.common.dto.SolutionDTO;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.service.ISolutionService;

/**
 * @author EGI
 * 
 */
@Controller
public class InputBaselineController extends BaseController {

	@Autowired
	private ISolutionService solutionService;

	@RequestMapping(value = "/solution/inputBaseline")
	public String initPage(Model model, HttpSession session) {
		Integer solId = getSessionSolutionId(session);
		try {

			SolutionDTO solutionDTO = solutionService.getSolutionDetail(solId);

			session.setAttribute(UTILIZATION_PER_YEAR,
					solutionDTO.getUtilizationPerYear());

			if (APPROACH_STAFF_AUG.equalsIgnoreCase(solutionDTO
					.getSolutionApproach())) {
				return "redirect:staffAugmentation";

			} else if (APPROACH_STAFF_DETAIL.equalsIgnoreCase(solutionDTO
					.getSolutionApproach())) {
				if (SOLTYPE_VOL_SOL.equalsIgnoreCase(solutionDTO
						.getSolutionType())) {
					// return "redirect:volumetricSolution"; changed on 2nd May
					// 2014
					return "redirect:volumetricAppDev";
				}
				if (SOLTYPE_IT_SPEND.equalsIgnoreCase(solutionDTO
						.getSolutionType())) {
					return "redirect:itSpend";
				}
				if (SOLTYPE_PEOPLE_STACK.equalsIgnoreCase(solutionDTO
						.getSolutionType())) {
					return "redirect:peopleStack";
				}
			}

		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

}
