package com.team9.had.service.doctor;

import com.team9.had.model.doc.HrModelForDoc;

import java.io.Serializable;

public interface ServiceForDoctor {
    Serializable getNewHealthRecords(String loginId);

    Serializable getOldHealthRecords(String loginId);

    boolean submitHealthRecord(HrModelForDoc hrModelForDoc);

    Serializable getConsentData(Integer uhId);
}
