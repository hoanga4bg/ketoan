package com.htttql.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htttql.entity.TaxStatistic;
import com.htttql.repository.ExpenseStatisticRepository;
import com.htttql.repository.TaxStatisticRepository;
import com.htttql.service.TaxStatisticDAO;


@Service
public class TaxStatisticDAOImpl implements TaxStatisticDAO{
	
	@Autowired
	private TaxStatisticRepository taxStaRepo;
	
	private ExpenseStatisticRepository expenseRepo;
	@Override
	public List<TaxStatistic> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaxStatistic save(TaxStatistic t) {
		
		return null;
	}

	@Override
	public TaxStatistic findOneById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TaxStatistic update(int id, TaxStatistic t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaxStatistic> findById(Integer id) {
		List<TaxStatistic> list=new ArrayList<TaxStatistic>();
		list=taxStaRepo.findOneById(id);
		
		return list;
	}

	@Override
	public Double vatCal(int month, int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double tncnCal(int month, int year) {
		// TODO Auto-generated method stub
		return null;
	}

}
