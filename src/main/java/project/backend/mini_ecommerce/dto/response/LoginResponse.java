package project.backend.mini_ecommerce.dto.response;

import lombok.Builder;
import lombok.Getter;
import project.backend.mini_ecommerce.enums.Role;

@Builder
@Getter
public class LoginResponse {
    private final Long id;
    private final String email;
    private final String fullName;
    private final Role role;
}
