package com.htttql;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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
import com.htttql.entity.Product;
import com.htttql.entity.Store;
import com.htttql.repository.AccountantRepository;
import com.htttql.repository.BillRepository;
import com.htttql.repository.OrdersRepository;
import com.htttql.repository.ProductRepository;
import com.htttql.service.AbstractDAO;
import com.htttql.service.BillDAO;
import com.htttql.service.StoreDAO;

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
	
	@Autowired
	private AbstractDAO abstractService;
	
	@Autowired
	private StoreDAO storeDAO;
	@Autowired
	private ProductRepository productReposity;
	
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
		List<Orders> orders = new ArrayList<Orders>();
		orders = ordersRepository.findByStatus(false);
		List<Bill> bills = new ArrayList<Bill>();
		for (Orders order : orders) {
			if(order.getCustomer().contains(name)) {
				Bill bill = billRepository.findOneByOrders(order);
				bills.add(bill);
			}
			
		}
		double totalprice = 0;
		for (Bill bill : bills) {
			totalprice += bill.getTotalPrice();
		}
		model.addAttribute("bills", bills);
		model.addAttribute("totalprice",totalprice);
		return "Bill/showbill";
	}
	
	@RequestMapping(value = "/search/bill/by/date", method = RequestMethod.GET)
	public String searchBillByDate(@RequestParam("datestart") String datestart, @RequestParam("dateend") String dateend,Model model) {
		if(datestart == "" || dateend == "") {
			return "redirect:/showbill";
		}
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate;
		Date enddate1;
		try {
			startdate = sim.parse(datestart);
			enddate1 = sim.parse(dateend);
			LocalDate day = enddate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate day1 = day.plusDays(1);
			Date enddate = Date.from(day1.atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			List<Bill> bills = new ArrayList<Bill>();
			bills = billRepository.findAll();
			List<Bill> listbills = new ArrayList<Bill>();
			for (Bill bill : bills) {
				if(((bill.getCreateDate().compareTo(startdate) == 1) && (bill.getCreateDate().compareTo(enddate) == -1)) || (bill.getCreateDate().compareTo(startdate) == 0)) {
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
        double vat = 0;
        double saleprice = 0;
        vat = bill.getOrders().getAmount()* bill.getOrders().getProduct().getSalePrice()*0.1;
        saleprice = bill.getOrders().getProduct().getSalePrice();
        model.addAttribute("vat", vat);
        model.addAttribute("saleprice",saleprice);
		model.addAttribute("bill", bill);
		model.addAttribute("totalprice", money);
		return "Bill/detail";
	}
	
	@RequestMapping(value = "/bill/create",method = RequestMethod.POST)
	public String createNewBill(Orders orders) {
		Product product = productReposity.findOneById(orders.getProduct().getId());
		orders.setProduct(product);
		orders.setStatus(false);
		ordersRepository.save(orders);
		Store store = new Store();
		store = storeDAO.findOneByProduct(orders.getProduct());
		store.setAmount(store.getAmount()-orders.getAmount());
		storeDAO.save(store);
		long millis=System.currentTimeMillis();
		java.sql.Date dateNow = new java.sql.Date(millis);
		double totalPrice = orders.getAmount() * orders.getProduct().getSalePrice() + (orders.getAmount() * orders.getProduct().getSalePrice())*0.1;
		Accountant accountant = new Accountant();
		accountant = abstractService.getAccountant();
		Bill bill = new Bill();
		bill.setCreateDate(dateNow);
		bill.setCreateBy(accountant);
		bill.setTotalPrice(totalPrice);
		bill.setOrders(orders);
		billRepository.save(bill);
		return "redirect:/showbill";
	}
	
	@RequestMapping(value = "/bill/edit",method = RequestMethod.GET)
	public String edit(@RequestParam("id") String id,Model model) {
		Bill bill = billRepository.findOneById(Integer.parseInt(id));
		List<Product> products = new ArrayList<Product>();
		List<Accountant> accs = new ArrayList<Accountant>();
		accs = accountantRepository.findAll();
		products = productReposity.findAll();
		model.addAttribute("bill", bill);
		model.addAttribute("products", products);
		model.addAttribute("accs",accs);
		return "Bill/edit";
	}
	@RequestMapping(value = "/bill/save",method = RequestMethod.POST)
	public String save(Bill bill, @RequestParam("amountold") String oldamount) {
		Product product = productReposity.findOneById(bill.getOrders().getProduct().getId());
		bill.getOrders().setProduct(product);
		ordersRepository.save(bill.getOrders());
		System.out.print(oldamount);
		
		Store store = new Store();
		store = storeDAO.findOneByProduct(bill.getOrders().getProduct());
		store.setAmount(store.getAmount() + Integer.parseInt(oldamount) - bill.getOrders().getAmount());
		storeDAO.save(store);
		
		Accountant acc = accountantRepository.findOneById(bill.getCreateBy().getId());
		bill.setCreateBy(acc);
		bill.setTotalPrice((bill.getOrders().getAmount() * bill.getOrders().getProduct().getSalePrice()) + (bill.getOrders().getAmount() * bill.getOrders().getProduct().getSalePrice()*0.1));
		billRepository.save(bill);
		return "redirect:/showbill";
	}
		
	

	
	
	
}
