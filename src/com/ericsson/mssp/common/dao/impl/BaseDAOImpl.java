/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.dao.impl
* File name       		    :  BaseDAOImpl.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 6, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 6, 2012  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.mssp.common.dao.IBaseDAO;


/**
 * @author egaivij
 * 
 */
@Repository
public class BaseDAOImpl extends HibernateDaoSupport implements
		IBaseDAO {
	
	@Autowired
	public void init(SessionFactory factory) {
	    setSessionFactory(factory);
	}
	
	@Transactional
	public void saveObject(Object o) {
		
		getHibernateTemplate().saveOrUpdate(o);
	}

	public Object getObject(Class clazz, Serializable id) {
		Object o = (Object)getHibernateTemplate().get(clazz, id);

		if (o == null) {
			throw new ObjectRetrievalFailureException(clazz, id);
		}

		return o;
	}

	public List getObjects(Class clazz) {
		return getHibernateTemplate().loadAll(clazz);
	}

	public void removeObject(Class clazz, Serializable id) {
		getHibernateTemplate().delete(getObject(clazz, id));
	}

	public void updateObject(Object o) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().update(o);
		getHibernateTemplate().flush();
	}
	 public void batchSaveUpdate(List<Object> objectsList)
	 {
		getHibernateTemplate().saveOrUpdateAll(objectsList);
		getHibernateTemplate().flush();
	 }
}
