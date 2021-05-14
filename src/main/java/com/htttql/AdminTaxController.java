package com.htttql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.htttql.entity.Tax;
import com.htttql.repository.TaxRepository;

@Controller
public class AdminTaxController {

	@Autowired
	private TaxRepository taxRepository;
	
	@RequestMapping(value = "/admin/tax",method = RequestMethod.GET)
	public String findAll(Model model) {
		List<Tax> taxs = new ArrayList<Tax>();
		taxs = taxRepository.findAll();
		model.addAttribute("taxs", taxs);
	
		return "admin/tax/display";
		
	}
	
	@RequestMapping(value = "/admin/createtax",method = RequestMethod.GET)
	public String createNewTax(Model model) {
		Tax tax = new Tax();
		model.addAttribute("tax", tax);
		return "admin/tax/form";
	}
	
	@RequestMapping(value = "/admin/createtax/save",method = RequestMethod.POST)
	public String saveTax(Tax tax) {
		taxRepository.save(tax);
		return "redirect:/admin/tax";
	}
	
	@RequestMapping(value = "/admin/tax/edit/form",method = RequestMethod.GET)
	public String EditTax(@RequestParam("id") String id,Model model) {
		Tax tax = taxRepository.findOneById(Integer.parseInt(id));
		model.addAttribute("tax", tax);
		return "admin/tax/form";
	}
	
	@RequestMapping(value = "/admin/createtax/delete",method = RequestMethod.GET)
	public String deleteTax(@RequestParam("id") String id) {
		taxRepository.delete(taxRepository.findOneById(Integer.parseInt(id)));
		return "redirect:/admin/tax";
	}
}
