package com.team9.had.model.doc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HrModelForDocRes {
    private Integer hrId;
    private Date creationDate;
    private Time creationTime;
    private String conclusion;
    private String treatment;
    private String prescription;
    private CizModelForDoc citizen;
    private Integer status;
    private List<FupModelForDocRes> followUps = new ArrayList<>();

}
