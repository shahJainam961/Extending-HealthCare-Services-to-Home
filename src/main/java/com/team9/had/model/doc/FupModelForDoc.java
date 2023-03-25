package com.team9.had.model.doc;

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
public class FupModelForDoc {

    private Integer fuId;
    private Date dateOfFollowUp;
    private Date actualDateOfFollowUp;
    private Time actualTimeOfFollowUp;
    private Integer status;
    private String instruction;
    private String fields;
    private String fieldsValue;
    private String observation;
    private String reasonIfDelayed;

}
