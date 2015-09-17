/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  CapacityManagementDTO.java
* Description				:	<To Do>
* Author, Date & Release	:	May 19, 2014 &2014
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| May 19, 2014  	   |ekanpah   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

import java.io.Serializable;


/**
 * @author eanujau
 *
 */
public class CapacityManagementDTO implements Serializable{

	    private static final long serialVersionUID = -5591137464178334254L;
	    private Integer capacityManagementID;
	    private float prepareAndUseModels;
	    private float fteCapacityManagement;
	  
		/**
		 * @return the capacityManagementID
		 */
		public Integer getCapacityManagementID() {
			return capacityManagementID;
		}
		/**
		 * @param capacityManagementID the capacityManagementID to set
		 */
		public void setCapacityManagementID(Integer capacityManagementID) {
			this.capacityManagementID = capacityManagementID;
		}
		public float getPrepareAndUseModels() {
			return prepareAndUseModels;
		}
		public void setPrepareAndUseModels(float prepareAndUseModels) {
			this.prepareAndUseModels = prepareAndUseModels;
		}
		public float getFteCapacityManagement() {
			return fteCapacityManagement;
		}
		public void setFteCapacityManagement(float fteCapacityManagement) {
			this.fteCapacityManagement = fteCapacityManagement;
		}
		
		
}

