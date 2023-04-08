package com.team9.had.utils;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

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
    String AUTH_TOKEN = "73bed48e0c17d0546fd1589cfa789b7e";
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
}
