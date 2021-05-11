package com.htttql.dto;



import lombok.Data;

@Data
public class SalaryDTO {
	private Integer id;
	private String nameStaff;
	private String position;
	private Double basicSalary;
	private boolean status ;

}
