package com.htttql;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.htttql.entity.Orders;
import com.htttql.repository.AccountantRepository;
import com.htttql.repository.BillRepository;
import com.htttql.repository.OrdersRepository;
import com.htttql.service.BillDAO;

@Controller
public class BillController {
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private AccountantRepository accountantRepository;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private BillDAO billDAO;
	
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
	public String searchBillByCreateBy(@RequestParam("usercreate") String name,Model model) {
		List<Accountant> accountants = new ArrayList<Accountant>();
		accountants = accountantRepository.findByName(name);
		List<Bill> listbills = new ArrayList<Bill>();
		for (Accountant accountant : accountants) {
			List<Bill> bills = new ArrayList<Bill>();
			bills = billRepository.findByCreateBy(accountant);
			for (Bill bill : bills) {
				listbills.add(bill);
			}
		}
		double totalprice = 0;
		
		for (Bill bill : listbills) {
			totalprice += bill.getTotalPrice();
			
		}
		
		model.addAttribute("bills", listbills);
		model.addAttribute("totalprice",totalprice);
		
		return "Bill/showbill";
	}
	
	@RequestMapping(value = "/search/bill/by/date", method = RequestMethod.GET)
	public String searchBillByDate(@RequestParam("datestart") String datestart, @RequestParam("dateend") String dateend,Model model) {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate;
		Date enddate;
		try {
			startdate = sim.parse(datestart);
			enddate = sim.parse(dateend);
			List<Bill> bills = new ArrayList<Bill>();
			bills = billRepository.findAll();
			List<Bill> listbills = new ArrayList<Bill>();
			for (Bill bill : bills) {
				if(((bill.getCreateDate().compareTo(startdate) == 1) && (bill.getCreateDate().compareTo(enddate) == -1)) || (bill.getCreateDate().compareTo(startdate) == 0) || (bill.getCreateDate().compareTo(enddate) == 0)) {
					listbills.add(bill);
				}
			}
			
			double totalprice = 0;
			
			for (Bill bill : listbills) {
				totalprice += bill.getTotalPrice();
				
			}
			model.addAttribute("bills", listbills);
			model.addAttribute("totalprice",totalprice);
			
			System.out.print(totalprice);
			return "Bill/showbill";
			
		} catch (ParseException e) {
			e.getStackTrace();
			return "Bill/test";
		}
		
		
	}
	
	@RequestMapping(value = "/bill/delete",method = RequestMethod.GET)
	public String deleteBill(@RequestParam("id") String id) {
		billRepository.deleteById(Integer.parseInt(id));
		
		return "redirect:/showbill";
	}
	
	@RequestMapping(value = "/bill/detail",method = RequestMethod.GET)
	public String detailBill(@RequestParam("id") String id,Model model) {
		Bill bill = billRepository.findOneById(Integer.parseInt(id));
		String totalprice = String.valueOf((int)Math.round(bill.getTotalPrice()));
		ArrayList<String> kq = billDAO.readNum(totalprice);
		String money = "";
        for (int i = 0; i < kq.size(); i++) {
        	money += kq.get(i) + " ";
            System.out.print(kq.get(i)+ " ");
        }
		model.addAttribute("bill", bill);
		model.addAttribute("totalprice", money);
		return "Bill/detail";
	}
	
	
	
	
}
