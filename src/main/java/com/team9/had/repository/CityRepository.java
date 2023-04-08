package com.team9.had.repository;

import com.team9.had.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    City findByCityName(String cityName);
    City findByPincode(String pincode);
    ArrayList<City> findAllByDistrict_Did(Integer did);
}
