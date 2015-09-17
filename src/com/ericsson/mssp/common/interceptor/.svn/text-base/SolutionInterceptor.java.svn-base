/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.controller
* File name       		    :  SolutionInterceptor.java
* Description				:	<To Do>
* Author, Date & Release	:	Jan 3, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Jan 3, 2013  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.controller.CreateOpportunityController;

/**
 * @author egaivij
 *
 */


	@Component
	public class SolutionInterceptor extends HandlerInterceptorAdapter implements MSSPConstants
	{
		public static Logger logger = Logger.getLogger(SolutionInterceptor.class);
		 @Override
		 public boolean preHandle(HttpServletRequest request,
		   HttpServletResponse response, Object handler) throws Exception
		 {
			 String uri = request.getRequestURI();
			  if(!uri.endsWith("saveDefineScope")){
				  Integer solutionId= (Integer) request.getSession().getAttribute(SOLUTION_ID);
				  logger.info(" Solution Id in session is :" + solutionId);
				   if(solutionId == null)
				   {
						request.getSession().setAttribute(SOLUTION_NOTEXISTS,ApplicationPropertiesUtil.getProperty("msg.common.solution.notExist")); 
				    response.sendRedirect("../opportunity/defineScope");
				    return false;
				   } else{
					   request.getSession().setAttribute(SOLUTION_NOTEXISTS,null);
				   }
			  }
		  return true;
		 }
	}

