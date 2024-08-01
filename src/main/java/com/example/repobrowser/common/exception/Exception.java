package com.example.repobrowser.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Exception {

    public static ResponseEntity<ErrorResponse> handle(HttpStatus status, String message) {
        var errorResponse = ErrorResponse.builder()
                .status(status.value())
                .message(message)
                .build();

        return new ResponseEntity<>(errorResponse, status);
    }
}
