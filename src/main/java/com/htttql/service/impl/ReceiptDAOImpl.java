package com.htttql.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htttql.entity.Receipt;
import com.htttql.repository.ReceiptRepository;
import com.htttql.service.ReceiptDAO;

@Service
public class ReceiptDAOImpl implements ReceiptDAO{
	
	@Autowired
	private ReceiptRepository receiptRepo;
	
	@Override
	public List<Receipt> findAll() {
		List<Receipt> list=new ArrayList<Receipt>();
		list = receiptRepo.findAll();
		return list;
	}

}
