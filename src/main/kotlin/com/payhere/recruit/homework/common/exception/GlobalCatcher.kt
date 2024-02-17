package com.payhere.recruit.homework.common.exception

import com.payhere.recruit.homework.common.dto.response.BaseResponse
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalCatcher {

    @ExceptionHandler(CustomException::class)
    protected fun handleCustomException(customException: CustomException) : ResponseEntity<BaseResponse<Any?>> {
        val errorCode = customException.errorCode
        val errorResponse = BaseResponse.customExceptionResponse(
            code = errorCode.status.value(),
            message = errorCode.message
        )

        return ResponseEntity.status(errorCode.status)
            .body(errorResponse)
    }

//    @ExceptionHandler(Exception::class)
//    protected fun handleNormalException(exception: Exception) =
//        ResponseEntity.internalServerError()
//            .body(BaseResponse.normalExceptionResponse())

    @ExceptionHandler(HttpMessageNotReadableException::class,
        MethodArgumentNotValidException::class)
    protected fun handleInvalidInputException() =
        ResponseEntity.badRequest()
            .body(BaseResponse.invalidInputExceptionResponse())
}