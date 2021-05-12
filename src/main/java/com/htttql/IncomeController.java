package com.htttql;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.htttql.entity.Income;
import com.htttql.entity.RevenueStatistics;
import com.htttql.service.AbstractDAO;
import com.htttql.service.BillDAO;
import com.htttql.service.DateDAO;
import com.htttql.service.IncomeDAO;

@Controller
public class IncomeController {
	@Autowired
	private IncomeDAO incomeDAO;
	@Autowired
	private DateDAO dateDAO;
	@Autowired
	private BillDAO billDAO;
	@Autowired
	private AbstractDAO abDAO;
	
	@RequestMapping(value = "/income",method = RequestMethod.GET)
	public String findAll(Model model) {
		List<Income> incomes = new ArrayList<Income>();
		incomes = incomeDAO.findAll();
		model.addAttribute("incomes", incomes);
		return "income/display";
	}
	
	@RequestMapping(value = "/income/incomenow",method = RequestMethod.GET)
	public String createNewIncome(Model model) {
		LocalDate todaydate = LocalDate.now();
		LocalDate fisrtdate = todaydate.withDayOfMonth(1);
		LocalDate endDate = todaydate.withDayOfMonth(todaydate.lengthOfMonth());
		Date enddate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date startdate = Date.from(fisrtdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date todate = Date.from(todaydate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		double saleprice = 0;
		List<Income> incomes = new ArrayList<Income>();
		incomes = incomeDAO.findByCreateDateBetween(startdate, enddate);
		if(incomes.size() != 0) {
			for (Income income : incomes) {
				if(((income.getCreateDate().compareTo(startdate) == 1) && (income.getCreateDate().compareTo(enddate) == -1)) || (income.getCreateDate().compareTo(startdate) == 0) || (income.getCreateDate().compareTo(enddate) == 0)) {
					saleprice = billDAO.revenueByMonthNow();
					income.setTotal(saleprice);
					income.setCreateDate(todate);
					incomeDAO.save(income);
				}
				
			}
			
		}
		else {
			Income income = new Income();
			saleprice = billDAO.revenueByMonthNow();
			income.setTotal(saleprice);
			income.setCreateDate(todate);
			income.setCreateBy(abDAO.getAccountant());
			income.setMonth(todate.getMonth()+1);
			income.setYear(todate.getYear()+1900);
			incomeDAO.save(income);
		}
		return "redirect:/income";
	}

}
