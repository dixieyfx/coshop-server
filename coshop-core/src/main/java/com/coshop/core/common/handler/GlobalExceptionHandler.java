package com.coshop.core.common.handler;

import com.coshop.core.common.enums.ResponseCode;
import com.coshop.core.common.exception.AuroraRuntimeException;
import com.coshop.core.common.exception.NotLoginException;
import com.coshop.core.common.request.GenericResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dixiey
 * @version 1.0
 * @description: TODO
 * @date 2023/11/26 15:27
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 定义要捕获的异常 可以多个 @ExceptionHandler({})     *
     * @param request  request
     * @param e        exception
     * @param response response
     * @return 响应结果
     */
    @ExceptionHandler(AuroraRuntimeException.class)
    public GenericResponse customExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        AuroraRuntimeException exception = (AuroraRuntimeException) e;

        if (exception.getCode() == ResponseCode.USER_INPUT_ERROR) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (exception.getCode() == ResponseCode.FORBIDDEN) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return new GenericResponse(exception.getCode(), null, exception.getMessage());
    }

    @ExceptionHandler(NotLoginException.class)
    public GenericResponse tokenExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        log.error("token exception", e);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        return new GenericResponse(ResponseCode.AUTHENTICATION_NEEDED);
    }

    @ExceptionHandler(Exception.class)
    public GenericResponse exceptionHandler1(Exception exception){
        return new GenericResponse(ResponseCode.INTERNAL_ERROR);
    }

}
