package com.htttql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.Product;
import com.htttql.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Integer>{

	public Store findOneById(int parseInt);
	public Store findOneByProduct(Product product);

	public Store findOneByProduct(Product p);

}
