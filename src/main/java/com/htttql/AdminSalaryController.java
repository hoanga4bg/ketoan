package com.htttql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.htttql.entity.Salary;
import com.htttql.repository.SalaryRepository;
import com.htttql.service.SalaryDAO;

@Controller
public class AdminSalaryController {
	@Autowired
	private SalaryDAO salaryDAO;
	@Autowired
	private SalaryRepository salaryRepository;
	
	@RequestMapping(value = "/admin/create/salary",method = RequestMethod.GET)
	public String createNewSalary(Model model) {
		Salary salary = new Salary();
		model.addAttribute("salary", salary);
		model.addAttribute("status", 1);
		return "admin/salary/add";
	}
	
	@RequestMapping(value = "/admin/save/salary",method = RequestMethod.POST)
	public String SaveSalary(Salary salary) {
		salary.setStatus(false);
		System.out.print(salary.getNameStaff());
		salaryRepository.save(salary);
		return "redirect:/admin/staff";
	}
	@RequestMapping(value = "/admin/staff",method = RequestMethod.GET)
	public String findAll(Model model) {
		List<Salary> salarys = new ArrayList<Salary>();
		salarys = salaryRepository.findAll();
		model.addAttribute("salarys", salarys);
		return "admin/salary/display";
	}
	
	@RequestMapping(value = "/admin/staff/off",method = RequestMethod.GET)
	public String setOffStaff(@RequestParam("id") String id) {
		Salary salary = salaryRepository.findOneById(Integer.parseInt(id));
		salary.setStatus(true);
		salaryRepository.save(salary);
		return "redirect:/admin/staff";
	}
	
	@RequestMapping(value = "/admin/staff/on",method = RequestMethod.GET)
	public String setOnStaff(@RequestParam("id") String id) {
		Salary salary = salaryRepository.findOneById(Integer.parseInt(id));
		salary.setStatus(false);
		salaryRepository.save(salary);
		return "redirect:/admin/staff";
	}
	
	@RequestMapping(value = "/admin/staff/edit",method = RequestMethod.GET)
	public String edit(@RequestParam("id") String id, Model model) {
		Salary salary = salaryRepository.findOneById(Integer.parseInt(id));
		model.addAttribute("salary", salary);
		model.addAttribute("status", 0);
		return "admin/salary/add";
	}
	

}
