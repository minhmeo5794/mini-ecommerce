package project.backend.mini_ecommerce.mapper;

import project.backend.mini_ecommerce.dto.response.LoginResponse;
import project.backend.mini_ecommerce.dto.response.RegisterResponse;
import project.backend.mini_ecommerce.model.User;

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
                .userInfo(LoginResponse.UserInfo.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .fullName(user.getFullName())
                        .role(user.getRole())
                        .build())
                .tokenInfo(LoginResponse.TokenInfo.builder()
                        .token(token)
                        .tokenType("Bearer")
                        .expiresIn(accessTokenExpiresIn)
                        .build())
                .build();
    }
}
