package com.htttql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Integer>{
	public Account findOneByUserName(String username);
}
