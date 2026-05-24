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
    private final String code;
    private final String message;
    private final int status;
    private final String path;
    private final Instant timestamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Map<String, String> details;
}
