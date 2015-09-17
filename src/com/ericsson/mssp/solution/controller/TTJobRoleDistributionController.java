package com.ericsson.mssp.solution.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.JobRoleDTO;
import com.ericsson.mssp.common.dto.JobRoleStagesDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.RateCardDTO;
import com.ericsson.mssp.common.dto.TTJobRoleDistributionDTO;
import com.ericsson.mssp.common.dto.TTPartitionNameDTO;
import com.ericsson.mssp.common.dto.TTSummaryStagingDTO;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.forms.TTDetailForm;
import com.ericsson.mssp.solution.forms.TTJobRoleDistributionForm;
import com.ericsson.mssp.solution.service.ISolutionService;
import com.ericsson.mssp.solution.service.ITTService;

@Controller
public class TTJobRoleDistributionController extends BaseController implements MSSPConstants{

	public static Logger logger = Logger.getLogger(TTJobRoleDistributionController.class);
	
	@Autowired
	ITTService ttService;
	
	@Autowired
	ISolutionService solutionService;
	
	@RequestMapping(value="/solution/ttJobroleDistribution")
	public String ttLCost(ModelMap model, HttpSession session){
		logger.info("inside tt jod role distribution controller");
		
		TTJobRoleDistributionForm distributionForm = new TTJobRoleDistributionForm();
		List<String> serviceBucketData = null;
		Integer solutionID = getSessionSolutionId(session);
		List<TTPartitionNameDTO> ttPartitionNameDTOs = null;
		OpportunityDTO opportunityDTO = new OpportunityDTO();
		Integer opportunityID = getSessionOpportunityId(session);
		Map<String,Object> timeInterval = null;
		List<RateCardDTO> rateCardDTOs = new ArrayList<RateCardDTO>();
		List<TTSummaryStagingDTO> ttSummaryStagingDTOs = new ArrayList<TTSummaryStagingDTO>();
		Integer ttPlanningId = null;
		
		
		
		try{
		
		if (opportunityID != null) {
		    opportunityDTO = solutionService.getOpportunity(opportunityID);
		}

		rateCardDTOs =  solutionService.getRateCardList();
    	logger.info("rate card dtos : " + rateCardDTOs.size());
    	ttPartitionNameDTOs = ttService.getPartitionNames(solutionID);
    	logger.info("tt partition names : " + ttPartitionNameDTOs.size());
    //	List<JobRoleDTO> jobRoleDTOList = solutionService.getJobRoleList();
    	List<JobRoleDTO> jobRoleDTOList = new ArrayList<JobRoleDTO>();
    	//Select on basis of Solution Id
    	List<JobRoleStagesDTO> jobRoleStagesDTOList = null;
    	try{
    	jobRoleStagesDTOList =  solutionService.getJobRoleStagesbySolutionID(solutionID);
    	 for(JobRoleStagesDTO jrsDTO:jobRoleStagesDTOList ){
    		 jobRoleDTOList.add(jrsDTO.getJobRoleDTO());
    	 }
    	}
    	catch(Exception e){
    		logger.error("::: " + e.getMessage() + " :::: " + e.getCause() + " ...");
    	}
    	
    	serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(opportunityID, solutionID);
    	logger.info("service bucket data : " + serviceBucketData.size());
    	timeInterval = ttService.getTimeInterval(solutionID,ApplicationPropertiesUtil.string2Date(opportunityDTO.getOpportunityDetailsDTO().getTsd()),ApplicationPropertiesUtil.string2Date(opportunityDTO.getOpportunityDetailsDTO().getTed()));
    	
    	if(timeInterval == null){
    		model.addAttribute("distributionForm", distributionForm);
    		model.addAttribute("message","Plese fill data for all the partition in previuos step ");
    		return "ttJobroleDistribution";
    	}
    	
    	List<TTJobRoleDistributionDTO> selectedJobRoleList = ttService.getSelectedJobRoleDistributionList(solutionID);
    	logger.info("selectedJobRoleList : " + selectedJobRoleList.size());
		
		String []timeLineInterval = (String[])timeInterval.get("dateArray");
		List<Map<String,String>> colorCode = (List<Map<String,String>>)timeInterval.get("colorCode");
			
		String partitionIds = "";
		for(int a=0;a<ttPartitionNameDTOs.size();a++){
			ttPlanningId= ttPartitionNameDTOs.get(a).getTtPlanningDTO().getTtplanningId();
		partitionIds +=ttPartitionNameDTOs.get(a).getTtpartitionNameId()+",";
		}
		
		partitionIds = partitionIds.substring(0, partitionIds.length() - 1);
		ttSummaryStagingDTOs = ttService.getTtSummaryStagingData(partitionIds);
		
		logger.info("tt summary staging list dtos size : " + ttSummaryStagingDTOs.size());
		
		Float []offShore = new Float[timeLineInterval.length];
		Float []onShore = new Float[timeLineInterval.length];
		Float []total = new Float[timeLineInterval.length];
		Float []offShorepc = new Float[timeLineInterval.length];
		Float []onShorepc = new Float[timeLineInterval.length];
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		
		for(int j=0;j<ttSummaryStagingDTOs.size();j++){
			for(int i=0;i<timeLineInterval.length;i++){
				
				c.setTime(ttSummaryStagingDTOs.get(j).getWeekDate());
				String tempDate = sdf.format(c.getTime());
				
				if(timeLineInterval[i].equals(tempDate)){
					if(onShore[i] == null){
						onShore[i] = 0F;
						offShore[i] = 0F;
						total[i] = 0F;
						onShorepc[i] = 0F;
						offShorepc[i] = 0F;
					}
					onShore[i] = onShore[i] + ttSummaryStagingDTOs.get(j).getOnshoreFte();
					offShore[i] = offShore[i] + ttSummaryStagingDTOs.get(j).getOffshoreFte();
					
					total[i] = onShore[i] + offShore[i];
					
					onShorepc[i] = (onShore[i]/total[i])*100;
					offShorepc[i] = (offShore[i]/total[i])*100;
					break;
				}
			}
		}
		
		String []partitionNames = new String[ttPartitionNameDTOs.size()];
		int i=0;
		for(;i<ttPartitionNameDTOs.size();i++){
			partitionNames[i] = ttPartitionNameDTOs.get(i).getTtpartitionName();
		}
		
		distributionForm.setTtPlanningId(ttPlanningId);
		
		model.addAttribute("distributionForm", distributionForm);
		model.addAttribute("onShore",onShore);
		model.addAttribute("offShore",offShore);
		model.addAttribute("total",total);
		model.addAttribute("onShorepc",onShorepc);
		model.addAttribute("offShorepc",offShorepc);
		model.addAttribute("timeLineInterval", timeLineInterval);
		model.addAttribute("partitionNames", partitionNames);
		model.addAttribute("colorCode", colorCode);
		model.addAttribute("jobRoleDTOList", jobRoleDTOList);
		model.addAttribute("opportunityDTO", opportunityDTO);
		model.addAttribute("serviceBucketData", serviceBucketData);
		model.addAttribute("selectedJobRoleList", selectedJobRoleList);
		
		model.addAttribute("jobRoleStagesDTOList", jobRoleStagesDTOList);
		}
		catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.info("::: " + e.getMessage() + " :::: " + e.getCause() + " ...");
			e.printStackTrace();
		}
		
		return "ttJobroleDistribution";
	}
	
	
	
	
	
	@RequestMapping(value="/solution/saveJobroleDistribution", method = RequestMethod.POST)
	public String saveJobroleDistribution(Model model, @ModelAttribute("distributionForm") TTJobRoleDistributionForm distributionForm, HttpSession session){
		//	log.info("inside saveFTEBreakup: ..............");
		  try {
			//
		//	Integer selPartitionId = ttDetailForm.getSelPartitionId();
			ttService.saveJobroleDistribution(distributionForm);
			
		  }catch(Exception e){
		    	logger.info("::: " + e.getMessage() + " :::: " + e.getCause() + " ...");
		  }
			return "redirect: ttJobroleDistribution";
	}
	
	
}
