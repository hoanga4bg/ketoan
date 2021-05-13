package com.htttql.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	private boolean status;

	@OneToMany(mappedBy = "salary")
	private List<HistorySalary> historySalary;
	
	
	
}
