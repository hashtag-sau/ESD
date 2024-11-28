package com.saurabh.erp.exception;

import com.saurabh.erp.dto.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    //for invalid request body when it doesn't match with dto.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Iterate over all field errors and collect them
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        // Return a 400 Bad Request with the validation error messages
        return ResponseEntity.badRequest().body(errors);
    }

    //if wrong argument are passed
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        // Create a custom error response with the message from the exception
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());

        // Return a 400 Bad Request with the error message
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request) {
        // Custom response message for invalid URL (404)
        String requestedUrl = request.getDescription(false).replace("uri=", "");
        String errorMessage = String.format("""
                404. That’s an error.
                
                The requested URL '%s' was not found on this server. That’s all we know.""", requestedUrl);

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    //can handle wrong url or api
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException ex, WebRequest request) {
        // Custom response message for invalid URL (404)
        String requestedUrl = request.getDescription(false).replace("uri=", "");
        String errorMessage = String.format("""
                404. That’s an error.
                
                The requested URL '%s' was not found on this server. That’s all we know.""", requestedUrl);

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Token has expired. Please log in again.");
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> handleJwtException(JwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid token. Please log in again.");
    }

    //can handle error 400, missing of authorization header
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<?> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)  // 400 Bad Request
                .body("Required request header is missing: " + ex.getHeaderName());
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> EmployeeNotFoundException(EmployeeNotFoundException ex) {
        // Custom response message for invalid URL (404)
        String errorMessage = "Wrong email or password";

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }



}
