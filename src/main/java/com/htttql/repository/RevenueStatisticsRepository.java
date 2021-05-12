package com.htttql.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.htttql.entity.Accountant;
import com.htttql.entity.RevenueStatistics;

public interface RevenueStatisticsRepository extends JpaRepository<RevenueStatistics, Integer>{
	RevenueStatistics findOneById(int id);
	List<RevenueStatistics> findByCreateBy(Accountant acc);
	
	@Query(value = "SELECT count(create_date) FROM ketoan.revenue_statistics where create_date >= ?1 and create_date <= ?2 ",nativeQuery = true)
	int countByDate(Date date1,Date date2);

}
