package com.javarnd.hrmis.repository;

import org.springframework.stereotype.Repository;

import com.javarnd.hrmis.model.Employee;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee> {
}