package com.team9.had.service.blackbox.jobForScheduler;

import com.team9.had.model.Citizen;
import com.team9.had.model.FieldHealthWorker;
import com.team9.had.model.FollowUp;
import com.team9.had.model.HealthRecord;
import com.team9.had.repository.FollowUpRepository;
import com.team9.had.service.twilio.TwilioMessaging;
import com.team9.had.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

@Service
public class SendSmsForFollowUpsImpl implements SendSmsForFollowUps {

    @Autowired
    FollowUpRepository followUpRepository;


    @Override
    public boolean sendMessage() {


        ArrayList<FollowUp> followUps = followUpRepository.findAllByDateOfFollowUp((new Date(System.currentTimeMillis() + Constant.DAY)));
        if(followUps.size()==0) return true;
        try{
            followUps.forEach(followUp -> {
                if(followUp.getHealthRecord().getFieldHealthWorker()!=null) {
                    Citizen citizen = followUp.getHealthRecord().getCitizen();
                    HealthRecord healthRecord = followUp.getHealthRecord();
                    Date date = followUp.getDateOfFollowUp();
                    FieldHealthWorker fieldHealthWorker = followUp.getHealthRecord().getFieldHealthWorker();
                    String secretKey = followUp.getSecretKey();
                    String msg = "\n\nHello "+citizen.getFname()+" "+citizen.getLname()+", hope you are doing well. Your follow-up is scheduled for the date "+date+" of Health Record Id "+healthRecord.getHrId()+", will be done by the field health worker, "+fieldHealthWorker.getCitizen().getFname()+" "+fieldHealthWorker.getCitizen().getLname()+". Please share this secret key to him/her after he/she has completed their work. Find below the secret key. Thank You.\n Secret Key: "+secretKey;
                    String mobileNo = Constant.MOBILE_PREFIX + citizen.getMobileNo();
                    TwilioMessaging.sendMessage(msg, mobileNo);
                }
            });
        }
        catch(Exception e){
            System.out.println("e = " + e);
            return false;
        }
        return true;
    }
}
