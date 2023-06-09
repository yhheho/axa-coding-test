package jp.co.axa.apidemo.payload.response.employee;

import com.fasterxml.jackson.annotation.JsonCreator;
import jp.co.axa.apidemo.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateEmployeeResponse {
    private Employee employee;

    @JsonCreator
    public UpdateEmployeeResponse() {

    }
}
