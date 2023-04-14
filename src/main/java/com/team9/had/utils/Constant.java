package com.team9.had.utils;

import com.team9.had.entity.Citizen;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Random;

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
    String AUTH_TOKEN = "b5e6209098fbe4bff5ba6d2055fd0d85";
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
    String USER_NOT_FOUND_MSG = "User Not Found!!";
    Character HASH = '#';
    //----------------------------------------------------------------------------//



    //---------------------------------------OTPStatuses-----------------------------//
    String OTP_VALID = "0";
    String OTP_EXPIRED = "2";
    String OTP_INVALID = "1";
    //--------------------------------------------------------------------------------//



    //--------------Encrypting and Decrypting PII information------------------------//

    static Citizen encryptPII(Citizen decryptedCitizen) throws Exception {
        Citizen encryptedCitizen = new Citizen();
        if(decryptedCitizen.getUhId()!=null)
            encryptedCitizen.setUhId(decryptedCitizen.getUhId());
        if(decryptedCitizen.getCity()!=null)
            encryptedCitizen.setCity(EncryptDecrypt.encrypt(decryptedCitizen.getCity(), Constant.SECRET_KEY));
        if(decryptedCitizen.getDistrict()!=null)
            encryptedCitizen.setDistrict(EncryptDecrypt.encrypt(decryptedCitizen.getDistrict(), Constant.SECRET_KEY));
        if(decryptedCitizen.getDob()!=null)
            encryptedCitizen.setDob(decryptedCitizen.getDob());
        if(decryptedCitizen.getFname()!=null)
            encryptedCitizen.setFname(EncryptDecrypt.encrypt(decryptedCitizen.getFname(), Constant.SECRET_KEY));
        if(decryptedCitizen.getGender()=='\0')
            encryptedCitizen.setGender(decryptedCitizen.getGender());
        if(decryptedCitizen.getGovId()!=null)
            encryptedCitizen.setGovId(EncryptDecrypt.encrypt(decryptedCitizen.getGovId(), Constant.SECRET_KEY));
        if(decryptedCitizen.getLname()!=null)
            encryptedCitizen.setLname(EncryptDecrypt.encrypt(decryptedCitizen.getLname(), Constant.SECRET_KEY));
        if(decryptedCitizen.getMobileNo()!=null)
            encryptedCitizen.setMobileNo(EncryptDecrypt.encrypt(decryptedCitizen.getMobileNo(), Constant.SECRET_KEY));
        if(decryptedCitizen.getPincode()!=null)
            encryptedCitizen.setPincode(EncryptDecrypt.encrypt(decryptedCitizen.getPincode(), Constant.SECRET_KEY));
        if(decryptedCitizen.getState()!=null)
            encryptedCitizen.setState(EncryptDecrypt.encrypt(decryptedCitizen.getState(), Constant.SECRET_KEY));
        if(decryptedCitizen.getStreet1()!=null)
            encryptedCitizen.setStreet1(EncryptDecrypt.encrypt(decryptedCitizen.getStreet1(), Constant.SECRET_KEY));
        if(decryptedCitizen.getFieldHealthWorker()!=null)
            encryptedCitizen.setFieldHealthWorker(decryptedCitizen.getFieldHealthWorker());

        return encryptedCitizen;
    }
    static Citizen decryptPII(Citizen encryptedCitizen) throws Exception {
        Citizen decryptedCitizen = new Citizen();
        if(encryptedCitizen.getUhId()!=null)
            decryptedCitizen.setUhId(encryptedCitizen.getUhId());
        if(encryptedCitizen.getCity()!=null)
            decryptedCitizen.setCity(EncryptDecrypt.decrypt(encryptedCitizen.getCity(), Constant.SECRET_KEY));
        if(encryptedCitizen.getDistrict()!=null)
            decryptedCitizen.setDistrict(EncryptDecrypt.decrypt(encryptedCitizen.getDistrict(), Constant.SECRET_KEY));
        if(encryptedCitizen.getDob()!=null)
            decryptedCitizen.setDob(encryptedCitizen.getDob());
        if(encryptedCitizen.getFname()!=null)
            decryptedCitizen.setFname(EncryptDecrypt.decrypt(encryptedCitizen.getFname(), Constant.SECRET_KEY));
        if(encryptedCitizen.getGender()=='\0')
            decryptedCitizen.setGender(encryptedCitizen.getGender());
        if(encryptedCitizen.getGovId()!=null)
            decryptedCitizen.setGovId(EncryptDecrypt.decrypt(encryptedCitizen.getGovId(), Constant.SECRET_KEY));
        if(encryptedCitizen.getLname()!=null)
            decryptedCitizen.setLname(EncryptDecrypt.decrypt(encryptedCitizen.getLname(), Constant.SECRET_KEY));
        if(encryptedCitizen.getMobileNo()!=null)
            decryptedCitizen.setMobileNo(EncryptDecrypt.decrypt(encryptedCitizen.getMobileNo(), Constant.SECRET_KEY));
        if(encryptedCitizen.getPincode()!=null)
            decryptedCitizen.setPincode(EncryptDecrypt.decrypt(encryptedCitizen.getPincode(), Constant.SECRET_KEY));
        if(encryptedCitizen.getState()!=null)
            decryptedCitizen.setState(EncryptDecrypt.decrypt(encryptedCitizen.getState(), Constant.SECRET_KEY));
        if(encryptedCitizen.getStreet1()!=null)
            decryptedCitizen.setStreet1(EncryptDecrypt.decrypt(encryptedCitizen.getStreet1(), Constant.SECRET_KEY));
        if(encryptedCitizen.getFieldHealthWorker()!=null)
            decryptedCitizen.setFieldHealthWorker(encryptedCitizen.getFieldHealthWorker());

        return decryptedCitizen;
    }
    //--------------------------------------------------------------------------------//
}
