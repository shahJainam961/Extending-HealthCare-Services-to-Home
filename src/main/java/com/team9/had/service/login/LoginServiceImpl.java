package com.team9.had.service.login;

import com.team9.had.Constant;
import com.team9.had.entity.Doctor;
import com.team9.had.entity.Receptionist;
import com.team9.had.model.DoctorModel;
import com.team9.had.model.LoginModel;
import com.team9.had.model.ReceptionistModel;
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
            Doctor doctor = doctorRepository.findByLoginId(loginId);
            ArrayList<Object> obj = new ArrayList<>();
            DoctorModel doctorModel = Constant.getModelMapper().map(doctor, DoctorModel.class);
            obj.add(doctorModel);
            return obj;
        }
        else if(loginId.startsWith(Constant.RECEPTIONIST)){
            Receptionist receptionist = receptionistRepository.findByLoginId(loginId);
            Integer hospitalId = receptionist.getHospital().getHospId();
            ArrayList<Doctor> doctorList = doctorRepository.findAllByHospitalHospId(hospitalId);
            ArrayList<DoctorModel> doctorModels = new ArrayList<>();
            ArrayList<Object> obj = new ArrayList<>();
            obj.add(Constant.getModelMapper().map(receptionist, ReceptionistModel.class));
            doctorList.stream().forEach((doctor)->{
                doctorModels.add(Constant.getModelMapper().map(doctor, DoctorModel.class));
            });
            obj.add(doctorModels);
            return obj;
        }
        else if(loginId.startsWith(Constant.SUPERVISOR)){
            return true;
        }
        else if(loginId.startsWith(Constant.FIELD_HEALTH_WORKER)){
            return true;
        }
        else return null;
    }
}
