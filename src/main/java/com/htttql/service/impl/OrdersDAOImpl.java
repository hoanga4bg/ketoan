package com.htttql.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htttql.entity.Orders;
import com.htttql.entity.Product;
import com.htttql.repository.OrdersRepository;
import com.htttql.service.OrdersDAO;
@Service
public class OrdersDAOImpl implements OrdersDAO {
	@Autowired
	private OrdersRepository ordersRepository;

	@Override
	public List<Orders> findAll() {
		List<Orders> orders = new ArrayList<Orders>();
		orders = ordersRepository.findAll();
		return orders;
	}

	@Override
	public Orders save(Orders t) {
		return ordersRepository.save(t);
	}

	@Override
	public Orders findOneById(int id) {
		
		return ordersRepository.findOneById(id);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		ordersRepository.deleteById(id);
		
	}

	@Override
	public Orders update(int id,Orders t) {
		t.setId(id);
		return ordersRepository.save(t);
	}

	@Override
	public List<Orders> findByCustomer(String name) {
		// TODO Auto-generated method stub
		return ordersRepository.findByCustomer(name);
	}

	@Override
	public List<Orders> findByProduct(Product product) {
		// TODO Auto-generated method stub
		return ordersRepository.findByProduct(product);
	}

	

}
