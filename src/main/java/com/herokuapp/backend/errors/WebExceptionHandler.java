package com.herokuapp.backend.errors;

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

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.herokuapp.backend.errors.ErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@RestController
public class WebExceptionHandler {

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
    public List<Error> handleNoSuchElement() {
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
        return toErrorList(ex);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<Error> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return toErrorList(ex.getBindingResult());
    }


    @ResponseStatus(METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public List<Error> handleRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return Collections.singletonList(new Error(ERROR_405_METHOD_NOT_ALLOWED));
    }

    @ResponseStatus(NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public List<Error> handleMessageNotReadable(HttpMessageNotReadableException ex) {
        return Collections.singletonList(new Error(ERROR_406_NOT_ACCEPTABLE));
    }


    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public List<Error> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return Collections.singletonList(new Error(ERROR_400_METHOD_ARGUMENT_TYPE_MISMATCH));
    }


    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public List<Error> handleInternalServerError(Exception ex) {
        ex.printStackTrace();
        return Collections.singletonList(new Error(ERROR_500_INTERNAL_SERVER_ERROR));
    }


    private List<Error> toErrorList(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream().map(e -> new Error(e.getDefaultMessage())).collect(Collectors.toList());
    }

}
