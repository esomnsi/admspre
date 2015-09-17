package com.ericsson.mssp.solution.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.TNTDetailDTO;
import com.ericsson.mssp.common.dto.TNTPartitionDateDTO;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.TNTDetail;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.dao.ITNTDao;
import com.ericsson.mssp.solution.service.ITNTControllerService;
@Service
public class TNTControllerServiceImpl implements ITNTControllerService {

	@Autowired
	private ITNTDao tntDao;
	@Override
	public void saveTntPartitionDateDetails(TNTPartitionDateDTO partitionDate)
			throws MSSPException {
		tntDao.saveTntPartitionDateDetails(partitionDate);
	}
	@Override
	public TNTPartitionDateDTO getPartitionDate(Long solutionId, Long oppId)
	{
		TNTPartitionDateDTO partitionDateDto = tntDao.getPartitionDate(solutionId, oppId);
		return partitionDateDto;
	}

	@Override
	public List<TNTDetailDTO> getTntDetails(Long solutionId) 
	{
		List<TNTDetailDTO> detailDtoList=null;
		List<TNTDetail> tntdetailList = tntDao.getTnTDetail(solutionId);
		Map<String,TNTDetailDTO> tntDTOMap = new LinkedHashMap<String, TNTDetailDTO>();
		String partitionName = "";
		String idTntDetail = "";
		for(TNTDetail detailObj: tntdetailList)
		{
			partitionName = detailObj.getPartitionName();
			TNTDetailDTO detailDto = tntDTOMap.get(partitionName);
			if(detailDto==null)
			{
				detailDto = new TNTDetailDTO();
				detailDto.setSolutionId(solutionId);
				detailDto.setTotalFte(detailObj.getTotalFte());
				tntDTOMap.put(partitionName,detailDto);
			}
			if(MSSPConstants.TNT_CATEGORY_PLANNING.equals(detailObj.getCategory()))
			{
				idTntDetail += detailObj.getTntdetailId()+",";
				detailDto.setStart_date_planning(formatDateToString(detailObj.getStartDate()));
				detailDto.setEnd_date_planning(formatDateToString(detailObj.getEndDate()));
				detailDto.setFte_count_planning(detailObj.getFtecount());
				detailDto.setFte_percent_planning(detailObj.getFtepercent());
			}
			else if(MSSPConstants.TNT_CATEGORY_LEARN.equals(detailObj.getCategory()))
			{
				idTntDetail += detailObj.getTntdetailId()+",";
				detailDto.setStart_date_learn(formatDateToString(detailObj.getStartDate()));
				detailDto.setEnd_date_learn(formatDateToString(detailObj.getEndDate()));
				detailDto.setFte_count_learn(detailObj.getFtecount());
				detailDto.setFte_percent_learn(detailObj.getFtepercent());
			}
			else if(MSSPConstants.TNT_CATEGORY_ASSIST.equals(detailObj.getCategory()))
			{
				idTntDetail += detailObj.getTntdetailId()+",";
				detailDto.setStart_date_assist(formatDateToString(detailObj.getStartDate()));
				detailDto.setEnd_date_assist(formatDateToString(detailObj.getEndDate()));
				detailDto.setFte_count_assist(detailObj.getFtecount());
				detailDto.setFte_percent_assist(detailObj.getFtepercent());
			}
			else if(MSSPConstants.TNT_CATEGORY_PERFORM.equals(detailObj.getCategory()))
			{
				idTntDetail += detailObj.getTntdetailId()+",";
				detailDto.setStart_date_perform(formatDateToString(detailObj.getStartDate()));
				detailDto.setEnd_date_perform(formatDateToString(detailObj.getEndDate()));
				detailDto.setFte_count_perform(detailObj.getFtecount());
				detailDto.setFte_percent_perform(detailObj.getFtepercent());
			}
			else
			{
				idTntDetail += detailObj.getTntdetailId();
				detailDto.setStart_date_deliver(formatDateToString(detailObj.getStartDate()));
				detailDto.setEnd_date_deliver(formatDateToString(detailObj.getEndDate()));
				detailDto.setFte_count_deliver(detailObj.getFtecount());
				detailDto.setFte_percent_deliver(detailObj.getFtepercent());
			}
			detailDto.setId_tnt_detail(idTntDetail);
			if(idTntDetail.split(",").length == 5)
			{
				idTntDetail = ""; // After 5 elements have been added, reset the string.
			}
		}
		detailDtoList = new ArrayList<TNTDetailDTO>();
		detailDtoList.addAll(tntDTOMap.values());
		return detailDtoList;
	}

	@Override
	public void saveTntDetailDto(List<TNTDetailDTO> tntDetail,Long solutionId) throws MSSPException 
	{
		List<TNTDetail> dataList = new ArrayList<TNTDetail>();
		TNTDetail objPlanning = null;
		TNTDetail objLearn = null;
		TNTDetail objAssist = null;
		TNTDetail objPerform = null;
		TNTDetail objDeliver = null;
		Solution solution = null;
		for(TNTDetailDTO detailDto : tntDetail)
		{
			String[] id = null;
			if(detailDto != null)
			{
				solution = new Solution();
				solution.setSolutionId(solutionId.intValue());
				id = detailDto.getId_tnt_detail().split(",");
				objPlanning = new TNTDetail();
				if(id.length < 5)
				{
					// New entry. Do nothing as the id value will be auto-incremented
				}
				else
				{
					objPlanning.setTntdetailId(Integer.parseInt(id[0]));
				}
				objPlanning.setSolution(solution);
				objPlanning.setTotalFte(detailDto.getTotalFte());
				objPlanning.setPartitionName(detailDto.getPartitionName());
				objPlanning.setCategory(MSSPConstants.TNT_CATEGORY_PLANNING);
				objPlanning.setStartDate(formatStringToDate(detailDto.getStart_date_planning()));
				objPlanning.setEndDate(formatStringToDate(detailDto.getEnd_date_planning()));
				objPlanning.setFtecount(detailDto.getFte_count_planning());
				objPlanning.setFtepercent(detailDto.getFte_percent_planning());
				dataList.add(objPlanning);
				objLearn = new TNTDetail();
				if(id.length < 5)
				{
				}
				else
				{
					objLearn.setTntdetailId(Integer.parseInt(id[1]));
				}
				objLearn.setSolution(solution);
				objLearn.setTotalFte(detailDto.getTotalFte());
				objLearn.setPartitionName(detailDto.getPartitionName());
				objLearn.setCategory(MSSPConstants.TNT_CATEGORY_LEARN);
				objLearn.setStartDate(formatStringToDate(detailDto.getStart_date_learn()));
				objLearn.setEndDate(formatStringToDate(detailDto.getEnd_date_learn()));
				objLearn.setFtecount(detailDto.getFte_count_learn());
				objLearn.setFtepercent(detailDto.getFte_percent_learn());
				dataList.add(objLearn);
				objAssist = new TNTDetail();
				if(id.length < 5)
				{
				}
				else
				{
					objAssist.setTntdetailId(Integer.parseInt(id[2]));
				}
				objAssist.setSolution(solution);
				objAssist.setPartitionName(detailDto.getPartitionName());
				objAssist.setTotalFte(detailDto.getTotalFte());
				objAssist.setCategory(MSSPConstants.TNT_CATEGORY_ASSIST);
				objAssist.setStartDate(formatStringToDate(detailDto.getStart_date_assist()));
				objAssist.setEndDate(formatStringToDate(detailDto.getEnd_date_assist()));
				objAssist.setFtecount(detailDto.getFte_count_assist());
				objAssist.setFtepercent(detailDto.getFte_percent_assist());
				dataList.add(objAssist);
				objPerform = new TNTDetail();
				if(id.length < 5)
				{
				}
				else
				{
					objPerform.setTntdetailId(Integer.parseInt(id[3]));
				}
				objPerform.setSolution(solution);
				objPerform.setPartitionName(detailDto.getPartitionName());
				objPerform.setTotalFte(detailDto.getTotalFte());
				objPerform.setCategory(MSSPConstants.TNT_CATEGORY_PERFORM);
				objPerform.setStartDate(formatStringToDate(detailDto.getStart_date_perform()));
				objPerform.setEndDate(formatStringToDate(detailDto.getEnd_date_perform()));
				objPerform.setFtecount(detailDto.getFte_count_perform());
				objPerform.setFtepercent(detailDto.getFte_percent_perform());
				dataList.add(objPerform);
				objDeliver = new TNTDetail();
				if(id.length < 5)
				{
				}
				else
				{
					objDeliver.setTntdetailId(Integer.parseInt(id[4]));
				}
				objDeliver.setSolution(solution);
				objDeliver.setPartitionName(detailDto.getPartitionName());
				objDeliver.setTotalFte(detailDto.getTotalFte());
				objDeliver.setCategory(MSSPConstants.TNT_CATEGORY_DELIVER);
				objDeliver.setStartDate(formatStringToDate(detailDto.getStart_date_deliver()));
				objDeliver.setEndDate(formatStringToDate(detailDto.getEnd_date_deliver()));
				objDeliver.setFtecount(detailDto.getFte_count_deliver());
				objDeliver.setFtepercent(detailDto.getFte_percent_deliver());
				dataList.add(objDeliver);
			}
		}
		tntDao.saveTntDetail(dataList);
	}
	/**
	 * This method converts a date to a string with the format (yyyy-mm-dd)
	 * Method Name	: formatDateToString
	 * Input& Output:
	 * 	@param date The date object that is to be converted to string.
	 * 	@return The formatted string from the date.
	 */
	private String formatDateToString(Date date)
	{
		if(date != null)
		{
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.format(date);
		}
		else
		{
			return "";
		}
		
	}
	/**
	 * This method converts the provided string to a date with the format (yyyy-mm-dd) 
	 * Method Name	: formatStringToDate
	 * Input& Output:
	 * 	@param s The String object that is to be converted to a Date object.
	 * 	@return The formatted date object from a String
	 */
	private Date formatStringToDate(String s)
	{
		Date date = null;
		if(s != null)
		{
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try 
			{
				date = formatter.parse(s);
			}
			catch (ParseException e) 
			{
				e.printStackTrace();
			}

		}
				return date;
	}
	
}
