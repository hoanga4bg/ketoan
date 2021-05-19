package com.htttql.dto;

import java.util.List;

import com.htttql.entity.Bill;
import com.htttql.entity.Orders;

import lombok.Data;
@Data
public class DetailInComeDTO {
	private double total;
	private List<Bill> bill;
	private int amount;
	private int month;
	private int year;
	private double vat;

}
