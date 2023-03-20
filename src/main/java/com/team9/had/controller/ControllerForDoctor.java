package com.team9.had.controller;

import com.team9.had.Constant;
import com.team9.had.model.HealthRecordModel;
import com.team9.had.service.doctor.ServiceForDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(originPatterns = "*")
public class ControllerForDoctor {

    @Autowired
    private ServiceForDoctor serviceForDoctor;
    @GetMapping("/getNewHealthRecords")
    public ResponseEntity<Serializable> getNewHealthRecords(@RequestParam String loginId, @RequestAttribute String role){

        if(isValid(role)){
            Serializable obj = serviceForDoctor.getNewHealthRecords(loginId);
            if(obj != null){
                return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
            }
            else{
                return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
            }
        }
        return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
    }

    @GetMapping("/getOldHealthRecords")
    public ResponseEntity<Serializable> getOldHealthRecords(@RequestParam String loginId, @RequestAttribute String role){
        if(isValid(role)){
            Serializable obj = serviceForDoctor.getOldHealthRecords(loginId);
            if(obj != null){
                return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
            }
            else{
                return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
            }
        }
        return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
    }

    @PostMapping("/submitHealthRecord")
    public ResponseEntity<Serializable> submitHealthRecord(@RequestBody HealthRecordModel healthRecordModel, @RequestAttribute String role){
//        HealthRecordModel healthRecordModel = Constant.getObjectMapper().convertValue(obj1, HealthRecordModel.class);
        if(isValid(role)){
            Serializable obj = serviceForDoctor.submitHealthRecord(healthRecordModel);
            if(obj != null){
                return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
            }
            else{
                return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
            }
        }
        return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
    }

    public boolean isValid(String role){
        return role.startsWith(Constant.DOCTOR);
    }
}
