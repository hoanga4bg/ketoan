package com.htttql.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;


import lombok.Data;

@Data
@Entity
public class Accountant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String phoneNumber;
	
	
	@OneToMany(mappedBy = "createBy")
	private List<ExpenseStatistic> listExpense;
	
	@OneToMany(mappedBy = "createBy")
	private List<RevenueStatistics> listRevenue;
	
	@OneToMany(mappedBy = "createBy")
	private List<Bill> bills;
	
	@OneToMany(mappedBy = "accountant")
	private List<Receipt> receipts;
	
	@OneToMany(mappedBy = "accountant")
	private List<IncurredBill> incurredBills;
}
