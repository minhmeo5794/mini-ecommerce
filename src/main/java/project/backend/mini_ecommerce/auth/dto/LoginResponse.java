package project.backend.mini_ecommerce.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.backend.mini_ecommerce.common.enums.UserRole;

@Builder
@Getter
public class LoginResponse {
    private final UserInfo user;
    private final TokenInfo token;

    @Builder
    @Getter
    @RequiredArgsConstructor
    public static class UserInfo {
        private final Long id;
        private final String email;
        private final String fullName;
        private final UserRole role;
    }

    @Builder
    @Getter
    @RequiredArgsConstructor
    public static class TokenInfo {
        private final String accessToken;
        private final String tokenType;
        private final long expiresIn;
    }
}
