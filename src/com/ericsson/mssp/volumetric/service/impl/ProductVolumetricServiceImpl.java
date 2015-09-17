package com.ericsson.mssp.volumetric.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.AppDevTicketDistributionDTO;
import com.ericsson.mssp.common.dto.OpportunityComponentDTO;
import com.ericsson.mssp.common.dto.ProductEstimationBaseEffortForSolutionDTO;
import com.ericsson.mssp.common.dto.ProductEstimationGenericInput;
import com.ericsson.mssp.common.dto.ProductVolumetricDTO;
import com.ericsson.mssp.common.dto.SaveProductEstimationForSolutionDTO;
import com.ericsson.mssp.common.dto.SaveProductEstimationForSolutionWrapperDTO;
import com.ericsson.mssp.common.dto.ServiceScopeAppMainDataBean;
import com.ericsson.mssp.common.entity.OpportunityComponent;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.ProductActivitiesComplexityAssumptions;
import com.ericsson.mssp.common.entity.ProductEstimationAuxiliaryParams;
import com.ericsson.mssp.common.entity.ProductEstimationAuxiliaryParamsForSolution;
import com.ericsson.mssp.common.entity.ProductEstimationBaseEffortForSolution;
import com.ericsson.mssp.common.entity.ProductEstimationForSolution;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.TicketDistribution;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.service.impl.ComplexityAdjusterServiceImpl;
import com.ericsson.mssp.volumetric.dao.ProductVolumetricDAO;
import com.ericsson.mssp.volumetric.forms.ProductAppTestingResponse;
import com.ericsson.mssp.volumetric.service.ProductVolumetricService;

@Service
public class ProductVolumetricServiceImpl implements ProductVolumetricService,
		MSSPConstants {

	final static Logger logger = LoggerFactory
			.getLogger(ComplexityAdjusterServiceImpl.class);

	@Autowired
	ProductVolumetricDAO productVolumetricDao;

	@Override
	public List<ProductVolumetricDTO> getProductEstimationActivities(
			Integer componentId, Integer solutionId) throws MSSPException {

		List<ProductVolumetricDTO> activityList = null;

		try {
			activityList = productVolumetricDao.getProductEstimationActivities(
					componentId, solutionId);

		} catch (Exception e) {
			logger.error(e.getMessage() + " |  " + e.getCause());
			throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		}
		return activityList;
	}

	@Override
	public String saveProductEstimationForSolution(
			SaveProductEstimationForSolutionWrapperDTO editedObject)
			throws MSSPException {
		List<ProductEstimationForSolution> persistentList = new ArrayList<>();
		ProductEstimationForSolution eachPersistentObject = null;
		ProductActivitiesComplexityAssumptions tempComplexityAssumption = null;
		Solution tempSolution = null;
		String[] ignoreProperties = null;
		String successMessage = SAVE_SUCCESS_MESSAGE;
		SaveProductEstimationForSolutionDTO[] persistentArray = null;
		Integer solutionId = null;
		HashMap<String, String> auxiliaryParamMap = null;
		List<ProductEstimationAuxiliaryParamsForSolution> auxiliaryParamList = null;
		ProductEstimationAuxiliaryParamsForSolution tempObject = null;
		ProductEstimationAuxiliaryParams tempParamObject = null;

		try {
			if (editedObject != null) {
				persistentArray = editedObject.getPersistentArray();
				solutionId = editedObject.getSolutionId();
				auxiliaryParamMap = editedObject.getAuxiliaryParamMap();

				if (persistentArray != null && persistentArray.length > 0) {

					ignoreProperties = new String[] { "serialVersionUID",
							"solution",
							"productActivitiesComplexityAssumptions" };

					tempSolution = new Solution();
					tempSolution.setSolutionId(solutionId);

					for (SaveProductEstimationForSolutionDTO eachDtoObject : persistentArray) {
						eachPersistentObject = new ProductEstimationForSolution();

						// Copying common properties
						BeanUtils.copyProperties(eachDtoObject,
								eachPersistentObject, ignoreProperties);

						// Copying modified properties
						tempComplexityAssumption = new ProductActivitiesComplexityAssumptions();
						tempComplexityAssumption
								.setProductActivitiesComplexityAssumptionsID(eachDtoObject
										.getId());
						eachPersistentObject
								.setProductActivitiesComplexityAssumptions(tempComplexityAssumption);

						eachPersistentObject.setSolution(tempSolution);

						persistentList.add(eachPersistentObject);
					}
					productVolumetricDao
							.saveProductEstimationForSolution(persistentList);
				}

				if (auxiliaryParamMap != null && auxiliaryParamMap.size() > 0) {
					auxiliaryParamList = new ArrayList<>();
					String[] paramValue = null;
					for (Map.Entry<String, String> entry : auxiliaryParamMap
							.entrySet()) {
						paramValue = entry.getValue().split(MSSPConstants.CONCAT_DOUBLE_UNDERSCORE);
						tempObject = new ProductEstimationAuxiliaryParamsForSolution();

						tempSolution = new Solution();
						tempSolution.setSolutionId(solutionId);
						tempObject.setSolution(tempSolution);

						tempParamObject = new ProductEstimationAuxiliaryParams();
						tempParamObject
								.setProductEstimationAuxiliaryParamsID(Integer
										.parseInt(paramValue[0]));
						tempObject
								.setProductEstimationAuxiliaryParams(tempParamObject);
						if(paramValue.length>=2 && paramValue[1]!=null && paramValue[1].length()>0){
							tempObject.setProductEstimationAuxiliaryParamsForSolutionID(Integer.parseInt(paramValue[1]));
						}
						tempObject.setParamTypeCode(entry.getKey());

						auxiliaryParamList.add(tempObject);
					}
					productVolumetricDao
							.saveProductEstimationAuxiliaryParamsForSolution(auxiliaryParamList);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + " |  " + e.getCause());
			successMessage = (e.getMessage() + " |  " + e.getCause());
			throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		}
		return successMessage;
	}

	@Override
	public LinkedHashMap<String, LinkedHashMap<String, Integer>> getProductEstimationAuxiliaryParams(
			LinkedHashMap<Integer, String> paramMapForSolution)
			throws MSSPException {
		LinkedHashMap<String, LinkedHashMap<String, Integer>> paramMap = new LinkedHashMap<>();
		LinkedHashMap<String, Integer> paramValuesMap = null;
		List<ProductEstimationAuxiliaryParams> parameterListFromPersistentStorage = null;
		String tempKey = "";
		String tempSelectedStr = "";

		try {
			parameterListFromPersistentStorage = productVolumetricDao
					.getObjects(ProductEstimationAuxiliaryParams.class);

			for (ProductEstimationAuxiliaryParams eachParam : parameterListFromPersistentStorage) {
				tempKey = eachParam.getParamTypeCode()
						+ CONCAT_DOUBLE_UNDERSCORE + eachParam.getParamType();
				if (paramMap.get(tempKey) == null) {
					paramValuesMap = new LinkedHashMap<>();
				} else {
					paramValuesMap = paramMap.get(tempKey);
				}

				if (paramValuesMap.get(eachParam.getParamName()) == null) {
					tempSelectedStr = paramMapForSolution.get(eachParam
							.getProductEstimationAuxiliaryParamsID());
					paramValuesMap.put((eachParam.getParamName()
							+ CONCAT_DOUBLE_UNDERSCORE
							+ (tempSelectedStr == null ? "notSelected"
									: tempSelectedStr)
							+ CONCAT_DOUBLE_UNDERSCORE + eachParam
							.getParamValue()), eachParam
							.getProductEstimationAuxiliaryParamsID());
				}

				paramMap.put(tempKey, paramValuesMap);
			}

		} catch (Exception e) {
			logger.error(e.getMessage() + " |  " + e.getCause());
			throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		}

		return paramMap;
	}

	@Override
	public List<ProductEstimationAuxiliaryParamsForSolution> getProductEstimationAuxiliaryParamsForSolution(
			Integer solutionId) throws MSSPException {
		List<ProductEstimationAuxiliaryParamsForSolution> objectList = productVolumetricDao
				.getProductEstimationAuxiliaryParamsForSolution(solutionId);
		return objectList;
	}

	@Override
	public LinkedHashMap<String, LinkedHashMap<String, Integer>> getProductEstimationAuxiliaryParamsWrapper(
			Integer solutionId) throws MSSPException {
		String markSelected = "selected";
		LinkedHashMap<Integer, String> paramMapForSolution = new LinkedHashMap<>();
		List<ProductEstimationAuxiliaryParamsForSolution> objectList = getProductEstimationAuxiliaryParamsForSolution(solutionId);

		for (ProductEstimationAuxiliaryParamsForSolution aParam : objectList) {
			paramMapForSolution.put(aParam
					.getProductEstimationAuxiliaryParams()
					.getProductEstimationAuxiliaryParamsID(), (markSelected+MSSPConstants.CONCAT_DOUBLE_UNDERSCORE+aParam.getProductEstimationAuxiliaryParamsForSolutionID()));
		}

		return getProductEstimationAuxiliaryParams(paramMapForSolution);
	}

	@Override
	public List<OpportunityComponentDTO> getComponentForOpportunity(
			Integer opportunityId) {
		List<OpportunityComponent> componentList = productVolumetricDao
				.getComponentForOpportunity(opportunityId);
		;
		List<OpportunityComponentDTO> componentDTOList = new ArrayList<OpportunityComponentDTO>();
		OpportunityComponentDTO opportunityComponentDTO = null;

		for (OpportunityComponent eachElement : componentList) {
			opportunityComponentDTO = new OpportunityComponentDTO();
			opportunityComponentDTO.setOpportunityComponentID(eachElement
					.getOpportunityComponentID());
			opportunityComponentDTO
					.setOpportunity(eachElement.getOpportunity());
			opportunityComponentDTO.setComponent(eachElement.getComponent());
			componentDTOList.add(opportunityComponentDTO);
		}

		return componentDTOList;
	}

	@Override
	public List<AppDevTicketDistributionDTO> loadPercentageDistribution(
			ProductEstimationGenericInput inputBean) throws MSSPException {
		return productVolumetricDao.loadPercentageDistribution(inputBean);
	}

	@Override
	public void savePercentDistribution(
			AppDevTicketDistributionDTO[] distributionArray, Integer solutionId) throws MSSPException {
		List<TicketDistribution> persistentList = new ArrayList<>();
		List<ProductEstimationBaseEffortForSolution> baseEffortList = new ArrayList<>();
		ProductEstimationBaseEffortForSolution tempEffortObject = null;
		TicketDistribution tempObject = null;
		OpportunityScope oppScope = null;
		Solution tempSol = null;

		if (distributionArray != null && distributionArray.length > 0)
			for (AppDevTicketDistributionDTO eachObject : distributionArray) {
				if (MSSPConstants.TRUE.equalsIgnoreCase(eachObject
						.getSelected())
						|| MSSPConstants.YES.equalsIgnoreCase(eachObject
								.getSelected())) {
					/*Persisting Distribution percentages*/
					tempObject = new TicketDistribution();
					tempObject.setPercentTicketDistribution(eachObject
							.getEditablePercentDistribution());

					oppScope = new OpportunityScope();
					oppScope.setOpportunityScopeId(eachObject.getOpportunityScopeID());
					tempObject.setOpportunityScope(oppScope);

					persistentList.add(tempObject);
					/*End*/
					
					/*Persisting Effort for Service Elements*/
					tempEffortObject = new ProductEstimationBaseEffortForSolution();
					tempEffortObject.setOpportunityScope(oppScope);
					tempSol = new Solution();
					tempSol.setSolutionId(solutionId);
					tempEffortObject.setSolution(tempSol);
					tempEffortObject.setTotalBaseHours(eachObject.getEffortForServiceElement());
					baseEffortList.add(tempEffortObject);
					/*End*/
				}
			}
		productVolumetricDao.savePercentDistribution(persistentList);
		productVolumetricDao.saveProductEstimationBaseEffortForSolutionNew(baseEffortList);
	}

	@Override
	public void saveProductEstimationBaseEffortForSolution(List<ServiceScopeAppMainDataBean> beanList) throws MSSPException {
		List<ProductEstimationBaseEffortForSolution> mylist = new ArrayList<ProductEstimationBaseEffortForSolution>();
		for(ServiceScopeAppMainDataBean bean : beanList){			
			ProductEstimationBaseEffortForSolution entity = new ProductEstimationBaseEffortForSolution();
			//entity.setProductEstimationBaseEffortForSolutionID(dto.getProductEstimationBaseEffortForSolutionID());
			entity.setOpportunityScope(getOpportunityScopeByOppScopeId(bean.getOppScopeId()));
			entity.setSolution(getSolutionBySolutionId(bean.getSolutionId()));			
			entity.setTotalBaseHours(bean.getTotalBaseHours());
			entity.setTotalBaseFTE(bean.getTotalBaseFTE());
			mylist.add(entity);
		}
		productVolumetricDao.saveProductEstimationBaseEffortForSolutionNew(mylist);
	}
	
	private OpportunityScope getOpportunityScopeByOppScopeId(Integer oppScopeId){
		OpportunityScope oppScope = new OpportunityScope();
		oppScope.setOpportunityScopeId(oppScopeId);
		return oppScope;
	}
	
	private Solution getSolutionBySolutionId(Integer solId){
		Solution solution = new Solution();
		solution.setSolutionId(solId);
		return solution;
	}

	@Override
	public List<ServiceScopeAppMainDataBean> getProductEstimationBaseEffortForSolution(	Integer solutionId, String serviceFunction) throws MSSPException {
		List<ServiceScopeAppMainDataBean> beanList = new ArrayList<ServiceScopeAppMainDataBean>();
		List<ProductEstimationBaseEffortForSolution> entityList = productVolumetricDao.getProductEstimationBaseEffortForSolution(solutionId,serviceFunction);
		for(ProductEstimationBaseEffortForSolution entity : entityList){
			ServiceScopeAppMainDataBean bean = new ServiceScopeAppMainDataBean();
			bean.setSolutionId(entity.getSolution().getSolutionId());
			bean.setOppScopeId(entity.getOpportunityScope().getOpportunityScopeId());
			bean.setServScopeName(entity.getOpportunityScope().getServiceScope().getServiceScopeName());
			bean.setTotalBaseFTE(entity.getTotalBaseFTE());
			bean.setTotalBaseHours(entity.getTotalBaseHours());
			beanList.add(bean);
		}
		return beanList;
	}

	@Override
	public ProductAppTestingResponse initProductAppTesting(Integer solutionId)
			throws MSSPException {
		ProductAppTestingResponse result = new ProductAppTestingResponse();
		List<String> serviceFunctionCode = new ArrayList<>();
		serviceFunctionCode.add(MSSPConstants.SERVICE_FUNCTION_APP_DEV);
		serviceFunctionCode.add(MSSPConstants.SERVICE_FUNCTION_APP_MAIN);
		
		result.setTotalEffort(productVolumetricDao.getTotalEffortForSolution(solutionId, serviceFunctionCode));
		
		return result;
	}

	@Override
	public Double getTotalProductEstimationForSolution(Integer solutionId)
			throws MSSPException {
		return productVolumetricDao.getTotalProductEstimationForSolution(solutionId);
	}
}
