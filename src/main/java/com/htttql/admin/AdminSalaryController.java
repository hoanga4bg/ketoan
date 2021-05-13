package com.htttql.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		return "admin/salary/add";
	}
	
	@RequestMapping(value = "/admin/save/salary",method = RequestMethod.POST)
	public String SaveSalary(Salary salary) {
		salary.setStatus(false);
		System.out.print(salary.getNameStaff());
		salaryRepository.save(salary);
		return "redirect:/admin/create/salary";
	}

}
