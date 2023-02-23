package com.team9.had.service.blackbox.addHospital;

import com.team9.had.entity.Hospital;

import java.util.List;

public interface HospitalService {
    boolean addHospital(Hospital hospital);

    boolean addHospitals(List<Hospital> hospitals);
}
