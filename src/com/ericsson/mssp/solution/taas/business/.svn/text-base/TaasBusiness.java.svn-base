package com.ericsson.mssp.solution.taas.business;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.entity.EfficiencyLever;
import com.ericsson.mssp.common.entity.GenericTestingInputs;
import com.ericsson.mssp.common.entity.GenericTestingOverhead;
import com.ericsson.mssp.common.entity.RegressionLever;
import com.ericsson.mssp.common.entity.TestingService;
import com.ericsson.mssp.taas.objects.ComplexityFactor;
import com.ericsson.mssp.taas.objects.EffLever;
import com.ericsson.mssp.taas.objects.Effort;
import com.ericsson.mssp.taas.objects.RegLever;
import com.ericsson.mssp.taas.objects.ReleaseCount;
import com.ericsson.mssp.taas.objects.TestEffRed;
import com.ericsson.mssp.taas.objects.TgtDistrn;

public class TaasBusiness implements Serializable, MSSPConstants 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2781998981422577560L;
	
	private Calculation mCalculation;
	private List<ReleaseCount> relCountList;
	private List<TgtDistrn> tgtDistrnList;
	private List<ComplexityFactor> complexityFactorList;
	private List<Effort> effList;
	private List<RegLever> regLevList;
	private List<EffLever> effLeverList;
	private Map<String, String> majRelDateMap;
	private Map<String, String> minRelDateMap;
	private List<TestEffRed> effRedList;
	public TaasBusiness(List<TestingService> testServList,
			List<GenericTestingInputs> genTestIPList,
			List<GenericTestingOverhead> genTestOHList,
			List<RegressionLever> regLeverList,
			List<EfficiencyLever> efficiencyLeverlist) {
		relCountList = new ArrayList<ReleaseCount>();
		tgtDistrnList = new ArrayList<TgtDistrn>();
		majRelDateMap = new HashMap<String, String>();
		minRelDateMap = new HashMap<String, String>();
		int majRelCount = 0, minRelCount = 0;
		String relName = null;
		for(TestingService testServObj : testServList)
		{
			TgtDistrn tgtDistrnObj = new TgtDistrn();
			if(testServObj.getReleaseType().equalsIgnoreCase(RELEASE_TYPE_MAJOR))
			{
				++majRelCount;
				tgtDistrnObj.setRatio(testServObj.getPcdistribution());
				tgtDistrnObj.setReleaseName("Major Release "+majRelCount);
				relName = RELEASE_TYPE_MAJOR+" "+majRelCount;
				majRelDateMap.put(relName, convertDateToString(testServObj.getReleaseDate()));
				tgtDistrnList.add(tgtDistrnObj);
			}
			else if(testServObj.getReleaseType().equalsIgnoreCase(RELEASE_TYPE_MINOR))
			{
				++minRelCount;
				relName = RELEASE_TYPE_MINOR+" "+minRelCount;
				minRelDateMap.put(relName, convertDateToString(testServObj.getReleaseDate()));
			}
		}
		ReleaseCount count = new ReleaseCount();
		count.setNumMajorRel(majRelCount);
		count.setNumMinorRel(minRelCount);
		relCountList.add(count);
		complexityFactorList = new ArrayList<ComplexityFactor>();
		for(GenericTestingInputs genTestIPObj : genTestIPList)
		{
			ComplexityFactor complexFactorObj = new ComplexityFactor();
			complexFactorObj.setFactor(genTestIPObj.getComplexity());
			complexFactorObj.setRatio(genTestIPObj.getRatio());
			complexFactorObj.setTestDesign(genTestIPObj.getTestDesignProductivity());
			complexFactorObj.setTestExec(genTestIPObj.getTestExecutionProductivity());
			complexFactorObj.setAutoScripting(genTestIPObj.getAutomationProductivity());
			complexityFactorList.add(complexFactorObj);
		}
		effList = new ArrayList<Effort>();
		for(GenericTestingOverhead genTestOHObj : genTestOHList)
		{
			Effort effObj = new Effort();
			effObj.setEffTestMgmt(genTestOHObj.getTestMgmtEffort());
			effObj.setEffAutoMaint(genTestOHObj.getAutomationMaintEffort());
			effObj.setEffTestDesign(genTestOHObj.getRatpeffort());
			effObj.setEffAutoExec(genTestOHObj.getAutomationExecEffort());
			effList.add(effObj);
		}
		regLevList = new ArrayList<RegLever>();
		for(RegressionLever regressionLevObj : regLeverList)
		{
			RegLever regLevObj = new RegLever();
			regLevObj.setRegOptRel(regressionLevObj.getPcregression());
			regLevObj.setNewFuncRel(regressionLevObj.getPcnewFunctionality());
			regLevObj.setAutoRegScriptRel(regressionLevObj.getPcautomatedRegression());
			regLevList.add(regLevObj);
		}
		effLeverList = new ArrayList<EffLever>();
		for(EfficiencyLever effLeverObj : efficiencyLeverlist)
		{
			EffLever obj = new EffLever();
			obj.setRelType(effLeverObj.getReleaseType());
			obj.setTestType(effLeverObj.getTestType());
			obj.setAvgNumTestCase(effLeverObj.getAvgNoTestCases());
			obj.setTestDesignReuse(effLeverObj.getTestDesignReuse());
			obj.setTestExecCycles(effLeverObj.getTestExecutionCycles());
			obj.setPercentAutoDesign(effLeverObj.getPcautomationDesign());
			obj.setAsIsTestExecCycles(effLeverObj.getAsistestExecutionCycles());
			effLeverList.add(obj);
		}
		effRedList = new ArrayList<TestEffRed>();
		/* This part needs to be calculated in Solution Lever hence removing all effects of this code*/
		TestEffRed obj = new TestEffRed();
		obj.setPercentSecondYear(0);
		obj.setPercentThirdYear(0);
		obj.setPercentFourthYear(0);
		obj.setPercentFifthYear(0);
		obj.setPercentSixthYear(0);
		obj.setPercentSeventhYear(0);
		effRedList.add(obj);
	}
	
	public Map<String, List<? extends Object>> calculateOutput() 
	{
		mCalculation = new Calculation(complexityFactorList, effList, relCountList, tgtDistrnList, effLeverList, regLevList, effRedList, majRelDateMap, minRelDateMap);
		Map<String, List<? extends Object>> map =  mCalculation.beginCalculation();
		return map;
	}
	
	private String convertDateToString(Date date)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
}
