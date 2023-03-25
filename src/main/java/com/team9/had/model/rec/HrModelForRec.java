package com.team9.had.model.rec;

import com.team9.had.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HrModelForRec {
    private String street1;
    private String district;
    private String city;
    private String state;
    private String mobileNo;
    private String pincode;
    private Doctor doctor;
    private CizModelForRec citizen;

}
