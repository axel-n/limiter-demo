package com.example.demo.dto;

import java.util.Date;

public class RequestDto {
    private final Date timeRequest = new Date();

    public Date getTimeRequest() {
        return timeRequest;
    }
}
