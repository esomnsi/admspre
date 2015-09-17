package com.ericsson.mssp.volumetric.dao;

import java.util.List;

import com.ericsson.mssp.common.dao.IBaseDAO;
import com.ericsson.mssp.common.dto.AppDevTicketDistributionDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.ProductEstimationGenericInput;
import com.ericsson.mssp.common.dto.ProductVolumetricDTO;
import com.ericsson.mssp.common.entity.OpportunityComponent;
import com.ericsson.mssp.common.entity.ProductEstimationBaseEffortForSolution;
import com.ericsson.mssp.common.entity.ProductEstimationAuxiliaryParamsForSolution;
import com.ericsson.mssp.common.entity.ProductEstimationForSolution;
import com.ericsson.mssp.common.entity.TicketDistribution;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;

public interface ProductVolumetricDAO extends IBaseDAO {

	/**
	 * Fetch Estimation Activities for a Service Scope
	 * 
	 * @param componentId
	 * @param solutionId
	 * @return List<ProductVolumetricDTO>
	 * @throws DAOException
	 */
	public List<ProductVolumetricDTO> getProductEstimationActivities(
			Integer componentId, Integer solutionId) throws DAOException;

	/**
	 * Saves Product Estimation Values for a particular Solution
	 * 
	 * @param persistentList
	 * @throws DAOException
	 */
	public void saveProductEstimationForSolution(
			List<ProductEstimationForSolution> persistentList)
			throws DAOException;

	/**
	 * Saves Auxiliary parameter values for a particular solution
	 * 
	 * @param persistentList
	 * @throws DAOException
	 */
	public void saveProductEstimationAuxiliaryParamsForSolution(
			List<ProductEstimationAuxiliaryParamsForSolution> persistentList)
			throws DAOException;

	/**
	 * Retrieves Auxiliary parameter values for a particular solution
	 * 
	 * @param solutionId
	 * @return List<ProductEstimationAuxiliaryParamsForSolution>
	 * @throws MSSPException
	 */
	public List<ProductEstimationAuxiliaryParamsForSolution> getProductEstimationAuxiliaryParamsForSolution(
			Integer solutionId) throws DAOException;

	/**
	 * Saves Base total values against selected service scopes for an opportunity
	 * 
	 * @param persistentList
	 * @throws DAOException
	 */
	public void saveProductEstimationBaseEffortForSolution(
			List<ProductEstimationBaseEffortForSolution> persistentList)
			throws DAOException;
	
	/**
	 * Fetches Components that were selected for an opportunity
	 * @param opportunityId
	 * @return List<OpportunityComponent>
	 */
	public List<OpportunityComponent> getComponentForOpportunity(Integer opportunityId);
	
	/**
	 * Fetches percentage distribution of Application Development service elements
	 * @param inputBean
	 * @return List<TicketDistributionDTO>
	 * @throws MSSPException
	 */
	public List<AppDevTicketDistributionDTO> loadPercentageDistribution(ProductEstimationGenericInput inputBean) throws MSSPException;
	
	/**
	 * Persists distribution percentages against opportunity scopes
	 * @param persistentList
	 * @throws MSSPException
	 */
	public void savePercentDistribution(List<TicketDistribution> persistentList) throws MSSPException;
	public List<ProductEstimationBaseEffortForSolution> getProductEstimationBaseEffortForSolution(Integer solutionId, String serviceFunction) throws MSSPException;
	public void saveProductEstimationBaseEffortForSolutionNew(	List<ProductEstimationBaseEffortForSolution> persistentList) throws DAOException;
	

	/**
	 * Returns the total effort for a solution and Service Functions
	 * @param solutionId
	 * @param List<String> serviceFunctionCode
	 * @return Double
	 * @throws DAOException
	 */
	public Double getTotalEffortForSolution(Integer solutionId, List<String> serviceFunctionCode) throws DAOException;
	
	/**
	 * Returns the total Application Development effort for a solution
	 * @param solutionId
	 * @return Double
	 * @throws DAOException
	 */
	public Double getTotalProductEstimationForSolution(Integer solutionId) throws DAOException;
	
}
