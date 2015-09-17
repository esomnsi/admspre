package com.ericsson.mssp.solution.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.TNTDetailDTO;
import com.ericsson.mssp.common.dto.TNTPartitionDateDTO;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.forms.TNTForm;
import com.ericsson.mssp.solution.service.ISolutionService;
import com.ericsson.mssp.solution.service.ITNTControllerService;

@Controller
public class TNTController extends BaseController 
{
	private final Logger log = Logger.getLogger(TNTController.class);
	@Autowired
	private ITNTControllerService mService;
	
	@Autowired
	private ISolutionService solutionService;
	
	@RequestMapping(value = "/solution/tnt" , method = RequestMethod.GET)
	public ModelAndView startTntControl(Model model, HttpSession session)
	{
		Integer solution = getSessionSolutionId(session);
		Long solutionId = new Long(solution);
		Long opportunityId = new Long(getSessionOpportunityId(session));
		log.debug(""+solutionId);
		TNTPartitionDateDTO tntPartitionDate = mService.getPartitionDate(solutionId, opportunityId);
		List<TNTDetailDTO> tntDetail = mService.getTntDetails(solutionId);
		OpportunityDTO opportunityDTO= solutionService.getOpportunity(opportunityId.intValue());
		model.addAttribute("opportunityDTO",opportunityDTO);
		List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(opportunityId.intValue(), solutionId.intValue());
		model.addAttribute("serviceBucketData", serviceBucketData);
		TNTForm tntForm = new TNTForm();
		tntForm.setSolutionId(solutionId);
		tntForm.setPartitionDate(tntPartitionDate);
		tntForm.setTntDetailList(tntDetail);
		return new ModelAndView("tnt", "tntForm", tntForm);
	}
	
	@RequestMapping(value = "/solution/save", method = RequestMethod.POST)
	public String saveTntValues(@ModelAttribute("tntForm") TNTForm tntForm)
	{
		log.debug("Save TNT Data");
		TNTPartitionDateDTO partitionDate =  tntForm.getPartitionDate();
		List<TNTDetailDTO> detail = tntForm.getTntDetailList();
		try 
		{
			mService.saveTntPartitionDateDetails(partitionDate);
			mService.saveTntDetailDto(detail,tntForm.getSolutionId());
		} 
		catch (MSSPException e)
		{
			log.error(e.getMessage());
		}
		return "redirect:/solution/tnt";
	}
}
