package com.htttql.service;

import java.util.List;

import com.htttql.entity.TaxStatistic;

public interface TaxStatisticDAO extends GeneralService<TaxStatistic>{
	public List<TaxStatistic> findById(Integer id);
	
	public Double vatCal(int month,int year);
	public Double tncnCal(int month,int year);
}
