package com.team9.had.service.blackbox.addCity;

import com.team9.had.entity.City;
import com.team9.had.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Override
    public boolean addCities(ArrayList<City> cities) {
        try{
            cities.forEach((city)->{
                cityRepository.save(city);
            });
            return true;
        }
        catch(Exception e){
            System.out.println("e = " + e);
            return false;
        }
    }
}
