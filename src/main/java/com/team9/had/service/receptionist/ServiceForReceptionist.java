package com.team9.had.service.receptionist;

import com.team9.had.entity.HealthRecord;

import java.io.Serializable;

public interface ServiceForReceptionist {
    Serializable createHealthRecord(HealthRecord healthRecord);
}
