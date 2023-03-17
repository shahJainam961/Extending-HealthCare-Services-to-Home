package com.team9.had.repository;

import com.team9.had.entity.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Integer> {
    ArrayList<HealthRecord> findAllByDoctor_LoginIdAndStatusAndCreationDateOrderByCreationTime(
            String loginId, Integer status, Date creationDate
    );

    ArrayList<HealthRecord> findAllByDoctor_LoginIdAndStatusOrderByCreationDateDescCreationTimeDesc(
            String loginId, Integer status
    );

    HealthRecord findByHrId(Integer hrId);

}
