package com.ericsson.mssp.report.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ericsson.mssp.approval.forms.ExecSummary;
import com.ericsson.mssp.approval.forms.ExecSummary.Row;
import com.ericsson.mssp.approval.service.IApprovalService;
import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.FteHeadCountAndPercentageDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.OpportunityDetailDTO;
import com.ericsson.mssp.common.dto.SolutionADRDTO;
import com.ericsson.mssp.solution.controller.BaseController;
import com.ericsson.mssp.solution.service.IReportService;
import com.ericsson.mssp.solution.service.ISolutionService;

@Controller
public class ReportViewController extends BaseController {

	public static Logger logger = Logger.getLogger(ReportViewController.class);
	
	boolean calanderYear = true;
	Map<String, String[]> laborCostReportData;
	Map<String, String[]> onshoreLocalReportData;
	Map<String, String[]> onshore3PPReportData;
	Map<String, String[]> onshoreGsciReportData;
	Map<String, String[]> offshoreReportData;	
	String [] nlcReportData = null;
	String[][] fteOnLocalShore;
	String[][] fteOn3PPShore;
	String[][] fteOnGsciShore;
	String[][] fteOffShore;
	String[][] fteNearShore;
	
	@Autowired
	ISolutionService iSolutionService;
	
	@Autowired
	IReportService reportService;
	
	
	@RequestMapping(value = "/report/ViewReports")
	public String showReportView(HttpSession session) throws Exception{
		System.out.println("Report Controller");
		return "viewReports";
	}
	
	@RequestMapping(value="/report/viewStaffPyramid")
	public String staffPyramid(HttpSession session, ModelMap model){
		
		logger.info("inside view staff pyramid report");
		Integer solutionID = getSessionSolutionId(session);
		Integer opportunityId = getSessionOpportunityId(session);
		Map<String,Object> map =  iSolutionService.getServiceScopeNamesFteHeadCountAndPercentage(solutionID);
		
		List<Map<String,String[]>> stafPyrReport = new ArrayList<>();
		int tempServiceScopeID = -1;
		int tempjobRoleStageID = -1;
		
		try{
			OpportunityDTO opportunityDTO = iSolutionService.getOpportunity(opportunityId);
			
			
			
			OpportunityDetailDTO opportunityDetailDTO = null;
			if(opportunityDTO.getOpportunityDetailsDTO() != null){
				opportunityDetailDTO = new OpportunityDetailDTO();
				opportunityDetailDTO = opportunityDTO.getOpportunityDetailsDTO();
			}
			
			
			String startDate = opportunityDetailDTO.getSssd();
			String endDate = opportunityDetailDTO.getSsed();
			if((null != startDate) || (null != endDate)){
			String year1 = startDate.substring(startDate.lastIndexOf("/")+1, startDate.length());
			String year2 = endDate.substring(endDate.lastIndexOf("/")+1, endDate.length());
			
			Integer diff = Integer.parseInt(year2)-Integer.parseInt(year1)+1;
			String []timeLine = new String[diff];
			
			String currency = /*iSolutionService.getOpportunity(opportunityId).getCustomerDTO().getCountryDTO().getCurrencyName()+*/"["+iSolutionService.getOpportunity(opportunityId).getCustomerDTO().getCountryDTO().getCurrencyCode()+"]";
			
			for(int i=0;i<diff;i++){
				
				timeLine[i] = year1;
				year1 = String.valueOf((Integer.parseInt(year1) + 1));
				
			}
			
			Map<Integer,String> jobRoleDTOs = iSolutionService.getJobRoles();
			logger.info("***************************************************");
			
			Map<Integer,String> serviceScopeNames = (Map<Integer,String>)map.get("SSN");
			logger.info("service scope names : " + serviceScopeNames.size());
			
			List<FteHeadCountAndPercentageDTO> fteHeadCountDtos = (List<FteHeadCountAndPercentageDTO>)map.get("FHC");
			logger.info("fte head count : " + fteHeadCountDtos.size());
			
			logger.info("time line : " + timeLine.length);
			
			
			
			String[] serviceScopes = serviceScopeNames.values().toArray(new String[serviceScopeNames.size()]);
			Integer[] serviceScopeIds = serviceScopeNames.keySet().toArray(new Integer[serviceScopeNames.size()]);
			String serviceScopeHeaderName = "";
			String jobRoleDesc = "";
			String[] interval = {""};	//Number Of Elements in FteHeadCountAndPercentageDTO
			String[]fteCount = null;
			int count=0;
			int addRowToMap = 0;
			boolean firstRowAddition = false;
			
			
			Map<String,String[]> timeInterval = new HashMap<>();
			timeInterval.put("Job Level & Career Stage", timeLine);
			stafPyrReport.add(timeInterval);
			
			for(FteHeadCountAndPercentageDTO percentageDTO : fteHeadCountDtos){
				
				if(percentageDTO.getOpportunityScopeID() != tempServiceScopeID){
					tempServiceScopeID = percentageDTO.getOpportunityScopeID();
					for(int i=0;i<serviceScopeIds.length;i++){
						if(tempServiceScopeID == serviceScopeIds[i]){
							serviceScopeHeaderName = serviceScopes[i];
						}
					}
					Map<String,String[]> headerRowMap = new HashMap<>();
					headerRowMap.put(serviceScopeHeaderName, interval);
					stafPyrReport.add(headerRowMap);
					tempjobRoleStageID = -1;
					addRowToMap = 0;
				}
				
				
				if(tempServiceScopeID == percentageDTO.getOpportunityScopeID()){
					if(percentageDTO.getJobRoleStageID() != tempjobRoleStageID){
					
						addRowToMap++;
						
						if(addRowToMap == 2 ){
							Map<String,String[]> fteValues = new HashMap<>();
							fteValues.put(jobRoleDesc, fteCount);
							stafPyrReport.add(fteValues);
							addRowToMap--;
						}

						fteCount = new String[timeLine.length];
						tempjobRoleStageID = percentageDTO.getJobRoleStageID();
						jobRoleDesc = percentageDTO.getJobRoleDesc();
						count = 0;
					}
					
					if(tempjobRoleStageID == percentageDTO.getJobRoleStageID()){
						fteCount[count] = percentageDTO.getFteHeadCount().toString();
					}
					count++;
				}
			}
			
			Map<String,String[]> fteValues = new HashMap<>();
			fteValues.put(jobRoleDesc, fteCount);
			stafPyrReport.add(fteValues);
			
			model.addAttribute("stafPyrReport",stafPyrReport);
			model.addAttribute("serviceScopeNames",serviceScopeNames);
			model.addAttribute("fteHeadCount", fteHeadCountDtos);
			model.addAttribute("timeLine", timeLine);
			model.addAttribute("jobRole",jobRoleDTOs);
			model.addAttribute("currency",currency);
			}else{
				model.addAttribute("stafPyrReport",stafPyrReport);
			}
		}
		catch(Exception me){
			me.printStackTrace();
		}
		return "staffingPyramidReport";
	}
	
	@RequestMapping(value="/report/fteChart")
	public String fteChartData(HttpSession session, ModelMap model){
		
		String[] intervals = null;
		Integer opportunityId = getSessionOpportunityId(session);
		intervals =  reportService.getMonthYear(opportunityId, calanderYear);
		Map<Integer, String> serviceScopes =  reportService.getServiceScopes(opportunityId);
		String[][] LfteOnLocalShore = fteOnLocalShore;
		String[][] LfteOn3PPShore =   fteOn3PPShore;
		String[][] LfteOnGsciShore =  fteOnGsciShore;
		String[][] LfteOffShore =     fteOffShore;
		String[][] LfteNearShore =    fteNearShore;
		
		String []local = new String[intervals.length];
		String []ppp = new String[intervals.length];
		String []gsci = new String[intervals.length];
		String []off = new String[intervals.length];
		String []near = new String[intervals.length];
		
		Float temp1 = 0f;
		Float temp2 = 0f;
		Float temp3 = 0f;
		Float temp4 = 0f;
		Float temp5 = 0f;
		
		for(int year=0;year<intervals.length;year++){
			temp1 =0f;
			temp2 =0f;
			temp3 =0f;
			temp4 =0f;
			temp5 =0f;
			for(int serviceScope=0;serviceScope<serviceScopes.size();serviceScope++){
				
				temp1 = temp1 + Float.parseFloat(((null != LfteOnLocalShore[serviceScope][year]) && ("" != LfteOnLocalShore[serviceScope][year])) ?LfteOnLocalShore[serviceScope][year]:"0.0");
				temp2 = temp2 + Float.parseFloat(((null != LfteOn3PPShore[serviceScope][year]) && ("" != LfteOn3PPShore[serviceScope][year])) ?LfteOn3PPShore[serviceScope][year]:"0.0");
				temp3 = temp3 + Float.parseFloat(((null != LfteOnGsciShore[serviceScope][year]) && ("" != LfteOnGsciShore[serviceScope][year])) ?LfteOnGsciShore[serviceScope][year]:"0.0");
				temp4 = temp4 + Float.parseFloat(((null != LfteOffShore[serviceScope][year]) && ("" != LfteOffShore[serviceScope][year])) ?LfteOffShore[serviceScope][year]:"0.0");
				temp5 = temp5 + Float.parseFloat(((null != LfteNearShore[serviceScope][year]) && ("" != LfteNearShore[serviceScope][year])) ?LfteNearShore[serviceScope][year]:"0.0");
			}
			local[year] = String.valueOf(temp1);
			ppp[year] = String.valueOf(temp2);
			gsci[year] = String.valueOf(temp3);
			off[year] = String.valueOf(temp4);
			near[year] = String.valueOf(temp5);
		}
		
		String []totalOnshore = new String[intervals.length];
		
		
		for(int i = 0;i<intervals.length;i++){
			totalOnshore[i] = String.valueOf(Float.parseFloat(local[i]) + Float.parseFloat(ppp[i]) + Float.parseFloat(gsci[i]));
		}
		
		for(int i=0;i<intervals.length;i++){
			System.out.println("onshore : " + totalOnshore[i]);
			System.out.println("off :  " + off[i]);
			System.out.println("near : " + near[i]);
		}
		
		
		String data = "[ ";
		
		for(int i=0;i<intervals.length;i++){
			data += "{ \"Onshore FTE\":"+totalOnshore[i]+",\" Offshore FTE\":"+off[i]+",\" Nearshore FTE\":"+near[i]+",\"Year\":\"Year "+(i+1)+"\" },";
		}
		data = data.substring(0, data.length()-1);
		data += " ]";
		
		System.out.println("json string : " + data);
		
		model.addAttribute("data",data);
		
		return "fteChart";
	}
	
	
	@RequestMapping(value="/report/reportFTESummary")
	public String reportFTESummary(HttpSession session, ModelMap model){
		
		logger.info("inside view staff pyramid report");
		Integer solutionID = getSessionSolutionId(session);
		Integer opportunityId = getSessionOpportunityId(session);
		String[] intervals = null;
		
		try {
			
			Map<Integer, String> serviceScopes =  reportService.getServiceScopes(opportunityId);
			
			intervals =  reportService.getMonthYear(opportunityId, calanderYear);
			
			for(int i=0;i<intervals.length;i++){
				System.out.println(intervals[i]);
			}
			
			if(null != intervals){
			//  for Onshore local
			Map<String, Map<String,Map<String,String[]>>> onShoreFTESummaryMap = reportService.getFTESummaryReportData(solutionID, intervals, serviceScopes, MSSPConstants.LOCATION_ONSHORE,MSSPConstants.SUBLOC_LOCAL);
			
			//  for Onshore 3pp

			Map<String, Map<String,Map<String,String[]>>> onShoreFTESummaryMap_3pp = reportService.getFTESummaryReportData(solutionID, intervals, serviceScopes, MSSPConstants.LOCATION_ONSHORE,MSSPConstants.SUBLOC_3PP);
			
			//  for Onshore GSCi

			Map<String, Map<String,Map<String,String[]>>> onShoreFTESummaryMap_SUBLOC_GSCI = reportService.getFTESummaryReportData(solutionID, intervals, serviceScopes, MSSPConstants.LOCATION_ONSHORE,MSSPConstants.SUBLOC_GSCI);

		
			
			Map<String, Map<String,Map<String,String[]>>> offShoreFTESummaryMap = reportService.getFTESummaryReportData(solutionID, intervals, serviceScopes, MSSPConstants.LOCATION_OFFSHORE,null);
			Map<String, Map<String,Map<String,String[]>>> nearShoreFTESummaryMap = reportService.getFTESummaryReportData(solutionID, intervals, serviceScopes, MSSPConstants.LOCATION_NEARSHORE,null);
			
			Map<String,Map<String,String[]>> offshoreFteMap = offShoreFTESummaryMap.get("offshore");
			Map<String,Map<String,String[]>> nearshoreFteMap = nearShoreFTESummaryMap.get("nearshore");
			Map<String,Map<String,String[]>> onshoreFteMap = onShoreFTESummaryMap.get("onshore");
			Map<String,Map<String,String[]>> onshoreFteMap2 = onShoreFTESummaryMap_3pp.get("onshore");
			Map<String,Map<String,String[]>> onshoreFteMap3 = onShoreFTESummaryMap_SUBLOC_GSCI.get("onshore");
			
			Map <String, String[]> onShoreLocalFTESummary = onshoreFteMap.get("onshoreLocalFteData");
			
			Map <String, String[]> onShore3PPFTESummary = onshoreFteMap2.get("onshore3PPFteData");
			Map <String, String[]> onShoreGsciFTESummary = onshoreFteMap3.get("onshoreGsciFteData");
			
			Map <String, String[]> offShoreFTESummary = offshoreFteMap.get("offshoreFteData");
			Map <String, String[]> nearShoreFTESummary = nearshoreFteMap.get("nearshoreFteData");
			
			
			fteOnLocalShore = null;
			fteOn3PPShore = null;
			fteOnGsciShore = null;
			fteOffShore = null;
			fteNearShore = null;
			
			
			
			
			Map<String, String[]> totalFTESummary = new HashMap<>();
			
			String [][]offshore = new String[serviceScopes.size()][intervals.length];
			String [][]onshoreLocal = new String[serviceScopes.size()][intervals.length];
			String [][]onshore3PP = new String[serviceScopes.size()][intervals.length];
			String [][]onshoreGsci = new String[serviceScopes.size()][intervals.length];
			String [][]nearshore = new String[serviceScopes.size()][intervals.length];
			
			String[] serviceScopeName = offShoreFTESummary.keySet().toArray(new String[serviceScopes.size()]);
			
			int i=0;
			for(Map.Entry<String,String[]> instance : offShoreFTESummary.entrySet()){
				if(i<offShoreFTESummary.size()){
					offshore[i] = instance.getValue();
					i++;
				}
			}
			i = 0;
			
			for(Map.Entry<String,String[]> instance : onShoreLocalFTESummary.entrySet()){
				if(i<onShoreLocalFTESummary.size()){
					onshoreLocal[i] = instance.getValue();
					i++;
				}
			}
			
			i = 0;
			
			for(Map.Entry<String,String[]> instance : onShore3PPFTESummary.entrySet()){
				if(i<onShore3PPFTESummary.size()){
					onshore3PP[i] = instance.getValue();
					i++;
				}
			}
			
			i = 0;
			
			for(Map.Entry<String,String[]> instance : onShoreGsciFTESummary.entrySet()){
				if(i<onShoreGsciFTESummary.size()){
					onshoreGsci[i] = instance.getValue();
					i++;
				}
			}
			
			i = 0;
			
			for(Map.Entry<String,String[]> instance : nearShoreFTESummary.entrySet()){
				if(i<nearShoreFTESummary.size()){
					nearshore[i] = instance.getValue();
					i++;
				}
			}
			
			
			fteOnLocalShore = onshoreLocal;
			fteOn3PPShore = onshore3PP;
			fteOnGsciShore = onshoreGsci;
			fteOffShore = offshore;
			fteNearShore = nearshore;
			
			
			
			DecimalFormat f = new DecimalFormat("##.00");
			String []tempFteData = null;
			for(int j=0;j<serviceScopes.size();j++){
				tempFteData = new String[intervals.length];
				for(int k=0;k<intervals.length;k++){
					
					double d = (offshore[j][k] != null)?Double.parseDouble(offshore[j][k]):0.00;
					double d1 = (onshoreLocal[j][k] != null)?Double.parseDouble(onshoreLocal[j][k]):0.00;
					double d2 = (onshore3PP[j][k] != null)?Double.parseDouble(onshore3PP[j][k]):0.00;
					double d3 = (onshoreGsci[j][k] != null)?Double.parseDouble(onshoreGsci[j][k]):0.00;
					double d4 = (nearshore[j][k] != null)?Double.parseDouble(nearshore[j][k]):0.00;
					
					double temp = d + d1 + d2 + d3 + d4;
					if(temp == 0)
					{
						
					}else{
						tempFteData[k] = f.format(temp);
					}
				}
				totalFTESummary.put(serviceScopeName[j], tempFteData);
			}
			
			String[] headerRow = new String[intervals.length];
			String[] headerRowWithCurrency = new String[intervals.length];
			List<Map<String,String[]>> fteSummaryReportList = new ArrayList<>();
			
			String currencyCode = reportService.getCurrencyCode(opportunityId).trim();
			headerRowWithCurrency[0] = "currency in ["+currencyCode+"]";
			
			System.out.println("Total FTESummary in "+currencyCode.trim());
			
			Map<String,String[]> mapForTotalFTESummary = new HashMap<>();
			mapForTotalFTESummary.put("Total FTESummary", headerRow);
			fteSummaryReportList.add(mapForTotalFTESummary);
			
			Map<String,String[]> mapForInterval_4 = new HashMap<>();
			mapForInterval_4.put("Service Scopes", intervals);
			fteSummaryReportList.add(mapForInterval_4);
			
			for(Map.Entry<String, String[]> map : totalFTESummary.entrySet()){
				
				Map<String,String[]> temp = new HashMap<>();
				temp.put(map.getKey(), map.getValue());
				fteSummaryReportList.add(temp);
			}
			
			Map<String,String[]> mapForGap_3 = new HashMap<>();
			mapForGap_3.put("LINE", headerRow);
			fteSummaryReportList.add(mapForGap_3);
			
			Map<String,String[]> mapForOffshoreFTE = new HashMap<>();
			mapForOffshoreFTE.put("Offshore (GSC) FTESummary", headerRow);
			fteSummaryReportList.add(mapForOffshoreFTE);
			
			Map<String,String[]> mapForInterval_1 = new HashMap<>();
			mapForInterval_1.put("Service Scopes", intervals);
			fteSummaryReportList.add(mapForInterval_1);
			
			for(Map.Entry<String, String[]> map : offShoreFTESummary.entrySet()){
				
				Map<String,String[]> temp = new HashMap<>();
				temp.put(map.getKey(), map.getValue());
				fteSummaryReportList.add(temp);
			}
			
			Map<String,String[]> mapForGap_1 = new HashMap<>();
			mapForGap_1.put("LINE", headerRow);
			fteSummaryReportList.add(mapForGap_1);
			
			Map<String,String[]> mapForOnshoreFTE_1 = new HashMap<>();
			mapForOnshoreFTE_1.put("Onshore (Local) FTESummary", headerRow);
			fteSummaryReportList.add(mapForOnshoreFTE_1);
			
			Map<String,String[]> mapForInterval_2_1 = new HashMap<>();
			mapForInterval_2_1.put("Service Scopes", intervals);
			fteSummaryReportList.add(mapForInterval_2_1);
			
			for(Map.Entry<String, String[]> map : onShoreLocalFTESummary.entrySet()){
				
				Map<String,String[]> temp = new HashMap<>();
				temp.put(map.getKey(), map.getValue());
				fteSummaryReportList.add(temp);
			}
			
			Map<String,String[]> mapForGap_2_2 = new HashMap<>();
			mapForGap_2_2.put("LINE", headerRow);
			fteSummaryReportList.add(mapForGap_2_2);
			
			
			
			Map<String,String[]> mapForOnshoreFTE_2 = new HashMap<>();
			mapForOnshoreFTE_2.put("Onshore (GSC Landed) FTESummary", headerRow);
			fteSummaryReportList.add(mapForOnshoreFTE_2);
			
			Map<String,String[]> mapForInterval_2_2 = new HashMap<>();
			mapForInterval_2_2.put("Service Scopes", intervals);
			fteSummaryReportList.add(mapForInterval_2_2);
			
			for(Map.Entry<String, String[]> map : onShoreGsciFTESummary.entrySet()){
				
				Map<String,String[]> temp = new HashMap<>();
				temp.put(map.getKey(), map.getValue());
				fteSummaryReportList.add(temp);
			}
			
			Map<String,String[]> mapForGap_2_3 = new HashMap<>();
			mapForGap_2_3.put("LINE", headerRow);
			fteSummaryReportList.add(mapForGap_2_3);
			
			
			
			Map<String,String[]> mapForOnshoreFTE_3 = new HashMap<>();
			mapForOnshoreFTE_3.put("Onshore (3PP) FTESummary", headerRow);
			fteSummaryReportList.add(mapForOnshoreFTE_3);
			
			Map<String,String[]> mapForInterval_2_3 = new HashMap<>();
			mapForInterval_2_3.put("Service Scopes", intervals);
			fteSummaryReportList.add(mapForInterval_2_3);
			
			for(Map.Entry<String, String[]> map : onShore3PPFTESummary.entrySet()){
				
				Map<String,String[]> temp = new HashMap<>();
				temp.put(map.getKey(), map.getValue());
				fteSummaryReportList.add(temp);
			}
			
			Map<String,String[]> mapForGap_2_4 = new HashMap<>();
			mapForGap_2_4.put("LINE", headerRow);
			fteSummaryReportList.add(mapForGap_2_4);
			
			
			
			Map<String,String[]> mapForNearshoreFTE = new HashMap<>();
			mapForNearshoreFTE.put("Nearshore (GSC) FTESummary", headerRow);
			fteSummaryReportList.add(mapForNearshoreFTE);
			
			Map<String,String[]> mapForInterval_3 = new HashMap<>();
			mapForInterval_3.put("Service Scopes", intervals);
			fteSummaryReportList.add(mapForInterval_3);
			
			for(Map.Entry<String, String[]> map : nearShoreFTESummary.entrySet()){
				
				Map<String,String[]> temp = new HashMap<>();
				temp.put(map.getKey(), map.getValue());
				fteSummaryReportList.add(temp);
			}
			
					
			model.addAttribute("fteSummaryReportList",fteSummaryReportList);
			model.addAttribute("serviceScopes",serviceScopes);
			model.addAttribute("intervals",intervals);
			model.addAttribute("offShoreFTESummary",offShoreFTESummary);
			model.addAttribute("onShore3PPFTESummary",onShore3PPFTESummary);
			model.addAttribute("onShoreLocalFTESummary",onShoreLocalFTESummary);
			model.addAttribute("onShoreGsciFTESummary",onShoreGsciFTESummary);
			model.addAttribute("nearShoreFTESummary",nearShoreFTESummary);
			model.addAttribute("totalFTESummary",totalFTESummary);
			model.addAttribute("calanderYear", calanderYear);
			model.addAttribute("currencyCode", currencyCode);
			
			}else{
				model.addAttribute("intervals",intervals);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "reportFTESummary";
	}
	
	private String[] yearWiseLaborCostData(Map<String,String[]> dataMap){
		String [] data = null;
		List<String[]> list = new ArrayList<>();
		
		Integer size = 0;
		
		for(String[] temp : dataMap.values()){
			size = temp.length;
			break;
		}
		
		for(String[] temp : dataMap.values()){
			list.add(temp);
		}
		
		data = new String[size];
		
		for(int i=0;i<list.size();i++){
			
			String [] temp = list.get(i);
			
			for(int j=0,k=0;j< temp.length;j++,k++){
				if(i == 0){
					int index = temp[k].indexOf('.');
					if(index > 0){
						temp[k] = temp[k].substring(0, index);
					}
					data[j] = String.valueOf(Integer.parseInt(temp[k]));
				}else{
					int index = temp[k].indexOf('.');
					if(index > 0){
						temp[k] = temp[k].substring(0, index);
					}
					data[j] = String.valueOf(Integer.parseInt(data[j]) + Integer.parseInt(temp[k]));
				}
			}
		}
		return data;
	}
	
	@Autowired
	IApprovalService approvalService;
	
	@RequestMapping(value="/report/reportCostSummary")
	public String reportCostSummary(HttpSession session, ModelMap model){
		
		Integer solutionID = getSessionSolutionId(session);
		Integer opportunityId = getSessionOpportunityId(session);
		DecimalFormat f = new DecimalFormat("##.00");
		Map<String, String[]> offshoreData = null;
		Map<String, String[]> onshoreLocalData = null;
		Map<String, String[]> onshore3PPData = null;
		Map<String, String[]> onshoreGsciData = null;
		Map<String, String[]> totalLaborCostData = null;
		
		try {
				String[] intervals =  reportService.getMonthYear(opportunityId, calanderYear);
				if((null != intervals)){
				for(int i=0;i<intervals.length;i++){
					System.out.println("interval returned for["+calanderYear+"] is["+intervals[i]+"]");
				}
				Map<String,Map<String,String[]>> laborCostSummaryData = new HashMap<String, Map<String,String[]>>();
				
				if(calanderYear){
					laborCostSummaryData = reportService.getCostSummaryData(solutionID, opportunityId, calanderYear);
				}else if(!calanderYear){
					laborCostSummaryData = reportService.getCostSummaryData(solutionID, opportunityId, calanderYear);			
				}
				
				
				offshoreData = laborCostSummaryData.get("offshore");
				onshoreLocalData = laborCostSummaryData.get("onshoreLocal");
				onshore3PPData = laborCostSummaryData.get("onshore3PP");
				onshoreGsciData = laborCostSummaryData.get("onshoreGsci");
				totalLaborCostData = laborCostSummaryData.get("totalLaborCost");
				Map<String, String[]> graphicsDisplay = laborCostSummaryData.get("graphicsDisplay");
				
				if(null == graphicsDisplay){
					model.addAttribute("graphicsDisplay","null");
				}else{
					model.addAttribute("graphicsDisplay","filled");
				}
				
				ExecSummary execSummary = approvalService.retrieveExeSummaryReport(solutionID);
				
				List<Row> travelCostRowList = execSummary.getTravelCost();
				List<Row> otherCostRowList = execSummary.getOtherCost();
				List<Float> totalNLCRowList = execSummary.getTotalNLC();
				
				String[] travelCost = new String[intervals.length];
				String[] otherCost = new String[intervals.length];
				String[] totalNlcCost = new String[intervals.length];
				
				
				
				laborCostReportData = null;
				onshoreLocalReportData = null;
				onshore3PPReportData = null;
				onshoreGsciReportData = null;
				offshoreReportData = null;
				nlcReportData = null;
				nlcReportData = new String[intervals.length];
				
				laborCostReportData = totalLaborCostData;
				onshoreLocalReportData = onshoreLocalData;
				onshore3PPReportData = onshore3PPData;
				onshoreGsciReportData = onshoreGsciData;
				offshoreReportData = offshoreData;
				
				String currencyCode = reportService.getCurrencyCode(opportunityId);
				
				String[] headerRow = new String[intervals.length];
				String[] headerRowWithCurrency = new String[intervals.length];
				List<Map<String,String[]>> costSummaryReportList = new ArrayList<>();
				
				headerRowWithCurrency[0] = "currency in [ "+currencyCode+" ]";
				
				Map<String,String[]> mapForHeaderRow = new HashMap<>();
				mapForHeaderRow.put("Total Labor Cost Summary", headerRowWithCurrency);
				costSummaryReportList.add(mapForHeaderRow);
				
				Map<String,String[]> mapForInterval_1 = new HashMap<>();
				mapForInterval_1.put("Service Scopes", intervals);
				costSummaryReportList.add(mapForInterval_1);
				
				for(Map.Entry<String, String[]> map : totalLaborCostData.entrySet()){
					
					Map<String,String[]> temp = new HashMap<>();
					temp.put(map.getKey(), map.getValue());
					costSummaryReportList.add(temp);
				}
				
				Map<String,String[]> mapForGap_1 = new HashMap<>();
				mapForGap_1.put("LINE", headerRow);
				costSummaryReportList.add(mapForGap_1);
				
				Map<String,String[]> mapForHeaderRow_NLC = new HashMap<>();
				mapForHeaderRow_NLC.put("Total Non Labor Cost Summary", headerRowWithCurrency);
				costSummaryReportList.add(mapForHeaderRow_NLC);
				
				
				Map<String,String[]> mapForNLC = new HashMap<>();
				
				if(totalNLCRowList.size() >0){
					for(int i=0;i<totalNlcCost.length;i++){
						totalNlcCost[i] = ((totalNLCRowList.get(i).toString() != "") && (null != totalNLCRowList.get(i))) ? ((new DecimalFormat("#.##")).format(totalNLCRowList.get(i))).toString() : "0.0";
					}
				}else{
					for(int k=0;k<totalNlcCost.length;k++){
						totalNlcCost[k] = "0";
					}
				}
				
				mapForNLC.put("Travel + Other Cost", totalNlcCost);
				costSummaryReportList.add(mapForNLC);
				nlcReportData = totalNlcCost;
				
				Map<String,String[]> mapForGap_1_1 = new HashMap<>();
				mapForGap_1_1.put("LINE", headerRow);
				costSummaryReportList.add(mapForGap_1_1);
				
				Map<String,String[]> mapForOffshoreData = new HashMap<>();
				mapForOffshoreData.put("Offshore (GSC)", headerRowWithCurrency);
				costSummaryReportList.add(mapForOffshoreData);
				
				Map<String,String[]> mapForInterval_2 = new HashMap<>();
				mapForInterval_2.put("Service Scopes", intervals);
				costSummaryReportList.add(mapForInterval_2);
								
				for(Map.Entry<String, String[]> map : offshoreData.entrySet()){
					
					Map<String,String[]> temp = new HashMap<>();
					temp.put(map.getKey(), map.getValue());
					costSummaryReportList.add(temp);
				}
				
				
				Map<String,String[]> mapForGap_2 = new HashMap<>();
				mapForGap_2.put("LINE", headerRow);
				costSummaryReportList.add(mapForGap_2);
				
				Map<String,String[]> mapForOnshoreLocalData = new HashMap<>();
				mapForOnshoreLocalData.put("Onshore (Local)", headerRowWithCurrency);
				costSummaryReportList.add(mapForOnshoreLocalData);
				
				Map<String,String[]> mapForInterval_3 = new HashMap<>();
				mapForInterval_3.put("Service Scopes", intervals);
				costSummaryReportList.add(mapForInterval_3);
				
				for(Map.Entry<String, String[]> map : onshoreLocalData.entrySet()){
					Map<String,String[]> temp = new HashMap<>();
					temp.put(map.getKey(), map.getValue());
					costSummaryReportList.add(temp);
				}
				
				
				Map<String,String[]> mapForGap_3 = new HashMap<>();
				mapForGap_3.put("LINE", headerRow);
				costSummaryReportList.add(mapForGap_3);
				
				Map<String,String[]> mapForOnshore3PPData = new HashMap<>();
				mapForOnshore3PPData.put("Onshore (3PP)", headerRowWithCurrency);
				costSummaryReportList.add(mapForOnshore3PPData);
				
				Map<String,String[]> mapForInterval_4 = new HashMap<>();
				mapForInterval_4.put("Service Scopes", intervals);
				costSummaryReportList.add(mapForInterval_4);
				
				for(Map.Entry<String, String[]> map : onshore3PPData.entrySet()){	
					Map<String,String[]> temp = new HashMap<>();
					temp.put(map.getKey(), map.getValue());
					costSummaryReportList.add(temp);
				}
				
				Map<String,String[]> mapForGap_4 = new HashMap<>();
				mapForGap_4.put("LINE", headerRow);
				costSummaryReportList.add(mapForGap_4);
				
				Map<String,String[]> mapForOnshoreGsciData = new HashMap<>();
				mapForOnshoreGsciData.put("Onshore (GSC Landed)", headerRowWithCurrency);
				costSummaryReportList.add(mapForOnshoreGsciData);
				
				Map<String,String[]> mapForInterval_5 = new HashMap<>();
				mapForInterval_5.put("Service Scopes", intervals);
				costSummaryReportList.add(mapForInterval_5);
				
				for(Map.Entry<String, String[]> map : onshoreGsciData.entrySet()){	
					Map<String,String[]> temp = new HashMap<>();
					temp.put(map.getKey(), map.getValue());
					costSummaryReportList.add(temp);
				}
				
				Map<String,String[]> mapForGap_1_2 = new HashMap<>();
				mapForGap_1_2.put("LINE", headerRow);
				costSummaryReportList.add(mapForGap_1_2);
				
				Map<String,String[]> mapForNlc = new HashMap<>();
				mapForNlc.put("Non Labor Cost", headerRowWithCurrency);
				costSummaryReportList.add(mapForNlc);
				
				Map<String,String[]> mapForNlcInterval = new HashMap<>();
				mapForNlcInterval.put("NLC Interval", intervals);
				costSummaryReportList.add(mapForNlcInterval);
				
				if((null != travelCostRowList) && (0 != travelCostRowList.size())){
					for(int i=0;i<travelCost.length;i++){
						if(travelCostRowList.get(i).getCostValue() <= 0){
							travelCost[i] = "0";
						}else{
							travelCost[i] = f.format(travelCostRowList.get(i).getCostValue());
						}
					}
				}
				Map<String,String[]> travelCostMap = new HashMap<>();
				travelCostMap.put("Travel Cost", travelCost);
				
				if((null != otherCostRowList) && (0 != otherCostRowList.size())){
				for(int i=0;i<otherCost.length;i++){
					if(otherCostRowList.get(i).getCostValue() <=0){
						otherCost[i] = "0";
					}else{
						otherCost[i] = f.format(otherCostRowList.get(i).getCostValue());
					}
				}
				}
				Map<String,String[]> otherCostMap = new HashMap<>();
				otherCostMap.put("Other Cost", otherCost);
				
				costSummaryReportList.add(travelCostMap);
				costSummaryReportList.add(otherCostMap);
				
				model.addAttribute("costSummaryReportList", costSummaryReportList);
				model.addAttribute("intervals",intervals);
				model.addAttribute("offShoreCostSummary",offshoreData);
				model.addAttribute("onShoreLocalCostSummary",onshoreLocalData);
				model.addAttribute("onShore3PPCostSummary",onshore3PPData);
				model.addAttribute("onShoreGsciCostSummary",onshoreGsciData);
				model.addAttribute("totalLaborCostData",totalLaborCostData);
				model.addAttribute("calanderYear", calanderYear);
				
				}else{
					model.addAttribute("intervals", intervals);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "reportCostSummary";
	}
	
	@RequestMapping(value="/report/laborCostDataYearWise")
	public String getLaborCostData(HttpSession session, ModelMap model,@RequestParam(value="type") String type){
	
		
			String [] laborCostYearwiseData = yearWiseLaborCostData(laborCostReportData);
			String [] nonLborCostData = nlcReportData;
			Integer numberOfYears = laborCostYearwiseData.length;
			Integer totalLaborCostValue = 0;
			Integer totalNonLaborCostValue = 0;
			String laborCostValues="";
			String nonLaborCostValues="";
			String []totalCost = new String[numberOfYears];
			
			String []serviceScopes = new String[laborCostReportData.size()]; 
			String [][]values = new String[laborCostReportData.size()][numberOfYears];
			int v=0;
			for(Map.Entry<String, String[]> map : laborCostReportData.entrySet()){
				Map<String,String[]> temp = new HashMap<>();
				serviceScopes[v] = map.getKey();
				values[v] = map.getValue();
				v++;
			}
			Float pt  = 0f;
			String []tp = new String[serviceScopes.length];
			for(int i =0;i<serviceScopes.length;i++){
				pt=0f;
				for(int j =0;j<numberOfYears;j++){
					pt += Float.parseFloat(values[i][j]);
				}
				tp[i] = String.valueOf(pt);
			}
			
			String serviceElementData = "[ ";
			
			for(int i=0;i<serviceScopes.length;i++){
				serviceElementData += "{ \"Service Element\":\""+serviceScopes[i]+"\""+",\"% Contribution\":"+tp[i]+"},";
			}
			serviceElementData = serviceElementData.substring(0, serviceElementData.length()-1);
			serviceElementData += " ]";
			
			
		
			for(int i=0;i<laborCostYearwiseData.length;i++){
				int index = laborCostYearwiseData[i].indexOf('.');
				if(index > 0){
					laborCostYearwiseData[i] = laborCostYearwiseData[i].substring(0, index);
				}
				totalLaborCostValue += Integer.parseInt(laborCostYearwiseData[i]);
				laborCostValues += laborCostYearwiseData[i]+",";
			}
			laborCostValues = laborCostValues.substring(0, (laborCostValues.length())-1);
			
			for(int i=0;i<nonLborCostData.length;i++){
				int index = nonLborCostData[i].indexOf('.');
				if(index > 0){
					nonLborCostData[i] = nonLborCostData[i].substring(0, index);
				}
				totalNonLaborCostValue += Integer.parseInt(nonLborCostData[i]);
				nonLaborCostValues += nonLborCostData[i]+",";
			}
			nonLaborCostValues = nonLaborCostValues.substring(0, (nonLaborCostValues.length())-1);
			
			
			for(int i=0;i<totalCost.length;i++){
				
				if(null !=laborCostYearwiseData[i]){
					if(null != nonLborCostData[i]){
						totalCost[i] = String.valueOf(BigDecimal.valueOf(Double.valueOf(laborCostYearwiseData[i]) + Double.valueOf(nonLborCostData[i])).toBigInteger());
					}else{
						totalCost[i] = String.valueOf(BigDecimal.valueOf(Double.valueOf(laborCostYearwiseData[i]) + 0.0d).toBigInteger());
					}
				}
				else{
					totalCost[i] = String.valueOf(0.0d + 0.0d);
				}
			}
			
			String totalCostData = "[ ";
			
			for(int i=0;i<numberOfYears;i++){
				totalCostData += "{ \"Total Cost Summary\":"+totalCost[i]+",\"Year\":\"Year "+(i+1)+"\" },";
			}
			totalCostData = totalCostData.substring(0, totalCostData.length()-1);
			totalCostData += " ]";
			
			
			String [] localReportData = yearWiseLaborCostData(onshoreLocalReportData);
			String [] pPPReportData = yearWiseLaborCostData(onshore3PPReportData);
			String [] gsciReportData = yearWiseLaborCostData(onshoreGsciReportData);
			String [] offReportData = yearWiseLaborCostData(offshoreReportData);
			
		
			String data1 = "[ ";
			for(int i=0;i<numberOfYears;i++){
				data1 += "{ \"Onshore (Local)\":"+localReportData[i]+",\" Onshore (3PP)\":"+pPPReportData[i]+",\"Onshore (GSC Landed)\":"+gsciReportData[i]+",\"Offshore (GSC)\":"+offReportData[i]+",\"Year\":\"Year "+(i+1)+"\" },";
			}
			data1 = data1.substring(0, data1.length()-1);
			data1 += " ]";
			
			System.out.println("labor cost data string : " + laborCostValues);
			System.out.println("non labor cost data string : " + nonLaborCostValues);
			System.out.println("detail string for labor cost : " + data1);
			System.out.println("service scope string : " + serviceElementData);
			System.out.println("total cost data string : " + totalCostData);
			
			model.addAttribute("data1", data1);
		
			model.addAttribute("years", numberOfYears);
			model.addAttribute("totalValue",totalLaborCostValue);
		
			model.addAttribute("totalCostData", totalCostData);
			model.addAttribute("serviceElementData", serviceElementData);
			model.addAttribute("laborDataString", laborCostValues);
			model.addAttribute("nonLaborDataString", nonLaborCostValues);
			
		
		return "costSummaryReportChart";
	}
	
/*	@RequestMapping(value="/report/viewRiskReport")
	public String riskReport(HttpSession session, ModelMap model){
		
		logger.info("inside view Risk Report");
		Integer solutionID = getSessionSolutionId(session);
		List<SolutionADRDTO> solADRDtoList = reportService.getADRReportDetails(solutionID, "RISK");
		if(solADRDtoList != null)
			System.out.println("SolutionADRList :"+solADRDtoList.size());
		try{
		logger.info("***************************************************");
		
			model.addAttribute("solADRList",solADRDtoList);
		}
		catch(Exception me){
			me.printStackTrace();
		}
		return "riskReport";
	}*/
	
	
	/*
	 * Consolidated controller for A-D-R report , previously 3 separate reports
	 */
	
	
	@RequestMapping(value="/report/viewRiskReport")
	public String riskReport(HttpSession session, ModelMap model){
		
		logger.info("inside view A-D-R consolidated Report");
		Integer solutionID = getSessionSolutionId(session);
		Integer opportunityId = getSessionOpportunityId(session);
		
		String displayFlag ="";
		Integer sumOfSize=0;
		//For Risk Report
		
		String currency =/* iSolutionService.getOpportunity(opportunityId).getCustomerDTO().getCountryDTO().getCurrencyName()+*/"["+iSolutionService.getOpportunity(opportunityId).getCustomerDTO().getCountryDTO().getCurrencyCode()+"]";
		
		List<SolutionADRDTO> solADRDtoList_risk = reportService.getADRReportDetails(solutionID, "RISK");
		if(solADRDtoList_risk != null)
			System.out.println("SolutionADRList RISK :"+solADRDtoList_risk.size());

		
		//For Assumption Report
		List<SolutionADRDTO> solADRDtoList_asumption = reportService.getADRReportDetails(solutionID, "ASSUMPTION");
		if(solADRDtoList_asumption != null)
			System.out.println("SolutionADRList ASSUMPTION :"+solADRDtoList_asumption.size());
	
		//For Dependency Report
			List<SolutionADRDTO> solADRDtoList_dependency = reportService.getADRReportDetails(solutionID,"DEPENDENCY");
		if(solADRDtoList_dependency != null)
			System.out.println("SolutionADRList DEPENDENCY :"+solADRDtoList_dependency.size());
		
		
		//Now it is A-D-R reports 
		List<SolutionADRDTO> finalList = new ArrayList<SolutionADRDTO>();
		
		SolutionADRDTO tempDto_0 = new SolutionADRDTO();
		//starting of DEPENDENCY list
		tempDto_0.setAdrStatement("Assumption Report");
		tempDto_0.setAdrType(" ");
				finalList.add(tempDto_0);

		
		for(SolutionADRDTO adrdto : solADRDtoList_asumption){
			finalList.add(adrdto);
		}
		SolutionADRDTO tempDto = new SolutionADRDTO();
		
		//adding line2
		SolutionADRDTO line2 = new SolutionADRDTO();
		line2.setAdrStatement(" ");
		finalList.add(line2);
		
		//starting of DEPENDENCY list
		tempDto.setAdrStatement("Dependency Report");
		tempDto.setAdrType(" ");
		finalList.add(tempDto);

		
		for(SolutionADRDTO adrdto : solADRDtoList_dependency){
			finalList.add(adrdto);
		}
		SolutionADRDTO tempDto1 = new SolutionADRDTO();
		
		//adding line3
		SolutionADRDTO line3 = new SolutionADRDTO();
		line3.setAdrStatement(" ");
		finalList.add(line3);
		
		//starting of RISK list
		tempDto1.setAdrType(" ");
		tempDto1.setAdrStatement("Risk Report");
		finalList.add(tempDto1);
		
	
		
		for(SolutionADRDTO adrdto : solADRDtoList_risk){
			finalList.add(adrdto);
		}
		sumOfSize= solADRDtoList_risk.size()+solADRDtoList_asumption.size()+solADRDtoList_dependency.size();
		if(sumOfSize.equals(0)){
			displayFlag="NO";
		}
		
		model.addAttribute("displayFlag",displayFlag);
		model.addAttribute("finalList",finalList);
		model.addAttribute("currency",currency);
			return "riskReport";
	}
	
	@RequestMapping(value="/report/viewAssumptionReport")
	public String assumptionReport(HttpSession session, ModelMap model){
		
		logger.info("inside view Assumption Report");
		Integer solutionID = getSessionSolutionId(session);
		List<SolutionADRDTO> solADRDtoList = reportService.getADRReportDetails(solutionID, "ASSUMPTION");
		if(solADRDtoList != null)
			System.out.println("SolutionADRList :"+solADRDtoList.size());
		try{
		logger.info("***************************************************");
		
			model.addAttribute("solADRList",solADRDtoList);
		}
		catch(Exception me){
			me.printStackTrace();
		}
		return "assumptionReport";
	}
	
	@RequestMapping(value="/report/viewDependencyReport")
	public String dependencyReport(HttpSession session, ModelMap model){
		
		logger.info("inside view Dependency Report");
		Integer solutionID = getSessionSolutionId(session);
		List<SolutionADRDTO> solADRDtoList = reportService.getADRReportDetails(solutionID,"DEPENDENCY");
		if(solADRDtoList != null)
			System.out.println("SolutionADRList :"+solADRDtoList.size());
		try{
		logger.info("***************************************************");
		
			model.addAttribute("solADRList",solADRDtoList);
		}
		catch(Exception me){
			me.printStackTrace();
		}
		return "dependencyReport";
	}
	
	@RequestMapping(value="/report/timelineType")
	public String tempFunc(@RequestParam(value="intervalType") String fieldName){
		System.out.println("interval type[" + fieldName.split("-")[0]+"] req from ["+fieldName.split("-")[1]+"]");
		
		String retString = "";
		
		if("calYear".equalsIgnoreCase(fieldName.split("-")[0]))
			calanderYear = true;
		else if("conYear".equalsIgnoreCase(fieldName.split("-")[0]))
			calanderYear = false;
		
		if("rcs".equalsIgnoreCase(fieldName.split("-")[1])){
			retString = "redirect:/report/reportCostSummary";
		}else if("frs".equalsIgnoreCase(fieldName.split("-")[1])){
			retString = "redirect:/report/reportFTESummary";
		}
		
		return retString;
	}
}
