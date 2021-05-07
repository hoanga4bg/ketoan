package com.htttql.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.htttql.config.MyUserDetails;
import com.htttql.entity.Accountant;
import com.htttql.service.AbstractDAO;

@Service
public class AbstractDAOImpl implements AbstractDAO{

	@Override
	public Accountant getAccountant() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Accountant accountant=((MyUserDetails) principal).getAccountant() ;
		return accountant;
	}

	@Override
	public String getUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=((MyUserDetails) principal).getUsername() ;
		return username;
	}
	
}
