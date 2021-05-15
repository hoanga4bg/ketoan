package com.htttql.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	private Double totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "accountant_id")
	
	private Accountant createBy;
	
	@OneToOne
	@JoinColumn(name="orders_id")
	private Orders orders;
}
