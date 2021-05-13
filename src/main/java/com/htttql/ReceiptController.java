package com.htttql;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.htttql.entity.Accountant;
import com.htttql.entity.Product;
import com.htttql.entity.Receipt;
import com.htttql.entity.Store;

import com.htttql.service.AbstractDAO;
import com.htttql.service.ProductDAO;
import com.htttql.service.ReceiptDAO;
import com.htttql.service.StoreDAO;

@Controller
@RequestMapping(value = "/receipt")
public class ReceiptController {
	
	@Autowired
	private ReceiptDAO receiptDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private AbstractDAO abstractDAO;
	@Autowired
	private StoreDAO storeDAO;
	
	@GetMapping
	private String showReceipt(Model model) {
		List<Receipt> list=new ArrayList<Receipt>();
		list=receiptDAO.findAll();
	
		Collections.reverse(list);
		model.addAttribute("list", list);
		return "receipt/showReceipt";
	}

	
	@GetMapping("/edit")
	private String editReceipt(@RequestParam("id") String id,Model model) {
		
		Receipt receipt=receiptDAO.findById(Integer.parseInt(id));
		List<Product> products=new ArrayList<>();
		products=productDAO.findAll();
		model.addAttribute("receipt", receipt);
		model.addAttribute("products", products);
		model.addAttribute("mode", 1);
		return "receipt/editReceipt";
	}
	
	
	@PostMapping("/edit")
	private String editReceipt(Model model, Receipt receipt) {
		Accountant accountant=abstractDAO.getAccountant();
		Product p=productDAO.findById(receipt.getProduct().getId());
		receipt.setTotalPrice(receipt.getAmount()*p.getImportPrice());
		receipt.setCreatedDate(new Date());
		receipt.setProduct(p);
		receipt.setAccountant(accountant);
		
		if(receipt.getId()==null) {
			Store store=storeDAO.findOneByProduct(p);
			System.out.println(store.getAmount());
			int amount = store.getAmount();
			amount+=receipt.getAmount();
			store.setAmount(amount);
			System.out.println(amount);
			storeDAO.save(store);
		}
		else {
			Receipt re=receiptDAO.findById(receipt.getId());
			Store store=storeDAO.findOneByProduct(p);
			int amount = store.getAmount();
			amount=amount-re.getAmount()+receipt.getAmount();
			store.setAmount(amount);
			storeDAO.save(store);
		}
		receiptDAO.save(receipt);
		return "redirect:/receipt";
	}
	
	
	@GetMapping("/add")
	private String addReceipt(Model model) {
		
		
		List<Product> products=new ArrayList<>();
		products=productDAO.findAll();
		Receipt r=new Receipt();
		r.setAmount(1);
		model.addAttribute("receipt", r);
		model.addAttribute("products", products);
		model.addAttribute("mode", 0);
		return "receipt/editReceipt";
	}
	
	
	@GetMapping("/delete")
	private String deleteReceipt(Model model,@RequestParam("id") String id) {
		Receipt r=receiptDAO.findById(Integer.parseInt(id));
		Product p=r.getProduct();
		Store s=storeDAO.findOneByProduct(p);
		
		int amount = s.getAmount()-r.getAmount();
		s.setAmount(amount);
		storeDAO.save(s);
		receiptDAO.deleteById(Integer.parseInt(id));
		
		return "redirect:/receipt";
	}
	@GetMapping("/search")
	private String search(Model model,@RequestParam("startdate") String sDate,
			@RequestParam("enddate") String eDate) {
		if(sDate.equals("")||eDate.equals("")) {
			return "redirect:/receipt";
		}
		else {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date startDate=sdf.parse(sDate);
				Date endDate=sdf.parse(eDate);
				List<Receipt> list=new ArrayList<Receipt>();
				list=receiptDAO.findByCreateDate(startDate, endDate);
				model.addAttribute("list", list);
				return "receipt/showReceipt";
			}
			catch(Exception e){
				
			}
		}
		return "redirect:/receipt";
	}
}

