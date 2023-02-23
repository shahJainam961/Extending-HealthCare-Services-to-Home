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
    private Integer id;

    @Column(nullable = false)
    private Date dateOfFollowUp;

    private Date actualDateOfFollowUp;
    private Time actualTimeOfFollowUp;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private String instruction;

    private Double bodyTemperature;

    private Double pulseRate;

    private Double respirationRate;

    private Double bloodPressure;

    @ManyToOne
    @JoinColumn(name = "health_record_id", nullable = false)
    private HealthRecord healthRecord;

    @Column(nullable = false)
    private String secretKey;

}
