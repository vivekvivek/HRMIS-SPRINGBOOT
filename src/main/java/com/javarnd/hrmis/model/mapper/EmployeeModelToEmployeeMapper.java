package com.javarnd.hrmis.model.mapper;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import com.javarnd.hrmis.dto.EmployeeModel;
import com.javarnd.hrmis.model.Employee;

//@Component
public class EmployeeModelToEmployeeMapper extends ConverterConfigurerSupport<EmployeeModel, Employee> {
    
	@Override
    protected Converter<EmployeeModel, Employee> converter() {
        return new AbstractConverter<EmployeeModel, Employee>() {
            @Override
            protected Employee convert(EmployeeModel source) {
                System.out.println("converter method from UserModelToUserMapper class");
                Employee user = new Employee();
                
                return user;
            }
        };
    }
	
}