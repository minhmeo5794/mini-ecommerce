package project.backend.mini_ecommerce.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    private final Instant timestamp;
    private final int status;
    private final String error;
    private final String path;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Map<String, List<String>> errors;
}
