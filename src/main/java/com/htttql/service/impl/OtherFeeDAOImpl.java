package com.htttql.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htttql.entity.OtherFee;
import com.htttql.repository.OtherFeeRepository;
import com.htttql.service.OtherFeeDAO;


@Service
public class OtherFeeDAOImpl implements OtherFeeDAO{
	
	
	@Autowired
	private OtherFeeRepository otherRepo;
	@Override
	public List<OtherFee> findAll() {
		
		return otherRepo.findAll();
	}

	@Override
	public OtherFee save(OtherFee t) {
		
		return otherRepo.save(t);
	}

	@Override
	public OtherFee findOneById(int id) {
	
		return otherRepo.findOneById(id);
	}

	@Override
	public void delete(int id) {
		otherRepo.deleteById(id);
		
	}

	@Override
	public OtherFee update(int id, OtherFee t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OtherFee> findByMonthAndYear(int month, int year) {
		List<OtherFee> list=new ArrayList<OtherFee>();
		list=otherRepo.findByMonthAndYear(month,year);
		return list;
	}

	@Override
	public List<OtherFee> findByCreateDate(Date sDate, Date eDate) {
		List<OtherFee> list=new ArrayList<OtherFee>();
		list=otherRepo.findByCreateDateBetween(sDate,eDate);
		return list;
	}

}
