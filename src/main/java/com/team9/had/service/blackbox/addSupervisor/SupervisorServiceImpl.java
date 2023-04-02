package com.team9.had.service.blackbox.addSupervisor;

import com.team9.had.entity.Citizen;
import com.team9.had.entity.Supervisor;
import com.team9.had.repository.CitizenRepository;
import com.team9.had.repository.SupervisorRepository;
import com.team9.had.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupervisorServiceImpl implements SupervisorService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;
    @Override
    public boolean addSupervisor(Supervisor supervisor) {
        try{
            if(citizenRepository.findById(supervisor.getCitizen().getUhId())!=null && supervisorRepository.findByCitizen_UhId(supervisor.getCitizen().getUhId())==null) {
                Citizen citizen = citizenRepository.findById(supervisor.getCitizen().getUhId()).get();
                String creds = "SUP"+citizen.getUhId();
                supervisor.setLoginId(creds);
                supervisor.setPassword(Constant.passwordEncode().encode(creds));
                supervisorRepository.save(supervisor);
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
    public boolean addSupervisors(List<Supervisor> supervisors) {
        try{
            for(Supervisor supervisor : supervisors){
                if(!addSupervisor(supervisor)) return false;
            }
            return true;
        }
        catch(Exception e){
            System.out.println("exception = " + e);
            return false;
        }
    }
}
