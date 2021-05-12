package com.htttql.service;

import java.util.Date;
import java.util.List;

import com.htttql.entity.Accountant;
import com.htttql.entity.RevenueStatistics;

public interface RevenueStatisticsDAO extends GeneralService<RevenueStatistics> {
	public int countByDate();
	List<RevenueStatistics> findByCreateBy(Accountant acc);

}
