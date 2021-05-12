package com.htttql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.Tax;
import com.htttql.entity.TaxStatistic;

public interface TaxStatisticRepository extends JpaRepository<TaxStatistic, Integer>{

	public List<TaxStatistic> findByTax(Tax t);

	public TaxStatistic findOneByTaxAndMonthAndYear(Tax t,int month, int year);

	public TaxStatistic findOneById(int id);

}
