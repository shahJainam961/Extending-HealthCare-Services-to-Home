package com.team9.had.repository;

import com.team9.had.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    District findByDistrictName(String districtName);
}
