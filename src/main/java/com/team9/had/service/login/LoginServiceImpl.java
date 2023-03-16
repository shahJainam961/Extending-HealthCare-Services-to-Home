package com.team9.had.service.login;

import com.team9.had.Constant;
import com.team9.had.entity.Doctor;
import com.team9.had.entity.FieldHealthWorker;
import com.team9.had.entity.Receptionist;
import com.team9.had.entity.Supervisor;
import com.team9.had.repository.DoctorRepository;
import com.team9.had.repository.FieldHealthWorkerRepository;
import com.team9.had.repository.ReceptionistRepository;
import com.team9.had.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private SupervisorRepository supervisorRepository;
    @Autowired
    private ReceptionistRepository receptionistRepository;
    @Autowired
    private FieldHealthWorkerRepository fieldHealthWorkerRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Serializable loggingIn(LoginModel loginModel) {
        String loginId = loginModel.getLoginId().trim();
        String password = loginModel.getPassword();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginId, password
                )
        );

        if(loginId.startsWith(Constant.DOCTOR)){
//           List<Doctor> doctors = doctorRepository.findById(loginId).stream().toList();
//           if(doctors.size()==0) return null;
//           else{
//               if(doctors.get(0).getPassword().equals(Constant.passwordEncode().encode(password))){
//                    return true;
//               }
//               else return null;
//           }

            return true;
        }
        else if(loginId.startsWith(Constant.RECEPTIONIST)){
//            Receptionist receptionist = receptionistRepository.findByLoginId(loginId);
//            if(receptionist==null) return null;
//            else{
//                if(receptionist.getPassword().equals(Constant.passwordEncode().encode(password))){
//                    Integer hospitalId = receptionist.getHospital().getId();
//                    ArrayList<Doctor> doctorList = doctorRepository.findAllByHospital_Id(hospitalId);
//                    ArrayList<Object> obj = new ArrayList<>();
//                    obj.add(receptionist);
//                    obj.add(doctorList);
//                    return obj;
//                }
//                else return null;
//            }
            Receptionist receptionist = receptionistRepository.findByLoginId(loginId);
            Integer hospitalId = receptionist.getHospital().getId();
            ArrayList<Doctor> doctorList = doctorRepository.findAllByHospital_Id(hospitalId);
            ArrayList<Object> obj = new ArrayList<>();
            obj.add(receptionist);
            obj.add(doctorList);
            return obj;
        }
        else if(loginId.startsWith(Constant.SUPERVISOR)){
//            List<Supervisor> supervisors = supervisorRepository.findById(loginId).stream().toList();
//            if(supervisors.size()==0) return null;
//            else{
//                if(supervisors.get(0).getPassword().equals(Constant.passwordEncode().encode(password))) return true;
//                else return null;
//            }
            return true;
        }
        else if(loginId.startsWith(Constant.FIELD_HEALTH_WORKER)){
//            List<FieldHealthWorker> fieldHealthWorkers = fieldHealthWorkerRepository.findById(loginId).stream().toList();
//            if(fieldHealthWorkers.size()==0) return null;
//            else{
//                if(fieldHealthWorkers.get(0).getPassword().equals(Constant.passwordEncode().encode(password))) return true;
//                else return null;
//            }
            return true;
        }
        else return null;
    }
}
