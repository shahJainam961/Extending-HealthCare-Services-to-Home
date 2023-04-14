package com.team9.had.repository;

import com.team9.had.entity.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Integer> {
    ArrayList<HealthRecord> findAllByDoctor_LoginIdAndStatusAndCreationDateOrderByCreationTime(
            String loginId, Integer status, Date creationDate
    );

    ArrayList<HealthRecord> findAllByDoctor_LoginIdAndStatusAndCreationDateBetweenOrderByCreationDateDescCreationTimeDesc(
            String loginId, Integer status, Date startDate, Date endDate
    );

    HealthRecord findByHrId(Integer hrId);

    ArrayList<HealthRecord> findAllByFieldHealthWorkerNullAndSupervisor_LoginId(String loginId);

    ArrayList<HealthRecord> findAllByFieldHealthWorkerNullAndCitizen_UhId(Integer uhId);

    ArrayList<HealthRecord> findAllByCitizen_UhIdAndStatusOrderByCreationDateDescCreationTimeDesc(Integer uhId, Integer status);

    ArrayList<HealthRecord> findAllByFieldHealthWorker_LoginId(String loginId);
}
