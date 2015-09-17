/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.myactionapproval.service
* File name       		    :  IInitiateApproval.java
* Description				:	<To Do>
* Author, Date & Release	:	20-Feb-20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| 20-Feb-2013  	   |eshtgar   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.myactionapproval.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ericsson.mssp.common.dto.InitiateApprovalProcessDTO;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.exception.MSSPException;

/**
 * @author eshtgar
 *
 */
public interface IInitiateApprovalService {
		public List<InitiateApprovalProcessDTO> getApprovalData(HttpSession session) throws MSSPException;
		public List<InitiateApprovalProcessDTO> inbox(String approverSignum,String selectedRole) throws DAOException;
}
