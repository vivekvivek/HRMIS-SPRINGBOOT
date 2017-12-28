package com.javarnd.hrmis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(value="Department")
@GenericGenerator(name=IdGen.NAME, strategy=IdGen.AUTO)
public class Department extends IdEntity<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 163268369889699144L;

	private String departmentName;
	
	//@OneToMany(targetEntity=Employee.class, mappedBy = "department", cascade=CascadeType.ALL)
	//@OneToMany(targetEntity=Employee.class, mappedBy = "department")
	private Set<Employee> employees = new HashSet<Employee>();

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@OneToMany(targetEntity=Employee.class, mappedBy = "department")
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}