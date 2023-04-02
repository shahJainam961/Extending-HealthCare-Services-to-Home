package com.team9.had.service.blackbox.getOtp;

import com.team9.had.entity.OTP;
import com.team9.had.exception.UserNotFoundException;

public interface OTPService {

    boolean getOtp(String loginId) throws UserNotFoundException;

    boolean validateOtp(OTP otpModel);

}
