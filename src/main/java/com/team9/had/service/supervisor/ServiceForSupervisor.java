package com.team9.had.service.supervisor;

import com.team9.had.customModel.sup.ReassignedForSup;
import com.team9.had.customModel.sup.SubmitAssignedForSup;

import java.io.Serializable;

public interface ServiceForSupervisor {
    Serializable getUnassignedCitizens(String loginId, String role) throws Exception;

    boolean submitAssignment(SubmitAssignedForSup submitAssignedForSup) throws Exception;

    Serializable getFhws(String loginId, String role) throws Exception;

    boolean reassign(ReassignedForSup reassignedForSup) throws Exception;

    Serializable stats(String loginId) throws Exception;
}
