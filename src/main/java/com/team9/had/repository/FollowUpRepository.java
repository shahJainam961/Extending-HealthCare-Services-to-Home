package com.team9.had.repository;

import com.team9.had.entity.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Integer> {

    

    ArrayList<FollowUp> findByHealthRecord_HrId(Integer hrId);

    ArrayList<FollowUp> findAllByFieldHealthWorker_LoginIdOrderByDateOfFollowUp(String loginId);

    ArrayList<FollowUp> findAllByFieldHealthWorker_LoginIdAndStatusOrderByDateOfFollowUp(String loginId, Integer followUpPending);

    FollowUp findByFuId(Integer fuId);
}
