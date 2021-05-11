package com.htttql.dto.convert;

import org.springframework.stereotype.Component;

import com.htttql.dto.SalaryDTO;
import com.htttql.entity.Salary;

@Component
public class SalaryConvert {
	public SalaryDTO toDTO(Salary salary) {
		SalaryDTO salaryDTO=new SalaryDTO();
		salaryDTO.setBasicSalary(salary.getBasicSalary());
		salaryDTO.setId(salary.getId());
		salaryDTO.setNameStaff(salary.getNameStaff());
		salaryDTO.setPosition(salary.getPosition());
		return salaryDTO;
		
	}
}
