package com.htttql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.htttql.entity.Category;
import com.htttql.entity.Product;
import com.htttql.entity.Store;
import com.htttql.entity.Supplier;
import com.htttql.repository.CategoryRepository;
import com.htttql.repository.ProductRepository;
import com.htttql.repository.StoreRepository;
import com.htttql.repository.SupplierRepository;
import com.htttql.service.ProductDAO;
import com.htttql.service.StoreDAO;

@Controller

@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	private ProductRepository productRepo;
	
	
	@Autowired
	private StoreRepository storeRepo;
	@Autowired
	private StoreDAO storeDAO;
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CategoryRepository cateRepo;
	
	@Autowired
	private SupplierRepository supRepo;
	
	@GetMapping
	private String storeHome(Model model) {
	
		List<Store> listStore=new ArrayList<Store>();
		listStore=storeDAO.findAll();
	
		model.addAttribute("listStore", listStore);
		return "store/storehome";
	}
	
	
	@GetMapping("/edit")
	private String editStore(Model model, @RequestParam("id") String id) {
		Store store=storeRepo.findOneById(Integer.parseInt(id));
		model.addAttribute("store", store);
		return "store/editStore";
	}
	
	
	@PostMapping("/edit")
	private String editStore(Model model, Store store) {
		
		store.setCreateDate(new Date());
		
		storeRepo.save(store);
		return "redirect:/store";
	}
}
