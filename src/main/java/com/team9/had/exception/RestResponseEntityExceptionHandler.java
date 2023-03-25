package com.team9.had.exception;


import com.team9.had.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;
import java.util.ArrayList;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CitizenNotFoundException.class)
    public ResponseEntity<Serializable> citizenNotFoundException(CitizenNotFoundException exception){
        ArrayList<Object> obj = new ArrayList<>();
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        obj.add(errorMessage);
        return new ResponseEntity<>(obj, HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<Serializable> doctorNotFoundException(DoctorNotFoundException exception){
        ArrayList<Object> obj = new ArrayList<>();
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        obj.add(errorMessage);
        return new ResponseEntity<>(obj, HttpStatusCode.valueOf(404));
    }


    @ExceptionHandler(ReceptionistNotFoundException.class)
    public ResponseEntity<Serializable> receptinistNotFoundException(ReceptionistNotFoundException exception){
        ArrayList<Object> obj = new ArrayList<>();
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        obj.add(errorMessage);
        return new ResponseEntity<>(obj, HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(SupervisorNotFoundException.class)
    public ResponseEntity<Serializable> supervisorNotFoundException(SupervisorNotFoundException exception){
        ArrayList<Object> obj = new ArrayList<>();
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        obj.add(errorMessage);
        return new ResponseEntity<>(obj, HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Serializable> exception(Exception exception){
        ArrayList<Object> obj = new ArrayList<>();
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        obj.add(errorMessage);
        return new ResponseEntity<>(obj, HttpStatusCode.valueOf(500));
    }

}
