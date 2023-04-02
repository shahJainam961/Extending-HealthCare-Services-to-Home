package com.team9.had.service.blackbox.getOtp;

import com.team9.had.entity.*;
import com.team9.had.exception.UserNotFoundException;
import com.team9.had.repository.*;
import com.team9.had.utils.Constant;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

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


    public boolean getOtp(String loginId) throws UserNotFoundException {
        String mobileNo = "+91";

        if(loginId.startsWith(Constant.DOCTOR)){
            Doctor doctor = doctorRepository.findByLoginId(loginId);
            if(doctor==null) {throw new UserNotFoundException("User Not Found!!");}//throw
            mobileNo += doctor.getCitizen().getMobileNo();
        }
        else if(loginId.startsWith(Constant.RECEPTIONIST)){
            Receptionist receptionist = receptionistRepository.findByLoginId(loginId);
            if(receptionist==null){throw new UserNotFoundException("User Not Found!!");}
            mobileNo += receptionist.getCitizen().getMobileNo();
        }
        else if(loginId.startsWith(Constant.SUPERVISOR)){
            Supervisor supervisor = supervisorRepository.findByLoginId(loginId);
            if(supervisor==null){throw new UserNotFoundException("User Not Found!!");}
            mobileNo += supervisor.getCitizen().getMobileNo();
        }
        else if(loginId.startsWith(Constant.FIELD_HEALTH_WORKER)){
            FieldHealthWorker fieldHealthWorker = fieldHealthWorkerRepository.findByLoginId(loginId);
            if(fieldHealthWorker==null){throw new UserNotFoundException("User Not Found!!");}
            mobileNo += fieldHealthWorker.getCitizen().getMobileNo();
        }
        else{
            throw new UserNotFoundException("User Not Found!!");
        }

        try{
            System.out.println("mobileNo = " + mobileNo);
            Twilio.init(Constant.ACCOUNT_SID, Constant.AUTH_TOKEN);

            String otp = Constant.generateOtp();
            Date date = new Date(System.currentTimeMillis()+(1000*60*30));

            OTP otp1 = new OTP(loginId, otp, date);
            otpRepository.save(otp1);
            String msg = "\n Password reset request (OPT : Valid for 30 minutes/Invalid after 30 minutes or on again requesting for otp) \nOTP: "+otp+"  \n Note: Do not share the otp to anyone!!";

            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber("+919426629406"),
// to give actual number but now only supports one mobile number code for fetching actual number is above commented
                            new com.twilio.type.PhoneNumber("+14344361379"),
                            msg)
                    .create();
            System.out.println("message = " + message.getSid());
            return true;
        }
        catch (Exception e){
            System.out.println("e = " + e);
            return false;
        }
    }

    @Override
    public boolean validateOtp(OTP otpModel) {
        String loginId = otpModel.getLoginId();

        OTP actualModel = otpRepository.findByLoginId(loginId);

        if(actualModel==null) return  false;

        long actualModelMilli = actualModel.getDate().getTime();
        long otpModelMilli = new Date(System.currentTimeMillis()).getTime();

        // expiry
        if(actualModelMilli<otpModelMilli){
            otpRepository.delete(actualModel);
            return false;
        }

        String actualModelOtp = actualModel.getOtp();
        String otpModelOtp = otpModel.getOtp();

        // non expiry and otpmatch
        if(otpModelOtp.equals(actualModelOtp)) {
            otpRepository.delete(actualModel);
        }

        return true;
    }

}
