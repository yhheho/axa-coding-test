package com.monstarlab.movie.controllers;

import com.monstarlab.movie.Exceptions.EmployeeNotFoundException;
import com.monstarlab.movie.models.Employee;
import com.monstarlab.movie.payload.request.employee.CreateEmployeeRequest;
import com.monstarlab.movie.payload.response.employee.CreateEmployeeResponse;
import com.monstarlab.movie.payload.response.employee.GetEmployeeResponse;
import com.monstarlab.movie.payload.response.employee.ListEmployeeResponse;
import com.monstarlab.movie.payload.response.employee.UpdateEmployeeResponse;
import com.monstarlab.movie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.monstarlab.movie.constants.Constant.ILLEGAL_ARGUMENT;


@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<ListEmployeeResponse> getEmployees() {
        List<Employee> employees = employeeService.listEmployees();
        ListEmployeeResponse response = new ListEmployeeResponse(employees);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{employee_id}")
    public ResponseEntity<GetEmployeeResponse> getEmployee(@PathVariable(name = "employee_id") Long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        GetEmployeeResponse response = new GetEmployeeResponse(employee);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<CreateEmployeeResponse> createEmployee(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest,
                                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        Employee newEmployee = employeeService.createEmployee(createEmployeeRequest.getEmployee());
        CreateEmployeeResponse response = new CreateEmployeeResponse(newEmployee);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{employee_id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity deleteEmployee(@PathVariable(name = "employee_id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{employee_id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UpdateEmployeeResponse> updateEmployee(@Valid @RequestBody UpdateEmployeeResponse updateEmployeeResponse,
                                                                 @PathVariable(name = "employee_id") Long employeeId,
                                                                 BindingResult bindingResult) throws EmployeeNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        Employee updatedEmployee = employeeService.updateEmployee(employeeId, updateEmployeeResponse.getEmployee());
        UpdateEmployeeResponse response = new UpdateEmployeeResponse(updatedEmployee);
        return ResponseEntity.ok(response);
    }

}
