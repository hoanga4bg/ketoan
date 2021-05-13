package com.htttql.service;

import java.util.List;

import com.htttql.entity.Report;

public interface ReportDAO extends GeneralService<Report>{
	public List<Report> findMonthFinance();
	public List<Report> findYearFinance();
}
