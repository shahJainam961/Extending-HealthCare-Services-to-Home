package com.team9.had.repository;

import com.team9.had.entity.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, String> {
    Receptionist findByCitizenUhId(Integer id);
    Receptionist findByLoginId(String loginId);
}
