package com.ericsson.mssp.solution.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.dto.SolutionTestingAsAserviceDTO;
import com.ericsson.mssp.common.dto.TaasServiceDTO;
import com.ericsson.mssp.common.entity.EfficiencyLever;
import com.ericsson.mssp.common.entity.GenericTestingInputs;
import com.ericsson.mssp.common.entity.GenericTestingOverhead;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.RegressionLever;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionTestingAsAservice;
import com.ericsson.mssp.common.entity.TestEffReduction;
import com.ericsson.mssp.common.entity.TestingService;
import com.ericsson.mssp.solution.dao.ITaasDao;
import com.ericsson.mssp.solution.forms.TaasGenericInputForm;
import com.ericsson.mssp.taas.objects.TaasSecondaryOutput;
@Repository
public class TaasDaoImpl extends BaseDAOImpl implements ITaasDao, MSSPConstants {
	
	
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
	public int updateGenericInputs(TaasGenericInputForm form) throws Exception 
	{
		
		List<TaasServiceDTO> serviceList  = form.getTestServList();
		List<TestingService> list = createDataList(serviceList);
		for(TestingService obj : list)
		{
			Solution solution = new Solution();
			solution.setSolutionId(form.getSolution());
			obj.setSolution(solution);
			try
			{
				getHibernateTemplate().saveOrUpdate(obj);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				//throw new Exception();
			}
		}
		
//		List<TaasServiceDTO> dtolist = form.getTestServList();
//		for(TaasServiceDTO dtoObj : dtolist)
//		{
//			TestingService obj = new TestingService();
//			obj.setPcdistribution(dtoObj.getPcdistribution());
//			obj.setReleaseDate(convertStringToDate(dtoObj.getReleaseDate()));
//			obj.setReleaseType(dtoObj.getReleaseType());
//			obj.setTestingServiceId(dtoObj.getTestingServiceId());
//			Solution solution = new Solution();
//			solution.setSolutionId(form.getSolution());
//			obj.setSolution(solution);
////			Solution solution = new Solution();
////			solution.setSolutionId(dtoObj.getSolution());
////			obj.setSolution(solution);
//			try
//			{
//				getHibernateTemplate().saveOrUpdate(obj);
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//				//throw new Exception();
//			}
//		}
		return 0;
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

	@Override
	public List<TestingService> getGenericInputList(Integer solutionId) 
	{
		List<TestingService> listGenericInput = getHibernateTemplate().find("from TestingService where solution="+solutionId);
		if(listGenericInput==null || listGenericInput.size()<=0)
		{
			TestingService service = new TestingService();
			Solution solution = new Solution();
			solution.setSolutionId(solutionId.intValue());
			service.setSolution(solution);
			service.setReleaseDate(null);
			listGenericInput = new ArrayList<TestingService>();
			listGenericInput.add(service);
		}
		return listGenericInput;
	}

	@Override
	public List<GenericTestingInputs> getTestingIPList(Integer solutionId) 
	{
		List<GenericTestingInputs> list = getHibernateTemplate().find("from GenericTestingInputs where solution="+solutionId);
		if(list == null || list.size()<=0)
		{
			Solution solution = new Solution();
			solution.setSolutionId(solutionId.intValue());
			list = new ArrayList<GenericTestingInputs>();
			GenericTestingInputs obj = new GenericTestingInputs();
			obj.setSolution(solution);
			obj.setComplexity(TEST_CASE_TYPE_SIMPLE);
			list.add(obj);
			obj = new GenericTestingInputs();
			obj.setSolution(solution);
			obj.setComplexity(TEST_CASE_TYPE_MEDIUM);
			list.add(obj);
			obj = new GenericTestingInputs();
			obj.setSolution(solution);
			obj.setComplexity(TEST_CASE_TYPE_COMPLEX);
			list.add(obj);
		}
		return list;
	}

	@Override
	public List<GenericTestingOverhead> getTestingOHList(Integer solutionId) 
	{
		List<GenericTestingOverhead> list = getHibernateTemplate().find("from GenericTestingOverhead where solution="+solutionId);
		if(list == null || list.size() <= 0)
		{
			list = new ArrayList<GenericTestingOverhead>();
			GenericTestingOverhead obj = new GenericTestingOverhead();
			Solution solution = new Solution();
			solution.setSolutionId(solutionId.intValue());
			obj.setSolution(solution);
			list.add(obj);
		}
		return list;
	}

	@Override
	public List<EfficiencyLever> getEffLeverValue(Integer solutionId) 
	{
		List<EfficiencyLever> list = getHibernateTemplate().find("from EfficiencyLever where solution="+solutionId);
		if(list == null || list.size() <= 0)
		{
			Solution solution = new Solution();
			solution.setSolutionId(solutionId.intValue());
			EfficiencyLever effLeverObj = new EfficiencyLever();
			effLeverObj.setTestType(TEST_TYPE_REGRESSION);
			effLeverObj.setSolution(solution);
			effLeverObj.setReleaseType(RELEASE_TYPE_MAJOR);
			effLeverObj.setAvgNoTestCases(0.0f);
			effLeverObj.setPcautomationDesign(0.0f);
			effLeverObj.setTestDesignReuse(0.0f);
			effLeverObj.setTestExecutionCycles(0.0f);
			effLeverObj.setAsistestExecutionCycles(0.0f);
			list.add(effLeverObj);
			effLeverObj = new EfficiencyLever();
			effLeverObj.setTestType(TEST_TYPE_NEW_FUNCTIONALITY);
			effLeverObj.setSolution(solution);
			effLeverObj.setReleaseType(RELEASE_TYPE_MAJOR);
			effLeverObj.setAvgNoTestCases(0.0f);
			effLeverObj.setPcautomationDesign(0.0f);
			effLeverObj.setTestDesignReuse(0.0f);
			effLeverObj.setTestExecutionCycles(0.0f);
			effLeverObj.setAsistestExecutionCycles(0.0f);
			list.add(effLeverObj);
			effLeverObj = new EfficiencyLever();
			effLeverObj.setTestType(TEST_TYPE_UAT_PROD_GO_LIVE);
			effLeverObj.setSolution(solution);
			effLeverObj.setReleaseType(RELEASE_TYPE_MAJOR);
			effLeverObj.setAvgNoTestCases(0.0f);
			effLeverObj.setPcautomationDesign(0.0f);
			effLeverObj.setTestDesignReuse(0.0f);
			effLeverObj.setTestExecutionCycles(0.0f);
			effLeverObj.setAsistestExecutionCycles(0.0f);
			list.add(effLeverObj);
			effLeverObj = new EfficiencyLever();
			effLeverObj.setTestType(TEST_TYPE_REGRESSION);
			effLeverObj.setSolution(solution);
			effLeverObj.setReleaseType(RELEASE_TYPE_MINOR);
			effLeverObj.setAvgNoTestCases(0.0f);
			effLeverObj.setPcautomationDesign(0.0f);
			effLeverObj.setTestDesignReuse(0.0f);
			effLeverObj.setTestExecutionCycles(0.0f);
			effLeverObj.setAsistestExecutionCycles(0.0f);
			list.add(effLeverObj);
			effLeverObj = new EfficiencyLever();
			effLeverObj.setTestType(TEST_TYPE_NEW_FUNCTIONALITY);
			effLeverObj.setSolution(solution);
			effLeverObj.setReleaseType(RELEASE_TYPE_MINOR);
			effLeverObj.setAvgNoTestCases(0.0f);
			effLeverObj.setPcautomationDesign(0.0f);
			effLeverObj.setTestDesignReuse(0.0f);
			effLeverObj.setTestExecutionCycles(0.0f);
			effLeverObj.setAsistestExecutionCycles(0.0f);
			list.add(effLeverObj);
			effLeverObj = new EfficiencyLever();
			effLeverObj.setTestType(TEST_TYPE_UAT_PROD_GO_LIVE);
			effLeverObj.setSolution(solution);
			effLeverObj.setReleaseType(RELEASE_TYPE_MINOR);
			effLeverObj.setAvgNoTestCases(0.0f);
			effLeverObj.setPcautomationDesign(0.0f);
			effLeverObj.setTestDesignReuse(0.0f);
			effLeverObj.setTestExecutionCycles(0.0f);
			effLeverObj.setAsistestExecutionCycles(0.0f);
			list.add(effLeverObj);
		}
		return list;
	}

	@Override
	public List<RegressionLever> getRegLeverList(Integer solutionId) 
	{
		List<RegressionLever> list = getHibernateTemplate().find("from RegressionLever where solution="+solutionId);
		if(list == null || list.size() <= 0)
		{
			list = new ArrayList<RegressionLever>();
			RegressionLever obj = new RegressionLever();
			Solution solution = new Solution();
			solution.setSolutionId(solutionId.intValue());
			obj.setSolution(solution);
			list.add(obj);
		}
		return list;
	}

	@Override
	public List<TestEffReduction> getTestEffReduction(Integer solutionId) 
	{
		List<TestEffReduction> list = getHibernateTemplate().find("from TestEffReduction where solution="+solutionId);
		if(list == null || list.size() <= 0)
		{
			list = new ArrayList<TestEffReduction>();
			TestEffReduction obj = new TestEffReduction();
			Solution solution = new Solution();
			solution.setSolutionId(solutionId.intValue());
			obj.setSolution(solution);
			obj.setSecondYear(0.0f);
			obj.setThirdYear(0.0f);
			obj.setFourthYear(0.0f);
			obj.setFifthYear(0.0f);
			obj.setSixthYear(0.0f);
			obj.setSeventhYear(0.0f);
			list.add(obj);
		}
		return list;
	}

	@Override
	public void saveGenericTestingIP(List<GenericTestingInputs> genTestInputList) {
		for(GenericTestingInputs obj : genTestInputList)
		{
			getHibernateTemplate().saveOrUpdate(obj);
		}
	}

	@Override
	public void saveGenericTestingOH(List<GenericTestingOverhead> genTestOHList) {
		for(GenericTestingOverhead obj : genTestOHList)
		{
			getHibernateTemplate().saveOrUpdate(obj);
		}
	}

	@Override
	public void saveRegLeverValues(List<RegressionLever> regLeverList) {
		for(RegressionLever obj : regLeverList)
		{
			getHibernateTemplate().saveOrUpdate(obj);
		}
	}

	@Override
	public void saveTestEffRedValues(List<TestEffReduction> testEffRedList) {
		for(TestEffReduction obj : testEffRedList)
		{
			getHibernateTemplate().saveOrUpdate(obj);
		}
	}

	@Override
	public void saveEffLeverValues(List<EfficiencyLever> effLeverList) {
		for(EfficiencyLever obj : effLeverList)
		{
			getHibernateTemplate().saveOrUpdate(obj);
		}
	}

	@Override
	public List<SolutionTestingAsAservice> getTaasOutput(Integer solutionId) {
		List<SolutionTestingAsAservice> list = getHibernateTemplate().find("from SolutionTestingAsAservice where solution="+solutionId);
		if(list == null || list.size() <= 0)
		{
			list = new ArrayList<SolutionTestingAsAservice>();
			SolutionTestingAsAservice taas = new SolutionTestingAsAservice();
			taas.setFte(0f);
			taas.setOpportunityScope(null);
			Solution solution = new Solution();
			solution.setSolutionId(solutionId);
			taas.setSolution(solution);
			taas.setSolutionApproachDimension(null);
			taas.setPercentOfServiceElementEffort(new Integer(0));
			list.add(taas);
		}
		return list;
	}

	@Override
	public void saveOutputData(Integer solutionId, Integer oppScopeID,
			TaasSecondaryOutput taasSecondaryOutput,
			SolutionTestingAsAserviceDTO dto) {
		Solution solution = new Solution();
		solution.setSolutionId(solutionId);
		OpportunityScope oppScopeObj = new OpportunityScope();
		oppScopeObj.setOpportunityScopeId(oppScopeID);
		SolutionTestingAsAservice taasObj = new SolutionTestingAsAservice();
		taasObj.setSolution(solution);
		taasObj.setOpportunityScope(oppScopeObj);
		taasObj.setFte(new Float(taasSecondaryOutput.getAvgFTEPY()));
		taasObj.setSolutionApproachDimension(null);
		taasObj.setSolutionTestingAsAserviceId(dto.getSolutionTestingAsAserviceId());
		taasObj.setPercentOfServiceElementEffort(dto.getPercentOfServiceElementEffort());
		getHibernateTemplate().saveOrUpdate(taasObj);
	}
	
}
