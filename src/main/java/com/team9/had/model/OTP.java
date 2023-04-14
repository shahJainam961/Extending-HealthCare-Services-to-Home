package com.team9.had.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OTP {

    @Id
    private String loginId;
    @Column(length = 6, nullable = false)
    private String otp;
    @Column(nullable = false)
    private Date date;

}
