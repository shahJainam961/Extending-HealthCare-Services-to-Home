package com.team9.had.service.blackbox.resetPassword;


import com.team9.had.entity.SecretForResetPassword;
import com.team9.had.repository.SecretRepository;
import com.team9.had.utils.Constant;
import com.team9.had.utils.EncryptDecrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SecretServiceImpl implements SecretService {

    @Autowired
    private SecretRepository secretRepository;
    @Override
    public String getSecret(String loginId) throws Exception {
        String secret = loginId+"#"+(new Date(System.currentTimeMillis()));
        String encryptedSecret = EncryptDecrypt.encrypt(secret, Constant.SECRET_KEY);

        SecretForResetPassword secretForResetPassword = new SecretForResetPassword();
        secretForResetPassword.setSecret(encryptedSecret);
        secretRepository.save(secretForResetPassword);
        return encryptedSecret;
    }
}
