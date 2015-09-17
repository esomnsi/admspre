package com.ericsson.mssp.search.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.dto.CountryDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.SearchDTO;
import com.ericsson.mssp.common.exception.DAOException;

public interface ISearchService {

	public List<CountryDTO> getRegions();
	public List<SearchDTO> getOpportunities(SearchDTO searchDTO);
	public List<SearchDTO> getOpportunitiesToBeReAssigned(SearchDTO searchDTO);
	public String updateOpportunity(SearchDTO searchDTO);
	public boolean deleteSolution(String solId) throws DAOException; 
}
