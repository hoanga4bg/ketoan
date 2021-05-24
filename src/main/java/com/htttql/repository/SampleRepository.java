package com.htttql.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.SampleReport;

public interface SampleRepository extends JpaRepository<SampleReport, Integer>{
	@Transactional
	public List<SampleReport> findByName(String string);
	@Transactional
	public SampleReport findOneById(int id);
	@Transactional
	public List<SampleReport> findByStatus(boolean b);

}
