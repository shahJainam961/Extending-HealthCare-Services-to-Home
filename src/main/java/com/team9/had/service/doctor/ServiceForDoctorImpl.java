package com.team9.had.service.doctor;

import com.team9.had.entity.*;
import com.team9.had.model.doc.FupModelForDoc;
import com.team9.had.model.doc.HrModelForDoc;
import com.team9.had.repository.*;
import com.team9.had.utils.Constant;
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
    @Autowired
    private CitizenRepository citizenRepository;
    @Autowired
    private SupervisorRepository supervisorRepository;
    @Autowired
    private FieldHealthWorkerRepository fieldHealthWorkerRepository;

    @Autowired
    private CityRepository cityRepository;
    @Override
    public Serializable getNewHealthRecords(String loginId) {
        ArrayList<Object> obj = new ArrayList<>();

        ArrayList<HealthRecord> healthRecords =
                healthRecordRepository
                .findAllByDoctor_LoginIdAndStatusAndCreationDateOrderByCreationTime(
                        loginId, Constant.HEALTH_RECORD_NOT_ASSESSED,
                        new Date(System.currentTimeMillis())
                );
        ArrayList<HrModelForDoc> hrModelForDocs = new ArrayList<>();
        healthRecords.stream().forEach((healthRecord) -> {
            hrModelForDocs.add(Constant.getModelMapper().map(healthRecord, HrModelForDoc.class));
        });
        obj.add(hrModelForDocs);
        return obj;
    }

    @Override
    public Serializable getOldHealthRecords(String loginId) {
        ArrayList<Object> obj = new ArrayList<>();

        ArrayList<HealthRecord> healthRecords =
                healthRecordRepository
                        .findAllByDoctor_LoginIdAndStatusOrderByCreationDateDescCreationTimeDesc(
                                loginId, Constant.HEALTH_RECORD_ASSESSED
                        );
        ArrayList<HrModelForDoc> hrModelForDocs = new ArrayList<>();

        for(HealthRecord healthRecord : healthRecords){
            HrModelForDoc hrModelForDoc = Constant.getModelMapper().map(healthRecord, HrModelForDoc.class);
            ArrayList<FollowUp> followUps = followUpRepository.findByHealthRecord_HrId(healthRecord.getHrId());
            for(FollowUp followUp : followUps){
                hrModelForDoc.getFollowUps().add(Constant.getModelMapper().map(followUp, FupModelForDoc.class));
            }
            hrModelForDocs.add(hrModelForDoc);
        }
        obj.add(hrModelForDocs);
        return obj;
    }
    @Override
    public boolean submitHealthRecord(HrModelForDoc hrModelForDoc) {
        try{
            ArrayList<FollowUp> followUps = new ArrayList<>();
            hrModelForDoc.getFollowUps().stream().forEach((fupModelForDoc -> {
                followUps.add(Constant.getModelMapper().map(fupModelForDoc, FollowUp.class));
            }));
            HealthRecord healthRecord = healthRecordRepository.findByHrId(hrModelForDoc.getHrId());
            healthRecord.setFields(hrModelForDoc.getFields());
            healthRecord.setFieldsValues(hrModelForDoc.getFieldsValues());
            healthRecord.setStatus(Constant.HEALTH_RECORD_ASSESSED);
            healthRecord.setConclusion(hrModelForDoc.getConclusion());
            healthRecord.setTreatment(hrModelForDoc.getTreatment());
            healthRecord.setPrescription(hrModelForDoc.getPrescription());
            healthRecord.setFieldHealthWorker(getFieldHealthWorker(healthRecord));
            healthRecord.setSupervisor(getSupervisor(healthRecord));

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

    @Override
    public Serializable getConsentData(Integer uhId) {
        // todo validation

        ArrayList<HealthRecord> healthRecords = healthRecordRepository.findAllByCitizen_UhIdAndStatusOrderByCreationDateDescCreationTimeDesc(
                    uhId, Constant.HEALTH_RECORD_ASSESSED
        );

        ArrayList<Object> obj = new ArrayList<>();
        ArrayList<HrModelForDoc> hrModelForDocs = new ArrayList<>();
        healthRecords.forEach((healthRecord)->{
            HrModelForDoc hrModelForDoc = Constant.getModelMapper().map(healthRecord, HrModelForDoc.class);
            hrModelForDocs.add(hrModelForDoc);
        });
        obj.add(hrModelForDocs);
        return obj;

    }


    // todo modify the below function as per the requirement
    private FieldHealthWorker getFieldHealthWorker(HealthRecord healthRecord) {
        FieldHealthWorker fieldHealthWorker = citizenRepository.findByUhId(healthRecord.getCitizen().getUhId()).getFieldHealthWorker();
        if(fieldHealthWorker == null) return null;
        if(!(fieldHealthWorker.getAssignedPincode().equals(healthRecord.getPincode()))) return null;
        return fieldHealthWorker;
    }

    // todo modify the below function as per the requirement
    private Supervisor getSupervisor(HealthRecord healthRecord) {
        City city = cityRepository.findByCityName(healthRecord.getDistrict());
        Supervisor supervisor = supervisorRepository.findByAssignedPincode(city.getPincode());
        return supervisor;
    }
}
