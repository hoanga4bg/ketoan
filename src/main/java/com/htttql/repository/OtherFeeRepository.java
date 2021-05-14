package com.htttql.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.OtherFee;

public interface OtherFeeRepository extends JpaRepository<OtherFee,Integer>{

	public List<OtherFee> findByMonthAndYear(int month, int year);

	public OtherFee findOneById(int id);

	public List<OtherFee> findByCreateDateBetween(Date sDate, Date eDate);

}
