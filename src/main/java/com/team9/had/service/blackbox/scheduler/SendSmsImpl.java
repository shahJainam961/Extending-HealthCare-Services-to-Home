//package com.team9.had.service.blackbox.scheduler;
//
//import com.team9.had.utils.Constant;
//import com.team9.had.entity.Citizen;
//import com.team9.had.entity.FieldHealthWorker;
//import com.team9.had.entity.FollowUp;
//import com.team9.had.entity.HealthRecord;
//import com.team9.had.repository.FollowUpRepository;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.sql.Date;
//import java.util.ArrayList;
//
//@Service
//public class SendSmsImpl implements SendSms{
//
//    @Autowired
//    FollowUpRepository followUpRepository;
//
//
//    @Override
//    public boolean sendMessage() {
//
//
//        ArrayList<FollowUp> followUps = followUpRepository.findAllByDateOfFollowUp(new Date(System.currentTimeMillis()));
//        Twilio.init(Constant.ACCOUNT_SID, Constant.AUTH_TOKEN);
//        if(followUps.size()==0) return true;
//        try{
//            followUps.forEach(followUp -> {
//                Citizen citizen = followUp.getHealthRecord().getCitizen();
//                HealthRecord healthRecord = followUp.getHealthRecord();
//                Date date = followUp.getDateOfFollowUp();
//                FieldHealthWorker fieldHealthWorker = followUp.getHealthRecord().getFieldHealthWorker();
//                String secretKey = followUp.getSecretKey();
//                Message message = Message.creator(
//                                new com.twilio.type.PhoneNumber("+919426629406"),
//                                new com.twilio.type.PhoneNumber("+14344361379"),
//                                "\n\nHello "+citizen.getFname()+" "+citizen.getLname()+", hope you are doing well. Your follow-up is scheduled for the date "+date+" of Health Record Id "+healthRecord.getHrId()+", will be done by the field health worker, "+fieldHealthWorker.getCitizen().getFname()+" "+fieldHealthWorker.getCitizen().getLname()+". Please share this secret key to him/her after he/she has completed their work. Find below the secret key. Thank You.\n Secret Key: "+secretKey)
//                        .create();
//                System.out.println(message.getSid());
//            });
//        }
//        catch(Exception e){
//            System.out.println("e = " + e);
//            return false;
//        }
//        return true;
//    }
//}
