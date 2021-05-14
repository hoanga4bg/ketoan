package com.htttql.service;

import java.util.Date;
import java.util.List;

import com.htttql.entity.OtherFee;

public interface OtherFeeDAO extends GeneralService<OtherFee>{
	public List<OtherFee> findByMonthAndYear(int month,int year);
	public List<OtherFee> findByCreateDate(Date sDate,Date eDate);
}
