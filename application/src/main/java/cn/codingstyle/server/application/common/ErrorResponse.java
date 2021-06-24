package cn.codingstyle.server.application.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private final int error;
    private final String message;

    public ErrorResponse(int error, String message) {
        this.error = error;
        this.message = message;
    }

    public static ErrorResponse of(HttpStatus httpStatus, String message) {
        return new ErrorResponse(httpStatus.value(), message);
    }
}
