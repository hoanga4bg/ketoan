package com.htttql.service;

import java.util.List;

import com.htttql.entity.ExpenseStatistic;
import com.htttql.entity.*;
public interface ExpenseDAO extends GeneralService<ExpenseStatistic>{
	public Double getTotalReceiptByMonthAndYear(int month, int year);
	public Double getTotalSalaryHistoryByMonthAndYear(int month, int year);
	public ExpenseStatistic getByMonthAndYear(int month,int year);
}
