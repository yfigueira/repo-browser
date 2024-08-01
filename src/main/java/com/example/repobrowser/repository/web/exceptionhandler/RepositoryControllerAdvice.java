package com.example.repobrowser.repository.web.exceptionhandler;

import com.example.repobrowser.common.exception.ErrorResponse;
import com.example.repobrowser.common.exception.Exception;
import com.example.repobrowser.repository.exception.RepositoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RepositoryControllerAdvice {

    @ExceptionHandler(RepositoryException.UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFound(RepositoryException.UsernameNotFoundException exception) {
        return Exception.handle(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMissingUsernameArgument(RepositoryException.MissingArgumentException exception) {
        return Exception.handle(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}
