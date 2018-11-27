package com.example.configclient.service.handle;


import com.example.configclient.util.exception.UserLoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(UserLoginException.class)
    public ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
    }
}















