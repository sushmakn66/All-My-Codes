package com.te.newtest.service;

import java.util.List;

import com.te.newtest.dto.EmployeeRequsetDto;
import com.te.newtest.entity.Employee;

public interface EmployeeService {
	
	Employee register(EmployeeRequsetDto dto);
	
	List<EmployeeRequsetDto> getAllBy(String any);
	
	List<EmployeeRequsetDto> getAll(String any);
	

}
