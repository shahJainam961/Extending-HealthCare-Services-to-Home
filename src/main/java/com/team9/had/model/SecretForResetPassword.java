package com.team9.had.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SecretForResetPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer sec_id;

    @Column(unique = true, nullable = false, length=999999999)
    private String secret;

}
