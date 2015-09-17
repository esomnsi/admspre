package com.ericsson.mssp.common.dto;

import java.util.HashSet;
import java.util.Set;

import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.TTJobRoleDistribution;
import com.ericsson.mssp.common.entity.TTPartitionName;
import com.ericsson.mssp.common.entity.TTSummary;

public class TTPlanningDTO {

	 	private Integer ttplanningId;
	    private Solution solution;
	    private Integer noofPartition;
	    private Set<TTSummaryDTO> ttSummaryDTOs = new HashSet<TTSummaryDTO>(0);
	    private Set<TTPartitionNameDTO> ttPartitionNameDTOs = new HashSet<TTPartitionNameDTO>();
	  
	    
	    
	    private Set<TTJobRoleDistributionDTO> ttJobRoleDistributionDTOs = new HashSet<TTJobRoleDistributionDTO>(
		    0);
	    
		public Integer getTtplanningId() {
			return ttplanningId;
		}
		public void setTtplanningId(Integer ttplanningId) {
			this.ttplanningId = ttplanningId;
		}
		public Solution getSolution() {
			return solution;
		}
		public void setSolution(Solution solution) {
			this.solution = solution;
		}
		public Integer getNoofPartition() {
			return noofPartition;
		}
		public void setNoofPartition(Integer noofPartition) {
			this.noofPartition = noofPartition;
		}
		public Set<TTSummaryDTO> getTtSummaryDTOs() {
			return ttSummaryDTOs;
		}
		public void setTtSummaryDTOs(Set<TTSummaryDTO> ttSummaryDTOs) {
			this.ttSummaryDTOs = ttSummaryDTOs;
		}
		public Set<TTPartitionNameDTO> getTtPartitionNameDTOs() {
			return ttPartitionNameDTOs;
		}
		public void setTtPartitionNameDTOs(Set<TTPartitionNameDTO> ttPartitionNameDTOs) {
			this.ttPartitionNameDTOs = ttPartitionNameDTOs;
		}
		public Set<TTJobRoleDistributionDTO> getTtJobRoleDistributionDTOs() {
			return ttJobRoleDistributionDTOs;
		}
		public void setTtJobRoleDistributionDTOs(
				Set<TTJobRoleDistributionDTO> ttJobRoleDistributionDTOs) {
			this.ttJobRoleDistributionDTOs = ttJobRoleDistributionDTOs;
		}
	
		
		
}
