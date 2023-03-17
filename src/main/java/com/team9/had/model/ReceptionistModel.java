package com.team9.had.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceptionistModel {
    private String loginId;
    private CitizenModel citizen;
    private HospitalModel hospital;
}
