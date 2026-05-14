package project.backend.mini_ecommerce.user;

import org.springframework.stereotype.Component;
import project.backend.mini_ecommerce.user.dto.UserResponse;

@Component
public class UserMapper {
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .build();
    }
}
