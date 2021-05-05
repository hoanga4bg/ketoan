package com.htttql.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Salary {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nameStaff;
	private String position;
	private Double basicSalary;
	private int dayWork;
	
	@OneToOne
	@JoinColumn(name = "tax_id")
	private Tax tax;
	

	
}
