package com.team9.had.service.blackbox.addHospital;

import com.team9.had.model.Hospital;
import com.team9.had.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService{

    @Autowired
    private HospitalRepository hospitalRepository;
    @Override
    public boolean addHospital(Hospital hospital) {
        try {
            hospitalRepository.save(hospital);
        }
        catch(Exception e){
            System.out.println("e = " + e);
            return false;
        }
        return true;
    }

    @Override
    public boolean addHospitals(List<Hospital> hospitals) {
        try{
            hospitalRepository.saveAll(hospitals);
            return true;
        }
        catch(Exception e){
            System.out.println("exception = " + e);
            return false;
        }
    }
}
