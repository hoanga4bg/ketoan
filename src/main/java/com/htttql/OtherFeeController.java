package com.htttql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.htttql.service.AbstractDAO;
import com.htttql.service.OtherFeeDAO;
import com.htttql.entity.ExpenseStatistic;
import com.htttql.entity.OtherFee;
import com.htttql.entity.Receipt;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/other")
public class OtherFeeController {
	
	@Autowired
	private OtherFeeDAO otherFeeDAO;
	@Autowired
	private AbstractDAO abstractDAO;
	@GetMapping
	public String listOtherFee(Model model) {
		List<OtherFee> list=new ArrayList<OtherFee>();
		list=otherFeeDAO.findAll();
		Collections.reverse(list);
		Double total=0.0;
		for(OtherFee o:list) {
			total+=o.getTotal();
		}
		model.addAttribute("totalFee", total);
		model.addAttribute("list", list);
		return "otherfee/otherFee";
	}
	
	@GetMapping("/add")
	public String addFee(Model model) {

		model.addAttribute("otherfee", new OtherFee());
		model.addAttribute("mode", 0);
		return "otherfee/addFee";
	}
	@GetMapping("/edit")
	public String editFee(Model model,@RequestParam("id") String id) {
		OtherFee otherFee=otherFeeDAO.findOneById(Integer.parseInt(id));
		model.addAttribute("otherfee", otherFee);
		model.addAttribute("mode", 1);
		return "otherfee/addFee";
	}
	
	@PostMapping("/add")
	public String addFee(OtherFee otherFee) {
		Date date=new Date();
		int month=date.getMonth()+1;
		int year=date.getYear()+1900;
		otherFee.setCreateBy(abstractDAO.getAccountant());
		otherFee.setCreateDate(date);
		otherFee.setMonth(month);
		otherFee.setYear(year);
		otherFeeDAO.save(otherFee);
		return "redirect:/other";
	}
	
	@GetMapping("/delete")
	public String deleteFee(Model model,@RequestParam("id") String id) {
		otherFeeDAO.delete(Integer.parseInt(id));
		return "redirect:/other";
	}
	
	@GetMapping("/search")
	private String search(Model model,@RequestParam("startdate") String sDate,
			@RequestParam("enddate") String eDate) {
		if(sDate.equals("")||eDate.equals("")) {
			return "redirect:/other";
		}
		else {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy-MM-dd");
			try {

				LocalDate ed = LocalDate.parse(eDate, dtf).plusDays(1);
				Date startDate=sdf.parse(sDate);
				Date endDate=Date.from(ed.atStartOfDay(ZoneId.systemDefault()).toInstant());
				List<OtherFee> list=new ArrayList<OtherFee>();
				list=otherFeeDAO.findByCreateDate(startDate, endDate);
				Collections.reverse(list);
				Double total=0.0;
				for(OtherFee o:list) {
					total+=o.getTotal();
				}
				model.addAttribute("totalFee", total);
				model.addAttribute("list", list);
				return "otherfee/otherFee";
			}
			catch(Exception e){
				
			}
		}
		return "redirect:/other";
	}
}
