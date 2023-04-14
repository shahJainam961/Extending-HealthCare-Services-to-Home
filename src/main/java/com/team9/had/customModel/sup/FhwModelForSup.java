package com.team9.had.customModel.sup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FhwModelForSup {

    private String loginId;
    private CizModelForSup citizen;
    private Integer citizenAssigned;

}
