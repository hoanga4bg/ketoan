package com.htttql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Integer>{
	public Account findOneByUserName(String username);
	public Account findOneById(int id);
	public List<Account> findByUserName(String name);
}
