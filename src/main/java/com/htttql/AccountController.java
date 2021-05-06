package com.htttql;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.htttql.entity.Account;
import com.htttql.entity.Accountant;
import com.htttql.repository.AccountRepository;
import com.htttql.repository.AccountantRepository;
import com.htttql.config.MyUserDetails;



@Controller

public class AccountController {
	
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Accountant accountant=null;
		
		accountant = ((MyUserDetails) principal).getAccountant();
		System.out.println(accountant.getName());
		return "home";
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth instanceof AnonymousAuthenticationToken){
			System.out.println("1");
			model.addAttribute("account",new Account());
			return "login";
			
		}
		else {
			System.out.println("0");
			return "redirect:/";
			
		}
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null) {
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login";
	}
	
	
	
}
