package com.team9.had.service.blackbox.addFieldHealthWorker;

import com.team9.had.Constant;
import com.team9.had.entity.Citizen;
import com.team9.had.entity.FieldHealthWorker;
import com.team9.had.repository.CitizenRepository;
import com.team9.had.repository.FieldHealthWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldHealthWorkerServiceImpl implements FieldHealthWorkerService {

    @Autowired
    private CitizenRepository citizenRepository;
    @Autowired
    private FieldHealthWorkerRepository fieldHealthWorkerRepository;
    @Override
    public boolean addFhw(FieldHealthWorker fieldHealthWorker) {
        try{
            if(citizenRepository.findById(fieldHealthWorker.getCitizen().getUhId())!=null && fieldHealthWorkerRepository.findByCitizen_UhId(fieldHealthWorker.getCitizen().getUhId())==null) {
                Citizen citizen = citizenRepository.findById(fieldHealthWorker.getCitizen().getUhId()).get();
                System.out.println("citizen = " + citizen);
                String creds = "FHW"+citizen.getUhId();
                fieldHealthWorker.setLoginId(creds);
                fieldHealthWorker.setPassword(Constant.passwordEncode().encode(creds));
                fieldHealthWorkerRepository.save(fieldHealthWorker);
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
    public boolean addFhws(List<FieldHealthWorker> fieldHealthWorkers) {
        try{
            for(FieldHealthWorker fieldHealthWorker : fieldHealthWorkers){
                if(!addFhw(fieldHealthWorker)) return false;
            }
            return true;
        }
        catch(Exception e){
            System.out.println("exception = " + e);
            return false;
        }
    }
}
