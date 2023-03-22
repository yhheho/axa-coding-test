package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.Exceptions.EmployeeNotFoundException;
import jp.co.axa.apidemo.models.Employee;
import jp.co.axa.apidemo.payload.request.employee.CreateEmployeeRequest;
import jp.co.axa.apidemo.payload.request.employee.UpdateEmployeeRequest;
import jp.co.axa.apidemo.payload.response.employee.CreateEmployeeResponse;
import jp.co.axa.apidemo.payload.response.employee.GetEmployeeResponse;
import jp.co.axa.apidemo.payload.response.employee.ListEmployeeResponse;
import jp.co.axa.apidemo.payload.response.employee.UpdateEmployeeResponse;
import jp.co.axa.apidemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static jp.co.axa.apidemo.constants.Constant.ILLEGAL_ARGUMENT;


@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ListEmployeeResponse> getEmployees() {
        List<Employee> employees = employeeService.listEmployees();
        ListEmployeeResponse response = new ListEmployeeResponse(employees);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employee/{employee_id}")
    public ResponseEntity<GetEmployeeResponse> getEmployee(@PathVariable(name = "employee_id") Long employeeId) throws EmployeeNotFoundException {
        Employee employee = employeeService.getEmployee(employeeId);
        GetEmployeeResponse response = new GetEmployeeResponse(employee);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/employee")
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

    @DeleteMapping("/employee/{employee_id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity deleteEmployee(@PathVariable(name = "employee_id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/employee/{employee_id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UpdateEmployeeResponse> updateEmployee(@Valid @RequestBody UpdateEmployeeRequest updateEmployeeRequest,
                                                                 @PathVariable(name = "employee_id") Long employeeId,
                                                                 BindingResult bindingResult) throws EmployeeNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        Employee updatedEmployee = employeeService.updateEmployee(employeeId, updateEmployeeRequest.getEmployee());
        UpdateEmployeeResponse response = new UpdateEmployeeResponse(updatedEmployee);
        return ResponseEntity.ok(response);
    }

}
