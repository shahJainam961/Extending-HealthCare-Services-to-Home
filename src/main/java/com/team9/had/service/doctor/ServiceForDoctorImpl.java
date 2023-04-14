package com.team9.had.service.doctor;

import com.team9.had.entity.*;
import com.team9.had.model.doc.FupModelForDocRes;
import com.team9.had.model.doc.HrModelForDoc;
import com.team9.had.model.doc.HrModelForDocRes;
import com.team9.had.model.doc.StartDateEndDateModel;
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
    private CityRepository cityRepository;
    @Override
    public Serializable getNewHealthRecords(String loginId){
        // todo validation --> loginId null hoi to bad request moklvaani
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
    public Serializable getOldHealthRecords(String loginId, StartDateEndDateModel startDateEndDateModel) throws Exception {
        // todo validations  --> loginId null hoi to bad request moklvaani
        ArrayList<Object> obj = new ArrayList<>();
        Date startDate = startDateEndDateModel.getStartDate();
        Date endDate = startDateEndDateModel.getEndDate();
        ArrayList<HealthRecord> healthRecords =
                healthRecordRepository
                        .findAllByDoctor_LoginIdAndStatusAndCreationDateBetweenOrderByCreationDateDescCreationTimeDesc(
                                loginId, Constant.HEALTH_RECORD_ASSESSED, startDate, endDate
                        );
        ArrayList<HrModelForDocRes> hrModelForDocs = new ArrayList<>();

        for(HealthRecord healthRecord : healthRecords){
            HrModelForDocRes hrModelForDoc = Constant.getModelMapper().map(healthRecord, HrModelForDocRes.class);
            ArrayList<FollowUp> followUps = followUpRepository.findAllByHealthRecord_HrId(healthRecord.getHrId());
            for(FollowUp followUp : followUps){
                hrModelForDoc.getFollowUps().add(Constant.getModelMapper().map(followUp, FupModelForDocRes.class));
            }
            hrModelForDocs.add(hrModelForDoc);
        }
        obj.add(hrModelForDocs);
        return obj;
    }


    @Override
    public boolean submitHealthRecord(HrModelForDoc hrModelForDoc) {
        // todo validations  --> nullity check for hrModelForDoc, if any null then return false
        try{
            ArrayList<FollowUp> followUps = new ArrayList<>();
            hrModelForDoc.getFollowUps().stream().forEach((fupModelForDoc -> {
                FollowUp followUp = Constant.getModelMapper().map(fupModelForDoc, FollowUp.class);
                ArrayList<Boolean> vitals = fupModelForDoc.getVitals();
                followUp.setBloodSugar(vitals.get(0)?"":null);
                followUp.setBloodOxygen(vitals.get(1)?"":null);
                followUp.setSkinColor(vitals.get(2)?"":null);
                followUp.setEyeColor(vitals.get(3)?"":null);
                followUp.setTemperature(vitals.get(4)?"":null);
                followUp.setInflammation(vitals.get(5)?"":null);
                followUps.add(followUp);
            }));
            HealthRecord healthRecord = healthRecordRepository.findByHrId(hrModelForDoc.getHrId());
            // todo validation --> healthRecord null hase to return false
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
        // todo validation --> uhId, if null then bad request
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

    private FieldHealthWorker getFieldHealthWorker(HealthRecord healthRecord) {
        FieldHealthWorker fieldHealthWorker = citizenRepository.findByUhId(healthRecord.getCitizen().getUhId()).getFieldHealthWorker();
        if(fieldHealthWorker == null) return null;
        if(!(fieldHealthWorker.getAssignedPincode().equals(healthRecord.getPincode()))) return null;
        return fieldHealthWorker;
    }

    private Supervisor getSupervisor(HealthRecord healthRecord) {
        City city = cityRepository.findByCityName(healthRecord.getDistrict());
        Supervisor supervisor = supervisorRepository.findByAssignedPincode(city.getPincode());
        // todo validation --> supervisor null hoi to user not found exception
        return supervisor;
    }
}
