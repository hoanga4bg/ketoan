package com.htttql.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.htttql.config.MyUserDetails;
import com.htttql.entity.Account;
import com.htttql.entity.Accountant;
import com.htttql.repository.AccountRepository;
import com.htttql.service.AbstractDAO;

@Service
public class AbstractDAOImpl implements AbstractDAO{

	@Autowired
	private AccountRepository accountRepo;
	@Override
	public Accountant getAccountant() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=((MyUserDetails) principal).getUsername() ;
		Account account=accountRepo.findOneByUserName(username);

		return account.getAccountant();
	}

	@Override
	public String getUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=((MyUserDetails) principal).getUsername() ;
		return username;
	}
	
}
