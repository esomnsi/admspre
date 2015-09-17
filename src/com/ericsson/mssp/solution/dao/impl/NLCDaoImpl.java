package com.ericsson.mssp.solution.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ericsson.mssp.approval.dao.impl.ApprovalDAOImpl;
import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.entity.NLCInputParam;
import com.ericsson.mssp.common.entity.NLCOther;
import com.ericsson.mssp.common.entity.NLCTravel;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.dao.INLCDao;
import com.ericsson.mssp.solution.forms.NLCForm;
import com.ericsson.mssp.solution.forms.NLCForm.InputParams;

@Repository
public class NLCDaoImpl extends BaseDAOImpl implements INLCDao,
MSSPConstants {
	public static Logger logger = Logger.getLogger(NLCDaoImpl.class);
	
	public void saveNLC(NLCForm nlcForm) throws MSSPException {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {

			session.createQuery(
					"delete from NLCInputParam where solutionID= "
							+ nlcForm.getSolutionId()).executeUpdate();

			NLCInputParam inputParam = new NLCInputParam();

			inputParam.setSolutionID(nlcForm.getSolutionId());
			inputParam.setOneRoundTripCost(nlcForm.getInputParams()
					.getRoundTripTicketCost());
			// inputParam.setOneLongTermVisaCost(nlcForm.getInputParams().getLongTermVisaCost());
			inputParam.setOneShortTermVisaCost(nlcForm.getInputParams()
					.getShortTermVisaCost());
			inputParam.setOnsiteHotelCost(nlcForm.getInputParams()
					.getOnsiteHotelCost());
			inputParam.setOnsitePerDiem(nlcForm.getInputParams()
					.getOnsitePerdiem());
			inputParam.setOnsiteConveyanceCost(nlcForm.getInputParams()
					.getOnsiteConveyanceCost());
			inputParam.setMobileCommCost(nlcForm.getInputParams()
					.getMobileCostPerOnsiteFTE());
			inputParam.setInvoicingType(nlcForm.getInputParams().getInvoiceType());
			inputParam.setMSDPPlatformUsed(nlcForm.getInputParams()
					.getMsdpPlatformUsed());
			if (nlcForm.getInputParams().getMsdpPlatformUsed() == 1) {
				inputParam.setMSDPCost(nlcForm.getInputParams().getMsdpCost());
			}

			session.saveOrUpdate(inputParam);

			for (int i = 0; i < nlcForm.getSsOnshoreFTE().size(); i++) {
				NLCTravel nlcTravel = new NLCTravel();
				nlcTravel.setType("SS");
				nlcTravel.setInputParam(inputParam);
				nlcTravel.setOnshoreFTE(nlcForm.getSsOnshoreFTE().get(i));
				nlcTravel.setRoundTrips(nlcForm.getSsRoundTripNo().get(i));
				// nlcTravel.setLongTermVisa(nlcForm.getSsLongTermVisa().get(i));
				nlcTravel.setShortTermVisa(nlcForm.getSsShortTermVisa().get(i));
				/*
				 * nlcTravel.setCalendarDays(nlcForm.getSsOnsiteCalDays().get(i))
				 * ;
				 * nlcTravel.setWorkingDays(nlcForm.getSsOnsiteWorkDays().get(i
				 * ));
				 */
				nlcTravel.setRoundTripCost(nlcForm.getSsRoundTripCost().get(i));
				// nlcTravel.setLongTermVisaCost(nlcForm.getSsVisaLTermCost().get(i));
				nlcTravel.setShortTermVisaCost(nlcForm.getSsVisaSTermCost()
						.get(i));
				nlcTravel.setOnsiteHotelCost(nlcForm.getSsOnsiteHotelCost()
						.get(i));
				// nlcTravel.setRelocationCost(null);
				nlcTravel.setOnsitePerDiem(nlcForm.getSsOnsitePerdiemCost()
						.get(i));
				// nlcTravel.setOnsiteFSI(null);
				nlcTravel.setOnsiteConveyanceCost(nlcForm
						.getSsOnsiteConveyanceCost().get(i));
				nlcTravel.setMoblileCommCost(nlcForm.getSsMobileCommCost().get(
						i));
				nlcTravel.setTotalCost(nlcForm.getSsTotCost().get(i));

				int yr = Integer.parseInt(nlcForm.getSsMonthYears().get(i)
						.split("-")[1]);
				int mnth = getMonthNumber(nlcForm.getSsMonthYears().get(i)
						.split("-")[0]);
				nlcTravel.setMonth(mnth);
				nlcTravel.setYear(yr);
				session.saveOrUpdate(nlcTravel);
			}

			for (int i = 0; i < nlcForm.getTransOnshoreFTE().size(); i++) {
				NLCTravel nlcTravel = new NLCTravel();
				nlcTravel.setType("TT");
				nlcTravel.setInputParam(inputParam);
				nlcTravel.setOnshoreFTE(nlcForm.getTransOnshoreFTE()
						.get(i));
				nlcTravel.setRoundTrips(nlcForm.getTransRoundTripNo().get(i));
				// nlcTravel.setLongTermVisa(nlcForm.getSsLongTermVisa().get(i));
				nlcTravel.setShortTermVisa(nlcForm.getTransShortTermVisa().get(
						i));
				// nlcTravel.setCalendarDays(nlcForm.getTransOnsiteCalDays().get(i));
				// nlcTravel.setWorkingDays(nlcForm.getTransOnsiteWorkDays().get(i));
				nlcTravel.setRoundTripCost(nlcForm.getTransRoundTripCost().get(
						i));
				// nlcTravel.setLongTermVisaCost(nlcForm.getSsVisaLTermCost().get(i));
				nlcTravel.setShortTermVisaCost(nlcForm.getTransVisaSTermCost()
						.get(i));
				nlcTravel.setOnsiteHotelCost(nlcForm.getTransOnsiteHotelCost()
						.get(i));
				// nlcTravel.setRelocationCost(null);
				nlcTravel.setOnsitePerDiem(nlcForm.getTransOnsitePerdiemCost()
						.get(i));
				// nlcTravel.setOnsiteFSI(null);
				nlcTravel.setOnsiteConveyanceCost(nlcForm
						.getTransOnsiteConveyanceCost().get(i));
				nlcTravel.setMoblileCommCost(nlcForm.getTransMobileCommCost().get(
						i));
				nlcTravel.setTotalCost(nlcForm.getTransTotCost().get(i));

				int yr = Integer.parseInt(nlcForm.getTransMonthYears().get(i)
						.split("-")[1]);
				int mnth = getMonthNumber(nlcForm.getTransMonthYears().get(i)
						.split("-")[0]);
				nlcTravel.setMonth(mnth);
				nlcTravel.setYear(yr);
				session.saveOrUpdate(nlcTravel);
			}

			for (int i = 0; i < nlcForm.getOtherOffShoreFTE().size(); i++) {
				NLCOther nlcOther = new NLCOther();
				nlcOther.setInputParam(inputParam);
				if (nlcForm.getInputParams().getMsdpPlatformUsed() == 1) {
					nlcOther.setMsdpCost(nlcForm.getMsdpCost().get(i));
				}
				nlcOther.setOffshoreFTE(nlcForm.getOtherOffShoreFTE().get(i));
				nlcOther.setConnClientToIndia(nlcForm.getConnectivityToIndia()
						.get(i));
				nlcOther.setConnIndiaToDev(nlcForm.getConnectivityToDev()
						.get(i));
				nlcOther.setTotalOtherCost(nlcForm.getTotalConnectivity()
						.get(i));
				int yr = Integer.parseInt(nlcForm.getOtherMonthYears().get(i)
						.split("-")[1]);
				int mnth = getMonthNumber(nlcForm.getOtherMonthYears().get(i)
						.split("-")[0]);
				nlcOther.setMonth(mnth);
				nlcOther.setYear(yr);
				session.saveOrUpdate(nlcOther);
			}
			tx.commit();
		} catch (Exception e) {
			logger.error("Exception in NLCDaoImpl[saveNLC] ", e);
			tx.rollback();
			logger.info("NLCDaoImpl[saveNLC] Successfully rolled back");
			throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		} finally {
			session.close();
		}

	}
    
	public NLCForm retrieveNLC(int solutionId, NLCForm nlcForm) throws MSSPException {

		//NLCForm nlcForm = new NLCForm();
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			List<NLCInputParam> inputParamList = session.createQuery(
					"from NLCInputParam where solutionID= " + solutionId).list();
			if (inputParamList.size() > 0) {
				NLCInputParam paramEntity = inputParamList.get(0);

				InputParams inputParams = nlcForm.getInputParams();

				inputParams.setRoundTripTicketCost(paramEntity
						.getOneRoundTripCost());
				inputParams.setLongTermVisaCost(paramEntity
						.getOneLongTermVisaCost());
				inputParams.setShortTermVisaCost(paramEntity
						.getOneShortTermVisaCost());
				inputParams
						.setOnsiteHotelCost(paramEntity.getOnsiteHotelCost());
				inputParams.setOnsitePerdiem(paramEntity.getOnsitePerDiem());
				// inputParams.setOnsiteFSICost();
				inputParams.setOnsiteConveyanceCost(paramEntity
						.getOnsiteConveyanceCost());
				inputParams.setMobileCostPerOnsiteFTE(paramEntity.getMobileCommCost());
				inputParams.setMsdpPlatformUsed(paramEntity
						.getMSDPPlatformUsed());
				inputParams.setMsdpCost(paramEntity.getMSDPCost());
				inputParams.setInvoiceType(paramEntity.getInvoicingType());

				//retrieving SS data
				List<NLCTravel> nlcTravelList = session.createQuery(
						"from NLCTravel t where t.inputParam.nlcInputParamID= "
								+ paramEntity.getNlcInputParamID()
								+" and t.type='SS'"
								+ " order by t.nlcTravelID asc")
						.list();
				
				for (int i = 0; i < nlcTravelList.size(); i++) {
					NLCTravel travelEntity = nlcTravelList.get(i);

					nlcForm.getSsRoundTripNo()
							.add(travelEntity.getRoundTrips());
					// nlcForm.getSsLongTermVisa().add(travelEntity.getLongTermVisa());
					nlcForm.getSsShortTermVisa().add(
							travelEntity.getShortTermVisa());
					nlcForm.getSsRoundTripCost().add(
							travelEntity.getRoundTripCost());
					/*nlcForm.getSsVisaLTermCost().add(
							travelEntity.getLongTermVisaCost());*/
					nlcForm.getSsVisaSTermCost().add(
							travelEntity.getShortTermVisaCost());
					nlcForm.getSsOnsiteHotelCost().add(
							travelEntity.getOnsiteHotelCost());
					nlcForm.getSsOnsitePerdiemCost().add(
							travelEntity.getOnsitePerDiem());
					// nlcForm.getSsOnsiteFSICost().add(travelEntity.getOnsiteFSI());
					nlcForm.getSsOnsiteConveyanceCost().add(
							travelEntity.getOnsiteConveyanceCost());
					nlcForm.getSsMobileCommCost().add(
							travelEntity.getMoblileCommCost());
					nlcForm.getSsTotCost().add(travelEntity.getTotalCost());
					}
				
				//retrieving Trans data
				nlcTravelList = session.createQuery(
						"from NLCTravel t where t.inputParam.nlcInputParamID= "
								+ paramEntity.getNlcInputParamID()
								+" and t.type='TT'"
								+ " order by t.nlcTravelID asc")
						.list();
				
				for (int i = 0; i < nlcTravelList.size(); i++) {
					NLCTravel travelEntity = nlcTravelList.get(i);

					nlcForm.getTransRoundTripNo()
							.add(travelEntity.getRoundTrips());
					// nlcForm.getSsLongTermVisa().add(travelEntity.getLongTermVisa());
					nlcForm.getTransShortTermVisa().add(
							travelEntity.getShortTermVisa());
					nlcForm.getTransRoundTripCost().add(
							travelEntity.getRoundTripCost());
					/*nlcForm.getSsVisaLTermCost().add(
							travelEntity.getLongTermVisaCost());*/
					nlcForm.getTransVisaSTermCost().add(
							travelEntity.getShortTermVisaCost());
					nlcForm.getTransOnsiteHotelCost().add(
							travelEntity.getOnsiteHotelCost());
					nlcForm.getTransOnsitePerdiemCost().add(
							travelEntity.getOnsitePerDiem());
					// nlcForm.getSsOnsiteFSICost().add(travelEntity.getOnsiteFSI());
					nlcForm.getTransOnsiteConveyanceCost().add(
							travelEntity.getOnsiteConveyanceCost());
					nlcForm.getTransMobileCommCost().add(
							travelEntity.getMoblileCommCost());
					nlcForm.getTransTotCost().add(travelEntity.getTotalCost());
					}
				//retrieving Other data
				List<NLCOther> nlcOtherList = session.createQuery(
						"from NLCOther t where t.inputParam.nlcInputParamID= "
								+ paramEntity.getNlcInputParamID()
								+ " order by t.nlcOtherID asc")
						.list();
				
				for (int i = 0; i < nlcOtherList.size(); i++) {
					NLCOther otherEntity = nlcOtherList.get(i);

					nlcForm.getMsdpCost()
							.add(otherEntity.getMsdpCost());
					nlcForm.getConnectivityToDev().add((otherEntity.getConnIndiaToDev()));
					nlcForm.getConnectivityToIndia().add(otherEntity.getConnClientToIndia());
					nlcForm.getTotalConnectivity().add(otherEntity.getTotalOtherCost());
				}
			}
			//check TTSummary and populate transition onshore and Offshore
			/*String transQuery = " select sum(t2.OnShoreFTE) , sum(t2.OffshoreFTE ), EXTRACT(MONTH FROM t2.WeekDate) "
					+ " from TTPlanning t1, TTSummary t2 "
					+ " where t1.SolutionID=  "
					+ solutionId
					+ " and t1.TTPlanningID=t2.TTPlanningID "
					+ " group by EXTRACT(MONTH FROM t2.WeekDate) "
					+ " order by t2.WeekDate asc ";*/
			String transQuery="select sum(t.OnShoreFTE), sum(t.OffshoreFTE ) ,EXTRACT(MONTH FROM t.WeekDate) "+
							" from ViewTTSummaryStaging t where t.SolutionID="+solutionId+
							" group by EXTRACT(MONTH FROM t.WeekDate)";
			List<Object> trnsDetailsList = session.createSQLQuery(
					transQuery).list();
			for (int i = 0; i < trnsDetailsList.size(); i++) {
				if (trnsDetailsList.get(i) != null) {
					Object[] o = (Object[]) trnsDetailsList.get(i);
					if (o != null && o[0]!=null) {
						float onShoreFTE = ((Double) o[0]).floatValue();
						float offShoreFTE = ((Double) o[1]).floatValue();
						int month;
						if(o[2] instanceof BigInteger ){
							month=((BigInteger)o[2]).intValue();
						}else {
							month = (Integer) o[2];
						}	
						nlcForm.getTransOnshoreFTE().add(onShoreFTE);
						nlcForm.getTransOffShoreFTE().add(offShoreFTE);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in NLCDaoImpl[retrieveNLC] ", e);
			tx.rollback();
			logger.info("NLCDaoImpl[retrieveNLC] Successfully rolled back");
			throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		} finally {
			session.close();
		}
		return nlcForm;
	}
   
    public static List<String> MonthYearsBetween(Date d1, Date d2) {
    	String[] mnthString=new String[]{"Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"};
	    List<String> ret = new ArrayList<String>();
	    Calendar c1 = Calendar.getInstance();
	    Calendar c2 = Calendar.getInstance();
	    c1.setTime(d1);c2.setTime(d2);
	    while(c1.before(c2)){
	    	ret.add(mnthString[c1.get(Calendar.MONTH)]+"-"+c1.get(Calendar.YEAR));
	    	c1.add(Calendar.MONTH, 1);
	    }
	    return ret;
	}
	private int getMonthNumber(String month) {
		int monthNum = 999;
		if ("Jan".equals(month)) {
			monthNum = 1;
		} else if ("Feb".equals(month)) {
			monthNum = 2;
		} else if ("Mar".equals(month)) {
			monthNum = 3;
		} else if ("Apr".equals(month)) {
			monthNum = 4;
		} else if ("May".equals(month)) {
			monthNum = 5;
		} else if ("Jun".equals(month)) {
			monthNum = 6;
		} else if ("Jul".equals(month)) {
			monthNum = 7;
		} else if ("Aug".equals(month)) {
			monthNum = 8;
		} else if ("Sep".equals(month)) {
			monthNum = 9;
		} else if ("Oct".equals(month)) {
			monthNum = 10;
		} else if ("Nov".equals(month)) {
			monthNum = 11;
		} else if ("Dec".equals(month)) {
			monthNum = 12;
		}
		return monthNum;

	}
}
