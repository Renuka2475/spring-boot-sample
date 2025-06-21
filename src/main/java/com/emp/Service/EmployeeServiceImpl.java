package com.emp.Service;


import com.emp.DAO.EmployeeRepository;
import com.emp.Entity.Employee;
import com.emp.Entity.Request.EmployeeRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
//    private EmployeeDAO employeeDAO;
    @Autowired
    public EmployeeServiceImpl (EmployeeRepository theEmployeeRepository){
        this.employeeRepository = theEmployeeRepository;
    }
    @Override
    public List<Employee> findAll(){

        return employeeRepository.findAll();
    }


    @Override
    public Employee getEmployeeById (int id){
        Employee theEmployee = null;
        Optional<Employee> result = employeeRepository.findById(id);
        if(result.isPresent()){
            theEmployee = result.get();
        }
        else{
            throw new RuntimeException("The given id is not present"+id);
        }
        return theEmployee;
    }

    @Override
    public Employee save(EmployeeRequest employeeRequest) {
        Employee employeeSave= convertToEmployee(0,employeeRequest);
        return employeeRepository.save(employeeSave);
    }

    @Override
    public Employee update(int id, EmployeeRequest employeeRequest) {
        Employee employeeUpdate = convertToEmployee(id,employeeRequest);
        return employeeRepository.save(employeeUpdate);
    }

    @Override
    public Employee convertToEmployee(int id, EmployeeRequest employeeRequest) {
        Employee convertedEmployee = new Employee(id,
                employeeRequest.getFirst_name(),
                employeeRequest.getLast_name(),
                employeeRequest.getEmail());
        return convertedEmployee;
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);

    }


}
