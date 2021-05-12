package com.htttql.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.htttql.entity.HistorySalary;
import com.htttql.entity.Salary;

public interface HistorySalaryRepository extends JpaRepository<HistorySalary, Integer>{
	public HistorySalary findOneBySalaryAndReceiveDateBetween(Salary salary,Date startDate, Date endDate);
	List<HistorySalary> findByReceiveDateBetween(Date startDate,Date endDate);
}
