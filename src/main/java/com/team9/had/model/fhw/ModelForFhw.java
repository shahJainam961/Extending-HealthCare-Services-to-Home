package com.team9.had.model.fhw;

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
public class ModelForFhw {
    private List<SyncModelForFhw> followUps = new ArrayList<>();
}
