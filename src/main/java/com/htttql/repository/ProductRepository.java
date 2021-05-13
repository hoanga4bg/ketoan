package com.htttql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.htttql.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	public Product findOneById(Integer id);
	

	List<Product> findByName(String name);

	public List<Product> findByStatus(int i);

}
