package com.team9.had.service.blackbox.addDoctor;

import com.team9.had.model.Doctor;

import java.util.List;

public interface DoctorService {
    boolean addDoctor(Doctor doctor);

    boolean addDoctors(List<Doctor> doctors);
//    boolean addDoctors(List<Doctor> doctors);
}
