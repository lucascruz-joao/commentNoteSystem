package com.letscode.commentNoteSystem.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Error> errors = this.makeErrorsList(ex.getBindingResult());

        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDuplicateException(DataIntegrityViolationException ex, WebRequest request) {

        String userMessage = messageSource.getMessage("email.duplicate", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.toString();

        List<Error> errors = List.of(new Error(userMessage, developerMessage));

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {

        String userMessage = ex.getMessage();
        String developerMessage = ex.toString();

        List<Error> errors = List.of(new Error(userMessage, developerMessage));

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<Error> makeErrorsList(BindingResult bindingResult) {
        List<Error> errors = new ArrayList<>();

        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String developerMessage = fieldError.toString();
            errors.add(new Error(userMessage, developerMessage));
        }

        return errors;
    }

    private static class Error {

        private final String userMessage;
        private final String developerMessage;

        public Error(String userMessage, String developerMessage) {

            this.userMessage = userMessage;
            this.developerMessage = developerMessage;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public String getDeveloperMessage() {
            return developerMessage;
        }

    }

}
