package com.te.manytomany.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.manytomany.dto.EmployeeDeleteDto;
import com.te.manytomany.dto.EmployeeRegisterDto;
import com.te.manytomany.dto.EmployeeSecondaryDto;
import com.te.manytomany.dto.EmployeeTechnicalSkillsDto;
import com.te.manytomany.dto.EmployeeUpdateDto;
import com.te.manytomany.entity.Employee;
import com.te.manytomany.entity.EmployeeSecondary;
import com.te.manytomany.entity.EmployeeTechnicalSkills;
import com.te.manytomany.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public EmployeeRegisterDto register(EmployeeRegisterDto dto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(dto, employee);
		EmployeeSecondary employeeSecondary=new EmployeeSecondary();
		EmployeeSecondaryDto employeeSecondaryDto = dto.getEmployeeSecondaryDto();
		BeanUtils.copyProperties(employeeSecondaryDto, employeeSecondary);
		employee.setEmployeeSecondary(employeeSecondary);
		List<EmployeeTechnicalSkillsDto> employeeTechnicalSkillsDtos = dto.getEmployeeTechnicalSkillsDtos();
		List<EmployeeTechnicalSkills> list = new ArrayList<>();
		employeeTechnicalSkillsDtos.forEach(i -> {
			EmployeeTechnicalSkills employeeTechnicalSkills = new EmployeeTechnicalSkills();
			BeanUtils.copyProperties(i, employeeTechnicalSkills);
			list.add(employeeTechnicalSkills);
			employee.setEmployeeTechnicalSkills(list);
		});
		Employee save = employeeRepo.save(employee);
		BeanUtils.copyProperties(save, dto);
		return dto;
	}

	@Override
	public List<EmployeeRegisterDto> get() {
		List<Employee> findAll = employeeRepo.findAll();
		List<EmployeeRegisterDto> emp = new ArrayList<>();
		EmployeeSecondary employeeSecondary = findAll.get(0).getEmployeeSecondary();
		EmployeeRegisterDto secDto = new EmployeeRegisterDto();
		BeanUtils.copyProperties(employeeSecondary, secDto);
		findAll.forEach(j -> {
			EmployeeRegisterDto skillsDto = new EmployeeRegisterDto();
			BeanUtils.copyProperties(j, skillsDto);
			emp.add(skillsDto);
		});
		return emp;
	}

	@Override
	public EmployeeUpdateDto update(EmployeeUpdateDto dto) {
		Optional<Employee> findById = employeeRepo.findById(dto.getEmpId());
		if (findById.isPresent()) {
			Employee employee = new Employee();
			BeanUtils.copyProperties(dto, employee);
			EmployeeSecondary employeeSecondary=new EmployeeSecondary();
			EmployeeSecondaryDto employeeSecondaryDto = dto.getEmployeeSecondaryDto();
			BeanUtils.copyProperties(employeeSecondaryDto, employeeSecondary);
			employee.setEmployeeSecondary(employeeSecondary);
			List<EmployeeTechnicalSkillsDto> employeeTechnicalSkillsDtos = dto.getEmployeeTechnicalSkillsDtos();
			List<EmployeeTechnicalSkills> list = new ArrayList<>();
			employeeTechnicalSkillsDtos.forEach(i -> {
				EmployeeTechnicalSkills employeeTechnicalSkills = new EmployeeTechnicalSkills();
				BeanUtils.copyProperties(i, employeeTechnicalSkills);
				list.add(employeeTechnicalSkills);
				employee.setEmployeeTechnicalSkills(list);
			});
			Employee save = employeeRepo.save(employee);
			BeanUtils.copyProperties(save, dto);
		}
		return dto;
	}

	@Override
	public EmployeeDeleteDto delete(int empId) {
		Optional<Employee> findById = employeeRepo.findById(empId);
		Employee employee = findById.get();
		EmployeeDeleteDto dto=new EmployeeDeleteDto();
		BeanUtils.copyProperties(employee, dto);
		employeeRepo.delete(employee);
		return dto;
	}
}
