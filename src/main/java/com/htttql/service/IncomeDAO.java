package com.htttql.service;

import java.util.Date;
import java.util.List;

import com.htttql.entity.Accountant;
import com.htttql.entity.Income;

public interface IncomeDAO extends GeneralService<Income> {
	List<Income> findByCreateDateBetween(Date startDate, Date endDate);
	List<Income> findByCreateBy(Accountant acc);

}
