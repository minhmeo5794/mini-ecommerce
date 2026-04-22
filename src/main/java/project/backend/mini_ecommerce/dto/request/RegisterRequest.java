package project.backend.mini_ecommerce.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterRequest {
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email can not be blank")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private final String email;

    @NotBlank(message = "Full name can not be blank")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private final String fullName;

    @NotBlank(message = "Password can not be blank")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private final String password;

    @NotBlank(message = "Confirm can not be blank")
    private final String confirmPassword;
}
