package com.team9.had;

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
    HashMap<String, List<String>> STATE_TO_DISTRICT = new HashMap<>() {{
        put("Karnataka", Arrays.asList("Bangalore", "Mysore", "Hassan"));
    }};
    HashMap<String, List<String>> DISTRICT_TO_TALUKA = new HashMap<>() {{
        put("Bangalore", Arrays.asList("Bangalore", "Electronic City", "HSR Layout", "Indiranagar", "Jayanagar"));
        put("Mysore", Arrays.asList("Mysore", "Nazarbad", "Kuvempunagar", "Gokulam", "Saraswathipuram"));
        put("Hassan", Arrays.asList("Hassan", "Alur", "Arakalagudu", "Belur", "Channarayapatna"));
    }};
    HashMap<String, String> STATE_TO_CODE = new HashMap<>(){{
        put("11", "Karnataka");
    }};
    HashMap<String, String> CODE_TO_STATE = new HashMap<>(){{
        put("Karnataka", "11");
    }};
    HashMap<String, String> DISTRICT_TO_CODE = new HashMap<>(){{
       put("Bangalore", "1101");
       put("Mysore", "1102");
       put("Hassan", "1103");
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
    HashMap<String, String> CODE_TO_TALUKA = new HashMap<>(){{
        put("560001", "Bangalore");
        put("560100", "Electronic City");
        put("560102", "HSR Layout");
        put("560038", "Indiranagar");
        put("560041", "Jayanagar");

        put("570001", "Mysore");
        put("570028", "Nazarbad");
        put("570023", "Kuvempunagar");
        put("570002", "Gokulam");
        put("570009", "Saraswathipuram");

        put("573201", "Hassan");
        put("573214", "Alur");
        put("573102", "Arakalagudu");
        put("573216", "Belur");
        put("573131", "Channarayapatna");
    }};
}
