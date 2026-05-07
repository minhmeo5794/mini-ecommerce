package project.backend.mini_ecommerce.common.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ApiResponse<T> {
    private final T data;
    private final String message;
    private final boolean success;
    private final String path;
    private final int status;

    @Builder.Default
    private final Instant timestamp = Instant.now();
}
