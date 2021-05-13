package com.htttql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.htttql.config.MyUserDetails;
import com.htttql.entity.Account;
import com.htttql.entity.Accountant;
import com.htttql.repository.AccountRepository;
import com.htttql.repository.AccountantRepository;
import com.htttql.service.AbstractDAO;

@Controller
@RequestMapping("/admin")
public class AdminAccountController {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountantRepository accountantRepository;
	
	
	@Autowired
	private AbstractDAO abstractDAO;
	

	
	@GetMapping("/info")
	public String adminInfo(Model model,@RequestParam("message") String message) {
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
		return "admin";
	}
	
	
	@PostMapping("/info")
	public String infoPage(Accountant accountant) {
		
		accountantRepository.save(accountant);
		
		return "redirect:/admin/info?message";
	}
	
	
	@GetMapping("/change")
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
		return "admin/account/changepass";
	}
	
	
	@PostMapping("/change")
	public String changePass(@RequestParam("old") String oldPass,
							@RequestParam("newpass") String newPass) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String password=((MyUserDetails) principal).getPassword();
		String username=((MyUserDetails) principal).getUsername();
		System.out.println(newPass);
		Account account=accountRepository.findOneByUserName(username);
		if(oldPass.equals(password)==true) {
			account.setPassword(newPass);
			accountRepository.save(account);
			return "redirect:/admin/info?message=success";
		}
		else {
			return "redirect:/admin/change?message=false";
		}
	}
}
