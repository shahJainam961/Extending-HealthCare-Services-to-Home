package com.team9.had.controller;

import com.team9.had.Constant;
import com.team9.had.model.FollowUpModel;
import com.team9.had.model.HealthRecordModel;
import com.team9.had.service.fhw.ServiceForFhw;
import com.team9.had.service.fhw.ServiceForFhwImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/fhw")
@CrossOrigin(originPatterns = "*")
public class ControllerForFHW {

    @Autowired
    private ServiceForFhw serviceForFhw;
    @GetMapping("/getAllFollowUp")
    public ResponseEntity<Serializable> getAllFollowUp(@RequestParam String loginId, @RequestAttribute String role){

        if(isValid(role)){
            Serializable obj = serviceForFhw.getAllFollowUp(loginId);
            if(obj != null){
                return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
            }
            else{
                return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
            }
        }
        return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
    }

    @GetMapping("/getPendingFollowUp")
    public ResponseEntity<Serializable> getPendingFollowUp(@RequestParam String loginId, @RequestAttribute String role){

        if(isValid(role)){
            Serializable obj = serviceForFhw.getPendingFollowUp(loginId);
            if(obj != null){
                return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
            }
            else{
                return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
            }
        }
        return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
    }

    @GetMapping("/getBackLoggedFollowUp")
    public ResponseEntity<Serializable> getBackLoggedFollowUp(@RequestParam String loginId, @RequestAttribute String role){

        if(isValid(role)){
            Serializable obj = serviceForFhw.getBackLoggedFollowUp(loginId);
            if(obj != null){
                return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
            }
            else{
                return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
            }
        }
        return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
    }
    @GetMapping("/getDoneFollowUp")
    public ResponseEntity<Serializable> getDoneFollowUp(@RequestParam String loginId, @RequestAttribute String role){

        if(isValid(role)){
            Serializable obj = serviceForFhw.getDoneFollowUp(loginId);
            if(obj != null){
                return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
            }
            else{
                return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
            }
        }
        return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
    }


    @PostMapping("/submitFollowUp")
    public ResponseEntity<Serializable> submitFollowUp(@RequestBody FollowUpModel followUpModel, @RequestAttribute String role){
//        HealthRecordModel healthRecordModel = Constant.getObjectMapper().convertValue(obj1, HealthRecordModel.class);
        if(isValid(role)){
            Serializable obj = serviceForFhw.submitFollowUp(followUpModel);
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
        return role.startsWith(Constant.FIELD_HEALTH_WORKER);
    }
}
