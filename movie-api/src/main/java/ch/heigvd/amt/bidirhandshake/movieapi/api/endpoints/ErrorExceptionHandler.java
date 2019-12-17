package ch.heigvd.amt.bidirhandshake.movieapi.api.endpoints;

import ch.heigvd.amt.bidirhandshake.authapi.api.exceptions.ApiError;
import ch.heigvd.amt.bidirhandshake.authapi.dto.Error;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorExceptionHandler extends ResponseEntityExceptionHandler {

    private final boolean DEBUG = true;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Malformed JSON request"));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder errorMessage = new StringBuilder("Malformed body request : {");

        errorMessage.append(ex.getBindingResult().getAllErrors().stream().map(objectError -> {
            if (objectError instanceof FieldError) {
                return ((FieldError) objectError).getField() + " " + objectError.getDefaultMessage();
            }
            return "";
        }).collect(Collectors.joining(", ")));

        errorMessage.append("}");

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMessage.toString()));
    }

    @ExceptionHandler(ApiError.class)
    protected ResponseEntity<Object> handleApiError(ApiError ex) {
        return buildResponseEntity(ex);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        Error error = new Error();

        error.setError((DEBUG) ? apiError.getLocalizedMessage(): apiError.getMessage());
        return new ResponseEntity<>(error, apiError.getStatus());
    }


}