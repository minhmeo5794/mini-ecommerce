package project.backend.mini_ecommerce.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.backend.mini_ecommerce.common.enums.UserRole;

@Getter
@RequiredArgsConstructor
public class UpdateUserRoleRequest {
    @NotNull(message = "Role can not be null")
    private final UserRole role;
}
