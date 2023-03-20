package com.team9.had.config;

import com.team9.had.Constant;
import com.team9.had.repository.DoctorRepository;
import com.team9.had.repository.FieldHealthWorkerRepository;
import com.team9.had.repository.ReceptionistRepository;
import com.team9.had.repository.SupervisorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {


    private final DoctorRepository doctorRepository;
    private final ReceptionistRepository receptionistRepository;
    private final SupervisorRepository supervisorRepository;
    private final FieldHealthWorkerRepository fieldHealthWorkerRepository;
    @Bean
    public UserDetailsService userDetailsService(){
        return loginid -> {
            if(loginid.startsWith(Constant.DOCTOR)){
                return doctorRepository.findById(loginid).orElseThrow(()->new UsernameNotFoundException("User not found"));
            }
            else if(loginid.startsWith(Constant.RECEPTIONIST)){
                return receptionistRepository.findById(loginid).orElseThrow(()->new UsernameNotFoundException("User not found"));
            }
            else if(loginid.startsWith(Constant.FIELD_HEALTH_WORKER)){
                return fieldHealthWorkerRepository.findById(loginid).orElseThrow(()->new UsernameNotFoundException("User not found"));
            }
            return supervisorRepository.findById(loginid).orElseThrow(()->new UsernameNotFoundException("User not found"));
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncode());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncode() {
        return new BCryptPasswordEncoder();
    }
}
