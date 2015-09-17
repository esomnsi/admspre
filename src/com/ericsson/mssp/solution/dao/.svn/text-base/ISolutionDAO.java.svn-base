package com.ericsson.mssp.solution.dao;

import java.util.List;
import java.util.Map;

import com.ericsson.mssp.common.dao.IBaseDAO;
import com.ericsson.mssp.common.dto.ExchangeRateDTO;
import com.ericsson.mssp.common.dto.FTEStagingDTO;
import com.ericsson.mssp.common.dto.FteHeadCountAndPercentageDTO;
import com.ericsson.mssp.common.dto.LaborCostDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.OpportunityDetailDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.SearchDTO;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.dto.SolutionADRDTO;
import com.ericsson.mssp.common.dto.SolutionAPADTO;
import com.ericsson.mssp.common.dto.SolutionApproachDimensionDTO;
import com.ericsson.mssp.common.dto.SolutionDTO;
import com.ericsson.mssp.common.dto.UserAccessDTO;
import com.ericsson.mssp.common.entity.CommercialModel;
import com.ericsson.mssp.common.entity.Component;
import com.ericsson.mssp.common.entity.Country;
import com.ericsson.mssp.common.entity.DeliveryModel;
import com.ericsson.mssp.common.entity.DeliveryType;
import com.ericsson.mssp.common.entity.FTEStaging;
import com.ericsson.mssp.common.entity.InputVolumetricsDefinedBy;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.NonLabourCost;
import com.ericsson.mssp.common.entity.OpportunityComponent;
import com.ericsson.mssp.common.entity.OpportunitySource;
import com.ericsson.mssp.common.entity.ProductDetails;
import com.ericsson.mssp.common.entity.RateCard;
import com.ericsson.mssp.common.entity.ScopeOfServicesDefinedBy;
import com.ericsson.mssp.common.entity.ServiceElement;
import com.ericsson.mssp.common.entity.ServiceScope;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionADR;
import com.ericsson.mssp.common.entity.SolutionAPA;
import com.ericsson.mssp.common.entity.StaffingPlan;
import com.ericsson.mssp.common.entity.User;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;

public interface ISolutionDAO extends IBaseDAO {

    public List<ServiceScope> getAllServiceScope() throws DAOException;

    public Integer saveObjectReturnId(Object o);

    List<SolutionADR> getAllSolutionADR() throws DAOException;

    public List<Country> getCountries();
    
    public String getCurrencyCode(Integer opportunityID);

    public List<OpportunitySource> getOpportunitySources();

    public List<CommercialModel> getCommercialModels();

    public List<DeliveryModel> getDeliveryModels();

    public List<DeliveryType> getDeliveryTypeModels();

    public List<ScopeOfServicesDefinedBy> getScopeDefinedBy();

    public List<InputVolumetricsDefinedBy> getVolumetricsDefinedBy();

    public List<SearchDTO> getOpportunities(SearchDTO searchDTO);

    public String updateOpportunity(SearchDTO searchDTO);

    public List<SearchDTO> getOpportunitiesToBeReAssigned(SearchDTO searchDTO);

    public Country getSelectedCountry(Integer countryId);

    public List<User> getRepresentatives();

    public String saveOpportunity(OpportunityDTO opportunityDTO);

    public OpportunityDTO getOpportunity(Integer opportunityId);

    public String saveOpportunityDetails(
	    OpportunityDetailDTO opportunityDetailDTO);

    public SolutionDTO getSolutionDetail(Integer solId) throws MSSPException;

    public List<OpportunityScopeDTO> getOpportunityScopes(Integer oppId)
	    throws MSSPException;

    // public Integer saveDefineScopeAndSolution(SolutionDTO solutionDTO,String
    // deleletedListstr,String newListstr,Integer oppId) throws MSSPException;
    public Integer saveSolutionDetail(SolutionDTO solutionDTO, Integer oppId)
	    throws MSSPException;

    public void saveOpportunityScopes(Integer oppId, String newServiceScopeIds,
	    String deleletedOppScopeIds) throws MSSPException;

    public List<SolutionApproachDimensionDTO> getDimentionListBySolutionId(
	    Integer solutionId) throws MSSPException;

    // public SolutionDTO getSolutionById(Integer solutionId) throws
    // MSSPException;

    /**
     * 
     * Description : Load solution ID based all solutionADR entity values to
     * SolADR DTO& return Method Name : loadAllADR2SolADRDTOBySolID Input&
     * Output:
     * 
     * @param solutionID
     * @return List<SolutionADRDTO>
     * @throws DAOException
     */
    List<SolutionADRDTO> loadAllADR2SolADRDTOBySolID(Integer solutionID)
	    throws DAOException;

    /**
     * 
     * Description : return all solution ADR entity in a list by passed sol id
     * Method Name : loadAllSolutionADRBySolID Input& Output:
     * 
     * @param solutionID
     * @return List<SolutionADR>
     * @throws DAOException
     */
    List<SolutionADR> loadAllSolutionADRBySolID(Integer solutionID)
	    throws DAOException;

    public List<ServiceScopeDTO> loadAllSelectedServiceScopes(
	    Integer opportunityId) throws DAOException;

    public List<OpportunityScopeDTO> loadAllOpportunityScopesByOppotunityId(
	    Integer opportunityId) throws DAOException;

    public void saveStaffAug(StaffingPlan staffingPlan) throws DAOException;

    public Solution getStaffAugWithSolutionID(int solutionID)
	    throws DAOException;

    /**
     * 
     * Description : It will return all Nonlabour cost of a solution searching
     * by solution ID Method Name : getAllNonLabourCostBySolId I nput& Output:
     * 
     * @param solutionID
     * @return List<NonLabourCost>
     * @throws DAOException
     */
    List<NonLabourCost> getAllNonLabourCostBySolId(Integer solutionID)
	    throws DAOException;

    /**
     * 
     * Description : To delete all non labour cost which are not in modified
     * opportunity new timeline Method Name : removeAllNNLCBySolIdTimeline
     * Input& Output:
     * 
     * @param solutionID
     * @param startYr
     * @param endYr
     * @return int number of deleted records
     * @throws DAOException
     */
    int removeAllNNLCBySolIdTimeline(Integer solutionID, int startYr, int endYr)
	    throws DAOException;

    /**
     * 
     * Description : Remove all duplicate NLC by checking same Solution ID and
     * year Method Name : removeAllDuplicateNLC Input& Output:
     * 
     * @return count: int
     * @throws DAOException
     */
    public int removeAllDuplicateNLC() throws DAOException;

    public List getStaffingPlan(int solutionID) throws DAOException;

    public void saveSolutionAPA(SolutionAPADTO solutionAPADTO, Integer oppId,
	    Integer solutionId) throws MSSPException;

    public List<JobRole> getAllJobRole();

    /**
     * 
     * Description : To Get all getSolutionAPA Method Name : getSolutionAPA
     * Input& Output:
     * 
     * @param solutionId
     *            , oppId, serviceScopeId
     * @param solutionAPADTO
     *            ,solutionAPAList
     * @return SolutionAPA
     * @throws MSSPException
     */
    public void getSolutionAPA(Integer solutionId, Integer oppId,
	    Integer serviceScopeId, SolutionAPADTO solutionAPADTO,
	    List<SolutionAPA> solutionAPAList) throws MSSPException;

    List<SolutionADR> loadAllSolutionADRByQuery(String query)
	    throws DAOException;

    public List<JobRole> getJobRoleList();

    public List<RateCard> getRateCards();
    public List<RateCard> getRateCards(Integer countryID);
    public List<RateCard> getRateCards(Integer opportunityID,Integer jobRoleID, String location);

    public List<FTEStaging> getFinalList(Integer solutionID,
	    Integer opportunityID, Integer opportunityScopeID);

    List<String> getServiceBucketDataBySolutionID(Integer solutionID);

    List<String> getServiceBucketDataByOppSolutionID(Integer opportunityID,
	    Integer solutionID) throws DAOException;

    /**
     * 
     * Description : Get save Staffs list order by created order Method Name :
     * getStaffingPlanBySolutionID Input& Output:
     * 
     * @param solutionID
     * @return List<StaffingPlan>
     * @throws DAOException
     */
    List<StaffingPlan> getStaffingPlanBySolutionID(int solutionID)
	    throws DAOException;
    /**
     * 
     * Description : Return opportunity id based OppScopeID based service scope
     * names Method Name : loadAllSelectedOppSSIdServiceScopes Input& Output:
     * 
     * @param opportunityId
     * @return Map<Integer, String>
     * @throws DAOException
     */
    public Map<Integer, String> loadAllSelectedOppSSIdServiceScopes(
	    Integer opportunityId) throws DAOException;
    
    
    /**
     * 
     * Description : Soft delete solution from the database.
     * names Method Name : deleteSolution:
     * 
     * @param solutionId
     * @return boolean
     * @throws DAOException
     */
    public boolean deleteSolution(String solutionId) throws DAOException; 

    
    public boolean updateTable(String file,String filePath,String tableName);
    public String getSteadyStateStartDate(Integer opportunityID);
    public List<ExchangeRateDTO> getExchangeRateList();

    List<Object> getEntitiesObjBySolutionID(String conditionalSQL);

    void saveObjects(List<Object> objectsList);
    public void saveLaborCost(LaborCostDTO lcDTO);
    
    public Map<Integer,String> getServiceScopeNames(Integer solutionID);
    public List<FteHeadCountAndPercentageDTO> getFteHeadCount(Integer solutionID);
    public List<FteHeadCountAndPercentageDTO> getDistributionPercentage(Integer solutionID);
    
    /**
	 * Returns all Service Elements from database
	 * 
	 * @return List<ServiceElement>
	 * @throws DAOException
	 */
    public List<ServiceElement> getAllServiceElement(String serviceType) throws DAOException;
    
    /**
     * Returns list of service scopes for a particular service element
     * @param serviceElements
     * @return List<ServiceScope>
     * @throws DAOException
     */
    public List<ServiceScope> getServiceScopeByServiceElement(String serviceElements) throws DAOException;

	public List<UserAccessDTO> searchLDAPUsers(String signumId);
	public List<FTEStagingDTO> getFTEStagingData(Integer solutionID,Integer opportunityScopeID);
	public double getRateForCurrentJobRole(Integer countryID,Integer jobRoleStageID,String location,String subLocation, String gsc, boolean isSubmitted, String solSubmittedDate);
	
	public List<JobRole> getJobRoleListForCCM(Integer flag);
	
	public List<RateCard> loadRateCards(Integer countryID);
	
	public List<JobRoleStages> getJobStages(Integer jobRoleID);

	public void updateServiceType(Integer oppId, String serviceType) throws Exception;
	
	public Map<String,Float> getUtilizationPerYearDefaultValues();
	
	public List<Component> getAllComponents();
	public void saveOpportunityComponent(List<OpportunityComponent> list);
	public List<Component> getSelectedComponents(List<Integer> ComponentIDs);
	public List<OpportunityComponent> getComponentsByOpportunityID(Integer oppId) throws MSSPException;
	public void removeOpportunityCompByOppId(Integer oppId);
	
	public ServiceScopeDTO getServiceScopeForOpportunityScopeId(Integer opportunityScopeID) throws DAOException;
	public Solution getSolution(Integer solutionId) throws DAOException;
	

	/**
	 * Fetches Product List
	 * @return List<ProductDetailsDTO>
	 * @throws MSSPException
	 */
	public List<ProductDetails> fetchProductList() throws DAOException;
	
	/**
	 * Fetches list of Components for a product
	 * @param productID
	 * @return List<ComponentDTO>
	 */
	public List<Component> getComponentByProduct(Integer productID) throws DAOException;

	/**
	 * Persists opportunity scopes from service elements
	 * @param oppId
	 * @param selectedServiceElements
	 * @throws DAOException
	 */
	public void saveOpportunityScopesFromServiceElements(Integer oppId, String selectedServiceElements) throws DAOException;
	public void removeOpportunityCompByOppId(Integer oppId, List<Integer> componentIdList) throws DAOException;

	
	/**
	 * Fetches list of JobRoleStages for particular Solution
	 * @param solutionId
	 * @return List<JobRoleStages>
	 * @throws MSSPException
	 */
	public List<JobRoleStages> getJobRoleStagesbySolutionID(Integer solutionId) throws MSSPException;
}
