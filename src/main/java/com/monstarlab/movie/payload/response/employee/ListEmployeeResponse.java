package com.monstarlab.movie.payload.response.employee;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monstarlab.movie.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ListEmployeeResponse {
    private List<Employee> employeeList;

    @JsonCreator
    public ListEmployeeResponse() {}
}
