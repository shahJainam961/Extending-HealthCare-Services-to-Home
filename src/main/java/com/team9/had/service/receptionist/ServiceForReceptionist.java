package com.team9.had.service.receptionist;

import com.team9.had.exception.UserNotFoundException;
import com.team9.had.model.rec.HrModelForRec;

import java.io.Serializable;

public interface ServiceForReceptionist {
    Serializable createHealthRecord(HrModelForRec hrModelForRec, String role) throws UserNotFoundException;

    Serializable confirmation(Integer uhId) throws UserNotFoundException;
}
