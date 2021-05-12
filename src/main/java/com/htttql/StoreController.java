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

@Controller

@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	private ProductRepository productRepo;
	
	
	@Autowired
	private StoreRepository storeRepo;
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CategoryRepository cateRepo;
	
	@Autowired
	private SupplierRepository supRepo;
	
	@GetMapping
	private String storeHome(Model model) {
		Category c=new Category();
		c.setId(1);
		c.setName("Tai nghe");
		Supplier s=new Supplier();
		s.setId(1);
		s.setName("Sony");
		if(cateRepo.findAll().size()==0) {
			cateRepo.save(c);
		}
		
		if(supRepo.findAll().size()==0) {
			supRepo.save(s);
		}
		
		if(productDAO.findAll().size()==0) {
			Product p=new Product();
			p.setId(1);
			p.setCategory(c);
			p.setName("Tai nghe MDR-Z7M2");
			p.setImportPrice(100000.0);
			p.setSalePrice(200000.0);
			p.setSupplier(s);
			productRepo.save(p);
			Store st=new Store();
			st.setId(1);
			st.setCreateDate(new Date());
			st.setAmount(500);
			st.setProduct(p);
			storeRepo.save(st);
			
			
			p.setId(2);
			p.setCategory(c);
			p.setName("Tai nghe không dây có công nghệ chống ồn WH-1000XM4");
			p.setImportPrice(200000.0);
			p.setSalePrice(400000.0);
			p.setSupplier(s);
			productRepo.save(p);
			st.setId(2);
			st.setCreateDate(new Date());
			st.setAmount(400);
			st.setProduct(p);
			storeRepo.save(st);
		}
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
