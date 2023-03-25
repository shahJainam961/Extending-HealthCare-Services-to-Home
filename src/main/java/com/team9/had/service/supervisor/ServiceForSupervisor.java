package com.team9.had.service.supervisor;

import com.team9.had.model.sup.ReassignedForSup;
import com.team9.had.model.sup.SubmitAssignedForSup;

import java.io.Serializable;

public interface ServiceForSupervisor {
    Serializable getUnassignedCitizens(String loginid, String role);

    Serializable submitAssignment(SubmitAssignedForSup submitAssignedForSup);

    // todo supervisor ne tool apvano che ena maate ni api's
    Serializable getFhws(String loginId, String role);

    Serializable reassign(ReassignedForSup reassignedForSup);
}
