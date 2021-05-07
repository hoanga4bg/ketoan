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
		list=productRepo.findAll();
		System.out.println(list.size());
		return list;
	}

	@Override
	public Product findById(Integer id) {
		
		return productRepo.findOneById(id);
	}
	
}
