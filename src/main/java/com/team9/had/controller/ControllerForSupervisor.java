package com.team9.had.controller;

import com.team9.had.model.sup.SubmitAssignedForSup;
import com.team9.had.service.supervisor.ServiceForSupervisor;
import com.team9.had.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@CrossOrigin("*")
@RequestMapping("/supervisor")
public class ControllerForSupervisor {

    @Autowired
    private ServiceForSupervisor serviceForSupervisor;

    @GetMapping("/getUnassignedCitizens")
    public ResponseEntity<Serializable> getUnassignedCitizens(@RequestParam String loginId, @RequestAttribute String role){
        if(Constant.isAuthorised(role, Constant.SUPERVISOR)){
            Serializable obj = serviceForSupervisor.getUnassignedCitizens(loginId, role);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(401));
    }

    @PostMapping("/submitAssignment")
    public ResponseEntity<Serializable> submitAssignment(@RequestBody  SubmitAssignedForSup submitAssignedForSup, @RequestAttribute String role){
        if(Constant.isAuthorised(role, Constant.SUPERVISOR)){
            Serializable obj = serviceForSupervisor.submitAssignment(submitAssignedForSup);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(401));
    }

//    @GetMapping("/getFhws")
//    public ResponseEntity<Serializable> getFhws(@RequestParam String loginId, @RequestAttribute String role){
//        if(Constant.isAuthorised(role, Constant.SUPERVISOR)){
//            Serializable obj = serviceForSupervisor.getFhws(loginId, role);
//            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
//        }
//        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(401));
//    }
//
//    @PostMapping("/reassign")
//    public ResponseEntity<Serializable> reassign(@RequestBody  ReassignedForSup reassignedForSup, @RequestAttribute String role){
//        if(Constant.isAuthorised(role, Constant.SUPERVISOR)){
//            Serializable obj = serviceForSupervisor.reassign(reassignedForSup);
//            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
//        }
//        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(401));
//    }

}
