package com.team9.had.controller;

import com.team9.had.customModel.sup.ReassignedForSup;
import com.team9.had.customModel.sup.SubmitAssignedForSup;
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
    public ResponseEntity<Serializable> getUnassignedCitizens(@RequestParam String loginId, @RequestAttribute String role) throws Exception{
        if(Constant.isAuthorised(role, Constant.SUPERVISOR)){
            Serializable obj = serviceForSupervisor.getUnassignedCitizens(loginId, role);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(Constant.HTTP_OK));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_UNAUTHORISED));
    }

    @PostMapping("/submitAssignment")
    public ResponseEntity<Serializable> submitAssignment(@RequestBody  SubmitAssignedForSup submitAssignedForSup, @RequestAttribute String role) throws Exception{
        if(Constant.isAuthorised(role, Constant.SUPERVISOR)){
            if(serviceForSupervisor.submitAssignment(submitAssignedForSup))
                return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_OK));
            else
                return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_UNAUTHORISED));
    }

    @GetMapping("/getFhws")
    public ResponseEntity<Serializable> getFhws(@RequestParam String loginId, @RequestAttribute String role) throws Exception{
        if(Constant.isAuthorised(role, Constant.SUPERVISOR)){
            Serializable obj = serviceForSupervisor.getFhws(loginId, role);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(Constant.HTTP_OK));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_UNAUTHORISED));
    }

    @PostMapping("/reassign")
    public ResponseEntity<Serializable> reassign(@RequestBody ReassignedForSup reassignedForSup, @RequestAttribute String role) throws Exception{
        if(Constant.isAuthorised(role, Constant.SUPERVISOR)){
            Serializable obj = serviceForSupervisor.reassign(reassignedForSup);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(Constant.HTTP_OK));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_UNAUTHORISED));
    }

    @GetMapping("/stats")
    public ResponseEntity<Serializable> stats(@RequestParam String loginId, @RequestAttribute String role) throws Exception{
        if(Constant.isAuthorised(role, Constant.SUPERVISOR)){
            Serializable obj = serviceForSupervisor.stats(loginId);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(Constant.HTTP_OK));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_UNAUTHORISED));
    }

}
