package com.ericsson.mssp.solution.dao.impl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.dto.TNTPartitionDateDTO;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.TNTDetail;
import com.ericsson.mssp.common.entity.TNTPartitionDate;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.dao.ITNTDao;
@Repository
public class TNTDaoImpl extends BaseDAOImpl implements ITNTDao
{

	@Override
	public void saveTntPartitionDateDetails(TNTPartitionDateDTO partitionDateDto)
			throws MSSPException 
			{
		TNTPartitionDate dateObj = new TNTPartitionDate();
		dateObj.setTntpartitionDateId(partitionDateDto.gettNTPartitionDateID());
		dateObj.setSolution(partitionDateDto.getSolution());
		dateObj.setNoOfPart(partitionDateDto.getNoOfPart());
		getHibernateTemplate().saveOrUpdate(dateObj);
	}

	@Override
	public TNTPartitionDateDTO getPartitionDate(Long solutionId, Long oppId) 
	{
		final TNTPartitionDateDTO dtoObj = new TNTPartitionDateDTO();
		List<TNTPartitionDate> partList = getHibernateTemplate().find("from  TNTPartitionDate where solution.solutionId="+solutionId);
		final String queryPartDate = "from OpportunityDetail where OpportunityID="+oppId;
		//List<OpportunityDetail> list = getHibernateTemplate().find("from  OpportunityDetail where opportunity="+oppId);
		ArrayList<OpportunityDetail> list = (ArrayList<OpportunityDetail>)getHibernateTemplate().execute(new HibernateCallback<ArrayList>() {
			@Override
			public ArrayList doInHibernate(Session session) throws HibernateException,
					SQLException {
				ArrayList<OpportunityDetail> list = (ArrayList<OpportunityDetail>)session.createQuery(queryPartDate).list();
				if(list == null || list.size() <= 0)
				{
					return null;
				}
				else
				{
					for(OpportunityDetail detailObj : list)
					{
						dtoObj.setCountryName(detailObj.getOpportunity().getCustomer().getCountry().getCountryName());
						dtoObj.setCustomerName(detailObj.getOpportunity().getCustomer().getCustomerName());
						dtoObj.setOpportunityName(detailObj.getOpportunity().getOpportunityName());
					}
					return list;
				}
			}
		});
		final String query = "select ServiceFTE from ViewStartupFTE "
			    + "where SolutionID=" + solutionId;
		Float fteValue = (Float)getHibernateTemplate().execute(
		        new HibernateCallback() {
		            public Float doInHibernate(Session session)
		                throws HibernateException {
		            	float totalServiceFte = 0;
		            	List<?> fteValues =  session.createSQLQuery(query).list();	   
		            	if (fteValues != null && fteValues.size() > 0 && fteValues.get(0) != null) {
		            		logger.info("FTE = "+ Float.valueOf(fteValues.get(0).toString()));
		            		Iterator<?> iterator = fteValues.iterator();
		            		while(iterator.hasNext())
		            		{
		            			totalServiceFte += Float.valueOf(iterator.next().toString());
		            		}
		                	return totalServiceFte;
		                }
		            	return Float.valueOf(0);
		            }
		        });
		TNTPartitionDate obj = null;
		OpportunityDetail oppDetailObj = null;
		if(list == null || (list.size() <= 0))
		{
			return null;
		}
		if((partList!=null) && (partList.size()>0))
		{
			obj = partList.get(0);
			oppDetailObj = list.get(0);
			dtoObj.settNTPartitionDateID(obj.getTntpartitionDateId());
			dtoObj.setNoOfPart(obj.getNoOfPart());
			dtoObj.setSolution(obj.getSolution());
			dtoObj.setStartDate(formatDateToString(oppDetailObj.getTransformationStartDate()));
			dtoObj.setEndDate(formatDateToString(oppDetailObj.getTransformationEndDate()));
			dtoObj.setServiceFteCount(fteValue.floatValue());
			dtoObj.setCadenceId(oppDetailObj.getCadenceId());
		}
		else
		{
			obj = new TNTPartitionDate();
			oppDetailObj = list.get(0);
			dtoObj.settNTPartitionDateID(obj.getTntpartitionDateId());
			dtoObj.setNoOfPart(obj.getNoOfPart());
			dtoObj.setSolution(obj.getSolution());
			dtoObj.setStartDate(formatDateToString(oppDetailObj.getTransformationStartDate()));
			dtoObj.setEndDate(formatDateToString(oppDetailObj.getTransformationEndDate()));
			dtoObj.setServiceFteCount(fteValue.floatValue());
			dtoObj.setCadenceId(oppDetailObj.getCadenceId());
		}
		return dtoObj;
	}

	@Override
	public List<TNTDetail> getTnTDetail(Long solutionId) 
	{
		List<TNTDetail> tntdetailList = getHibernateTemplate().find("from TNTDetail where solution="+solutionId);
		return tntdetailList;
	}

	@Override
	public void saveTntDetail(List<TNTDetail> tntDetail) throws MSSPException 
	{
		for(TNTDetail tntObj:tntDetail)
		{
			getHibernateTemplate().saveOrUpdate(tntObj);
		}
	}
	
	private String formatDateToString(Date date)
	{
		if(date != null)
		{
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.format(date);
		}
		else
		{
			return "";
		}
		
	}
}
