package com.ericsson.mssp.common.dto;

import java.util.HashSet;
import java.util.Set;

import com.ericsson.mssp.common.entity.TTOnOffRatio;
import com.ericsson.mssp.common.entity.TTPartitionDetail;
import com.ericsson.mssp.common.entity.TTPlanning;
import com.ericsson.mssp.common.entity.TTSummaryStaging;

public class TTPartitionNameDTO {

	 private Integer ttpartitionNameId;
	    private TTPlanningDTO ttPlanningDTO;
	    private String ttpartitionName;
	    private Float ttpartitionFte;
	    private Set<TTPartitionDetailDTO> ttPartitionDetailDTOs = new HashSet<TTPartitionDetailDTO>(
		    0);
	    private Set<TTOnOffRatioDTO> ttOnOffRatioDTOs = new HashSet<TTOnOffRatioDTO>(
			    0);
	    private Set<TTSummaryStagingDTO> ttSummaryStagingDTOs = new HashSet<TTSummaryStagingDTO>(
		    0);
	    
		public Integer getTtpartitionNameId() {
			return ttpartitionNameId;
		}
		public void setTtpartitionNameId(Integer ttpartitionNameId) {
			this.ttpartitionNameId = ttpartitionNameId;
		}
		public String getTtpartitionName() {
			return ttpartitionName;
		}
		public void setTtpartitionName(String ttpartitionName) {
			this.ttpartitionName = ttpartitionName;
		}
		public Float getTtpartitionFte() {
			return ttpartitionFte;
		}
		public void setTtpartitionFte(Float ttpartitionFte) {
			this.ttpartitionFte = ttpartitionFte;
		}
		public TTPlanningDTO getTtPlanningDTO() {
			return ttPlanningDTO;
		}
		public void setTtPlanningDTO(TTPlanningDTO ttPlanningDTO) {
			this.ttPlanningDTO = ttPlanningDTO;
		}
		public Set<TTPartitionDetailDTO> getTtPartitionDetailDTOs() {
			return ttPartitionDetailDTOs;
		}
		public void setTtPartitionDetailDTOs(
				Set<TTPartitionDetailDTO> ttPartitionDetailDTOs) {
			this.ttPartitionDetailDTOs = ttPartitionDetailDTOs;
		}
		public Set<TTSummaryStagingDTO> getTtSummaryStagingDTOs() {
			return ttSummaryStagingDTOs;
		}
		public void setTtSummaryStagingDTOs(
				Set<TTSummaryStagingDTO> ttSummaryStagingDTOs) {
			this.ttSummaryStagingDTOs = ttSummaryStagingDTOs;
		}
		public Set<TTOnOffRatioDTO> getTtOnOffRatioDTOs() {
			return ttOnOffRatioDTOs;
		}
		public void setTtOnOffRatioDTOs(Set<TTOnOffRatioDTO> ttOnOffRatioDTOs) {
			this.ttOnOffRatioDTOs = ttOnOffRatioDTOs;
		}
}
