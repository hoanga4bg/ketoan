package com.htttql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htttql.entity.Report;
import com.htttql.entity.SampleReport;
import com.htttql.repository.ReportRepository;
import com.htttql.repository.SampleRepository;
import com.htttql.service.ReportDAO;


@Service
public class ReportDAOImpl implements ReportDAO{
	
	@Autowired
	private ReportRepository reportRepo;
	@Autowired
	private SampleRepository sampleRepo;
	@Override
	public List<Report> findAll() {
		return null;
	}

	@Override
	public Report save(Report t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Report findOneById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Report update(int id, Report t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> findMonthFinance() {
		SampleReport s=sampleRepo.findByName("TCT").get(0);
		List<Report> list=reportRepo.findByType(s);
		return list;
	}

	@Override
	public List<Report> findYearFinance() {
		SampleReport s=sampleRepo.findByName("TCN").get(0);
		List<Report> list=reportRepo.findByType(s);
		return list;
	}
	


}
