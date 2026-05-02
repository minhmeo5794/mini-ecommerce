package project.backend.mini_ecommerce.auth.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class RegisterResponse {
    private final Long id;
    private final String email;
    private final String fullName;
    private final Instant createdAt;
}
