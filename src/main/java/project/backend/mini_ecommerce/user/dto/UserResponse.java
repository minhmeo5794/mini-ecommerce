package project.backend.mini_ecommerce.user.dto;

import lombok.Builder;
import lombok.Getter;
import project.backend.mini_ecommerce.common.enums.UserRole;
import project.backend.mini_ecommerce.common.enums.UserStatus;

@Getter
@Builder
public class UserResponse {
    private final Long id;
    private final String email;
    private final String fullName;
    private final String phoneNumber;
    private final UserRole role;
    private final UserStatus status;
}
