package it.pingflood.demoapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  
  @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
  protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "Error";
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }
  
  @ExceptionHandler(value = {NoSuchElementException.class})
  protected ResponseEntity<Object> handleNoElement(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "No Element Present";
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NO_CONTENT, request);
  }
}
