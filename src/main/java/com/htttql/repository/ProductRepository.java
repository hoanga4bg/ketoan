package com.htttql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
