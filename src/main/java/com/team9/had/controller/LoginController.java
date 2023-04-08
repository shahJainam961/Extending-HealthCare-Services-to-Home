package com.team9.had.controller;

import com.team9.had.config.JwtService;
import com.team9.had.entity.Doctor;
import com.team9.had.entity.FieldHealthWorker;
import com.team9.had.entity.Receptionist;
import com.team9.had.entity.Supervisor;
import com.team9.had.exception.BadCredentialException;
import com.team9.had.exception.UserNotFoundException;
import com.team9.had.model.LoginModel;
import com.team9.had.service.login.LoginService;
import com.team9.had.utils.Constant;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/common")
@CrossOrigin(originPatterns = "*", exposedHeaders = "*")
public class LoginController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<Serializable> loggingIn(@RequestBody LoginModel loginModel, HttpServletResponse httpServletResponse) throws UserNotFoundException, BadCredentialException {
        Serializable obj = loginService.loggingIn(loginModel);
        String loginId = loginModel.getLoginId();
        String authToken = null;
        if(obj != null){
            if(loginId.startsWith(Constant.RECEPTIONIST)){
                Receptionist userDetails = new Receptionist();
                userDetails.setLoginId(loginId);
                authToken = jwtService.generateToken(userDetails);
            }
            else if(loginId.startsWith(Constant.DOCTOR)){
                Doctor userDetails = new Doctor();
                userDetails.setLoginId(loginId);
                authToken = jwtService.generateToken(userDetails);
            }
            else if(loginId.startsWith(Constant.SUPERVISOR)){
                Supervisor userDetails = new Supervisor();
                userDetails.setLoginId(loginId);
                authToken = jwtService.generateToken(userDetails);
            }
            else if(loginId.startsWith(Constant.FIELD_HEALTH_WORKER)){
                FieldHealthWorker userDetails = new FieldHealthWorker();
                userDetails.setLoginId(loginId);
                authToken = jwtService.generateToken(userDetails);
            }
            httpServletResponse.setHeader("token", authToken);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(Constant.HTTP_OK));
        }
        else{
            return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_UNAUTHENTICATED));
        }
    }
}
