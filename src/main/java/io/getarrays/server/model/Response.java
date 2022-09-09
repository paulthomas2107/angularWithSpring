package io.getarrays.server.model;


import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class Response {

    protected LocalDate timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String reason;
    protected String message;

}
