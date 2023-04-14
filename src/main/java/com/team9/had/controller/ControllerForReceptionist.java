package com.team9.had.controller;

import com.team9.had.customModel.rec.HrModelForRec;
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
    public ResponseEntity<Serializable> createHealthRecord(@RequestBody HrModelForRec hrModelForRec, @RequestAttribute String role) throws Exception {
        if(Constant.isAuthorised(role, Constant.RECEPTIONIST)){
            if(serviceForReceptionist.createHealthRecord(hrModelForRec, role))
                return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_OK));
            else
                return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_UNAUTHORISED));
    }

    @GetMapping("/confirmation")
    public ResponseEntity<Serializable> confirmation(@RequestParam Integer uhId, @RequestAttribute String role) throws Exception {
        if(Constant.isAuthorised(role, Constant.RECEPTIONIST)){
            Serializable obj = serviceForReceptionist.confirmation(uhId);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(Constant.HTTP_OK));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_UNAUTHORISED));
    }

}
