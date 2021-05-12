package com.htttql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.Tax;

public interface TaxRepository extends JpaRepository<Tax, Integer>{
	public Tax findOneById(int id);

	public Tax findOneByName(String name);

}
