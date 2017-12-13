package com.javarnd.hrmis.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.javarnd.hrmis.constant.Gender;
import com.javarnd.hrmis.constant.MaritalStatus;


@Entity
@Table(name="EMPLOYEE_DETAILS")
public class Employee extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5436742491317719312L;

	@Column(name = "employee_name" , nullable = false)
	private String empName;
	
	@Column(name = "contact_number" , nullable = false)
	private String contactNumber;
	
	@Column(name = "passport_number" , nullable = false)
	private String passportNumber;
	
	@Column(name = "account_number" , nullable = false)
	private String bankAccountNumber;
	
	@Column(name = "voter_id" , nullable = false)
	private String voterId;
	
	@Column(name = "driving_license" , nullable = false)
	private String drivingLicense;
	
	private String uid;
	
	private String pancard;
	
	private String email;
	
	private String designation;
	
	private String grade;
	
	@Column(name = "date_of_birth" , nullable = false)
	private Date dob;
	
	@Column(name = "date_of_confirmation" , nullable = false)
	private Date dateOfConfirmation;
	
	@Column(name = "date_of_joining" , nullable = false)
	private Date dateOfJoining;
	
	@Column(name = "gender" )
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(name = "marital_status" )
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;
	
	private Department department;
	
	@Embedded
	private List<Address> address;
	
	private List<Project> projects;
	
	private List<Salary> salaries;

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPancard() {
		return pancard;
	}

	public void setPancard(String pancard) {
		this.pancard = pancard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDateOfConfirmation() {
		return dateOfConfirmation;
	}

	public void setDateOfConfirmation(Date dateOfConfirmation) {
		this.dateOfConfirmation = dateOfConfirmation;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Salary> getSalaries() {
		return salaries;
	}

	public void setSalaries(List<Salary> salaries) {
		this.salaries = salaries;
	}
	
}