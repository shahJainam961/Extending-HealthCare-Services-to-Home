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
public class FollowUp {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer fuId;
    @Column(nullable = false)
    private Date dateOfFollowUp;
    private Date actualDateOfFollowUp;
    private Time actualTimeOfFollowUp;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private String instruction;

    private String bloodSugar;
    private String bloodOxygen;
    private String skinColor;
    private String eyeColor;
    private String temperature;
    private String inflammation;

    @ManyToOne
    @JoinColumn(name = "health_record_hr_id", nullable = false)
    private HealthRecord healthRecord;

    @Column(nullable = false, length = 6)
    private String secretKey;

    @Column(length = 999999999)
    private String observation;

    @Column(length = 999999999)
    private String reasonIfDelayed;
}
