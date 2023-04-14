package com.team9.had.customModel.doc;

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
public class FupModelForDocRes {

    private Integer fuId;
    private Date dateOfFollowUp;
    private Date actualDateOfFollowUp;
    private Time actualTimeOfFollowUp;
    private Integer status;
    private String instruction;

    private String bloodSugar;
    private String bloodOxygen;
    private String skinColor;
    private String eyeColor;
    private String temperature;
    private String inflammation;

    private String observation;
    private String reasonIfDelayed;
}
