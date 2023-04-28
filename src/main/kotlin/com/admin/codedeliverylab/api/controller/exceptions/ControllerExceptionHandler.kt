package com.admin.codedeliverylab.api.controller.exceptions

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

data class StandardError(
        var status: Int?,
        var error: Any?,
        var path: String?,
)

@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(
            ex: Exception,
            request: HttpServletRequest,
    ): ResponseEntity<StandardError> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(StandardError(
                status = HttpStatus.BAD_REQUEST.value(),
                error = ex.message,
                path = request.requestURI
        ))
    }
}