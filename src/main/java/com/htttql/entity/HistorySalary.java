package com.htttql.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class HistorySalary {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Date receiveDate;
	
	private Double money;
	@ManyToOne
	@JoinColumn(name = "salary_id")
	private Salary salary;
	
	@ManyToOne
	@JoinColumn(name = "accountant_id")
	private Accountant accountant;
}
