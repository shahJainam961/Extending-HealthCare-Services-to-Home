package com.team9.had.service.login;

import com.team9.had.customModel.LoginModel;
import com.team9.had.customModel.doc.DocModelForDoc;
import com.team9.had.customModel.fhw.FhwModelForFhw;
import com.team9.had.customModel.rec.DocModelForRec;
import com.team9.had.customModel.rec.RecModelForRec;
import com.team9.had.customModel.sup.SupModelForSup;
import com.team9.had.exception.BadCredentialException;
import com.team9.had.exception.BadRequestException;
import com.team9.had.exception.ResourceNotFoundException;
import com.team9.had.model.Doctor;
import com.team9.had.model.FieldHealthWorker;
import com.team9.had.model.Receptionist;
import com.team9.had.model.Supervisor;
import com.team9.had.repository.DoctorRepository;
import com.team9.had.repository.FieldHealthWorkerRepository;
import com.team9.had.repository.ReceptionistRepository;
import com.team9.had.repository.SupervisorRepository;
import com.team9.had.utils.Constant;
import com.team9.had.utils.V;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private SupervisorRepository supervisorRepository;
    @Autowired
    private ReceptionistRepository receptionistRepository;
    @Autowired
    private FieldHealthWorkerRepository fieldHealthWorkerRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Serializable loggingIn(LoginModel loginModel) throws Exception {

        if(!V.validateLoginModel(loginModel)) throw new BadRequestException(Constant.BAD_REQUEST_MSG);

        String loginId = loginModel.getLoginId().trim();
        String password = loginModel.getPassword();

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginId, password
                    )
            );
        }
        catch(Exception e){
            System.out.println("e = " + e);
            throw new BadCredentialException(Constant.BAD_CREDS);
        }


        if(loginId.startsWith(Constant.DOCTOR)){
            Doctor doctor = doctorRepository.findByLoginId(loginId);
            if(doctor==null) throw new ResourceNotFoundException(Constant.RESOURCE_NOT_FOUND_MSG);
            doctor = Constant.getDecryptedDoctor(doctor);

            ArrayList<Object> obj = new ArrayList<>();
            DocModelForDoc docModelForDoc = Constant.getModelMapper().map(doctor, DocModelForDoc.class);
            obj.add(docModelForDoc);
            return obj;
        }

        else if(loginId.startsWith(Constant.RECEPTIONIST)){
            Receptionist receptionist = receptionistRepository.findByLoginId(loginId);
            if(receptionist==null) throw new ResourceNotFoundException(Constant.RESOURCE_NOT_FOUND_MSG);
            receptionist = Constant.getDecryptedReceptionist(receptionist);

            Integer hospitalId = receptionist.getHospital().getHospId();
            ArrayList<Doctor> doctors = doctorRepository.findAllByHospital_HospId(hospitalId);
            ArrayList<DocModelForRec> docModelForRecs = new ArrayList<>();
            ArrayList<Object> obj = new ArrayList<>();
            obj.add(Constant.getModelMapper().map(receptionist, RecModelForRec.class));
            for(Doctor doctor : doctors){
                try {
                    doctor = Constant.getDecryptedDoctor(doctor);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                docModelForRecs.add(Constant.getModelMapper().map(doctor, DocModelForRec.class));
            }
            obj.add(docModelForRecs);
            return obj;
        }
        else if(loginId.startsWith(Constant.SUPERVISOR)){
            Supervisor supervisor = supervisorRepository.findByLoginId(loginId);
            if(supervisor==null) throw new ResourceNotFoundException(Constant.RESOURCE_NOT_FOUND_MSG);
            supervisor = Constant.getDecryptedSupervisor(supervisor);

            ArrayList<Object> obj = new ArrayList<>();
            SupModelForSup supModelForSup = Constant.getModelMapper().map(supervisor, SupModelForSup.class);
            obj.add(supModelForSup);
            return obj;
        }
        else if(loginId.startsWith(Constant.FIELD_HEALTH_WORKER)){
            FieldHealthWorker fieldHealthWorker = fieldHealthWorkerRepository.findByLoginId(loginId);
            if(fieldHealthWorker==null) throw new ResourceNotFoundException(Constant.RESOURCE_NOT_FOUND_MSG);
            fieldHealthWorker = Constant.getDecryptedFieldHealthWorker(fieldHealthWorker);

            ArrayList<Object> obj = new ArrayList<>();
            FhwModelForFhw fhwModelForFhw = Constant.getModelMapper().map(fieldHealthWorker, FhwModelForFhw.class);
            obj.add(fhwModelForFhw);
            return obj;
        }
        else return null;
    }
}
