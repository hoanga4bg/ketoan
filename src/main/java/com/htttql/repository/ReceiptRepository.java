package com.htttql.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.htttql.entity.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer>{

	public Receipt findOneById(Integer id);
	
	
	List<Receipt> findByCreatedDateBetween(Date startDate,Date endDate);
	

}
