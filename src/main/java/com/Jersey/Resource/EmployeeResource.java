package com.Jersey.Resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Jersey.Entity.Employee;
import com.Jersey.Repository.EmployeeDao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Component
@XmlAccessorType(XmlAccessType.FIELD)
//search and access  at that particular location
@Path("employeeInfo/v2")
@Api(value = "employeeInfo")
//response that Consumed will be converted into JSON format.
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
// response that produced will be converted into JSON format.
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class EmployeeResource {

	@Autowired
	private EmployeeDao dao;

	@POST

	@ApiOperation(produces = "application/json, application/xml", value = "Save employee details", httpMethod = "POST", notes = "<br>This service Post Employee details", response = Employee.class)
	@Path("/save")
	public Employee saveEmployee(Employee employee) {
		return dao.save(employee);
	}

	@PUT

	@ApiOperation(produces = "application/json, application/xml", value = "Update employee details", httpMethod = "PUT", notes = "<br>This service Update Employee details", response = Employee.class)
    @Path("/update")
	public Employee updateEmployee(Employee employee) {
		return dao.save(employee);
	}

	@GET
	@Path("/getEmployee/{name}")
	@ApiOperation(produces = "application/json, application/xml", value = "Fetch employee", httpMethod = "GET", notes = "<br>This service fetch Employee details", response = Employee.class)

	public Employee getEmployeeById(@PathParam("id") Long id) {
		return dao.findById(id);
	}

	@GET
	@ApiOperation(produces = "application/json, application/xml", value = "Fetch all employee details", httpMethod = "GET", notes = "<br>This service fetch Employee details", response = Employee.class)
	@Path("/getAllEmployeee")
	public List<Employee> getAllEmployees() {
		return dao.findAll();
	}
	
	@DELETE
	@ApiOperation(produces = "application/json, application/xml", value = "Delete employee details", httpMethod = "DELETE", notes = "<br>This service delete Employee details", response = Employee.class)
	@Path("/{id}")
	public  Boolean deleteEmployee(@PathParam("id") Long id)
	{
		 return dao.deleteById(id);
	    
	}

}
