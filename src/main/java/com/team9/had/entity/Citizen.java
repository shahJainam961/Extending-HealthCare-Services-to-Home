package com.team9.had.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer uhId;

    @Column(nullable = false)
    private String fname;

    @Column(nullable = false)
    private String lname;

    @Column(nullable = false)
    private char gender;

    @Column(nullable = false)
    private Date dob;

    @Column(nullable = false)
    private String street1;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String pincode;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String mobileNo;

    @Column(unique = true, nullable = false)
    private String govId;

    @ManyToOne
    @JoinColumn(name = "field_health_worker_login_id")
    private FieldHealthWorker fieldHealthWorker;
}
