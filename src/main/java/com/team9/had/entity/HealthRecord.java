package com.team9.had.entity;

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

    @Column(nullable = false)
    private String street1;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String pincode;

    private String fields;
    private String fieldsValues;
    private String conclusion;
    private String prescription;
    @Column(nullable = false)
    private String mobileNo;
    @ManyToOne
    @JoinColumn(name = "doctor_login_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "citizen_id", nullable = false)
    private Citizen citizen;

    @ManyToOne
    @JoinColumn(name = "field_health_worker_login_id")
    private FieldHealthWorker fieldHealthWorker;

    @ManyToOne
    @JoinColumn(name = "supervisor_login_id")
    private Supervisor supervisor;

    @Column(nullable = false)
    private Integer status;
}
