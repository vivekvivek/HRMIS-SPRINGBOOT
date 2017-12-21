package com.javarnd.hrmis;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javarnd.hrmis.model.Authority;
import com.javarnd.hrmis.model.AuthorityRepository;
import com.javarnd.hrmis.model.Employee;
import com.javarnd.hrmis.model.User;
import com.javarnd.hrmis.model.UserRepository;
import com.javarnd.hrmis.repository.EmployeeRepository;

@SpringBootApplication
public class HrmisApplication implements CommandLineRunner {

	private EmployeeRepository employeeRepository;
	
	private UserRepository userRepository;
	
	private AuthorityRepository authorityRepository;
	
	@Autowired
	public void userRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void authorityRepository(AuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}
	
	@Autowired
	public void employeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HrmisApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		if(employeeRepository.count()==0) {
			Employee employee = new Employee();
			employee.setEmpName("Employee 1");
			employeeRepository.save(employee);
		}
		Authority authorityUser = new Authority();
		authorityUser.setName("USER");
		Authority authorityAdmin = new Authority();
		authorityAdmin.setName("ADMIN");
		if(authorityRepository.count()==0) {
			authorityRepository.save(authorityUser);
			authorityRepository.save(authorityAdmin);
		}
		if(userRepository.count()==0){
			User user = new User();
			user.setUsername("user");
			user.setEmail("user@mail.me");
			user.setPassword("userpassword");
			user.setAuthorities(new HashSet<Authority>(Arrays.asList(authorityUser)));
			user.setActivated(true);
			userRepository.save(user);
			
			User admin = new User();
			admin.setUsername("admin");
			admin.setEmail("admin@mail.me");
			admin.setPassword("adminpassword");
			admin.setAuthorities(new HashSet<Authority>(Arrays.asList(authorityAdmin)));
			admin.setActivated(true);
			userRepository.save(admin);
			
			User superUser = new User();
			superUser.setUsername("super");
			superUser.setEmail("super@mail.me");
			superUser.setPassword("superpassword");
			superUser.setAuthorities(new HashSet<Authority>(Arrays.asList(authorityUser, authorityAdmin)));
			superUser.setActivated(true);
			userRepository.save(superUser);
		}
		
	}
}
