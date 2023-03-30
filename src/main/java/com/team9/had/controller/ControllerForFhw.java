package com.team9.had.controller;

import com.team9.had.Constant;
import com.team9.had.model.fhw.ModelForFhw;
import com.team9.had.service.fhw.ServiceForFhw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@CrossOrigin("*")
@RequestMapping("/fhw")
public class ControllerForFhw {

    @Autowired
    private ServiceForFhw serviceForFhw;

    @PostMapping("/sync")
    public ResponseEntity<Serializable> sync(@RequestBody ModelForFhw modelForFhw, @RequestAttribute String role){
        if(Constant.isAuthorised(role, Constant.FIELD_HEALTH_WORKER)){
            Serializable obj = serviceForFhw.sync(modelForFhw, role);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(401));
    }


}
