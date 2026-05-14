package project.backend.mini_ecommerce.auth.dto;

import lombok.Builder;
import lombok.Getter;
import project.backend.mini_ecommerce.common.enums.UserRole;

@Builder
@Getter
public class LoginResponse {
    private final UserInfo user;
    private final TokenInfo token;

    @Builder
    @Getter
    public static class UserInfo {
        private final Long id;
        private final String email;
        private final String fullName;
        private final String phoneNumber;
        private final UserRole role;
    }

    @Builder
    @Getter
    public static class TokenInfo {
        private final String accessToken;
        private final String tokenType;
        private final long expiresIn;
    }
}
