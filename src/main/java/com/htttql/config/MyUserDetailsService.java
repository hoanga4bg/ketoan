package com.htttql.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.htttql.entity.Account;
import com.htttql.repository.AccountRepository;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private AccountRepository accountRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//System.out.println(username);
		
		Account account=accountRepo.findOneByUserName(username);
//		System.out.print(user.getPassWord());
		if(account==null) {
			throw new UsernameNotFoundException("not found : "+username);
		}
		
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(account.getRole()));
		return new MyUserDetails(account);
		
	}

}
