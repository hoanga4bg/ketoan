package com.htttql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.ExpenseStatistic;

public interface ExpenseStatisticRepository extends JpaRepository<ExpenseStatistic, Integer>{

	public ExpenseStatistic findOneById(int id);
	public ExpenseStatistic findOneByMonthAndYear(int month,int year);
}
