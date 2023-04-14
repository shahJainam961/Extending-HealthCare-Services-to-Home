package com.team9.had.service.blackbox.OtpService;

import com.team9.had.exception.ResourceNotFoundException;
import com.team9.had.model.OTP;

public interface OTPService {

    boolean getOtp(String loginId) throws ResourceNotFoundException;

    String validateOtp(OTP otpModel);

}
