package com.javarnd.hrmis.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.javarnd.hrmis.constant.Gender;
import com.javarnd.hrmis.constant.MaritalStatus;


@Entity
@Table(name="EMPLOYEE_DETAILS")
public class Employee extends BaseEntity {
	
	//@Column(name = "employee_name" , nullable = false)
	@Column(name = "employee_name")
	private String empName;
	
	//@Column(name = "contact_number" , nullable = false)
	@Column(name = "contact_number")
	private String contactNumber;
	
	//@Column(name = "passport_number" , nullable = false)
	@Column(name = "passport_number")
	private String passportNumber;
	
	//@Column(name = "account_number" , nullable = false)
	@Column(name = "account_number")
	private String bankAccountNumber;
	
	//@Column(name = "voter_id" , nullable = false)
	@Column(name = "voter_id")
	private String voterId;
	
	//@Column(name = "driving_license" , nullable = false)
	@Column(name = "driving_license")
	private String drivingLicense;
	
	private String uid;
	
	private String pancard;
	
	private String email;
	
	private String designation;
	
	private String grade;
	
	//@Column(name = "date_of_birth" , nullable = false)
	@Column(name = "date_of_birth")
	private Date dob;
	
	//@Column(name = "date_of_confirmation" , nullable = false)
	@Column(name = "date_of_confirmation")
	private Date dateOfConfirmation;
	
	//@Column(name = "date_of_joining" , nullable = false)
	@Column(name = "date_of_joining")
	private Date dateOfJoining;
	
	@Column(name = "gender" )
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(name = "marital_status" )
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;
	
	private Department department;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<EmployeeProject> employeeProjects = new HashSet<EmployeeProject>();
	
	@ElementCollection
	private Set<Address> listOfAddress = new HashSet<Address>();

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

	public Set<Address> getListOfAddress() {
		return listOfAddress;
	}

	public void setListOfAddress(Set<Address> listOfAddress) {
		this.listOfAddress = listOfAddress;
	}

	public Set<EmployeeProject> getEmployeeProjects() {
		return employeeProjects;
	}

	public void setEmployeeProjects(Set<EmployeeProject> employeeProjects) {
		this.employeeProjects = employeeProjects;
	}
	
}