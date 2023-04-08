package com.team9.had.exception;


import com.team9.had.model.ErrorMessage;
import com.team9.had.utils.Constant;
import jakarta.transaction.TransactionRequiredException;
import org.apache.tomcat.util.bcel.Const;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
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
        return new ResponseEntity<>(obj, HttpStatusCode.valueOf(Constant.HTTP_404));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Serializable> exceptionHandler(Exception exception){
        ArrayList<Object> obj = new ArrayList<>();
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        obj.add(errorMessage);
        if(exception.getClass()== DataIntegrityViolationException.class ||
                exception.getClass() == TransactionRequiredException.class ||
                exception.getClass() == IllegalArgumentException.class ||
                exception.getClass() == ConstraintViolationException.class)
            return new ResponseEntity<>(obj, HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
        return new ResponseEntity<>(obj, HttpStatusCode.valueOf(Constant.HTTP_INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<Serializable> internalServerError(InternalServerError exception){
        ArrayList<Object> obj = new ArrayList<>();
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        obj.add(errorMessage);
        return new ResponseEntity<>(obj, HttpStatusCode.valueOf(Constant.HTTP_INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Serializable> badRequestException(BadRequestException exception){
        ArrayList<Object> obj = new ArrayList<>();
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        obj.add(errorMessage);
        return new ResponseEntity<>(obj, HttpStatusCode.valueOf(Constant.HTTP_BAD_REQUEST));
    }

    @ExceptionHandler(BadCredentialException.class)
    public ResponseEntity<Serializable> badCredentialException(BadCredentialException exception){
        ArrayList<Object> obj = new ArrayList<>();
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        obj.add(errorMessage);
        return new ResponseEntity<>(obj, HttpStatusCode.valueOf(Constant.HTTP_UNAUTHENTICATED));
    }

}
