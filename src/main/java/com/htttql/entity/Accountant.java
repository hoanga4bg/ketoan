package com.htttql.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Accountant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String phoneNumber;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	
	private String address;
	
	@OneToMany(mappedBy = "createBy")
	private List<ExpenseStatistic> listExpense;
	
	@OneToMany(mappedBy = "createBy")
	private List<RevenueStatistics> listRevenue;
	
	@OneToMany(mappedBy = "createBy")
	private List<Bill> bills;
	@OneToMany(mappedBy = "createBy")
	private List<Income> listIncome;
	
	@OneToMany(mappedBy = "createBy")
	private List<Tax> listTax;
	@OneToMany(mappedBy = "accountant")
	private List<Receipt> receipts;
	
	@OneToMany(mappedBy = "accountant")
	private List<IncurredBill> incurredBills;
	
	@OneToMany(mappedBy = "accountant")
	private List<HistorySalary> historySalarys;
	
	
}
