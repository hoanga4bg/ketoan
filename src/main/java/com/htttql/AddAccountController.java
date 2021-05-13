package com.htttql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.htttql.entity.Account;
import com.htttql.entity.Accountant;
import com.htttql.repository.AccountRepository;
import com.htttql.repository.AccountantRepository;

@Controller
public class AddAccountController {
	@Autowired
	private AccountantRepository accountantRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	
	@RequestMapping(value = "/admin/display/account",method = RequestMethod.GET)
	public String findAll(Model model) {
		List<Account> accounts = new ArrayList<Account>();
		accounts = accountRepository.findAll();
		model.addAttribute("accounts", accounts);
		return "admin/account/display";
	}
	
	@RequestMapping(value = "/admin/create/account",method = RequestMethod.GET)
	public String createNewAccount(Model model) {
		Account account = new Account();
		model.addAttribute("account", account);
		return "admin/account/addform";
	}
	
	@RequestMapping(value = "/admin/account/save",method = RequestMethod.POST)
	public String createNewAccount(Account account) {
		accountantRepository.save(account.getAccountant());
		account.setStatus(true);
		accountRepository.save(account);
		return "redirect:/admin/display/account";
	}
	
	@RequestMapping(value = "/admin/account/delete",method = RequestMethod.GET)
	public String deleteAccount(@RequestParam("id") String id) {
		Account account = new Account();
		account = accountRepository.findOneById(Integer.parseInt(id));
		account.setStatus(false);
		accountRepository.save(account);
		return "redirect:/admin/display/account";
	}
	
	@RequestMapping(value = "/admin/account/kh",method = RequestMethod.GET)
	public String khAccount(@RequestParam("id") String id) {
		Account account = new Account();
		account = accountRepository.findOneById(Integer.parseInt(id));
		account.setStatus(true);
		accountRepository.save(account);
		return "redirect:/admin/display/account";
	}
	
	@RequestMapping(value = "/admin/search/account",method = RequestMethod.GET)
	public String searchAccount(@RequestParam("name") String name, Model model) {
		if(name =="") {
			return "redirect:/admin/display/account";
		}
		List<Account> accounts = new ArrayList<Account>();
		accounts = accountRepository.findByUserName(name);
		model.addAttribute("accounts", accounts);
		return "admin/account/display";
	}

}
