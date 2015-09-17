package com.ericsson.mssp.solution.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.solution.dao.IOpportunityScopeDAO;

@Repository
public class OpportunityScopeDAOImpl extends BaseDAOImpl implements IOpportunityScopeDAO{

	@SuppressWarnings("unchecked")
	@Override
	public OpportunityScope getOpportunityScope(Integer opportunityScopeID)
			throws DAOException {
		OpportunityScope opportunityScope = null;
		List<OpportunityScope> list = null;
		list = getHibernateTemplate().find(" from OpportunityScope where opportunityScopeId="+ opportunityScopeID );
		if (list!=null && list.size() > 0)
			opportunityScope = list.get(0);
		return opportunityScope;
	}

	
	
}
