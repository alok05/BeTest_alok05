package com.example.creditlimit.exception;

import com.example.creditlimit.utility.CommonUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class CentralizedExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String GENERIC_ERROR_MESSAGE = "Error during processing the data";

    @Autowired
    private CommonUtility commonUtility;

    @ExceptionHandler(InvalidSourceException.class)
    public ResponseEntity<Object> handleInvalidSourceException(InvalidSourceException ex) {

        return commonUtility.buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ArrayIndexOutOfBoundsException.class, IOException.class})
    public ResponseEntity<Object> handleArrayAndIOException(Exception ex) {
        return commonUtility.buildResponse(GENERIC_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception ex) {
        ResponseStatus findAnnotation =
                AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);

        HttpStatus httpStatus = findAnnotation != null ? findAnnotation.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        return commonUtility.buildResponse(GENERIC_ERROR_MESSAGE, httpStatus);
    }
}
