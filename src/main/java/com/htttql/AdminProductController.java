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

import com.htttql.repository.CategoryRepository;
import com.htttql.repository.ProductRepository;
import com.htttql.repository.StoreRepository;
import com.htttql.repository.SupplierRepository;
import com.htttql.service.ProductDAO;
import com.htttql.entity.Category;
import com.htttql.entity.Product;
import com.htttql.entity.Store;
import com.htttql.entity.Supplier;
@Controller
@RequestMapping("/admin/product")
public class AdminProductController {
	
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
	public String productHome(Model model) {
		List<Product> isActive=new ArrayList<Product>();
		isActive=productRepo.findByStatus(0);
		List<Store> listA=new ArrayList<Store>();
		for(Product p:isActive) {
			Store s=storeRepo.findOneByProduct(p);
			listA.add(s);
		}
		
		List<Product> isUnactive=new ArrayList<Product>();
		isUnactive=productRepo.findByStatus(1);
		
		for(Product p:isUnactive) {
			Store s=storeRepo.findOneByProduct(p);
			listA.add(s);
		}
		model.addAttribute("listA", listA);

		return "admin/product/productHome";
		
	}
	
	@GetMapping("/stop")
	public String stopSale(Model model,@RequestParam("id") String id) {
		Product p=productDAO.findById(Integer.parseInt(id));
		p.setStatus(1);
		productRepo.save(p);
		return "redirect:/admin/product";
		
	}
	
	@GetMapping("/resume")
	public String resumeSale(Model model,@RequestParam("id") String id) {
		Product p=productDAO.findById(Integer.parseInt(id));
		p.setStatus(0);
		productRepo.save(p);
		return "redirect:/admin/product";
		
	}
	
	@GetMapping("/addpro")
	public String addPro(Model model) {
		List<Category> categories=cateRepo.findAll();
		List<Supplier> suppliers=supRepo.findAll();
		model.addAttribute("product", new Product());
		model.addAttribute("category", categories);
		model.addAttribute("supplier", suppliers);
		return "admin/product/addProduct";
		
	}
	
	
	@PostMapping("/addpro")
	public String addPro(Product product) {
		
		product.setStatus(0);
		productRepo.save(product);
		
			List<Product> list=productDAO.findAll();
			Store s=new Store();
			s.setAmount(0);
			s.setCreateDate(new Date());
			s.setProduct(list.get(list.size()-1));
			storeRepo.save(s);
		
		return "redirect:/admin/product";
		
	}
	@GetMapping("/addcate")
	public String addCate(Model model) {
		model.addAttribute("category", new Category());

		return "admin/product/addCategory";
		
	}
	@GetMapping("/addsup")
	public String addSup(Model model) {

		model.addAttribute("supplier", new Supplier());

		return "admin/product/addSupplier";
		
	}
	@PostMapping("/addcate")
	public String addCate(Category category) {
		cateRepo.save(category);

		return "redirect:/admin/product";
		
	}
	@PostMapping("/addsup")
	public String addSup(Supplier supplier) {
		supRepo.save(supplier);

		return "redirect:/admin/product";
		
	}
	
	@GetMapping("/edit")
	public String editProduct(Model model,@RequestParam("id") String id) {
		Product p = productDAO.findById(Integer.parseInt(id));
		List<Category> categories=cateRepo.findAll();
		List<Supplier> suppliers=supRepo.findAll();
		model.addAttribute("product", p);
		model.addAttribute("category", categories);
		model.addAttribute("supplier", suppliers);

		return "admin/product/editProduct";
		
	}
	@PostMapping("/edit")
	public String editPro(Product product) {
		productRepo.save(product);
		return "redirect:/admin/product";
		
	}
}
