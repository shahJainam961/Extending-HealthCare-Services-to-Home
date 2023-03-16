package com.team9.had.controller;

import com.team9.had.Constant;
import com.team9.had.config.JwtService;
import com.team9.had.entity.Doctor;
import com.team9.had.entity.FieldHealthWorker;
import com.team9.had.entity.Receptionist;
import com.team9.had.entity.Supervisor;
import com.team9.had.repository.DoctorRepository;
import com.team9.had.service.login.LoginModel;
import com.team9.had.service.login.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;

@RestController
@RequestMapping("/common")
@CrossOrigin(originPatterns = "*")
public class LoginController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<Serializable> loggingIn(@RequestBody LoginModel loginModel, HttpServletResponse httpServletResponse){
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
            Cookie jwtToken = new Cookie("token", authToken);
            jwtToken.setMaxAge(Constant.AGE/Constant.MILLI);
            jwtToken.setPath("/");
            httpServletResponse.addCookie(jwtToken);
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(200));
        }
        else{
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
        }
    }
}
