package com.ericsson.mssp.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.dto.CountryDTO;
import com.ericsson.mssp.common.dto.JobRoleDTO;
import com.ericsson.mssp.common.dto.JobStageDTO;
import com.ericsson.mssp.common.dto.RateCardDTO;
import com.ericsson.mssp.common.entity.CurrencyExchange;
import com.ericsson.mssp.rate.management.dto.RateManagementDTO;
import com.ericsson.mssp.rate.management.service.IRateManagementService;
import com.ericsson.mssp.solution.controller.BaseController;
import com.ericsson.mssp.solution.service.ISolutionService;

@Controller
public class RateManagementController extends BaseController{

	
	@Autowired
	ISolutionService solutionService;
	
	@Autowired
	IRateManagementService rateManagementService;
	
	private final Log logger = LogFactory.getLog(RateManagementController.class);

	@RequestMapping(value="/admin/rateManagement")
	public String displayCurrencyExchange(Model model, HttpSession session,@ModelAttribute("rateManagementDTO")RateManagementDTO rateManagementDTO){
		
		
		logger.info("inside display rate management home");
		List<CountryDTO> countriesList = new ArrayList<CountryDTO>();
		countriesList = solutionService.getCountries();
		logger.info("countries list :" + countriesList.size());
		
		List<CountryDTO> gscCountries = solutionService.getGscCountries();
		
		List<JobRoleDTO> jobRoleList = new ArrayList<>();
		jobRoleList = solutionService.getJobRoleList();
		logger.info("job role list :" + jobRoleList.size());
		
		List<CurrencyExchange> currencyExchangeList = rateManagementService.getCurrencyExchangeList();
		
		if(rateManagementDTO == null){
			
			rateManagementDTO = new RateManagementDTO();
		}else {
			if(null != rateManagementDTO.getCountryID()){
				List<RateCardDTO> rateCardList= solutionService.getRateCardList(rateManagementDTO.getCountryID());
				List<RateManagementDTO> rateManagementList = getRateManagementDTOList(rateCardList);
				model.addAttribute("rateManagementList", rateManagementList);
			}
		}
		
		model.addAttribute("jobRoleList",jobRoleList);
		model.addAttribute("countries", countriesList);
		model.addAttribute("rateManagementDTO",rateManagementDTO);
		model.addAttribute("currencyExchangeList", currencyExchangeList);
		model.addAttribute("gscCountriesList", gscCountries);
		return "rateManagement";
	}
	
	@RequestMapping(value="/admin/getDataForSelectedCountry")
	public String getData(@RequestParam("countryID")Integer countryID,Model model){
		
		System.out.println("inside get data with country ID : "+ countryID);
		model.addAttribute("rateManagementDTO",new RateManagementDTO());
		
		List<RateCardDTO> rateCardList= solutionService.getRateCardList(countryID);
		
		List<RateManagementDTO> rateManagementList = getRateManagementDTOList(rateCardList);
		
		model.addAttribute("countryId", countryID);
		model.addAttribute("rateManagementList", rateManagementList);
		return "forward:./rateManagement";
		
	}
	
	private List<RateManagementDTO> getRateManagementDTOList(List<RateCardDTO> rateList){
		
		List<RateManagementDTO> rateManagementList = new ArrayList<>();
		
		for(RateCardDTO item : rateList){
			
			RateManagementDTO rateManagementDTO = new RateManagementDTO();
			
			rateManagementDTO.setRateManagementID(item.getRateCardId());
			rateManagementDTO.setJobRole(item.getJobRoleStages().getJobRoleDTO().getRole());
			rateManagementDTO.setJobStage(item.getJobRoleStages().getJobStageDTO().getStage());
			rateManagementDTO.setRate(item.getRate());
			rateManagementDTO.setGsc(item.getGsc());
			
			rateManagementList.add(rateManagementDTO);
		}
		
		return rateManagementList;
	}
	
	@RequestMapping(value="/admin/saveRateData")
	public @ResponseBody String saveRateData(Model model,@RequestParam("data")String data, HttpSession session){
		
		logger.info("inside save rate data : " + data);
		
		String message="";
		RateManagementDTO rateManagementDTO = new RateManagementDTO();
		String []temp = data.split("-");
		
		String gsc = rateManagementService.getCountryName(Integer.parseInt(temp[3]));
		
		rateManagementDTO.setCountryID(Integer.parseInt(temp[0]));
		rateManagementDTO.setJobRoleID(Integer.parseInt(temp[1]));
		rateManagementDTO.setJobStageID(Integer.parseInt(temp[2]));
		rateManagementDTO.setGsc(gsc);
		rateManagementDTO.setLocation(temp[4].split(" ")[0]);
		rateManagementDTO.setSubLocation(temp[4].split(" ")[1]);
		rateManagementDTO.setRate(Double.parseDouble(temp[5]));
		rateManagementDTO.setDate(new Date());
		rateManagementDTO.setUpdatedDate(new Date());
		
		try{
				if(rateManagementService.checkEntry(rateManagementDTO)){
					message = "DAE";//Data Already Exists
				}else{
					rateManagementService.saveAndUpdate(rateManagementDTO);
					message = "successfull";
				}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return  message;
	}
	
	@RequestMapping(value="/admin/getJobStages")
	public @ResponseBody String getJobStages(Model model,@RequestParam("jobRoleID")Integer jobRoleID){
		
		logger.info("inside getJobStages");
		String temp = "";
		List<JobStageDTO> jobStageDTOList = solutionService.getJobStages(jobRoleID);
		
		if(jobStageDTOList.size() > 0){
			
			for(JobStageDTO jobStageDTO : jobStageDTOList){
				
				temp += "<option value='"+jobStageDTO.getJobStageID()+"'>"+jobStageDTO.getStage()+ "</option>";
			}
		}
		
		logger.info("temp string : " + temp);
		
		return temp;
	}
	
	
	@RequestMapping(value="/admin/updateRate")
	public String updateRate(Model model,@RequestParam("param")String values){
		logger.info("inside updateRate : " + values);
		
		String[]temp = values.split("-");
		String rate = temp[0];
		
		String countryID = temp[2];
		model.addAttribute("rowId", temp[1]);
		model.addAttribute("rate", rate);
		model.addAttribute("countryID", countryID);
		model.addAttribute("jobRole", temp[3]);
		model.addAttribute("jobStage", temp[4]);
		model.addAttribute("gsc",temp[5]);
		return "updateRatePopup";
	}
	
	@RequestMapping(value="/admin/rateCardUpdate")
	public @ResponseBody String updateRateCard(Model model,@RequestParam("values")String values){
		logger.info("inside update RateCard : " + values);
		String[] temp = values.split("-");
		RateManagementDTO rateManagementDTO = new RateManagementDTO();
		
		rateManagementDTO.setCountryID(Integer.parseInt(temp[2]));
		rateManagementDTO.setRateManagementID(Integer.parseInt(temp[1]));
		rateManagementDTO.setRate(Double.parseDouble(temp[0]));
		rateManagementDTO.setJobRole(temp[3]);
		rateManagementDTO.setJobStage(temp[4]);
		rateManagementDTO.setGsc(temp[5]);
		rateManagementDTO.setDate(new Date());
		
		try{
			String ret = rateManagementService.updateRateCard(rateManagementDTO);
			
			logger.info(ret);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//model.addAttribute("rateManagementDTO", rateManagementDTO);
		
		return "update successfull";
	}
	
	@RequestMapping(value="/admin/saveExchangeRate")
	public @ResponseBody String saveExchangeRate(Model model,@RequestParam("values")String values){
		String resp = "";
		
		logger.info("values received : " + values);
		
		String []temp = values.split("-");
		
		resp = rateManagementService.updateCurrencyExchange(temp[0], temp[1],temp[2]);
		
		return resp;
	}
}
