package com.huce.ltudm.doan_tra_cu_dulich.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handlerNotFoundException(NotFoundException ex, WebRequest req) {
        // Log err
        return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handlerException(Exception ex, WebRequest req) {

        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<?> handlerDuplicateRecordException(DuplicateRecordException ex, WebRequest req) {
        // Log err

        ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handlerBadRequestException(BadRequestException ex, WebRequest req) {
        // Log err

        ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}