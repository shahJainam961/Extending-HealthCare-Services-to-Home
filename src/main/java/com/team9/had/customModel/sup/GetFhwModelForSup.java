package com.team9.had.customModel.sup;

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
    FhwModelForSup fieldHealthWorker;
    ArrayList<CizModelForSup> citizens = new ArrayList<>();
    ArrayList<FhwModelForSup> fieldHealthWorkers;
}
