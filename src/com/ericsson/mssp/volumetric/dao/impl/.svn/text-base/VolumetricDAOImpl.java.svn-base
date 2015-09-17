package com.ericsson.mssp.volumetric.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.dto.SupportWindowMatrixDTO;
import com.ericsson.mssp.common.entity.AccessManagement;
import com.ericsson.mssp.common.entity.AppMainSla;
import com.ericsson.mssp.common.entity.AppMainSupportActivity;
import com.ericsson.mssp.common.entity.AvailabilityManagement;
import com.ericsson.mssp.common.entity.Build;
import com.ericsson.mssp.common.entity.CapacityManagement;
import com.ericsson.mssp.common.entity.ChangeManagement;
import com.ericsson.mssp.common.entity.ConfigurationManagement;
import com.ericsson.mssp.common.entity.DemandSupport;
import com.ericsson.mssp.common.entity.DeploymentRollOut;
import com.ericsson.mssp.common.entity.Design;
import com.ericsson.mssp.common.entity.DesignAndBuild;
import com.ericsson.mssp.common.entity.JobStage;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.OtherDefaults;
import com.ericsson.mssp.common.entity.PostReleaseActivities;
import com.ericsson.mssp.common.entity.ProductEstimationAuxiliaryParams;
import com.ericsson.mssp.common.entity.ProductEstimationBaseEffortForSolution;
import com.ericsson.mssp.common.entity.ReleaseManagement;
import com.ericsson.mssp.common.entity.SecurityManagement;
import com.ericsson.mssp.common.entity.ServiceAssurance;
import com.ericsson.mssp.common.entity.ServiceScope;
import com.ericsson.mssp.common.entity.ProgramManagement;
import com.ericsson.mssp.common.entity.SupportWindowMatrix;
import com.ericsson.mssp.common.entity.TicketDistribution;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.volumetric.dao.VolumetricDAO;

@Repository
public class VolumetricDAOImpl extends BaseDAOImpl implements VolumetricDAO, MSSPConstants{

	@Override
	public List<OpportunityScopeDTO> getServiceScopeByServiceElement(Integer opportunityId, String selectCriteria) throws MSSPException{
		
		System.out.println("fetching adm support services for opportunity id ["+ opportunityId +"] with select criteria ["+selectCriteria+"]");
		
		@SuppressWarnings("unchecked")
		List<OpportunityScope> opportunityScopeList = getHibernateTemplate()
			.find(" from OpportunityScope os where opportunityID="
				+ opportunityId + " and os.serviceScope.serviceElement.serviceFunctionCode='"+selectCriteria +"' order by os.serviceScope.serviceScopeId");
		
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
			serviceScopeDTO.setServiceElementDefaultValue(serviceScope.getServiceElementDefaultValue());
			return serviceScopeDTO;
		    }
	 
	 @Override
		public List<ProgramManagement> loadProgramManagementListBySolutionId(
				Integer solId) throws MSSPException {

			//String query = "from ProgramManagement where solutionID=" + solId;
			//List<ProgramManagement> list = getHibernateTemplate().find(query);
			//Query query = getHibernateTemplate().getSessionFactory().openSession().getNamedQuery("get.ProgramManagement.bySolutionID");
			List<ProgramManagement> list = (List<ProgramManagement>) getHibernateTemplate().findByNamedQuery("get.ProgramManagement.bySolutionID",solId);
			
			//query.setInteger(0, solId);
			//List<ProgramManagement> list = query.list();
     		return list;
		}
		
		@Override
		public void saveProgramManagement(ProgramManagement programManagement)throws MSSPException {
			saveObject(programManagement);
		}
		
		@Override
		public List<ReleaseManagement> loadReleaseManagementListBySolutionId(
				Integer solId) throws MSSPException {
			
			/*String query = "from ReleaseManagement where solutionID=" + solId;
			List<ReleaseManagement> list = getHibernateTemplate().find(query);*/
			//Query query = getHibernateTemplate().getSessionFactory().openSession().getNamedQuery("get.releaseManagement.bySolutionID");
			//query.setInteger(0, solId);
			List<ReleaseManagement> list= (List<ReleaseManagement>) getHibernateTemplate().findByNamedQuery("get.releaseManagement.bySolutionID", solId);
			return list;
		}
		
		@Override
		public void saveReleaseManagement(ReleaseManagement releaseManagement)throws MSSPException {
			saveObject(releaseManagement);
		}
		
		@Override
		public List<ChangeManagement> loadChangeManagementListBySolutionId(
				Integer solId) {
			
			//String query = "from ChangeManagement where solutionID=" + solId;
			//List<ChangeManagement> list = getHibernateTemplate().find(query);
			/*Query query = getHibernateTemplate().getSessionFactory().openSession().getNamedQuery("get.changeManagement.bySolutionID");
			query.setInteger(0, solId);*/
			List<ChangeManagement> list = (List<ChangeManagement>) getHibernateTemplate().findByNamedQuery("get.changeManagement.bySolutionID", solId);
			return list;
			
			
		}
		
		@Override
		public void saveChangeManagement(ChangeManagement changeManagement) {
			// TODO Auto-generated method stub
			saveObject(changeManagement);
		}
		
		@Override
		public List<CapacityManagement> loadCapacityManagementListBySolutionId(
				Integer solId) {
			// TODO Auto-generated method stub
			
			//String query = "from CapacityManagement where solutionID=" + solId;
			//List<CapacityManagement> list = getHibernateTemplate().find(query);
			List<CapacityManagement> list = (List<CapacityManagement>) getHibernateTemplate().findByNamedQuery("get.CapacityManagement.bySolutionID", solId);
			return list;
		}
		
		@Override
		public void saveConfigurationManagement(ConfigurationManagement configurationManagement)throws MSSPException {
			saveObject(configurationManagement);
		}
		
		@Override
		public List<ConfigurationManagement> loadConfigurationManagementListBySolutionId(
				Integer solId) {
			//String query = "from ConfigurationManagement where solutionID=" + solId;
			//List<ConfigurationManagement> list = getHibernateTemplate().find(query);
			//List<ConfigurationManagement> list = getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(ConfigurationManagement.class).add(Restrictions.eq("configurationManagementID", solId)));
			List<ConfigurationManagement> list = (List<ConfigurationManagement>) getHibernateTemplate().findByNamedQuery("get.configurationManagement.bySolutionID", solId);
			return list;
		}
		
		@Override
		public void saveSecurityManagement(SecurityManagement securityManagement)throws MSSPException {
			saveObject(securityManagement);
		}
		
		@Override
		public List<SecurityManagement> loadSecurityManagementListBySolutionId(
				Integer solId) {
			
			//String query = "from SecurityManagement where solutionID=" + solId;
			//List<SecurityManagement> list = getHibernateTemplate().find(query);
			List<SecurityManagement> list = getHibernateTemplate().findByNamedQuery("get.securityManagement.bySolutionID", solId);
			return list;
		}
		
		@Override
		public void saveAvailabilityManagement(AvailabilityManagement availabilityManagement)throws MSSPException {
			saveObject(availabilityManagement);
		}
		
		@Override
		public List<AvailabilityManagement> loadAvailabilityManagementListBySolutionId(
				Integer solId) {
			// TODO Auto-generated method stub
			
			//String query = "from AvailabilityManagement where solutionID=" + solId;
			//List<AvailabilityManagement> list = getHibernateTemplate().find(query);
			
			List<AvailabilityManagement> list = getHibernateTemplate().findByNamedQuery("get.availabilityManagement.bySolutionID", solId);
			return list;
		}
		
		@Override
		public void saveAccessManagement(AccessManagement accessManagement)throws MSSPException {
			saveObject(accessManagement);
		}
		
		@Override
		public List<AccessManagement> loadAccessManagementListBySolutionId(
				Integer solId) {
			// TODO Auto-generated method stub
			
			String query = "from AccessManagement where solutionID=" + solId;
			List<AccessManagement> list = getHibernateTemplate().find(query);
			return list;
		}
		
		@Override
		public void saveCapacityManagement(CapacityManagement capacityManagement)throws MSSPException {
			saveObject(capacityManagement);
		}
		
		@Override
	    public List<String> getServiceBucketDataByOppSolutionID(
		    Integer opportunityID, Integer solutionID) {
		String queryString = "select distinct OpportunityScopeID, "
			+ "serviceScopeCategory,ServiceScopeName, ServiceFTE,"
			+ "NoDemandedPositions "
			+ "from ViewServiceBucket where opportunityID=" + opportunityID
			+ " and (SolutionID=" + solutionID
			+ " or SolutionID is NULL) order by ServiceScopeID";
		return getServiceBucketDataByQuery(queryString, solutionID,
			opportunityID);
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
			    isProductivityLeverAppliedIn1stYr = isProductivityLeverInFirstYear(
				    opportunityID, solutionID, opportunityScopeID);
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

		@Override
		public List<ServiceScopeDTO> getAllServiceScopeByServiceElement(
				String serviceelement) {
			
			System.out.println("fetching all adm support services with select criteria ["+serviceelement+"]");
			
			@SuppressWarnings("unchecked")
			List<ServiceScope> serviceScopeList = getHibernateTemplate()
				.find(" from ServiceScope ss where ss.serviceElement.serviceFunctionCode='"+serviceelement +"' order by ss.serviceScopeId");
			
			List<ServiceScopeDTO> serviceScopeDTOList = new ArrayList<ServiceScopeDTO>();
			ServiceScopeDTO serviceScopeDTO ;
			for (ServiceScope svcScope : serviceScopeList) {
				serviceScopeDTO = new  ServiceScopeDTO();
				serviceScopeDTO = setSSEntityIntoSSDTO(svcScope);
			    serviceScopeDTOList.add(serviceScopeDTO);
			}
			return serviceScopeDTOList;
		}

		// Demand Support 
		
		@Override
		public void saveDemandSupport(DemandSupport demandSupport)
				throws MSSPException {
			saveObject(demandSupport);
			
		}

		@Override
		public List<DemandSupport> loadDemandSupportListBySolutionId(
				Integer solId) throws MSSPException {
			String query = "from DemandSupport where solutionID=" + solId;
			List<DemandSupport> list = getHibernateTemplate().find(query);
     		return list;
		}

		/*// Design
		@Override
		public void saveDesign(Design design) throws MSSPException {
			saveObject(design);
			
		}

		@Override
		public List<Design> loadDesignListBySolutionId(Integer solId)
				throws MSSPException {
			String query = "from Design where solutionID=" + solId;
			List<Design> list = getHibernateTemplate().find(query);
			return list;
		}

		// Build
		@Override
		public void saveBuild(Build build) throws MSSPException {
			saveObject(build);
			
		}

		@Override
		public List<Build> loadBuildListBySolutionId(Integer solId)
				throws MSSPException {
			String query = "from Build where solutionID=" + solId;
			List<Build> list = getHibernateTemplate().find(query);
			return list;
		}*/

		// Deployment Roll Out
		
		@Override
		public void saveDeploymentRollOut(DeploymentRollOut deploymentRollOut)
				throws MSSPException {
			saveObject(deploymentRollOut);
			
		}

		@Override
		public List<DeploymentRollOut> loadDeploymentRollOutListBySolutionId(
				Integer solId) throws MSSPException {
			String query = "from DeploymentRollOut where solutionID="+solId;
			List<DeploymentRollOut> list = getHibernateTemplate().find(query);
			return list;
		}

		// Post Release Activities
		
		@Override
		public void savePostReleaseActivities(PostReleaseActivities postRelAct)
				throws MSSPException {
			saveObject(postRelAct);
			
		}

		@Override
		public List<PostReleaseActivities> loadPostReleaseActivitiesListBySolutionId(
				Integer solId) throws MSSPException {
			String query = "from PostReleaseActivities where solutionID="+solId;
			List<PostReleaseActivities> list = getHibernateTemplate().find(query);
			return list;
		}
		
		// Design And Build
		@Override
		public List<DesignAndBuild> loadDesignBuildListBySolutionId(
				Integer solId) throws MSSPException {
			String query = "from DesignAndBuild where solutionID="+solId;
			List<DesignAndBuild> list = getHibernateTemplate().find(query);
			return list;
		}

		@Override
		public void saveDesignBuild(DesignAndBuild designBuild)
				throws MSSPException {
			saveObject(designBuild);
			
		}

		@Override
		public List<OtherDefaults> loadDefaultValuesByServiceScopeID(Integer serviceScopeID) throws MSSPException {
			String query = "from OtherDefaults where ServiceScopeID="+serviceScopeID;
			List<OtherDefaults> list = getHibernateTemplate().find(query);
			return list;
		}

		@Override
		public OpportunityDetail getOpportunityDetail(Integer opportunityId)
				throws DAOException {
			String query = "from OpportunityDetail where opportunity.opportunityId=?";
			List<OpportunityDetail> opportunityDetails = getHibernateTemplate().find(query,opportunityId);
			
			return ((opportunityDetails!=null && opportunityDetails.size()>0)?opportunityDetails.get(0):new OpportunityDetail());
		}

		@Override
		public List<ServiceAssurance> loadServiceAssuranceListBySolutionId(
				Integer solId) throws MSSPException {
			List<ServiceAssurance> list = (List<ServiceAssurance>) getHibernateTemplate().findByNamedQuery("get.ServiceAssurance.bySolutionID",solId);
			return list;
		}

		@Override
		public void saveServiceAssurance(ServiceAssurance serviceAssurance)
				throws MSSPException {
			saveObject(serviceAssurance);
			
		}

		@Override
		public AppMainSupportActivity getSuppActivityByOppCompID(
				Integer oppCompId) throws MSSPException {
			String query = "from AppMainSupportActivity where OpportunityComponentID = "+oppCompId;
			List<AppMainSupportActivity> list = (List<AppMainSupportActivity>) getHibernateTemplate().find(query);
			return list.get(0);
		}

		@Override
		public void saveAppMainSupportActivity(AppMainSupportActivity appMainSupportActivity)
				throws MSSPException {
			saveObject(appMainSupportActivity);
			
		}

		@Override
		public void saveAppMainSLA(AppMainSla appMainSla) throws MSSPException {
			saveObject(appMainSla);
			
		}

		@Override
		public List<AppMainSla> getAppMainSlaDtoByOppId(Integer oppId)
				throws MSSPException {
			String query = "from AppMainSla where OpportunityID = "+oppId;
			List<AppMainSla> entityList = (List<AppMainSla>) getHibernateTemplate().find(query);
			return entityList;
		}

		@Override
		public List<OtherDefaults> getDefaultAppMainSlaDto()
				throws MSSPException {
			String query = "from OtherDefaults where ApplicationArea in ('P1','P2','P3','P4')";
			List<OtherDefaults> defList = (List<OtherDefaults>) getHibernateTemplate().find(query);
			return defList;
		}

		@Override
		public List<TicketDistribution> getTktDistByOppScopeIds(List<Integer> oppScopeIds) throws MSSPException {
			StringBuilder sb = new StringBuilder();
			for(Integer oppScopeID : oppScopeIds){
				sb.append("'"+oppScopeID+"',");
			}
			String str = sb.substring(0, sb.length()-1);
			String query = "from TicketDistribution where OpportunityScopeID IN ("+str+")";
			List<TicketDistribution> list = getHibernateTemplate().find(query);
			
			return list;
		}

		@Override
		public List<OtherDefaults> getDefTktDistByServEle(String serviceElement)
				throws MSSPException {
			//SELECT myCollection FROM A as a WHERE a.id = :id
			String query = "from OtherDefaults where applicationArea = '"+serviceElement+"'";
			List<OtherDefaults> defList = (List<OtherDefaults>) getHibernateTemplate().find(query);
			return defList;
		}

		@Override
		public void saveTicketDistribution(TicketDistribution entity)
				throws MSSPException {
			saveObject(entity);
			
		}

		@Override
		public List<AppMainSupportActivity> getSuppActListByOppCompIds(	List<Integer> oppCompIds) throws MSSPException {
			StringBuilder sb = new StringBuilder();
			for(Integer id : oppCompIds){
				sb.append("'"+id+"',");
			}
			String str = sb.substring(0, sb.length()-1);
			String query = "from AppMainSupportActivity where OpportunityComponentID IN ("+str+")";
			List<AppMainSupportActivity> list = (List<AppMainSupportActivity>) getHibernateTemplate().find(query);
			return list;
		}

		@Override
		public List<OtherDefaults> getDefSuppActivity() throws MSSPException {
			String query = "from OtherDefaults where applicationArea = 'SupportActivity'";
			List<OtherDefaults> list = (List<OtherDefaults>) getHibernateTemplate().find(query);
			return list;
		}

		@Override
		public SupportWindowMatrix getSupportWindowMatrixById(Integer suppWindowMatrixID) throws MSSPException {
			String query = "from SupportWindowMatrix where SupportWindowMatrixID="+suppWindowMatrixID;
			List<SupportWindowMatrix> list = (List<SupportWindowMatrix>) getHibernateTemplate().find(query);
			return list.get(0);
		}

		
		@Override
		public List<SupportWindowMatrix> getAllSupportWindowMatrix() throws MSSPException {
			String query = "from SupportWindowMatrix";
			List<SupportWindowMatrix> list = (List<SupportWindowMatrix>) getHibernateTemplate().find(query);
			return list;
		}

		@Override
		public List<Object> getFactorBySolutionId(Integer SolutionID, 	String ParamTypeCode) throws MSSPException {
			final String query = "select ParamValue from ProductEstimationAuxiliaryParams where ProductEstimationAuxiliaryParamsID=(select ProductEstimationAuxiliaryParamsID from ProductEstimationAuxiliaryParamsForSolution where SolutionID="+SolutionID+" and ParamTypeCode='"+ParamTypeCode+"')";
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<Object> results = (List<Object>) getHibernateTemplate()
					.execute(new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							SQLQuery qry = session.createSQLQuery(query);
							List<Object> results = (List<Object>) qry.list();
							return results;
						}

					});
			
			return results;
		}

		@Override
		public List<Object> getDefaultFactor(String paramTypeCode)
				throws MSSPException {
			final String query = "select ParamValue from ProductEstimationAuxiliaryParams where ParamTypeCode='"+paramTypeCode+"'";
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<Object> results = (List<Object>) getHibernateTemplate()
					.execute(new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							SQLQuery qry = session.createSQLQuery(query);
							List<Object> results = (List<Object>) qry.list();
							return results;
						}

					});
			System.out.println("Results : "+results);
			return results;
		}

		@Override
		public List<Object[]> getTotalBaseHrsFteForDevMain(Integer solId)
				throws MSSPException {
			String query = "select sum(totalBaseHours),sum(totalBaseFTE) from ProductEstimationBaseEffortForSolution os where SolutionID="+solId+"and (os.opportunityScope.serviceScope.serviceElement.serviceFunctionCode='APP_MAIN' OR os.opportunityScope.serviceScope.serviceElement.serviceFunctionCode='APP_DEV') order by os.opportunityScope.opportunityScopeId";
			@SuppressWarnings("unchecked")
			List<Object[]> list = getHibernateTemplate().find(query);
			return list;
		}

		@Override
		public List<ProductEstimationBaseEffortForSolution> getProductEstBaseEffData(
				List<Integer> oppScopeIdList) throws MSSPException {
			StringBuilder sb = new StringBuilder();
			for(Integer id : oppScopeIdList){
				sb.append("'"+id+"',");
			}
			String str = sb.substring(0, sb.length()-1);
			String query = "from ProductEstimationBaseEffortForSolution where OpportunityScopeID IN ("+str+")";
			List<ProductEstimationBaseEffortForSolution> list = (List<ProductEstimationBaseEffortForSolution>) getHibernateTemplate().find(query);
			return list;
		}

		@Override
		public List<OtherDefaults> getDefTktDistByServEleServScopes(
				String serviceElement, List<Integer> addedServElementIds)
				throws MSSPException {
			StringBuilder sb = new StringBuilder();
			for(Integer id : addedServElementIds){
				sb.append("'"+id+"',");
			}
			String str = sb.substring(0, sb.length()-1);
			String queryString = "from OtherDefaults where ServiceScopeID IN ("+str+") and ApplicationArea = '"+serviceElement+"'";
			List<OtherDefaults> list = getHibernateTemplate().find(queryString);
			return list;
		}

		@Override
		public List<OtherDefaults> getDefSuppActivityByCompName(
				List<String> compNames) throws MSSPException {
			StringBuilder sb = new StringBuilder();
			for(String name : compNames){
				sb.append("'"+name+"',");
			}
			String str = sb.substring(0, sb.length()-1);
			String queryString = "from OtherDefaults where applicationArea = 'SupportActivity' and OtherFieldsName IN ("+str+")";
			List<OtherDefaults> list = (List<OtherDefaults>) getHibernateTemplate().find(queryString);
			return list;
		}

		@Override
		public boolean opportunityHasServiceElement(Integer opportunityId,
				String serviceElementName) throws DAOException {
			boolean hasServiceElement = false;
			List<OpportunityScope> opportunityScopeList = getHibernateTemplate()
					.find(" from OpportunityScope os where opportunityID=? and os.serviceScope.serviceElement.serviceElementName=?",
							opportunityId,serviceElementName);
			
			if(opportunityScopeList!=null && opportunityScopeList.size()>0)
				hasServiceElement = true;
			
			return hasServiceElement;
		}

		
}
