package com.ericsson.mssp.volumetric.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.ericsson.mssp.common.dto.AppDevTicketDistributionDTO;
import com.ericsson.mssp.common.dto.OpportunityComponentDTO;
import com.ericsson.mssp.common.dto.ProductEstimationGenericInput;
import com.ericsson.mssp.common.dto.ProductVolumetricDTO;
import com.ericsson.mssp.common.dto.SaveProductEstimationForSolutionWrapperDTO;
import com.ericsson.mssp.common.dto.ServiceScopeAppMainDataBean;
import com.ericsson.mssp.common.entity.ProductEstimationAuxiliaryParamsForSolution;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.volumetric.forms.ProductAppTestingResponse;

public interface ProductVolumetricService {

	/**
	 * Fetch Estimation Activities for a Service Scope
	 * @param componentId
	 * @param solutionId TODO
	 * @return
	 * @throws MSSPException
	 */
	public List<ProductVolumetricDTO> getProductEstimationActivities(
			Integer componentId, Integer solutionId) throws MSSPException;
	
	/**
	 * Saves Product Estimation Values for a particular Solution
	 * @param editedObject
	 * @return String - success message
	 * @throws MSSPException
	 */
	public String saveProductEstimationForSolution(
			SaveProductEstimationForSolutionWrapperDTO editedObject) throws MSSPException;
	
	/**
	 * Retrieve the Auxiliary Parameters for Product Estimation
	 * @param paramMapForSolution TODO
	 * @return List<ProductEstimationAuxiliaryParamsDTO>
	 * @throws MSSPException
	 */
	public LinkedHashMap<String, LinkedHashMap<String, Integer>> getProductEstimationAuxiliaryParams(LinkedHashMap<Integer, String> paramMapForSolution) throws MSSPException;
	
	/**
	 * Retrieves Auxiliary parameter values for a particular solution
	 * @param solutionId
	 * @return List<ProductEstimationAuxiliaryParamsForSolution>
	 * @throws MSSPException
	 */
	public List<ProductEstimationAuxiliaryParamsForSolution> getProductEstimationAuxiliaryParamsForSolution(Integer solutionId) throws MSSPException;
	
	/**
	 * Wrapper to fetch Auxiliary Parameters drop-downs
	 * including the ones selected for a particular solution
	 * @param solutionId
	 * @return LinkedHashMap<String, LinkedHashMap<String, Integer>>
	 * @throws MSSPException
	 */
	public LinkedHashMap<String, LinkedHashMap<String, Integer>> getProductEstimationAuxiliaryParamsWrapper(Integer solutionId) throws MSSPException;

	/**
	 * Returns Components that were selected for an opportunity
	 * @param opportunityId
	 * @return List<OpportunityComponentDTO>
	 */
	public List<OpportunityComponentDTO> getComponentForOpportunity(Integer opportunityId);
	
	/**
	 * Fetches percentage distribution of Application Development service elements
	 * @param inputBean
	 * @return List<TicketDistributionDTO>
	 * @throws MSSPException
	 */
	public List<AppDevTicketDistributionDTO> loadPercentageDistribution(ProductEstimationGenericInput inputBean) throws MSSPException;
	
	/**
	 * Persists distribution percentages against opportunity scopes
	 * @param distributionArray
	 * @param solutionId TODO
	 * @throws MSSPException
	 */
	public void savePercentDistribution(AppDevTicketDistributionDTO[] distributionArray, Integer solutionId) throws MSSPException;
	public void saveProductEstimationBaseEffortForSolution(List<ServiceScopeAppMainDataBean> dtoList) throws MSSPException;
	public List<ServiceScopeAppMainDataBean> getProductEstimationBaseEffortForSolution(Integer solutionId, String serviceFunction) throws MSSPException;
	

	/**
	 * Retrieves initial parameters for Application Testing for products
	 * @param solutionId
	 * @return ProductAppTestingResponse
	 * @throws MSSPException
	 */
	public ProductAppTestingResponse initProductAppTesting(Integer solutionId) throws MSSPException;
	
	/**
	 * Returns the total Application Development effort for a solution
	 * @param solutionId
	 * @return Double
	 * @throws MSSPException
	 */
	public Double getTotalProductEstimationForSolution(Integer solutionId) throws MSSPException;
}
