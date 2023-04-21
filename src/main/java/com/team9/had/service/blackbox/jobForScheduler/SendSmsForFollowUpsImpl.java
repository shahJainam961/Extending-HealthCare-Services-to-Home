package com.team9.had.service.blackbox.jobForScheduler;

import com.team9.had.model.Citizen;
import com.team9.had.model.FieldHealthWorker;
import com.team9.had.model.FollowUp;
import com.team9.had.model.HealthRecord;
import com.team9.had.repository.FollowUpRepository;
import com.team9.had.service.twilio.TwilioMessaging;
import com.team9.had.utils.Constant;
import com.team9.had.utils.EncryptDecrypt;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class SendSmsForFollowUpsImpl implements SendSmsForFollowUps {

    @Autowired
    FollowUpRepository followUpRepository;


    @Override
    public boolean sendMessage() {

        Set<Citizen> citizenSet = new HashSet<>();
        ArrayList<FollowUp> followUps = followUpRepository.findAllByDateOfFollowUpAndStatus((new Date(System.currentTimeMillis() + Constant.DAY)), Constant.FOLLOW_UP_PENDING);
        if(followUps.size()==0) return true;
        try{
            followUps.forEach(followUp -> {
                if(followUp.getHealthRecord().getFieldHealthWorker()!=null) {
                    Citizen citizen = followUp.getHealthRecord().getCitizen();
                    HealthRecord healthRecord = followUp.getHealthRecord();
                    Date date = followUp.getDateOfFollowUp();
                    FieldHealthWorker fieldHealthWorker = followUp.getHealthRecord().getFieldHealthWorker();
                    String secretKey = followUp.getSecretKey();
                    String msg;

                    try {
                        Constant.getDecryptedCitizen(citizen, citizenSet);
                        Constant.getDecryptedHealthRecord(healthRecord, citizenSet);
                        Constant.getDecryptedFieldHealthWorker(fieldHealthWorker, citizenSet);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        msg = "\n\nHello "+ citizen.getFname() +" "
                                + citizen.getLname()
                                + ", hope you are doing well. Your follow-up is scheduled for the date "
                                + date
                                + " of Health Record Id "
                                + healthRecord.getHrId()
                                + ", will be done by the field health worker, "
                                + fieldHealthWorker.getCitizen().getFname()
                                + " "
                                + fieldHealthWorker.getCitizen().getLname()
                                + ". Please share this secret key to him/her after he/she has completed their work. Find below the secret key. Thank You.\n Secret Key: "
                                + secretKey;


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    String mobileNo;
                    try {
                        mobileNo = citizen.getMobileNo();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
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
