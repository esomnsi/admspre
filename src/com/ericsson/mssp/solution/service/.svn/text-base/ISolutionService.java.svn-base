package com.ericsson.mssp.solution.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ericsson.mssp.common.dto.AdditionalFTEDTO;
import com.ericsson.mssp.common.dto.CommercialModelDTO;
import com.ericsson.mssp.common.dto.ComponentDTO;
import com.ericsson.mssp.common.dto.CountryDTO;
import com.ericsson.mssp.common.dto.DeliveryModelDTO;
import com.ericsson.mssp.common.dto.DeliveryTypeModelDTO;
import com.ericsson.mssp.common.dto.FTEStagingDTO;
import com.ericsson.mssp.common.dto.JobRoleDTO;
import com.ericsson.mssp.common.dto.JobRoleStagesDTO;
import com.ericsson.mssp.common.dto.JobStageDTO;
import com.ericsson.mssp.common.dto.LaborCostDTO;
import com.ericsson.mssp.common.dto.NonLabourCostDTO;
import com.ericsson.mssp.common.dto.OpportunityComponentDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.OpportunityDetailDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.OpportunitySourceDTO;
import com.ericsson.mssp.common.dto.ProductDetailsDTO;
import com.ericsson.mssp.common.dto.ProductivityLeverDTO;
import com.ericsson.mssp.common.dto.RateCardDTO;
import com.ericsson.mssp.common.dto.ScopeDefinedByDTO;
import com.ericsson.mssp.common.dto.ServiceElementDTO;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.dto.SolutionADRDTO;
import com.ericsson.mssp.common.dto.SolutionAPADTO;
import com.ericsson.mssp.common.dto.SolutionApproachDimensionDTO;
import com.ericsson.mssp.common.dto.SolutionComplexityDTO;
import com.ericsson.mssp.common.dto.SolutionDTO;
import com.ericsson.mssp.common.dto.UserDTO;
import com.ericsson.mssp.common.dto.VolumetricsDefinedByDTO;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.NonLabourCost;
import com.ericsson.mssp.common.entity.Opportunity;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionAPA;
import com.ericsson.mssp.common.entity.SolutionLever;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.forms.NonLabourCostForm;
import com.ericsson.mssp.solution.forms.StaffAugForm;
import com.ericsson.mssp.solution.forms.StaffingPlanForm;

public interface ISolutionService {

    public Integer saveSolutionDetail(SolutionDTO solutionDTO, Integer oppId)
	    throws MSSPException;

    public void saveOpportunityScopes(Integer oppId, String newServiceScopeIds,
	    String deleletedOppScopeIds) throws MSSPException;

    public CountryDTO getSelectedCountry(Integer countryId);

    public List<UserDTO> getRepresentatives();
    
    public String getCurrencyCode(Integer oppportunityID);

    public String initiateOpportunity(OpportunityDTO opportunity);

    public OpportunityDTO getOpportunity(Integer opportunityId);

    public SolutionDTO getSolutionDetail(Integer solId) throws MSSPException;

    public List<OpportunityScopeDTO> getOpportunityScopes(Integer oppId)
	    throws MSSPException;

    public List<OpportunitySourceDTO> getOpportunitySources();

    public List<CommercialModelDTO> getCommercialModels();

    public List<DeliveryModelDTO> getDeliveryModels();

    public List<DeliveryTypeModelDTO> getDeliveryTypeModels();

    public List<ScopeDefinedByDTO> getScopeDefinedBy();

    public List<VolumetricsDefinedByDTO> getVolumetricsDefinedBy();

    public String[] getAllServiceScopeByOppId(Integer oppId)
	    throws MSSPException;

    /**
     * 
     * Description : TODO Method Name : loadAllSelectedServiceScopes Input&
     * Output:
     * 
     * @param opportunityId
     * @return
     * @throws MSSPException
     */
    public String saveOpportunityDetails(
	    OpportunityDetailDTO opportunityDetailDTO);

    public List<ServiceScopeDTO> loadAllSelectedServiceScopes(
	    Integer opportunityId) throws MSSPException;

    public List<SolutionApproachDimensionDTO> getDimentionListBySolutionId(
	    Integer solutionId) throws MSSPException;

    public void saveFTEStaging(FTEStagingDTO fteStagingDTO);

    public void saveSolutionComplexity(
	    SolutionComplexityDTO solutionComplexityDTO);

    public void saveSolutionApproachDimension(
	    SolutionApproachDimensionDTO solutionApproachDimensionDTO);

    public boolean saveSolutionADR(SolutionADRDTO solutionADRDTO)
	    throws MSSPException;

    public void saveNonLabourCost(NonLabourCostDTO nonLabourCostDTO);

    public void saveAdditionalFTE(AdditionalFTEDTO additionalFTEDTO);

    public void saveStaffingPlan(StaffingPlanForm staffingPlanForm,
	    int solutionID);

    public void saveSolutionAPA(SolutionAPADTO solutionAPADTO, Integer oppId,
	    Integer solutionId) throws MSSPException;

    public List<ServiceScopeDTO> getAllServiceScope() throws MSSPException;

    List<SolutionADRDTO> getAllSolutionADR() throws MSSPException;

    /**
     * 
     * Description : return a map with having individual ADR lists with loaded
     * data Method Name : getAllADRInADRListsMap Input& Output:
     * 
     * @return
     * @throws MSSPException
     */
    public Map<String, List<SolutionADRDTO>> getAllADRInADRListsMap()
	    throws MSSPException;

    public List<SolutionADRDTO> getSolutionAllAssumptions()
	    throws MSSPException;

    public List<SolutionADRDTO> getSolutionAllDependencies()
	    throws MSSPException;

    public List<SolutionADRDTO> getSolutionAllRisks() throws MSSPException;

    public List<SolutionADRDTO> loadAllADR2SolADRDTOBySolID(Integer solutionID)
	    throws MSSPException;

    public Map<String, List<SolutionADRDTO>> getAllADRInADRListsMapBySolId(
	    Integer solutionID) throws MSSPException;

    /**
     * 
     * Description : Return an map with having individual ADR lists Method Name
     * : getAllADRInADRListsMap Input& Output:
     * 
     * @param sArea
     * @param sType
     * @param sImpact
     * @param solutionID
     * @return
     * @throws MSSPException
     */
    public Map<String, List<SolutionADRDTO>> getAllADRInADRListsMap(
	    String sArea, String sType, String sImpact, Integer solutionID)
	    throws MSSPException;

    public boolean deleteSolutionADR(SolutionADRDTO solutionADRDTO)
	    throws MSSPException;

    void saveStaffAug(StaffAugForm staffAugForm, int solutionID)
	    throws MSSPException;

    List<CountryDTO> getCountries();

    /**
     * 
     * Description : It will return all non labour cost of the solution by
     * passed solution id Method Name : getSolutionNLC Input& Output:
     * 
     * @param solutionID
     * @return
     * @throws MSSPException
     */
    public NonLabourCostForm getSolutionNLC(Integer solutionID)
	    throws MSSPException;

    /**
     * 
     * Description : It will return opportunity steady years in comma separated
     * string by passing solution id Method Name :
     * getOpportunitySteadyYearsString Input& Output:
     * 
     * @param solutionID
     * @return
     * @throws MSSPException
     */
    String getOpportunitySteadyYearsString(Integer solutionID)
	    throws MSSPException;

    /**
     * 
     * Description : It will save solutions non labour cost Method Name :
     * saveSolutionNLC Input& Output:
     * 
     * @param nonLabourCost
     * @throws MSSPException
     */
    Integer saveSolutionNLC(NonLabourCost nonLabourCost) throws MSSPException;

    /**
     * 
     * Description : It will return Solution object by passed solution ID Method
     * Name : getSolutionById Input& Output:
     * 
     * @param solutionID
     * @return Solution
     * @throws MSSPException
     */
    Solution getSolutionById(Integer solutionID) throws MSSPException;

    /**
     * 
     * Description : It will return opportunity object by passed sol ID Method
     * Name : getOpportunityById Input& Output:
     * 
     * @param solutionID
     * @return Opportunity
     * @throws MSSPException
     */
    Opportunity getOpportunityBySolId(Integer solutionID) throws MSSPException;

    public StaffAugForm getStaffAugWithSolutionID(int solutionID)
	    throws MSSPException;

    /**
     * 
     * Description : It will return a map with key service scope ID and name in
     * value
     * 
     * Method Name : getAllServiceScopeInMap Input& Output:
     * 
     * @return Map<Integer, String>
     * @throws MSSPException
     */
    Map<Integer, String> getAllServiceScopeInMap() throws MSSPException;

    public StaffingPlanForm getStaffingPlan(int solutionID)
	    throws MSSPException;

    /**
     * 
     * Description : It will fetch opportunity selected services list in scope
     * by opp ID Method Name : getAllServiceScopeInMapByOppID Input& Output:
     * 
     * @param opportunityId
     * @return
     * @throws MSSPException
     */
    Map<Integer, String> getAllServiceScopeInMapByOppID(Integer opportunityId)
	    throws MSSPException;

    /**
     * 
     * Description : It will return all opportunity selected services list by
     * solution ID Method Name : getAllServiceScopeInMapBySolId Input& Output:
     * 
     * @param solutionId
     * @return
     * @throws MSSPException
     */
    Map<Integer, String> getAllServiceScopeInMapBySolId(Integer solutionId)
	    throws MSSPException;

    /**
     * 
     * Description : Returns all job roles of the system Method Name :
     * getJobRoles Input& Output:
     * 
     * @return Map<Integer, String>
     * @throws MSSPException
     */
    Map<Integer, String> getJobRoles() throws MSSPException;

    List<JobRole> getAllJobRole();

    /**
     * 
     * Description : It will return all SolutionAPA for selected services list
     * by solution ID Method Name : getAllSolutionAPABySolId Input& Output:
     * 
     * @param solutionId
     * @return
     * @throws MSSPException
     */
    public void getAllSolutionAPABySolId(Integer solutionId, Integer oppId,
	    Integer ServiceScopeID, SolutionAPADTO solutionAPADTO,
	    List<SolutionAPA> solutionAPAList) throws MSSPException;

    /**
     * 
     * Description : Save solution ID and return generated ID Method Name :
     * saveSolutionADRReturnId Input& Output:
     * 
     * @param solutionADRDTO
     * @return
     * @throws MSSPException
     */
    Integer saveSolutionADRReturnId(SolutionADRDTO solutionADRDTO)
	    throws MSSPException;

    List<SolutionADRDTO> getSolutionAllADRBySolID(String sArea, String sType,
	    String sImpact, Integer solID) throws MSSPException;

    public List<JobRoleDTO> getJobRoleList();

    String getOpportunitySteadyMonthYearsStringByOpportunityID(
	    Integer solutionID) throws MSSPException;

    public List<RateCardDTO> getRateCardList();
    public List<RateCardDTO> getRateCardList(Integer countryID);

    public Map<String,Map<String,String[]>> getFinalList(Integer solutionID,
    	    Integer opportunityID, Integer opportunityScopeID, String gsc);
    
  
    /**
     * 
     * Description : It will load all service bucket values of the Solution. It
     * will return FTE filled solution records only Method Name :
     * loadServiceBucketData Input& Output:
     * 
     * @param opportunityID
     * @param solutionID
     * @return List<String>
     */
    List<String> loadServiceBucketData(Integer solutionID);

    /**
     * 
     * Description : It will load all service bucket values of the Opp& Solution
     * It will return FTE value filled solution records and null solution id
     * filled for selected services of the opportunity. Opportunity selected all
     * services record will be retunred. Method Name : loadServiceBucketData
     * Input& Output:
     * 
     * @param opportunityID
     * @param solutionID
     * @return List<String>
     * @throws MSSPException 
     */
    List<String> loadServiceBucketDataByOppSolutionID(Integer opportunityID,
	    Integer solutionID);

    public String validateUserEditAccess(Integer solId, String userId,String selectedRole)
    	    throws MSSPException;

    public String getSelOpportunityScopes(Integer opportunityId)
	    throws MSSPException;

    public List<OpportunityScopeDTO> loadAllOpportunityScopesByOppotunityId(
	    Integer oppId) throws MSSPException;

    public boolean updateTable(String file,String filePath,String tableName);
    
    public List<Float> getExchangeRateList(Integer opportunityID);
    
    public void saveLaborCost(LaborCostDTO lcDTO);
    
    public Map<String,Object> getServiceScopeNamesFteHeadCountAndPercentage(Integer solutionID);
    
    /**
	 * Returns all Service Elements
	 * 
	 * @return List<ServiceElementDTO>
	 * @throws DAOException
	 */
    public HashMap<String,List<ServiceElementDTO>> getAllServiceElement(String serviceType) throws MSSPException;
    
    /**
     * Returns list of service scopes for a particular service element
     * @param serviceElements
     * @return HashMap<String, List<ServiceScopeDTO>>
     * @throws DAOException
     */
    public HashMap<String, List<ServiceScopeDTO>> getServiceScopeByServiceElement(String[] serviceElements) throws MSSPException;

	public String searchLDAPUsers(String signumId);
	
	public List<JobRoleDTO> getJobRoleListForCCM(Integer flag);
	
	public List<RateCardDTO> loadRateCardList(Integer countryID);
	
	public List<FTEStagingDTO> getFteStagingData(Integer solId, Integer serviceScopeId);
	
	public List<JobStageDTO> getJobStages(Integer JobRoleID);

	public void updateServiceType(Integer oppId, String serviceType) throws Exception;
	
	public Map<String,Float> getUtilizationPerYearDefaultValues();

	public List<CountryDTO> getGscCountries();
	
	public List<ComponentDTO> getAllComponents();
	
	public void saveOpportunityComponent(Integer oppId, String[] componentIDs) throws MSSPException;
	public List<OpportunityComponentDTO> getComponentsByOpportunityID(Integer oppId) throws MSSPException;
	public SolutionLever getSolutionLever(Integer solnId) throws MSSPException;
	

	/**
	 * Fetches Product List
	 * @return List<ProductDetailsDTO>
	 * @throws MSSPException
	 */
	public List<ProductDetailsDTO> fetchProductList() throws MSSPException;
	
	/**
	 * Fetches list of Components for a product
	 * @param productID
	 * @return List<ComponentDTO>
	 */
	public List<ComponentDTO> getComponentByProduct(Integer productID) throws MSSPException;


	/**
	 * Persists opportunity scopes from service elements
	 * @param oppId
	 * @param selectedServiceElements
	 * @throws MSSPException
	 */
	public void saveOpportunityScopesFromServiceElements(Integer oppId, String[] selectedServiceElements) throws MSSPException;
	
	/**
	 * Fetches list of components associated with the opportunity
	 * @param oppId
	 * @return List<Integer>
	 * @throws MSSPException
	 */
	public List<Integer> getOpportunityComponents(Integer oppId) throws MSSPException;
	
	/**
	 * Fetches list of JobRoleStages DTO for particular solution.
	 * @param solutionId
	 * @return List<JobRoleStagesDTO>
	 * @throws MSSPException
	 */
	public List<JobRoleStagesDTO> getJobRoleStagesbySolutionID(Integer solutionId) throws MSSPException;


}
