/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  SolutionL3OperationsDTO.java
* Description				:	<To Do>
* Author, Date & Release	:	Feb 7, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Feb 7, 2013  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionApproachDimension;
import com.ericsson.mssp.common.entity.SupportWindowMatrix;

/**
 * @author egaivij
 *
 */
public class SolutionL3OperationsDTO {

	  	private Integer solutionL3operationsId;
	    private SolutionDTO solutionDTO;
	    private SupportWindowMatrixDTO supportWindowMatrixDTO;
	    private SolutionApproachDimensionDTO solutionApproachDimensionDTO;
	    private Float pcofL2incidentsConvL3;
	    private Float totalL3bugFixesPerYear;
	    private Float pcsimpleIncidents;
	    private Float pcmediumIncidents;
	    private Float pccomplexIncidents;
	    private Float hrsSimpleIncidents;
	    private Float hrsMediumIncidents;
	    private Float hrsComplexIncidents;
	    private Float avgResolutionTimeHrs;
	    private Float anualHrsSpent;
	    private Float utilizationperYr;
	    private Float baseL3operationFte;
	    private Float augmentedL3operationFte;
		/**
		 * @return the solutionL3operationsId
		 */
		public Integer getSolutionL3operationsId() {
			return solutionL3operationsId;
		}
		/**
		 * @param solutionL3operationsId the solutionL3operationsId to set
		 */
		public void setSolutionL3operationsId(Integer solutionL3operationsId) {
			this.solutionL3operationsId = solutionL3operationsId;
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
		/**
		 * @return the pcofL2incidentsConvL3
		 */
		public Float getPcofL2incidentsConvL3() {
			return pcofL2incidentsConvL3;
		}
		/**
		 * @param pcofL2incidentsConvL3 the pcofL2incidentsConvL3 to set
		 */
		public void setPcofL2incidentsConvL3(Float pcofL2incidentsConvL3) {
			this.pcofL2incidentsConvL3 = pcofL2incidentsConvL3;
		}
		/**
		 * @return the totalL3bugFixesPerYear
		 */
		public Float getTotalL3bugFixesPerYear() {
			return totalL3bugFixesPerYear;
		}
		/**
		 * @param totalL3bugFixesPerYear the totalL3bugFixesPerYear to set
		 */
		public void setTotalL3bugFixesPerYear(Float totalL3bugFixesPerYear) {
			this.totalL3bugFixesPerYear = totalL3bugFixesPerYear;
		}
		/**
		 * @return the pcsimpleIncidents
		 */
		public Float getPcsimpleIncidents() {
			return pcsimpleIncidents;
		}
		/**
		 * @param pcsimpleIncidents the pcsimpleIncidents to set
		 */
		public void setPcsimpleIncidents(Float pcsimpleIncidents) {
			this.pcsimpleIncidents = pcsimpleIncidents;
		}
		/**
		 * @return the pcmediumIncidents
		 */
		public Float getPcmediumIncidents() {
			return pcmediumIncidents;
		}
		/**
		 * @param pcmediumIncidents the pcmediumIncidents to set
		 */
		public void setPcmediumIncidents(Float pcmediumIncidents) {
			this.pcmediumIncidents = pcmediumIncidents;
		}
		/**
		 * @return the pccomplexIncidents
		 */
		public Float getPccomplexIncidents() {
			return pccomplexIncidents;
		}
		/**
		 * @param pccomplexIncidents the pccomplexIncidents to set
		 */
		public void setPccomplexIncidents(Float pccomplexIncidents) {
			this.pccomplexIncidents = pccomplexIncidents;
		}
		/**
		 * @return the hrsSimpleIncidents
		 */
		public Float getHrsSimpleIncidents() {
			return hrsSimpleIncidents;
		}
		/**
		 * @param hrsSimpleIncidents the hrsSimpleIncidents to set
		 */
		public void setHrsSimpleIncidents(Float hrsSimpleIncidents) {
			this.hrsSimpleIncidents = hrsSimpleIncidents;
		}
		/**
		 * @return the hrsMediumIncidents
		 */
		public Float getHrsMediumIncidents() {
			return hrsMediumIncidents;
		}
		/**
		 * @param hrsMediumIncidents the hrsMediumIncidents to set
		 */
		public void setHrsMediumIncidents(Float hrsMediumIncidents) {
			this.hrsMediumIncidents = hrsMediumIncidents;
		}
		/**
		 * @return the hrsComplexIncidents
		 */
		public Float getHrsComplexIncidents() {
			return hrsComplexIncidents;
		}
		/**
		 * @param hrsComplexIncidents the hrsComplexIncidents to set
		 */
		public void setHrsComplexIncidents(Float hrsComplexIncidents) {
			this.hrsComplexIncidents = hrsComplexIncidents;
		}
		/**
		 * @return the avgResolutionTimeHrs
		 */
		public Float getAvgResolutionTimeHrs() {
			return avgResolutionTimeHrs;
		}
		/**
		 * @param avgResolutionTimeHrs the avgResolutionTimeHrs to set
		 */
		public void setAvgResolutionTimeHrs(Float avgResolutionTimeHrs) {
			this.avgResolutionTimeHrs = avgResolutionTimeHrs;
		}
		/**
		 * @return the anualHrsSpent
		 */
		public Float getAnualHrsSpent() {
			return anualHrsSpent;
		}
		/**
		 * @param anualHrsSpent the anualHrsSpent to set
		 */
		public void setAnualHrsSpent(Float anualHrsSpent) {
			this.anualHrsSpent = anualHrsSpent;
		}
		/**
		 * @return the utilizationperYr
		 */
		public Float getUtilizationperYr() {
			return utilizationperYr;
		}
		/**
		 * @param utilizationperYr the utilizationperYr to set
		 */
		public void setUtilizationperYr(Float utilizationperYr) {
			this.utilizationperYr = utilizationperYr;
		}
		/**
		 * @return the baseL3operationFte
		 */
		public Float getBaseL3operationFte() {
			return baseL3operationFte;
		}
		/**
		 * @param baseL3operationFte the baseL3operationFte to set
		 */
		public void setBaseL3operationFte(Float baseL3operationFte) {
			this.baseL3operationFte = baseL3operationFte;
		}
		/**
		 * @return the augmentedL3operationFte
		 */
		public Float getAugmentedL3operationFte() {
			return augmentedL3operationFte;
		}
		/**
		 * @param augmentedL3operationFte the augmentedL3operationFte to set
		 */
		public void setAugmentedL3operationFte(Float augmentedL3operationFte) {
			this.augmentedL3operationFte = augmentedL3operationFte;
		}
	    
	    
}
