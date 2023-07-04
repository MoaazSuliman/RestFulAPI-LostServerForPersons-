package com.moaaz.lost.exception;


import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchElementException(Exception exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(Exception exception) {
        return exception.getMessage();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            String message = "Email already exists";
            return new ResponseEntity<>(message +ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Email Already In Our Database Take Another One", HttpStatus.BAD_REQUEST);
    }
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//    public ErrorDetails handleConstraintViolationException(
//            ConstraintViolationException exception,
//            WebRequest request) {
//
//        return new ErrorDetails("This Email Already In Our Database...",
//                request.toString(),
//                String.valueOf(new Date()));
//    }

    @ExceptionHandler(TransactionSystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleTransactionSystemException(TransactionSystemException transactionSystemException) {
        transactionSystemException.printStackTrace();
        return "Password Must Be Greater Than Or Equal 8 Characters " + transactionSystemException.getMessage()+" "+transactionSystemException.getCause()+" "+transactionSystemException.getStackTrace();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return illegalArgumentException.getMessage();
    }
}


