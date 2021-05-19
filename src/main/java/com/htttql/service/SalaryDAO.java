package com.htttql.service;


import java.util.List;

import com.htttql.entity.HistorySalary;
import com.htttql.entity.Salary;
public interface SalaryDAO {
	public List<Salary> findAll();
	public Salary findById(Integer id);
	public void addSalary(Salary salary);
	public void deleteSalary(Integer id);
	public void pay(Salary salary);
	public HistorySalary getInMonth(Salary salary);
	public double getTotalSalaryOfMonthNow();
	public List<Salary> findByPositionAndStatus(boolean status, String position);
<<<<<<< HEAD
	public List<HistorySalary> getAllInMonth(int month,int year);
=======
	public List<HistorySalary> getAllInMonth(int month, int year);
>>>>>>> df366623f0da99b8a1dbc4181d6ae435d7e9c6a0
}
