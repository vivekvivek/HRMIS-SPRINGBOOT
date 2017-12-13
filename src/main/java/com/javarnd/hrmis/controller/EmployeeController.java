package com.javarnd.hrmis.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javarnd.hrmis.dto.EmployeeModel;
import com.javarnd.hrmis.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/employees")
@Api(value = "EmployeeControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private EmployeeService employeeService;
    
    private Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ApiOperation("Gets the employee with specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = EmployeeModel.class)})
    @ResponseStatus(value=HttpStatus.OK)
    public Resource<EmployeeModel> getEmployee(@PathVariable(name = "id") String id) throws UserException {
    	return getEmployeeModelResource(employeeService.findOne(Long.parseLong(id)));
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value=HttpStatus.OK)
    public Collection<Resource<EmployeeModel>> getAllEmployees() throws UserException {
        List<EmployeeModel> employees = employeeService.findAll();
		if(employees != null) {
			List<Resource<EmployeeModel>> resources = new ArrayList<Resource<EmployeeModel>>();
			for(EmployeeModel employeeModel : employees)
				resources.add(getEmployeeModelResource(employeeModel));
			return resources;
		}
		return null;
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value=HttpStatus.CREATED)
    public Resource<EmployeeModel> saveEmployee(@RequestBody EmployeeModel employeeToSave) throws UserException {
        return getEmployeeModelResource(employeeService.create(employeeToSave));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value=HttpStatus.OK)
    public Resource<EmployeeModel> updateEmployee(@RequestBody EmployeeModel employeeToUpdate, @PathVariable(name = "id") Long id) throws UserException {
    	employeeToUpdate.setId(id);
        return getEmployeeModelResource(employeeService.update(employeeToUpdate));
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value=HttpStatus.OK)
    public void deleteEmployee(@PathVariable(name = "id") String id) {
    	employeeService.delete(Long.parseLong(id));
    }
    
    private Resource<EmployeeModel> getEmployeeModelResource(EmployeeModel employeeModel) throws UserException {
    	System.out.println(employeeModel.getEmpName());
		Resource<EmployeeModel> resource = new Resource<EmployeeModel>(employeeModel);
		// Link to EmployeeModel
		resource.add(linkTo(methodOn(EmployeeController.class).getEmployee(String.valueOf(employeeModel.getId()))).withSelfRel());
		return resource;
	}
    
}