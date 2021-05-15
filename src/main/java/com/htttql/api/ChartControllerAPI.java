package com.htttql.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htttql.dto.ChartDraw;
import com.htttql.service.BillDAO;
import com.htttql.service.ProductDAO;
import com.htttql.entity.Product;
@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api", produces = "application/json")
public class ChartControllerAPI {
	
	
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private BillDAO billDAO;
	@GetMapping("/chart")
	private ResponseEntity<?> getData(@RequestParam("month") String month, @RequestParam("year") String year ) {
		ChartDraw chart=new ChartDraw();
		List<Product> products=productDAO.findAll();
		List<String> names=new ArrayList<String>();
		List<Integer> money=new ArrayList<Integer>();
		for(Product p:products) {
			names.add(p.getName());
			money.add((int) billDAO.salePriceOfProductByMonthAndYear(Integer.parseInt(month), Integer.parseInt(year), p));
		}
		chart.setMoneys(money);
		chart.setProducts(names);
		return ResponseEntity.status(HttpStatus.OK).body(chart);
	}
}
