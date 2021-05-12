package com.htttql.service;

import java.util.Date;

import com.htttql.entity.RevenueStatistics;

public interface RevenueStatisticsDAO extends GeneralService<RevenueStatistics> {
	public int countByDate();

}
