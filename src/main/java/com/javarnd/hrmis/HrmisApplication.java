package com.javarnd.hrmis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javarnd.hrmis.model.Employee;
import com.javarnd.hrmis.repository.EmployeeRepository;

@SpringBootApplication
public class HrmisApplication implements CommandLineRunner {

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public void employeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HrmisApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Employee employee = new Employee();
		employee.setEmpName("Employee 1");
		employeeRepository.save(employee);
		
	}
}
