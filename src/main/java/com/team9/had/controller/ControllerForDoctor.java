package com.team9.had.controller;

import com.team9.had.exception.InternalServerError;
import com.team9.had.model.doc.HrModelForDoc;
import com.team9.had.service.doctor.ServiceForDoctor;
import com.team9.had.utils.Constant;
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
    public ResponseEntity<Serializable> getNewHealthRecords(@RequestParam String loginId, @RequestAttribute String role) throws InternalServerError {
        if(Constant.isAuthorised(role, Constant.DOCTOR)){
            Serializable obj = serviceForDoctor.getNewHealthRecords(loginId);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(Constant.HTTP_OK));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_UNAUTHORISED));
    }

    @PostMapping("/submitHealthRecord")
    public ResponseEntity<Serializable> submitHealthRecord(@RequestBody HrModelForDoc hrModelForDoc, @RequestAttribute String role){
        if(Constant.isAuthorised(role, Constant.DOCTOR)){
            Serializable obj = serviceForDoctor.submitHealthRecord(hrModelForDoc);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(Constant.HTTP_OK));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_UNAUTHORISED));
    }

    @GetMapping("/getConsentData")
    public ResponseEntity<Serializable> getConsentData(@RequestParam Integer uhId, @RequestAttribute String role){
        if(Constant.isAuthorised(role, Constant.DOCTOR)){
            Serializable obj = serviceForDoctor.getConsentData(uhId);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(Constant.HTTP_OK));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_UNAUTHORISED));
    }

}
