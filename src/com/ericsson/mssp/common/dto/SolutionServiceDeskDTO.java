/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  ServiceDeskDTO.java
* Description				:	<To Do>
* Author, Date & Release	:	Jan 23, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Jan 23, 2013  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

import java.io.Serializable;

import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionApproachDimension;
import com.ericsson.mssp.common.entity.SupportWindowMatrix;
import com.ericsson.mssp.common.entity.TouchPointChannel;


/**
 * @author egaivij
 *
 */
public class SolutionServiceDeskDTO implements Serializable{

	    private static final long serialVersionUID = -5591137464178334254L;
	    private Integer serviceDeskId;
	    private SolutionDTO solutionDTO;
	    private TouchPointChannelDTO touchPointChannelDTO;
	    private SupportWindowMatrixDTO supportWindowMatrixDTO;
	    private SolutionApproachDimensionDTO solutionApproachDimensionDTO;
	    private Float transactionsPerMonth;
	    private Float averageHandlingTime;
	    private Float totalTransPerYear;
	    private Float durationOfCallsPerYear;
	    private Float utilizationPerYear;
	    private Float baseServiceDeskFte;
	    private Float augmentedHeads;
		/**
		 * @return the serviceDeskId
		 */
		public Integer getServiceDeskId() {
			return serviceDeskId;
		}
		/**
		 * @param serviceDeskId the serviceDeskId to set
		 */
		public void setServiceDeskId(Integer serviceDeskId) {
			this.serviceDeskId = serviceDeskId;
		}
		
		/**
		 * @return the transactionsPerMonth
		 */
		public Float getTransactionsPerMonth() {
			return transactionsPerMonth;
		}
		/**
		 * @param transactionsPerMonth the transactionsPerMonth to set
		 */
		public void setTransactionsPerMonth(Float transactionsPerMonth) {
			this.transactionsPerMonth = transactionsPerMonth;
		}
		/**
		 * @return the averageHandlingTime
		 */
		public Float getAverageHandlingTime() {
			return averageHandlingTime;
		}
		/**
		 * @param averageHandlingTime the averageHandlingTime to set
		 */
		public void setAverageHandlingTime(Float averageHandlingTime) {
			this.averageHandlingTime = averageHandlingTime;
		}
		/**
		 * @return the totalTransPerYear
		 */
		public Float getTotalTransPerYear() {
			return totalTransPerYear;
		}
		/**
		 * @param totalTransPerYear the totalTransPerYear to set
		 */
		public void setTotalTransPerYear(Float totalTransPerYear) {
			this.totalTransPerYear = totalTransPerYear;
		}
		/**
		 * @return the durationOfCallsPerYear
		 */
		public Float getDurationOfCallsPerYear() {
			return durationOfCallsPerYear;
		}
		/**
		 * @param durationOfCallsPerYear the durationOfCallsPerYear to set
		 */
		public void setDurationOfCallsPerYear(Float durationOfCallsPerYear) {
			this.durationOfCallsPerYear = durationOfCallsPerYear;
		}
		/**
		 * @return the utilizationPerYear
		 */
		public Float getUtilizationPerYear() {
			return utilizationPerYear;
		}
		/**
		 * @param utilizationPerYear the utilizationPerYear to set
		 */
		public void setUtilizationPerYear(Float utilizationPerYear) {
			this.utilizationPerYear = utilizationPerYear;
		}
		/**
		 * @return the baseServiceDeskFte
		 */
		public Float getBaseServiceDeskFte() {
			return baseServiceDeskFte;
		}
		/**
		 * @param baseServiceDeskFte the baseServiceDeskFte to set
		 */
		public void setBaseServiceDeskFte(Float baseServiceDeskFte) {
			this.baseServiceDeskFte = baseServiceDeskFte;
		}
		/**
		 * @return the augmentedHeads
		 */
		public Float getAugmentedHeads() {
			return augmentedHeads;
		}
		/**
		 * @param augmentedHeads the augmentedHeads to set
		 */
		public void setAugmentedHeads(Float augmentedHeads) {
			this.augmentedHeads = augmentedHeads;
		}
		/**
		 * @return the solutionDTO
		 */
		public SolutionDTO getSolutionDTO() {
			return solutionDTO;
		}
		/**
		 * @param solutionDTO the solutionDTO to set
		 */
		public void setSolutionDTO(SolutionDTO solutionDTO) {
			this.solutionDTO = solutionDTO;
		}
		/**
		 * @return the touchPointChannelDTO
		 */
		public TouchPointChannelDTO getTouchPointChannelDTO() {
			return touchPointChannelDTO;
		}
		/**
		 * @param touchPointChannelDTO the touchPointChannelDTO to set
		 */
		public void setTouchPointChannelDTO(TouchPointChannelDTO touchPointChannelDTO) {
			this.touchPointChannelDTO = touchPointChannelDTO;
		}
		/**
		 * @return the supportWindowMatrixDTO
		 */
		public SupportWindowMatrixDTO getSupportWindowMatrixDTO() {
			return supportWindowMatrixDTO;
		}
		/**
		 * @param supportWindowMatrixDTO the supportWindowMatrixDTO to set
		 */
		public void setSupportWindowMatrixDTO(
				SupportWindowMatrixDTO supportWindowMatrixDTO) {
			this.supportWindowMatrixDTO = supportWindowMatrixDTO;
		}
		/**
		 * @return the solutionApproachDimensionDTO
		 */
		public SolutionApproachDimensionDTO getSolutionApproachDimensionDTO() {
			return solutionApproachDimensionDTO;
		}
		/**
		 * @param solutionApproachDimensionDTO the solutionApproachDimensionDTO to set
		 */
		public void setSolutionApproachDimensionDTO(
				SolutionApproachDimensionDTO solutionApproachDimensionDTO) {
			this.solutionApproachDimensionDTO = solutionApproachDimensionDTO;
		}
	
	    
	    
	    

	    
}
