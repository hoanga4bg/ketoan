package com.htttql.service;

import java.util.List;

import com.htttql.entity.Product;

public interface ProductDAO {
	public List<Product> findAll();
	public Product findById(Integer id);
	List<Product> findByName(String name);
}
