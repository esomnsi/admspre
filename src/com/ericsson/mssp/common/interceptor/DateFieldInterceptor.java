package com.ericsson.mssp.common.interceptor;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class DateFieldInterceptor extends EmptyInterceptor {

    private static final long serialVersionUID = -8374988621501998008L;

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {
    	return formatDate(state, propertyNames);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id,
            Object[] currentState, Object[] previousState,
            String[] propertyNames, Type[] types) {
    	return formatDate(currentState, propertyNames);
    }
    
    private boolean formatDate(Object[] currentState, String[] propertyNames) {
System.out.println("Inside");
        for(int i=0;i<propertyNames.length;i++) {
            if("monthYear".equals(propertyNames[i])) {
                Object currentDate = currentState[i];
System.out.println("Before: "+currentDate);                
                Calendar cal = Calendar.getInstance();
                cal.setTime((Date)currentDate);
                currentState[i] = cal.getTime();
System.out.println("After: "+currentState[i]);
            }
        }
        return true;
    }
}
