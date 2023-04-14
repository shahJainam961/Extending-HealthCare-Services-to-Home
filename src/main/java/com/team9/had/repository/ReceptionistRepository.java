package com.team9.had.repository;

import com.team9.had.model.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, String> {
    Receptionist findByCitizen_UhId(Integer uhId);
    Receptionist findByLoginId(String loginId);
}
