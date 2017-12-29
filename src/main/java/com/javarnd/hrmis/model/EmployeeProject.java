package com.javarnd.hrmis.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "employee_project")
@ApiModel(value="EmployeeProject")
public class EmployeeProject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6906715891630210616L;

	/*@Id
    @ManyToOne(targetEntity=Employee.class)
    @JoinColumn(name = "employee_id")*/
	private Employee employee;
	
	/*@Id
    @ManyToOne(targetEntity=Project.class)
    @JoinColumn(name = "project_id")*/
	private Project project;

	@Id
    @ManyToOne(targetEntity=Employee.class)
    @JoinColumn(name = "employee_id")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Id
    @ManyToOne(targetEntity=Project.class)
    @JoinColumn(name = "project_id")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
}
