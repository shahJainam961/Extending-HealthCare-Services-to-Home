package com.team9.had.repository;

import com.team9.had.model.FieldHealthWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FieldHealthWorkerRepository extends JpaRepository<FieldHealthWorker, String> {
    FieldHealthWorker findByCitizen_UhId(Integer uhId);
    FieldHealthWorker findByLoginId(String loginId);
    ArrayList<FieldHealthWorker> findAllByAssignedPincode(String loginId);
}
