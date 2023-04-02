package com.team9.had.repository;

import com.team9.had.entity.SecretForResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretRepository extends JpaRepository<SecretForResetPassword, Integer> {
    SecretForResetPassword findBySecret(String secret);
}
