package com.team9.had.model.sup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubmitAssignedForSup {

    private CizModelForSup citizen;
    private FhwModelForSup fieldHealthWorker;
}
