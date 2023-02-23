package com.team9.had.service.blackbox.addCitizen;

import com.team9.had.entity.Citizen;
import com.team9.had.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenServiceImpl implements CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Override
    public boolean addCitizen(Citizen citizen) {
        try{
            citizenRepository.save(citizen);
            return true;
        }
        catch(Exception e){
            System.out.println("exception = " + e);
            return false;
        }
    }

    @Override
    public boolean addCitizens(List<Citizen> citizens) {
        try{
            citizenRepository.saveAll(citizens);
            return true;
        }
        catch(Exception e){
            System.out.println("exception = " + e);
            return false;
        }
    }
}
