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
public class ReassignedForSup {
    private ArrayList<CizModelForSup> citizens = new ArrayList<>();
    private FhwModelForSup fieldHealthWorker;
}
