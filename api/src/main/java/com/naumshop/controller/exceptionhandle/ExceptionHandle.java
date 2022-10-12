package com.naumshop.controller.exceptionhandle;

import com.naumshop.exception.NoSuchEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

import static com.naumshop.util.UUIDGenerator.generateUUID;

@RestControllerAdvice
public class ExceptionHandle {

    private static final String ERROR = "error";

    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<Object> handleNoSuchEntityException(Exception e) {

        return new ResponseEntity<>(
                Collections.singletonMap(ERROR, composeErrorContainer(e, 2)),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({NumberFormatException.class, IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(Exception e) {

        return new ResponseEntity<>(
                Collections.singletonMap(ERROR, composeErrorContainer(e, 3)),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception e) {

        return new ResponseEntity<>(
                Collections.singletonMap(ERROR, composeErrorContainer(e, 1)),
                HttpStatus.BAD_REQUEST
        );
    }

    private ErrorContainer composeErrorContainer(Exception e, int errorCode) {

        return ErrorContainer.builder()
                .exceptionId(generateUUID())
                .errorCode(errorCode)
                .clazz(e.getClass().getSimpleName())
                .message(e.getMessage())
                .build();
    }
}
