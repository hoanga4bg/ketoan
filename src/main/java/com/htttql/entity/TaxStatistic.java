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
public class TaxStatistic {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Double total;
	private int month;
	private int year;
	
	private Date createDate;
	@ManyToOne
	@JoinColumn(name="accountant_id")
	private Accountant createBy;
	
	@ManyToOne
	@JoinColumn(name="tax_id")
	private Tax tax;
}
