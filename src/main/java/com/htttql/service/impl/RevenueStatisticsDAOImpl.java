package com.htttql.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htttql.entity.Accountant;
import com.htttql.entity.RevenueStatistics;
import com.htttql.repository.RevenueStatisticsRepository;
import com.htttql.service.DateDAO;
import com.htttql.service.RevenueStatisticsDAO;

@Service
public class RevenueStatisticsDAOImpl implements RevenueStatisticsDAO {
	@Autowired
	private RevenueStatisticsRepository reve;
	
	@Autowired
	private DateDAO dateDAO;

	@Override
	public List<RevenueStatistics> findAll() {
		// TODO Auto-generated method stub
		return reve.findAll();
	}

	@Override
	public RevenueStatistics save(RevenueStatistics t) {
		// TODO Auto-generated method stub
		return reve.save(t);
	}

	@Override
	public RevenueStatistics findOneById(int id) {
		// TODO Auto-generated method stub
		return reve.findOneById(id);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		reve.delete(reve.findOneById(id));
	}

	@Override
	public RevenueStatistics update(int id, RevenueStatistics t) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<RevenueStatistics> findByCreateBy(Accountant acc) {
		// TODO Auto-generated method stub
		return reve.findByCreateBy(acc);
	}

	@Override
	public List<RevenueStatistics> findByCreateDateBetween(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return reve.findByCreateDateBetween(startDate, endDate);
	}


}
