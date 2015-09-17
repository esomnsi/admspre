package com.ericsson.mssp.solution.service;

import java.util.List;
import java.util.Map;

import com.ericsson.mssp.common.dto.APADTO;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.exception.MSSPException;

public interface APAService {

	public List<OpportunityScope> getOppourtunityScopeList(Integer oppId, Integer solnId) throws MSSPException;
	public void saveAPA(APADTO apaDTO) throws MSSPException;
	public void loadAPA(APADTO apaDTO) throws MSSPException;
	public Map<Long,Integer> createScopeAPACountMap(Integer oppId, Integer solnId) throws MSSPException;
	
}
