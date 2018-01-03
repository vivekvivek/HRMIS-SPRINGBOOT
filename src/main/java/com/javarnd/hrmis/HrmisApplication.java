package com.javarnd.hrmis;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.javarnd.hrmis.constant.Gender;
import com.javarnd.hrmis.constant.MaritalStatus;
import com.javarnd.hrmis.model.Authority;
import com.javarnd.hrmis.model.Department;
import com.javarnd.hrmis.model.Employee;
import com.javarnd.hrmis.repository.AuthorityRepository;
import com.javarnd.hrmis.repository.DepartmentRepository;
import com.javarnd.hrmis.repository.EmployeeRepository;

@SpringBootApplication
public class HrmisApplication implements CommandLineRunner {

	private EmployeeRepository employeeRepository;
	private AuthorityRepository authorityRepository;
	private DepartmentRepository departmentRepository;
	
	@Autowired
	public void employeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	@Autowired
	public void authorityRepository(AuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}
	@Autowired
	public void departmentRepository(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HrmisApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Authority authorityUser = new Authority();
		authorityUser.setName("ROLE_USER");
		Authority authorityAdmin = new Authority();
		authorityAdmin.setName("ROLE_ADMIN");
		if(authorityRepository.count()==0) {
			authorityRepository.save(authorityUser);
			authorityRepository.save(authorityAdmin);
		}
		
		Department hrDepartment = new Department();
		hrDepartment.setDepartmentName("HR");
		Department adminDepartment = new Department();
		adminDepartment.setDepartmentName("ADMIN");
		if(departmentRepository.count()==0) {
			departmentRepository.save(hrDepartment);
			departmentRepository.save(adminDepartment);
		}
		
		if(employeeRepository.count() == 0){
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			Employee user = new Employee();
			user.setEmpCode(101L);
			user.setEmpName("Alex");
			user.setPassword(passwordEncoder.encode("password"));
			user.setAuthorities(new HashSet<Authority>(Arrays.asList(authorityUser)));
			user.setBankAccountNumber("789456123");
			user.setContactNumber("9876543217");
			user.setDesignation("Developer");
			user.setDrivingLicense("MP04A-2012-369874");
			user.setEmail("alex@javarndcorp.com");
			user.setGender(Gender.MALE);
			user.setGrade("A");
			user.setMaritalStatus(MaritalStatus.MARRIED);
			user.setPancard("7852PAN12");
			employeeRepository.save(user);
			
			Employee admin = new Employee();
			admin.setEmpCode(102L);
			admin.setEmpName("Sajid");
			admin.setPassword(passwordEncoder.encode("adminpassword"));
			admin.setAuthorities(new HashSet<Authority>(Arrays.asList(authorityAdmin)));
			admin.setDepartment(hrDepartment);
			admin.setBankAccountNumber("789456123");
			admin.setContactNumber("9876543217");
			admin.setDesignation("HR Manager");
			admin.setDrivingLicense("MP04A-2012-369874");
			admin.setEmail("sajid@javarndcorp.com");
			admin.setGender(Gender.MALE);
			admin.setGrade("A");
			admin.setMaritalStatus(MaritalStatus.MARRIED);
			admin.setPancard("7852PAN12");
			employeeRepository.save(admin);
			
			Employee superUser = new Employee();
			superUser.setEmpCode(103L);
			superUser.setEmpName("Surya");
			superUser.setPassword(passwordEncoder.encode("superpassword"));
			superUser.setAuthorities(new HashSet<Authority>(Arrays.asList(authorityUser, authorityAdmin)));
			superUser.setDepartment(adminDepartment);
			superUser.setBankAccountNumber("789456123");
			superUser.setContactNumber("9876543217");
			superUser.setDesignation("Admin Head");
			superUser.setDrivingLicense("MP04A-2012-369874");
			superUser.setEmail("surya@javarndcorp.com");
			superUser.setGender(Gender.MALE);
			superUser.setGrade("A");
			superUser.setMaritalStatus(MaritalStatus.MARRIED);
			superUser.setPancard("7852PAN12");
			employeeRepository.save(superUser);
		}
	}
}
