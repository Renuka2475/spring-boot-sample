package com.emp.controller;


import com.emp.entity.Employee;
import com.emp.entity.Request.EmployeeRequest;
import com.emp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Employee")
@Tag(name="Employee Rest API Endpoints",description = "Operations related to Employees")
public class EmployeeRestController {


//	private EmployeeDAO employeeDAO;
	private EmployeeService employeeService;
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		this.employeeService = theEmployeeService;
	}

	@Operation(summary="Retrieve Employees",description = "Get all employee details")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<Employee> findall(){
		
		return employeeService.findAll();
	}

	@Operation(summary="Retrieve Employee",description = "Get Single employee by Id")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("{id}")
	public Employee getEmployeeById (@PathVariable @Min(value=1) int id){
		return employeeService.getEmployeeById(id);

	}

	@Operation(summary = "Create a new employee", description = "Insert a new Employee record")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Employee insertRecord(@Valid @RequestBody EmployeeRequest employee){
		Employee newEmpRecord = employeeService.save(employee);
		return newEmpRecord;

	}

	@Operation(summary = "Update an Employee",description = "Update an existing employee")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("{id}")

	public Employee updateRecord(int id, @Valid @RequestBody EmployeeRequest employee){
		Employee empUpdated = employeeService.update(id,employee);
		return empUpdated;
	}

	@Operation(summary = "Delete an Employee", description = "Delete an existing employee.")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public String deleteEmployee(int id){
		employeeService.deleteById(id);
		return "Employee Deleted Succesfully.";
	}


}
