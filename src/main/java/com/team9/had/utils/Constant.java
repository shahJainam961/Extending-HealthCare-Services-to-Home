package com.team9.had.utils;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

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

    Integer DAY = 1000*60*60*24;
    Integer MILLI = 1000;
    Integer OTP_LENGTH = 6;

    String ACCOUNT_SID = "AC4dc6465df71d2b5f60edc0facfa02cd1";
    String AUTH_TOKEN = "f950ce7fca5f9d4db4c33354be34d147";

    String SECRET_KEY = "8y/B?E(H+MbQeThWmZq4t6w9z$C&F)J@";

    static PasswordEncoder passwordEncode(){
        return new BCryptPasswordEncoder();
    }
    static ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    static boolean isAuthorised(String actualRole, String expectedRole){
        return actualRole.startsWith(expectedRole);
    }

    ArrayList<Object> EMPTY = new ArrayList<>();

    static String generateOtp() {
        String numbers = "0123456789";
        String otp = "";
        for(int i = 0; i < OTP_LENGTH; i++)
            otp += numbers.charAt(new Random().nextInt(numbers.length()));
        return otp;
    }

    HashMap<String, List<String>> DISTRICT_TO_TALUKA = new HashMap<>() {{
        put("Bangalore", Arrays.asList("Bangalore", "Electronic City", "HSR Layout", "Indiranagar", "Jayanagar"));
        put("Mysore", Arrays.asList("Mysore", "Nazarbad", "Kuvempunagar", "Gokulam", "Saraswathipuram"));
        put("Hassan", Arrays.asList("Hassan", "Alur", "Arakalagudu", "Belur", "Channarayapatna"));
    }};
    HashMap<String, String> CODE_TO_DISTRICT = new HashMap<>(){{
        put("1101", "Bangalore");
        put("1102", "Mysore");
        put("1103", "Hassan");
    }};
    HashMap<String, String> TALUKA_TO_CODE = new HashMap<>(){{
        put("Bangalore", "560001");
        put("Electronic City", "560100");
        put("HSR Layout", "560102");
        put("Indiranagar", "560038");
        put("Jayanagar", "560041");

        put("Mysore", "570001");
        put("Alanahalli", "570028");
        put("Kuvempunagar", "570023");
        put("Gokulam", "570002");
        put("Saraswathipuram", "570009");

        put("Hassan", "573201");
        put("Alur", "573214");
        put("Arakalagudu", "573102");
        put("Belur", "573216");
        put("Channarayapatna", "573131");
    }};
}
