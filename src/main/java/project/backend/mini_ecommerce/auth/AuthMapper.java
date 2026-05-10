package project.backend.mini_ecommerce.auth;

import org.springframework.stereotype.Component;
import project.backend.mini_ecommerce.auth.dto.LoginResponse;
import project.backend.mini_ecommerce.auth.dto.RegisterRequest;
import project.backend.mini_ecommerce.auth.dto.RegisterResponse;
import project.backend.mini_ecommerce.user.User;

@Component
public class AuthMapper {
    public User toEntity(RegisterRequest request, String encodedPassword) {
        return User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(encodedPassword)
                .build();
    }

    public RegisterResponse toRegisterResponse(User user) {
        return RegisterResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public LoginResponse toLoginResponse(User user, String token, long accessTokenExpiresIn) {
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
