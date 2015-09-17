package com.ericsson.mssp.solution.dao;

import com.ericsson.mssp.common.dao.IBaseDAO;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.exception.DAOException;

public interface IOpportunityScopeDAO extends IBaseDAO{

	public OpportunityScope getOpportunityScope(Integer opportunityScopeID) throws DAOException;
}
