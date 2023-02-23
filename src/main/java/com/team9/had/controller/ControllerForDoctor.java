package com.team9.had.controller;

import com.team9.had.entity.HealthRecord;
import com.team9.had.service.doctor.ServiceForDoctor;
import com.team9.had.service.login.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/doctor")
public class ControllerForDoctor {

    @Autowired
    private ServiceForDoctor serviceForDoctor;
    @GetMapping("/getNewHealthRecords")
    public ResponseEntity<Serializable> getNewHealthRecords(@RequestBody LoginModel loginModel){
        Serializable obj = serviceForDoctor.getNewHealthRecords(loginModel);
        if(obj != null){
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
        }
        else{
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
        }
    }

    @GetMapping("/getOldHealthRecords")
    public ResponseEntity<Serializable> getOldHealthRecords(@RequestBody LoginModel loginModel){
        Serializable obj = serviceForDoctor.getOldHealthRecords(loginModel);
        if(obj != null){
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
        }
        else{
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
        }
    }

    @PostMapping("/submitHealthRecord")
    public ResponseEntity<Serializable> submitHealthRecord(@RequestBody HealthRecord healthRecord){
        Serializable obj = serviceForDoctor.submitHealthRecord(healthRecord);
        if(obj != null){
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
        }
        else{
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
        }
    }

}
