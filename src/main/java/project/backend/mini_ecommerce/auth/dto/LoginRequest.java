package project.backend.mini_ecommerce.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequest {
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email can not be blank")
    private final String email;

    @NotBlank(message = "Password can not be blank")
    private final String password;
}
