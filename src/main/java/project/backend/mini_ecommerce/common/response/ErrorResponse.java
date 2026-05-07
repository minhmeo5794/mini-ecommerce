package project.backend.mini_ecommerce.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Getter
@Builder
public class ErrorResponse {
    private final Instant timestamp;
    private final int status;
    private final String error;
    private final String path;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Map<String, String> errors;
}
