package com.htttql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.htttql.entity.Tax;
import com.htttql.entity.TaxStatistic;
import com.htttql.repository.TaxRepository;
import com.htttql.service.TaxStatisticDAO;

@Controller
@RequestMapping("/tax")
public class TaxController {
	
	@Autowired
	private TaxStatisticDAO taxDAO;
	
	@Autowired
	private TaxRepository taxRepo;
	
	@GetMapping("/vat")
	public String vatHome(Model model) {
		Tax t=taxRepo.findOneByName("VAT");
		List<TaxStatistic> list=taxDAO.findById(t.getId());
		Collections.reverse(list);
		model.addAttribute("mode", true);
		model.addAttribute("list",list);
		return "tax/VAT";
	}
	
	@GetMapping("/tncn")
	public String tncnHome(Model model) {
		Tax t=taxRepo.findOneByName("TNCN");
		List<TaxStatistic> list=taxDAO.findById(t.getId());
		Collections.reverse(list);
		model.addAttribute("mode", true);
		model.addAttribute("list",list);
		return "tax/tncn";
	}
}
