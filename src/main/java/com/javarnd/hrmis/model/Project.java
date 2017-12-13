package com.javarnd.hrmis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PROJECT_DETAILS")
public class Project extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3369454155370084918L;
	@OneToMany(mappedBy = "project")
	private Set<EmployeeProject> employeeProjects = new HashSet<EmployeeProject>();

	public Set<EmployeeProject> getEmployeeProjects() {
		return employeeProjects;
	}

	public void setEmployeeProjects(Set<EmployeeProject> employeeProjects) {
		this.employeeProjects = employeeProjects;
	}
	
}