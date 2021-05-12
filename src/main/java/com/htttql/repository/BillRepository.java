package com.htttql.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.htttql.entity.Accountant;
import com.htttql.entity.Bill;
import com.htttql.entity.Orders;

public interface BillRepository extends JpaRepository<Bill, Integer>{
	List<Bill> findByCreateBy(Accountant createBy);
	Bill findOneById(int id);
	Bill findOneByOrders(Orders orders);
	
	@Query(value = "SELECT * FROM ketoan.bill where create_date >= ?1 and create_date<= ?2",nativeQuery = true)
	List<Bill> findByDate(Date date1,Date date2);

}
