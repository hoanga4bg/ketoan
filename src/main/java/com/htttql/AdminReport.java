package com.htttql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.htttql.entity.Report;
import com.htttql.entity.SampleReport;
import com.htttql.service.AbstractDAO;
import com.htttql.service.ReportDAO;

@Controller
@RequestMapping("/admin/report")
public class AdminReport {
	@Autowired
	private ReportDAO reportDAO;
	@Autowired
	private AbstractDAO abstractDAO;
	@GetMapping
	public String homeReport(Model model) {
		List<Report> list=new ArrayList<Report>();
		list=reportDAO.findAll();
		List<String> typelist=new ArrayList<String>();
		typelist=reportDAO.findAllName();
//		System.out.println(typelist.toString());
		Collections.reverse(list);
		model.addAttribute("list",list);
		model.addAttribute("typelist", typelist);
		return "/admin/report/reportHome";
	}

	@GetMapping("/detail")
	public String detail(Model model, @RequestParam("id") String id) {
		Report report=reportDAO.findOneById(Integer.parseInt(id));
		
		model.addAttribute("report",report);
		return "/admin/report/detail";
	}
	@GetMapping("/delete")
	public String delete(Model model, @RequestParam("id") String id) {
		reportDAO.delete(Integer.parseInt(id));
		return "redirect:/admin/report";
	}
	@GetMapping("/search")
	public String search(Model model, @RequestParam("type") String type) {
		SampleReport s=reportDAO.findByName(type);
		List<Report> list=reportDAO.findByType(s);
		List<String> typelist=new ArrayList<String>();
		typelist=reportDAO.findAllName();
		Collections.reverse(list);
		model.addAttribute("list",list);
		model.addAttribute("typelist", typelist);
		return "/admin/report/reportHome";
	}
}
