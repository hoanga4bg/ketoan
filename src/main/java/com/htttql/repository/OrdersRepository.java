package com.htttql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

}
