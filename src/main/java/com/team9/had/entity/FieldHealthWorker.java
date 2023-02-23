package com.team9.had.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldHealthWorker{

    @Id
    private String loginId;

    @OneToOne
    @JoinColumn(name = "citizen_id", unique = true, nullable = false)
    private Citizen citizen;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String assignedPincode;

    private String pin;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false)
    private Integer noOfCitizensUnderTaken;

//    private Integer numberOfTaskCurrentlyAssigned;
//    private Integer numberOfTaskAssigned;
//    private Integer numberOfTaskCompleted;
//    private Integer numberOfTaskBacklogged;

}
