package com.monstarlab.movie.payload.request.employee;

import com.monstarlab.movie.models.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CreateEmployeeRequest {

    @NotNull
    private Employee employee;
}
