/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.constant
 * File name       		    :  MSSPConstants.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Dec 5, 20122012
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Dec 5, 2012  	   |egaivij   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.common.constant;

import java.util.List;

public interface MSSPConstants {
    // Solution ID session storing key
    String SOLUTION_ID = "solutionId";
    String OPPORTUNITY_ID = "opportunityId";
    // E/V/I :V is for view only rest will be in editable form
    String OPERATION_MODE = "ModeOfOperation";

    String ADR_STRING_DELIMITER = "adr.string.delimiter";
    String ADR_AREAS = "adr.areas";
    String ADR_TYPES = "adr.types";
    String ADR_COMPLEXITIES = "adr.complexities";
    String ADR_RISK_CATEGORY = "RISK";
    String ADR_ASSUM_CATEGORY = "ASSUMPTION";
    String ADR_DEPEND_CATEGORY = "DEPENDENCY";
    String[] ADR_IMPACTS = new String[] { "High", "Medium", "Low" };

    String INPUTBASELINE_INPUTFIELD_LIST_MAP = "inputFieldsListMap";
    String INPUTBASELINE_ITOPS_UNIT_LISTBOX = "inputbaseline.itops.unit.listbox";
    String INPUTBASELINE_ITOPS_LANGUAGE_LISTBOX = "inputbaseline.itops.language.listbox";
    String INPUTBASELINE_SUPPORT_WINDOW_LISTBOX = "volumetricSol.supportWindow";
    String DEFINESCOPE_DIMENSION_NAME = "definescope.dimension.name";
    String DEFINESCOPE_SOULUTION_APPROACH = "definescope.solution.approach";
    String DEFINESCOPE_SOULUTION_TYPE = "definescope.solution.type";
    Integer SOLUTION_STATUS_DRAFT = 1;

    String APA_COMPLEXITYFACTORS = "apa.comFact";
    String APA_SCOPE_FACTORS = "apa.param4rSelectedScope";

    String USER_NAME = "userName";
    String SESSION_EXPIRED = "sessionExpired";

    String APPROACH_STAFF_AUG = "Staff Augmentation";
    String APPROACH_STAFF_DETAIL = "Manage Delivery";

    String SOLTYPE_VOL_SOL = "Volumetric Solution";
    String SOLTYPE_IT_SPEND = "IT Spend";
    String SOLTYPE_PEOPLE_STACK = "People Stack";
    String OPPORTUNITY_NOTEXISTS = "opportunityNotExists";
    String SOLUTION_NOTEXISTS = "solutionNotExists";

    String LOCATION_ONSHORE = "Onshore";
    String LOCATION_ONSHORE_LOCAL = "OnshoreLocal";
    String LOCATION_ONSHORE_GSCI = "OnshoreGsci";
    String LOCATION_ONSHORE_3PP = "Onshore3PP";
    String LOCATION_OFFSHORE = "Offshore";
    String LOCATION_NEARSHORE = "Nearshore";
    String SUBLOC_LOCAL = "Local";
    String SUBLOC_GSCI = "GSCi";
    String SUBLOC_3PP = "3PP";
    
    // .# stands for 1 decimal, .## for 2decimals and so on
    String DECIMAL_POINTS_STRING = "######.##";
    // 10 stands for 1 decimal, 100 for 2decimals and so on
    float DECIMAL_POINTS_NUM = 100f;
    String EMPTY_STR = "";
    String SOLUTION_COMPLEXITY = "solution.complexity";
    String STATUS_FAILURE = "failure";
    String FTE_SERVICES = "services";
    String FTE_TIMELINE = "timeLine";
    String FTE_ALL = "all";
    
    
    String VOLSOL_UNITL3_List = "volsol.unitl3.list";
    String VOLSOL_SCOPE_List = "volsol.scope.list";
    String VOLSOL_DURUNIT_List = "volsol.durunit.list";
    String VOLSOL_UNIT_FC_LIST="volsol.unit.fc.list";
    
    String HAS_EDIT_SOL_ACCESS ="hasEditSolAccess";

    /*Strings added as per category for TNT -- START*/
    String TNT_CATEGORY_PLANNING = "Planning";
    String TNT_CATEGORY_LEARN = "Learn";
    String TNT_CATEGORY_ASSIST = "Assist";
    String TNT_CATEGORY_PERFORM = "Perform";
    String TNT_CATEGORY_DELIVER = "Deliver";
    /*Strings added as per category for TNT -- END*/
    
    
    
    String LOGGED_IN_MODE_APPRROVER = "Approver";
    String LOGGED_IN_MODE_SOL_MANAGER = "SolutionManager";
    String LOGGED_IN_MODE_SUPER_USER = "SuperUser";
    
    /**Approval Entries**/
    Integer SOLUTION_STATUS_SUBMIT = 2;
    Integer SOLUTION_STATUS_APPROVED = 3;
    Integer SOLUTION_STATUS_REJECTED = 4;
    Integer SOLUTION_STATUS_DELETED = 5;
    Integer TOTAL_APPROVAL_LEVEL=3;
    String NON_DRAFT_SOL_SUBMIT_ERR="APRVL_001-User submitted solution not in draft state";
    String APPROVAL_LEVEL_ERR="APRVL_002-User submitted solution without specifying 3 approvers ";
    
    String APPROVER_LIST_CHANGE = "false";
    
    String SOLUTION_APPROACH_STAIR_CASE = "Stair Case";
    String SOLUTION_APPROACH_UNIFORM = "Uniform";
    
    //Roles for application
    String ROLE_SUPER_USER = "ROLE_SUPER_USER";
    String ROLE_APPROVER = "ROLE_APPROVER";
    String ROLE_SOLUTION_MANAGER = "ROLE_SOLUTION_MANAGER";
    String ROLE_GUEST = "ROLE_GUEST";
    
    //Constants added for TAAS
    int TEST_GRP_REG = 1;
	int TEST_GRP_REG_NEWFUNC = 2;
	int TEST_GRP_REG_OPT = 3;
	int TEST_GRP_REG_EFF = 4;
	int TEST_GRP_NEW_FUNC = 5;
	int TEST_GRP_UAT_PROD = 6;
	int AS_IS = 7;
	int TEST_DESIGN = 300;
	int TEST_EXECUTION = 301;
	int TEST_PLAN_REQMNT_AUTO_ANALYSIS = 302;
	int TEST_MGMT = 303;
	int TOTAL_EFFORT = 304;
	String TEST_CASE_TYPE_SIMPLE = "Simple";
	String TEST_CASE_TYPE_MEDIUM = "Medium";
	String TEST_CASE_TYPE_COMPLEX = "Complex";
	String RELEASE_TYPE_MAJOR = "Major";
	String RELEASE_TYPE_MINOR = "Minor";
	String TEST_TYPE_REGRESSION = "Regression";
	String TEST_TYPE_NEW_FUNCTIONALITY = "New_Functionality";
	String TEST_TYPE_UAT_PROD_GO_LIVE = "UAT_Prod_Go_Live";
	String TAAS_OUTPUT_PRIMARY1 = "Primary1";
	String TAAS_OUTPUT_PRIMARY2 = "Primary2";
	String TAAS_OUTPUT_SECONDARY = "Secondary";
	String TAAS_OUTPUT_YEARLY = "Yearly";
	
	String COMMA_SEPARATOR = ",";
	String SERVICE_ELEMENT_HAS_NO_SERVICE_SCOPE = "hasNoScope";
	String ACTIVE = "Y";
	
	int SERVICE_SCOPE_MS_TAAS = 6;
	int SERVICE_SCOPE_TAAS = 25;
	
	String CCM_JOB_ROLE_MODEL = "CCM";
	Integer SERVICE_ELEMENT_FOR_CCM_JOB_ROLES = 1000;
	int JOB_ROLE_CCM_FLAG = 1;
	int JOB_ROLE_NON_CCM_FLAG = 0;
	int SERVICE_ELEMENT_FULL_PERCENT = 100;
	
	String UTILIZATION_PER_YEAR = "utilizationPerYear";
	String DEFAULT_UTILIZATION_PER_YEAR = "2040";
	
	String CHANGE_MANAGEMENT_STR = "Change Management";
	String PROGRAM_MANAGEMENT_STR = "Program Management";
	String RELEASE_MANAGEMENT_STR = "Release Management";
	String CAPACITY_MANAGEMENT_STR = "Capacity Management";
	String CONFIGURATION_MANAGEMENT_STR = "Configuration Management";
	String SECURITY_MANAGEMENT_STR = "Security Management";
	String AVAILABILITY_MANAGEMENT_STR = "Availability Management";
	String ACCESS_MANAGEMENT_STR = "Access Management";
	
	String JOB_ROLE_ID = "JOB_ROLE_ID";
	
	// Column Names
	
	String DemandSupport_CRAnalysis = "CRAnalysis";
	String DemandSupport_RequirementGathering = "RequirementGathering";
	String DemandSupport_TechFeasibilityAnalysis = "TechFeasibilityAnalysis";
	String DemandSupport_EffortCostEstimation = "EffortCostEstimation";
	String DemandSupport_WorkPlanFormulation = "WorkPlanFormulation";
	String DemandSupport_TotalDemandSupportHours = "TotalDemandSupportHours";
	
	String DesignBuild_DetailedTechDesign = "DetailedTechDesign";
	String DesignBuild_TestCaseDesignTestCyclePlan = "TestCaseDesignTestCyclePlan";
	String DesignBuild_FunctionalSpecDevFinal = "FunctionalSpecDevFinal";
	String DesignBuild_CapacityPlanning = "CapacityPlanning";
	String DesignBuild_SoftwareChanges = "SoftwareChanges";
	String DesignBuild_ConfigChanges = "ConfigChanges";
	String DesignBuild_UnitComponentTest = "UnitComponentTest";
	String DesignBuild_RelNotesPrepareDistribute = "RelNotesPrepareDistribute";
	String DesignBuild_MaintananceManualCreation = "MaintananceManualCreation";
	
	String DeploymentRollOut_OpsBusinessTraining = "OpsBusinessTraining";
	String DeploymentRollOut_DeploySoftwareInstln = "DeploySoftwareInstln";
	String DeploymentRollOut_DeployInstlnPlanExec = "DeployInstlnPlanExec";
	String DeploymentRollOut_DataMigration = "DataMigration";
	String DeploymentRollOut_LegacySwitchOff = "LegacySwitchOff";
	
	String PostReleaseActivities_PostInstRelIssues = "PostInstRelIssues";
	String PostReleaseActivities_SuppBusinessSimulations = "SuppBusinessSimulations";
	String PostReleaseActivities_ManageStaggeredAct = "ManageStaggeredAct";
	String PostReleaseActivities_TunePerformance = "TunePerformance";
	String PostReleaseActivities_PostInstallationComm = "PostInstallationComm";
	
	String ProgramManagement_CoordinateProjects = "CoordinateProjects";
	String ProgramManagement_EnsureAchievement = "EnsureAchievement";
	String ProgramManagement_ManageClient = "ManageClient";
	String ProgramManagement_ManageQualityRiskIssues = "ManageQualityRiskIssues";
	String ProgramManagement_MeasureADMServiceKPI = "MeasureADMServiceKPI";
	
	String ReleaseManagement_InstlnDistnOfSoftRel = "InstlnDistnOfSoftRel";
	String ReleaseManagement_DefineRolloutPlan = "DefineRolloutPlan";
	String ReleaseManagement_EnsureTraceability = "EnsureTraceability";
	String ReleaseManagement_EnsureInstallation = "EnsureInstallation";
	
	String ChangeManagement_ReqGatheringAndAnalysis = "ReqGatheringAndAnalysis";
	String ChangeManagement_FeasibilityImpactAndCostAnalysis = "FeasibilityImpactAndCostAnalysis";
	String ChangeManagement_ApproveChangesAndPlanning = "ApproveChangesAndPlanning";
	String ChangeManagement_EnsureStandards = "EnsureStandards";
	String ChangeManagement_ManageTraceability = "ManageTraceability";
	
	String CapacityManagement_PrepareAndUseModels = "PrepareAndUseModels";
	
	String ConfigurationManagement_ManageConfigurationItems = "ManageConfigurationItems";
	String ConfigurationManagement_ManageConfigInfoAndDocumentation = "ManageConfigInfoAndDocumentation";
	
	String SecurityManagement_ManageSecurity = "ManageSecurity";
	
	String AvailabilityManagement_ManageAvailability = "ManageAvailability";
	String AvailabilityManagement_ManageOutageAndRisks = "ManageOutageAndRisks";
	
	String ServiceAssurance_HelpDeskSupport = "HelpDeskSupport";
	String ServiceAssurance_IncidentManagement = "IncidentManagement";
	String ServiceAssurance_ProblemManagement = "ProblemManagement";
	String ServiceAssurance_ImpactAnalysis = "ImpactAnalysis";
	String ServiceAssurance_DataAlignment = "DataAlignment";
	String ServiceAssurance_IncidentReporting = "IncidentReporting";
	String ServiceAssurance_BugFixing = "BugFixing";
	
	String CONCAT_DOUBLE_UNDERSCORE = "__";
	String SAVE_SUCCESS_MESSAGE = "Data saved successfully.";
	
	// iText Parameters.
	String NotAvailable = "N/A";
	String OpportunityHead = "Opportunity";
	String SolutionHead = "Solution";
	String ServiceScopeHead = "Service Scope";
	String TransformationHead = "Transformation";
	String Top3ADR = "Top 3 ADR";
	String checkLists = "CheckLists";
	String FinancialHead = "Financial";
	String ExecutiveSummary = "Executive Summary";
	String Overview = "Opportunity Overview";
	String aboutClient = "About the Client";
	String valueProposition = "EGI’s Value Proposition";
	String servicesInScope = "Services in-scope";
	String developmentScope = "Development Scope";
	String maintenanceScope = "Maintenance Scope";
	String testingScope = "Testing Scope";
	String managementScope = "Management Scope";
	String servicesOutOfScope = "Services out of scope";
	String architecture = "Architecture";
	String proposedSolution = "Proposed Solution";
	String supportSolution = "Support Solution";
	String crApproach = "Existing System Architecture & Implementation Approach";
	String tools = "Tools & Techniques";
	String connectivity = "Connectivity";
	String estimates = "Ticket Volumetric Estimates";
	String crEstimates = "CR Estimates";
	String crAssumptions = "Assumptions for CRs";
	String skillRequirements = "Skill Requirements";
	String knowledgeTransferMeth = "Knowledge Transfer Methodology";
	String serviceLevels = "Service Levels";
	String serviceWindow = "Service Window";
	String engagementModel = "Engagement Model";
	String timelines = "Project Timelines";
	String assumptions = "Solution Assumptions & Dependencies";
	String responsibility = "Responsibility Matrix";
	String commercials = "Commercials";
	String costSummary = "Cost Summary";
	String termsAndCond = "Terms and Conditions";
	
	String ClientName = "Client Name";
	String Region = "Originating From Region";
	String OpportunnityName = "Opportunity Name";
	String OpportunitySource = "Source Of Opportunity";
	String vertical = "Owning GSC Vertical";
	String duration = "Duration of Engagement (in Months)";
	String opportunityId = "Opportunity Id";
	String model = "Commercial Model";
	String regionalOwner = "Regional Owner";
	String approval = "Approval";
	String currentDate = "Date";
	String documentDistribution = "Document Distribution";
	String name = "Name";
	String version = "Version";
	String changeRemarks = "Change Remarks";
	String abstractKey = "Abstract";
	
	String deliveryModel = "Delivery Model";
	String deliveryType = "Delivery Type";
	String proposedStartDate = "Proposed Steady State Start Date(MM/DD/YYYY)";
	String offshoreDeliveryLoc = "Offshore Delivery Location for GSC Staff ";
	String onshoreDeliveryLoc = "Onshore Location for GSC Staff ";
	String scopeOfService = "Scope of Services Defined By";
	String knowledgeTransfer = "Knowledge Transfer In Scope";
	String ktStartDate = "Proposed KT Start Date (MM/DD/YYYY)";
	String ktDuration = "Duration of KT (Months) ";
	String knowledgeTransferLoc = "Location of Knowledge Transfer";
	String prodLever = "YOY Productivity Lever Applied at Steady State";
	String inputVolDefBy = "Input Volumetrics Defined By";
	String prodGainAppDate = "Productivity Gain First applied Date";
	
	String services = "Services";
	String operationWindow = "Operation Window";
	String gscFte = "GSC FTE(Day 1 SS)";
	
	String partitionName = "Partition Name";
	String durationWeeks = "Duration Weeks(P-L-A-P-D)=Total";
	String rampUpPattern = "Staff Ramp Up Pattern(%)";
	String date = "StartDate to EndDate";
	
	String top3Assumptions = "Top 3 Assumptions";
	String top3Dependencies = "Top 3 Dependencies";
	String top3Risks = "Top 3 Risks";
	String adrStatement = "ADR Statement";
	String adrArea = "ADR Area";
	String adrType = "ADR Type";
	String impact = "Impact";
	String weightage = "Weightage";
	
	String checkListSolDev = "Check List - Solution Development";
	String checkListStaffPlanning = "Check List - Staff Planning";
	
	String staffingPyramidCheck = "Staffing Pyramid applied as per CSI-ADM Solution Metrics Guideline";
	String governAndPmoCheck = "Governance & PMO Lever applied as per defined guidelines";
	String transformationCheck = "Transformation (KT) duration is as per defined guidelines and reviewed by T&T PA Lead";
	String shoreCheck = "Onshore - Offshore Mix is as per defined guidelines";
	String allCostAddressCheck = "All cost adders for out of India business hour support has been added as per HR Policy";
	String onsiteRestrictionCheck = "Onsite travel Restrictions, Policies, Visa Timelines, Costs have been checked and applied";
	String gscSlaCheck = "Any GSC SLA applicable for this opportunity?";
	String slaParameterCheck = "Is the SLA parameters defined by the Region/End Client";
	
	String availabilitySkillsCheck = "Availability of Skills have been pre-checked with RM Team";
	String startDateCheck = "Is the start Date of the project within 90 days of the proposal submission date ?";
	String confirmDeployCheck = "For <= 90 days to start project, confirmation of Deployment is available from RM";
	String confirmTravelDeskCheck = "Visa Eligibility of Practitioners & associated timelines have been confirmed by Travel Desk";
	String approvalPracticeHeadCheck = "For SI->AMS, written approval from respective Practice heads and/or RM team available to continue same practitioners for atleast 6 mths.";
	String demandsRaisedCheck = "All Demands have been raised in EriPro with appropriate Win-Odds Probability";
	
	String currency = "Currency : ";
	String particulars = "Particulars";
	String egilRevenue = "EGIL Revenue";
	String manpower = "Manpower";
	String travel = "Travel";
	String others = "Others";
	String grandTotal = "Total";
	String grandTotalRevenue = "Total Revenue";
	String egilCost = "EGIL Cost";
	String grandTotalCost = "Grand Total Cost";
	String markupNonManPowerCost = "MarkUp On Non Manpower Cost";
	String percntMarkupNonManPowerCost = "% MarkUp On Non Manpower Cost";
	String onsiteEffort = "Onsite Effort (In Hours)";
	String offshoreEffort = "Offshore Effort (In Hours)";
	String grandTotalEffort = "Grand Total Effort (In Hours)";
	String keyRatios = "Key Ratios";
	String onsiteEffortToTotalEffort = "Onsite Effort to Total Effort";
	String offshoreEffortToTotalEffort = "Offshore Effor to Total Effort";
	String blendedCost = "Blended Cost/Hour";
	String normalRateCostAsPercnt = "Normal Rate Cost as % of Overall Cost";
	String hotSkillRateAsPercnt = "Hot Skill Rate as % of overall cost";
	String manpowerAsPercntTotalCost = "Manpower as % of Total cost";
	String travelAsPercntTotalCost = "Travel as % of Total cost";
	String otherAsPercntTotalCost = "Other as % of Total cost";
	String travelCostPerOnsiteHour = "Travel cost per Onsite Hour";
	
	String comments = "Approver's Comments :";
	
	String noDataFound = "No Data Found";
	
	String[] versionControlTableList = {version,name,currentDate,changeRemarks};
	String[] documentControlList = {documentDistribution,name,approval,currentDate};
	String[] oppDetailsList = {ClientName,Region,OpportunnityName,OpportunitySource,vertical,duration,opportunityId,model};
	String[] solDetailsList = {deliveryModel,deliveryType,proposedStartDate,offshoreDeliveryLoc,onshoreDeliveryLoc,scopeOfService,knowledgeTransfer,ktStartDate,ktDuration,knowledgeTransferLoc,prodLever,inputVolDefBy,prodGainAppDate};
	//String[] transformationDetailsList = {partitionName,durationWeeks,rampUpPattern,date};
	String[] top3ADRList = {top3Assumptions,top3Dependencies,top3Risks};
	String[] checkListSolDevList = {staffingPyramidCheck,governAndPmoCheck,transformationCheck,shoreCheck,allCostAddressCheck,onsiteRestrictionCheck,gscSlaCheck,slaParameterCheck};
	String[] checkListStaffPlanningList = {availabilitySkillsCheck,startDateCheck,confirmDeployCheck,confirmTravelDeskCheck,approvalPracticeHeadCheck,demandsRaisedCheck};
	String releaseNotices = "RELEASE NOTICES";
	String documentControl = "DOCUMENT CONTROL";
	String versionControl = "VERSION CONTROL AND HISTORY";
	
	String YES = "Y";
	String TRUE = "True";
	String Priority1 = "P1 (Emergency)";
	String Priority2 = "P2 (High)";
	String Priority3 = "P3 (Medium)";
	String Priority4 = "P4 (Low)";
	
	String SERVICE_FUNCTION_APP_DEV = "APP_DEV";
	String SERVICE_FUNCTION_APP_MAIN = "APP_MAIN";
	
	String ServiceType_Generic = "Generic";
	String ServiceType_Product = "Product";
	
	String ServiceElementName_Fast_Track_Requests = "Fast Track Requests";
}

