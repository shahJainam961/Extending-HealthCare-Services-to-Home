package com.team9.had.repository;

import com.team9.had.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, String> {
    Supervisor findByCitizen_UhId(Integer uhId);
    Supervisor findByAssignedPincode(String assignedPincode);
    Supervisor findByLoginId(String loginId);
}
