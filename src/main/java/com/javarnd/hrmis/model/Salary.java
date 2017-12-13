package com.javarnd.hrmis.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Salary extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -879515573004080915L;

	private double salary;
	
	@Column(name = "credit date" , nullable = false)
	private Date creditDate;
	
	private Employee employee;

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getCreditDate() {
		return creditDate;
	}

	public void setCreditDate(Date creditDate) {
		this.creditDate = creditDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}