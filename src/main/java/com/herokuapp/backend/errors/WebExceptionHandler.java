package com.herokuapp.backend.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import static com.herokuapp.backend.errors.ErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@RestController
public class WebExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public List<Error> handleUnauthorized() {
        return Collections.singletonList(new Error(ERROR_401_UAUTHORIZED));
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public List<Error> handleForbidden() {
        return Collections.singletonList(new Error(ERROR_403_FORBIDEN));
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public List<Error> handleNoSuchElement(NoSuchElementException ex) {
        return Collections.singletonList(new Error(ERROR_404_NOT_FOUND));
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public List<Error> handleNoHandlerFound() {
        return Collections.singletonList(new Error(ERROR_404_INVALID_URL));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public List<Error> handleBind(BindException ex) {
        log.error(ex.getMessage(), ex);
        return toErrorList(ex);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<Error> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error("Method argument not valid", ex);
        return toErrorList(ex.getBindingResult());
    }

    @ResponseStatus(METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public List<Error> handleRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        log.error(ERROR_405_METHOD_NOT_ALLOWED, ex);
        return Collections.singletonList(new Error(ERROR_405_METHOD_NOT_ALLOWED));
    }

    @ResponseStatus(NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public List<Error> handleMessageNotReadable(HttpMessageNotReadableException ex) {
        log.error(ERROR_406_NOT_ACCEPTABLE, ex);
        return Collections.singletonList(new Error(ERROR_406_NOT_ACCEPTABLE));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public List<Error> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        log.error(ERROR_400_METHOD_ARGUMENT_TYPE_MISMATCH, ex);
        return Collections.singletonList(new Error(ERROR_400_METHOD_ARGUMENT_TYPE_MISMATCH));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<Error> handleConstrainViolationException(ConstraintViolationException ex) {
        log.error(ERROR_400_METHOD_ARGUMENT_TYPE_MISMATCH, ex);
        return toErrorList(ex.getConstraintViolations());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public List<Error> handleIllegalArgumentException(Exception ex) {
        log.error(ERROR_400_METHOD_ARGUMENT_TYPE_MISMATCH, ex);
        return Collections.singletonList(new Error(ex.getMessage()));
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public List<Error> handleInternalServerError(Exception ex) {
        log.error(ERROR_500_INTERNAL_SERVER_ERROR, ex);
        return Collections.singletonList(new Error(ERROR_500_INTERNAL_SERVER_ERROR));
    }

    private List<Error> toErrorList(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream().map(e -> new Error(e.getDefaultMessage())).collect(Collectors.toList());
    }

    private List<Error> toErrorList(Set<ConstraintViolation<?>> constraintViolationSet) {
        return constraintViolationSet.stream()
                .map(e -> new Error(e.getPropertyPath() + " " + e.getMessage()))
                .collect(Collectors.toList());
    }

}
