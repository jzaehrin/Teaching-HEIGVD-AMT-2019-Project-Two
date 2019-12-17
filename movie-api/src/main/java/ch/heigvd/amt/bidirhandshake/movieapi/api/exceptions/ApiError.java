package ch.heigvd.amt.bidirhandshake.movieapi.api.exceptions;

import lombok.Getter;
import org.joda.time.LocalDateTime;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletException;

@Getter
public class ApiError extends ServletException {

    private HttpStatus status;
    private LocalDateTime timestamp;

    public ApiError(HttpStatus status) {
        this(status, "Unexpected error");
    }

    public ApiError(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
