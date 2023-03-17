package com.team9.had.repository;

import com.team9.had.entity.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, String> {
    Supervisor findByCitizenUhId(Integer id);
}
