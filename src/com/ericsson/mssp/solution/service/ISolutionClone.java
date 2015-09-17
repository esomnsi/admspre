/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.service
* File name       		    :  ISolutionClone.java
* Description				:	<To Do>
* Author, Date & Release	:	Apr 24, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Apr 24, 2013  	   |edassri   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.service;

import com.ericsson.mssp.common.exception.MSSPException;

/**
 * @author edassri
 *
 */
public interface ISolutionClone {

    Integer cloneOfTheSolutionBySolutionIdUserID(Integer solutionID,
	    String userName) throws MSSPException;
 
}
