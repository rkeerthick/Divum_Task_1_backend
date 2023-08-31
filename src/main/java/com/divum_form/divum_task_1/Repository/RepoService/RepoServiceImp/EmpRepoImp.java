package com.divum_form.divum_task_1.Repository.RepoService.RepoServiceImp;

import com.divum_form.divum_task_1.Model.Employee;
import com.divum_form.divum_task_1.Repository.EmployeeRepository;
import com.divum_form.divum_task_1.Repository.RepoService.EmpRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpRepoImp implements EmpRepoService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
}
