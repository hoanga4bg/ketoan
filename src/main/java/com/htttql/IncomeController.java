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
import org.springframework.web.bind.annotation.RequestParam;

import com.htttql.entity.Accountant;
import com.htttql.entity.Income;
import com.htttql.entity.RevenueStatistics;
import com.htttql.repository.AccountantRepository;
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
	@Autowired 
	private AccountantRepository accountantRepository;
	
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
	
	@RequestMapping(value = "/incomes/delete",method = RequestMethod.GET)
	public String deleteReve(@RequestParam("id") String id) {
		incomeDAO.delete(Integer.parseInt(id));
		return "redirect:/income";
	}
	
	@RequestMapping(value = "/search/incomes",method = RequestMethod.GET)
	public String searchIncome(@RequestParam("name") String name,Model model) {
		if(name == "") {
			return "redirect:/income";
		}
		List<Accountant> accountants = new ArrayList<Accountant>();
		accountants = accountantRepository.findByName(name);
		List<Income> incomes = new ArrayList<Income>();
		for (Accountant acc : accountants) {
			List<Income> incomes1 = new ArrayList<Income>();
			incomes1 = incomeDAO.findByCreateBy(acc);
			for (Income income : incomes1) {
				incomes.add(income);
			}
		}
		
		model.addAttribute("incomes", incomes);
		
		return "income/display";
	}
	
	@RequestMapping(value = "/income/month",method = RequestMethod.GET)
	public String createIncomeByMonthAndYear(@RequestParam("month") String month,@RequestParam("year") String year) {
		if(month =="" || year == "") {
			return "redirect:/reve";
		}
		List<Income> incomes = new ArrayList<Income>();
		incomes = incomeDAO.findAll();
		int dem = 0;
		double saleprice = 0;
		for (Income income : incomes) {
			if(income.getCreateDate().getYear() + 1900 == Integer.parseInt(year)) {
				if(income.getCreateDate().getMonth()+1 == Integer.parseInt(month)) {
					dem ++;
				}
			}	
		}
		if(dem != 0) {
			return "redirect:/income";
		}
		else {
			Income income = new Income();
			
						saleprice = billDAO.salePriceByMonth(Integer.parseInt(month), Integer.parseInt(year));
						
						
						income.setTotal(saleprice);
						LocalDate initial = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 15);
						LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
						Date enddate = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
						income.setCreateDate(enddate);
						income.setMonth(Integer.parseInt(month));
						income.setYear(Integer.parseInt(year));
						income.setCreateBy(abDAO.getAccountant());
						incomeDAO.save(income);
						
	
			}
		return "redirect:/income";
	}

}

	