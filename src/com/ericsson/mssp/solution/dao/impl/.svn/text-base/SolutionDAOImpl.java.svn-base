/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.dao.impl
 * File name       		    :  SolutionDAOImpl.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Dec 11, 20122012
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Dec 11, 2012  	   |eruvwyn   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.dao.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.dto.CommercialModelDTO;
import com.ericsson.mssp.common.dto.CountryDTO;
import com.ericsson.mssp.common.dto.CustomerDTO;
import com.ericsson.mssp.common.dto.DeliveryModelDTO;
import com.ericsson.mssp.common.dto.DeliveryTypeModelDTO;
import com.ericsson.mssp.common.dto.ExchangeRateDTO;
import com.ericsson.mssp.common.dto.FTEStagingDTO;
import com.ericsson.mssp.common.dto.FteHeadCountAndPercentageDTO;
import com.ericsson.mssp.common.dto.JobRoleDTO;
import com.ericsson.mssp.common.dto.JobRoleStagesDTO;
import com.ericsson.mssp.common.dto.JobStageDTO;
import com.ericsson.mssp.common.dto.LaborCostDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.OpportunityDetailDTO;
import com.ericsson.mssp.common.dto.OpportunityLocationDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.OpportunitySourceDTO;
import com.ericsson.mssp.common.dto.ScopeDefinedByDTO;
import com.ericsson.mssp.common.dto.SearchDTO;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.dto.SolutionADRDTO;
import com.ericsson.mssp.common.dto.SolutionAPAClone;
import com.ericsson.mssp.common.dto.SolutionAPADTO;
import com.ericsson.mssp.common.dto.SolutionApproachDimensionDTO;
import com.ericsson.mssp.common.dto.SolutionDTO;
import com.ericsson.mssp.common.dto.UserAccessDTO;
import com.ericsson.mssp.common.dto.VolumetricsDefinedByDTO;
import com.ericsson.mssp.common.dto.WorkflowTimelineDTO;
import com.ericsson.mssp.common.entity.CommercialModel;
import com.ericsson.mssp.common.entity.Component;
import com.ericsson.mssp.common.entity.Country;
import com.ericsson.mssp.common.entity.Customer;
import com.ericsson.mssp.common.entity.DeliveryModel;
import com.ericsson.mssp.common.entity.DeliveryType;
import com.ericsson.mssp.common.entity.ExchangeRate;
import com.ericsson.mssp.common.entity.FTEStaging;
import com.ericsson.mssp.common.entity.InputVolumetricsDefinedBy;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.JobStage;
import com.ericsson.mssp.common.entity.LaborCost;
import com.ericsson.mssp.common.entity.NonLabourCost;
import com.ericsson.mssp.common.entity.Opportunity;
import com.ericsson.mssp.common.entity.OpportunityComponent;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.OpportunityLocation;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.OpportunitySource;
import com.ericsson.mssp.common.entity.ProductDetails;
import com.ericsson.mssp.common.entity.RateCard;
import com.ericsson.mssp.common.entity.RateCardHistory;
import com.ericsson.mssp.common.entity.ScopeOfServicesDefinedBy;
import com.ericsson.mssp.common.entity.ServiceElement;
import com.ericsson.mssp.common.entity.ServiceScope;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionADR;
import com.ericsson.mssp.common.entity.SolutionAPA;
import com.ericsson.mssp.common.entity.SolutionApproachDimension;
import com.ericsson.mssp.common.entity.SolutionLever;
import com.ericsson.mssp.common.entity.StaffingPlan;
import com.ericsson.mssp.common.entity.Status;
import com.ericsson.mssp.common.entity.User;
import com.ericsson.mssp.common.entity.WorkflowTimeline;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.dao.ISolutionDAO;

/**
 * @author eruvwyn
 * 
 */

@Repository
public class SolutionDAOImpl extends BaseDAOImpl implements ISolutionDAO,
		MSSPConstants {

	public List<ServiceScope> getAllServiceScope() throws DAOException {

		return getObjects(ServiceScope.class);

	}

	@Override
	public Map<Integer, String> loadAllSelectedOppSSIdServiceScopes(
			Integer opportunityId) throws DAOException {

		@SuppressWarnings("unchecked")
		List<OpportunityScope> opportunityScopeList = getHibernateTemplate()
				.find(" from OpportunityScope op where opportunityID="
						+ opportunityId + "order by op.serviceScope.serviceScopeId ");

		Map<Integer, String> oppScopeIDSSMap = new HashMap<Integer, String>();

		for (OpportunityScope oppScope : opportunityScopeList) {
			oppScopeIDSSMap.put(oppScope.getOpportunityScopeId(), oppScope
					.getServiceScope().getServiceScopeName());
		}
		return oppScopeIDSSMap;

	}

	@Override
	public Integer saveObjectReturnId(Object o) {
		return (Integer) getHibernateTemplate().save(o);
	}

	public Long saveObjectReturnLongId(Object o) {
		return (Long) getHibernateTemplate().save(o);
	}

	public List<SolutionApproachDimensionDTO> getDimentionListBySolutionId(
			Integer solutionId) throws MSSPException {

		List<SolutionApproachDimension> solAppDimList = getHibernateTemplate()
				.find(" from SolutionApproachDimension where solutionID="
						+ solutionId);
		List<SolutionApproachDimensionDTO> solDimDTOList = new ArrayList();
		for (SolutionApproachDimension solDim : solAppDimList) {
			solDimDTOList.add(setSADEntityIntoSADDTO(solDim));
		}

		return solDimDTOList;
	}

	private SolutionApproachDimensionDTO setSADEntityIntoSADDTO(
			SolutionApproachDimension solDim) {

		SolutionApproachDimensionDTO solDimDTO = new SolutionApproachDimensionDTO();

		solDimDTO.setSolutionApproachDimensionId(solDim
				.getSolutionApproachDimensionId());

		solDimDTO.setDimensionAttributeName(solDim.getDimensionAttributeName());
		solDimDTO.setDimensionName(solDim.getDimensionName());
		solDimDTO.setSolutionApproachDimensionId(solDim
				.getSolutionApproachDimensionId());

		return solDimDTO;
	}

	public List<ServiceScopeDTO> loadAllSelectedServiceScopes(
			Integer opportunityId) throws DAOException {

		List<OpportunityScope> opportunityScopeList = getHibernateTemplate()
				.find(" from OpportunityScope where opportunityID="
						+ opportunityId + "order by ServiceScopeID");

		List<ServiceScopeDTO> serviceScopeDTOList = new ArrayList<ServiceScopeDTO>();

		for (OpportunityScope oppscope : opportunityScopeList) {
			serviceScopeDTOList.add(setSSEntityIntoSSDTO(oppscope
					.getServiceScope()));
		}
		return serviceScopeDTOList;

	}

	public List<OpportunityScopeDTO> loadAllOpportunityScopesByOppotunityId(
			Integer opportunityId) throws DAOException {

		List<OpportunityScope> opportunityScopeList = getHibernateTemplate()
				.find(" from OpportunityScope where opportunityID="
						+ opportunityId + "order by ServiceScopeID");

		List<OpportunityScopeDTO> serviceScopeDTOList = new ArrayList<OpportunityScopeDTO>();
		OpportunityScopeDTO opportunityScopeDTO;
		for (OpportunityScope oppscope : opportunityScopeList) {
			opportunityScopeDTO = new OpportunityScopeDTO();
			opportunityScopeDTO.setOpportunityScopeId(oppscope
					.getOpportunityScopeId());
			opportunityScopeDTO
					.setServiceScopeDTO(setSSEntityIntoSSDTO(oppscope
							.getServiceScope()));
			serviceScopeDTOList.add(opportunityScopeDTO);
		}
		return serviceScopeDTOList;

	}

	private ServiceScopeDTO setSSEntityIntoSSDTO(ServiceScope serviceScope) {
		// TODO Auto-generated method stub
		ServiceScopeDTO serviceScopeDTO = new ServiceScopeDTO();

		serviceScopeDTO.setServiceScopeId(serviceScope.getServiceScopeId());
		serviceScopeDTO.setServiceScopeCategory(serviceScope
				.getServiceScopeCategory());
		serviceScopeDTO.setServiceScopeName(serviceScope.getServiceScopeName());
		serviceScopeDTO.setServiceElementId(serviceScope.getServiceElement()
				.getServiceElementID());
		serviceScopeDTO.setServiceElementName(serviceScope.getServiceElement()
				.getServiceElementName());

		return serviceScopeDTO;
	}

	/**
	 * 
	 * Description : Saving opportunity scopes and Solution details Method Name
	 * : saveDefineScopeAndSolution Input& Output:
	 * 
	 * @param solutionDTO
	 * @param oppId
	 * @return
	 */

	public Integer saveSolutionDetail(SolutionDTO solutionDTO, Integer oppId)
			throws MSSPException {

		/*
		 * //deleting detials from SolutionInputDefinition if any exists if
		 * (!"".equals(deleletedOppScopeIds) && solutionDTO.getSolutionId() !=
		 * null){ deleletedOppScopeIds =
		 * deleletedOppScopeIds.substring(0,deleletedOppScopeIds.length()-1);
		 * deleteSolutionInputFields(deleletedOppScopeIds,
		 * solutionDTO.getSolutionId()); }
		 * 
		 * // saving opportunity scopes if(!"".equals(deleletedOppScopeIds))
		 * deleteOpprtunityScope(deleletedOppScopeIds);
		 * 
		 * if(!"".equals(newServiceScopeIds))
		 * saveOpportunityScope(newServiceScopeIds, oppId);
		 */

		if (!"".equals(solutionDTO.getDeleltedDimAttributesIds())) {
			deletedSolutionDimensionAttributes(solutionDTO
					.getDeleltedDimAttributesIds());
		}

		Solution solution = setSolution(solutionDTO, oppId);
		// saving Solution details
		saveObject(solution);
		//creating a for solution lever  in table
		List<SolutionLever> slList = getHibernateTemplate().find(" from SolutionLever where SolutionID="+ solutionDTO.getSolutionId());
		if(slList == null || slList.size() ==0) {
			SolutionLever sl = new SolutionLever();
			sl.setSolution(solution);
			saveObject(sl);
		}
		
		return solution.getSolutionId();
	}

	public void saveOpportunityScopes(Integer oppId, String newServiceScopeIds,
			String deleletedOppScopeIds) throws MSSPException {
		/*
		 * //deleting detials from SolutionInputDefinition if any exists if
		 * (!"".equals(deleletedOppScopeIds) && solutionDTO.getSolutionId() !=
		 * null){ deleletedOppScopeIds =
		 * deleletedOppScopeIds.substring(0,deleletedOppScopeIds.length()-1);
		 * deleteSolutionInputFields(deleletedOppScopeIds,
		 * solutionDTO.getSolutionId()); }
		 */

		// saving opportunity scopes
		if (!"".equals(deleletedOppScopeIds))
			deleteOpprtunityScope(deleletedOppScopeIds);

		if (!"".equals(newServiceScopeIds))
			saveOpportunityScope(newServiceScopeIds, oppId);

	}

	private void deleteOpprtunityScope(String deleletedOppScopeIds)
			throws MSSPException {

		String[] delOppScopeArray = deleletedOppScopeIds.split(",");
		for (String oppScope : delOppScopeArray) {
			removeObject(OpportunityScope.class, new Integer(oppScope));
		}
	}

	private void deletedSolutionDimensionAttributes(
			String deleletedAttributesIds) throws MSSPException {

		String[] delDimAttArray = deleletedAttributesIds.split(",");
		for (String id : delDimAttArray) {
			removeObject(SolutionApproachDimension.class, new Integer(id));
		}
	}

	/*
	 * SELECT SolutionInputDefinitionID FROM SolutionInputDefinition where
	 * InputFieldsID in (select InputFieldsID from InputFields where
	 * ServiceScopeID in (select ServiceScopeID from OpportunityScope where
	 * OpportunityScopeID in(224))); Session session =
	 * SessionFactoryUtils.getSession(getHibernateTemplate(
	 * ).getSessionFactory(), false); final String query =
	 * " delete From SolutionInputDefinition where InputFieldsID in(Select InputFieldsID from InputFields "
	 * +
	 * "where ServiceScopeID in (select ServiceScopeID from OpportunityScope where OpportunityScopeID in("
	 * +deleletedOppScopeIds+"))) and SolutionID="+solutionId;
	 * 
	 * getHibernateTemplate().execute(new HibernateCallback() { public Object
	 * doInHibernate(Session session) throws HibernateException { SQLQuery sq
	 * =session.createSQLQuery(query); sq.executeUpdate(); return null; }
	 * 
	 * });
	 */

	/**
	 * 
	 * Description : Setting Solution DTO details into Solution Entity Method
	 * Name : setSolution Input& Output:
	 * 
	 * @param solutionDTO
	 * @param oppId
	 * @return
	 */
	private Solution setSolution(SolutionDTO solutionDTO, Integer oppId)
			throws MSSPException {

		Solution solution = new Solution();
		if (solutionDTO.getSolutionId() != null) {
			solution.setSolutionId(solutionDTO.getSolutionId());
		}
		solution.setSolutionApproach(solutionDTO.getSolutionApproach());
		solution.setSolutionType(solutionDTO.getSolutionType());
	solution.setJobRoleModel(solutionDTO.getJobRoleModel());
	solution.setUtilizationPerYear(solutionDTO.getUtilizationPerYear());

		Status status = new Status();
		status.setStatusId(SOLUTION_STATUS_DRAFT);

		Opportunity opp = new Opportunity();
		opp.setOpportunityId(oppId);
		solution.setCreatedBy(solutionDTO.getCreatedBy());
		solution.setCreatedDate(new Date());
		solution.setStatus(status);
		solution.setOpportunity(opp);
		String attributesNameList = solutionDTO.getNewDimAttributesList();

		Set<SolutionApproachDimension> set = new HashSet<SolutionApproachDimension>();
		SolutionApproachDimension solAPPDim;
		if (!"".equals(attributesNameList)) {
			String[] newDimAttArray = attributesNameList.split(",");
			for (String attributeName : newDimAttArray) {
				solAPPDim = new SolutionApproachDimension();
				solAPPDim.setDimensionName(solutionDTO
						.getSolutionApproachDimensionDTO().getDimensionName());

				solAPPDim.setDimensionAttributeName(attributeName);

				set.add(solAPPDim);
			}
		}
		solution.setSolutionApproachDimensions(set);

		return solution;
	}

	/**
	 * 
	 * Description : Setting opportunity and service scope details Method Name :
	 * setOpportunityScope Input& Output:
	 * 
	 * @param serviceScopes
	 * @param oppId
	 * @return
	 */
	private void saveOpportunityScope(String newListstr, Integer oppId) {

		String[] serviceScopeIdArray = newListstr.split(",");
		OpportunityScope oppScope;
		Opportunity opp = new Opportunity();
		opp.setOpportunityId(oppId);

		for (String serviceScopeId : serviceScopeIdArray) {
			oppScope = new OpportunityScope();
			ServiceScope serviceScope = new ServiceScope();
			// Integer serviceScopeId=
			// oppScopeDTO.getServiceScopeDTO().getServiceScopeId();
			serviceScope.setServiceScopeId(new Integer(serviceScopeId));
			oppScope.setServiceScope(serviceScope);
			oppScope.setOpportunity(opp);

			saveObject(oppScope);
		}

	}

	public SolutionDTO getSolutionDetail(Integer solId) throws MSSPException {
		Solution solution = (Solution) getObject(Solution.class, solId);

		SolutionDTO solutionDTO = setSolEntityToSolDTO(solution);
		return solutionDTO;
	}

	public List<OpportunityScopeDTO> getOpportunityScopes(Integer oppId)
			throws MSSPException {
		List<OpportunityScope> opportunityScopeList = getHibernateTemplate()
				.find(" from OpportunityScope where opportunityID=" + oppId);

		List<OpportunityScopeDTO> oppScopeDTOList = new ArrayList<OpportunityScopeDTO>();
		OpportunityScopeDTO oppScopeDTO;
		for (OpportunityScope oppscope : opportunityScopeList) {
			oppScopeDTO = new OpportunityScopeDTO();
			oppScopeDTO.setOpportunityScopeId(oppscope.getOpportunityScopeId());
			oppScopeDTO.setServiceScopeDTO(setSSEntityIntoSSDTO(oppscope
					.getServiceScope()));
			oppScopeDTOList.add(oppScopeDTO);
		}
		return oppScopeDTOList;

	}

	private SolutionDTO setSolEntityToSolDTO(Solution solution) {
		SolutionDTO solutionDTO = new SolutionDTO();
		
		Status status = new Status();
		
		if(solution.getStatus() != null){
			status.setStatusId(solution.getStatus().getStatusId());
			solutionDTO.setStatus(status);
		}
		
		solutionDTO.setUpdatedDate(solution.getUpdatedDate());
		solutionDTO.setSolutionApproach(solution.getSolutionApproach());
		solutionDTO.setSolutionType(solution.getSolutionType());
	solutionDTO.setJobRoleModel(solution.getJobRoleModel());
	solutionDTO.setUtilizationPerYear(solution.getUtilizationPerYear());
		solutionDTO.setCreatedBy(solution.getCreatedBy());

		Set<SolutionApproachDimension> solAPPDimlist = solution
				.getSolutionApproachDimensions();

		List<String> dimensionAttributes = new ArrayList<String>();
		SolutionApproachDimensionDTO sadDTO = new SolutionApproachDimensionDTO();
		String dimensionName = "";
		String dimensionAttributesStr = "";
		for (SolutionApproachDimension solAPPDim : solAPPDimlist) {
			dimensionAttributes.add(solAPPDim.getDimensionAttributeName());
			dimensionName = solAPPDim.getDimensionName();

			dimensionAttributesStr += solAPPDim
					.getSolutionApproachDimensionId()
					+ "_"
					+ solAPPDim.getDimensionAttributeName() + ";";

		}
		sadDTO.setDimensionAttributes(dimensionAttributes);
		sadDTO.setDimensionName(dimensionName);
		solutionDTO.setSolutionApproachDimensionDTO(sadDTO);

		if (!"".equals(dimensionAttributesStr)) {
			dimensionAttributesStr = dimensionAttributesStr.substring(0,
					dimensionAttributesStr.length() - 1);
		}
		solutionDTO.setDimentionAttributesString(dimensionAttributesStr);
		return solutionDTO;
	}

	public List<SolutionADR> getAllSolutionADR() {
		return getObjects(SolutionADR.class);
	}

	public List<Country> getCountries() {
		return getObjects(Country.class);
	}

	@Transactional
	public Country getSelectedCountry(Integer countryId) {
		return (Country) getObject(Country.class, countryId);
	}

	@Transactional
	public List<User> getRepresentatives() {
		List<User> userList = new ArrayList<User>();
		//String hql = "select u from User u group by u.signumId";
		
		//Query for selecting Approver or Super User. 
		String hql = "select u from User u JOIN u.applicationRoles a WHERE a.applicationRoleId in (2,3) group by u.signumId";

		userList = getHibernateTemplate().find(hql);
		return userList;
	}

	@Transactional
	public String saveOpportunity(OpportunityDTO opportunityDTO) {
		Opportunity opportunity = new Opportunity();
		Customer customer = new Customer();
		OpportunitySource opportunitySource = new OpportunitySource();
		CommercialModel commercialModel = new CommercialModel();

		logger.info("check if opportunityID already exists : "
				+ opportunityDTO.getOpportunityId());
		/******** check for unique opportunity, customer and country name combination *******/
		if (opportunityDTO.getOpportunityId() == null) {
			String checkQuery = "select a.OpportunityName, b.CustomerName,c.CountryID"
					+ " from Opportunity a, Customer b, Country c"
					+ " where a.CustomerID=b.CustomerID and b.CountryID=c.CountryID and a.OpportunityName='"
					+ opportunityDTO.getOpportunityName()
					+ "' "
					+ " and b.CustomerName='"
					+ opportunityDTO.getCustomerDTO().getCustomerName()
					+ "' "
					+ " and c.CountryID="
					+ opportunityDTO.getCustomerDTO().getCountryDTO()
							.getCountryId();

			logger.info("query created to execute : " + checkQuery);
			Session session = null;
			try {
				session = getSession();
				SQLQuery query1 = session.createSQLQuery(checkQuery);
				List tempResultSet = query1.list();
				logger.info("list for unique oppor combination : "
						+ tempResultSet.size());
				if (tempResultSet.size() > 0) {
					logger.info(" list size for tempResultSet : "
							+ tempResultSet.size());
					return null;
				}
			} catch (Exception e) {
				logger.info("exception : " + e.getMessage());
				// TODO: handle exception
			} finally {
				if (null != session) {
					session.close();
				}
			}
		}
		/** ends here **/

		Country country = (Country) getObject(Country.class, opportunityDTO
				.getCustomerDTO().getCountryDTO().getCountryId());

		customer.setCustomerName(opportunityDTO.getCustomerDTO()
				.getCustomerName());
		customer.setCountry(country);

		if (opportunityDTO.getOpportunitySourceDTO().getOpportunitySourceId() != null) {
			opportunitySource.setOpportunitySourceId(opportunityDTO
					.getOpportunitySourceDTO().getOpportunitySourceId());
			opportunity.setOpportunitySource(opportunitySource);
		}

		if (opportunityDTO.getCommercialModelDTO().getCommercialModelId() != null) {
			commercialModel.setCommercialModelId(opportunityDTO
					.getCommercialModelDTO().getCommercialModelId());
			opportunity.setCommercialModel(commercialModel);
		}

		if (opportunityDTO.getOpportunityId() != null) {
			opportunity.setOpportunityId(opportunityDTO.getOpportunityId());
		}
		opportunity.setOpportunityName(opportunityDTO.getOpportunityName());
		opportunity.setCustomer(customer);
		opportunity.setCreatedBy(opportunityDTO.getCreatedBy());
		opportunity.setAssignedTo(opportunityDTO.getAssignedTo());

		saveObject(opportunity);

		WorkflowTimeline workflowTimeline = new WorkflowTimeline();

		workflowTimeline.setApprovalDate(ApplicationPropertiesUtil
				.string2Date(opportunityDTO.getWorkflowTimelinesDTO()
						.getaDate()));
		workflowTimeline
				.setQuestionnaireSubmissionDate(ApplicationPropertiesUtil
						.string2Date(opportunityDTO.getWorkflowTimelinesDTO()
								.getqSubmissionDate()));
		workflowTimeline.setRfpreceiptDate(ApplicationPropertiesUtil
				.string2Date(opportunityDTO.getWorkflowTimelinesDTO()
						.getRfpRecieveDate()));
		workflowTimeline.setSolutionReviewDate(ApplicationPropertiesUtil
				.string2Date(opportunityDTO.getWorkflowTimelinesDTO()
						.getsReviewDate()));
		workflowTimeline.setSubmissionDate(ApplicationPropertiesUtil
				.string2Date(opportunityDTO.getWorkflowTimelinesDTO()
						.getsDate()));

		workflowTimeline.setAssignedTo(opportunityDTO.getWorkflowTimelinesDTO()
				.getAssignedTo());
		workflowTimeline.setComments(opportunityDTO.getWorkflowTimelinesDTO()
				.getComments());

		if (opportunityDTO.getWorkflowTimelinesDTO().getWorkflowTimelineId() != null) {
			workflowTimeline.setWorkflowTimelineId(opportunityDTO
					.getWorkflowTimelinesDTO().getWorkflowTimelineId());
		}

		workflowTimeline.setOpportunity(opportunity);

		opportunity.setWorkflowTimeline(workflowTimeline);

		saveObject(opportunity);

		return opportunity.getOpportunityId().toString();
	}

	@Transactional
	public OpportunityDTO getOpportunity(Integer opportunityId) {
		logger.info("opportunity ID in dao impl getOpportunity : "
				+ opportunityId);

		Opportunity opportunity = new Opportunity();
		OpportunityDTO opportunityDTO = new OpportunityDTO();
		WorkflowTimelineDTO workflowTimelineDTO = new WorkflowTimelineDTO();
		CustomerDTO customerDTO = new CustomerDTO();
		CountryDTO countryDTO = new CountryDTO();
		OpportunityDetailDTO opportunityDetailDTO = new OpportunityDetailDTO();
		OpportunityLocationDTO opportunityLocationDTO = new OpportunityLocationDTO();

		OpportunitySourceDTO opportunitySourceDTO = new OpportunitySourceDTO();
		CommercialModelDTO commercialModelDTO = new CommercialModelDTO();

		opportunity = (Opportunity) getObject(Opportunity.class, opportunityId);
		countryDTO.setCountryId(opportunity.getCustomer().getCountry()
				.getCountryId());
		countryDTO.setCountryCode(opportunity.getCustomer().getCountry()
				.getCountryCode());
		countryDTO.setCountryName(opportunity.getCustomer().getCountry()
				.getCountryName());
		countryDTO.setCurrencyCode(opportunity.getCustomer().getCountry()
				.getCurrencyCode());
		countryDTO.setCurrencyName(opportunity.getCustomer().getCountry()
				.getCurrencyName());
		countryDTO.setTimeZone(opportunity.getCustomer().getCountry()
				.getTimeZone());
		countryDTO
				.setRegion(opportunity.getCustomer().getCountry().getRegion()); // setting
		// region
		customerDTO.setCountryDTO(countryDTO);
		customerDTO
				.setCustomerName(opportunity.getCustomer().getCustomerName());

		if (opportunity.getOpportunitySource() != null) {
			opportunitySourceDTO.setDescription(opportunity
					.getOpportunitySource().getDescription());
			opportunitySourceDTO.setName(opportunity.getOpportunitySource()
					.getName());
			opportunitySourceDTO.setOpportunitySourceId(opportunity
					.getOpportunitySource().getOpportunitySourceId());

			opportunityDTO.setOpportunitySourceDTO(opportunitySourceDTO);
		}

		if (opportunity.getCommercialModel() != null) {
			commercialModelDTO.setCommercialModelId(opportunity
					.getCommercialModel().getCommercialModelId());
			commercialModelDTO.setDescription(opportunity.getCommercialModel()
					.getDescription());
			commercialModelDTO.setName(opportunity.getCommercialModel()
					.getName());

			opportunityDTO.setCommercialModelDTO(commercialModelDTO);
		}

		if (opportunity.getOpportunityDetail() != null) {

			opportunityDetailDTO
					.setCustomerFulfillmentResponsible(opportunity
							.getOpportunityDetail()
							.getCustomerFulfillmentResponsible());
			opportunityDetailDTO.setCustomerSolutionResponsible(opportunity
					.getOpportunityDetail().getCustomerSolutionResponsible());
			opportunityDetailDTO.setAccountCommercialResponsible(opportunity
					.getOpportunityDetail().getAccountCommercialResponsible());

			opportunityDetailDTO.setCadenceId(opportunity
					.getOpportunityDetail().getCadenceId());
			opportunityDetailDTO.setCapexSpend(opportunity
					.getOpportunityDetail().getCapexSpend());
			opportunityDetailDTO.setOpexSpend(opportunity
					.getOpportunityDetail().getOpexSpend());
			opportunityDetailDTO.setOpportunityDetailId(opportunity
					.getOpportunityDetail().getOpportunityDetailId());
			opportunityDetailDTO.setOpportunityOwner(opportunity
					.getOpportunityDetail().getOpportunityOwner());
			opportunityDetailDTO.setExistingCustomer(opportunity
					.getOpportunityDetail().getExistingCustomer());
			opportunityDetailDTO.setNewBusiness(opportunity
					.getOpportunityDetail().getNewBusiness());
			opportunityDetailDTO.setVertical(opportunity.getOpportunityDetail()
					.getVertical());
			opportunityDetailDTO.setTsd(ApplicationPropertiesUtil
					.date2string(opportunity.getOpportunityDetail()
							.getTransformationStartDate()));
			opportunityDetailDTO.setTed(ApplicationPropertiesUtil
					.date2string(opportunity.getOpportunityDetail()
							.getTransformationEndDate()));
			opportunityDetailDTO.setSssd(ApplicationPropertiesUtil
					.date2string(opportunity.getOpportunityDetail()
							.getSteadyStateStartDate()));
			opportunityDetailDTO.setSsed(ApplicationPropertiesUtil
					.date2string(opportunity.getOpportunityDetail()
							.getSteadyStateEndDate()));
			opportunityDetailDTO.setRegionKam(opportunity
					.getOpportunityDetail().getOpportunityOwner());
			opportunityDetailDTO.setCompetitors(opportunity
					.getOpportunityDetail().getCompetitors());
			opportunityDetailDTO.setExistingFte(opportunity
					.getOpportunityDetail().getExistingFte());
			opportunityDetailDTO.setTurnOver(opportunity.getOpportunityDetail()
					.getTurnOver());
			opportunityDetailDTO.setPrimaryBusinessLine(opportunity
					.getOpportunityDetail().getPrimaryBusinessLine());

			opportunityDetailDTO.setPrimaryVerticalRepresentative(opportunity
					.getOpportunityDetail().getPrimaryVerticalRepresentative());
			opportunityDetailDTO.setServiceType(opportunity.getOpportunityDetail().getServiceType());

			/*
			 * opportunityDetailDTO.setPrimaryVerticalRepresentative(opportunity
			 * .getCreatedBy());
			 */

			opportunityDetailDTO.setAdmssolutionRepresentative(opportunity
					.getOpportunityDetail().getAdmssolutionRepresentative());
			opportunityDetailDTO.setTertiarySolutionSME(opportunity.
					getOpportunityDetail().getTertiarySolutionSME());
			opportunityDetailDTO.setContractDuration(opportunity
					.getOpportunityDetail().getContractDuration());
			opportunityDetailDTO.setSteadyStateDuration(opportunity
					.getOpportunityDetail().getSteadyStateDuration());
			opportunityDetailDTO.setRfpreason(opportunity
					.getOpportunityDetail().getRfpreason());
			opportunityDetailDTO.setOtherReason(opportunity.getOpportunityDetail().getOtherReason());
			
			if (opportunity.getOpportunityDetail().getDeliveryModel() != null) {

				DeliveryModelDTO deliveryModelDTO = new DeliveryModelDTO();

				deliveryModelDTO.setDeliveryModelId(opportunity
						.getOpportunityDetail().getDeliveryModel()
						.getDeliveryModelId());
				deliveryModelDTO.setDescription(opportunity
						.getOpportunityDetail().getDeliveryModel()
						.getDescription());
				deliveryModelDTO.setName(opportunity.getOpportunityDetail()
						.getDeliveryModel().getName());

				opportunityDetailDTO.setDeliveryModelDTO(deliveryModelDTO);
			}
			if (opportunity.getOpportunityDetail().getDeliveryType() != null) {

				DeliveryTypeModelDTO deliveryTypeModelDTO = new DeliveryTypeModelDTO();

				deliveryTypeModelDTO.setDeliveryTypeId(opportunity
						.getOpportunityDetail().getDeliveryType()
						.getDeliveryTypeId());
				deliveryTypeModelDTO.setDescription(opportunity
						.getOpportunityDetail().getDeliveryType()
						.getDescription());
				deliveryTypeModelDTO.setName(opportunity.getOpportunityDetail()
						.getDeliveryType().getName());

				opportunityDetailDTO
						.setDeliveryTypeModelDTO(deliveryTypeModelDTO);
			}
			if (opportunity.getOpportunityDetail()
					.getScopeOfServicesDefinedBy() != null) {

				ScopeDefinedByDTO scopeDefinedByDTO = new ScopeDefinedByDTO();

				scopeDefinedByDTO.setDescription(opportunity
						.getOpportunityDetail().getScopeOfServicesDefinedBy()
						.getDescription());
				scopeDefinedByDTO.setName(opportunity.getOpportunityDetail()
						.getScopeOfServicesDefinedBy().getName());
				scopeDefinedByDTO.setScopeOfServicesDefinedById(opportunity
						.getOpportunityDetail().getScopeOfServicesDefinedBy()
						.getScopeOfServicesDefinedById());

				opportunityDetailDTO.setScopeDefinedByDTO(scopeDefinedByDTO);
			}
			if (opportunity.getOpportunityDetail().getVolumetricsDefinedBy() != null) {

				VolumetricsDefinedByDTO volumetricsDefinedByDTO = new VolumetricsDefinedByDTO();

				volumetricsDefinedByDTO.setDescription(opportunity
						.getOpportunityDetail().getVolumetricsDefinedBy()
						.getDescription());
				volumetricsDefinedByDTO
						.setInputVolumetricsDefinedById(opportunity
								.getOpportunityDetail()
								.getVolumetricsDefinedBy()
								.getInputVolumetricsDefinedById());
				volumetricsDefinedByDTO.setName(opportunity
						.getOpportunityDetail().getVolumetricsDefinedBy()
						.getName());

				opportunityDetailDTO
						.setVolumetricsDefinedByDTO(volumetricsDefinedByDTO);
			}

			
			String query = "from OpportunityLocation where OpportunityDetailID="
					+ opportunity.getOpportunityDetail()
							.getOpportunityDetailId();

			List<OpportunityLocation> opportunityLocations = getHibernateTemplate()
					.find(query);

			for (OpportunityLocation opportunityLocation : opportunityLocations) {
				opportunityLocationDTO.setOpportunityLocationId(opportunityLocation.getOpportunityLocationId());
				opportunityLocationDTO.setGsc1(opportunityLocation.getGsc1());
				opportunityLocationDTO.setGsc2(opportunityLocation.getGsc2());
				opportunityLocationDTO.setNearShore(opportunityLocation	.getNearShore());

			}
		
			/*opportunityDetailDTO.setOnshore3PP(opportunity.getOpportunityDetail().getOnshore3PP());
			opportunityDetailDTO.setOnshoreGSC(opportunity.getOpportunityDetail().getOnshoreGSC());
			opportunityDetailDTO.setOnshoreLocal(opportunity.getOpportunityDetail().getOnshoreLocal());
			opportunityDetailDTO.setNearShore(opportunity.getOpportunityDetail().getNearShore());
			opportunityDetailDTO.setOffShore(opportunity.getOpportunityDetail().getOffShore());*/
			
			opportunityDetailDTO
					.setOpportunityLocationsDTO(opportunityLocationDTO);
			opportunityDTO.setOpportunityDetailsDTO(opportunityDetailDTO);
		}
		logger.info("opportunity -- Workflow--"
				+ opportunity.getWorkflowTimeline());
		if (opportunity.getWorkflowTimeline() != null) {
			workflowTimelineDTO.setWorkflowTimelineId(opportunity
					.getWorkflowTimeline().getWorkflowTimelineId());
			workflowTimelineDTO.setaDate(ApplicationPropertiesUtil
					.date2string(opportunity.getWorkflowTimeline()
							.getApprovalDate()));
			workflowTimelineDTO.setqSubmissionDate(ApplicationPropertiesUtil
					.date2string(opportunity.getWorkflowTimeline()
							.getQuestionnaireSubmissionDate()));
			workflowTimelineDTO.setRfpRecieveDate(ApplicationPropertiesUtil
					.date2string(opportunity.getWorkflowTimeline()
							.getRfpreceiptDate()));
			workflowTimelineDTO.setsDate(ApplicationPropertiesUtil
					.date2string(opportunity.getWorkflowTimeline()
							.getSubmissionDate()));
			workflowTimelineDTO.setsReviewDate(ApplicationPropertiesUtil
					.date2string(opportunity.getWorkflowTimeline()
							.getSolutionReviewDate()));
			workflowTimelineDTO.setAssignedTo(opportunity.getWorkflowTimeline()
					.getAssignedTo());
			workflowTimelineDTO.setComments(opportunity.getWorkflowTimeline()
					.getComments());
			opportunityDTO.setWorkflowTimelinesDTO(workflowTimelineDTO);
		}
		opportunityDTO.setOpportunityId(opportunityId);
		opportunityDTO.setCustomerDTO(customerDTO);
		opportunityDTO.setOpportunityName(opportunity.getOpportunityName());
		opportunityDTO.setCreatedBy(opportunity.getCreatedBy());
		opportunityDTO.setAssignedTo(opportunity.getAssignedTo());

		return opportunityDTO;
	}

	@Transactional
	public String saveOpportunityDetails(
			OpportunityDetailDTO opportunityDetailDTO) {

		logger.info("inside save opportunity dao impl");

		OpportunityDetail opportunityDetail = new OpportunityDetail();
		Set<OpportunityLocation> opportunityLocations = new HashSet<OpportunityLocation>();
		OpportunityLocation opportunityLocation = new OpportunityLocation();

		DeliveryModel deliveryModel = new DeliveryModel();
		DeliveryType deliveryType = new DeliveryType();
		ScopeOfServicesDefinedBy scopeOfServicesDefinedBy = new ScopeOfServicesDefinedBy();
		InputVolumetricsDefinedBy volumetricsDefinedBy = new InputVolumetricsDefinedBy();

		Opportunity opportunity = new Opportunity();

		opportunity.setOpportunityId(opportunityDetailDTO.getOpportunityDTO()
				.getOpportunityId());

		if (opportunityDetailDTO.getOpportunityDetailId() != null) {
			logger.info("updating the opportunity details");
			opportunityDetail.setOpportunityDetailId(opportunityDetailDTO
					.getOpportunityDetailId());
		}

		if (opportunityDetailDTO.getDeliveryModelDTO().getDeliveryModelId() != null) {
			deliveryModel.setDeliveryModelId(opportunityDetailDTO
					.getDeliveryModelDTO().getDeliveryModelId());

			opportunityDetail.setDeliveryModel(deliveryModel);
		}
		if (opportunityDetailDTO.getDeliveryTypeModelDTO() != null)
			if (opportunityDetailDTO.getDeliveryTypeModelDTO()
					.getDeliveryTypeId() != null) {
				deliveryType.setDeliveryTypeId(opportunityDetailDTO
						.getDeliveryTypeModelDTO().getDeliveryTypeId());

				opportunityDetail.setDeliveryType(deliveryType);
			}
		if (opportunityDetailDTO.getScopeDefinedByDTO()
				.getScopeOfServicesDefinedById() != null) {
			scopeOfServicesDefinedBy
					.setScopeOfServicesDefinedById(opportunityDetailDTO
							.getScopeDefinedByDTO()
							.getScopeOfServicesDefinedById());

			opportunityDetail
					.setScopeOfServicesDefinedBy(scopeOfServicesDefinedBy);
		}
		if (opportunityDetailDTO.getVolumetricsDefinedByDTO() != null)
			if (opportunityDetailDTO.getVolumetricsDefinedByDTO().getInputVolumetricsDefinedById() != null) {
				volumetricsDefinedBy.setInputVolumetricsDefinedById(opportunityDetailDTO
								.getVolumetricsDefinedByDTO()
								.getInputVolumetricsDefinedById());

				opportunityDetail.setVolumetricsDefinedBy(volumetricsDefinedBy);
			}

		
		if (opportunityDetailDTO.getOpportunityLocationsDTO() != null)
			if (opportunityDetailDTO.getOpportunityLocationsDTO().getOpportunityLocationId() != null) {
				opportunityLocation.setOpportunityLocationId(opportunityDetailDTO
								.getOpportunityLocationsDTO().getOpportunityLocationId());

				opportunityDetail.setOpportunityLocations(opportunityLocations);
			}
		
		
			
		opportunityDetail.setCadenceId(opportunityDetailDTO.getCadenceId());
		opportunityDetail.setCapexSpend(opportunityDetailDTO.getCapexSpend());
		opportunityDetail.setCompetitors(opportunityDetailDTO.getCompetitors());
		opportunityDetail.setExistingCustomer(opportunityDetailDTO
				.getExistingCustomer());
		opportunityDetail.setExistingFte(opportunityDetailDTO.getExistingFte());
		opportunityDetail.setNewBusiness(opportunityDetailDTO.getNewBusiness());
		opportunityDetail.setOpexSpend(opportunityDetailDTO.getOpexSpend());
		opportunityDetail.setOpportunityOwner(opportunityDetailDTO
				.getOpportunityOwner());
		opportunityDetail.setOtherReason(opportunityDetailDTO.getOtherReason());
		opportunityDetail.setPrimaryBusinessLine(opportunityDetailDTO
				.getPrimaryBusinessLine());
		opportunityDetail.setPrimaryVerticalRepresentative(opportunityDetailDTO
				.getPrimaryVerticalRepresentative());

		opportunityDetail.setAccountCommercialResponsible(opportunityDetailDTO
				.getAccountCommercialResponsible());
		opportunityDetail
				.setCustomerFulfillmentResponsible(opportunityDetailDTO
						.getCustomerFulfillmentResponsible());
		opportunityDetail.setCustomerSolutionResponsible(opportunityDetailDTO
				.getCustomerSolutionResponsible());
		opportunityDetail.setTertiarySolutionSME(opportunityDetailDTO.
				getTertiarySolutionSME());

		// opportunityDetail.setRegionKam(opportunityDetailDTO.getRegionKam());
		opportunityDetail.setRegionKam(opportunityDetailDTO
				.getOpportunityOwner());
		opportunityDetail.setRfpreason(opportunityDetailDTO.getRfpreason());
		opportunityDetail.setSteadyStateEndDate(opportunityDetailDTO
				.getSteadyStateEndDate());
		opportunityDetail.setSteadyStateStartDate(opportunityDetailDTO
				.getSteadyStateStartDate());
		opportunityDetail.setTransformationEndDate(opportunityDetailDTO
				.getTransformationEndDate());
		opportunityDetail.setTransformationStartDate(opportunityDetailDTO
				.getTransformationStartDate());
		opportunityDetail.setAdmssolutionRepresentative(opportunityDetailDTO
				.getAdmssolutionRepresentative());
		opportunityDetail.setTurnOver(opportunityDetailDTO.getTurnOver());
		opportunityDetail.setVertical(opportunityDetailDTO.getVertical());
		opportunityDetail.setContractDuration(opportunityDetailDTO
				.getContractDuration());
		opportunityDetail.setSteadyStateDuration(opportunityDetailDTO
				.getSteadyStateDuration());

		opportunityDetail.setOpportunity(opportunity);

		saveObject(opportunityDetail);

		
		
		
		
		if ((opportunityDetailDTO.getOpportunityLocationsDTO().getGsc1() != null)
				&& !(opportunityDetailDTO.getOpportunityLocationsDTO()
						.getGsc1().equals(""))) {
			opportunityLocation.setGsc1(opportunityDetailDTO
					.getOpportunityLocationsDTO().getGsc1());
		}

		if (opportunityDetailDTO.getOpportunityLocationsDTO().getGsc2() != null
				&& !opportunityDetailDTO.getOpportunityLocationsDTO().getGsc2()
						.equals("")) {
			opportunityLocation.setGsc2(opportunityDetailDTO
					.getOpportunityLocationsDTO().getGsc2());
		}

		if (opportunityDetailDTO.getOpportunityLocationsDTO().getNearShore() != null
				&& !opportunityDetailDTO.getOpportunityLocationsDTO()
						.getNearShore().equals("")) {
			opportunityLocation.setNearShore(opportunityDetailDTO
					.getOpportunityLocationsDTO().getNearShore());
		}

		opportunityLocation.setCustomerCountry(opportunityDetailDTO
				.getOpportunityLocationsDTO().getCustomerCountry());

		
		
		
		
		
		
		opportunityLocation.setOpportunityDetail(opportunityDetail);

		opportunityLocations.add(opportunityLocation);

		opportunityDetail.setOpportunityLocations(opportunityLocations);

		saveObject(opportunityDetail);

		return opportunityDetail.getOpportunityDetailId().toString();
	}

	/**
	 * 
	 * Description : Convert SOlutionADR entity to SolutionADRDTO Method Name :
	 * convertSolADR2SolADRDTO Input& Output:
	 * 
	 * @param solADR
	 * @return
	 */
	public SolutionADRDTO convertSolADR2SolADRDTO(SolutionADR solADR) {
		SolutionADRDTO solutionADRDTO = new SolutionADRDTO();
		solutionADRDTO.setAdrArea(solADR.getAdrarea());
		solutionADRDTO.setAdrCategory(solADR.getAdrcategory());
		solutionADRDTO.setAdrImpact(solADR.getAdrimpact());
		solutionADRDTO.setAdrStatement(solADR.getAdrstatement());
		solutionADRDTO.setAdrType(solADR.getAdrtype());
		solutionADRDTO.setAdrWeightage(solADR.getAdrweightage());
		solutionADRDTO.setSolutionId(solADR.getSolution().getSolutionId());
		solutionADRDTO.setSolutionAdrid(solADR.getSolutionAdrid());
		return solutionADRDTO;

	}

	/**
	 * 
	 * Description : Returns result set based on passed session and query Method
	 * Name : getQueryResults Input& Output:
	 * 
	 * @param session
	 * @param queryString
	 * @return ScrollableResults
	 */
	private ScrollableResults getQueryResults(Session session,
			String queryString) {
		ScrollableResults results = null;
		try {
			logger.debug("queryString=" + queryString);
			SQLQuery query = session.createSQLQuery(queryString);
			query.setReadOnly(true).setFetchSize(30).setCacheable(false);
			results = query.scroll(ScrollMode.FORWARD_ONLY);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getQueryResults-" + e);
		}
		return results;
	}

	/**
	 * 
	 * Description : It will return true if non zero productivity applied in
	 * first year Method Name : isProductivityLeverInFirstYear Input& Output:
	 * 
	 * @param opportunityID
	 * @param solutionID
	 * @param opportunityScopeID
	 * @return boolean
	 */
	private boolean isProductivityLeverInFirstYear(Integer opportunityID,
			Integer solutionID, Integer opportunityScopeID) {
		boolean isApplied = false;
		// Session session = null;
		try {
			final String productivityQueryString = "select ComputedProductivity from"
					+ " ProductivityLever,(select SteadyStateStartDate fromDt,"
					+ "if(SteadyStateEndDate>(SteadyStateStartDate+interval 12 month),"
					+ " last_Day((SteadyStateStartDate+ interval 12 month)) , "
					+ "SteadyStateEndDate) toDt from OpportunityDetail where "
					+ "OpportunityID="
					+ opportunityID
					+ " LIMIT 1) dateRange where "
					+ "SolutionLeverID=(select "
					+ "SolutionLeverID from SolutionLever where SolutionID="
					+ +solutionID
					+ " LIMIT 1)	and OpportunityScopeID="
					+ +opportunityScopeID
					+ " and MonthYear between fromDt and toDt";
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<Object> results = (List<Object>) getHibernateTemplate()
					.execute(new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							SQLQuery qry = session
									.createSQLQuery(productivityQueryString);

							List<Object> results = (List<Object>) qry.list();

							return results;
						}

					});
			// ScrollableResults results = getQueryResults(session,
			// productivityQueryString);
			for (Object result : results) {
				float productivityValue = Float.parseFloat(result.toString());
				if (productivityValue > 0) {
					isApplied = true;
					break;
				}
			}

		} catch (Exception e) {
			logger.error("isProductivityLeverInFirstYear Error-" + e);
			e.printStackTrace();
			// return isApplied;
		}
		logger.debug("isProductivityLeverInFirstYear isApplied = " + isApplied);
		return isApplied;
	}

	/**
	 * 
	 * Description : This will return Service bucket values for GUI Method Name
	 * : getServiceBucketDataByQuery Input& Output:
	 * 
	 * @param queryString
	 * @return List<String>
	 */
	private List<String> getServiceBucketDataByQuery(String queryString,
			Integer solutionID, Integer opportunityID) {
		List<String> serviceBucketData = null;
		String defaultVal = "NA";
		String delimiters = ";";
		StringBuilder dataRow = null;
		boolean isProductivityLeverAppliedIn1stYr = false;
		Session session = null;
		OpportunityScope opportunityScope = null;
		try {
			serviceBucketData = new ArrayList<String>(10);
			session = getSession();
			ScrollableResults results = getQueryResults(session, queryString);
			while (results.next()) {
				Object[] dataR = (Object[]) results.get();
				dataRow = new StringBuilder();
				int position = -1;
				int opportunityScopeID = (int) ++position < dataR.length
						&& null != dataR[position] ? (Integer) dataR[position]
						: -1;
				if (opportunityScopeID > 0) {
					opportunityScope = (OpportunityScope) getObject(
							OpportunityScope.class, opportunityScopeID);
				} else {
					opportunityScope = null;
				}
				logger.debug("solutionID=" + solutionID
						+ " opportunityScopeID=" + opportunityScopeID);
				// Is productivity leaver Applied
				if (null != opportunityScope
						&& !opportunityScope.getServiceScope()
								.getServiceScopeId().equals(6)) {
					//TODO: Commented as query is failing : Unknown column 'ComputedProductivity'
					/*isProductivityLeverAppliedIn1stYr = isProductivityLeverInFirstYear(
							opportunityID, solutionID, opportunityScopeID);*/
				} else {
					isProductivityLeverAppliedIn1stYr = false;
				}
				// Its for Service Category
				dataRow.append(
						++position < dataR.length && null != dataR[position] ? dataR[position]
								: defaultVal).append(delimiters);
				// Its for Service Type name
				dataRow.append(
						++position < dataR.length && null != dataR[position] ? dataR[position]
								: defaultVal).append(delimiters);
				// Its for day one FTE
				String day1FTE = ++position < dataR.length
						&& null != dataR[position] ? dataR[position].toString()
						: defaultVal;
				dataRow.append(day1FTE).append(delimiters);
				// Its for day one head count
				String day1HC = ++position < dataR.length
						&& null != dataR[position] ? dataR[position].toString()
						: defaultVal;
				dataRow.append(
						opportunityScope.getServiceScope().getServiceScopeId()
								.equals(6) ? defaultVal : day1HC).append(
						delimiters);
				// Check for productivity lever applied or not logic
				if (isProductivityLeverAppliedIn1stYr) {
					// Its for year FTE
					dataRow.append(
							day1FTE.equals(defaultVal) ? defaultVal
									: 12 * Float.parseFloat(day1FTE)).append(
							delimiters);
					// Its for year head count
					dataRow.append(
							day1HC.equals(defaultVal) ? defaultVal : 12 * Float
									.parseFloat(day1HC)).append(delimiters);
				} else {
					// Its for year FTE
					dataRow.append(day1FTE).append(delimiters);
					// Its for year head count
					dataRow.append(day1HC).append(delimiters);
				}
				serviceBucketData.add(dataRow.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error during serviceBucketData-" + e);
		} finally {
			if (null != session) {
				session.close();
			}

		}
		return serviceBucketData;
	}

	@Override
	public List<String> getServiceBucketDataBySolutionID(Integer solutionID) {
		String queryString = "select OpportunityScopeID, serviceScopeCategory,"
				+ "ServiceScopeName, ServiceFTE, NoDemandedPositions "
				+ "from ViewServiceBucket where SolutionID=" + solutionID
				+ " order by ServiceScopeID";
		return getServiceBucketDataByQuery(queryString, solutionID, null);
	}

	@Override
	public List<String> getServiceBucketDataByOppSolutionID(
			Integer opportunityID, Integer solutionID) throws DAOException{

		/*String queryString = "select distinct OpportunityScopeID, "
				+ "serviceScopeCategory,ServiceScopeName, ServiceFTE,"
				+ "NoDemandedPositions "
				+ "from ViewServiceBucket where opportunityID=" + opportunityID
				+ " and (SolutionID=" + solutionID
				+ " or SolutionID is NULL) order by ServiceScopeID";*/

		final String  queryString ="select  pe.OpportunityScopeID, ss.ServiceScopeCategory,ss.ServiceScopeName,pe.TotalBaseHours, "+ 
 "(select sum(FTECount) from FTEStaging fte where fte.OpportunityScopeID = pe.OpportunityScopeID group by fte.OpportunityScopeID ) as FTE "+
  "from OpportunityScope os, ServiceScope ss, ProductEstimationBaseEffortForSolution pe   where os.OpportunityScopeID = pe.OpportunityScopeID and os.ServiceScopeID =ss.ServiceScopeID "+ 
  "and pe.SolutionID =" +solutionID;
				
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Object> results = (List<Object>) getHibernateTemplate()
				.execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery qry = session
								.createSQLQuery(queryString);

						List<Object> results = (List<Object>) qry.list();

						return results;
					}

				});
		
		List<String> strResults = new ArrayList<String>();
		for (Object item : results) {
			Object[] element = (Object[]) item;
			StringBuilder  temp = new StringBuilder();
			/*temp.append((Integer) element[0]);
			temp.append(";");*/
			temp.append((String) element[1]);
			temp.append(";");
			temp.append((String) element[2]);
			temp.append(";");
			temp.append((Float) element[3]);
			temp.append(";");
			if(element[4] == null){
				temp.append(new Double(0));
			}
			else{
				temp.append((Double) element[4]);
			}
			temp.append(";");
			
			strResults.add(temp.toString());
		}
		

		return strResults;
//		return getServiceBucketDataByQuery(queryString, solutionID,
//				opportunityID);
	}

	/**
	 * 
	 * Description : To get all SolADRDTO by sol ID Method Name :
	 * loadAllADRBySolID Input& Output:
	 * 
	 * @param solID
	 * @return
	 * @throws DAOException
	 */
	public List<SolutionADRDTO> loadAllADR2SolADRDTOBySolID(Integer solID)
			throws DAOException {

		List<SolutionADR> solutionADRList = getHibernateTemplate().find(
				" from SolutionADR where solutionID=" + solID);

		List<SolutionADRDTO> solutionADRDTOList = new LinkedList<SolutionADRDTO>();

		for (SolutionADR solutionADR : solutionADRList) {
			solutionADRDTOList.add(convertSolADR2SolADRDTO(solutionADR));
		}
		return solutionADRDTOList;

	}

	@Override
	public List<SolutionADR> loadAllSolutionADRBySolID(Integer solID)
			throws DAOException {

		List<SolutionADR> solutionADRList = getHibernateTemplate().find(
				" from SolutionADR where solutionID=" + solID);
		return solutionADRList;

	}

	@Override
	public List<SolutionADR> loadAllSolutionADRByQuery(String query)
			throws DAOException {
		List<SolutionADR> solutionADRList = getHibernateTemplate().find(query);
		return solutionADRList;
	}

	@Override
	public void saveStaffAug(StaffingPlan staffingPlan) throws DAOException {

		saveObject(staffingPlan);
	}

	@Override
	public List<StaffingPlan> getStaffingPlanBySolutionID(int solutionID)
			throws DAOException {
		List<StaffingPlan> staffingPlanList = getHibernateTemplate().find(
				" from StaffingPlan where SolutionID=" + solutionID
						+ " order by StaffingPlanID");
		return staffingPlanList;
	}

	@Override
	public Solution getStaffAugWithSolutionID(int solutionID)
			throws DAOException {
		// List<StaffingPlan> staffAugList =
		// getHibernateTemplate().find("from StaffingPlan where solutionID="+
		// solutionID);

		Solution objSol = (Solution) getObject(Solution.class, solutionID);
		/*
		 * Set<StaffingPlan> staffAugSet = objSol.getStaffingPlans();
		 * List<StaffingPlan> staffAugList = new ArrayList<StaffingPlan>(
		 * staffAugSet);
		 */
		return objSol;
	}

	@Override
	public List<NonLabourCost> getAllNonLabourCostBySolId(Integer solID)
			throws DAOException {
		List<NonLabourCost> solutionNLCList = getHibernateTemplate().find(
				" from NonLabourCost where SolutionID=" + solID
						+ " order by Year");
		return solutionNLCList;
	}

	@Override
	public int removeAllNNLCBySolIdTimeline(Integer solID, int startYr,
			int endYr) throws DAOException {
		final String query = " delete from NonLabourCost where SolutionId="
				+ solID + " and year not between " + startYr + " and " + endYr;
		Integer deltdCount = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery sq = session.createSQLQuery(query);
						int counts = sq.executeUpdate();
						return Integer.valueOf(counts);
					}

				});
		return deltdCount;
	}

	@Override
	public int removeAllDuplicateNLC() throws DAOException {
		final String query = "delete a.* from NonLabourCost a inner join (select max(NonlabourCostID) maxNonlabourCostID,"
				+ "year,SolutionID from NonLabourCost group by year,SolutionID having count(1)>1) b "
				+ "on a.NonlabourCostID<>b.maxNonlabourCostID and b.year=a.year and b.SolutionID=a.SolutionID";
		Integer deltdCount = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery sq = session.createSQLQuery(query);
						int counts = sq.executeUpdate();
						return Integer.valueOf(counts);
					}

				});
		return deltdCount;
	}

	@Override
	public List getStaffingPlan(int solutionID) throws DAOException {
		List<StaffingPlan> staffPlanList = getHibernateTemplate().find(
				"from StaffingPlan where solutionID=" + solutionID);
		return staffPlanList;
	}

	@Override
	public void saveSolutionAPA(SolutionAPADTO solutionAPADTO, Integer oppId,
			Integer solutionId) throws MSSPException {

		Solution solution = new Solution();
		// solution.setSolutionId(solutionId);
		OpportunityScope opportunityScope = new OpportunityScope();
		dataConvertionToEntity(solutionAPADTO);

		for (SolutionAPA solutionAPA : solutionAPADTO.getSolutionAPAListCN()) {

			if (solutionAPA.getComputedRiskFactor() != null) {
				Integer OpportunityScopeID = checkForSolutionAPATable(
						solutionAPA, oppId, solutionId);
				opportunityScope.setOpportunityScopeId(OpportunityScopeID);
				solution.setSolutionId(solutionId);
				solutionAPA.setSolution(solution);
				solutionAPA.setOpportunityScope(opportunityScope);

				saveObject(solutionAPA);
				System.out.println("SolutionAPA Saved Id is "
						+ solutionAPA.getSolutionApaid().toString());
			}

		}
	}

	public List<JobRole> getAllJobRole() {
		List<JobRole> listJobRole = getHibernateTemplate().find("from JobRole");
		return listJobRole;
	}

	/**
	 * 
	 * Description : checkForSolutionAPATable details into SolutionAPA Entity
	 * Method Name : setSolutionAPA Input& Output:
	 * 
	 * @param SolutionAPADTO
	 * @param oppId
	 *            ,solutionId
	 * @return SolutionAPA
	 */
	private Integer checkForSolutionAPATable(SolutionAPA solutionAPA,
			Integer oppId, Integer solutionId) {
		Integer OpportunityScopeID = 0;
		if (solutionId != null) {
			final String query = "SELECT OpportunityScopeID FROM OpportunityScope where OpportunityID="
					+ oppId
					+ " and ServiceScopeID="
					+ solutionAPA.getSolution().getSolutionId();

			OpportunityScopeID = (Integer) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							// SQLQuery sq =
							// session.createSQLQuery(query).setFirstResult(0);
							List results = session.createSQLQuery(query)
									.setFirstResult(0).list();
							return Integer.valueOf(results.get(0).toString());
						}

					});
		}
		return OpportunityScopeID;
	}

	/**
	 * 
	 * Description : Getting SolutionAPADTO details from SolutionAPA Entity
	 * Method Name : getSolutionAPA Input& Output:
	 * 
	 * @param SolutionAPADTO
	 * @param oppId
	 *            ,solutionId
	 * @return SolutionAPA
	 */
	@Override
	public void getSolutionAPA(Integer solutionId, Integer oppId,
			Integer serviceScopeId, SolutionAPADTO solutionAPADTO,
			List<SolutionAPA> solutionAPAList) throws MSSPException {

		Solution solution = new Solution();
		@SuppressWarnings("unchecked")
		List<OpportunityScope> opportunityScopeList = getHibernateTemplate()
				.find(" from OpportunityScope where opportunityID=" + oppId
						+ " and ServiceScopeID=" + serviceScopeId);
		solution.setSolutionId(serviceScopeId);
		for (OpportunityScope oppscope : opportunityScopeList) {

			@SuppressWarnings("unchecked")
			List<SolutionAPA> solutionAPAOPPList = getHibernateTemplate().find(
					" from SolutionAPA WHERE SolutionId=" + solutionId
							+ " and OpportunityScopeID="
							+ oppscope.getOpportunityScopeId());

			for (SolutionAPA solutionAPA : solutionAPAOPPList) {
				solution.setSolutionId(serviceScopeId);
				solutionAPA.setSolution(solution);
				solutionAPAList.add(solutionAPA);
			}
			solutionAPADTO.setSolutionAPAListCN(solutionAPAList);
		}
	}

	/*
	 * @Override public OpportunityDetailDTO getOpportunityDetail(Integer oppId)
	 * { OpportunityDetailDTO opportunityDetailDTO = new OpportunityDetailDTO();
	 * OpportunityDetail opportunityDetail =
	 * getHibernateTemplate().find("from OpportunityDetail where ");
	 * opportunityDetailDTO.set return opportunityDetailDTO; }
	 */
	private void dataConvertionToEntity(SolutionAPADTO solutionAPADTO) {

		List<SolutionAPA> solutionAPAListACE = new ArrayList<SolutionAPA>();
		try {
			for (int i = 0; i < solutionAPADTO.getSolutionAPAList().size(); i++) {
				SolutionAPAClone temp = solutionAPADTO.getSolutionAPAList()
						.get(i);

				for (int j = 0; j < temp.getBusinessFunction().split(",").length; j++) {
					if (Integer.valueOf(temp.getBusinessCriticalilty().split(
							",")[j]) != 0) {
						Solution solution = new Solution();
						solution.setSolutionId(temp.getSolution()
								.getSolutionId());
						SolutionAPA solutionAPA = new SolutionAPA();

						solutionAPA.setSolution(solution);
						if (!temp.getSolutionApaid().equals("")) {
							solutionAPA.setSolutionApaid(Integer.valueOf(temp
									.getSolutionApaid().split(",")[j]));
						}

						if (!temp.getBusinessFunction().equals("")) {
							solutionAPA.setBusinessFunction(temp
									.getBusinessFunction().split(",")[j]);
						}
						if (!temp.getApplicationName().equals("")) {
							solutionAPA.setApplicationName(temp
									.getApplicationName().split(",")[j]);
						}
						if (!temp.getNoUsers().equals("")) {
							solutionAPA.setNoUsers(Integer.valueOf(temp
									.getNoUsers().split(",")[j]));
						}
						if (!temp.getPlatform().equals("")) {
							solutionAPA.setPlatform(temp.getPlatform().split(
									",")[j]);
						}
						if (!temp.getDatabasenm().equals("")) {
							solutionAPA.setDatabasenm(temp.getDatabasenm()
									.split(",")[j]);
						}
						if (!temp.getPrimarySkill().equals("")) {
							solutionAPA.setPrimarySkill(temp.getPrimarySkill()
									.split(",")[j]);
						}
						if (!temp.getSecondarySkill().equals("")) {
							solutionAPA.setSecondarySkill(temp
									.getSecondarySkill().split(",")[j]);
						}
						solutionAPA.setBusinessCriticalilty(Integer
								.valueOf(temp.getBusinessCriticalilty().split(
										",")[j]));

						solutionAPA.setLevelofDocuments(Integer.valueOf(temp
								.getLevelofDocuments().split(",")[j]));
						solutionAPA.setStability(Integer.valueOf(temp
								.getStability().split(",")[j]));
						solutionAPA.setLongivity(Integer.valueOf(temp
								.getLevelofDocuments().split(",")[j]));
						solutionAPA.setComplexity(Integer.valueOf(temp
								.getComplexity().split(",")[j]));
						solutionAPA.setPercentageShare(Integer.valueOf(temp
								.getPercentageShare().split(",")[j]));
						solutionAPA.setComputedRiskFactor(Float.valueOf(temp
								.getComputedRiskFactor().split(",")[j]));
						solutionAPA.setComputedRiskExposure(Float.valueOf(temp
								.getComputedRiskExposure().split(",")[j]));

						solutionAPAListACE.add(solutionAPA);
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("Exception dataConvertionToEntity "
					+ ex.getMessage());
			ex.printStackTrace();
		}
		solutionAPADTO.setSolutionAPAListCN(solutionAPAListACE);
	}

	@Override
	public List<SearchDTO> getOpportunities(SearchDTO searchDTO) {
		List<SearchDTO> searchDTOs = new ArrayList<SearchDTO>();
		final String query;

		String oppportunityName = "";
		String customerName = "";
		String region = "";
		/*
		 * query =
		 * " select a.OpportunityID,a.OpportunityName, b.CustomerName, c.Region, d.CadenceID, e.SolutionID"
		 * + " from Customer AS b, Country AS c,Opportunity AS a" +
		 * " LEFT JOIN OpportunityDetail AS d"+ " LEFT JOIN Solution AS e " +
		 * " where a.OpportunityID = d.OpportunityID and e.OpportunityID = a.OpportunityID"
		 * + " and a.CustomerID=b.CustomerID and b.CountryID=c.CountryID ";
		 */

		if(searchDTO.getOpportunityName().equalsIgnoreCase("*")){
			oppportunityName = "%";
		}else{
			oppportunityName = searchDTO.getOpportunityName();
		}
		if(searchDTO.getCustomerName().equalsIgnoreCase("*")){
			customerName = "%";
		}else{
			customerName = searchDTO.getCustomerName();
		}
		if(searchDTO.getRegion().equalsIgnoreCase("all")){
			region = "%";
		}else{
			region = searchDTO.getRegion();
		}
		
		query = /*" select a.OpportunityID,a.OpportunityName, b.CustomerName, c.Region, d.CadenceID,d.TurnOver, e.SolutionID, f.StatusName"
				+ " from Status As f, Customer AS b, Country AS c,Opportunity AS a"
				+ " LEFT JOIN OpportunityDetail AS d ON a.OpportunityID = d.OpportunityID"
				+ " LEFT JOIN Solution AS e ON e.OpportunityID = a.OpportunityID"
				+ " where a.CustomerID=b.CustomerID and b.CountryID=c.CountryID and e.StatusID=f.StatusID and e.statusId NOT IN (5)"
				+ (searchDTO.getOpportunityName() != null
						&& !(searchDTO.getOpportunityName().equals("")) ? " and a.OpportunityName LIKE '%"
						+ searchDTO.getOpportunityName() + "%' "
						: "")
				+ (searchDTO.getCustomerName() != null
						&& !(searchDTO.getCustomerName().equals("")) ? " and b.CustomerName LIKE '%"
						+ searchDTO.getCustomerName() + "%' "
						: "")
				+ (searchDTO.getRegion() != null
						&& !(searchDTO.getRegion().equals("")) ? " and c.Region LIKE '%"
						+ searchDTO.getRegion() + "%' "
						: "")
				+ (searchDTO.getMin() != null
						&& !(searchDTO.getMin().equals("")) ? " and d.TurnOver > "
						+ searchDTO.getMin() + " "
						: "")

				+ (searchDTO.getMax() != null
						&& !(searchDTO.getMax().equals("")) ? " and d.TurnOver < "
						+ searchDTO.getMax() + " "
						: "");*/
				
				"Select x.OpportunityID,SolutionID,OpportunityName,CustomerName,Region, TurnOver,StatusName from (select a.OpportunityID, b.SolutionID,f.StatusName, " +
				"c.TurnOver, a.OpportunityName from  Opportunity a  LEFT JOIN " +
				"Solution b on a.OpportunityID=b.OpportunityID left join OpportunityDetail c on a.OpportunityID = c.OpportunityID left join Status f on f.statusID = b.StatusID " +
				"and b.StatusID NOT IN (5))As x LEFT JOIN " +
				"(select a.OpportunityID,c.CustomerName,r.Region from Country r, Customer c, Opportunity a " +
				"where a.CustomerID=c.CustomerID and c.CountryID=r.CountryID)" +
				" As y on x.OpportunityID=y.OpportunityID where "
				+ (searchDTO.getOpportunityName() != null && !(searchDTO.getOpportunityName().equals("")) ? " OpportunityName LIKE '%" 
				+ oppportunityName + "%' and": "")
				+ (searchDTO.getCustomerName() != null && !(searchDTO.getCustomerName().equals("")) ? "  CustomerName LIKE '%" 
				+ customerName + "%' and CustomerName is not null and": "")
				+ (searchDTO.getRegion() != null
						&& !(searchDTO.getRegion().equals("")) ? "  Region LIKE '%"
						+ region + "%' and Region is not null and"
						: "")
				+ (searchDTO.getMin() != null
						&& !(searchDTO.getMin().equals("")) ? " TurnOver > "
						+ searchDTO.getMin() + " and "
						: "")

				+ (searchDTO.getMax() != null
						&& !(searchDTO.getMax().equals("")) ? " TurnOver < "
						+ searchDTO.getMax() + " and  "
						: "")
				+" x.OpportunityID is not null";

		logger.info("query : " + query);
		List<Object> dtos = (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery qry = session.createSQLQuery(query);
						List<Object> results = (List<Object>) qry.list();
						return results;
					}
				});
		// SQLQuery query1 = session.createSQLQuery(query);
		// List dtos = query1.list();
		for (Object item : dtos) {
			Object[] element = (Object[]) item;
			SearchDTO searchDTO2 = new SearchDTO();
			
			searchDTO2.setOpportunityID((Integer) element[0]);
			
			searchDTO2.setOpportunityName((String) element[2]);
			
			searchDTO2.setCustomerName((String) element[3]);
			
			searchDTO2.setRegion((String) element[4]);
			

			if (!(("".equals((String) element[5]) || null == (String) element[5])))
				searchDTO2.setMin(Integer.valueOf((String) element[5]));

			searchDTO2.setSolutionID(((Integer) element[1]));
			
			searchDTO2.setStatus((String) element[6]);
			
			
			searchDTOs.add(searchDTO2);
		}
		// Session session =
		// searchDTOs = getHibernateTemplate().find(query);
		/*
		 * searchDTOs = (List)getHibernateTemplate().execute( new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { List results =
		 * session.createSQLQuery(query).setFirstResult(0).list(); return
		 * Integer.valueOf(results.get(0).toString()); }
		 * 
		 * });
		 */

		logger.info("search dtos list size : " + searchDTOs.size());
		return searchDTOs;
	}

	@Override
	public List<JobRole> getJobRoleList() {
		return getObjects(JobRole.class);
	}
	
	
	public List<JobRoleStages> getJobRoleStagesbySolutionID(Integer solutionID) throws MSSPException{
/*		final String query;
		List<JobRoleStages> jobRoleStagesList = new ArrayList< JobRoleStages>();
		query="Select JobRoleStageId,JobRoleID,JobStageID   from JobRoleStages where JobRoleStageId in " +
    	" (select distinct JobRoleStagesID FROM ServiceElementJobRoleStages where ServiceElementID in " +
    	" (select ServiceElementID from ServiceScope where ServiceScopeID in "+
    	" (select ServiceScopeID from OpportunityScope where OpportunityID = "+
    	" (Select OpportunityID from  Solution where SolutionID= "+ 16 +")))) ";
		
		logger.info("query : " + query);
		
		List<Object> objList = (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery qry = session.createSQLQuery(query);
						List<Object> results = (List<Object>) qry.list();
						return results;
					}
				});
		for (Object item : objList) {
			Object[] element = (Object[]) item;
			JobRoleStages jobRoleStages = new JobRoleStages();
			jobRoleStages.setJobRoleStagesId((Integer) element[0]);
			
			searchDTO2.setOpportunityName((String) element[1]);
			searchDTO2.setCustomerName((String) element[2]);
			searchDTO2.setRegion((String) element[3]);
			searchDTO2.setAssignedTo((String) element[5]);
			
			jobRoleStagesList.add(jobRoleStages);
		
		
		
		}*/
		
		//HQL Query
		
		String q = "  from JobRoleStages where jobRoleStagesId in " +
"	(select distinct jobRoleStages.jobRoleStagesId FROM ServiceElementJobRoleStages where serviceElement.serviceElementID in "+
"	(select serviceElement.serviceElementID from ServiceScope where serviceScopeID in "+
"	(select serviceScope.serviceScopeId from OpportunityScope where opportunity.opportunityId = "+
"	(Select opportunity.opportunityId from  Solution where solutionId = "+ solutionID+"))))";
	
		
		List <JobRoleStages>jrsList = new ArrayList<JobRoleStages>();
		jrsList = getHibernateTemplate().find(q);
		
		return jrsList;
	}
	

	@Override
	public List<RateCard> getRateCards() {

		String query = "from RateCard where location='Onshore'";
		List<RateCard> rateCards = new ArrayList<RateCard>();
		rateCards = getHibernateTemplate().find(query);
		return rateCards;
	}
	
	@Override
	public List<RateCard> getRateCards(Integer countryID) {

		String query = "from RateCard where country.countryId="+countryID;
		List<RateCard> rateCards = new ArrayList<RateCard>();
		rateCards = getHibernateTemplate().find(query);
		return rateCards;
	}

	@Override
	public List<RateCard> getRateCards(Integer opportunityID,
			Integer jobRoleID, String location) {
		/*
		 * String query =
		 * "from RateCard where JobRoleID='"+jobRoleID+"' and Location='"
		 * +location+"'" +
		 * " (select SteadyStateStartDate from OpportunityDetail where OpportunityID='"
		 * +opportunityID+"') between" +
		 * " CreatedTimestamp and UpdatedTimestamp;";
		 */
		String query = "from RateCard where JobRoleID='" + jobRoleID
				+ "' and Location='" + location + "'";
		List<RateCard> rateCards = new ArrayList<RateCard>();
		// logger.info("rate card query : " + query);
		rateCards = getHibernateTemplate().find(query);
		// logger.info("rate cards list length : " + rateCards.size());
		return rateCards;
	}

	@Override
	public List<FTEStaging> getFinalList(Integer solutionID,
			Integer opportunityID, Integer opportunityScopeID) {
		List<String> a = new ArrayList<String>();
		List<FTEStaging> temp = null;

		String query = "from FTEStaging where solution.solutionId="
				+ solutionID + " and opportunityScope.opportunityScopeId="
				+ opportunityScopeID;
		temp = null;
		logger.info("query : " + query);

		temp = getHibernateTemplate().find(query);
		return temp;
	}

	@Override
	public List<SearchDTO> getOpportunitiesToBeReAssigned(SearchDTO searchDTO) {
		List<SearchDTO> searchDTOs = new ArrayList<SearchDTO>();
		final String query;
		query = " select a.OpportunityID,a.OpportunityName, b.CustomerName, c.Region, d.CadenceID, a.assignedTo, a.createdBy"
				+ " from Customer AS b, Country AS c,Opportunity AS a"
				+ " LEFT JOIN OpportunityDetail AS d ON a.OpportunityID = d.OpportunityID"
				+ " where a.CustomerID=b.CustomerID and b.CountryID=c.CountryID "
				+ (searchDTO.getOpportunityName() != null
						&& !(searchDTO.getOpportunityName().equals("")) ? " and a.OpportunityName LIKE '%"
						+ searchDTO.getOpportunityName() + "%' "
						: "")
				+ (searchDTO.getCustomerName() != null
						&& !(searchDTO.getCustomerName().equals("")) ? " and b.CustomerName LIKE '%"
						+ searchDTO.getCustomerName() + "%' "
						: "")
				+ (searchDTO.getRegion() != null
						&& !(searchDTO.getRegion().equals("")) ? " and c.Region LIKE '%"
						+ searchDTO.getRegion() + "%' "
						: "");

		logger.info("query : " + query);
		List<Object> dtos = (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery qry = session.createSQLQuery(query);
						List<Object> results = (List<Object>) qry.list();
						return results;
					}
				});
		for (Object item : dtos) {
			Object[] element = (Object[]) item;
			SearchDTO searchDTO2 = new SearchDTO();
			searchDTO2.setOpportunityID((Integer) element[0]);
			searchDTO2.setOpportunityName((String) element[1]);
			searchDTO2.setCustomerName((String) element[2]);
			searchDTO2.setRegion((String) element[3]);
			searchDTO2.setAssignedTo((String) element[5]);
			//Currency code variable is used for Created By value 
			searchDTO2.setCurrencyCode((String) element[6]);
			searchDTOs.add(searchDTO2);
		}

		logger.info("search dtos list for re-assignment : " + searchDTOs.size());
		return searchDTOs;
	}

	@Override
	public String updateOpportunity(SearchDTO searchDTO) {
		String[] ids = searchDTO.getSelected().split("_");
		Integer opportunityId = Integer.parseInt(ids[0]);
		/*final String query = "update Opportunity set AssignedTo='"
				+ searchDTO.getCurrencyCode().trim() + "' where OpportunityID="
				+ opportunityId;*/
		
		final String query = "update Opportunity set CreatedBy='"
				+ searchDTO.getCurrencyCode().trim() + "' where OpportunityID="
				+ opportunityId;
		
		logger.info("update query : " + query);
		String id = (String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery sq = session.createSQLQuery(query);
						Integer counts = sq.executeUpdate();
						return counts.toString();
					}
				});
		return id;
	}

	@Override
	public List<OpportunitySource> getOpportunitySources() {
		return getObjects(OpportunitySource.class);
	}

	@Override
	public List<CommercialModel> getCommercialModels() {
		return getObjects(CommercialModel.class);
	}

	@Override
	public List<DeliveryModel> getDeliveryModels() {
		return getObjects(DeliveryModel.class);
	}

	@Override
	public List<DeliveryType> getDeliveryTypeModels() {
		return getObjects(DeliveryType.class);
	}

	@Override
	public List<ScopeOfServicesDefinedBy> getScopeDefinedBy() {
		return getObjects(ScopeOfServicesDefinedBy.class);
	}

	@Override
	public List<InputVolumetricsDefinedBy> getVolumetricsDefinedBy() {
		return getObjects(InputVolumetricsDefinedBy.class);
	}

	@Override
	public boolean updateTable(String file, String filePath, String tableName) {

		final String query;
		String tempQuery = "";
		String path = filePath + file;

		logger.info("file to be loaded : " + path);

		if ("ratecard".equals(tableName)) {
			tempQuery = "LOAD DATA LOCAL INFILE '"
					+ path
					+ "' REPLACE INTO TABLE '"
					+ tableName
					+ "' fields terminated by ',' (RateCardID,JobRoleID,Location,RateINR,CreatedBy,CreatedTimestamp,UpdatedBy,UpdatedTimestamp);";
		} else if ("country".equals(tableName)) {
			tempQuery = "LOAD DATA LOCAL INFILE '"
					+ path
					+ "' REPLACE INTO TABLE '"
					+ tableName
					+ "' fields terminated by ',' (CountryID,CountryCode,CountryName,TimeZone,CurrencyCode,CurrencyName,Region,Active);";
		} else if ("exchangerate".equals(tableName)) {
			tempQuery = "LOAD DATA LOCAL INFILE '"
					+ path
					+ "' REPLACE INTO TABLE '"
					+ tableName
					+ "' fields terminated by ',' (RateCardID,JobRoleID,Location,RateINR,CreatedBy,CreatedTimestamp,UpdatedBy,UpdatedTimestamp);";
		} else if ("currency".equals(tableName)) {
			tempQuery = "LOAD DATA LOCAL INFILE '"
					+ path
					+ "' REPLACE INTO TABLE '"
					+ tableName
					+ "' fields terminated by ',' (CountryID,CountryCode,CountryName,TimeZone,CurrencyCode,CurrencyName,Region,Active);";
		}
		query = tempQuery;
		Integer count = 0;
		logger.info("query : " + query);
		try {
			count = (Integer) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							SQLQuery sq = session.createSQLQuery(query);
							Integer counts = sq.executeUpdate();
							return counts;
						}
					});
		} catch (Exception e) {
			return false;
		}
		logger.info("rows affected : " + count);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteSolution(String solutionId) throws DAOException {
		logger.info("Going to soft delete solution with solutionId:: "
				+ solutionId);
		boolean deleted = false;
		Solution s = new Solution();
		Status status = new Status();
		String query = "from Solution s where s.solutionId=" + solutionId;
		logger.info("query : " + query);
		List<Solution> solution = getHibernateTemplate().find(query);
		try {
			if (solution != null) {
				Solution sol = solution.get(0);
				status.setStatusId(SOLUTION_STATUS_DELETED);
				sol.setStatus(status);
				sol.setUpdatedDate(new Date());
				sol.setUpdatedBy("Admin");
				updateObject(sol);
				deleted = true;
			}
		} catch (Exception e) {
			logger.error("Operation failed with error:: " + e.getMessage());
			deleted = false;
		}
		return deleted;
	}

	@Override
	public String getSteadyStateStartDate(Integer opportunityID) {
		String query = "from OpportunityDetail where OpportunityID='"
				+ opportunityID + "'";
		String steadyStateStartDate = "";
		List<OpportunityDetail> oppD = getHibernateTemplate().find(query);
		for (OpportunityDetail opportunityDetail : oppD) {
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			steadyStateStartDate = formatter.format(opportunityDetail
					.getSteadyStateStartDate());
		}
		return steadyStateStartDate;
	}

	@Override
	public List<ExchangeRateDTO> getExchangeRateList() {
		String query = "from ExchangeRate";
		ExchangeRateDTO exchangeRateDTO = null;
		List<ExchangeRateDTO> exchangeRateDTOs = new ArrayList<ExchangeRateDTO>();
		List<ExchangeRate> exchangeRates = getHibernateTemplate().find(query);
		for (ExchangeRate exchangeRate : exchangeRates) {
			exchangeRateDTO = new ExchangeRateDTO();

			exchangeRateDTO.setCreatedOn(exchangeRate.getCreatedOn());
			exchangeRateDTO.setEndDate(exchangeRate.getEndDate());
			exchangeRateDTO.setExchangeRateID(exchangeRate.getExchangeRateId());

			exchangeRateDTO.setPound(exchangeRate.getPound());
			exchangeRateDTO.setDollarRs(exchangeRate.getDollarRs());
			exchangeRateDTO.setPoundRs(exchangeRate.getPoundRs());
			exchangeRateDTO.setStartDate(exchangeRate.getStartDate());
			exchangeRateDTO.setUSD(exchangeRate.getUsd());
			exchangeRateDTO.setDollarPound(exchangeRate.getDollarPound());
			exchangeRateDTO.setPoundDollar(exchangeRate.getPoundDollar());

			exchangeRateDTOs.add(exchangeRateDTO);
		}
		return exchangeRateDTOs;
	}

	@Override
	public String getCurrencyCode(Integer opportunityID) {
		Session session = getSession();
		String query = "select CurrencyCode from Country where CountryID=(select CountryID from Customer where CustomerID=(select CustomerID from Opportunity where OpportunityID="
				+ opportunityID + "))";

		String currencyCode = null;
		SQLQuery sql = session.createSQLQuery(query);
		List<String> temp = sql.list();

		currencyCode = temp.get(0);
		session.close();
		return currencyCode;
	}

	@Override
	public List<Object> getEntitiesObjBySolutionID(String conditionalSQL) {
		return getHibernateTemplate().find(conditionalSQL);
	}

	@Override
	public void saveObjects(List<Object> objectsList) {
		batchSaveUpdate(objectsList);
	}

	// to save labor cost
	public void saveLaborCost(LaborCostDTO lcDTO) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.createQuery(
					"delete from LaborCost where solutionID= "
							+ lcDTO.getSolutionID()).executeUpdate();
			for (int i = 0; i < lcDTO.getRowList().size(); i++) {

				LaborCostDTO.Row serviceRow = lcDTO.getRowList().get(i);
				// save onshore data
				for (int j = 0; j < serviceRow.getJobRoleListOnshore().size(); j++) {
					for (int k = 0; k < lcDTO.getOpportunityInterval().length; k++) {
						LaborCost cost = new LaborCost();
						cost.setServiceScopeID(fetchServiceScopeFromOppScope(session, serviceRow.getServiceScoprID()));
						cost.setSolutionID(lcDTO.getSolutionID());
						cost.setJobRoleID(serviceRow.getJobRoleListOnshore().get(j));
						cost.setLocation("ONSHORE");
						int mnth=getMonthNumber(lcDTO.getOpportunityInterval()[k].split("-")[0]);
						int yr=Integer.parseInt(lcDTO.getOpportunityInterval()[k].split("-")[1]);
						cost.setMonth(mnth);
						cost.setYear(yr);
						try {
							cost.setCost(Float.parseFloat(serviceRow.getFonshore().get(j)[k]));
						} catch (NumberFormatException e1) {
							cost.setCost(0f);
						}
						session.saveOrUpdate(cost);
					}
				}
				// save offshore data
				for (int j = 0; j < serviceRow.getJobRoleListOffshore().size(); j++) {
					for (int k = 0; k < lcDTO.getOpportunityInterval().length; k++) {
						LaborCost cost = new LaborCost();
						cost.setServiceScopeID(fetchServiceScopeFromOppScope(
								session, serviceRow.getServiceScoprID()));
						cost.setSolutionID(lcDTO.getSolutionID());
						cost.setJobRoleID(serviceRow.getJobRoleListOnshore()
								.get(j));
						cost.setLocation("OFFSHORE");
						try {
							cost.setCost(Float.parseFloat(serviceRow
									.getFoffshore().get(j)[k]));
						} catch (NumberFormatException e1) {
							cost.setCost(0f);
						}
						int mnth = getMonthNumber(lcDTO
								.getOpportunityInterval()[k].split("-")[0]);
						int yr = Integer.parseInt(lcDTO
								.getOpportunityInterval()[k].split("-")[1]);
						cost.setMonth(mnth);
						cost.setYear(yr);
						session.saveOrUpdate(cost);
					}
				}
			}
			tx.commit();
		} catch (Exception e) {
			logger.error("Exception in SolutionDaoImpl[saveLaborCost] ", e);
			tx.rollback();
			logger.info("SolutionDaoImpl[saveLaborCost] Successfully rolled back");
			// throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		} finally {
			session.close();
		}
	}

	private int fetchServiceScopeFromOppScope(Session session, int oppScopeId) {
		int serviceScopeId = (Integer) session
				.createSQLQuery(
						"select ServiceScopeID from OpportunityScope where OpportunityScopeID="
								+ oppScopeId).list().get(0);
		return serviceScopeId;
	}

	private int getMonthNumber(String month) {
		int monthNum = 999;
		if ("Jan".equals(month)) {
			monthNum = 1;
		} else if ("Feb".equals(month)) {
			monthNum = 2;
		} else if ("Mar".equals(month)) {
			monthNum = 3;
		} else if ("Apr".equals(month)) {
			monthNum = 4;
		} else if ("May".equals(month)) {
			monthNum = 5;
		} else if ("Jun".equals(month)) {
			monthNum = 6;
		} else if ("Jul".equals(month)) {
			monthNum = 7;
		} else if ("Aug".equals(month)) {
			monthNum = 8;
		} else if ("Sep".equals(month)) {
			monthNum = 9;
		} else if ("Oct".equals(month)) {
			monthNum = 10;
		} else if ("Nov".equals(month)) {
			monthNum = 11;
		} else if ("Dec".equals(month)) {
			monthNum = 12;
		}
		return monthNum;

	}

	@Override
	public Map<Integer, String> getServiceScopeNames(Integer solutionID) {
		final String query = "select ServiceScopeName, OpportunityScopeID from ServiceScope a, OpportunityScope b "
				+ " where a.ServiceScopeID=b.ServiceScopeID and OpportunityID in (select OpportunityID from Solution where SolutionID="
				+ solutionID + ")";

		Map<Integer, String> serviceScopes = new HashMap<Integer, String>();
		List<Object> dtos = (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery qry = session.createSQLQuery(query);
						List<Object> results = (List<Object>) qry.list();
						return results;
					}
				});
		for (Object item : dtos) {
			Object[] element = (Object[]) item;
			serviceScopes.put((Integer) element[1], (String) element[0]);
		}
		return serviceScopes;
	}

	@Override
	public List<FteHeadCountAndPercentageDTO> getFteHeadCount(Integer solutionID) {
		final String query = "select OpportunityScopeID, a.JobRoleStageID, YEAR(MonthYear) Year,SUM(IF(FTECount<0,0,FTECount)) FTECount , "
				+ " c.RoleName, d.Stage from FTEStaging a, JobRoleStages b, JobRole c, JobStage d  " 
				+ "where a.JobRoleStageID = b.JobRoleStageID and b.JobRoleID= c.JobRoleID and b.JobStageID= d.JobStageID  " 
				+" group by solutionID,OpportunityScopeID,a.JobRoleStageID,YEAR(MonthYear) "
				+ "having solutionID="
				+ solutionID
				+ " and OpportunityScopeID in "
				+ "( select OpportunityScopeID from OpportunityScope where OpportunityID in "
				+ "(select OpportunityID from Solution where SolutionID="
				+ solutionID + "))";

		List<FteHeadCountAndPercentageDTO> headCountAndPercentageDTOs = new ArrayList<FteHeadCountAndPercentageDTO>();
		List<Object> dtos = (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery qry = session.createSQLQuery(query);
						List<Object> results = (List<Object>) qry.list();
						return results;
					}
				});
		for (Object item : dtos) {

			Object[] element = (Object[]) item;
			FteHeadCountAndPercentageDTO fteHeadCountAndPercentageDTO = new FteHeadCountAndPercentageDTO();

			fteHeadCountAndPercentageDTO
					.setOpportunityScopeID((Integer) element[0]);
			fteHeadCountAndPercentageDTO.setJobRoleStageID((Integer) element[1]);
			fteHeadCountAndPercentageDTO.setYear(String.valueOf(element[2]));
			fteHeadCountAndPercentageDTO.setFteHeadCount((Double) element[3]);
			fteHeadCountAndPercentageDTO.setJobRoleDesc( element[4]+"("+element[5]+")");

			headCountAndPercentageDTOs.add(fteHeadCountAndPercentageDTO);
		}
		return headCountAndPercentageDTOs;
	}

	@Override
	public List<FteHeadCountAndPercentageDTO> getDistributionPercentage(
			Integer solutionID) {
		final String query = "select OpportunityScopeID, JobRoleID, YEAR(MonthYear) Year,SUM(DistributionPC) DistributionPC "
				+ "from LocationPyramid a group by solutionLeverID,OpportunityScopeID,a.JobRoleID,YEAR(MonthYear) "
				+ "having solutionLeverID=(select max(SolutionLeverID) from SolutionLever where solutionID="
				+ solutionID
				+ ") and "
				+ "OpportunityScopeID in "
				+ "( select OpportunityScopeID from OpportunityScope where OpportunityID in "
				+ "(select OpportunityID from Solution where SolutionID="
				+ solutionID + "))";

		List<FteHeadCountAndPercentageDTO> headCountAndPercentageDTOs = new ArrayList<FteHeadCountAndPercentageDTO>();
		List<Object> dtos = (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery qry = session.createSQLQuery(query);
						List<Object> results = (List<Object>) qry.list();
						return results;
					}
				});

		for (Object item : dtos) {

			Object[] element = (Object[]) item;
			FteHeadCountAndPercentageDTO fteHeadCountAndPercentageDTO = new FteHeadCountAndPercentageDTO();

			fteHeadCountAndPercentageDTO
					.setOpportunityScopeID((Integer) element[0]);
			fteHeadCountAndPercentageDTO.setJobRoleStageID((Integer) element[1]);
			fteHeadCountAndPercentageDTO.setYear(String.valueOf(element[2]));
			fteHeadCountAndPercentageDTO
					.setDistributionPercentage((Double) element[3]);

			headCountAndPercentageDTOs.add(fteHeadCountAndPercentageDTO);
		}
		return headCountAndPercentageDTOs;
	}

	/**
	 * Returns all Service Elements from database
	 * 
	 * @return List<ServiceElement>
	 * @throws DAOException
	 */
	public List<ServiceElement> getAllServiceElement(String serviceType) throws DAOException {
		//List<ServiceElement> listServiceElement = getHibernateTemplate().find(" from ServiceElement where Active='Y' and ServiceType='"+serviceType+"' order by ServiceFunctionName");
		List<ServiceElement> listServiceElement = getHibernateTemplate().find(" from ServiceElement where Active='Y' order by ServiceFunctionName");
		return listServiceElement;
	}

	/**
	 * Returns list of service scopes for a particular service element
	 * 
	 * @param serviceElements
	 * @return List<ServiceScope>
	 * @throws DAOException
	 */
	public List<ServiceScope> getServiceScopeByServiceElement(
			String serviceElements) throws DAOException {
		final StringBuilder query = new StringBuilder()
				.append("select s from ServiceScope s WHERE s.serviceElement.serviceElementID in (")
				.append(serviceElements).append(")")
				.append(" and s.active = '").append(MSSPConstants.ACTIVE)
				.append("'").append(" Order by s.serviceScopeCategory");

		List<ServiceScope> serviceScopeList = new ArrayList<ServiceScope>();

		List<Object> dtos = (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query qry = session.createQuery(query.toString());
						List<Object> results = null;
						try {
							results = (List<Object>) qry.list();
						} catch (Exception e) {
							e.printStackTrace();
						}
						return results;
					}
				});

		for (Object item : dtos) {
			serviceScopeList.add((ServiceScope) item);
		}

		return serviceScopeList;
	}

	@Override
	public List<UserAccessDTO> searchLDAPUsers(String signumId) {
		// TODO Auto-generated method stub

		List<UserAccessDTO> searchResultList = new ArrayList<UserAccessDTO>();
		Hashtable<String, String> env = new Hashtable<String, String>();
		DirContext ctx = null;
		SearchResult sr = null;
		String search_base = "ou=users,ou=internal,o=ericsson";
		String[] my_attributes = { "mail", "uid", "cn", "telephonenumber",
				"sn", "employeeType", "manager", "givenName" };

		/* connect to the server */

		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ecd.ericsson.se:389/");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL,
				"uid=egiotrs,ou=users,ou=internal,o=ericsson");
		env.put(Context.SECURITY_CREDENTIALS, "@WSX3edc4RFV%tgb");

		Enumeration vals = null;
		String[] temp = new String[my_attributes.length];

		try {
			ctx = new InitialDirContext(env);

			SearchControls constraints = new SearchControls();
			constraints.setReturningAttributes(my_attributes);
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			NamingEnumeration results = null;

			signumId = signumId == null ? "" : signumId;

			results = ctx.search(search_base, "(uid=" + signumId + "*" + ")",
					constraints);

			int user = 0;
			int index = 0;
			while (results != null && results.hasMore()) {
				UserAccessDTO uad = new UserAccessDTO();
				user++;
				index++;
				sr = (SearchResult) results.next();
				String dn = sr.getName() + "," + search_base;
				Attributes attr = ctx.getAttributes(dn, my_attributes);

				if (attr == null) {
					return searchResultList;
				}
				int i = 0;
				for (; i < my_attributes.length; i++) {
					Attribute arr = attr.get(my_attributes[i]);
					if (arr == null) {
						temp[i] = "-";
						continue;
					}
					for (vals = arr.getAll(); vals.hasMoreElements();) {
						temp[i] = (vals.nextElement()).toString();
					}
				}
				i = 0;
				uad.setId(index);
				uad.setEmailId(temp[0]);
				uad.setSignumId(temp[1]);
				uad.setFirstName(temp[7]);
				uad.setContactNumber(temp[3]);
				uad.setLastName(temp[4]);

				searchResultList.add(uad);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				ctx.close();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return searchResultList;
	}

	@Override
	public List<FTEStagingDTO> getFTEStagingData(Integer solutionID,
			Integer opportunityScopeID) {
		String query = "from FTEStaging where solution.solutionId="
				+ solutionID + " and opportunityScope.opportunityScopeId="
				+ opportunityScopeID;

		FTEStagingDTO fteStagingDTO;

		List<FTEStagingDTO> fteStagingDTOList = new ArrayList<FTEStagingDTO>();

		List<FTEStaging> fteStagingData = getHibernateTemplate().find(query);
		JobRoleStagesDTO jobRoleStagesDTO = null;
		JobStageDTO jobStageDTO = null;
		JobRoleDTO jobRoleDTO = null;
		
		for (FTEStaging fteStaging : fteStagingData) {
			
			jobRoleDTO = new JobRoleDTO();
			jobStageDTO = new JobStageDTO();
			fteStagingDTO = new FTEStagingDTO();
			jobRoleStagesDTO = new JobRoleStagesDTO();
			
			try{
			org.apache.commons.beanutils.BeanUtils.copyProperties(jobRoleDTO,fteStaging.getJobRoleStage().getJobRole());
			org.apache.commons.beanutils.BeanUtils.copyProperties(jobStageDTO,fteStaging.getJobRoleStage().getJobStage() );
			org.apache.commons.beanutils.BeanUtils.copyProperties(jobRoleStagesDTO,fteStaging.getJobRoleStage());
			org.apache.commons.beanutils.BeanUtils.copyProperties(fteStagingDTO,fteStaging);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			jobRoleStagesDTO.setJobRoleDTO(jobRoleDTO);
			jobRoleStagesDTO.setJobStageDTO(jobStageDTO);
			
			fteStagingDTO.setJobRoleStagesDTO(jobRoleStagesDTO);
			
			fteStagingDTOList.add(fteStagingDTO);
		}

		return fteStagingDTOList;
	}

	@Override
	public double getRateForCurrentJobRole(Integer countryID,
			Integer jobRoleStageID, String location,String subLocation, String gsc,boolean isSubmitted,String solSubDate) {
		
		System.out.println("inside getRateForCurrentJobRole");
		double rate=0;
		
		if(isSubmitted){//fetch from rate card history
			
			System.out.println("inside isSubmitted");
			
			String checkRow= "from RateCardHistory rch where hrc.country.countryId="+countryID;
			
			List<RateCardHistory> testList = getHibernateTemplate().find(checkRow);
			
			if(testList.size() == 0){
				System.out.println("indside testList");
				String query = "from RateCard rc where rc.country.countryId="
						+ countryID + " and rc.jobRoleStages.jobRoleStagesId="
						+ jobRoleStageID + " and rc.location='"+location+"' and rc.gsc='"+gsc+"'";
				
				
					List<RateCard> rateCard = getHibernateTemplate().find(query);
				
					for (RateCard rateCard2 : rateCard) {
						rate = rateCard2.getRate();
					}
				return rate;
			}
			
			final String query = "rch.rate from RateCardHistory rch where Date(rch.createdTimestamp) in " +
					"(select max(Date(hrc.createdTimestamp)) from RateCardHistory hrc where hrc.country.countryId="+countryID+" " +
					"and Date(hrc.createdTimestamp) <= '"+solSubDate+"')" +
					"and rch.country.countryId="+countryID+ " and rch.location="+location+" and rch.gsc="+gsc;
		
			
			List<RateCardHistory> dto = (List<RateCardHistory>) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							Query qry = session.createQuery(query.toString());
							List<RateCardHistory> results = null;
							try {
								results = (List<RateCardHistory>) qry.list();
							} catch (Exception e) {
								e.printStackTrace();
							}
							return results;
						}
					});
			
			for(RateCardHistory cardHistory : dto){
				
				rate = cardHistory.getRate();
			}
		}else{
			String query = "";
			System.out.println("inside isSubmitted as false");
			if(("Nearshore".equalsIgnoreCase(location))||("Offshore").equalsIgnoreCase(location)){
			query = "from RateCard rc where rc.country.countryId="
				+ countryID + " and rc.jobRoleStages.jobRoleStagesId="
				+ jobRoleStageID + " and rc.location='"+location+"' and rc.gsc='"+gsc+"'";
			}
			else{
				query = "from RateCard rc where rc.country.countryId="
						+ countryID + " and rc.jobRoleStages.jobRoleStagesId="
						+ jobRoleStageID + " and rc.location='"+location+"' and rc.gsc='"+gsc+"' and rc.subLocation='"+subLocation+"'";
			}
		
			List<RateCard> rateCard = getHibernateTemplate().find(query);
		
			for (RateCard rateCard2 : rateCard) {
				rate = rateCard2.getRate();
			}
		
		}
		return rate;
	}

	@Override
	public List<JobRole> getJobRoleListForCCM(Integer flag) {
		
		String query = "from JobRole where CCMFlag="+flag;
		
		List<JobRole> jobRoles = getHibernateTemplate().find(query);
				
		return jobRoles;
	}

	@Override
	public List<RateCard> loadRateCards(Integer countryID) {
		
		String query = "from RateCard where country.countryId="+countryID;
		
		List<RateCard> rateCards = getHibernateTemplate().find(query);
		
		return rateCards;
	}

	@Override
	public List<JobRoleStages> getJobStages(Integer jobRoleID) {
		 
		List<JobStage> jobStages = new ArrayList<JobStage>();
		
			final String query = "from JobRoleStages jrs where jrs.jobRole.jobRoleId="+jobRoleID;
		
			List<JobRoleStages> list1 = getHibernateTemplate().find(query);

			logger.info("query for job stages : " + query);
	
		return list1;
	}
	
	@Override
	public void updateServiceType(Integer oppId, String serviceType) throws Exception {
		
			String query = "from OpportunityDetail where OpportunityId="+oppId;
		
			List<OpportunityDetail> oppDetailList = getHibernateTemplate().find(query);
			
			
			OpportunityDetail opportunityDetail = (OpportunityDetail)oppDetailList.get(0);
		
			opportunityDetail.setServiceType(serviceType);
			
			saveObject(opportunityDetail);
	}

	
	public Map<String,Float> getUtilizationPerYearDefaultValues(){
		final String query = "select OtherfieldsName, DefaultValues from OtherDefaults where ApplicationArea='UtilizationPerYear'";
		
		logger.info("query : " + query);
		List<Object> dtos = (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery qry = session.createSQLQuery(query);
						List<Object> results = (List<Object>) qry.list();
						return results;
					}
				});
		
		Map<String,Float> map = new HashMap<>();
		Map<String,Float> map1 = new HashMap<>();
		
		for (Object item : dtos) {
			Object[] element = (Object[]) item;
			map.put((String)element[0], Float.parseFloat((String)element[1]));
		}
		
		for (Map.Entry<String,Float> object : map.entrySet()) {
			if("onshoreLocal".equalsIgnoreCase(object.getKey())){
				map1.put("onshoreLocal",object.getValue());
			}else if("onshoreGSC".equalsIgnoreCase(object.getKey())){
				map1.put("onshoreGSC",object.getValue());
			}else if("onshore3PP".equalsIgnoreCase(object.getKey())){
				map1.put("onshore3PP",object.getValue());
			}else if("nearShore".equalsIgnoreCase(object.getKey())){
				map1.put("nearShore",object.getValue());
			}else if("offShore".equalsIgnoreCase(object.getKey())){
				map1.put("offShore",object.getValue());
			}
		}
		
		return map1;
	}

	@Override
	public List<Component> getAllComponents() {
		String query = "from Component";
		List<Component> components = getHibernateTemplate().find(query);
		return components;
	}

	@Override
	public void saveOpportunityComponent(List<OpportunityComponent> list) {
		
		
		for(OpportunityComponent entity : list){
			getHibernateTemplate().save(entity);
		}
		
	}

	@Override
	public List<Component> getSelectedComponents(List<Integer> ComponentIDs) {
		StringBuilder sb = new StringBuilder();
		for(Integer componentID : ComponentIDs){
			sb.append("'"+componentID+"',");
		}
		String str = sb.substring(0, sb.length()-1);
		String query = "from Component where ComponentID IN ("+str+")";
		List<Component> list = getHibernateTemplate().find(query);
		return list;
	}

	@Override
	public List<OpportunityComponent> getComponentsByOpportunityID(Integer oppId) throws MSSPException{
		String query = "from OpportunityComponent where OpportunityID="+oppId;
		List<OpportunityComponent> list = getHibernateTemplate().find(query);
		return list;
	}

	@Override
	public void removeOpportunityCompByOppId(Integer oppId) {
		final String query  = "delete from OpportunityComponent where OpportunityID = "+oppId;
		Integer deltdCount = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery sq = session.createSQLQuery(query);
						int counts = sq.executeUpdate();
						return Integer.valueOf(counts);
					}

				});
		logger.info(deltdCount + " records deleted");
		
	}

	public ServiceScopeDTO getServiceScopeForOpportunityScopeId(Integer opportunityScopeID) throws DAOException {
		
		System.out.println("opportunityScopeID======================in getServiceScopeForOpportunityScopeId ================="+opportunityScopeID);
		List<OpportunityScope> opportunityScopeList = getHibernateTemplate()
				.find(" from OpportunityScope where opportunityScopeId ="+ opportunityScopeID);

		List<ServiceScopeDTO> serviceScopeDTOList = new ArrayList<ServiceScopeDTO>();
		ServiceScopeDTO ServiceScopeDTO = null;

		for (OpportunityScope oppscope : opportunityScopeList) {
			serviceScopeDTOList.add(setSSEntityIntoSSDTO(oppscope
					.getServiceScope()));
		}
		if (serviceScopeDTOList!=null && serviceScopeDTOList.size() > 0) {
			ServiceScopeDTO = serviceScopeDTOList.get(0);
		}
		return ServiceScopeDTO;
	}
	
	@Override
	public Solution getSolution(Integer solutionId) throws DAOException {
		Solution solution = null;
		
		List<Solution> list  = null;
		
		list = getHibernateTemplate().find(" from Solution where solutionId=" + solutionId);
		if (list!=null && list.size() > 0) {
			solution = list.get(0);
		}
		return solution;
	}
	

	@Override
	public List<ProductDetails> fetchProductList() throws DAOException {
		List<ProductDetails> productDetailsList = getHibernateTemplate().find(
				" from ProductDetails");
		return productDetailsList;
	}

	@Override
	public List<Component> getComponentByProduct(Integer productID)
			throws DAOException {
		String query = "from Component where ProductID=?";
		List<Component> components = getHibernateTemplate().find(query,productID);
		return components;
	}
	@Override
	public void saveOpportunityScopesFromServiceElements(Integer oppId,
			String selectedServiceElements) throws DAOException {
		HashMap<Integer, OpportunityScope> opportunityScopeMap = new HashMap<>();
		List<Integer> opportunityScopesToBeRemoved = new ArrayList<>();
		List<ServiceScope> serviceScopeList = getServiceScopeByServiceElement(selectedServiceElements);
		List<OpportunityScope> opportunityScopesToBeAdded = new ArrayList<>();
		OpportunityScope tempOppScope = null;
		Opportunity tempOpp = null;
		ServiceScope tempServiceScope = null;
		
		List<OpportunityScope> opportunityScopeList = getHibernateTemplate()
				.find(" from OpportunityScope where opportunityID=?", oppId);
		
		for(OpportunityScope eachOpportunityScope: opportunityScopeList){
			opportunityScopeMap.put(eachOpportunityScope.getServiceScope().getServiceScopeId(), eachOpportunityScope);
		}
		
		for(ServiceScope eachScope: serviceScopeList){
			if(!opportunityScopeMap.containsKey(eachScope.getServiceScopeId())){
				// selected service scopes are not associated with opportunity - so needs to be added
				tempOpp = new Opportunity();
				tempOpp.setOpportunityId(oppId);
				
				tempServiceScope = new ServiceScope();
				tempServiceScope.setServiceScopeId(eachScope.getServiceScopeId());
				
				tempOppScope = new OpportunityScope();
				tempOppScope.setOpportunity(tempOpp);
				tempOppScope.setServiceScope(tempServiceScope);
				
				saveObject(tempOppScope);
			}else{				
				// opportunity scope processed - hence removed
				opportunityScopeMap.remove(eachScope.getServiceScopeId());
			}
		}
		
		// Remaining opportunity scopes are to be removed
		for(OpportunityScope eachObject: opportunityScopeMap.values()){
			removeObject(OpportunityScope.class, eachObject.getOpportunityScopeId());
		}
	}

	@Override
	public void removeOpportunityCompByOppId(Integer oppId,
			List<Integer> componentIdList) throws DAOException {
		StringBuilder sb = new StringBuilder();
		for(Integer componentID : componentIdList){
			sb.append("'"+componentID+"',");
		}
		String str = sb.substring(0, sb.length()-1);
		final String query  = "delete from OpportunityComponent where OpportunityID = "+oppId+" and ComponentID IN ("+str+")";
		System.out.println("QQQ = "+query);
		Integer deltdCount = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery sq = session.createSQLQuery(query);
						int counts = sq.executeUpdate();
						return Integer.valueOf(counts);
					}

				});
		logger.info(deltdCount + " records deleted");
		
	
		
	}
}
