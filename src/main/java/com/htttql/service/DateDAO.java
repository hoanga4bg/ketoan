package com.htttql.service;

import java.util.Date;

public interface DateDAO {
	public Date getFirstDayOfMonthNow();
	public Date getEndDayOfMonthNow();
	public Date getEndDayOfMonth(int month,int year);
	

}
