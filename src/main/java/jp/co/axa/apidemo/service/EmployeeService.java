package jp.co.axa.apidemo.service;

import jp.co.axa.apidemo.Exceptions.EmployeeNotFoundException;
import jp.co.axa.apidemo.models.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> listEmployees();

    Employee getEmployee(Long employeeId) throws EmployeeNotFoundException;

    Employee createEmployee(Employee employee);

    void deleteEmployee(Long employeeId);

    Employee updateEmployee(Long employeeId, Employee employee) throws EmployeeNotFoundException;
}