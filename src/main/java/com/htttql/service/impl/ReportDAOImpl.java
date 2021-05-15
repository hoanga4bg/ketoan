package com.htttql.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		return reportRepo.findAll();
	}
	
	@Override
	public Report save(Report t) {
		return reportRepo.save(t);
	}

	@Override
	public Report findOneById(int id) {
		Report report= reportRepo.findOneById(id); 
	
		return report;
	}

	@Override
	public void delete(int id) {
		reportRepo.deleteById(id);
		
	}

	@Override
	public Report update(int id, Report t) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/*
	 * Lấy danh sách thể loại hóa đơn
	 * 
	 */
	@Override
	public List<String> findAllName() {
		
		List<String> list=new ArrayList<String>();
		List<SampleReport> slist=new ArrayList<SampleReport>();
		slist=findAllActive();
		for(SampleReport s:slist) {
			list.add(s.getName());
		}
		return list;
	}

	@Override
	public SampleReport findByName(String name) {
		List<SampleReport> slist=new ArrayList<SampleReport>();
		slist=sampleRepo.findByName(name);
		System.out.print(slist.size());
		return slist.get(0);
	}

	@Override
	public List<Report> findByType(SampleReport sample) {
		List<Report> list=reportRepo.findByType(sample);
		
		return list;
	}

	@Override
	public List<SampleReport> findAllActive() {
		List<SampleReport> list=sampleRepo.findByStatus(false);
		
		return list;
	}







}
