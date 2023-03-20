package com.team9.had.service.receptionist;

import com.team9.had.Constant;
import com.team9.had.entity.HealthRecord;
import com.team9.had.repository.HealthRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Service
public class ServiceForReceptionistImpl implements ServiceForReceptionist{

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    @Override
    public Serializable createHealthRecord(HealthRecord healthRecord) {
        try{
            healthRecord.setCreationDate(new Date(System.currentTimeMillis()));
            healthRecord.setCreationTime(new Time(System.currentTimeMillis()));
            healthRecord.setStatus(Constant.HEALTH_RECORD_NOT_ASSESSED);
            healthRecordRepository.save(healthRecord);
            // Koi bija hospital no doctor aavi gayo to
            // health record ma receptionist aavse many to one
            return true;
        }
        catch(Exception e){
            System.out.println("e = " + e);
            return null;
        }
    }
}
