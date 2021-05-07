package com.htttql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.htttql.entity.Receipt;
import com.htttql.repository.ReceiptRepository;
import com.htttql.service.ReceiptDAO;

public class ReceiptDAOImpl implements ReceiptDAO{
	
	@Autowired
	private ReceiptRepository receiptRepo;
	
	@Override
	public List<Receipt> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
