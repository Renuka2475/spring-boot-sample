package com.emp.Service;

import com.emp.Entity.Employee;
import com.emp.Entity.Request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();
    public Employee getEmployeeById(int id);
    public Employee save(EmployeeRequest employeeRequest);
    public  Employee update (int id,EmployeeRequest employeeRequest);
    public  Employee convertToEmployee(int id,EmployeeRequest employeeRequest);
    void deleteById(int id);
}
