package com.team9.had.customModel.fhw;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelForFhw {
    private ArrayList<SyncModelForFhw> followUps = new ArrayList<>();
}
