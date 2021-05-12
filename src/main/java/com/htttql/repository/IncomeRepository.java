package com.htttql.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.Income;

public interface IncomeRepository extends JpaRepository<Income, Integer> {
	Income findOneById(Integer id);
	List<Income> findByCreateDateBetween(Date startDate, Date endDate);

}
