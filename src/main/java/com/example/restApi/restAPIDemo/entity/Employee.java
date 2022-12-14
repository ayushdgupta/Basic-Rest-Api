package com.example.restApi.restAPIDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employeeInfo")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;     // here if you will send ID in your input then our code will pick up that ID else
                        // it'll be automatically generated. but it does n't mean that if you will send 250 as ID
                        // then it'll pick 250, ID's will be picked p in increment manner like 1,2,3,4,5,6,...

    @Column(name = "fst_name")
    private String firstName;

    @Column(name = "lst_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "aadhar", length = 12)
    private String aadharNo;
}
