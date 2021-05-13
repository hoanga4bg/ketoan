package com.htttql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.htttql.entity.Report;
import com.htttql.service.ReportDAO;

@Controller
@RequestMapping("report")
public class ReportController {
	
	@Autowired
	private ReportDAO reportDAO;
	@GetMapping("/finance")
	public String financeReport(Model model, @RequestParam("type") String type) {
		List<Report> list=new ArrayList<Report>();
		if(type.equals("month")) {
			list=reportDAO.findMonthFinance();
		}
		else {
			list=reportDAO.findYearFinance();
		}
		model.addAttribute("list",list);
		return "report/financeReport";
	}
}
