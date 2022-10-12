package com.naumshop.controller.exceptionhandle;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorContainer {

    private String exceptionId;

    private int errorCode;

    private String clazz;

    private String message;
}
