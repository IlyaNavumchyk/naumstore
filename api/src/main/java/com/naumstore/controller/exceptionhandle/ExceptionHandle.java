package com.naumstore.controller.exceptionhandle;

import com.naumstore.exception.NoSuchEntityException;
import com.naumstore.exception.SystemStoreWorkException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

import static com.naumstore.util.UUIDGenerator.generateUUID;

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

    @ExceptionHandler({IllegalArgumentException.class, SystemStoreWorkException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(Exception e) {

        return new ResponseEntity<>(
                Collections.singletonMap(ERROR, composeErrorContainer(e, 3)),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(Exception e) {

        ErrorContainer errorContainer = composeErrorContainer(e, 4);
        errorContainer.setMessage("This login or email already exist");

        return new ResponseEntity<>(
                Collections.singletonMap(ERROR, errorContainer),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception e) {

        return new ResponseEntity<>(
                Collections.singletonMap(ERROR, composeErrorContainer(e, 911)),
                HttpStatus.INTERNAL_SERVER_ERROR
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