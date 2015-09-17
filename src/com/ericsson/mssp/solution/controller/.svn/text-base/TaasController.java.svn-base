package com.ericsson.mssp.solution.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.dto.SolutionTestingAsAserviceDTO;
import com.ericsson.mssp.solution.forms.TaasForm;
import com.ericsson.mssp.solution.forms.TaasGenericInputForm;
import com.ericsson.mssp.solution.service.ITaasService;
import com.ericsson.mssp.taas.dto.EffLeverDTO;
import com.ericsson.mssp.taas.objects.TaasOutput;
import com.ericsson.mssp.taas.objects.TaasSecondaryOutput;

@Controller
public class TaasController extends BaseController
{
	private Integer oppScopeID;
	private SolutionTestingAsAserviceDTO dto;
	@Autowired
	private ITaasService itaasService;
	
	/*@RequestMapping(value = "/solution/taas", method = RequestMethod.GET)
	public ModelAndView showTaasTable(HttpSession session)
	{
		Integer solutionId = 1;//getSessionSolutionId(session);
		TaasGenericInputForm taasGenIPForm = new TaasGenericInputForm();
		TaasForm taasForm = new TaasForm();
		Map<String, Object> objectMap = new HashMap<String, Object>();
		List<TaasServiceDTO> testServList = itaasService.getGenericInputList(solutionId);
		List<GenericTestingInputs> genTestIPList = itaasService.getGenericTestingInputList(solutionId);
		List<GenericTestingOverhead> testOverheadList = itaasService.getTestingOverheadList(solutionId);
		HashMap<String, Object> effLeverMap = itaasService.getEffLeverValues(solutionId);
		List<EffLeverDTO> majEffLevList = (List<EffLeverDTO>) effLeverMap.get("Major");
		List<EffLeverDTO> minEffLevList = (List<EffLeverDTO>) effLeverMap.get("Minor");
		majEffLevList = createDtoList(majEffLevList);
		minEffLevList = createDtoList(minEffLevList);
		List<RegressionLever> regLevList = itaasService.getRegLeverValues(solutionId);
		List<TestEffReduction> testEffRedList = itaasService.getTestEffRedValues(solutionId);
		taasForm.setMajEffLeverList(majEffLevList);
		taasForm.setMinEffLeverList(minEffLevList);
		taasGenIPForm.setSolution(solutionId);
		taasGenIPForm.setTestServList(testServList);
		taasForm.setGenTestInputList(genTestIPList);
		taasForm.setGenTestOverheadList(testOverheadList);
		taasForm.setRegLeverList(regLevList);
		taasForm.setTestEffRedList(testEffRedList);
		objectMap.put("taasGenIPForm", taasGenIPForm);
		objectMap.put("taasForm", taasForm);
		objectMap.put("taasOPForm", new TaasOutputForm());
		//return new ModelAndView("taas", "taasGenIPForm", taasGenIPForm);
		return new ModelAndView("taas", objectMap);
	}*/


/*	@RequestMapping(value = "/solution/savetaas", method = RequestMethod.POST)
	public @ResponseBody String saveTaasTable(@ModelAttribute("taasGenIPForm") TaasGenericInputForm taasGenIPForm)
	{
		try 
		{
			itaasService.updateGenericInputs(taasGenIPForm);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "success";
		//return "redirect:/solution/taas";//new ModelAndView("taas", "taasGenIPForm", taasGenIPForm);
	}
	
	@RequestMapping(value = "/save/taasdetails", method = RequestMethod.POST)
	public String saveTaasDetails(@ModelAttribute TaasForm taasForm)
	{
		oppScopeID = taasForm.getOppScopeID();
		dto = taasForm.getServiceList().get(0);
		itaasService.updateTaasDetails(taasForm);
		return "redirect:/taas/output";
	}
	
	@RequestMapping(value = "/taas/output", method = RequestMethod.GET)
	public String showTaasOutput(HttpSession session)
	{
		Integer solutionId = getSessionSolutionId(session);
		//HashMap<String, Object> objMap = new HashMap<String, Object>();
		//TaasOutputForm taasOPForm = new TaasOutputForm();
		//List<TaasOutput> taasOPList = itaasService.getMajTaasOPList(solutionId);
		taasOPForm.setMajTaasOpList(taasOPList.get(0).getMajTaasOPList());
		taasOPForm.setMinTaasOpList(taasOPList.get(0).getMinTaasOPList());
		taasOPForm.setYearlyTaasOpList(taasOPList.get(0).getTaasSecondaryOPList());
		taasOPForm.setYearlyOPObj(taasOPList.get(0).getYearlyOPObj());
		objMap.put("taasGenIPForm", new TaasGenericInputForm());
		objMap.put("taasForm", new TaasForm());
		objMap.put("taasOPForm", taasOPForm);
		List<TaasOutput> list = itaasService.getMajTaasOPList(solutionId);
		TaasSecondaryOutput obj = list.get(0).getTaasSecondaryOPList().get(0);
		itaasService.saveOutput(solutionId, oppScopeID, obj, dto);
		oppScopeID = null;
		dto = null;
		return "redirect:/solution/volumetricSolution";
		//return new ModelAndView("taas_middle", "form", taasOPForm);
		//return new ModelAndView("taas", objMap);
	}
	
	*//**
	 * This method creates the dto list to be pushed in the TaasForm object based on released type.
	 * @param list The list containing the EffLeverDTO objects 
	 * @param type The type of released based on which the Major & Minor efficiency lever list will be created.
	 * @return The list containing the EffLeverDTO objects.
	 *//*
	private List<EffLeverDTO> createDtoList(List<EffLeverDTO> list) 
	{
		List<EffLeverDTO> dtoList = new ArrayList<EffLeverDTO>();
		for(EffLeverDTO obj : list)
		{
			while(obj.getTestType().equalsIgnoreCase("Regression"))
			{
				dtoList.add(obj);
				break;
			}
			while(obj.getTestType().equalsIgnoreCase("New_Functionality"))
			{
				dtoList.add(obj);
				break;
			}
			while(obj.getTestType().equalsIgnoreCase("UAT_Prod_Go_Live"))
			{
				dtoList.add(obj);
				break;
			}
		}
		return dtoList;
	}*/
}
