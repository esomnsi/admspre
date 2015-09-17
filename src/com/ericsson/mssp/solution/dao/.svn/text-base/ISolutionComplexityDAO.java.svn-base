package com.ericsson.mssp.solution.dao;

import java.util.List;
import java.util.Map;

import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.entity.Apacomplexity;
import com.ericsson.mssp.common.entity.SolutionComplexity;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.forms.ComplexityAdjusterForm;

public interface ISolutionComplexityDAO {
	
	public ComplexityAdjusterForm loadComplexityAdjusterForm(final Integer solutionId,final SolutionComplexity solutionComplexity,final int OpportunityId,List<ServiceScopeDTO> listOfServiceScope) throws MSSPException;
	
	public SolutionComplexity load(final Integer solutionId,final SolutionComplexity solutionComplexity,final int OpportunityId) throws MSSPException;
	
	public List<Apacomplexity> loadApacomplexity(final Integer solutionId,final int OpportunityId) throws MSSPException;

	public int delete(final long id) throws Exception;

	public int update(final SolutionComplexity solutionComplexity) throws Exception;
	
	public void updateAPA(final List<Apacomplexity> apacomplexityList,final int OpportunityId,final int solutionId) throws Exception;
	
	public Map<String,String> startUpFTEDAOImpl(final SolutionComplexity solutionComplexity,final int OpportunityId,final int solutionId,List<ServiceScopeDTO> listOfServiceScope,final int serviceDeliveryYear) throws Exception;

	public int insert(final SolutionComplexity solutionComplexity) throws Exception;

	public List<SolutionComplexity> queryAll()throws Exception;
	
	public int getServiceDeliveryYearByOpportunityID(final int OpportunityId)throws MSSPException;
}
