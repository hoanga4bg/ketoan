package com.htttql.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.htttql.entity.Orders;
import com.htttql.entity.Product;
public interface OrdersDAO extends GeneralService<Orders> {
	List<Orders> findByCustomer(String name);
	List<Orders> findByProduct(Product product);

}
