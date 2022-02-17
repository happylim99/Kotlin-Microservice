package com.sean.user_service.exception

import com.sean.user_service.ui.res.ErrorMsg
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class AuthExceptionHandler {

    @ExceptionHandler(value = [UserServiceException::class])
    fun handleUserServiceException(ex: UserServiceException, request: WebRequest?): ResponseEntity<Any?>? {
        val errorMessage = ErrorMsg(msg = ex.message!!)
        return ResponseEntity(errorMessage, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleOtherException(ex: Exception, request: WebRequest?): ResponseEntity<Any?>? {
        val errorMessage = ErrorMsg(msg = ex.message!!)
        return ResponseEntity(errorMessage, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
