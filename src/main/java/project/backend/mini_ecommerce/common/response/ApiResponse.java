package project.backend.mini_ecommerce.common.response;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ApiResponse<T> {
    private final T data;
    private final boolean success;
    private final String message;
    private final String path;
    private final int status;

    @Builder.Default
    private final Instant timestamp = Instant.now();

    public static <T> ApiResponse<T> success(T data, String message, int status, HttpServletRequest httpServletRequest) {
        return ApiResponse.<T>builder()
                .data(data)
                .success(true)
                .message(message)
                .path(httpServletRequest.getRequestURI())
                .status(status)
                .build();
    }
}
