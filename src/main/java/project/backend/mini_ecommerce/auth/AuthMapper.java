package project.backend.mini_ecommerce.auth;

import project.backend.mini_ecommerce.auth.dto.LoginResponse;
import project.backend.mini_ecommerce.auth.dto.RegisterResponse;
import project.backend.mini_ecommerce.user.User;

public class AuthMapper {
    public static RegisterResponse mapToRegisterResponse(User user) {
        return RegisterResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static LoginResponse mapToLoginResponse(User user, String token, long accessTokenExpiresIn) {
        return LoginResponse.builder()
                .userInfo(LoginResponse.UserInfo.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .fullName(user.getFullName())
                        .userRole(user.getUserRole())
                        .build())
                .tokenInfo(LoginResponse.TokenInfo.builder()
                        .token(token)
                        .tokenType("Bearer")
                        .expiresIn(accessTokenExpiresIn)
                        .build())
                .build();
    }
}
