package com.team9.had.model.fhw;

import com.team9.had.entity.HealthRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SyncModelForFhw {
    private Integer fuId;
    private Date dateOfFollowUp;
    private Date actualDateOfFollowUp;
    private Time actualTimeOfFollowUp;
    private Integer status;
    private String instruction;
    private String fields;
    private String fieldsValue;
    private String secretKey;
    private String observation;
    private String reasonIfDelayed;
    private String street1;
    private String state;
    private String district;
    private String city;
    private String pincode;
    private String mobileNo;
    private Integer uhId;
    private String fname;
    private String lname;
    private char gender;
    private Date dob;
    private String prescription;
    private HrModelForFhw healthRecord;
}
