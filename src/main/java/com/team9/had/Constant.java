package com.team9.had;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    Integer AGE = 10000;
    Integer MILLI = 1000;

    static PasswordEncoder passwordEncode(){
        return new BCryptPasswordEncoder();
    }

}
