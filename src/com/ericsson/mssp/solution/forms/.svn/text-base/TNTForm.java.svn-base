package com.ericsson.mssp.solution.forms;

import java.util.List;

import com.ericsson.mssp.common.dto.TNTDetailDTO;
import com.ericsson.mssp.common.dto.TNTPartitionDateDTO;
/**
 * Form class for TNT
 *
 */
public class TNTForm 
{
	private TNTPartitionDateDTO  partitionDate;
	private List<TNTDetailDTO> tntDetailList;
	private int tntDetailSize;

	private Long solutionId;

	public Long getSolutionId() {
		return solutionId;
	}

	public void setSolutionId(Long solutionId) {
		this.solutionId = solutionId;
	}

	public TNTPartitionDateDTO getPartitionDate() {
		return partitionDate;
	}

	public void setPartitionDate(TNTPartitionDateDTO partitionDate) {
		this.partitionDate = partitionDate;
	}

	public List<TNTDetailDTO> getTntDetailList() {
		return tntDetailList;
	}

	public void setTntDetailList(List<TNTDetailDTO> tntDetailList) {
		this.tntDetailList = tntDetailList;
	}

	public int getTntDetailSize() 
	{
		int size = 0;
		if(tntDetailList != null)
		{
			size = tntDetailList.size();
		}
		return size;
	}

	public void setTntDetailSize(int tntDetailSize) {
		this.tntDetailSize = tntDetailSize;
	}
	
}
