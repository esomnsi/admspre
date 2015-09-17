/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.dao.impl
* File name       		    :  ReportDAOImpl.java
* Description				:	<To Do>
* Author, Date & Release	:	Jan 10, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Jan 10, 2013  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.dto.FTEStagingDTO;
import com.ericsson.mssp.common.dto.JobRoleStagesDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.SolutionADRDTO;
import com.ericsson.mssp.common.dto.SolutionDTO;
import com.ericsson.mssp.common.entity.FTEStaging;
import com.ericsson.mssp.common.entity.ServiceScope;
import com.ericsson.mssp.common.entity.SolutionADR;
import com.ericsson.mssp.solution.dao.IReportDAO;

/**
 * @author eruvwyn
 *
 */
@Repository
public class ReportDAOImpl extends BaseDAOImpl implements IReportDAO {

	@Override
	public List<ServiceScope> getAllServiceScope() {
		return(getHibernateTemplate().find("from  ServiceScope"));
		
	}

	@Override
	public List<Object[]> getAllFTEStagingDetailBySolId(Integer solId,  String location, String subLocation) {
		String query ;
		//pass sublocation in query also
		if(null!=subLocation){
			query= "select f.opportunityScope.opportunityScopeId,  DATE_FORMAT(monthYear,'%m-%Y') as MonthYear , SUM(ftecount) as FTECount , f.opportunityScope.serviceScope.serviceScopeId,f.subLocation" +
					" from FTEStaging f"+ 
					" where solutionId="+solId+" and subLocation='"+subLocation+"' and location='"+location+"' GROUP BY DATE_FORMAT(monthYear,'%Y'), f.opportunityScope.opportunityScopeId order by  f.opportunityScope.serviceScope.serviceScopeId" ;
		}
		else{
		 query = "select f.opportunityScope.opportunityScopeId,  DATE_FORMAT(monthYear,'%m-%Y') as MonthYear , SUM(ftecount) as FTECount , f.opportunityScope.serviceScope.serviceScopeId,f.subLocation" +
				" from FTEStaging f"+ 
				" where solutionId="+solId+" and location='"+location+"' GROUP BY DATE_FORMAT(monthYear,'%Y'), f.opportunityScope.opportunityScopeId order by  f.opportunityScope.serviceScope.serviceScopeId" ;
		}
		System.out.println(query);
		List<Object[]>fteStagingList = getHibernateTemplate().find(query);
		
		return fteStagingList;
		
	}
	

	@Override
	public List<SolutionADRDTO> getADRReportDetails(Integer solutionID, String adrCategory) {
		String query = "from SolutionADR adr where adr.solution.solutionId="
				+ solutionID +" and adr.adrcategory='"+ adrCategory+"'" ;
	
		List<SolutionADR> solAdrList = getHibernateTemplate().find(query);
		
		List<SolutionADRDTO> solAdrDtoList = new ArrayList<SolutionADRDTO>();
		
		for(SolutionADR solAdrItem:solAdrList){
			SolutionADRDTO solAdrDto = new SolutionADRDTO();
			solAdrDto.setSolutionId(solAdrItem.getSolution().getSolutionId());
			solAdrDto.setAdrCategory(solAdrItem.getAdrcategory());
			solAdrDto.setAdrStatement(solAdrItem.getAdrstatement());
			solAdrDto.setAdrArea(solAdrItem.getAdrarea());
			solAdrDto.setAdrType(solAdrItem.getAdrtype());
			solAdrDto.setAdrImpact(solAdrItem.getAdrimpact());
			solAdrDto.setAdrWeightage(solAdrItem.getAdrweightage());
			solAdrDtoList.add(solAdrDto);
		}
		
		return solAdrDtoList;
	}

	@Override
	public String getCurrencyCode(Integer opportunityID) {
		Session session = getSession();
		String query = "select CurrencyCode from Country where CountryID=(select CountryID from Customer where CustomerID=(select CustomerID from Opportunity where OpportunityID="
				+ opportunityID + "))";

		String currencyCode = null;
		SQLQuery sql = session.createSQLQuery(query);
		List<String> temp = sql.list();

		currencyCode = temp.get(0);
		session.close();
		return currencyCode;
	}

	@Override
	public List<FTEStagingDTO> getFTEStaingDataForContractYear(Integer solId,
			Integer serviceScopeId, String startDate, String endDate) {
		
			List<FTEStagingDTO> fteStagingDTOs = new ArrayList<>();
			
			String query = "from FTEStaging f where f.solution.solutionId="+solId+" and f.opportunityScope.opportunityScopeId="+serviceScopeId+" and monthYear >='"+startDate+"' and monthYear <='"+endDate+"'";
			System.out.println("contract year query string : " + query);
			
			List<FTEStaging> fteStagings = getHibernateTemplate().find(query);
		
			for (FTEStaging fteStaging : fteStagings) {
				
				FTEStagingDTO fteStagingDTO = new FTEStagingDTO();
				SolutionDTO solutionDTO = new SolutionDTO();
				OpportunityScopeDTO opportunityScopeDTO = new OpportunityScopeDTO();
				JobRoleStagesDTO jobRoleStagesDTO = new JobRoleStagesDTO();
				try{
					org.apache.commons.beanutils.BeanUtils.copyProperties(solutionDTO, fteStaging.getSolution());
					org.apache.commons.beanutils.BeanUtils.copyProperties(opportunityScopeDTO, fteStaging.getOpportunityScope());
					org.apache.commons.beanutils.BeanUtils.copyProperties(jobRoleStagesDTO, fteStaging.getJobRoleStage());	
				}catch(Exception e){
					e.printStackTrace();
				}
				
				fteStagingDTO.setFtecount(fteStaging.getFtecount());
				fteStagingDTO.setFtestagingId(fteStaging.getFtestagingId());
				fteStagingDTO.setJobRoleStagesDTO(jobRoleStagesDTO);
				fteStagingDTO.setLocation(fteStaging.getLocation());
				fteStagingDTO.setMonthYear(fteStaging.getMonthYear());
				fteStagingDTO.setOpportunityScopeDTO(opportunityScopeDTO);
				fteStagingDTO.setSolutionDTO(solutionDTO);
				fteStagingDTO.setSubLocation(fteStaging.getSubLocation());
				
				fteStagingDTOs.add(fteStagingDTO);
			}
			
			
		return fteStagingDTOs;
	}

}
