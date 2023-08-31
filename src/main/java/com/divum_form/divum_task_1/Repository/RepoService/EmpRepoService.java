package com.divum_form.divum_task_1.Repository.RepoService;

import com.divum_form.divum_task_1.Model.Employee;

public interface EmpRepoService {
    Employee findByEmail(String email);
}
