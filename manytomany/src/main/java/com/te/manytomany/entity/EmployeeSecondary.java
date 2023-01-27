package com.te.manytomany.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "emp_secondary")
public class EmployeeSecondary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int secId;
	private String panNum;
	private String aadharNum;
	private String designation;
	private String department;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "employeeSecondary")
	private Employee employee;

}