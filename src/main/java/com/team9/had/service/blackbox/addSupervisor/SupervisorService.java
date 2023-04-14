package com.team9.had.service.blackbox.addSupervisor;

import com.team9.had.model.Supervisor;

import java.util.List;

public interface SupervisorService {
    boolean addSupervisor(Supervisor supervisor);

    boolean addSupervisors(List<Supervisor> supervisors);
}
