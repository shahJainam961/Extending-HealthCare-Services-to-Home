package com.team9.had.service.supervisor;

import com.team9.had.entity.*;
import com.team9.had.exception.UserNotFoundException;
import com.team9.had.model.sup.*;
import com.team9.had.repository.*;
import com.team9.had.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private FollowUpRepository followUpRepository;

    @Override
    public Serializable getUnassignedCitizens(String loginId, String role) {
        //todo validation loginId null hoi to bad request
        ArrayList<Object> obj = new ArrayList<>();
        ArrayList<HealthRecord> unassignedHealthRecords = healthRecordRepository.findAllByFieldHealthWorkerNullAndSupervisor_LoginId(loginId);
        ArrayList<UnassignedCitizenModelForSup> unassignedCitizenModelForSups = new ArrayList<>();
        Set<Citizen> st = new HashSet<>();
        for (HealthRecord unassignedHealthRecord : unassignedHealthRecords)
        {
            Citizen citizen = unassignedHealthRecord.getCitizen();
            if(st.contains(citizen) == true) continue;
            st.add(citizen);
            CizModelForSup cizModelForSup = Constant.getModelMapper().map(citizen, CizModelForSup.class);
            ArrayList<FieldHealthWorker> fieldHealthWorkers = fieldHealthWorkerRepository.findAllByAssignedPincode(unassignedHealthRecord.getPincode());
            ArrayList<FhwModelForSup> fhwModelForSups = new ArrayList<>();
            for(FieldHealthWorker fieldHealthWorker : fieldHealthWorkers)
            {
                FhwModelForSup fhwModelForSup = Constant.getModelMapper().map(fieldHealthWorker, FhwModelForSup.class);
                ArrayList<Citizen> citizens = citizenRepository.findAllByFieldHealthWorker_LoginId(fieldHealthWorker.getLoginId());
                Integer citizenAssigned = citizens.size();
                fhwModelForSup.setCitizenAssigned(citizenAssigned);
                fhwModelForSups.add(fhwModelForSup);

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
    public boolean submitAssignment(SubmitAssignedForSup submitAssignedForSup) throws UserNotFoundException {
        // todo validation of submitAssignedForSup --> ekad null then throw bad request
        CizModelForSup cizModelForSup = submitAssignedForSup.getCitizen();
        FhwModelForSup fhwModelForSup = submitAssignedForSup.getFieldHealthWorker();
        Citizen citizen = citizenRepository.findByUhId(cizModelForSup.getUhId());
        FieldHealthWorker fieldHealthWorker = fieldHealthWorkerRepository.findByLoginId(fhwModelForSup.getLoginId());

        if(citizen==null || fieldHealthWorker==null) throw new UserNotFoundException("User Not Found!!");

        try{
            citizen.setFieldHealthWorker(fieldHealthWorker);
            ArrayList<HealthRecord> healthRecords = healthRecordRepository.findAllByFieldHealthWorkerNullAndCitizen_UhId(citizen.getUhId());
            for(HealthRecord healthRecord : healthRecords){
                healthRecord.setFieldHealthWorker(fieldHealthWorker);
                healthRecordRepository.save(healthRecord);
            }
            return true;
        }
        catch(Exception e){
            System.out.println("e = " + e);
            return false;
        }
    }

    @Override
    public Serializable getFhws(String loginId, String role){
        // todo validation of loginId, if null then throw bad request
        ArrayList<Object> obj = new ArrayList<>();
        Supervisor supervisor = supervisorRepository.findByLoginId(loginId);
        // todo throw user not found if supervisor is null
        String districtName = cityRepository.findByPincode(supervisor.getAssignedPincode()).getCityName();
        Integer did = districtRepository.findByDistrictName(districtName).getDid();
        ArrayList<City> cities = cityRepository.findAllByDistrict_Did(did);
        ArrayList<GetFhwModelForSup> getFhwModelForSups = new ArrayList<>();
        cities.forEach((city)->{
            String assignedPincode = city.getPincode();
            ArrayList<FieldHealthWorker> fieldHealthWorkers = fieldHealthWorkerRepository.findAllByAssignedPincode(assignedPincode);
            fieldHealthWorkers.forEach((fieldHealthWorker)->{
                ArrayList<FhwModelForSup> otherFhwModelForSups = new ArrayList<>();
                fieldHealthWorkers.forEach(other->{
                    if(other!=fieldHealthWorker){
                        FhwModelForSup otherFhwModelForSup = Constant.getModelMapper().map(other, FhwModelForSup.class);
                        otherFhwModelForSup.setCitizenAssigned(citizenRepository.findAllByFieldHealthWorker_LoginId(otherFhwModelForSup.getLoginId()).size());
                        otherFhwModelForSups.add(otherFhwModelForSup);
                    }
                });
                ArrayList<CizModelForSup> cizModelForSups = new ArrayList<>();
                ArrayList<Citizen> citizens = citizenRepository.findAllByFieldHealthWorker_LoginId(fieldHealthWorker.getLoginId());
                citizens.forEach((citizen)->{
                    cizModelForSups.add(Constant.getModelMapper().map(citizen, CizModelForSup.class));
                });
                FhwModelForSup fhwModelForSup = Constant.getModelMapper().map(fieldHealthWorker, FhwModelForSup.class);
                fhwModelForSup.setCitizenAssigned(cizModelForSups.size());
                GetFhwModelForSup getFhwModelForSup = new GetFhwModelForSup(
                        fhwModelForSup,
                        cizModelForSups,
                        otherFhwModelForSups
                );
                getFhwModelForSups.add(getFhwModelForSup);
            });
        });
        obj.add(getFhwModelForSups);
        return obj;
    }

    @Override
    public boolean reassign(ReassignedForSup reassignedForSup){
        // todo validation of ReassignedForSup, if any required is null then throw bad request
        try{
            ArrayList<CizModelForSup> cizModelForSups = reassignedForSup.getCitizens();
            FieldHealthWorker fieldHealthWorker = fieldHealthWorkerRepository.findByLoginId(reassignedForSup.getFieldHealthWorker().getLoginId());
            cizModelForSups.forEach((cizModelForSup)->{
                Citizen citizen = citizenRepository.findByUhId(cizModelForSup.getUhId());
                citizen.setFieldHealthWorker(fieldHealthWorker);
                citizenRepository.save(citizen);
            });
            return true;
        }
        catch(Exception e){
            System.out.println("e = " + e);
            return false;
        }
    }

    @Override
    public Serializable stats(String loginId) {
        // todo validation --> loginId null hoi to bad request throw
        ArrayList<Object> obj = new ArrayList<>();
        Supervisor supervisor = supervisorRepository.findByLoginId(loginId);
        Integer did = cityRepository.findByPincode(supervisor.getAssignedPincode()).getDistrict().getDid();

        ArrayList<City> cities = cityRepository.findAllByDistrict_Did(did);
        ArrayList<FhwModelForStat> fhwModelForStats = new ArrayList<>();
        cities.forEach(city -> {
            String assignedPincode = city.getPincode();
            ArrayList<FieldHealthWorker> fieldHealthWorkers = fieldHealthWorkerRepository.findAllByAssignedPincode(assignedPincode);
            fieldHealthWorkers.forEach(fieldHealthWorker -> {
                FhwModelForStat fhwModelForStat = new FhwModelForStat();
                FhwModelForSup fhwModelForSup = Constant.getModelMapper().map(fieldHealthWorker, FhwModelForSup.class);
                fhwModelForSup.setCitizenAssigned(citizenRepository.findAllByFieldHealthWorker_LoginId(fieldHealthWorker.getLoginId()).size());
                fhwModelForStat.setFieldHealthWorker(fhwModelForSup);
                ArrayList<HealthRecord> healthRecords = healthRecordRepository.findAllByFieldHealthWorker_LoginId(fieldHealthWorker.getLoginId());
                healthRecords.forEach(healthRecord -> {
                    ArrayList<FollowUp> followUps = followUpRepository.findAllByHealthRecord_HrId(healthRecord.getHrId());
                    followUps.forEach(followUp -> {
                        fhwModelForStat.getFollowUps().add(Constant.getModelMapper().map(followUp, FollowUpModelForSup.class));
                    });
                });
                fhwModelForStats.add(fhwModelForStat);
            });
        });
        obj.add(fhwModelForStats);
        return obj;
    }
}
