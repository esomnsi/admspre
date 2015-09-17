package com.ericsson.mssp.solution.service;

import java.util.HashMap;
import java.util.List;

import com.ericsson.mssp.common.dto.SolutionTestingAsAserviceDTO;
import com.ericsson.mssp.common.dto.TaasServiceDTO;
import com.ericsson.mssp.common.entity.GenericTestingInputs;
import com.ericsson.mssp.common.entity.GenericTestingOverhead;
import com.ericsson.mssp.common.entity.RegressionLever;
import com.ericsson.mssp.common.entity.TestEffReduction;
import com.ericsson.mssp.solution.forms.TaasForm;
import com.ericsson.mssp.solution.forms.TaasGenericInputForm;
import com.ericsson.mssp.taas.objects.TaasOutput;
import com.ericsson.mssp.taas.objects.TaasSecondaryOutput;

public interface ITaasService 
{
	int updateGenericInputs(TaasGenericInputForm form) throws Exception;
	void updateTaasDetails(TaasForm form);
	List<TaasServiceDTO> getGenericInputList(Integer solutionId);
	List<GenericTestingInputs> getGenericTestingInputList(Integer solutionId);
	List<GenericTestingOverhead> getTestingOverheadList(Integer solutionId);
	HashMap<String, Object> getEffLeverValues(Integer solutionId);
	List<RegressionLever> getRegLeverValues(Integer solutionId);
	List<TestEffReduction> getTestEffRedValues(Integer solutionId);
	List<TaasOutput> getMajTaasOPList(Integer solutionId);
	List<SolutionTestingAsAserviceDTO> getTaasOutput(Integer solutionId);
	void saveOutput(Integer solutionId, Integer oppScopeID,
			TaasSecondaryOutput obj, SolutionTestingAsAserviceDTO dto);
}
