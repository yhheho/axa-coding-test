package jp.co.axa.apidemo.payload.request.employee;

import jp.co.axa.apidemo.models.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UpdateEmployeeRequest {
    @NotNull
    private Employee employee;
}
