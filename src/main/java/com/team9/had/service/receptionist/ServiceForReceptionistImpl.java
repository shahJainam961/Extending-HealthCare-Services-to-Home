package com.team9.had.service.receptionist;

import com.team9.had.Constant;
import com.team9.had.entity.Citizen;
import com.team9.had.entity.Doctor;
import com.team9.had.entity.HealthRecord;
import com.team9.had.exception.CitizenNotFoundException;
import com.team9.had.exception.DoctorNotFoundException;
import com.team9.had.model.rec.CizModelForRec;
import com.team9.had.model.rec.HrModelForRec;
import com.team9.had.repository.CitizenRepository;
import com.team9.had.repository.DoctorRepository;
import com.team9.had.repository.HealthRecordRepository;
import com.team9.had.repository.ReceptionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

@Service
public class ServiceForReceptionistImpl implements ServiceForReceptionist{

    @Autowired
    private HealthRecordRepository healthRecordRepository;
    @Autowired
    private CitizenRepository citizenRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ReceptionistRepository receptionistRepository;

    @Override
    public Serializable createHealthRecord(HrModelForRec hrModelForRec, String role) throws CitizenNotFoundException, DoctorNotFoundException {

        // todo validation of the request object!

        Integer uhId = hrModelForRec.getCitizen().getUhId();
        Citizen citizen = citizenRepository.findByUhId(uhId);
        if(citizen==null) throw new CitizenNotFoundException("Citizen Not Found!!");

        String loginId = hrModelForRec.getDoctor().getLoginId();
        Doctor doctor = doctorRepository.findByLoginId(loginId);
        if(doctor==null) throw new DoctorNotFoundException("Doctor Not Found!!");

        HealthRecord healthRecord = Constant.getModelMapper().map(hrModelForRec, HealthRecord.class);

        ArrayList<Object> obj = new ArrayList<>();
        healthRecord.setCreationDate(new Date(System.currentTimeMillis()));
        healthRecord.setCreationTime(new Time(System.currentTimeMillis()));
        healthRecord.setStatus(Constant.HEALTH_RECORD_NOT_ASSESSED);
        healthRecord.setReceptionist(receptionistRepository.findByLoginId(role));
        healthRecord.setDoctor(doctor);
        healthRecord.setCitizen(citizen);
        healthRecord.setPincode(Constant.TALUKA_TO_CODE.get(healthRecord.getCity()));
        healthRecordRepository.save(healthRecord);
        obj.add(true);
        return obj;
    }

    @Override
    public Serializable confirmation(Integer uhId) throws CitizenNotFoundException {
        // todo validation of the request object!

        Citizen citizen = citizenRepository.findByUhId(uhId);
        if(citizen==null) throw new CitizenNotFoundException("Citizen Not Found!!");
        ArrayList<Object> obj = new ArrayList<>();
        CizModelForRec cizModelForRec = Constant.getModelMapper().map(citizen, CizModelForRec.class);
        obj.add(cizModelForRec);
        return obj;
    }
}
