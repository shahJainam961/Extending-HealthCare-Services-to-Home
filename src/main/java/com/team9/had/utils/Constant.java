package com.team9.had.utils;

import com.team9.had.model.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public interface Constant {

    //---------------------------Roles---------------------------------//
    String DOCTOR = "DOC";
    String RECEPTIONIST = "REC";
    String SUPERVISOR = "SUP";
    String FIELD_HEALTH_WORKER = "FHW";
    //-----------------------------------------------------------------//





    //----------------------HealthRecord Status------------------------//
    Integer HEALTH_RECORD_ASSESSED = 1;
    Integer HEALTH_RECORD_NOT_ASSESSED = 0;
    //-----------------------------------------------------------------//





    //---------------------FollowUps Status---------------------------//
    Integer FOLLOW_UP_PENDING = 0;
    Integer FOLLOW_UP_DONE = 1;
    //----------------------------------------------------------------//





    //---------------------Numbers-----------------------------------//
    Integer DAY = 1000*60*60*24; // milliseconds
    Integer OTP_LENGTH = 6;
    Integer OTP_EXPIRATION_TIME = 1000 * 60 * 30;
    //---------------------------------------------------------------//





    //----------------------Twilio Credentials----------------------------//
    String ACCOUNT_SID = "AC4dc6465df71d2b5f60edc0facfa02cd1";
    String AUTH_TOKEN = "42ae716a1df9911917e93b40415599c7";
    //--------------------------------------------------------------------//





    //--------------------Encrypt-Decrypt Secret Key----------------------//
    String SECRET_KEY = "8y/B?E(H+MbQeThWmZq4t6w9z$C&F)J@";
    //-------------------------------------------------------------------//





    //------------------------------JWT Secret Key----------------------------------------------------//
    String JWT_SECRET_KEY = "98hjdsfhhkjfd9qe1j2eufsdf12lskfdldkfn98hjdsfhhkjfd9qe1j2eufsdf12lskfdldkfn";
    //------------------------------------------------------------------------------------------------//





    //-------------------------------------HTTP STATUSES-------------------------------------------//
    Integer HTTP_OK = 200;
    Integer HTTP_INTERNAL_SERVER_ERROR = 500;
    Integer HTTP_UNAUTHORISED = 403;
    Integer HTTP_UNAUTHENTICATED = 401;
    Integer HTTP_BAD_REQUEST = 400;
    Integer HTTP_404 = 404;
    //---------------------------------------------------------------------------------------------//





    // Password Encoder
    static PasswordEncoder passwordEncode(){
        return new BCryptPasswordEncoder();
    }




    // For Mapping Models to Entity and vice versa
    static ModelMapper getModelMapper(){
        return new ModelMapper();
    }





    // For Authorisation purpose
    static boolean isAuthorised(String actualRole, String expectedRole){
        return actualRole.startsWith(expectedRole);
    }




    // Empty Array-List
    ArrayList<Object> EMPTY = new ArrayList<>();





    // Generating OTPs
    static String generateOtp() {
        String numbers = "0123456789";
        String otp = "";
        for(int i = 0; i < OTP_LENGTH; i++)
            otp += numbers.charAt(new Random().nextInt(numbers.length()));
        return otp;
    }




    //---------------------------------------Headers-------------------------------//
    String FORGOT_PASSWORD_SECRET = "secret";
    String JWT_AUTH_HEADER = "Authorization";
    String JWT_TOKEN_PREFIX = "Bearer ";
    String ROLE_HEADER = "role";
    //-----------------------------------------------------------------------------//




    //-------------------------------------------Strings---------------------------//
    String MOBILE_PREFIX = "+91";
    String RESOURCE_NOT_FOUND_MSG = "Resource Not Found!!";
    String BAD_REQUEST_MSG = "Bad Request";
    Character HASH = '#';
    String BAD_CREDS = "Invalid Credentials!!";
    //----------------------------------------------------------------------------//



    //---------------------------------------OTPStatuses-----------------------------//
    String OTP_VALID = "0";
    String OTP_EXPIRED = "2";
    String OTP_INVALID = "1";
    //--------------------------------------------------------------------------------//



    //--------------Encrypting and Decrypting PII information------------------------//

    static void encryptPII(Citizen citizen) throws Exception {
        if(citizen.getUhId()!=null)
            citizen.setUhId(citizen.getUhId());
        if(citizen.getCity()!=null)
            citizen.setCity(EncryptDecrypt.encrypt(citizen.getCity(), Constant.SECRET_KEY));
        if(citizen.getDistrict()!=null)
            citizen.setDistrict(EncryptDecrypt.encrypt(citizen.getDistrict(), Constant.SECRET_KEY));
        if(citizen.getDob()!=null)
            citizen.setDob(citizen.getDob());
        if(citizen.getFname()!=null)
            citizen.setFname(EncryptDecrypt.encrypt(citizen.getFname(), Constant.SECRET_KEY));
        citizen.setGender(citizen.getGender());
        if(citizen.getGovId()!=null)
            citizen.setGovId(EncryptDecrypt.encrypt(citizen.getGovId(), Constant.SECRET_KEY));
        if(citizen.getLname()!=null)
            citizen.setLname(EncryptDecrypt.encrypt(citizen.getLname(), Constant.SECRET_KEY));
        if(citizen.getMobileNo()!=null)
            citizen.setMobileNo(EncryptDecrypt.encrypt(citizen.getMobileNo(), Constant.SECRET_KEY));
        if(citizen.getPincode()!=null)
            citizen.setPincode(EncryptDecrypt.encrypt(citizen.getPincode(), Constant.SECRET_KEY));
        if(citizen.getState()!=null)
            citizen.setState(EncryptDecrypt.encrypt(citizen.getState(), Constant.SECRET_KEY));
        if(citizen.getStreet1()!=null)
            citizen.setStreet1(EncryptDecrypt.encrypt(citizen.getStreet1(), Constant.SECRET_KEY));
        if(citizen.getFieldHealthWorker()!=null)
            citizen.setFieldHealthWorker(citizen.getFieldHealthWorker());
    }
    static void decryptPII(Citizen citizen) throws Exception {
        if(citizen.getUhId()!=null)
            citizen.setUhId(citizen.getUhId());
        if(citizen.getCity()!=null)
            citizen.setCity(EncryptDecrypt.decrypt(citizen.getCity(), Constant.SECRET_KEY));
        if(citizen.getDistrict()!=null)
            citizen.setDistrict(EncryptDecrypt.decrypt(citizen.getDistrict(), Constant.SECRET_KEY));
        if(citizen.getDob()!=null)
            citizen.setDob(citizen.getDob());
        if(citizen.getFname()!=null)
            citizen.setFname(EncryptDecrypt.decrypt(citizen.getFname(), Constant.SECRET_KEY));
        citizen.setGender(citizen.getGender());
        if(citizen.getGovId()!=null)
            citizen.setGovId(EncryptDecrypt.decrypt(citizen.getGovId(), Constant.SECRET_KEY));
        if(citizen.getLname()!=null)
            citizen.setLname(EncryptDecrypt.decrypt(citizen.getLname(), Constant.SECRET_KEY));
        if(citizen.getMobileNo()!=null)
            citizen.setMobileNo(EncryptDecrypt.decrypt(citizen.getMobileNo(), Constant.SECRET_KEY));
        if(citizen.getPincode()!=null)
            citizen.setPincode(EncryptDecrypt.decrypt(citizen.getPincode(), Constant.SECRET_KEY));
        if(citizen.getState()!=null)
            citizen.setState(EncryptDecrypt.decrypt(citizen.getState(), Constant.SECRET_KEY));
        if(citizen.getStreet1()!=null)
            citizen.setStreet1(EncryptDecrypt.decrypt(citizen.getStreet1(), Constant.SECRET_KEY));
        if(citizen.getFieldHealthWorker()!=null)
            citizen.setFieldHealthWorker(citizen.getFieldHealthWorker());
    }
    //--------------------------------------------------------------------------------//



    //------------------------------------------------------------------------------------//
    static void getDecryptedHealthRecord(HealthRecord healthRecord, Set<Citizen> citizenSet) throws Exception {
        if(!citizenSet.contains(healthRecord.getCitizen())){
            decryptPII(healthRecord.getCitizen());
            citizenSet.add(healthRecord.getCitizen());
        }
        if(healthRecord.getDoctor()!=null)
            getDecryptedDoctor(healthRecord.getDoctor(), citizenSet);
        if(healthRecord.getReceptionist()!=null)
            getDecryptedReceptionist(healthRecord.getReceptionist(), citizenSet);
        if(healthRecord.getSupervisor()!=null)
            getDecryptedSupervisor(healthRecord.getSupervisor(), citizenSet);
        if(healthRecord.getFieldHealthWorker()!=null)
            getDecryptedFieldHealthWorker(healthRecord.getFieldHealthWorker(), citizenSet);
    }

    static void getDecryptedCitizen(Citizen citizen, Set<Citizen> citizenSet) throws Exception {
        if(citizen==null) return;
        if(citizenSet.contains(citizen)) return;
        decryptPII(citizen);
        citizenSet.add(citizen);
    }

    static void getDecryptedDoctor(Doctor doctor, Set<Citizen> citizenSet) throws Exception {
        if(doctor==null) return;
        if(citizenSet.contains(doctor.getCitizen())) return;
        decryptPII(doctor.getCitizen());
        citizenSet.add(doctor.getCitizen());
    }

    static void getDecryptedFieldHealthWorker(FieldHealthWorker fieldHealthWorker, Set<Citizen> citizenSet) throws Exception {
        if(fieldHealthWorker==null) return;
        if(citizenSet.contains(fieldHealthWorker.getCitizen())) return;
        decryptPII(fieldHealthWorker.getCitizen());
        citizenSet.add(fieldHealthWorker.getCitizen());
    }

    static void getDecryptedSupervisor(Supervisor supervisor, Set<Citizen> citizenSet) throws Exception {
        if(supervisor==null) return;
        if(citizenSet.contains(supervisor.getCitizen())) return;
        decryptPII(supervisor.getCitizen());
        citizenSet.add(supervisor.getCitizen());
    }

    static void getDecryptedReceptionist(Receptionist receptionist, Set<Citizen> citizenSet) throws Exception {
        if(receptionist==null) return;
        if(citizenSet.contains(receptionist.getCitizen())) return;
        decryptPII(receptionist.getCitizen());
        citizenSet.add(receptionist.getCitizen());
    }

    //---------------------------------------------------------------------------------------//
}
