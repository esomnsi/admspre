package com.ericsson.mssp.solution.dao;

import java.util.Date;
import java.util.List;

import com.ericsson.mssp.common.dao.IBaseDAO;
import com.ericsson.mssp.common.entity.FTESummary;
import com.ericsson.mssp.common.entity.LocationBreakup;
import com.ericsson.mssp.common.entity.LocationPyramid;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.ProductEstimationBaseEffortForSolution;
import com.ericsson.mssp.common.entity.ProductivityLever;
import com.ericsson.mssp.common.entity.SolutionLever;
import com.ericsson.mssp.common.entity.SolutionUtilPerYear;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;

public interface ISolutionLeverDAO extends IBaseDAO {
	
	// For soultion util per year
	public void saveSolutionUtilPerYear(SolutionUtilPerYear solutionUtilPerYear) throws DAOException;
	public SolutionUtilPerYear getSolutionUtilBySolutionId(Integer solutionId);
	
	public OpportunityDetail getOpportunityDetail(Integer opportunityId) throws DAOException;
	
	public void saveProductivityLever(ProductivityLever pl) throws DAOException;
	
	public void saveSolutionLever(SolutionLever sl) throws DAOException;
	
	public List<ProductivityLever> getProductivityLeverList(Integer solutionId) throws DAOException;
	
	
	//
	
	//For Location Pyramid
	public void saveLocationBreakup(LocationBreakup locationBreakup);
	public SolutionLever getSolutionLever(Integer solnId) throws DAOException;
	public List<LocationBreakup> loadLocationBreakupByOppScopeID(Integer oppScopeId);

	public void saveLocationPyramid(LocationPyramid locationPyramid);
	public 	List<LocationPyramid> loadLocationPyramid(Integer solutionId,Integer oppScopeId,String location ,String subLocation);
	
	public List<LocationBreakup> getAllLocationBreakupBySolLeverId(
			Integer solLeverId);
	
	public void saveProductivityLeverMonthwise(List<FTESummary> fteSummaryList) throws Exception;
	public List<FTESummary> getMonthwiseFTEList(Integer solutionId,
			Date startDate, Date endDate)throws  MSSPException;
	
	public ProductEstimationBaseEffortForSolution getBaseEffort(Integer solutionId,Integer oppScopeId);
	public String updateFTESummary(Long fteSummaryId, Float fteValue) throws MSSPException;
	
	
	// End for Location Pyramid
	/*
	public Date getSteadyStateStartDate(Integer opportunityId) throws DAOException;
	public Date getSteadyStateEndDate(Integer opportunityId) throws DAOException;	
	public Float getStartupFTE(Integer solutionId, Integer oppScopeId) throws DAOException;
	public SolutionLever getSolutionLever(Integer solnId) throws DAOException;
	public AutoPopulatingList<ProductivityLever> getProductivityLeverList(Integer solnId) throws DAOException;
	public AutoPopulatingList<LocationBreakup> getLocationBreakupList(Integer solnId) throws DAOException;
	public AutoPopulatingList<LocationPyramid> getLocationPyramidList(Integer solnId) throws DAOException;
	public AutoPopulatingList<LocationPyramid> getOnshorePyramidList(Integer solnId) throws DAOException;
	public AutoPopulatingList<LocationPyramid> getOffshorePyramidList(Integer solnId) throws DAOException;
	public AutoPopulatingList<ProductivityLever> getProductivityLeverListByScope(Integer opScopeId, Integer solnId) throws DAOException;
	public AutoPopulatingList<LocationBreakup> getLocationBreakupListByScope(Integer opScopeId, Integer solnId) throws DAOException;
	public AutoPopulatingList<LocationPyramid> getLocationPyramidListByScope(Integer opScopeId, Integer solnId) throws DAOException;
	public AutoPopulatingList<LocationPyramid> getOnshorePyramidListByScope(Integer opScopeId, Integer solnId) throws DAOException;
	public AutoPopulatingList<LocationPyramid> getOffshorePyramidListByScope(Integer opScopeId, Integer solnId) throws DAOException;
	
	
	public int deleteSolutionLever(Integer solnId) throws DAOException;
	
	public int deleteProductivityLever(Integer solnId, Integer oppScopeId) throws DAOException;
	public List<JobRole> getJobRoleList() throws DAOException;
	public void saveLocationBreakup(LocationBreakup lb) throws DAOException;
	public int deleteLocationBreakup(Integer solnId,Integer oppScopeId) throws DAOException;
	public void saveLocationPyramid(LocationPyramid lp) throws DAOException;
	public int deleteLocationPyramid(Integer solnId,Integer oppScopeId) throws DAOException;
	public Map<Long,Integer> createScopePLCountMap(Integer solnId) throws DAOException;
	public Integer getLBRowCount(Integer solnId) throws DAOException;
	public Map<Long,Integer> createScopePyramidCountMap(Integer solnId) throws DAOException;
	public Map<Long,Integer> createScopeOffPyrCountMap(Integer solnId) throws DAOException;
	
	AutoPopulatingList<LocationPyramid> getAllPyramidListByScope(
			Integer opScopeId, Integer solnleverId) throws DAOException;*/

}
