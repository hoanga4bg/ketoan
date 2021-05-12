package com.htttql.service;

import java.util.ArrayList;

public interface BillDAO {
	public  ArrayList<String> readNum(String a);
	public double revenueByMonthNow();
	public double salePriceByMonth(int month,int year);
}
