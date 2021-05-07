package com.htttql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.htttql.entity.Accountant;
import com.htttql.entity.Bill;
import com.htttql.repository.AccountantRepository;
import com.htttql.repository.BillRepository;

@Controller
public class BillController {
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private AccountantRepository accountantRepository;
	
	@RequestMapping(value = "/showbill",method = RequestMethod.GET)
	public String getAllBill(Model model) {
		List<Bill> bills = new ArrayList<Bill>();
		bills = billRepository.findAll();
		double totalprice = 0;
		for (Bill bill : bills) {
			totalprice += bill.getTotalPrice();
		}
		model.addAttribute("bills",bills);
		model.addAttribute("totalprice", totalprice);
		return "Bill/showbill";
	}
	
	@RequestMapping(value = "/search/bill",method = RequestMethod.GET)
	public String searchBillByCreateBy(@RequestParam("usercreate") String name) {
		List<Accountant> accountants = new ArrayList<Accountant>();
		accountants = accountantRepository.findByName(name);
		System.out.print(accountants.get(0).getName());
		List<Bill> bills = new ArrayList<Bill>();
		
		return "Bill/test";
	}
}
