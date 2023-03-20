package com.team9.had.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HealthRecordModel {
    private Integer hrId;
    private Date creationDate;
    private Time creationTime;
    private String fields;
    private String fieldsValues;
    private String conclusion;
    private String prescription;
    private CitizenModel citizen;
    private String mobileNo;
    private ArrayList<FollowUpModel> followUps = new ArrayList<>();
}
