package com.team9.had.service.doctor;

import com.team9.had.entity.HealthRecord;
import com.team9.had.service.login.LoginModel;

import java.io.Serializable;

public interface ServiceForDoctor {
    Serializable getNewHealthRecords(String loginId);

    Serializable getOldHealthRecords(String loginId);

    boolean submitHealthRecord(HealthRecord healthRecord);
}
