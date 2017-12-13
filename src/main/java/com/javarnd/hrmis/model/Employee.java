package com.javarnd.hrmis.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee code")
	private long empCode;
	
	@Column(name = "employee name" , nullable = false)
	private String empName;
	
	@Column(name = "contact number" , nullable = false)
	private String contactNumber;
	
	@Column(name = "date of birth" , nullable = false)
	private Date dob;
	
	@Column(name = "date of confirmation" , nullable = false)
	private Date dateOfConfirmation;
	
	@Column(name = "date of joining" , nullable = false)
	private Date dateOfJoining;
	
	
	private List<Address> address;
	
	private Enum gender ;
	
	@Column(name = "maritial Status" )
	private Enum maritialStatus;
	
	@Column(name = "passport number" , nullable = false)
	private String passportNumber;
	
	@Column(name = "account number" , nullable = false)
	private String bankAccountNumber;
	
	@Column(name = "voter id" , nullable = false)
	private String voterId;
	
	@Column(name = "driving license" , nullable = false)
	private String drivingLicense;
	
	private String uid;
	
	private String pancard;
	
	private String Email;
	
	private String designation;
	
	private String grade;
	
	private List<Departmet> departments;
	
	private List<Salary> salaries;
	
	private List<Project> projects;

	public long getEmpCode() {
		return empCode;
	}

	public void setEmpCode(long empCode) {
		this.empCode = empCode;
	}

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

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Enum getGender() {
		return gender;
	}

	public void setGender(Enum gender) {
		this.gender = gender;
	}

	public Enum getMaritialStatus() {
		return maritialStatus;
	}

	public void setMaritialStatus(Enum maritialStatus) {
		this.maritialStatus = maritialStatus;
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
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
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

	public List<Departmet> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Departmet> departments) {
		this.departments = departments;
	}

	public List<Salary> getSalaries() {
		return salaries;
	}

	public void setSalaries(List<Salary> salaries) {
		this.salaries = salaries;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	
}
