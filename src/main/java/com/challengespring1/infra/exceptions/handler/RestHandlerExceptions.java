package com.challengespring1.infra.exceptions.handler;

import com.challengespring1.infra.exceptions.*;
import com.challengespring1.infra.exceptions.response.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestHandlerExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    private ResponseEntity<ResponseException> clientNotFoundErrorHandler(ClientNotFoundException exception){
        ResponseException responseExceptions = new ResponseException("Client not found.", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(responseExceptions.getHttpStatus()).body(responseExceptions);
    }
    @ExceptionHandler(ClientNotFitInsurance.class)
    private ResponseEntity<ResponseException> clientNotFitInsuranceErrorHandler(ClientNotFitInsurance exception){
        ResponseException responseExceptions = new ResponseException("Client not fit.", HttpStatus.UNPROCESSABLE_ENTITY);
        return ResponseEntity.status(responseExceptions.getHttpStatus()).body(responseExceptions);
    }
    @ExceptionHandler(HouseNotFoundException.class)
    private ResponseEntity<ResponseException> houseNotFoundErrorHandler(HouseNotFoundException exception){
        ResponseException responseExceptions = new ResponseException("House not found.", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(responseExceptions.getHttpStatus()).body(responseExceptions);
    }
    @ExceptionHandler(VehicleNotFoundException.class)
    private ResponseEntity<ResponseException> vehicleNotFoundErrorHandler(VehicleNotFoundException exception){
        ResponseException responseExceptions = new ResponseException("Vehicle not found.", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(responseExceptions.getHttpStatus()).body(responseExceptions);
    }

}
