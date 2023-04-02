package com.team9.had.repository;

import com.team9.had.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPRepository extends JpaRepository<OTP, String> {

    OTP findByLoginId(String loginId);

}
