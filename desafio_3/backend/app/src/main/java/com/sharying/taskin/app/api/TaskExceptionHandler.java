package com.sharying.taskin.app.api;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sharying.taskin.app.api.error.ApiException;
import com.sharying.taskin.app.api.error.ApiResponseException;
import com.sharying.taskin.app.domain.error.ParamInvalid;

@ControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(value = { ParamInvalid.class })
    public ResponseEntity<Object> x400(ApiResponseException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                status.value(),
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, status);
    }

}
