package com.team9.had.model.sup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnassignedCitizenModelForSup {
    CizModelForSup citizen;
    List<FhwModelForSup> fieldHealthWorkers = new ArrayList<>();
}
