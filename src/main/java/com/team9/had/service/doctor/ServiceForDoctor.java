package com.team9.had.service.doctor;

import com.team9.had.customModel.doc.HrModelForDoc;
import com.team9.had.customModel.doc.StartDateEndDateModel;

import java.io.Serializable;

public interface ServiceForDoctor {
    Serializable getNewHealthRecords(String loginId) throws Exception;

    Serializable getOldHealthRecords(String loginId, StartDateEndDateModel startDateEndDateModel) throws Exception;
    boolean submitHealthRecord(HrModelForDoc hrModelForDoc) throws Exception;

    Serializable getConsentData(Integer uhId) throws Exception;
}
