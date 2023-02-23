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
    private Integer id;

    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private Time creationTime;

    @Column(nullable = false)
    private String currentStreet1;

    private String currentStreet2;

    @Column(nullable = false)
    private String currentCity;

    @Column(nullable = false)
    private String currentState;

    @Column(nullable = false)
    private String currentPincode;

    private String symptoms;

    private Double bodyTemperature;

    private Double pulseRate;

    private Double respirationRate;

    private Double bloodPressure;
    // Body temperature, Pulse rate, Respiration rate (rate of breathing)
    // Blood pressure (Blood pressure is not considered a vital sign, but is often measured along with the vital signs)

    private String prescription;

    private String conclusion;

    @ManyToOne
    @JoinColumn(name = "doctor_login_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "citizen_id", nullable = false)
    private Citizen citizen;

    @ManyToOne
    @JoinColumn(name = "field_health_worker_login_id")
    private FieldHealthWorker fieldHealthWorker;

    @Column(nullable = false)
    private Integer status;
}
