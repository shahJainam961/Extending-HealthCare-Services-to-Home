package com.team9.had.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer hrId;

    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private Time creationTime;

    @Column(nullable = false, length = 1000)
    private String street1;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false, length = 6)
    private String pincode;

    @Column(length = 999999999)
    private String fields;
    @Column(length = 999999999)
    private String fieldsValues;
    @Column(length = 999999999)
    private String conclusion;
    @Column(length = 999999999)
    private String treatment;
    @Column(length = 999999999)
    private String prescription;
    @Column(nullable = false)
    private String mobileNo;
    @ManyToOne
    @JoinColumn(name = "doctor_login_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "citizen_uh_id", nullable = false)
    private Citizen citizen;

    @ManyToOne
    @JoinColumn(name = "field_health_worker_login_id")
    private FieldHealthWorker fieldHealthWorker;

    @ManyToOne
    @JoinColumn(name = "supervisor_login_id")
    private Supervisor supervisor;

    @ManyToOne
    @JoinColumn(name = "receptionist_login_id", nullable = false)
    private Receptionist receptionist;

    @Column(nullable = false)
    private Integer status;
}
