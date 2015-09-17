/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.dao
* File name       		    :  IBaseDAO.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 7, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 7, 2012  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dao;


import java.io.Serializable;
import java.util.List;


/**
 * Data Access Object (Dao) interface.   This is an interface
 * used to tag our Dao classes and to provide common methods to all Daos.
 *
 * <p><a href="Dao.java.html"><i>View Source</i></a></p>
 *
 * @author egaivij</a>
 */
public interface IBaseDAO {

    /**
     * Generic method used to get all objects of a particular type. This
     * is the same as lookup up all rows in a table.
     * @param clazz the type of objects (a.k.a. while table) to get data from
     * @return List of populated objects
     */
    public List getObjects(Class clazz);
    
    /**
     * Generic method to get an object based on class and identifier. An 
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param clazz model class to lookup
     * @param id the identifier (primary key) of the class
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    public Object getObject(Class clazz, Serializable id);

    /**
     * Generic method to save an object - handles both update and insert.
     * @param o the object to save
     */
    public void saveObject(Object o);

    /**
     * Generic method to delete an object based on class and id
     * @param clazz model class to lookup
     * @param id the identifier (primary key) of the class
     */
    public void removeObject(Class clazz, Serializable id);
    
    /**
     * Generic method to update an object 
     * @param object to update
     * @param id the identifier (primary key) of the class
     */
    public void updateObject(Object o);
}