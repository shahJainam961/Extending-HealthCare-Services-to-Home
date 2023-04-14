package com.team9.had.controller;

import com.team9.had.customModel.LoginModel;
import com.team9.had.exception.ResourceNotFoundException;
import com.team9.had.model.*;
import com.team9.had.service.blackbox.OtpService.OTPService;
import com.team9.had.service.blackbox.addCitizen.CitizenService;
import com.team9.had.service.blackbox.addCity.CityService;
import com.team9.had.service.blackbox.addDistrict.DistrictService;
import com.team9.had.service.blackbox.addDoctor.DoctorService;
import com.team9.had.service.blackbox.addFieldHealthWorker.FieldHealthWorkerService;
import com.team9.had.service.blackbox.addHospital.HospitalService;
import com.team9.had.service.blackbox.addReceptionist.ReceptionistService;
import com.team9.had.service.blackbox.addState.StateService;
import com.team9.had.service.blackbox.addSupervisor.SupervisorService;
import com.team9.had.service.blackbox.jobForScheduler.SendSmsForFollowUps;
import com.team9.had.service.blackbox.resetPassword.ResetPasswordService;
import com.team9.had.service.blackbox.resetPassword.SecretService;
import com.team9.had.utils.Constant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blackbox")
@CrossOrigin(originPatterns = "*", exposedHeaders = "*")
public class BlackboxController {

    @Autowired
    private CitizenService citizenService;
    @Autowired
    private FieldHealthWorkerService fieldHealthWorkerService;
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ReceptionistService receptionistService;

    @Autowired
    private SupervisorService supervisorService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private StateService stateService;
    @Autowired
    private CityService cityService;
    @Autowired
    private DistrictService districtService;

    @Autowired
    private SendSmsForFollowUps sendSmsForFollowUps;

    @Autowired
    private OTPService otpService;
    @Autowired
    private ResetPasswordService resetPasswordService;

    @Autowired
    private SecretService secretService;

    @PostMapping("/addCitizen")
    public ResponseEntity<String> addCitizen(@RequestBody Citizen citizen){
        if(citizenService.addCitizen(citizen)){
            return new ResponseEntity<>("Citizen added Successfully!!", HttpStatus.OK);
        }
        return  new ResponseEntity<>("Something went wrong!!", HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }

    @PostMapping("/addCitizens")
    public ResponseEntity<String> addCitizens(@RequestBody List<Citizen> citizens){
        if(citizenService.addCitizens(citizens)){
            return new ResponseEntity<>("Citizens added Successfully!!", HttpStatus.OK);
        }
        return  new ResponseEntity<>("Something went wrong!!", HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }

    @PostMapping("/addHospital")
    public ResponseEntity<String> addHospital(@RequestBody Hospital hospital){
        if(hospitalService.addHospital(hospital)){
            return new ResponseEntity<>("Hospital added Successfully!!", HttpStatus.OK);
        }
        return  new ResponseEntity<>("Something went wrong!!", HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }

    @PostMapping("/addHospitals")
    public ResponseEntity<String> addHospitals(@RequestBody List<Hospital> hospitals){
        if(hospitalService.addHospitals(hospitals)){
            return new ResponseEntity<>("Hospitals added Successfully!!", HttpStatus.OK);
        }
        return  new ResponseEntity<>("Something went wrong!!", HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor){
        if(doctorService.addDoctor(doctor)){
            return new ResponseEntity<>("Doctor added Successfully!!", HttpStatus.OK);
        }
        return  new ResponseEntity<>("Something went wrong!!", HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }

    @PostMapping("/addDoctors")
    public ResponseEntity<String> addDoctors(@RequestBody List<Doctor> doctors){
        if(doctorService.addDoctors(doctors)){
            return new ResponseEntity<>("Doctors added Successfully!!", HttpStatus.OK);
        }
        return  new ResponseEntity<>("Something went wrong!!", HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }

    @PostMapping("/addReceptionist")
    public ResponseEntity<String> addReceptionist(@RequestBody Receptionist receptionist){
        if(receptionistService.addReceptionist(receptionist)){
            return new ResponseEntity<>("Receptionist added Successfully!!", HttpStatus.OK);
        }
        return  new ResponseEntity<>("Something went wrong!!", HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }

    @PostMapping("/addReceptionists")
    public ResponseEntity<String> addReceptionists(@RequestBody List<Receptionist> receptionists){
        if(receptionistService.addReceptionists(receptionists)){
            return new ResponseEntity<>("Receptionists added Successfully!!", HttpStatus.OK);
        }
        return  new ResponseEntity<>("Something went wrong!!", HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }

    @PostMapping("/addFhw")
    public ResponseEntity<String> addFhw(@RequestBody FieldHealthWorker fieldHealthWorker){
        if(fieldHealthWorkerService.addFhw(fieldHealthWorker)){
            return new ResponseEntity<>("FHW added Successfully!!", HttpStatus.OK);
        }
        return  new ResponseEntity<>("Something went wrong!!", HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }

    @PostMapping("/addFhws")
    public ResponseEntity<String> addFhws(@RequestBody List<FieldHealthWorker> fieldHealthWorkers){
        if(fieldHealthWorkerService.addFhws(fieldHealthWorkers)){
            return new ResponseEntity<>("FHWs added Successfully!!", HttpStatus.OK);
        }
        return  new ResponseEntity<>("Something went wrong!!", HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }

    @PostMapping("/addSupervisor")
    public ResponseEntity<String> addSupervisor(@RequestBody Supervisor supervisor){
        if(supervisorService.addSupervisor(supervisor)){
            return new ResponseEntity<>("Supervisor added Successfully!!", HttpStatus.OK);
        }
        return  new ResponseEntity<>("Something went wrong!!", HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }

    @PostMapping("/addSupervisors")
    public ResponseEntity<String> addSupervisors(@RequestBody List<Supervisor> supervisors){
        if(supervisorService.addSupervisors(supervisors)){
            return new ResponseEntity<>("Supervisors added Successfully!!", HttpStatus.OK);
        }
        return  new ResponseEntity<>("Something went wrong!!", HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }


    @PostMapping("/addStates")
    public ResponseEntity<String> addStates(@RequestBody ArrayList<State> states){
        if(stateService.addStates(states)){
            return new ResponseEntity<>("States added successfully!!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong!!", HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }

    @PostMapping("/addDistricts")
    public ResponseEntity<String> addDistricts(@RequestBody ArrayList<District> districts){
        if(districtService.addDistricts(districts)){
            return new ResponseEntity<>("Districts added successfully!!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong!!", HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }

    @PostMapping("/addCities")
    public ResponseEntity<String> addCities(@RequestBody ArrayList<City> cities){
        if(cityService.addCities(cities)){
            return new ResponseEntity<>("Cities added successfully!!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong!!", HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }

//    @Scheduled(cron = "0 * * * * *")
    @GetMapping("/f")
    public void f(){
        System.out.println("===========================================================================================");
        sendSmsForFollowUps.sendMessage();
        System.out.println("===========================================================================================");
    }

    @GetMapping("/getOtp")
    public ResponseEntity<Serializable> getOtp(@RequestParam String loginId) throws ResourceNotFoundException {
        if(otpService.getOtp(loginId)){
            return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_OK));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_INTERNAL_SERVER_ERROR));
    }

    @PostMapping("/validateOtp")
    public ResponseEntity<Serializable> validateOtp(@RequestBody OTP otpModel, HttpServletResponse httpServletResponse) throws Exception {
        String status = otpService.validateOtp(otpModel);
        if(status.equals(Constant.OTP_VALID)){
            String encryptedSecret = secretService.getSecret(otpModel.getLoginId());
            httpServletResponse.setHeader(Constant.FORGOT_PASSWORD_SECRET, encryptedSecret);
            return new ResponseEntity<>(status, HttpStatusCode.valueOf(Constant.HTTP_OK));
        }
        return new ResponseEntity<>(status, HttpStatusCode.valueOf(Constant.HTTP_OK));
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<Serializable> resetPassword(@RequestBody LoginModel loginModel, HttpServletRequest httpServletRequest) throws Exception {
        if(resetPasswordService.resetPassword(loginModel, httpServletRequest)){
            return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_OK));
        }
        return new ResponseEntity<>(Constant.EMPTY, HttpStatusCode.valueOf(Constant.HTTP_UNAUTHENTICATED));
    }
}
