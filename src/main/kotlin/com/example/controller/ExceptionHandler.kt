package com.example.controller

import com.example.model.ErrorResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleException(): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(ErrorResponse().message("Last Name must not be null"))
    }
}