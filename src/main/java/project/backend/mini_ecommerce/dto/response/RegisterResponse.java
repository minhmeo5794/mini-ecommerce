package project.backend.mini_ecommerce.dto.response;

import lombok.Builder;
import lombok.Getter;
import project.backend.mini_ecommerce.enums.Role;

@Builder
@Getter
public class RegisterResponse {
    private Long id;
    private String email;
    private String fullName;
    private Role role;
}
