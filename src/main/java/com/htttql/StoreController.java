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

import com.htttql.entity.Store;
import com.htttql.repository.ProductRepository;
import com.htttql.repository.StoreRepository;

@Controller

@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	private ProductRepository productRepo;
	
	
	@Autowired
	private StoreRepository storeRepo;
	
	@GetMapping
	private String storeHome(Model model) {
		List<Store> listStore=new ArrayList<Store>();
		listStore=storeRepo.findAll();
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
