package com.ericsson.mssp.solution.service;

import java.util.Date;
import java.util.List;

import com.ericsson.mssp.common.dto.FTESummaryDTO;
import com.ericsson.mssp.common.dto.LocationBreakupDTO;
import com.ericsson.mssp.common.dto.LocationPyramidDTO;
import com.ericsson.mssp.common.dto.ProductEstimationBaseEffortForSolutionDTO;
import com.ericsson.mssp.common.dto.ProductivityLeverDTO;
import com.ericsson.mssp.common.dto.ServiceElementJobRoleStagesDTO;
import com.ericsson.mssp.common.dto.SolutionLeverDTO;
import com.ericsson.mssp.common.dto.SolutionUtilPerYearDTO;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.ServiceElementJobRoleStages;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;

public interface SolutionLeverService {
	
	public Integer getServiceElementIdByOppScopeId(Integer oppScopeId) throws MSSPException;
	
	public List<JobRoleStages> getJobRoleByServiceElementId(Integer serviceElementId,
			boolean ccmFLag) throws MSSPException;
	public OpportunityDetail getOpportunityDetail(Integer opportunityId) throws MSSPException;
	
	//For solution util per year
	public void saveSolutionUtilPerYear(SolutionUtilPerYearDTO solutionUtilPerYearDTO,Integer solutionId) throws DAOException;
	public SolutionUtilPerYearDTO getSolutionUtilBySolutionId(Integer solutionId);
	// End solution util per year
	
	public void saveProductivityLever(SolutionLeverDTO solutionLeverDTO, Integer opportunityId)throws MSSPException, Exception;

	public List<ProductivityLeverDTO> getProductivityLeverList(
			Integer solutionId)throws DAOException;
	
	//For Location Pyramid
	public void saveLocationBreakup(LocationBreakupDTO locationBreakupDTO,Integer solutionId,Integer oppScopeId )throws DAOException;
	public List<LocationBreakupDTO> loadLocationBreakupByOppScopeID(Integer oppScopeId);
	public  List<Integer> getYearList(OpportunityDetail oppDetail);
	//End for Location Pyramid
	
	//For Pyramid Popup
	
	public List<OpportunityScope> getOppourtunityScopeList(Integer oppId,
			Integer solnId) throws DAOException;
	public List<ServiceElementJobRoleStages> getJobRoleSerEleByServiceElementId(Integer serviceElementId,
			boolean ccmFLag)throws MSSPException;
	public List<ServiceElementJobRoleStagesDTO> getJobRoleSerEleDTOListByServiceElementId(Integer serviceElementId,
			boolean ccmFLag) throws DAOException;
	public List <LocationPyramidDTO> loadLocationPyramid( Integer solutionId, Integer oppScopeId,String location ,String subLocation);
	//save Location Pyramid
	
	public void saveLocationPyramid(LocationPyramidDTO locationPyramidDTO,Integer solutionId,Integer oppScopeId )throws DAOException;
	
	public ProductEstimationBaseEffortForSolutionDTO getBaseEffort(Integer solutionId,Integer oppScopeId);
	//

	public List<FTESummaryDTO> getMonthwiseFTEList(Integer solutionId,
			Date startDate, Date endDate)throws MSSPException;

	public String updateFTESummary(Long fteSummaryId, Float fteValue)throws MSSPException;

	public String getSolutionLeverApporach(Integer solutionId) throws Exception;
	
	/*public Date getSteadyStateStartDate(Integer opportunityId) throws MSSPException;
	public Date getSteadyStateEndDate(Integer opportunityId) throws MSSPException;
	public Float getStartupFTE(Integer solutionId, Integer oppScopeId) throws MSSPException;
	public void loadSolutionLevers(SolutionLeverDTO solnLeverDTO, Integer oppScopeId) throws MSSPException;
	public void saveSolutionLevers(SolutionLeverDTO solnLeverDTO) throws MSSPException;
	public Map<Long,Integer> createScopePLCountMap(Integer oppId, Integer solnId) throws MSSPException;
	public Integer getLBRowCount(Integer solnId) throws MSSPException;
	public Map<Long,Integer> createScopePyramidCountMap(Integer oppId, Integer solnId) throws MSSPException;
	public Map<Long,Integer> createScopeOffPyrCountMap(Integer oppId, Integer solnId) throws MSSPException;
	public List<JobRole> getJobRoleList() throws MSSPException;
	
	
	
	
	//public String loadDefaultValuesByServiceElementId(Integer id);
	public LocationBreakupDefault loadDefaultValuesByServiceElementId(Integer id);
	*/
	
}
