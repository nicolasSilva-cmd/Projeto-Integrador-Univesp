package com.univesp.pi.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseError> EntityNotFound(EntityNotFoundException ex){
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ConstraintException.class)
    public ResponseEntity<ResponseError> ConstraintException(ConstraintException ex) {
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @Builder
    private record InvalidatedParams (String cause, String attribute) {}

    @ExceptionHandler(ConstraintViolationException.class)
    ProblemDetail handleConstraintViolationException(ConstraintViolationException e)  {
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
        List<InvalidatedParams> validationResponse = errors.stream()
                .map(err -> InvalidatedParams.builder()
                        .cause(err.getMessage())
                        .attribute(err.getPropertyPath().toString())
                        .build()).toList();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Falha na validação de campos");
        problemDetail.setTitle("Campos nulos");
        problemDetail.setProperty("Parâmetros inválidos", validationResponse);
        return problemDetail;
    }

}
