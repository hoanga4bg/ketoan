package com.htttql.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.Accountant;
import com.htttql.entity.Income;
import com.htttql.entity.RevenueStatistics;

public interface IncomeRepository extends JpaRepository<Income, Integer> {
	Income findOneById(Integer id);
	List<Income> findByCreateDateBetween(Date startDate, Date endDate);
	List<Income> findByCreateBy(Accountant acc);

}
