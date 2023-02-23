package com.team9.had.controller;

import com.team9.had.entity.HealthRecord;
import com.team9.had.service.receptionist.ServiceForReceptionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/receptionist")
public class ControllerForReceptionist {

    @Autowired
    private ServiceForReceptionist serviceForReceptionist;
    @PostMapping("/createHealthRecord")
    public ResponseEntity<Serializable> createHealthRecord(@RequestBody HealthRecord healthRecord){
        Serializable obj = serviceForReceptionist.createHealthRecord(healthRecord);
        if(obj != null){
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
        }
        else{
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
        }
    }
}
