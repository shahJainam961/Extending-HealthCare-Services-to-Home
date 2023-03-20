package com.team9.had.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CitizenModel {
    private Integer uhId;
    private String fname;
    private String lname;
    private char gender;
    private Date dob;
    private String street1;
    private String city;
    private String state;
    private String pincode;
}
