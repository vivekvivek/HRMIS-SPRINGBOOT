package com.javarnd.hrmis;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.javarnd.hrmis.model.Authority;
import com.javarnd.hrmis.model.Employee;
import com.javarnd.hrmis.repository.AuthorityRepository;
import com.javarnd.hrmis.repository.EmployeeRepository;

@SpringBootApplication
public class HrmisApplication implements CommandLineRunner {

	private EmployeeRepository employeeRepository;
	private AuthorityRepository authorityRepository;
	
	@Autowired
	public void employeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	@Autowired
	public void authorityRepository(AuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
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
		if(employeeRepository.count() == 0){
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			Employee user = new Employee();
			//user.setEmpCode(101L);
			user.setEmpName("Alex");
			user.setPassword(passwordEncoder.encode("password"));
			user.setAuthorities(new HashSet<Authority>(Arrays.asList(authorityUser)));
			employeeRepository.save(user);
			
			Employee admin = new Employee();
			//admin.setEmpCode(102L);
			admin.setEmpName("Flex");
			admin.setPassword(passwordEncoder.encode("adminpassword"));
			admin.setAuthorities(new HashSet<Authority>(Arrays.asList(authorityAdmin)));
			employeeRepository.save(admin);
			
			Employee superUser = new Employee();
			//superUser.setEmpCode(103L);
			superUser.setEmpName("AlexFlex");
			superUser.setPassword(passwordEncoder.encode("superassword"));
			superUser.setAuthorities(new HashSet<Authority>(Arrays.asList(authorityUser, authorityAdmin)));
			employeeRepository.save(superUser);
		}
	}
}
