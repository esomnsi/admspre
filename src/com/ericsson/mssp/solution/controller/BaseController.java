/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.controller
* File name       		    :  BaseController.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 21, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 21, 2012  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ericsson.mssp.common.constant.MSSPConstants;

/**
 * @author egaivij
 *
 */
public class BaseController implements MSSPConstants {
	
	public static Logger logger = Logger.getLogger(BaseController.class);
	
	/**
	 * 
	 * Description	: set the solution id in current session
	 * Method Name	: setSessionSolutionId
	 * Input& Output:
	 * 	@param session
	 * 	@param solId
	 */
	public void setSessionSolutionId(HttpSession session, Integer solId){
		
		session.setAttribute(SOLUTION_ID,solId);
		
	}
	
	/**
	 * 
	 * Description	: return the solution Id from session
	 * Method Name	: getSessionSolutionId
	 * Input& Output:
	 * 	@param session
	 * 	@return
	 */
	public Integer getSessionSolutionId(HttpSession session){
		
		return (Integer)session.getAttribute(SOLUTION_ID);
		
	}
	
	/**
	 * 
	 * Description	: set the solution id in current session
	 * Method Name	: setSessionSolutionId
	 * Input& Output:
	 * 	@param session
	 * 	@param solId
	 */
	public void setSessionOpportunityId(HttpSession session, Integer oppId){
		
		session.setAttribute(OPPORTUNITY_ID,oppId);
		
	}
	
	/**
	 * 
	 * Description	: return the solution Id from session
	 * Method Name	: getSessionSolutionId
	 * Input& Output:
	 * 	@param session
	 * 	@return
	 */
	public Integer getSessionOpportunityId(HttpSession session){
		Integer id = -1;
		
		try{
			id = (Integer)session.getAttribute(OPPORTUNITY_ID);
		}catch(Exception e)
		{
			logger.info("e:" + e+"|id:" + id);
		}
		return id;
		
	}
	/**
	 * 
	 * Description	: return the solution Id from session
	 * Method Name	: getSessionSolutionId
	 * Input& Output:
	 * 	@param session
	 * 	@return
	 */
	public Object getSessionValueFromKey(HttpSession session, String key){
		
		return session.getAttribute(key);
		
	}
	/**
	 * 
	 * Description	: Set the key value from session
	 * Method Name	: setSessionValueFromKey
	 * Input& Output:
	 * 	@param session
	 * 	@return
	 */
	public void setSessionValueByKey(HttpSession session, String key,Object objVal){
		
		session.setAttribute(key,objVal);
		
	}
/*	
	 @ExceptionHandler(Throwable.class)
	    public String handleException(Throwable t) {
	        return "redirect:../login/error";
	    }

	    @ExceptionHandler(Exception.class)
	    public String handleException1(Throwable t) {
	        return "redirect:../login/error";
	    }
	*/
	
}
