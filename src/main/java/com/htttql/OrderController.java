package com.htttql;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.standard.expression.Each;

import com.htttql.service.OrdersDAO;
import com.htttql.entity.Bill;
import com.htttql.entity.Orders;
import com.htttql.entity.Product;
import com.htttql.entity.Store;
import com.htttql.entity.Tax;
import com.htttql.repository.BillRepository;
import com.htttql.repository.OrdersRepository;
import com.htttql.repository.ProductRepository;
import com.htttql.repository.TaxRepository;
import com.htttql.service.*;

@Controller
public class OrderController {

	@Autowired
	private OrdersDAO ordersService;
	
	@Autowired
	private ProductRepository productReposity;
	
	@Autowired
	private TaxRepository taxRepository;
	
	@Autowired
	private OrdersRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private StoreDAO storeDAO;
	
	@RequestMapping(value = "/orders",method = RequestMethod.GET)
	public String findAll(Model model) {
		List<Orders> orders = new ArrayList<Orders>();
		orders = ordersService.findAll();
		double totalprice = 0;
		for (Orders order : orders) {
			if(order.getStatus()) {
				totalprice += order.getAmount()* order.getProduct().getSalePrice();
			}
		}
		model.addAttribute("orders", orders);
		model.addAttribute("totalprice",totalprice);
		return "orders/order";
	}
	
	@RequestMapping(value = "/orders/search",method = RequestMethod.GET)
	public String findOneById(Model model,@RequestParam("id") String id) {
		Orders orders = new Orders();
		orders = ordersService.findOneById(Integer.parseInt(id));
		model.addAttribute("orders", orders);
		return "orders/order";
	}
	
	@RequestMapping(value = "/orders/delete",method = RequestMethod.GET)
	public String delete(@RequestParam("id") String id) {
		System.out.print(id);
		ordersService.delete(Integer.parseInt(id));
		return "redirect:/orders";
	}
	
	@RequestMapping(value = "/orders/create/form",method = RequestMethod.GET)
	public String createFormOrders(Model model) {
		Orders orders = new Orders();
		List<Product> products = new ArrayList<Product>();
		List<Tax> taxs = new ArrayList<Tax>();
		products = productReposity.findAll();
		taxs = taxRepository.findAll();
		model.addAttribute("orders", orders);
		model.addAttribute("products", products);
		model.addAttribute("taxs", taxs);
		model.addAttribute("mode",0);
		System.out.println(orders.getId());
		return "orders/insert";
	}
	
	@RequestMapping(value = "/orders/save",method = RequestMethod.POST)
	public String saveOrder(Orders orders) {
		Product product = productReposity.findOneById(orders.getProduct().getId());
		orders.setProduct(product);
		orders.setStatus(true);
		System.out.print(orders.getId());
		ordersService.save(orders);
		return "redirect:/orders";
	}
	
	@RequestMapping(value = "/search/orders",method = RequestMethod.GET)
	public String searchByNameCustomer(@RequestParam("name") String name,Model model) {
		System.out.print(name);
		if(name == "") {
			return "redirect:/orders";
		}
		List<Orders> orders = new ArrayList<Orders>();
		 orders = orderRepository.findByCustomer(name);
		 List<Product> products = new ArrayList<Product>();
		 products = productRepository.findByName(name);
		 for (Product product : products) {
			List<Orders> orders1 = new ArrayList<Orders>();
			orders1 = orderRepository.findByProduct(product);
			for (Orders order : orders1) {
				orders.add(order);
			}
		}
		 List<Orders> listorders = new ArrayList<Orders>();
		 for (Orders order : orders) {
			if(!listorders.contains(order)) {
				listorders.add(order);
			}
		}
		 double totalprice = 0;
			for (Orders order : orders) {
				if(order.getStatus()) {
					totalprice += order.getAmount()* order.getProduct().getSalePrice();
				}
			}
		model.addAttribute("orders", listorders);
		model.addAttribute("totalprice",totalprice);
		return "orders/order";
	}
	
	@RequestMapping(value = "/orders/edit",method = RequestMethod.GET)
	public String editOrder(@RequestParam("id") String id, Model model) {
		Orders orders = new Orders();
		orders = orderRepository.findOneById(Integer.parseInt(id));
		List<Product> products = new ArrayList<Product>();
		products = productReposity.findAll();
		model.addAttribute("orders", orders);
		model.addAttribute("products", products);
		model.addAttribute("mode",1);
		return "orders/insert";
	}
	
	@RequestMapping(value = "/orders/cancel",method = RequestMethod.GET)
	public String cancelBill(@RequestParam("id") String id,Model model) {
		Orders orders = orderRepository.findOneById(Integer.parseInt(id));
		orders.setStatus(true);
		orderRepository.save(orders);
		Store store = new Store();
		store = storeDAO.findOneByProduct(orders.getProduct());
		store.setAmount(store.getAmount() + orders.getAmount());
		storeDAO.save(store);
		Bill bill = billRepository.findOneByOrders(orders);
		if(bill != null) {
			billRepository.delete(bill);
		}
		
		
		return "redirect:/orders";
	}
	
}
