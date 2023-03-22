package jp.co.axa.apidemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.models.Employee;
import jp.co.axa.apidemo.payload.request.employee.UpdateEmployeeRequest;
import jp.co.axa.apidemo.payload.response.employee.GetEmployeeResponse;
import jp.co.axa.apidemo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@SpringBootTest
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private BindingResult mockBindingResult;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupTest() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        MockitoAnnotations.initMocks(this);
        when(mockBindingResult.hasErrors()).thenReturn(false);
    }

    @Test
    public void testGetEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("abc");
        employee.setSalary(500);
        employee.setDepartment("backend");
        GetEmployeeResponse response = new GetEmployeeResponse(employee);
        when(employeeService.getEmployee(1L)).thenReturn(employee);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/employee/1"))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        GetEmployeeResponse actual = new ObjectMapper().readValue(responseJson, GetEmployeeResponse.class);
        assertEquals("Check employee name", response.getEmployee().getName(), actual.getEmployee().getName());
        assertEquals("Check employee salary", response.getEmployee().getSalary(), actual.getEmployee().getSalary());
        assertEquals("Check employee department", response.getEmployee().getDepartment(), actual.getEmployee().getDepartment());
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("abc");
        employee.setSalary(500);
        employee.setDepartment("backend");
        GetEmployeeResponse response = new GetEmployeeResponse(employee);
        when(employeeService.updateEmployee(eq(1L), any())).thenReturn(employee);

        UpdateEmployeeRequest updateEmployeeRequest = new UpdateEmployeeRequest();
        updateEmployeeRequest.setEmployee(employee);

        MvcResult mvcResult = mockMvc.perform(put("/api/v1/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updateEmployeeRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        GetEmployeeResponse actual = new ObjectMapper().readValue(responseJson, GetEmployeeResponse.class);
        assertEquals("Check employee name", response.getEmployee().getName(), actual.getEmployee().getName());
        assertEquals("Check employee salary", response.getEmployee().getSalary(), actual.getEmployee().getSalary());
        assertEquals("Check employee department", response.getEmployee().getDepartment(), actual.getEmployee().getDepartment());
    }
}
