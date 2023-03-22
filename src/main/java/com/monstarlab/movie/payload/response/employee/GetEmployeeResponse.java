package com.monstarlab.movie.payload.response.employee;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monstarlab.movie.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetEmployeeResponse {
    private Employee employee;

    @JsonCreator
    public GetEmployeeResponse() {

    }
}