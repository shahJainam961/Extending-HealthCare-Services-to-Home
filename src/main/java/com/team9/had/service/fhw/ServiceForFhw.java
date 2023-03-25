package com.team9.had.service.fhw;

import com.team9.had.model.FollowUpModel;

import java.io.Serializable;

public interface ServiceForFhw {
    Serializable getAllFollowUp(String loginId);

    Serializable getPendingFollowUp(String loginId);

    Serializable getBackLoggedFollowUp(String loginId);

    Serializable submitFollowUp(FollowUpModel followUpModel);

    Serializable getDoneFollowUp(String loginId);
}
