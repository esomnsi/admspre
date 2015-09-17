package com.ericsson.mssp.solution.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.dto.FTESummaryDTO;
import com.ericsson.mssp.common.dto.LocationBreakupDTO;
import com.ericsson.mssp.common.dto.LocationPyramidDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.ProductEstimationBaseEffortForSolutionDTO;
import com.ericsson.mssp.common.dto.ProductivityLeverDTO;
import com.ericsson.mssp.common.dto.ServiceElementJobRoleStagesDTO;
import com.ericsson.mssp.common.dto.SolutionLeverDTO;
import com.ericsson.mssp.common.dto.SolutionUtilPerYearDTO;
import com.ericsson.mssp.common.entity.FTESummary;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.LocationBreakup;
import com.ericsson.mssp.common.entity.LocationPyramid;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.ProductEstimationBaseEffortForSolution;
import com.ericsson.mssp.common.entity.ProductivityLever;
import com.ericsson.mssp.common.entity.ServiceElementJobRoleStages;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionLever;
import com.ericsson.mssp.common.entity.SolutionUtilPerYear;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.dao.APADAO;
import com.ericsson.mssp.solution.dao.ISolutionLeverDAO;
import com.ericsson.mssp.solution.service.SolutionLeverService;

@Service
public class SolutionLeverServiceImpl implements SolutionLeverService {

    public static Logger logger = Logger.getLogger(SolutionLeverServiceImpl.class);

    @Autowired
    
    private ISolutionLeverDAO solutionLeverDAO;
    
    @Autowired
    private APADAO apaDAO;
    
    
    @Override
	public Integer getServiceElementIdByOppScopeId(Integer oppScopeId) throws MSSPException {
		// TODO Auto-generated method stub
		return apaDAO.getServiceElementIdByOppScopeId(oppScopeId);
	}
    
    @Override
	public List<JobRoleStages> getJobRoleByServiceElementId(Integer serviceElementId,
			boolean ccmFLag) throws MSSPException {
		// TODO Auto-generated method stub
		List<JobRoleStages> JobRoleList = new ArrayList<JobRoleStages>();
		List<ServiceElementJobRoleStages> serEleJobRoles = apaDAO.getJobRoleByServiceElementId(serviceElementId, ccmFLag);
			JobRoleStages jobRole = null;
		for(ServiceElementJobRoleStages serviceElementJobRoles : serEleJobRoles ){
			jobRole = serviceElementJobRoles.getJobRoleStages();
			JobRoleList.add(jobRole);
			
		}
		
		return JobRoleList;
	}
    
	public OpportunityDetail getOpportunityDetail(Integer opportunityId)  throws MSSPException{
		return solutionLeverDAO.getOpportunityDetail(opportunityId);
	}
	
    //For solution util per year
    
	@Override
	public void saveSolutionUtilPerYear(SolutionUtilPerYearDTO solutionUtilPerYearDTO,Integer solutionId ) throws DAOException {
		SolutionUtilPerYear solutionUtilPerYear = new SolutionUtilPerYear();
		BeanUtils.copyProperties(solutionUtilPerYearDTO, solutionUtilPerYear);
		
		Solution solution = new Solution();
		solution.setSolutionId(solutionId);
		
		solutionUtilPerYear.setSolution(solution);
		
		solutionLeverDAO.saveSolutionUtilPerYear(solutionUtilPerYear);

	}

	@Override
	public SolutionUtilPerYearDTO getSolutionUtilBySolutionId(Integer solutionId) {
		SolutionUtilPerYear solutionUtilPerYear =solutionLeverDAO.getSolutionUtilBySolutionId(solutionId);
		SolutionUtilPerYearDTO solutionUtilPerYearDTO = new SolutionUtilPerYearDTO();
		//populating dto from entity
		if(null !=solutionUtilPerYear){
		BeanUtils.copyProperties(solutionUtilPerYear,solutionUtilPerYearDTO);
		}
		return solutionUtilPerYearDTO;
	}
	
	@Override
	public void saveProductivityLever(SolutionLeverDTO solutionLeverDTO, Integer opportunityId)
			 throws Exception {
		// TODO Auto-generated method stub
		
		SolutionLever solLever =  solutionLeverDAO.getSolutionLever(solutionLeverDTO.getSolutionId());
		//solLever.setSolutionLeverId(solutionLeverDTO.getSolutionLeverId());
		solLever.setSolutionLeverApproach(solutionLeverDTO.getSolutionLeverApproach());
		solutionLeverDAO.saveSolutionLever(solLever);
		
		//SolutionUtilPerYear solutionUtilPerYear =solutionLeverDAO.getSolutionUtilBySolutionId(solutionLeverDTO.getSolutionId());
		Map<String,Float> productivityPCMap = new HashMap<String,Float>();
		List<ProductivityLeverDTO> prodLeverList =solutionLeverDTO.getProdLeverList();
		ProductivityLever productivityLever = null;
		OpportunityScope oppScope = null;
		for(ProductivityLeverDTO prodLeverDTO : prodLeverList){
			productivityLever = new ProductivityLever();
			oppScope = new OpportunityScope();
			BeanUtils.copyProperties(prodLeverDTO, productivityLever);
			oppScope.setOpportunityScopeId(prodLeverDTO.getOpportunityScopeDTO().getOpportunityScopeId());
			productivityLever.setSolutionLever(solLever);
			productivityLever.setOpportunityScope(oppScope);
			solutionLeverDAO.saveProductivityLever(productivityLever);
			
			productivityPCMap.put(oppScope.getOpportunityScopeId()+":"+getYear(prodLeverDTO.getMonthYear()), prodLeverDTO.getProductivityPC());
			
		
			
		}
		
		
		List<FTESummary> fteSummaryList = getFTEMonthwise(solutionLeverDTO, solLever, productivityPCMap, opportunityId);
		
		solutionLeverDAO.saveProductivityLeverMonthwise(fteSummaryList);		
			
		
		
	}

	private List<FTESummary>  getFTEMonthwise(SolutionLeverDTO solutionLeverDTO,
			SolutionLever solLever, Map<String, Float> productivityPCMap, Integer opportunityId) throws MSSPException{
		
		
	
		List<LocationBreakup> locationBreakupList = solutionLeverDAO.getAllLocationBreakupBySolLeverId(solLever.getSolutionLeverId());
		
		OpportunityDetail oppDetail = getOpportunityDetail(opportunityId);
		//List <Integer>yearList = getYearList(oppDetail);
		
		//Solution sol = new Solution();
		//sol.setSolutionId(solutionLeverDTO.getSolutionId());
		
		 Date endDate = oppDetail.getSteadyStateEndDate();
		 Date startDate = oppDetail.getSteadyStateStartDate();
		 Calendar cal = Calendar.getInstance();
		 Calendar cal2 = Calendar.getInstance();
		 cal2.setTime(endDate);
		 
		 cal.setTime(startDate);
		 int startMon = cal.get(Calendar.MONTH);
		 int startYear =  cal.get(Calendar.YEAR);
		 int currMon = startMon;
		//
		Float totalFTE = 0F;
		List<FTESummary> fteSummaryList = new ArrayList<FTESummary>();
		Integer lastOppScopeId= null;
		
		for(LocationBreakup lb : locationBreakupList){
			Integer oppScopeId = lb.getOpportunityScope().getOpportunityScopeId();
			String key = oppScopeId+ ":"+getYear(lb.getMonthYear());
			int currYear = getYear(lb.getMonthYear());
			float prodPC=0;
		
			if(productivityPCMap.get(key) != null){
				 prodPC = productivityPCMap.get(key);
			}else{
				 prodPC=0; 
				 }
			
			if(oppScopeId != lastOppScopeId){
				 currMon = startMon;
				 currYear =  startYear;
			}
			
		
				cal.set(Calendar.YEAR,currYear);
					 
				if("Uniform".equals(solutionLeverDTO.getSolutionLeverApproach())){
					currMon = 0;
					cal.set(Calendar.MONTH,currMon);
					if(startYear == currYear){
						currMon=startMon;
						totalFTE = getTotalFTEForScope(lb);
					}
						
						Float prodFTE = totalFTE*prodPC/100;
						
						Float monDiff = prodFTE/((11-currMon)+1);
						while(currMon < 12){
						
							FTESummary fteSummary = setFTESummary(solutionLeverDTO,
									cal, currMon, totalFTE, oppScopeId);
							
							if(cal.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal.get(Calendar.MONTH)>cal2.get(Calendar.MONTH)){
								break;
							}
							
							totalFTE = totalFTE-monDiff;
							fteSummaryList.add(fteSummary);
							currMon+=1;
							
						}
					
					
				}else{
					if(startYear == currYear){
						totalFTE = getTotalFTEForScope(lb);
					}else{
						totalFTE = totalFTE - (totalFTE*prodPC/100);
						currMon =0;
					}
					
					while(currMon < 12){
						FTESummary fteSummary = setFTESummary(solutionLeverDTO,
								cal, currMon, totalFTE, oppScopeId);
					
						fteSummaryList.add(fteSummary);
						currMon+=1;
						cal.set(Calendar.MONTH,currMon);
						if(cal.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal.get(Calendar.MONTH)>cal2.get(Calendar.MONTH)){
								break;
						}
						//
					}
					
				  
				}
				lastOppScopeId = oppScopeId;
			}
		
		/*for(FTESummary fte : fteSummaryList){
			System.out.print( "***********                 getOpportunityScopeId : " + fte.getOpportunityScope().getOpportunityScopeId());
			System.out.print( " getToDate : " + fte.getToDate());
			System.out.print( " getFtecount : " + fte.getFtecount()  +"             *************\n" );
		}*/
		return fteSummaryList;
	}

	private FTESummary setFTESummary(SolutionLeverDTO solutionLeverDTO,
			Calendar cal, int currMon, Float totalFTE, Integer oppScopeId) {
		FTESummary fteSummary = new FTESummary();
		OpportunityScope oppScope = new OpportunityScope();
		oppScope.setOpportunityScopeId(oppScopeId);
		fteSummary.setSolutionId(solutionLeverDTO.getSolutionId());
		fteSummary.setOpportunityScope(oppScope);
		cal.set(Calendar.MONTH,currMon);
		cal.set(Calendar.DATE,1);
		fteSummary.setToDate(cal.getTime());
		fteSummary.setFtecount(totalFTE);
		return fteSummary;
	}

	private Float getTotalFTEForScope(LocationBreakup lb) {
		float totalFTE =0F;
		totalFTE +=  (lb.getOnshoreLocalFte()== null)?0F:lb.getOnshoreLocalFte();
		totalFTE +=  (lb.getOnshoreGSCiFte()== null)?0F:lb.getOnshoreGSCiFte();	
		totalFTE +=  (lb.getOnshore3PPFte()== null)?0F:lb.getOnshore3PPFte();
		totalFTE +=  (lb.getOffshoreFte()== null)?0F:lb.getOffshoreFte();
		totalFTE +=  (lb.getNearshoreFte()== null)?0F:lb.getNearshoreFte();
		
		System.out.println( " totalFTE  :::  "  +totalFTE );
		return totalFTE;
	}
	
	public  List<Integer> getYearList(OpportunityDetail oppDetail){
		Date startDate= oppDetail.getSteadyStateStartDate();
		Date endDate= oppDetail.getSteadyStateEndDate();
		List<Integer> yearList = new ArrayList<Integer>();
		
		 Calendar cal1 = Calendar.getInstance();
		 cal1.setTime(startDate);
		 int startYear= cal1.get(Calendar.YEAR);
		
		 Calendar cal2 = Calendar.getInstance();
		 cal2.setTime(endDate);
		 int endYear = cal2.get(Calendar.YEAR);
		 
		 while(startYear<=endYear){
			 yearList.add(startYear);
			 startYear=startYear+1;
		 }
		 
		 return yearList;
	}
	
	public  List<Date> getYearList(Date startDate, Date endDate){
		
		List<Date> yearList = new ArrayList<Date>();
		
		while(startDate.before(endDate)){
			 Calendar cal1 = Calendar.getInstance();
			 cal1.setTime(startDate);
			 cal1.add(Calendar.MONTH, 1);
			 yearList.add(cal1.getTime());
			
		}
		
		 return yearList;
	}
	
	
		@Override
	public List<ProductivityLeverDTO> getProductivityLeverList(
			Integer solutionId) throws DAOException{
		// TODO Auto-generated method stub
		List <ProductivityLeverDTO> plList = new ArrayList<ProductivityLeverDTO>();
		List<ProductivityLever> prodLeverList = solutionLeverDAO.getProductivityLeverList(solutionId);
		OpportunityScopeDTO oppScopeDTO = null;
		ProductivityLeverDTO prodLeverDTO = null;
		for(ProductivityLever prodLever: prodLeverList){
			prodLeverDTO = new ProductivityLeverDTO();
			oppScopeDTO = new OpportunityScopeDTO();
			BeanUtils.copyProperties(prodLever, prodLeverDTO);
			oppScopeDTO.setOpportunityScopeId(prodLever.getOpportunityScope().getOpportunityScopeId());
			
			prodLeverDTO.setOpportunityScopeDTO(oppScopeDTO);
			plList.add(prodLeverDTO);
		}
		return plList;
	}
    //End for solution util per year
	
	
	//For LocationPyramid
	@Override
	public void saveLocationBreakup(LocationBreakupDTO locationBreakupDTO,Integer solutionId,Integer oppScopeId ) throws DAOException

	 {
		LocationBreakup locationBreakup = new LocationBreakup();
		BeanUtils.copyProperties(locationBreakupDTO, locationBreakup);
		
		SolutionLever solutionLever = solutionLeverDAO.getSolutionLever(solutionId);
		/*Solution solution = new Solution();
		solution.setSolutionId(solutionId);
		
		solutionLever.setSolution(solution);*/
		// setting solutionLever
		
		locationBreakup.setSolutionLever(solutionLever);
		
		//Creating and setting OpportunityScope
		OpportunityScope opportunityScope = new OpportunityScope();
		opportunityScope.setOpportunityScopeId(oppScopeId);
		
		locationBreakup.setOpportunityScope(opportunityScope);
		
		solutionLeverDAO.saveLocationBreakup(locationBreakup);

	}
	
	@Override
	public List<LocationBreakupDTO> loadLocationBreakupByOppScopeID(Integer oppScopeId){
		List<LocationBreakup> locationBreakupList = solutionLeverDAO.loadLocationBreakupByOppScopeID(oppScopeId);

		List<LocationBreakupDTO>locationBreakupDTOList = new ArrayList<LocationBreakupDTO>();

		if(null!= locationBreakupList && locationBreakupList.size() >0){
			
			for(LocationBreakup  locationBreakup :  locationBreakupList){
				LocationBreakupDTO locationBreakupDTO = new LocationBreakupDTO();
				BeanUtils.copyProperties(locationBreakup,locationBreakupDTO);
				locationBreakupDTOList.add(locationBreakupDTO);
			}
		}
		return locationBreakupDTOList;
		
	}
	
	
	private int getYear(Date date){
	 Calendar cal1 = Calendar.getInstance();
	 cal1.setTime(date);
	 return cal1.get(Calendar.YEAR);
	}
	 
	 
	//End for Location Pyramid
	
	//For Pyramid Pop-up
	@Override
	public List<OpportunityScope> getOppourtunityScopeList(Integer oppId,
			Integer solnId) throws DAOException{
		
		return apaDAO.getOppourtunityScopeList(oppId, solnId);
	}
	public List<ServiceElementJobRoleStages> getJobRoleSerEleByServiceElementId(Integer serviceElementId,
			boolean ccmFLag) throws MSSPException {
		// TODO Auto-generated method stub
		
		List<ServiceElementJobRoleStages> serEleJobRoles = apaDAO.getJobRoleByServiceElementId(serviceElementId, ccmFLag);
		
		return serEleJobRoles;
	}
	
	/*
	 * Service returning DTO instead of Entity List
	 */
	@Override
	public List<ServiceElementJobRoleStagesDTO> getJobRoleSerEleDTOListByServiceElementId(Integer serviceElementId,
			boolean ccmFLag) throws DAOException{
		List<ServiceElementJobRoleStagesDTO>serviceElementJobRoleStagesDTOList = new ArrayList<ServiceElementJobRoleStagesDTO>();
		List<ServiceElementJobRoleStages> serviceElementJobRoleStagesList = apaDAO.getJobRoleByServiceElementId(serviceElementId, ccmFLag);		
	
		for(ServiceElementJobRoleStages  serviceElementJobRoleStages :  serviceElementJobRoleStagesList){
			ServiceElementJobRoleStagesDTO serviceElementJobRoleStagesDTO = new ServiceElementJobRoleStagesDTO();
			BeanUtils.copyProperties(serviceElementJobRoleStages,serviceElementJobRoleStagesDTO);
			//Setting complex object
			serviceElementJobRoleStagesDTO.setJobRoleStages(serviceElementJobRoleStages.getJobRoleStages());
			serviceElementJobRoleStagesDTO.setServiceElement(serviceElementJobRoleStages.getServiceElement());
			
			serviceElementJobRoleStagesDTOList.add(serviceElementJobRoleStagesDTO);
		}
		return serviceElementJobRoleStagesDTOList;
	
	}
	//
	/*
	 * saving Location Pyramid
	 */
	public void saveLocationPyramid(LocationPyramidDTO locationPyramidDTO,Integer solutionId,Integer oppScopeId )
			throws DAOException{
		
		LocationPyramid locationPyramid = new LocationPyramid();
		BeanUtils.copyProperties(locationPyramidDTO, locationPyramid);
		
		SolutionLever solutionLever = solutionLeverDAO.getSolutionLever(solutionId);
	
		// setting solutionLever
		
		locationPyramid.setSolutionLever(solutionLever);
		
		//Creating and setting OpportunityScope
		OpportunityScope opportunityScope = new OpportunityScope();
		opportunityScope.setOpportunityScopeId(oppScopeId);
		
		locationPyramid.setOpportunityScope(opportunityScope);
		
		solutionLeverDAO.saveLocationPyramid(locationPyramid);
		
	}
	
	/*
	 * Fetching Location Pyramid by solutionId and oppScopeId
	 */
	@Override
	public List <LocationPyramidDTO> loadLocationPyramid( Integer solutionId, Integer oppScopeId ,String location ,String subLocation){
		List<LocationPyramid> locationPyramidList = solutionLeverDAO.loadLocationPyramid(solutionId,oppScopeId, location , subLocation);
		List<LocationPyramidDTO>locationPyramidDTOList = new ArrayList<LocationPyramidDTO>();

		if(null!= locationPyramidList && locationPyramidList.size() >0){
			
			for(LocationPyramid  locationPyramid :  locationPyramidList){
				LocationPyramidDTO locationPyramidDTO = new LocationPyramidDTO();
				BeanUtils.copyProperties(locationPyramid,locationPyramidDTO);
				
				locationPyramidDTO.setJobRoleStages(locationPyramid.getJobRoleStages());
				locationPyramidDTO.setOpportunityScope(locationPyramid.getOpportunityScope());
				locationPyramidDTO.setSolutionLever(locationPyramid.getSolutionLever());
				locationPyramidDTOList.add(locationPyramidDTO);
			}
		}
		return locationPyramidDTOList;
		
	}
	
	@Override
	public ProductEstimationBaseEffortForSolutionDTO getBaseEffort(Integer solutionId,Integer oppScopeId){
		
		ProductEstimationBaseEffortForSolutionDTO dto = new ProductEstimationBaseEffortForSolutionDTO();
		ProductEstimationBaseEffortForSolution entity = 
				solutionLeverDAO.getBaseEffort(solutionId, oppScopeId);
		//BeanUtils.copyProperties(entity, dto);
		if(null!= entity){
		dto.setTotalBaseFTE(entity.getTotalBaseFTE());
		dto.setTotalBaseHours(entity.getTotalBaseHours());
		dto.setOpportunityScopeID(entity.getOpportunityScope().getOpportunityScopeId());
		dto.setSolutionID(entity.getSolution().getSolutionId());
		}
		return dto;
	}

	@Override
	public List<FTESummaryDTO> getMonthwiseFTEList(Integer solutionId,
			Date startDate, Date endDate) throws MSSPException {
		List<FTESummaryDTO> monthWiseDTOList = new ArrayList<FTESummaryDTO> ();
		Calendar cal =Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		List<FTESummary> monthWiseList = solutionLeverDAO.getMonthwiseFTEList(solutionId,
			 startDate, cal.getTime());
		OpportunityScopeDTO op = null;
		for(FTESummary fteSummary : monthWiseList){
			FTESummaryDTO dto = new FTESummaryDTO();
			op = new OpportunityScopeDTO(); 
			BeanUtils.copyProperties(fteSummary.getOpportunityScope(),op);
			BeanUtils.copyProperties(fteSummary,dto);
			dto.setOpportunityScopeDTO(op);
			monthWiseDTOList.add(dto);
		}
		//System.out.println( "monthWiseList  + " + monthWiseList);
		return monthWiseDTOList;
	}

	@Override
	public String updateFTESummary(Long fteSummaryId, Float fteValue)throws MSSPException {
	
		return solutionLeverDAO.updateFTESummary(fteSummaryId,fteValue);
	}

	@Override
	public String getSolutionLeverApporach(Integer solId) throws Exception {
		// TODO Auto-generated method stub
		SolutionLever solutionLever = solutionLeverDAO.getSolutionLever(solId);
		
		return solutionLever.getSolutionLeverApproach();
	}

	
    /*@Override
	
	@Override
	public Date getSteadyStateStartDate(Integer opportunityId) throws MSSPException {
		
		return solutionLeverDAO.getSteadyStateStartDate(opportunityId);		
	}

	@Override
	public Date getSteadyStateEndDate(Integer opportunityId) throws MSSPException{
		
		return solutionLeverDAO.getSteadyStateEndDate(opportunityId);
	}
	

	@Override
	public Float getStartupFTE(Integer solutionId, Integer oppScopeId)
			throws MSSPException {

		return solutionLeverDAO.getStartupFTE(solutionId, oppScopeId);
	}

	@Override
	@Transactional
	public void saveSolutionLevers(SolutionLeverDTO solnLeverDTO)
			throws MSSPException {

		Integer oppScopeId = solnLeverDTO.getOppScopeId();
		
		OpportunityScope  opportunityScope = new OpportunityScope();
		opportunityScope.setOpportunityScopeId(oppScopeId);
		
		SolutionLever sl = solnLeverDTO.getSolutionLever();		
		// Save Soln Lever
		if(sl != null) {
			//int n = solutionLeverDAO.deleteSolutionLever(solnLeverDTO.getSolutionId());
			//logger.info("Solution Lever with SolutionID "+solnLeverDTO.getSolutionId()+" deleted :"+n);
			//Fresh Insert
			//sl.setSolutionLeverId(null);
			logger.info("Solution Lever Id to be saved:"+sl.getSolutionLeverId());
			solutionLeverDAO.saveSolutionLever(sl);	
			logger.info("Solution Lever Id generated:"+sl.getSolutionLeverId());
			//solnLeverDTO.setSolutionLever(sl);
		}
		if(sl != null && solnLeverDTO.getProdLeverList() != null) {
			logger.info("Total number of Productivity Levers: "+(solnLeverDTO.getProdLeverList().size()-1));
			logger.info("Solution Lever Approach: "+sl.getSolutionLeverApproach());	
			// Added to incorporate delete functionality
			int n = solutionLeverDAO.deleteProductivityLever(solnLeverDTO.getSolutionId(), oppScopeId);
			logger.info("Total number of Productivity Levers deleted :"+n);					
				
		
		Solution soln = new Solution();
		soln.setSolutionId(solnLeverDTO.getSolutionId());
		sl.setSolution(soln);
			
		//sl.setSolutionLeverApproach(solnLeverDTO.getSolutionLever().getSolutionLeverApproach());
				
		
	// For omitting 1st row of Productivity Lever
	//		int count = 0; 
			for(ProductivityLever pl : solnLeverDTO.getProdLeverList()) {	
				//Always fresh insert
				pl.setProductivityLeverId(null);
				pl.setOpportunityScope(opportunityScope);
				pl.setSolutionLever(sl);
				if(pl.getOpportunityScope() != null && pl.getMonthYear() != null && pl.getComputedProductivity() != 0&& count > 0) {
					solutionLeverDAO.saveProductivityLever(pl);
				}
	//			count++;
			}
		}
		if(sl != null && solnLeverDTO.getLocBreakupList() != null) {

			// Added to incorporate delete functionality
			int nlb = solutionLeverDAO.deleteLocationBreakup(solnLeverDTO.getSolutionId(), oppScopeId);
			logger.info("Total number of Location Breakups deleted :"+nlb);	
			
			logger.info("Total number of Location Breakups: "+solnLeverDTO.getLocBreakupList().size());		
			for(LocationBreakup lb : solnLeverDTO.getLocBreakupList()) {		
				//Always fresh insert
				lb.setLocationBreakupId(null);
				lb.setOpportunityScope(opportunityScope);
				lb.setSolutionLever(sl);
				logger.info("LocationBreakup Opportunity Scope: "+lb.getOpportunityScope());
				logger.info("LocationBreakup TimeLine: "+lb.getMonthYear());
				if(lb.getOpportunityScope() != null && lb.getMonthYear() != null && lb.getOnshorePc() != null) {
					solutionLeverDAO.saveLocationBreakup(lb);
				}
			}
		}
		
		// Added to incorporate delete functionality for location pyramid
			int nlp = solutionLeverDAO.deleteLocationPyramid(solnLeverDTO.getSolutionId(), oppScopeId);
			logger.info("Total number of Location Pyramids deleted :"+nlp);	

		//saving location pyramid data for onshore
		if(sl != null && solnLeverDTO.getOnLocalLocListForSave() != null) {
			
			for(LocationPyramid lp : solnLeverDTO.getOnLocalLocListForSave()) {		
				saveLocationPyramid(opportunityScope, sl, lp);
			}	
		}
		
		if(sl != null && solnLeverDTO.getOnGSCiLocListForSave() != null) {
			
			for(LocationPyramid lp : solnLeverDTO.getOnGSCiLocListForSave()) {		
				saveLocationPyramid(opportunityScope, sl, lp);
			}	
		}
		
		if(sl != null && solnLeverDTO.getOn3PPLocListForSave() != null) {
			
			for(LocationPyramid lp : solnLeverDTO.getOn3PPLocListForSave()) {		
				saveLocationPyramid(opportunityScope, sl, lp);
			}	
		}
		
		if(sl != null && solnLeverDTO.getOffLocListForSave() != null) {

			for(LocationPyramid lp : solnLeverDTO.getOffLocListForSave()) {		
				saveLocationPyramid(opportunityScope, sl, lp);
			}	
		}
		
		if(sl != null && solnLeverDTO.getNearLocListForSave() != null) {

			for(LocationPyramid lp : solnLeverDTO.getNearLocListForSave()) {		
				saveLocationPyramid(opportunityScope, sl, lp);
			}	
		}
	}

	*//**
	 * @param opportunityScope
	 * @param sl
	 * @param lp
	 * @throws DAOException
	 *//*
	private void saveLocationPyramid(OpportunityScope opportunityScope,
			SolutionLever sl, LocationPyramid lp) throws DAOException {
		//Always fresh insert
		lp.setLocationPyramidId(null);
		lp.setOpportunityScope(opportunityScope);
		lp.setSolutionLever(sl);
		solutionLeverDAO.saveLocationPyramid(lp);
	}

	@Override
	public List<JobRole> getJobRoleList() throws MSSPException {
		
		return solutionLeverDAO.getJobRoleList();
	}

	@Override
	public void loadSolutionLevers(SolutionLeverDTO solnLeverDTO, Integer opScopeId)
			throws MSSPException {
		SolutionLever sl = solutionLeverDAO.getSolutionLever(solnLeverDTO.getSolutionId());
		solnLeverDTO.setSolutionLever(sl);
		if(sl != null) {
			logger.info("Loaded SolutionLeverID: "+sl.getSolutionLeverId());	
		}
		
		if(sl != null) {
			solnLeverDTO.setProdLeverList(solutionLeverDAO.getProductivityLeverListByScope(opScopeId,sl.getSolutionLeverId()));
			solnLeverDTO.setLocBreakupList(solutionLeverDAO.getLocationBreakupListByScope(opScopeId,sl.getSolutionLeverId()));
			
			List <LocationPyramid> allPyramidList = solutionLeverDAO.getAllPyramidListByScope(opScopeId, sl.getSolutionLeverId());
			
			setAllLocPyramidToSolLeverDTO(solnLeverDTO,allPyramidList );
			
			
			//solnLeverDTO.setOffLocPyramidList(solutionLeverDAO.getOffshorePyramidListByScope(opScopeId,sl.getSolutionLeverId()));
			
			logger.info("Saved data loaded.\nProdLeverList: "+solnLeverDTO.getProdLeverList()+"\tLocBreakupList: "
				+solnLeverDTO.getLocBreakupList()+"\tLocPyramidList: "+solnLeverDTO.getOnLocPyramidList());	
		}
	}
	
	
	
	private void setAllLocPyramidToSolLeverDTO(SolutionLeverDTO solnLeverDTO,
			List<LocationPyramid> allPyramidList) {
		// TODO Auto-generated method stub
		
		List<LocationPyramid> onlocalList = new ArrayList<LocationPyramid>();
		List<LocationPyramid> ongsciList = new ArrayList<LocationPyramid>();
		List<LocationPyramid> on3ppList = new ArrayList<LocationPyramid>();
		List<LocationPyramid> nearList = new ArrayList<LocationPyramid>();
		List<LocationPyramid> offList = new ArrayList<LocationPyramid>();
		
		
		for(LocationPyramid lp: allPyramidList ){
			
			if(MSSPConstants.LOCATION_ONSHORE.equalsIgnoreCase(lp.getLocation())){
				if(MSSPConstants.SUBLOC_LOCAL.equalsIgnoreCase(lp.getSubLocation())){
					onlocalList.add(lp);
				}else if(MSSPConstants.SUBLOC_GSCI.equalsIgnoreCase(lp.getSubLocation())){
					ongsciList.add(lp);
				}else if(MSSPConstants.SUBLOC_3PP.equalsIgnoreCase(lp.getSubLocation())){
					on3ppList.add(lp);
				}
					
			}else if(MSSPConstants.LOCATION_NEARSHORE.equalsIgnoreCase(lp.getLocation())){
					nearList.add(lp);
			}else if(MSSPConstants.LOCATION_OFFSHORE.equalsIgnoreCase(lp.getLocation())){
					offList.add(lp);
			}
		}
		
		
		solnLeverDTO.setOnLocalLocPyramidList(onlocalList);
		solnLeverDTO.setOnGSCiLocPyramidList(ongsciList);
		solnLeverDTO.setOn3PPLocPyramidList(on3ppList);
		solnLeverDTO.setNearLocPyramidList(nearList);
		solnLeverDTO.setOffLocPyramidList(offList);
	}

	@Override
	public Map<Long, Integer> createScopePLCountMap(Integer oppId,
			Integer solnId) throws MSSPException {
		Map<Long,Integer> scopePLCountMap = new HashMap<Long, Integer>();
		for(OpportunityScope os: apaDAO.getOppourtunityScopeList(oppId, solnId)) {
			scopePLCountMap.put(Long.valueOf(String.valueOf(os.getOpportunityScopeId())), 1);
		}
		logger.info("scopePLCountMap before DB call: "+scopePLCountMap);
		Map<Long,Integer>scopePLCountMapDB = solutionLeverDAO.createScopePLCountMap(solnId);
		logger.info("scopePLCountMap from DB call: "+scopePLCountMapDB);
		
		scopePLCountMap.putAll(scopePLCountMapDB);
		logger.info("scopePLCountMap after DB call: "+scopePLCountMap);
		
		return scopePLCountMap;
	}

	@Override
	public Integer getLBRowCount(Integer solnId) throws MSSPException {
		
		Integer lbrc = solutionLeverDAO.getLBRowCount(solnId);
		logger.info("LocationBreakup row count from DB call: "+lbrc);
		return (lbrc == 0 ? 1 : lbrc);
	}

	@Override
	public Map<Long, Integer> createScopePyramidCountMap(Integer oppId,
			Integer solnId) throws MSSPException {
		Map<Long,Integer> scopePyrCountMap = new HashMap<Long, Integer>();
		for(OpportunityScope os: apaDAO.getOppourtunityScopeList(oppId, solnId)) {
			scopePyrCountMap.put(Long.valueOf(String.valueOf(os.getOpportunityScopeId())), 1);
		}
		logger.info("onshore scopePyrCountMap before DB call: "+scopePyrCountMap);
		Map<Long,Integer>scopePyrCountMapDB = solutionLeverDAO.createScopePyramidCountMap(solnId);
		logger.info("onshore scopePyrCountMap from DB call: "+scopePyrCountMapDB);
		
		scopePyrCountMap.putAll(scopePyrCountMapDB);
		logger.info("onshore scopePyrCountMap after DB call: "+scopePyrCountMap);
		
		return scopePyrCountMap;
	}

	@Override
	public Map<Long, Integer> createScopeOffPyrCountMap(Integer oppId,
			Integer solnId) throws MSSPException {
		Map<Long,Integer> scopePyrCountMap = new HashMap<Long, Integer>();
		for(OpportunityScope os: apaDAO.getOppourtunityScopeList(oppId, solnId)) {
			scopePyrCountMap.put(Long.valueOf(String.valueOf(os.getOpportunityScopeId())), 1);
		}
		logger.info("offshore scopePyrCountMap before DB call: "+scopePyrCountMap);
		Map<Long,Integer>scopePyrCountMapDB = solutionLeverDAO.createScopeOffPyrCountMap(solnId);
		logger.info("offshore scopePyrCountMap from DB call: "+scopePyrCountMapDB);
		
		scopePyrCountMap.putAll(scopePyrCountMapDB);
		logger.info("offshore scopePyrCountMap after DB call: "+scopePyrCountMap);
		
		return scopePyrCountMap;
	}

	
	
	

	@Override
	public String loadDefaultValuesByServiceElementId(Integer id) {
		// TODO Auto-generated method stub
		String defaultStr="";
		List<LocationBreakupDefault>  list = apaDAO.loadDefaultValuesByServiceElementId(id);
		
		if(list.size()>0){
			
			LocationBreakupDefault lbd = list.get(0);
			
			defaultStr = lbd.getOnshoreLocalPC() +";" +lbd.getOnshoreGSCPC() +";" +lbd.getOnshore3PPPC() +";" +
						lbd.getNearshoreGSCPC() +";" + lbd.getOffshoreGSCPC();
		}
		
		return defaultStr;
	}
	
	@Override
	public LocationBreakupDefault loadDefaultValuesByServiceElementId(Integer id) {
		// TODO Auto-generated method stub
		String defaultStr="";
		List<LocationBreakupDefault>  list = apaDAO.loadDefaultValuesByServiceElementId(id);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
		
	}*/

}
