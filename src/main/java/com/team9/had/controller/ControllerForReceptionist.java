package com.team9.had.controller;

import com.team9.had.exception.UserNotFoundException;
import com.team9.had.model.rec.HrModelForRec;
import com.team9.had.service.receptionist.ServiceForReceptionist;
import com.team9.had.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/receptionist")
@CrossOrigin("*")
public class ControllerForReceptionist {

    @Autowired
    private ServiceForReceptionist serviceForReceptionist;

    @PostMapping("/createHealthRecord")
    public ResponseEntity<Serializable> createHealthRecord(@RequestBody HrModelForRec hrModelForRec, @RequestAttribute String role) throws UserNotFoundException {
        if(Constant.isAuthorised(role, Constant.RECEPTIONIST)){
            Serializable obj = serviceForReceptionist.createHealthRecord(hrModelForRec, role);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(401));
    }

    @GetMapping("/confirmation")
    public ResponseEntity<Serializable> confirmation(@RequestParam Integer uhId, @RequestAttribute String role) throws UserNotFoundException {

        if(Constant.isAuthorised(role, Constant.RECEPTIONIST)){
            Serializable obj = serviceForReceptionist.confirmation(uhId);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(401));
    }

}
