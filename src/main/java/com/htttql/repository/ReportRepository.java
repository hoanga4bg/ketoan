package com.htttql.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.Report;
import com.htttql.entity.SampleReport;

public interface ReportRepository extends JpaRepository<Report, Integer>{
	@Transactional
	public List<Report> findByType(SampleReport s);
	@Transactional
	public Report findOneById(int id);

}
