package com.ericsson.mssp.search.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ericsson.mssp.common.dto.CountryDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.SearchDTO;
import com.ericsson.mssp.common.entity.Country;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.search.controller.SearchController;
import com.ericsson.mssp.search.service.ISearchService;
import com.ericsson.mssp.solution.dao.ISolutionDAO;


@Service
public class ISearchServiceImpl implements ISearchService{
	
	public static Logger logger = Logger.getLogger(ISearchServiceImpl.class);

	@Autowired
	ISolutionDAO solutionDAO;
	
	@Override
	public List<CountryDTO> getRegions() {
		
		List<CountryDTO> countryDTO = new ArrayList<CountryDTO>();
		List<Country> country = new ArrayList<Country>();
		
		country = solutionDAO.getCountries();
		
		for(int i = 0; i < country.size(); i++) {
		    CountryDTO localCountryDTO = new CountryDTO();
		    localCountryDTO.setCountryId(country.get(i).getCountryId());
		    localCountryDTO.setRegion(country.get(i).getRegion());
		    localCountryDTO.setCurrencyCode(country.get(i).getCurrencyCode());
		    countryDTO.add(localCountryDTO);
		}
		logger.info("region list : " + countryDTO.size());
		return countryDTO;
	}

	@Override
	public List<SearchDTO> getOpportunities(SearchDTO searchDTO) {
		List<SearchDTO> searchDTOs = new ArrayList<SearchDTO>();
		searchDTOs = solutionDAO.getOpportunities(searchDTO);
		return searchDTOs;
	}

	@Override
	public List<SearchDTO> getOpportunitiesToBeReAssigned(SearchDTO searchDTO) {
		List<SearchDTO> searchDTOs = new ArrayList<SearchDTO>();
		searchDTOs = solutionDAO.getOpportunitiesToBeReAssigned(searchDTO);
		return searchDTOs;
	}

	@Override
	public String updateOpportunity(SearchDTO searchDTO) {
		return solutionDAO.updateOpportunity(searchDTO);
	}

	@Override
	public boolean deleteSolution(String solutionId) throws DAOException {
		boolean deleted = solutionDAO.deleteSolution(solutionId);
		return deleted;
	}

}
