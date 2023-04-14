package com.team9.had.service.receptionist;

import com.team9.had.customModel.rec.CizModelForRec;
import com.team9.had.customModel.rec.HrModelForRec;
import com.team9.had.exception.BadRequestException;
import com.team9.had.exception.ResourceNotFoundException;
import com.team9.had.model.Citizen;
import com.team9.had.model.Doctor;
import com.team9.had.model.HealthRecord;
import com.team9.had.repository.CitizenRepository;
import com.team9.had.repository.DoctorRepository;
import com.team9.had.repository.HealthRecordRepository;
import com.team9.had.repository.ReceptionistRepository;
import com.team9.had.utils.Constant;
import com.team9.had.utils.V;
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
    public boolean createHealthRecord(HrModelForRec hrModelForRec, String role) throws Exception {

        if(!V.validateHrModelForRec(hrModelForRec)) throw new BadRequestException(Constant.BAD_REQUEST_MSG);

        Integer uhId = hrModelForRec.getCitizen().getUhId();
        Citizen citizen = citizenRepository.findByUhId(uhId);
        if(citizen==null) throw new ResourceNotFoundException(Constant.RESOURCE_NOT_FOUND_MSG);

        String loginId = hrModelForRec.getDoctor().getLoginId();
        Doctor doctor = doctorRepository.findByLoginId(loginId);
        if(doctor==null) throw new ResourceNotFoundException(Constant.RESOURCE_NOT_FOUND_MSG);

        try{
            HealthRecord healthRecord = Constant.getModelMapper().map(hrModelForRec, HealthRecord.class);

            healthRecord.setCreationDate(new Date(System.currentTimeMillis()));
            healthRecord.setCreationTime(new Time(System.currentTimeMillis()));
            healthRecord.setStatus(Constant.HEALTH_RECORD_NOT_ASSESSED);
            healthRecord.setReceptionist(receptionistRepository.findByLoginId(role));
            healthRecord.setDoctor(doctor);
            healthRecord.setCitizen(citizen);
            healthRecord.setPincode(hrModelForRec.getPincode());
            healthRecordRepository.save(healthRecord);
            return true;
        }
        catch(Exception e){
            System.out.println("e = " + e);
            return false;
        }
    }

    @Override
    public Serializable confirmation(Integer uhId) throws Exception {

        if(uhId==null) throw new BadRequestException(Constant.BAD_REQUEST_MSG);

        Citizen citizen = citizenRepository.findByUhId(uhId);
        if(citizen==null) throw new ResourceNotFoundException(Constant.RESOURCE_NOT_FOUND_MSG);

        ArrayList<Object> obj = new ArrayList<>();
        CizModelForRec cizModelForRec = Constant.getModelMapper().map(citizen, CizModelForRec.class);
        obj.add(cizModelForRec);
        return obj;
    }
}
