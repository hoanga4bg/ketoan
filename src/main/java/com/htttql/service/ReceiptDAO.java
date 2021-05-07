package com.htttql.service;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.context.annotation.Bean;

import com.htttql.entity.Receipt;


public interface ReceiptDAO {
	
	public List<Receipt> findAll();
}
