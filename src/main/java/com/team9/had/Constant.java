package com.team9.had;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    HashMap<String, List<String>> STATETODISTRICT = new HashMap<>() {{
        put("Karnataka", Arrays.asList("Bangalore", "Mysore", "Hassan", "Mangalore", "Hampi"));
    }};
    HashMap<String, List<String>> DISTRICTTOTALUKA = new HashMap<>() {{
        put("Bangalore", Arrays.asList("Adugodi", "Banashankari", "Basavanagudi", "Bommanahalli", "Chickpet", "Electronic City", "HSR Layout", "Indiranagar", "Jayanagar", "Malleswaram"));
        put("Mysore", Arrays.asList("Bannimantap", "Nazarbad", "Kuvempunagar", "Gokulam", "Saraswathipuram", "Yadavagiri", "Siddarthanagar", "Vivekanandanagar", "Jayalakshmipuram", "Kesare"));
        put("Hassan", Arrays.asList("Alur", "Arakalagudu", "Belur", "Channarayapatna", "Hassan", "Hiriyur", "Holenarsipur", "K.R.Pet", "Sakleshpur", "Santhigiri"));
    }};
}
