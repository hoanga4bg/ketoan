package com.htttql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.Accountant;
import com.htttql.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer>{
	List<Bill> findByCreateBy(Accountant createBy);

}
