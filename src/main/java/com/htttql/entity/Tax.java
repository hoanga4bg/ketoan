package com.htttql.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import lombok.Data;


@Entity
@Data
public class Tax{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private Double coefficient;
	
	@OneToMany(mappedBy = "tax")
	private List<TaxStatistic> listTax;
}
