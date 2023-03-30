package com.team9.had.service.blackbox.addDistrict;

import com.team9.had.entity.District;
import com.team9.had.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;
    @Override
    public boolean addDistricts(ArrayList<District> districts) {
        try{
            districts.forEach((district)->{
                districtRepository.save(district);
            });
            return true;
        }
        catch(Exception e){
            System.out.println("e = " + e);
            return false;
        }
    }
}
