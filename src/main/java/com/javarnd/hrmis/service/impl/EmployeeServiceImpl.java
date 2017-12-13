package com.javarnd.hrmis.service.impl;

import org.springframework.stereotype.Service;

import com.javarnd.hrmis.dto.EmployeeModel;
import com.javarnd.hrmis.model.Employee;
import com.javarnd.hrmis.service.EmployeeService;

@Service
public class EmployeeServiceImpl extends CrudServiceImpl<Employee, EmployeeModel> implements EmployeeService {
 
}