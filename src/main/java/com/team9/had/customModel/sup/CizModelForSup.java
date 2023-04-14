package com.team9.had.customModel.sup;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CizModelForSup {

    private Integer uhId;
    private String fname;
    private String lname;
    private char gender;
    private Date dob;

}
