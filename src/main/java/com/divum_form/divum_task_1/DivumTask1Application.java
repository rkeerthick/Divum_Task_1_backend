package com.divum_form.divum_task_1;

import com.divum_form.divum_task_1.Model.Employee;
import com.divum_form.divum_task_1.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DivumTask1Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DivumTask1Application.class, args);
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
//        Employee employee = new Employee();
//        employee.setEmail("a@mail.com");
//        employee.setFirstName("aaa");
//        employee.setLastName("zzz");
//        employee.setPhoneNumber("9864725733");
//        employee.setDob("1/2/3");
//        employee.setAddress("chennai");
//
//        employeeRepository.save(employee);
    }
}
