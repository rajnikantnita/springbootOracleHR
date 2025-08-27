package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) throws ResourceNotFoundException {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = getEmployeeById(id);
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhoneNumber(employeeDetails.getPhoneNumber());
        employee.setHireDate(employeeDetails.getHireDate());
        employee.setJobId(employeeDetails.getJobId());
        employee.setSalary(employeeDetails.getSalary());
        employee.setCommissionPct(employeeDetails.getCommissionPct());
        employee.setManagerId(employeeDetails.getManagerId());
        employee.setDepartmentId(employeeDetails.getDepartmentId());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) throws ResourceNotFoundException {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}
