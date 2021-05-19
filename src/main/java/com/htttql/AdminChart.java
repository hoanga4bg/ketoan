package com.htttql;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminChart {
	
	@RequestMapping(value = "/admin/chart",method = RequestMethod.GET)
	public String show() {
		return "chart";
	}
}
