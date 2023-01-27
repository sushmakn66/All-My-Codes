package com.te.manytomany.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "emp_technical")
public class EmployeeTechnicalSkills {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int skId;
	private String skill;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "employeeTechnicalSkills")
	private List<Employee> employees;

}
