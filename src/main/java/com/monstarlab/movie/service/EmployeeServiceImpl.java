package com.monstarlab.movie.service;

import com.monstarlab.movie.Exceptions.EmployeeNotFoundException;
import com.monstarlab.movie.models.Employee;
import com.monstarlab.movie.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.monstarlab.movie.constants.Constant.EMPLOYEE_NOT_FOUND;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public List<Employee> listEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        return optEmp.get();
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public Employee updateEmployee(Long employeeId, Employee employee) throws EmployeeNotFoundException {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setDepartment(employee.getDepartment());
        return employeeRepository.save(existingEmployee);
    }
}