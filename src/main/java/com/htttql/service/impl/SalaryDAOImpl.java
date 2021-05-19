package com.htttql.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.htttql.entity.Account;
import com.htttql.entity.Accountant;
import com.htttql.entity.HistorySalary;
import com.htttql.entity.RevenueStatistics;
import com.htttql.entity.Salary;
import com.htttql.entity.Tax;
import com.htttql.repository.AccountRepository;
import com.htttql.repository.HistorySalaryRepository;
import com.htttql.repository.SalaryRepository;
import com.htttql.repository.TaxRepository;
import com.htttql.service.AbstractDAO;
import com.htttql.service.DateDAO;
import com.htttql.service.SalaryDAO;


@Service
public class SalaryDAOImpl implements SalaryDAO {

	@Autowired
	private SalaryRepository salaryRepo;
	
	@Autowired
	private HistorySalaryRepository hisRepository;
	
	@Autowired
	private AbstractDAO abstractDAO;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private DateDAO dateDAO;
	@Autowired
	private TaxRepository taxRepo;
	@Override
	public void addSalary(Salary salary) {
		
		salaryRepo.save(salary);
	}
	@Override
	public void deleteSalary(Integer id) {
		salaryRepo.deleteById(id);
		
	}
	@Override
	public void pay(Salary salary) {
		String username=abstractDAO.getUsername();
		Accountant accountant=accountRepo.findOneByUserName(username).getAccountant();
		HistorySalary hist=new HistorySalary();

		hist.setAccountant(accountant);
		hist.setReceiveDate(new Date());
		hist.setSalary(salary);
		hist.setMoney(salary.getBasicSalary());
		hist.setTax(0.0);
		hisRepository.save(hist);
	}
	@Override
	public List<Salary> findAll() {
		List<Salary> list=salaryRepo.findByStatus(false);
		System.out.println(list.size());
		return list;
	}
	@Override
	public Salary findById(Integer id) {
	
		return salaryRepo.findOneById(id);
	}
	@Override
	public HistorySalary getInMonth(Salary salary) {
		Date date=new Date();
		int month=date.getMonth()+1;
		int year=date.getYear()+1900;
		String startDate="";
		String endDate="";
		if(month<10) {
			startDate+=(year+"-0"+month+"-01");
			endDate+=(year+"-0"+month+"-30");
		}
		else {
			startDate+=(year+"-"+month+"-01");
			endDate+=(year+"-"+month+"-30");
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		HistorySalary h=new HistorySalary();
		try {
			Date sDate=sdf.parse(startDate);
			Date eDate=sdf.parse(endDate);
			h=hisRepository.findOneBySalaryAndReceiveDateBetween(salary, sDate, eDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return h;
	}
	@Override
	public double getTotalSalaryOfMonthNow() {
		Date startDate = dateDAO.getFirstDayOfMonthNow();
		Date endDate = dateDAO.getEndDayOfMonthNow();
		List<HistorySalary> his = new ArrayList<HistorySalary>();
		his = hisRepository.findByReceiveDateBetween(startDate, endDate);
		double totalPrice = 0;
		for (HistorySalary hi : his) {
			totalPrice += hi.getMoney();
		}
		return totalPrice;
	}
	@Override
	public List<Salary> findByPositionAndStatus(boolean status, String position) {
		List<Salary> list=new ArrayList<Salary>();
		list=salaryRepo.findByStatusAndPosition(status,position);
		return list;
	}
	@Override
	public List<HistorySalary> getAllInMonth(int month, int year) {
		String startDate="";
		String endDate="";
		if(month<10) {
			startDate+=(year+"-0"+month+"-01");
			endDate+=(year+"-0"+month+"-30");
		}
		else {
			startDate+=(year+"-"+month+"-01");
			endDate+=(year+"-"+month+"-30");
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		List<HistorySalary> h=new ArrayList<HistorySalary>();
		try {
			Date sDate=sdf.parse(startDate);
			Date eDate=sdf.parse(endDate);
			h=hisRepository.findByReceiveDateBetween(sDate, eDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return h;
	}
	
	
	
	
	
}
