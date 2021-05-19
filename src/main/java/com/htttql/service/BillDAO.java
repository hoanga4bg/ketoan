package com.htttql.service;

import java.util.ArrayList;

import com.htttql.entity.Product;

public interface BillDAO {
	public  ArrayList<String> readNum(String a);
	public double revenueByMonthNow();
	public double salePriceByMonth(int month,int year);
	public double salePriceOfProductByMonthAndYear(int month,int year,Product p);
}
