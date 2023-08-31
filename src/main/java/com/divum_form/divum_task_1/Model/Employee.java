package com.divum_form.divum_task_1.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Id
    @Column(name = "email_id", nullable = false)
    private String email;

    @Column(name = "ph_no", nullable = false)
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "dob")
    private String dob;


    }

