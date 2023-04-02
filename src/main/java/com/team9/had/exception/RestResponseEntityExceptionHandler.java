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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Serializable> userNotFoundException(UserNotFoundException exception){
        ArrayList<Object> obj = new ArrayList<>();
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        obj.add(errorMessage);
        return new ResponseEntity<>(obj, HttpStatusCode.valueOf(404));
    }

}
