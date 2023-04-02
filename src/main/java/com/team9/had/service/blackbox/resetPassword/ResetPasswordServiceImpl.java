package com.team9.had.service.blackbox.resetPassword;

import com.team9.had.entity.*;
import com.team9.had.exception.UserNotFoundException;
import com.team9.had.model.LoginModel;
import com.team9.had.repository.*;
import com.team9.had.utils.Constant;
import com.team9.had.utils.EncryptDecrypt;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    @Autowired
    private ReceptionistRepository receptionistRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private SupervisorRepository supervisorRepository;
    @Autowired
    private FieldHealthWorkerRepository fieldHealthWorkerRepository;

    @Autowired
    private SecretRepository secretRepository;

    @Override
    public boolean resetPassword(LoginModel loginModel, HttpServletRequest httpServletRequest) throws Exception {

        String encryptedSecret = httpServletRequest.getHeader("secret");

        SecretForResetPassword secretForResetPassword = secretRepository.findBySecret(encryptedSecret);
        if(secretForResetPassword==null) return false;
        else{
            String secret = EncryptDecrypt.decrypt(encryptedSecret, Constant.SECRET_KEY);
            int index = secret.indexOf('#');
            String loginId = secret.substring(0, index);
            if(!(loginId.equals(loginModel.getLoginId()))) return false;
            secretRepository.delete(secretForResetPassword);
        }

        String loginId = loginModel.getLoginId();
        String password = loginModel.getPassword();
        if(loginId.startsWith(Constant.DOCTOR)){
            Doctor doctor = doctorRepository.findByLoginId(loginId);
            if(doctor==null) {throw new UserNotFoundException("User Not Found!!");}
            try{
                doctor.setPassword(Constant.passwordEncode().encode(password));
                doctorRepository.save(doctor);
            }
            catch(Exception e){
                System.out.println("e = " + e);
                return false;
            }
        }
        else if(loginId.startsWith(Constant.RECEPTIONIST)){
            Receptionist receptionist = receptionistRepository.findByLoginId(loginId);
            if(receptionist==null){throw new UserNotFoundException("User Not Found!!");}
            try{
                receptionist.setPassword(Constant.passwordEncode().encode(password));
                receptionistRepository.save(receptionist);
            }
            catch(Exception e){
                System.out.println("e = " + e);
                return false;
            }
        }
        else if(loginId.startsWith(Constant.SUPERVISOR)){
            Supervisor supervisor = supervisorRepository.findByLoginId(loginId);
            if(supervisor==null){throw new UserNotFoundException("User Not Found!!");}
            try{
                supervisor.setPassword(Constant.passwordEncode().encode(password));
                supervisorRepository.save(supervisor);
            }
            catch(Exception e){
                System.out.println("e = " + e);
                return false;
            }
        }
        else if(loginId.startsWith(Constant.FIELD_HEALTH_WORKER)){
            FieldHealthWorker fieldHealthWorker = fieldHealthWorkerRepository.findByLoginId(loginId);
            if(fieldHealthWorker==null){throw new UserNotFoundException("User Not Found!!");}
            try{
                fieldHealthWorker.setPassword(Constant.passwordEncode().encode(password));
                fieldHealthWorkerRepository.save(fieldHealthWorker);
            }
            catch(Exception e){
                System.out.println("e = " + e);
                return false;
            }
        }
        else{
            throw new UserNotFoundException("User Not Found!!");
        }
        return true;
    }
}
