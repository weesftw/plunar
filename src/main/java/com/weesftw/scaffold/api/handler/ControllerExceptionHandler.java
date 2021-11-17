package com.weesftw.scaffold.api.handler;

import com.weesftw.scaffold.api.request.ExceptionResponse;
import com.weesftw.scaffold.domain.exception.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex,
                                                                         WebRequest request)
    {
        return super.handleExceptionInternal(ex,
                ExceptionResponse
                        .builder()
                        .time(OffsetDateTime.now())
                        .errorCode(HttpStatus.NOT_FOUND.value())
                        .description(ex.getMessage())
                        .build(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request)
    {
        return super.handleExceptionInternal(ex,
                ExceptionResponse
                    .builder()
                    .time(OffsetDateTime.now())
                    .errorCode(status.value())
                    .description("One or more errors has found.")
                    .errors(ex.getBindingResult().getAllErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()))
                    .build(), headers, status, request);
    }
}
