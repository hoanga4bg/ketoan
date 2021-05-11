package com.htttql.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.HistorySalary;
import com.htttql.entity.Salary;

public interface HistorySalaryRepository extends JpaRepository<HistorySalary, Integer>{
	public HistorySalary findOneBySalaryAndReceiveDateBetween(Salary salary,Date startDate, Date endDate);
}
