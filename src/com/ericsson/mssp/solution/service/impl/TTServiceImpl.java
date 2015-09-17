/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.service.impl
* File name       		    :  ITTServiceImpl.java
* Description				:	<To Do>
* Author, Date & Release	:	May 8, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| May 8, 2013  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.dto.JobRoleDTO;
import com.ericsson.mssp.common.dto.TTJobRoleDistributionDTO;
import com.ericsson.mssp.common.dto.TTOnOffRatioDTO;
import com.ericsson.mssp.common.dto.TTPartitionDetailDTO;
import com.ericsson.mssp.common.dto.TTPartitionNameDTO;
import com.ericsson.mssp.common.dto.TTPlanningDTO;
import com.ericsson.mssp.common.dto.TTSummaryStagingDTO;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.TTJobRoleDistribution;
import com.ericsson.mssp.common.entity.TTOnOffRatio;
import com.ericsson.mssp.common.entity.TTPartitionDetail;
import com.ericsson.mssp.common.entity.TTPartitionName;
import com.ericsson.mssp.common.entity.TTPlanning;
import com.ericsson.mssp.common.entity.TTSummaryStaging;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.dao.ITTDAO;
import com.ericsson.mssp.solution.forms.TTDetailForm;
import com.ericsson.mssp.solution.forms.TTJobRoleDistributionForm;
import com.ericsson.mssp.solution.forms.TTLaborCostForm;
import com.ericsson.mssp.solution.service.ISolutionService;
import com.ericsson.mssp.solution.service.ITTService;

/**
 * @author egaivij
 *
 */
@Service
public class TTServiceImpl implements ITTService {

	@Autowired
	private ITTDAO ttDAO;
	
	@Autowired
	ISolutionService solutionService;

	private static final Logger logger = Logger
			.getLogger(TTServiceImpl.class);
	
	@Override
	public void savePartitionNames(TTDetailForm ttDetailForm, Integer solutionId)
			throws MSSPException {
		
		TTPlanningDTO planningDTO = ttDetailForm.getPlanningDTO();
		
		List<TTPartitionNameDTO> partitionList = ttDetailForm.getPartitionNameList();
		
		Solution solution = new Solution();
		solution.setSolutionId(solutionId);
		
		if(ttDetailForm.getDelPlanningId() != null){
			
			ttDAO.deleteJobRoleDistributionByPlanningId(ttDetailForm.getDelPlanningId());
		
			ttDAO.deletePlanningByPlanningId(ttDetailForm.getDelPlanningId());
		}
		
		TTPlanning ttPlanning = new TTPlanning();
		BeanUtils.copyProperties(planningDTO, ttPlanning);
		
		ttPlanning.setSolution(solution);
		
		TTPartitionName partitionName = null;
		Set<TTPartitionName>  ttpartitionNames= new HashSet<TTPartitionName>();
		for(TTPartitionNameDTO partitionNameDTO:partitionList ){
			partitionName = new TTPartitionName();
			BeanUtils.copyProperties(partitionNameDTO, partitionName);
			ttpartitionNames.add(partitionName);
		}
		
		ttPlanning.setTtpartitionNames(ttpartitionNames);
		
		ttDAO.savePlanning(ttPlanning);
		
		
	}

	@Override
	public void loadPartitionNames(Integer solutionId, TTDetailForm ttDetailForm)
			throws MSSPException {
		// TODO Auto-generated method stub
		TTPlanningDTO planningDTO = new TTPlanningDTO();
		TTPlanning ttPlanning = ttDAO.loadPartitionNames(solutionId);
		List<TTPartitionNameDTO> partitionNameList = new ArrayList<TTPartitionNameDTO>();
		if (ttPlanning != null) {
			BeanUtils.copyProperties(ttPlanning, planningDTO);

			Set<TTPartitionName> partitionNames = ttPlanning
					.getTtpartitionNames();

			for (TTPartitionName partitionName : partitionNames) {
				TTPartitionNameDTO partitionNameDTO = new TTPartitionNameDTO();
				BeanUtils.copyProperties(partitionName, partitionNameDTO);
				partitionNameList.add(partitionNameDTO);
			}
		}
		ttDetailForm.setPartitionNameList(partitionNameList);
		ttDetailForm.setPlanningDTO(planningDTO);

	}

	@Override
	public void saveFTEBreakup(TTDetailForm ttDetailForm, Integer selPartitionId)
			throws MSSPException {
		// TODO Auto-generated method stub

		TTPartitionDetailDTO partitionDetailDTO = ttDetailForm
				.getPartitionDetailDTO();
		TTOnOffRatioDTO ttOnOffRatioDTO = ttDetailForm.getTtOnOffRatioDTO();

		TTPartitionName ttpartitionName = new TTPartitionName();
		ttpartitionName.setTtpartitionNameId(selPartitionId);

		TTPartitionDetail partitionDetail = new TTPartitionDetail();
		BeanUtils.copyProperties(partitionDetailDTO, partitionDetail);
		partitionDetail.setTtpartitionName(ttpartitionName);

		TTOnOffRatio ttOnOffRatio = new TTOnOffRatio();
		BeanUtils.copyProperties(ttOnOffRatioDTO, ttOnOffRatio);
		ttOnOffRatio.setTtpartitionName(ttpartitionName);

		ttDAO.savePartitionDetail(partitionDetail, ttOnOffRatio);

	
		//deleting records from staging
		ttDAO.deleteSummaryStagingByPartitionId(selPartitionId);
		
		// insert data in summary staging table
		List<Object> summaryStagingList = setSummaryStagingList(
				partitionDetailDTO, ttOnOffRatioDTO, ttpartitionName);
		
		ttDAO.saveSummaryStagingList(summaryStagingList);

	}
	
	private List<Object> setSummaryStagingList(
			TTPartitionDetailDTO partitionDetailDTO,
			TTOnOffRatioDTO ttOnOffRatioDTO, TTPartitionName ttpartitionName) {

		List<Object> summaryStagingList = new ArrayList<Object>();
		Calendar c = Calendar.getInstance();

		
		int totalWeeks = getTotalWeeks(partitionDetailDTO.getPlanStartDate(),partitionDetailDTO.getPlanEndDate());
		c.setTime(partitionDetailDTO.getPlanStartDate());

		for (int i = 0; i < totalWeeks; i++) {
			TTSummaryStaging ttSummaryStaging = setSummaryStaging(ttpartitionName, c.getTime(),
					ttOnOffRatioDTO.getPlanOffFte(), ttOnOffRatioDTO.getPlanOnFte());
			c.add(Calendar.DATE, 7);
			summaryStagingList.add(ttSummaryStaging);

		}
		
		totalWeeks = getTotalWeeks(partitionDetailDTO.getLearnStartDate(),partitionDetailDTO.getLearnEndDate());
		c.setTime(partitionDetailDTO.getLearnStartDate());

		for (int i = 0; i < totalWeeks; i++) {
			TTSummaryStaging ttSummaryStaging = setSummaryStaging(ttpartitionName, c.getTime(),
					ttOnOffRatioDTO.getLearnOffFte(), ttOnOffRatioDTO.getLearnOnFte());
			c.add(Calendar.DATE, 7);
			summaryStagingList.add(ttSummaryStaging);

		}
		
		totalWeeks = getTotalWeeks(partitionDetailDTO.getAssistStartDate(),partitionDetailDTO.getAssistEndDate());
		c.setTime(partitionDetailDTO.getAssistStartDate());

		for (int i = 0; i < totalWeeks; i++) {
			TTSummaryStaging ttSummaryStaging = setSummaryStaging(ttpartitionName, c.getTime(),
					ttOnOffRatioDTO.getAssistOffFte(), ttOnOffRatioDTO.getAssistOnFte());
			c.add(Calendar.DATE, 7);
			summaryStagingList.add(ttSummaryStaging);

		}
		
		totalWeeks = getTotalWeeks(partitionDetailDTO.getPerformStartDate(),partitionDetailDTO.getPerformEndDate());
		c.setTime(partitionDetailDTO.getPerformStartDate());

		for (int i = 0; i < totalWeeks; i++) {
			TTSummaryStaging ttSummaryStaging = setSummaryStaging(ttpartitionName, c.getTime(),
					ttOnOffRatioDTO.getPerformOffFte(), ttOnOffRatioDTO.getPerformOnFte());
			c.add(Calendar.DATE, 7);
			summaryStagingList.add(ttSummaryStaging);

		}
		
		totalWeeks = getTotalWeeks(partitionDetailDTO.getDeliverStartDate(),partitionDetailDTO.getDeliverEndDate());
		c.setTime(partitionDetailDTO.getDeliverStartDate());

		for (int i = 0; i < totalWeeks; i++) {
			TTSummaryStaging ttSummaryStaging = setSummaryStaging(ttpartitionName, c.getTime(),
					ttOnOffRatioDTO.getDeliverOffFte(), ttOnOffRatioDTO.getDeliverOnFte());
			c.add(Calendar.DATE, 7);
			summaryStagingList.add(ttSummaryStaging);

		}
		
		return summaryStagingList;
	}
	
	private int getTotalWeeks(Date startDate, Date endDate) {
		int totalDays = Math.round((endDate.getTime() - startDate.getTime())
				/ (24 * 60 * 60 * 1000));
		int totalWeeks = (totalDays+3) / 7;
		return totalWeeks;
	}


	private TTSummaryStaging setSummaryStaging(TTPartitionName ttpartitionName,
			Date time, Float planOffFte, Float planOnFte) {

		TTSummaryStaging ttSummaryStaging = new TTSummaryStaging();
		ttSummaryStaging.setTtpartitionName(ttpartitionName);
		ttSummaryStaging.setWeekDate(time);
		ttSummaryStaging.setOffshoreFte(planOffFte);
		ttSummaryStaging.setOnshoreFte(planOnFte);

		return ttSummaryStaging;
	}
	
	@Override
	public List<TTPartitionNameDTO> getPartitionNames(Integer solutionID) throws MSSPException {
		
		TTPartitionNameDTO ttPartitionNameDTO;
		List<TTPartitionNameDTO> ttPartitionNameDTOs = new ArrayList<TTPartitionNameDTO>();
		List<TTPartitionName> ttPartitionNames = ttDAO.getPartitionNames(solutionID);
		TTPlanningDTO ttPlanningDTO;
		
		for (TTPartitionName ttPartitionName : ttPartitionNames) {
			
			ttPartitionNameDTO = new TTPartitionNameDTO();
			ttPlanningDTO = new TTPlanningDTO();
			
			ttPlanningDTO.setTtplanningId(ttPartitionName.getTtplanning().getTtplanningId());
			
			ttPartitionNameDTO.setTtPlanningDTO(ttPlanningDTO);
			ttPartitionNameDTO.setTtpartitionFte(ttPartitionName.getTtpartitionFte());
			ttPartitionNameDTO.setTtpartitionName(ttPartitionName.getTtpartitionName());
			ttPartitionNameDTO.setTtpartitionNameId(ttPartitionName.getTtpartitionNameId());
			
			ttPartitionNameDTOs.add(ttPartitionNameDTO);
		}
		
		return ttPartitionNameDTOs;
	}

	@Override
	public Map<String,Object> getTimeInterval(Integer solutionID, Date transformationStartDate, Date transformationEndDate) throws MSSPException {
		
		int totalDays,totalWeeks = 0;
		String startingWeek = null;
		List<String []>timeInterval = null;
		TTPartitionDetail ttPartitionDetail;
		Calendar c = Calendar.getInstance();
		String []partitionsTimeIntervalArray = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		Map<String,String> numberOfColoredTds =null;
		List<Map<String,String>> timeLineColorCode = new ArrayList<Map<String,String>>();
		//Map<String[],List<Map<String,Integer>>> finalMap = new HashMap<String[], List<Map<String,Integer>>>();
		Date planStartDate,planEndDate,learnStartDate,learnEndDate,assistStartDate,assistEndDate,performStartDate,performEndDate,deliverStartDate,deliverEndDate = null;
		Map<String,Object> finalMap =null;
		logger.info("transformation start date  : " + transformationStartDate.getTime());
		logger.info("transformation end date  : " + transformationEndDate.getTime());
		
		totalDays = Math.round((transformationEndDate.getTime() - transformationStartDate.getTime()) / (24 * 60 * 60 * 1000));
		logger.info("total days : " + totalDays);
		if((totalDays % 7) > 0 ){
			totalWeeks = totalDays/7 + 1;
		}
		else{
			totalWeeks = totalDays/7;
		}
		logger.info("total weeks : " + totalWeeks);
		partitionsTimeIntervalArray = new String[totalWeeks];
		c.setTime(transformationStartDate);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		partitionsTimeIntervalArray[0] = sdf.format(c.getTime());
		
		for(int i =1; i<totalWeeks;i++){
			c.add(Calendar.DATE,7);
			partitionsTimeIntervalArray[i] = sdf.format(c.getTime());
		}
		
		List<TTPartitionName> ttPartitionNames = ttDAO.getPartitionNames(solutionID);
		
		for (TTPartitionName ttPartitionName : ttPartitionNames) {
			ttPartitionDetail = ttDAO.getTtPartitionDetails(ttPartitionName.getTtpartitionNameId());
			if(ttPartitionDetail == null){
				return finalMap;
			}
			 numberOfColoredTds = new HashMap<String, String>();
			totalWeeks = 0;
			totalDays = 0;
			
			/* Planning partition */
			startingWeek = "";
			planStartDate = ttPartitionDetail.getPlanStartDate();
			planEndDate = ttPartitionDetail.getPlanEndDate();
			
			totalDays = Math.round((planEndDate.getTime() - planStartDate.getTime()) / (24 * 60 * 60 * 1000));
			
			if(totalDays % 7 > 0){
				totalWeeks = totalDays/7 + 1;
			}
			else{
				totalWeeks = totalDays/7;
			}
			
			c.setTime(planStartDate);
			startingWeek = sdf.format(c.getTime());
			
			logger.info("planning start date : " + planStartDate + " | planning end date : " +planEndDate+ " |total days : " + totalDays + " | total weeks : " + totalWeeks);
			
			numberOfColoredTds.put("planningPartition", totalWeeks+"|"+startingWeek);
			totalWeeks = 0;
			totalDays = 0;
			
			/* Learning partition */
			startingWeek = null;
			learnStartDate = ttPartitionDetail.getLearnStartDate();
			learnEndDate = ttPartitionDetail.getLearnEndDate();
			
			totalDays = Math.round((learnEndDate.getTime() - learnStartDate.getTime()) / (24 * 60 * 60 * 1000));
			if(totalDays % 7 > 0){
				totalWeeks = totalDays/7 + 1;
			}
			else{
				totalWeeks = totalDays/7;
			}
			
			c.setTime(learnStartDate);
			startingWeek = sdf.format(c.getTime());
			
			logger.info("learning start date : " + learnStartDate + " | learning end date : " +learnEndDate+ " | total days : " + totalDays + " | total weeks : " + totalWeeks);
			
			numberOfColoredTds.put("learningPartition", totalWeeks+"|"+startingWeek);
			totalWeeks = 0;
			totalDays = 0;
			
			/* Assist partition */
			startingWeek = null;
			assistStartDate = ttPartitionDetail.getAssistStartDate();
			assistEndDate = ttPartitionDetail.getAssistEndDate();
			
			totalDays = Math.round((assistEndDate.getTime() - assistStartDate.getTime()) / (24 * 60 * 60 * 1000));
			if(totalDays % 7 > 0){
				totalWeeks = totalDays/7 + 1;
			}
			else{
				totalWeeks = totalDays/7;
			}
			
			c.setTime(assistStartDate);
			startingWeek = sdf.format(c.getTime());
			
			logger.info("assist start date : " + assistStartDate + " | assist end date : " +assistEndDate+ " | total days : " + totalDays + " | total weeks : " + totalWeeks);
			
			numberOfColoredTds.put("assistPartition", totalWeeks+"|"+startingWeek);
			totalWeeks = 0;
			totalDays = 0;
			
			/* Perform partition */
			startingWeek = null;
			performStartDate = ttPartitionDetail.getPerformStartDate();
			performEndDate = ttPartitionDetail.getPerformEndDate();
			
			totalDays = Math.round((performEndDate.getTime() - performStartDate.getTime()) / (24 * 60 * 60 * 1000));
			if(totalDays % 7 > 0){
				totalWeeks = totalDays/7 + 1;
			}
			else{
				totalWeeks = totalDays/7;
			}
			
			c.setTime(performStartDate);
			startingWeek = sdf.format(c.getTime());
			
			logger.info("perform start date : " + performStartDate + " | perform end date : " +performEndDate+ " | total days : " + totalDays + " | total weeks : " + totalWeeks);
			
			numberOfColoredTds.put("performPartition", totalWeeks+"|"+startingWeek);
			totalWeeks = 0;
			totalDays = 0;
			
			/* Deliver partition */
			startingWeek = null;
			deliverStartDate = ttPartitionDetail.getDeliverStartDate();
			deliverEndDate = ttPartitionDetail.getDeliverEndDate();
			
			totalDays = Math.round((deliverEndDate.getTime() - deliverStartDate.getTime()) / (24 * 60 * 60 * 1000));
			if(totalDays % 7 > 0){
				totalWeeks = totalDays/7 + 1;
			}
			else{
				totalWeeks = totalDays/7;
			}
			
			c.setTime(deliverStartDate);
			startingWeek = sdf.format(c.getTime());
			
			logger.info("deliver start date : " + deliverStartDate + " | deliver end date : " +deliverEndDate+ " | total days : " + totalDays + " | total weeks : " + totalWeeks);
			
			numberOfColoredTds.put("deliverPartition", totalWeeks+"|"+startingWeek);
			logger.info("--------NEXT------------");
			timeLineColorCode.add(numberOfColoredTds);
		}
		finalMap = new HashMap<String, Object>();
		
		finalMap.put("dateArray", partitionsTimeIntervalArray);
		finalMap.put("colorCode", timeLineColorCode);
		
		//finalMap.put(partitionsTimeIntervalArray, timeLineColorCode);
		
		return finalMap;
	}

	@Override
	public List<TTJobRoleDistributionDTO> getSelectedJobRoleDistributionList(
			Integer solutionID) throws MSSPException {
		
		List<TTJobRoleDistribution> jobRoleDistributions = new ArrayList<TTJobRoleDistribution>();
		List<TTJobRoleDistributionDTO> jobRoleDistributionDTOs = new ArrayList<TTJobRoleDistributionDTO>();
		jobRoleDistributions = ttDAO.getJobRoleDistributions(solutionID);
		TTJobRoleDistributionDTO ttJobRoleDistributionDTO;
		JobRoleDTO jobRoleDTO;
		JobRole jobRole;
		
		for (TTJobRoleDistribution ttJobRoleDistribution : jobRoleDistributions) {
			ttJobRoleDistributionDTO = new TTJobRoleDistributionDTO();
			jobRoleDTO = new JobRoleDTO();
			
			jobRole = ttJobRoleDistribution.getJobRole();
		
			BeanUtils.copyProperties(jobRole, jobRoleDTO);
			ttJobRoleDistributionDTO.setJobRoleDTO(jobRoleDTO);
			BeanUtils.copyProperties(ttJobRoleDistribution, ttJobRoleDistributionDTO);
			jobRoleDistributionDTOs.add(ttJobRoleDistributionDTO);
				}
		
		return jobRoleDistributionDTOs;
	}

	@Override
	public List<TTSummaryStagingDTO> getTtSummaryStagingData(String partitionNameIds) throws MSSPException {
		List<TTSummaryStagingDTO> ttSummaryDTOs = new ArrayList<TTSummaryStagingDTO>();
		List<TTSummaryStaging> onOffRatios = new ArrayList<TTSummaryStaging>();
		TTSummaryStagingDTO ttSummaryStagingDTO;
		
		onOffRatios = ttDAO.getTtSummaryStagingData(partitionNameIds);
		
		for (TTSummaryStaging ttSummaryStaging : onOffRatios) {
			ttSummaryStagingDTO = new TTSummaryStagingDTO();
		
			BeanUtils.copyProperties(ttSummaryStaging,ttSummaryStagingDTO);
			ttSummaryDTOs.add(ttSummaryStagingDTO);
		}
		
		return ttSummaryDTOs;
	}

	
	@Override
	public TTPartitionDetailDTO getPartitionDetailById(Integer selPartitionId)
			throws MSSPException {

		TTPartitionDetailDTO ttPartitionDetailDTO = new TTPartitionDetailDTO();
		TTPartitionDetail ttPartitionDetail = ttDAO
				.getTtPartitionDetails(selPartitionId);
		if (ttPartitionDetail != null) {
			BeanUtils.copyProperties(ttPartitionDetail, ttPartitionDetailDTO);
		}
		return ttPartitionDetailDTO;

	}

	@Override
	public TTOnOffRatioDTO getOnOffRatioByPartitionId(Integer selPartitionId)
			throws MSSPException {

		TTOnOffRatioDTO ttOnOffRatioDTO = new TTOnOffRatioDTO();
		TTOnOffRatio ttOnOffRatio = ttDAO
				.getOnOffRatioByPartitionId(selPartitionId);

		if (ttOnOffRatio != null) {
			BeanUtils.copyProperties(ttOnOffRatio, ttOnOffRatioDTO);
		}
		return ttOnOffRatioDTO;
	}

	@Override
	public void saveJobroleDistribution(
			TTJobRoleDistributionForm distributionForm)throws MSSPException {
			
		List <TTJobRoleDistributionDTO> jobroleDistDTOList = distributionForm.getTtDistributionList();
		Integer ttPlanningId = distributionForm.getTtPlanningId();
		
		//delete jobroleDistDTOList by plaaningId
		
		ttDAO.deleteJobRoleDistributionByPlanningId(ttPlanningId);
		
		TTPlanning ttPlanning = new TTPlanning();
		ttPlanning.setTtplanningId(ttPlanningId);
		JobRoleDTO jobRoleDTO = null;
		TTJobRoleDistribution ttJobRoleDistribution = null;
		JobRole jobRole = null;
		List <Object> jobroleDistList = new ArrayList<Object>();
		for(TTJobRoleDistributionDTO distDTO : jobroleDistDTOList){
			
			 if(distDTO.getDistributionPc() != null){
				 ttJobRoleDistribution = new TTJobRoleDistribution();
				 jobRole = new JobRole();
				 jobRoleDTO = distDTO.getJobRoleDTO();
				 //coping ttJobRoleDistribution into entity
				 BeanUtils.copyProperties(distDTO, ttJobRoleDistribution);
				 //coping jobRoleDTO into entity
				 BeanUtils.copyProperties(jobRoleDTO, jobRole);
				 ttJobRoleDistribution.setJobRole(jobRole);
				 ttJobRoleDistribution.setTtplanning(ttPlanning);
				 jobroleDistList.add(ttJobRoleDistribution);
			 }
		}
		
		if(jobroleDistList.size()>0){
			ttDAO.saveJobroleDistribution(jobroleDistList);
		}
		
	}

	@Override
	public void saveTTLaborCost(TTLaborCostForm ttLaborCostForm,
			Integer solutionID, Integer opportunityID)throws MSSPException {
		ttDAO.saveTTLaborCost(ttLaborCostForm,solutionID,opportunityID);
	}

}
