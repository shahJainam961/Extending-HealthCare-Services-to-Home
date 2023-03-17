package com.team9.had;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Random;

public interface Constant {
    String DOCTOR = "DOC";
    String RECEPTIONIST = "REC";
    String SUPERVISOR = "SUP";
    String FIELD_HEALTH_WORKER = "FHW";
    Integer HEALTH_RECORD_ASSESSED = 1;
    Integer HEALTH_RECORD_NOT_ASSESSED = 0;

    Integer FOLLOW_UP_PENDING = 0;
    Integer FOLLOW_UP_DONE = 1;
    Integer FOLLOW_UP_BACKLOGGED = 2;

    Integer AGE = 1000*60*60*24;
    Integer MILLI = 1000;
    Integer OTP_LENGTH = 6;

    static PasswordEncoder passwordEncode(){
        return new BCryptPasswordEncoder();
    }
    static ModelMapper getModelMapper(){
        return new ModelMapper();
    }
    static ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    static String generateOtp() {
        String numbers = "0123456789";
        String otp = "";
        for(int i = 0; i < OTP_LENGTH; i++)
            otp += numbers.charAt(new Random().nextInt(numbers.length()));
        return otp;
    }
}
