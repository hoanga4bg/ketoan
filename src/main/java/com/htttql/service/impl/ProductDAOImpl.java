package com.htttql.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htttql.entity.Product;
import com.htttql.repository.ProductRepository;
import com.htttql.service.ProductDAO;


@Service
public class ProductDAOImpl implements ProductDAO{
	
	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public List<Product> findAll() {
		List<Product> list=new ArrayList<Product>();
		list=productRepo.findByStatus(0);

		return list;
	}
	
	
	@Override
	public Product findById(Integer id) {
		
		return productRepo.findById(id);
	}

	@Override
	public List<Product> findByName(String name) {
		// TODO Auto-generated method stub
		return productRepo.findByName(name);
	}
	
}
