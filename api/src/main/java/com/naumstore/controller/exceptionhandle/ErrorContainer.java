package com.naumstore.controller.exceptionhandle;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorContainer {

    private String exceptionId;

    private LocalDateTime timestamp;

    private int errorCode;

    private String clazz;

    private String message;
}
