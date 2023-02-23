package com.team9.had.repository;

import com.team9.had.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Doctor findByCitizen_Id(Integer id);

    ArrayList<Doctor> findAllByHospital_Id(Integer id);
}
