package com.ericsson.mssp.common.dto;

import java.util.ArrayList;
import java.util.List;

public class LaborCostDTO {
    private int solutionID;
	private List<Row> rowList = new ArrayList<Row>();
	String[] opportunityInterval;

	public String[] getOpportunityInterval() {
		return opportunityInterval;
	}

	public void setOpportunityInterval(String[] opportunityInterval) {
		this.opportunityInterval = opportunityInterval;
	}
	

	public int getSolutionID() {
		return solutionID;
	}

	public void setSolutionID(int solutionID) {
		this.solutionID = solutionID;
	}


	public class Row {

		private int serviceScoprID;
		List<Integer> jobRoleListOnshore= new ArrayList<Integer>();
		List<Integer> jobRoleListOffshore= new ArrayList<Integer>();
		List<String[]> fonshore = new ArrayList<String[]>();
		List<String[]> foffshore = new ArrayList<String[]>();
		public int getServiceScoprID() {
			return serviceScoprID;
		}
		
		public List<Integer> getJobRoleListOnshore() {
			return jobRoleListOnshore;
		}

		public void setJobRoleListOnshore(List<Integer> jobRoleListOnshore) {
			this.jobRoleListOnshore = jobRoleListOnshore;
		}


		public List<Integer> getJobRoleListOffshore() {
			return jobRoleListOffshore;
		}



		public void setJobRoleListOffshore(List<Integer> jobRoleListOffshore) {
			this.jobRoleListOffshore = jobRoleListOffshore;
		}





		public void setServiceScoprID(int serviceScoprID) {
			this.serviceScoprID = serviceScoprID;
		}

		public List<String[]> getFonshore() {
			return fonshore;
		}

		public void setFonshore(List<String[]> fonshore) {
			this.fonshore = fonshore;
		}

		public List<String[]> getFoffshore() {
			return foffshore;
		}

		public void setFoffshore(List<String[]> foffshore) {
			this.foffshore = foffshore;
		}
	}

	public List<Row> getRowList() {
		return rowList;
	}

	public void setRowList(List<Row> rowList) {
		this.rowList = rowList;
	}

}
