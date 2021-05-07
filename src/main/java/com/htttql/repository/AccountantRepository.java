package com.htttql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.Accountant;

public interface AccountantRepository extends JpaRepository<Accountant, Integer>{

	Accountant findOneById(Integer id);
	List<Accountant> findByName(String name);

}
