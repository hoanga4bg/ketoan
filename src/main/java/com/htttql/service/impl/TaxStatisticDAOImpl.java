package com.htttql.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htttql.entity.ExpenseStatistic;
import com.htttql.entity.Tax;
import com.htttql.entity.TaxStatistic;
import com.htttql.repository.BillRepository;
import com.htttql.repository.ExpenseStatisticRepository;
import com.htttql.repository.TaxRepository;
import com.htttql.repository.TaxStatisticRepository;
import com.htttql.service.BillDAO;
import com.htttql.service.ExpenseDAO;
import com.htttql.service.TaxStatisticDAO;


@Service
public class TaxStatisticDAOImpl implements TaxStatisticDAO{
	
	@Autowired
	private TaxStatisticRepository taxStaRepo;
	@Autowired
	private ExpenseStatisticRepository expenseRepo;
	@Autowired
	private TaxRepository taxRepo;
	@Autowired
	private BillDAO billDAO;
	@Autowired
	private ExpenseDAO eDAO;
	@Override
	public List<TaxStatistic> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaxStatistic save(TaxStatistic t) {
		TaxStatistic ts=taxStaRepo.save(t);
		return ts;
	}

	@Override
	public TaxStatistic findOneById(int id) {
		TaxStatistic t=taxStaRepo.findOneById(id);
		return t;
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
	public List<TaxStatistic> findByTax(Tax t) {
		List<TaxStatistic> list=new ArrayList<TaxStatistic>();
		list=taxStaRepo.findByTax(t);
		
		return list;
	}

	@Override
	public Double vatCal(int month, int year) {
		Tax t=taxRepo.findByName("VAT").get(0);
		Double salePrice=billDAO.salePriceByMonth(month, year);
	
		Double total=0.0;
			total=	t.getCoefficient()*salePrice;
	
		return total;
	}

	@Override
	public Double tncnCal(int month, int year) {
		
		
		Tax t=taxRepo.findByName("TNCN").get(0);
	
		Double total=0.0;
		total=eDAO.getTotalSalaryHistoryByMonthAndYear(month, year)*t.getCoefficient();
		System.out.println(total);
		return total;
	}

	@Override
	public TaxStatistic findOneByTaxAndMonthAndYear(Tax t,int month, int year) {
		return taxStaRepo.findOneByTaxAndMonthAndYear(t,month,year);
	}

}
