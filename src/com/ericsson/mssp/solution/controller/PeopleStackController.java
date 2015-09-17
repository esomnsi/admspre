package com.ericsson.mssp.solution.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.ScopeSummaryReportDTO;
import com.ericsson.mssp.common.dto.StaffCategoryMap;
import com.ericsson.mssp.common.entity.LessEffortDetail;
import com.ericsson.mssp.common.entity.PeopleStackResourceDetail;
import com.ericsson.mssp.common.entity.StaffAllocation;
import com.ericsson.mssp.common.entity.StaffCategory;
import com.ericsson.mssp.solution.forms.PeopleStackForm;
import com.ericsson.mssp.solution.service.ISolutionService;
import com.ericsson.mssp.solution.service.PeopleStackService;

@Controller
public class PeopleStackController extends BaseController
{
	@Autowired
	private PeopleStackService peopleStackService;
	@Autowired
	private ISolutionService solutionService;
	private final Log log = LogFactory.getLog(PeopleStackController.class);
	
	@RequestMapping("/solution/peopleStack")
	public ModelAndView peopleStackMgmt(Model model, HttpSession session) {
		Integer solution = getSessionSolutionId(session);
		Integer opportunityId = getSessionOpportunityId(session);
		log.info("solution from session: "+solution);
		
		
		PeopleStackForm peopleStackForm = new PeopleStackForm();
		if(solution!=null)
		{	
			Long solutionID = new Long(solution);
			List<PeopleStackResourceDetail> peopleStackResourceDetailDTOs = peopleStackService.loadAllResourceDetails(solutionID);
			double totalAnnualHrs=0;
			LessEffortDetail lessEffortDto;
			List<ScopeSummaryReportDTO> summaryDTOs;
			double peopleManagementEffortPercent=0;
			double peopleManagementEffortPercentRatio = 100;
			if(peopleStackResourceDetailDTOs==null || peopleStackResourceDetailDTOs.size()==0)// no resource details for the specific solution id
			{
				peopleStackResourceDetailDTOs.add(new PeopleStackResourceDetail());//add an empty PeopleStackResourceDetailDTO to show one empty row in ui
				summaryDTOs = new ArrayList<ScopeSummaryReportDTO>();//empty list when there is no resource details for specific solution
			}
			else
			{
				summaryDTOs = peopleStackService.loadInScopeSummaryData(solutionID);
				if(summaryDTOs==null || summaryDTOs.size()==0)
				{
					summaryDTOs.add(new ScopeSummaryReportDTO());
				}
				totalAnnualHrs = getToalAnnualHrs(summaryDTOs);//remains 0 when no resource is added
			}	
			lessEffortDto = peopleStackService.loadInScopeSummaryLessEffort(solutionID);
			//calculate only when there is at least one resource
			if(lessEffortDto==null)
				lessEffortDto = new LessEffortDetail();//default dto containing all less effort management as 0.0 and comments as blank
			if(totalAnnualHrs!=0)
				peopleManagementEffortPercent = generateDoubleScale((lessEffortDto.getPeopleMgmt()*100)/totalAnnualHrs,2);
			if(peopleManagementEffortPercent!=0)
				peopleManagementEffortPercentRatio=generateDoubleScale(100/peopleManagementEffortPercent,2);
			OpportunityDTO opportunityDTO= solutionService.getOpportunity(opportunityId.intValue());
			model.addAttribute("opportunityDTO",opportunityDTO);
			List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(opportunityId.intValue(), solutionID.intValue());
			model.addAttribute("serviceBucketData", serviceBucketData);
			//populate form data
			peopleStackForm.setSolId(solutionID);
			peopleStackForm.setPeopleStackResourceDetailDTOs(peopleStackResourceDetailDTOs);
			peopleStackForm.setScopeSummaryReportDTOs(summaryDTOs);
			peopleStackForm.setLessEffortDTO(lessEffortDto);
			peopleStackForm.setTotalAnnualHrs(totalAnnualHrs);
			double d = totalAnnualHrs-lessEffortDto.getPeopleMgmt()-lessEffortDto.getOthers();
			peopleStackForm.setNetOutSourcedEffort(d<0?0:d);
			peopleStackForm.setPeopleManagementPercent(peopleManagementEffortPercent);
			peopleStackForm.setPeopleManagementPercentRatio(peopleManagementEffortPercentRatio);
			//get all the staff allocation list
			List<StaffAllocation> staffAllocationDTOs = loadStaffAllocationDTOs(solutionID);
			peopleStackForm.setStaffCategoryDTOs(staffAllocationDTOs);
		}
		else
		{
			peopleStackForm.setSolId(null);
		}
		return new ModelAndView("peopleStack","peopleStackForm", peopleStackForm);
	}
	@RequestMapping(value = "/solution/saveResourceDetails", method = RequestMethod.POST)
	public ModelAndView saveResourceDetails(@ModelAttribute("peopleStackForm") PeopleStackForm peopleStackForm)
	{
		List<PeopleStackResourceDetail> dtos = peopleStackForm.getPeopleStackResourceDetailDTOs();
		dtos = calculateYearlyUtilizationAnnulaSallaryUSSD(dtos);
		peopleStackService.saveAllResourceDetails(dtos);
		dtos = peopleStackService.loadAllResourceDetails(peopleStackForm.getSolId().intValue());
		peopleStackForm.setPeopleStackResourceDetailDTOs(dtos);
		List<ScopeSummaryReportDTO> summaryDTOs = peopleStackService.loadInScopeSummaryData(peopleStackForm.getSolId());
		peopleStackForm.setScopeSummaryReportDTOs(summaryDTOs);
		double totalAnnualHrs = getToalAnnualHrs(summaryDTOs);
		peopleStackForm.setTotalAnnualHrs(totalAnnualHrs);
		double peopleManagementEffortPercent = 0;
		if(totalAnnualHrs!=0)
			peopleManagementEffortPercent = generateDoubleScale((peopleStackForm.getLessEffortDTO().getPeopleMgmt()*100)/totalAnnualHrs,2);
		peopleStackForm.setPeopleManagementPercent(peopleManagementEffortPercent);
		double peopleManagementEffortPercentRatio = 100;
		if(peopleManagementEffortPercent!=0)
			peopleManagementEffortPercentRatio= generateDoubleScale(100/peopleManagementEffortPercent,2);
		peopleStackForm.setPeopleManagementPercentRatio(peopleManagementEffortPercentRatio);
		double d = totalAnnualHrs-peopleStackForm.getLessEffortDTO().getPeopleMgmt()-peopleStackForm.getLessEffortDTO().getOthers();
		peopleStackForm.setNetOutSourcedEffort(d<0?0:d);
		
		List<StaffAllocation> staffAllocdtos = peopleStackForm.getStaffCategoryDTOs();
		if(staffAllocdtos!=null && staffAllocdtos.size()>0)
			setSolutionId(staffAllocdtos,peopleStackForm.getSolId(),peopleStackForm.getNetOutSourcedEffort());
		
		return new ModelAndView("peopleStack", "peopleStackForm", peopleStackForm);
	}
	
	@RequestMapping(value = "/solution/saveScopeSummary", method = RequestMethod.POST)
	public ModelAndView saveScopeSummary(@ModelAttribute("peopleStackForm") PeopleStackForm peopleStackForm)
	{
		LessEffortDetail dto = peopleStackForm.getLessEffortDTO();
		dto.setSolId(peopleStackForm.getSolId());
		peopleStackService.saveScopeSummary(dto);
		dto = peopleStackService.loadInScopeSummaryLessEffort(peopleStackForm.getSolId().intValue());
		peopleStackForm.setLessEffortDTO(dto);
		double d = peopleStackForm.getTotalAnnualHrs()-dto.getPeopleMgmt()-dto.getOthers();
		peopleStackForm.setNetOutSourcedEffort(d<0?0:d);
		double peopleManagementEffortPercent = 0;
		if(peopleStackForm.getTotalAnnualHrs()!=0)
			peopleManagementEffortPercent = generateDoubleScale((dto.getPeopleMgmt()*100)/peopleStackForm.getTotalAnnualHrs(),2);
		peopleStackForm.setPeopleManagementPercent(peopleManagementEffortPercent);
		
		double peopleManagementEffortPercentRatio = 100;
		if(peopleManagementEffortPercent!=0)
			peopleManagementEffortPercentRatio=generateDoubleScale(100/peopleManagementEffortPercent,2);
		peopleStackForm.setPeopleManagementPercentRatio(peopleManagementEffortPercentRatio);
		
		List<StaffAllocation> dtos = peopleStackForm.getStaffCategoryDTOs();
		setSolutionId(dtos,peopleStackForm.getSolId(),peopleStackForm.getNetOutSourcedEffort());
		peopleStackService.saveStaffAllocation(dtos);
		dtos = peopleStackService.loadStaffAllocation(peopleStackForm.getSolId().intValue());
		peopleStackForm.setStaffCategoryDTOs(dtos);
		
		return new ModelAndView("peopleStack", "peopleStackForm", peopleStackForm);
	}
	private List<StaffAllocation> loadStaffAllocationDTOs(long solutionID)
	{
		List<StaffAllocation> staffAllocationDTOs = peopleStackService.loadStaffAllocation(solutionID);
		if(staffAllocationDTOs==null || staffAllocationDTOs.size()==0)
		{
			staffAllocationDTOs  = new ArrayList<StaffAllocation>();
			StaffCategoryMap staffCategoryMap = StaffCategoryMap.getInstance();
			if(staffCategoryMap.staffAllocationList==null || staffCategoryMap.staffAllocationList.size()==0)
			{
				List<StaffCategory> staffCategoryDTOs = peopleStackService.loadStaffCategory();
				staffCategoryMap.setStaffAllocationList(staffCategoryDTOs);
			}
			staffAllocationDTOs = staffCategoryMap.staffAllocationList;
		}
		return staffAllocationDTOs;
	}
	private double getToalAnnualHrs(List<ScopeSummaryReportDTO> summaryDTOs)
	{
		double d=0;
		for(ScopeSummaryReportDTO dto:summaryDTOs)
			d+=dto.getTotalOutsourcedHrs();
		return d;
	}
	
	private List<PeopleStackResourceDetail> calculateYearlyUtilizationAnnulaSallaryUSSD(List<PeopleStackResourceDetail> dtos)
	{
		List<PeopleStackResourceDetail> dtoList = dtos;
		double d,dUSSD = 0;
		for(PeopleStackResourceDetail dto:dtoList)
		{
			d = generateDoubleScale((dto.getAnnualHrs()/(52*40))*100, 2);//40 hours per week, 52 weeks in a year
			dto.setYearlyUtilization(d);
			if(dto.getCuurency().equals("INR"))
				dUSSD=getSalaryInUSSD("INR", dto.getAnnualSummary());
			else if(dto.getCuurency().equals("EURO"))
				dUSSD=getSalaryInUSSD("EURO", dto.getAnnualSummary());
			else if(dto.getCuurency().equals("USSD"))
				dUSSD=dto.getAnnualSummary();
			dto.setAnnualSalaryUSSD(dUSSD);
		}
		return dtoList;
	}
	private void setSolutionId(List<StaffAllocation> dtos,Long solId,double netOutsourcedEffort)
	{
		for(StaffAllocation dto:dtos)
		{	
			dto.getPrimaryKey().setSolId(solId);
			double d = 0;
			if(dto.getAnnualUtil()!=0)
				d = generateDoubleScale(dto.getPercentageEffort()*netOutsourcedEffort/dto.getAnnualUtil()/100,2);
			dto.setFte(d);
			d=0;
			d = generateDoubleScale(dto.getPercentageEffort()*netOutsourcedEffort/100,2);
			dto.setEffort(d);
		}	
	}
//	private void setFteEFFORT(List<StaffAllocation> dtos,Long solId,double netOutsourcedEffort)
//	{
//		for(StaffAllocation dto:dtos)
//		{	
//			double d = 0;
//			if(dto.getAnnualUtil()!=0)
//				d = generateDoubleScale(dto.getPercentageEffort()*netOutsourcedEffort/dto.getAnnualUtil()/100,2);
//			dto.setFte(d);
//			d = generateDoubleScale(dto.getPercentageEffort()*netOutsourcedEffort/100,2);
//			dto.setEffort(d);
//		}	
//	}
	public double generateDoubleScale(double input,int scale)
	{
		return new BigDecimal(input).setScale(scale, RoundingMode.HALF_UP).doubleValue();
	}
	public double getSalaryInUSSD(String type,double salary)
	{
		double d = 0;
		if(type.equals("INR"))
		{
			d = getUSSDFromINR(salary);
		}
		else if(type.equals("EURO"))
		{
			d = getUSSDFromEURO(salary);
		}
		return d;
	}
	public double getUSSDFromINR(double salary){
		return generateDoubleScale(salary/50,2);
	}
	public double getUSSDFromEURO(double salary){
		return generateDoubleScale(salary/60,2);
	}
}
