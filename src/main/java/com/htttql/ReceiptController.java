package com.htttql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.htttql.entity.Receipt;
import com.htttql.repository.ReceiptRepository;
import com.htttql.service.ReceiptDAO;

@Controller
@RequestMapping(value = "/receipt")
public class ReceiptController {
	
	@Autowired
	private ReceiptDAO receiptDAO;
	
	@GetMapping
	private String showReceipt(Model model) {
		List<Receipt> list=new ArrayList<Receipt>();
		receiptDAO.findAll();
		Collections.reverse(list);
		
		return "receipt/showReceipt";
	}
	
	
}
