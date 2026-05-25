package project.backend.mini_ecommerce.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.backend.mini_ecommerce.common.enums.UserStatus;

@Getter
@RequiredArgsConstructor
public class UpdateUserStatusRequest {
    @NotNull(message = "Status can not be null")
    private final UserStatus status;
}
