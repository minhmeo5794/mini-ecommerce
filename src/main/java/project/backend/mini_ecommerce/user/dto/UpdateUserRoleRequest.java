package project.backend.mini_ecommerce.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import project.backend.mini_ecommerce.common.enums.UserRole;

@Getter
@Builder
public class UpdateUserRoleRequest {
    @NotNull(message = "Role can not be null")
    private final UserRole role;
}
