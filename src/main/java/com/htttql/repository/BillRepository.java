package com.htttql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer>{

}
