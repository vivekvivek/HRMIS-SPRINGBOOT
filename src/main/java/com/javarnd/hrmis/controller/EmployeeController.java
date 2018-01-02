package com.javarnd.hrmis.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.omg.CORBA.UserException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javarnd.hrmis.dto.EmployeeModel;
import com.javarnd.hrmis.security.IAuthenticationFacade;
import com.javarnd.hrmis.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/secure/employees", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "EmployeeControllerAPI", description = "Provides all actions related to employee.")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
    private IAuthenticationFacade authenticationFacade;
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ApiOperation("Gets the employee with specific id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = EmployeeModel.class) })
	@ResponseStatus(value = HttpStatus.OK)
	public Resource<EmployeeModel> getEmployee(
			@ApiParam(value = "ID of the Employee", required = true) @PathVariable(name = "id") Long id)
			throws UserException {
		logger.debug("getEmployee(POST) is invoked ... " + id);
		String username = currentUserNameSimple();
		Long empCode = Long.parseLong(username);
		if(!id.equals(empCode)) {
			// User should not be able to access any other id information
			logger.debug("User is trying to access another id information");
		}
		return getEmployeeModelResource(employeeService.findOne(id));
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation("API to get all employee")
	@ResponseStatus(value = HttpStatus.OK)
	public Collection<Resource<EmployeeModel>> getAllEmployees() throws UserException {
		logger.debug("getAllEmployees(POST) is invoked");
		List<EmployeeModel> employees = employeeService.findAll();
		if (employees != null) {
			List<Resource<EmployeeModel>> resources = new ArrayList<Resource<EmployeeModel>>();
			for (EmployeeModel employeeModel : employees)
				resources.add(getEmployeeModelResource(employeeModel));
			return resources;
		}
		return null;
	}

	@ApiOperation("API to create employee")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	//@PreAuthorize("hasRole('ADMIN')")
	public EmployeeModel saveEmployee(@RequestBody EmployeeModel employeeModel) throws UserException {
		logger.debug("saveEmployee(POST) is invoked ... " + employeeModel);
		return employeeService.create(employeeModel);
	}

	@ApiOperation("API to update employee")
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	//@PreAuthorize("hasRole('ADMIN')")
	public Resource<EmployeeModel> updateEmployee(@RequestBody EmployeeModel employeeModel,
			@PathVariable(name = "id") Long id) throws UserException {
		logger.debug("saveEmployee(POST) is invoked ... " + employeeModel);
		employeeModel.setId(id);
		return getEmployeeModelResource(employeeService.update(employeeModel));
	}

	@ApiOperation("API to delete employee")
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteEmployee(
			@ApiParam(value = "ID of the Employee", required = true) @PathVariable(name = "id") Long id) {
		logger.debug("deleteEmployee(POST) is invoked ... " + id);
		employeeService.delete(id);
	}

	private Resource<EmployeeModel> getEmployeeModelResource(EmployeeModel employeeModel) throws UserException {
		logger.debug("getEmployeeModelResource(POST) is invoked ... " + employeeModel);
		Resource<EmployeeModel> resource = new Resource<EmployeeModel>(employeeModel);
		// Link to EmployeeModel
		resource.add(linkTo(methodOn(EmployeeController.class).getEmployee(employeeModel.getId())).withSelfRel());
		return resource;
	}

}