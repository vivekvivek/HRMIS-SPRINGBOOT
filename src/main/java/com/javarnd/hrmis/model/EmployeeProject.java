package com.javarnd.hrmis.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_project")
public class EmployeeProject implements Serializable {

	@Id
    @ManyToOne(targetEntity=Employee.class)
    @JoinColumn(name = "employee_id")
	private Employee employee;
	
	@Id
    @ManyToOne(targetEntity=Project.class)
    @JoinColumn(name = "project_id")
	private Project project;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
}
