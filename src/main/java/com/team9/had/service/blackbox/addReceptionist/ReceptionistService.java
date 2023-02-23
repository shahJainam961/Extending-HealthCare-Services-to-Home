package com.team9.had.service.blackbox.addReceptionist;

import com.team9.had.entity.Receptionist;

import java.util.List;

public interface ReceptionistService {
    boolean addReceptionist(Receptionist receptionist);

    boolean addReceptionists(List<Receptionist> receptionists);
}
