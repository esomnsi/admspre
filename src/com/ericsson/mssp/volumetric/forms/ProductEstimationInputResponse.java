package com.ericsson.mssp.volumetric.forms;

import java.util.List;

import com.ericsson.mssp.common.dto.AppDevTicketDistributionDTO;

public class ProductEstimationInputResponse {

	/**
	 * Ticket Distribution List - actual data to be displayed in UI
	 */
	private List<AppDevTicketDistributionDTO> ticketDistributionList;
	/**
	 * Current page of the query
	 */
	private String page;

	/**
	 * Total pages for the query
	 */
	private String total;

	/**
	 * Total number of records for the query
	 */
	private String records;

	
	public List<AppDevTicketDistributionDTO> getTicketDistributionList() {
		return ticketDistributionList;
	}

	public void setTicketDistributionList(
			List<AppDevTicketDistributionDTO> ticketDistributionList) {
		this.ticketDistributionList = ticketDistributionList;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRecords() {
		return records;
	}

	public void setRecords(String records) {
		this.records = records;
	}
}
