package com.htttql.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htttql.entity.Accountant;
import com.htttql.entity.Income;
import com.htttql.repository.IncomeRepository;
import com.htttql.service.IncomeDAO;
@Service
public class IncomeDAOImpl implements IncomeDAO {
	@Autowired
	private IncomeRepository incomeRepository;

	@Override
	public List<Income> findAll() {
		// TODO Auto-generated method stub
		return incomeRepository.findAll();
	}

	@Override
	public Income save(Income t) {
		// TODO Auto-generated method stub
		return incomeRepository.save(t);
	}

	@Override
	public Income findOneById(int id) {
		// TODO Auto-generated method stub
		return incomeRepository.findOneById(id);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		incomeRepository.delete(incomeRepository.findOneById(id));
	}

	@Override
	public Income update(int id, Income t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Income> findByCreateDateBetween(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return incomeRepository.findByCreateDateBetween(startDate, endDate);
	}

	@Override
	public List<Income> findByCreateBy(Accountant acc) {
		// TODO Auto-generated method stub
		return incomeRepository.findByCreateBy(acc);
	}

}
