package com.htttql.entity;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class ExpenseStatistic {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Double total;
	private Double salaryTotal;
	private Double importTotal;
	private Double otherTotal;
	private Date createDate;
	private int month;
	private int year;
	@ManyToOne
	@JoinColumn(name="accountant_id")
	private Accountant createBy;
}
