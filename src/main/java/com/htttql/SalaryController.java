package com.htttql;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.htttql.service.SalaryDAO;
import com.htttql.dto.SalaryDTO;
import com.htttql.dto.convert.SalaryConvert;
import com.htttql.entity.*;
import com.htttql.repository.HistorySalaryRepository;
import com.htttql.repository.SalaryRepository;
@Controller
@RequestMapping("/salary")
public class SalaryController {
	
	@Autowired
	private SalaryDAO salaryDAO;
	@Autowired
	private HistorySalaryRepository histRepo;
	@Autowired
	private SalaryRepository salaryRepo;
	private SalaryConvert sc = new SalaryConvert(); 
	
	@GetMapping
	public String salaryHome(Model model) {
		List<Salary> list=new ArrayList<Salary>();
		
		list=salaryDAO.findAll();
		List<SalaryDTO> listDTO=new ArrayList<SalaryDTO>();
		
		

		for(int i=0;i<list.size();i++) {
			HistorySalary h=salaryDAO.getInMonth(list.get(i));
			SalaryDTO sDTO=new SalaryDTO();
			sDTO=sc.toDTO(list.get(i));
			if(h!=null) {
				sDTO.setStatus(false);
			}
			else {
				sDTO.setStatus(true);
			}
			listDTO.add(sDTO);
		}
		
		
		model.addAttribute("list", listDTO);
		return "salary/salary";
	}
	
	@GetMapping("/create")
	public String paySalary(Model model,@RequestParam("id") String id) {
		Salary salary=salaryDAO.findById(Integer.parseInt(id));
		
		salaryDAO.pay(salary);
		return "redirect:/salary";
	}
	
	@GetMapping("/undo")
	public String undoSalary(Model model,@RequestParam("id") String id) {
		Salary salary=salaryDAO.findById(Integer.parseInt(id));
		HistorySalary h=salaryDAO.getInMonth(salary);
		histRepo.deleteById(h.getId());
		
		return "redirect:/salary";
	}
	
	@GetMapping("/hispay")
	public String hispay(Model model) {
	
		List<HistorySalary> list=new ArrayList<HistorySalary>();
		list=histRepo.findAll();
		Collections.reverse(list);
		Double total=0.0;
		Double totalTax=0.0;
		for(HistorySalary l:list) {
			total+=l.getMoney();
			if(l.getMoney()<=9000000) {
				totalTax+=0;
			}
			else {
				totalTax+=((l.getMoney()-9000000)*0.15);
			}
		}
		model.addAttribute("total", total);
		model.addAttribute("totalTax", totalTax);
		model.addAttribute("list", list);
		return "salary/paySalary";
	}
	
	@GetMapping("/hispay/search")
	public String hispaySearch(Model model,
								@RequestParam("startdate") String startDate,
								@RequestParam("enddate") String endDate) {
		if(startDate.equals("")||endDate.equals("")) {
			return "redirect:/salary/hispay";
		}
		else {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy-MM-dd");
			try {

				LocalDate ed = LocalDate.parse(endDate, dtf).plusDays(1);
				Date sDate=sdf.parse(startDate);
				Date eDate=Date.from(ed.atStartOfDay(ZoneId.systemDefault()).toInstant());
				List<HistorySalary> list=new ArrayList<HistorySalary>();
				list=histRepo.findByReceiveDateBetween(sDate,eDate);
				Double total=0.0;
				Double totalTax=0.0;
				for(HistorySalary l:list) {
					total+=l.getMoney();
					if(l.getMoney()<=9000000) {
						totalTax+=0;
					}
					else {
						totalTax+=((l.getMoney()-9000000)*0.15);
					}
				}
				model.addAttribute("total", total);
				model.addAttribute("totalTax", totalTax);
				model.addAttribute("list", list);
			}	
			catch(Exception e){
				
			}
		}
		return "salary/paySalary";
	}
	
	
	@GetMapping("/detail")
	public String detail(Model model,@RequestParam("id") String id) {
		Salary s=salaryDAO.findById(Integer.parseInt(id));
		List<HistorySalary> list=new ArrayList<HistorySalary>();
		list=histRepo.findBySalary(s);
		Collections.reverse(list);
		model.addAttribute("list", list);
		return "salary/detail";
	}
	@GetMapping("/search")
	public String search(Model model, @RequestParam("position") String position) {
		
		List<Salary> list=salaryDAO.findByPositionAndStatus(false, position);
		model.addAttribute("list", list);
		return "salary/salary";
	}
}
