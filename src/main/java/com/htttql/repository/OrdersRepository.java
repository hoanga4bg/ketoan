package com.htttql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.htttql.entity.Orders;
import com.htttql.entity.Product;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	
	Orders findOneById(int id);
	
	@Query(value = "SELECT * FROM ketoan.orders WHERE customer LIKE %?1% ",nativeQuery = true)
	List<Orders> findByCustomer(String name);
	
	List<Orders> findByProduct(Product product);
	List<Orders> findByStatus(Boolean status);
	
	

}
