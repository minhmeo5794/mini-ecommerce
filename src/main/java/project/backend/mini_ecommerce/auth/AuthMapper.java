package project.backend.mini_ecommerce.auth;

import project.backend.mini_ecommerce.auth.dto.LoginResponse;
import project.backend.mini_ecommerce.auth.dto.RegisterResponse;
import project.backend.mini_ecommerce.user.User;

public class AuthMapper {
    public static RegisterResponse toRegisterResponse(User user) {
        return RegisterResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static LoginResponse toLoginResponse(User user, String token, long accessTokenExpiresIn) {
        return LoginResponse.builder()
                .user(LoginResponse.UserInfo.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .fullName(user.getFullName())
                        .role(user.getRole())
                        .build())
                .token(LoginResponse.TokenInfo.builder()
                        .accessToken(token)
                        .tokenType("Bearer")
                        .expiresIn(accessTokenExpiresIn)
                        .build())
                .build();
    }
}
