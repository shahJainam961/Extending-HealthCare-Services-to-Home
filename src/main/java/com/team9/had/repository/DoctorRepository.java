package com.team9.had.repository;

import com.team9.had.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Doctor findByCitizen_UhId(Integer uhId);
    ArrayList<Doctor> findAllByHospital_HospId(Integer hospId);
    Doctor findByLoginId(String loginId);
}
