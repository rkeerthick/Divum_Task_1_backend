package com.divum_form.divum_task_1.Controller;

import com.divum_form.divum_task_1.Exception.ResourceNotFoundException;
import com.divum_form.divum_task_1.Model.Employee;
import com.divum_form.divum_task_1.Repository.EmployeeRepository;
import com.divum_form.divum_task_1.Repository.RepoService.EmpRepoService;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmpRepoService empRepoService;

    @Autowired
    private JavaMailSender javaMailSender;


    @GetMapping("/get10/{offset}/{pageSize}")
    Page<Employee> getFirstTenEmployees(@PathVariable("offset") int offset,@PathVariable("pageSize") int pageSize) {
        return employeeRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.DESC, "updatedDate")));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll(Sort.by("updatedDate").descending());
        System.out.println(employees);
        return ResponseEntity.ok(employees);
    }

    //Build create employee REST API
    @PostMapping("/post")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee employee1 = empRepoService.findByEmail(employee.getEmail());
        if (employee1 == null) {
            employee.setUpdatedDate(new Date());
            return ResponseEntity.ok(employeeRepository.save(employee));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    //
//    //Get employee by id
    @GetMapping("/email={email}")
    public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable String email) {
        Employee employee = empRepoService.findByEmail(email);
        if(employee != null) {
            return ResponseEntity.ok(employee);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    //    //Build update api
    @PutMapping("/update/email={email}")
    public ResponseEntity<Employee> updateEmployeeByEmail(@PathVariable String email, @RequestBody Employee updatedData) {
        Employee updatedEmployee = empRepoService.findByEmail(email);

        updatedEmployee.setFirstName(updatedData.getFirstName());
        updatedEmployee.setLastName(updatedData.getLastName());
        updatedEmployee.setEmail(updatedData.getEmail());
        updatedEmployee.setAddress(updatedData.getAddress());
        updatedEmployee.setPhoneNumber(updatedData.getPhoneNumber());
        updatedEmployee.setDob(updatedData.getDob());
        updatedEmployee.setUpdatedDate(new Date());

        employeeRepository.save(updatedEmployee);

        return ResponseEntity.ok(updatedEmployee);
    }
    //
//    //Build delete api
    @DeleteMapping("/delete/email={email}")
    public String deleteEmployeeById(@PathVariable String email) {
//        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id = " + id));
        Employee employee = empRepoService.findByEmail(email);
        if(employee == null) {
            return "not exist";
        }
        employeeRepository.delete(employee);
        return "deleted";
    }

    @GetMapping("/sendDeleteMail/{email}")
    public String sendUpdateMail(@PathVariable String email)
    {
        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();
            System.out.println();
            // Setting up necessary details
            mailMessage.setFrom("20f223@kce.ac.in");
            mailMessage.setTo(email);
            mailMessage.setSubject("User Deleted!!!");
            mailMessage.setText("Your account has been successfully removed...");

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "User deleted Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return e+"";
        }
    }

    @GetMapping("/sendAddMail/{email}")
    public String sendAddMail(@PathVariable String email, @RequestBody Employee employee)
    {
        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();
            System.out.println();
            // Setting up necessary details
            mailMessage.setFrom("20f223@kce.ac.in");
            mailMessage.setTo(email);
            mailMessage.setSubject("Your account has been created successfully!!!");
            mailMessage.setText("" +
                    "Your account has been successfully created...\n" +
                    "The details are,\nFirst Name : "+employee.getFirstName()+
                    "\nLast Name : " + employee.getLastName() +
                    "\nEmail : " + employee.getEmail() +
                    "\nPhone Number : " + employee.getPhoneNumber() +
                    "\nDOB : " + employee.getDob() +
                    "\nAddress : " + employee.getAddress()
            );

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "User added Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return e+"";
        }
    }

}
