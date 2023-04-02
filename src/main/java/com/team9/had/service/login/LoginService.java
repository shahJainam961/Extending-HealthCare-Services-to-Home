package com.team9.had.service.login;

import com.team9.had.exception.UserNotFoundException;
import com.team9.had.model.LoginModel;

import java.io.Serializable;

public interface LoginService {
    Serializable loggingIn(LoginModel loginModel) throws UserNotFoundException;
}
