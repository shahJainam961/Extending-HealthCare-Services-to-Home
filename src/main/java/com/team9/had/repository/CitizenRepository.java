package com.team9.had.repository;

import com.team9.had.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {

    Citizen findByUhId(Integer uhId);

    ArrayList<Citizen> findAllByFieldHealthWorker_LoginId(String loginId);
}
