package com.team9.had.service.blackbox.addCitizen;

import com.team9.had.model.Citizen;
import com.team9.had.repository.CitizenRepository;
import com.team9.had.utils.Constant;
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
            Constant.encryptPII(citizen);
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
            citizens.forEach(citizen -> {
                try {
                    Constant.encryptPII(citizen);
                    citizenRepository.save(citizen);
                } catch (Exception e) {
                    System.out.println("e = " + e);
                }
            });
        }
        catch(Exception e){
            System.out.println("exception = " + e);
            return false;
        }
        return true;
    }
}
