package com.monstarlab.movie.service;

import com.monstarlab.movie.Exceptions.EmployeeNotFoundException;
import com.monstarlab.movie.models.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> listEmployees();

    Employee getEmployee(Long employeeId);

    Employee createEmployee(Employee employee);

    void deleteEmployee(Long employeeId);

    Employee updateEmployee(Long employeeId, Employee employee) throws EmployeeNotFoundException;
}