package com.htttql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.SampleReport;

public interface SampleRepository extends JpaRepository<SampleReport, Integer>{

	public List<SampleReport> findByName(String string);
	public SampleReport findOneById(int id);
	public List<SampleReport> findByStatus(boolean b);

}
