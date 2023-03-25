package com.team9.had.model.doc;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HosModelForDoc {
    private Integer hospId;
    private String name;
    private String street1;
    private String city;
    private String district;
    private String state;
    private String pincode;
    private String contactNo;
}
