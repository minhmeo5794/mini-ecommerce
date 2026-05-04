package project.backend.mini_ecommerce.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.backend.mini_ecommerce.common.exception.custom.EmailAlreadyExistsException;
import project.backend.mini_ecommerce.common.exception.custom.PasswordMismatchException;
import project.backend.mini_ecommerce.common.exception.custom.ResourceNotFoundException;
import project.backend.mini_ecommerce.common.response.ErrorResponse;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            HttpStatus status,
            String path,
            String message,
            Map<String, String> errors
    ) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .path(path)
                .message(message)
                .errors(errors)
                .build();
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler({EmailAlreadyExistsException.class, PasswordMismatchException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(Exception ex, HttpServletRequest request) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                request.getRequestURI(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            String message = error.getDefaultMessage();

            errors.putIfAbsent(field, message);
        });

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                request.getRequestURI(),
                "Validation failed",
                errors
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                request.getRequestURI(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {

        log.error("Unhandled exception: {}", ex.getMessage(), ex);

        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                request.getRequestURI(),
                "Something went wrong",
                null
        );
    }
}
