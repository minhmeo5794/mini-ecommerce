package project.backend.mini_ecommerce.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import project.backend.mini_ecommerce.common.enums.UserStatus;

@Getter
@Builder
public class UpdateUserStatusRequest {
    @NotNull(message = "Status can not be null")
    private final UserStatus status;
}
