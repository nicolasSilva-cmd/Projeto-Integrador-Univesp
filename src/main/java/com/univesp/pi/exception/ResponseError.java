package com.univesp.pi.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ResponseError(String message, HttpStatus status, LocalDateTime time) {
}
