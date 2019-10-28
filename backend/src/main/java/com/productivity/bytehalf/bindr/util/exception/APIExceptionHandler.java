package com.productivity.bytehalf.bindr.util.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Api exception handler.
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle all exceptions response entity.
     *
     * @param exception  the exception
     * @param webRequest the web request
     * @return the response and http status
     */
    @ExceptionHandler
    public ResponseEntity<Object> handleAllExceptions(Exception exception,
                                                      WebRequest webRequest) {

        List<String> details = new ArrayList<>();

        details.add(exception.getLocalizedMessage());

        ErrorResponse errorResponse = new ErrorResponse("Server Error", details);

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle record not found response entity.
     *
     * @param exception  the exception
     * @param webRequest the web request
     * @return the response and http status
     */
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleRecordNotFound(Exception exception,
                                                             WebRequest webRequest) {

        List<String> details = new ArrayList<>();

        details.add(exception.getLocalizedMessage());

        ErrorResponse errorResponse = new ErrorResponse("Not found",
                details);

        return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers,
            HttpStatus status, WebRequest webRequest) {

        List<String> details = new ArrayList<>();

        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse("Validation Failed", details);

        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
