package com.htttql.service;

import java.util.List;

import com.htttql.entity.Tax;
import com.htttql.entity.TaxStatistic;

public interface TaxStatisticDAO extends GeneralService<TaxStatistic>{
	public List<TaxStatistic> findByTax(Tax t);
	
	public Double vatCal(int month,int year);
	public Double tncnCal(int month,int year);
	
	public TaxStatistic findOneByTaxAndMonthAndYear(Tax t,int month,int year);
}
