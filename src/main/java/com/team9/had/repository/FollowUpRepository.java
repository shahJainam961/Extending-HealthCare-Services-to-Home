package com.team9.had.repository;

import com.team9.had.model.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Integer> {
    ArrayList<FollowUp> findAllByHealthRecord_HrId(Integer hrId);

    ArrayList<FollowUp> findAllByHealthRecord_FieldHealthWorker_LoginIdAndStatus(String loginId, Integer status);

    ArrayList<FollowUp> findAllByDateOfFollowUpAndStatus(Date date, Integer status);
}
