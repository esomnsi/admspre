package com.ericsson.mssp.common.dto;

import java.util.Date;

public class MonthFTEDTO {

	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	private float fteCount;
	
	public float getFteCount() {
		return fteCount;
	}
	public void setFteCount(float fteCount) {
		this.fteCount = fteCount;
	}
	
	
}
