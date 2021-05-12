package com.htttql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.Tax;

public interface TaxRepository extends JpaRepository<Tax, Integer>{
	public Tax findOneById(int id);

	public List<Tax> findByName(String name);

}
