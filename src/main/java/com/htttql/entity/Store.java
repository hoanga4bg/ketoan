package com.htttql.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;
import lombok.Data;

@Data
@Entity
public class Store {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private int amount;
	
	private Date createDate;
	
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
