package com.htttql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.Report;
import com.htttql.entity.SampleReport;

public interface ReportRepository extends JpaRepository<Report, Integer>{

	public List<Report> findByType(SampleReport s);

}
