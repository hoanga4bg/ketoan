package com.htttql.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.htttql.entity.Account;
import com.htttql.entity.Accountant;
import com.htttql.repository.AccountantRepository;



public class MyUserDetails implements UserDetails{
	
	private String username;
	private String password;
	private boolean status;
	private Accountant accountant;
	private List<GrantedAuthority> authorities;
	public MyUserDetails(Account account) {
		this.username=account.getUserName();
		this.password=account.getPassword();
		this.status=account.getStatus();
		this.authorities=Arrays.stream(account.getRole().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		this.accountant=account.getAccountant();
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.status;
	}
	
	public Accountant getAccountant() {
		return this.accountant;
	}

}
