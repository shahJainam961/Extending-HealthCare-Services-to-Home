package com.team9.had.model.sup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetFhwModelForSup {
    FhwModelForSup fhwModelForSup;
    ArrayList<CizModelForSup> cizModelForSups = new ArrayList<>();
    Integer citizensAssigned;
    ArrayList<FhwModelForSup> otherFhwModelForSups;
}
