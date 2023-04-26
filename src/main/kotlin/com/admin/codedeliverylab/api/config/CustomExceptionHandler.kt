package com.admin.codedeliverylab.api.config

import com.admin.codedeliverylab.api.controller.dto.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.ExceptionHandler



class CustomException(message: String) : RuntimeException(message)

@RestControllerAdvice
@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(CustomException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleCustomBadRequest(ex: CustomException): ErrorResponse {
        return ErrorResponse(ex.message!!,  HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(CustomException::class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ResponseBody
    fun handleCustomBadGateway(ex: CustomException): ErrorResponse {
        return ErrorResponse(ex.message!!,  HttpStatus.BAD_GATEWAY)
    }


}