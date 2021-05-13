package com.htttql.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



import lombok.Data;

@Data
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private Double salePrice;
	private Double importPrice;
	private int status;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;
	
	@OneToMany(mappedBy = "product")
	private List<Receipt> receipts;
	
	@OneToMany(mappedBy = "product")
	private List<Orders> orders;
}
