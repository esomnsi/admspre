/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.common.util
 * File name       		    :  ApplicationPropertiesUtil.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Dec 13, 20122012
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Dec 13, 2012  	   |eruvwyn   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.common.util;

/**
 * @author eruvwyn
 *
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.exception.MSSPException;

public class ApplicationPropertiesUtil extends PropertyPlaceholderConfigurer
	implements MSSPConstants {
    public final static SimpleDateFormat MM_SLSH_DD_SLSH_YYYY = new SimpleDateFormat(
	    "MM/dd/yyyy");
    private static Map<String, String> propertiesMap;
    private static Map<String, Map<String, String>> loadedMapValues;
    private final Log log = LogFactory.getLog(ApplicationPropertiesUtil.class);

    private static String STRING_DELIMITER = ";";

    @Override
    protected void processProperties(
	    ConfigurableListableBeanFactory beanFactory, Properties props)
	    throws BeansException {
	super.processProperties(beanFactory, props);

	propertiesMap = new HashMap<String, String>(20);
	try {
	    for (Object key : props.keySet()) {
		String keyStr = key.toString();
		String value = props.getProperty(keyStr);
		propertiesMap.put(keyStr, value);
	    }
	} catch (Exception e) {
	    log.info(":::::: " + e.getMessage() + " ::::: " + e.getCause()
		    + " ...", new MSSPException("Property Files Not Found"));
	    // TODO: handle exception
	}
    }

    public static String getProperty(String name) {
	return propertiesMap.get(name);
    }

    /**
     * 
     * Description : It will return map with same key and value if only key
     * passed. Fetching from cache after first time fetch& store
     * 
     * 
     * Method Name : getMapConfigKeyValue Input& Output:
     * 
     * @param key
     * @return Map of same key and values
     */
    public static Map<String, String> getMapConfigKeyValue(String key) {
	return getMapConfigKeyValue(key, false);

    }

    /**
     * 
     * Description : It will return list with same value
     * 
     * Method Name : getListConfigKeyValue Input& Output:
     * 
     * @param key
     * @return Map of same key and values
     */
    public static List<String> getListConfigKeyValue(String key) {
	String configValue = ApplicationPropertiesUtil.getProperty(key);
	List<String> list = null;
	if (null != configValue)// Value found in config file
	{
	    STRING_DELIMITER = ApplicationPropertiesUtil
		    .getProperty(ADR_STRING_DELIMITER);
	    String[] configValues = configValue.split(STRING_DELIMITER);
	    list = new LinkedList<String>();
	    for (int countr = 0; countr < configValues.length; countr++) {
		list.add(configValues[countr]);
	    }
	}
	return list;

    }

    /**
     * 
     * Description : It will return map with same key or numeric(Starting from
     * One) key value based on passed parameter. Fetching from cache after first
     * time fetch& store 
     * Method Name : getMapConfigKeyValue 
     * Input& Output:
     * 
     * @param key
     * @param isNumericKeyValReqrd : True will return numeric key and values
     * @return Map of same key and values if false passed else numeric key and
     *         value
     */
    public static Map<String, String> getMapConfigKeyValue(String key,
	    boolean isNumericKeyValReqrd) {
	return getMapConfigKeyValue(key, isNumericKeyValReqrd, false);
    }

    /**
     * 
     * Description : It will return map with same key or numeric(Starting from
     * One) key value based on passed parameter 
     * Method Name :
     * getMapConfigKeyValue Input& Output:
     * 
     * @param key
     * @param isNumericKeyValReqrd
     *            : True will return numeric with values else same key& values
     * @param isDirectConfigValRequired
     *            : True flag value will fetch config value directly by passing
     *            caching mechanism
     * @return Map of same key and values if false passed else numeric key and
     *         value
     */
    public static Map<String, String> getMapConfigKeyValue(String key,
	    boolean isNumericKeyValReqrd, boolean isDirectConfigValRequired) {
	String configValue = ApplicationPropertiesUtil.getProperty(key);
	Map<String, String> keyValueMap = null;
	if (null != configValue)// Value found in config file
	{
	    STRING_DELIMITER = ApplicationPropertiesUtil
		    .getProperty(ADR_STRING_DELIMITER);
	    // it will fetch direct config value without touching caching
	    // mechanism
	    if (isDirectConfigValRequired) {
		String[] configValues = configValue.split(STRING_DELIMITER);
		return loadConfigValues(configValues, isNumericKeyValReqrd);
	    }
	    if (null == loadedMapValues) // If not created yet for caching
	    {

		String[] configValues = configValue.split(STRING_DELIMITER);
		loadedMapValues = new LinkedHashMap<String, Map<String, String>>(
			10);
		keyValueMap = loadConfigValues(configValues,
			isNumericKeyValReqrd);
		loadedMapValues.put(key, keyValueMap);
		// return keyValueMap;
	    } else// Config values Cached
	    {
		String[] configValues = configValue.split(STRING_DELIMITER);
		keyValueMap = loadedMapValues.get(key);
		if (null == keyValueMap) {// Key map already not created yet
		    keyValueMap = loadConfigValues(configValues,
			    isNumericKeyValReqrd);
		    loadedMapValues.put(key, keyValueMap);
		    // return keyValueMap;
		}
	    }

	}
	return keyValueMap;

    }

    /**
     * 
     * Description : Based on config value and numeric key required flag value
     * it will return key value hashmap Method Name : loadConfigValues Input&
     * Output:
     * 
     * @param configValues
     * @param isNumericKeyValReqrd
     * @return
     */
    private static Map<String, String> loadConfigValues(String[] configValues,
	    boolean isNumericKeyValReqrd) {
	Map<String, String> keyValueMap = new LinkedHashMap<String, String>(5);
	if (!isNumericKeyValReqrd) {
	    for (int countr = 0; countr < configValues.length; countr++) {
		keyValueMap.put(configValues[countr], configValues[countr]);
	    }
	} else {
	    for (int countr = 0; countr < configValues.length; countr++) {
		keyValueMap.put("" + (countr + 1), configValues[countr]);
	    }
	}
	return keyValueMap;
    }

    /**
     * 
     * @param sValue
     * @param format
     * @return
     */
    public static Date string2Date(String strDate) {

	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	Date newdate = null;
	if (null != strDate && !"".equals(strDate)) {
	    try {
		newdate = df.parse(strDate);
	    } catch (ParseException e) {
		e.printStackTrace();
	    }
	}

	return newdate;

    }

    public static String date2string(Date date) {
	if (date != null) {
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    String formattedDate = sdf.format(date);
	    return formattedDate;
	} else
	    return "";
    }
}
