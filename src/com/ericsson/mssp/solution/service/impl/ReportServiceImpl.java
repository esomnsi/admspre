/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.service.impl
* File name       		    :  ReportServiceImpl.java
* Description				:	<To Do>
* Author, Date & Release	:	Jan 10, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Jan 10, 2013  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.dto.FTEStagingDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.OpportunityDetailDTO;
import com.ericsson.mssp.common.dto.RateCardDTO;
import com.ericsson.mssp.common.dto.SolutionADRDTO;
import com.ericsson.mssp.common.entity.ServiceScope;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.dao.IReportDAO;
import com.ericsson.mssp.solution.service.IReportService;
import com.ericsson.mssp.solution.service.ISolutionService;

/**
 * @author eruvwyn
 *
 */
@Service
public class ReportServiceImpl implements IReportService {
	@Autowired
	IReportDAO reportDAO;
	
	@Autowired
	ISolutionService solutionService;
	
	public static Logger logger = Logger.getLogger(ReportServiceImpl.class);

	@Override
	public List<ServiceScope> getAllServiceScope() {
		
		List<ServiceScope> listServiceScope= reportDAO.getAllServiceScope();
		return listServiceScope;
	}
	
	public static String getMonthName(int month){
	    String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	    return monthNames[month];
	}
	
	
	@Override
	public String[] getMonthYear(Integer oppId,boolean isCalendar){
		
		OpportunityDTO opportunityDTO = solutionService.getOpportunity(oppId);
		
		String []interval = getMonthYearInterval(opportunityDTO.getOpportunityDetailsDTO().getSssd(),opportunityDTO.getOpportunityDetailsDTO().getSsed(),isCalendar);
		
		return interval;
	}

	
	@Override
	public Map<Integer, String> getServiceScopes(Integer oppId){
		
		Map<Integer, String> serviceScopes = null;
		
		try{
			serviceScopes = solutionService.getAllServiceScopeInMapByOppID(oppId);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return serviceScopes;
	}


	@Override	
	public Map<String, Map<String,Map<String,String[]>>> getFTESummaryReportData(Integer solId, String[] interval, Map<Integer, String> serviceScopes,String location ,String subLocation) throws Exception{		
		
		Map<String,Map<String,String[]>> fteData = new HashedMap();
		
		Map<String,String[]> fteOffshoreData = null;
		Map<String,String[]> fteNearshoreData = null;
		Map<String,String[]> fteOnshoreLocalData = null;
		Map<String,String[]> fteOnshore3PPData = null;
		Map<String,String[]> fteOnshoreGsciData = null;
		
		if("onshore".equalsIgnoreCase(location)){
			fteOnshoreLocalData = new HashedMap();
			fteOnshore3PPData = new HashedMap();
			fteOnshoreGsciData = new HashedMap();
		}else if("offshore".equalsIgnoreCase(location)){
			fteOffshoreData = new HashedMap();
		}else if("nearshore".equalsIgnoreCase(location)){
			fteNearshoreData = new HashedMap();
		}
		
		String[] offshoreFteValue = null;
		String[] nearshoreFteValue = null;
		String[] onshoreLocalFteValue = null;
		String[] onshore3PPFteValue = null;
		String[] onshoreGsciFteValue = null;
		//pass sublocation also
		List <Object[]> fteStagingList = reportDAO.getAllFTEStagingDetailBySolId(solId, location,subLocation);
		for (Map.Entry<Integer, String> entry : serviceScopes.entrySet()) {
			
			offshoreFteValue = new String[interval.length];
			nearshoreFteValue = new String[interval.length];
			onshoreLocalFteValue = new String[interval.length];
			onshore3PPFteValue = new String[interval.length];
			onshoreGsciFteValue = new String[interval.length];
			
				for(Object[] obj : fteStagingList){
					if(((Integer)obj[0]).equals(entry.getKey())){
						String year = ((String) obj[1]).substring(3);
						for(int i = 0;  i<interval.length; i++ ){
							String yearInterval = interval[i].substring(4,8);
							if(year.equals(yearInterval)){
								if("onshore".equalsIgnoreCase(location)){
									if("local".equalsIgnoreCase(obj[4].toString())){
										if((Double)obj[2] > 0){
											onshoreLocalFteValue[i]= obj[2].toString();
										}else{
											onshoreLocalFteValue[i]="0.0";
										}
									}
									if("3pp".equalsIgnoreCase(obj[4].toString())){
										if((Double)obj[2] > 0){
											onshore3PPFteValue[i]= obj[2].toString();
										}else{
											onshore3PPFteValue[i]="0.0";
										}
									}
									if("gsci".equalsIgnoreCase(obj[4].toString())){
										if((Double)obj[2] > 0){
											onshoreGsciFteValue[i]= obj[2].toString();
										}else{
											onshoreGsciFteValue[i]="0.0";
										}
									}
									break;
								}else if("offshore".equalsIgnoreCase(location)){
									if((Double)obj[2] > 0){
										offshoreFteValue[i]= obj[2].toString();
									}else{
										offshoreFteValue[i]="0.0";
									}
									break;
								}else if("nearshore".equalsIgnoreCase(location)){
									if((Double)obj[2] > 0){
										nearshoreFteValue[i]= obj[2].toString();
									}else{
										nearshoreFteValue[i]="0.0";
									}
									break;
								}
							}
						}
					}
				}
				if("onshore".equalsIgnoreCase(location)){
					fteOnshore3PPData.put(entry.getValue(), onshore3PPFteValue);
					fteOnshoreGsciData.put(entry.getValue(), onshoreGsciFteValue);
					fteOnshoreLocalData.put(entry.getValue(), onshoreLocalFteValue);
				}else if("offshore".equalsIgnoreCase(location)){
					fteOffshoreData.put(entry.getValue(),offshoreFteValue);
				}else if("nearshore".equalsIgnoreCase(location)){
					fteNearshoreData.put(entry.getValue(),nearshoreFteValue);
				}
		}
		
		Map<String, Map<String,Map<String,String[]>>> finalDataList = new HashedMap();
		
		if("onshore".equalsIgnoreCase(location)){
			fteData.put("onshoreLocalFteData", fteOnshoreLocalData);
			fteData.put("onshore3PPFteData", fteOnshore3PPData);
			fteData.put("onshoreGsciFteData", fteOnshoreGsciData);
			
			finalDataList.put("onshore", fteData);
			
		}else if("offshore".equalsIgnoreCase(location)){
			fteData.put("offshoreFteData", fteOffshoreData);
			finalDataList.put("offshore",fteData);
		}else if("nearshore".equalsIgnoreCase(location)){
			fteData.put("nearshoreFteData", fteNearshoreData);
			finalDataList.put("nearshore",fteData);
		}
		logger.info("*********** Size of list is : "+fteData);
		return finalDataList;
	}
	
	/*@Override
	public Map<String,String[]> getFTESummaryReportData(Integer solId, String[] interval, Map<Integer, String> serviceScopes,String location) throws Exception{
		
		
		Map<String,String[]> fteData = new HashedMap();
		
		String[] fteValue = null;
		
		List <Object[]> fteStagingList = reportDAO.getAllFTEStagingDetailBySolId(solId, location);
		for (Map.Entry<Integer, String> entry : serviceScopes.entrySet()) {
			fteValue = new String[interval.length];
				for(Object[] obj : fteStagingList){
			
					if(((Integer)obj[0]).equals(entry.getKey())){
						
						String year = ((String) obj[1]).substring(3);
						for(int i = 0;  i<interval.length; i++ ){
							
							String yearInterval = interval[i].substring(4,8);
							if(year.equals(yearInterval)){
								if((Double)obj[2] > 0){
									fteValue[i]= obj[2].toString();
								}else{
									fteValue[i]="0.0";
								}
								break;
							}
						}
						
					}
			
				}
				fteData.put(entry.getValue(), fteValue);
		}
		
		logger.info("*********** Size of list is : "+fteData);
		return fteData;
		
		
	
	}*/
	
	private String[] getMonthYearInterval(String date1, String date2, boolean isCalendar){
		
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		
		String[] interval=null; 
		if((null != date1) && (null != date2) && (date1 != "") && (date2 != "")){
			start.setTime(new Date(date1));
			end.setTime(new Date(date2));
			
			if(isCalendar){
				interval = new String[(end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) + 1];
				for(int i = 0; i< interval.length ;i++){
					interval[i] = getMonthName(0)+" "+(start.get(Calendar.YEAR)+i) +" - " +getMonthName(11)+" "+(start.get(Calendar.YEAR)+i);
				}
			}else{
				
				int i=0,j=0;
				
				if( (end.get(Calendar.YEAR)) - (start.get(Calendar.YEAR)) == 0){
					interval = new String[1];
					interval[0] = getMonthName(start.get(Calendar.MONTH)) + " " + (start.get(Calendar.YEAR)) +" - " + getMonthName(end.get(Calendar.MONTH))+" "+end.get(Calendar.YEAR);
				}else if((start.get(Calendar.MONTH)) == 0){
					interval = new String[(end.get(Calendar.YEAR))-(start.get(Calendar.YEAR))+1];
					for(;i<( (end.get(Calendar.YEAR))-(start.get(Calendar.YEAR)));i++){
						interval[i] = getMonthName(start.get(Calendar.MONTH))+" "+(start.get(Calendar.YEAR)+i) +" - "+ getMonthName((start.get(Calendar.MONTH))+11)+" "+ ((start.get(Calendar.YEAR))+i);
					}
					interval[i] = getMonthName(start.get(Calendar.MONTH))+" "+(start.get(Calendar.YEAR)+i) +" - " + getMonthName(end.get(Calendar.MONTH)) + " " + (end.get(Calendar.YEAR));
				}else if((start.get(Calendar.MONTH)) > (end.get(Calendar.MONTH))){
					interval = new String[( (end.get(Calendar.YEAR))-(start.get(Calendar.YEAR)))];
					for(;i< ( ( (end.get(Calendar.YEAR))-(start.get(Calendar.YEAR)))); j++){
						interval[j] = getMonthName(start.get(Calendar.MONTH)) +" "+(start.get(Calendar.YEAR) +i++)+" - " + getMonthName(start.get(Calendar.MONTH)-1)+" "+(start.get(Calendar.YEAR)+i);
					}
				}else if((start.get(Calendar.MONTH)) <= (end.get(Calendar.MONTH))){
					interval = new String[( (end.get(Calendar.YEAR))-(start.get(Calendar.YEAR))) +1];
					for(;i< ( ( (end.get(Calendar.YEAR))-(start.get(Calendar.YEAR)))); j++){
						interval[j] = getMonthName(start.get(Calendar.MONTH)) +" "+(start.get(Calendar.YEAR) +i++)+" - " + getMonthName(start.get(Calendar.MONTH)-1)+" "+(start.get(Calendar.YEAR)+i);
					}
					interval[j] = getMonthName(start.get(Calendar.MONTH)) +" "+(start.get(Calendar.YEAR) +i++)+" - " + getMonthName(end.get(Calendar.MONTH))+" "+(end.get(Calendar.YEAR));
				}
			}
		}
		return interval;
	}
	
	private Map<String,Map<String,String[]>> getCostSummaryDataContractYear(Integer solId,
			Integer oppId, boolean isCalendar){
		
		String[] monthYear  = getMonthYear(oppId, isCalendar);
		
		Double rateNearshore = 0D;
		Double rateOnshoreLocal = 0D;
		Double rateOnshore3PP = 0D;
		Double rateOnshoreGsci = 0D;
		Double rateOffshore = 0D;
		Double totalRate = 0D;
		
		String[] offshoreRateArray;
		String[] nearshoreRateArray;
		String[] onshoreLocalRateArray;
		String[] onshore3PPRateArray;
		String[] onshoreGsciRateArray;
		String[] totalCostArray;
		
		Map<String, String[]>offshoreRateMap = new HashMap<String, String[]>();
		Map<String, String[]>nearshoreRateMap = new HashMap<String, String[]>();
		Map<String, String[]>onshoreLocalRateMap = new HashMap<String, String[]>();
		Map<String, String[]>onshore3PPRateMap = new HashMap<String, String[]>();
		Map<String, String[]>onshoreGsciRateMap = new HashMap<String, String[]>();
		Map<String, String[]>totalLaborCostMap = new HashMap<String, String[]>();
		Map<String, String[]>graphicsDisplay = new HashMap<String, String[]>();
		
		boolean present = true;
		
		OpportunityDetailDTO detailDTO = solutionService.getOpportunity(oppId).getOpportunityDetailsDTO();
		
		String startDate = detailDTO.getSssd()/*.split("")[0]*/;
		String endDate = detailDTO.getSsed()/*.split("")[0]*/;
		String ed = "";
		String []startDateArray = startDate.split("/");
		String []endDateArray = endDate.split("/");
		
		Integer stDate = Integer.parseInt(startDateArray[2]);
		Integer finalDate = Integer.parseInt(endDateArray[2]);
		Integer edDate = stDate;
		
		Map<Integer, String> serviceScopes = getServiceScopes(oppId);
		Collection<Integer> ids = serviceScopes.keySet();
		Integer[] serviceScopeIds = ids.toArray(new Integer[serviceScopes.size()]);
		Collection<String> values = serviceScopes.values();
		String[] serviceScopeNames = values.toArray(new String[serviceScopes.size()]);
		
		List<RateCardDTO> rateCardDTOs = solutionService.loadRateCardList(solutionService.getOpportunity(oppId).getCustomerDTO().getCountryDTO().getCountryId());
		Map <Integer, Double> onshoreRateCardMap = getRateCardMap(rateCardDTOs,"onshore");
		Map <Integer, Double> offshoreRateCardMap = getRateCardMap(rateCardDTOs,"offshore");
		Map <Integer, Double> nearshoreRateCardMap = getRateCardMap(rateCardDTOs,"nearshore");
		
		String tempStartDate = null;
		String tempEndDate = null;
		
		List<FTEStagingDTO> fteStagingDTOs = new ArrayList<>();
		
		for(int i=0;i<serviceScopes.size();i++){
			offshoreRateArray = new String[monthYear.length];
			onshoreLocalRateArray = new String[monthYear.length];
			onshore3PPRateArray = new String[monthYear.length];
			onshoreGsciRateArray = new String[monthYear.length];
			nearshoreRateArray = new String[monthYear.length];
			totalCostArray  = new String[monthYear.length];
			for(int j =0;j<monthYear.length;j++){
				
				rateOffshore 		= 0.0;
				rateNearshore 		= 0.0;
				rateOnshoreLocal 	= 0.0;
				rateOnshore3PP 		= 0.0;
				rateOnshoreGsci 	= 0.0;
				totalRate 			= 0.0;
			
				if(edDate < finalDate){
					
					tempStartDate = "";
					tempEndDate = "";
					
					stDate = edDate;
					edDate = stDate + 1;
					
					tempStartDate = String.valueOf(stDate)+"-"+startDateArray[0]+"-"+startDateArray[1];
					if(Integer.parseInt(startDateArray[0])-1 == 2){
						if(Integer.parseInt(startDateArray[1])-1 > 28){
							tempEndDate = String.valueOf(edDate)+"-"+ String.valueOf((Integer.parseInt(startDateArray[0]))-1)+"-28";
						}
					}else{
						tempEndDate = String.valueOf(edDate)+"-"+ String.valueOf((Integer.parseInt(startDateArray[0]))-1)+"-"+String.valueOf(Integer.parseInt(startDateArray[1]) - 1);
					}
					
					if(Integer.parseInt(tempEndDate.split("-")[0]) == finalDate){
						if(Integer.parseInt(tempEndDate.split("-")[1])>Integer.parseInt(endDate.split("/")[0])){
							tempEndDate = endDate;
							System.out.println("temp start|end date : " + tempStartDate+"|"+tempEndDate);
							System.out.println("tempend == finaldate breaking");
							break;
						}else{
							
						}
					}
					
					System.out.println("temp end|start dates : " + tempEndDate+"|"+tempStartDate);
					fteStagingDTOs = reportDAO.getFTEStaingDataForContractYear(solId,serviceScopeIds[i],tempStartDate, tempEndDate);
				}else if(edDate.intValue() == finalDate.intValue()){
					String finalStartDate = "";
					if(null == tempEndDate){
						finalStartDate = startDateArray[2]+"-"+startDateArray[0]+"-"+startDateArray[1];
					}
					else if(Integer.parseInt(tempEndDate.split("-")[1]) <= (Integer.parseInt(endDate.split("/")[0]))){
						finalStartDate = tempEndDate.split("-")[0]+"-"+tempEndDate.split("-")[1]+"-"+String.valueOf(Integer.parseInt(tempEndDate.split("-")[2])+1);
					}
					
					ed = endDateArray[2]+"-"+endDateArray[0]+"-"+endDateArray[1];
					
					
					System.out.println("final start date | final date : " + finalStartDate+"|"+ed);
					fteStagingDTOs = reportDAO.getFTEStaingDataForContractYear(solId,serviceScopeIds[i],finalStartDate,ed);
				}
				
			
					for( FTEStagingDTO fteStagingDTO : fteStagingDTOs ){
					
						if("nearshore".equalsIgnoreCase(fteStagingDTO.getLocation())){
							if(nearshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId()) != null){
								rateNearshore += (nearshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId())) * (fteStagingDTO.getFtecount())/* * (hoursPerMonth)*/;
							}
							if(rateNearshore < 0.0){
								rateNearshore = 0.0;
							}
						}else if("offshore".equalsIgnoreCase(fteStagingDTO.getLocation())){
							if(offshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId()) != null){
								rateOffshore += (offshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId())) * (fteStagingDTO.getFtecount())/* * (hoursPerMonth)*/;
							}
							if(rateOffshore < 0.0){
								rateOffshore = 0.0;
							}
						}else if("onshore".equalsIgnoreCase(fteStagingDTO.getLocation())){
							if("3pp".equalsIgnoreCase(fteStagingDTO.getSubLocation())){
								if(onshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId()) != null){
									rateOnshore3PP += (onshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId())) * (fteStagingDTO.getFtecount())/* * (hoursPerMonth)*/;
								}
								if(rateOnshore3PP < 0.0){
									rateOnshore3PP = 0.0;
								}
							}else if("local".equalsIgnoreCase(fteStagingDTO.getSubLocation())){
								if(onshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId()) != null){
									rateOnshoreLocal += (onshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId())) * (fteStagingDTO.getFtecount())/* * (hoursPerMonth)*/;
								}
								if(rateOnshoreLocal < 0.0){
									rateOnshoreLocal = 0.0;
								}
							}else if("gsci".equalsIgnoreCase(fteStagingDTO.getSubLocation())){
								if(onshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId()) != null){
									rateOnshoreGsci += (onshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId())) * (fteStagingDTO.getFtecount())/* * (hoursPerMonth)*/;
								}
								if(rateOnshoreGsci < 0.0){
									rateOnshoreGsci = 0.0;
								}
							}
						}
					}
				
					totalRate = rateOffshore + rateOnshoreLocal + rateOnshore3PP + rateOnshoreGsci + rateNearshore;
					if(present){
						if(totalRate <= 0.0){
							//graphicsDisplay = null;
							present = true;
						}else{
							String temp[] = {"found"};
							graphicsDisplay.put("found", temp);
							present = false;
						}
					}
					totalCostArray[j] = ((new DecimalFormat("#.##")).format(totalRate)).toString();
					offshoreRateArray[j] = ((rateOffshore.toString() != "") || (null != rateOffshore)) ? ((new DecimalFormat("#.##")).format(rateOffshore)).toString() : "0.0";
					onshoreLocalRateArray[j] = ((rateOnshoreLocal.toString() != "") || (null != rateOnshoreLocal)) ? ((new DecimalFormat("#.##")).format(rateOnshoreLocal)).toString() : "0.0";
					onshore3PPRateArray[j] = ((rateOnshore3PP.toString() != "") || (null != rateOnshore3PP)) ? ((new DecimalFormat("#.##")).format(rateOnshore3PP)).toString() : "0.0";
					onshoreGsciRateArray[j] = ((rateOnshoreGsci.toString() != "") || (null != rateOnshoreGsci)) ? ((new DecimalFormat("#.##")).format(rateOnshoreGsci)).toString() : "0.0";
					nearshoreRateArray[j] = ((rateNearshore.toString() != "") || (null != rateNearshore)) ? ((new DecimalFormat("#.##")).format(rateNearshore)).toString() : "0.0";
			}
			
			offshoreRateMap.put(serviceScopeNames[i], offshoreRateArray);
			onshoreLocalRateMap.put(serviceScopeNames[i], onshoreLocalRateArray);
			onshore3PPRateMap.put(serviceScopeNames[i], onshore3PPRateArray);
			onshoreGsciRateMap.put(serviceScopeNames[i], onshoreGsciRateArray);
			nearshoreRateMap.put(serviceScopeNames[i], nearshoreRateArray);
			totalLaborCostMap.put(serviceScopeNames[i], totalCostArray);
		}	
		
		Map<String,Map<String,String[]>> costSummaryData = new HashMap<String, Map<String,String[]>>();
		
		costSummaryData.put("offshore", offshoreRateMap);
		costSummaryData.put("nearshore", nearshoreRateMap);
		costSummaryData.put("onshoreLocal", onshoreLocalRateMap);
		costSummaryData.put("onshore3PP", onshore3PPRateMap);
		costSummaryData.put("onshoreGsci", onshoreGsciRateMap);
		costSummaryData.put("totalLaborCost", totalLaborCostMap);
		if(present){
		costSummaryData.put("graphicsDisplay", null);
		}else{
			costSummaryData.put("graphicsDisplay", graphicsDisplay);
		}
		return costSummaryData;
	}

	@Override
	public Map<String,Map<String,String[]>> getCostSummaryData(Integer solId,
			Integer oppId, boolean isCalendar) {
		
		if(!isCalendar){
			return getCostSummaryDataContractYear(solId, oppId, isCalendar);
		}
		
		Map<Integer, String> serviceScopes = getServiceScopes(oppId);
		
		Double rateNearshore = 0D;
		Double rateOnshoreLocal = 0D;
		Double rateOnshore3PP = 0D;
		Double rateOnshoreGsci = 0D;
		Double rateOffshore = 0D;
		Double totalRate = 0D;
		
		String[] offshoreRateArray;
		String[] nearshoreRateArray;
		String[] onshoreLocalRateArray;
		String[] onshore3PPRateArray;
		String[] onshoreGsciRateArray;
		String[] totalCostArray;
		
		Map<String, String[]>offshoreRateMap = new HashMap<String, String[]>();
		Map<String, String[]>nearshoreRateMap = new HashMap<String, String[]>();
		Map<String, String[]>onshoreLocalRateMap = new HashMap<String, String[]>();
		Map<String, String[]>onshore3PPRateMap = new HashMap<String, String[]>();
		Map<String, String[]>onshoreGsciRateMap = new HashMap<String, String[]>();
		Map<String, String[]>totalLaborCostMap = new HashMap<String, String[]>();
		Map<String, String[]>graphicsDisplay = new HashMap<String, String[]>();
		
		boolean present = true;
		
		Collection<Integer> ids = serviceScopes.keySet();
		Integer[] serviceScopeIds = ids.toArray(new Integer[serviceScopes.size()]);
		
		Collection<String> values = serviceScopes.values();
		String[] serviceScopeNames = values.toArray(new String[serviceScopes.size()]);
		
		//float hoursPerMonth = new Float(ApplicationPropertiesUtil.getProperty("msg.labourcost.hoursmonth"));
		
		List<RateCardDTO> rateCardDTOs = solutionService.loadRateCardList(solutionService.getOpportunity(oppId).getCustomerDTO().getCountryDTO().getCountryId());
		
		Map <Integer, Double> onshoreRateCardMap = getRateCardMap(rateCardDTOs,"onshore");
		Map <Integer, Double> offshoreRateCardMap = getRateCardMap(rateCardDTOs,"offshore");
		Map <Integer, Double> nearshoreRateCardMap = getRateCardMap(rateCardDTOs,"nearshore");
		
		String[] monthYear  = getMonthYear(oppId, isCalendar);
		
		if(null != monthYear)
		for(int serviceScopeCounter = 0; serviceScopeCounter < serviceScopes.size(); serviceScopeCounter++){
			
			List<FTEStagingDTO> fteStagingDTOs = solutionService.getFteStagingData(solId,serviceScopeIds[serviceScopeCounter]);
			offshoreRateArray = new String[monthYear.length];
			onshoreLocalRateArray = new String[monthYear.length];
			onshore3PPRateArray = new String[monthYear.length];
			onshoreGsciRateArray = new String[monthYear.length];
			nearshoreRateArray = new String[monthYear.length];
			totalCostArray  = new String[monthYear.length];
			
			for(int yearCounter = 0; yearCounter < monthYear.length; yearCounter++){
				
				String intervalYear = monthYear[yearCounter].substring(4,8);
				rateOffshore = 0.0;
				rateNearshore = 0.0;
				rateOnshoreLocal = 0.0;
				rateOnshore3PP = 0.0;
				rateOnshoreGsci = 0.0;
				totalRate = 0.0;
				for( FTEStagingDTO fteStagingDTO : fteStagingDTOs ){
					
					String year = (fteStagingDTO.getMonthYear().toString()).split("-")[0];
					
					if(year.equals(intervalYear)){
						if("nearshore".equalsIgnoreCase(fteStagingDTO.getLocation())){
							if(nearshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId()) != null){
								rateNearshore += (nearshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId())) * (fteStagingDTO.getFtecount())/* * (hoursPerMonth)*/;
							}
							if(rateNearshore < 0.0){
								rateNearshore = 0.0;
							}
						}else if("offshore".equalsIgnoreCase(fteStagingDTO.getLocation())){
							if(offshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId()) != null){
								rateOffshore += (offshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId())) * (fteStagingDTO.getFtecount())/* * (hoursPerMonth)*/;
							}
							if(rateOffshore < 0.0){
								rateOffshore = 0.0;
							}
						}else if("onshore".equalsIgnoreCase(fteStagingDTO.getLocation())){
							if("3pp".equalsIgnoreCase(fteStagingDTO.getSubLocation())){
								if(onshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId()) != null){
									rateOnshore3PP += (onshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId())) * (fteStagingDTO.getFtecount())/* * (hoursPerMonth)*/;
								}
								if(rateOnshore3PP < 0.0){
									rateOnshore3PP = 0.0;
								}
							}else if("local".equalsIgnoreCase(fteStagingDTO.getSubLocation())){
								if(onshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId()) != null){
									rateOnshoreLocal += (onshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId())) * (fteStagingDTO.getFtecount())/* * (hoursPerMonth)*/;
								}
								if(rateOnshoreLocal < 0.0){
									rateOnshoreLocal = 0.0;
								}
							}else if("gsci".equalsIgnoreCase(fteStagingDTO.getSubLocation())){
								if(onshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId()) != null){
									rateOnshoreGsci += (onshoreRateCardMap.get(fteStagingDTO.getJobRoleStagesDTO().getJobRoleStagesId())) * (fteStagingDTO.getFtecount())/* * (hoursPerMonth)*/;
								}
								if(rateOnshoreGsci < 0.0){
									rateOnshoreGsci = 0.0;
								}
							}
						}
					}
				}
				totalRate = rateOffshore + rateOnshoreLocal + rateOnshore3PP + rateOnshoreGsci + rateNearshore;
				if(present){
					if(totalRate <= 0.0){
						//graphicsDisplay = null;
						present = true;
					}else{
						String temp[] = {"found"};
						graphicsDisplay.put("found", temp);
						present = false;
					}
				}
				totalCostArray[yearCounter] = ((new DecimalFormat("#.##")).format(totalRate)).toString();
				offshoreRateArray[yearCounter] = ((rateOffshore.toString() != "") || (null != rateOffshore)) ? ((new DecimalFormat("#.##")).format(rateOffshore)).toString() : "0.0";
				onshoreLocalRateArray[yearCounter] = ((rateOnshoreLocal.toString() != "") || (null != rateOnshoreLocal)) ? ((new DecimalFormat("#.##")).format(rateOnshoreLocal)).toString() : "0.0";
				onshore3PPRateArray[yearCounter] = ((rateOnshore3PP.toString() != "") || (null != rateOnshore3PP)) ? ((new DecimalFormat("#.##")).format(rateOnshore3PP)).toString() : "0.0";
				onshoreGsciRateArray[yearCounter] = ((rateOnshoreGsci.toString() != "") || (null != rateOnshoreGsci)) ? ((new DecimalFormat("#.##")).format(rateOnshoreGsci)).toString() : "0.0";
				nearshoreRateArray[yearCounter] = ((rateNearshore.toString() != "") || (null != rateNearshore)) ? ((new DecimalFormat("#.##")).format(rateNearshore)).toString() : "0.0";
			}
		
		offshoreRateMap.put(serviceScopeNames[serviceScopeCounter], offshoreRateArray);
		onshoreLocalRateMap.put(serviceScopeNames[serviceScopeCounter], onshoreLocalRateArray);
		onshore3PPRateMap.put(serviceScopeNames[serviceScopeCounter], onshore3PPRateArray);
		onshoreGsciRateMap.put(serviceScopeNames[serviceScopeCounter], onshoreGsciRateArray);
		nearshoreRateMap.put(serviceScopeNames[serviceScopeCounter], nearshoreRateArray);
		totalLaborCostMap.put(serviceScopeNames[serviceScopeCounter], totalCostArray);
		}
		
		Map<String,Map<String,String[]>> costSummaryData = new HashMap<String, Map<String,String[]>>();
		
		costSummaryData.put("offshore", offshoreRateMap);
		costSummaryData.put("nearshore", nearshoreRateMap);
		costSummaryData.put("onshoreLocal", onshoreLocalRateMap);
		costSummaryData.put("onshore3PP", onshore3PPRateMap);
		costSummaryData.put("onshoreGsci", onshoreGsciRateMap);
		costSummaryData.put("totalLaborCost", totalLaborCostMap);
		if(present){
		costSummaryData.put("graphicsDisplay", null);
		}
		else{
			costSummaryData.put("graphicsDisplay", graphicsDisplay);
		}
		return costSummaryData;
	}
	
	
	private Map<Integer,Double> getRateCardMap(List<RateCardDTO> rateCardDTOs,String location){
		
		Map<Integer,Double> map = new HashMap<Integer,Double>();
		for(RateCardDTO rateCardDTO : rateCardDTOs){
			if(location.equalsIgnoreCase(rateCardDTO.getLocation()))
			if(rateCardDTO.getJobRoleStages() != null){
				map.put(rateCardDTO.getJobRoleStages().getJobRoleStagesId(), rateCardDTO.getRate());
			}
		}
		return map;
	}
	

	
	@Override
	public List<SolutionADRDTO> getADRReportDetails(Integer solutionID,String adrCategory) {
		// TODO Auto-generated method stub
		
		List<SolutionADRDTO> resultSet = reportDAO.getADRReportDetails(solutionID, adrCategory);
		return resultSet;
	}
	
	@Override
	public String getCurrencyCode(Integer oppportunityID) throws MSSPException{
		String currencyCode = null;
		try {
			currencyCode = reportDAO.getCurrencyCode(oppportunityID);
		} catch (Exception e) {
		    logger.error(e.getMessage() + " |  " + e.getCause());
		    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		}
		return currencyCode;
	}	

}
