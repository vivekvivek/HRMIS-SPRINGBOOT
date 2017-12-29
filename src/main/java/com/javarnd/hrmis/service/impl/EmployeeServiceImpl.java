package com.javarnd.hrmis.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javarnd.hrmis.dto.EmployeeModel;
import com.javarnd.hrmis.model.Authority;
import com.javarnd.hrmis.model.Employee;
import com.javarnd.hrmis.repository.EmployeeRepository;
import com.javarnd.hrmis.service.EmployeeService;

@Service(value = "employeeService")
public class EmployeeServiceImpl extends CrudServiceImpl<Employee, EmployeeModel> implements UserDetailsService, EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		Long employeeId = Long.parseLong(username);
		Employee employeeDb = employeeRepository.findOne(employeeId);
		
		if(employeeDb == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : employeeDb.getAuthorities()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }
		
		//return new org.springframework.security.core.userdetails.User(String.valueOf(user.getId()), user.getPassword(), getAuthority());
        return new org.springframework.security.core.userdetails.User(String.valueOf(employeeDb.getEmpCode()), employeeDb.getPassword(), grantedAuthorities);
	
	}
 
}