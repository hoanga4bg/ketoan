package com.htttql.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.htttql.entity.HistorySalary;
import com.htttql.service.DateDAO;
@Service
public class DateDAOImpl implements DateDAO {

	@Override
	public Date getFirstDayOfMonthNow() {
		Date date=new Date();
		int month=date.getMonth()+1;
		int year=date.getYear()+1900;
		String startDate="";
		if(month<10) {
			startDate+=(year+"-0"+month+"-01");
		}
		else {
			startDate+=(year+"-"+month+"-01");
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date sDate=sdf.parse(startDate);
			return sDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Date getEndDayOfMonthNow() {
		Date date=new Date();
		int month=date.getMonth()+1;
		int year=date.getYear()+1900;
		String endDate="";
		if(month<10) {
			endDate+=(year+"-0"+month+"-30");
		}
		else {
			endDate+=(year+"-"+month+"-30");
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		HistorySalary h=new HistorySalary();
		try {
			Date eDate=sdf.parse(endDate);
			return eDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Date getEndDayOfMonth(int month, int year) {
		String endDate="";
		if(month<10) {
			endDate+=(year+"-0"+month+"-30");
		}
		else {
			endDate+=(year+"-"+month+"-30");
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		HistorySalary h=new HistorySalary();
		try {
			Date eDate=sdf.parse(endDate);
			return eDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
