package com.team9.had.service.supervisor;

import com.team9.had.Constant;
import com.team9.had.entity.Citizen;
import com.team9.had.entity.FieldHealthWorker;
import com.team9.had.entity.HealthRecord;
import com.team9.had.entity.Supervisor;
import com.team9.had.model.sup.*;
import com.team9.had.repository.CitizenRepository;
import com.team9.had.repository.FieldHealthWorkerRepository;
import com.team9.had.repository.HealthRecordRepository;
import com.team9.had.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;

@Service
public class ServiceForSupervisorImpl implements ServiceForSupervisor{

    @Autowired
    private CitizenRepository citizenRepository;
    @Autowired
    private FieldHealthWorkerRepository fieldHealthWorkerRepository;
    @Autowired
    private HealthRecordRepository healthRecordRepository;
    @Autowired
    private SupervisorRepository supervisorRepository;

    @Override
    public Serializable getUnassignedCitizens(String loginId, String role) {
        ArrayList<Object> obj = new ArrayList<>();
        ArrayList<HealthRecord> unassignedHealthRecords = healthRecordRepository.findAllByFieldHealthWorkerNullAndSupervisor_LoginId(loginId);
        ArrayList<UnassignedCitizenModelForSup> unassignedCitizenModelForSups = new ArrayList<>();
        for (HealthRecord unassignedHealthRecord : unassignedHealthRecords) {
            Citizen citizen = unassignedHealthRecord.getCitizen();
            CizModelForSup cizModelForSup = Constant.getModelMapper().map(citizen, CizModelForSup.class);
            ArrayList<FieldHealthWorker> fieldHealthWorkers = fieldHealthWorkerRepository.findAllByAssignedPincode(unassignedHealthRecord.getPincode());
            ArrayList<FhwModelForSup> fhwModelForSups = new ArrayList<>();
            for(FieldHealthWorker fieldHealthWorker : fieldHealthWorkers){
                fhwModelForSups.add(Constant.getModelMapper().map(fieldHealthWorker, FhwModelForSup.class));
            }
            UnassignedCitizenModelForSup unassignedCitizenModelForSup = new UnassignedCitizenModelForSup();
            unassignedCitizenModelForSup.setCitizen(cizModelForSup);
            unassignedCitizenModelForSup.setFieldHealthWorkers(fhwModelForSups);
            unassignedCitizenModelForSups.add(unassignedCitizenModelForSup);
        }
        obj.add(unassignedCitizenModelForSups);
        return obj;
    }

    @Override
    public Serializable submitAssignment(SubmitAssignedForSup submitAssignedForSup) {
        CizModelForSup cizModelForSup = submitAssignedForSup.getCitizen();
        FhwModelForSup fhwModelForSup = submitAssignedForSup.getFieldHealthWorker();
        Citizen citizen = citizenRepository.findByUhId(cizModelForSup.getUhId());
        FieldHealthWorker fieldHealthWorker = fieldHealthWorkerRepository.findByLoginId(fhwModelForSup.getLoginId());

        citizen.setFieldHealthWorker(fieldHealthWorker);
        ArrayList<HealthRecord> healthRecords = healthRecordRepository.findAllByFieldHealthWorkerNullAndCitizen_UhId(citizen.getUhId());
        for(HealthRecord healthRecord : healthRecords){
            healthRecord.setFieldHealthWorker(fieldHealthWorker);
            healthRecordRepository.save(healthRecord);
        }
        ArrayList<Object> obj = new ArrayList<>();
        obj.add(true);
        return obj;
    }

    // todo supervisor ne tool apvano che ena maate ni api's
    @Override
    public Serializable getFhws(String loginId, String role){
        ArrayList<Object> obj = new ArrayList<>();
        Supervisor supervisor = supervisorRepository.findByLoginId(loginId);
        String district = Constant.CODE_TO_DISTRICT.get(supervisor.getAssignedPincode());
        ArrayList<String> cities = (ArrayList<String>) Constant.DISTRICT_TO_TALUKA.get(district);
        ArrayList<GetFhwModelForSup> getFhwModelForSups = new ArrayList<>();
        cities.forEach((city)->{
            String assignedPincode = Constant.TALUKA_TO_CODE.get(city);
            ArrayList<FieldHealthWorker> fieldHealthWorkers = fieldHealthWorkerRepository.findAllByAssignedPincode(assignedPincode);
            fieldHealthWorkers.forEach((fieldHealthWorker)->{
                ArrayList<FhwModelForSup> otherFhwModelForSups = new ArrayList<>();
                fieldHealthWorkers.forEach(other->{
                    if(other!=fieldHealthWorker){
                        otherFhwModelForSups.add(Constant.getModelMapper().map(other, FhwModelForSup.class));
                    }
                });
                ArrayList<CizModelForSup> cizModelForSups = new ArrayList<>();
                ArrayList<Citizen> citizens = citizenRepository.findAllByFieldHealthWorker_LoginId(fieldHealthWorker.getLoginId());
                citizens.forEach((citizen)->{
                    cizModelForSups.add(Constant.getModelMapper().map(citizen, CizModelForSup.class));
                });
                GetFhwModelForSup getFhwModelForSup = new GetFhwModelForSup(
                        Constant.getModelMapper().map(fieldHealthWorker, FhwModelForSup.class),
                        cizModelForSups,
                        cizModelForSups.size(),
                        otherFhwModelForSups
                );
                getFhwModelForSups.add(getFhwModelForSup);
            });
        });
        obj.add(getFhwModelForSups);
        return obj;
    }

    @Override
    public Serializable reassign(ReassignedForSup reassignedForSup){
        ArrayList<CizModelForSup> cizModelForSups = reassignedForSup.getCizModelForSups();
        FieldHealthWorker fieldHealthWorker = fieldHealthWorkerRepository.findByLoginId(reassignedForSup.getFhwModelForSup().getLoginId());
        cizModelForSups.forEach((cizModelForSup)->{
            Citizen citizen = citizenRepository.findByUhId(cizModelForSup.getUhId());
            citizen.setFieldHealthWorker(fieldHealthWorker);
            citizenRepository.save(citizen);
        });
        ArrayList<Object> obj = new ArrayList<>();
        obj.add(true);
        return obj;
    }
}
