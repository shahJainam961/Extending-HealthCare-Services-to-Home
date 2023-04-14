package com.team9.had.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor implements UserDetails {


    @Id
    private String loginId;

    @OneToOne
    @JoinColumn(name = "citizen_uh_id", unique = true, nullable = false)
    private Citizen citizen;

    @Column(unique = true, nullable = false)
    private String licenseId;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "hospital_hosp_id", nullable = false)
    private Hospital hospital;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
