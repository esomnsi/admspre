/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.controller
 * File name       		    :  ReviewFTEController.java
 * Description				:	This will be controller for Review FTE page view interactions
 * Author, Date & Release	:	Dec 21, 20122012
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Dec 21, 2012  	   |edassri   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.service.ISolutionService;

/**
 * @author edassri
 * 
 */
@Controller
public class ServiceBucketController extends BaseController implements
	MSSPConstants {

    private static final Logger logger = Logger
	    .getLogger(ServiceBucketController.class);
    private Integer solutionID = 1;
    @Autowired
    private ISolutionService solutionService;

    @RequestMapping("/solution/serviceBucket")
    public String initForm(ModelMap model, HttpSession session)
	    throws MSSPException {
	logger.info("Reached serviceBucket........session:" + session);
	Integer sessSolId = getSessionSolutionId(session);
	if (null != sessSolId) {
	    solutionID = sessSolId;
	}
	Integer opportunityID = null;
	Integer sessOppId = getSessionOpportunityId(session);
	if (null != sessOppId) {
	    opportunityID = sessOppId;
	}

	// Service Bucket table data
	List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(opportunityID, solutionID);
	//Map<String,String> serviceBucketData = solutionService.loadServiceBucketData(opportunityID, solutionID);
	model.addAttribute("serviceBucketData", serviceBucketData);
	logger.debug("serviceBucket Done");
	// return form view
	return "serviceBucket";
    }

}
