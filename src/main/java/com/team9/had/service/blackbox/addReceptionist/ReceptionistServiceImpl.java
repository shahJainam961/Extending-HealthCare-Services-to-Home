package com.team9.had.service.blackbox.addReceptionist;

import com.team9.had.Constant;
import com.team9.had.entity.Citizen;
import com.team9.had.entity.Receptionist;
import com.team9.had.repository.CitizenRepository;
import com.team9.had.repository.ReceptionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceptionistServiceImpl implements ReceptionistService {

    @Autowired
    private CitizenRepository citizenRepository;
    @Autowired
    private ReceptionistRepository receptionistRepository;

    @Override
    public boolean addReceptionist(Receptionist receptionist) {
        try{
            if(citizenRepository.findById(receptionist.getCitizen().getUhId())!=null && receptionistRepository.findByCitizen_UhId(receptionist.getCitizen().getUhId())==null) {
                Citizen citizen = citizenRepository.findById(receptionist.getCitizen().getUhId()).get();
                System.out.println("citizen = " + citizen);
                String creds = "REC"+citizen.getUhId();
                receptionist.setLoginId(creds);
                receptionist.setPassword(Constant.passwordEncode().encode(creds));
                receptionistRepository.save(receptionist);
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
    public boolean addReceptionists(List<Receptionist> receptionists) {
        try{
            for(Receptionist receptionist : receptionists){
                if(!addReceptionist(receptionist)) return false;
            }
            return true;
        }
        catch(Exception e){
            System.out.println("exception = " + e);
            return false;
        }
    }
}
