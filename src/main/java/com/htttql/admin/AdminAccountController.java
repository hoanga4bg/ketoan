package com.htttql.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		return "admin/account/info";
	}
}
