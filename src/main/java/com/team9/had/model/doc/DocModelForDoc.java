package com.team9.had.model.doc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocModelForDoc {

    private String loginId;
    private CizModelForDoc citizen;
    private HosModelForDoc hospital;

}
