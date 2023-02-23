package com.team9.had.repository;

import com.team9.had.entity.FieldHealthWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldHealthWorkerRepository extends JpaRepository<FieldHealthWorker, String> {
    FieldHealthWorker findByCitizen_Id(Integer id);
}
