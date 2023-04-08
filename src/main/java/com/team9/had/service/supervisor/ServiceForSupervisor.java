package com.team9.had.service.supervisor;

import com.team9.had.exception.UserNotFoundException;
import com.team9.had.model.sup.ReassignedForSup;
import com.team9.had.model.sup.SubmitAssignedForSup;

import java.io.Serializable;

public interface ServiceForSupervisor {
    Serializable getUnassignedCitizens(String loginId, String role);

    boolean submitAssignment(SubmitAssignedForSup submitAssignedForSup) throws UserNotFoundException;

    Serializable getFhws(String loginId, String role);

    boolean reassign(ReassignedForSup reassignedForSup);
}
