/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.myactionapproval.service.impl
* File name       		    :  InitiateApprovalServiceImpl.java
* Description				:	<To Do>
* Author, Date & Release	:	20-Feb-20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| 20-Feb-2013  	   |eshtgar   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.myactionapproval.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.dto.InitiateApprovalProcessDTO;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.login.service.impl.ManageUserAccessServiceImpl;
import com.ericsson.mssp.myactionapproval.dao.IInitiateApprovalProcessDAO;
import com.ericsson.mssp.myactionapproval.service.IInitiateApprovalService;

/**
 * @author eshtgar
 *
 */
@Service
public class InitiateApprovalServiceImpl implements IInitiateApprovalService{
	
	public static Logger logger = Logger.getLogger(InitiateApprovalServiceImpl.class);
	
	@Autowired
	private IInitiateApprovalProcessDAO initiateApprovalProcessDAO;

	@Override
	public List<InitiateApprovalProcessDTO> getApprovalData(HttpSession session) throws MSSPException {
		// TODO Auto-generated method stub
		List<InitiateApprovalProcessDTO> approvalData = new ArrayList<InitiateApprovalProcessDTO>();
		/*System.out.println("Approval Service Impl...");
		List<InitiateApprovalProcessDTO> approvalData = new ArrayList<InitiateApprovalProcessDTO>();
		InitiateApprovalProcessDTO iapd = null;
		List<Object> list = initiateApprovalProcessDAO.inbox(session);
		
		for(int i=0;i<list.size();i++){
			iapd = new InitiateApprovalProcessDTO();
			Object[] obj = (Object[]) list.get(i);			
			iapd.setOpportunityName((String) obj[1]);
			iapd.setSolutionName(obj[2] + "_" + obj[3] + "_" + obj[4]);
			iapd.setSubmittedDate((Timestamp) obj[9]);
			iapd.setSolutionId((Integer) obj[6]);				
						
			approvalData.add(iapd);
		}
		*/
		return approvalData;
	}
	public List<InitiateApprovalProcessDTO> inbox(String approverSignum,String selectedRole) throws DAOException{
		List<InitiateApprovalProcessDTO> sList=initiateApprovalProcessDAO.inbox(approverSignum,selectedRole);
		return sList;
	}
}
