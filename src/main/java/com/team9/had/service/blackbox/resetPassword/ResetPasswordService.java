package com.team9.had.service.blackbox.resetPassword;

import com.team9.had.model.LoginModel;
import jakarta.servlet.http.HttpServletRequest;

public interface ResetPasswordService {
    boolean resetPassword(LoginModel loginModel, HttpServletRequest httpServletRequest) throws Exception;
}
