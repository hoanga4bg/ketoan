package com.htttql;

import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.htttql.entity.Account;
import com.htttql.entity.Accountant;
import com.htttql.repository.AccountRepository;
import com.htttql.repository.AccountantRepository;
import com.htttql.service.AbstractDAO;
import com.mysql.cj.protocol.Message;
import com.htttql.config.MyUserDetails;



@Controller

public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountantRepository accountantRepository;
	
	
	@Autowired
	private AbstractDAO abstractDAO;
	
	
	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultHome() {
		Collection<? extends GrantedAuthority> authorities;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        authorities = auth.getAuthorities();
        String myRole = authorities.toArray()[0].toString();
        System.out.println(myRole);
		if(myRole.equals("ROLE_ADMIN")) {
			return "redirect:/admin";
		} 
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminHome() {
		System.out.println("1");
		return "admin";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		int size=accountRepository.findAll().size();
		if(size==0) {
			Accountant accountant=new Accountant();
			accountant.setAddress("A");
			accountant.setName("Hoàng");
			accountant.setPhoneNumber("0123456789");
			accountantRepository.save(accountant);
			
			Accountant accountantAd=new Accountant();
			accountantAd.setAddress("A");
			accountantAd.setName("admin");
			accountantAd.setPhoneNumber("0122455669");
			accountantRepository.save(accountantAd);
			
			Account account=new Account();
			account.setAccountant(accountant);
			account.setRole("ROLE_USER");
			account.setPassword("123456");
			account.setUserName("user");
			account.setStatus(true);
			accountRepository.save(account);
			
			Account accountAd=new Account();
			accountAd.setUserName("admin");
			accountAd.setPassword("admin");
			accountAd.setAccountant(accountantAd);
			accountAd.setRole("ROLE_ADMIN");
			accountAd.setStatus(true);
			accountRepository.save(accountAd);
			
		}
		
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth instanceof AnonymousAuthenticationToken) {
				model.addAttribute("account",new Account());
				return "login";	
			}
			else {
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
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String infoPage(Model model,@RequestParam("message") String message) {
		
		
		Accountant accountant=abstractDAO.getAccountant();
		String username=abstractDAO.getUsername();
		
		Accountant acc=accountantRepository.findOneById(accountant.getId());
		model.addAttribute("accountant", acc);
		model.addAttribute("username", username);
		if(message.equals("success")) {
			model.addAttribute("message", "Cập nhật mật khẩu thành công");
		}
		else {
			model.addAttribute("message", "");
		}
		//System.out.print(accountant.toString());
		return "account/info";
	}
	
	
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public String infoPage(Accountant accountant) {
		
		accountantRepository.save(accountant);
		
		return "redirect:/info?message";
	}
	
	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public String changepassPage(Model model, @RequestParam("message") String message) {
		Accountant accountant=abstractDAO.getAccountant();
		String username=abstractDAO.getUsername();
		Accountant acc=accountantRepository.findOneById(accountant.getId());
		model.addAttribute("accountant", acc);
		model.addAttribute("username", username);
		if(message.equals("false")==true) {
			model.addAttribute("message", "Mật khẩu cũ không đúng");
		}
		else {
			model.addAttribute("message", "");
		}
		return "account/changepass";
	}
	
	
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public String infoPage(@RequestParam("old") String oldPass,
						   @RequestParam("newpass") String newPass) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String password=((MyUserDetails) principal).getPassword();
		String username=((MyUserDetails) principal).getUsername();
		System.out.println(newPass);
		Account account=accountRepository.findOneByUserName(username);
		if(oldPass.equals(password)==true) {
			account.setPassword(newPass);
			accountRepository.save(account);
			return "redirect:/info?message=success";
		}
		else {
			return "redirect:/change?message=false";
		}
	}
	
}
