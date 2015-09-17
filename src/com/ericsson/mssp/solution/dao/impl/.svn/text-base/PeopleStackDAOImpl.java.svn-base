package com.ericsson.mssp.solution.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.mssp.common.dto.ScopeSummaryReportDTO;
import com.ericsson.mssp.common.entity.LessEffortDetail;
import com.ericsson.mssp.common.entity.PeopleStackResourceDetail;
import com.ericsson.mssp.common.entity.StaffAllocation;
import com.ericsson.mssp.common.entity.StaffCategory;
import com.ericsson.mssp.solution.dao.PeopleStackDAO;

@Repository
@Transactional(propagation=Propagation.MANDATORY, readOnly=true)
public class PeopleStackDAOImpl extends HibernateDaoSupport implements PeopleStackDAO
{
	@Autowired
	public void init(SessionFactory factory) {
	    setSessionFactory(factory);
	}
	@Override
	public List<PeopleStackResourceDetail> loadAllResourceDetails(long solutionId) {
		List<PeopleStackResourceDetail> peopleStackgList = getHibernateTemplate().find("from PeopleStackResourceDetail where dtoPK.solId="+solutionId);
		return peopleStackgList;
	}
	@Override
	public void saveResourceDetails(List<PeopleStackResourceDetail> dtos) {
		for(PeopleStackResourceDetail dto : dtos)
			getHibernateTemplate().saveOrUpdate(dto);
	}
	@Override
	public LessEffortDetail loadInScopeSummaryLessEffort(long solutionId) {

		LessEffortDetail dto = getHibernateTemplate().get(LessEffortDetail.class,solutionId);
		if(dto==null)
			dto = new LessEffortDetail();
		return dto;
	}
	@Override
	public void saveScopeSummary(LessEffortDetail dto) {
		getHibernateTemplate().saveOrUpdate(dto);
	}

	public List<ScopeSummaryReportDTO> loadInScopeSummaryDTO(long solutionId) {
		Session session = null;
        	List<ScopeSummaryReportDTO> summaryList = null;
        	try {
        	    session = getSession();
        	    Transaction tx = session.beginTransaction();
        	    summaryList = new ArrayList<ScopeSummaryReportDTO>();
        
        	    String SQL_QUERY = "SELECT SUM(d.annualHrs) as TotalOutsourcedHrs, AVG(d.yearlyUtilization) as AvgUtil, d.department as Department,d.category as Category "
        		    + "FROM PeopleStackResourceDetail d where d.inScope = 0 and d.dtoPK.solId="
        		    + solutionId + " GROUP BY d.department,d.category";
        	    Query query = session.createQuery(SQL_QUERY).setResultTransformer(
        		    new AliasToBeanResultTransformer(
        			    ScopeSummaryReportDTO.class));
        	    summaryList = query.list();
        	    tx.commit();
        	} finally {
        	    if (null != session) {
        		session.close();
        	    }
        	}
		return summaryList;
	}
	@Override
	public List<StaffAllocation> loadStaffAllocation(long solutionId) {
		List<StaffAllocation> staffAllocList = getHibernateTemplate().find("from StaffAllocation where primaryKey.solId="+solutionId);
		return staffAllocList;
	}
	@Override
	public void saveStaffAllocation(List<StaffAllocation> staffList) {
		for(StaffAllocation dto : staffList)
			getHibernateTemplate().saveOrUpdate(dto);
	}
	@Override
	public List<StaffCategory> loadStaffCategoryList()
	{
		List<StaffCategory> staffCategoryList = new ArrayList<StaffCategory>();
		staffCategoryList = getHibernateTemplate().loadAll(StaffCategory.class);
		return staffCategoryList;
	}
	@Override
	public void deleteResource(PeopleStackResourceDetail dto) {
		getHibernateTemplate().delete(dto);
	}
}
