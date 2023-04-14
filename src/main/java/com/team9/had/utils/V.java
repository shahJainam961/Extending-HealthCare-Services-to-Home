package com.team9.had.utils;

import com.team9.had.customModel.LoginModel;
import com.team9.had.customModel.doc.HrModelForDoc;
import com.team9.had.customModel.doc.StartDateEndDateModel;
import com.team9.had.customModel.rec.HrModelForRec;
import com.team9.had.customModel.sup.ReassignedForSup;
import com.team9.had.customModel.sup.SubmitAssignedForSup;

public interface V {

    static boolean validateLoginModel(LoginModel loginModel){
        return loginModel!=null && loginModel.getLoginId()!=null && loginModel.getPassword()!=null;
    }

    static boolean validateHrModelForRec(HrModelForRec hrModelForRec){
        return  hrModelForRec!=null && hrModelForRec.getStreet1()!=null &&
                hrModelForRec.getDistrict()!=null && hrModelForRec.getCity()!=null &&
                hrModelForRec.getState()!=null && hrModelForRec.getMobileNo()!=null &&
                hrModelForRec.getDoctor()!=null && hrModelForRec.getDoctor().getLoginId()!=null &&
                hrModelForRec.getCitizen()!=null && hrModelForRec.getCitizen().getUhId()!=null;
    }

    static boolean validateStartDateEndDateModel(StartDateEndDateModel startDateEndDateModel){
        return startDateEndDateModel!=null && startDateEndDateModel.getStartDate()!=null && startDateEndDateModel.getEndDate()!=null;
    }

    static boolean validateHrModelForDoc(HrModelForDoc hrModelForDoc){
        return  hrModelForDoc!=null && hrModelForDoc.getHrId()!=null &&
                hrModelForDoc.getCitizen()!=null && hrModelForDoc.getCitizen().getUhId()!=null;
    }

    static boolean validateSubmitAssignedForSup(SubmitAssignedForSup submitAssignedForSup){
        return submitAssignedForSup!=null && submitAssignedForSup.getCitizen()!=null &&
                submitAssignedForSup.getCitizen().getUhId()!=null && submitAssignedForSup.getFieldHealthWorker()!=null &&
                submitAssignedForSup.getFieldHealthWorker().getLoginId()!=null;
    }

    static boolean validateReassignedForSup(ReassignedForSup reassignedForSup){
        return reassignedForSup!=null && reassignedForSup.getCitizens()!=null &&
                reassignedForSup.getFieldHealthWorker()!=null && reassignedForSup.getFieldHealthWorker().getLoginId()!=null;
    }

}
