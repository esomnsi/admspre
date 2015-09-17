package com.ericsson.mssp.solution.taas.business;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.taas.objects.ComplexityFactor;
import com.ericsson.mssp.taas.objects.EffLever;
import com.ericsson.mssp.taas.objects.Effort;
import com.ericsson.mssp.taas.objects.MajorRelease;
import com.ericsson.mssp.taas.objects.MinorRelease;
import com.ericsson.mssp.taas.objects.RegLever;
import com.ericsson.mssp.taas.objects.ReleaseCount;
import com.ericsson.mssp.taas.objects.TaasPrimaryOutput;
import com.ericsson.mssp.taas.objects.TaasSecondaryOutput;
import com.ericsson.mssp.taas.objects.TestEffRed;
import com.ericsson.mssp.taas.objects.TgtDistrn;

public class Calculation implements Serializable, MSSPConstants
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6534196354383132263L;

	private double simpleRatio;
	private double simpleTestDesign;
	private double simpleTestExec;
	private double simpleAutoScript;
	private double mediumRatio;
	private double mediumTestDesign;
	private double mediumTestExec;
	private double mediumAutoScript;
	private double complexRatio;
	private double complexTestDesign;
	private double complexTestExec;
	private double complexAutoScript;
	private double testMgmtEff;
	private double reqmntAnalysisEff;
	private double autoExecEff;
	private double autoMaintEff;
	private int numMajorRel;
	private int numMinorRel;
	private List<TgtDistrn> tgtDistnList;
	private double majRegAvgNumTestCase;
	private double majRegPerAutoDesign;
	private double majRegTestDesignReuse;
	private double majRegTestExecCycles;
	private double majRegAsIsTestExecCycles;
	private double majNewFuncAvgNumTestCase;
	private double majNewFuncPerAutoDesign;
	private double majNewFuncTestDesignReuse;
	private double majNewFuncTestExecCycles;
	private double majNewFuncAsIsTestExecCycles;
	private double majUatProdAvgNumTestCase;
	//private double majUatProdPerAutoDesign;
	private double majUatProdTestDesignReuse;
	private double majUatProdTestExecCycles;
	private double majUatProdAsIsTestExecCycles;
	private double minRegAvgNumTestCase;
	//private double minRegPerAutoDesign;
	private double minRegTestDesignReuse;
	private double minRegTestExecCycles;
	private double minRegAsIsTestExecCycles;
	private double minNewFuncAvgNumTestCase;
	private double minNewFuncPerAutoDesign;
	private double minNewFuncTestDesignReuse;
	private double minNewFuncTestExecCycles;
	private double minNewFuncAsIsTestExecCycles;
	private double minUatProdAvgNumTestCase;
	private double minUatProdPerAutoDesign;
	private double minUatProdTestDesignReuse;
	private double minUatProdTestExecCycles;
	private double minUatProdAsIsTestExecCycles;
	private double regRelease;
	private double newFuncRelease;
	private double autoScriptRelease;
	private List<TestEffRed> testEffRedList;
	private Map<String, String> majRelDateMap;
	private Map<String, String> minRelDateMap;
	
	public Calculation(List<ComplexityFactor> factorList,
			List<Effort> effortList, List<ReleaseCount> relCountList,
			List<TgtDistrn> tgtDistnList, List<EffLever> effLeverList,
			List<RegLever> regLeverList, List<TestEffRed> testEffRedList, Map<String, String> majRelDateMap, Map<String, String> minRelDateMap) 
	{
		this.testEffRedList = testEffRedList;
		this.tgtDistnList = tgtDistnList;
		this.majRelDateMap = majRelDateMap;
		this.minRelDateMap = minRelDateMap;
		for(ComplexityFactor complexityFactorObj : factorList)
		{
			if(complexityFactorObj.getFactor().equalsIgnoreCase(TEST_CASE_TYPE_SIMPLE))
			{
				this.simpleRatio = complexityFactorObj.getRatio();
				this.simpleTestDesign = complexityFactorObj.getTestDesign();
				this.simpleTestExec = complexityFactorObj.getTestExec();
				this.simpleAutoScript = complexityFactorObj.getAutoScripting();
			}
			else if(complexityFactorObj.getFactor().equalsIgnoreCase(TEST_CASE_TYPE_MEDIUM))
			{
				this.mediumRatio = complexityFactorObj.getRatio();
				this.mediumTestDesign = complexityFactorObj.getTestDesign();
				this.mediumTestExec = complexityFactorObj.getTestExec();
				this.mediumAutoScript = complexityFactorObj.getAutoScripting();
			}
			else if(complexityFactorObj.getFactor().equalsIgnoreCase(TEST_CASE_TYPE_COMPLEX))
			{
				this.complexRatio = complexityFactorObj.getRatio();
				this.complexTestDesign = complexityFactorObj.getTestDesign();
				this.complexTestExec = complexityFactorObj.getTestExec();
				this.complexAutoScript = complexityFactorObj.getAutoScripting();
			}
		}
		for(Effort effdtoObj : effortList)
		{
			this.testMgmtEff = effdtoObj.getEffTestMgmt();
			this.reqmntAnalysisEff = effdtoObj.getEffTestDesign();
			this.autoExecEff = effdtoObj.getEffAutoExec();
			this.autoMaintEff = effdtoObj.getEffAutoMaint();
		}
		for(ReleaseCount relCountObj : relCountList)
		{
			this.numMajorRel = relCountObj.getNumMajorRel();
			this.numMinorRel = relCountObj.getNumMinorRel();
		}
		for(EffLever effLeverDtoObj : effLeverList)
		{
			if(effLeverDtoObj.getRelType().equalsIgnoreCase(RELEASE_TYPE_MAJOR))
			{
				if(effLeverDtoObj.getTestType().equalsIgnoreCase(TEST_TYPE_REGRESSION))
				{
					this.majRegAvgNumTestCase = effLeverDtoObj.getAvgNumTestCase();
					this.majRegPerAutoDesign = effLeverDtoObj.getPercentAutoDesign();
					this.majRegTestDesignReuse = effLeverDtoObj.getTestDesignReuse();
					this.majRegTestExecCycles = effLeverDtoObj.getTestExecCycles();
					this.majRegAsIsTestExecCycles = effLeverDtoObj.getAsIsTestExecCycles();
				}
				else if(effLeverDtoObj.getTestType().equalsIgnoreCase(TEST_TYPE_NEW_FUNCTIONALITY))
				{
					this.majNewFuncAvgNumTestCase = effLeverDtoObj.getAvgNumTestCase();
					this.majNewFuncPerAutoDesign = effLeverDtoObj.getPercentAutoDesign();
					this.majNewFuncTestDesignReuse = effLeverDtoObj.getTestDesignReuse();
					this.majNewFuncTestExecCycles = effLeverDtoObj.getTestExecCycles();
					this.majNewFuncAsIsTestExecCycles = effLeverDtoObj.getAsIsTestExecCycles();
				}
				else if(effLeverDtoObj.getTestType().equalsIgnoreCase(TEST_TYPE_UAT_PROD_GO_LIVE))
				{
					this.majUatProdAvgNumTestCase = effLeverDtoObj.getAvgNumTestCase();
					//this.majUatProdPerAutoDesign = effLeverDtoObj.getPercentAutoDesign();
					this.majUatProdTestDesignReuse = effLeverDtoObj.getTestDesignReuse();
					this.majUatProdTestExecCycles = effLeverDtoObj.getTestExecCycles();
					this.majUatProdAsIsTestExecCycles = effLeverDtoObj.getAsIsTestExecCycles();
				}
			}
			else if(effLeverDtoObj.getRelType().equalsIgnoreCase(RELEASE_TYPE_MINOR))
			{
				if(effLeverDtoObj.getTestType().equalsIgnoreCase(TEST_TYPE_REGRESSION))
				{
					this.minRegAvgNumTestCase = effLeverDtoObj.getAvgNumTestCase();
					//this.minRegPerAutoDesign = effLeverDtoObj.getPercentAutoDesign();
					this.minRegTestDesignReuse = effLeverDtoObj.getTestDesignReuse();
					this.minRegTestExecCycles = effLeverDtoObj.getTestExecCycles();
					this.minRegAsIsTestExecCycles = effLeverDtoObj.getAsIsTestExecCycles();
				}
				else if(effLeverDtoObj.getTestType().equalsIgnoreCase(TEST_TYPE_NEW_FUNCTIONALITY))
				{
					this.minNewFuncAvgNumTestCase = effLeverDtoObj.getAvgNumTestCase();
					this.minNewFuncPerAutoDesign = effLeverDtoObj.getPercentAutoDesign();
					this.minNewFuncTestDesignReuse = effLeverDtoObj.getTestDesignReuse();
					this.minNewFuncTestExecCycles = effLeverDtoObj.getTestExecCycles();
					this.minNewFuncAsIsTestExecCycles = effLeverDtoObj.getAsIsTestExecCycles();
				}
				else if(effLeverDtoObj.getTestType().equalsIgnoreCase(TEST_TYPE_UAT_PROD_GO_LIVE))
				{
					this.minUatProdAvgNumTestCase = effLeverDtoObj.getAvgNumTestCase();
					this.minUatProdPerAutoDesign = effLeverDtoObj.getPercentAutoDesign();
					this.minUatProdTestDesignReuse = effLeverDtoObj.getTestDesignReuse();
					this.minUatProdTestExecCycles = effLeverDtoObj.getTestExecCycles();
					this.minUatProdAsIsTestExecCycles = effLeverDtoObj.getAsIsTestExecCycles();
				}
			}
		}
		for(RegLever regLeverdtoObj : regLeverList)
		{
			this.regRelease = regLeverdtoObj.getRegOptRel();
			this.newFuncRelease = regLeverdtoObj.getNewFuncRel();
			this.autoScriptRelease = regLeverdtoObj.getAutoRegScriptRel();
		}
	}
	

	public boolean calculateResults() 
	{
		return false;
	}

	
	public Map<String, List<? extends Object>> beginCalculation() 
	{
		/*-------------------------------------------------------- MAJOR RELEASE --------------------------------------------------------------------*/
		//Populates data for Major Release - Regression
		double numAutoTestCase = (majRegAvgNumTestCase*majRegPerAutoDesign)/100;
		double majRegAutoScPlDevMaintEffPerDay = numAutoTestCase*(simpleRatio/(100*simpleAutoScript)+(mediumRatio/(100*mediumAutoScript))+(complexRatio/(100*complexAutoScript)));
		double majRegAutoMaintEffPerDay = majRegAutoScPlDevMaintEffPerDay*autoMaintEff/100;
		HashMap<Integer, Double> regAutoTestCountMap = new HashMap<Integer, Double>();
		regAutoTestCountMap.put(new Integer(AS_IS), new Double(0));
		for(int i=0; i<numMajorRel; i++)
		{
			regAutoTestCountMap.put(new Integer(AS_IS+(i+1)), new Double((tgtDistnList.get(i).getRatio()/100)*numAutoTestCase));
		}
		HashMap<Integer, Double> regManualTestCountMap = new HashMap<Integer, Double>();
		regManualTestCountMap.put(new Integer(AS_IS), new Double(majRegAvgNumTestCase));
		double testCount = 0;
		for(int i=0; i<numMajorRel; i++)
		{
			testCount += regAutoTestCountMap.get(AS_IS+i+1).doubleValue();
			regManualTestCountMap.put(new Integer(AS_IS+i+1), new Double(majRegAvgNumTestCase-testCount));
		}
		testCount = 0;
		HashMap<Integer, Double> majRegAsIsReleaseMap = new HashMap<Integer, Double>();
		majRegAsIsReleaseMap.put(new Integer(TEST_DESIGN), new Double((100-majRegTestDesignReuse)/100)*(((regManualTestCountMap.get(AS_IS).doubleValue()*simpleRatio/100)/simpleTestDesign)+((regManualTestCountMap.get(AS_IS).doubleValue()*mediumRatio/100)/mediumTestDesign)+((regManualTestCountMap.get(AS_IS).doubleValue()*complexRatio/100)/complexTestDesign)));
		majRegAsIsReleaseMap.put(new Integer(TEST_EXECUTION), new Double(regManualTestCountMap.get(AS_IS).doubleValue()/100*((majRegAsIsTestExecCycles*simpleRatio/simpleTestExec)+(mediumRatio/mediumTestExec)+(complexRatio/complexTestExec))));
		majRegAsIsReleaseMap.put(new Integer(TEST_PLAN_REQMNT_AUTO_ANALYSIS), new Double((majRegAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+majRegAsIsReleaseMap.get(TEST_EXECUTION).doubleValue())*reqmntAnalysisEff/100));
		majRegAsIsReleaseMap.put(new Integer(TEST_MGMT), new Double((majRegAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+majRegAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+majRegAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue())*testMgmtEff/100));
		majRegAsIsReleaseMap.put(new Integer(TOTAL_EFFORT), new Double(majRegAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+majRegAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+majRegAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue()+majRegAsIsReleaseMap.get(TEST_MGMT).doubleValue()));
		List<MajorRelease> majorRelRegList = new ArrayList<MajorRelease>();
		for(int i=0; i<numMajorRel; i++)
		{
			MajorRelease mjrRel = new MajorRelease();
			if(i == 0)
			{
				mjrRel.setTestDesign(((100-majRegTestDesignReuse)/100)*(majRegAvgNumTestCase*((simpleRatio/(100*simpleTestDesign))+(mediumRatio/(100*mediumTestDesign))+(complexRatio/(100*complexTestDesign)))));
				mjrRel.setTestExec((regManualTestCountMap.get(AS_IS+i+1)*((majRegTestExecCycles*simpleRatio/(100*simpleTestExec))+(mediumRatio/(100*mediumTestExec))+(complexRatio/(100*complexTestExec)))));
				mjrRel.setTestPlRqAnAtEx((mjrRel.getTestDesign()*reqmntAnalysisEff)/100+(mjrRel.getTestExec()*autoExecEff)/100);
				mjrRel.setTestMgmt((mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx())*testMgmtEff/100);
				mjrRel.setTotalEffort(mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx()+mjrRel.getTestMgmt());
			}
			else
			{
				mjrRel.setTestDesign(0);
				mjrRel.setTestExec(regManualTestCountMap.get(AS_IS+i+1).doubleValue()*((majRegTestExecCycles*simpleRatio/(100*simpleTestExec))+(mediumRatio/(100*mediumTestExec))+(complexRatio/(100*complexTestExec))));
				mjrRel.setTestPlRqAnAtEx(mjrRel.getTestExec()*autoExecEff/100);
				mjrRel.setTestMgmt((mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx())*testMgmtEff/100);
				mjrRel.setTotalEffort(mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx()+mjrRel.getTestMgmt());
			}
			majorRelRegList.add(mjrRel);
		}
		
		//Populates data for Major Release - New Functionality
		double majNewFuncPercentTotalAuto = majNewFuncPerAutoDesign*numMajorRel;
		double majNumNewFuncAutoTestCase = majNewFuncPercentTotalAuto*majNewFuncAvgNumTestCase/100;
		double majNewFuncAutoScPlDevMaintEffPerDay = majNumNewFuncAutoTestCase*(simpleRatio/(100*simpleAutoScript)+(mediumRatio/(100*mediumAutoScript))+(complexRatio/(100*complexAutoScript)));
		double majNewFuncAutoMaintEffPerDay = majNewFuncAutoScPlDevMaintEffPerDay*autoMaintEff/100;
		HashMap<Integer, Double> majNewFuncAutoTestCountMap = new HashMap<Integer, Double>();
		majNewFuncAutoTestCountMap.put(new Integer(AS_IS), new Double(0));
		for(int i=0; i<numMajorRel; i++)
		{
			majNewFuncAutoTestCountMap.put(new Integer(AS_IS+(i+1)), new Double((tgtDistnList.get(i).getRatio()/100)*majNumNewFuncAutoTestCase));
		}
		HashMap<Integer, Double> majNewFuncManualTestCountMap = new HashMap<Integer, Double>();
		majNewFuncManualTestCountMap.put(new Integer(AS_IS), new Double(majNewFuncAvgNumTestCase));
		for(int i=0; i<numMajorRel; i++)
		{
			testCount += majNewFuncAutoTestCountMap.get(AS_IS+i+1).doubleValue();
			majNewFuncManualTestCountMap.put(new Integer(AS_IS+i+1), new Double(majNewFuncAvgNumTestCase-testCount));
		}
		testCount = 0;
		HashMap<Integer, Double> majNewFuncAsIsReleaseMap = new HashMap<Integer, Double>();
		majNewFuncAsIsReleaseMap.put(new Integer(TEST_DESIGN), new Double((100-majNewFuncTestDesignReuse)/100)*(((majNewFuncManualTestCountMap.get(AS_IS).doubleValue()*simpleRatio/100)/simpleTestDesign)+((majNewFuncManualTestCountMap.get(AS_IS).doubleValue()*mediumRatio/100)/mediumTestDesign)+((majNewFuncManualTestCountMap.get(AS_IS).doubleValue()*complexRatio/100)/complexTestDesign)));
		majNewFuncAsIsReleaseMap.put(new Integer(TEST_EXECUTION), new Double(majNewFuncManualTestCountMap.get(AS_IS).doubleValue()/100*((majNewFuncAsIsTestExecCycles*simpleRatio/simpleTestExec)+(mediumRatio/mediumTestExec)+(complexRatio/complexTestExec))));
		majNewFuncAsIsReleaseMap.put(new Integer(TEST_PLAN_REQMNT_AUTO_ANALYSIS), new Double((majNewFuncAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+majNewFuncAsIsReleaseMap.get(TEST_EXECUTION).doubleValue())*reqmntAnalysisEff/100));
		majNewFuncAsIsReleaseMap.put(new Integer(TEST_MGMT), new Double((majNewFuncAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+majNewFuncAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+majNewFuncAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue())*testMgmtEff/100));
		majNewFuncAsIsReleaseMap.put(new Integer(TOTAL_EFFORT), new Double(majNewFuncAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+majNewFuncAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+majNewFuncAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue()+majNewFuncAsIsReleaseMap.get(TEST_MGMT).doubleValue()));
		List<MajorRelease> majorRelNewFuncList = new ArrayList<MajorRelease>();
		for(int i=0; i<numMajorRel; i++)
		{
			MajorRelease mjrRel = new MajorRelease();
			if(i == 0)
			{
				mjrRel.setTestDesign(((100-majNewFuncTestDesignReuse)/100)*(majNewFuncManualTestCountMap.get(AS_IS+i+1).doubleValue()*((simpleRatio/(100*simpleTestDesign))+(mediumRatio/(100*mediumTestDesign))+(complexRatio/(100*complexTestDesign)))));
				mjrRel.setTestExec((majNewFuncManualTestCountMap.get(AS_IS+i+1)*((majNewFuncTestExecCycles*simpleRatio/(100*simpleTestExec))+(mediumRatio/(100*mediumTestExec))+(complexRatio/(100*complexTestExec)))));
				mjrRel.setTestPlRqAnAtEx((mjrRel.getTestDesign()*reqmntAnalysisEff)/100+(mjrRel.getTestExec()*autoExecEff)/100);
				mjrRel.setTestMgmt((mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx())*testMgmtEff/100);
				mjrRel.setTotalEffort(mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx()+mjrRel.getTestMgmt());
			}
			else
			{
				mjrRel.setTestDesign(((100-majNewFuncTestDesignReuse)/100)*(majNewFuncManualTestCountMap.get(AS_IS+i+1).doubleValue()*((simpleRatio/(100*simpleTestDesign))+(mediumRatio/(100*mediumTestDesign))+(complexRatio/(100*complexTestDesign)))));
				mjrRel.setTestExec(majNewFuncManualTestCountMap.get(AS_IS+i+1).doubleValue()*((majNewFuncTestExecCycles*simpleRatio/(100*simpleTestExec))+(mediumRatio/(100*mediumTestExec))+(complexRatio/(100*complexTestExec))));
				mjrRel.setTestPlRqAnAtEx((mjrRel.getTestDesign()*reqmntAnalysisEff/100)+(mjrRel.getTestExec()*autoExecEff/100));
				mjrRel.setTestMgmt((mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx())*testMgmtEff/100);
				mjrRel.setTotalEffort(mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx()+mjrRel.getTestMgmt());
			}
			majorRelNewFuncList.add(mjrRel);
		}
		
		//Populate data for Major Release - UAT/Prod/Go Live
		double majNumUATAutoTestCase = majUatProdAvgNumTestCase*minUatProdPerAutoDesign;
		double majUATAutoScPlDevMaintEffPerDay = majNumUATAutoTestCase*(simpleRatio/(100*simpleAutoScript)+(mediumRatio/(100*mediumAutoScript))+(complexRatio/(100*complexAutoScript)));
		double majUATAutoMaintEffPerDay = majUATAutoScPlDevMaintEffPerDay*autoMaintEff/100;
		HashMap<Integer, Double> majUATAutoTestCountMap = new HashMap<Integer, Double>();
		majUATAutoTestCountMap.put(new Integer(AS_IS), new Double(0));
		for(int i=0; i<numMajorRel; i++)
		{
			majUATAutoTestCountMap.put(new Integer(AS_IS+(i+1)), new Double((tgtDistnList.get(i).getRatio()/100)*majNumUATAutoTestCase));
		}
		HashMap<Integer, Double> majUATManualTestCountMap = new HashMap<Integer, Double>();
		majUATManualTestCountMap.put(new Integer(AS_IS), new Double(majUatProdAvgNumTestCase));
		for(int i=0; i<numMajorRel; i++)
		{
			testCount += majUATAutoTestCountMap.get(AS_IS+i+1).doubleValue();
			majUATManualTestCountMap.put(new Integer(AS_IS+i+1), new Double(majUatProdAvgNumTestCase-testCount));
		}
		testCount = 0;
		HashMap<Integer, Double> majUATAsIsReleaseMap = new HashMap<Integer, Double>();
		majUATAsIsReleaseMap.put(new Integer(TEST_DESIGN), new Double((100-majUatProdTestDesignReuse)/100)*(((majUATManualTestCountMap.get(AS_IS).doubleValue()*simpleRatio/100)/simpleTestDesign)+((majUATManualTestCountMap.get(AS_IS).doubleValue()*mediumRatio/100)/mediumTestDesign)+((majUATManualTestCountMap.get(AS_IS).doubleValue()*complexRatio/100)/complexTestDesign)));
		majUATAsIsReleaseMap.put(new Integer(TEST_EXECUTION), new Double(majUATManualTestCountMap.get(AS_IS).doubleValue()/100*((majUatProdAsIsTestExecCycles*simpleRatio/simpleTestExec)+(mediumRatio/mediumTestExec)+(complexRatio/complexTestExec))));
		majUATAsIsReleaseMap.put(new Integer(TEST_PLAN_REQMNT_AUTO_ANALYSIS), new Double((majUATAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+majUATAsIsReleaseMap.get(TEST_EXECUTION).doubleValue())*reqmntAnalysisEff/100));
		majUATAsIsReleaseMap.put(new Integer(TEST_MGMT), new Double((majUATAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+majUATAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+majUATAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue())*testMgmtEff/100));
		majUATAsIsReleaseMap.put(new Integer(TOTAL_EFFORT), new Double(majUATAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+majUATAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+majUATAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue()+majUATAsIsReleaseMap.get(TEST_MGMT).doubleValue()));
		List<MajorRelease> majorRelUATList = new ArrayList<MajorRelease>();
		for(int i=0; i<numMajorRel; i++)
		{
			MajorRelease mjrRel = new MajorRelease();
			if(i == 0)
			{
				mjrRel.setTestDesign(((100-majUatProdTestDesignReuse)/100)*(majUATManualTestCountMap.get(AS_IS+i+1).doubleValue()*((simpleRatio/(100*simpleTestDesign))+(mediumRatio/(100*mediumTestDesign))+(complexRatio/(100*complexTestDesign)))));
				mjrRel.setTestExec((majUATManualTestCountMap.get(AS_IS+i+1)*((majUatProdTestExecCycles*simpleRatio/(100*simpleTestExec))+(mediumRatio/(100*mediumTestExec))+(complexRatio/(100*complexTestExec)))));
				mjrRel.setTestPlRqAnAtEx((mjrRel.getTestDesign()*reqmntAnalysisEff)/100+(mjrRel.getTestExec()*autoExecEff)/100);
				mjrRel.setTestMgmt((mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx())*testMgmtEff/100);
				mjrRel.setTotalEffort(mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx()+mjrRel.getTestMgmt());
			}
			else
			{
				mjrRel.setTestDesign(((100-majUatProdTestDesignReuse)/100)*(majUATManualTestCountMap.get(AS_IS+i+1).doubleValue()*((simpleRatio/(100*simpleTestDesign))+(mediumRatio/(100*mediumTestDesign))+(complexRatio/(100*complexTestDesign)))));
				mjrRel.setTestExec(majUATManualTestCountMap.get(AS_IS+i+1).doubleValue()*((majUatProdTestExecCycles*simpleRatio/(100*simpleTestExec))+(mediumRatio/(100*mediumTestExec))+(complexRatio/(100*complexTestExec))));
				mjrRel.setTestPlRqAnAtEx((mjrRel.getTestDesign()*reqmntAnalysisEff/100)+(mjrRel.getTestExec()*autoExecEff/100));
				mjrRel.setTestMgmt((mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx())*testMgmtEff/100);
				mjrRel.setTotalEffort(mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx()+mjrRel.getTestMgmt());
			}
			majorRelUATList.add(mjrRel);
		}
		//Populate data for Major Release - Regression test cases added from New Functionality
		double numRegNewFuncTestCase = majNewFuncAvgNumTestCase*newFuncRelease/100;
		HashMap<Integer, Double> majRegNewFuncAutoTestCountMap = new HashMap<Integer, Double>();
		majRegNewFuncAutoTestCountMap.put(new Integer(AS_IS), new Double(0));
		for(int i=0; i<numMajorRel; i++)
		{
			majRegNewFuncAutoTestCountMap.put(new Integer(AS_IS+(i+1)), new Double(majNewFuncAutoTestCountMap.get(AS_IS+i)));
		}
		
		HashMap<Integer, Double> majRegNewFuncManualTestCountMap = new HashMap<Integer, Double>();
		if(numMajorRel == 1){
			majRegNewFuncManualTestCountMap.put(new Integer(AS_IS), new Double(0));
			majRegNewFuncManualTestCountMap.put(new Integer(AS_IS+1), new Double(0));
		}
		if(numMajorRel == 2){
			majRegNewFuncManualTestCountMap.put(new Integer(AS_IS), new Double(0));
			majRegNewFuncManualTestCountMap.put(new Integer(AS_IS+1), new Double(0));
			majRegNewFuncManualTestCountMap.put(new Integer(AS_IS+2), new Double(numRegNewFuncTestCase-majRegNewFuncAutoTestCountMap.get(AS_IS+2).doubleValue()));
		}
		if(numMajorRel == 3){
			majRegNewFuncManualTestCountMap.put(new Integer(AS_IS), new Double(0));
			majRegNewFuncManualTestCountMap.put(new Integer(AS_IS+1), new Double(0));
			majRegNewFuncManualTestCountMap.put(new Integer(AS_IS+2), new Double(numRegNewFuncTestCase-majRegNewFuncAutoTestCountMap.get(AS_IS+2).doubleValue()));
			majRegNewFuncManualTestCountMap.put(new Integer(AS_IS+3), new Double((2*numRegNewFuncTestCase)-majRegNewFuncAutoTestCountMap.get(AS_IS+2).doubleValue()-majRegNewFuncAutoTestCountMap.get(AS_IS+3).doubleValue()));
		}
		
		HashMap<Integer, Double> majRegNewFuncAsIsReleaseMap = new HashMap<Integer, Double>();
		
		majRegNewFuncAsIsReleaseMap.put(new Integer(TEST_DESIGN), new Double(0));
		majRegNewFuncAsIsReleaseMap.put(new Integer(TEST_EXECUTION), new Double((majRegNewFuncManualTestCountMap.get(AS_IS).doubleValue()/100)*((majRegAsIsTestExecCycles*simpleRatio/simpleTestExec)+(mediumRatio/mediumTestExec)+(complexRatio/complexTestExec))));
		majRegNewFuncAsIsReleaseMap.put(new Integer(TEST_PLAN_REQMNT_AUTO_ANALYSIS), new Double((majRegNewFuncAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+majRegNewFuncAsIsReleaseMap.get(TEST_EXECUTION).doubleValue())*reqmntAnalysisEff));
		majRegNewFuncAsIsReleaseMap.put(new Integer(TEST_MGMT), new Double((majRegNewFuncAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+majRegNewFuncAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+majRegNewFuncAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue())*testMgmtEff));
		majRegNewFuncAsIsReleaseMap.put(new Integer(TOTAL_EFFORT), new Double(majRegNewFuncAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+majRegNewFuncAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+majRegNewFuncAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue()+majRegNewFuncAsIsReleaseMap.get(TEST_MGMT).doubleValue()));
		List<MajorRelease> majorRelRegNewFuncList = new ArrayList<MajorRelease>();
		for(int i=0; i<numMajorRel; i++)
		{
			MajorRelease mjrRel = new MajorRelease();
			mjrRel.setTestDesign(0);
			mjrRel.setTestExec((majRegNewFuncManualTestCountMap.get(AS_IS+i+1).doubleValue()/100)*((majRegTestExecCycles*simpleRatio/simpleTestExec)+(mediumRatio/mediumTestExec)+(complexRatio/complexTestExec)));
			mjrRel.setTestPlRqAnAtEx((mjrRel.getTestDesign()*reqmntAnalysisEff/100)+(mjrRel.getTestExec()*autoExecEff/100));
			mjrRel.setTestMgmt((mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx())*testMgmtEff/100);
			mjrRel.setTotalEffort(mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx()+mjrRel.getTestMgmt());
			majorRelRegNewFuncList.add(mjrRel);
		}
		// Populate data for Major Release - Optimized regression test cases
		HashMap<Integer, Double> majRegOptTestCountMap = new HashMap<Integer, Double>();
		majRegOptTestCountMap.put(new Integer(AS_IS), new Double(0));
		for(int i=0; i<numMajorRel; i++)
		{
			majRegOptTestCountMap.put(new Integer(AS_IS+(i+1)), new Double(regRelease*(regAutoTestCountMap.get(AS_IS+i)+majRegNewFuncAutoTestCountMap.get(AS_IS+i))/100));
		}
		HashMap<Integer, Double> majRegOptManualTestCountMap = new HashMap<Integer, Double>();
		majRegOptManualTestCountMap.put(new Integer(AS_IS), new Double(majRegNewFuncManualTestCountMap.get(AS_IS).doubleValue()+regManualTestCountMap.get(AS_IS)));
		for(int i=0; i<numMajorRel; i++)
		{
			majRegOptManualTestCountMap.put(new Integer(AS_IS+i+1), new Double(regRelease*(majRegNewFuncManualTestCountMap.get(AS_IS+i+1).doubleValue()+regManualTestCountMap.get(AS_IS+i+1).doubleValue())/100));
		}
		HashMap<Integer, Double> majRegOptAsIsReleaseMap = new HashMap<Integer, Double>();
		majRegOptAsIsReleaseMap.put(new Integer(TEST_DESIGN), new Double(0));
		majRegOptAsIsReleaseMap.put(new Integer(TEST_EXECUTION), new Double(0));
		majRegOptAsIsReleaseMap.put(new Integer(TEST_PLAN_REQMNT_AUTO_ANALYSIS), new Double((majRegOptAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+majRegOptAsIsReleaseMap.get(TEST_EXECUTION).doubleValue())*reqmntAnalysisEff/100));
		majRegOptAsIsReleaseMap.put(new Integer(TEST_MGMT), new Double((majRegOptAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+majRegOptAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+majRegOptAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue())*testMgmtEff/100));
		majRegOptAsIsReleaseMap.put(new Integer(TOTAL_EFFORT), new Double(majRegOptAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+majRegOptAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+majRegOptAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue()+majRegOptAsIsReleaseMap.get(TEST_MGMT).doubleValue()));
		List<MajorRelease> majorRelRegOptList = new ArrayList<MajorRelease>();
		for(int i=0; i<numMajorRel; i++)
		{
			MajorRelease mjrRel = new MajorRelease();
			mjrRel.setTestDesign(0);
			mjrRel.setTestExec((majRegOptManualTestCountMap.get(AS_IS+i+1).doubleValue()/100)*((majRegTestExecCycles*simpleRatio/simpleTestExec)+(mediumRatio/mediumTestExec)+(complexRatio/complexTestExec)));
			mjrRel.setTestPlRqAnAtEx((mjrRel.getTestDesign()*reqmntAnalysisEff/100)+(mjrRel.getTestExec()*autoExecEff/100));
			mjrRel.setTestMgmt((mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx())*testMgmtEff/100);
			mjrRel.setTotalEffort(mjrRel.getTestDesign()+mjrRel.getTestExec()+mjrRel.getTestPlRqAnAtEx()+mjrRel.getTestMgmt());
			majorRelRegOptList.add(mjrRel);
		}
		//Populate data for Major Release - Effective Regression
		//double majNumEffRegAutoTestCase = numAutoTestCase;
		double majEffRegAutoScPlDevMaintEffPerDay = majRegAutoScPlDevMaintEffPerDay;
		double majEffRegAutoMaintEffPerDay = majRegAutoMaintEffPerDay;
		HashMap<Integer, Double> majEffRegTestCountMap = new HashMap<Integer, Double>();
		majEffRegTestCountMap.put(new Integer(AS_IS), new Double(0));
		for(int i=0; i<numMajorRel; i++)
		{
			majEffRegTestCountMap.put(new Integer(AS_IS+(i+1)), new Double(majEffRegTestCountMap.get(AS_IS+i).doubleValue()+regAutoTestCountMap.get(AS_IS+i+1).doubleValue()+majRegNewFuncAutoTestCountMap.get(AS_IS+i+1).doubleValue()-majRegOptTestCountMap.get(AS_IS+i+1).doubleValue()));
		}
		HashMap<Integer, Double> majEffRegManualTestCountMap = new HashMap<Integer, Double>();
		majEffRegManualTestCountMap.put(new Integer(AS_IS), new Double(majRegOptManualTestCountMap.get(AS_IS)));
		for(int i=0; i<numMajorRel; i++)
		{
			majEffRegManualTestCountMap.put(new Integer(AS_IS+i+1), new Double(regManualTestCountMap.get(AS_IS+i+1).doubleValue()+majRegNewFuncManualTestCountMap.get(AS_IS+i+1).doubleValue()-majRegOptManualTestCountMap.get(AS_IS+i+1).doubleValue()));
		}
		HashMap<Integer, Double> majEffRegAsIsReleaseMap = new HashMap<Integer, Double>();
		for(int i=0; i<5; i++)
		{
			majEffRegAsIsReleaseMap.put(new Integer(TEST_DESIGN+i), new Double(majRegAsIsReleaseMap.get(TEST_DESIGN+i).doubleValue()+majRegNewFuncAsIsReleaseMap.get(TEST_DESIGN+i).doubleValue()-majRegOptAsIsReleaseMap.get(TEST_DESIGN+i).doubleValue()));
		}
		List<MajorRelease> majorRelEffRegList = new ArrayList<MajorRelease>();
		for(int i=0; i<numMajorRel; i++)
		{
			MajorRelease mjrRel = new MajorRelease();
			mjrRel.setTestDesign(majorRelRegList.get(i).getTestDesign()+majorRelRegNewFuncList.get(i).getTestDesign()-majorRelRegOptList.get(i).getTestDesign());
			mjrRel.setTestExec(majorRelRegList.get(i).getTestExec()+majorRelRegNewFuncList.get(i).getTestExec()-majorRelRegOptList.get(i).getTestExec());
			mjrRel.setTestPlRqAnAtEx(majorRelRegList.get(i).getTestPlRqAnAtEx()+majorRelRegNewFuncList.get(i).getTestPlRqAnAtEx()-majorRelRegOptList.get(i).getTestPlRqAnAtEx());
			mjrRel.setTestMgmt(majorRelRegList.get(i).getTestMgmt()+majorRelRegNewFuncList.get(i).getTestMgmt()-majorRelRegOptList.get(i).getTestMgmt());
			mjrRel.setTotalEffort(majorRelRegList.get(i).getTotalEffort()+majorRelRegNewFuncList.get(i).getTotalEffort()-majorRelRegOptList.get(i).getTotalEffort());
			majorRelEffRegList.add(mjrRel);
		}
		// Calculate the automation person month effort
		double majAutoScPlDevMaintEffPerDay = majUATAutoScPlDevMaintEffPerDay+majNewFuncAutoScPlDevMaintEffPerDay+majEffRegAutoScPlDevMaintEffPerDay;
		double majAutoMaintEffPerDay = majUATAutoMaintEffPerDay+majNewFuncAutoMaintEffPerDay+majEffRegAutoMaintEffPerDay;
		double autoPersonMonthEff = majAutoScPlDevMaintEffPerDay/20 + majAutoMaintEffPerDay/20;
		HashMap<Integer, Double> majTestCountPerMonthMap = new HashMap<Integer, Double>();
		majTestCountPerMonthMap.put(new Integer(AS_IS), new Double(0));
		for(int i=0; i<numMajorRel; i++)
		{
			majTestCountPerMonthMap.put(new Integer(AS_IS+i+1), new Double(autoPersonMonthEff*tgtDistnList.get(i).getRatio()/100));
		}
		//double sumAsIsTotalEffort = majEffRegAsIsReleaseMap.get(TOTAL_EFFORT)+majNewFuncAsIsReleaseMap.get(TOTAL_EFFORT)+majUATAsIsReleaseMap.get(TOTAL_EFFORT);
		//double autoAsIsPersonMonthEff = sumAsIsTotalEffort/20;
		List<Double> majRelPMTotalEffList = new ArrayList<Double>();
		for(int i=0; i<numMajorRel; i++)
		{
			majRelPMTotalEffList.add((majorRelEffRegList.get(i).getTotalEffort()+majorRelNewFuncList.get(i).getTotalEffort()+majorRelUATList.get(i).getTotalEffort())/20);
		}
		
		/*---------------------------------------------------------- MINOR RELEASE --------------------------------------------------------------------*/
		//Populate data for Minor Release - Regression
		//double minRegNumTestCase = minRegAvgNumTestCase;
		//double minRegPercentAuto = minRegPerAutoDesign;
		//double minRegNumAutoTestCase = minRegAvgNumTestCase*minRegPerAutoDesign;
		//double minRegAutoScPlDevMaintEffPerDay = minRegNumAutoTestCase*(simpleRatio/(100*simpleAutoScript)+(mediumRatio/(100*mediumAutoScript))+(complexRatio/(100*complexAutoScript)));
		//double minRegAutoMaintEffPerDay = minRegAutoScPlDevMaintEffPerDay*autoMaintEff;
		Map<Integer, Map<Integer, Double>> minRegReusableMajRelMap = createReusableTCMap_(majRelDateMap,minRelDateMap, regAutoTestCountMap);
		HashMap<Integer, Double> map = new HashMap<Integer, Double>();
		map.put(1, 0.0);
		map.put(2, 0.0);
		minRegReusableMajRelMap.put(AS_IS, map);
		HashMap<Integer, Double> minRegManualTestCountMap = new HashMap<Integer, Double>();
		minRegManualTestCountMap.put(new Integer(AS_IS), new Double(minRegAvgNumTestCase));
		for(int i=0; i<numMajorRel; i++)
		{
			if(minRegReusableMajRelMap.get(AS_IS+i+1).get(i+i+3) == null)
			{
				testCount += 0;
			}
			else
			{
				testCount += (minRegReusableMajRelMap.get(AS_IS+i+1).get(i+i+3).doubleValue()*autoScriptRelease/100);
			}
			minRegManualTestCountMap.put(new Integer(AS_IS+i+i+1), new Double(minRegAvgNumTestCase-testCount));
			minRegManualTestCountMap.put(new Integer(AS_IS+i+i+2), minRegManualTestCountMap.get(AS_IS+i+i+1));
		}
		testCount = 0;
		HashMap<Integer, Double> minRegAsIsReleaseMap = new HashMap<Integer, Double>();
		minRegAsIsReleaseMap.put(new Integer(TEST_DESIGN), new Double((100-minRegTestDesignReuse)/100)*(((minRegManualTestCountMap.get(AS_IS).doubleValue()*simpleRatio/100)/simpleTestDesign)+((minRegManualTestCountMap.get(AS_IS).doubleValue()*mediumRatio/100)/mediumTestDesign)+((minRegManualTestCountMap.get(AS_IS).doubleValue()*complexRatio/100)/complexTestDesign)));
		minRegAsIsReleaseMap.put(new Integer(TEST_EXECUTION), new Double(minRegManualTestCountMap.get(AS_IS).doubleValue()/100*((minRegAsIsTestExecCycles*simpleRatio/simpleTestExec)+(mediumRatio/mediumTestExec)+(complexRatio/complexTestExec))));
		minRegAsIsReleaseMap.put(new Integer(TEST_PLAN_REQMNT_AUTO_ANALYSIS), new Double((minRegAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+minRegAsIsReleaseMap.get(TEST_EXECUTION).doubleValue())*reqmntAnalysisEff/100));
		minRegAsIsReleaseMap.put(new Integer(TEST_MGMT), new Double((minRegAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+minRegAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+minRegAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue())*testMgmtEff/100));
		minRegAsIsReleaseMap.put(new Integer(TOTAL_EFFORT), new Double(minRegAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+minRegAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+minRegAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue()+minRegAsIsReleaseMap.get(TEST_MGMT).doubleValue()));
		List<MinorRelease> minorRelRegList = new ArrayList<MinorRelease>();
		for(int i=0; i<numMinorRel; i++)
		{
			MinorRelease mirRel = new MinorRelease();
			if(i == 0)
			{
				mirRel.setTestDesign(((100-minRegTestDesignReuse)/100)*((minRegManualTestCountMap.get(AS_IS+1).doubleValue()*simpleRatio/(100*simpleTestDesign))+(minRegManualTestCountMap.get(AS_IS+1).doubleValue()*mediumRatio/(100*mediumTestDesign))+(minRegManualTestCountMap.get(AS_IS+1).doubleValue()*complexRatio/(100*complexTestDesign))));
				mirRel.setTestExec((minRegManualTestCountMap.get(AS_IS+i+1)*((minRegTestExecCycles*simpleRatio/(100*simpleTestExec))+(mediumRatio/(100*mediumTestExec))+(complexRatio/(100*complexTestExec)))));
				mirRel.setTestPlRqAnAtEx((mirRel.getTestDesign()*reqmntAnalysisEff)/100+(mirRel.getTestExec()*autoExecEff)/100);
				mirRel.setTestMgmt((mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx())*testMgmtEff/100);
				mirRel.setTotalEffort(mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx()+mirRel.getTestMgmt());
			}
			else
			{
				mirRel.setTestDesign(0);
				mirRel.setTestExec(minRegManualTestCountMap.get(AS_IS+i+1).doubleValue()*((minRegTestExecCycles*simpleRatio/(100*simpleTestExec))+(mediumRatio/(100*mediumTestExec))+(complexRatio/(100*complexTestExec))));
				mirRel.setTestPlRqAnAtEx(mirRel.getTestExec()*autoExecEff/100);
				mirRel.setTestMgmt((mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx())*testMgmtEff/100);
				mirRel.setTotalEffort(mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx()+mirRel.getTestMgmt());
			}
			minorRelRegList.add(mirRel);
		}
		//Populate data for Minor Release - New Functionality
		double minNumNewFuncAutoTestCase = minNewFuncAvgNumTestCase*minNewFuncPerAutoDesign;
		//double minNewFuncAutoScPlDevMaintEffPerDay = minNumNewFuncAutoTestCase*((simpleRatio/simpleAutoScript)+(mediumRatio/mediumAutoScript)+(complexRatio/complexAutoScript));
		//double minNewFuncAutoMaintEffPerDay = minNewFuncAutoScPlDevMaintEffPerDay*autoMaintEff;
		HashMap<Integer, Double> minNewFuncAutoTestCountMap = new HashMap<Integer, Double>();
		minNewFuncAutoTestCountMap.put(new Integer(AS_IS), new Double(0));
		for(int i=0; i<numMajorRel; i++)
		{
			minNewFuncAutoTestCountMap.put(new Integer(AS_IS+(i+1)), new Double((tgtDistnList.get(i).getRatio()/100)*minNumNewFuncAutoTestCase));
		}
		HashMap<Integer, Double> minNewFuncManualTestCountMap = new HashMap<Integer, Double>();
		minNewFuncManualTestCountMap.put(new Integer(AS_IS), new Double(minNewFuncAvgNumTestCase));
		for(int i=0; i<numMinorRel; i++)
		{
			minNewFuncManualTestCountMap.put(new Integer(AS_IS+i+1), new Double(minNewFuncManualTestCountMap.get(AS_IS+i).doubleValue()));
		}
		testCount = 0;
		HashMap<Integer, Double> minNewFuncAsIsReleaseMap = new HashMap<Integer, Double>();
		minNewFuncAsIsReleaseMap.put(new Integer(TEST_DESIGN), new Double((100-minNewFuncTestDesignReuse)/100)*(((minNewFuncManualTestCountMap.get(AS_IS).doubleValue()*simpleRatio/100)/simpleTestDesign)+((minNewFuncManualTestCountMap.get(AS_IS).doubleValue()*mediumRatio/100)/mediumTestDesign)+((minNewFuncManualTestCountMap.get(AS_IS).doubleValue()*complexRatio/100)/complexTestDesign)));
		minNewFuncAsIsReleaseMap.put(new Integer(TEST_EXECUTION), new Double(minNewFuncManualTestCountMap.get(AS_IS).doubleValue()/100*((minNewFuncAsIsTestExecCycles*simpleRatio/simpleTestExec)+(mediumRatio/mediumTestExec)+(complexRatio/complexTestExec))));
		minNewFuncAsIsReleaseMap.put(new Integer(TEST_PLAN_REQMNT_AUTO_ANALYSIS), new Double((minNewFuncAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+minNewFuncAsIsReleaseMap.get(TEST_EXECUTION).doubleValue())*reqmntAnalysisEff/100));
		minNewFuncAsIsReleaseMap.put(new Integer(TEST_MGMT), new Double((minNewFuncAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+minNewFuncAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+minNewFuncAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue())*testMgmtEff/100));
		minNewFuncAsIsReleaseMap.put(new Integer(TOTAL_EFFORT), new Double(minNewFuncAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+minNewFuncAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+minNewFuncAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue()+minNewFuncAsIsReleaseMap.get(TEST_MGMT).doubleValue()));
		List<MinorRelease> minorRelNewFuncList = new ArrayList<MinorRelease>();
		for(int i=0; i<numMinorRel; i++)
		{
			MinorRelease mirRel = new MinorRelease();
			if(i == 0)
			{
				mirRel.setTestDesign(((100-minNewFuncTestDesignReuse)/100)*(minNewFuncManualTestCountMap.get(AS_IS+i+1).doubleValue()*((simpleRatio/(100*simpleTestDesign))+(mediumRatio/(100*mediumTestDesign))+(complexRatio/(100*complexTestDesign)))));
				mirRel.setTestExec((minNewFuncManualTestCountMap.get(AS_IS+i+1)*((minNewFuncTestExecCycles*simpleRatio/(100*simpleTestExec))+(mediumRatio/(100*mediumTestExec))+(complexRatio/(100*complexTestExec)))));
				mirRel.setTestPlRqAnAtEx((mirRel.getTestDesign()*reqmntAnalysisEff)/100+(mirRel.getTestExec()*autoExecEff)/100);
				mirRel.setTestMgmt((mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx())*testMgmtEff/100);
				mirRel.setTotalEffort(mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx()+mirRel.getTestMgmt());
			}
			else
			{
				mirRel.setTestDesign(((100-minNewFuncTestDesignReuse)/100)*(minNewFuncManualTestCountMap.get(AS_IS+i+1).doubleValue()*((simpleRatio/(100*simpleTestDesign))+(mediumRatio/(100*mediumTestDesign))+(complexRatio/(100*complexTestDesign)))));
				mirRel.setTestExec(minNewFuncManualTestCountMap.get(AS_IS+i+1).doubleValue()*((minNewFuncTestExecCycles*simpleRatio/(100*simpleTestExec))+(mediumRatio/(100*mediumTestExec))+(complexRatio/(100*complexTestExec))));
				mirRel.setTestPlRqAnAtEx((mirRel.getTestDesign()*reqmntAnalysisEff/100)+(mirRel.getTestExec()*autoExecEff/100));
				mirRel.setTestMgmt((mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx())*testMgmtEff/100);
				mirRel.setTotalEffort(mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx()+mirRel.getTestMgmt());
			}
			minorRelNewFuncList.add(mirRel);
		}
		//Populate data for Minor Release - UAT/Prod/Go Live
		double minNumUATAutoTestCase = minUatProdAvgNumTestCase*minUatProdPerAutoDesign;
		//double minUATAutoScPlDevMaintEffPerDay = minNumUATAutoTestCase*(simpleRatio/(100*simpleAutoScript)+(mediumRatio/(100*mediumAutoScript))+(complexRatio/(100*complexAutoScript)));
		//double minUATAutoMaintEffPerDay = minUATAutoScPlDevMaintEffPerDay*autoMaintEff/100;
		HashMap<Integer, Double> minUATAutoTestCountMap = new HashMap<Integer, Double>();
		minUATAutoTestCountMap.put(new Integer(AS_IS), new Double(0));
		for(int i=0; i<numMajorRel; i++)
		{
			minUATAutoTestCountMap.put(new Integer(AS_IS+(i+1)), new Double((tgtDistnList.get(i).getRatio()/100)*minNumUATAutoTestCase));
		}
		HashMap<Integer, Double> minUATManualTestCountMap = new HashMap<Integer, Double>();
		minUATManualTestCountMap.put(new Integer(AS_IS), new Double(minUatProdAvgNumTestCase));
		for(int i=0; i<numMinorRel; i++)
		{
			minUATManualTestCountMap.put(new Integer(AS_IS+i+1), new Double(minUATManualTestCountMap.get(AS_IS+i).doubleValue()));
		}
		HashMap<Integer, Double> minUATAsIsReleaseMap = new HashMap<Integer, Double>();
		minUATAsIsReleaseMap.put(new Integer(TEST_DESIGN), new Double((100-majUatProdTestDesignReuse)/100)*(((minUATManualTestCountMap.get(AS_IS).doubleValue()*simpleRatio/100)/simpleTestDesign)+((minUATManualTestCountMap.get(AS_IS).doubleValue()*mediumRatio/100)/mediumTestDesign)+((minUATManualTestCountMap.get(AS_IS).doubleValue()*complexRatio/100)/complexTestDesign)));
		minUATAsIsReleaseMap.put(new Integer(TEST_EXECUTION), new Double(minUATManualTestCountMap.get(AS_IS).doubleValue()/100*((minUatProdAsIsTestExecCycles*simpleRatio/simpleTestExec)+(mediumRatio/mediumTestExec)+(complexRatio/complexTestExec))));
		minUATAsIsReleaseMap.put(new Integer(TEST_PLAN_REQMNT_AUTO_ANALYSIS), new Double((minUATAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+minUATAsIsReleaseMap.get(TEST_EXECUTION).doubleValue())*reqmntAnalysisEff/100));
		minUATAsIsReleaseMap.put(new Integer(TEST_MGMT), new Double((minUATAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+minUATAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+minUATAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue())*testMgmtEff/100));
		minUATAsIsReleaseMap.put(new Integer(TOTAL_EFFORT), new Double(minUATAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+minUATAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+minUATAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue()+minUATAsIsReleaseMap.get(TEST_MGMT).doubleValue()));
		List<MinorRelease> minorRelUATList = new ArrayList<MinorRelease>();
		for(int i=0; i<numMinorRel; i++)
		{
			MinorRelease mirRel = new MinorRelease();
			if(i == 0)
			{
				mirRel.setTestDesign(((100-minUatProdTestDesignReuse)/100)*(minUATManualTestCountMap.get(AS_IS+i+1).doubleValue()*((simpleRatio/(100*simpleTestDesign))+(mediumRatio/(100*mediumTestDesign))+(complexRatio/(100*complexTestDesign)))));
				mirRel.setTestExec((minUATManualTestCountMap.get(AS_IS+i+1)*((minUatProdTestExecCycles*simpleRatio/(100*simpleTestExec))+(mediumRatio/(100*mediumTestExec))+(complexRatio/(100*complexTestExec)))));
				mirRel.setTestPlRqAnAtEx((mirRel.getTestDesign()*reqmntAnalysisEff)/100+(mirRel.getTestExec()*autoExecEff)/100);
				mirRel.setTestMgmt((mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx())*testMgmtEff/100);
				mirRel.setTotalEffort(mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx()+mirRel.getTestMgmt());
			}
			else
			{
				mirRel.setTestDesign(((100-minUatProdTestDesignReuse)/100)*(minUATManualTestCountMap.get(AS_IS+i+1).doubleValue()*((simpleRatio/(100*simpleTestDesign))+(mediumRatio/(100*mediumTestDesign))+(complexRatio/(100*complexTestDesign)))));
				mirRel.setTestExec(minUATManualTestCountMap.get(AS_IS+i+1).doubleValue()*((minUatProdTestExecCycles*simpleRatio/(100*simpleTestExec))+(mediumRatio/(100*mediumTestExec))+(complexRatio/(100*complexTestExec))));
				mirRel.setTestPlRqAnAtEx((mirRel.getTestDesign()*reqmntAnalysisEff/100)+(mirRel.getTestExec()*autoExecEff/100));
				mirRel.setTestMgmt((mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx())*testMgmtEff/100);
				mirRel.setTotalEffort(mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx()+mirRel.getTestMgmt());
			}
			minorRelUATList.add(mirRel);
		}
		//Populate data for Minor Release - Regression test cases added from New Functionality
		double numMinRegNewFuncTestCase = minNewFuncAvgNumTestCase*newFuncRelease/100;
		//double numMinRegNewFuncAutoTestCase = minNumNewFuncAutoTestCase;
		HashMap<Integer, Double> minRegNewFuncAutoTestCountMap = new HashMap<Integer, Double>();
		minRegNewFuncAutoTestCountMap.put(new Integer(AS_IS), new Double(0));
		for(int i=0; i<numMajorRel; i++)
		{
			minRegNewFuncAutoTestCountMap.put(new Integer(AS_IS+(i+1)), new Double(minNewFuncAutoTestCountMap.get(AS_IS+i)));
		}
		HashMap<Integer, Double> minRegNewFuncManualTestCountMap = new HashMap<Integer, Double>();
		minRegNewFuncManualTestCountMap.put(new Integer(AS_IS), new Double(0));
		minRegNewFuncManualTestCountMap.put(new Integer(AS_IS+1), new Double(numMinRegNewFuncTestCase));
		for(int i=1; i<numMinorRel; i++)
		{
			minRegNewFuncManualTestCountMap.put(new Integer(AS_IS+i+1), minRegNewFuncManualTestCountMap.get(AS_IS+1));
		}
		HashMap<Integer, Double> minRegNewFuncAsIsReleaseMap = new HashMap<Integer, Double>();
		minRegNewFuncAsIsReleaseMap.put(new Integer(TEST_DESIGN), new Double(0));
		minRegNewFuncAsIsReleaseMap.put(new Integer(TEST_EXECUTION), new Double((minRegNewFuncManualTestCountMap.get(AS_IS).doubleValue()/100)*((minRegAsIsTestExecCycles*simpleRatio/simpleTestExec)+(mediumRatio/mediumTestExec)+(complexRatio/complexTestExec))));
		minRegNewFuncAsIsReleaseMap.put(new Integer(TEST_PLAN_REQMNT_AUTO_ANALYSIS), new Double((minRegNewFuncAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+minRegNewFuncAsIsReleaseMap.get(TEST_EXECUTION).doubleValue())*reqmntAnalysisEff));
		minRegNewFuncAsIsReleaseMap.put(new Integer(TEST_MGMT), new Double((minRegNewFuncAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+minRegNewFuncAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+minRegNewFuncAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue())*testMgmtEff));
		minRegNewFuncAsIsReleaseMap.put(new Integer(TOTAL_EFFORT), new Double(minRegNewFuncAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+minRegNewFuncAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+minRegNewFuncAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue()+minRegNewFuncAsIsReleaseMap.get(TEST_MGMT).doubleValue()));
		List<MinorRelease> minorRelRegNewFuncList = new ArrayList<MinorRelease>();
		for(int i=0; i<numMinorRel; i++)
		{
			MinorRelease mirRel = new MinorRelease();
			mirRel.setTestDesign(0);
			mirRel.setTestExec((minRegNewFuncManualTestCountMap.get(AS_IS+i+1).doubleValue()/100)*((minRegTestExecCycles*simpleRatio/simpleTestExec)+(mediumRatio/mediumTestExec)+(complexRatio/complexTestExec)));
			mirRel.setTestPlRqAnAtEx((mirRel.getTestDesign()*reqmntAnalysisEff/100)+(mirRel.getTestExec()*autoExecEff/100));
			mirRel.setTestMgmt((mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx())*testMgmtEff/100);
			mirRel.setTotalEffort(mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx()+mirRel.getTestMgmt());
			minorRelRegNewFuncList.add(mirRel);
		}
		// Populate data for Minor Release - Optimized regression test cases
		HashMap<Integer, Double> minRegOptTestCountMap = new HashMap<Integer, Double>();
		minRegOptTestCountMap.put(new Integer(AS_IS), new Double(0));
		for(int i=0; i<numMajorRel; i++)
		{
			if(minRegReusableMajRelMap.get(AS_IS+i).get(i+i+1) == null)
			{
				minRegOptTestCountMap.put(new Integer(AS_IS+(i+1)), new Double(regRelease*minRegNewFuncAutoTestCountMap.get(AS_IS+i).doubleValue()/100));
			}
			else
			{
				minRegOptTestCountMap.put(new Integer(AS_IS+(i+1)), new Double(regRelease*(minRegReusableMajRelMap.get(AS_IS+i).get(i+i+1).doubleValue()*autoScriptRelease/100+minRegNewFuncAutoTestCountMap.get(AS_IS+i).doubleValue())/100));
			}
		}
		HashMap<Integer, Double> minRegOptManualTestCountMap = new HashMap<Integer, Double>();
		minRegOptManualTestCountMap.put(new Integer(AS_IS), new Double(0));
		for(int i=0; i<numMinorRel; i++)
		{
			minRegOptManualTestCountMap.put(new Integer(AS_IS+i+1), new Double(regRelease*(minRegNewFuncManualTestCountMap.get(AS_IS+i+1).doubleValue()+minRegManualTestCountMap.get(AS_IS+i+1).doubleValue())/100));
		}
		HashMap<Integer, Double> minRegOptAsIsReleaseMap = new HashMap<Integer, Double>();
		minRegOptAsIsReleaseMap.put(new Integer(TEST_DESIGN), new Double(0));
		minRegOptAsIsReleaseMap.put(new Integer(TEST_EXECUTION), new Double(minRegOptManualTestCountMap.get(AS_IS).doubleValue()*((minRegTestExecCycles*simpleRatio/(simpleTestExec*100))+(mediumRatio/(mediumTestExec*100))+(complexRatio/(complexTestExec*100)))));
		minRegOptAsIsReleaseMap.put(new Integer(TEST_PLAN_REQMNT_AUTO_ANALYSIS), new Double((minRegOptAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+minRegOptAsIsReleaseMap.get(TEST_EXECUTION).doubleValue())*reqmntAnalysisEff/100));
		minRegOptAsIsReleaseMap.put(new Integer(TEST_MGMT), new Double((minRegOptAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+minRegOptAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+minRegOptAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue())*testMgmtEff/100));
		minRegOptAsIsReleaseMap.put(new Integer(TOTAL_EFFORT), new Double(minRegOptAsIsReleaseMap.get(TEST_DESIGN).doubleValue()+minRegOptAsIsReleaseMap.get(TEST_EXECUTION).doubleValue()+minRegOptAsIsReleaseMap.get(TEST_PLAN_REQMNT_AUTO_ANALYSIS).doubleValue()+minRegOptAsIsReleaseMap.get(TEST_MGMT).doubleValue()));
		List<MinorRelease> minorRelRegOptList = new ArrayList<MinorRelease>();
		for(int i=0; i<numMinorRel; i++)
		{
			MinorRelease mirRel = new MinorRelease();
			mirRel.setTestDesign(0);
			mirRel.setTestExec((minRegOptManualTestCountMap.get(AS_IS+i+1).doubleValue()/100)*((minRegTestExecCycles*simpleRatio/simpleTestExec)+(mediumRatio/mediumTestExec)+(complexRatio/complexTestExec)));
			mirRel.setTestPlRqAnAtEx((mirRel.getTestDesign()*reqmntAnalysisEff/100)+(mirRel.getTestExec()*autoExecEff/100));
			mirRel.setTestMgmt((mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx())*testMgmtEff/100);
			mirRel.setTotalEffort(mirRel.getTestDesign()+mirRel.getTestExec()+mirRel.getTestPlRqAnAtEx()+mirRel.getTestMgmt());
			minorRelRegOptList.add(mirRel);
		}
		//Populate data for Minor Release - Effective Regression
		//double minNumEffRegAutoTestCase = minRegNumAutoTestCase;
		//double minEffRegAutoScPlDevMaintEffPerDay = minRegAutoScPlDevMaintEffPerDay;
		//double minEffRegAutoMaintEffPerDay = minRegAutoMaintEffPerDay;
		HashMap<Integer, Double> minEffRegTestCountMap = new HashMap<Integer, Double>();
		minEffRegTestCountMap.put(new Integer(AS_IS), new Double(0));
		for(int i=0; i<numMajorRel; i++)
		{
			if(minRegReusableMajRelMap.get(AS_IS+i+1).get(i+i+3) == null)
			{
				minEffRegTestCountMap.put(new Integer(AS_IS+(i+1)), new Double(minEffRegTestCountMap.get(AS_IS+i).doubleValue()+minRegNewFuncAutoTestCountMap.get(AS_IS+i+1).doubleValue()-minRegOptTestCountMap.get(AS_IS+i+1).doubleValue()));
			}
			else
			{
				minEffRegTestCountMap.put(new Integer(AS_IS+(i+1)), new Double(minEffRegTestCountMap.get(AS_IS+i).doubleValue()+minRegReusableMajRelMap.get(AS_IS+i+1).get(i+i+3).doubleValue()*autoScriptRelease/100+minRegNewFuncAutoTestCountMap.get(AS_IS+i+1).doubleValue()-minRegOptTestCountMap.get(AS_IS+i+1).doubleValue()));
			}
			
		}
		HashMap<Integer, Double> minEffRegManualTestCountMap = new HashMap<Integer, Double>();
		for(int i=0; i<=numMinorRel; i++)
		{
			minEffRegManualTestCountMap.put(new Integer(AS_IS+i), new Double(minRegManualTestCountMap.get(AS_IS+i).doubleValue()+minRegNewFuncManualTestCountMap.get(AS_IS+i).doubleValue()-minRegOptManualTestCountMap.get(AS_IS+i).doubleValue()));
		}
		HashMap<Integer, Double> minEffRegAsIsReleaseMap = new HashMap<Integer, Double>();
		for(int i=0; i<5; i++)
		{
			minEffRegAsIsReleaseMap.put(new Integer(TEST_DESIGN+i), new Double(minRegAsIsReleaseMap.get(TEST_DESIGN+i).doubleValue()+minRegNewFuncAsIsReleaseMap.get(TEST_DESIGN+i).doubleValue()-minRegOptAsIsReleaseMap.get(TEST_DESIGN+i).doubleValue()));
		}
		List<MinorRelease> minorRelEffRegList = new ArrayList<MinorRelease>();
		for(int i=0; i<numMinorRel; i++)
		{
			MinorRelease mirRel = new MinorRelease();
			mirRel.setTestDesign(minorRelRegList.get(i).getTestDesign()+minorRelRegNewFuncList.get(i).getTestDesign()-minorRelRegOptList.get(i).getTestDesign());
			mirRel.setTestExec(minorRelRegList.get(i).getTestExec()+minorRelRegNewFuncList.get(i).getTestExec()-minorRelRegOptList.get(i).getTestExec());
			mirRel.setTestPlRqAnAtEx(minorRelRegList.get(i).getTestPlRqAnAtEx()+minorRelRegNewFuncList.get(i).getTestPlRqAnAtEx()-minorRelRegOptList.get(i).getTestPlRqAnAtEx());
			mirRel.setTestMgmt(minorRelRegList.get(i).getTestMgmt()+minorRelRegNewFuncList.get(i).getTestMgmt()-minorRelRegOptList.get(i).getTestMgmt());
			mirRel.setTotalEffort(minorRelRegList.get(i).getTotalEffort()+minorRelRegNewFuncList.get(i).getTotalEffort()-minorRelRegOptList.get(i).getTotalEffort());
			minorRelEffRegList.add(mirRel);
		}
		// Sum up the required fields for output.
		/*------------------------------------------ Major Release ---------------------------------------------------------*/
		//double _sumMajorNumAutoTC = Math.ceil(majNumEffRegAutoTestCase + majNumUATAutoTestCase + majNumNewFuncAutoTestCase); 
		//double _sumMajorASPDAEff = majEffRegAutoScPlDevMaintEffPerDay + majUATAutoScPlDevMaintEffPerDay + majNewFuncAutoScPlDevMaintEffPerDay;
		//double _sumMajorAutoMaintEff = majUATAutoMaintEffPerDay + majNewFuncAutoMaintEffPerDay + majEffRegAutoMaintEffPerDay;
		Map<Integer, Long> _sumMajorAutoTCMap = new HashMap<Integer, Long>();
		for(int i=0; i<=numMajorRel; i++)
		{
			_sumMajorAutoTCMap.put(AS_IS+i, Math.round(majEffRegTestCountMap.get(AS_IS+i)+(majUATAutoTestCountMap.get(AS_IS+i)+(majNewFuncAutoTestCountMap.get(AS_IS+i)))));
		}
		Map<Integer, Long> _sumMajorManualTCCount = new HashMap<Integer, Long>();
		for(int i=0; i<=numMajorRel; i++)
		{
			_sumMajorManualTCCount.put(AS_IS+i, Math.round(majEffRegManualTestCountMap.get(AS_IS+i)+(majUATManualTestCountMap.get(AS_IS+i)+(majNewFuncManualTestCountMap.get(AS_IS+i)))));
		}
		Map<Integer, Double> _sumMajorASISRelMap = new HashMap<Integer, Double>();
		for(int i=0; i<5; i++)
		{
			_sumMajorASISRelMap.put(TEST_DESIGN+i, (majEffRegAsIsReleaseMap.get(TEST_DESIGN+i)+majNewFuncAsIsReleaseMap.get(TEST_DESIGN+i)+majUATAsIsReleaseMap.get(TEST_DESIGN+i)));
		}
		List<MajorRelease> _sumMajRelList = new ArrayList<MajorRelease>();
		for(int i=0; i<numMajorRel; i++)
		{
			MajorRelease mjrRel = new MajorRelease();
			mjrRel.setTestDesign(majorRelNewFuncList.get(i).getTestDesign()+majorRelUATList.get(i).getTestDesign()+majorRelEffRegList.get(i).getTestDesign());
			mjrRel.setTestExec(majorRelNewFuncList.get(i).getTestExec()+majorRelUATList.get(i).getTestExec()+majorRelEffRegList.get(i).getTestExec());
			mjrRel.setTestPlRqAnAtEx(majorRelNewFuncList.get(i).getTestPlRqAnAtEx()+majorRelUATList.get(i).getTestPlRqAnAtEx()+majorRelEffRegList.get(i).getTestPlRqAnAtEx());
			mjrRel.setTestMgmt(majorRelNewFuncList.get(i).getTestMgmt()+majorRelUATList.get(i).getTestMgmt()+majorRelEffRegList.get(i).getTestMgmt());
			mjrRel.setTotalEffort(majorRelNewFuncList.get(i).getTotalEffort()+majorRelUATList.get(i).getTotalEffort()+majorRelEffRegList.get(i).getTotalEffort());
			_sumMajRelList.add(mjrRel);
		}
		/*----------------------------------------------------- MINOR RELEASE ---------------------------------------------------*/
		//double _sumMinorNumAutoTC = Math.ceil(minNumEffRegAutoTestCase + minNumUATAutoTestCase + minNumNewFuncAutoTestCase);
		//double _sumMinorASPDAEff = minEffRegAutoScPlDevMaintEffPerDay + minUATAutoScPlDevMaintEffPerDay + minNewFuncAutoScPlDevMaintEffPerDay;
		//double _sumMinorAutoMaintEff = minUATAutoMaintEffPerDay + minNewFuncAutoMaintEffPerDay + minEffRegAutoMaintEffPerDay;
		Map<Integer, Double> _sumMinorReusableTCMap = new HashMap<Integer, Double>();
		for(int i=0; i<=numMajorRel; i++)
		{
			_sumMinorReusableTCMap.put(AS_IS+i, minEffRegTestCountMap.get(AS_IS+i)+minNewFuncAutoTestCountMap.get(AS_IS+i)+minUATAutoTestCountMap.get(AS_IS+i));
		}
		Map<Integer, Long> _sumMinorManualTCCountMap = new HashMap<Integer, Long>();
		for(int i=0; i<=numMinorRel; i++)
		{
			_sumMinorManualTCCountMap.put(AS_IS+i, Math.round(minEffRegManualTestCountMap.get(AS_IS+i)+(minUATManualTestCountMap.get(AS_IS+i)+(minNewFuncManualTestCountMap.get(AS_IS+i)))));
		}
		Map<Integer, Double> _sumMinorASISRelMap = new HashMap<Integer, Double>();
		for(int i=0; i<5; i++)
		{
			_sumMinorASISRelMap.put(TEST_DESIGN+i, (minEffRegAsIsReleaseMap.get(TEST_DESIGN+i)+minNewFuncAsIsReleaseMap.get(TEST_DESIGN+i)+minUATAsIsReleaseMap.get(TEST_DESIGN+i)));
		}
		List<MinorRelease> _sumMinRelList = new ArrayList<MinorRelease>();
		for(int i=0; i<numMinorRel; i++)
		{
			MinorRelease mirRel = new MinorRelease();
			mirRel.setTestDesign(minorRelNewFuncList.get(i).getTestDesign()+minorRelUATList.get(i).getTestDesign()+minorRelEffRegList.get(i).getTestDesign());
			mirRel.setTestExec(minorRelNewFuncList.get(i).getTestExec()+minorRelUATList.get(i).getTestExec()+minorRelEffRegList.get(i).getTestExec());
			mirRel.setTestPlRqAnAtEx(minorRelNewFuncList.get(i).getTestPlRqAnAtEx()+minorRelUATList.get(i).getTestPlRqAnAtEx()+minorRelEffRegList.get(i).getTestPlRqAnAtEx());
			mirRel.setTestMgmt(minorRelNewFuncList.get(i).getTestMgmt()+minorRelUATList.get(i).getTestMgmt()+minorRelEffRegList.get(i).getTestMgmt());
			mirRel.setTotalEffort(minorRelNewFuncList.get(i).getTotalEffort()+minorRelUATList.get(i).getTotalEffort()+minorRelEffRegList.get(i).getTotalEffort());
			_sumMinRelList.add(mirRel);
		}
		/* Create the output objects and add it to the list to be returned to the form object*/
//		System.out.println("***********************************************************************************************************************");
//		System.out.println("*							OUTPUT 							      *");
//		System.out.println("***********************************************************************************************************************");
//		System.out.println("");
//		System.out.println("YEAR1\t\tRelease#\tNo. of Manual Test Case\tNo. of Automated Test Case\tManual Test Efforts(pm)\tAutomation Dev Effort(pm)");
//		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		List<TaasPrimaryOutput> majListPO = new ArrayList<TaasPrimaryOutput>();
		for(int i=0; i<numMajorRel; i++)
		{
			TaasPrimaryOutput obj = new TaasPrimaryOutput();
			obj.setNumRelease(i+1);
			obj.setNumManualTC(Math.round(_sumMajorManualTCCount.get(AS_IS+i+1)));
			obj.setNumAutoTC(Math.round(_sumMajorAutoTCMap.get(AS_IS+i+1)));
			obj.setManualTestEff(Math.round(majRelPMTotalEffList.get(i)*100.0)/100.0);
			obj.setAutoDevEff(Math.round(autoPersonMonthEff/numMajorRel)*100.0/100.0);
			majListPO.add(obj);
		}
		List<TaasPrimaryOutput> minListPO = new ArrayList<TaasPrimaryOutput>();
		Map<Integer, Double> minorReusableMap = new HashMap<Integer, Double>();
		for(int i=0; i<numMajorRel; i++)
		{
			minorReusableMap.put(i+i, _sumMinorReusableTCMap.get(AS_IS+i+1));
			minorReusableMap.put(i+i+1, _sumMinorReusableTCMap.get(AS_IS+i+1));
		}
		for(int i=0; i<numMinorRel; i++)
		{
			TaasPrimaryOutput obj = new TaasPrimaryOutput();
			obj.setNumRelease(i+1);
			obj.setNumManualTC(_sumMinorManualTCCountMap.get(AS_IS+i+1));
			obj.setNumAutoTC(Math.round(minorReusableMap.get(i)));
			obj.setManualTestEff(Math.round(_sumMinRelList.get(i).getTotalEffort()/20)*100.0/100.0);
			obj.setAutoDevEff(0);
			minListPO.add(obj);
		}
		long _sumNumManualTC = 0, _sumNumAutoTC = 0,  _sumAutoDevEff = 0;
		double _sumManualTestEff = 0;
		for(int i=0; i<numMajorRel; i++)
		{
			_sumNumManualTC += majListPO.get(i).getNumManualTC();
			_sumNumAutoTC += majListPO.get(i).getNumAutoTC();
			_sumManualTestEff += majListPO.get(i).getManualTestEff();
			_sumAutoDevEff += majListPO.get(i).getAutoDevEff();
		}
		for(int i=0; i<numMinorRel; i++)
		{
			_sumNumManualTC += minListPO.get(i).getNumManualTC();
			_sumNumAutoTC += minListPO.get(i).getNumAutoTC();
			_sumManualTestEff += minListPO.get(i).getManualTestEff();
			_sumAutoDevEff += minListPO.get(i).getAutoDevEff();
		}
		TaasPrimaryOutput _sumPrimaryOPObj = new TaasPrimaryOutput();
		_sumPrimaryOPObj.setNumRelease(numMajorRel+numMinorRel);
		_sumPrimaryOPObj.setNumManualTC(_sumNumManualTC);
		_sumPrimaryOPObj.setNumAutoTC(_sumNumAutoTC);
		_sumPrimaryOPObj.setManualTestEff(Math.round(_sumManualTestEff*100.0)/100.0);
		_sumPrimaryOPObj.setAutoDevEff(Math.round(_sumAutoDevEff*100.0)/100.0);
//		for(int i=0; i<numMajorRel; i++)
//		{
//			System.out.println("Major Release\t"+(i+1)+"\t\t\t"+majListPO.get(i).getNumManualTC()+"\t\t\t"+majListPO.get(i).getNumAutoTC()+"\t\t\t"+majListPO.get(i).getManualTestEff()+"\t"+majListPO.get(i).getAutoDevEff());
//		}
//		for(int i=0; i<numMinorRel; i++)
//		{
//			System.out.println("Minor Release\t"+(i+1)+"\t\t\t"+minListPO.get(i).getNumManualTC()+"\t\t\t"+minListPO.get(i).getNumAutoTC()+"\t\t\t"+minListPO.get(i).getManualTestEff()+"\t"+minListPO.get(i).getAutoDevEff());
//		}
//		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
//		System.out.println("Yearly\t\t"+_sumPrimaryOPObj.getNumRelease()+"\t\t\t"+_sumPrimaryOPObj.getNumManualTC()+"\t\t\t"+_sumPrimaryOPObj.getNumAutoTC()+"\t\t\t"+_sumPrimaryOPObj.getManualTestEff()+"\t"+_sumPrimaryOPObj.getAutoDevEff());
//		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
//		System.out.println("");
//		System.out.println("Year#\t\tTotal Effort(pm)/Year\t\tTotal Effort(pd)/Year\t\tTotal Effort(ph)/Year\t\tAvg FTE/Year");
//		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
		List<TaasSecondaryOutput> secondaryOPList = new ArrayList<TaasSecondaryOutput>();
		List<Float> pcEffRedList = new ArrayList<Float>();
		for(TestEffRed obj : testEffRedList)
		{
			Float val = obj.getPercentSecondYear();
			pcEffRedList.add(val);
			val = obj.getPercentThirdYear();
			pcEffRedList.add(val);
			val = obj.getPercentFourthYear();
			pcEffRedList.add(val);
			val = obj.getPercentFifthYear();
			pcEffRedList.add(val);
			val = obj.getPercentSixthYear();
			pcEffRedList.add(val);
			val = obj.getPercentSeventhYear();
			pcEffRedList.add(val);
		}
		for(int i=0; i<=pcEffRedList.size(); i++)
		{
			TaasSecondaryOutput obj = new TaasSecondaryOutput();
			if(i==0)
			{
				obj.setTotalEffPMPY(Math.round(_sumPrimaryOPObj.getManualTestEff()+_sumPrimaryOPObj.getAutoDevEff())*100.0/100.0);
			}
			else
			{
				obj.setTotalEffPMPY(Math.round(secondaryOPList.get(i-1).getTotalEffPMPY()*(100-pcEffRedList.get(i-1))/100)*100.0/100.0);
			}
			obj.setTotalEffPDPY(Math.round(obj.getTotalEffPMPY()*20*100.0)/100.0);
			obj.setTotalEffPHPY(Math.round(obj.getTotalEffPDPY()*8.5*100.0)/100.0);
			obj.setAvgFTEPY(Math.round(obj.getTotalEffPHPY()/2040));
			secondaryOPList.add(obj);
		}
//		for(int i=0; i<=pcEffRedList.size(); i++)
//		{
//			System.out.println("Year"+(i+1)+"\t\t"+secondaryOPList.get(i).getTotalEffPMPY()+"\t\t"+secondaryOPList.get(i).getTotalEffPDPY()+"\t\t"+secondaryOPList.get(i).getTotalEffPHPY()+"\t\t"+secondaryOPList.get(i).getAvgFTEPY());
//		}
//		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		Map<String, List<? extends Object>> outputMap = new HashMap<String, List<? extends Object>>();
		outputMap.put(TAAS_OUTPUT_PRIMARY1, majListPO);
		outputMap.put(TAAS_OUTPUT_PRIMARY2, minListPO);
		outputMap.put(TAAS_OUTPUT_SECONDARY, secondaryOPList);
		List<TaasPrimaryOutput> yearlyPrimaryOP = new ArrayList<TaasPrimaryOutput>();
		yearlyPrimaryOP.add(_sumPrimaryOPObj);
		outputMap.put(TAAS_OUTPUT_YEARLY, yearlyPrimaryOP);
		return outputMap;
	}

	
	private Map<Integer, Map<Integer, Double>> createReusableTCMap_(Map<String, String> majorRelDateMap,
			Map<String, String> minorRelDateMap, HashMap<Integer, Double> regAutoTestCountMap) 
	{
		int minorCount = 0;
		Map<Integer, Map<Integer, Double>> testCountMap = new HashMap<Integer, Map<Integer, Double>>();
		for(int majorCount=1; majorCount<=majorRelDateMap.size(); majorCount++)
		{
			String majorDateStr = majorRelDateMap.get(RELEASE_TYPE_MAJOR+" "+majorCount).substring(0, 10);
			Date majorDate = getDateFromString(majorDateStr);
			String majorDateNextStr = null;
			Date majorNextDate = null;
			if(majorRelDateMap.get(RELEASE_TYPE_MAJOR+" "+(majorCount+1))!=null)
			{	
				majorDateNextStr = majorRelDateMap.get(RELEASE_TYPE_MAJOR+" "+(majorCount+1));
				majorNextDate = getDateFromString(majorDateNextStr);
			}	
			Map<Integer, Double> valueMap = new HashMap<Integer, Double>();
			for(String minorStr:minorRelDateMap.keySet())
			{
				String minorDateStr = minorRelDateMap.get(minorStr).substring(0, 10);
				Date minorDate = getDateFromString(minorDateStr);
				if(minorDate.after(majorDate) && (majorNextDate==null ||minorDate.before(majorNextDate)))
				{	
					valueMap.put(++minorCount+2, regAutoTestCountMap.get(AS_IS+majorCount));
				}
			}
			testCountMap.put(AS_IS+majorCount, valueMap);
		}
		return testCountMap;
	}
	private Date getDateFromString(String strDate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try 
		{
			date = sdf.parse(strDate);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return date;
	}
}
