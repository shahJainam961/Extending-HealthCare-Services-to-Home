package com.team9.had.service.blackbox.addCitizen;

import com.team9.had.model.Citizen;

import java.util.List;


public interface CitizenService {
    boolean addCitizen(Citizen citizen);

    boolean addCitizens(List<Citizen> citizen);
}
