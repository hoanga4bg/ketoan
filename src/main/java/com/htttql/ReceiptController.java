package com.htttql;

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

import com.htttql.config.MyUserDetails;
import com.htttql.entity.Accountant;
import com.htttql.entity.Product;
import com.htttql.entity.Receipt;
import com.htttql.repository.ReceiptRepository;
import com.htttql.service.AbstractDAO;
import com.htttql.service.ProductDAO;
import com.htttql.service.ReceiptDAO;

@Controller
@RequestMapping(value = "/receipt")
public class ReceiptController {
	
	@Autowired
	private ReceiptDAO receiptDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private AbstractDAO abstractDAO;
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
		receiptDAO.save(receipt);
		return "redirect:/receipt";
	}
	
	
	@GetMapping("/add")
	private String editReceipt(Model model) {
		
		
		List<Product> products=new ArrayList<>();
		products=productDAO.findAll();
		model.addAttribute("receipt", new Receipt());
		model.addAttribute("products", products);
		return "receipt/editReceipt";
	}
}

