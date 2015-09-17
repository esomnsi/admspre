/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.myactionapproval.dao.impl
 * File name       		    :  InitiateApprovalProcessDAOImpl.java
 * Description				:	<To Do>
 * Author, Date & Release	:	20-Feb-20132013
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| 20-Feb-2013  	   |eshtgar   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.myactionapproval.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.dto.InitiateApprovalProcessDTO;
import com.ericsson.mssp.common.entity.SolutionApprover;
import com.ericsson.mssp.common.entity.Status;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.myactionapproval.dao.IInitiateApprovalProcessDAO;

/**
 * @author eshtgar
 * 
 */
@Repository
public class InitiateApprovalProcessDAOImpl extends BaseDAOImpl implements
	IInitiateApprovalProcessDAO {

    public static Logger logger = Logger
	    .getLogger(InitiateApprovalProcessDAOImpl.class);

    public String getSolutionNameByOpportunityId(Integer oppId) {
	logger.info("Computing Solution name for SolutionId::: " + oppId);
	String solutionName = null;
	List<Object> list = null;
	Session session = getSession();
	try {
	    String query = "select o.opportunityName,c.customerName,co.countryName,s.solutionId,co.region "
		    + "from Opportunity o,Customer c, Country co, Solution s"
		    + " where co.countryId=c.country.countryId and s.opportunity.opportunityId=o.opportunityId and  o.customer.customerId=c.customerId and o.opportunityId="
		    + oppId;

	    list = session.createQuery(query).list();
	    if (list.size() == 0) {
		solutionName = "--";
	    }
	    for (int i = 0; i < list.size(); i++) {
		Object[] obj = (Object[]) list.get(0);
		for (int j = 0; j < obj.length; j++) {
		    logger.info(obj[0] + "_" + obj[1] + "_" + obj[2] + "_"
			    + obj[3]);
		    solutionName = obj[0] + "_" + obj[1] + "_" + obj[2] + "_"
			    + obj[3];
		}
	    }
	} finally {
	    if (session != null) {
		session.close();
	    }
	}
	logger.debug(list);

	return solutionName;
    }

    public List<InitiateApprovalProcessDTO> inbox(String approverSignum,
	    String selectedRole) throws DAOException {
	List<InitiateApprovalProcessDTO> inboxSolutionList = new ArrayList<InitiateApprovalProcessDTO>();
	Session session = getSession();

	if (selectedRole.equalsIgnoreCase("ROLE_APPROVER")
		|| selectedRole.equalsIgnoreCase("ROLE_SUPER_USER")) {

	    String hql = " from SolutionApprover t1  "
		    + " where t1.approver='"
		    + approverSignum
		    + "'"
		    + "	and t1.activeId=1 "
		    + " and t1.solution.status.statusId=2"
		    + "	and t1.solutionApproverId NOT IN (select t2.solutionApprover.solutionApproverId "
		    + "	from SolutionApprovalStatus t2  where t2.solutionApprover.solutionApproverId=t1.solutionApproverId) "
		    + "	order by t1.sequenceId ASC ";

	    List<SolutionApprover> approverList = session.createQuery(hql)
		    .list();
	    SolutionApprover temp = null;
	    for (int i = 0; i < approverList.size(); i++) {
		SolutionApprover eachApprover = approverList.get(i);
		if (eachApprover.getSolutionApprovalStatuses() == null
			|| eachApprover.getSolutionApprovalStatuses().size() == 0) {
		    List<SolutionApprover> prevSeqApproverList = session
			    .createQuery(
				    "from SolutionApprover t where t.solution.solutionId="
					    + eachApprover.getSolution()
						    .getSolutionId()
					    + " and t.sequenceId="
					    + (eachApprover.getSequenceId() - 1)
					    + "order by t.solutionApproverId desc")
			    .list();
		    if (prevSeqApproverList != null
			    && prevSeqApproverList.size() > 0) {
			SolutionApprover prevApprover = prevSeqApproverList
				.get(0);
			if (prevApprover.getActiveId()) {
			    if (!(prevApprover.getSolutionApprovalStatuses() == null || prevApprover
				    .getSolutionApprovalStatuses().size() == 0)) {
				// prevSequenceApprover has approved
				InitiateApprovalProcessDTO dto = new InitiateApprovalProcessDTO();
				dto.setOpportunityId(eachApprover.getSolution()
					.getOpportunity().getOpportunityId());
				dto.setOpportunityName(eachApprover
					.getSolution().getOpportunity()
					.getOpportunityName());
				dto.setSolutionId(eachApprover.getSolution()
					.getSolutionId());
				dto.setSolutionName(getSolutionNameByOpportunityId(eachApprover
					.getSolution().getOpportunity()
					.getOpportunityId()));
				dto.setSubmittedDate(eachApprover
					.getRequestDate());
				dto.setStatusName(eachApprover.getSolution()
					.getStatus().getStatusName());
				Map<String, String> map = getSolutionDetailsByOpportunityId(eachApprover
					.getSolution().getOpportunity()
					.getOpportunityId());
				dto.setCountry(map.get("Country"));
				dto.setCustomer(map.get("Customer"));
				dto.setRegion(map.get("Region"));
				inboxSolutionList.add(dto);
			    } else {
				// prevSequenceApprover has not yet
				// approved/rejected
			    }
			} else {
			    // No Action. PrevSeqApprover has rejected hence no
			    // notification at inbox of next seq approver.
			}
		    } else {
			// prev approver is null hence current approver should
			// approve
			InitiateApprovalProcessDTO dto = new InitiateApprovalProcessDTO();
			dto.setOpportunityId(eachApprover.getSolution()
				.getOpportunity().getOpportunityId());
			dto.setOpportunityName(eachApprover.getSolution()
				.getOpportunity().getOpportunityName());
			dto.setSolutionId(eachApprover.getSolution()
				.getSolutionId());
			dto.setSolutionName(getSolutionNameByOpportunityId(eachApprover
				.getSolution().getOpportunity()
				.getOpportunityId()));
			dto.setSubmittedDate(eachApprover.getRequestDate());
			dto.setStatusName(eachApprover.getSolution()
				.getStatus().getStatusName());
			Map<String, String> map = getSolutionDetailsByOpportunityId(eachApprover
				.getSolution().getOpportunity()
				.getOpportunityId());
			dto.setCountry(map.get("Country"));
			dto.setCustomer(map.get("Customer"));
			dto.setRegion(map.get("Region"));
			inboxSolutionList.add(dto);
		    }
		}
	    }
	} else if (selectedRole.equalsIgnoreCase("ROLE_SOLUTION_MANAGER")) {
	    logger.info("Inbox initiated with role SOLUTION MANAGER");

	    String query = "select o.OpportunityID,o.OpportunityName,o.CustomerID, s.SolutionID,s.statusId, o.assignedTo,o.CreatedBy from Opportunity o left join Solution s on "
		    + " o.OpportunityID=s.OpportunityID where s.SolutionID is null and s.statusId NOT IN (5) and o.assignedTo="
		    + "'"
		    + approverSignum
		    + "' "
		    + "UNION"
		    + " select o.OpportunityID,o.OpportunityName,o.CustomerID, s.SolutionID,s.statusId, o.assignedTo,o.CreatedBy from Opportunity o, Solution s "
		    + "where o.OpportunityID=s.OpportunityID and s.StatusID=1 and s.statusId NOT IN (5) and o.assignedTo="
		    + "'" + approverSignum + "' ";

	    List<Object> approverList = session.createSQLQuery(query).list();
	    logger.debug(approverList);
	    InitiateApprovalProcessDTO dto = null;
	    int solutionId = 0;

	    for (int i = 0; i < approverList.size(); i++) {
		dto = new InitiateApprovalProcessDTO();

		Object[] o = (Object[]) approverList.get(i);
		dto.setOpportunityId((Integer) o[0]);
		solutionId = o[3] != null ? (Integer) o[3] : solutionId;
		dto.setSolutionId(solutionId);
		dto.setOpportunityName((String) o[1]);
		dto.setSolutionName(getSolutionNameByOpportunityId((Integer) o[0]));
		Status s = (Status) getObject(Status.class, (Serializable) o[4]);

		dto.setStatusName(s.getStatusName());
		dto.setCreatedBy((String) o[5]);

		Map<String, String> map = getSolutionDetailsByOpportunityId((Integer) o[0]);
		dto.setCountry(map.get("Country"));
		dto.setCustomer(map.get("Customer"));
		dto.setRegion(map.get("Region"));
		inboxSolutionList.add(dto);
	    }

	}

	session.close();
	return inboxSolutionList;
    }

    public Map<String, String> getSolutionDetailsByOpportunityId(Integer oppId) {
	Map<String, String> map = new HashMap<String, String>();
	logger.info("Computing Solution Details for OppId::: " + oppId);
	String solutionName = null;
	Session session = null;
	List<Object> list = null;
	try {
	    session = getSession();
	    String query = "select o.opportunityName,c.customerName,co.countryName,s.solutionId,co.region "
		    + "from Opportunity o,Customer c, Country co, Solution s"
		    + " where co.countryId=c.country.countryId and s.opportunity.opportunityId=o.opportunityId and  o.customer.customerId=c.customerId and o.opportunityId="
		    + oppId;

	    list = session.createQuery(query).list();
	    if (list.size() == 0) {
		map.put("Country", "--");
		map.put("Customer", "--");
		map.put("Region", "--");
	    }

	    for (int i = 0; i < list.size(); i++) {
		Object[] obj = (Object[]) list.get(0);
		for (int j = 0; j < obj.length; j++) {
		    logger.info(obj[0] + "_" + obj[1] + "_" + obj[2] + "_"
			    + obj[3]);
		    map.put("Customer", (String) obj[1]);
		    map.put("Country", (String) obj[2]);
		    map.put("Region", (String) obj[4]);
		}
	    }
	} finally {
	    if (session != null) {
		session.close();
	    }
	}
	logger.debug(list);

	return map;
    }

}
