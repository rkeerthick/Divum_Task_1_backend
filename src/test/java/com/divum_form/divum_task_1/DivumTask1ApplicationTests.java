package com.divum_form.divum_task_1;

import com.divum_form.divum_task_1.Controller.EmployeeController;
import com.divum_form.divum_task_1.Model.Employee;
import com.divum_form.divum_task_1.Repository.EmployeeRepository;
import com.divum_form.divum_task_1.Repository.RepoService.EmpRepoService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class DivumTask1ApplicationTests {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmpRepoService empRepoService;

    @Test
    public void getAllTest() {
        List<Employee> employees = new ArrayList<>();
//        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
        ResponseEntity<List<Employee>> response = employeeController.getAllEmployee();
        System.out.println(employees);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assert.assertEquals(response.getBody().size(),employees.size());
    }

    @Test
    public void getByEmailTest() {
        Employee employees = new Employee("kee","kee","ab@mail.com","23456789","kuhad","2345678",new Date());
        when(empRepoService.findByEmail(anyString())).thenReturn(employees);
        ResponseEntity<Employee> response = employeeController.getEmployeeByEmail(anyString());
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getByEmailNotFoundTest() {
        Employee employees = new Employee("kee","kee","a@mail.com","23456789","kuhad","2345678",new Date());
        when(empRepoService.findByEmail(employees.getEmail())).thenReturn(null);
        ResponseEntity<Employee> response = employeeController.getEmployeeByEmail(employees.getEmail());
        Assert.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

    @Test
    public void createEmployeeTest() {

        Employee employees = new Employee("kee","kee","abc@mail.com","23456789","kuhad","2345678",new Date());
        when(empRepoService.findByEmail(employees.getEmail())).thenReturn(null);
        ResponseEntity<Employee> response = employeeController.createEmployee(employees);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void createEmployeeInvalidTest() {
        Employee employees = new Employee("kee","kee","a@mail.com","23456789","kuhad","2345678",new Date());
        when(empRepoService.findByEmail(employees.getEmail())).thenReturn(employees);
        ResponseEntity<Employee> response = employeeController.createEmployee(employees);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void updateEmployeeTest() {

        Employee employees = new Employee("kee","kee","kee@gmail.com","23456789","kuhad","2345678",new Date());
        when(empRepoService.findByEmail(anyString())).thenReturn(employees);
        ResponseEntity<Employee> response = employeeController.updateEmployeeByEmail(employees.getEmail(), employees);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());

    }

    @Test
    public void deleteEmployeeTest() {
        Employee employees = new Employee("kee","kee","abc@mail.com","23456789","kuhad","2345678",new Date());
        when(empRepoService.findByEmail(anyString())).thenReturn(employees);
        String response = employeeController.deleteEmployeeById(anyString());
        Assert.assertEquals("deleted", response);
    }

    @Test
    public void deleteEmployeeNotExistTest() {
        when(empRepoService.findByEmail(anyString())).thenReturn(null);
        String response = employeeController.deleteEmployeeById(anyString());
        Assert.assertEquals("not exist", response);
    }

}
