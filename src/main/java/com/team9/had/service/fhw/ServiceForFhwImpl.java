//package com.team9.had.service.fhw;
//
//import com.team9.had.Constant;
//import com.team9.had.entity.FollowUp;
//import com.team9.had.model.CitizenApplicationModel;
//import com.team9.had.model.FollowUpApplicationModel;
//import com.team9.had.repository.FollowUpRepository;
//import org.springframework.stereotype.Service;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//
//@Service
//public class ServiceForFhwImpl implements ServiceForFhw {
//    private final FollowUpRepository followUpRepository;
//
//    public ServiceForFhwImpl(FollowUpRepository followUpRepository) {
//        this.followUpRepository = followUpRepository;
//    }
//
//    @Override
//    public Serializable download(String loginId) {
//
//        ArrayList<Object> obj = new ArrayList<>();
//        ArrayList<FollowUpApplicationModel> app = new ArrayList<>();
//
//        ArrayList<FollowUp> pendingFollowUps = followUpRepository.findAllByHealthRecord_FieldHealthWorker_LoginIdAndStatus(loginId, Constant.FOLLOW_UP_PENDING);
//        ArrayList<FollowUp> completedFollowUps = followUpRepository.findAllByHealthRecord_FieldHealthWorker_LoginIdAndStatus(loginId, Constant.FOLLOW_UP_DONE);
//        ArrayList<FollowUp> backloggedFollowUps = followUpRepository.findAllByHealthRecord_FieldHealthWorker_LoginIdAndStatus(loginId, Constant.FOLLOW_UP_BACKLOGGED);
//
//
//        ArrayList<FollowUpApplicationModel>  pendingFollowUpModels = getFollowUpModels(pendingFollowUps);
//        ArrayList<FollowUpApplicationModel>  completedFollowUpModels = getFollowUpModels(completedFollowUps);
//        ArrayList<FollowUpApplicationModel>  backloggedFollowUpModels = getFollowUpModels(backloggedFollowUps);
//        obj.add(pendingFollowUpModels);
//        obj.add(completedFollowUpModels);
//        obj.add(backloggedFollowUpModels);
//
//        return obj;
//    }
//
//    private ArrayList<FollowUpApplicationModel> getFollowUpModels(ArrayList<FollowUp> followUps) {
//        ArrayList<FollowUpApplicationModel> tmp = new ArrayList<>();
//        for(FollowUp followUp : followUps){
//            FollowUpApplicationModel followUpModel = Constant.getModelMapper().map(followUp, FollowUpApplicationModel.class);
//            followUpModel.setCitizen(Constant.getModelMapper().map(followUp.getHealthRecord().getCitizen(), CitizenApplicationModel.class));
//            followUpModel.setStreet1(followUp.getHealthRecord().getStreet1());
//            followUpModel.setCity(followUp.getHealthRecord().getCity());
//            followUpModel.setState(followUp.getHealthRecord().getState());
//            followUpModel.setPincode(followUp.getHealthRecord().getPincode());
//            followUpModel.setPrescription(followUp.getHealthRecord().getPrescription());
//            tmp.add(followUpModel);
//        }
//        return tmp;
//    }
//
////    @Override
////    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
////    public void backlogSyncing() {
////        System.out.println("Syncing the backlog status...");
////
////        ArrayList<FollowUp> pendingFollowUps = followUpRepository.findAllByStatus(Constant.FOLLOW_UP_PENDING);
////
////        f+or(FollowUp followUp : pendingFollowUps){
////            if(followUp.getDateOfFollowUp().before(DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH))){
////                followUp.setStatus(Constant.FOLLOW_UP_BACKLOGGED);
////                followUpRepository.save(followUp);
////            }
////        }
////    }
//
//}
