package com.ericsson.mssp.volumetric.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.dto.AppDevTicketDistributionDTO;
import com.ericsson.mssp.common.dto.ProductEstimationGenericInput;
import com.ericsson.mssp.common.dto.ProductVolumetricDTO;
import com.ericsson.mssp.common.entity.OpportunityComponent;
import com.ericsson.mssp.common.entity.OtherDefaults;
import com.ericsson.mssp.common.entity.ProductEstimationAuxiliaryParamsForSolution;
import com.ericsson.mssp.common.entity.ProductEstimationBaseEffortForSolution;
import com.ericsson.mssp.common.entity.ProductEstimationForSolution;
import com.ericsson.mssp.common.entity.TicketDistribution;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.volumetric.dao.ProductVolumetricDAO;

@Repository
public class ProductVolumetricDAOImpl extends BaseDAOImpl implements
		ProductVolumetricDAO, MSSPConstants {

	@Override
	public List<ProductVolumetricDTO> getProductEstimationActivities(
			final Integer componentId, final Integer solutionId) throws DAOException {
		/*
		 * StringBuffer queryStr = new StringBuffer() .append(
		 * "select act.ProductEstimationActivitiesID as productEstimationActivitiesID,  act.ConfigurationType as configurationType,  act.ConfigurationArea as configurationArea,  act.ActivityName as activityName,  act.ServiceScopeID as serviceScopeId,  comp.ProductActivitiesComplexityAssumptionsID as productActivitiesComplexityAssumptionsID,  comp.EstimationComplexityID as estimationComplexityID,  compLookup.EstimationComplexityName as estimationComplexityName,  comp.EstimationEffortFactorValue as estimationEffortFactorValue,  comp.EstimationEffortFactorUnit as estimationEffortFactorUnit,  sol.ProductEstimationForSolutionID as productEstimationForSolutionID,  sol.PerMonthEventCountInput as perMonthEventCountInput,  sol.TotalEffortDerived as totalEffortDerived,  sol.SolutionID as solutionId from  ProductEstimationActivities act,  ProductActivitiesComplexityAssumptions comp      LEFT JOIN  EstimationComplexityLookup compLookup ON compLookup.EstimationComplexityID = comp.EstimationComplexityID      LEFT JOIN  ProductEstimationForSolution sol ON comp.ProductActivitiesComplexityAssumptionsID = sol.ProductActivitiesComplexityAssumptionsID and sol.SolutionID = :solutionId where  act.ProductEstimationActivitiesID = comp.ProductEstimationActivitiesID and act.ServiceScopeID = :serviceScopeId"
		 * );
		 */
		
		final StringBuffer queryStr = new StringBuffer()
				.append("select act.ProductEstimationActivitiesID as productEstimationActivitiesID,  act.ConfigurationType as configurationType,  act.ConfigurationArea as configurationArea,  act.ActivityName as activityName,  act.ServiceScopeID as serviceScopeId,  act.ComponentID as componentId,  comp.ProductActivitiesComplexityAssumptionsID as productActivitiesComplexityAssumptionsID,  comp.EstimationComplexityID as estimationComplexityID,  compLookup.EstimationComplexityName as estimationComplexityName,  comp.EstimationEffortFactorValue as estimationEffortFactorValue,  comp.EstimationEffortFactorUnit as estimationEffortFactorUnit,  sol.ProductEstimationForSolutionID as productEstimationForSolutionID,  sol.PerMonthEventCountInput as perMonthEventCountInput,  sol.TotalEffortDerived as totalEffortDerived,  sol.SolutionID as solutionId from  ProductEstimationActivities act,  ProductActivitiesComplexityAssumptions comp      LEFT JOIN  EstimationComplexityLookup compLookup ON compLookup.EstimationComplexityID = comp.EstimationComplexityID      LEFT JOIN  ProductEstimationForSolution sol ON comp.ProductActivitiesComplexityAssumptionsID = sol.ProductActivitiesComplexityAssumptionsID and sol.SolutionID = :solutionId where  act.ProductEstimationActivitiesID = comp.ProductEstimationActivitiesID and act.ComponentID=:componentId");
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<ProductVolumetricDTO> activityList = (List) getHibernateTemplate().execute(
			new HibernateCallback() {
			    public List<ProductVolumetricDTO> doInHibernate(Session session) throws HibernateException {
		List<ProductVolumetricDTO> activityList1 = (List<ProductVolumetricDTO>) session
				.createSQLQuery(queryStr.toString())
				.addScalar("productEstimationActivitiesID", new IntegerType())
				.addScalar("configurationType", new StringType())
				.addScalar("configurationArea", new StringType())
				.addScalar("activityName", new StringType())
				.addScalar("serviceScopeId", new IntegerType())
				.addScalar("componentId", new IntegerType())
				.addScalar("productActivitiesComplexityAssumptionsID",new IntegerType())
				.addScalar("estimationComplexityID", new IntegerType())
				.addScalar("estimationComplexityName", new StringType())
				.addScalar("estimationEffortFactorValue", new DoubleType())
				.addScalar("estimationEffortFactorUnit", new StringType())
				.addScalar("productEstimationForSolutionID", new IntegerType())
				.addScalar("perMonthEventCountInput", new DoubleType())
				.addScalar("totalEffortDerived", new DoubleType())
				.addScalar("solutionId", new IntegerType())
				.setParameter("solutionId", solutionId)
				.setParameter("componentId", componentId)
				.setResultTransformer(Transformers.aliasToBean(ProductVolumetricDTO.class))
				.list();
		
				return activityList1;
			    }

			});
		return activityList;
	}

	@Override
	public void saveProductEstimationForSolution(
			List<ProductEstimationForSolution> persistentList)
			throws DAOException {
		try {
			for (ProductEstimationForSolution eachObject : persistentList) {
				saveObject(eachObject);
				getHibernateTemplate().flush();
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + " |  " + e.getCause());
			throw new DAOException(e.getMessage() + " |  " + e.getCause());
		}
	}

	@Override
	public void saveProductEstimationAuxiliaryParamsForSolution(
			List<ProductEstimationAuxiliaryParamsForSolution> persistentList)
			throws DAOException {
		
		try {
			for (ProductEstimationAuxiliaryParamsForSolution eachObject : persistentList) {
				saveObject(eachObject);
				getHibernateTemplate().flush();
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + " |  " + e.getCause());
			throw new DAOException(e.getMessage() + " |  " + e.getCause());
		}

	}

	@Override
	public List<ProductEstimationAuxiliaryParamsForSolution> getProductEstimationAuxiliaryParamsForSolution(
			Integer solutionId) throws DAOException {
		StringBuffer queryStr = new StringBuffer().append(
				"from ProductEstimationAuxiliaryParamsForSolution act ")
				.append(" where act.solution.solutionId = ? ");

		List<ProductEstimationAuxiliaryParamsForSolution> objectList = getHibernateTemplate()
				.find(queryStr.toString(), solutionId);

		return objectList;
	}

	@Override
	public void saveProductEstimationBaseEffortForSolution(
			List<ProductEstimationBaseEffortForSolution> persistentList)
			throws DAOException {
		try {
			for (ProductEstimationBaseEffortForSolution eachObject : persistentList) {
				saveObject(eachObject);
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + " |  " + e.getCause());
			throw new DAOException(e.getMessage() + " |  " + e.getCause());
		}
	}
	
	@Override
	public void saveProductEstimationBaseEffortForSolutionNew(List<ProductEstimationBaseEffortForSolution> entityList) throws DAOException{
		final StringBuffer sqlInsert = new StringBuffer().append("insert into ProductEstimationBaseEffortForSolution (SolutionID, OpportunityScopeID, TotalBaseFTE, TotalBaseHours) values (:solutionID,:opportunityScopeID,:totalBaseFTE,:totalBaseHours) ON DUPLICATE KEY UPDATE SolutionID=VALUES(SolutionID),OpportunityScopeID=VALUES(OpportunityScopeID),TotalBaseFTE=VALUES(TotalBaseFTE),TotalBaseHours=VALUES(TotalBaseHours)") ;
		int row;
		try {
			for (final ProductEstimationBaseEffortForSolution eachObject : entityList) {
				// getHibernateTemplate().save(eachObject);
				@SuppressWarnings({ "unchecked", "rawtypes" })
				Integer deltdCount = (Integer) getHibernateTemplate().execute(
					new HibernateCallback() {
					    public Object doInHibernate(Session session)
						    throws HibernateException {
						SQLQuery sq = (SQLQuery) session.createSQLQuery(sqlInsert.toString())
								.addScalar("SolutionID", new IntegerType())
								.addScalar("OpportunityScopeID",new IntegerType())
								.addScalar("TotalBaseFTE", new DoubleType())
								.addScalar("TotalBaseHours", new DoubleType())
								.setParameter("solutionID",eachObject.getSolution().getSolutionId())
								.setParameter("opportunityScopeID",	eachObject.getOpportunityScope().getOpportunityScopeId())
								.setParameter("totalBaseFTE",eachObject.getTotalBaseFTE())
								.setParameter("totalBaseHours",eachObject.getTotalBaseHours());
						
						int counts = sq.executeUpdate();
						return Integer.valueOf(counts);
					    }

					});
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + " |  " + e.getCause());
			throw new DAOException(e.getMessage() + " |  " + e.getCause());
		}
		
	}

	@Override
	public List<OpportunityComponent> getComponentForOpportunity(
			Integer opportunityId) {

		List<OpportunityComponent> opportunityComponentList = getHibernateTemplate()
				.find(" from OpportunityComponent tab where tab.opportunity.opportunityId=? order by tab.component.componentID",
						opportunityId);

		return opportunityComponentList;
	}

	@Override
	public List<AppDevTicketDistributionDTO> loadPercentageDistribution(final
			ProductEstimationGenericInput inputBean) throws MSSPException {
		final StringBuffer queryStr = new StringBuffer()
				.append("SELECT tcktdist.TicketDistributionID as ticketDistributionID, oppscp.OpportunityID as opportunityID, elem.ServiceFunctionName as serviceFunctionName, scp.ServiceScopeID as serviceScopeID," +
						" ServiceElementName as serviceElementName, oppscp.OpportunityScopeID as opportunityScopeID, (IF(oppscp.OpportunityID IS NULL, 'N', 'Y')) as selected, CONVERT(def.DefaultValues,DECIMAL(10,1)) as defaultValue, " +
						"tcktdist.percentTicketDistribution as percentDistribution, IF(oppscp.OpportunityID IS NULL,0,IFNULL(CONVERT(tcktdist.percentTicketDistribution,DECIMAL(10,2)),CONVERT(def.DefaultValues,DECIMAL(10,2)))) as editablePercentDistribution  " +
						"FROM ServiceElement elem, ServiceScope scp left join OpportunityScope oppscp on oppscp.OpportunityID = :opportunityID and scp.ServiceScopeID = oppscp.ServiceScopeID left join OtherDefaults def on upper(def.ApplicationArea) = :serviceFunctionCode " +
						"and def.ServiceScopeID = scp.ServiceScopeID left join TicketDistribution tcktdist on tcktdist.OpportunityScopeID = oppscp.OpportunityScopeID  where upper(elem.ServiceFunctionCode) = :serviceFunctionCode and upper(elem.Active) = 'Y' and scp.ServiceElementID = elem.ServiceElementID");

		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<AppDevTicketDistributionDTO> resultList = (List<AppDevTicketDistributionDTO>) getHibernateTemplate().execute(
			new HibernateCallback() {
			    public List<AppDevTicketDistributionDTO> doInHibernate(Session session)  throws HibernateException {
			    List<AppDevTicketDistributionDTO> resultList1 = session
				.createSQLQuery(queryStr.toString())
				.addScalar("ticketDistributionID", new IntegerType())
				.addScalar("opportunityID", new IntegerType())
				.addScalar("serviceFunctionName", new StringType())
				.addScalar("serviceScopeID", new IntegerType())
				.addScalar("serviceElementName", new StringType())
				.addScalar("opportunityScopeID", new IntegerType())
				.addScalar("selected", new StringType())
				.addScalar("defaultValue", new FloatType())
				.addScalar("percentDistribution", new FloatType())
				.addScalar("editablePercentDistribution", new FloatType())
				.setParameter("opportunityID", inputBean.getOpportunityID())
				.setParameter("serviceFunctionCode",inputBean.getServiceFunctionCode())
				.setResultTransformer(Transformers.aliasToBean(AppDevTicketDistributionDTO.class))
				.list();
				
				return resultList1;
			    }
			});
		return resultList;
	}

	@Override
	public void savePercentDistribution(List<TicketDistribution> persistentList)
			throws MSSPException {
		try {
			for (TicketDistribution eachObject : persistentList) {
				saveObject(eachObject);
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + " |  " + e.getCause());
			throw new DAOException(e.getMessage() + " |  " + e.getCause());
		}
	}

	@Override
	public List<ProductEstimationBaseEffortForSolution> getProductEstimationBaseEffortForSolution(
			Integer solutionId, String serviceFunction) throws MSSPException {
		String query = "from ProductEstimationBaseEffortForSolution tbl where SolutionID = "+solutionId+" and tbl.opportunityScope.serviceScope.serviceElement.serviceFunctionCode='"+serviceFunction+"'";
		List<ProductEstimationBaseEffortForSolution> entityList = (List<ProductEstimationBaseEffortForSolution>) getHibernateTemplate().find(query);
		return entityList;
	}
	
	@Override
	public Double getTotalEffortForSolution(final Integer solutionId,
			final List<String> serviceFunctionCode) throws DAOException {
		Double returnEffort = new Double(0);
		
		final StringBuffer queryStr = new StringBuffer()
				.append("SELECT sum(TotalBaseHours) as totalBaseHours FROM ProductEstimationBaseEffortForSolution eff, OpportunityScope oppscope, ServiceScope scope, ServiceElement element where eff.OpportunityScopeID = oppscope.OpportunityScopeID and scope.ServiceScopeID = oppscope.ServiceScopeID and scope.ServiceElementID = element.ServiceElementID and element.ServiceFunctionCode in (:serviceFunctionCode) and eff.SolutionID = :solutionID");

		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Double> totalEffort = (List<Double>) getHibernateTemplate().execute(
			new HibernateCallback() {
			    public List<Double> doInHibernate(Session session)  throws HibernateException {
			    List<Double> totalEffort1 = session
				.createSQLQuery(queryStr.toString())
				.addScalar("totalBaseHours", new DoubleType())
				.setParameter("solutionID", solutionId)
				.setParameterList("serviceFunctionCode", serviceFunctionCode)
				.list();
			    return totalEffort1;
			    }
			});

		if (totalEffort != null && totalEffort.size() > 0
				&& totalEffort.get(0) != null && totalEffort.get(0).SIZE > 0) {
			returnEffort = totalEffort.get(0);
		}
		return returnEffort;
	}

	@Override
	public Double getTotalProductEstimationForSolution(Integer solutionId)
			throws DAOException {
		Double returnEffort = new Double(0);
		
		String query = "select SUM(totalEffortDerived) from ProductEstimationForSolution where solution.solutionId=?";
		List<Double> totalList = (List<Double>) getHibernateTemplate().find(query,solutionId);
		
		if (totalList != null && totalList.size() > 0
				&& totalList.get(0) != null && totalList.get(0).SIZE > 0) {
			returnEffort = totalList.get(0);
		}
		
		return returnEffort;
	}
}
