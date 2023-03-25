package com.team9.had.service.receptionist;

import com.team9.had.exception.CitizenNotFoundException;
import com.team9.had.exception.DoctorNotFoundException;
import com.team9.had.model.rec.HrModelForRec;

import java.io.Serializable;

public interface ServiceForReceptionist {
    Serializable createHealthRecord(HrModelForRec hrModelForRec, String role) throws CitizenNotFoundException, DoctorNotFoundException;

    Serializable confirmation(Integer uhId) throws CitizenNotFoundException;
}
