package com.team9.had.service.fhw;

import com.team9.had.customModel.fhw.ModelForFhw;
import com.team9.had.customModel.fhw.SyncModelForFhw;
import com.team9.had.model.FollowUp;
import com.team9.had.repository.FollowUpRepository;
import com.team9.had.utils.Constant;
import com.team9.had.utils.EncryptDecrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;

@Service
public class ServiceForFhwImpl implements ServiceForFhw{

    @Autowired
    private FollowUpRepository followUpRepository;

    @Override
    public Serializable sync(ModelForFhw modelForFhw, String role) {
        // todo validation of every followups --> throw bad request
        try{
            modelForFhw.getFollowUps().forEach((syncModelForFhw)->{
                FollowUp followUp = Constant.getModelMapper().map(syncModelForFhw, FollowUp.class);
                followUpRepository.save(followUp);
            });

            ArrayList<SyncModelForFhw> followUps1 = new ArrayList<>();
            ArrayList<FollowUp> followUps = followUpRepository.findAllByHealthRecord_FieldHealthWorker_LoginIdAndStatus(role, Constant.FOLLOW_UP_PENDING);
            followUps.forEach((followUp)->{
                SyncModelForFhw syncModelForFhw1 = Constant.getModelMapper().map(followUp, SyncModelForFhw.class);
                syncModelForFhw1.setStreet1(followUp.getHealthRecord().getStreet1());
                syncModelForFhw1.setDistrict(followUp.getHealthRecord().getDistrict());
                syncModelForFhw1.setCity(followUp.getHealthRecord().getCity());
                syncModelForFhw1.setPincode(followUp.getHealthRecord().getPincode());
                syncModelForFhw1.setMobileNo(followUp.getHealthRecord().getMobileNo());
                syncModelForFhw1.setUhId(followUp.getHealthRecord().getCitizen().getUhId());
                try {
                    syncModelForFhw1.setFname(EncryptDecrypt.decrypt(followUp.getHealthRecord().getCitizen().getFname(), Constant.SECRET_KEY));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    syncModelForFhw1.setLname(EncryptDecrypt.decrypt(followUp.getHealthRecord().getCitizen().getLname(), Constant.SECRET_KEY));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                syncModelForFhw1.setGender(followUp.getHealthRecord().getCitizen().getGender());
                syncModelForFhw1.setDob(followUp.getHealthRecord().getCitizen().getDob());
                syncModelForFhw1.setState(followUp.getHealthRecord().getState());
                syncModelForFhw1.setPrescription(followUp.getHealthRecord().getPrescription());
                followUps1.add(syncModelForFhw1);
            });
            ModelForFhw modelForFhw1 = new ModelForFhw();
            modelForFhw1.setFollowUps(followUps1);
            ArrayList<Object> obj = new ArrayList<>();
            obj.add(modelForFhw1);
            return obj;
        }
        catch(Exception e){
            System.out.println("e = " + e);
            return null;
        }
    }
}
