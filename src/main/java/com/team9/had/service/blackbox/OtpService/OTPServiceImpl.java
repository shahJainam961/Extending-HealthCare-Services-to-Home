package com.team9.had.service.blackbox.OtpService;

import com.team9.had.exception.ResourceNotFoundException;
import com.team9.had.model.*;
import com.team9.had.repository.*;
import com.team9.had.service.twilio.TwilioMessaging;
import com.team9.had.utils.Constant;
import com.team9.had.utils.EncryptDecrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class OTPServiceImpl implements OTPService{

    @Autowired
    private OTPRepository otpRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ReceptionistRepository receptionistRepository;
    @Autowired
    private SupervisorRepository supervisorRepository;
    @Autowired
    private FieldHealthWorkerRepository fieldHealthWorkerRepository;


    public boolean getOtp(String loginId) throws Exception {
        // todo validations --> loginId null hoi to Bad Request moklvaani
        Set<Citizen> citizenSet = new HashSet<>();
        String mobileNo = "";
        if(loginId.startsWith(Constant.DOCTOR)){
            Doctor doctor = doctorRepository.findByLoginId(loginId);
            if(doctor==null) {throw new ResourceNotFoundException(Constant.RESOURCE_NOT_FOUND_MSG);}
//            Constant.getDecryptedDoctor(doctor, citizenSet);
            mobileNo += doctor.getCitizen().getMobileNo();
        }
        else if(loginId.startsWith(Constant.RECEPTIONIST)){
            Receptionist receptionist = receptionistRepository.findByLoginId(loginId);
            if(receptionist==null){throw new ResourceNotFoundException(Constant.RESOURCE_NOT_FOUND_MSG);}
//            Constant.getDecryptedReceptionist(receptionist, citizenSet);
            mobileNo += receptionist.getCitizen().getMobileNo();
        }
        else if(loginId.startsWith(Constant.SUPERVISOR)){
            Supervisor supervisor = supervisorRepository.findByLoginId(loginId);
            if(supervisor==null){throw new ResourceNotFoundException(Constant.RESOURCE_NOT_FOUND_MSG);}
//            Constant.getDecryptedSupervisor(supervisor, citizenSet);
            mobileNo += supervisor.getCitizen().getMobileNo();
        }
        else if(loginId.startsWith(Constant.FIELD_HEALTH_WORKER)){
            FieldHealthWorker fieldHealthWorker = fieldHealthWorkerRepository.findByLoginId(loginId);
            if(fieldHealthWorker==null){throw new ResourceNotFoundException(Constant.RESOURCE_NOT_FOUND_MSG);}
//            Constant.getDecryptedFieldHealthWorker(fieldHealthWorker, citizenSet);
            mobileNo += fieldHealthWorker.getCitizen().getMobileNo();
        }
        else{
            throw new ResourceNotFoundException(Constant.RESOURCE_NOT_FOUND_MSG);
        }

        try{
            mobileNo = Constant.MOBILE_PREFIX + EncryptDecrypt.decrypt(mobileNo, Constant.SECRET_KEY);
            System.out.println("mobileNo = " + mobileNo);
            String otp = Constant.generateOtp();
            Date date = new Date(System.currentTimeMillis()+ Constant.OTP_EXPIRATION_TIME);
            String msg = "\n Password reset request (OPT : Valid for 30 minutes | Invalid after 30 minutes or on again requesting for otp) \nOTP: "+otp+"  \n Note: Do not share the otp to anyone!!";

            boolean flag = TwilioMessaging.sendMessage(msg, mobileNo);
            if(!flag) return false;

            OTP otp1 = new OTP(loginId, otp, date);
            otpRepository.save(otp1);
            return true;
        }
        catch (Exception e){
            System.out.println("e = " + e);
            return false;
        }
    }

    @Override
    public String validateOtp(OTP otpModel) {
        // todo validations --> otpModel ma thi fields ekad null hoi to bad requst moklvaani
        String loginId = otpModel.getLoginId();

        OTP actualModel = otpRepository.findByLoginId(loginId);

        if(actualModel==null) return Constant.OTP_EXPIRED;

        long actualModelMilli = actualModel.getDate().getTime();
        long otpModelMilli = new Date(System.currentTimeMillis()).getTime();

        // expiry
        if(actualModelMilli<otpModelMilli){
            otpRepository.delete(actualModel);
            return Constant.OTP_EXPIRED;
        }

        String actualModelOtp = actualModel.getOtp();
        String otpModelOtp = otpModel.getOtp();

        // non expiry and otp-match
        if(otpModelOtp.equals(actualModelOtp)) {
            otpRepository.delete(actualModel);
            return Constant.OTP_VALID;
        }
        return Constant.OTP_INVALID;
    }

}
