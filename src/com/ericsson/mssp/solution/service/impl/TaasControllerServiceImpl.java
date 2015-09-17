package com.ericsson.mssp.solution.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.SolutionTestingAsAserviceDTO;
import com.ericsson.mssp.common.dto.TaasServiceDTO;
import com.ericsson.mssp.common.entity.EfficiencyLever;
import com.ericsson.mssp.common.entity.GenericTestingInputs;
import com.ericsson.mssp.common.entity.GenericTestingOverhead;
import com.ericsson.mssp.common.entity.RegressionLever;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionTestingAsAservice;
import com.ericsson.mssp.common.entity.TestEffReduction;
import com.ericsson.mssp.common.entity.TestingService;
import com.ericsson.mssp.solution.dao.ITaasDao;
import com.ericsson.mssp.solution.forms.TaasForm;
import com.ericsson.mssp.solution.forms.TaasGenericInputForm;
import com.ericsson.mssp.solution.service.ITaasService;
import com.ericsson.mssp.solution.taas.business.TaasBusiness;
import com.ericsson.mssp.taas.dto.EffLeverDTO;
import com.ericsson.mssp.taas.objects.TaasOutput;
import com.ericsson.mssp.taas.objects.TaasPrimaryOutput;
import com.ericsson.mssp.taas.objects.TaasSecondaryOutput;
@Service
public class TaasControllerServiceImpl implements ITaasService, MSSPConstants {
	@Autowired
	private ITaasDao itaasDao;

	@Override
	public int updateGenericInputs(TaasGenericInputForm form) throws Exception{
		//List<TestingService> list  = form.getTestServList();
//		List<TaasServiceDTO> serviceList  = form.getTestServList();
//		List<TestingService> list = createDataList(serviceList);
//		for(TestingService obj : list)
//		{
//			Solution solution = new Solution();
//			solution.setSolutionId(form.getSolution());
//			obj.setSolution(solution);
//		}
		return itaasDao.updateGenericInputs(form);
	}

	private List<TestingService> createDataList(List<TaasServiceDTO> serviceList) {
		List<TestingService> list = new ArrayList<TestingService>();
		TestingService obj;
		for(TaasServiceDTO dtoObj : serviceList)
		{
			obj = new TestingService();
			Solution solution = new Solution();
			solution.setSolutionId(dtoObj.getSolution());
			obj.setSolution(solution);
			obj.setReleaseType(dtoObj.getReleaseType());
			obj.setTestingServiceId(dtoObj.getTestingServiceId());
			obj.setPcdistribution(dtoObj.getPcdistribution());
			obj.setReleaseDate(convertStringToDate(dtoObj.getReleaseDate()));
			list.add(obj);
		}
		return list;
	}

	@Override
	public List<TaasServiceDTO> getGenericInputList(Integer solutionId) {
		List<TestingService> dataList =  itaasDao.getGenericInputList(solutionId);
		List<TaasServiceDTO> dtoList = new ArrayList<TaasServiceDTO>();
		TaasServiceDTO dtoObj = null;
		for(TestingService obj : dataList)
		{
			dtoObj = new TaasServiceDTO();
			dtoObj.setPcdistribution(obj.getPcdistribution());
			dtoObj.setReleaseType(obj.getReleaseType());
			dtoObj.setReleaseDate(convertDateToString(obj.getReleaseDate()));
			dtoObj.setSolution(solutionId);
			dtoObj.setTestingServiceId(obj.getTestingServiceId());
			dtoList.add(dtoObj);
		}
		return dtoList;
	}

	@Override
	public List<GenericTestingInputs> getGenericTestingInputList(Integer solutionId) {
		return itaasDao.getTestingIPList(solutionId);
	}

	@Override
	public List<GenericTestingOverhead> getTestingOverheadList(Integer solutionId) 
	{
		return itaasDao.getTestingOHList(solutionId);
	}

	@Override
	public HashMap<String, Object> getEffLeverValues(Integer solutionId) 
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<EffLeverDTO> majEffLeverList = new ArrayList<EffLeverDTO>();
		List<EffLeverDTO> minEffLeverList = new ArrayList<EffLeverDTO>();
		List<EfficiencyLever> list = itaasDao.getEffLeverValue(solutionId);
		EffLeverDTO dtoObj;
		for(EfficiencyLever effLeverObj : list)
		{
			dtoObj = new EffLeverDTO();
			if(effLeverObj.getReleaseType().equalsIgnoreCase(RELEASE_TYPE_MAJOR))
			{
				if(effLeverObj.getTestType().equalsIgnoreCase(TEST_TYPE_REGRESSION))
				{
					dtoObj.setTestType(effLeverObj.getTestType());
					dtoObj.setEfficiencyLeverId(effLeverObj.getEfficiencyLeverId());
					dtoObj.setReleaseType(effLeverObj.getReleaseType());
					dtoObj.setSolution(effLeverObj.getSolution());
					dtoObj.setAvgNoTestCases(effLeverObj.getAvgNoTestCases());
					dtoObj.setTestDesignReuse(effLeverObj.getTestDesignReuse());
					dtoObj.setTestExecutionCycles(effLeverObj.getTestExecutionCycles());
					dtoObj.setPcautomationDesign(effLeverObj.getPcautomationDesign());
					dtoObj.setAsistestExecutionCycles(effLeverObj.getAsistestExecutionCycles());
					majEffLeverList.add(dtoObj);
				}
				else if(effLeverObj.getTestType().equalsIgnoreCase(TEST_TYPE_NEW_FUNCTIONALITY))
				{
					dtoObj.setTestType(effLeverObj.getTestType());
					dtoObj.setEfficiencyLeverId(effLeverObj.getEfficiencyLeverId());
					dtoObj.setReleaseType(effLeverObj.getReleaseType());
					dtoObj.setSolution(effLeverObj.getSolution());
					dtoObj.setAvgNoTestCases(effLeverObj.getAvgNoTestCases());
					dtoObj.setTestDesignReuse(effLeverObj.getTestDesignReuse());
					dtoObj.setTestExecutionCycles(effLeverObj.getTestExecutionCycles());
					dtoObj.setPcautomationDesign(effLeverObj.getPcautomationDesign());
					dtoObj.setAsistestExecutionCycles(effLeverObj.getAsistestExecutionCycles());
					majEffLeverList.add(dtoObj);
				}
				else if(effLeverObj.getTestType().equalsIgnoreCase(TEST_TYPE_UAT_PROD_GO_LIVE))
				{
					dtoObj.setTestType(effLeverObj.getTestType());
					dtoObj.setEfficiencyLeverId(effLeverObj.getEfficiencyLeverId());
					dtoObj.setReleaseType(effLeverObj.getReleaseType());
					dtoObj.setSolution(effLeverObj.getSolution());
					dtoObj.setAvgNoTestCases(effLeverObj.getAvgNoTestCases());
					dtoObj.setTestDesignReuse(effLeverObj.getTestDesignReuse());
					dtoObj.setTestExecutionCycles(effLeverObj.getTestExecutionCycles());
					dtoObj.setPcautomationDesign(effLeverObj.getPcautomationDesign());
					dtoObj.setAsistestExecutionCycles(effLeverObj.getAsistestExecutionCycles());
					majEffLeverList.add(dtoObj);
				}
			}
			else if(effLeverObj.getReleaseType().equalsIgnoreCase(RELEASE_TYPE_MINOR))
			{
				if(effLeverObj.getTestType().equalsIgnoreCase(TEST_TYPE_REGRESSION))
				{
					dtoObj.setTestType(effLeverObj.getTestType());
					dtoObj.setEfficiencyLeverId(effLeverObj.getEfficiencyLeverId());
					dtoObj.setReleaseType(effLeverObj.getReleaseType());
					dtoObj.setSolution(effLeverObj.getSolution());
					dtoObj.setAvgNoTestCases(effLeverObj.getAvgNoTestCases());
					dtoObj.setTestDesignReuse(effLeverObj.getTestDesignReuse());
					dtoObj.setTestExecutionCycles(effLeverObj.getTestExecutionCycles());
					dtoObj.setPcautomationDesign(effLeverObj.getPcautomationDesign());
					dtoObj.setAsistestExecutionCycles(effLeverObj.getAsistestExecutionCycles());
					minEffLeverList.add(dtoObj);
				}
				else if(effLeverObj.getTestType().equalsIgnoreCase(TEST_TYPE_NEW_FUNCTIONALITY))
				{
					dtoObj.setTestType(effLeverObj.getTestType());
					dtoObj.setEfficiencyLeverId(effLeverObj.getEfficiencyLeverId());
					dtoObj.setReleaseType(effLeverObj.getReleaseType());
					dtoObj.setSolution(effLeverObj.getSolution());
					dtoObj.setAvgNoTestCases(effLeverObj.getAvgNoTestCases());
					dtoObj.setTestDesignReuse(effLeverObj.getTestDesignReuse());
					dtoObj.setTestExecutionCycles(effLeverObj.getTestExecutionCycles());
					dtoObj.setPcautomationDesign(effLeverObj.getPcautomationDesign());
					dtoObj.setAsistestExecutionCycles(effLeverObj.getAsistestExecutionCycles());
					minEffLeverList.add(dtoObj);
				}
				else if(effLeverObj.getTestType().equalsIgnoreCase(TEST_TYPE_UAT_PROD_GO_LIVE))
				{
					dtoObj.setTestType(effLeverObj.getTestType());
					dtoObj.setEfficiencyLeverId(effLeverObj.getEfficiencyLeverId());
					dtoObj.setReleaseType(effLeverObj.getReleaseType());
					dtoObj.setSolution(effLeverObj.getSolution());
					dtoObj.setAvgNoTestCases(effLeverObj.getAvgNoTestCases());
					dtoObj.setTestDesignReuse(effLeverObj.getTestDesignReuse());
					dtoObj.setTestExecutionCycles(effLeverObj.getTestExecutionCycles());
					dtoObj.setPcautomationDesign(effLeverObj.getPcautomationDesign());
					dtoObj.setAsistestExecutionCycles(effLeverObj.getAsistestExecutionCycles());
					minEffLeverList.add(dtoObj);
				}
			}
		}
		map.put(RELEASE_TYPE_MAJOR, majEffLeverList);
		map.put(RELEASE_TYPE_MINOR, minEffLeverList);
		return map;
	}

	@Override
	public List<RegressionLever> getRegLeverValues(Integer solutionId) 
	{
		return itaasDao.getRegLeverList(solutionId);
	}

	@Override
	public List<TestEffReduction> getTestEffRedValues(Integer solutionId) 
	{
//		return itaasDao.getTestEffReduction(solutionId);
		return null;
	}

	@Override
	public void updateTaasDetails(TaasForm form) 
	{
		Solution solution = new Solution();
		solution.setSolutionId(form.getSolution());
		List<GenericTestingInputs> genTestInputList = form.getGenTestInputList();
		for(GenericTestingInputs obj : genTestInputList)
		{
			obj.setSolution(solution);
		}
		List<GenericTestingOverhead> genTestOHList = form.getGenTestOverheadList();
		for(GenericTestingOverhead obj : genTestOHList)
		{
			obj.setSolution(solution);
		}
		List<RegressionLever> regLeverList = form.getRegLeverList();
		for(RegressionLever obj : regLeverList)
		{
			obj.setSolution(solution);
		}
		/*List<TestEffReduction> testEffRedList = form.getTestEffRedList();
		for(TestEffReduction obj : testEffRedList)
		{
			obj.setSolution(solution);
		}*/
		List<EffLeverDTO> majorEffLevList = form.getMajEffLeverList();
		List<EffLeverDTO> minorEffLevList = form.getMinEffLeverList();
		List<EfficiencyLever> effLeverList = new ArrayList<EfficiencyLever>();
		for(EffLeverDTO obj : majorEffLevList)
		{
			EfficiencyLever effLeverObj = new EfficiencyLever();
			while(obj.getTestType().equalsIgnoreCase(TEST_TYPE_REGRESSION))
			{
				effLeverObj.setReleaseType(RELEASE_TYPE_MAJOR);
				effLeverObj.setSolution(solution);
				effLeverObj.setTestType(TEST_TYPE_REGRESSION);
				effLeverObj.setEfficiencyLeverId(obj.getEfficiencyLeverId());
				effLeverObj.setAvgNoTestCases(obj.getAvgNoTestCases());
				effLeverObj.setPcautomationDesign(obj.getPcautomationDesign());
				effLeverObj.setTestDesignReuse(obj.getTestDesignReuse());
				effLeverObj.setTestExecutionCycles(obj.getTestExecutionCycles());
				effLeverObj.setAsistestExecutionCycles(obj.getAsistestExecutionCycles());
				effLeverList.add(effLeverObj);
				break;
			}
			while(obj.getTestType().equalsIgnoreCase(TEST_TYPE_NEW_FUNCTIONALITY))
			{
				effLeverObj.setReleaseType(RELEASE_TYPE_MAJOR);
				effLeverObj.setSolution(solution);
				effLeverObj.setTestType(TEST_TYPE_NEW_FUNCTIONALITY);
				effLeverObj.setEfficiencyLeverId(obj.getEfficiencyLeverId());
				effLeverObj.setAvgNoTestCases(obj.getAvgNoTestCases());
				effLeverObj.setPcautomationDesign(obj.getPcautomationDesign());
				effLeverObj.setTestDesignReuse(obj.getTestDesignReuse());
				effLeverObj.setTestExecutionCycles(obj.getTestExecutionCycles());
				effLeverObj.setAsistestExecutionCycles(obj.getAsistestExecutionCycles());
				effLeverList.add(effLeverObj);
				break;
			}
			while(obj.getTestType().equalsIgnoreCase(TEST_TYPE_UAT_PROD_GO_LIVE))
			{
				effLeverObj.setReleaseType(RELEASE_TYPE_MAJOR);
				effLeverObj.setSolution(solution);
				effLeverObj.setTestType(TEST_TYPE_UAT_PROD_GO_LIVE);
				effLeverObj.setEfficiencyLeverId(obj.getEfficiencyLeverId());
				effLeverObj.setAvgNoTestCases(obj.getAvgNoTestCases());
				effLeverObj.setPcautomationDesign(obj.getPcautomationDesign());
				effLeverObj.setTestDesignReuse(obj.getTestDesignReuse());
				effLeverObj.setTestExecutionCycles(obj.getTestExecutionCycles());
				effLeverObj.setAsistestExecutionCycles(obj.getAsistestExecutionCycles());
				effLeverList.add(effLeverObj);
				break;
			}
		}
		for(EffLeverDTO obj : minorEffLevList)
		{
			EfficiencyLever effLeverObj = new EfficiencyLever();
			while(obj.getTestType().equalsIgnoreCase(TEST_TYPE_REGRESSION))
			{
				effLeverObj.setReleaseType(RELEASE_TYPE_MINOR);
				effLeverObj.setSolution(solution);
				effLeverObj.setTestType(TEST_TYPE_REGRESSION);
				effLeverObj.setEfficiencyLeverId(obj.getEfficiencyLeverId());
				effLeverObj.setAvgNoTestCases(obj.getAvgNoTestCases());
				effLeverObj.setPcautomationDesign(obj.getPcautomationDesign());
				effLeverObj.setTestDesignReuse(obj.getTestDesignReuse());
				effLeverObj.setTestExecutionCycles(obj.getTestExecutionCycles());
				effLeverObj.setAsistestExecutionCycles(obj.getAsistestExecutionCycles());
				effLeverList.add(effLeverObj);
				break;
			}
			while(obj.getTestType().equalsIgnoreCase(TEST_TYPE_NEW_FUNCTIONALITY))
			{
				effLeverObj.setReleaseType(RELEASE_TYPE_MINOR);
				effLeverObj.setSolution(solution);
				effLeverObj.setTestType(TEST_TYPE_NEW_FUNCTIONALITY);
				effLeverObj.setEfficiencyLeverId(obj.getEfficiencyLeverId());
				effLeverObj.setAvgNoTestCases(obj.getAvgNoTestCases());
				effLeverObj.setPcautomationDesign(obj.getPcautomationDesign());
				effLeverObj.setTestDesignReuse(obj.getTestDesignReuse());
				effLeverObj.setTestExecutionCycles(obj.getTestExecutionCycles());
				effLeverObj.setAsistestExecutionCycles(obj.getAsistestExecutionCycles());
				effLeverList.add(effLeverObj);
				break;
			}
			while(obj.getTestType().equalsIgnoreCase(TEST_TYPE_UAT_PROD_GO_LIVE))
			{
				effLeverObj.setReleaseType(RELEASE_TYPE_MINOR);
				effLeverObj.setSolution(solution);
				effLeverObj.setTestType(TEST_TYPE_UAT_PROD_GO_LIVE);
				effLeverObj.setEfficiencyLeverId(obj.getEfficiencyLeverId());
				effLeverObj.setAvgNoTestCases(obj.getAvgNoTestCases());
				effLeverObj.setPcautomationDesign(obj.getPcautomationDesign());
				effLeverObj.setTestDesignReuse(obj.getTestDesignReuse());
				effLeverObj.setTestExecutionCycles(obj.getTestExecutionCycles());
				effLeverObj.setAsistestExecutionCycles(obj.getAsistestExecutionCycles());
				effLeverList.add(effLeverObj);
				break;
			}
		}
		itaasDao.saveGenericTestingIP(genTestInputList);
		itaasDao.saveGenericTestingOH(genTestOHList);
		itaasDao.saveRegLeverValues(regLeverList);
//		itaasDao.saveTestEffRedValues(testEffRedList);
		itaasDao.saveEffLeverValues(effLeverList);
	}

	@Override
	public List<TaasOutput> getMajTaasOPList(Integer solutionId) 
	{
		List<TestingService> testServList = itaasDao.getGenericInputList(solutionId);
		List<GenericTestingInputs> genTestIPList = itaasDao.getTestingIPList(solutionId);
		List<GenericTestingOverhead> genTestOHList = itaasDao.getTestingOHList(solutionId);
		List<RegressionLever> regLeverList = itaasDao.getRegLeverList(solutionId);
		List<EfficiencyLever> effLeverlist = itaasDao.getEffLeverValue(solutionId);
//		List<TestEffReduction> testEffRedList = itaasDao.getTestEffReduction(solutionId);
		/*	Pass the parameters to the business layer that will create the components for calculation*/
		/* Once calculation is done pass the result back to the controller. The result will be rendered through to the jsp*/
		TaasBusiness business = new TaasBusiness(testServList, genTestIPList, genTestOHList, regLeverList, effLeverlist);
		Map<String, List<? extends Object>> output =  business.calculateOutput();
		List<TaasOutput> list = new ArrayList<TaasOutput>();
		TaasOutput obj = new TaasOutput();
		obj.setMajTaasOPList((List<TaasPrimaryOutput>)output.get(TAAS_OUTPUT_PRIMARY1));
		obj.setMinTaasOPList((List<TaasPrimaryOutput>)output.get(TAAS_OUTPUT_PRIMARY2));
		obj.setTaasSecondaryOPList((List<TaasSecondaryOutput>)output.get(TAAS_OUTPUT_SECONDARY));
	//	itaasDao.saveOutputData(obj.getTaasSecondaryOPList().get(0));
		obj.setYearlyOPObj((TaasPrimaryOutput)output.get(TAAS_OUTPUT_YEARLY).get(0));
		list.add(obj);
		return list;
	}
	
	private Date convertStringToDate(String dateStr)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try 
		{
			date = formatter.parse(dateStr);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return date;
	}
	
	private String convertDateToString(Date date)
	{
		if(date == null)
			return null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	@Override
	public List<SolutionTestingAsAserviceDTO> getTaasOutput(Integer solutionId) {
		List<SolutionTestingAsAservice> opList = itaasDao.getTaasOutput(solutionId);
		List<SolutionTestingAsAserviceDTO> list = new ArrayList<SolutionTestingAsAserviceDTO>();
		for(SolutionTestingAsAservice obj : opList)
		{
			SolutionTestingAsAserviceDTO dtoObj = new SolutionTestingAsAserviceDTO();
			dtoObj.setFte(obj.getFte());
			dtoObj.setOpportunityScope(obj.getOpportunityScope());
			dtoObj.setSolution(obj.getSolution());
			dtoObj.setSolutionApproachDimension(obj.getSolutionApproachDimension());
			dtoObj.setSolutionTestingAsAserviceId(obj.getSolutionTestingAsAserviceId());
			dtoObj.setPercentOfServiceElementEffort(obj.getPercentOfServiceElementEffort());
			list.add(dtoObj);
		}
		return list;
	}

	@Override
	public void saveOutput(Integer solutionId, Integer oppScopeID,
			TaasSecondaryOutput obj, SolutionTestingAsAserviceDTO dto) {
		itaasDao.saveOutputData(solutionId, oppScopeID, obj, dto);
	}
}
