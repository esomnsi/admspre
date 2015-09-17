package com.ericsson.mssp.solution.dao;

import java.util.List;
import java.util.Map;

import org.springframework.util.AutoPopulatingList;

import com.ericsson.mssp.common.dao.IBaseDAO;
import com.ericsson.mssp.common.entity.Apacomplexity;
import com.ericsson.mssp.common.entity.Apaweightage;
import com.ericsson.mssp.common.entity.LocationBreakupDefault;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.ServiceElementJobRoleStages;
import com.ericsson.mssp.common.entity.SolutionAPA;
import com.ericsson.mssp.common.exception.DAOException;

public interface APADAO extends IBaseDAO {
	
	public List<OpportunityScope> getOppourtunityScopeList(Integer oppId, Integer solnId) throws DAOException;
	public Apaweightage getAPAWeightage(Integer solnId) throws DAOException;
	public void saveAPAWeightage(Apaweightage apaWtg) throws DAOException;
	public int deleteAPAWeightage(Integer solnId) throws DAOException;
	public AutoPopulatingList<SolutionAPA> getSolutionAPAList(Integer solnId) throws DAOException;
	public AutoPopulatingList<Apacomplexity> getAPAComplexityList(Integer solnId) throws DAOException;
	public Map<Long,Integer> createScopeAPACountMap(Integer solnId) throws DAOException;
	public void saveSolutionAPA(SolutionAPA solnAPA) throws DAOException;
	public int deleteSolutionAPA(Integer solnId) throws DAOException;	
	public void saveAPAComplexity(Apacomplexity apaComplexity) throws DAOException;
	public int deleteAPAComplexity(Integer solnId) throws DAOException;
	public Integer getServiceElementIdByOppScopeId(Integer oppScopeId) throws DAOException;
	public List<ServiceElementJobRoleStages> getJobRoleByServiceElementId(Integer serviceElementId,
			boolean ccmFLag)throws DAOException;
	public List<LocationBreakupDefault>  loadDefaultValuesByServiceElementId(Integer id);

}
