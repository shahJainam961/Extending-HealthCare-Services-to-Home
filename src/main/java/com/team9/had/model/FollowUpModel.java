package com.team9.had.model;

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
public class FollowUpModel {
    private Integer fuId;
    private Date dateOfFollowUp;
    private Date actualDateOfFollowUp;
    private Time actualTimeOfFollowUp;
    private Integer status;
    private String instruction;
    private String fields;
    private String fieldsValue;
    private String secretKey;
    private String street1;
    private String city;
    private String state;
    private String pincode;
}
