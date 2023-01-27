package com.te.newtest.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.newtest.dao.EmployeeDao;
import com.te.newtest.dto.EmployeeRequsetDto;
import com.te.newtest.entity.Employee;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	public EmployeeDao employeeDao;

	@Override
	public Employee register(EmployeeRequsetDto dto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(dto, employee);
		return employeeDao.save(employee);
	}

	@Override
	public List<EmployeeRequsetDto> getAllBy(String any) {
		List<Employee> findAll = employeeDao.findAll();
		if (any.equalsIgnoreCase("byage")) {
			List<Employee> list = findAll.stream().sorted(Comparator.comparing(Employee::getAge)).toList();
			List<EmployeeRequsetDto> all = new ArrayList<>();
			for (Employee employee : list) {
				EmployeeRequsetDto dto = new EmployeeRequsetDto();
				BeanUtils.copyProperties(employee, dto);
				all.add(dto);
			}
			log.info("List of emps based on age: { }", all);
			return all;
		} else if (any.equalsIgnoreCase("bysal")) {
			List<Employee> collect = findAll.stream().sorted(Comparator.comparing(Employee::getSal)).toList();
			List<EmployeeRequsetDto> all = new ArrayList<>();
			for (Employee employee : collect) {
				EmployeeRequsetDto dto = new EmployeeRequsetDto();
				BeanUtils.copyProperties(employee, dto);
				all.add(dto);
			}
			log.info("List of emps based on sal: { }", all);
			return all;
		}
		return Collections.emptyList();
	}

	@Override
	public List<EmployeeRequsetDto> getAll(String any) {
		List<Employee> findAll = employeeDao.findAll();
		if (any.equalsIgnoreCase("byage")) {
			List<Employee> list = findAll.stream().sorted(Comparator.comparing(Employee::getAge)).toList();
			List<EmployeeRequsetDto> all = new ArrayList<>();
			for (Employee employee : list) {
				EmployeeRequsetDto dto = new EmployeeRequsetDto();
				BeanUtils.copyProperties(employee, dto);
				all.add(dto);
			}
			log.info("List of emps based on age: { }", all);
			return all;
		} else if (any.equalsIgnoreCase("bysal")) {
			List<Employee> collect = findAll.stream().sorted(Comparator.comparing(Employee::getSal)).toList();
			List<EmployeeRequsetDto> all = new ArrayList<>();
			for (Employee employee : collect) {
				EmployeeRequsetDto dto = new EmployeeRequsetDto();
				BeanUtils.copyProperties(employee, dto);
				all.add(dto);
			}
			log.info("List of emps based on sal: { }", all);
			return all;
		}
		return Collections.emptyList();
	}
}
