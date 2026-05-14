package project.backend.mini_ecommerce.user;

import org.springframework.stereotype.Component;
import project.backend.mini_ecommerce.user.dto.CreateUserRequest;
import project.backend.mini_ecommerce.user.dto.UserResponse;

@Component
public class UserMapper {
    public User toEntity(CreateUserRequest request, String encodedPassword) {
        return User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .password(encodedPassword)
                .build();
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }
}
