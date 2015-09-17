/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.common.exception
 * File name       		    :  MSSPException.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Dec 13, 20122012
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Dec 13, 2012  	   |eruvwyn   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.common.exception;

import java.io.PrintStream;

/**
 * @author eruvwyn
 * 
 */
public class MSSPException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String addedInfo;
	
	public MSSPException(){
		
	}
	
	public MSSPException(String message){
		
		this.message = message;
	}

	public MSSPException(String message,  String addedInfo) {
		this.message = message;
		//this.initCause(initCause);
		this.addedInfo = addedInfo;
	}
	
	
	public MSSPException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * Default Constructor @param stringMess
	 * Default Constructor @param throwable
	 */
	public MSSPException(String stringMess,Throwable throwable) {
		super(stringMess,throwable);
	}
	public String toString() {
		String message = "ErrorDesc : " + this.message;
		if (this.addedInfo != null && !"".equals(this.addedInfo)) {
			message += ":" + this.addedInfo;
		}
		return message;
	}
	
	public String getMessage(){
		return message;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	@Override
	public String getLocalizedMessage() {
		// TODO Auto-generated method stub
		return super.getLocalizedMessage();
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#initCause(java.lang.Throwable)
	 */
	@Override
	public synchronized Throwable initCause(Throwable cause) {
		// TODO Auto-generated method stub
		return super.initCause(cause);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getCause()
	 */
	@Override
	public Throwable getCause() {
		// TODO Auto-generated method stub
		return super.getCause();
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace()
	 */
	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
	 */
	@Override
	public void printStackTrace(PrintStream printStream) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	
}
