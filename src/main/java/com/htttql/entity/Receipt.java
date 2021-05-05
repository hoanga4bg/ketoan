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
public class Receipt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Date createdDate;
	private Double totalPrice;
	@ManyToOne
	@JoinColumn(name = "accountant_id")
	private Accountant accountant;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
}
