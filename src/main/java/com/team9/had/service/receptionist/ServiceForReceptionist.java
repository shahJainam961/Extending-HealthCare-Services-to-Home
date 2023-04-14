package com.team9.had.service.receptionist;

import com.team9.had.model.rec.HrModelForRec;

import java.io.Serializable;

public interface ServiceForReceptionist {
    boolean createHealthRecord(HrModelForRec hrModelForRec, String role) throws Exception;

    Serializable confirmation(Integer uhId) throws Exception;
}
