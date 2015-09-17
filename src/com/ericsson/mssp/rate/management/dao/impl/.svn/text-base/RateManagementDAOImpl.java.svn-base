package com.ericsson.mssp.rate.management.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.entity.Country;
import com.ericsson.mssp.common.entity.CurrencyExchange;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.JobStage;
import com.ericsson.mssp.common.entity.RateCard;
import com.ericsson.mssp.common.entity.RateCardHistory;
import com.ericsson.mssp.rate.management.dao.IRateManagementDAO;
import com.ericsson.mssp.rate.management.dto.RateManagementDTO;


@Repository
public class RateManagementDAOImpl extends BaseDAOImpl implements IRateManagementDAO,
MSSPConstants{

	@Override
	public String saveAndUpdate(RateCard rateCard) {
		
		saveObject(rateCard);		
		String rowsUpdated = updateLocalRateCard(rateCard.getUpdatedTimestamp(),rateCard.getCountry().getCountryId());
		logger.info("timestamp updated in rate card table after save [" + rowsUpdated + "]");
		return rateCard.getRateCardId().toString();
	}

	@Override
	public String getUsdValue(String currencyCode) {
		
		final String query = "select USDValue from CurrencyExchange where CurrencyCode='"+currencyCode+"'";

		String UsdValue = (String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						List results = session.createSQLQuery(query)
								.setFirstResult(0).list();
						if(results.size()>0)
							return results.get(0);
						else
							return "";
					}

				});
		
		return UsdValue;
		
	}

	@Override
	public Integer getJobRoleStageID(Integer jobRoleID, Integer jobStageID) {
		
		
		
		final String query = "select JobRoleStageID from JobRoleStages where JobRoleID="+jobRoleID+" and JobStageID="+jobStageID;

		Integer jobRoleStageID = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						List results = session.createSQLQuery(query)
								.setFirstResult(0).list();
						return Integer.parseInt(results.get(0).toString());
					}

				});
		
		return jobRoleStageID;
	}
	
	@Override
	public Integer getJobRoleStageID(String jobRole, String jobStage,Integer ccmFlag) {
		
		
		
		final String queryJobRoleID = "select JobRoleID from JobRole where RoleName='"+jobRole+"' and CCMFlag="+ccmFlag;

		Integer jobRoleID = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						List results = session.createSQLQuery(queryJobRoleID)
								.setFirstResult(0).list();
						return Integer.parseInt(results.get(0).toString());
					}

				});
		
		final String queryJobStageID = "select JobStageID from JobStage where Stage='"+jobStage+"' and CCMFlag="+ccmFlag;

		Integer jobStgaeID = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						List results = session.createSQLQuery(queryJobStageID)
								.setFirstResult(0).list();
						return Integer.parseInt(results.get(0).toString());
					}

				});
		
		final String queryJobRoleStageID = "select JobRoleStageID from JobRoleStages where JobRoleID="+jobRoleID+" and JobStageID="+jobStgaeID;

		Integer jobRoleStageID = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						List results = session.createSQLQuery(queryJobRoleStageID)
								.setFirstResult(0).list();
						return Integer.parseInt(results.get(0).toString());
					}

				});
		
		
		return jobRoleStageID;
	}

	@Override
	public String updateRateCard(RateManagementDTO rateManagementDTO) {
		
		String rateCardQuery = "from RateCard rc where rc.country.countryId="+rateManagementDTO.getCountryID();
		String stageIdQuery = "from JobStage js where js.stage='"+rateManagementDTO.getJobStage()+"'";
		String roleIdQuery = "from JobRole jr where jr.role='"+rateManagementDTO.getJobRole()+"'";
		
		List<RateCard> rateCardList = getHibernateTemplate().find(rateCardQuery);
		List<JobStage> stage = getHibernateTemplate().find(stageIdQuery);
		List<JobRole> role = getHibernateTemplate().find(roleIdQuery);
		
		Integer jobRoleStageID = getJobRoleStageID(role.get(0).getJobRoleId(),stage.get(0).getJobStageID());		
		
		final String dateQuery = "select * from RateCardHistory where Date(UpdatedTimestamp) in (select max(Date(UpdatedTimestamp)) " +
"from RateCardHistory where CountryID="+rateManagementDTO.getCountryID()+" and JobRoleStageID="+jobRoleStageID+") and CountryID="+rateManagementDTO.getCountryID()+" and JobRoleStageID="+jobRoleStageID+" and Gsc='"+rateManagementDTO.getGsc()+"'";
		List<Object> rateCardHistoryDate = (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery sq = session.createSQLQuery(dateQuery);
						List<Object> maxdate = sq.list();
						return maxdate;
					}

				});
		
		Date maxDate = null;
		boolean insertValues = false;
		
		for(Object item : rateCardHistoryDate){
			Object[] element = (Object[]) item;
			maxDate = (Date)element[6];
		}

		if(rateCardHistoryDate.size() != 0){
			System.out.println("***********INSIDE IF **************");
			String latestRateHistoryDate = maxDate.toString();
			Calendar cal = Calendar.getInstance();
			cal.setTime(rateManagementDTO.getDate());
			String currentDate = String.valueOf(cal.get(Calendar.YEAR))+"-"+String.valueOf((cal.get(Calendar.MONTH))+1)+"-"+String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
			Date date1 =null,date2=null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			try{
				date1 = sdf.parse(currentDate);
				date2 = sdf.parse(latestRateHistoryDate);
			
				System.out.println("latestRateHistoryDate[" + latestRateHistoryDate+"] currentDate["+currentDate+"]");
					String rateQuery = "from RateCardHistory rch where date(rch.updatedTimestamp) in (select max(date(hrc.updatedTimestamp)) from RateCardHistory hrc where hrc.country.countryId="+rateManagementDTO.getCountryID()+" and hrc.jobRoleStages.jobRoleStagesId="+jobRoleStageID+") and rch.country.countryId="+rateManagementDTO.getCountryID()+" and rch.jobRoleStages.jobRoleStagesId="+jobRoleStageID+" and rch.gsc='"+rateManagementDTO.getGsc()+"'";
					List <RateCardHistory> rateCardHistories = getHibernateTemplate().find(rateQuery);
					
					if(date1.equals(date2)){
						for(RateCardHistory cardHistory :  rateCardHistories){
							logger.info("rate card history update");
							
							//RateCardHistory history = new RateCardHistory();
							cardHistory.setRateCardHistoryId(cardHistory.getRateCardHistoryId());
							cardHistory.setRate(rateManagementDTO.getRate());
							saveObject(cardHistory);
						}
					}else{
						logger.info("rate card history insertions");
						insertValues = true;
					}
				}catch(Exception e){
					e.printStackTrace();
				}
		}
		else if(insertValues || rateCardHistoryDate.size() == 0){
			System.out.println("***********INSIDE ELSE IF **************");
			String checkEntryQuery = "from RateCardHistory rch where date(rch.updatedTimestamp) in (select max(date(hrc.updatedTimestamp)) from RateCardHistory hrc where hrc.country.countryId="+rateManagementDTO.getCountryID()+") and rch.country.countryId="+rateManagementDTO.getCountryID();
			List<RateCardHistory> rateCardHistories = getHibernateTemplate().find(checkEntryQuery);
			
			if(rateCardHistories.size() !=0){
			
			boolean found = false;
			Calendar cal = Calendar.getInstance();
			cal.setTime(rateManagementDTO.getDate());
			String currentDate = String.valueOf(cal.get(Calendar.YEAR))+"-"+String.valueOf((cal.get(Calendar.MONTH))+1)+"-"+String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
			Date date1 =null,date2=null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Date maximumDate = null;
			
			for(RateCardHistory cardHistory : rateCardHistories){
				maximumDate = cardHistory.getUpdatedTimestamp();
				break;
			}
			
			try{
			date1 = sdf.parse(currentDate);
			date2 = sdf.parse(maximumDate.toString());
			System.out.println("date1[" + date1+"] date2["+date2+"]");
			}catch(Exception e){
				e.printStackTrace();
			}
			if(date1.equals(date2)){
				System.out.println("dates are equal");
				if(rateCardHistories.size() > 0){
					System.out.println("rateCardHistories.size() > 0");
						for(RateCard rateCard : rateCardList){
							found = false;
							for(RateCardHistory cardHistory : rateCardHistories){
								if(String.valueOf(rateCard.getJobRoleStages().getJobRoleStagesId()).equalsIgnoreCase(String.valueOf(cardHistory.getJobRoleStages().getJobRoleStagesId()))){
									found = true;
									break;
								}else{
									continue;
								}
							}
							if(!found){
								RateCardHistory rateCardHistory = new RateCardHistory();
								Country country = new Country();
								JobRoleStages jobRoleStages = new JobRoleStages();
								
								country.setCountryId(rateCard.getCountry().getCountryId());
								jobRoleStages.setJobRoleStagesId(rateCard.getJobRoleStages().getJobRoleStagesId());
								
								rateCardHistory.setCountry(country);
								rateCardHistory.setJobRoleStages(jobRoleStages);
								rateCardHistory.setGsc(rateCard.getGsc());
								rateCardHistory.setLocation(rateCard.getLocation());
								rateCardHistory.setRate(rateCard.getRate());
								rateCardHistory.setSubLocation(rateCard.getSubLocation());
								rateCardHistory.setCreatedTimestamp(new java.sql.Timestamp(rateCard.getUpdatedTimestamp().getTime()));
								rateCardHistory.setUpdatedTimestamp(new java.sql.Timestamp(rateManagementDTO.getDate().getTime()));
								rateCardHistory.setStatusFlag(rateCard.getStatusFlag());
							
								saveObject(rateCardHistory);
							}
						}
					}					
				}
			}else{
			
					for(RateCard rateCard : rateCardList){
						
						RateCardHistory rateCardHistory = new RateCardHistory();
						Country country = new Country();
						JobRoleStages jobRoleStages = new JobRoleStages();
						
						country.setCountryId(rateCard.getCountry().getCountryId());
						jobRoleStages.setJobRoleStagesId(rateCard.getJobRoleStages().getJobRoleStagesId());
						
						rateCardHistory.setCountry(country);
						rateCardHistory.setJobRoleStages(jobRoleStages);
						rateCardHistory.setGsc(rateCard.getGsc());
						rateCardHistory.setLocation(rateCard.getLocation());
						rateCardHistory.setRate(rateCard.getRate());
						rateCardHistory.setSubLocation(rateCard.getSubLocation());
						rateCardHistory.setCreatedTimestamp(new java.sql.Timestamp(rateCard.getUpdatedTimestamp().getTime()));
						rateCardHistory.setUpdatedTimestamp(new java.sql.Timestamp(rateManagementDTO.getDate().getTime()));
						rateCardHistory.setStatusFlag(rateCard.getStatusFlag());
					
						saveObject(rateCardHistory);
					}
				}
			}
		
		final String queryRateCard = "update RateCard set Rate="+rateManagementDTO.getRate()+" where RateCardID="+rateManagementDTO.getRateManagementID();

		String retVal = (String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery sq = session.createSQLQuery(queryRateCard);
						int counts = sq.executeUpdate();
						return String.valueOf(counts);
					}

				});
		
		String rowsUpdated = updateLocalRateCard(new java.sql.Timestamp(rateManagementDTO.getDate().getTime()),rateManagementDTO.getCountryID());
		logger.info("timestamp updated in rate card table after rate history save/update [" + rowsUpdated + "]");
		return retVal;
	}

	private String updateLocalRateCard(Date date,Integer countryID){
		
		String totalRowsAffected = "";
		
		final String updateDateQuery = "update RateCard set UpdatedTimestamp='"+date+"' where CountryID="+countryID;
		totalRowsAffected = (String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery sq = session.createSQLQuery(updateDateQuery);
						int counts = sq.executeUpdate();
						return String.valueOf(counts);
					}

				});
		
		return totalRowsAffected;
	}
	
	@Override
	public String updateCurrencyExchange(String currencyCode, String usdValue, String countryName) {
		String resp = "";
		String query =  "from CurrencyExchange ce where ce.currencyCode='"+currencyCode+"' and ce.countryName='"+countryName+"'";
		
		List<CurrencyExchange> currencyExchangeList = getHibernateTemplate().find(query);
		if(currencyExchangeList.size() == 0){
			CurrencyExchange currencyExchange = new CurrencyExchange();
			currencyExchange.setCountryName(countryName);
			currencyExchange.setCurrencyCode(currencyCode);
			currencyExchange.setUsdValue(usdValue);
			
			try{
			saveObject(currencyExchange);
			resp = "Data inserted successfully";
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			resp = "dataExists";
			final String updateQuery = "update CurrencyExchange set USDValue="+usdValue+" where CurrencyCode='"+currencyCode+"' and CountryName='"+countryName+"'";

			String retVal = (String) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							SQLQuery sq = session.createSQLQuery(updateQuery);
							int counts = sq.executeUpdate();
							return String.valueOf(counts);
						}

					});
			
		}
		
		return resp;
	}

	@Override
	public List<CurrencyExchange> getCurrencyExchangeList() {
		return (List<CurrencyExchange>)getHibernateTemplate().find("from CurrencyExchange");
	}

	@Override
	public boolean checkEntry(RateManagementDTO rateManagementDTO) {
		
		Integer jobRoleStageID = getJobRoleStageID(rateManagementDTO.getJobRoleID(),rateManagementDTO.getJobStageID());
		
		String query = "from RateCard rc where rc.country.countryId="+rateManagementDTO.getCountryID()+" and rc.location='"+rateManagementDTO.getLocation()+"' and rc.subLocation='"+rateManagementDTO.getSubLocation()+"' and rc.gsc='"+rateManagementDTO.getGsc()+"' and rc.jobRoleStages.jobRoleStagesId="+jobRoleStageID;
		boolean message = false;
		
		List<RateCard> rateCards = getHibernateTemplate().find(query); 
		
		if(null != rateCards && rateCards.size() > 0){
			
			message = true;
		}
		
		return message;
	}

	@Override
	public String getCountryName(Integer gscId) {
		String countryName = "";
		String query = "from Country c where c.countryId="+gscId;
		List<Country> country = getHibernateTemplate().find(query);
		
		for(Country item  : country){
			countryName = item.getCountryName();
		}
		
		return countryName;
	}

	@Override
	public String checkDataByCountryID(Integer countryID) {
		String query="from RateCard rc where rc.country.countryId="+countryID;
		
		List<RateCard> list = getHibernateTemplate().find(query);
		
		if(list.size() > 0){
			return "true";
		}else{
			return "false";
		}
	}

}
