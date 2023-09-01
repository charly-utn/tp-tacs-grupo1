package org.tptacs.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.tptacs.domain.exceptions.NotFoundException;
import org.tptacs.domain.exceptions.ValidationException;
import org.tptacs.exceptionHandler.ErrorResponse;

import java.util.Set;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        System.out.println("error no manejado: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        System.out.println("error: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        System.out.println("error: " + ex.getMessage());
        var error = new ErrorResponse(getHttpStatus(ex.getCodes()), ex.getMessage());
        return ResponseEntity.status(error.getHttpCode()).body(error);
    }

    private HttpStatus getHttpStatus(Set<String> codes) {
        if (codes.contains(HttpStatus.UNPROCESSABLE_ENTITY.name())) return HttpStatus.UNPROCESSABLE_ENTITY;
        if (codes.contains(HttpStatus.CONFLICT.name())) return HttpStatus.CONFLICT;
        return HttpStatus.BAD_REQUEST;
    }

}
