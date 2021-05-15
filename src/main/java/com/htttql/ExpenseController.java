package com.htttql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.htttql.entity.ExpenseStatistic;
import com.htttql.repository.AccountRepository;
import com.htttql.service.AbstractDAO;
import com.htttql.service.ExpenseDAO;

import java.util.Collections;
import java.util.Date;;

@Controller
@RequestMapping("/expense")
public class ExpenseController {
	
	@Autowired
	private ExpenseDAO expenseDAO;
	@Autowired
	private AbstractDAO abstractDAO;
	@GetMapping
	public String expenseHome(Model model) {
		Date date=new Date();
		int month=date.getMonth()+1;
		int year=date.getYear()+1900;
		ExpenseStatistic e=expenseDAO.getByMonthAndYear(month, year);
		
		if(e==null) {
			model.addAttribute("mode", true);
		
		}
		else {
			model.addAttribute("mode", false);
			
		}
		
		List<ExpenseStatistic> list=expenseDAO.findAll();
		Collections.reverse(list);
		Double total=0.0;
		for(ExpenseStatistic es:list) {
			total+=es.getTotal();
		}
		model.addAttribute("total", total);
		model.addAttribute("list",list);
		return "expense/expense";
	}
	
	@GetMapping("/detail")
	public String expenseDetail(Model model, @RequestParam("id") String id) {
		ExpenseStatistic e=expenseDAO.findOneById(Integer.parseInt(id));
		model.addAttribute("expense", e);
		String title="Chi phí tháng "+e.getMonth()+" năm "+e.getYear();
		model.addAttribute("title", title);
		return "expense/detail";
	}
	
	@GetMapping("/add")
	public String addExpense(Model model) {
		Date date=new Date();
		int month=date.getMonth()+1;
		int year=date.getYear()+1900;
		Double totalSalary=expenseDAO.getTotalSalaryHistoryByMonthAndYear(month, year);
		Double importTotal=expenseDAO.getTotalReceiptByMonthAndYear(month, year);
		Double otherTotal=expenseDAO.getTotalFeeByMonthAndYear(month, year);
		ExpenseStatistic expense=new ExpenseStatistic();
		expense.setCreateBy(abstractDAO.getAccountant());
		expense.setCreateDate(date);
		expense.setMonth(month);
		expense.setYear(year);
		expense.setImportTotal(importTotal);
		expense.setSalaryTotal(totalSalary);
		expense.setOtherTotal(otherTotal);
		expense.setTotal(totalSalary+importTotal+otherTotal);
		expenseDAO.save(expense);
		return "redirect:/expense";
	}
	
	@GetMapping("/edit")
	public String edit(Model model,@RequestParam("id") String id) {
		ExpenseStatistic e=expenseDAO.findOneById(Integer.parseInt(id));
		int month=e.getMonth();
		int year=e.getYear();
		Double totalSalary=expenseDAO.getTotalSalaryHistoryByMonthAndYear(month, year);
		Double importTotal=expenseDAO.getTotalReceiptByMonthAndYear(month, year);
		Double otherTotal=expenseDAO.getTotalFeeByMonthAndYear(month, year);
		e.setImportTotal(importTotal);
		e.setSalaryTotal(totalSalary);
		e.setOtherTotal(otherTotal);
		e.setTotal(totalSalary+importTotal+otherTotal);
		expenseDAO.save(e);
		return "redirect:/expense";
			
	}
}
