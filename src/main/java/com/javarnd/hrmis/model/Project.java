package com.javarnd.hrmis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PROJECT_DETAILS")
public class Project extends BaseEntity {
	
	@OneToMany(mappedBy = "project")
	private Set<EmployeeProject> employeeProjects = new HashSet<EmployeeProject>();

	public Set<EmployeeProject> getEmployeeProjects() {
		return employeeProjects;
	}

	public void setEmployeeProjects(Set<EmployeeProject> employeeProjects) {
		this.employeeProjects = employeeProjects;
	}
	
}