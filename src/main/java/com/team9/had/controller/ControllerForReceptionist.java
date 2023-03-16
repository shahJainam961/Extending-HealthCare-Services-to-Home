package com.team9.had.controller;

import com.team9.had.Constant;
import com.team9.had.entity.HealthRecord;
import com.team9.had.service.receptionist.ServiceForReceptionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/receptionist")
@CrossOrigin(originPatterns = "*")
public class ControllerForReceptionist {

    @Autowired
    private ServiceForReceptionist serviceForReceptionist;
    @PostMapping("/createHealthRecord")
    public ResponseEntity<Serializable> createHealthRecord(@RequestBody HealthRecord healthRecord,@RequestAttribute String role){
        if(role.startsWith(Constant.RECEPTIONIST)){
            Serializable obj = serviceForReceptionist.createHealthRecord(healthRecord);
            if(obj != null){
                return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
            }
            else{
                return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
            }
        }
        return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));

    }
}
