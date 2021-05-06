package com.htttql;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/store")
public class StoreController {
	@GetMapping
	private String storeHome(Model model) {
		return "store/storehome";
	}
}
