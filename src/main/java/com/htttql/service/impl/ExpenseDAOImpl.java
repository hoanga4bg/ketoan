package com.htttql.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htttql.entity.ExpenseStatistic;
import com.htttql.entity.HistorySalary;
import com.htttql.entity.Receipt;
import com.htttql.repository.ExpenseStatisticRepository;
import com.htttql.repository.HistorySalaryRepository;
import com.htttql.repository.ReceiptRepository;
import com.htttql.service.ExpenseDAO;
@Service
public class ExpenseDAOImpl implements ExpenseDAO{

	@Autowired
	private ExpenseStatisticRepository expenseRepo;
	@Autowired
	private HistorySalaryRepository hisRepository;
	@Autowired
	private ReceiptRepository receiptRepo;
	@Override
	public List<ExpenseStatistic> findAll() {
		List<ExpenseStatistic> list=expenseRepo.findAll();
		return list;
	}

	@Override
	public ExpenseStatistic save(ExpenseStatistic t) {
		ExpenseStatistic e=expenseRepo.save(t);
		return e;
	}

	@Override
	public ExpenseStatistic findOneById(int id) {
		ExpenseStatistic e=expenseRepo.findOneById(id);
		return e;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ExpenseStatistic update(int id, ExpenseStatistic t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getTotalReceiptByMonthAndYear(int month, int year) {
		Date date=new Date();
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
		Double total=0.0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		List<Receipt> relist=new ArrayList<Receipt>();
		try {
			Date sDate=sdf.parse(startDate);
			Date eDate=sdf.parse(endDate);
			relist=receiptRepo.findByCreatedDateBetween(sDate, eDate);
			for(Receipt r:relist) {
				total+=r.getTotalPrice();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public Double getTotalSalaryHistoryByMonthAndYear(int month, int year) {
		Date date=new Date();
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
		Double total=0.0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		List<HistorySalary> hist=new ArrayList<HistorySalary>();
		try {
			Date sDate=sdf.parse(startDate);
			Date eDate=sdf.parse(endDate);
			hist=hisRepository.findByReceiveDateBetween(sDate, eDate);
			for(HistorySalary h:hist) {
				total+=h.getMoney();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}
	
	@Override
	public ExpenseStatistic getByMonthAndYear(int month, int year) {
	
		return expenseRepo.findOneByMonthAndYear(month, year);
	}

}
