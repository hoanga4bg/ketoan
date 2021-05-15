package com.htttql.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.htttql.entity.Accountant;
import com.htttql.entity.Bill;
import com.htttql.entity.Orders;
import com.htttql.entity.Product;

public interface BillRepository extends JpaRepository<Bill, Integer>{
	List<Bill> findByCreateBy(Accountant createBy);
	Bill findOneById(int id);
	Bill findOneByOrders(Orders orders);
	
	
	List<Bill> findByCreateDateBetween(Date date1,Date date2);


}
