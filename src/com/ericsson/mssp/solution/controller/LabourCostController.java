package com.ericsson.mssp.solution.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.dto.JobRoleDTO;
import com.ericsson.mssp.common.dto.LaborCostDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.RateCardDTO;
import com.ericsson.mssp.common.entity.CurrencyExchange;
import com.ericsson.mssp.rate.management.service.IRateManagementService;
import com.ericsson.mssp.solution.service.ISolutionService;

@Controller
public class LabourCostController extends BaseController{

	@Autowired
	ISolutionService solutionService;
	@Autowired
	IRateManagementService rateManagementService;
	
	String secondTime = "";
	static boolean partitionName = true;

	public static Logger logger = Logger.getLogger(LabourCostController.class);
	
	@RequestMapping(value="/solution/labourCost")
	public String displayLabourCost(ModelMap map, HttpSession session)
	{
		String interval = "";
		secondTime = "";
		String []opportunityInterval = null;
		//OpportunityDTO opportunityDTO = new OpportunityDTO();
		Integer opportunityID = getSessionOpportunityId(session);
		Map<Integer, String> serviceScopes = null;
		Integer solutionID = null;
		String data = "not empty";
		//boolean lengthFlag = false;
		
		//List<JobRoleDTO> jobRoleDTOList = getJobRoleList();
		
		try{
		    solutionID =getSessionSolutionId(session);
			serviceScopes = solutionService.getAllServiceScopeInMapByOppID(opportunityID);
			interval = solutionService.getOpportunitySteadyMonthYearsStringByOpportunityID(opportunityID);
			
			opportunityInterval = interval.split("\\|");
			
			logger.info("time line received:["+interval+"] | with length:["+opportunityInterval.length+"]");
			
		}catch(Exception e){
			logger.info("exception at labour cost : " + e);
		}
		if (opportunityID != null) {
		   // opportunityDTO = solutionService.getOpportunity(opportunityID);
		}
		
		if(interval.length() > 0){
		boolean pName = true;
		pName = partitionName;
		
		partitionName = true;
		
		String partitionNamesMessage = "";
		
		if(pName == false){
			partitionNamesMessage = "<ul><li>No PartitionName Details found !!!</li></ul>";
		}
		
		// Service Bucket table data
		List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(opportunityID, solutionID);
		map.addAttribute("serviceBucketData", serviceBucketData);
		//map.addAttribute("jobRoleDTOList", jobRoleDTOList);
		map.addAttribute("opportunityInterval", opportunityInterval);
		map.addAttribute("interval", interval);
		map.addAttribute("serviceScopes", serviceScopes);
	//	map.addAttribute("opportunityDTO", opportunityDTO);
		map.addAttribute("secondTime",secondTime);
		map.addAttribute("partitionName",pName);
		map.addAttribute("partitionNamesMessage", partitionNamesMessage);
		map.addAttribute("data", data);
		
		//added by sibayan for saving
		logger.info("LabourCostController[displayLabourCost] Begin saving labour cost for SolutionID: "+solutionID);
		LaborCostDTO lcDTO=new LaborCostDTO(); 
		List<Float> exchangeRates = solutionService.getExchangeRateList(opportunityID);
		String currencyCode = solutionService.getCurrencyCode(opportunityID);
		Integer solutionId = getSessionSolutionId(session);
		Iterator iter=serviceScopes.keySet().iterator();
		while(iter.hasNext()){
			Integer serviceScopeID = (Integer)iter.next();
			//populateLCDTO(opportunityID, solutionId,  serviceScopeID, opportunityInterval,exchangeRates,currencyCode,  lcDTO);	
			logger.info("LabourCostController[displayLabourCost] Save Labor Cost : Successfully populated DTO for SolutionID: "+solutionID);
		}
		try{
			solutionService.saveLaborCost(lcDTO);
			logger.info("LabourCostController[displayLabourCost] Successfully saved labour cost  for SolutionID: "+solutionID);
		}catch(Exception e ){
			logger.error("LabourCostController[displayLabourCost] exception in saving labour cost for SolutionID: "+solutionID , e);
		}
		}else{
			map.addAttribute("interval", interval);
			map.addAttribute("data", data);
		}
		return "labourCost";
	}
	
	@RequestMapping(value="/solution/changeRate")
	public @ResponseBody String getRate(HttpSession session, ModelMap map,@RequestParam(value = "currencyCode")String currencyCode){
		
		DecimalFormat df = new DecimalFormat("#.#####");
		logger.info("rate multiplication factor");
		Float currentCurrency = null;
		String factor = "";
		String result = "";
		String temp = rateManagementService.getUsdValue(currencyCode);
		
		if(""!=temp){
		Float toBEChangedTo = Float.valueOf(temp);
		
		if(toBEChangedTo > 1){
			factor="M"; //multiply
		}else{
			factor="D"; //divide
		}
		
		logger.info("usd value : " + toBEChangedTo);
		String temp1 = rateManagementService.getUsdValue(solutionService.getOpportunity(getSessionOpportunityId(session)).getCustomerDTO().getCountryDTO().getCurrencyCode());
		if(""!=temp1){
			currentCurrency = Float.valueOf(temp1);
			logger.info("current usd value : " + currentCurrency);
			result = df.format(toBEChangedTo / currentCurrency);
			result +="-"+factor;
		} 
		logger.info("result : " + result);
		}
		return result;
	}
	
	@Autowired
	IRateManagementService iRateManagementService;
	
	@RequestMapping(value="/solution/getjobRole")
	public String populateJobeRole(ModelMap map,HttpSession session,HttpServletRequest request)
	{
		logger.info("inside get job role **************");
		
		Integer solutionId;
		String interval = "";
		Integer opportunityID;
		String selectedServiceScope ="";
		String []opportunityInterval = null;
		List<CurrencyExchange> currencyCodeList = new ArrayList<CurrencyExchange>();
		OpportunityDTO opportunityDTO = new OpportunityDTO();
		
		secondTime = "filled";
		solutionId = getSessionSolutionId(session);
		opportunityID = getSessionOpportunityId(session);
		selectedServiceScope = request.getParameter("selectedServiceScope");
		
		Map<Integer, String> serviceScopes = null;
		String data = "not empty";
		String checkCountryData = iRateManagementService.checkDataByCountryID(solutionService.getOpportunity(opportunityID).getCustomerDTO().getCountryDTO().getCountryId());
		
		String currency = solutionService.getOpportunity(opportunityID).getCustomerDTO().getCountryDTO().getCurrencyName()+
				"["+solutionService.getOpportunity(opportunityID).getCustomerDTO().getCountryDTO().getCurrencyCode()+"]";
		
		
		if(checkCountryData.equals("false")){
			data = "";
			map.addAttribute("data",data);
			interval="not empty";
			map.addAttribute("interval", interval);
			map.addAttribute("currency", currency);
			return "labourCost";
		}
		
		String currencyCode = currency.substring( currency.indexOf('[') +1,currency.indexOf(']') );
		logger.info("found the interval : " +  currencyCode);
		try{
		
			serviceScopes = solutionService.getAllServiceScopeInMapByOppID(opportunityID);
		
		logger.info("selected Service Scope["+selectedServiceScope+"] opportunityID["+opportunityID+"]   opportunityScopeID["+selectedServiceScope+"]   solutionID["+solutionId+"]");
		
		interval = solutionService.getOpportunitySteadyMonthYearsStringByOpportunityID(opportunityID);
		
		if(interval.length() > 0){
		currencyCodeList = rateManagementService.getCurrencyExchangeList();
		
		
		Map<String,Map<String,String[]>> finalList = getFinalList(solutionId,opportunityID,Integer.parseInt(selectedServiceScope));
		
		Map<String,String[]> pppMap = finalList.get("3pp");
		Map<String,String[]> gsciMap = finalList.get("gsci");
		Map<String,String[]> localMap = finalList.get("local");
		Map<String,String[]> offShoreMap = finalList.get("offShore");
		Map<String,String[]> nearShoreMap = finalList.get("nearShore");
		Map<String,String[]> laborCostForSelectedOppScopeID = finalList.get("totalforSelectedOppScopeID");
		
				
		
		String valueOfSelectedOppScopeID = laborCostForSelectedOppScopeID.get("totalforSelectedOppScopeID")[0];
		
 		opportunityInterval = interval.split("\\|");
		
		//Service Bucket table data
		List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(opportunityID, solutionId);
			
			
			boolean pName = true;
			pName = partitionName;
			
			partitionName = true;
			
			String partitionNamesMessage = "";
			
			if(pName == false){
				partitionNamesMessage = "<ul><li>No PartitionName Details found !!!</li></ul>";
			}
			
			map.addAttribute("serviceBucketData", serviceBucketData);
			map.addAttribute("opportunityInterval", opportunityInterval);
			map.addAttribute("opportunityDTO", opportunityDTO);
			map.addAttribute("selectedScope", selectedServiceScope);
			map.addAttribute("secondTime",secondTime);
			map.addAttribute("currencyCodeList",currencyCodeList);
			map.addAttribute("partitionName",pName);
			map.addAttribute("partitionNamesMessage", partitionNamesMessage);
			
			map.addAttribute("serviceScopes", serviceScopes);
			
			map.addAttribute("pppValues", pppMap);
			map.addAttribute("localValues", localMap);
			map.addAttribute("gsciValues", gsciMap);
			map.addAttribute("offShoreValues", offShoreMap);
			map.addAttribute("nearShoreValues", nearShoreMap);
			map.addAttribute("totalLCforSlectedOppScopeID", valueOfSelectedOppScopeID);
			map.addAttribute("currency", currency);
			map.addAttribute("interval", interval);
			map.addAttribute("currencyCode", currencyCode);
			map.addAttribute("data", data);
		}else{
			map.addAttribute("interval", interval);
		}
		
		}catch(Exception e){
			logger.info(e);
		}
		return "labourCost";
	}
	
	public Map<String,Map<String,String[]>> getFinalList(Integer solutionID, Integer opportunityID, Integer opportunityScopeID)
	{
		Integer gscId = solutionService.getOpportunity(opportunityID).getOpportunityDetailsDTO().getOpportunityLocationsDTO().getGsc1();
		String gsc = rateManagementService.getCountryName(gscId);
		Map<String,Map<String,String[]>> a = solutionService.getFinalList(solutionID,opportunityID,opportunityScopeID,gsc);
	return a;
	}
	
	public List<JobRoleDTO> getJobRoleList()
    {
    	List<JobRoleDTO> jobRoleDTOs = new ArrayList<JobRoleDTO>();
    	jobRoleDTOs =  solutionService.getJobRoleList();
    	return jobRoleDTOs;
    }
	public List<RateCardDTO> getRateCard()
	{
		List<RateCardDTO> rateCardDTOs = new ArrayList<RateCardDTO>();
		rateCardDTOs =  solutionService.getRateCardList();
    	return rateCardDTOs;
	}
	
	/****New method by sibayan****/
/*	private void populateLCDTO(Integer opportunityID, int solutionId, int serviceScopeID, String []opportunityInterval,List<Float> exchangeRates,
			String currencyCode, LaborCostDTO lcDTO){
		
		lcDTO.setOpportunityInterval(opportunityInterval);
		lcDTO.setSolutionID(solutionId);
		
		//List<Map<String,String[]>> a = getFinalList(solutionId,opportunityID,serviceScopeID);
		List<String[]> fonshore = new ArrayList<String[]>();
		List<String[]> foffshore = new ArrayList<String[]>();
		
		String []finalIntervalOnshoreValues = null;
		String []finalIntervalOffshoreValues = null;
		
		List<JobRoleDTO> jobRoleDTOListOnshore= new ArrayList<JobRoleDTO>();
		List<JobRoleDTO> jobRoleDTOListOffshore= new ArrayList<JobRoleDTO>();
		
		//Map<String,String[]> itemMap = a.get(0);
		
		for(Map.Entry<String,String[]> it : itemMap.entrySet())
		{
			finalIntervalOnshoreValues = new String[opportunityInterval.length];
			JobRoleDTO j = new JobRoleDTO();
			j.setJobRoleId(Integer.parseInt(it.getKey().split(",")[0]));
			j.setRole(it.getKey().split(",")[1]);
			
			jobRoleDTOListOnshore.add(j);
			
			finalIntervalOnshoreValues = it.getValue();
			
			 currency level changes 16 April 2013 
			logger.debug("*********BEFORE*******");
			for(int i =0; i<finalIntervalOnshoreValues.length;i++){
				logger.debug(finalIntervalOnshoreValues[i]);
			}
			
			if("GBP".equalsIgnoreCase(currencyCode)){
				for(int counter = 0; counter < finalIntervalOnshoreValues.length; counter++){
					if(Float.parseFloat(finalIntervalOnshoreValues[counter]) < 0 ){
						finalIntervalOnshoreValues[counter] = "-";
					}
					else{
						//finalIntervalOnshoreValues[counter] = String.valueOf(exchangeRates.get(1) * new Float(finalIntervalOnshoreValues[counter]));
					}
				}
			}else if("USD".equalsIgnoreCase(currencyCode)){
				for(int counter = 0; counter < finalIntervalOnshoreValues.length; counter++){
					if(Float.parseFloat(finalIntervalOnshoreValues[counter]) < 0 ){
						finalIntervalOnshoreValues[counter] = "-";
					}
					else{
					//finalIntervalOnshoreValues[counter] = String.valueOf(exchangeRates.get(0) * new Float(finalIntervalOnshoreValues[counter]));
					}
				}
			}else if("INR".equalsIgnoreCase(currencyCode)){
				for(int counter = 0; counter < finalIntervalOnshoreValues.length; counter++){
					finalIntervalOnshoreValues[counter] = String.valueOf(exchangeRates.get(2) * new Float(finalIntervalOnshoreValues[counter]));
				}
			}else{
				logger.debug("no currency matched : picking up dollar as default");
				for(int counter = 0; counter < finalIntervalOnshoreValues.length; counter++){
					if(Float.parseFloat(finalIntervalOnshoreValues[counter]) < 0 ){
						finalIntervalOnshoreValues[counter] = "-";
					}
					else{
					//finalIntervalOnshoreValues[counter] = String.valueOf(exchangeRates.get(0) * new Float(finalIntervalOnshoreValues[counter]));
					}
				}
				currencyCode = "USD";
			}
			
			
			for(int i =0; i<finalIntervalOnshoreValues.length;i++){
				logger.debug(finalIntervalOnshoreValues[i]);
			}
			
			
			 ends here 
			
			
			fonshore.add(finalIntervalOnshoreValues);
		}
		
		Map<String,String[]> itemMap1 = a.get(1);
		
		for(Map.Entry<String,String[]> it : itemMap1.entrySet())
		{
			finalIntervalOffshoreValues = new String[opportunityInterval.length];
			JobRoleDTO j = new JobRoleDTO();
			j.setJobRoleId(Integer.parseInt(it.getKey().split(",")[0]));
			j.setRole(it.getKey().split(",")[1]);
			
			jobRoleDTOListOffshore.add(j);
			
			finalIntervalOffshoreValues = it.getValue();
			
			 currency level changes 16 April 2013 
			
			
			for(int i =0; i<finalIntervalOffshoreValues.length;i++){
				logger.debug(finalIntervalOffshoreValues[i]);
			}
			
			
			if("GBP".equalsIgnoreCase(currencyCode)){
				for(int counter = 0; counter < finalIntervalOffshoreValues.length; counter++){
					if(Float.parseFloat(finalIntervalOffshoreValues[counter]) < 0 ){
						finalIntervalOffshoreValues[counter] = "-";
					}
					else{
					//finalIntervalOffshoreValues[counter] = String.valueOf(exchangeRates.get(1) * new Float(finalIntervalOffshoreValues[counter]));
					}
				}
			}else if("USD".equalsIgnoreCase(currencyCode)){
				for(int counter = 0; counter < finalIntervalOffshoreValues.length; counter++){
					if(Float.parseFloat(finalIntervalOffshoreValues[counter]) < 0 ){
						finalIntervalOffshoreValues[counter] = "-";
					}
					else{
					//finalIntervalOffshoreValues[counter] = String.valueOf(exchangeRates.get(0) * new Float(finalIntervalOffshoreValues[counter]));
					}
				}
			}else if("INR".equalsIgnoreCase(currencyCode)){
				for(int counter = 0; counter < finalIntervalOffshoreValues.length; counter++){
					finalIntervalOffshoreValues[counter] = String.valueOf(exchangeRates.get(2) * new Float(finalIntervalOffshoreValues[counter]));
				}
			}else{
				for(int counter = 0; counter < finalIntervalOffshoreValues.length; counter++){
					if(Float.parseFloat(finalIntervalOffshoreValues[counter]) < 0 ){
						finalIntervalOffshoreValues[counter] = "-";
					}
					else{
					//finalIntervalOffshoreValues[counter] = String.valueOf(exchangeRates.get(0) * new Float(finalIntervalOffshoreValues[counter]));
					}
				}
				currencyCode = "USD";
			}
			
			
			for(int i =0; i<finalIntervalOffshoreValues.length;i++){
				logger.debug(finalIntervalOffshoreValues[i]);
			}
			
			 ends here 
			
			foffshore.add(finalIntervalOffshoreValues);
		}
		
		
		LaborCostDTO.Row row=lcDTO.new Row();
		row.setServiceScoprID(serviceScopeID);
		row.setFoffshore(foffshore);
		row.setFonshore(fonshore);
		for(int i=0;i<jobRoleDTOListOffshore.size();i++){
			row.getJobRoleListOffshore().add(jobRoleDTOListOffshore.get(i).getJobRoleId());
		}
		for(int i=0;i<jobRoleDTOListOnshore.size();i++){
			row.getJobRoleListOnshore().add(jobRoleDTOListOnshore.get(i).getJobRoleId());
		}
		lcDTO.getRowList().add(row);
		
		
	
	}*/
	
	
}
