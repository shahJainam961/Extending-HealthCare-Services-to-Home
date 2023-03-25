package com.team9.had.model.rec;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecModelForRec {

    private String loginId;
    private CizModelForRec citizen;
    private HosModelForRec hospital;

}
