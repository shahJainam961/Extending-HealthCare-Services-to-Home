package com.team9.had.service.blackbox.addState;

import com.team9.had.model.State;
import com.team9.had.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class StateServiceImpl implements StateService{

    @Autowired
    private StateRepository stateRepository;
    public boolean addStates(ArrayList<State> states){
        try{
            states.forEach((state)->{
                stateRepository.save(state);
            });
            return true;
        }
        catch(Exception e){
            System.out.println("e = " + e);
            return false;
        }
    }
}
