package com.team9.had.service.twilio;

import com.team9.had.utils.Constant;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class TwilioMessaging {

    public static boolean sendMessage(String msg, String mobileNo){
        try{
            Twilio.init(Constant.ACCOUNT_SID, Constant.AUTH_TOKEN);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber("+919426629406"),// here I will use actual mobileNo
                            // to give actual number but now only supports one mobile number code for fetching actual number is above commented
                            new com.twilio.type.PhoneNumber("+14344361379"),
                            msg)
                    .create();
            System.out.println("message = " + message.getSid());
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

}
