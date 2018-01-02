package com.javarnd.hrmis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.javarnd.hrmis.constant.IdGen;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name="PROJECT_DETAILS")
@ApiModel(value="Project")
@GenericGenerator(name=IdGen.NAME, strategy=IdGen.AUTO)
public class Project extends IdEntity<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3369454155370084918L;
	//@OneToMany(mappedBy = "project")
	private Set<EmployeeProject> employeeProjects = new HashSet<EmployeeProject>();

	@OneToMany(mappedBy = "project")
	public Set<EmployeeProject> getEmployeeProjects() {
		return employeeProjects;
	}

	public void setEmployeeProjects(Set<EmployeeProject> employeeProjects) {
		this.employeeProjects = employeeProjects;
	}
	
}