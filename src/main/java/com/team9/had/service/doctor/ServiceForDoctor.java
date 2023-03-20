package com.team9.had.service.doctor;

import com.team9.had.model.HealthRecordModel;

import java.io.Serializable;

public interface ServiceForDoctor {
    Serializable getNewHealthRecords(String loginId);

    Serializable getOldHealthRecords(String loginId);

    boolean submitHealthRecord(HealthRecordModel healthRecordModel);
}
