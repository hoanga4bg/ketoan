package com.htttql.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.htttql.repository.ProductRepository;
import com.htttql.entity.Product;
@Controller
@RequestMapping("/admin/product")
public class AdminProductController {
	
	@Autowired
	private ProductRepository productRepo;
	@GetMapping
	public String productHome(Model model) {
		List<Product> isActive=new ArrayList<Product>();
		isActive=productRepo.findByStatus(0);
		
		
		List<Product> isUnactive=new ArrayList<Product>();
		isUnactive=productRepo.findByStatus(1);
		
		model.addAttribute("listA", isActive);
		model.addAttribute("listU", isUnactive);
		return "admin/productHome";
		
	}
}
