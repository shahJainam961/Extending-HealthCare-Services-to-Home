package com.team9.had.service.blackbox.OtpService;

import com.team9.had.entity.OTP;
import com.team9.had.exception.UserNotFoundException;

public interface OTPService {

    boolean getOtp(String loginId) throws UserNotFoundException;

    String validateOtp(OTP otpModel);

}
