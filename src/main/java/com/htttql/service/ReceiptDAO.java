package com.htttql.service;

import java.util.List;

import org.jvnet.hk2.annotations.Service;

import com.htttql.entity.Receipt;

@Service
public interface ReceiptDAO {
	public List<Receipt> findAll();
}
