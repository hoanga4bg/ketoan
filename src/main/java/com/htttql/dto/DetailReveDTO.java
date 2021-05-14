package com.htttql.dto;

import java.util.Date;

import lombok.Data;
@Data
public class DetailReveDTO {
	private int id;
	private double importPrice;
	private double salePrice;
	private double salaryPrice;
	private double psPrice;
	private double vat;
	private double tncn;
	private int month;
	private int year;
	private Date createDate;
	private String createBy;
	private double totalPrice;

}
