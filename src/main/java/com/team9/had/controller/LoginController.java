package com.team9.had.controller;

import com.team9.had.service.login.LoginModel;
import com.team9.had.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;

@RestController
@RequestMapping("/common")
@CrossOrigin(originPatterns = "*")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<Serializable> loggingIn(@RequestBody LoginModel loginModel){
        Serializable obj = loginService.loggingIn(loginModel);
        if(obj != null){
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
        }
        else{
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
        }
    }
}
