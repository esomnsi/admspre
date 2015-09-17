package com.ericsson.mssp.solution.dao;

import java.util.List;

import com.ericsson.mssp.common.dto.SolutionTestingAsAserviceDTO;
import com.ericsson.mssp.common.entity.EfficiencyLever;
import com.ericsson.mssp.common.entity.GenericTestingInputs;
import com.ericsson.mssp.common.entity.GenericTestingOverhead;
import com.ericsson.mssp.common.entity.RegressionLever;
import com.ericsson.mssp.common.entity.SolutionTestingAsAservice;
import com.ericsson.mssp.common.entity.TestEffReduction;
import com.ericsson.mssp.common.entity.TestingService;
import com.ericsson.mssp.solution.forms.TaasGenericInputForm;
import com.ericsson.mssp.taas.objects.TaasSecondaryOutput;

public interface ITaasDao {

	int updateGenericInputs(TaasGenericInputForm form) throws Exception;

	List<TestingService> getGenericInputList(Integer solutionId);
	List<GenericTestingInputs> getTestingIPList(Integer solutionId);
	List<GenericTestingOverhead> getTestingOHList(Integer solutionId);
	List<EfficiencyLever> getEffLeverValue(Integer solutionId);
	List<RegressionLever> getRegLeverList(Integer solutionId);
	List<TestEffReduction> getTestEffReduction(Integer solutionId);
	List<SolutionTestingAsAservice> getTaasOutput(Integer solutionId);

	void saveGenericTestingIP(List<GenericTestingInputs> genTestInputList);

	void saveGenericTestingOH(List<GenericTestingOverhead> genTestOHList);

	void saveRegLeverValues(List<RegressionLever> regLeverList);

	void saveTestEffRedValues(List<TestEffReduction> testEffRedList);

	void saveEffLeverValues(List<EfficiencyLever> effLeverList);

	void saveOutputData(Integer solutionId, Integer oppScopeID, TaasSecondaryOutput taasSecondaryOutput, SolutionTestingAsAserviceDTO dto);
}
