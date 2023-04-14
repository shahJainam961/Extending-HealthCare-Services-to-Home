package com.team9.had.service.doctor;

import com.team9.had.exception.InternalServerError;
import com.team9.had.model.doc.HrModelForDoc;
import com.team9.had.model.doc.StartDateEndDateModel;

import java.io.Serializable;

public interface ServiceForDoctor {
    Serializable getNewHealthRecords(String loginId) throws InternalServerError;

    Serializable getOldHealthRecords(String loginId, StartDateEndDateModel startDateEndDateModel) throws Exception;
    boolean submitHealthRecord(HrModelForDoc hrModelForDoc);

    Serializable getConsentData(Integer uhId);
}
