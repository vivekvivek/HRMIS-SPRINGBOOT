package com.javarnd.hrmis.model;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Department extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4220057626667902900L;

	private String departmentName;
	
	private List<Employee> employees;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
}