package com.team9.had.customModel.fhw;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CizModelForFhw {
    private Integer uhId;
    private String fname;
    private String lname;
    private char gender;
    private Date dob;
    private String mobileNo;
    private String street1;
    private String district;
    private String city;
    private String pincode;
    private String state;
}
