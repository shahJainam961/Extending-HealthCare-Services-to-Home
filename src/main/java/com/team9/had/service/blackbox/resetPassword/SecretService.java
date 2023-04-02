package com.team9.had.service.blackbox.resetPassword;

public interface SecretService {
    String getSecret(String loginId) throws Exception;
}
