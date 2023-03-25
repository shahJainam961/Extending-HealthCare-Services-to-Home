package com.team9.had.service.fhw;

import com.team9.had.Constant;
import com.team9.had.entity.FollowUp;
import com.team9.had.entity.HealthRecord;
import com.team9.had.model.FollowUpModel;
import com.team9.had.model.HealthRecordModel;
import com.team9.had.repository.FollowUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

@Service
public class ServiceForFhwImpl implements ServiceForFhw{

    @Autowired
    private FollowUpRepository followUpRepository;
    @Override
    public Serializable getAllFollowUp(String loginId) {
        ArrayList<Object> obj = new ArrayList<>();

        ArrayList<FollowUp> followUpList =
                followUpRepository
                        .findAllByFieldHealthWorker_LoginIdOrderByDateOfFollowUp(
                                loginId);
        if(followUpList==null) return null;
        ArrayList<FollowUpModel> allFollowUpForFhw = new ArrayList<>();
        followUpList.stream().forEach((followUp) -> {
            allFollowUpForFhw.add(Constant.getModelMapper().map(followUp, FollowUpModel.class));
        });
        obj.add(allFollowUpForFhw);
        return obj;
    }

    @Override
    public Serializable getPendingFollowUp(String loginId) {
        ArrayList<Object> obj = new ArrayList<>();

        ArrayList<FollowUp> followUpList =
                followUpRepository
                        .findAllByFieldHealthWorker_LoginIdAndStatusOrderByDateOfFollowUp(
                                loginId, Constant.FOLLOW_UP_PENDING);
        if(followUpList==null) return null;
        ArrayList<FollowUpModel> pendingFollowUpForFhw = new ArrayList<>();
        followUpList.stream().forEach((followUp) -> {
            pendingFollowUpForFhw.add(Constant.getModelMapper().map(followUp, FollowUpModel.class));
        });
        obj.add(pendingFollowUpForFhw);
        return obj;
    }

    @Override
    public Serializable getBackLoggedFollowUp(String loginId) {
        ArrayList<Object> obj = new ArrayList<>();

        ArrayList<FollowUp> followUpList =
                followUpRepository
                        .findAllByFieldHealthWorker_LoginIdAndStatusOrderByDateOfFollowUp(
                                loginId, Constant.FOLLOW_UP_BACKLOGGED);
        if(followUpList==null) return null;
        ArrayList<FollowUpModel> backlogFollowUpForFhw = new ArrayList<>();
        followUpList.stream().forEach((followUp) -> {
            backlogFollowUpForFhw.add(Constant.getModelMapper().map(followUp, FollowUpModel.class));
        });
        obj.add(backlogFollowUpForFhw);
        return obj;
    }

    @Override
    public Serializable getDoneFollowUp(String loginId) {
        ArrayList<Object> obj = new ArrayList<>();

        ArrayList<FollowUp> followUpList =
                followUpRepository
                        .findAllByFieldHealthWorker_LoginIdAndStatusOrderByDateOfFollowUp(
                                loginId, Constant.FOLLOW_UP_DONE);
        if(followUpList==null) return null;
        ArrayList<FollowUpModel> doneFollowUpForFhw = new ArrayList<>();
        followUpList.stream().forEach((followUp) -> {
            doneFollowUpForFhw.add(Constant.getModelMapper().map(followUp, FollowUpModel.class));
        });
        obj.add(doneFollowUpForFhw);
        return obj;
    }

    @Override
    public Serializable submitFollowUp(FollowUpModel followUpModel) {

        FollowUp followUp = followUpRepository.findByFuId(followUpModel.getFuId());
        followUp.setActualDateOfFollowUp(new Date(System.currentTimeMillis()));
        followUp.setActualTimeOfFollowUp(new Time(System.currentTimeMillis()));
        followUp.setStatus(Constant.FOLLOW_UP_DONE);
        followUp.setFields(followUp.getFields());
        followUp.setFieldsValue(followUp.getFieldsValue());

        // todo logic for getting FieldHealthWorker and getting Supervisor remaining
        try{
                followUpRepository.save(followUp);
        }
        catch(Exception e){
            System.out.println("e = " + e);
            return false;
        }
        return true;
    }
}
