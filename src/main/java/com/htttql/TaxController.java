package com.htttql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.htttql.entity.Tax;
import com.htttql.entity.TaxStatistic;
import com.htttql.repository.TaxRepository;
import com.htttql.service.AbstractDAO;
import com.htttql.service.TaxStatisticDAO;

@Controller
@RequestMapping("/tax")
public class TaxController {
	
	@Autowired
	private TaxStatisticDAO taxDAO;
	
	@Autowired
	private TaxRepository taxRepo;
	
	@Autowired
	private AbstractDAO abstractDAO;
	
	
	@GetMapping("/vat")
	public String vatHome(Model model) {
		Date d=new Date();
		int month=d.getMonth()+1;
		int year=d.getYear()+1900;
		Tax t=taxRepo.findOneByName("VAT");
		List<TaxStatistic> list=taxDAO.findByTax(t);
		TaxStatistic ts=taxDAO.findOneByTaxAndMonthAndYear(t, month, year);
		Collections.reverse(list);
		if(ts==null) {
			model.addAttribute("mode", true);
		}
		else {
			model.addAttribute("mode", false);
		}
		model.addAttribute("list",list);
		return "tax/VAT";
	}
	
	@GetMapping("/tncn")
	public String tncnHome(Model model) {
		Date d=new Date();
		int month=d.getMonth()+1;
		int year=d.getYear()+1900;
		Tax t=taxRepo.findOneByName("TNCN");
		List<TaxStatistic> list=taxDAO.findByTax(t);
		TaxStatistic ts=taxDAO.findOneByTaxAndMonthAndYear(t, month, year);
		Collections.reverse(list);
		if(ts==null) {
			model.addAttribute("mode", true);
		}
		else {
			model.addAttribute("mode", false);
		}
		model.addAttribute("list",list);
		return "tax/tncn";
	}
	
	@GetMapping("/vat/add")
	public String vatAdd(Model model) {
		Date date=new Date();
		TaxStatistic tax=new TaxStatistic();
		int month=date.getMonth()+1;
		int year=date.getYear()+1900;
		tax.setCreateBy(abstractDAO.getAccountant());
		tax.setCreateDate(date);
		tax.setMonth(month);
		tax.setYear(year);
		tax.setTotal(taxDAO.vatCal(month, year));
		tax.setTax(taxRepo.findOneByName("VAT"));
		taxDAO.save(tax);
		return "redirect:/tax/vat";
	}
	
	@GetMapping("/tncn/add")
	public String tncnAdd(Model model) {
		Date date=new Date();
		TaxStatistic tax=new TaxStatistic();
		int month=date.getMonth()+1;
		int year=date.getYear()+1900;
		tax.setCreateBy(abstractDAO.getAccountant());
		tax.setCreateDate(date);
		tax.setMonth(month);
		tax.setYear(year);
		Double total=taxDAO.tncnCal(month, year);
		System.out.println(total);
		tax.setTotal(total);
		tax.setTax(taxRepo.findOneByName("TNCN"));
		taxDAO.save(tax);
		return "redirect:/tax/tncn";
	}
	
	@GetMapping("/tncn/edit")
	public String tncnEdit(Model model,@RequestParam("id") String id) {
		TaxStatistic tax=taxDAO.findOneById(Integer.parseInt(id));
		int month=tax.getMonth();
		int year=tax.getYear();
		Double total=taxDAO.tncnCal(month, year);

		tax.setTotal(total);
		taxDAO.save(tax);
		return "redirect:/tax/tncn";
	}
	
	@GetMapping("/vat/edit")
	public String vatEdit(Model model,@RequestParam("id") String id) {
		TaxStatistic tax=taxDAO.findOneById(Integer.parseInt(id));
		int month=tax.getMonth();
		int year=tax.getYear();
		Double total=taxDAO.vatCal(month, year);

		tax.setTotal(total);
		taxDAO.save(tax);
		return "redirect:/tax/vat";
	}
	
}
