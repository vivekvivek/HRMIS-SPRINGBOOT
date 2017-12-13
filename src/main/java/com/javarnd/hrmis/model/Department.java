package com.javarnd.hrmis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Department extends BaseEntity {
	
	private String departmentName;
	
	//@OneToMany(targetEntity=Employee.class, mappedBy = "department", cascade=CascadeType.ALL)
	@OneToMany(targetEntity=Employee.class, mappedBy = "department")
	private Set<Employee> employees = new HashSet<Employee>();

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}