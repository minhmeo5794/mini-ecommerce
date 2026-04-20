package project.backend.mini_ecommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterRequest {
    @Email(message = "Email is not valid")
    @NotNull(message = "Email can not be null")
    @Size(message = "Email must be between 6 and 100 characters", min = 6, max = 100)
    private String email;

    @NotNull(message = "Full name can not be null")
    @Size(message = "Full name must be between 6 and 100 characters", min = 6, max = 100)
    private String fullName;

    @NotNull(message = "Password can not be null")
    @Size(message = "Password must have at least 6 characters", min = 6)
    private String password;

    @NotBlank(message = "Confirm can not be blank")
    private String confirmPassword;
}
