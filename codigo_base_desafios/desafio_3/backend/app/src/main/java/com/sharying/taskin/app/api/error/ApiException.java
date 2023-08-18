package com.sharying.taskin.app.api.error;

import java.time.ZonedDateTime;

public class ApiException {
    final String message;
    final int status;
    final ZonedDateTime timestamp;

    public ApiException(String message, int status, ZonedDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;     
    }

    public String message() {
        return message;
    }

    public int status() {
        return status;
    }

    public ZonedDateTime timestamp() {
        return timestamp;
    }

}
