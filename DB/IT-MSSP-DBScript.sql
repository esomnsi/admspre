CREATE DATABASE IF NOT EXISTS IT_MSSP_DEV;
USE IT_MSSP_DEV;

#DROP TABLE IF EXISTS ExchangeRate;
CREATE TABLE ExchangeRate 
	(
	ExchangeRateID 		INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	StartDate  DATETIME,
	EndDate   DATETIME,
	DollarRs  FLOAT(10,2),
	PoundRs  FLOAT(10,2),
	USD FLOAT(10,2),
	Pound  FLOAT(10,2),
	DollarPound FLOAT(10,2),
	PoundDollar FLOAT(10,2),
	createdOn DATETIME, 
	PRIMARY KEY(ExchangeRateID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS Country;
CREATE TABLE Country 
	(
	CountryID 		INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	CountryCode 		VARCHAR(4), 
	CountryName 		VARCHAR(50), 
	TimeZone		VARCHAR(15), 
	CurrencyCode 		VARCHAR(4), 
	CurrencyName 		VARCHAR(50), 
	Region 			VARCHAR(4), 
	Active 			VARCHAR(1), 
	PRIMARY KEY(CountryID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS CurrencyExchange;
CREATE TABLE CurrencyExchange
	(
	CurrencyExchangeID 		INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	CountryName 		VARCHAR(100), 
	CurrencyCode 		VARCHAR(50), 
	USDValue 			VARCHAR(20), 
	PRIMARY KEY(CurrencyExchangeID)
	) 
ENGINE=INNODB;


#DROP TABLE IF EXISTS Customer;
CREATE TABLE Customer 
	(
	CustomerID 		INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	CustomerName 		VARCHAR(100), 
	CountryID 		INT UNSIGNED NOT NULL, 
	INDEX (CustomerID), 
	FOREIGN KEY (CountryID) REFERENCES Country (CountryID), 
	PRIMARY KEY(CustomerID)
	) 
ENGINE=INNODB;


#DROP TABLE IF EXISTS ServiceElement;
CREATE TABLE ServiceElement (
  ServiceElementID INT unsigned NOT NULL AUTO_INCREMENT,
  ServiceElementName varchar(50) DEFAULT NULL,
  ServiceElementDescription varchar(2000) DEFAULT NULL,
  ServiceFunctionName varchar(50) DEFAULT NULL,
  ServiceFunctionCode varchar(50) DEFAULT NULL,
  ServiceType varchar(50) DEFAULT NULL,
  Active varchar(1) DEFAULT 'Y',
  HasServiceScope varchar(1) DEFAULT 'N',
  CreatedBy varchar(50) DEFAULT NULL,
  CreatedTimestamp datetime DEFAULT NULL,
  UpdatedBy varchar(50) DEFAULT NULL,
  UpdatedTimestamp datetime DEFAULT NULL,
  INDEX (ServiceElementID),
  PRIMARY KEY (ServiceElementID)
) ENGINE=InnoDB;

#DROP TABLE IF EXISTS ServiceScope;
CREATE TABLE ServiceScope (
  ServiceScopeID INT unsigned NOT NULL AUTO_INCREMENT,
  ServiceScopeCategory varchar(50) DEFAULT NULL,
  ServiceScopeName varchar(50) DEFAULT NULL,
  ServiceScopeDescription varchar(2000) DEFAULT NULL,
  ServiceElementID int(10) unsigned DEFAULT NULL,
  Active varchar(1) DEFAULT 'Y',
  CreatedBy varchar(50) DEFAULT NULL,
  CreatedTimestamp datetime DEFAULT NULL,
  UpdatedBy varchar(50) DEFAULT NULL,
  UpdatedTimestamp datetime DEFAULT NULL,
  ServiceElementDefaultValue int(10) unsigned DEFAULT NULL,
  INDEX (ServiceScopeID), 
  FOREIGN KEY (ServiceElementID) REFERENCES ServiceElement (ServiceElementID),
  PRIMARY KEY (ServiceScopeID)
) ENGINE=InnoDB;


#DROP TABLE IF EXISTS Status;
CREATE TABLE Status 
	(
	StatusID 		INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	StatusName 		VARCHAR(50), 
	PRIMARY KEY(StatusID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS InputFields;
CREATE TABLE InputFields 
	(
	InputFieldsID 		INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	ServiceScopeID 		INT UNSIGNED NOT NULL, 
	InputFieldsName 	VARCHAR(200), 
	DefaultValues 		VARCHAR(50), 
	CreatedBy 		VARCHAR(50), 
	CreatedTimestamp 	DATETIME, 
	UpdatedBy 		VARCHAR(50), 
	UpdatedTimestamp 	DATETIME, 
	INDEX (InputFieldsID), 
	FOREIGN KEY (ServiceScopeID) REFERENCES ServiceScope (ServiceScopeID), 
	PRIMARY KEY(InputFieldsID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS OtherDefaults;
CREATE TABLE OtherDefaults 
	(
	OtherDefaultsID 	INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	ApplicationArea 	VARCHAR(50), 
	OtherFieldsName 	VARCHAR(50), 
	DefaultValues 		VARCHAR(50), 
	CreatedBy 		VARCHAR(50), 
	CreatedTimestamp 	DATETIME, 
	UpdatedBy 		VARCHAR(50), 
	UpdatedTimestamp 	DATETIME,
	ServiceScopeID int(10) unsigned DEFAULT NULL,
	PRIMARY KEY(OtherDefaultsID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS ApplicationConfig;
CREATE TABLE ApplicationConfig 
	(
	ApplicationConfigID 	INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	ConfigName 		VARCHAR(50),
	ConfigValue 		VARCHAR(50), 
	CreatedBy 		VARCHAR(50), 
	CreatedTimestamp 	DATETIME, 
	UpdatedBy 		VARCHAR(50),
	UpdatedTimestamp 	DATETIME, 
	PRIMARY KEY(ApplicationConfigID)
	) 
ENGINE=INNODB;
	
#DROP TABLE IF EXISTS JobRole;	
CREATE TABLE JobRole 
	(
	JobRoleID			INT UNSIGNED NOT NULL AUTO_INCREMENT,
	RoleName 			VARCHAR(200),
	HRMSObject			varchar(100),
	JobRoleFamily		varchar(100),
	JobRoleFamilyNumber	varchar(50),
	UsedByFA			varchar(50),
	CCMFlag				boolean,
	PRIMARY KEY(JobRoleID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS JobStage;
create table JobStage (
	JobStageID int(10) unsigned not null AUTO_INCREMENT, 
	Stage varchar(50),
	CCMFlag boolean,
	PRIMARY KEY(JobStageID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS JobStage;
create table JobRoleStages (
	JobRoleStageID int(10) unsigned not null auto_increment, 
	JobRoleID int(10) unsigned , 
	JobStageID int(10) unsigned ,
	foreign key (jobRoleID) references JobRole (JobRoleID),
	foreign key (jobStageID) references JobStage (JobStageID),
	primary key (JobRoleStageID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS RateCard;	
CREATE TABLE RateCard 
	(
	RateCardID 			INT UNSIGNED NOT NULL AUTO_INCREMENT,
	CountryID			INT (10) UNSIGNED,
	JobRoleStageID		INT UNSIGNED NOT NULL, 
	Location 			VARCHAR(50), 
	Rate	 			DOUBLE(16,4), 
	CreatedBy 			VARCHAR(50), 
	CreatedTimestamp	DATETIME,
	UpdatedBy 			VARCHAR(50),
	UpdatedTimestamp	DATETIME,
	Gsc					varchar(10),
	SubLocation			varchar (30),
	StatusFlag			varchar(1),
	INDEX (RateCardID), 
	FOREIGN KEY (CountryID) REFERENCES Country (CountryID),
	FOREIGN KEY (JobRoleStageID) REFERENCES JobRoleStages (JobRoleStageID), 
	PRIMARY KEY(RateCardID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS RateCardHistory;
CREATE TABLE RateCardHistory 
	(
		RateCardHistoryID 	int(10) unsigned not null auto_increment, 
		CountryID 			int(10), 
		JobRoleStageID 		int(10),
		Location 			varchar(50), 
		Rate 				double(16,4),
		CreatedTimestamp	DATETIME,
		UpdatedTimestamp 	DATETIME, 
		Gsc 				varchar(10), 
		SubLocation 		varchar(30),
		StatusFlag			varchar(1),
		primary key	(RateCardHistoryID), 
		foreign key (CountryID) references Country(CountryID), 
		foreign key (JobRoleStageID) references JobRoleStages(JobRoleStageID)
	)ENGINE=INNODB;

#DROP TABLE IF EXISTS ApplicationRole;	
CREATE TABLE ApplicationRole
	(
	ApplicationRoleID 	INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	RoleName 		VARCHAR(25), 
	DisplayName varchar(30),
	PRIMARY KEY(ApplicationRoleID)
	) 
ENGINE=INNODB;




#DROP TABLE IF EXISTS User;
CREATE TABLE User 
	(
	UserID			INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	SignumID 		VARCHAR(10), 
	ApplicationRoleID	INT UNSIGNED,
	StartDate		DATETIME,
	EndDate 		DATETIME,
	FirstName varchar(100),
  	LastName varchar(100),
  	EmailID varchar(200),
  	ContactNumber varchar(20),
	INDEX (UserID), 
	FOREIGN KEY (ApplicationRoleID) REFERENCES ApplicationRole (ApplicationRoleID), 
	PRIMARY KEY(UserID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS UserApplicationRole;
CREATE TABLE UserApplicationRole (
  UserID int(11) NOT NULL,
  ApplicationRoleID int(11) NOT NULL
) ENGINE=InnoDB;
#DROP TABLE IF EXISTS DealCharacteristics;
CREATE TABLE DealCharacteristics
(
 DealCharacteristicsID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 DealCharacteristicsName VARCHAR(50),
 Effort INT,
 Cost INT,
 DealIdentifier INT,
 INDEX (DealCharacteristicsID), 
 PRIMARY KEY(DealCharacteristicsID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS OpportunitySource;
CREATE TABLE OpportunitySource
(
 OpportunitySourceID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 Name VARCHAR(30),
 Description VARCHAR(50),
 OrderID INT,
 INDEX (OpportunitySourceID), 
 PRIMARY KEY(OpportunitySourceID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS CommercialModel;
CREATE TABLE CommercialModel
(
 CommercialModelID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 Name VARCHAR(30),
 Description VARCHAR(50),
 OrderID INT,
 INDEX (CommercialModelID), 
 PRIMARY KEY(CommercialModelID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS DeliveryModel;
CREATE TABLE DeliveryModel
(
 DeliveryModelID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 Name VARCHAR(30),
 Description VARCHAR(50),
 INDEX (DeliveryModelID), 
 PRIMARY KEY(DeliveryModelID)
)
ENGINE=INNODB;


#DROP TABLE IF EXISTS DeliveryType;
CREATE TABLE DeliveryType
(
 DeliveryTypeID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 Name VARCHAR(30),
 Description VARCHAR(50),
 INDEX (DeliveryTypeID), 
 PRIMARY KEY(DeliveryTypeID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS InputVolumetricsDefinedBy;
CREATE TABLE InputVolumetricsDefinedBy
(
 InputVolumetricsDefinedByID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 Name VARCHAR(30),
 Description VARCHAR(50),
 INDEX (InputVolumetricsDefinedByID), 
 PRIMARY KEY(InputVolumetricsDefinedByID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS ScopeOfServicesDefinedBy;
CREATE TABLE ScopeOfServicesDefinedBy
(
 ScopeOfServicesDefinedByID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 Name VARCHAR(30),
 Description VARCHAR(50),
 INDEX (ScopeOfServicesDefinedByID), 
 PRIMARY KEY(ScopeOfServicesDefinedByID)
)
ENGINE=INNODB;
#DROP TABLE IF EXISTS Opportunity;
CREATE TABLE Opportunity 
	(
	OpportunityID			INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	OpportunityName     VARCHAR(100),
    CustomerID INT UNSIGNED NOT NULL,
    OpportunitySourceID INT UNSIGNED,
    CommercialModelID INT UNSIGNED,
    CreatedBy VARCHAR(10),
    AssignedTo varchar(7),
	INDEX (OpportunityID), 
	FOREIGN KEY  (OpportunitySourceID) REFERENCES OpportunitySource (OpportunitySourceID),
	FOREIGN KEY  (CommercialModelID) REFERENCES CommercialModel (CommercialModelID),
	FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID), 
	PRIMARY KEY(OpportunityID)
	) 
ENGINE=INNODB;


#DROP TABLE IF EXISTS WorkflowTimeline;	
CREATE TABLE WorkflowTimeline 
	(
	WorkflowTimelineID 	INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	OpportunityID 		INT UNSIGNED NOT NULL, 
	RFPReceiptDate 		DATETIME,
	QuestionnaireSubmissionDate DATETIME, 
	SolutionReviewDate 	DATETIME, 
	SubmissionDate 		DATETIME, 
	ApprovalDate 		DATETIME, 
	AssignedTo 		VARCHAR(50), 
	Comments 		TEXT, 
	INDEX (WorkflowTimelineID), 
	FOREIGN KEY (OpportunityID) REFERENCES Opportunity (OpportunityID), 
	PRIMARY KEY(WorkflowTimelineID)
	) 
ENGINE=INNODB;
#DROP TABLE IF EXISTS OpportunityDetail	
CREATE TABLE OpportunityDetail 
	(
	OpportunityDetailID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
	OpportunityID INT UNSIGNED NOT NULL,
	DeliveryModelID INT UNSIGNED,
	DeliveryTypeID INT UNSIGNED,
	InputVolumetricsDefinedByID  INT UNSIGNED,
	ScopeOfServicesDefinedByID  INT UNSIGNED,
	OpportunityOwner	VARCHAR(50),
	NewBusiness		VARCHAR(1),
	ExistingCustomer	VARCHAR(1),
	CadenceID		VARCHAR(50),
	Vertical		VARCHAR(50),
	TransformationStartDate	DATETIME,
	TransformationEndDate	DATETIME,
	SteadyStateStartDate	DATETIME,
	SteadyStateEndDate	DATETIME,
	Competitors		VARCHAR(255),
	CapexSpend		VARCHAR(50),
	OpexSpend		VARCHAR(50),
	ExistingFTE		VARCHAR(100),
	TurnOver		VARCHAR(50),
	PrimaryBusinessLine	VARCHAR(50),
	RFPReason		VARCHAR(255),
	OtherReason		VARCHAR(255),
	RegionKAM		VARCHAR(50),
	PrimaryVerticalRepresentative	VARCHAR(200),
	ADMSSolutionRepresentative	VARCHAR(200),
	ContractDuration VARCHAR(12),
	SteadyStateDuration VARCHAR(12),
	AccountCommercialResponsible varchar (200),
	CustomerFulfillmentResponsible varchar(200),
	CustomerSolutionResponsible varchar(200),
	TertiarySolutionSME varchar(200),
	ServiceType varchar(50) DEFAULT NULL,
	Offshore float(10,2), 
	OnshoreLocal float(10,2), 
	OnshoreGSC float(10,2), 
	Onshore3PP float(10,2),
	Nearshore float(10,2),
	INDEX (OpportunityDetailID), 
	FOREIGN KEY (OpportunityID) REFERENCES Opportunity (OpportunityID),
	FOREIGN KEY  (DeliveryModelID) REFERENCES DeliveryModel (DeliveryModelID),
	FOREIGN KEY  (DeliveryTypeID) REFERENCES DeliveryType (DeliveryTypeID),
	FOREIGN KEY  (InputVolumetricsDefinedByID) REFERENCES InputVolumetricsDefinedBy (InputVolumetricsDefinedByID),
	FOREIGN KEY  (ScopeOfServicesDefinedByID) REFERENCES ScopeOfServicesDefinedBy (ScopeOfServicesDefinedByID),
	PRIMARY KEY(OpportunityDetailID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS OpportunityLocation	
CREATE TABLE OpportunityLocation
	(
	OpportunityLocationID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
	OpportunityDetailID	INT UNSIGNED NOT NULL,
	CustomerCountry		INT UNSIGNED,
	NearShore		INT UNSIGNED,
	GSC1			INT UNSIGNED,
	GSC2			INT UNSIGNED,
	INDEX (OpportunityLocationID), 
	FOREIGN KEY (OpportunityDetailID) REFERENCES OpportunityDetail (OpportunityDetailID), 
	FOREIGN KEY (CustomerCountry) REFERENCES Country (CountryID),
	FOREIGN KEY (NearShore) REFERENCES Country (CountryID),
	FOREIGN KEY (GSC1) REFERENCES Country (CountryID),
	FOREIGN KEY (GSC2) REFERENCES Country (CountryID),
	PRIMARY KEY(OpportunityLocationID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS OpportunityScope	
CREATE TABLE OpportunityScope
	(
	OpportunityScopeID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
	OpportunityID	INT UNSIGNED NOT NULL,
	ServiceScopeID	INT UNSIGNED NOT NULL,	
	INDEX (OpportunityScopeID), 
	FOREIGN KEY (OpportunityID) REFERENCES Opportunity (OpportunityID), 
    FOREIGN KEY (ServiceScopeID) REFERENCES ServiceScope (ServiceScopeID), 
	PRIMARY KEY(OpportunityScopeID)
	) 
ENGINE=INNODB;
#------------------------------------------------------------------
#DROP TABLE IF EXISTS Solution	
CREATE TABLE Solution
	(
	SolutionID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
	OpportunityID	INT UNSIGNED NOT NULL,
    StatusID	INT UNSIGNED NOT NULL,
    #SubmissionDate	DATETIME,
    #SubmittedBy	VARCHAR(50),
    SolutionApproach	VARCHAR(50),
    SolutionType	VARCHAR(50),
    StepCompleted	VARCHAR(50),
    JobRoleModel	VARCHAR(20),
    UtilizationPerYear varchar(10),
    SolutionVersion	INT,
    CreatedBy VARCHAR(10), 
    CreatedDate DATETIME, 
    UpdatedBy VARCHAR(10), 
    UpdatedDate DATETIME,
	INDEX (SolutionID), 
	FOREIGN KEY (OpportunityID) REFERENCES  Opportunity(OpportunityID), 
    FOREIGN KEY (StatusID) REFERENCES  Status(StatusID),  
 	PRIMARY KEY(SolutionID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS SolutionApproachDimension	
CREATE TABLE SolutionApproachDimension
	(
	SolutionApproachDimensionID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
	SolutionID	INT UNSIGNED NOT NULL,
    DimensionName	VARCHAR(50),
    DimensionAttributeName	VARCHAR(50),
	INDEX (SolutionApproachDimensionID), 
	FOREIGN KEY (SolutionID) REFERENCES  Solution(SolutionID), 
 	PRIMARY KEY(SolutionApproachDimensionID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS SolutionADR	
CREATE TABLE SolutionADR
	(
	SolutionADRID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
	SolutionID	INT UNSIGNED NOT NULL,
	ADRCategory	VARCHAR(100),
    ADRStatement	VARCHAR(255),
    ADRArea	VARCHAR(100),
    ADRType	VARCHAR(100),
    ADRImpact	INT,
    ADRWeightage	FLOAT(10,2),
	INDEX (SolutionADRID), 
	FOREIGN KEY (SolutionID) REFERENCES  Solution(SolutionID),
 	PRIMARY KEY(SolutionADRID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS StaffingPlan	
CREATE TABLE StaffingPlan
	(
	StaffingPlanID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
	SolutionID	INT UNSIGNED NOT NULL,
    Region 	VARCHAR(4),
    Vertical 	VARCHAR(100),
    EGIBU 	VARCHAR(100),
    Client 	VARCHAR(100),
    Opportunity 	VARCHAR(100),
    ProjectType 	VARCHAR(100),
    GTTOpportunity 	VARCHAR(1),
    NoDemandedPositions 	VARCHAR(100),
    AcceptSubcontractors 	VARCHAR(1),
    WinOddsProbability 	VARCHAR(100),
    PrimaryLocation	VARCHAR(100),
    SecondaryLocation	VARCHAR(100),
    OnshoreLocation 	VARCHAR(100),
    PositionStartdate 	DATETIME,
    PositionEnddate 	DATETIME,
    OnshoreStartdate 	DATETIME,
    OnshoreEnddate	DATETIME,
    GradeLow 	VARCHAR(100),
    GradeHigh	VARCHAR(100),
    JobFamily	VARCHAR(100),
    JobRoleID INT UNSIGNED NOT NULL,
    Competency	VARCHAR(100),
    Practice	VARCHAR(100),
    PracticeArea	VARCHAR(100),
    PrimarySkillSet	VARCHAR(100),
    SecondarySkillSet	VARCHAR(100),
    TypeofRequest	VARCHAR(100),
    ResourceManager	VARCHAR(100),
    AdditionalInformation	VARCHAR(255),
    DemandRaisedBy varchar (100),
	DemandCreateddate DATETIME,
	Status varchar(40),
	INDEX (StaffingPlanID), 
    FOREIGN KEY (JobRoleID) REFERENCES  JobRole(JobRoleID),
	FOREIGN KEY (SolutionID) REFERENCES  Solution(SolutionID), 
 	PRIMARY KEY(StaffingPlanID)
	) 
ENGINE=INNODB;


#DROP TABLE IF EXISTS FTEStaging	
CREATE TABLE FTEStaging
	(
	FTEStagingID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
	SolutionID	INT UNSIGNED NOT NULL,
    OpportunityScopeID	INT UNSIGNED NOT NULL,
    JobRoleStageID INT UNSIGNED NOT NULL,
	MonthYear	DATE,
    Location	VARCHAR(20),
    SubLocation varchar(50) default NULL,
    FTECount	FLOAT(10,2),
	INDEX (FTEStagingID),
	FOREIGN KEY (SolutionID) REFERENCES  Solution(SolutionID),
    FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE,
    FOREIGN KEY (JobRoleStageID) REFERENCES  JobRoleStages(JobRoleStageID),
 	PRIMARY KEY(FTEStagingID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS AdditionalFTE;
create table AdditionalFTE (
AdditionalFTEID INT UNSIGNED NOT NULL AUTO_INCREMENT,
SolutionID INT UNSIGNED NOT NULL,
OpportunityScopeID INT,
JobRoleStageID INT unsigned NOT NULL,
Location VARCHAR(20),
SubLocation VARCHAR(50) default NULL,
FromMonthYear Date,
ToMonthYear Date,
NoofFTE FLOAT(10,2),
INDEX(AdditionalFTEID),
FOREIGN KEY (SolutionID) REFERENCES  Solution(SolutionID),
FOREIGN KEY (JobRoleStageID) REFERENCES  JobRoleStages(JobRoleStageID),
PRIMARY KEY(AdditionalFTEID)
)
ENGINE=INNODB;


#DROP TABLE IF EXISTS NonLabourCost;
create table NonLabourCost (
NonlabourCostID INT UNSIGNED NOT NULL AUTO_INCREMENT,
SolutionID INT UNSIGNED NOT NULL,
Year INT,
ITNoofTravels INT,
ITStayDuration INT,
ITShortVisaCost INT,
ITTickectCost INT,
ITDailyPerDiem INT,
ITHotelCostPerNight INT,
ITConveyancePerDay INT,
DTNoofTravels INT,
DTStayDuration INT,
DTTickectCost INT,
DTDailyPerDiem INT,
DTHotelCostPerNight INT,
DTConveyancePerDay INT,
EGIConnectivityCost INT,
GSCConnectivityCost INT,
OtherConnectivityCost INT,
MonthlyNightAllowance INT,
PCPeopleAtNight INT,
MonthlyWeekendAllowance INT,
PCPeopleAtWeekend INT,
MonthMobileCost INT,
MonthlyDataCardCost INT,
HWSW3PPCost INT,
TrainingCost INT,
OtherCost INT,
INDEX(NonlabourCostID),
FOREIGN KEY (SolutionID) REFERENCES  Solution(SolutionID),
PRIMARY KEY(NonlabourCostID)
)
ENGINE=INNODB;



#DROP TABLE IF EXISTS FTESummary;
create table FTESummary (
FTESummaryID INT UNSIGNED NOT NULL AUTO_INCREMENT,
OpportunityScopeID INT UNSIGNED NOT NULL,
SolutionID INT UNSIGNED NOT NULL,
JobRoleStageID INT(10) default NULL,
Location VARCHAR(20),
SubLocation VARCHAR(50) default NULL,
FTECount FLOAT(10,2),
FromDate Date,
ToDate Date,
IsSteadyStateType Bool,
INDEX(FTESummaryID),
FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE,
PRIMARY KEY(FTESummaryID)
)
ENGINE=INNODB;


#DROP TABLE IF EXISTS CostSummary;
create table CostSummary (
CostSummaryID INT UNSIGNED NOT NULL AUTO_INCREMENT,
OpportunityScopeID INT UNSIGNED NOT NULL,
JobeRoleID INT,
Location VARCHAR(20),
CostINR FLOAT(10,2),
INDEX(CostSummaryID),
FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE,
PRIMARY KEY(CostSummaryID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS SolutionInputDefinition	
#CREATE TABLE SolutionInputDefinition 
#	(
#	SolutionInputDefinitionID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
#	SolutionID			INT UNSIGNED NOT NULL,
#	SolutionApproachDimensionID	INT UNSIGNED,
#	InputFieldsID			INT UNSIGNED NOT NULL,
#	InputFieldsValue		VARCHAR(255),
#	Unit					VARCHAR(255),
#	INDEX (SolutionInputDefinitionID), 
#	FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
#	FOREIGN KEY (InputFieldsID) REFERENCES InputFields (InputFieldsID), 	
#	FOREIGN KEY (SolutionApproachDimensionID) REFERENCES SolutionApproachDimension (SolutionApproachDimensionID), 
#	PRIMARY KEY(SolutionInputDefinitionID)
#	) 
#ENGINE=INNODB;


#DROP TABLE IF EXISTS SolutionComplexity	
CREATE TABLE SolutionComplexity 
	(
	SolutionComplexityID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
	SolutionID		INT UNSIGNED NOT NULL,
	SkillRating		SMALLINT,
	RegionRating		SMALLINT,
	AuditRating		SMALLINT,
	SLARating		SMALLINT,
	SkillWeightage		SMALLINT,
	RegionWeightage		SMALLINT,
	AuditWeightage		SMALLINT,
	SLAWeightage		SMALLINT,
	ComputedComplexity	SMALLINT,
	OverriddenComplexity 	SMALLINT,
	ComplexityAdjustor 	FLOAT(10,2),
	ContingencyEffort 	FLOAT(10,2),
	INDEX (SolutionComplexityID), 
	FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
	PRIMARY KEY(SolutionComplexityID)
	) 
ENGINE=INNODB;


#DROP TABLE IF EXISTS APAComplexity	
CREATE TABLE APAComplexity 
	(
	APAComplexityID		INT UNSIGNED NOT NULL AUTO_INCREMENT,
	SolutionID		INT UNSIGNED NOT NULL,
	OpportunityScopeID	INT UNSIGNED NOT NULL,
	ComplexityAssessed	SMALLINT,
	ComplexityOverride	SMALLINT,
	FTEPercentage		FLOAT(10,2),
	DealCharacteristicsID INT UNSIGNED,
    AdjustedFTE	FLOAT(10,2),
    AdjustedHrs	FLOAT(10,2),
	INDEX (APAComplexityID), 
	FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
	FOREIGN KEY (OpportunityScopeID) REFERENCES OpportunityScope (OpportunityScopeID) on delete CASCADE,
	FOREIGN KEY  (DealCharacteristicsID) REFERENCES DealCharacteristics (DealCharacteristicsID),
	PRIMARY KEY(APAComplexityID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS SolutionAPA
CREATE TABLE SolutionAPA
	(	
	SolutionAPAID		INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	SolutionID 		INT UNSIGNED NOT NULL , 
	OpportunityScopeID 	INT UNSIGNED NOT NULL,
	BusinessFunction	VARCHAR(100),
	ApplicationName		VARCHAR(100),
	NoUsers			INT,
	Platform		VARCHAR(100),
	Databasenm		VARCHAR(100),
	PrimarySkill		VARCHAR(100),
	SecondarySkill		VARCHAR(100),
	BusinessCriticalilty	INT,
	LevelofDocuments	INT,
	Stability		INT,
	Longivity		INT,
	Complexity		INT,
	PercentageShare		INT,
	ComputedRiskExposure	FLOAT(10,2),
	ComputedRiskFactor	FLOAT(10,2),
	INDEX (SolutionAPAID), 
    FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
    FOREIGN KEY (OpportunityScopeID) REFERENCES OpportunityScope (OpportunityScopeID) on delete CASCADE, 
    PRIMARY KEY(SolutionAPAID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS APAWeightage
CREATE TABLE APAWeightage
	(	
	APAWeightageID		INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	SolutionID 		INT UNSIGNED NOT NULL , 
	BusinessCriticalilty	INT,
	LevelofDocuments	INT,
	Stability		INT,
	Longivity		INT,
	Complexity		INT,
	INDEX (APAWeightageID), 
    FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
    PRIMARY KEY(APAWeightageID)
	) 
ENGINE=INNODB;

# NOT in Current USE
#DROP TABLE IF EXISTS StartUpFTE;
#CREATE TABLE StartUpFTE 
#	(		
#	StartUpFTEID		INT UNSIGNED NOT NULL AUTO_INCREMENT, 
#	SolutionID 		INT UNSIGNED NOT NULL , 
#	OpportunityScopeID INT UNSIGNED NOT NULL , 
#	ComputedHrs	INT,
#	ComputedFTE	FLOAT(10,2),
#	RolledupFTE	FLOAT(10,2),
#	INDEX (StartUpFTEID), 
#    FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID),
#    FOREIGN KEY (OpportunityScopeID) REFERENCES OpportunityScope (OpportunityScopeID) on delete CASCADE,
#    PRIMARY KEY(StartUpFTEID)
#	) 
#ENGINE=INNODB;

#DROP TABLE IF EXISTS SolutionLever
CREATE TABLE SolutionLever 
	(		
	SolutionLeverID		INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	SolutionID 			INT UNSIGNED NOT NULL , 
	SolutionLeverApproach	VARCHAR(20),
	INDEX (SolutionLeverID), 
    FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
    PRIMARY KEY(SolutionLeverID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS ProductivityLever
CREATE TABLE ProductivityLever
	(	
	ProductivityLeverID	INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	SolutionLeverID		INT UNSIGNED NOT NULL , 
	OpportunityScopeID 	INT UNSIGNED NOT NULL , 
	MonthYear		DATE,
	OrgStructure		FLOAT(10,2),
	Process			FLOAT(10,2),
	ServiceManagement	FLOAT(10,2),
	TeamSourcing		FLOAT(10,2),
	PortfolioOptimization	FLOAT(10,2),
	ComputedProductivity	FLOAT(10,2),
	CalculatedFTE		FLOAT(10,2),

	INDEX (ProductivityLeverID), 
    FOREIGN KEY (SolutionLeverID) REFERENCES SolutionLever (SolutionLeverID), 
    FOREIGN KEY (OpportunityScopeID) REFERENCES OpportunityScope (OpportunityScopeID) on delete CASCADE, 
    PRIMARY KEY(ProductivityLeverID)
	) 
ENGINE=INNODB;


#DROP TABLE IF EXISTS 	
CREATE TABLE LocationBreakup 
	(		
	LocationBreakupID	INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	SolutionLeverID		INT UNSIGNED NOT NULL , 
	OpportunityScopeID 	INT UNSIGNED NOT NULL , 
	OnshorePC		FLOAT(10,2),
	OffshorePC		FLOAT(10,2),
	OnshoreFTE		FLOAT(10,2),
	OffshoreFTE		FLOAT(10,2),
	OnshoreLocalPC	FLOAT(10,2),
	OnshoreGSCiPC	FLOAT(10,2),
	Onshore3PPPC	FLOAT(10,2),
	OnshoreLocalFTE	FLOAT(10,2),
	OnshoreGSCiFTE	FLOAT(10,2),
	Onshore3PPFTE	FLOAT(10,2),
	NearShorePC		FLOAT(10,2),
	NearShoreFTE	FLOAT(10,2),
	MonthYear DATE,
	INDEX (LocationBreakupID), 
	FOREIGN KEY (SolutionLeverID) REFERENCES SolutionLever (SolutionLeverID), 
	FOREIGN KEY (OpportunityScopeID) REFERENCES OpportunityScope (OpportunityScopeID) on delete CASCADE, 
	PRIMARY KEY(LocationBreakupID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS LocationPyramid
CREATE TABLE LocationPyramid 
	(		
	LocationPyramidID	INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	SolutionLeverID		INT UNSIGNED NOT NULL , 
	OpportunityScopeID 	INT UNSIGNED NOT NULL , 
	JobRoleStageID			INT UNSIGNED NOT NULL , 
	Location			VARCHAR(20),		
	DistributionPC 		FLOAT(10,2),
	FTECount			FLOAT(10,2),
	MonthYear			Date,
	SubLocation			VARCHAR(50),
	INDEX (LocationPyramidID), 
    FOREIGN KEY (SolutionLeverID) REFERENCES SolutionLever (SolutionLeverID), 
    FOREIGN KEY (OpportunityScopeID) REFERENCES OpportunityScope (OpportunityScopeID) on delete CASCADE, 
    FOREIGN KEY (JobRoleStageID) REFERENCES JobRoleStages (JobRoleStageID), 
    PRIMARY KEY(LocationPyramidID)
	) 
ENGINE=INNODB;



#DROP TABLE IF EXISTS ServiceElementJobRoleStages
CREATE TABLE ServiceElementJobRoleStages 
	(		
	ServiceElementJobRoleStagesID	INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	ServiceElementID				INT UNSIGNED NOT NULL , 
	JobRoleStagesID					INT UNSIGNED NOT NULL , 
	OnshoreLocalDefault				VARCHAR(10),
	OnshoreGSCDefault				VARCHAR(10),
	Onshore3PPDefault				VARCHAR(10),
	NearshoreDefault				VARCHAR(10),
	OffshoreDefault					VARCHAR(10),
	INDEX (ServiceElementJobRoleStagesID), 
    FOREIGN KEY (ServiceElementID) REFERENCES ServiceElement (ServiceElementID), 
    FOREIGN KEY (JobRoleStagesID) REFERENCES JobRoleStages (JobRoleStageID), 
    PRIMARY KEY(ServiceElementJobRoleStagesID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS LocationBreakupDefault;
CREATE TABLE LocationBreakupDefault 
	(
	LocationBreakupDefaultID 	INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	ServiceElementID INT(10) unsigned DEFAULT NULL,
	OnshoreLocalPC varchar(10) DEFAULT NULL,
	OnshoreGSCPC varchar(10) DEFAULT NULL,
	Onshore3PPPC varchar(10) DEFAULT NULL,
	NearshoreGSCPC varchar(10) DEFAULT NULL,
	OffshoreGSCPC varchar(10) DEFAULT NULL,
	INDEX (LocationBreakupDefaultID), 
	FOREIGN KEY (ServiceElementID) REFERENCES ServiceElement (ServiceElementID),
	PRIMARY KEY(LocationBreakupDefaultID)
	) 
ENGINE=INNODB;



#DROP TABLE IF EXISTS PEOPLE_STACK;
CREATE TABLE PEOPLE_STACK 
	(
  NAME varchar(45) DEFAULT NULL,
  ROLE varchar(45) DEFAULT NULL,
  CATEGORY varchar(45) DEFAULT NULL,
  REPORTING_MGR char(1) DEFAULT '0',
  DEPARTMENT varchar(45) DEFAULT NULL,
  IN_SCOPE char(1) DEFAULT '0',
  SCOPE varchar(45) DEFAULT NULL,
  ANNUAL_HRS decimal(10,2) DEFAULT 0.00,
  YEARLY_UTIL decimal(5,2) DEFAULT 0.00,
  ANNUAL_SALARY decimal(10,2) DEFAULT 0.00,
  CURRENCY varchar(10) DEFAULT NULL,
  YEARS_EMPLOYMENT decimal(4,2) DEFAULT NULL,
  BASE_LOCATION varchar(45) DEFAULT NULL,
  SOL_ID int(11) NOT NULL,
  RESOURCE_ID int(11) NOT NULL,
  ANNUAL_SALARY_USSD decimal(10,2) DEFAULT 0.00,
  PRIMARY KEY (SOL_ID,RESOURCE_ID)
) ENGINE=InnoDB;

#DROP TABLE IF EXISTS LESS_EFFORT;
CREATE TABLE LESS_EFFORT (
  PEOPLE_MANAGEMENT decimal(10,2) DEFAULT NULL,
  OTHERS decimal(10,2) DEFAULT NULL,
  COMMENTS varchar(500) DEFAULT NULL,
  SOL_ID int(11) NOT NULL,
  PRIMARY KEY (SOL_ID)
) ENGINE=InnoDB;

#DROP TABLE IF EXISTS STAFF_CATEGORY;
CREATE TABLE STAFF_CATEGORY (
  ID int(11) NOT NULL,
  CATEGORY varchar(20) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB;

#DROP TABLE IF EXISTS STAFF_CATEGORY_ALLOCATION;
CREATE TABLE STAFF_CATEGORY_ALLOCATION (
  STAFF_CATEGORY_ID int(11) NOT NULL,
  SOL_ID int(11) NOT NULL,
  PERCENTAGE_EFFORT decimal(4,2) DEFAULT 0.00,
  ANNUAL_UTILIZATION decimal(4,2) DEFAULT 0.00,
  FTE decimal(10,2) DEFAULT 0.00,
  EFFORT decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (SOL_ID,STAFF_CATEGORY_ID),
  KEY fk_STAFF_CATEGORY_ALLOCATION (STAFF_CATEGORY_ID),
  CONSTRAINT fk_STAFF_CATEGORY_ALLOCATION FOREIGN KEY (STAFF_CATEGORY_ID) REFERENCES STAFF_CATEGORY (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;

#New tables#################
#STAFF_CATEGORY modified on above table
#DROP TABLE IF EXISTS Solution_People_Stack;
CREATE TABLE Solution_People_Stack (
  Solution_People_Stack_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
  StaffCategoryName int(11) NOT NULL,
  PRIMARY KEY (Solution_People_Stack_ID)
) ENGINE=InnoDB;

#Not in Current use
#DROP TABLE IF EXISTS PeopleStackResourceDetails
#CREATE TABLE PeopleStackResourceDetails 
#	(		
#    PeopleStackResourceDetailsID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
#    SolutionPeopleStackID	INT UNSIGNED NOT NULL , 
#    Name	VARCHAR(100),
#    JobPosition	VARCHAR(50),
#    EmploymentCategory	VARCHAR(20),
#    ReportingManager	INT,
#    Department	VARCHAR(20),
#    InScopeService	INT,
#    ServiceScopeID INT UNSIGNED NOT NULL , 
#    BaseLocation	VARCHAR(100),
#    AnualHours	FLOAT(10,2),
#    YearlyUtilization	FLOAT(10,2),
#    AnualSalary	FLOAT(10,2),
#    AnualSalaryUSD	FLOAT(10,2),
#    YearsOfEmployment	VARCHAR(20),
#    FOREIGN KEY (SolutionPeopleStackID) REFERENCES  Solution_People_Stack(Solution_People_Stack_ID), 
#    FOREIGN KEY (ServiceScopeID) REFERENCES  ServiceScope(ServiceScopeID), 
#    PRIMARY KEY(PeopleStackResourceDetailsID)
#	) 
#ENGINE=INNODB;
#
##DROP TABLE IF EXISTS PeopleStackSummary
#CREATE TABLE PeopleStackSummary 
#	(		
#    PeopleStackSummaryID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
#    SolutionPeopleStackID   INT UNSIGNED NOT NULL,
#    LessManagementEffort	FLOAT(10,2),
#    LessOthers	FLOAT(10,2),
#    LessOthersComment	VARCHAR(200),
#    FOREIGN KEY (SolutionPeopleStackID) REFERENCES  Solution_People_Stack(Solution_People_Stack_ID), 
#    PRIMARY KEY(PeopleStackSummaryID)
#	) 
#ENGINE=INNODB;
#
##DROP TABLE IF EXISTS PeopleStackStartUpFTE
#CREATE TABLE PeopleStackStartUpFTE 
#	(
#        PeopleStackStartUpFTEID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
#        SolutionPeopleStackID INT UNSIGNED NOT NULL,
#        OpportunityScopeID INT UNSIGNED NOT NULL,
#        StaffCategoryID	INT,
#        PercentageEffort	FLOAT(10,2),
#        AnualUtilization	FLOAT(10,2),
#        ComputedFTE	FLOAT(10,2),
#        ComputedHrs	INT,
#        FOREIGN KEY (SolutionPeopleStackID) REFERENCES  Solution_People_Stack(Solution_People_Stack_ID), 
#        FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE, 
#        PRIMARY KEY(PeopleStackStartUpFTEID)
#    ) 
#ENGINE=INNODB;


#Changes done for new changes in volumetric services
#DROP TABLE IF EXISTS SupportWindowMatrix
CREATE TABLE SupportWindowMatrix (
	  SupportWindowMatrixID int(10) unsigned NOT NULL AUTO_INCREMENT,
	  SupportWindow varchar(50),
	  DerivedFactor float,
  PRIMARY KEY (SupportWindowMatrixID)
) 
ENGINE=InnoDB;

#DROP TABLE IF EXISTS TouchPointChannel;
CREATE TABLE TouchPointChannel (
  TouchPointChannelID int(10) unsigned NOT NULL AUTO_INCREMENT,
  ChannelName varchar(50) NOT NULL,
  PRIMARY KEY (TouchPointChannelID)
) 
ENGINE=InnoDB;

#DROP TABLE IF EXISTS SolutionL1Operations;
CREATE TABLE SolutionL1Operations(
		SolutionL1OperationsID INT UNSIGNED NOT NULL AUTO_INCREMENT,
		SolutionID INT UNSIGNED,
		SolutionApproachDimensionID INT UNSIGNED,
		SupportWindowMatrixID INT UNSIGNED,
		OpportunityScopeID INT UNSIGNED,
		PCofL1Incidents FLOAT(10,2),
		TotalL1IncidentsperYr FLOAT(10,2),
		PCSimpleIncidents FLOAT(10,2),
		PCMediumIncidents FLOAT(10,2),
		PCComplexIncidents FLOAT(10,2),
		HrsSimpleIncidents FLOAT(10,2),
		HrsMediumIncidents FLOAT(10,2),
		HrsComplexIncidents FLOAT(10,2),
		AvgResolutionTimeHrs FLOAT(10,2),
		AnualHrsSpent FLOAT(10,2),
		UtilizationperYr FLOAT(10,2),
		BaseL1OperationFTE FLOAT(10,2),
		AugmentedL1OperationFTE FLOAT(10,2),
		INDEX (SolutionL1OperationsID), 
		FOREIGN KEY (SolutionID) REFERENCES  Solution(SolutionID), 
    	FOREIGN KEY (SolutionApproachDimensionID) REFERENCES  SolutionApproachDimension(SolutionApproachDimensionID),
    	FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE, 
    	FOREIGN KEY (SupportWindowMatrixID) REFERENCES  SupportWindowMatrix(SupportWindowMatrixID),
        PRIMARY KEY(SolutionL1OperationsID)
)
ENGINE=INNODB;


#DROP TABLE IF EXISTS SolutionL1AddServices;
CREATE TABLE SolutionL1AddServices(
		SolutionL1AddServicesID INT UNSIGNED NOT NULL AUTO_INCREMENT,
		SolutionID INT UNSIGNED,
		SolutionApproachDimensionID INT UNSIGNED,
		OpportunityScopeID INT UNSIGNED,
		ScopeAccessMgmt VARCHAR(3),
		ScopeRequestMgmt VARCHAR(3),
		ScopeEventMgmt VARCHAR(3),
		ScopeL1BillOperation VARCHAR(3),
		ScopeL1Preventive VARCHAR(3),
		ScopeL1Change VARCHAR(3),
		ScopeL1Routine VARCHAR(3),
		ScopeL1Performance VARCHAR(3),
		EffortAccessMgmt FLOAT(10,2),
		EffortRequestMgmt FLOAT(10,2),
		EffortEventMgmt FLOAT(10,2),
		EffortL1BillOperation FLOAT(10,2),
		EffortL1Preventive FLOAT(10,2),
		EffortL1Change FLOAT(10,2),
		EffortL1Routine FLOAT(10,2),
		EffortL1Performance FLOAT(10,2),
		FTEAccessMgmt FLOAT(10,2),
		FTERequestMgmt FLOAT(10,2),
		FTEEventMgmt FLOAT(10,2),
		FTEL1BillOperation FLOAT(10,2),
		FTEL1Preventive FLOAT(10,2),
		FTEL1Change FLOAT(10,2),
		FTEL1Routine FLOAT(10,2),
		FTEL1Performance FLOAT(10,2),
		BaseL1ServiceFTE FLOAT(10,2),
		AugmentedL1ServiceFTE FLOAT(10,2),
		INDEX (SolutionL1AddServicesID), 
    	FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID),
    	FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE, 
    	FOREIGN KEY (SolutionApproachDimensionID) REFERENCES  SolutionApproachDimension(SolutionApproachDimensionID),
        PRIMARY KEY(SolutionL1AddServicesID)
		)
ENGINE=INNODB;

#DROP TABLE IF EXISTS SolutionServiceDesk;
CREATE TABLE  SolutionServiceDesk  (
   ServiceDeskID  int(10) unsigned NOT NULL AUTO_INCREMENT,
   SolutionID  int(10) unsigned NOT NULL,
   TouchPointChannelID  int(10) unsigned ,
   SupportWindowMatrixID  int(10) unsigned,
   OpportunityScopeID INT UNSIGNED NOT NULL,
   TransactionsPerMonth  float(10,2) ,
   AverageHandlingTime  float(10,2) ,
   TotalTransPerYear  float(10,2) ,
   DurationOfCallsPerYear  float(10,2) ,
   UtilizationPerYear  float(10,2),
   BaseServiceDeskFTE  float(10,2),
   AugmentedHeads  float(10,2),
   SolutionApproachDimensionID  int(11) unsigned,
   PRIMARY KEY ( ServiceDeskID ),
   FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID),
   FOREIGN KEY (SupportWindowMatrixID) REFERENCES SupportWindowMatrix (SupportWindowMatrixID),
   FOREIGN KEY (TouchPointChannelID) REFERENCES  TouchPointChannel(TouchPointChannelID),
   FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE, 
   FOREIGN KEY (SolutionApproachDimensionID) REFERENCES  SolutionApproachDimension(SolutionApproachDimensionID)

) ENGINE=InnoDB;

#DROP TABLE IF EXISTS SolutionL2Operations;
CREATE TABLE SolutionL2Operations
(
	SolutionL2OperationsID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
	SolutionID	INT UNSIGNED NOT NULL,
	SolutionApproachDimensionID	INT UNSIGNED,
	SupportWindowMatrixID	INT UNSIGNED,
	OpportunityScopeID INT UNSIGNED,
	PCofL2Incidents	FLOAT(10,2),
	TotalL2IncidentsperYr	FLOAT(10,2),
	PCSimpleIncidents	FLOAT(10,2),
	PCMediumIncidents	FLOAT(10,2),
	PCComplexIncidents	FLOAT(10,2),
	HrsSimpleIncidents	FLOAT(10,2),
	HrsMediumIncidents	FLOAT(10,2),
	HrsComplexIncidents	FLOAT(10,2),
	AvgResolutionTimeHrs	FLOAT(10,2),
	AnualHrsSpent	FLOAT(10,2),
	UtilizationperYr	FLOAT(10,2),
	BaseL2OperationFTE	FLOAT(10,2),
	AugmentedL2OperationFTE	FLOAT(10,2),
	INDEX (SolutionL2OperationsID), 
	FOREIGN KEY (SolutionID) REFERENCES  Solution(SolutionID),
	FOREIGN KEY (SolutionApproachDimensionID) REFERENCES  SolutionApproachDimension(SolutionApproachDimensionID),
	FOREIGN KEY (SupportWindowMatrixID) REFERENCES  SupportWindowMatrix(SupportWindowMatrixID),
	FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE, 
	PRIMARY KEY(SolutionL2OperationsID)
    ) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS SolutionL2AddServices;
CREATE TABLE SolutionL2AddServices (
  SolutionL2AddServicesID int(10) unsigned NOT NULL AUTO_INCREMENT,
  SolutionID int(10) unsigned,
  SolutionApproachDimensionID int(10) unsigned,
  OpportunityScopeID INT UNSIGNED,
  ScopeAvailMgmt varchar(3),
  ScopeCapacityMgmt varchar(3),
  ScopeSecurityMgmt varchar(3),
  ScopeL2BillOperation varchar(3),
  ScopeL2Preventive varchar(3),
  ScopeL2Change varchar(3),
  ScopeL2Routine varchar(3),
  ScopeL2Performance varchar(3),
  ScopeL2ReleaseDev varchar(3),
  EffortAvailMgmt float(10,2),
  EffortCapacityMgmt float(10,2),
  EffortSecurityMgmt float(10,2),
  EffortL2BillOperation float(10,2),
  EffortL2Preventive float(10,2),
  EffortL2Change float(10,2),
  EffortL2Routine float(10,2),
  EffortL2Performance float(10,2),
  EffortL2ReleaseDev float(10,2),
  FTEAvailMgmt float(10,2),
  FTECapacityMgmt float(10,2),
  FTESecurityMgmt float(10,2),
  FTEL2BillOperation float(10,2),
  FTEL2Preventive float(10,2),
  FTEL2Change float(10,2),
  FTEL2Routine float(10,2),
  FTEL2Performance float(10,2),
  FTEL2ReleaseDev float(10,2),
  BaseL2ServiceFTE float(10,2),
  AugmentedL2ServiceFTE float(10,2),
  PRIMARY KEY (SolutionL2AddServicesID),
  FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID),
  FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE, 
  FOREIGN KEY (SolutionApproachDimensionID) REFERENCES SolutionApproachDimension (SolutionApproachDimensionID)
) ENGINE=InnoDB;

#DROP TABLE IF EXISTS VolumetricSolutionDefault;
CREATE TABLE VolumetricSolutionDefault
    (
        VolumetricSolutionDefaultID    INT UNSIGNED NOT NULL AUTO_INCREMENT,
        ServiceScopeID INT UNSIGNED NOT NULL,
        ColumnKeyName    VARCHAR(50),
        ColumnDisplayName VARCHAR(100),
        ColumnDefaultValue VARCHAR(50),
        FOREIGN KEY (ServiceScopeID) REFERENCES  ServiceScope(ServiceScopeID),
        PRIMARY KEY(VolumetricSolutionDefaultID)
    )
ENGINE=INNODB; 

#DROP TABLE IF EXISTS SolutionL3Operations;
CREATE TABLE SolutionL3Operations
(
	SolutionL3OperationsID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
	SolutionID	INT UNSIGNED NOT NULL,
	SolutionApproachDimensionID	INT UNSIGNED,
	SupportWindowMatrixID	INT UNSIGNED,
	OpportunityScopeID	INT UNSIGNED,
	PCofL2IncidentsConvL3	FLOAT(10,2),
	TotalL3BugFixesPerYear	FLOAT(10,2),
	PCSimpleIncidents	FLOAT(10,2),
	PCMediumIncidents	FLOAT(10,2),
	PCComplexIncidents	FLOAT(10,2),
	HrsSimpleIncidents	FLOAT(10,2),
	HrsMediumIncidents	FLOAT(10,2),
	HrsComplexIncidents	FLOAT(10,2),
	AvgResolutionTimeHrs	FLOAT(10,2),
	AnualHrsSpent		FLOAT(10,2),
	UtilizationperYr	FLOAT(10,2),
	BaseL3OperationFTE	FLOAT(10,2),
	AugmentedL3OperationFTE	FLOAT(10,2),
	INDEX (SolutionL3OperationsID), 
	FOREIGN KEY (SolutionID) REFERENCES  Solution(SolutionID),
	FOREIGN KEY (SolutionApproachDimensionID) REFERENCES  SolutionApproachDimension(SolutionApproachDimensionID),
	FOREIGN KEY (SupportWindowMatrixID) REFERENCES  SupportWindowMatrix(SupportWindowMatrixID),
	FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE, 
	PRIMARY KEY(SolutionL3OperationsID)
    ) 
ENGINE=INNODB;


#DROP TABLE IF EXISTS SolutionL3AddServices;
CREATE TABLE SolutionL3AddServices (
  SolutionL3AddServicesID int(10) unsigned NOT NULL AUTO_INCREMENT,
  SolutionID int(10) unsigned,
  SolutionApproachDimensionID int(10) unsigned,
  OpportunityScopeID INT UNSIGNED,
  ScopeIntegTest varchar(3),
  ScopeRegreTest varchar(3),
  ScopeOtherTest varchar(3),
  ScopeMisceSupp varchar(3),
  ScopePhysicalDatabase varchar(3),
  ScopeServerAndVirt varchar(3),
  ScopeOtherInfra varchar(3),
 
  UnitIntegTest varchar(20),
  UnitRegreTest varchar(20),
  UnitOtherTest varchar(20),
  UnitMisceSupp varchar(20),
  UnitPhysicalDatabase varchar(20),
  UnitServerAndVirt varchar(20),
  UnitOtherInfra varchar(20),

  ValueIntegTest float(10,2),
  ValueRegreTest float(10,2),
  ValueOtherTest float(10,2),
  ValueMisceSupp float(10,2),
  ValuePhysicalDatabase float(10,2),
  ValueServerAndVirt float(10,2),
  ValueOtherInfra float(10,2),

  FTEIntegTest float(10,2),
  FTERegreTest float(10,2),
  FTEOtherTest float(10,2),
  FTEMisceSupp float(10,2),
  FTEPhysicalDatabase float(10,2),
  FTEServerAndVirt float(10,2),
  FTEOtherInfra float(10,2),

  BaseL3ServiceFTE float(10,2),
  AugmentedL3ServiceFTE float(10,2),
  PRIMARY KEY (SolutionL3AddServicesID),
  FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID),
  FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE, 
  FOREIGN KEY (SolutionApproachDimensionID) REFERENCES SolutionApproachDimension (SolutionApproachDimensionID)
) ENGINE=InnoDB;

#DROP TABLE IF EXISTS SolutionOtherMisc;
CREATE TABLE SolutionOtherMisc (
  SolutionOtherMiscID int(10) unsigned NOT NULL AUTO_INCREMENT,
  SolutionID int(10) unsigned,
  SolutionApproachDimensionID int(10) unsigned,
  JobRoleID int(10) unsigned,
  OpportunityScopeID INT UNSIGNED,
  FTE float(10,2),
  Skill varchar(1000),
  Remarks varchar(500),
  PRIMARY KEY (SolutionOtherMiscID),
  FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID),
  FOREIGN KEY (JobRoleID) REFERENCES JobRole (JobRoleID),
  FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE, 
  FOREIGN KEY (SolutionApproachDimensionID) REFERENCES SolutionApproachDimension (SolutionApproachDimensionID)
  )ENGINE=InnoDB;
  
  #DROP TABLE IF EXISTS SolutionGovAndPMO;
CREATE TABLE SolutionGovAndPMO (
  SolutionGovAndPMOID int(10) unsigned NOT NULL AUTO_INCREMENT,
  SolutionID int(10) unsigned,
  SolutionApproachDimensionID int(10) unsigned,
  JobRoleID int(10) unsigned,
  OpportunityScopeID INT UNSIGNED,
  FTE float(10,2),
  Remarks varchar(500),
  PRIMARY KEY (SolutionGovAndPMOID),
  FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID),
  FOREIGN KEY (JobRoleID) REFERENCES JobRole (JobRoleID),
  FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE, 
  FOREIGN KEY (SolutionApproachDimensionID) REFERENCES SolutionApproachDimension (SolutionApproachDimensionID)
  )ENGINE=InnoDB;
  
 #DROP TABLE IF EXISTS TNTPartitionDate;
CREATE TABLE TNTPartitionDate (
  	TNTPartitionDateID int(10) unsigned NOT NULL AUTO_INCREMENT,
	SolutionID	INT UNSIGNED,
	NoOfPart	INT,
	StartDate	DATE,
	EndDate	DATE,
  PRIMARY KEY (TNTPartitionDateID),
  FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID)
  )ENGINE=InnoDB;
  
 #DROP TABLE IF EXISTS TNTDetail;
CREATE TABLE TNTDetail (
	TNTDetailID int(10) unsigned NOT NULL AUTO_INCREMENT,
	SolutionID int(10) unsigned,
	PartitionName	VARCHAR(50),
	TotalFTE	FLOAT(10,2),
	FTEPercent FLOAT(10,2),
	StartDate	DATE,
	EndDate	DATE,
	FTECount	FLOAT(10,2),
	Category	VARCHAR(10),
  PRIMARY KEY (TNTDetailID),
  FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID)
  )ENGINE=InnoDB;
  
  #DROP TABLE IF EXISTS SolutionApprover;
  CREATE TABLE SolutionApprover 
	(
	SolutionApproverID		INT UNSIGNED NOT NULL AUTO_INCREMENT,
	SolutionID		INT UNSIGNED NOT NULL,
	Approver	VARCHAR(100),
	SequenceID	INT,
	ActiveID	Bool,
	RequestDate	DATETIME,
	INDEX (SolutionApproverID), 
	FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
	PRIMARY KEY(SolutionApproverID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS CheckListMaster;
CREATE TABLE CheckListMaster
(
 CheckListMasterID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 CheckListName VARCHAR(20),
 CheckListType VARCHAR(12),
 CheckListSubType VARCHAR(20),
 CheckListDescription VARCHAR(100),
 INDEX (CheckListMasterID), 
 PRIMARY KEY(CheckListMasterID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS CheckLists;
CREATE TABLE CheckLists
(
 CheckListsID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 SolutionID INT UNSIGNED,
 CheckListMasterID INT UNSIGNED,
 CheckListValue	INT(1),
 INDEX (CheckListsID), 
 FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
 FOREIGN KEY (CheckListMasterID) REFERENCES CheckListMaster (CheckListMasterID), 
 PRIMARY KEY(CheckListsID)
)
ENGINE=INNODB;


  #DROP TABLE IF EXISTS SolutionApprovalStatus;
CREATE TABLE SolutionApprovalStatus 
	(
		SolutionApprovalStatusID		INT UNSIGNED NOT NULL AUTO_INCREMENT,
		SolutionApproverID	INT unsigned,
		Approved	bool,
		ResponseDate	DATETIME,
		Comments	VARCHAR(255),
		INDEX (SolutionApprovalStatusID), 
		FOREIGN KEY (SolutionApproverID) REFERENCES SolutionApprover (SolutionApproverID), 
		PRIMARY KEY(SolutionApprovalStatusID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS SolutionEnhanDevRICEFW;
CREATE TABLE SolutionEnhanDevRICEFW
(
    SolutionEnhanDevRICEFWID    INT UNSIGNED NOT NULL AUTO_INCREMENT,
    SolutionID    INT UNSIGNED NOT NULL,
    SolutionApproachDimensionID    INT UNSIGNED,
    OpportunityScopeID INT UNSIGNED,
    QaunSimpleInterface        FLOAT(10,2),
    QaunMediumInterface        FLOAT(10,2),
    QaunComplexInterface    FLOAT(10,2),
    QaunSimpleExtension        FLOAT(10,2),
    QaunMediumExtension        FLOAT(10,2),
    QaunComplexExtension    FLOAT(10,2),
    QaunSimpleReport        FLOAT(10,2),
    QaunMediumReport        FLOAT(10,2),
    QaunComplexReport        FLOAT(10,2),
    QaunSimpleWorkflow        FLOAT(10,2),
    QaunMediumWorkflow        FLOAT(10,2),
    QaunComplexWorkflow        FLOAT(10,2),
    QaunSimpleTotal            FLOAT(10,2),
    QaunMediumTotal            FLOAT(10,2),
    QaunComplexTotal        FLOAT(10,2),

    EffortSimpleInterface    FLOAT(10,2),
    EffortMediumInterface    FLOAT(10,2),
    EffortComplexInterface    FLOAT(10,2),
    EffortSimpleExtension    FLOAT(10,2),
    EffortMediumExtension    FLOAT(10,2),
    EffortComplexExtension    FLOAT(10,2),
    EffortSimpleReport        FLOAT(10,2),
    EffortMediumReport        FLOAT(10,2),
    EffortComplexReport        FLOAT(10,2),
    EffortSimpleWorkflow    FLOAT(10,2),
    EffortMediumWorkflow    FLOAT(10,2),
    EffortComplexWorkflow    FLOAT(10,2),
    EffortSimpleTotal        FLOAT(10,2),
    EffortMediumTotal        FLOAT(10,2),
    EffortComplexTotal        FLOAT(10,2),

    TotalEffortHrsInterface FLOAT(10,2),
    TotalEffortHrsExtension FLOAT(10,2),
    TotalEffortHrsReport     FLOAT(10,2),
    TotalEffortHrsWorkflow     FLOAT(10,2),
    TotalEffortHrs              FLOAT(10,2),
    EffortManDays             FLOAT(10,2),
    EffortManWeeks             FLOAT(10,2),
    EffortManMonths         FLOAT(10,2),

    Durition                 FLOAT(10,2),
    DuritionUnit             VARCHAR(50),
    AverageFTE                 FLOAT(10,2),
    INDEX (SolutionEnhanDevRICEFWID),
    FOREIGN KEY (SolutionID) REFERENCES  Solution(SolutionID),
    FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE, 
    FOREIGN KEY (SolutionApproachDimensionID) REFERENCES  SolutionApproachDimension(SolutionApproachDimensionID),
    PRIMARY KEY(SolutionEnhanDevRICEFWID)
    )
ENGINE=INNODB;

#DROP TABLE IF EXISTS SolutionEnhanDevFixedDeposite;
CREATE TABLE SolutionEnhanDevFixedDeposite
(
    SolutionEnhanDevFixedDepositeID    INT UNSIGNED NOT NULL AUTO_INCREMENT,
    SolutionID    INT UNSIGNED NOT NULL,
    SolutionApproachDimensionID    INT UNSIGNED,
    OpportunityScopeID INT UNSIGNED,
    CustomerBuying    INT,
    CustomerBuyingUnit    INT,
    PCQaunSimpleCR      FLOAT(10,2),
    PCQaunMediumCR    FLOAT(10,2),
    PCQaunComplexCR        FLOAT(10,2),


    QaunSimpleCR    FLOAT(10,2),
    QaunMediumCR           FLOAT(10,2),
    QaunComplexCR      FLOAT(10,2),

    EffortSimpleCR    FLOAT(10,2),
    EffortMediumCR      FLOAT(10,2),
    EffortComplexCR      FLOAT(10,2),


    TotalEffortHrsCR    FLOAT(10,2),


    EffortManDays       FLOAT(10,2),
    EffortManWeeks      FLOAT(10,2),
    EffortManMonths     FLOAT(10,2),

    Durition            FLOAT(10,2),
    DuritionUnit        VARCHAR(50),
    AverageFTECR        FLOAT(10,2),
    AverageFTEHrs       FLOAT(10,2),

    INDEX (SolutionEnhanDevFixedDepositeID),
    FOREIGN KEY (SolutionID) REFERENCES  Solution(SolutionID),
    FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE, 
    FOREIGN KEY (SolutionApproachDimensionID) REFERENCES  SolutionApproachDimension(SolutionApproachDimensionID),
    PRIMARY KEY(SolutionEnhanDevFixedDepositeID)
    )
ENGINE=INNODB;

#DROP TABLE IF EXISTS TestingService;
CREATE TABLE TestingService
(
 TestingServiceID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 SolutionID INT unsigned,
 ReleaseType VARCHAR(10),
 ReleaseDate DATETIME,
 PCDistribution FLOAT(10,2),
 INDEX (TestingServiceID), 
 FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
 PRIMARY KEY(TestingServiceID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS GenericTestingInputs;
CREATE TABLE GenericTestingInputs
(
 GenericTestingInputsID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 SolutionID INT unsigned,
 Complexity VARCHAR(10),
 Ratio FLOAT(10,2),
 TestDesignProductivity FLOAT(10,2),
 TestExecutionProductivity FLOAT(10,2),
 AutomationProductivity FLOAT(10,2),
 INDEX (GenericTestingInputsID), 
 FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
 PRIMARY KEY(GenericTestingInputsID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS GenericTestingOverhead;
CREATE TABLE GenericTestingOverhead
(
 GenericTestingOverheadID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 SolutionID INT unsigned,
 TestMgmtEffort	FLOAT(10,2),
 RATPEffort FLOAT(10,2),
 AutomationExecEffort FLOAT(10,2),
 AutomationMaintEffort FLOAT(10,2),
 INDEX (GenericTestingOverheadID), 
 FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
 PRIMARY KEY(GenericTestingOverheadID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS SolutionTestingAsAService;
CREATE TABLE SolutionTestingAsAService
(
 SolutionTestingAsAServiceID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 SolutionID INT unsigned,
 OpportunityScopeID INT unsigned,
 SolutionApproachDimensionID INT unsigned,
 FTE FLOAT(10,2),
 PercentOfServiceElementEffort int(10) unsigned,
 INDEX (SolutionTestingAsAServiceID), 
 FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
 FOREIGN KEY (OpportunityScopeID) REFERENCES  OpportunityScope(OpportunityScopeID) on delete CASCADE, 
 FOREIGN KEY (SolutionApproachDimensionID) REFERENCES  SolutionApproachDimension(SolutionApproachDimensionID),
 PRIMARY KEY(SolutionTestingAsAServiceID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS EfficiencyLever;
CREATE TABLE EfficiencyLever
(
 EfficiencyLeverID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 SolutionID INT unsigned,
 ReleaseType VARCHAR(10),
 TestType VARCHAR(20),
 AvgNoTestCases FLOAT(10,2),
 PCAutomationDesign FLOAT(10,2),
 TestDesignReuse FLOAT(10,2),
 TestExecutionCycles FLOAT(10,2),
 ASISTestExecutionCycles FLOAT(10,2),
 INDEX (EfficiencyLeverID), 
 FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
 PRIMARY KEY(EfficiencyLeverID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS RegressionLever;
CREATE TABLE RegressionLever
(
 RegressionLeverID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 SolutionID INT unsigned,
 PCRegression FLOAT(10,2),
 PCNewFunctionality FLOAT(10,2),
 PCAutomatedRegression FLOAT(10,2),
 INDEX (RegressionLeverID), 
 FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
 PRIMARY KEY(RegressionLeverID)
)
ENGINE=INNODB;

CREATE TABLE TestEffReduction
	(	
	TestEffReductionID INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	SolutionID   INT UNSIGNED NOT NULL , 
	SecondYear float(10,2),
    ThirdYear float(10,2),
    FourthYear float(10,2),
    FifthYear float(10,2),
    SixthYear float(10,2),
    SeventhYear float(10,2),
    INDEX (TestEffReductionID), 
    FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
    PRIMARY KEY(TestEffReductionID)
	) 
ENGINE=INNODB;

#NonLaborCost Redesign Tables***********
#DROP TABLE IF EXISTS NLCInputParam;
CREATE TABLE NLCInputParam
	(
	NLCInputParamID		INT UNSIGNED NOT NULL AUTO_INCREMENT,
	SolutionID		INT UNSIGNED NOT NULL,
	OneRoundTripCost	FLOAT(10,2),
	OneLongTermVisaCost	FLOAT(10,2),
	OneShortTermVisaCost	FLOAT(10,2),
    OnsiteHotelCost FLOAT(10,2),
    OnsitePerDiem FLOAT(10,2),
    OnsiteConveyanceCost FLOAT(10,2),
    MobileCommCost FLOAT(10,2),
    MSDPPlatformUsed INT,
    MSDPCost FLOAT(10,2),
    InvoicingType VARCHAR(20), 
    INDEX (NLCInputParamID), 
	FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
	PRIMARY KEY(NLCInputParamID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS NLCTravel;
CREATE TABLE NLCTravel 
	(
	NLCTravelID		INT UNSIGNED NOT NULL AUTO_INCREMENT,
	NLCInputParamID    INT UNSIGNED NOT NULL, 
    Month    INT,
    Year    INT,
    Type  VARCHAR(10),
	OnshoreFTE	FLOAT(10,2),
	RoundTrips	FLOAT(10,2),
	LongTermVisa	FLOAT(10,2),
    ShortTermVisa	FLOAT(10,2),
    RoundTripCost FLOAT(10,2),
    LongTermVisaCost FLOAT(10,2),
    ShortTermVisaCost FLOAT(10,2),
    OnsiteHotelCost FLOAT(10,2),
    RelocationCost FLOAT(10,2),
    OnsitePerDiem FLOAT(10,2),
    OnsiteFSI FLOAT(10,2),
    OnsiteConveyanceCost FLOAT(10,2),
    MoblileCommCost FLOAT(10,2),
    TotalCost FLOAT(10,2),
    INDEX (NLCTravelID), 
	FOREIGN KEY (NLCInputParamID) REFERENCES NLCInputParam (NLCInputParamID) on delete cascade, 
	PRIMARY KEY(NLCTravelID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS NLCOther;
CREATE TABLE NLCOther
	(
	NLCOtherID		INT UNSIGNED NOT NULL AUTO_INCREMENT,
	NLCInputParamID    INT UNSIGNED NOT NULL, 
    Month    INT,
    Year    INT,
    OffshoreFTE FLOAT(10,2),     
    MsdpCost	FLOAT(10,2),
	ConnClientToIndia	FLOAT(10,2),
	ConnIndiaToDev	FLOAT(10,2),
    TotalOtherCost FLOAT(10,2),
    INDEX (NLCOtherID), 
	FOREIGN KEY (NLCInputParamID) REFERENCES NLCInputParam (NLCInputParamID) on delete cascade, 
	PRIMARY KEY(NLCOtherID)
	) 
ENGINE=INNODB;

#DROP TABLE IF EXISTS LaborCost;
CREATE TABLE LaborCost
	(
	LaborCostID	INT UNSIGNED NOT NULL AUTO_INCREMENT,
	SolutionID	INT UNSIGNED NOT NULL,
	ServiceScopeID	INT UNSIGNED NOT NULL,
    JobRoleID 	INT UNSIGNED NOT NULL,
    Location VARCHAR(10),
    Month    INT,
    Year    INT,  
    Cost FLOAT(10,2),
    CurrencyCode  VARCHAR(10), 
	INDEX (LaborCostID), 
    FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID),
	FOREIGN KEY (ServiceScopeID) REFERENCES ServiceScope (ServiceScopeID), 
    FOREIGN KEY (JobRoleID) REFERENCES JobRole(JobRoleID), 
	PRIMARY KEY(LaborCostID)
	) 
ENGINE=INNODB;

#tables for new tnt		
#DROP TABLE IF EXISTS TTPlanning;
CREATE TABLE TTPlanning
(
 TTPlanningID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 SolutionID INT unsigned,
 NoofPartition INT,
 INDEX (TTPlanningID), 
 FOREIGN KEY (SolutionID) REFERENCES Solution (SolutionID), 
 PRIMARY KEY(TTPlanningID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS TTPartitionName;
CREATE TABLE TTPartitionName
(
 TTPartitionNameID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 TTPlanningID 	INT unsigned,
 TTPartitionName varchar(50),
 TTPartitionFTE	FLOAT(10,2),
 INDEX (TTPartitionNameID), 
 FOREIGN KEY (TTPlanningID) REFERENCES TTPlanning (TTPlanningID), 
 PRIMARY KEY(TTPartitionNameID)
)
ENGINE=INNODB;
	
#DROP TABLE IF EXISTS TTPartitionDetail;
CREATE TABLE TTPartitionDetail
(
 TTPartitionDetailID INT UNSIGNED NOT NULL AUTO_INCREMENT,
 TTPartitionNameID INT unsigned,
 PlanStartDate		DATETIME,
 PlanEndDate		DATETIME,
 PlanFTECount		FLOAT(10,2),
 LearnStartDate		DATETIME,
 LearnEndDate		DATETIME,
 LearnFTECount		FLOAT(10,2),
 AssistStartDate	DATETIME,
 AssistEndDate		DATETIME,
 AssistFTECount		FLOAT(10,2),
 PerformStartDate	DATETIME,
 PerformEndDate		DATETIME,
 PerformFTECount 	FLOAT(10,2),
 DeliverStartDate 	DATETIME,
 DeliverEndDate		DATETIME,
 DeliverFTECount	FLOAT(10,2),
 INDEX (TTPartitionDetailID), 
 FOREIGN KEY (TTPartitionNameID) REFERENCES TTPartitionName (TTPartitionNameID), 
 PRIMARY KEY(TTPartitionDetailID)
)
ENGINE=INNODB;

#DROP TABLE IF EXISTS TTOnOffRatio;
CREATE TABLE TTOnOffRatio
(
TTOnOffRatioID INT UNSIGNED NOT NULL AUTO_INCREMENT,
TTPartitionNameID INT unsigned,
PlanOffFTE FLOAT(10,2),
PlanOnFTE FLOAT(10,2),
PlanOffPC FLOAT(10,2),
PlanOnPC FLOAT(10,2),
LearnOffFTE FLOAT(10,2),
LearnOnFTE FLOAT(10,2),
LearnOffPC FLOAT(10,2),
LearnOnPC FLOAT(10,2),
AssistOffFTE FLOAT(10,2),
AssistOnFTE FLOAT(10,2),
AssistOffPC FLOAT(10,2),
AssistOnPC FLOAT(10,2),
PerformOffFTE FLOAT(10,2),
PerformOnFTE FLOAT(10,2),
PerformOffPC FLOAT(10,2),
PerformOnPC FLOAT(10,2),
DeliverOffFTE FLOAT(10,2),
DeliverOnFTE FLOAT(10,2),
DeliverOffPC FLOAT(10,2),
DeliverOnPC FLOAT(10,2),
INDEX (TTOnOffRatioID), 
FOREIGN KEY (TTPartitionNameID) REFERENCES TTPartitionName (TTPartitionNameID), 
PRIMARY KEY(TTOnOffRatioID)
)ENGINE=INNODB;


#DROP TABLE IF EXISTS TTLaborCost;
CREATE TABLE TTLaborCost 
(
TTLaborCostID INT UNSIGNED NOT NULL AUTO_INCREMENT, 
OpportunityID INT UNSIGNED NOT NULL,
SolutionID INT UNSIGNED NOT NULL,
WeekDate DATETIME, Rate FLOAT(10,2),
FOREIGN KEY (SolutionID) REFERENCES  Solution(SolutionID),
FOREIGN KEY (OpportunityID) REFERENCES Opportunity (OpportunityID), 
PRIMARY KEY(TTLaborCostID)
);

#DROP TABLE IF EXISTS TTSummaryStaging;
CREATE TABLE TTSummaryStaging
(
TTSummaryStagingID INT UNSIGNED NOT NULL AUTO_INCREMENT,
TTPartitionNameID INT unsigned,
WeekDate DATETIME,
OnshoreFTE FLOAT(10,2),
OffshoreFTE FLOAT(10,2),
INDEX (TTSummaryStagingID), 
FOREIGN KEY (TTPartitionNameID) REFERENCES TTPartitionName (TTPartitionNameID), 
PRIMARY KEY(TTSummaryStagingID)
)ENGINE=INNODB;


#DROP TABLE IF EXISTS TTSummary;
CREATE TABLE TTSummary
(
TTSummaryID INT UNSIGNED NOT NULL AUTO_INCREMENT,
TTPlanningID INT unsigned,
WeekDate DATETIME,
OnshoreFTE FLOAT(10,2),
OffshoreFTE FLOAT(10,2),
TotalFTE FLOAT(10,2),
INDEX (TTSummaryID), 
FOREIGN KEY (TTPlanningID) REFERENCES TTPlanning (TTPlanningID), 
PRIMARY KEY(TTSummaryID)
)ENGINE=INNODB;

#DROP TABLE IF EXISTS TTJobRoleDistribution;
CREATE TABLE TTJobRoleDistribution
(
TTJobRoleDistributionID INT UNSIGNED NOT NULL AUTO_INCREMENT,
TTPlanningID INT unsigned,
WeekDate DATETIME,
JobRoleID INT unsigned,
DistributionPC FLOAT(10,2),
FTECount FLOAT(10,2),
INDEX (TTJobRoleDistributionID), 
FOREIGN KEY (TTPlanningID) REFERENCES TTPlanning (TTPlanningID), 
FOREIGN KEY (JobRoleID) REFERENCES JobRole (JobRoleID), 
PRIMARY KEY(TTJobRoleDistributionID)
)ENGINE=INNODB;

#DROP TABLE IF EXISTS ProgramManagement;

CREATE TABLE `ProgramManagement` (
  `ProgramManagementID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SolutionID` int(10) unsigned NOT NULL,
  `OpportunityScopeID` int(10) unsigned NOT NULL,
  `CoordinateProjects` FLOAT(10,2) unsigned DEFAULT NULL,
  `EnsureAchievement` FLOAT(10,2) unsigned DEFAULT NULL,
  `ManageClient` FLOAT(10,2) unsigned DEFAULT NULL, 
  `ManageQualityRiskIssues` FLOAT(10,2) unsigned DEFAULT NULL,
  `MeasureADMServiceKPI` FLOAT(10,2) unsigned DEFAULT NULL,
  `TotalProgramManagementHours` FLOAT(10,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`ProgramManagementID`),
  KEY `SolutionID` (`SolutionID`),
  KEY `OpportunityScopeID` (`OpportunityScopeID`),
  CONSTRAINT `ProgramManagement_ibfk_1` FOREIGN KEY (`SolutionID`) REFERENCES `Solution` (`SolutionID`),
  CONSTRAINT `ProgramManagement_ibfk_4` FOREIGN KEY (`OpportunityScopeID`) REFERENCES `OpportunityScope` (`OpportunityScopeID`) ON DELETE CASCADE
) ENGINE=InnoDB;

#DROP TABLE IF EXISTS ReleaseManagement;
CREATE TABLE `ReleaseManagement` (
  `ReleaseManagementID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SolutionID` int(10) unsigned NOT NULL,
  `OpportunityScopeID` int(10) unsigned NOT NULL,
  `InstlnDistnOfSoftRel` FLOAT(10,2) unsigned DEFAULT NULL,
  `DefineRolloutPlan` FLOAT(10,2) unsigned DEFAULT NULL,
  `EnsureTraceability` FLOAT(10,2) unsigned DEFAULT NULL, 
  `EnsureInstallation` FLOAT(10,2) unsigned DEFAULT NULL,
  `TotalReleaseManagementHours` FLOAT(10,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`ReleaseManagementID`),
  KEY `SolutionID` (`SolutionID`),
  KEY `OpportunityScopeID` (`OpportunityScopeID`),
  CONSTRAINT `ReleaseManagement_ibfk_1` FOREIGN KEY (`SolutionID`) REFERENCES `Solution` (`SolutionID`),
  CONSTRAINT `ReleaseManagement_ibfk_4` FOREIGN KEY (`OpportunityScopeID`) REFERENCES `OpportunityScope` (`OpportunityScopeID`) ON DELETE CASCADE
) ENGINE=InnoDB;

#DROP TABLE IF EXISTS ChangeManagement;
CREATE TABLE `ChangeManagement` (
  `ChangeManagementID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SolutionID` int(10) unsigned NOT NULL,
  `OpportunityScopeID` int(10) unsigned NOT NULL,
  `ReqGatheringAndAnalysis` FLOAT(10,2) unsigned DEFAULT NULL,
  `FeasibilityImpactAndCostAnalysis` FLOAT(10,2) unsigned DEFAULT NULL,
  `ApproveChangesAndPlanning` FLOAT(10,2) unsigned DEFAULT NULL, 
  `EnsureStandards` FLOAT(10,2) unsigned DEFAULT NULL,
  `ManageTraceability` FLOAT(10,2) unsigned DEFAULT NULL,
  `TotalChangeManagementHours` FLOAT(10,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`ChangeManagementID`),
  KEY `SolutionID` (`SolutionID`),
  KEY `OpportunityScopeID` (`OpportunityScopeID`),
  CONSTRAINT `ChangeManagement_ibfk_1` FOREIGN KEY (`SolutionID`) REFERENCES `Solution` (`SolutionID`),
  CONSTRAINT `ChangeManagement_ibfk_4` FOREIGN KEY (`OpportunityScopeID`) REFERENCES `OpportunityScope` (`OpportunityScopeID`) ON DELETE CASCADE
) ENGINE=InnoDB;

#DROP TABLE IF EXISTS CapacityManagement;

CREATE TABLE `CapacityManagement` (
  `CapacityManagementID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SolutionID` int(10) unsigned NOT NULL,
  `OpportunityScopeID` int(10) unsigned NOT NULL,
  `PrepareAndUseModels` FLOAT(10,2) unsigned DEFAULT NULL,
  `FteCapacityManagement` FLOAT(10,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`CapacityManagementID`),
  KEY `SolutionID` (`SolutionID`),
  KEY `OpportunityScopeID` (`OpportunityScopeID`),
  CONSTRAINT `CapacityManagement_ibfk_1` FOREIGN KEY (`SolutionID`) REFERENCES `Solution` (`SolutionID`),
  CONSTRAINT `CapacityManagement_ibfk_4` FOREIGN KEY (`OpportunityScopeID`) REFERENCES `OpportunityScope` (`OpportunityScopeID`) ON DELETE CASCADE
) ENGINE=InnoDB;


#DROP TABLE IF EXISTS ConfigurationManagement;

CREATE TABLE `ConfigurationManagement` (
  `ConfigurationManagementID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SolutionID` int(10) unsigned NOT NULL,
  `OpportunityScopeID` int(10) unsigned NOT NULL,
  `ManageConfigurationItems` FLOAT(10,2) unsigned DEFAULT NULL,
  `ManageConfigInfoAndDocumentation` FLOAT(10,2) unsigned DEFAULT NULL,
  `FteConfigurationManagement` FLOAT(10,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`ConfigurationManagementID`),
  KEY `SolutionID` (`SolutionID`),
  KEY `OpportunityScopeID` (`OpportunityScopeID`),
  CONSTRAINT `ConfigurationManagement_ibfk_1` FOREIGN KEY (`SolutionID`) REFERENCES `Solution` (`SolutionID`),
  CONSTRAINT `ConfigurationManagement_ibfk_4` FOREIGN KEY (`OpportunityScopeID`) REFERENCES `OpportunityScope` (`OpportunityScopeID`) ON DELETE CASCADE
) ENGINE=InnoDB;

#DROP TABLE IF EXISTS SecurityManagement;

CREATE TABLE `SecurityManagement` (
  `SecurityManagementID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SolutionID` int(10) unsigned NOT NULL,
  `OpportunityScopeID` int(10) unsigned NOT NULL,
  `ManageSecurity` FLOAT(10,2) unsigned DEFAULT NULL,
  `FteSecurityManagement` FLOAT(10,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`SecurityManagementID`),
  KEY `SolutionID` (`SolutionID`),
  KEY `OpportunityScopeID` (`OpportunityScopeID`),
  CONSTRAINT `SecurityManagement_ibfk_1` FOREIGN KEY (`SolutionID`) REFERENCES `Solution` (`SolutionID`),
  CONSTRAINT `SecurityManagement_ibfk_4` FOREIGN KEY (`OpportunityScopeID`) REFERENCES `OpportunityScope` (`OpportunityScopeID`) ON DELETE CASCADE
) ENGINE=InnoDB;

#DROP TABLE IF EXISTS AvailabilityManagement;

CREATE TABLE `AvailabilityManagement` (
  `AvailabilityManagementID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SolutionID` int(10) unsigned NOT NULL,
  `OpportunityScopeID` int(10) unsigned NOT NULL,
  `ManageAvailability` FLOAT(10,2) unsigned DEFAULT NULL,
  `ManageOutageAndRisks` FLOAT(10,2) unsigned DEFAULT NULL,
  `FteAvailabilityManagement` FLOAT(10,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`AvailabilityManagementID`),
  KEY `SolutionID` (`SolutionID`),
  KEY `OpportunityScopeID` (`OpportunityScopeID`),
  CONSTRAINT `AvailabilityManagement_ibfk_1` FOREIGN KEY (`SolutionID`) REFERENCES `Solution` (`SolutionID`),
  CONSTRAINT `AvailabilityManagement_ibfk_4` FOREIGN KEY (`OpportunityScopeID`) REFERENCES `OpportunityScope` (`OpportunityScopeID`) ON DELETE CASCADE
) ENGINE=InnoDB;	


#DROP TABLE IF EXISTS DemandSupport;


CREATE TABLE `DemandSupport` (
  `DemandSupportID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SolutionID` int(10) unsigned NOT NULL,
  `OpportunityScopeID` int(10) unsigned NOT NULL,
  `CRAnalysis` FLOAT(10,2) unsigned DEFAULT NULL,
  `RequirementGathering` FLOAT(10,2) unsigned DEFAULT NULL,
  `TechFeasibilityAnalysis` FLOAT(10,2) unsigned DEFAULT NULL,
  `EffortCostEstimation` FLOAT(10,2) unsigned DEFAULT NULL,
  `WorkPlanFormulation` FLOAT(10,2) unsigned DEFAULT NULL,
  `TotalDemandSupportHours` FLOAT(10,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`DemandSupportID`),
  KEY `SolutionID` (`SolutionID`),
  KEY `OpportunityScopeID` (`OpportunityScopeID`),
  CONSTRAINT `DemandSupport_ibfk_1` FOREIGN KEY (`SolutionID`) REFERENCES `Solution` (`SolutionID`),
  CONSTRAINT `DemandSupport_ibfk_4` FOREIGN KEY (`OpportunityScopeID`) REFERENCES `OpportunityScope` (`OpportunityScopeID`) ON DELETE CASCADE
) ENGINE=InnoDB;

#DROP TABLE IF EXISTS DesignBuild;

CREATE TABLE `DesignBuild` (
  `DesignBuildID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SolutionID` int(10) unsigned NOT NULL,
  `OpportunityScopeID` int(10) unsigned NOT NULL,
  `DetailedTechDesign` FLOAT(10,2) unsigned DEFAULT NULL,
  `TestCaseDesignTestCyclePlan` FLOAT(10,2) unsigned DEFAULT NULL,
  `FunctionalSpecDevFinal` FLOAT(10,2) unsigned DEFAULT NULL,
  `CapacityPlanning` FLOAT(10,2) unsigned DEFAULT NULL,
  `SoftwareChanges` FLOAT(10,2) unsigned DEFAULT NULL,
  `ConfigChanges` FLOAT(10,2) unsigned DEFAULT NULL,
  `UnitComponentTest` FLOAT(10,2) unsigned DEFAULT NULL,
  `RelNotesPrepareDistribute` FLOAT(10,2) unsigned DEFAULT NULL,
  `MaintananceManualCreation` FLOAT(10,2) unsigned DEFAULT NULL,
  `TotalDesignBuildHours` FLOAT(10,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`DesignBuildID`),
  KEY `SolutionID` (`SolutionID`),
  KEY `OpportunityScopeID` (`OpportunityScopeID`),
  CONSTRAINT `DesignBuild_ibfk_1` FOREIGN KEY (`SolutionID`) REFERENCES `Solution` (`SolutionID`),
  CONSTRAINT `DesignBuild_ibfk_4` FOREIGN KEY (`OpportunityScopeID`) REFERENCES `OpportunityScope` (`OpportunityScopeID`) ON DELETE CASCADE
) ENGINE=InnoDB;

#DROP TABLE IF EXISTS DeploymentRollOut;

CREATE TABLE `DeploymentRollOut` (
  `DeploymentRollOutID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SolutionID` int(10) unsigned NOT NULL,
  `OpportunityScopeID` int(10) unsigned NOT NULL,
  `OpsBusinessTraining` FLOAT(10,2) unsigned DEFAULT NULL,
  `DeploySoftwareInstln` FLOAT(10,2) unsigned DEFAULT NULL,
  `DeployInstlnPlanExec` FLOAT(10,2) unsigned DEFAULT NULL,
  `DataMigration` FLOAT(10,2) unsigned DEFAULT NULL,
  `LegacySwitchOff` FLOAT(10,2) unsigned DEFAULT NULL,
  `TotalDeploymentRollOutHours` FLOAT(10,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`DeploymentRollOutID`),
  KEY `SolutionID` (`SolutionID`),
  KEY `OpportunityScopeID` (`OpportunityScopeID`),
  CONSTRAINT `DeploymentRollOut_ibfk_1` FOREIGN KEY (`SolutionID`) REFERENCES `Solution` (`SolutionID`),
  CONSTRAINT `DeploymentRollOut_ibfk_4` FOREIGN KEY (`OpportunityScopeID`) REFERENCES `OpportunityScope` (`OpportunityScopeID`) ON DELETE CASCADE
) ENGINE=InnoDB;

#DROP TABLE IF EXISTS PostReleaseActivities;

CREATE TABLE `PostReleaseActivities` (
  `PostRelActivityID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SolutionID` int(10) unsigned NOT NULL,
  `OpportunityScopeID` int(10) unsigned NOT NULL,
  `PostInstRelIssues` FLOAT(10,2) unsigned DEFAULT NULL,
  `SuppBusinessSimulations` FLOAT(10,2) unsigned DEFAULT NULL,
  `ManageStaggeredAct` FLOAT(10,2) unsigned DEFAULT NULL, 
  `TunePerformance` FLOAT(10,2) unsigned DEFAULT NULL,
  `PostInstallationComm` FLOAT(10,2) unsigned DEFAULT NULL,
  `TotalPostRelActHours` FLOAT(10,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`PostRelActivityID`),
  KEY `SolutionID` (`SolutionID`),
  KEY `OpportunityScopeID` (`OpportunityScopeID`),
  CONSTRAINT `PostReleaseActivities_ibfk_1` FOREIGN KEY (`SolutionID`) REFERENCES `Solution` (`SolutionID`),
  CONSTRAINT `PostReleaseActivities_ibfk_4` FOREIGN KEY (`OpportunityScopeID`) REFERENCES `OpportunityScope` (`OpportunityScopeID`) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE  TABLE `ProductDetails` (
  `ProductID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `ProductName` VARCHAR(50) NULL ,
  `ProductDescription` VARCHAR(200) NULL DEFAULT NULL ,
  `IsActive` ENUM('Y','N') NULL DEFAULT NULL ,
  PRIMARY KEY (`ProductID`) )
ENGINE = InnoDB;

CREATE TABLE `Component` (
  `ComponentID` int(10) unsigned NOT NULL auto_increment,
  `ComponentName` varchar(200) default NULL,
  `ComponentType` varchar(200) default NULL,
  `ProductID` int(10) unsigned default NULL,
  PRIMARY KEY  (`ComponentID`),
  KEY `ComponentID` (`ComponentID`),
  KEY `fk_Component_1` (`ProductID`),
  CONSTRAINT `fk_Component_1` FOREIGN KEY (`ProductID`) REFERENCES `ProductDetails` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;

CREATE TABLE `OpportunityComponent` (
  `OpportunityComponentID` int(10) unsigned NOT NULL auto_increment,
  `OpportunityID` int(10) unsigned NOT NULL,
  `ComponentID` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`OpportunityComponentID`),
  KEY `OpportunityComponentID` (`OpportunityComponentID`),
  KEY `OpportunityID` (`OpportunityID`),
  KEY `ComponentID` (`ComponentID`),
  CONSTRAINT `OpportunityComponent_ibfk_1` FOREIGN KEY (`OpportunityID`) REFERENCES `Opportunity` (`OpportunityID`),
  CONSTRAINT `OpportunityComponent_ibfk_2` FOREIGN KEY (`ComponentID`) REFERENCES `Component` (`ComponentID`)
) ENGINE=InnoDB;

CREATE  TABLE `ProductEstimationActivities` (
  `ProductEstimationActivitiesID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `ConfigurationType` VARCHAR(50) NULL DEFAULT NULL ,
  `ConfigurationArea` VARCHAR(50) NULL DEFAULT NULL ,
  `ActivityName` VARCHAR(100) NULL DEFAULT NULL ,
  `ServiceScopeID` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `ComponentID` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`ProductEstimationActivitiesID`) ,
  INDEX `fk_ProductEstimationActivities_1` (`ServiceScopeID` ASC) ,
  INDEX `fk_ProductEstimationActivities_2` (`ComponentID` ASC) ,
  CONSTRAINT `fk_ProductEstimationActivities_1`
    FOREIGN KEY (`ServiceScopeID` )
    REFERENCES `ServiceScope` (`ServiceScopeID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ProductEstimationActivities_2`
	  FOREIGN KEY (`ComponentID` )
	  REFERENCES `Component` (`ComponentID` )
	  ON DELETE NO ACTION
	  ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE `EstimationComplexityLookup` (
  `EstimationComplexityID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `EstimationComplexityName` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`EstimationComplexityID`) )
ENGINE = InnoDB;

CREATE  TABLE `ProductActivitiesComplexityAssumptions` (
  `ProductActivitiesComplexityAssumptionsID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `ProductEstimationActivitiesID` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `EstimationComplexityID` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `EstimationEffortFactorValue` DECIMAL(10,2) NULL ,
  `EstimationEffortFactorUnit` VARCHAR(50) NULL ,
  PRIMARY KEY (`ProductActivitiesComplexityAssumptionsID`) ,
  INDEX `fk_ProductActivitiesComplexityAssumptions_1` (`EstimationComplexityID` ASC) ,
  INDEX `fk_ProductActivitiesComplexityAssumptions_2` (`ProductEstimationActivitiesID` ASC) ,
  CONSTRAINT `fk_ProductActivitiesComplexityAssumptions_1`
    FOREIGN KEY (`EstimationComplexityID` )
    REFERENCES `EstimationComplexityLookup` (`EstimationComplexityID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ProductActivitiesComplexityAssumptions_2`
    FOREIGN KEY (`ProductEstimationActivitiesID` )
    REFERENCES `ProductEstimationActivities` (`ProductEstimationActivitiesID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE `ProductEstimationForSolution` (
  `ProductEstimationForSolutionID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `SolutionID` INT(10) UNSIGNED NOT NULL ,
  `ProductActivitiesComplexityAssumptionsID` INT(10) UNSIGNED NOT NULL ,
  `PerMonthEventCountInput` DECIMAL(10,2) NULL DEFAULT 0 ,
  `TotalEffortDerived` DECIMAL(10,2) NULL DEFAULT 0.00 ,
  PRIMARY KEY (`ProductEstimationForSolutionID`) ,
  INDEX `fk_ProductEstimationForSolution_1` (`SolutionID` ASC) ,
  INDEX `fk_ProductEstimationForSolution_2` (`ProductActivitiesComplexityAssumptionsID` ASC) ,
  UNIQUE INDEX `unique_index1` (`SolutionID` ASC, `ProductActivitiesComplexityAssumptionsID` ASC),
  CONSTRAINT `fk_ProductEstimationForSolution_1`
    FOREIGN KEY (`SolutionID` )
    REFERENCES `Solution` (`SolutionID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ProductEstimationForSolution_2`
    FOREIGN KEY (`ProductActivitiesComplexityAssumptionsID` )
    REFERENCES `ProductActivitiesComplexityAssumptions` (`ProductActivitiesComplexityAssumptionsID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE `ProductEstimationAuxiliaryParams` (
  `ProductEstimationAuxiliaryParamsID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `ParamType` ENUM('CBIO Version','Customer Base','CBIO Impacted 3PP Nodes') NULL ,
  `ParamTypeCode` ENUM('CBIOVersion','CustomerBase','CBIOImpacted3PPNodes') NULL DEFAULT NULL,
  `ParamName` VARCHAR(50) NULL DEFAULT NULL ,
  `ParamValue` DECIMAL(10,2) NULL DEFAULT 1.0 ,
  PRIMARY KEY (`ProductEstimationAuxiliaryParamsID`) )
ENGINE = InnoDB;

CREATE  TABLE `ProductEstimationAuxiliaryParamsForSolution` (
  `ProductEstimationAuxiliaryParamsForSolutionID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `SolutionID` INT(10) UNSIGNED NOT NULL ,
  `ProductEstimationAuxiliaryParamsID` INT(10) UNSIGNED NOT NULL ,
  `ParamTypeCode` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`ProductEstimationAuxiliaryParamsForSolutionID`) ,
  INDEX `fk_Solution` (`SolutionID` ASC) ,
  INDEX `fk_Auxiliary_Param` (`ProductEstimationAuxiliaryParamsID` ASC),
  UNIQUE INDEX `index4` (`SolutionID` ASC, `ParamTypeCode` ASC),
  CONSTRAINT `fk_Solution`
    FOREIGN KEY (`SolutionID` )
    REFERENCES `Solution` (`SolutionID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Auxiliary_Param`
    FOREIGN KEY (`ProductEstimationAuxiliaryParamsID` )
    REFERENCES `ProductEstimationAuxiliaryParams` (`ProductEstimationAuxiliaryParamsID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE `TicketDistribution` (
  `TicketDistributionID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `OpportunityScopeID` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `percentTicketDistribution` FLOAT(10,2) UNSIGNED NULL DEFAULT NULL ,
  PRIMARY KEY (`TicketDistributionID`) ,
  INDEX `index2` (`OpportunityScopeID` ASC) ,
  INDEX `fk_TicketDistribution_1` (`OpportunityScopeID` ASC) ,
  CONSTRAINT `fk_TicketDistribution_1`
    FOREIGN KEY (`OpportunityScopeID` )
    REFERENCES `OpportunityScope` (`OpportunityScopeID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


  #drop table E2EProcessQuality
  CREATE TABLE `E2EProcessQuality` (
  `E2EProcessQualityID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SolutionID` int(10) unsigned NOT NULL,
  `OpportunityScopeID` int(10) unsigned NOT NULL,
  `MonitorBusinessProcess` FLOAT(10,2) unsigned DEFAULT NULL,
  `MonitorDataQualityAndAlign` FLOAT(10,2) unsigned DEFAULT NULL,
  `AnalyzeBPandDataQuality` FLOAT(10,2) unsigned DEFAULT NULL, 
  `ImprovementOfProcesses` FLOAT(10,2) unsigned DEFAULT NULL,
  `AnalyzeRCofTTVolume` FLOAT(10,2) unsigned DEFAULT NULL,
  `ReportPreparation` FLOAT(10,2) unsigned DEFAULT NULL,
  `TotalHours` FLOAT(10,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`E2EProcessQualityID`),
  KEY `SolutionID` (`SolutionID`),
  KEY `OpportunityScopeID` (`OpportunityScopeID`),
  CONSTRAINT `E2EProcessQuality_ibfk_1` FOREIGN KEY (`SolutionID`) REFERENCES `Solution` (`SolutionID`),
  CONSTRAINT `E2EProcessQuality_ibfk_4` FOREIGN KEY (`OpportunityScopeID`) REFERENCES `OpportunityScope` (`OpportunityScopeID`) ON DELETE CASCADE
) ENGINE=InnoDB;

#DROP TABLE IF EXISTS AccessManagement;
CREATE TABLE `AccessManagement` (
  `AccessManagementID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SolutionID` int(10) unsigned NOT NULL,
  `OpportunityScopeID` int(10) unsigned NOT NULL,
  `ManageUsersRights`  FLOAT(10,2) unsigned DEFAULT NULL,
  `ManageUsers`  FLOAT(10,2) unsigned DEFAULT NULL,
  `TotalHours`  FLOAT(10,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`AccessManagementID`),
  KEY `SolutionID` (`SolutionID`),
  KEY `OpportunityScopeID` (`OpportunityScopeID`),
  CONSTRAINT `AccessManagement_ibfk_1` FOREIGN KEY (`SolutionID`) REFERENCES `Solution` (`SolutionID`),
  CONSTRAINT `AccessManagement_ibfk_2` FOREIGN KEY (`OpportunityScopeID`) REFERENCES `OpportunityScope` (`OpportunityScopeID`) ON DELETE CASCADE
) ENGINE=InnoDB;


CREATE  TABLE `ProductBaseEstimationForSolution` (
  `ProductBaseEstimationForSolutionID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `SolutionID` INT(10) UNSIGNED NOT NULL ,
  `ServiceScopeID` INT(10) UNSIGNED NOT NULL ,
  `TotalBaseHours` FLOAT(10,2) NULL ,
  `TotalBaseFTE` FLOAT(10,2) NULL ,
  PRIMARY KEY (`ProductBaseEstimationForSolutionID`) ,
  INDEX `fk_ProductBaseEstimationForSolution_1` (`SolutionID` ASC) ,
  INDEX `fk_ProductBaseEstimationForSolution_2` (`ServiceScopeID` ASC) ,
  UNIQUE INDEX `ProductBaseEstimationForSolution_3` (`SolutionID` ASC, `ServiceScopeID` ASC),
  CONSTRAINT `fk_ProductBaseEstimationForSolution_1`
    FOREIGN KEY (`SolutionID` )
    REFERENCES `Solution` (`SolutionID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ProductBaseEstimationForSolution_2`
    FOREIGN KEY (`ServiceScopeID` )
    REFERENCES `ServiceScope` (`ServiceScopeID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION) ENGINE = InnoDB;
    
#DROP TABLE IF EXISTS ServiceAssurance;

CREATE TABLE `ServiceAssurance` (
  `ServiceAssuranceID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SolutionID` int(10) unsigned NOT NULL,
  `OpportunityScopeID` int(10) unsigned NOT NULL,
  `HelpDeskSupport` FLOAT(10,2) unsigned DEFAULT NULL,
  `IncidentManagement` FLOAT(10,2) unsigned DEFAULT NULL,
  `ProblemManagement` FLOAT(10,2) unsigned DEFAULT NULL, 
  `ImpactAnalysis` FLOAT(10,2) unsigned DEFAULT NULL,
  `DataAlignment` FLOAT(10,2) unsigned DEFAULT NULL,
  `IncidentReporting` FLOAT(10,2) unsigned DEFAULT NULL,
  `BugFixing` FLOAT(10,2) unsigned DEFAULT NULL,
  `TotalServiceAssuranceHours` FLOAT(10,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`ServiceAssuranceID`),
  KEY `SolutionID` (`SolutionID`),
  KEY `OpportunityScopeID` (`OpportunityScopeID`),
  CONSTRAINT `ServiceAssurance_ibfk_1` FOREIGN KEY (`SolutionID`) REFERENCES `Solution` (`SolutionID`),
  CONSTRAINT `ServiceAssurance_ibfk_4` FOREIGN KEY (`OpportunityScopeID`) REFERENCES `OpportunityScope` (`OpportunityScopeID`) ON DELETE CASCADE
) ENGINE=InnoDB;


#Service Bucket views IMPORTANT************* following views DDLs will run one byOne....***********
CREATE VIEW ViewEnhanDevServiceFTEPart as 
select SolutionID,IFNULL(SolutionApproachDimensionID,-1) SolutionApproachDimensionID, IFNULL(sedr.AverageFTE,0) ServiceFTE from SolutionEnhanDevRICEFW sedr
UNION
select SolutionID, IFNULL(SolutionApproachDimensionID,-1) SolutionApproachDimensionID,ifnull(AverageFTECR,0)+ifnull(AverageFTEHrs,0) ServiceFTE from SolutionEnhanDevFixedDeposite;

CREATE VIEW ViewEnhanDevServiceFTE as 
select 5 ServiceScopeID,SolutionID,SolutionApproachDimensionID,'N' isAdditionalFTE,ROUND(sum(ServiceFTE),2) ServiceFTE from ViewEnhanDevServiceFTEPart group by SolutionID


#Joining All services making plus

#ViewServicesFTEPart
#Joining All services making plus
create view ViewServicesFTEPart as 
select 1 ServiceScopeID,SolutionID,ifnull(SolutionApproachDimensionID,-1) SolutionApproachDimensionID,'N' isAdditionalFTE, AugmentedHeads ServiceFTE from SolutionServiceDesk where TouchPointChannelID=4
UNION
select 
 2 ServiceScopeID, SolutionID,IFNULL(SolutionApproachDimensionID,-1) SolutionApproachDimensionID, 'Y' isAdditionalFTE,
AugmentedL1ServiceFTE ServiceFTE 
from SolutionL1AddServices
UNION
select 
2 ServiceScopeID,SolutionID,IFNULL(SolutionApproachDimensionID,-1) SolutionApproachDimensionID, 'N' isAdditionalFTE,
AugmentedL1OperationFTE ServiceFTE 
from SolutionL1Operations
UNION
select 
 3 ServiceScopeID,SolutionID,IFNULL(SolutionApproachDimensionID,-1) SolutionApproachDimensionID, 'Y' isAdditionalFTE,
AugmentedL2ServiceFTE ServiceFTE 
from SolutionL2AddServices
UNION
select 
 3 ServiceScopeID,SolutionID,IFNULL(SolutionApproachDimensionID,-1) SolutionApproachDimensionID, 'N' isAdditionalFTE,
AugmentedL2OperationFTE ServiceFTE 
from SolutionL2Operations
UNION
select 
4 ServiceScopeID,SolutionID,IFNULL(SolutionApproachDimensionID,-1) SolutionApproachDimensionID, 'Y' isAdditionalFTE,
AugmentedL3ServiceFTE ServiceFTE 
from SolutionL3AddServices
UNION
select 
4 ServiceScopeID,SolutionID,IFNULL(SolutionApproachDimensionID,-1) SolutionApproachDimensionID, 'N' isAdditionalFTE,
AugmentedL3OperationFTE ServiceFTE 
from SolutionL3Operations
UNION
select * from ViewEnhanDevServiceFTE
UNION
select 7 ServiceScopeID,SolutionID,IFNULL(SolutionApproachDimensionID,-1) SolutionApproachDimensionID, 'N' isAdditionalFTE,
FTE ServiceFTE 
from SolutionGovAndPMO
UNION
select 9 ServiceScopeID,SolutionID,IFNULL(SolutionApproachDimensionID,-1) SolutionApproachDimensionID, 'N' isAdditionalFTE,
FTE ServiceFTE 
from SolutionOtherMisc
UNION
select 10 ServiceScopeID,SolutionID,-1 SolutionApproachDimensionID, 'N' isAdditionalFTE,
TotalProgramManagementHours ServiceFTE 
from ProgramManagement
UNION
select 11 ServiceScopeID,SolutionID,-1 SolutionApproachDimensionID, 'N' isAdditionalFTE,
TotalReleaseManagementHours ServiceFTE 
from ReleaseManagement
UNION
select 12 ServiceScopeID,SolutionID,-1 SolutionApproachDimensionID, 'N' isAdditionalFTE,
TotalChangeManagementHours ServiceFTE 
from ChangeManagement
UNION
select 13 ServiceScopeID,SolutionID,-1 SolutionApproachDimensionID, 'N' isAdditionalFTE,
FteCapacityManagement ServiceFTE 
from CapacityManagement
UNION
select 14 ServiceScopeID,SolutionID,-1 SolutionApproachDimensionID, 'N' isAdditionalFTE,
FteConfigurationManagement ServiceFTE 
from ConfigurationManagement
UNION
select 15 ServiceScopeID,SolutionID,-1 SolutionApproachDimensionID, 'N' isAdditionalFTE,
FteSecurityManagement ServiceFTE 
from SecurityManagement
UNION
select 16 ServiceScopeID,SolutionID,-1 SolutionApproachDimensionID, 'N' isAdditionalFTE,
FteAvailabilityManagement ServiceFTE 
from AvailabilityManagement
UNION
select 17 ServiceScopeID,SolutionID,-1 SolutionApproachDimensionID, 'N' isAdditionalFTE,
TotalHours ServiceFTE 
from AccessManagement
UNION
select 18 ServiceScopeID,SolutionID,-1 SolutionApproachDimensionID, 'N' isAdditionalFTE,
TotalDemandSupportHours ServiceFTE 
from DemandSupport
UNION
select 19 ServiceScopeID,SolutionID,-1 SolutionApproachDimensionID, 'N' isAdditionalFTE,
TotalDesignBuildHours ServiceFTE 
from DesignBuild
UNION
select 21 ServiceScopeID,SolutionID,-1 SolutionApproachDimensionID, 'N' isAdditionalFTE,
TotalDeploymentRollOutHours ServiceFTE 
from DeploymentRollOut
UNION
select 22 ServiceScopeID,SolutionID,-1 SolutionApproachDimensionID, 'N' isAdditionalFTE,
TotalPostRelActHours ServiceFTE 
from PostReleaseActivities
UNION
select 23 ServiceScopeID,SolutionID,-1 SolutionApproachDimensionID, 'N' isAdditionalFTE,
TotalServiceAssuranceHours ServiceFTE 
from ServiceAssurance
UNION
select 24 ServiceScopeID,SolutionID,-1 SolutionApproachDimensionID, 'N' isAdditionalFTE,
TotalHours ServiceFTE 
from E2EProcessQuality
UNION
select 25 ServiceScopeID,SolutionID,IFNULL(SolutionApproachDimensionID,-1) SolutionApproachDimensionID, 'N' isAdditionalFTE,
FTE ServiceFTE 
from SolutionTestingAsAService
UNION
select ServiceScopeID, SolutionID, - 1 SolutionApproachDimensionID,'N' isAdditionalFTE,
(sum(TotalEffortDerived)/2040) ServiceFTE
from ProductEstimationForSolution sol, 
ProductActivitiesComplexityAssumptions comp,
ProductEstimationActivities act
where act.ProductEstimationActivitiesID = comp.ProductEstimationActivitiesID
and comp.ProductActivitiesComplexityAssumptionsID = sol.ProductActivitiesComplexityAssumptionsID
group by sol.SolutionID, act.ServiceScopeID;

#It used to return Services wise FTE addition irespetcive of additional FTE/ Solution Approach
create view ViewServicesFTE as select ServiceScopeID,SolutionID, ROUND(SUM(ServiceFTE),2) ServiceFTE from ViewServicesFTEPart group by ServiceScopeID,SolutionID;

#For Service category details 
create view VieweOpportunityScopeSolution as select
	os.OpportunityID,s.SolutionID,
	os.OpportunityScopeID,
	sc.ServiceScopeID,
	sc.ServiceScopeCategory,
	sc.ServiceScopeName	
from
	OpportunityScope os,
	ServiceScope sc,
	Solution s
where
	s.OpportunityID=os.OpportunityID and os.ServiceScopeID=sc.ServiceScopeID;

#Outerjoin fr whole fields Opp Scope solution... even FTE absent
create view VieweOpportunityScopeSolutionServicesFTE as select voss.*,vsf.ServiceFTE from VieweOpportunityScopeSolution voss 
left join ViewServicesFTE vsf on (voss.SolutionID=vsf.SolutionID and 

voss.ServiceScopeID=vsf.ServiceScopeID);

create view ViewStartupFTE as select voss.OpportunityID, voss.SolutionID, voss.OpportunityScopeID, voss.ServiceScopeID, voss.ServiceScopeCategory, voss.ServiceScopeName, 
voss.ServiceFTE BaseServiceFTE,ifnull(apac.AdjustedFTE,voss.ServiceFTE) ServiceFTE,apac.AdjustedFTE AdjustedFTE from VieweOpportunityScopeSolutionServicesFTE voss 
left join APAComplexity apac on (voss.SolutionID=apac.SolutionID and 
voss.OpportunityScopeID=apac.OpportunityScopeID);

create view ViewStaffingPlan as select SolutionID, ROUND(sum(NoDemandedPositions))  NoDemandedPositions from StaffingPlan group by SolutionID;

#Doingouter joins fr StaffingPlan tables data
create view ViewServiceBucket as Select vsf.*,sp.NoDemandedPositions from ViewStartupFTE 
	vsf left join ViewStaffingPlan sp on  sp.SolutionID=vsf.SolutionID;

#ViewTTSummaryStagingJoins joined for SolutionID and Planning ID
CREATE VIEW ViewTTSummaryStagingJoins AS select TTP.SolutionID,TTPN.TTPlanningID,TTSS.TTPartitionNameID, TTSS.WeekDate,TTSS.OnshoreFTE,TTSS.OffshoreFTE from TTSummaryStaging TTSS, TTPartitionName TTPN ,
TTPlanning TTP
where TTPN.TTPartitionNameID=TTSS.TTPartitionNameID and TTP.TTPlanningID=TTPN.TTPlanningID;

#ViewTTSummaryStaging for solutionID/Planning ID based weekly FTE values
CREATE VIEW ViewTTSummaryStaging AS Select JoinedTab.SolutionID,JoinedTab.TTPlanningID,JoinedTab.WeekDate,sum(JoinedTab.OnshoreFTE) OnshoreFTE,sum(JoinedTab.OffshoreFTE) OffshoreFTE from ViewTTSummaryStagingJoins JoinedTab 
group by JoinedTab.WeekDate,JoinedTab.TTPlanningID 
order by JoinedTab.SolutionID,JoinedTab.TTPlanningID,JoinedTab.WeekDate;


