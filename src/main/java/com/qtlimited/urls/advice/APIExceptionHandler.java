package com.qtlimited.urls.advice;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.qtlimited.urls.payload.BodyResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class APIExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(SignatureException.class)
    public BodyResponse handleSignatureException(SignatureException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ExpiredJwtException.class)
    public BodyResponse handleExpiredTokenException(ExpiredJwtException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UsernameNotFoundException.class)
    public BodyResponse handleUsernameNotFoundException(UsernameNotFoundException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public BodyResponse handleBadCredentialsException(BadCredentialsException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BodyResponse handleBadRequestException(Exception ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }
}
