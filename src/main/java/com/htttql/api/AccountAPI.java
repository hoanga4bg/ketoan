package com.htttql.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.htttql.entity.Account;
import com.htttql.repository.AccountRepository;

@RestController
@CrossOrigin("*")
@RequestMapping(path="/api/account", produces = "application/json")
public class AccountAPI {
	
	@Autowired
	private AccountRepository accountRepo;
	@GetMapping
	public ResponseEntity<?> activeAdmin(){
		Account account=accountRepo.findByUserName("admin").get(0);
		account.setStatus(true);
		accountRepo.save(account);
		return ResponseEntity.status(HttpStatus.OK).body(account);
	}
}
