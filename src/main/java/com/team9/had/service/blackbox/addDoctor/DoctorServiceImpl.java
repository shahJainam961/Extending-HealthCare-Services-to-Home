package com.team9.had.service.blackbox.addDoctor;

import com.team9.had.model.Citizen;
import com.team9.had.model.Doctor;
import com.team9.had.repository.CitizenRepository;
import com.team9.had.repository.DoctorRepository;
import com.team9.had.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    @Override
    public boolean addDoctor(Doctor doctor) {
        try{
            if(citizenRepository.findById(doctor.getCitizen().getUhId())!=null && doctorRepository.findByCitizen_UhId(doctor.getCitizen().getUhId())==null) {
                Citizen citizen = citizenRepository.findById(doctor.getCitizen().getUhId()).get();
                System.out.println("citizen = " + citizen);
                String creds = "DOC"+citizen.getUhId();
                doctor.setLoginId(creds);
                doctor.setPassword(Constant.passwordEncode().encode(creds));
                doctorRepository.save(doctor);
                return true;
            }
            else return false;
        }
        catch(Exception e) {
            System.out.println("exception = " + e);
            return false;
        }
    }

    @Override
    public boolean addDoctors(List<Doctor> doctors) {
        try{
            for(Doctor doctor : doctors){
                if(!addDoctor(doctor)) return false;
            }
            return true;
        }
        catch(Exception e){
            System.out.println("exception = " + e);
            return false;
        }
    }

}
