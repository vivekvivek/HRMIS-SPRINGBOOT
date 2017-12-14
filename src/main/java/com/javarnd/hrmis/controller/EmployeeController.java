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

	@Autowired
    private EmployeeService employeeService;
    
    private Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    
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
        List<EmployeeModel> books = employeeService.findAll();
		if(books != null) {
			List<Resource<EmployeeModel>> resources = new ArrayList<Resource<EmployeeModel>>();
			for(EmployeeModel bookModel : books)
				resources.add(getEmployeeModelResource(bookModel));
			return resources;
		}
		return null;
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value=HttpStatus.CREATED)
    public Resource<EmployeeModel> saveEmployee(@RequestBody EmployeeModel bookToSave) throws UserException {
        
    	
    	
    	return getEmployeeModelResource(employeeService.create(bookToSave));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value=HttpStatus.OK)
    public Resource<EmployeeModel> updateEmployee(@RequestBody EmployeeModel bookToUpdate, @PathVariable(name = "id") String id) throws UserException {
        return getEmployeeModelResource(employeeService.update(bookToUpdate));
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value=HttpStatus.OK)
    public void deleteEmployee(@PathVariable(name = "id") String id) {
    	employeeService.delete(Long.parseLong(id));
    }
    
    private Resource<EmployeeModel> getEmployeeModelResource(EmployeeModel bookModel) throws UserException {
		Resource<EmployeeModel> resource = new Resource<EmployeeModel>(bookModel);
		// Link to EmployeeModel
		resource.add(linkTo(methodOn(EmployeeController.class).getEmployee(String.valueOf(bookModel.getId()))).withSelfRel());
		return resource;
	}
    
}