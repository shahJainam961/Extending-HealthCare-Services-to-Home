package com.team9.had.controller;

import com.team9.had.Constant;
import com.team9.had.model.doc.HrModelForDoc;
import com.team9.had.service.doctor.ServiceForDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/doctor")
@CrossOrigin("*")
public class ControllerForDoctor {

    @Autowired
    private ServiceForDoctor serviceForDoctor;
    @GetMapping("/getNewHealthRecords")
    public ResponseEntity<Serializable> getNewHealthRecords(@RequestParam String loginId, @RequestAttribute String role){

            if(Constant.isAuthorised(role, Constant.DOCTOR)){
            Serializable obj = serviceForDoctor.getNewHealthRecords(loginId);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(401));
    }

    @GetMapping("/getOldHealthRecords")
    public ResponseEntity<Serializable> getOldHealthRecords(@RequestParam String loginId, @RequestAttribute String role){
        if(Constant.isAuthorised(role, Constant.DOCTOR)){
            Serializable obj = serviceForDoctor.getOldHealthRecords(loginId);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(401));
    }

    @PostMapping("/submitHealthRecord")
    public ResponseEntity<Serializable> submitHealthRecord(@RequestBody HrModelForDoc hrModelForDoc, @RequestAttribute String role){
        if(Constant.isAuthorised(role, Constant.DOCTOR)){
            Serializable obj = serviceForDoctor.submitHealthRecord(hrModelForDoc);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(401));
    }

    @GetMapping("/getConsentData")
    public ResponseEntity<Serializable> getConsentData(@RequestParam Integer uhId, @RequestAttribute String role){
        if(Constant.isAuthorised(role, Constant.DOCTOR)){
            Serializable obj = serviceForDoctor.getConsentData(uhId);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(401));
    }

}
