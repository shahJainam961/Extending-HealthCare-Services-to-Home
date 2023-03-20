package com.team9.had.service.doctor;

import com.team9.had.Constant;
import com.team9.had.entity.FieldHealthWorker;
import com.team9.had.entity.FollowUp;
import com.team9.had.entity.HealthRecord;
import com.team9.had.entity.Supervisor;
import com.team9.had.model.FollowUpModel;
import com.team9.had.model.HealthRecordModel;
import com.team9.had.repository.FollowUpRepository;
import com.team9.had.repository.HealthRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

@Service
public class ServiceForDoctorImpl implements ServiceForDoctor{

    @Autowired
    private HealthRecordRepository healthRecordRepository;
    @Autowired
    private FollowUpRepository followUpRepository;

    @Override
    public Serializable getNewHealthRecords(String loginId) {
        ArrayList<Object> obj = new ArrayList<>();

        ArrayList<HealthRecord> healthRecordList =
                healthRecordRepository
                .findAllByDoctor_LoginIdAndStatusAndCreationDateOrderByCreationTime(
                        loginId, Constant.HEALTH_RECORD_NOT_ASSESSED,
                        new Date(System.currentTimeMillis())
                );
        if(healthRecordList==null) return null;
        ArrayList<HealthRecordModel> newHealthRecordForDoctors = new ArrayList<>();
        healthRecordList.stream().forEach((healthRecord) -> {
            newHealthRecordForDoctors.add(Constant.getModelMapper().map(healthRecord, HealthRecordModel.class));
        });
        obj.add(newHealthRecordForDoctors);
        return obj;
    }

    @Override
    public Serializable getOldHealthRecords(String loginId) {
        ArrayList<Object> obj = new ArrayList<>();

        ArrayList<HealthRecord> healthRecordList =
                healthRecordRepository
                        .findAllByDoctor_LoginIdAndStatusOrderByCreationDateDescCreationTimeDesc(
                                loginId, Constant.HEALTH_RECORD_ASSESSED
                        );
        ArrayList<HealthRecordModel> healthRecordModels = new ArrayList<>();

        for(HealthRecord healthRecord : healthRecordList){
            HealthRecordModel healthRecordModel = Constant.getModelMapper().map(healthRecord, HealthRecordModel.class);
            ArrayList<FollowUp> followUpList = followUpRepository.findByHealthRecord_HrId(healthRecord.getHrId());
            for(FollowUp followUp : followUpList){
                healthRecordModel.getFollowUps().add(Constant.getModelMapper().map(followUp, FollowUpModel.class));
            }
            healthRecordModels.add(healthRecordModel);
        }
        obj.add(healthRecordModels);
        return obj;
    }
    @Override
    public boolean submitHealthRecord(HealthRecordModel healthRecordModel) {
        ArrayList<FollowUp> followUps = new ArrayList<>();
        healthRecordModel.getFollowUps().stream().forEach((followUpModel -> {
            followUps.add(Constant.getModelMapper().map(followUpModel, FollowUp.class));
        }));
        HealthRecord healthRecord = healthRecordRepository.findByHrId(healthRecordModel.getHrId());
        healthRecord.setFields(healthRecordModel.getFields());
        healthRecord.setFieldsValues(healthRecordModel.getFieldsValues());
        healthRecord.setStatus(Constant.HEALTH_RECORD_ASSESSED);
        healthRecord.setConclusion(healthRecordModel.getConclusion());
        healthRecord.setPrescription(healthRecordModel.getPrescription());
        healthRecord.setFieldHealthWorker(getFieldHealthWorker(healthRecord));
        healthRecord.setSupervisor(getSupervisor());

        // todo logic for getting FieldHealthWorker and getting Supervisor remaining
        try{
            healthRecordRepository.save(healthRecord);
            followUps.stream().forEach(followUp -> {
                followUp.setHealthRecord(healthRecord);
                followUp.setSecretKey(Constant.generateOtp());
                followUp.setStatus(Constant.FOLLOW_UP_PENDING);
                followUpRepository.save(followUp);
            });
        }
        catch(Exception e){
            System.out.println("e = " + e);
            return false;
        }
        return true;
    }

    private FieldHealthWorker getFieldHealthWorker(HealthRecord healthRecord) {
        return null;
    }

    private Supervisor getSupervisor() {
        return null;
    }
}
